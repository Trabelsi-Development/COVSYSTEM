/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.OtherCountriesManagement;

import animatefx.animation.FadeIn;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class OtherCountriesManagementController implements Initializable {

    private ObservableList <OtherCountriesRegistration> myList= FXCollections.observableArrayList ();
    private double xOffset=0, yOffset=0;
    public static int nbrrr=0,nbr3=0;     
        
    private final ObservableList <String> countries=FXCollections.observableArrayList();
    
    @FXML
    private ImageView add_button;
    @FXML
    private DatePicker specificDate;
    @FXML
    private ComboBox<String> specificCountry;
    @FXML
    public TableView <OtherCountriesRegistration> myTableView;     
    @FXML
    private TableColumn <OtherCountriesRegistration,String> country_nameCol; 
    @FXML
    private TableColumn <OtherCountriesRegistration,String> total_casesCol;
    @FXML
    private TableColumn  <OtherCountriesRegistration,String> new_casesCol;
    @FXML
    private TableColumn <OtherCountriesRegistration,String> total_deathsCol;
    @FXML
    private TableColumn <OtherCountriesRegistration,String> new_deathsCol;
    @FXML
    private TableColumn <OtherCountriesRegistration,String> total_recoveredCol;    
    @FXML
    private TableColumn <OtherCountriesRegistration,String> active_casesCol;
    @FXML
    private TableColumn <OtherCountriesRegistration,String> serious_criticalCol;       
    @FXML
   private TableColumn <OtherCountriesRegistration,String> total_testsCol;
    @FXML
    private TableColumn <OtherCountriesRegistration,String> date_updateCol;
    @FXML
    private TableColumn <OtherCountriesRegistration,Button>editCol;
    @FXML
    private TableColumn <OtherCountriesRegistration,Button>deleteCol;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void add_button_MouseEntered()
    {
     add_button.setLayoutY(180);
    }
  
    @FXML
    private void add_button_MouseExited()
    {
     add_button.setLayoutY(190);
    }
    
     @FXML
    private void add_button_MouseClicked() throws IOException
    {   nbrrr++; 
    if (nbrrr==1)//pour ne pas ouvrir plusieurs fois la même fenêtre
    {
        FXMLLoader f1=new FXMLLoader(getClass().getResource("AddCountry.fxml"));
        Parent root = f1.load();
        AddCountryController controller1=f1.getController();
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
        pop_up.show();
            pop_up.setOnHidden(e -> {// pour rafraîchir la table après l'insertion d'un nouveau pays               
                String ch=specificCountry.getSelectionModel().getSelectedItem();
                remplissage_comboboxCountries();
                specificCountry.getSelectionModel().select(ch);
                });
            
        new FadeIn(root).play();//animation
    }
     else {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION DIALOG");
        alert.setContentText("You have already opened this window !");
        alert.showAndWait();
    }
    
    }
    
    public void loadData(String selectedCountry, String selectedDate)//loading the data from the database into the tableview
    {Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
    String data="";
    if (selectedCountry!=null)
        {if (selectedCountry.equals("All Countries") && selectedDate.equals(""))
       {data="SELECT * FROM CORONAWORLD ORDER BY DATEUPDATE DESC"; //pour afficher les pays ajoutés ou modifiés récemment de haut en bas                 
       }
       else if (!selectedCountry.equals("All Countries") && selectedDate.equals(""))
       {data="SELECT * FROM CORONAWORLD WHERE COUNTRY='"+selectedCountry+"'ORDER BY DATEUPDATE DESC";          
       }else if (selectedCountry.equals("All Countries") && !selectedDate.equals(""))
       {
           data="SELECT * FROM CORONAWORLD WHERE DATEUPDATE=TO_DATE('"+selectedDate+"')";  
       }
       else if (!selectedCountry.equals("All Countries") && !selectedDate.equals(""))
       {
            data="SELECT * FROM CORONAWORLD WHERE DATEUPDATE=TO_DATE('"+selectedDate+"') and COUNTRY='"+selectedCountry+"'"; 
       }
        
        try{
         
        ResultSet rs; 
        rs=conn.getStmt().executeQuery(data);
        myList.clear();           
           while(rs.next())
           {           
            Date d =rs.getDate("DATEUPDATE");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDate = dateFormat.format(d);  
            myList.add(new OtherCountriesRegistration (rs.getString("COUNTRY"),rs.getString("TOTALCASES"),rs.getString("NEWCASES"),rs.getString("TOTALDEATHS"),rs.getString("NEWDEATHS"),rs.getString("TOTALRECOVERED"),rs.getString("ACTIVECASES"),rs.getString("SERIOUSCRITICAL"),rs.getString("TOTALTESTS"),strDate));
                           
           }
            
        myTableView.setItems(myList);
        
        for (int i=0;i<myList.size();i++)
        { 
        OtherCountriesRegistration ad=myList.get(i);
            myList.get(i).getDeleteCountry().setOnMouseClicked(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {deleteRow(ad);
            
            }
            
        });
           myList.get(i).getEditCountry().setOnMouseClicked(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {  
                try {
                    updateRow(ad);
                } catch (IOException ex) {
                    Logger.getLogger(OtherCountriesManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            }
            
        });}
        
        conn.getStmt().close();
        conn.getConDB().close();  
        
       }catch (Exception e){
           Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
       }
        }
    }
    
    private void remplissage_comboboxCountries()
    {
    try{    
    Register_SignInForms.ConnexionDB con=new Register_SignInForms.ConnexionDB();
    String data="SELECT DISTINCT COUNTRY FROM CORONAWORLD"; //pour remplir le combobox pour que l'admin puisse choisir un pays bien déterminé
        ResultSet rsc=con.getStmt().executeQuery(data);
        countries.clear();
         countries.add("All Countries");
        while (rsc.next())            
        { countries.add(rsc.getString("COUNTRY"));}      
        specificCountry.setItems(countries);       
        con.getStmt().close();
        con.getConDB().close();
    }catch (Exception e){
           Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
       }
        
    }
    
    private void updateRow(OtherCountriesRegistration x) throws IOException
    { nbr3++; 
    if (nbr3==1)//pour ne pas ouvrir plusieurs fois la même fenêtre
    {        
        FXMLLoader f2 = new FXMLLoader(getClass().getResource("UpdateCountry.fxml"));
        Parent root2=f2.load();
        Stage pop_up2=new Stage();
        Scene scene2 = new Scene(root2,Color.TRANSPARENT);
        pop_up2.initStyle(StageStyle.TRANSPARENT);
        UpdateCountryController controller2=f2.getController();
        controller2.UPDate=x.getUpdateDate();
        controller2.countryName.setText(x.getCountryName()); 
        controller2.totalCases.setText(x.getTotalCases());  
        controller2.newCases.setText(x.getNewCases());
        controller2.totalDeaths.setText(x.getTotalDeaths());
        controller2.newDeaths.setText(x.getNewDeaths());
        controller2.totalRecovered.setText(x.getTotalRecovered());
        controller2.activeCases.setText(x.getActiveCases());
        controller2.seriousCritical.setText(x.getSeriousCritical());
        controller2.totalTests.setText(x.getTotalTests());

        //to make the window draggable (you can move it)
        root2.setOnMousePressed(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {xOffset = event.getSceneX();
            yOffset = event.getSceneY();
                
            }
        });
        root2.setOnMouseDragged(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event)
           {pop_up2.setX(event.getScreenX() - xOffset);
           pop_up2.setY(event.getScreenY() - yOffset);
                         
           }
        });
        pop_up2.setScene(scene2);               
        pop_up2.centerOnScreen();
        pop_up2.show();
            pop_up2.setOnHidden(e -> {// pour rafraichir la table après la mise à jour d'un pays
                specificCountryAction();
                });
            
        new FadeIn(root2).play();//animation
    }
    else {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION DIALOG");
        alert.setContentText("There's a window already opened!\nPlease close it before!");
        alert.showAndWait();
    }
        
    }
       
    private void deleteRow(OtherCountriesRegistration x)
    {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION DIALOG");
        alert.setContentText("Are you sure to delete ?");
        Optional <ButtonType> action= alert.showAndWait();
        
        if (action.get()==ButtonType.OK)
            {try
                {
                    String name=x.getCountryName();
                    String ud=x.getUpdateDate();
                Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
                String delete="Delete from CORONAWORLD where COUNTRY='"+name+"' and DATEUPDATE=TO_DATE('"+ud+"')";
                    
                int rs; 
                rs=conn.getStmt().executeUpdate(delete);

                if (rs==0)
                       {Alert alert2=new Alert(Alert.AlertType.ERROR);
                       alert2.setTitle("ERROR DIALOG");
                       alert2.setContentText("The removal has failed !");
                       alert2.showAndWait();
                       }else
                       { Alert alert3=new Alert(Alert.AlertType.CONFIRMATION);
                       alert3.setTitle("CONFIRMATION DIALOG");
                       alert3.setContentText("Congratulations !\nThe removal has succeeded !");
                       alert3.showAndWait();
                       String ch=specificCountry.getSelectionModel().getSelectedItem();
                       remplissage_comboboxCountries();
                       Boolean trouve=false;
                       for (int i=0;i<specificCountry.getItems().size();i++)
                       { if (ch.equals(specificCountry.getItems().get(i)))
                            {specificCountry.getSelectionModel().select(ch);
                            trouve=true;
                            break;}
                       }
                       if (!trouve)
                           specificCountry.getSelectionModel().select("All Countries");
                       
                       }

                conn.getStmt().close();
                conn.getConDB().close();
    }
        
    catch (Exception e){
           Alert alert4=new Alert(Alert.AlertType.ERROR);
               alert4.setTitle("ERROR DIALOG");
               alert4.setContentText(e.getMessage());
               alert4.showAndWait();
       }
            }
    }
    
    @FXML
    private void resetDate_MouseClicked()
    {specificDate.setValue(null);
    }
    
 @FXML
    private void specificCountryAction()
    {String sDate="";
        if (specificDate.getValue()!=null)
            sDate = specificDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        loadData(specificCountry.getSelectionModel().getSelectedItem(),sDate);

    }
    
    @FXML
    private void specificDateAction()
    {String sDate="";
        if (specificDate.getValue()!=null)
            sDate = specificDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        loadData(specificCountry.getSelectionModel().getSelectedItem(),sDate);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        country_nameCol.setCellValueFactory(new PropertyValueFactory("countryName"));
        total_casesCol.setCellValueFactory(new PropertyValueFactory("totalCases"));
        new_casesCol.setCellValueFactory(new PropertyValueFactory("newCases"));
        total_deathsCol.setCellValueFactory(new PropertyValueFactory("totalDeaths"));
        new_deathsCol.setCellValueFactory(new PropertyValueFactory("NewDeaths"));
        total_recoveredCol.setCellValueFactory(new PropertyValueFactory("totalRecovered"));
        active_casesCol.setCellValueFactory(new PropertyValueFactory("activeCases"));
        serious_criticalCol.setCellValueFactory(new PropertyValueFactory("seriousCritical"));
        total_testsCol.setCellValueFactory(new PropertyValueFactory("totalTests"));
        date_updateCol.setCellValueFactory(new PropertyValueFactory("updateDate"));      
        editCol.setCellValueFactory(new PropertyValueFactory("editCountry"));    
        deleteCol.setCellValueFactory(new PropertyValueFactory("deleteCountry"));
     
        remplissage_comboboxCountries();  
        specificCountry.getSelectionModel().select("All Countries");
        loadData(specificCountry.getSelectionModel().getSelectedItem(),"");
      
}
    
}
