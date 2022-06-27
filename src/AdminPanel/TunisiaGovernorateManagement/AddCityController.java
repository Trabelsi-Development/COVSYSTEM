/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.TunisiaGovernorateManagement;

import AdminPanel.OtherCountriesManagement.AddCountryController;
import animatefx.animation.FadeIn;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddCityController implements Initializable {
    
    public static long i=1,nbrp=0;
    private double xOffsett=0, yOffsett=0;
    public static String city="";
    
    @FXML
    private Label ErrTotalDeaths;
    @FXML
    private Button saveAdd;
    @FXML
    private TextField newCases;
    @FXML
    private ImageView back;
    @FXML
    private TextField totalRecovered;
    @FXML
    private Label ErrTotalRecovered;
    @FXML
    private Label ErrTotalTests;
    @FXML
    private Label ErrNewDeaths;
    @FXML
    private TextField totalDeaths;
    @FXML
    private TextField seriousCritical;
    @FXML
    private Label ErrNewCases;
    @FXML
    private Label ErrCityName;
    @FXML
    private TextField activeCases;
    @FXML 
    private ImageView rightSide;
    @FXML
    private Label ErrSeriousCritical;
    @FXML
    private TextField totalTests;
    @FXML
    private Label ErrActiveCases;
    @FXML
    private Label ErrTotalCases;    
    @FXML
    private TextField newDeaths;
    @FXML
    public ComboBox <String> cityName;
     @FXML
    private TextField totalCases;
    
    
     private  void openAddPerson() throws IOException//loading the data from the database into the tableview
    {        
        FXMLLoader ff = new FXMLLoader(getClass().getResource("SickPeopleManagement/AddPerson.fxml"));
        Parent root2=ff.load();
        Stage pop_up2=new Stage();
        Scene scene2 = new Scene(root2,Color.TRANSPARENT);
        pop_up2.initStyle(StageStyle.TRANSPARENT);
        AdminPanel.TunisiaGovernorateManagement.SickPeopleManagement.AddPersonController controllerr2=ff.getController();
        controllerr2.cityName.setText(city);
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
         
    
    @FXML
    void saveAddButton_MouseClicked(MouseEvent event) {
        int i=0;//ce compteur va nous renseigner après si les champs sont tous remplis correctement ou pas
         
         if (cityName.getValue()==null)
        {ErrCityName.setText("Please select a city name !");
        i=1;}
        else 
        {ErrCityName.setText("");
        }
         
        if (!AddCountryController.verifLongTotalCases(totalCases.getText()))
        {i=1;
        ErrTotalCases.setText("Please enter a correct number of\nthe total cases !");
        totalCases.setText("");
        totalCases.setStyle("-fx-text-inner-color: rgb(203,153,201) ;");     
        }
        else {ErrTotalCases.setText(""); 
        totalCases.setStyle("-fx-text-inner-color: black ;");     
       }
       
            if (!AddCountryController.verifLong(newCases.getText()))
        {i=1;
        ErrNewCases.setText("Please enter a correct number of\nthe new cases !");
        newCases.setText("");
        newCases.setStyle("-fx-text-inner-color: rgb(203,153,201) ;");     
        }
        else {ErrNewCases.setText(""); 
        newCases.setStyle("-fx-text-inner-color: black ;");     
       }
            
       if (!AddCountryController.verifLong(totalDeaths.getText()))
        {i=1;
        ErrTotalDeaths.setText("Please enter a correct number of\nthe total deaths !");
        totalDeaths.setText("");
        totalDeaths.setStyle("-fx-text-inner-color: rgb(203,153,201) ;");     
        }
        else {ErrTotalDeaths.setText(""); 
        totalDeaths.setStyle("-fx-text-inner-color: black ;");     
       }
       
       if (!AddCountryController.verifLong(newDeaths.getText()))
        {i=1;
        ErrNewDeaths.setText("Please enter a correct number of\nthe new deaths !");
        newDeaths.setText("");
        newDeaths.setStyle("-fx-text-inner-color: rgb(203,153,201) ;");     
        }
        else {ErrNewDeaths.setText(""); 
        newDeaths.setStyle("-fx-text-inner-color: black ;");     
       }

        if (!AddCountryController.verifLong(totalRecovered.getText()))
        {i=1;
        ErrTotalRecovered.setText("Please enter a correct number of\nthe total recovered !");
        totalRecovered.setText("");
        totalRecovered.setStyle("-fx-text-inner-color: rgb(203,153,201) ;");     
        }
        else {ErrTotalRecovered.setText(""); 
        totalRecovered.setStyle("-fx-text-inner-color: black ;");     
       }    
        
        if (!AddCountryController.verifLong(seriousCritical.getText()))
        {i=1;
        ErrSeriousCritical.setText("Please enter a correct number of\nthe serious critical cases !");
        seriousCritical.setText("");
        seriousCritical.setStyle("-fx-text-inner-color: rgb(203,153,201) ;");     
        }
        else {ErrSeriousCritical.setText(""); 
        seriousCritical.setStyle("-fx-text-inner-color: black ;");     
       }
        
        if (!AdminPanel.OtherCountriesManagement.AddCountryController.verifLong(activeCases.getText()))
        {i=1;
        ErrActiveCases.setText("Please enter a correct number of\nthe active cases !");
        activeCases.setText("");
        activeCases.setStyle("-fx-text-inner-color: rgb(203,153,201) ;");     
        }
        else {ErrActiveCases.setText(""); 
        activeCases.setStyle("-fx-text-inner-color: black ;");     
       }
        
        if (!AdminPanel.OtherCountriesManagement.AddCountryController.verifLong(totalTests.getText()))
        {i=1;
        ErrTotalTests.setText("Please enter a correct number of\nthe total tests !");
        totalTests.setText("");
        totalTests.setStyle("-fx-text-inner-color: rgb(203,153,201) ;");     
        }
        else {ErrTotalTests.setText(""); 
        totalTests.setStyle("-fx-text-inner-color: black ;");     
       }
        
        if (i==0)//tous les champs sont remplis correctement
         {TunisiaGovernorateRegistration regC=new TunisiaGovernorateRegistration(); 
        regC.setCityName(cityName.getValue());
        
        regC.setTotalCases(totalCases.getText());
        
        if (!newCases.getText().equals("")) 
        regC.setNewCases(newCases.getText());   
        else regC.setNewCases(null);
        
        if (!totalDeaths.getText().equals("")) 
        regC.setTotalDeaths(totalDeaths.getText());
        else regC.setTotalDeaths(null);  
        
       if (!newDeaths.getText().equals("")) 
        regC.setNewDeaths(newDeaths.getText()); 
       else regC.setNewDeaths(null);
       
       if (!totalRecovered.getText().equals("")) 
        regC.setTotalRecovered(totalRecovered.getText());
       else regC.setTotalRecovered(null);
       
       if (!activeCases.getText().equals("")) 
        regC.setActiveCases(activeCases.getText());
       else regC.setActiveCases(null);
       
        if (!seriousCritical.getText().equals(""))
        regC.setSeriousCritical(seriousCritical.getText());
        else regC.setSeriousCritical(null);
        
        if (!totalTests.getText().equals(""))
        regC.setTotalTests(totalTests.getText());
        else   regC.setTotalTests(null);
        

        Date d=new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String strDate = dateFormat.format(d);  
        regC.setUpdateDate(strDate);  
        
        
             try{//insertion du nouveau gouvernorat dans la BD puis rafraîchissement de la tableview 
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        String row="INSERT INTO CORONATUNIS (CITY,TOTALCASES,NEWCASES,TOTALDEATHS,NEWDEATHS,TOTALRECOVERED,ACTIVECASES,SERIOUSCRITICAL,TOTALTESTS,DATEUPDATE) VALUES ('"+regC.getCityName()+"',"+regC.getTotalCases()+','+regC.getNewCases()+','+regC.getTotalDeaths()+','+regC.getNewDeaths()+','+regC.getTotalRecovered()+','+regC.getActiveCases()+','+regC.getSeriousCritical()+','+regC.getTotalTests()+", TO_DATE('"+strDate+"'))";
                
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
              TunisiaGovernorateManagementController.nbrrrr=0;//lorsque tout est bon et l'ajout s'est effectué avec succès la fenêtre add city va être fermée et le compteur nbr (qui nous renseigne sur le nombre de fois la fenêtre add city est ouverte) sera initialisé à 0
               Stage stage = (Stage) saveAdd.getScene().getWindow();
               stage.close();
               city=regC.getCityName();
               nbrp=Long.parseLong(regC.getTotalCases());
                   if (nbrp>0)
                    openAddPerson();
               
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
    void saveAddButton_MouseEntered(MouseEvent event) {
          saveAdd.setLayoutY(562);
    }

    @FXML
    void saveAddButton_MouseExited(MouseEvent event) {
          saveAdd.setLayoutY(572);
    }

    @FXML
    void backMouseClicked(MouseEvent event) {
        TunisiaGovernorateManagementController.nbrrrr=0;
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
       
        
    }    
    
}
