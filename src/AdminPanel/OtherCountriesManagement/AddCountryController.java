/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.OtherCountriesManagement;

import java.net.URL;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddCountryController implements Initializable {
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
    private Label ErrCountryName;
    @FXML
    private TextField activeCases;
    @FXML 
    private ImageView rightSide;
    @FXML 
    private ImageView countryExistance_png;
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
    private TextField countryName;
    @FXML
    private Label countryExistance;
    @FXML
    private TextField totalCases;
    
    
    public static boolean verifLongTotalCases (String x)
    {boolean test=true;
    if (x.equals("")|| x.contains(" "))
        test=false;
    else{
    for (int i=0;i<x.length();i++)
    {
        if (!Character.isDigit(x.charAt(i)))
        {test=false;
        break;            
        }
    }
    }
    return test;
    }
    
    public static boolean verifLong (String x)
    {boolean test=true;
    if (x!=null){
        if (x.contains(" "))
            test=false;
        else 
        {for (int i=0;i<x.length();i++)
        {
            if (!Character.isDigit(x.charAt(i)))
            {test=false;
            break;            
            }
        }
        }
    }
    return test;
    }
    
@FXML
    private void country_KeyReleased(KeyEvent evt) {//évenement qui nous renseigne si le pays saisi existe déjà dans la BD ou pas
        if (!countryName.getText().equals(""))
        {
     try{
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        String data="Select * from CORONAWORLD where COUNTRY="+"'"+countryName.getText()+"'";        
        ResultSet rs; 
        rs=conn.getStmt().executeQuery(data);
        if (rs.next()) //si le pays saisi existe déjà 
        {
            countryExistance.setText("ALREADY EXISTS");
            Image image1 =new Image (getClass().getResourceAsStream("/images/cross-48.png"));
            countryExistance_png.setImage(image1);
        }
        else //Sinon
        {
             countryExistance.setText("DOESN'T EXIST YET");            
            Image image2 =new Image (getClass().getResourceAsStream("/images/correct-48.png"));
            countryExistance_png.setImage(image2);
           
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
        else {countryExistance.setText("");
         countryExistance_png.setImage(null);}
    }
    
    
    @FXML
    void saveAddButton_MouseClicked(MouseEvent event) {
        int i=0;//ce compteur va nous renseigner après si les champs sont tous remplis correctement ou pas
       
         if (!Register_SignInForms.RegisterFormController.verifAlpha(countryName.getText())||countryExistance.getText().equals("ALREADY EXISTS"))
        {i=1;
        ErrCountryName.setText("Please enter a correct non-existent country name !");
        countryName.setText("");
        countryName.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrCountryName.setText(""); 
        countryName.setStyle("-fx-text-inner-color: black ;");     
       }
         
            if (!verifLongTotalCases(totalCases.getText()))
        {i=1;
        ErrTotalCases.setText("Please enter a correct number of\nthe total cases !");
        totalCases.setText("");
        totalCases.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrTotalCases.setText(""); 
        totalCases.setStyle("-fx-text-inner-color: black ;");     
       }
       
            if (!verifLong(newCases.getText()))
        {i=1;
        ErrNewCases.setText("Please enter a correct number of\nthe new cases !");
        newCases.setText("");
        newCases.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrNewCases.setText(""); 
        newCases.setStyle("-fx-text-inner-color: black ;");     
       }
            
       if (!verifLong(totalDeaths.getText()))
        {i=1;
        ErrTotalDeaths.setText("Please enter a correct number of\nthe total deaths !");
        totalDeaths.setText("");
        totalDeaths.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrTotalDeaths.setText(""); 
        totalDeaths.setStyle("-fx-text-inner-color: black ;");     
       }
       
       if (!verifLong(newDeaths.getText()))
        {i=1;
        ErrNewDeaths.setText("Please enter a correct number of\nthe new deaths !");
        newDeaths.setText("");
        newDeaths.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrNewDeaths.setText(""); 
        newDeaths.setStyle("-fx-text-inner-color: black ;");     
       }

        if (!verifLong(totalRecovered.getText()))
        {i=1;
        ErrTotalRecovered.setText("Please enter a correct number of\nthe total recovered !");
        totalRecovered.setText("");
        totalRecovered.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrTotalRecovered.setText(""); 
        totalRecovered.setStyle("-fx-text-inner-color: black ;");     
       }    
        
        if (!verifLong(seriousCritical.getText()))
        {i=1;
        ErrSeriousCritical.setText("Please enter a correct number of\nthe serious critical cases !");
        seriousCritical.setText("");
        seriousCritical.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrSeriousCritical.setText(""); 
        seriousCritical.setStyle("-fx-text-inner-color: black ;");     
       }
        
        if (!verifLong(activeCases.getText()))
        {i=1;
        ErrActiveCases.setText("Please enter a correct number of\nthe active cases !");
        activeCases.setText("");
        activeCases.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrActiveCases.setText(""); 
        activeCases.setStyle("-fx-text-inner-color: black ;");     
       }
        
        if (!verifLong(totalTests.getText()))
        {i=1;
        ErrTotalTests.setText("Please enter a correct number of\nthe total tests !");
        totalTests.setText("");
        totalTests.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrTotalTests.setText(""); 
        totalTests.setStyle("-fx-text-inner-color: black ;");     
       }
        
        if (i==0)//tous les champs sont remplis correctement
         {OtherCountriesRegistration regC=new OtherCountriesRegistration(); 
        regC.setCountryName(countryName.getText());
        
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
        
        
             try{//insertion du nouveau pays dans la BD puis rafraîchissement de la tableview 
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        String row="INSERT INTO CORONAWORLD (COUNTRY,TOTALCASES,NEWCASES,TOTALDEATHS,NEWDEATHS,TOTALRECOVERED,ACTIVECASES,SERIOUSCRITICAL,TOTALTESTS,DATEUPDATE) VALUES ('"+regC.getCountryName()+"',"+regC.getTotalCases()+','+regC.getNewCases()+','+regC.getTotalDeaths()+','+regC.getNewDeaths()+','+regC.getTotalRecovered()+','+regC.getActiveCases()+','+regC.getSeriousCritical()+','+regC.getTotalTests()+", TO_DATE('"+strDate+"'))";
                
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
              OtherCountriesManagementController.nbrrr=0;//lorsque tout est bon et l'ajout s'est effectué avec succès la fenêtre add country va être fermée et le compteur nbr (qui nous renseigne sur le nombre de fois la fenêtre add country est ouverte) sera initialisé à 0
               Stage stage = (Stage) saveAdd.getScene().getWindow();
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
                 e.printStackTrace();
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
        OtherCountriesManagementController.nbrrr=0;        
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
