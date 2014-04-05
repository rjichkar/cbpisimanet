/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.logger;

import gui.scenario.ScenarioPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author SHRI
 */
public class EventLoggerFrame extends JFrame {

    private EventLoggerPane eventLoggerPane;
    private ScenarioPane scenarioPane;

    public EventLoggerFrame(ScenarioPane scenarioPane) {
        initComponents(scenarioPane);
    }

    private void initComponents(ScenarioPane scenarioPane) {
        this.scenarioPane = scenarioPane;
        eventLoggerPane = scenarioPane.getEventLoggerPane();
        setTitle("Event Viewer");
        setSize(new java.awt.Dimension(990, 250));
        setResizable(false);
        setLocationRelativeTo(null);
        setFocusableWindowState(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e); //To change body of generated methods, choose Tools | Templates.
                onDefaultClose();
            }
        });
    }

    private void onDefaultClose() {
        eventLoggerPane.setBounds(0, 530, 1290, 200);
        scenarioPane.add(eventLoggerPane);
    }

    public static void main(String... args) {
        new EventLoggerFrame(null).setVisible(true);
    }
}
