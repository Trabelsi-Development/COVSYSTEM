/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class COVSYSTEMApplicationController implements Initializable {

    @FXML
    private Label progress;
    
     @FXML
    private ProgressBar progressBar;
    
    public static Label loading;
    
    public static ProgressBar myProgressBar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loading=progress;
        myProgressBar=progressBar;
    }    
    
}
