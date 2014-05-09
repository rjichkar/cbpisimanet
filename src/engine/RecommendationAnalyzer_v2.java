/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import custom.CustomTableCellRenderer;
import custom.CustomTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author SHRI
 */
public final class RecommendationAnalyzer_v2 extends javax.swing.JFrame {

    private static final int BIN_SIZE = 10;
    private String graphHeading;
    private TreeSet moParamSet;
    private boolean moModeSelected;

    /*Reference Data Set*/
    //Data Set 1
   /* private static int recommendations[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
     1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
     2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
     3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9};*/
    //Data Set 2
    /*private static int recommendations[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
     1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};*/
    //Data Set 3
    //private static int recommendations[] = {5, 5, 5, 5, 5, 5,5};
    //Test Data Set 1 for RO Attack 
   /* private static int recommendations[]
     = {1, 2, 9, 3, 4,
     7, 3, 4, 7, 3,
     4, 7, 3, 4, 7,
     3, 4, 7, 3, 4,
     7, 3, 4, 7, 3,
     4, 7, 3, 4, 7,};*/
    //Test Data Set 2 for RO Attack 
    /*private static int recommendations[]
     = { 1, 2, 9, 10, 4,
     5, 6, 7, 4, 5,
     6, 7, 4, 5, 6,
     7, 4, 5, 6, 7,
     4, 5, 6, 7, 4,
     5, 6, 7, 4, 5,
     6, 7, 4, 5, 6,
     7, 4, 5, 6, 7};*/
    //Test Data Set for BS Attack
    private static int recommendations[]
            = {9, 8, 10, 9, 8,
                10, 9, 8, 10, 9,
                8, 10, 2, 3, 1,
                2, 3, 1, 2, 3,
                1, 2, 3, 1, 2,
                3, 1, 2, 3, 1};
    // private static int recommendations[] = {0, 2, 1, 0, 3, 2, 0, 0, 2, 2};
    /**
     * Creates new form TabbedPane
     */
    public RecommendationAnalyzer_v2() {
        initComponents();
    }

    public RecommendationAnalyzer_v2(int recommendations[]) {
        initComponents();
        moGraphPaneTab.setMOHistogramParams();
        setMoModeSelected(true);
        computeAlgorithmResults(recommendations);
    }

