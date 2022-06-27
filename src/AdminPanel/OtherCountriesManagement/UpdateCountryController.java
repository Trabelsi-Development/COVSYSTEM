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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class UpdateCountryController implements Initializable {
    public String UPDate=""; private int rss;
    
   @FXML
    private Label ErrTotalDeaths;
    @FXML
    private Button saveUpdate;
    @FXML
    public TextField newCases;
    @FXML
    private ImageView back;
    @FXML
    public TextField totalRecovered;
    @FXML
    private Label ErrTotalRecovered;
    @FXML
    private Label ErrTotalTests;
    @FXML
    private Label ErrNewDeaths;
    @FXML
    public TextField totalDeaths;
    @FXML
    public TextField seriousCritical;
    @FXML
    private Label ErrNewCases;
    @FXML
    private Label ErrCountryName;
    @FXML
    public TextField activeCases;
    @FXML 
    private ImageView rightSide;   
    @FXML
    private Label ErrSeriousCritical;
    @FXML
    public TextField totalTests;
    @FXML
    private Label ErrActiveCases;
    @FXML
    private Label ErrTotalCases;    
    @FXML
    public TextField newDeaths;
    @FXML
    public TextField countryName;   
    @FXML
    public TextField totalCases;
   
    
    @FXML
    void saveUpdateButton_MouseClicked(MouseEvent event) {
        int i=0;//ce compteur va nous renseigner après si les champs sont tous remplis correctement ou pas
             
            if (!AddCountryController.verifLongTotalCases(totalCases.getText()))
        {i=1;
        ErrTotalCases.setText("Please enter a correct number of\nthe total cases !");
        totalCases.setText("");
        totalCases.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrTotalCases.setText(""); 
        totalCases.setStyle("-fx-text-inner-color: black ;");     
       }
       
            if (!AddCountryController.verifLong(newCases.getText()))
        {i=1;
        ErrNewCases.setText("Please enter a correct number of\nthe new cases !");
        newCases.setText("");
        newCases.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrNewCases.setText(""); 
        newCases.setStyle("-fx-text-inner-color: black ;");     
       }
            
       if (!AddCountryController.verifLong(totalDeaths.getText()))
        {i=1;
        ErrTotalDeaths.setText("Please enter a correct number of\nthe total deaths !");
        totalDeaths.setText("");
        totalDeaths.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrTotalDeaths.setText(""); 
        totalDeaths.setStyle("-fx-text-inner-color: black ;");     
       }
       
       if (!AddCountryController.verifLong(newDeaths.getText()))
        {i=1;
        ErrNewDeaths.setText("Please enter a correct number of\nthe new deaths !");
        newDeaths.setText("");
        newDeaths.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrNewDeaths.setText(""); 
        newDeaths.setStyle("-fx-text-inner-color: black ;");     
       }

        if (!AddCountryController.verifLong(totalRecovered.getText()))
        {i=1;
        ErrTotalRecovered.setText("Please enter a correct number of\nthe total recovered !");
        totalRecovered.setText("");
        totalRecovered.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrTotalRecovered.setText(""); 
        totalRecovered.setStyle("-fx-text-inner-color: black ;");     
       }    
        
        if (!AddCountryController.verifLong(seriousCritical.getText()))
        {i=1;
        ErrSeriousCritical.setText("Please enter a correct number of\nthe serious critical cases !");
        seriousCritical.setText("");
        seriousCritical.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrSeriousCritical.setText(""); 
        seriousCritical.setStyle("-fx-text-inner-color: black ;");     
       }
        
        if (!AddCountryController.verifLong(activeCases.getText()))
        {i=1;
        ErrActiveCases.setText("Please enter a correct number of\nthe active cases !");
        activeCases.setText("");
        activeCases.setStyle("-fx-text-inner-color: rgb(251,205,89) ;");     
        }
        else {ErrActiveCases.setText(""); 
        activeCases.setStyle("-fx-text-inner-color: black ;");     
       }
        
        if (!AddCountryController.verifLong(totalTests.getText()))
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
        
        if (newCases.getText()==null || newCases.getText().equals("")) 
        regC.setNewCases(null);   
        else regC.setNewCases(newCases.getText());
        
        if (totalDeaths.getText()==null || totalDeaths.getText().equals("")) 
        regC.setTotalDeaths(null);
        else regC.setTotalDeaths(totalDeaths.getText());  
        
       if (newDeaths.getText()==null || newDeaths.getText().equals("")) 
        regC.setNewDeaths(null); 
       else regC.setNewDeaths(newDeaths.getText());
       
       if (totalRecovered.getText()==null || totalRecovered.getText().equals("")) 
        regC.setTotalRecovered(null);
       else regC.setTotalRecovered(totalRecovered.getText());
       
       if (activeCases.getText()==null || activeCases.getText().equals("")) 
        regC.setActiveCases(null);
       else regC.setActiveCases(activeCases.getText());
       
        if (seriousCritical.getText()==null || seriousCritical.getText().equals(""))
        regC.setSeriousCritical(null);
        else regC.setSeriousCritical(seriousCritical.getText());
        
        if (totalTests.getText()==null || totalTests.getText().equals(""))
        regC.setTotalTests(null);
        else   regC.setTotalTests(totalTests.getText());
        

        Date d=new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String strDate = dateFormat.format(d);  
        regC.setUpdateDate(strDate); 
        
        
        try{//insertion de la nouvelle mise à jour d'un pays bien déterminé puisque c'est sa première mise à jour aujourd'hui dans la BD puis rafraîchissement de la tableview 
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        Boolean trouve;
        String check ="SELECT * FROM CORONAWORLD WHERE COUNTRY='"+regC.getCountryName()+"' and DATEUPDATE=TO_DATE('"+strDate+"')";
        ResultSet rs= conn.getStmt().executeQuery(check);
            if (!rs.next())//pour verifier si l'admin a déjà effectué une mise à jour à ce pays aujourd'hui ou pas sinon il y aura une erreur de duplication au niveau des clés primaires (COUNTRY et DATEUPDATE) 
                trouve=false;
            else trouve=true;
String row="";
            if (!UPDate.equals(strDate) && !trouve)    
            {row="INSERT INTO CORONAWORLD (COUNTRY,TOTALCASES,NEWCASES,TOTALDEATHS,NEWDEATHS,TOTALRECOVERED,ACTIVECASES,SERIOUSCRITICAL,TOTALTESTS,DATEUPDATE) VALUES ('"+regC.getCountryName()+"',"+regC.getTotalCases()+','+regC.getNewCases()+','+regC.getTotalDeaths()+','+regC.getNewDeaths()+','+regC.getTotalRecovered()+','+regC.getActiveCases()+','+regC.getSeriousCritical()+','+regC.getTotalTests()+", TO_DATE('"+strDate+"'))";
            }
        
            else //update de la nouvelle mise à jour d'un pays bien déterminé puisque ce n'est pas sa première mise à jour aujourd'hui dans la BD puis rafraîchissement de la tableview
            {           
            row="UPDATE CORONAWORLD SET TOTALCASES="+regC.getTotalCases()+','
                    + "NEWCASES="+regC.getNewCases()+','
                    + "TOTALDEATHS="+regC.getTotalDeaths()+','
                    + "NEWDEATHS="+regC.getNewDeaths()+','
                    + "TOTALRECOVERED="+regC.getTotalRecovered()+','
                    + "ACTIVECASES="+regC.getActiveCases()+','
                    + "SERIOUSCRITICAL="+regC.getSeriousCritical()+','
                    + "TOTALTESTS="+regC.getTotalTests()+','
                    + "DATEUPDATE= TO_DATE('"+strDate+"') where COUNTRY='"+regC.getCountryName()+"' and DATEUPDATE= TO_DATE('"+strDate+"')";
            }
            rss=conn.getStmt().executeUpdate(row); 
            conn.getStmt().close();
            conn.getConDB().close();
                 
            }
             catch (Exception e){
           Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
       
            
        }
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
              OtherCountriesManagementController.nbr3=0;//lorsque tout est bon et l'update est effectuée avec succès la fenêtre update country va être fermée et le compteur nbr (qui nous renseigne sur le nombre de fois la fenêtre update country est ouverte) sera initialisé à 0
               Stage stage = (Stage) saveUpdate.getScene().getWindow();
               stage.close();
            
               }
        }
      
    
    }
    
    @FXML
    void saveUpdateButton_MouseEntered(MouseEvent event) {
          saveUpdate.setLayoutY(562);
    }

    @FXML
    void saveUpdateButton_MouseExited(MouseEvent event) {
          saveUpdate.setLayoutY(572);
    }

    @FXML
    void backMouseClicked(MouseEvent event) {
        OtherCountriesManagementController.nbr3=0;        
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



