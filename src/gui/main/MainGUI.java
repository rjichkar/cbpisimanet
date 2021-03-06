/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.main;

import gui.scenario.ScenarioPane;
import gui.controls.ControlsToobar;
import gui.logger.EventLoggerFrame;
import gui.logger.EventLoggerPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 *
 * @author RJ
 */
public class MainGUI extends javax.swing.JFrame {

    private ScenarioPane scenarioPane;
    private ControlsToobar controlsToobar;


    /**
     * Creates new form MainWindow
     */
    public MainGUI() {
        initComponents();
        //Show Splash Screen
        new SplashScreenNew(null, true).setVisible(true);
         
        //Initialize Scenario Panel
        scenarioPane = new ScenarioPane();
        scenarioPane.setBounds(0, 50, 1290, 900);
        scenarioPane.setBorder(BorderFactory.createTitledBorder("Scenario"));
        add(scenarioPane);

        //Initialize Control Toolbar
        controlsToobar = new ControlsToobar(scenarioPane, this);
        scenarioPane.setControlsToobar(controlsToobar);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlToolbar = new javax.swing.JToolBar();
        configureInputButton = new javax.swing.JButton();
        generateScenarioButton = new javax.swing.JButton();
        playPauseToggleButton = new javax.swing.JToggleButton();
        showAnalysisDialogButton = new javax.swing.JButton();
        mainMenuBar = new javax.swing.JMenuBar();
        optionsMenu = new javax.swing.JMenu();
        configureMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        viewEventLogsMenuItem = new javax.swing.JMenuItem();
        clearLogsMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trust SIM 4.5");
        setResizable(false);
        getContentPane().setLayout(null);

        controlToolbar.setRollover(true);

        configureInputButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/configure_input.png"))); // NOI18N
        configureInputButton.setToolTipText("Configure Input");
        configureInputButton.setFocusable(false);
        configureInputButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        configureInputButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        configureInputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configureInputButtonActionPerformed(evt);
            }
        });
        controlToolbar.add(configureInputButton);

        generateScenarioButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/scenario_button_compressed.jpg"))); // NOI18N
        generateScenarioButton.setToolTipText("Generate Scenario");
        generateScenarioButton.setFocusable(false);
        generateScenarioButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        generateScenarioButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        generateScenarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateScenarioButtonActionPerformed(evt);
            }
        });
        controlToolbar.add(generateScenarioButton);

        playPauseToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/play.jpg"))); // NOI18N
        playPauseToggleButton.setToolTipText("Play/Pause Simulation");
        playPauseToggleButton.setFocusable(false);
        playPauseToggleButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playPauseToggleButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        playPauseToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playPauseToggleButtonActionPerformed(evt);
            }
        });
        controlToolbar.add(playPauseToggleButton);

        showAnalysisDialogButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/analysis_button_small.jpg"))); // NOI18N
        showAnalysisDialogButton.setToolTipText("View Analysis");
        showAnalysisDialogButton.setFocusable(false);
        showAnalysisDialogButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        showAnalysisDialogButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        showAnalysisDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAnalysisDialogButtonActionPerformed(evt);
            }
        });
        controlToolbar.add(showAnalysisDialogButton);

        getContentPane().add(controlToolbar);
        controlToolbar.setBounds(0, 0, 1290, 40);

        optionsMenu.setText("Options");
        optionsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsMenuActionPerformed(evt);
            }
        });

        configureMenuItem.setText("Configure");
        configureMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configureMenuItemActionPerformed(evt);
            }
        });
        optionsMenu.add(configureMenuItem);

        mainMenuBar.add(optionsMenu);

        viewMenu.setText("View");

        viewEventLogsMenuItem.setText("Event Logs");
        viewEventLogsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewEventLogsMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(viewEventLogsMenuItem);

        clearLogsMenuItem.setText("Clear Logs");
        clearLogsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearLogsMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(clearLogsMenuItem);

        mainMenuBar.add(viewMenu);

        setJMenuBar(mainMenuBar);

        setSize(new java.awt.Dimension(1296, 900));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clearLogsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearLogsMenuItemActionPerformed
        // TODO add your handling code here:
        scenarioPane.getEventLoggerPane().clearLogs();
    }//GEN-LAST:event_clearLogsMenuItemActionPerformed

    private void configureInputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configureInputButtonActionPerformed
        // TODO add your handling code here:
        controlsToobar.showConfigureInputDialog(true);
    }//GEN-LAST:event_configureInputButtonActionPerformed

    private void generateScenarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateScenarioButtonActionPerformed
        // TODO add your handling code here:
        controlsToobar.generateScenarioActionPerformed();
    }//GEN-LAST:event_generateScenarioButtonActionPerformed

    private void playPauseToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playPauseToggleButtonActionPerformed
        // TODO add your handling code here:
        controlsToobar.playPauseToggleButtonActionPerformed();
    }//GEN-LAST:event_playPauseToggleButtonActionPerformed

    private void showAnalysisDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showAnalysisDialogButtonActionPerformed
        // TODO add your handling code here:
        controlsToobar.showChooseAnalysisDialog(true);
    }//GEN-LAST:event_showAnalysisDialogButtonActionPerformed

    private void optionsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optionsMenuActionPerformed

    private void configureMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configureMenuItemActionPerformed
        // TODO add your handling code here:
        controlsToobar.showConfigureInputDialog(true);
    }//GEN-LAST:event_configureMenuItemActionPerformed

    private void viewEventLogsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewEventLogsMenuItemActionPerformed
        // TODO add your handling code here:
        EventLoggerFrame eventLoggerFrame = new EventLoggerFrame(scenarioPane);
        EventLoggerPane eventLoggerPane = scenarioPane.getEventLoggerPane();
        eventLoggerPane.setEventLoggerFrame(eventLoggerFrame);
        eventLoggerPane.setBounds(0, 0, 1300, 500);
        eventLoggerFrame.add(eventLoggerPane);
        eventLoggerFrame.setVisible(true);
    }//GEN-LAST:event_viewEventLogsMenuItemActionPerformed

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    public ControlsToobar getControlsToobar() {
        return controlsToobar;
    }

    public JMenuItem getClearLogsMenuItem() {
        return clearLogsMenuItem;
    }

    public JButton getConfigureInputButton() {
        return configureInputButton;
    }

    public JMenu getConfigureMenu() {
        return optionsMenu;
    }

    public JToolBar getControlToolbar() {
        return controlToolbar;
    }

    public JButton getGenerateScenarioButton() {
        return generateScenarioButton;
    }

    public JToggleButton getPlayPauseToggleButton() {
        return playPauseToggleButton;
    }

    public JMenuItem getViewEventLogsMenuItem() {
        return viewEventLogsMenuItem;
    }

    public JMenu getViewMenu() {
        return viewMenu;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem clearLogsMenuItem;
    private javax.swing.JButton configureInputButton;
    private javax.swing.JMenuItem configureMenuItem;
    private javax.swing.JToolBar controlToolbar;
    private javax.swing.JButton generateScenarioButton;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenu optionsMenu;
    private javax.swing.JToggleButton playPauseToggleButton;
    private javax.swing.JButton showAnalysisDialogButton;
    private javax.swing.JMenuItem viewEventLogsMenuItem;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
}
