/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author SHRI
 */
public class SplashPane extends JPanel {

    private java.util.Timer progressTimer;
    private java.util.Timer progressStatusTimer;
    private JProgressBar progressBar;
    private String progress[] = {"Loading modules...", "Done loading modules....", "Starting Main Window..."};
    private int progressBarStatus;
    private int progressStatus;
    private ImageIcon splashBackgroundIcon;

    public SplashPane() {
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setSize(500, 288);

        splashBackgroundIcon = new ImageIcon(getClass().getResource("/images/splash.jpg"));
        progressBar = new JProgressBar();
        progressBar.setForeground(new java.awt.Color(255, 51, 51));
        progressBar.setBounds(0, 240, 540, 5);
        add(progressBar);
  
        startProgressStatusTimer(50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(splashBackgroundIcon.getImage(), 0, 0, null);
        progressBar.setValue(progressBarStatus);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 12));
        g2d.setColor(Color.WHITE);
        
        //Update Progress Status
        g2d.drawString(progress[progressStatus], 10, 220);
        //Static Owner
       // g2d.drawString("All rights are reserved. RAJ Softech Pvt.Ltd", 110, 270);
    }

    private void startProgressStatusTimer(int delay) {
        progressStatusTimer = new java.util.Timer();
        progressStatusTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(progressBarStatus<=25){
                    progressStatus=0;
                }else if(progressBarStatus>25 && progressBarStatus<=50){
                    progressStatus=1;
                }else{
                    progressStatus=2;
                }
                
                progressBarStatus++;
                repaint();
            }
        }, 0, delay);
    }
}
