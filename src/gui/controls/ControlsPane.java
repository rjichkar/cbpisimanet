/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controls;

import engine.MobileNode;
import gui.graphs.GraphsFrame;

import gui.scenario.ScenarioPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author SHRI
 */
public class ControlsPane extends JPanel {

    private MobileNode node[];
    private MobileNode externalNodes[];
    private MobileNode serviceProviders[];
    private ArrayList<Integer> memberNodeCountList;
    private ArrayList<Integer> externalNodeCountList;
    private ArrayList<Double> powerValuesList;

    private JLabel nodeCountLabel;
    private JLabel externalNodeCountLabel;
    private JLabel meanOffsetLabel;
    private JLabel attackSelectLabel;
    private JLabel attackTrustValueLabel;
    private JLabel attackPercentDishonestRALabel;
    private JLabel selectNodeLabel;
    private JButton generateSceneButton;
    private JButton resetClusterButton;
    private JButton showAnalysisButton;
    private JButton powerTrendButton;
    private JToggleButton playPauseToggleButton;
    private JButton resetPowerTrendButton;
    private JTextField nodesCountField;
    private JTextField externalNodesCountField;
    private JTextField attackTrustValueField;
    private JTextField attackPercentDishonestField;
    private JCheckBox performanceEvaluationCheckBox;
    private JComboBox meanOffsetComboBox;
    private JComboBox attacksComboBox;
    private JComboBox nodesSelectComboBox;
    private JRadioButton meanOffsetScenarioRadioButton;
    private JRadioButton attackScenarioRadioButton;
    private ButtonGroup radioGroup;
    private ScenarioPane scenarioPane;
    private int attackType;
    private int controlsComponontYPos = 150;

    private ImageIcon playIcon;
    private ImageIcon pauseIcon;


    public ControlsPane(ScenarioPane scenarioPane) {
        this.scenarioPane = scenarioPane;
        initComponents();
        initEvents();
    }

