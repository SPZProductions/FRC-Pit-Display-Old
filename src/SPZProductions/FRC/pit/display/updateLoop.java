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
    public boolean runLoop = true;
    public int waitTime = 30;
    private boolean successful = false;
    
    
    public updateLoop(){
        runLoop();
    }
    
    public void runLoop(){
        System.out.println("Update Loop Starting.....");
        while(true){
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(waitTime));
            } catch (InterruptedException ex) {
                Logger.getLogger(updateLoop.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(runLoop){
                System.out.println("Updating...");
                mainDisp.update();
                successful = true;
            }else{
                System.out.println("Updating Disabled");
            }
            if(successful){
                System.out.println("Update Successfull. Next Update in " + waitTime + " seconds.");
                successful = false;
            }else{
                System.out.println("Update failed.  Either it is disabled, or there was an error. Check console for details.  Next Attempt in " + waitTime + " seconds.");
            }
            
        }
    }
}