/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author SHRI
 */
public class TimerUtil {
    
    public static void main(String...args){
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               System.out.println("Timer says: "+new Date());
            }
        }, 0, 5000);
    }
    
}