    private void initComponents() {
        setLayout(null);
        setBackground(Color.WHITE);

        //Load Images
        playIcon = new ImageIcon(getClass().getResource("../../images/play.jpg"));
        pauseIcon = new ImageIcon(getClass().getResource("../../images/pause.jpg"));

        memberNodeCountList = new ArrayList<>();
        externalNodeCountList = new ArrayList<>();
        powerValuesList = new ArrayList<>();
        nodeCountLabel = new JLabel("Mem. Node Count: ");
        externalNodeCountLabel = new JLabel("External Node Count: ");
        meanOffsetLabel = new JLabel("Mean Offset Value:");
        attackSelectLabel = new JLabel("Select Attack: ");
        attackTrustValueLabel = new JLabel("Trust Value of External Node: ");
        attackPercentDishonestRALabel = new JLabel("% Dishonest RA: ");
        selectNodeLabel = new JLabel("Detected Nodes:");
        performanceEvaluationCheckBox = new JCheckBox("Evaluate Performance");
        generateSceneButton = new JButton("Generate Scence");
        showAnalysisButton = new JButton("Analysis");
        powerTrendButton = new JButton("Graphical Analysis");
        resetPowerTrendButton = new JButton("Reset Graphs");
        resetClusterButton = new JButton("Reset Cluster");
        playPauseToggleButton = new JToggleButton(playIcon);
        nodesCountField = new JTextField();
        externalNodesCountField = new JTextField();
        attackTrustValueField = new JTextField();
        attackPercentDishonestField = new JTextField();
        meanOffsetComboBox = new JComboBox(new String[]{"0.1", "0.2", "0.4", "0.6", "0.8"});
        attacksComboBox = new JComboBox(new String[]{"Bad Mouthing", "Ballot Stuffing", "Random Opinion"});
        nodesSelectComboBox = new JComboBox();
        meanOffsetScenarioRadioButton = new JRadioButton("Mean Offset (MO) Scenario (For BS Attack only)");
        attackScenarioRadioButton = new JRadioButton("Attack Scenario");
        radioGroup = new ButtonGroup();
        radioGroup.add(meanOffsetScenarioRadioButton);
        radioGroup.add(attackScenarioRadioButton);
        nodesCountField.setColumns(3);

        //Level 1 Controls
        nodeCountLabel.setBounds(20, 190 - controlsComponontYPos, 150, 30);
        nodesCountField.setBounds(130, 190 - controlsComponontYPos, 80, 30);
        externalNodeCountLabel.setBounds(220, 190 - controlsComponontYPos, 130, 30);
        externalNodesCountField.setBounds(350, 190 - controlsComponontYPos, 70, 30);

        //Level 2 
        performanceEvaluationCheckBox.setBounds(20, 250 - controlsComponontYPos, 200, 30);

        //Level 3
        meanOffsetScenarioRadioButton.setBounds(20, 300 - controlsComponontYPos, 400, 30);
        attackScenarioRadioButton.setBounds(20, 330 - controlsComponontYPos, 400, 30);

        //Level 4
        meanOffsetLabel.setBounds(20, 390 - controlsComponontYPos, 150, 30);
        meanOffsetComboBox.setBounds(140, 390 - controlsComponontYPos, 90, 30);
        attackPercentDishonestRALabel.setBounds(250, 390 - controlsComponontYPos, 120, 30);
        attackPercentDishonestField.setBounds(350, 390 - controlsComponontYPos, 70, 30);

        //Level 5
        attackSelectLabel.setBounds(20, 450 - controlsComponontYPos, 120, 30);
        attacksComboBox.setBounds(110, 450 - controlsComponontYPos, 120, 30);

        //Level 6
        generateSceneButton.setBounds(20, 510 - controlsComponontYPos, 140, 30);
        playPauseToggleButton.setBounds(170, 510 - controlsComponontYPos, 50, 30);
        resetClusterButton.setBounds(230, 510 - controlsComponontYPos, 190, 30);

        //Level 7
        selectNodeLabel.setBounds(20, 570 - controlsComponontYPos, 100, 30);
        nodesSelectComboBox.setBounds(130, 570 - controlsComponontYPos, 90, 30);
        showAnalysisButton.setBounds(230, 570 - controlsComponontYPos, 190, 30);

        //Level 8
        powerTrendButton.setBounds(20, 630 - controlsComponontYPos, 200, 30);
        resetPowerTrendButton.setBounds(230, 630 - controlsComponontYPos, 190, 30);

        //Disable Critical Controls
        showAnalysisButton.setEnabled(false);
        selectNodeLabel.setEnabled(false);
        nodesSelectComboBox.setEnabled(false);
        meanOffsetScenarioRadioButton.setSelected(true);

        setMOControls(false);
        setAttackControls(false);
        setPowerTrendControls(false);
        resetClusterButton.setEnabled(false);

        add(meanOffsetScenarioRadioButton);
        //  add(attackScenarioRadioButton);//
        add(nodeCountLabel);
        add(nodesCountField);
        add(externalNodeCountLabel);
        add(selectNodeLabel);
        add(externalNodesCountField);
        add(meanOffsetLabel);
        add(meanOffsetComboBox);
        add(performanceEvaluationCheckBox);
        add(generateSceneButton);
        add(playPauseToggleButton);
        add(resetClusterButton);
        add(showAnalysisButton);
        add(powerTrendButton);
        add(resetPowerTrendButton);
        // add(attackSelectLabel);//
        // add(attacksComboBox);//
        add(nodesSelectComboBox);
        add(attackTrustValueLabel);
        add(attackTrustValueField);
        add(attackPercentDishonestRALabel);
        add(attackPercentDishonestField);
    }

    public void resetControls() {
        nodesSelectComboBox.removeAllItems();
        showAnalysisButton.setEnabled(false);
        selectNodeLabel.setEnabled(false);
        nodesSelectComboBox.setEnabled(false);
        playPauseToggleButton.setSelected(false);
        playPauseToggleButton.setIcon(playIcon);
        resetClusterButton.setEnabled(true);
    }

