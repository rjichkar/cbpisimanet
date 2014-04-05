/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.graphs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author SHRI
 */
public class SFGraphPane extends JPanel {

    private final int HIST_SCALE = 10;
    private final int ALIGN_Y = 50;
    private ArrayList<Double> smoothingFactorList;
    private int highestSFIndex;
    private JLabel graphLabel;

    public SFGraphPane() {
        initComponents();
    }

    private void initComponents() {
        graphLabel = new JLabel();
        graphLabel.setBounds(200, ALIGN_Y - 30, 600, 30);
        setLayout(null);
        setBackground(Color.WHITE);
        add(graphLabel);
    }

    //W=645 H=350
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

        Graphics2D g2d = (Graphics2D) g;

        //Plot X-Y Plane
        g2d.drawRect(50, 10 + ALIGN_Y, 900, 340);

        //Plot Markings on Y Axis
        int markY = HIST_SCALE;
        g2d.drawString("0", 30, 355 + ALIGN_Y);
        for (int y = 0; y < 300; y += 50) {
            g2d.drawString("" + markY, 30, 305 - y + ALIGN_Y);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawLine(48, 300 - y + ALIGN_Y, 950, 300 - y + ALIGN_Y);
            g2d.setColor(Color.BLACK);
            markY += HIST_SCALE;
        }
        g2d.drawString("Y-AXIS (SMOOTHING FACTOR)", 10, 40);

        //Plot Markings on X Axis
        for (int x = 0; x < 600; x += 50) {
            g2d.drawLine(50 + x, 348 + ALIGN_Y, 50 + x, 352 + ALIGN_Y);
        }
        g2d.drawString("X-AXIS (SR DOMAIN)", 840, 440);

        //Draw Histogram
        drawHistogram(smoothingFactorList, highestSFIndex, g2d);
    }

    public void drawHistogram(ArrayList<Double> smoothingFactorList, int highestSFIndex, Graphics2D g2d) {
        int x = 0;
        int magnitudes[] = new int[smoothingFactorList.size()];
        for (int i = 0; i < smoothingFactorList.size(); i++) {
            magnitudes[i] = (int) Math.round(smoothingFactorList.get(i) * 5);
            //   System.out.println("MAGNITUDE::"+magnitudes[i]);
        }
        for (int i = 0; i < magnitudes.length; i++) {
            if (i == highestSFIndex) {
                g2d.setColor(Color.BLUE);
            } else {
                g2d.setColor(Color.DARK_GRAY);
            }
            g2d.fillRect(80 + x, 350 - magnitudes[i] + ALIGN_Y, 40, magnitudes[i]);
            g2d.drawString("SF=" + smoothingFactorList.get(i), 80 + x, 350 - magnitudes[i] + 30);
            g2d.drawString("SRDomain-" + (i + 1), 65 + x, 420);
            x += 100;
        }
    }

    public void setHistogramParams(ArrayList<Double> smoothingFactorList, int highestSFIndex, String graphHeading) {
        this.smoothingFactorList = smoothingFactorList;
        this.highestSFIndex = highestSFIndex;
        graphLabel.setText(graphHeading);
    }

    public JLabel getGraphLabel() {
        return graphLabel;
    }

}
