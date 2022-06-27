/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserPanel;

import animatefx.animation.BounceIn;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hp
 */


public class UserDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private int nbrclick=0;
     private double xOffset=0, yOffset=0;
   
    @FXML
    private HBox tunisia_HBox;
    @FXML
    private HBox World_HBox;
    @FXML
    private HBox Hospital_HBox;
    @FXML
    public HBox User_HBox;
    @FXML
    public HBox SignOut_HBox;
    @FXML
    private HBox StaticWorld_HBox;
    @FXML
    private HBox StatisticTunis_HBox;
     @FXML
    public HBox settings;
    @FXML
    private HBox show_user;
    @FXML
    private VBox slider_menu;
    @FXML
    private VBox resultSide;
    @FXML
    public ImageView backk;
    @FXML
    private ImageView menuBar;
    @FXML
    public ImageView close;
    @FXML
    public ImageView profilePicture;
    @FXML
    private TextField search;
    @FXML
    private Button tunisia_management;
    @FXML
    private Button world_management;
    @FXML
    private Button hospital_management;
    @FXML
    private Button Userr;
    @FXML
    private Button signOut;
    @FXML
    private Button UpdateUser ;
    @FXML
    private Button StatisticsWorld;
    @FXML
    private Button StatisticsTunis;
    @FXML
    private HBox admin;
    @FXML
    private HBox tunisia;
    @FXML
    private HBox world;
    @FXML
    private HBox hospital;
    @FXML
    private HBox userr_management;
    @FXML
    public HBox sign_out;
    @FXML
    private HBox Statistics_World;
    @FXML
    private HBox Statistics_Tunis;
    @FXML
    public HBox UpdateUser_HBox;
    @FXML
    private Label title_label;
    
    @FXML
    private void close_MouseClicked()
    {
      Stage stage = (Stage) close.getScene().getWindow();
         stage.close();
    }
    
    @FXML
    private void TunisiaStatistics1_MouseEntered()
    {
     tunisia_HBox.setStyle("-fx-background-color: #9d9c9c;");
    }
    @FXML
    private void TunisiaStatistics2_MouseEntered()
    {
     StatisticTunis_HBox.setStyle("-fx-background-color: #9d9c9c;");
    }
    @FXML
    private void TunisiaStatistics1_MouseClicked() throws IOException
    {
     title_label.setPrefWidth(1600);
     title_label.setPrefHeight(127);
     title_label.setStyle("-fx-background-color: #2b3f63; -fx-text-fill: white;  -fx-font-weight: bold; -fx-font-size:32px;  -fx-font-family:System;");
     title_label.setText("T U N I S I A    S T A T I S T I C S   1");      
     new BounceIn(title_label).play();//animation         
 
     HBox show=FXMLLoader.load(getClass().getResource("StatisticsTunis/StatisticsT.fxml"));
     show_user.getChildren().setAll(show); 
    
    }
    
    @FXML
    private void TunisiaStatistics2_MouseClicked() throws IOException
    {
     title_label.setPrefWidth(1600);
     title_label.setPrefHeight(127);
     title_label.setStyle("-fx-background-color: #db8484; -fx-text-fill: white;  -fx-font-weight: bold; -fx-font-size:32px;  -fx-font-family:System;");
     title_label.setText("T U N I S I A    S T A T I S T I C S   2");      
     new BounceIn(title_label).play();//animation         
 
     HBox show=FXMLLoader.load(getClass().getResource("StatisticsTunis2/StatisticsT2.fxml"));
     show_user.getChildren().setAll(show); 
    
    }
    
    @FXML
    private void TunisiaStatistics1_MouseExited()
    {
     tunisia_HBox.setStyle("-fx-background-color: TRANSPARENT;");
    }
    
    @FXML
    private void TunisiaStatistics2_MouseExited()
    {
     StatisticTunis_HBox.setStyle("-fx-background-color: TRANSPARENT;");
    }
    
    @FXML
    private void WorldManagement_MouseEntered()
    {
     World_HBox.setStyle("-fx-background-color: #9d9c9c;");
    }
    
    @FXML
    private void WorldManagement_MouseClicked() throws IOException
    {
    title_label.setPrefWidth(1600);
     title_label.setPrefHeight(127);
     title_label.setStyle("-fx-background-color: #db8484; -fx-text-fill: white;  -fx-font-weight: bold; -fx-font-size:32px;  -fx-font-family:System;");
     title_label.setText("T U N I S I A    &    O T H E R    C O U N T R I E S");      
     new BounceIn(title_label).play();//animation
 
     HBox show=FXMLLoader.load(getClass().getResource("UserManagementCW/ConsultWorld.fxml"));
     show_user.getChildren().setAll(show); 
    }
    
    @FXML
    private void WorldManagement_MouseExited()
    {
     World_HBox.setStyle("-fx-background-color: TRANSPARENT;");
    }
    
    @FXML
    private void HospitalManagement_MouseEntered()
    {
     Hospital_HBox.setStyle("-fx-background-color: #9d9c9c;");
    }
    @FXML
    private void HospitalManagement_MouseClicked() throws IOException
    {
    title_label.setPrefWidth(1600);
     title_label.setPrefHeight(127);
     title_label.setStyle("-fx-background-color: #00B0B5; -fx-text-fill: white;  -fx-font-weight: bold; -fx-font-size:32px;  -fx-font-family:System;");
     title_label.setText("H O S P I T A L S");      
     new BounceIn(title_label).play();//animation
 
     HBox show=FXMLLoader.load(getClass().getResource("UserManagementCH/ConsultHospital.fxml"));
     show_user.getChildren().setAll(show); 
    }
    @FXML
    private void HospitalManagement_MouseExited()
    {
     Hospital_HBox.setStyle("-fx-background-color: TRANSPARENT;");
    }       
    
    @FXML
    private void SIGNOUT_MouseEntered()
    {
     SignOut_HBox.setStyle("-fx-background-color: #9d9c9c;");
    }
    
    @FXML
    private void SIGNOUT_MouseClicked() throws IOException
    {
     Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("CONFIRMATION DIALOG");
           alert.setContentText("Do you really want to log out ?");
          Optional <ButtonType> action= alert.showAndWait();
          if (action.get()==ButtonType.OK)
          {
              
                Register_SignInForms.UserRegistration.loginUser=null;
                Stage stage = (Stage) show_user.getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/Register_SignInForms/SIGNINForm.fxml"));

                Scene scene = new Scene(root,Color.TRANSPARENT);
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
                   {stage.setX(event.getScreenX() - xOffset);
                   stage.setY(event.getScreenY() - yOffset);


                   }
                });
                stage.setScene(scene);               
                stage.centerOnScreen();
                stage.show();


          }
    }
    
    @FXML
    private void SIGNOUT_MouseExited()
    {
     SignOut_HBox.setStyle("-fx-background-color: TRANSPARENT;");
    }
    @FXML
    private void StatisticsWorld_MouseClicked() throws IOException 
    {    
     title_label.setPrefWidth(1600);
     title_label.setPrefHeight(127);
     title_label.setStyle("-fx-background-color: #2b3f63; -fx-text-fill: white;  -fx-font-weight: bold; -fx-font-size:32px;  -fx-font-family:System;");
     title_label.setText("W O R L D    S T A T I S T I C S ");      
     new BounceIn(title_label).play();//animation
          
 
     HBox show=FXMLLoader.load(getClass().getResource("StatisticsWorld/StatisticsW.fxml"));
     show_user.getChildren().setAll(show); 
    }
   
    @FXML
    private void StatisticsWorld_MouseEntered()
    {
     StaticWorld_HBox.setStyle("-fx-background-color: #9d9c9c;");
    }
     
    @FXML
    private void StatisticsWorld_MouseExited()
    {
     StaticWorld_HBox.setStyle("-fx-background-color: TRANSPARENT;");
    }
    
    
       
    @FXML
    private void UpdateUser_MouseEntered()
    {
     UpdateUser_HBox.setStyle("-fx-background-color: #9d9c9c;");
    }
    @FXML
    private void UpdateUser_MouseExited()
    {
     UpdateUser_HBox.setStyle("-fx-background-color: TRANSPARENT;");
    }
   
    @FXML
     private void UpdateUser_MouseClicked() throws IOException 
    {    
     title_label.setPrefWidth(1600);
     title_label.setPrefHeight(127);
     title_label.setStyle("-fx-background-color: #2b3f63; -fx-text-fill: white;  -fx-font-weight: bold; -fx-font-size:32px;  -fx-font-family:System;");
     title_label.setText("U P D A T E    A C C O U N T");      
     new BounceIn(title_label).play();//animation
          
 
     HBox show=FXMLLoader.load(getClass().getResource("UpdateUserAccount/UpdateUser.fxml"));
     show_user.getChildren().setAll(show); 
    }
      
    
    @FXML
    private void menuBar_MouseClicked() throws InterruptedException
    {nbrclick++;
    TranslateTransition translate1 = new TranslateTransition(); 
    TranslateTransition translate2 = new TranslateTransition(); 
    if (nbrclick%2!=0)
    { profilePicture.setVisible(false);
    search.setVisible(false);
    StatisticsWorld.setVisible(false);   
    tunisia_management.setVisible(false);
    StatisticsTunis.setVisible(false); 
    world_management.setVisible(false);
    hospital_management.setVisible(false);
    UpdateUser.setVisible(false);   
    signOut.setVisible(false);
    signOut.setVisible(false);
    title_label.setPrefWidth(1470);
    translate1.setNode(menuBar);
    translate1.setDuration(Duration.millis(1000));  
    translate1.setToX(-151);
    translate1.play(); 
    
    translate2.setNode(resultSide);
    translate2.setDuration(Duration.millis(1000)); 
    translate2.setToX(-223);
    translate2.play();
    resultSide.setPrefWidth(1470);
    }
    else 
    {
   profilePicture.setVisible(true);
    search.setVisible(true);
    StatisticsWorld.setVisible(true); 
    StatisticsTunis.setVisible(true);  
    
    tunisia_management.setVisible(true);
    world_management.setVisible(true);
    hospital_management.setVisible(true);
    UpdateUser.setVisible(true);   
    signOut.setVisible(true);
    signOut.setVisible(true);
    translate1.setNode(menuBar);
    translate1.setDuration(Duration.millis(1000));  
    translate1.setToX(70);
    translate1.play(); 
    
    translate2.setNode(resultSide);
    translate2.setDuration(Duration.millis(1000)); 
    translate2.setToX(0);
    translate2.play(); 
    
    }  
        
    }
    @FXML
    private void backk_MouseClicked()
    {
    Stage stage = (Stage) show_user.getScene().getWindow();
         stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //welcome page
     title_label.setPrefWidth(1600);
     title_label.setPrefHeight(127);
     title_label.setStyle("-fx-background-color: #8b8bff; -fx-text-fill: white;  -fx-font-weight: bold; -fx-font-size:32px;  -fx-font-family:System;");
     title_label.setText("C O V I D-19   C O R O N A V I R U S   P A N D E M I C");      
     new BounceIn(title_label).play();//animation
     HBox show;
        try {
            show = FXMLLoader.load(getClass().getResource("/Welcome/WelcomePage.fxml"));
             show_user.getChildren().setAll(show); 
            } catch (IOException ex) {
            Logger.getLogger(UserDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        backk.setVisible(false);
    }    
    
}
