/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.text.DecimalFormat;

/**
 *
 * @author SHRI
 */
public class MeanOffsetRccGenerator_v1 {

    public static void main(String... args) {
        int inputNodeCount = 20;
        int externalNodeCount = 1;
        int attackType = 1;
        int percentDishonestRA = 20;
        double inputMO = 0.2;
        boolean moMode=true;
        int rcc[][] = new MeanOffsetRccGenerator_v1().generateAttackRecommendations(inputNodeCount, externalNodeCount, attackType, percentDishonestRA, moMode, inputMO);
        for (int i = 0; i < externalNodeCount; i++) {
            System.out.println("\nRCC for EXT NODE "+i+":\n");
            for (int j = 0; j < inputNodeCount; j++) {
                System.out.println("RCC VALUE " + j + ": " + rcc[i][j] / 10.0);
            }
            System.out.println();
        }
    }

    public int[][] generateAttackRecommendations(int inputNodeCount, int externalNodeCount, int attackType, int percentDishonestRA, boolean moMode, double inputMO) {
        int recommendations[][] = new int[externalNodeCount][inputNodeCount];
        int dishonestRACount = (int) (inputNodeCount * (percentDishonestRA / 100.0));
        double meanOffset = 0;
        for (int i = 0; i < externalNodeCount; i++) {
            do {
                meanOffset = 0;
                double sumDishonestRA = 0;
                double sumHonestRA = 0;

                //Add Honest Rcc
                for (int j = 0; j < inputNodeCount; j++) {
                    int rcValue = 0;
                    switch (attackType) {
                        case 0://BM
                            System.out.print("BM 1");
                            while (rcValue < 5) {
                                rcValue = (int) (Math.random() * 11);
                            }
                            break;

                        case 1://BS 
                            //  System.out.print("BS 1");
                            rcValue = 5;
                            while (rcValue >= 5) {
                                rcValue = (int) (Math.random() * 11);
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

                System.out.println("Dishonest RA Count:" + dishonestRACount);

                //Get Trust Value for above recommendations
                int attackTrustValue = 0;//(new RecommendationAnalyzer_v2()).computeAlgorithmResults(recommendations);

                //Add Dishonest Recommendations
                //  for (int i = 0; i < externalNodeCount; i++) {
                for (int j = 0; j < dishonestRACount; j++) {

                    int rcValue = 0;
                    switch (attackType) {
                        case 0://BM
                            System.out.print("BM 2");
                            while (rcValue == 0) {
                                rcValue = (int) (Math.random() * 5);
                            }

                            break;
                        case 1://BS
                            // System.out.print("BS 2");
                            while (rcValue < 5) {
                                rcValue = (int) (Math.random() * 11);
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
                //   }
            } while (moMode && (inputMO != meanOffset));

        }
        return recommendations;
    }
}
