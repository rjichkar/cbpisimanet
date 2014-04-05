/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apis;

import java.awt.Color;

/**
 *
 * @author SHRI
 */
public interface IConfiguration {
    
    int CH_RANGE=520;

    int BAD_MOUTHING = 0;
    int BALLOT_STUFFING = 1;
    int RANDOM_OPINION = 2;
    int OFFSET_X = 100;
    int OFFSET_Y = -30;
    Color chColor = new Color(0, 128, 0);
    Color memberColor = new Color(0, 102, 204);
    Color lowPowerNodeColor = new Color(255, 102, 102);
    Color externalNodeColor = new Color(96, 96, 96);
    Color trustedNodeColor = new Color(0, 204, 0);
    Color nonTrustedNodeColor = new Color(255, 51, 102);
    Color dishonestNodeColor = new Color(255, 153, 51);
    
    int SERVICE_PROVIDERS_COUNT=4;

    int DELAY_MAIN_TIMER = 80;
    int DELAY_SUPP_TIMER = 60;

    int LAPTOP_NODE = 0;
    int SMART_PHONE_NODE = 1;
    int WIFI_HOTSPOT = 2;
    int WIFI_HOTSPOT_GREEN = 3;
    int WIFI_HOTSPOT_RED=4;
    int DISHONEST_LAPTOP_NODE=5;
    int DEFAULT_NODE = 6;
    
    String OUTPUT_FILE_PATH="C:\\Users\\Admin\\Desktop\\Events.ts";

    //Define Node Mappings
    String nodeMappings[] = {"Cluster Head", "Member Node", "Unknown/External Node", "Trusted Node", "Blacklisted Node", "Dishonest Node", "Low Power Node"};
    Color nodeMapColors[] = {chColor, memberColor, externalNodeColor, trustedNodeColor, nonTrustedNodeColor, dishonestNodeColor, lowPowerNodeColor};
}
