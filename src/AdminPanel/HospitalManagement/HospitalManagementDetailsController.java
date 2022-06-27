/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.HospitalManagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class HospitalManagementDetailsController implements Initializable {
    
       
    @FXML
    private ImageView back;
    @FXML
    public TableView<HospitalRegistration> myTableViewDetails;
    @FXML
    public TableColumn<HospitalRegistration,String> supply_needsCol;
    @FXML
    public TableColumn<HospitalRegistration,String> equip_needsCol;
    @FXML
    public TableColumn<HospitalRegistration,String> donation_needsCol;
     
    
    
    @FXML
    void backMouseClicked(MouseEvent event) {
        HospitalManagementGeneralController.nombre=0;
        Stage stage = (Stage) back.getScene().getWindow();
         stage.close();
    }

    @FXML
    void backMouseEntered(MouseEvent event) {
        back.setLayoutY(326);
    }

    @FXML
    void backMouseExited(MouseEvent event) {
        back.setLayoutY(336);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       }    
    
}