    private void initEvents() {
        generateSceneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                triggerButtonActionPerformed();
            }
        });

        playPauseToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playPauseToggleButton.isSelected()) {
                    playPauseToggleButton.setIcon(pauseIcon);
                    scenarioPane.setPlayPauseControlFlag(true);
                    resetClusterButton.setEnabled(false);
                } else {
                    //playPauseToggleButton.setText("|>");
                    playPauseToggleButton.setIcon(playIcon);
                    scenarioPane.setPlayPauseControlFlag(false);
                    resetClusterButton.setEnabled(true);
                }
            }
        });

        resetClusterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scenarioPane.resetCluster();
            }
        });

        showAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String frameTitle = "Recommendation Analysis For \"Node " + (String) nodesSelectComboBox.getSelectedItem() + "\"";
                String graphHeading = "";
                if (performanceEvaluationCheckBox.isSelected()) {
                    graphHeading = "VALIDATION AGAINST DEVIATION (MO LEVEL= " + meanOffsetComboBox.getSelectedItem() + ")";
                } else {
                    graphHeading = "RANDOM SCENARIO";
                }
                /* if (attackScenarioRadioButton.isSelected()) {
                 graphHeading = ((String) attacksComboBox.getSelectedItem()).toUpperCase()+" ATTACK SCENARIO";
                 }*/
                //System.out.println("GRAPH LABEL:" + graphHeading);
            //    new RecommendationAnalyzer_v2(scenarioPane.getRecommendationsForNode(nodesSelectComboBox.getSelectedIndex()),null, frameTitle, graphHeading).setVisible(true);
            }
        });

        performanceEvaluationCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (performanceEvaluationCheckBox.isSelected()) {
                    setMOControls(true);
                } else {
                    setMOControls(false);
                }

            }
        });

        attacksComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });

        attackScenarioRadioButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        powerTrendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("SIZE OF EXT:" + externalNodeCountList.size() + " SIZE OF POWER:" + powerValuesList.size());
                if (memberNodeCountList.size() <= 9) {
                    new GraphsFrame(memberNodeCountList, externalNodeCountList, powerValuesList).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Please Reset the Graph Trend!!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        resetPowerTrendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure want to reset all the graphs?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    memberNodeCountList.removeAll(memberNodeCountList);
                    externalNodeCountList.removeAll(externalNodeCountList);
                    powerValuesList.removeAll(powerValuesList);
                }
            }
        });

    }

    private void setMOControls(boolean flag) {
        meanOffsetScenarioRadioButton.setEnabled(flag);
        meanOffsetLabel.setEnabled(flag);
        meanOffsetComboBox.setEnabled(flag);
        attackPercentDishonestRALabel.setEnabled(flag);
        attackPercentDishonestField.setEnabled(flag);
    }

    public void triggerButtonActionPerformed() {
        if (!nodesCountField.getText().trim().equals("") && !externalNodesCountField.getText().trim().equals("")) {

            int inputNodeCount = Integer.parseInt(nodesCountField.getText().trim());
            int inputExternalNodeCount = Integer.parseInt(externalNodesCountField.getText().trim());
            boolean validDishonestRAField = true;
            String dishonestRACount = attackPercentDishonestField.getText().trim();

            if (((inputNodeCount >= 10) && (inputExternalNodeCount >= 1)) && ((inputNodeCount <= 100) && (inputExternalNodeCount <= 10))) {
                //Validate Dishonest RA Field 
                if (performanceEvaluationCheckBox.isSelected()) {
                    if (dishonestRACount.equals("") || (Integer.parseInt(dishonestRACount) > 100)) {
                        validDishonestRAField = false;
                        JOptionPane.showMessageDialog(null, "Enter % Dishonest RA count (<=100)!", "Alert", JOptionPane.WARNING_MESSAGE);
                    }
                }

                if (validDishonestRAField) {
                    resetControls();

                    setPowerTrendControls(false);

                    scenarioPane.setInputNodeCount(inputNodeCount);

                    createNodes(inputNodeCount, inputExternalNodeCount);

                    scenarioPane.configureNodes(inputNodeCount);

                    scenarioPane.configureExternalNodes(inputExternalNodeCount);

                    scenarioPane.configureServiceProviders(4);

                    if (performanceEvaluationCheckBox.isSelected()) {
                        scenarioPane.generateRecommendationsForPE(inputNodeCount, inputExternalNodeCount, 1, Integer.parseInt(dishonestRACount), true, Double.parseDouble((String) meanOffsetComboBox.getSelectedItem()));
                    } else {
                        scenarioPane.generateRandomRecommendations(inputNodeCount - 1, inputExternalNodeCount);
                    }

                    scenarioPane.setSceneTriggered(true);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Mem Node Count (>=10&&<=100) & Ext Node Count (>=1&&<=10)!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Enter valid node & external node count!", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void createNodes(int inputNodeCount, int inputExternalNodeCount) {
        //Member Nodes
        node = new MobileNode[inputNodeCount];
        for (int i = 0; i < inputNodeCount; i++) {
            node[i] = new MobileNode();
            node[i].setEnergyLevel(100);
        }
        scenarioPane.setNode(node);

        //Service providers
        serviceProviders = new MobileNode[4];
        for (int i = 0; i < 4; i++) {
            serviceProviders[i] = new MobileNode();
            serviceProviders[i].setEnergyLevel(100);
        }
        scenarioPane.setServiceProviders(serviceProviders);

        //External Nodes
        externalNodes = new MobileNode[inputExternalNodeCount];
        char nodeLabel = 'A';
        for (int i = 0; i < inputExternalNodeCount; i++) {
            externalNodes[i] = new MobileNode();
            externalNodes[i].setEnergyLevel(100);
            nodesSelectComboBox.addItem("Node-" + nodeLabel);
            nodeLabel++;
        }
        scenarioPane.setExternalNodes(externalNodes);

    }

    public void setAttackControls(boolean flag) {
        attackSelectLabel.setEnabled(flag);
        attacksComboBox.setEnabled(flag);
        attackTrustValueLabel.setEnabled(flag);
        attackTrustValueField.setEnabled(flag);
        attackPercentDishonestRALabel.setEnabled(flag);
    }

    public void setPowerTrendControls(boolean flag) {
        powerTrendButton.setEnabled(flag);
        resetPowerTrendButton.setEnabled(flag);
    }

    public void updateExternalNodeComboBox() {
        getNodesSelectComboBox().removeAllItems();
        for (int i = 0; i < externalNodes.length; i++) {
            getNodesSelectComboBox().addItem(externalNodes[i].getNodeLabel());
        }
    }

    public JCheckBox getPerformanceEvaluationCheckBox() {
        return performanceEvaluationCheckBox;
    }

    public JButton getShowAnalysisButton() {
        return showAnalysisButton;
    }

    public int getAttackType() {
        return attackType;
    }

    public JTextField getTrustValueField() {
        return attackTrustValueField;
    }

    public JRadioButton getAttackScenarioRadioButton() {
        return attackScenarioRadioButton;
    }

    public JComboBox getAttacksComboBox() {
        return attacksComboBox;
    }

    public JTextField getAttackPercentDishonestField() {
        return attackPercentDishonestField;
    }

    public JRadioButton getMeanOffsetScenarioRadioButton() {
        return meanOffsetScenarioRadioButton;
    }

    public JButton getGenerateSceneButton() {
        return generateSceneButton;
    }

    public JLabel getSelectNodeLabel() {
        return selectNodeLabel;
    }

    public JComboBox getNodesSelectComboBox() {
        return nodesSelectComboBox;
    }

    public ArrayList<Double> getPowerValuesList() {
        return powerValuesList;
    }

    public ArrayList<Integer> getMemberNodeCountList() {
        return memberNodeCountList;
    }

    public ArrayList<Integer> getExternalNodeCountList() {
        return externalNodeCountList;
    }


}
