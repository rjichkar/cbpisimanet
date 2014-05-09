/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.Timer;

/**
 *
 * @author SHRI
 */
public class SplashScreenNew extends JDialog implements ActionListener {

    private SplashPane splashPane;
    private Timer timer;

    public SplashScreenNew(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        timer = new Timer(3000, this);
        setLayout(null);
        setUndecorated(true);
        setSize(500, 288);

        splashPane = new SplashPane();
        add(splashPane);

        setLocationRelativeTo(null);
        timer.start();
    }

    public static void main(String[] args) {
        new SplashScreenNew(null,true).setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }

}
