/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.scenario;

import static apis.IConfiguration.LAPTOP_NODE;
import static apis.IConfiguration.OFFSET_X;
import static apis.IConfiguration.OFFSET_Y;
import static apis.IConfiguration.WIFI_HOTSPOT;
import static apis.IConfiguration.chColor;
import static apis.IConfiguration.dishonestNodeColor;
import static apis.IConfiguration.lowPowerNodeColor;
import static apis.IConfiguration.memberColor;
import engine.MobileNode;
import engine.NodeLink;

import gui.controls.ControlsToobar;
import gui.logger.EventLoggerPane;
import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import javax.swing.JLabel;

/**
 *
 * @author SHRI
 */
public class NodeInteractions {

    private MobileNode node[];
    private MobileNode externalNodes[];
    private MobileNode serviceProviders[];
    private JLabel energyStatuLabel;
    private JLabel headingLabel;
    private int recommendations[][];
    private ArrayList<String> nodesConfList;
    private ArrayList<String> externalNodesConfList;
    private ArrayList<String> serviceProvidersConfList;
    private ControlsToobar controlsToobar;
    private EventLoggerPane eventLoggerPane;
    private ScenarioPane scenarioPane;
    private Timer timer;
    private NodeLink link;
    private NodeInteractions nodeInteractions;
    private DecimalFormat dformat;

    private int inputNodeCount;
    private double energyLevelCounter;
    private double totalEnergyCounter;
    private int nodeNum;
    private int requestQueue;
    private String nodeLabel;
    private boolean networkAlive;
    private boolean sceneTriggered;
    private boolean triggerCommunication = false;
    private boolean triggerReply = false;
    private boolean stopEnergyMeter = true;
    private int countSteps[];
    private int delayCommToMemberNodes;
    private int timeSapnToShowRequestLinks;
    private int timeSapnToShowLinks;
    private int timeSpanToShowReplyLinks;
    private int[] delayCounterArray;
    private int dishonestRACount;
    private int index = 0;
    private boolean playPauseControlFlag = false;

    private boolean logOnceE1Flag;
    private boolean logOnceE2Flag;
    private boolean logOnceE3Flag;

    public NodeInteractions(ScenarioPane scenarioPane) {
        this.scenarioPane = scenarioPane;
        inputNodeCount=scenarioPane.getInputNodeCount();
        nodesConfList=scenarioPane.getNodesConfList();

    }

    public void clusterHeadSelectionAlgo(Graphics2D g2d) {
        int dishonestRACounter = 0;
        for (int i = 1; i < getInputNodeCount(); i++) {
            String param[] = nodesConfList.get(i).split(",");
            if (energyLevelCounter <= 35.0 && nodeNum == i && nodeNum != (getInputNodeCount() - 1)) {
                node[i].drawNode(Integer.parseInt(param[0]) + OFFSET_X, Integer.parseInt(param[1]) + OFFSET_Y, 25, param[2], "", 0, LAPTOP_NODE, chColor, g2d);
            } else {
                boolean dishonestNode = false;

                if (controlsPane.getPerformanceEvaluationCheckBox().isSelected() || controlsToobar.getEvaluatePerformanceCheckBox().isSelected()) {
                    if (i >= (getInputNodeCount() - dishonestRACount)) {
                        if (dishonestRACounter < dishonestRACount) {
                            dishonestNode = true;
                            dishonestRACounter++;
                        } else {
                            dishonestNode = false;
                        }
                    }
                }
                if (dishonestNode) {
                    node[i].drawNode(Integer.parseInt(param[0]) + OFFSET_X, Integer.parseInt(param[1]) + OFFSET_Y, 25, param[2], "", 0, LAPTOP_NODE, dishonestNodeColor, g2d);
                } else {
                    node[i].drawNode(Integer.parseInt(param[0]) + OFFSET_X, Integer.parseInt(param[1]) + OFFSET_Y, 25, param[2], "", 0, LAPTOP_NODE, memberColor, g2d);
                }
            }
        }

        //Plot Service Providers
        for (int i = 0; i < 4; i++) {
            String param[] = serviceProvidersConfList.get(i).split(",");
            serviceProviders[i].drawNode(Integer.parseInt(param[0]) + OFFSET_X, Integer.parseInt(param[1]) + OFFSET_Y, 25, param[2], "", 0, WIFI_HOTSPOT, chColor, g2d);
        }

        Color color = chColor;
        if (nodeNum < getInputNodeCount()) {
            if (energyLevelCounter < 25.0) {
                energyLevelCounter = 100.0;

                String param[] = nodesConfList.get(nodeNum).split(",");
                nodeLabel = param[2];
                nodesConfList.set(nodeNum, param[0] + "," + param[1] + "," + node[0].getNodeLabel() + "," + param[3]);

                if (nodeNum < getInputNodeCount() - 1) {
                    nodeNum++;
                } else {
                    networkAlive = false;
                }
            } else {
                if (energyLevelCounter <= 35.0) {
                    color = lowPowerNodeColor;
                }
            }
        }

        // showParameterLabels(g2d);
        //Cluster Fence
        // node[0].drawNode(300 + OFFSET_X, 300 + OFFSET_Y, 25, nodeLabel, "WI-FI", 450, DEFAULT_NODE, color, g2d);
        int range = 450;
        g2d.drawOval((300 + OFFSET_X) - (range / 2) + 12, (300 + OFFSET_Y) - (range / 2) + 12, range, range);

    }