    public RecommendationAnalyzer_v2(int recommendations[], TreeSet moParamSet, boolean moModeSelected, String frameTitle, String graphHeading) {
        initComponents();
        setTitle(frameTitle);
        this.moParamSet = moParamSet;
        this.moModeSelected = moModeSelected;
        this.graphHeading = graphHeading;
        computeAlgorithmResults(recommendations);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        stepOneTab = new javax.swing.JPanel();
        medianLabel = new javax.swing.JLabel();
        tabOneMessageLabel = new javax.swing.JLabel();
        freqDistPanel = new javax.swing.JPanel();
        freqDistScrollPane = new javax.swing.JScrollPane();
        freqDistributionTable = new javax.swing.JTable();
        stepTwoTab = new javax.swing.JPanel();
        sortedDFsPanel = new javax.swing.JPanel();
        sortedDFsScrollPane = new javax.swing.JScrollPane();
        sortedDFsTable = new javax.swing.JTable();
        stepThreeTab = new javax.swing.JPanel();
        resultTablePanel = new javax.swing.JPanel();
        resultTableScrollpane = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        smoothingFactorTablePanel = new javax.swing.JPanel();
        smoothingFactorsScrollPane = new javax.swing.JScrollPane();
        smoothingFactorTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Recommendations Analysis");
        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(null);

        tabbedPane.setPreferredSize(new java.awt.Dimension(800, 498));

        stepOneTab.setLayout(null);

        medianLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        stepOneTab.add(medianLabel);
        medianLabel.setBounds(410, 280, 150, 20);

        tabOneMessageLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        stepOneTab.add(tabOneMessageLabel);
        tabOneMessageLabel.setBounds(220, 260, 650, 20);

        freqDistPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Table 1:   Frequency Distribution of Recommendations", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        freqDistPanel.setLayout(new java.awt.BorderLayout());

        freqDistributionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "RCi", "Recommendation Value ( rc i )", "Frequncy ( fi )"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        freqDistScrollPane.setViewportView(freqDistributionTable);

        freqDistPanel.add(freqDistScrollPane, java.awt.BorderLayout.CENTER);

        stepOneTab.add(freqDistPanel);
        freqDistPanel.setBounds(0, 30, 1000, 230);

        tabbedPane.addTab("Step 1", stepOneTab);

        stepTwoTab.setLayout(null);

        sortedDFsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Table 2:  Recommendation classes sorted with respect to their DFs", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        sortedDFsPanel.setLayout(new java.awt.BorderLayout());

        sortedDFsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        sortedDFsScrollPane.setViewportView(sortedDFsTable);

        sortedDFsPanel.add(sortedDFsScrollPane, java.awt.BorderLayout.CENTER);

        stepTwoTab.add(sortedDFsPanel);
        sortedDFsPanel.setBounds(0, 30, 1000, 210);

        tabbedPane.addTab("Step 2", stepTwoTab);

        stepThreeTab.setPreferredSize(new java.awt.Dimension(645, 350));
        stepThreeTab.setLayout(null);

        resultTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Results Table"));
        resultTablePanel.setLayout(new java.awt.BorderLayout());

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        resultTableScrollpane.setViewportView(resultTable);

        resultTablePanel.add(resultTableScrollpane, java.awt.BorderLayout.CENTER);

        stepThreeTab.add(resultTablePanel);
        resultTablePanel.setBounds(260, 290, 470, 120);

        smoothingFactorTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Table 3:  Smoothing factor computation", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        smoothingFactorTablePanel.setLayout(new java.awt.BorderLayout());

        smoothingFactorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        smoothingFactorsScrollPane.setViewportView(smoothingFactorTable);

        smoothingFactorTablePanel.add(smoothingFactorsScrollPane, java.awt.BorderLayout.CENTER);

        stepThreeTab.add(smoothingFactorTablePanel);
        smoothingFactorTablePanel.setBounds(0, 30, 1000, 190);

        tabbedPane.addTab("Step 3", stepThreeTab);
        sfGraphPaneTab=new gui.graphs.SFGraphPane();
        sfGraphPaneTab.setPreferredSize(new java.awt.Dimension(645, 350));
        tabbedPane.add("Smoothing Factor Trend", sfGraphPaneTab);

        moGraphPaneTab=new gui.graphs.MOGraphPane();
        moGraphPaneTab.setPreferredSize(new java.awt.Dimension(645, 350));
        tabbedPane.add("Validation Against Deviation", moGraphPaneTab);

        getContentPane().add(tabbedPane);
        tabbedPane.setBounds(0, 0, 1010, 500);

        setSize(new java.awt.Dimension(1019, 539));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RecommendationAnalyzer_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecommendationAnalyzer_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecommendationAnalyzer_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecommendationAnalyzer_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecommendationAnalyzer_v2(recommendations).setVisible(true);
            }
        });
    }

    public Double computeAlgorithmResults(int recommendations[]) {

        String rcsFreq[] = new String[BIN_SIZE];
        StringBuilder msg = new StringBuilder();
        DecimalFormat dformat = new DecimalFormat("##.####");

        String[] columnNamesTableOne = {"RCi", "Recommendation value rci", " Frequency fi"};
        String[] columnNamesTableTwo = {"Rci", "Recommendation value rci", "Frequency fi", "DF (Rci)"};
        String[] columnNamesTableThree = {"SRdomain", "Rdomain-SRdomain", "DF(Rdomain-SRdomain)", "SF"};
        String[] columnNamesResultTable = {"Result Parameters", "Values"};

        DefaultTableModel freqDistModel = new DefaultTableModel(columnNamesTableOne, BIN_SIZE);
        freqDistributionTable.setModel(freqDistModel);

        int count = 0;

        //Step 1:Compute freqencies & classify the recommendations into bins
        for (int i = 1; i <= BIN_SIZE; i++) {
            count = 0;
            for (int j = 0; j < recommendations.length; j++) {
                if (i == recommendations[j]) {
                    count++;
                }
            }
            rcsFreq[i - 1] = (i / 10.0) + "-" + count;

            freqDistributionTable.getModel().setValueAt("RC" + i, i - 1, 0);
            freqDistributionTable.getModel().setValueAt(i / 10.0, i - 1, 1);
            freqDistributionTable.getModel().setValueAt(count, i - 1, 2);

        }

        int freqMax = Integer.parseInt(rcsFreq[((recommendations[0] - 1) >= 0) ? (recommendations[0] - 1) : 0].split("-")[1]);

        if (recommendations.length == freqMax) {
            // System.out.println("ALL RECOMMENDATIONS ARE SAME..NO FURTHER COMPUTATION CAN BE PERFORMED!!!");
            tabbedPane.remove(stepTwoTab);
            tabbedPane.remove(stepThreeTab);
            tabbedPane.remove(sfGraphPaneTab);
            tabbedPane.remove(moGraphPaneTab);
            tabOneMessageLabel.setText("ALL RECOMMENDATIONS ARE SAME..NO FURTHER COMPUTATION CAN BE PERFORMED!!!");

            return (recommendations[0] / 10.0);
        } else {

            //Step 2: Filter out the zero frequency values
            ArrayList<String> rDomain = new ArrayList<>();
            int rcCounter = 1;
            for (int i = 0; i < rcsFreq.length; i++) {
                String rcsFq[] = rcsFreq[i].split("-");
                if (!rcsFq[1].equals("0")) {
                    rDomain.add(rcCounter + "-" + rcsFreq[i]);
                    rcCounter++;
                }
            }

            //Step 3: Find the median value of RDomain
            //Step 3.1: Sorting Frequencies
            for (int i = 0; i < rDomain.size(); i++) {
                for (int j = 0; j < rDomain.size(); j++) {
                    int a = Integer.parseInt(rDomain.get(i).split("-")[2]);
                    int b = Integer.parseInt(rDomain.get(j).split("-")[2]);
                    String temp;
                    if (a < b) {
                        temp = rDomain.get(i);
                        rDomain.set(i, rDomain.get(j));
                        rDomain.set(j, temp);
                    }
                }
            }

            //Step 3.2: Find Median Rci
            double medianRDomain = Double.parseDouble(rDomain.get((int) Math.floor(rDomain.size() / 2.0)).split("-")[1]);

            for (int i = 0; i < rDomain.size(); i++) {
                double rci = Double.parseDouble(rDomain.get(i).split("-")[1]);
                int freq = Integer.parseInt(rDomain.get(i).split("-")[2]);
                double df = (Math.pow((rci - medianRDomain), 2)) / freq;
                rDomain.set(i, rDomain.get(i) + "-" + dformat.format(df));
            }

            medianLabel.setText("Median Value: " + medianRDomain);

            //Step 3.3: Sorting with respect to DF
            DefaultTableModel sortedDFsModel = new DefaultTableModel(columnNamesTableTwo, rDomain.size());
            sortedDFsTable.setModel(sortedDFsModel);

            for (int i = 0; i < rDomain.size(); i++) {
                for (int j = 0; j < rDomain.size(); j++) {
                    double a = Double.parseDouble(rDomain.get(i).split("-")[3]);
                    double b = Double.parseDouble(rDomain.get(j).split("-")[3]);
                    String temp;
                    if (a > b) {
                        temp = rDomain.get(i);
                        rDomain.set(i, rDomain.get(j));
                        rDomain.set(j, temp);
                    }
                }
            }

            for (int i = 0; i < rDomain.size(); i++) {
                sortedDFsTable.getModel().setValueAt("RC" + rDomain.get(i).split("-")[0], i, 0);
                sortedDFsTable.getModel().setValueAt(rDomain.get(i).split("-")[1], i, 1);
                sortedDFsTable.getModel().setValueAt(rDomain.get(i).split("-")[2], i, 2);
                sortedDFsTable.getModel().setValueAt(rDomain.get(i).split("-")[3], i, 3);
            }

            sortedDFsTable.setRowSelectionInterval(0, 0);

            //Step 4: Smoothing factor computation
            DefaultTableModel smoothingFactorModel = new DefaultTableModel(columnNamesTableThree, rDomain.size() - 1);
            smoothingFactorTable.setModel(smoothingFactorModel);

            ArrayList<String> srDomainSF = new ArrayList<>();
            ArrayList<Double> smoothingFactorList = new ArrayList();
            for (int i = 0; i < rDomain.size() - 1; i++) {
                double sumDF = 0;
                int sumFreq = 0;
                String srDomainStr = "{";
                String rSRDomainStr = "{";

                for (int j = 0; j <= i; j++) {
                    // System.out.print(rDomain.get(j)+" ");
                    sumDF += (Double.parseDouble(rDomain.get(j).split("-")[3]));
                    srDomainStr += rDomain.get(j).split("-")[1] + ((j == i) ? "" : ",");
                }
                srDomainStr += "}";

                for (int k = i + 1; k < rDomain.size(); k++) {
                    //  System.out.print(rDomain.get(k)+" ");
                    sumFreq += (Integer.parseInt(rDomain.get(k).split("-")[2]));
                    rSRDomainStr += rDomain.get(k).split("-")[1] + ((k == (rDomain.size() - 1)) ? "" : ",");
                }
                rSRDomainStr += "}";

                //Calculate Smoothing Factor (SF) 
                String smoothingFactor = dformat.format(sumDF * sumFreq) + "";

                smoothingFactorList.add(Double.parseDouble(smoothingFactor));

                smoothingFactorTable.getModel().setValueAt(srDomainStr, i, 0);
                smoothingFactorTable.getModel().setValueAt(rSRDomainStr, i, 1);
                smoothingFactorTable.getModel().setValueAt(dformat.format(sumDF), i, 2);
                smoothingFactorTable.getModel().setValueAt(smoothingFactor, i, 3);
                srDomainSF.add(srDomainStr + ":" + rSRDomainStr + ":" + sumDF + ":" + smoothingFactor);
            }

            //Find index for largest SF
            double highestSF = smoothingFactorList.get(0);

            int highestSFIndex = 0;
            for (int i = 0; i < smoothingFactorList.size(); i++) {
                if (highestSF < smoothingFactorList.get(i)) {
                    highestSF = smoothingFactorList.get(i);
                    highestSFIndex = i;
                }
            }

            smoothingFactorTable.setRowSelectionInterval(highestSFIndex, highestSFIndex);
            String dishonestReccomendationClasses = srDomainSF.get(highestSFIndex).split(":")[0];

            String rDomainFliteredSet = srDomainSF.get(highestSFIndex).split(":")[1];
            String rDomainFilteredClasses[] = rDomainFliteredSet.split("\\{")[1].split("\\}")[0].split(",");
            String srDomainClasses[] = dishonestReccomendationClasses.split("\\{")[1].split("\\}")[0].split(",");

            ArrayList<Integer> freqList = new ArrayList<Integer>();
            for (int i = 0; i < rDomainFilteredClasses.length; i++) {
                for (int j = 0; j < rDomain.size(); j++) {
                    if (rDomainFilteredClasses[i].equals(rDomain.get(j).split("-")[1])) {
                        freqList.add(Integer.parseInt(rDomain.get(j).split("-")[2]));
                    }
                }
            }

            //Calculate Trust Value
            String trustParams[] = computeTrustValueMethod1(freqList, rDomainFilteredClasses).split("-");

            double trustValue = Double.parseDouble(trustParams[0]);
            int freqHighestIndex = Integer.parseInt(trustParams[1]);

            //double trustValue = computeTrustValueMethod2(srDomainClasses, rDomainFilteredClasses);
            String trustDecision = "";
            if (trustValue >= 0.8) {
                trustDecision = "HIGH";
            } else if (trustValue >= 0.4 && trustValue <= 0.7) {
                trustDecision = "MEDIUM";
            } else if (trustValue >= 0.1 && trustValue <= 0.3) {
                trustDecision = "LOW";
            }
            //Plotting graph for Smoothing Factor
            sfGraphPaneTab.setHistogramParams(smoothingFactorList, highestSFIndex, graphHeading);

            //Plotting graph for MO Level if MO mode selected
            if (moModeSelected) {
                if (moParamSet != null) {
                    moGraphPaneTab.setMOHistogramParams(moParamSet, graphHeading);
                }
            } else {
                tabbedPane.remove(moGraphPaneTab);
            }
            //Result Data
            Object[][] data = {
                {"Dishonest Recommendation Classes", dishonestReccomendationClasses},
                {"Final Trust Value", trustValue},
                {"Rcc Frequency", freqList.get(freqHighestIndex)},
                {"Trust Level", trustDecision}
            };
            //Set Table model for Result Table
            CustomTableModel customTableModel = new CustomTableModel(columnNamesResultTable, data);
            resultTable.setModel(customTableModel);
            TableCellRenderer renderer = new CustomTableCellRenderer();
            try {
                resultTable.setDefaultRenderer(Class.forName("java.lang.String"), renderer);
            } catch (ClassNotFoundException ex) {
                System.exit(0);
            }

            return trustValue;
        }
    }

    private String computeTrustValueMethod1(ArrayList<Integer> freqList, String rDomainFilteredClasses[]) {
        int freqHighest = freqList.get(0);
        int freqHighestIndex = 0;
        for (int i = 0; i < freqList.size(); i++) {
            if (freqHighest < freqList.get(i)) {
                freqHighest = freqList.get(i);
                freqHighestIndex = i;
            }
        }
        return rDomainFilteredClasses[freqHighestIndex] + "-" + freqHighestIndex;
    }

    private double computeTrustValueMethod2(String[] srDomainClasses, String[] rDomainFilteredClasses) {
        DecimalFormat dformat = new DecimalFormat("##.##");
        double alpha = rDomainFilteredClasses.length + sumArray(rDomainFilteredClasses) + 1;
        double beta = srDomainClasses.length + sumArray(srDomainClasses) + 1;
        double trustValue = alpha / (alpha + beta);
        System.out.println("TRUST VALUE:" + trustValue);
        return Double.parseDouble(dformat.format(trustValue));
    }

    private double computeTrustValueMethod3(String[] srDomainClasses, String[] rDomainFilteredClasses) {
        DecimalFormat dformat = new DecimalFormat("##.##");
        ArrayList<Double> trustedList = new ArrayList();
        ArrayList<Double> nonTrustedList = new ArrayList();
        double trustValue = 0.0;
        double alpha = 0.0;
        double beta = 0.0;

        for (String str : rDomainFilteredClasses) {
            double num = Double.parseDouble(str);
            if (num <= 0.5) {
                nonTrustedList.add(num);
            } else {
                trustedList.add(num);
            }
        }

        if (trustedList.size() > nonTrustedList.size()) {
            // alpha = trustedList.size() + sumArray((String[]) trustedList.toArray()) + 1;
            // beta = nonTrustedList.size() + sumArray((String[]) nonTrustedList.toArray()) + 1;
        } else if (trustedList.size() < nonTrustedList.size()) {
            alpha = nonTrustedList.size() + sumArray(nonTrustedList.toArray()) + 1;
            beta = trustedList.size() + sumArray(trustedList.toArray()) + 1;
            System.out.println("NT ALPHA: " + alpha + " BETA: " + beta);
        }

        trustValue = alpha / (alpha + beta);
        System.out.println("TESTING TRUST VALUE: " + trustValue);
        return trustValue;
    }

    private double sumArray(Object[] strArray) {
        double sum = 0.0;
        for (Object value : strArray) {
            sum += (Double) value;
        }
        return sum;
    }

    public boolean isMoModeSelected() {
        return moModeSelected;
    }

    public void setMoModeSelected(boolean moModeSelected) {
        this.moModeSelected = moModeSelected;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel freqDistPanel;
    private javax.swing.JScrollPane freqDistScrollPane;
    private javax.swing.JTable freqDistributionTable;
    private javax.swing.JLabel medianLabel;
    private javax.swing.JTable resultTable;
    private javax.swing.JPanel resultTablePanel;
    private javax.swing.JScrollPane resultTableScrollpane;
    private javax.swing.JTable smoothingFactorTable;
    private javax.swing.JPanel smoothingFactorTablePanel;
    private javax.swing.JScrollPane smoothingFactorsScrollPane;
    private javax.swing.JPanel sortedDFsPanel;
    private javax.swing.JScrollPane sortedDFsScrollPane;
    private javax.swing.JTable sortedDFsTable;
    private javax.swing.JPanel stepOneTab;
    private javax.swing.JPanel stepThreeTab;
    private gui.graphs.SFGraphPane sfGraphPaneTab;
    private gui.graphs.MOGraphPane moGraphPaneTab;
    private javax.swing.JPanel stepTwoTab;
    private javax.swing.JLabel tabOneMessageLabel;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

}
