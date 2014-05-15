/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import gui.scenario.*;
import gui.controls.ControlsPane;
import apis.IConfiguration;
import engine.MobileNode;
import engine.NodeLink;
import engine.RecommendationAnalyzer_v2;
import gui.controls.ControlsToobar;
import gui.logger.EventLoggerPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author RJ
 */
public class ScenarioPane_BAK extends JPanel implements IConfiguration {

    private MobileNode node[];
    private MobileNode externalNodes[];
    private MobileNode serviceProviders[];
    private JLabel energyStatuLabel;
    private JLabel headingLabel;
    private int recommendations[][];
    private ArrayList<String> nodesConfList;
    private ArrayList<String> externalNodesConfList;
    private ArrayList<String> serviceProvidersConfList;
    private ControlsPane controlsPane;
    private ControlsToobar controlsToobar;
    private EventLoggerPane eventLoggerPane;
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

    //TS 4.0
    private boolean stopInteractionsFlag = true;
    private int serviceProvideIndex;

    //TS 3.0
    private int countSteps[];
    private int showInteractionsTimer = 30;
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

    public ScenarioPane_BAK() {
        initComponents();
    }

    private void initComponents() {
        setLayout(null);

        dformat = new DecimalFormat("##.###");
        link = new NodeLink();
      //  nodeInteractions = new NodeInteractions(this);

        eventLoggerPane = new EventLoggerPane(null);
        eventLoggerPane.setName("Event Logger");
        energyStatuLabel = new JLabel();
        headingLabel = new JLabel();

        energyStatuLabel.setBounds(20, 500, 150, 20);
        headingLabel.setBounds(210, 25, 500, 60);
        eventLoggerPane.setBounds(0, 530, 1290, 200);

        setBackground(Color.WHITE);

        //Start Main Timer
        startTimer(DELAY_MAIN_TIMER);

        //Start other activities
        startSupportingTimer(DELAY_SUPP_TIMER);

        setEnergyLevelCounter(100.0);
        setNodeLabel("1");
        setNodeNum(1);
        add(headingLabel);
        add(energyStatuLabel);
        add(eventLoggerPane);

    }

    public void resetParams() {
        setLogOnceE1Flag(true);
        setLogOnceE2Flag(true);
        setLogOnceE3Flag(true);
        setTotalEnergyCounter(0.0);
        setStopEnergyMeter(true);
        setNetworkAlive(true);
        setTriggerCommunication(false);
        setTriggerReply(false);
        setStopInteractionsFlag(true);
        setShowInteractionsTimer(30);
        setServiceProvideIndex(0);

        index = 0;
        delayCommToMemberNodes = 50;//50
        timeSapnToShowRequestLinks = 40;//40
        timeSapnToShowLinks = 40;//30
        timeSpanToShowReplyLinks = 30;
        requestQueue = 0;
        playPauseControlFlag = false;
    }

