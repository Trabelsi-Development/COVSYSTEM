/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Welcome;

import UserPanel.ProgressIndic.RingProgressIndicator;
import UserPanel.ProgressIndic.WorkerThread;
import Register_SignInForms.ConnexionDB;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author selmi
 */
public class WelcomePageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    Label Cases ;
    @FXML
    Label Recovered ;
    @FXML
    Label Deaths ;
    @FXML
    Label active ;
    @FXML
    HBox Hrecovered  ;
    @FXML
    HBox Hdeaths  ;
    @FXML
    HBox Hactive  ;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select sum(TotalCases) from coronaWorld "
                            + "where to_char(dateupdate,'dd-mm-yyyy') = to_char(sysdate,'dd-mm-yyyy') ";
                ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                
                if(rs.next()){
                   String Tcases =String.valueOf((int) rs.getDouble(1)) ;
                   int n=Tcases.length() ;
                  if(Tcases.length()>6)
                      Tcases=Tcases.substring(0,n-6)+","+Tcases.substring(n-6,n-3)+","+Tcases.substring(n-3,n) ;
                  else
                      Tcases=Tcases.substring(0,n-3)+","+Tcases.substring(n-3,n) ;
                 
                  Cases.setText(Tcases) ;
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        RingProgressActive();
        RingProgressDeath();
        RingProgressRecovered();
    }    
        private void RingProgressDeath(){
    		RingProgressIndicator indicator = new RingProgressIndicator();
		Slider slider = new Slider(0, 100, 50);

		slider.valueProperty().addListener((o, oldVal, newVal) -> indicator.setProgress(newVal.intValue()));
		VBox main = new VBox(1, indicator, slider);
		indicator.setProgress(Double.valueOf(slider.getValue()).intValue());
		Scene scene = new Scene(main);
		 try {
                double end = 0 ;
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select sum(TotalDeaths),sum(TotalCases) from coronaWorld "
                            + "where to_char(dateupdate,'dd-mm-yyyy') = to_char(sysdate,'dd-mm-yyyy') ";
                ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                
                if(rs.next()){
                  end=  (rs.getDouble(1)/rs.getDouble(2))*100 ;
                  String Tcases =String.valueOf((int) rs.getDouble(1)) ;
                  int n=Tcases.length() ;
                  if(Tcases.length()>6)
                      Tcases=Tcases.substring(0,n-6)+","+Tcases.substring(n-6,n-3)+","+Tcases.substring(n-3,n) ;
                  else
                      Tcases=Tcases.substring(0,n-3)+","+Tcases.substring(n-3,n) ;
                 
                  Deaths.setText(Tcases) ;
                }
                new WorkerThread(indicator,end).start() ;
                indicator.setMaxSize(330, 167);
                Hdeaths.getChildren().addAll(indicator) ;
                
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
                
                
                
                
                
}
    
    
    private void RingProgressRecovered(){
    		RingProgressIndicator indicator = new RingProgressIndicator();
		Slider slider = new Slider(0, 100, 50);

		slider.valueProperty().addListener((o, oldVal, newVal) -> indicator.setProgress(newVal.intValue()));
		VBox main = new VBox(1, indicator, slider);
		indicator.setProgress(Double.valueOf(slider.getValue()).intValue());
		Scene scene = new Scene(main);
		 try {
                double end = 0 ;
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select sum(TotalRecovered),sum(TotalCases) from coronaWorld "
                            + "where to_char(dateupdate,'dd-mm-yyyy') = to_char(sysdate,'dd-mm-yyyy') ";
                ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                
                if(rs.next()){
                  end=  (rs.getDouble(1)/rs.getDouble(2))*100 ;
                  String Tcases =String.valueOf((int) rs.getDouble(1)) ;
                  int n=Tcases.length() ;
                  if(Tcases.length()>6)
                      Tcases=Tcases.substring(0,n-6)+","+Tcases.substring(n-6,n-3)+","+Tcases.substring(n-3,n) ;
                  else
                      Tcases=Tcases.substring(0,n-3)+","+Tcases.substring(n-3,n) ;
                 
                  Recovered.setText(Tcases) ;
                }
                new WorkerThread(indicator,end).start() ;
                Hrecovered.getChildren().addAll(indicator) ;
                
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
                             
}     
    
    private void RingProgressActive(){
    		RingProgressIndicator indicator = new RingProgressIndicator();
		Slider slider = new Slider(0, 100, 50);

		slider.valueProperty().addListener((o, oldVal, newVal) -> indicator.setProgress(newVal.intValue()));
		VBox main = new VBox(1, indicator, slider);
		indicator.setProgress(Double.valueOf(slider.getValue()).intValue());
		Scene scene = new Scene(main);
		 try {
                double end = 0 ;
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select sum(activecases),sum(TotalCases) from coronaWorld "
                            + "where to_char(dateupdate,'dd-mm-yyyy') = to_char(sysdate,'dd-mm-yyyy') ";
                ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                
                if(rs.next()){
                  end=  (rs.getDouble(1)/rs.getDouble(2))*100 ;
                   String Tcases =String.valueOf((int) rs.getDouble(1)) ;
                  int n=Tcases.length() ;
                  if(Tcases.length()>6)
                      Tcases=Tcases.substring(0,n-6)+","+Tcases.substring(n-6,n-3)+","+Tcases.substring(n-3,n) ;
                  else
                      Tcases=Tcases.substring(0,n-3)+","+Tcases.substring(n-3,n) ;
                 
                  active.setText(Tcases) ;
                }
                new WorkerThread(indicator,end).start() ;
                Hactive.getChildren().addAll(indicator) ;
                
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
                
                
                
                
                
}
    

}
