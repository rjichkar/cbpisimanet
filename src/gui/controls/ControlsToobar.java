/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controls;

import apis.IConfiguration;
import engine.MobileNode;
import engine.RecommendationAnalyzer_v2;
import gui.graphs.MOGraphParameters;
import gui.main.MainGUI;
import gui.scenario.ScenarioPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.TreeSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;

/**
 *
 * @author SHRI
 */
public class ControlsToobar implements IConfiguration {

    private MobileNode node[];
    private MobileNode externalNodes[];
    private MobileNode serviceProviders[];
    private TreeSet<String> paramSet;

    private ConfigureInputDialog configureInputDialog;
    private ChooseAnalysisDialog chooseAnalysisDialog;
    private ScenarioPane scenarioPane;
    private MainGUI mainGUI;

    private JLabel selectDetectedNodeLabel;

    private JSpinner externalNodeCountSpinner;
    private JSpinner inputNodesCountSpinner;
    private JSpinner percentDishonestRASpinner;
    private JCheckBox evaluatePerformanceCheckBox;
    private JCheckBox keepStaticRecommendationsCheckBox;
    private JRadioButton meanOffsetScenarioRadioButton;
    private JRadioButton attackScenarioRadioButton;
    private JRadioButton attackScenarioIdealRadioButton;
    private JComboBox meanOffsetValueComboBox;
    private JComboBox selectAttackComboBox;
    private JComboBox selectDetectedNodeComboBox;

    private JButton showAlgorithmAnalysisButton;
    private JButton showGraphicalAnalysisButton;
    private JButton resetGraphicalAnalysisButton;

    private JToggleButton playPauseToggleButton;

    private ImageIcon playIcon;
    private ImageIcon pauseIcon;

    public ControlsToobar(ScenarioPane scenarioPane, MainGUI mainGUI) {
        initComponents(scenarioPane, mainGUI);
        initEvents();
    }

    private void initComponents(ScenarioPane scenarioPane, MainGUI mainGUI) {
        this.scenarioPane = scenarioPane;
        this.mainGUI = mainGUI;

        //For MO Graph Parameters
        paramSet = new TreeSet<>();

        //Load Images
        playIcon = new ImageIcon(getClass().getResource("/images/play.jpg"));
        pauseIcon = new ImageIcon(getClass().getResource("/images/pause.jpg"));

        configureInputDialog = new ConfigureInputDialog(null, true, this);
        chooseAnalysisDialog = new ChooseAnalysisDialog(null, true);

        //Configure Input Dialog Controls
        inputNodesCountSpinner = configureInputDialog.getInputNodesCountSpinner();
        externalNodeCountSpinner = configureInputDialog.getExternalNodeCountSpinner();
        percentDishonestRASpinner = configureInputDialog.getPercentDishonestRASpinner();
        evaluatePerformanceCheckBox = configureInputDialog.getEvaluatePerformanceCheckBox();
        meanOffsetScenarioRadioButton = configureInputDialog.getMeanOffsetScenarioRadioButton();
        attackScenarioRadioButton = configureInputDialog.getAttackScenarioRadioButton();
        attackScenarioIdealRadioButton = configureInputDialog.getAttackScenarioIdealRadioButton();
        meanOffsetValueComboBox = configureInputDialog.getMeanOffsetValueComboBox();
        selectAttackComboBox = configureInputDialog.getSelectAttackComboBox();
        keepStaticRecommendationsCheckBox = configureInputDialog.getKeepStaticRecommendationsCheckBox();

        //Toolbar Controls
        playPauseToggleButton = mainGUI.getPlayPauseToggleButton();

        //Configure Analysis Dialog Controls
        selectDetectedNodeLabel = chooseAnalysisDialog.getSelectDetectedNodeLabel();
        selectDetectedNodeComboBox = chooseAnalysisDialog.getSelectDetectedNodeComboBox();
        showAlgorithmAnalysisButton = chooseAnalysisDialog.getShowAlgorithmAnalysisButton();
        showGraphicalAnalysisButton = chooseAnalysisDialog.getShowGraphicalAnalysisButton();
        resetGraphicalAnalysisButton = chooseAnalysisDialog.getResetGraphicalAnalysisButton();

        //Initialize Controls
        setPerformanceEvaluationControls(false);
        setAnalysisControls(false);
        keepStaticRecommendationsCheckBox.setEnabled(false);
    }

