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
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddHospitalGeneralController implements Initializable {
private double xOffset=0, yOffset=0;
    
    
    @FXML
    private Label rooms_number;

    @FXML
    private Label available_beds;

    @FXML
    private TextField roomsNumber;

    @FXML
    private ImageView back;

    @FXML
    private TextField totalTests;

    @FXML
    private Label ErrTotalTests;

    @FXML
    private Label city_name;

    @FXML
    public ComboBox<String> CityName_cmb;

    @FXML
    private Label ErrCityName;

    @FXML
    private TextField doctorsNumber;

    @FXML
    private Label ErrRoomsNumber;

    @FXML
    private Label hospitalname;

    @FXML
    private Label hospitalExistance;

    @FXML
    private Label ErrHospitalName;

    @FXML
    private Label hospitalized_cases;

    @FXML
    private ImageView hospitalExistance_png;

    @FXML
    private TextField availableBeds;

    @FXML
    private TextField hospitalizedCases;

    @FXML
    private ImageView rightSide;

    @FXML
    private TextField hospitalName;

    @FXML
    private Label ErrDoctorsNumber;

    @FXML
    private Button nextButton;

    @FXML
    private Label ErrAvailableBeds;

    @FXML
    private Label general_informations;

    @FXML
    private Label ErrHospitalizedCases;

    @FXML
    private Label add_title;

    @FXML
    private Label total_tests;

    @FXML
    private Label doctors_number;

    @FXML
    private void hospital_KeyReleased(KeyEvent evt) {//évenement qui nous renseigne si l'hôpital saisi existe déjà dans la BD ou pas
        if (!hospitalName.getText().equals(""))
        {
     try{
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        String data="Select * from HOSPITALS where HOSPITALNAME="+"'"+hospitalName.getText()+"'";        
        ResultSet rs; 
        rs=conn.getStmt().executeQuery(data);
        if (rs.next()) //si l'hôpital saisi existe déjà 
        {
            hospitalExistance.setText("ALREADY EXISTS");
            Image image1 =new Image (getClass().getResourceAsStream("/images/cross-48.png"));
            hospitalExistance_png.setImage(image1);
        }
        else //Sinon
        {
             hospitalExistance.setText("DOESN'T EXIST YET");            
            Image image2 =new Image (getClass().getResourceAsStream("/images/correct-48.png"));
            hospitalExistance_png.setImage(image2);
           
        }
        conn.getStmt().close();
        conn.getConDB().close();
             }
     
     catch (Exception e)
     {
         Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
     }
    }
        else {hospitalExistance.setText("");
         hospitalExistance_png.setImage(null);}
    }
    
    @FXML
    void saveNextButton_MouseClicked(MouseEvent event) throws IOException {
        int i=0;//ce compteur va nous renseigner après si les champs sont tous remplis correctement ou pas
       
         if (!Register_SignInForms.RegisterFormController.verifAlpha(hospitalName.getText())||hospitalExistance.getText().equals("ALREADY EXISTS"))
        {i=1;
        ErrHospitalName.setText("Please enter a correct non-existent hospital name !");
        hospitalName.setText("");
        hospitalName.setStyle("-fx-text-inner-color: rgb(242,124,124) ;");     
        }
        else {ErrHospitalName.setText(""); 
        hospitalName.setStyle("-fx-text-inner-color: black ;");     
       }
         
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
        if (CityName_cmb.getValue()==null)
        {ErrCityName.setText("Please select a city name !");
        i=1;}
        else 
        {ErrCityName.setText("");
        }
        
        if (i==0)//tous les champs sont remplis correctement
         { FXMLLoader f1=new FXMLLoader(getClass().getResource("AddHospitalDetails.fxml"));
        Parent root = f1.load();
        AddHospitalDetailsController controllera=f1.getController();
        controllera.hospitalName.setText(hospitalName.getText());        
        controllera.cityName.setText(CityName_cmb.getSelectionModel().getSelectedItem());       
        controllera.hcases=hospitalizedCases.getText();
        controllera.bavailable=availableBeds.getText();
        controllera.doctorsn=doctorsNumber.getText();
        controllera.roomsn=roomsNumber.getText();
        controllera.totaltests=totalTests.getText();
        controllera.supplyNeedsArea.setText(AddHospitalDetailsController.supp);
        controllera.EquipmentNeedsArea.setText(AddHospitalDetailsController.equip);
        controllera.DonationNeedsArea.setText(AddHospitalDetailsController.donation);
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
            if (AddHospitalDetailsController.success)
            {thisStage.show();
            thisStage.hide();            
            HospitalManagementGeneralController.nbrrrrr=0;}
            else thisStage.show();
            });
            
            
        new FadeIn(root).play();//animation
        
            
        }
    }

    @FXML
    void saveNextButton_MouseEntered(MouseEvent event) {
          nextButton.setLayoutY(562);
    }

    @FXML
    void saveNextButton_MouseExited(MouseEvent event) {
          nextButton.setLayoutY(572);
    }

    @FXML
    void backMouseClicked(MouseEvent event) {
        HospitalManagementGeneralController.nbrrrrr=0;        
        Stage stage = (Stage) back.getScene().getWindow();
         stage.close();
    }

    @FXML
    void backMouseEntered(MouseEvent event) {
        back.setLayoutY(560);
    }

    @FXML
    void backMouseExited(MouseEvent event) {
        back.setLayoutY(570);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
