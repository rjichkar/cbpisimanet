/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

/**
 *
 * @author RJ
 */
public class NodeLink {

    private final int ARR_SIZE = 4;
    float[] dash = {9f, 0f, 0f};

    Stroke drawingStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, dash, 2f);

    public void drawLink(int x1, int y1, int x2, int y2, Graphics2D g2d) {
        g2d.setStroke(drawingStroke);
        g2d.drawLine(x1 + 13, y1 + 13, x2 + 7, y2 + 5);
    }

    public void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
        Graphics2D g2d = (Graphics2D) g.create();
        x1 = x1 + 13;
        y1 = y1 + 13;
        x2 = x2 + 5; //7
        y2 = y2 + 2; //5
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g2d.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g2d.setStroke(drawingStroke);
        g2d.drawLine(0, 0, len, 0);
        g2d.fillPolygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len}, new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }
}