    public void resetCluster() {
        setEnergyLevelCounter(100.0);
        setNodeLabel("1");
        setNodeNum(1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

        Graphics2D g2d = (Graphics2D) g;

      //plotAssumptions(g2d);
      //plotNodeMap(g2d);
        
        if (isSceneTriggered()) {
            //Plot Nodes TS 4.0
            plotNodes(g2d);

            //Generate Interations TS 4.0
            if (isStopInteractionsFlag() && isPlayPauseControlFlag()) {
                generateMemberNodesInteractions(g2d);
            }
            
            //Plot & Move External Nodes TS 4.0
            plotAndMoveExternalNodes(g2d);

            
            //External nodes request to ISPs
            

            /*
             //Cluster Head Selection
             clusterHeadSelectionAlgo(g2d);
             setEnergyConsumption(0.01, g2d);
             // totalEnergyCounter += 0.01;

             //Plot External Nodes
             plotAndMoveExternalNodes(g2d);

             //Communicate with CH
             if (triggerCommunication && (timeSapnToShowRequestLinks >= 0)) {
             communicateToCH(g2d);
             if (isLogOnceE1Flag()) {
             eventLoggerPane.logEvent("U-1 Registered with ISP-1.....");
             setLogOnceE1Flag(false);
             }
             }

             if (delayCommToMemberNodes <= 0) {
             communicateCHToMemberNodes(g2d);
             if (isLogOnceE2Flag()) {
             eventLoggerPane.logEvent("ISP-1 Broadcast Recommendation Request to other Services.....");
             eventLoggerPane.logEvent("ISP-1 Applies Indirect Trust Algo on the aggregated recc.....");
             eventLoggerPane.logEvent("ISP-1 computes Trust Value based on Rcc");
             setLogOnceE2Flag(false);
             }
             setEnergyConsumption(1.0, g2d);
             if (isPlayPauseControlFlag()) {
             totalEnergyCounter += 0.001;
             }
             triggerReply = true;

             }
             if (timeSapnToShowLinks <= 0) {
             delayCommToMemberNodes = 100;
             communicateReplyToExtNodes(g2d);
             if (isLogOnceE3Flag()) {
             eventLoggerPane.logEvent("U-1 makes decision based on Trust Value of ISP-1...");
             setLogOnceE3Flag(false);
             }
             }
             */
        } else {
            new MobileNode().drawNode(300 + OFFSET_X, 300 + OFFSET_Y, 25, "CH", "CH", 450, DEFAULT_NODE, Color.WHITE, g2d);
        }
    }

    private void plotNodes(Graphics2D g2d) {
        //Plot Member Nodes
        for (int i = 1; i < getInputNodeCount(); i++) {
            String param[] = nodesConfList.get(i).split(",");
            node[i].drawNode(Integer.parseInt(param[0]) + OFFSET_X, Integer.parseInt(param[1]) + OFFSET_Y, 25, param[2], "ID:" + i, 0, LAPTOP_NODE, chColor, g2d);
        }
        //Plot Service Providers
        for (int i = 0; i < 4; i++) {
            String param[] = serviceProvidersConfList.get(i).split(",");
            serviceProviders[i].drawNode(Integer.parseInt(param[0]) + OFFSET_X, Integer.parseInt(param[1]) + OFFSET_Y, 25, param[2], "SP-" + (i + 1), 0, WIFI_HOTSPOT, chColor, g2d);
        }
        //Draw Range
        int range = 450;
        g2d.drawOval((300 + OFFSET_X) - (range / 2) + 12, (300 + OFFSET_Y) - (range / 2) + 12, range, range);
    }

    private void generateMemberNodesInteractions(Graphics2D g2d) {
        for (int j = 1; j < nodesConfList.size(); j++) {
            String params[] = nodesConfList.get(j).split(",");
            String paramsSP[] = serviceProvidersConfList.get(serviceProvideIndex).split(",");
            link.drawLink(Integer.parseInt(paramsSP[0]) + OFFSET_X, Integer.parseInt(paramsSP[1]) - 20, Integer.parseInt(params[0]) + 100, Integer.parseInt(params[1]) - 40 - OFFSET_Y, g2d);
        }
    }

    private void interactionTimerLogic() {
        if (showInteractionsTimer >= 0) {
            showInteractionsTimer--;
        } else {
            if (serviceProvideIndex < serviceProvidersConfList.size() - 1) {
                serviceProvideIndex++;
            } else {
                serviceProvideIndex = 0;
                stopInteractionsFlag = false;
            }
            showInteractionsTimer = 30;
        }

    }

    private void clusterHeadSelectionAlgo(Graphics2D g2d) {
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

    private void showParameterLabels(Graphics2D g2d) {
        g2d.setFont(new Font("default", Font.BOLD, 11));
        g2d.drawString("CH NODE-" + nodeNum + " ENERGY LEVEL: " + dformat.format(node[nodeNum - 1].getEnergyLevel()) + " Joules.", OFFSET_X + 440, 100);
        g2d.drawString("APPROX. TOTAL ENERGY CONS. IN DETECTION: " + dformat.format(totalEnergyCounter) + " J", OFFSET_X + 440, 130);
        g2d.drawString("REQUESTS TO JOIN CLUSTER IN 'CH' QUEUE: " + requestQueue, OFFSET_X + 440, 160);

    }

    private void plotAndMoveExternalNodes(Graphics2D g2d) {
        //Plot External Nodes
        for (int i = 0; i < externalNodes.length; i++) {
            String params[] = externalNodesConfList.get(i).split(",");
            if (controlsPane.getPerformanceEvaluationCheckBox().isSelected() || controlsToobar.getEvaluatePerformanceCheckBox().isSelected()) {
                externalNodes[i].drawNode(Integer.parseInt(params[0]), Integer.parseInt(params[1]), 25, params[2], "T=" + params[3], 0, SMART_PHONE_NODE, externalNodeColor, g2d);
            } else {
                externalNodes[i].drawNode(Integer.parseInt(params[0]), Integer.parseInt(params[1]), 25, params[2], "U-1", 0, SMART_PHONE_NODE, externalNodeColor, g2d);
            }
            //String params[]=externalNodesConfList.get(0).split(",");
            int posX = Integer.parseInt(params[0]);
            int posY = Integer.parseInt(params[1]);

            if (isPlayPauseControlFlag()&&!isStopInteractionsFlag()) {
                if (countSteps[i] < 150) {
                    posX += 5;
                    posY += 5;
                } else {
                    triggerCommunication = true;
                }
                countSteps[i] += 3;
            }

            externalNodesConfList.set(i, posX + "," + posY + "," + params[2] + "," + params[3]);
        }

    }

    private void communicateToCH(Graphics2D g2d) {
        for (int i = 0; i < externalNodesConfList.size(); i++) {
            String params[] = externalNodesConfList.get(i).split(",");
            String paramsSP[] = serviceProvidersConfList.get(0).split(",");
            link.drawArrow((Graphics) g2d, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(paramsSP[0]) + OFFSET_X, Integer.parseInt(paramsSP[1]) + OFFSET_Y);
        }
        requestQueue = externalNodes.length;
    }

    private void communicateCHToMemberNodes(Graphics2D g2d) {
        for (int i = 1; i < nodesConfList.size(); i++) {
            String params[] = nodesConfList.get(i).split(",");
            String paramsSP[] = serviceProvidersConfList.get(0).split(",");
            link.drawLink(Integer.parseInt(paramsSP[0]) + OFFSET_X, Integer.parseInt(paramsSP[1]) - 20, Integer.parseInt(params[0]) + 100, Integer.parseInt(params[1]) - 40 - OFFSET_Y, g2d);
            //link.drawArrow((Graphics) g2d, Integer.parseInt(params[0]) + 100-13, Integer.parseInt(params[1]) + 100-13, 300 + OFFSET_X-5, 300 + OFFSET_Y-7);
            //System.out.println("LINK NODES CONF: X="+params[0]+" Y="+params[1]);
        }
    }

    private void communicateReplyToExtNodes(Graphics2D g2d) {
        for (int i = 0; i < externalNodesConfList.size(); i++) {
            String params[] = externalNodesConfList.get(i).split(",");
            String paramsSP[] = serviceProvidersConfList.get(0).split(",");
            link.drawArrow((Graphics) g2d, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(paramsSP[0]) + OFFSET_X, Integer.parseInt(paramsSP[1]) + OFFSET_Y);
            double trustValue = new RecommendationAnalyzer_v2().computeAlgorithmResults(recommendations[0]);
            serviceProviders[0].drawNode(Integer.parseInt(paramsSP[0]) + OFFSET_X, Integer.parseInt(paramsSP[1]) + OFFSET_Y, 25, paramsSP[2], "T=" + trustValue, 0, WIFI_HOTSPOT, chColor, g2d);
        }
    }

    private void setEnergyConsumption(double energyConsumed, Graphics2D g2d) {
        if (isPlayPauseControlFlag()) {
            energyLevelCounter -= energyConsumed;
        }
        if (!networkAlive && energyLevelCounter <= 25) {
            g2d.drawString("NETWORK LIFE TIME OVER!!", OFFSET_X - 50, 550 + OFFSET_Y);
            energyLevelCounter = 25.0;
        } else {
            //Set Energy Level for CH
            node[nodeNum - 1].setEnergyLevel(energyLevelCounter);//nodeNum-1
        }
    }

    private void plotAssumptions(Graphics2D g2d) {
        headingLabel.setText("CLUSTERING BASED PERFORMANCE IMPROVEMENT STRATEGY IN MANET");

        int ALIGN_Y = 10;
        g2d.drawString("CLUSTER (RADIUS= 225m APPROX.)", 300, 70 + OFFSET_Y);
        g2d.setFont(new Font("default", Font.BOLD, 11));
        g2d.setColor(Color.BLUE);

        //Plot Assumptions
        g2d.drawString("ASSUMPTIONS", 20, 510 + ALIGN_Y);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("default", Font.PLAIN, 11));
        g2d.drawString("1.Static Mobility Model", 20, 530 + ALIGN_Y);
        g2d.drawString("2.Nodes Type: Homogenous", 20, 550 + ALIGN_Y);
        g2d.drawString("3.Clustering Formation Algo: Lowest Clustering-ID Technique", 20, 570 + ALIGN_Y);
        g2d.drawString("4.Cluster Head Selection Algo: Lowest-ID & Energy Threshold", 20, 590 + ALIGN_Y);
        g2d.drawString("5.All member nodes have interacted at least once with the Unknown nodes (A,B..).", 20, 610 + ALIGN_Y);
        g2d.drawString("6.Bin size =10  i.e RCs={0.1,0.2, …..1.0}", 20, 630 + ALIGN_Y);
    }

    private void plotNodeMap(Graphics2D g2d) {
        int ALIGN_Y = 10;
        //Plot Node Map
        g2d.setFont(new Font("default", Font.BOLD, 11));
        g2d.setColor(Color.BLUE);
        MobileNode mappingNode = new MobileNode();

        g2d.drawString("NODE MAP", 630, 500 + ALIGN_Y);
        int yPosOffset = 25;
        for (int index = 0; index < nodeMappings.length; index++) {
            mappingNode.drawNode(630, 485 + yPosOffset + ALIGN_Y, 20, "#", "", 0, LAPTOP_NODE, nodeMapColors[index], g2d);
            g2d.drawString(nodeMappings[index], 660, 500 + yPosOffset + ALIGN_Y);
            yPosOffset += 25;
        }
    }

    public void startTimer(int delay) {
        timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //System.out.println("Timer says: " + new Date());
                repaint();
            }
        }, 0, delay/*Delay*/);

    }

    public void startSupportingTimer(int delay) {
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (/*isTriggerCommunication() &&*/isPlayPauseControlFlag()) {
                    interactionTimerLogic();
                    /*
                     if (delayCommToMemberNodes >= 0) {
                     delayCommToMemberNodes--;
                     }
                     if (timeSapnToShowRequestLinks >= 0) {
                     timeSapnToShowRequestLinks--;
                     }
                     if (triggerReply) {
                     if (delayCounterArray[index] >= -1) {
                     delayCounterArray[index]--;
                     } else {
                     if (index < delayCounterArray.length - 1) {
                     index++;
                     } else {
                     controlsPane.getSelectNodeLabel().setEnabled(true);
                     controlsPane.getNodesSelectComboBox().setEnabled(true);
                     controlsPane.getShowAnalysisButton().setEnabled(true);

                     controlsToobar.getSelectDetectedNodeLabel().setEnabled(true);
                     controlsToobar.getSelectDetectedNodeComboBox().setEnabled(true);
                     controlsToobar.getShowAlgorithmAnalysisButton().setEnabled(true);
                     }
                     if ((index <= delayCounterArray.length - 1) && stopEnergyMeter) {
                     setEnergyConsumption(1.5, null);
                     totalEnergyCounter += (0.02 * inputNodeCount);

                     if (index == delayCounterArray.length - 1) {
                     stopEnergyMeter = false;
                     //Add power value to Power Value List
                     controlsPane.setPowerTrendControls(true);
                     controlsToobar.setGraphTrendControls(true);

                     //Add Parameters to list for Graphical Analysis
                     controlsPane.getPowerValuesList().add(totalEnergyCounter);
                     controlsPane.getMemberNodeCountList().add(inputNodeCount);
                     controlsPane.getExternalNodeCountList().add(externalNodes.length);
                     }
                     }
                     if (requestQueue > 0) {
                     requestQueue--;
                     }
                     }
                     if (timeSapnToShowLinks >= 0) {
                     timeSapnToShowLinks--;
                     }
                     }
                     */
                }

            }

        }, 0, delay/*Delay*/);
    }

    public void configureNodes(int nodeCount) {
        int nodeID = 1;
        int[] nodeIDArray = new int[nodeCount - 1];

        resetParams();

        nodesConfList = new ArrayList<>();

        int index = 0;
        if (Integer.parseInt(nodeLabel) > 1) {
            for (int i = 1; i <= nodeCount; i++) {
                if (!(Integer.parseInt(nodeLabel) == i)) {
                    nodeIDArray[index] = i;
                    //System.out.println("NUM:" + nodeIDArray[index]);
                    index++;
                }
            }
        }
        //Reset Index
        index = 0;
        System.out.println("NODE COUNT:" + nodeCount);
        for (int i = 0; i < nodeCount; i++) {

            Random rand = new Random();
            double angle = Math.random() * Math.PI * 2;
            int radius = 0;
            //Prevent Overlapping on CH
            while (radius < 10) {
                radius = rand.nextInt(200);
            }
            int posX = (int) Math.round((Math.cos(angle) * radius));
            int posY = (int) Math.round((Math.sin(angle) * radius));//rand.nextInt(200)

            //System.out.println("Circle X=" + x + " Y=" + y);
            //Generate random RC values
            int rcValue = 0;
            while (rcValue == 0) {
                rcValue = (int) (Math.random() * 11);
            }

            //System.out.println("RC: " + rcValue);
            if (Integer.parseInt(nodeLabel) > 1) {
                if (i == 0) {
                    nodesConfList.add((posX + 300) + "," + (posY + 300) + "," + nodeLabel + "," + rcValue);
                } else {
                    nodesConfList.add((posX + 300) + "," + (posY + 300) + "," + nodeIDArray[index] + "," + rcValue);
                    if (index < nodeCount - 1) {
                        index++;
                    }
                }
            } else {
                nodesConfList.add((posX + 300) + "," + (posY + 300) + "," + nodeID + "," + rcValue);
            }
            // System.out.println("NODES CONF: X="+nodesConfList.get(i));
            nodeID++;
        }
    }

    public void configureExternalNodes(int externalNodeCount) {
        char alpha = 'A';
        externalNodesConfList = new ArrayList<String>();
        countSteps = new int[externalNodeCount];
        delayCounterArray = new int[externalNodeCount];
        for (int i = 0; i < delayCounterArray.length; i++) {
            delayCounterArray[i] = 15;
        }

        for (int i = 0; i < externalNodeCount; i++) {

            Random rand = new Random();
            int posX = rand.nextInt(200);
            int posY = rand.nextInt(200);

            int rcValue = 0;

            externalNodesConfList.add((10 + posX) + "," + (10 + posY) + "," + alpha + "," + rcValue);
            alpha++;
        }
    }

    public void configureServiceProviders(int serviceProviderCount) {
        serviceProvidersConfList = new ArrayList<>();
        for (int i = 0; i < serviceProviderCount; i++) {
            Random rand = new Random();
            double angle = Math.random() * Math.PI * 2;
            int radius = 0;

            //Prevent Overlapping on CH
            while (radius < 10) {
                radius = rand.nextInt(200);
            }

            int posX = (int) Math.round((Math.cos(angle) * radius));
            int posY = (int) Math.round((Math.sin(angle) * radius));//rand.nextInt(200)

            //System.out.println("Circle X=" + x + " Y=" + y);
            //Generate random RC values
            int rcValue = 0;
            while (rcValue == 0) {
                rcValue = (int) (Math.random() * 11);
            }

            //System.out.println("RC: " + rcValue);
            serviceProvidersConfList.add((posX + 300) + "," + (posY + 300) + "," + "#" + "," + rcValue);

            // System.out.println("NODES CONF: X="+nodesConfList.get(i));
        }
    }

    public void generateRandomRecommendations(int inputNodeCount, int externalNodeCount) {
        recommendations = new int[externalNodeCount][inputNodeCount];
        for (int i = 0; i < externalNodeCount; i++) {
            for (int j = 0; j < inputNodeCount; j++) {
                int rcValue = 0;
                while (rcValue == 0) {
                    rcValue = (int) (Math.random() * 11);
                }

                recommendations[i][j] = rcValue;
            }
        }
    }

    public int[][] generateRecommendationsForPE(int inputNodeCount, int externalNodeCount, int attackType, int percentDishonestRA, boolean moMode, double inputMO) {
        recommendations = new int[externalNodeCount][inputNodeCount];
        int dishonestRACount = (int) (inputNodeCount * (percentDishonestRA / 100.0));
        this.dishonestRACount = dishonestRACount;
        double meanOffset = 0;
        double attackTrustValue = 0;

        for (int i = 0; i < externalNodeCount; i++) {

            meanOffset = 0;
            double sumDishonestRA = 0;
            double sumHonestRA = 0;
            attackTrustValue = 0;

            //Add Honest Rcc
            for (int j = 0; j < inputNodeCount; j++) {
                int rcValue = 0;
                switch (attackType) {
                    case 0://BM
                        while (rcValue < 5) {
                            rcValue = (int) (Math.random() * 11);
                        }
                        break;

                    case 1://BS 
                        if (moMode) {
                            switch (inputMO + "") {
                                case "0.1":
                                    rcValue = 4;
                                    break;
                                case "0.2":
                                    // System.out.println("MO LEVEL HON:" + inputMO);
                                    rcValue = 4;
                                    break;
                                case "0.4":
                                    rcValue = 4;
                                    break;
                                case "0.6":
                                    rcValue = 3;
                                    break;
                                case "0.8":
                                    rcValue = 2;
                                    break;
                            }
                        } else {
                            rcValue = 5;
                            while (rcValue >= 5) {
                                rcValue = (int) (Math.random() * 11);
                            }
                        }
                        break;
                    case 2://RO
                        rcValue = (int) (Math.random() * 11);
                        while (rcValue == 0) {
                            rcValue = (int) (Math.random() * 11);
                        }
                        break;

                }
                recommendations[i][j] = rcValue;
            }

            System.out.println("DISHONEST RA COUNT:" + dishonestRACount + "RCC[i] LEN:" + recommendations[i].length);

            //Get Trust Value for above recommendations
            attackTrustValue = (new RecommendationAnalyzer_v2()).computeAlgorithmResults(recommendations[i]);

            System.out.println("PRE CALC TRUST VALUE:" + attackTrustValue);
            //Add Dishonest Recommendations
            for (int j = 0; j < dishonestRACount; j++) {

                int rcValue = 0;
                switch (attackType) {
                    case 0://BM
                        while (rcValue == 0) {
                            rcValue = (int) (Math.random() * 5);
                        }
                        break;
                    case 1://BS
                        if (moMode) {
                            switch (inputMO + "") {
                                case "0.1":
                                    rcValue = 5;
                                    break;
                                case "0.2":
                                    // System.out.println("MO LEVEL DIS:" + inputMO);
                                    rcValue = 6;
                                    break;
                                case "0.4":
                                    rcValue = 8;
                                    break;
                                case "0.6":
                                    rcValue = 9;
                                    break;
                                case "0.8":
                                    rcValue = 10;
                                    break;
                            }
                        } else {
                            while (rcValue < 5) {
                                rcValue = (int) (Math.random() * 11);
                            }
                        }
                        break;
                    case 2://RO
                        if (attackTrustValue < 0.5) {
                            while (rcValue < 6) {
                                rcValue = (int) (Math.random() * 11);
                            }
                        } else {
                            while (rcValue == 0) {
                                rcValue = (int) (Math.random() * 5);
                            }
                        }
                        break;
                }
                recommendations[i][j] = rcValue;
            }

            // for (int i = 0; i < externalNodeCount; i++) {
            for (int j = 0; j < dishonestRACount; j++) {
                sumDishonestRA += (recommendations[i][j] / 10.0);
            }

            for (int k = dishonestRACount; k < recommendations[i].length; k++) {
                sumHonestRA += (recommendations[i][k] / 10.0);
                //System.out.println("K="+k);
            }
            if (moMode) {
                System.out.println("SUM Honest RA:" + sumHonestRA + " SUM Dishonest RA:" + sumDishonestRA);
                meanOffset = (sumDishonestRA / dishonestRACount) - (sumHonestRA / (inputNodeCount - dishonestRACount));
                DecimalFormat df = new DecimalFormat("#.#");

                System.out.println("MEAN OFFSET " + i + ": " + df.format(meanOffset) + "\n");
            }

            //Store RC Values in Ext Nodes
            String params[] = externalNodesConfList.get(i).split(",");
            externalNodesConfList.set(i, params[0] + "," + params[1] + "," + params[2] + "," + attackTrustValue);
        }
        return recommendations;
    }

    public boolean isPlayPauseControlFlag() {
        return playPauseControlFlag;
    }

    public void setPlayPauseControlFlag(boolean playPauseControlFlag) {
        this.playPauseControlFlag = playPauseControlFlag;
    }

    public ControlsPane getControlsPane() {
        return controlsPane;
    }

    public void setControlsPane(ControlsPane controlsPane) {
        this.controlsPane = controlsPane;
    }

    public ControlsToobar getControlsToobar() {
        return controlsToobar;
    }

    public void setControlsToobar(ControlsToobar controlsToobar) {
        this.controlsToobar = controlsToobar;
    }

    public int getInputNodeCount() {
        return inputNodeCount;
    }

    public void setInputNodeCount(int inputNodeCount) {
        this.inputNodeCount = inputNodeCount;
    }

    public boolean isSceneTriggered() {
        return sceneTriggered;
    }

    public void setSceneTriggered(boolean sceneTriggered) {
        this.sceneTriggered = sceneTriggered;
    }

    public void setNode(MobileNode[] node) {
        this.node = node;
    }

    public MobileNode[] getServiceProviders() {
        return serviceProviders;
    }

    public void setServiceProviders(MobileNode[] serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    public void setNodeLabel(String nodeLabel) {
        this.nodeLabel = nodeLabel;
    }

    public void setNodesConfList(ArrayList<String> nodesConfList) {
        this.nodesConfList = nodesConfList;
    }

    public MobileNode[] getNode() {
        return node;
    }

    public MobileNode[] getExternalNodes() {
        return externalNodes;
    }

    public ArrayList<String> getNodesConfList() {
        return nodesConfList;
    }

    public double getTotalEnergyCounter() {
        return totalEnergyCounter;
    }

    public int getNodeNum() {
        return nodeNum;
    }

    public String getNodeLabel() {
        return nodeLabel;
    }

    public boolean isNetworkAlive() {
        return networkAlive;
    }

    public boolean isTriggerReply() {
        return triggerReply;
    }

    public boolean isStopEnergyMeter() {
        return stopEnergyMeter;
    }

    public void setEnergyLevelCounter(double energyLevelCounter) {
        this.energyLevelCounter = energyLevelCounter;
    }

    public double getEnergyLevelCounter() {
        return energyLevelCounter;
    }

    public void setNodeNum(int nodeNum) {
        this.nodeNum = nodeNum;
    }

    public void setNetworkAlive(boolean networkAlive) {
        this.networkAlive = networkAlive;
    }

    public void setExternalNodes(MobileNode[] externalNodes) {
        this.externalNodes = externalNodes;
    }

    public boolean isTriggerCommunication() {
        return triggerCommunication;
    }

    public void setTriggerCommunication(boolean triggerCommunication) {
        this.triggerCommunication = triggerCommunication;
    }

    public void setTriggerReply(boolean triggerReply) {
        this.triggerReply = triggerReply;
    }

    public int[] getRecommendationsForNode(int node) {
        return recommendations[node];
    }

    public void setTotalEnergyCounter(double totalEnergyCounter) {
        this.totalEnergyCounter = totalEnergyCounter;
    }

    public void setStopEnergyMeter(boolean stopEnergyMeter) {
        this.stopEnergyMeter = stopEnergyMeter;
    }

    public boolean isLogOnceE1Flag() {
        return logOnceE1Flag;
    }

    public void setLogOnceE1Flag(boolean logOnceE1Flag) {
        this.logOnceE1Flag = logOnceE1Flag;
    }

    public boolean isLogOnceE2Flag() {
        return logOnceE2Flag;
    }

    public void setLogOnceE2Flag(boolean logOnceE2Flag) {
        this.logOnceE2Flag = logOnceE2Flag;
    }

    public boolean isLogOnceE3Flag() {
        return logOnceE3Flag;
    }

    public void setLogOnceE3Flag(boolean logOnceE3Flag) {
        this.logOnceE3Flag = logOnceE3Flag;
    }

    public EventLoggerPane getEventLoggerPane() {
        return eventLoggerPane;
    }

    public JLabel getEnergyStatuLabel() {
        return energyStatuLabel;
    }

    public void setEnergyStatuLabel(JLabel energyStatuLabel) {
        this.energyStatuLabel = energyStatuLabel;
    }

    public JLabel getHeadingLabel() {
        return headingLabel;
    }

    public void setHeadingLabel(JLabel headingLabel) {
        this.headingLabel = headingLabel;
    }

    public int[][] getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(int[][] recommendations) {
        this.recommendations = recommendations;
    }

    public ArrayList<String> getExternalNodesConfList() {
        return externalNodesConfList;
    }

    public void setExternalNodesConfList(ArrayList<String> externalNodesConfList) {
        this.externalNodesConfList = externalNodesConfList;
    }

    public ArrayList<String> getServiceProvidersConfList() {
        return serviceProvidersConfList;
    }

    public void setServiceProvidersConfList(ArrayList<String> serviceProvidersConfList) {
        this.serviceProvidersConfList = serviceProvidersConfList;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public NodeLink getLink() {
        return link;
    }

    public void setLink(NodeLink link) {
        this.link = link;
    }

    public NodeInteractions getNodeInteractions() {
        return nodeInteractions;
    }

    public void setNodeInteractions(NodeInteractions nodeInteractions) {
        this.nodeInteractions = nodeInteractions;
    }

    public DecimalFormat getDformat() {
        return dformat;
    }

    public void setDformat(DecimalFormat dformat) {
        this.dformat = dformat;
    }

    public int getRequestQueue() {
        return requestQueue;
    }

    public void setRequestQueue(int requestQueue) {
        this.requestQueue = requestQueue;
    }

    public int[] getCountSteps() {
        return countSteps;
    }

    public void setCountSteps(int[] countSteps) {
        this.countSteps = countSteps;
    }

    public int getDelayCommToMemberNodes() {
        return delayCommToMemberNodes;
    }

    public void setDelayCommToMemberNodes(int delayCommToMemberNodes) {
        this.delayCommToMemberNodes = delayCommToMemberNodes;
    }

    public int getTimeSapnToShowRequestLinks() {
        return timeSapnToShowRequestLinks;
    }

    public void setTimeSapnToShowRequestLinks(int timeSapnToShowRequestLinks) {
        this.timeSapnToShowRequestLinks = timeSapnToShowRequestLinks;
    }

    public int getTimeSapnToShowLinks() {
        return timeSapnToShowLinks;
    }

    public void setTimeSapnToShowLinks(int timeSapnToShowLinks) {
        this.timeSapnToShowLinks = timeSapnToShowLinks;
    }

    public int getTimeSpanToShowReplyLinks() {
        return timeSpanToShowReplyLinks;
    }

    public void setTimeSpanToShowReplyLinks(int timeSpanToShowReplyLinks) {
        this.timeSpanToShowReplyLinks = timeSpanToShowReplyLinks;
    }

    public int[] getDelayCounterArray() {
        return delayCounterArray;
    }

    public void setDelayCounterArray(int[] delayCounterArray) {
        this.delayCounterArray = delayCounterArray;
    }

    public int getDishonestRACount() {
        return dishonestRACount;
    }

    public void setDishonestRACount(int dishonestRACount) {
        this.dishonestRACount = dishonestRACount;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isStopInteractionsFlag() {
        return stopInteractionsFlag;
    }

    public void setStopInteractionsFlag(boolean stopInteractionsFlag) {
        this.stopInteractionsFlag = stopInteractionsFlag;
    }

    public void setServiceProvideIndex(int serviceProvideIndex) {
        this.serviceProvideIndex = serviceProvideIndex;
    }

    public void setShowInteractionsTimer(int showInteractionsTimer) {
        this.showInteractionsTimer = showInteractionsTimer;
    }

}
