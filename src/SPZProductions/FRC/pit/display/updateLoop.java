/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPZProductions.FRC.pit.display;

import static SPZProductions.FRC.pit.display.SPZProductionsFRCPitDisplayUI.mainDisp;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SPZ Productions
 */
public class updateLoop {
    public boolean runLoop = false;
    public int waitTime = 30;
    
    
    public updateLoop(){
        runLoop();
    }
    
    public void runLoop(){
        while(true){
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(waitTime));
            } catch (InterruptedException ex) {
                Logger.getLogger(updateLoop.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(runLoop){
                mainDisp.update();
            }
           
        }
    }
}