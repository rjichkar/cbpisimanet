/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.graphs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author RJ
 */
public class EnergyTrendPane extends JPanel {

    private final String X_AXIS_QUANTITY = "EXT. NODE COUNT";
    private final String Y_AXIS_QUANTITY = "ENERGY Joules";
    private final double HIST_SCALE = 3.0;
    private final int SCALE_MULTIPLIER = 17;
    private final int SHIFT_Y = 50;
    private final Color barColor = new Color(153, 76, 0);
    private JLabel graphLabel;
    private ArrayList<Integer> memberNodeCountList;
    private ArrayList<Integer> externalNodeCountList;
    private ArrayList<Double> powerValuesList;
    private DecimalFormat df = new DecimalFormat("#.##");

    public EnergyTrendPane() {
        initComponents();
        testData();
    }

    public EnergyTrendPane(ArrayList<Integer> memberNodeCountList, ArrayList<Integer> externalNodeCountList, ArrayList<Double> powerValuesList) {
        this.memberNodeCountList = memberNodeCountList;
        this.externalNodeCountList = externalNodeCountList;
        this.powerValuesList = powerValuesList;
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setBackground(Color.WHITE);
        graphLabel = new JLabel("EXT.NODE COUNT VS ENERGY");
        graphLabel.setBounds(400, SHIFT_Y - 30, 250, 30);
        add(graphLabel);
    }

    private void testData() {
        memberNodeCountList = new ArrayList<>();
        memberNodeCountList.add(30);
        memberNodeCountList.add(30);
        memberNodeCountList.add(30);

        externalNodeCountList = new ArrayList<>();
        externalNodeCountList.add(20);
        externalNodeCountList.add(50);
        externalNodeCountList.add(50);

        powerValuesList = new ArrayList<>();
        powerValuesList.add(1.0);
        powerValuesList.add(1.5);
        powerValuesList.add(3.0);
    }

    //W=645 H=350
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

        Graphics2D g2d = (Graphics2D) g;

        //Plot X-Y Plane
        g2d.drawRect(50, 10 + SHIFT_Y, 900, 340);

        //Plot Markings on Y Axis
        double markY = HIST_SCALE;
        g2d.drawString("0", 30, 355 + SHIFT_Y);
        for (int y = 0; y < 300; y += 50) {
            g2d.drawString("" + markY, 25, 305 - y + SHIFT_Y);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawLine(48, 300 - y + SHIFT_Y, 950, 300 - y + SHIFT_Y);
            g2d.setColor(Color.BLACK);
            markY += HIST_SCALE;
        }
        g2d.drawString("Y-AXIS (" + Y_AXIS_QUANTITY + ")", 10, 40);

        //Plot Markings on X Axis
        for (int x = 0; x < 900; x += 50) {
            g2d.drawLine(50 + x, 348 + SHIFT_Y, 50 + x, 352 + SHIFT_Y);
        }
        g2d.drawString("X-AXIS (" + X_AXIS_QUANTITY + ")", 840, 460);

        //Draw Histogram
        System.out.println("MEM NODE LIST LEN:" + memberNodeCountList.size() + " EXT NODE LIST LEN:" + externalNodeCountList.size() + " POWER LIST LEN:" + powerValuesList.size());
        drawHistogram(memberNodeCountList, externalNodeCountList, powerValuesList, 0, g2d);
    }

    public void drawHistogram(ArrayList<Integer> memberNodeCountList, ArrayList<Integer> externalNodeCountList, ArrayList<Double> powerValuesList, int highestSFIndex, Graphics2D g2d) {
        int x = 0;

        for (int i = 0; i < externalNodeCountList.size(); i++) {
            if (i == highestSFIndex) {
                // g2d.setColor(Color.BLUE);
            } else {
                // g2d.setColor(Color.DARK_GRAY);
            }
            g2d.setColor(barColor);
            int scaleMagnitude = (int) (powerValuesList.get(i) * SCALE_MULTIPLIER);
            g2d.fillRect(80 + x, 350 - scaleMagnitude + SHIFT_Y, 40, scaleMagnitude);
            g2d.setColor(Color.BLACK);
            g2d.drawString("E=" + df.format(powerValuesList.get(i)) + " J", 80 + x, 350 - scaleMagnitude + 30);
            g2d.drawString("EXT.N=" + externalNodeCountList.get(i), 73 + x, 420);
            g2d.drawString("MEM.N=" + memberNodeCountList.get(i), 73 + x, 440);
            x += 100;
        }
    }

}