    private void initEvents() {
        //Evaluate Performance Checkbox Event
        evaluatePerformanceCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (evaluatePerformanceCheckBox.isSelected()) {
                    setPerformanceEvaluationControls(true);

                    if (attackScenarioRadioButton.isSelected()) {
                        keepStaticRecommendationsCheckBox.setEnabled(true);
                    }

                    if (meanOffsetScenarioRadioButton.isSelected()) {
                        setMeanOffsetControls(true);
                    } else {
                        setMeanOffsetControls(false);
                    }
                } else {
                    setPerformanceEvaluationControls(false);
                    keepStaticRecommendationsCheckBox.setEnabled(false);
                }
            }
        });

        //Mean Offset Scenario Radio Button (Lambda Expression)
        meanOffsetScenarioRadioButton.addActionListener((e) -> {
            setMeanOffsetControls(true);
            keepStaticRecommendationsCheckBox.setEnabled(false);
        });

        keepStaticRecommendationsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keepStaticRecommendationsCheckBox.isSelected()) {
                    configureInputDialog.getSelectAttackLabel().setEnabled(false);
                    selectAttackComboBox.setEnabled(false);
                } else {
                    configureInputDialog.getSelectAttackLabel().setEnabled(true);
                    selectAttackComboBox.setEnabled(true);
                }
            }
        });

        //Attack Scenario Radio Button
        attackScenarioRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMeanOffsetControls(false);
                keepStaticRecommendationsCheckBox.setEnabled(true);
            }
        });

        //Attack Scenario Ideal Radio Button
        attackScenarioIdealRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMeanOffsetControls(false);
                keepStaticRecommendationsCheckBox.setEnabled(false);
            }
        });

        //Show Algorithm Analysis Radio Button
        showAlgorithmAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                chooseAnalysisDialog.dispose();

                String frameTitle = "Recommendation Analysis For \"Node " + selectDetectedNodeComboBox.getSelectedItem() + "\"";
                String graphHeading = "";

                if (evaluatePerformanceCheckBox.isSelected()) {
                    if (attackScenarioRadioButton.isSelected()) {
                        graphHeading = "VALIDATION AGAINST ATTACKS , ATTACK TYPE: " + ((String) selectAttackComboBox.getSelectedItem()).toUpperCase() + ", % DISHONEST RA=" + percentDishonestRASpinner.getValue();
                    } else if (attackScenarioIdealRadioButton.isSelected()) {
                        graphHeading = "VALIDATION AGAINST ATTACKS , ATTACK TYPE: " + ((String) selectAttackComboBox.getSelectedItem()).toUpperCase() + " (IDEAL MODE), % DISHONEST RA=" + percentDishonestRASpinner.getValue();
                    } else {
                        graphHeading = "VALIDATION AGAINST DEVIATION , ATTACK TYPE: " + ((String) selectAttackComboBox.getSelectedItem()).toUpperCase() + " , MO LEVEL= " + meanOffsetValueComboBox.getSelectedItem() + ", % DISHONEST RA=" + percentDishonestRASpinner.getValue();
                    }
                } else {
                    graphHeading = "RANDOM SCENARIO";
                }
                //Set Parameters for MO Graph
                boolean moMode = evaluatePerformanceCheckBox.isSelected() & meanOffsetScenarioRadioButton.isSelected();
                if (moMode) {
                    MOGraphParameters moGraphParameters = scenarioPane.getmOGraphParameters();
                    paramSet.add(moGraphParameters.getPercentDishonestRA() + "-" + moGraphParameters.getRecommendedTrustActual() + "-" + moGraphParameters.getRecommendedTrustWOD() + "-" + moGraphParameters.getRecommendedTrustWD());

                    Iterator<String> iterator = paramSet.iterator();
                    while (iterator.hasNext()) {
                        System.out.println("PARAMS:" + iterator.next());
                    }
                }
                /*
                 System.out.println("RCC BEFORE ANALYSIS DISPLAY:");
                 for(int i:scenarioPane.getRecommendationsForNode(selectDetectedNodeComboBox.getSelectedIndex())){
                 System.out.print(i+",");
                 }
                 */
                new RecommendationAnalyzer_v2(scenarioPane.getRecommendationsForNode(selectDetectedNodeComboBox.getSelectedIndex()), paramSet, moMode, frameTitle, graphHeading).setVisible(true);
            }
        });

        //Show Graphical Analysis Button
        showGraphicalAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("SIZE OF EXT:" + externalNodeCountList.size() + " SIZE OF POWER:" + powerValuesList.size());
                /*if (memberNodeCountList.size() <= 9) {
                 new GraphsFrame(memberNodeCountList, externalNodeCountList, powerValuesList).setVisible(true);
                 } else {
                 JOptionPane.showMessageDialog(null, "Please Reset the Graph Trend!!", "Alert", JOptionPane.WARNING_MESSAGE);
                 }*/
            }
        });

        //Reset Graphical Analysis Button
        resetGraphicalAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure want to reset all the graphs?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    /*  memberNodeCountList.removeAll(memberNodeCountList);
                     externalNodeCountList.removeAll(externalNodeCountList);
                     powerValuesList.removeAll(powerValuesList);*/
                }
            }
        });

        meanOffsetValueComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                paramSet.removeAll(paramSet);
            }
        });

        selectAttackComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //System.out.println("CHANGED!");
                paramSet.removeAll(paramSet);
            }
        });

    }

    public void playPauseToggleButtonActionPerformed() {
        if (playPauseToggleButton.isSelected()) {
            playPauseToggleButton.setIcon(pauseIcon);
            scenarioPane.setPlayPauseControlFlag(true);
            // resetClusterButton.setEnabled(false);
        } else {
            //playPauseToggleButton.setText("|>");
            playPauseToggleButton.setIcon(playIcon);
            scenarioPane.setPlayPauseControlFlag(false);
            //resetClusterButton.setEnabled(true);
        }
    }

    public void showConfigureInputDialog(boolean flag) {
        configureInputDialog.setVisible(flag);
    }

    public void showChooseAnalysisDialog(boolean flag) {
        chooseAnalysisDialog.setVisible(flag);
    }

    private void setMeanOffsetControls(boolean flag) {
        configureInputDialog.getMeanOffsetValueLabel().setEnabled(flag);
        meanOffsetValueComboBox.setEnabled(flag);
    }

    private void setAnalysisControls(boolean flag) {
        selectDetectedNodeLabel.setEnabled(flag);
        selectDetectedNodeComboBox.setEnabled(flag);
        showAlgorithmAnalysisButton.setEnabled(flag);
        showGraphicalAnalysisButton.setEnabled(flag);
        resetGraphicalAnalysisButton.setEnabled(flag);
    }

    private void resetControls() {
        setAnalysisControls(false);
        selectDetectedNodeComboBox.removeAllItems();
        playPauseToggleButton.setSelected(false);
        playPauseToggleButton.setIcon(playIcon);
        // resetClusterButton.setEnabled(true);
    }

    private void setPerformanceEvaluationControls(boolean flag) {
        meanOffsetScenarioRadioButton.setEnabled(flag);
        attackScenarioRadioButton.setEnabled(flag);
        attackScenarioIdealRadioButton.setEnabled(flag);
        configureInputDialog.getMeanOffsetValueLabel().setEnabled(flag);
        meanOffsetValueComboBox.setEnabled(flag);
        configureInputDialog.getPercentDishonestRALabel().setEnabled(flag);
        percentDishonestRASpinner.setEnabled(flag);
        configureInputDialog.getSelectAttackLabel().setEnabled(flag);
        selectAttackComboBox.setEnabled(flag);
    }

    public void generateScenarioActionPerformed() {

        int inputNodeCount = (Integer) inputNodesCountSpinner.getValue();
        int inputExternalNodeCount = (Integer) externalNodeCountSpinner.getValue();
        int percentDishonestRA = (Integer) percentDishonestRASpinner.getValue();

        //System.out.println("INPUT NODE COUNT:" + inputNodeCount + " EXT NODE COUNT:" + inputExternalNodeCount);
        boolean validDishonestRAField = true;

        if (((inputNodeCount >= 10) && (inputExternalNodeCount >= 1)) && ((inputNodeCount <= 100) && (inputExternalNodeCount <= 10))) {
            //Validate Dishonest RA Field 
            if (evaluatePerformanceCheckBox.isSelected()) {
                if (percentDishonestRA > 100) {
                    validDishonestRAField = false;
                    JOptionPane.showMessageDialog(null, "Enter % Dishonest RA count (<=100)!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }

            if (validDishonestRAField) {
                resetControls();

                setGraphTrendControls(false);

                scenarioPane.setInputNodeCount(inputNodeCount);

                createNodes(inputNodeCount, inputExternalNodeCount);

                scenarioPane.configureNodes(inputNodeCount);

                scenarioPane.configureExternalNodes(inputExternalNodeCount);

                if (!keepStaticRecommendationsCheckBox.isSelected()) {
                    scenarioPane.configureServiceProviders(4);
                }

                if (evaluatePerformanceCheckBox.isSelected()) {
                    if (!keepStaticRecommendationsCheckBox.isSelected()) {
                        scenarioPane.generateRecommendationsForPE(inputNodeCount, inputExternalNodeCount, selectAttackComboBox.getSelectedIndex(), percentDishonestRA, meanOffsetScenarioRadioButton.isSelected(), Double.parseDouble((String) meanOffsetValueComboBox.getSelectedItem()));
                    } else {
                        scenarioPane.generateStaticRecommendations(percentDishonestRA);
                    }
                } else {
                    scenarioPane.generateRandomRecommendations(inputNodeCount, inputExternalNodeCount);
                }
                //Display
               /* for (int i[] : scenarioPane.getRecommendations()) {
                 System.out.print("RCC: ");
                 for (int j : i) {
                 System.out.print(j + ",");
                 }
                 System.out.println("");
                 }*/

                scenarioPane.setSceneTriggered(true);

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
        serviceProviders = new MobileNode[SERVICE_PROVIDERS_COUNT];
        for (int i = 0; i < SERVICE_PROVIDERS_COUNT; i++) {
            serviceProviders[i] = new MobileNode();
            serviceProviders[i].setEnergyLevel(100);
            selectDetectedNodeComboBox.addItem("CH-" + (i + 1));
        }
        scenarioPane.setServiceProviders(serviceProviders);

        //External Nodes
        externalNodes = new MobileNode[inputExternalNodeCount];
        char nodeLabel = 'A';
        for (int i = 0; i < inputExternalNodeCount; i++) {
            externalNodes[i] = new MobileNode();
            externalNodes[i].setEnergyLevel(100);
            //  selectDetectedNodeComboBox.addItem("" + (i+1));
            nodeLabel++;
        }
        scenarioPane.setExternalNodes(externalNodes);
    }

    public void setGraphTrendControls(boolean flag) {
        showGraphicalAnalysisButton.setEnabled(flag);
        resetGraphicalAnalysisButton.setEnabled(flag);
    }

    public void updateExternalNodeComboBox() {
        selectDetectedNodeComboBox.removeAllItems();
        for (int i = 0; i < externalNodes.length; i++) {
            selectDetectedNodeComboBox.addItem(externalNodes[i].getNodeLabel());
        }
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

    public ConfigureInputDialog getConfigureInputDialog() {
        return configureInputDialog;
    }

    public ChooseAnalysisDialog getChooseAnalysisDialog() {
        return chooseAnalysisDialog;
    }

    public ScenarioPane getScenarioPane() {
        return scenarioPane;
    }

    public MainGUI getMainGUI() {
        return mainGUI;
    }

    public JSpinner getExternalNodeCountSpinner() {
        return externalNodeCountSpinner;
    }

    public JSpinner getInputNodesCountSpinner() {
        return inputNodesCountSpinner;
    }

    public JSpinner getPercentDishonestRASpinner() {
        return percentDishonestRASpinner;
    }

    public JCheckBox getEvaluatePerformanceCheckBox() {
        return evaluatePerformanceCheckBox;
    }

    public JRadioButton getMeanOffsetScenarioRadioButton() {
        return meanOffsetScenarioRadioButton;
    }

    public JRadioButton getAttackScenarioRadioButton() {
        return attackScenarioRadioButton;
    }

    public JRadioButton getAttackScenarioIdealRadioButton() {
        return attackScenarioIdealRadioButton;
    }

    public void setAttackScenarioIdealRadioButton(JRadioButton attackScenarioIdealRadioButton) {
        this.attackScenarioIdealRadioButton = attackScenarioIdealRadioButton;
    }

    public JComboBox getMeanOffsetValueComboBox() {
        return meanOffsetValueComboBox;
    }

    public JLabel getSelectDetectedNodeLabel() {
        return selectDetectedNodeLabel;
    }

    public JComboBox getSelectAttackComboBox() {
        return selectAttackComboBox;
    }

    public JComboBox getSelectDetectedNodeComboBox() {
        return selectDetectedNodeComboBox;
    }

    public JButton getShowAlgorithmAnalysisButton() {
        return showAlgorithmAnalysisButton;
    }

    public JButton getShowGraphicalAnalysisButton() {
        return showGraphicalAnalysisButton;
    }

    public JButton getResetGraphicalAnalysisButton() {
        return resetGraphicalAnalysisButton;
    }

    public JToggleButton getPlayPauseToggleButton() {
        return playPauseToggleButton;
    }

    public ImageIcon getPlayIcon() {
        return playIcon;
    }

    public ImageIcon getPauseIcon() {
        return pauseIcon;
    }

}
