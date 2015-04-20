package nl.tue.win.vcp.virtualbreitenbergenvironment.gui;

import com.jogamp.opengl.util.FPSAnimator;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.KeyStroke.getKeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import nl.tue.win.vcp.virtualbreitenbergenvironment.io.Serialization;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.Environment;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Movable;
import nl.tue.win.vcp.virtualbreitenbergenvironment.opengl.EnvironmentContainer;
import nl.tue.win.vcp.virtualbreitenbergenvironment.opengl.EnvironmentMover;
import nl.tue.win.vcp.virtualbreitenbergenvironment.opengl.GLEventListenerImpl;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.VirtualCamera;

/**
 *
 * @author maikel
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        initActions();
        GLEventListenerImpl listener = new GLEventListenerImpl();
        ec = listener;
        em = listener;
        camera = listener;
        GLJPanel glPanel = (GLJPanel) jPanel1;
        glPanel.addGLEventListener(listener);
        glPanel.addMouseListener(listener);
        glPanel.addMouseMotionListener(listener);
        glPanel.addMouseWheelListener(listener);
        glPanel.setFocusable(true);
        glPanel.requestFocusInWindow();
        // Attach animator to OpenGL panel and begin refresh
        // at the specified number of frames per second.
        final FPSAnimator animator
                = new FPSAnimator((GLJPanel) glPanel, FPS, true);
        animator.setIgnoreExceptions(false);
        animator.setPrintExceptions(true);

        animator.start();

        // Stop animator when window is closed.
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                animator.stop();
            }
        });

        listener.setListener(new GLEventListenerImpl.SelectionListener() {

            @Override
            public void selectionChanged(Movable selection) {
                MainFrame.this.selection = selection;
                deleteAction.setEnabled(selection != Movable.NULL);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        jPanel1 = new GLJPanel(glcapabilities);
        jMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        toolsMenu = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        timeMenu = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Virtual Braitenberg Environment");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 904, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 807, Short.MAX_VALUE)
        );

        fileMenu.setText("File");

        jMenuItem1.setText("New environment");
        fileMenu.add(jMenuItem1);

        jMenuItem5.setText("Load...");
        fileMenu.add(jMenuItem5);

        jMenuItem6.setText("Save...");
        fileMenu.add(jMenuItem6);

        jMenuItem8.setText("Quit");
        fileMenu.add(jMenuItem8);

        jMenuBar.add(fileMenu);

        editMenu.setText("Edit");

        jMenuItem2.setText("Add vehicle...");
        editMenu.add(jMenuItem2);

        jMenuItem7.setText("Add light...");
        editMenu.add(jMenuItem7);

        jMenuItem9.setText("Add heat source...");
        editMenu.add(jMenuItem9);

        jMenuItem10.setText("Delete selection");
        editMenu.add(jMenuItem10);

        jMenuBar.add(editMenu);

        viewMenu.setText("View");

        jMenu8.setText("Movement");

        jMenuItem15.setText("Move forwards");
        jMenu8.add(jMenuItem15);

        jMenuItem16.setText("Move backwards");
        jMenu8.add(jMenuItem16);

        jMenuItem17.setText("Move left");
        jMenu8.add(jMenuItem17);

        jMenuItem18.setText("Move right");
        jMenu8.add(jMenuItem18);

        jMenuItem19.setText("Move up");
        jMenu8.add(jMenuItem19);

        jMenuItem20.setText("Move down");
        jMenu8.add(jMenuItem20);

        viewMenu.add(jMenu8);

        jMenu9.setText("Zooming");

        jMenuItem13.setText("Zoom in");
        jMenu9.add(jMenuItem13);

        jMenuItem14.setText("Zoom out");
        jMenu9.add(jMenuItem14);

        viewMenu.add(jMenu9);

        jMenuItem11.setText("Reset camera");
        viewMenu.add(jMenuItem11);

        jMenuBar.add(viewMenu);

        toolsMenu.setText("Tools");

        jMenuItem12.setText("Options...");
        toolsMenu.add(jMenuItem12);

        jMenuBar.add(toolsMenu);

        timeMenu.setText("Time");
        timeMenu.setEnabled(false);

        jMenuItem3.setText("Pause");
        jMenuItem3.setEnabled(false);
        timeMenu.add(jMenuItem3);

        jMenuItem4.setText("Play");
        jMenuItem4.setEnabled(false);
        timeMenu.add(jMenuItem4);

        jMenuBar.add(timeMenu);

        helpMenu.setText("Help");

        jMenuItem22.setText("Open online user manual...");
        helpMenu.add(jMenuItem22);

        jMenuItem21.setText("About...");
        helpMenu.add(jMenuItem21);

        jMenuBar.add(helpMenu);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    private void initActions() {
        newAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        addVehicleAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        loadAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        saveAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        addLightAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        quitAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        addHeatSourceAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        deleteAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        resetCameraAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_0, java.awt.event.InputEvent.CTRL_MASK));
        showOptionsAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK | java.awt.event.KeyEvent.SHIFT_MASK));
        zoomInAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_EQUALS, java.awt.event.InputEvent.CTRL_MASK));
        zoomOutAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_MINUS, java.awt.event.InputEvent.CTRL_MASK));
        moveForwardsAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_W, 0));
        moveLeftAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_A, 0));
        moveBackwardsAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_S, 0));
        moveRightAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_D, 0));
        moveUpAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_Q, 0));
        moveDownAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_Z, 0));
        helpAction.putValue(Action.ACCELERATOR_KEY, getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));

        deleteAction.setEnabled(false);

        jMenuItem1.setAction(newAction);
        jMenuItem2.setAction(addVehicleAction);
        jMenuItem5.setAction(loadAction);
        jMenuItem6.setAction(saveAction);
        jMenuItem7.setAction(addLightAction);
        jMenuItem8.setAction(quitAction);
        jMenuItem9.setAction(addHeatSourceAction);
        jMenuItem10.setAction(deleteAction);
        jMenuItem11.setAction(resetCameraAction);
        jMenuItem12.setAction(showOptionsAction);
        jMenuItem13.setAction(zoomInAction);
        jMenuItem14.setAction(zoomOutAction);
        jMenuItem15.setAction(moveForwardsAction);
        jMenuItem16.setAction(moveBackwardsAction);
        jMenuItem17.setAction(moveLeftAction);
        jMenuItem18.setAction(moveRightAction);
        jMenuItem19.setAction(moveUpAction);
        jMenuItem20.setAction(moveDownAction);
        jMenuItem21.setAction(aboutAction);
        jMenuItem22.setAction(helpAction);
    }

    private final Action saveAction = new AbstractAction("Save...") {

        @Override
        public void actionPerformed(ActionEvent e) {
            Environment environment = ec.getEnvironment();

            JFileChooser fc = new JFileChooser();
            fc.setDialogType(JFileChooser.SAVE_DIALOG);
            final FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Braitenberg Environment", "env");
            fc.setFileFilter(filter);
            final int showSaveDialog = fc.showSaveDialog(MainFrame.this);

            File outputFile = fc.getSelectedFile();
            if (fc.getFileFilter().equals(filter)) {
                if (outputFile != null && !outputFile.toString().endsWith(".env")) {
                    outputFile = new File(outputFile.toString() + ".env");
                }
            }
            // TODO: add confirmation
            if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
                try {
                    Serialization.write(environment, outputFile);

                } catch (IOException ex) {
                    System.err.println("While writing: " + ex);
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "There was a problem when writing to the "
                            + "specified file.");
                }
            }

        }
    };

    private final Action loadAction = new AbstractAction("Load...") {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            fc.setDialogType(JFileChooser.OPEN_DIALOG);
            final FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Braitenberg Environment", "env");
            fc.setFileFilter(filter);
            final int showOpenDialog = fc.showOpenDialog(MainFrame.this);
            final File selectedFile = fc.getSelectedFile();
            if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
                try {
                    ec.setEnvironment((Environment) Serialization.read(selectedFile));
                    additionFrame.dispose(); // close any open windows
                } catch (IOException | ClassNotFoundException ex) {
                    System.err.println("While reading: " + ex);
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "The selected file could not be read.");
                }
            }

        }
    };

    private final Action newAction = new AbstractAction("New environment") {

        @Override
        public void actionPerformed(ActionEvent e) {
            additionFrame.dispose(); // close any open windows
            ec.setEnvironment(new Environment());
        }
    };

    private final Action addVehicleAction = new AbstractAction("Add vehicle...") {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (additionFrame == null || !additionFrame.isVisible()) {
                additionFrame = new AddVehicleFrame(ec.getEnvironment(), em);
                additionFrame.setVisible(true);
            }
        }
    };

    private final Action addLightAction = new AbstractAction("Add light source...") {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (additionFrame == null || !additionFrame.isVisible()) {
                additionFrame = new AddLightFrame(ec.getEnvironment(), em);
                additionFrame.setVisible(true);
            }
        }
    };

    private final Action quitAction = new AbstractAction("Quit") {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (additionFrame != null) {
                additionFrame.dispose(); // close any open windows
            }
            MainFrame.this.dispose();
            System.exit(0);
        }
    };

    private final Action addHeatSourceAction = new AbstractAction("Add heat source...") {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (additionFrame == null || !additionFrame.isVisible()) {
                additionFrame = new AddHeatFrame(ec.getEnvironment(), em);
                additionFrame.setVisible(true);
            }
        }
    };

    private final Action deleteAction = new AbstractAction("Delete selection") {

        @Override
        public void actionPerformed(ActionEvent e) {
            ec.getEnvironment().removeObject(selection);
            em.select(Movable.NULL);
        }
    };

    private final Action resetCameraAction = new AbstractAction("Reset camera") {

        @Override
        public void actionPerformed(ActionEvent e) {
            camera.resetCamera();
        }
    };

    private final Action showOptionsAction = new AbstractAction("Options...") {

        @Override
        public void actionPerformed(ActionEvent e) {
            OptionsFrame.getInstance().setVisible(true);
        }
    };

    private final Action zoomInAction = new AbstractAction("Zoom in") {

        @Override
        public void actionPerformed(ActionEvent e) {
            camera.zoomIn();
        }
    };

    private final Action zoomOutAction = new AbstractAction("Zoom out") {

        @Override
        public void actionPerformed(ActionEvent e) {
            camera.zoomOut();
        }
    };

    private final Action moveUpAction = new AbstractAction("Move up") {

        @Override
        public void actionPerformed(ActionEvent e) {
            camera.moveUp();
        }
    };

    private final Action moveDownAction = new AbstractAction("Move down") {

        @Override
        public void actionPerformed(ActionEvent e) {
            camera.moveDown();
        }
    };

    private final Action moveLeftAction = new AbstractAction("Move left") {

        @Override
        public void actionPerformed(ActionEvent e) {
            camera.moveLeft();
        }
    };

    private final Action moveRightAction = new AbstractAction("Move right") {

        @Override
        public void actionPerformed(ActionEvent e) {
            camera.moveRight();
        }
    };

    private final Action moveForwardsAction = new AbstractAction("Move forwards") {

        @Override
        public void actionPerformed(ActionEvent e) {
            camera.moveForwards();
        }
    };

    private final Action moveBackwardsAction = new AbstractAction("Move backwards") {

        @Override
        public void actionPerformed(ActionEvent e) {
            camera.moveBackwards();
        }
    };

    private final Action aboutAction = new AbstractAction("About") {

        @Override
        public void actionPerformed(ActionEvent e) {
            final Object[] options = {"Close"};
            JOptionPane.showOptionDialog(MainFrame.this,
                    "Virtual Braitenberg Environment\nVisual computing project",
                    "About",
                    JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, //do not use a custom Icon
                    options, //the titles of buttons
                    options[0]); //default button title
        }
    };

    private final Action helpAction = new AbstractAction("Open online user manual...") {
        final private String URL
                = "http://maikelsteneker.github.io/BraitenbergUserManual";

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Desktop.getDesktop().browse(new URI(URL));
            } catch (IOException | URISyntaxException ex) {
            }
        }
    };

    private final static int FPS = 30;
    private final EnvironmentContainer ec;
    private final EnvironmentMover em;
    private final VirtualCamera camera;
    private Movable selection = Movable.NULL;
    private JFrame additionFrame = null;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu timeMenu;
    private javax.swing.JMenu toolsMenu;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
}
