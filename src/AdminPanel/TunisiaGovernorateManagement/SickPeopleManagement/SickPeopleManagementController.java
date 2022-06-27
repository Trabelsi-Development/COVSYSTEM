/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.TunisiaGovernorateManagement.SickPeopleManagement;

import animatefx.animation.FadeIn;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SickPeopleManagementController implements Initializable {

 public static int nbr44;   
  private double xOffset=0, yOffset=0;
   

    @FXML
    public TableView<PersonRegistration> myTableViewPeople;
        
    @FXML
    public TableColumn<PersonRegistration, String> ageCol;
    @FXML
    public TableColumn<PersonRegistration, String> editCol;
    @FXML
    public TableColumn<PersonRegistration, String> cinCol;
    @FXML
    public TableColumn<PersonRegistration, String> statusCol;
    @FXML
    public TableColumn<PersonRegistration, String> cityCol;
    @FXML
    public TableColumn<PersonRegistration, String> date_updateCol;
    @FXML
    public TableColumn<PersonRegistration, String> date_addCol;   
    @FXML
    public TableColumn<PersonRegistration, String> last_nameCol;
    @FXML
    public TableColumn<PersonRegistration, String> genderCol;
    @FXML
    public TableColumn<PersonRegistration, String> foreign_caseCol;
    @FXML
    public TableColumn<PersonRegistration, String> first_nameCol;
    @FXML
    private ImageView back;

       
    @FXML
    void backMouseClicked(MouseEvent event) {
    AdminPanel.TunisiaGovernorateManagement.TunisiaGovernorateManagementController.nbr42=0;
    Stage stage = (Stage) back.getScene().getWindow();
         stage.close();
    }

    @FXML
    void backMouseEntered(MouseEvent event) {
        back.setLayoutY(370);
    }

    @FXML
    void backMouseExited(MouseEvent event) {
        back.setLayoutY(380);
    }
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
}
