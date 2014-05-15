/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import apis.IConfiguration;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author RJ
 */
public class MobileNode implements IConfiguration {

    private int x;
    private int y;
    private int nodeRange;
    private int nodeMaxRange;
    private boolean inflateRange;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private double energyLevel;
    private String nodeLabel;

    private ImageIcon memberNodeIcon;
    private ImageIcon externalNodeIcon;
    private ImageIcon serviceProviderIcon;
    private ImageIcon serviceProviderTrustedIcon;
    private ImageIcon serviceProviderNonTrustedIcon;
    private ImageIcon dishonestMemberNodeIcon;

    public MobileNode() {
        //startSupportingTimer();
        loadImages();
    }

    private void loadImages() {
        memberNodeIcon = new ImageIcon(getClass().getResource("/images/laptop_medium.png"));
        externalNodeIcon = new ImageIcon(getClass().getResource("/images/samrtphone_medium.png"));
        serviceProviderIcon = new ImageIcon(getClass().getResource("/images/wifi_icon_medium.png"));
        serviceProviderTrustedIcon = new ImageIcon(getClass().getResource("/images/wifi_green_medium.jpg"));
        serviceProviderNonTrustedIcon = new ImageIcon(getClass().getResource("/images/wifi_red_medium.png"));
        dishonestMemberNodeIcon = new ImageIcon(getClass().getResource("/images/dishonest_node_small.png"));
    }

    public void startSupportingTimer() {
        Timer sTimer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doSupportingTasks();
            }
        });
        sTimer.setRepeats(true);
        sTimer.setCoalesce(true);
        sTimer.start();
    }

    public void drawNode(int posX, int posY, int size, int range, boolean inflateRange, Graphics2D g2d) {
        nodeMaxRange = range;
        this.inflateRange = inflateRange;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawOval(posX + 15 - (nodeRange / 2), posY + 15 - (nodeRange / 2), nodeRange, nodeRange);
        g2d.setColor(new Color(0, 128, 0));
        g2d.fillOval(posX, posY, size, size);
    }

    public void drawNode(int x, int y, int nodeSize, String nodeLabel, String label, int range, int nodeType, Color color, Graphics g) {
        setNodeLabel(nodeLabel);

        switch (nodeType) {
            case LAPTOP_NODE:
                g.drawImage(memberNodeIcon.getImage(), x, y, null);
                break;
            case SMART_PHONE_NODE:
                g.drawImage(externalNodeIcon.getImage(), x, y, null);
                break;
            case WIFI_HOTSPOT:
                g.drawImage(serviceProviderIcon.getImage(), x, y, null);
                break;
            case WIFI_HOTSPOT_GREEN:
                g.drawImage(serviceProviderTrustedIcon.getImage(), x, y, null);
                break;
            case WIFI_HOTSPOT_RED:
                g.drawImage(serviceProviderNonTrustedIcon.getImage(), x, y, null);
                break;
            case DISHONEST_LAPTOP_NODE:
                g.drawImage(dishonestMemberNodeIcon.getImage(), x, y, null);
                break;
            case DEFAULT_NODE:
                g.setColor(color);
                g.fillOval(x, y, nodeSize, nodeSize);
                g.setColor(Color.WHITE);
                g.drawString(nodeLabel, x + 5, y + 17);

        }

        g.drawString(label, x + 2, y - 10);

        g.setColor(Color.LIGHT_GRAY);
        g.drawOval(x - (range / 2) + 12, y - (range / 2) + 12, range, range);
        g.setColor(Color.BLACK);
    }

    public void doSupportingTasks() {
        if (inflateRange) {
            nodeRange = nodeRange % nodeMaxRange;
            nodeRange++;
        }
    }

    public double getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(double energyLevel) {
        this.energyLevel = energyLevel;
    }

    public String getNodeLabel() {
        return nodeLabel;
    }

    public void setNodeLabel(String nodeLabel) {
        this.nodeLabel = nodeLabel;
    }

}
