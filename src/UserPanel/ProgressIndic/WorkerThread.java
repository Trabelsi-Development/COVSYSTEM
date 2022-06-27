package UserPanel.ProgressIndic;

import javafx.application.Platform;
import javax.swing.JOptionPane;

/**
 *
 * @author selmi
 */
public class WorkerThread extends Thread{
    int progress=0 ;
    RingProgressIndicator rpi ;
    double end ;
    public WorkerThread(RingProgressIndicator rpi, double end){
        this.rpi=rpi ;
        this.end=end ;
    }
    @Override
    public void run(){
        while (true) {            
            
                try {
            Thread.sleep(100);
            
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
                Platform.runLater(()->{ 
                    rpi.setProgress(progress);
                });
                progress++ ;
                if(progress>end){
                    break ;
                }
        }
}
    
}
