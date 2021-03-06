package nl.tue.win.vcp.virtualbreitenbergenvironment.model.vehicles;

import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.*;
import static javax.media.opengl.GL2GL3.*;

import nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors.RandomSensor;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors.Sensor;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Rectangle;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Graphics;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Two-wheel vehicle.
 *
 * @author maikel
 */
public class TwoWheelVehicle extends Vehicle {

    private final float wheelDistance = 1;
    private final float wheelRadius = 0.5f;
    private int inactivityCounter = INACTIVITY_THRESHOLD - 1;
    private final static int INACTIVITY_THRESHOLD = 100;
    private final RandomSensor[] randomSlots = {new RandomSensor(), new RandomSensor()};
    private final float EPS = 0.001f;

    public TwoWheelVehicle(Vector initialPosition, float initialAngle) {
        this.position = initialPosition;
        this.angle = initialAngle;
        this.slots = new Sensor[2];
    }
    
    @Override
    public void draw(GL2 gl) {
        super.drawBoundingBox(gl);
        super.loadName(gl);
        // position is middle between wheels (behind)

        gl.glPushMatrix();
        gl.glPushAttrib(GL_CURRENT_BIT);
        
        // Lift everything up high enough for the wheels to touch the ground
        gl.glTranslated(0, 0, wheelRadius);

        // Line to indicate direction the vehicle points in
        gl.glColor3f(1, 0, 0);
        gl.glBegin(GL_LINES);
        gl.glVertex3dv(position.asBuffer());
        gl.glVertex3dv(position.plus(getDirection()).asBuffer());
        gl.glEnd();

        // Move / rotate to correct position
        double deg = Math.toDegrees(angle);
        gl.glTranslated(position.x(), position.y(), position.z());
        gl.glRotated(deg, 0, 0, 1);

        // Draw wheels
        gl.glPushMatrix();
        gl.glColor3f(0, 0, 0);
        gl.glTranslatef(this.wheelDistance * -0.5f, 0, 0);
        Graphics.drawDisk(gl, wheelRadius, 50);
        gl.glTranslatef(this.wheelDistance, 0, 0);
        Graphics.drawDisk(gl, wheelRadius, 50);
        gl.glPopMatrix();

        gl.glPopAttrib();
        gl.glPopMatrix();

        for (Sensor s : slots) {
            s.draw(gl);
        }
        
        super.unloadName(gl);
    }

    @Override
    public void move() {
        // Determine how much the wheels turn
        final float leftWheel = slots[0].getValue(position, angle);
        final float rightWheel = slots[1].getValue(position, angle);

        if (leftWheel < EPS && rightWheel < EPS) {
            inactivityCounter++;
        } else {
            inactivityCounter = 0;
        }

        if (inactivityCounter < INACTIVITY_THRESHOLD || !RANDOM) {
            updateLocation(leftWheel, rightWheel);
            for (RandomSensor r : randomSlots) {
                r.reset();
            }
        } else {
            // revert to random behaviour
            final float leftWheelR = randomSlots[0].getValue(position, angle);
            final float rightWheelR = randomSlots[1].getValue(position, angle);
            updateLocation(leftWheelR, rightWheelR);
        }
    }

    private void updateLocation(final float leftWheel, final float rightWheel) {
        if (leftWheel == rightWheel) {
            // move forward in a straight line by the amount leftWheel
            this.position = this.position.plus(this.getDirection().scale(leftWheel));
        } else if (leftWheel == 0) {
            final float rotationAngle = rightWheel / wheelDistance;
            final Vector direction = this.getDirection().cross(Vector.Z);
            final float magnitude = -0.5f * wheelDistance;
            final Vector leftWheelPosition = this.position.plus(direction.scale(magnitude));
            this.position = Vector.rotate(position, leftWheelPosition, rotationAngle);
            angle += rotationAngle;
        } else if (rightWheel == 0) {
            final float rotationAngle = -(leftWheel / wheelDistance);
            final Vector direction = this.getDirection().cross(Vector.Z);
            final float magnitude = 0.5f * wheelDistance;
            final Vector rightWheelPosition = this.position.plus(direction.scale(magnitude));
            this.position = Vector.rotate(position, rightWheelPosition, rotationAngle);
            angle += rotationAngle;
        } else {
            final float r; // radius
            final boolean left = (leftWheel > rightWheel);
            if (left) {
                // determine the radius of the turning circle
                r = determineRadius(leftWheel, rightWheel, wheelDistance);
            } else {
                // Switch a and b around
                r = determineRadius(rightWheel, leftWheel, wheelDistance);
            }

            // Now determine the point to rotate around.
            final int sign = left ? 1 : -1;
            final Vector direction = this.getDirection().cross(Vector.Z);
            final float magnitude = sign * (r + 0.5f * wheelDistance);
            final Vector centerPoint = this.position.plus(direction.scale(magnitude));
            final float rotationAngle = left ? -rightWheel / r : leftWheel / r;
            this.position = Vector.rotate(this.position, centerPoint, rotationAngle);
            angle += rotationAngle;
        }
    }

    private static float determineRadius(float a, float b, float d) {
        // We know the the angle of a on a circle with radius r+d equals
        // the angle of b on a circle with radius r. Then a / (r+d) == b/r.
        // Therefore, r = bd / (a-b)
        // http://www.wolframalpha.com/input/?i=a%2F%28r%2Bd%29%3Db%2Fr
        assert a - b != 0 : "Wheels rotate by the same amount; infinite radius";
        assert a * b * d != 0 : "Wheel distance or rotation is 0.";
        return b * d / (a - b);
    }

    @Override
    public Vector[] getSensorLocations() {
        final Vector wheelDirection = INITIAL_DIRECTION.cross(Vector.Z);
        final Vector wheelVector = wheelDirection.normalized().scale(0.5 * wheelDistance);
        return new Vector[]{wheelVector, wheelVector.scale(-1)};
    }

    @Override
    public void move(Vector movement) {
        this.position = this.position.plus(movement);
    }

    @Override
    public String toString() {
        return "Two-wheel vehicle at" + position;
    }

    @Override
    public Rectangle getBoundingBox() {
        final Vector direction = this.getDirection();
        final Vector wheelDirection = this.getDirection().cross(Vector.Z);
        
        final Vector upperRight = position.plus(direction.normalized().scale(0.5)).plus(wheelDirection.normalized().scale(0.5));
        final Vector upperLeft = position.plus(direction.normalized().scale(0.5)).minus(wheelDirection.normalized().scale(0.5));
        final Vector lowerRight = position.minus(direction.normalized().scale(0.5)).plus(wheelDirection.normalized().scale(0.5));
        final Vector lowerLeft = position.minus(direction.normalized().scale(0.5)).minus(wheelDirection.normalized().scale(0.5));
        
        return new Rectangle(direction, upperRight, upperLeft, lowerLeft, lowerRight);
    }
}
