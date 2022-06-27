/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.HospitalManagement;

import static AdminPanel.OtherCountriesManagement.AddCountryController.verifLong;
import static AdminPanel.OtherCountriesManagement.AddCountryController.verifLongTotalCases;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class-
 *
 * @author hp
 */
public class UpdateHospitalGeneralController implements Initializable {
    
    public String UPDatee=""; private int rss;
    private double xOffset=0, yOffset=0;
    public static String supply="",equip="",donation="";
    
    @FXML
    private Label rooms_number;
    @FXML
    private Label available_beds;
    @FXML
    public TextField roomsNumber;
    @FXML
    private Label update_title;
    @FXML
    private ImageView back;
    @FXML
    private Label ErrTotalTests;
    @FXML
    public TextField cityName;
    @FXML
    private Label city_name;
    @FXML
    private Label ErrCityName;
    @FXML
    public TextField doctorsNumber;
    @FXML
    private Label ErrRoomsNumber;
    @FXML
    private Button nextButton;
    @FXML
    private Label hospitalname;
    @FXML
    private Label hospitalized_cases;
    @FXML
    public TextField availableBeds;
    @FXML
    public TextField hospitalizedCases;
    @FXML
    private ImageView rightSide;
    @FXML
    public TextField hospitalName;
    @FXML
    private Label ErrDoctorsNumber;
    @FXML
    public TextField totalTests;
    @FXML
    private Label ErrAvailableBeds;
    @FXML
    private Label general_informations;
    @FXML
    private Label ErrHospitalizedCases;
    @FXML
    private Label total_tests;
    @FXML
    private Label doctors_number;

    
    @FXML
    void saveNextButton_MouseClicked(MouseEvent event) throws IOException {
        int i=0;//ce compteur va nous renseigner après si les champs sont tous remplis correctement ou pas
                      
            if (!verifLongTotalCases(hospitalizedCases.getText()))
        {i=1;
        ErrHospitalizedCases.setText("Please enter a correct number of\nthe hospitalized cases !");
        hospitalizedCases.setText("");
        hospitalizedCases.setStyle("-fx-text-inner-color: rgb(242,124,124) ;");     
        }
        else {ErrHospitalizedCases.setText(""); 
        hospitalizedCases.setStyle("-fx-text-inner-color: black ;");     
       }
       
            if (!verifLong(availableBeds.getText()))
        {i=1;
        ErrAvailableBeds.setText("Please enter a correct number of\nthe available beds !");
        availableBeds.setText("");
        availableBeds.setStyle("-fx-text-inner-color: rgb(242,124,124) ;");     
        }
        else {ErrAvailableBeds.setText(""); 
        availableBeds.setStyle("-fx-text-inner-color: black ;");     
       }
            
       if (!verifLong(doctorsNumber.getText()))
        {i=1;
        ErrDoctorsNumber.setText("Please enter a correct number of doctors !");
        doctorsNumber.setText("");
        doctorsNumber.setStyle("-fx-text-inner-color: rgb(242,124,124) ;");     
        }
        else {ErrDoctorsNumber.setText(""); 
        doctorsNumber.setStyle("-fx-text-inner-color: black ;");     
       }
       
       if (!verifLong(roomsNumber.getText()))
        {i=1;
        ErrRoomsNumber.setText("Please enter a correct number of rooms !");
        roomsNumber.setText("");
        roomsNumber.setStyle("-fx-text-inner-color: rgb(242,124,124) ;");     
        }
        else {ErrRoomsNumber.setText(""); 
        roomsNumber.setStyle("-fx-text-inner-color: black ;");     
       }

        
        if (!verifLong(totalTests.getText()))
        {i=1;
        ErrTotalTests.setText("Please enter a correct number of\nthe total tests !");
        totalTests.setText("");
        totalTests.setStyle("-fx-text-inner-color: rgb(242,124,124) ;");     
        }
        else {ErrTotalTests.setText(""); 
        totalTests.setStyle("-fx-text-inner-color: black ;");     
       }
        
        if (i==0)//tous les champs sont remplis correctement
         {
        FXMLLoader f1=new FXMLLoader(getClass().getResource("UpdateHospitalDetails.fxml"));
        Parent root = f1.load();
        UpdateHospitalDetailsController controlleru=f1.getController(); 
        
        controlleru.hospitalName.setText(hospitalName.getText());
        controlleru.cityName.setText(cityName.getText());
        if (hospitalizedCases.getText()!=null)
            controlleru.hcasesu=hospitalizedCases.getText();
        if (availableBeds.getText()!=null)
            controlleru.bavailableu=availableBeds.getText();
        if (doctorsNumber.getText()!=null)
            controlleru.doctorsnu=doctorsNumber.getText();
        if (roomsNumber.getText()!=null)
            controlleru.roomsnu=roomsNumber.getText();
        if (totalTests.getText()!=null)
            controlleru.totaltestsu=totalTests.getText();
        controlleru.UPDate=UPDatee;
        controlleru.supply_NeedsArea.setText(supply);
        controlleru.Equipment_NeedsArea.setText(equip);
        controlleru.Donation_NeedsArea.setText(donation);
        Stage pop_up=new Stage();
        Scene scene = new Scene(root,Color.TRANSPARENT);
        pop_up.initStyle(StageStyle.TRANSPARENT);
        //to make the window draggable (you can move it)
        root.setOnMousePressed(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {xOffset = event.getSceneX();
            yOffset = event.getSceneY();
                
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event)
           {pop_up.setX(event.getScreenX() - xOffset);
           pop_up.setY(event.getScreenY() - yOffset);
                         
           }
        });
        pop_up.setScene(scene);               
        pop_up.centerOnScreen();
        Stage thisStage = (Stage) back.getScene().getWindow();
        thisStage.hide();//hide the current stage
        pop_up.show();
        pop_up.setOnHidden(e -> {  
            if (UpdateHospitalDetailsController.successUpdate)
            {thisStage.show();
            thisStage.hide();  //pour pouvoir détecter de nouveau l'évenement onhidden et par la suite on peut rafraîchir le tableView          
            HospitalManagementGeneralController.nbr5=0;}
            else thisStage.show();
            });
            
            
        new FadeIn(root).play();//animation
        
            
        
        }
    }

    @FXML
    void saveNextButton_MouseEntered(MouseEvent event) {
          nextButton.setLayoutY(538);
    }

    @FXML
    void saveNextButton_MouseExited(MouseEvent event) {
          nextButton.setLayoutY(548);
    }

    @FXML
    void backMouseClicked(MouseEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
         stage.close();
    }

    @FXML
    void backMouseEntered(MouseEvent event) {
        back.setLayoutY(536);
    }

    @FXML
    void backMouseExited(MouseEvent event) {
        back.setLayoutY(546);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
