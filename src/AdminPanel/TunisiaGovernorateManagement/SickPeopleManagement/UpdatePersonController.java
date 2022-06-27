/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.TunisiaGovernorateManagement.SickPeopleManagement;

import AdminPanel.TunisiaGovernorateManagement.AddCityController;
import AdminPanel.TunisiaGovernorateManagement.TunisiaGovernorateManagementController;
import static Register_SignInForms.RegisterFormController.verifAlpha;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class UpdatePersonController implements Initializable {
    
    private final ToggleGroup group1 = new ToggleGroup();//on doit ajouter les boutons radio à ce groupe pour qu'on ne peut sélectionner qu'un seul bouton
    private final ToggleGroup group2 = new ToggleGroup();//on doit ajouter les boutons radio à ce groupe pour qu'on ne peut sélectionner qu'un seul bouton
    
    @FXML
    private final ObservableList <String> status = FXCollections.observableArrayList("ACTIVE","DEAD","RECOVERED","SERIOUS");
    public String ADDDate="";
    
   @FXML
    public TextField lastName;
    @FXML
    public RadioButton no;
    @FXML
    private Label ErrAge;
    @FXML
    public TextField cin;
    @FXML
    public RadioButton Male;
    @FXML
    private Label ErrLastName;
    @FXML
    private Label ErrGender;
    @FXML
    private Label ErrForeignCase;
    @FXML
    public TextField cityName;
    @FXML
    private Button saveUpdate;
    @FXML
    public RadioButton Female;
    @FXML
    private Label ErrFirstName;
    @FXML
    private Label first_name;
    @FXML
    private Label status_corona;
    @FXML
    public RadioButton yes;
    @FXML
    private Label cityname;
    @FXML
    private Label last_name;
    @FXML
    private Label ErrStatut;
    @FXML
    private ImageView rightSide;
    @FXML
    private Label agee;
    @FXML
    public TextField firstName;
    @FXML
    private Label new_deaths;
    @FXML
    private Label add_title;
    @FXML
    private Label foreign_case;
    @FXML
    public ComboBox<String> statusCmb;
    @FXML
    public TextField age;
    @FXML
    private ImageView back;

    
     @FXML
    void saveUpdateButton_MouseClicked(MouseEvent event) {
       int i=0;//ce compteur va nous renseigner après si les champs sont tous remplis correctement ou pas
        
                 
          if (!verifAlpha(firstName.getText()))
        {i=1;
        ErrFirstName.setText("Please enter a correct first name !");
        firstName.setText("");
        firstName.setStyle("-fx-text-inner-color: rgb(203,153,201) ;");     
        }
        else {ErrFirstName.setText(""); 
        firstName.setStyle("-fx-text-inner-color: black ;");     
       }
           if (!verifAlpha(lastName.getText()))
        {i=1;
        ErrLastName.setText("Please enter a correct last name !");
        lastName.setText("");
        lastName.setStyle("-fx-text-inner-color: rgb(203,153,201) ;");     
        }
        else {ErrLastName.setText(""); 
        lastName.setStyle("-fx-text-inner-color: black ;");     
       }
            
           
           if (!AddPersonController.verifAge(age.getText()))
        {i=1;
        ErrAge.setText("Please enter a valid age !");
        age.setText("");
        age.setStyle("-fx-text-inner-color: rgb(203,153,201) ;");     
        }
        else {ErrAge.setText(""); 
        age.setStyle("-fx-text-inner-color: black ;");     
       }
         
        
        if (statusCmb.getValue()==null)
        {ErrStatut.setText("Please select a status !");
        i=1;}
        else 
        {ErrStatut.setText("");
        }
        
         
          if (i==0)//tous les champs sont remplis correctement
         {PersonRegistration regP=new PersonRegistration(); 
         
        regP.setCin(cin.getText());        
        regP.setFirst_name(firstName.getText());
        regP.setLast_name(lastName.getText());
        
        if (Male.isSelected())
        regP.setGender("Male");
        else regP.setGender("Female");
        
        regP.setAge(age.getText());
        regP.setStatusCorona(statusCmb.getValue());
        
        char f;
        if (yes.isSelected())
            f='Y';
        else f='N';
        
        regP.setCity(cityName.getText());
        
         Date d=new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String strDate = dateFormat.format(d);  
        regP.setUpdateDate(strDate);  
        regP.setAddDate(ADDDate);
        
             try{//insertion d'une nouvelle personne dans la BD puis rafraîchissement de la tableview 
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        String row="UPDATE PERSON SET FIRST_NAME='"+regP.getFirst_name()
                +"',LAST_NAME='"+regP.getLast_name()
                +"',GENDER='"+regP.getGender()
                + "',AGE="+regP.getAge()
                + ",STATUSCORONA='"+regP.getStatusCorona()
                + "',FOREIGNCASE='"+f
                + "',DATEUPDATE=TO_DATE('"+regP.getUpdateDate()
                + "') WHERE CIN='"+regP.getCin()+"' and CITY='"+regP.getCity()+"' and DATEADD=TO_DATE('"+regP.getAddDate()+"')";
                    
        int rss=conn.getStmt().executeUpdate(row); 
        
        if (rss==0)
               {Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText("The update has failed !");
               alert.showAndWait();
               }else
               { Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
               alert.setTitle("CONFIRMATION DIALOG");
               alert.setContentText("Congratulations !\nThe update has succeeded !");
               alert.showAndWait(); 
               AdminPanel.TunisiaGovernorateManagement.TunisiaGovernorateManagementController.nbr45=0;      
                Stage stage = (Stage) saveUpdate.getScene().getWindow();
               stage.close();
             }
        conn.getStmt().close();
        conn.getConDB().close();
             }
             catch (Exception e){
           Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
       }
        }
       
    }
    
    @FXML
    void saveUpdateButton_MouseEntered(MouseEvent event) {
        saveUpdate.setLayoutY(524);
    }

    @FXML
    void saveUpdateButton_MouseExited(MouseEvent event) {
        saveUpdate.setLayoutY(534);
    }
    
    @FXML
    void backMouseClicked(MouseEvent event) {
        TunisiaGovernorateManagementController.nbr45=0;
    Stage stage = (Stage) back.getScene().getWindow();
         stage.close();
    }

    @FXML
    void backMouseEntered(MouseEvent event) {
        back.setLayoutY(520);
    }

    @FXML
    void backMouseExited(MouseEvent event) {
        back.setLayoutY(530);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Male.setToggleGroup(group1);
        Female.setToggleGroup(group1);
        yes.setToggleGroup(group2);
        no.setToggleGroup(group2);
        statusCmb.setItems(status);
    }    
    
}
