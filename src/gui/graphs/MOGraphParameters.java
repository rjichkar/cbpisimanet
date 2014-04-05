/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.graphs;

import java.util.TreeSet;

/**
 *
 * @author SHRI
 */
public class MOGraphParameters {

    private double recommendedTrustActual;
    private double recommendedTrustWOD;
    private double recommendedTrustWD;
    private int percentDishonestRA;

    public MOGraphParameters() {

    }

    public double getRecommendedTrustActual() {
        return recommendedTrustActual;
    }

    public void setRecommendedTrustActual(double recommendedTrustActual) {
        this.recommendedTrustActual = recommendedTrustActual;
    }

    public double getRecommendedTrustWOD() {
        return recommendedTrustWOD;
    }

    public void setRecommendedTrustWOD(double recommendedTrustWOD) {
        this.recommendedTrustWOD = recommendedTrustWOD;
    }

    public double getRecommendedTrustWD() {
        return recommendedTrustWD;
    }

    public void setRecommendedTrustWD(double recommendedTrustWD) {
        this.recommendedTrustWD = recommendedTrustWD;
    }

    public int getPercentDishonestRA() {
        return percentDishonestRA;
    }

    public void setPercentDishonestRA(int percentDishonestRA) {
        this.percentDishonestRA = percentDishonestRA;
    }
    
 
}