    public MobileNode[] getNode() {
        return node;
    }

    public MobileNode[] getExternalNodes() {
        return externalNodes;
    }

    public MobileNode[] getServiceProviders() {
        return serviceProviders;
    }

    public JLabel getEnergyStatuLabel() {
        return energyStatuLabel;
    }

    public JLabel getHeadingLabel() {
        return headingLabel;
    }

    public int[][] getRecommendations() {
        return recommendations;
    }

    public ArrayList<String> getNodesConfList() {
        return nodesConfList;
    }

    public ArrayList<String> getExternalNodesConfList() {
        return externalNodesConfList;
    }

    public ArrayList<String> getServiceProvidersConfList() {
        return serviceProvidersConfList;
    }

    public ControlsPane getControlsPane() {
        return controlsPane;
    }

    public ControlsToobar getControlsToobar() {
        return controlsToobar;
    }

    public EventLoggerPane getEventLoggerPane() {
        return eventLoggerPane;
    }

    public ScenarioPane getScenarioPane() {
        return scenarioPane;
    }

    public Timer getTimer() {
        return timer;
    }

    public NodeLink getLink() {
        return link;
    }

    public NodeInteractions getNodeInteractions() {
        return nodeInteractions;
    }

    public DecimalFormat getDformat() {
        return dformat;
    }

    public int getInputNodeCount() {
        return inputNodeCount;
    }

    public double getEnergyLevelCounter() {
        return energyLevelCounter;
    }

    public double getTotalEnergyCounter() {
        return totalEnergyCounter;
    }

    public int getNodeNum() {
        return nodeNum;
    }

    public int getRequestQueue() {
        return requestQueue;
    }

    public String getNodeLabel() {
        return nodeLabel;
    }

    public boolean isNetworkAlive() {
        return networkAlive;
    }

    public boolean isSceneTriggered() {
        return sceneTriggered;
    }

    public boolean isTriggerCommunication() {
        return triggerCommunication;
    }

    public boolean isTriggerReply() {
        return triggerReply;
    }

    public boolean isStopEnergyMeter() {
        return stopEnergyMeter;
    }

    public int[] getCountSteps() {
        return countSteps;
    }

    public int getDelayCommToMemberNodes() {
        return delayCommToMemberNodes;
    }

    public int getTimeSapnToShowRequestLinks() {
        return timeSapnToShowRequestLinks;
    }

    public int getTimeSapnToShowLinks() {
        return timeSapnToShowLinks;
    }

    public int getTimeSpanToShowReplyLinks() {
        return timeSpanToShowReplyLinks;
    }

    public int[] getDelayCounterArray() {
        return delayCounterArray;
    }

    public int getDishonestRACount() {
        return dishonestRACount;
    }

    public int getIndex() {
        return index;
    }

    public boolean isPlayPauseControlFlag() {
        return playPauseControlFlag;
    }

    public boolean isLogOnceE1Flag() {
        return logOnceE1Flag;
    }

    public boolean isLogOnceE2Flag() {
        return logOnceE2Flag;
    }

    public boolean isLogOnceE3Flag() {
        return logOnceE3Flag;
    }
    
    
    
}
