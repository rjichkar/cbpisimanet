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
import java.util.Iterator;
import java.util.TreeSet;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author SHRI
 */
public class MOGraphPane extends JPanel {

    private final double HIST_SCALE = 0.2;
    private final int ALIGN_Y = 50;
    private final Color COLOR_VAR1 = new Color(82, 130, 197);
    private final Color COLOR_VAR2 = new Color(197, 81, 82);
    private final Color COLOR_VAR3 = new Color(156, 190, 90);
    private ArrayList<Double> recommendedTrustListActual;
    private ArrayList<Double> recommendedTrustListWOD;
    private ArrayList<Double> recommendedTrustListWD;
    private ArrayList<Integer> percentDishonestRAList;
    private int highestSFIndex;
    private JLabel graphLabel;
    private DecimalFormat decimalFormat;

    public MOGraphPane() {
        initComponents();
    }

    private void initComponents() {
        decimalFormat = new DecimalFormat("#.#");
        graphLabel = new JLabel();
        graphLabel.setBounds(200 + 100, ALIGN_Y - 30, 600, 30);
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
        double markY = HIST_SCALE;
        g2d.drawString("0", 30, 355 + ALIGN_Y);
        for (int y = 0; y < 300; y += 50) {
            g2d.drawString("" + decimalFormat.format(markY), 30, 305 - y + ALIGN_Y);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawLine(48, 300 - y + ALIGN_Y, 950, 300 - y + ALIGN_Y);
            g2d.setColor(Color.BLACK);
            markY += HIST_SCALE;
        }
        g2d.drawString("RECOMMENDED TRUST VALUE", 10, 40);

        //Plot Markings on X Axis
        for (int x = 0; x < 600; x += 50) {
            g2d.drawLine(50 + x, 348 + ALIGN_Y, 50 + x, 352 + ALIGN_Y);
        }
        g2d.drawString("% OF DISHONEST RECOMMENDATIONS", 700, 450);

        //Draw Histogram
        //plotAttrOnXAxis(recommendedTrustListActual.size(), 25, g2d);
        drawHistogram(recommendedTrustListActual, highestSFIndex, 0, COLOR_VAR1, g2d);
        drawHistogram(recommendedTrustListWOD, highestSFIndex, 20, COLOR_VAR2, g2d);
        drawHistogram(recommendedTrustListWD, highestSFIndex, 40, COLOR_VAR3, g2d);
    }

    public void plotAttrOnXAxis(int plotLength, int shiftX, Graphics2D g2d) {
        int x = shiftX;
        for (int i = 0; i < plotLength; i++) {
            g2d.drawString(percentDishonestRAList.get(i) + "", 65 + x, 420);
            x += 100;
        }

        int xShift = 90;
        g2d.drawString("Actual", 50 + xShift, 433 + 15);
        g2d.setColor(COLOR_VAR1);
        g2d.fillRect(90 + xShift, 420 + 15, 50, 20);
        g2d.setColor(Color.BLACK);

        g2d.drawString("Without Detection", 50 + 100 + xShift, 433 + 15);
        g2d.setColor(COLOR_VAR2);
        g2d.fillRect(90 + 165 + xShift, 420 + 15, 50, 20);
        g2d.setColor(Color.BLACK);

        g2d.drawString("With Detection", 50 + 270 + xShift, 433 + 15);
        g2d.setColor(COLOR_VAR3);
        g2d.fillRect(90 + 320 + xShift, 420 + 15, 50, 20);
        g2d.setColor(Color.BLACK);
    }

    public void drawHistogram(ArrayList<Double> parameterList, int highestSFIndex, int shiftX, Color color, Graphics2D g2d) {
        int x = shiftX;
        int magnitudes[] = new int[parameterList.size()];
        for (int i = 0; i < parameterList.size(); i++) {
            magnitudes[i] = (int) Math.round(parameterList.get(i) * 250);
            //System.out.println("MAGNITUDE::"+magnitudes[i]);
        }
        for (int i = 0; i < magnitudes.length; i++) {
            g2d.setColor(color);
            g2d.fillRect(70 + x, 350 - magnitudes[i] + ALIGN_Y, 20, magnitudes[i]);
            //g2d.drawString("SF=" + recommendedTrustList.get(i), 80 + x, 350 - y[i] + 30);
            x += 100;
        }
    }

    public void setMOHistogramParams(ArrayList<Double> recommendedTrustListActual, ArrayList<Double> recommendedTrustListWOD, ArrayList<Double> recommendedTrustListWD, int highestSFIndex, String graphHeading) {
        this.recommendedTrustListActual = recommendedTrustListActual;
        this.recommendedTrustListWOD = recommendedTrustListWOD;
        this.recommendedTrustListWD = recommendedTrustListWD;
        graphLabel.setText(graphHeading);
    }

    public void setMOHistogramParams(TreeSet paramSet, String graphHeading) {
        String manipulatedGraphHeading[] = graphHeading.split(",");
        graphLabel.setText(manipulatedGraphHeading[1] + "," + manipulatedGraphHeading[2]);
        recommendedTrustListActual = new ArrayList<>();
        recommendedTrustListWOD = new ArrayList<>();
        recommendedTrustListWD = new ArrayList<>();
        percentDishonestRAList = new ArrayList<>();

        Iterator iterator = paramSet.iterator();
        while (iterator.hasNext()) {         
            String[] params = ((String) iterator.next()).split("-");
            percentDishonestRAList.add(Integer.parseInt(params[0]));
            recommendedTrustListActual.add(Double.parseDouble(params[1]));
            recommendedTrustListWOD.add(Double.parseDouble(params[2]));
            recommendedTrustListWD.add(Double.parseDouble(params[3]));
        }
    }

    public void setMOHistogramParams() {
        //Dataset
        recommendedTrustListActual = new ArrayList<>();
        recommendedTrustListActual.add(0.1);
        recommendedTrustListActual.add(0.2);
        recommendedTrustListActual.add(0.3);

        recommendedTrustListWOD = new ArrayList<>();
        recommendedTrustListWOD.add(0.3);
        recommendedTrustListWOD.add(0.5);
        recommendedTrustListWOD.add(0.57);

        recommendedTrustListWD = new ArrayList<>();
        recommendedTrustListWD.add(0.15);
        recommendedTrustListWD.add(0.26);
        recommendedTrustListWD.add(0.34);

    }

    public JLabel getGraphLabel() {
        return graphLabel;
    }
}
