/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel;

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
import javafx.scene.Node;
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


public class AdminDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private int nbrclick=0;
     private double xOffset=0, yOffset=0;
    
     
    @FXML
    private HBox userDash_HBox; 
    @FXML
    private HBox Admin_HBox;
    @FXML
    private HBox tunisia_HBox;
    @FXML
    private HBox World_HBox;
    @FXML
    private HBox Hospital_HBox;
     @FXML
    private ImageView close;
    @FXML
    private HBox User_HBox;
    @FXML
    private HBox SignOut_HBox;
    @FXML
    private HBox show_admin;
    @FXML
    private VBox slider_menu;
    @FXML
    private VBox resultSide;
    @FXML
    private ImageView menuBar;
    @FXML
    public ImageView profilePicture;
    @FXML
    private TextField search;
    @FXML
    private Button admin_management;
    @FXML
    private Button tunisia_management;
    
    @FXML
    private Button world_management;
    @FXML
    private Button hospital_management;
    @FXML
    private Button Userr;
     @FXML
    private Button userDash;
    @FXML
    private Button signOut;
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
    private HBox sign_out;
    @FXML
    private Label title_label;
    
    
    
    @FXML
    private void adminManagement_MouseEntered()
    {
     Admin_HBox.setStyle("-fx-background-color: #9d9c9c;");
    }
    
    @FXML
    private void adminManagement_MouseExited()
    {
     Admin_HBox.setStyle("-fx-background-color: TRANSPARENT;");
    }
    
    @FXML
    private void adminManagement_MouseClicked() throws IOException 
    {    
     title_label.setPrefWidth(1600);
     title_label.setPrefHeight(127);
     title_label.setStyle("-fx-background-color: #2b3f63; -fx-text-fill: white;  -fx-font-weight: bold; -fx-font-size:32px;  -fx-font-family:System;");
     title_label.setText("A D M I N I S T R A T O R S");      
     new BounceIn(title_label).play();//animation
           
     HBox show=FXMLLoader.load(getClass().getResource("AdminManagement/AdministratorManagement.fxml"));
     show_admin.getChildren().setAll(show); 
    }
   
    
    @FXML
    private void TunisiaManagement_MouseEntered()
    {
     tunisia_HBox.setStyle("-fx-background-color: #9d9c9c;");
    }
    @FXML
    private void TunisiaManagement_MouseExited()
    {
     tunisia_HBox.setStyle("-fx-background-color: TRANSPARENT;");
    }
    @FXML
    private void TunisiaManagement_MouseClicked() throws IOException
    {
     title_label.setPrefWidth(1600);
     title_label.setPrefHeight(127);
     title_label.setStyle("-fx-background-color: #cb99c9; -fx-text-fill: white;  -fx-font-weight: bold; -fx-font-size:32px;  -fx-font-family:System;");
     title_label.setText("T U N I S I A    G O V E R N O R A T E S");      
     new BounceIn(title_label).play();//animation
           
     HBox show=FXMLLoader.load(getClass().getResource("TunisiaGovernorateManagement/TunisiaGovernorateManagement.fxml"));
     show_admin.getChildren().setAll(show); 
    }
    
    @FXML
    private void WorldManagement_MouseEntered()
    {
     World_HBox.setStyle("-fx-background-color: #9d9c9c;");
    }
    
    @FXML
    private void WorldManagement_MouseClicked() throws IOException
    {title_label.setPrefWidth(1600);
     title_label.setPrefHeight(127);
     title_label.setStyle("-fx-background-color: #FBCD59; -fx-text-fill: white;  -fx-font-weight: bold; -fx-font-size:32px;  -fx-font-family:System;");
     title_label.setText("O T H E R    C O U N T R I E S");      
     new BounceIn(title_label).play();//animation
          
 
     HBox show=FXMLLoader.load(getClass().getResource("OtherCountriesManagement/OtherCountriesManagement.fxml"));
     show_admin.getChildren().setAll(show); 
    
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
     title_label.setStyle("-fx-background-color: #F27C7C; -fx-text-fill: white;  -fx-font-weight: bold; -fx-font-size:32px;  -fx-font-family:System;");
     title_label.setText("H O S P I T A L S");      
     new BounceIn(title_label).play();//animation
           
     HBox show=FXMLLoader.load(getClass().getResource("HospitalManagement/HospitalManagementGeneral.fxml"));
     show_admin.getChildren().setAll(show); 
    }
    @FXML
    private void HospitalManagement_MouseExited()
    {
     Hospital_HBox.setStyle("-fx-background-color: TRANSPARENT;");
    }
    @FXML
    private void UserManagement_MouseEntered()
    {
     User_HBox.setStyle("-fx-background-color: #9d9c9c;");
    }
    @FXML
    private void UserManagement_MouseExited()
    {
     User_HBox.setStyle("-fx-background-color: TRANSPARENT;");
    }
    
    @FXML
    private void UserManagement_MouseClicked() throws IOException
    {
     title_label.setPrefWidth(1600);
     title_label.setPrefHeight(127);
     title_label.setStyle("-fx-background-color: #8ac6d1; -fx-text-fill: white;  -fx-font-weight: bold; -fx-font-size:32px;  -fx-font-family:System;");
     title_label.setText("U S E R S");      
     new BounceIn(title_label).play();//animation
          
     HBox show=FXMLLoader.load(getClass().getResource("UserManagement/User_Management.fxml"));
     show_admin.getChildren().setAll(show); 
    }
    
    @FXML
    private void SIGNOUTManagement_MouseEntered()
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
                Stage stage = (Stage) show_admin.getScene().getWindow();
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
    private void SIGNOUTManagement_MouseExited()
    {
     SignOut_HBox.setStyle("-fx-background-color: TRANSPARENT;");
    }
    
    @FXML
    private void close_MouseClicked()
    {
     Stage stage = (Stage) show_admin.getScene().getWindow();
         stage.close();
    }
    
    @FXML
    private void userDash_MouseEntered()
    {
     userDash_HBox.setStyle("-fx-background-color: #9d9c9c;");
    }
    
        
     @FXML
    private void userDash_MouseClicked() throws IOException
    {Stage stage = (Stage) show_admin.getScene().getWindow();
                    
     Stage stage2 = new Stage();
                   FXMLLoader fuser=new FXMLLoader(getClass().getResource("/UserPanel/UserDashboard.fxml"));
                    Parent root = fuser.load();
                    UserPanel.UserDashboardController controllerUser=fuser.getController();
                    controllerUser.backk.setVisible(true);
                    
                    controllerUser.UpdateUser_HBox.setVisible(false);
                    controllerUser.settings.setVisible(false);
                    controllerUser.SignOut_HBox.setVisible(false);
                    controllerUser.sign_out.setVisible(false);
                    controllerUser.close.setVisible(false);
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
                       {stage2.setX(event.getScreenX() - xOffset);
                       stage2.setY(event.getScreenY() - yOffset);


                       }
                    });

                    stage2.setScene(scene);  
                    stage2.centerOnScreen();
                    stage.hide();
                    
                    stage2.show();
                     stage2.setOnHidden(e -> {// pour revenir au dashboard de l'admin
                        stage.show();
                     });
    }
    @FXML
    private void userDash_MouseExited()
    {
     userDash_HBox.setStyle("-fx-background-color: TRANSPARENT;");
    }
        
    @FXML
    private void menuBar_MouseClicked() throws InterruptedException
    {nbrclick++;
    TranslateTransition translate1 = new TranslateTransition(); 
    TranslateTransition translate2 = new TranslateTransition(); 
    if (nbrclick%2!=0)
    { profilePicture.setVisible(false);
    search.setVisible(false);
    admin_management.setVisible(false);   
    tunisia_management.setVisible(false);
    world_management.setVisible(false);
    hospital_management.setVisible(false);
    User_HBox.setVisible(false);
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
    admin_management.setVisible(true);   
    tunisia_management.setVisible(true);
    world_management.setVisible(true);
    hospital_management.setVisible(true);
    User_HBox.setVisible(true);
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      title_label.setPrefWidth(1600);
     title_label.setPrefHeight(127);
     title_label.setStyle("-fx-background-color: #8b8bff; -fx-text-fill: white;  -fx-font-weight: bold; -fx-font-size:32px;  -fx-font-family:System;");
     title_label.setText("C O V I D-19   C O R O N A V I R U S   P A N D E M I C");      
     new BounceIn(title_label).play();//animation
     
        HBox show;
        try {
            show = FXMLLoader.load(getClass().getResource("/Welcome/WelcomePage.fxml"));
             show_admin.getChildren().setAll(show); 
            } catch (IOException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
    
}
