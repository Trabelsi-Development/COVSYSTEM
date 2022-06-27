/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.TunisiaGovernorateManagement.SickPeopleManagement;

import AdminPanel.TunisiaGovernorateManagement.AddCityController;
import static Register_SignInForms.RegisterFormController.verifAlpha;
import animatefx.animation.FadeIn;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddPersonController implements Initializable {
    private String cityy="";
    private final ToggleGroup group1 = new ToggleGroup();//on doit ajouter les boutons radio à ce groupe pour qu'on ne peut sélectionner qu'un seul bouton
    private final ToggleGroup group2 = new ToggleGroup();//on doit ajouter les boutons radio à ce groupe pour qu'on ne peut sélectionner qu'un seul bouton
 
    private double xOffsett=0, yOffsett=0;
    
    @FXML
    private final ObservableList <String> status = FXCollections.observableArrayList("ACTIVE","DEAD","RECOVERED","SERIOUS");
    
    @FXML
    public Label number;
    @FXML
    private TextField lastName;
    @FXML
    private RadioButton no;
    @FXML
    private Label ErrAge;
    @FXML
    private Button saveAdd;
    @FXML
    private RadioButton Male;
    @FXML
    private Label ErrLastName;
    @FXML
    private Label ErrGender;
    @FXML
    private Label ErrForeignCase;
    @FXML
    public TextField cityName;
    @FXML
    private RadioButton Female;
    @FXML
    private Label ErrFirstName;
    @FXML
    private Label first_name;
    @FXML
    private Label status_corona;
    @FXML
    private RadioButton yes;
    @FXML
    private Label cityname;
    @FXML
    private Label last_name;
    @FXML
    private Label ErrStatut;
    @FXML
    private ImageView rightSide;
    @FXML
    private ImageView cinExistance_png;
    @FXML
    private Label cinExistance;
    @FXML
    private Label agee;
    @FXML
    private TextField firstName;
    @FXML
    private TextField cin;
    @FXML
    private Label new_deaths;
    @FXML
    private Label add_title;
    @FXML
    private Label ErrCIN;
    @FXML
    private Label foreign_case;
    @FXML
    private ComboBox<String> statusCmb;
    @FXML
    private TextField age;

   
    public void reloadAddPerson() throws IOException//loading the data from the database into the tableview
    {        
        FXMLLoader ff = new FXMLLoader(getClass().getResource("AddPerson.fxml"));
        Parent root2=ff.load();
        Stage pop_up2=new Stage();
        Scene scene2 = new Scene(root2,Color.TRANSPARENT);
        pop_up2.initStyle(StageStyle.TRANSPARENT);
        AdminPanel.TunisiaGovernorateManagement.SickPeopleManagement.AddPersonController controllerr2=ff.getController();
        controllerr2.cityName.setText(AddCityController.city);
       controllerr2.number.setText("N° "+AddCityController.i);
        //to make the window draggable (you can move it)
        root2.setOnMousePressed(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {xOffsett = event.getSceneX();
            yOffsett = event.getSceneY();
                
            }
        });
        root2.setOnMouseDragged(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event)
           {pop_up2.setX(event.getScreenX() - xOffsett);
           pop_up2.setY(event.getScreenY() - yOffsett);
                         
           }
        });
        pop_up2.setScene(scene2);               
        pop_up2.centerOnScreen();
        pop_up2.show();
                        
        new FadeIn(root2).play();//animation
     
        
    }
     
    public static boolean verifCIN(String ch)
    {Boolean test=true;
    if (ch.length()!=8)
        test=false;
    else {
        for (int i=0;i<ch.length();i++)
        {if (!(Character.isDigit(ch.charAt(i))))
            {test=false;
            break;}            
        }
    }
        return test;  
    }
    
    public static boolean verifAge (String ch)
    {Boolean test=true; int agee=0;
    if (ch.length()>3 || ch.equals("") || ch.contains(" "))
        test=false;
    else {
        for (int i=0;i<ch.length();i++)
        {if (!(Character.isDigit(ch.charAt(i))))
            {test=false;
            break;}            
        
        if (test)
        {agee=Integer.parseInt(ch);
            if (agee>130)
                test=false;
        }
        }
    }
      return test;  
        
    }
    @FXML
    private void cinExistance_KeyReleased(KeyEvent evt) {//évenement qui nous renseigne si le cin saisi existe déjà dans la BD ou pas
        if (!cin.getText().equals(""))
        {
     try{
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        String data="Select * from PERSON where CIN="+"'"+cin.getText()+"'";        
        ResultSet rs; 
        rs=conn.getStmt().executeQuery(data);
        if (rs.next()) //si le cin saisi existe déjà 
        { cityy=rs.getString("CITY");
            cinExistance.setText("ALREADY EXISTS IN "+cityy.toUpperCase());
            Image image1 =new Image (getClass().getResourceAsStream("/images/cross-48.png"));
            cinExistance_png.setImage(image1);
        }
        else //Sinon
        {
             cinExistance.setText("DOESN'T EXIST YET");            
            Image image2 =new Image (getClass().getResourceAsStream("/images/correct-48.png"));
            cinExistance_png.setImage(image2);
           
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
        else {cinExistance.setText("");
         cinExistance_png.setImage(null);}
    }
    
       
    @FXML
    void saveAddButton_MouseClicked(MouseEvent event) {
        int i=0;//ce compteur va nous renseigner après si les champs sont tous remplis correctement ou pas
        
         if ((!verifCIN(cin.getText()))||cinExistance.getText().contains("ALREADY EXISTS IN"))
        {i=1;
        ErrCIN.setText("Please enter a correct non-existent National Identity Card number!");
        cin.setText("");
        cin.setStyle("-fx-text-inner-color: rgb(203,153,201) ;");     
        }
        else {ErrCIN.setText(""); 
        cin.setStyle("-fx-text-inner-color: black ;");     
       }
         
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
            
           if ((!Male.isSelected()) && (!Female.isSelected()))
        {i=1;
        ErrGender.setText("Please select a gender !");
        }
        else {ErrGender.setText(""); }
           
           if (!verifAge(age.getText()))
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
        
         if ((!yes.isSelected()) && (!no.isSelected()))
        {i=1;
        ErrForeignCase.setText("Please select whether it is a foreign\ncase or not !");
        }
        else {ErrForeignCase.setText(""); }
         
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
        
        regP.setCity(AddCityController.city);
        
         Date d=new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String strDate = dateFormat.format(d);  
        regP.setAddDate(strDate);  
        
        
             try{//insertion d'une nouvelle personne dans la BD puis rafraîchissement de la tableview 
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        String row="INSERT INTO PERSON(CIN,FIRST_NAME,LAST_NAME,GENDER,AGE,CITY,DATEADD,STATUSCORONA,FOREIGNCASE) VALUES ('"+regP.getCin()+"','"+regP.getFirst_name()+"','"+regP.getLast_name()+"','"+regP.getGender()+"',"+regP.getAge()+",'"+regP.getCity()+"','"+regP.getAddDate()+"','"+regP.getStatusCorona()+"','"+f+"')";
                
        int rss=conn.getStmt().executeUpdate(row); 
        
        if (rss==0)
               {Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText("The registration has failed !");
               alert.showAndWait();
               }else
               { Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
               alert.setTitle("CONFIRMATION DIALOG");
               alert.setContentText("Congratulations !\nThe registration has succeeded !");
               alert.showAndWait();                  
               AddCityController.i++;
                if (AddCityController.i<=AddCityController.nbrp)//l'Admin n'a pas encore ajouté toutes les personnes attaquées par Corona 
                    reloadAddPerson();
                else AddCityController.i=1;     
               }        
                Stage stage = (Stage) saveAdd.getScene().getWindow();
               stage.close();
               
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
    void saveAddButton_MouseEntered(MouseEvent event) {
        saveAdd.setLayoutY(562);
    }

    @FXML
    void saveAddButton_MouseExited(MouseEvent event) {
        saveAdd.setLayoutY(572);
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
