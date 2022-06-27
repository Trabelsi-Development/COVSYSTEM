/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.TunisiaGovernorateManagement;

import AdminPanel.TunisiaGovernorateManagement.SickPeopleManagement.PersonRegistration;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class TunisiaGovernorateManagementController implements Initializable {

     private ObservableList <TunisiaGovernorateRegistration> myList= FXCollections.observableArrayList ();
    private double xOffset=0, yOffset=0, xOffsett=0, yOffsett=0;
    public static int nbrrrr=0,nbr4=0,nbr42=0,nbr45=0;   
   
    private ObservableList <PersonRegistration> myListPeople= FXCollections.observableArrayList ();
    private  ObservableList <String> citiesAdd = FXCollections.observableArrayList("Ariana","Beja","Ben Arous","Tunis","Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kasserine","Kebili","Kef","Mahdia","Manouba","Medenine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine","Tozeur","Zaghouan");//une liste contenant les 24 gouvernorats qui existent en Tunisie qu'on va les affecter par la suite au combobox city name   
    private final ObservableList <String> cities=FXCollections.observableArrayList();
    
    @FXML
    private ImageView add_button;
    @FXML
    private DatePicker specificDate;
    @FXML
    private ComboBox<String> specificCity;
    @FXML
    public TableView <TunisiaGovernorateRegistration> myTableView;     
    @FXML
    private TableColumn <TunisiaGovernorateRegistration,String> city_nameCol; 
    @FXML
    private TableColumn <TunisiaGovernorateRegistration,String> total_casesCol;
    @FXML
    private TableColumn  <TunisiaGovernorateRegistration,String> new_casesCol;
    @FXML
    private TableColumn <TunisiaGovernorateRegistration,String> total_deathsCol;
    @FXML
    private TableColumn <TunisiaGovernorateRegistration,String> new_deathsCol;
    @FXML
    private TableColumn <TunisiaGovernorateRegistration,String> total_recoveredCol;    
    @FXML
    private TableColumn <TunisiaGovernorateRegistration,String> active_casesCol;
    @FXML
    private TableColumn <TunisiaGovernorateRegistration,String> serious_criticalCol;       
    @FXML
   private TableColumn <TunisiaGovernorateRegistration,String> total_testsCol;
    @FXML
    private TableColumn <TunisiaGovernorateRegistration,String> date_updateCol;
    @FXML
    private TableColumn <TunisiaGovernorateRegistration,Button>editCol;
    @FXML
    private TableColumn <TunisiaGovernorateRegistration,Button>deleteCol;
    @FXML
    private TableColumn <TunisiaGovernorateRegistration,Button>detailsCol;
    
     
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
    {   if (loadingCities().isEmpty())
    {Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION DIALOG");
        alert.setContentText("All cities are added in the data base !");
        alert.showAndWait();}
    else{
        nbrrrr++; 
    if (nbrrrr==1)//pour ne pas ouvrir plusieurs fois la même fenêtre
    {
        FXMLLoader f1=new FXMLLoader(getClass().getResource("AddCity.fxml"));
        Parent root = f1.load();
        AddCityController controller1=f1.getController();
        controller1.cityName.setItems(loadingCities());
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
            pop_up.setOnHidden(e -> {// pour rafraîchir la table après l'insertion d'un nouveau gouvernorat               
                String ch=specificCity.getSelectionModel().getSelectedItem();
                remplissage_comboboxCountries();
                specificCity.getSelectionModel().select(ch);
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
    }
    
    public void loadData(String selectedCity, String selectedDate)//loading the data from the database into the tableview
    {Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
    String data="";
    if (selectedCity!=null)
        {if (selectedCity.equals("All Cities") && selectedDate.equals(""))
       {data="SELECT * FROM CORONATUNIS ORDER BY DATEUPDATE DESC"; //pour afficher les gouvernorats ajoutés ou modifiés récemment de haut en bas                 
       }
       else if (!selectedCity.equals("All Cities") && selectedDate.equals(""))
       {data="SELECT * FROM CORONATUNIS WHERE CITY='"+selectedCity+"'ORDER BY DATEUPDATE DESC";          
       }else if (selectedCity.equals("All Cities") && !selectedDate.equals(""))
       {
           data="SELECT * FROM CORONATUNIS WHERE DATEUPDATE=TO_DATE('"+selectedDate+"')";  
       }
       else if (!selectedCity.equals("All Cities") && !selectedDate.equals(""))
       {
            data="SELECT * FROM CORONATUNIS WHERE DATEUPDATE=TO_DATE('"+selectedDate+"') and CITY='"+selectedCity+"'"; 
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
            myList.add(new TunisiaGovernorateRegistration (rs.getString("CITY"),rs.getString("TOTALCASES"),rs.getString("NEWCASES"),rs.getString("TOTALDEATHS"),rs.getString("NEWDEATHS"),rs.getString("TOTALRECOVERED"),rs.getString("ACTIVECASES"),rs.getString("SERIOUSCRITICAL"),rs.getString("TOTALTESTS"),strDate));
                           
           }
            
        myTableView.setItems(myList);
        
        for (int i=0;i<myList.size();i++)
        { 
        TunisiaGovernorateRegistration ad=myList.get(i);
            myList.get(i).getDeleteCity().setOnMouseClicked(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {deleteRow(ad);
            
            }
            
        });
           myList.get(i).getEditCity().setOnMouseClicked(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {  
                try {
                    updateRow(ad);
                } catch (IOException ex) {
                    Logger.getLogger(TunisiaGovernorateManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            }
            
        });
         myList.get(i).getDetailsPerson().setOnMouseClicked(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {  
                try {
                    show_tableViewPerson(ad);
                } catch (IOException ex) {
                    Logger.getLogger(TunisiaGovernorateManagementController.class.getName()).log(Level.SEVERE, null, ex);
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
   private ObservableList <String> loadingCities()//cette méthode sert à eliminer les gouvernorats déjà ajoutés dans la BD de la liste citiesAdd et puis on trouve uniquement les gouvernorats non ajoutés dans le combobox
    {try{    
    Register_SignInForms.ConnexionDB con=new Register_SignInForms.ConnexionDB();
    String data="SELECT DISTINCT CITY FROM CORONATUNIS"; 
        ResultSet rsc=con.getStmt().executeQuery(data);
        citiesAdd.clear();
        citiesAdd = FXCollections.observableArrayList("Ariana","Beja","Ben Arous","Tunis","Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kasserine","Kebili","Kef","Mahdia","Manouba","Medenine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine","Tozeur","Zaghouan");//une liste contenant les 24 gouvernorats qui existent en Tunisie qu'on va les affecter par la suite au combobox city name 
        while (rsc.next())
        {for (int i=0;i<citiesAdd.size();i++)
            {if (rsc.getString("CITY").equals(citiesAdd.get(i)))
                {citiesAdd.remove(i);
                break;
                }
            }

        }           
            
        con.getStmt().close();
        con.getConDB().close();
    }catch (Exception e){
           Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
       }
    return citiesAdd;    

    } 
   
    private void remplissage_comboboxCountries()
    {
    try{    
    Register_SignInForms.ConnexionDB con=new Register_SignInForms.ConnexionDB();
    String data="SELECT DISTINCT CITY FROM CORONATUNIS"; //pour remplir le combobox pour que l'admin puisse choisir un gouvernorat bien déterminé
        ResultSet rsc=con.getStmt().executeQuery(data);
        cities.clear();
         cities.add("All Cities");
        while (rsc.next())            
        { cities.add(rsc.getString("CITY"));}      
        specificCity.setItems(cities);       
        con.getStmt().close();
        con.getConDB().close();
    }catch (Exception e){
           Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
       }
        
    }
    
     private void show_tableViewPerson(TunisiaGovernorateRegistration x) throws IOException
    { nbr42++; 
    if (nbr42==1)//pour ne pas ouvrir plusieurs fois la même fenêtre
    {x.getDetailsPerson().setStyle("-fx-background-color: #e5d4de; -fx-background-radius: 10; -fx-text-fill:white; -fx-font-family:System; -fx-font-size:13px; -fx-font-weight:bold;");   
          
        FXMLLoader ff = new FXMLLoader(getClass().getResource("SickPeopleManagement/SickPeopleManagement.fxml"));
        Parent root2=ff.load();
        Stage pop_up2=new Stage();
        Scene scene2 = new Scene(root2,Color.TRANSPARENT);
        pop_up2.initStyle(StageStyle.TRANSPARENT);
        AdminPanel.TunisiaGovernorateManagement.SickPeopleManagement.SickPeopleManagementController controllerr2=ff.getController();
        controllerr2.cinCol.setCellValueFactory(new PropertyValueFactory("cin"));
        controllerr2.first_nameCol.setCellValueFactory(new PropertyValueFactory("first_name"));
        controllerr2.last_nameCol.setCellValueFactory(new PropertyValueFactory("last_name"));
        controllerr2.genderCol.setCellValueFactory(new PropertyValueFactory("gender"));
        controllerr2.ageCol.setCellValueFactory(new PropertyValueFactory("age"));
        controllerr2.cityCol.setCellValueFactory(new PropertyValueFactory("city"));
        controllerr2.statusCol.setCellValueFactory(new PropertyValueFactory("statusCorona"));
        controllerr2.foreign_caseCol.setCellValueFactory(new PropertyValueFactory("foreignCase"));
        controllerr2.date_addCol.setCellValueFactory(new PropertyValueFactory("addDate"));
        controllerr2.date_updateCol.setCellValueFactory(new PropertyValueFactory("updateDate"));      
        controllerr2.editCol.setCellValueFactory(new PropertyValueFactory("editPerson"));        
        controllerr2.myTableViewPeople.setItems(loadDataPeople(x.getCityName(),x.getUpdateDate()));
        
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
        pop_up2.setOnHidden(e -> {// pour que la couleur du bouton sélectionné revient à sa couleur initiale
         x.getDetailsPerson().setStyle("-fx-background-color:  #cb99c9; -fx-background-radius: 10; -fx-text-fill:white; -fx-font-family:System; -fx-font-size:13px; -fx-font-weight:bold;");   
    
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
       
    
    private void updateRow(TunisiaGovernorateRegistration x) throws IOException
    { nbr4++; 
    if (nbr4==1)//pour ne pas ouvrir plusieurs fois la même fenêtre
    {        
        FXMLLoader ff = new FXMLLoader(getClass().getResource("UpdateCity.fxml"));
        Parent root2=ff.load();
        Stage pop_up2=new Stage();
        Scene scene2 = new Scene(root2,Color.TRANSPARENT);
        pop_up2.initStyle(StageStyle.TRANSPARENT);
        UpdateCityController controllerr2=ff.getController();
        controllerr2.UPDate=x.getUpdateDate();
        controllerr2.cityName.setText(x.getCityName()); 
        controllerr2.totalCases.setText(x.getTotalCases());  
        controllerr2.newCases.setText(x.getNewCases());
        controllerr2.totalDeaths.setText(x.getTotalDeaths());
        controllerr2.newDeaths.setText(x.getNewDeaths());
        controllerr2.totalRecovered.setText(x.getTotalRecovered());
        controllerr2.activeCases.setText(x.getActiveCases());
        controllerr2.seriousCritical.setText(x.getSeriousCritical());
        controllerr2.totalTests.setText(x.getTotalTests());

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
            pop_up2.setOnHidden(e -> {// pour rafraichir la table après la mise à jour d'un gouvernorat
                specificCityAction();
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
       
    private void deleteRow(TunisiaGovernorateRegistration x)
    {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION DIALOG");
        alert.setContentText("Are you sure to delete ?");
        Optional <ButtonType> action= alert.showAndWait();
        
        if (action.get()==ButtonType.OK)
            {try
                {
                    String name=x.getCityName();
                    String ud=x.getUpdateDate();
                    
                Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
                Register_SignInForms.ConnexionDB connP= new Register_SignInForms.ConnexionDB();
                String deleteP="Delete from PERSON where CITY='"+name+"' and DATEADD=TO_DATE('"+ud+"')";//pour supprimer les peronnes malades dans ce gouvernorat avant
                
                String delete="Delete from CORONATUNIS where CITY='"+name+"' and DATEUPDATE=TO_DATE('"+ud+"')";//puis on supprime le gouvernorat
                    
                int rs,rsP; 
                
                rsP=connP.getStmt().executeUpdate(deleteP);
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
                       String ch=specificCity.getSelectionModel().getSelectedItem();
                       remplissage_comboboxCountries();
                       Boolean trouve=false;
                       if (!ch.equals("All Cities"))
                            {for (int i=0;i<specificCity.getItems().size();i++)
                                     { if (ch.equals(specificCity.getItems().get(i)))//s'il existe encore d'updates avec ce gouvernorat supprimé
                                          {trouve=true;
                                          break;}
                                     }

                            }
                       else 
                            {for (int i=0;i<specificCity.getItems().size();i++)
                                { if (ch.equals(specificCity.getItems().get(i)))//s'il existe encore d'updates avec ce gouvernorat supprimé
                                    {trouve=true;
                                     break;}
                               }
                                
                       }
                       if (!trouve)//s'il n'existe plus d'updates avec ce gouvernorat supprimé
                            {
                            ch="All Cities";
                                }
                         specificCity.getSelectionModel().select(ch);//pour rafraîchir le TableView   
                       }

                conn.getStmt().close();
                conn.getConDB().close();
                connP.getStmt().close();
                connP.getConDB().close();
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
    private void specificCityAction()
    {String sDate="";
        if (specificDate.getValue()!=null)
            sDate = specificDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        loadData(specificCity.getSelectionModel().getSelectedItem(),sDate);

    }
    
    private ObservableList<PersonRegistration> loadDataPeople(String city_name, String DUPDATECITY)//loading the data from the database into the tableview
    {
        try{
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        String data="SELECT * FROM Person where CITY='"+city_name+"' and DATEADD<=TO_DATE('"+DUPDATECITY+"') ORDER BY DATEADD DESC";
        ResultSet rs; 
        rs=conn.getStmt().executeQuery(data);
        myListPeople.clear();
            String fc,UPdate="",ADDdate="";
           while(rs.next())
           {
           if (rs.getString("FOREIGNCASE").equals("Y"))
               fc="YES";                
           else fc="NO";
           SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");                   
           Date da =rs.getDate("DATEADD");
                 ADDdate= dateFormat.format(da);
           if (rs.getString("DATEUPDATE")!=null)
                {Date d =rs.getDate("DATEUPDATE");
                 UPdate= dateFormat.format(d);  }
           else UPdate="NEVER UPDATED BEFORE";
            
            myListPeople.add(new PersonRegistration (rs.getString("CIN"),rs.getString("FIRST_NAME"),rs.getString("LAST_NAME"),rs.getString("GENDER"),rs.getString("AGE"),rs.getString("CITY"),rs.getString("STATUSCORONA"),fc,ADDdate,UPdate));
                           
           }
            
        
        for (int i=0;i<myListPeople.size();i++)
        { 
        PersonRegistration pr=myListPeople.get(i);
        myListPeople.get(i).getEditPerson().setOnMouseClicked(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {   try {
                updateRowPeople(pr);
                } catch (IOException ex) {
                    Logger.getLogger(TunisiaGovernorateManagementController.class.getName()).log(Level.SEVERE, null, ex);
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
        return myListPeople;
    }
    
     private void updateRowPeople(PersonRegistration x) throws IOException
    { nbr45++; 
    if (nbr45==1)//pour ne pas ouvrir plusieurs fois la même fenêtre
    {        
        FXMLLoader ff = new FXMLLoader(getClass().getResource("SickPeopleManagement/UpdatePerson.fxml"));
        Parent root2=ff.load();
        Stage pop_up2=new Stage();
        Scene scene2 = new Scene(root2,Color.TRANSPARENT);
        pop_up2.initStyle(StageStyle.TRANSPARENT);
        AdminPanel.TunisiaGovernorateManagement.SickPeopleManagement.UpdatePersonController controllerr5=ff.getController();
        controllerr5.cin.setText(x.getCin());
        controllerr5.ADDDate=x.getAddDate();
        controllerr5.cityName.setText(x.getCity());
        controllerr5.firstName.setText(x.getFirst_name());
        controllerr5.lastName.setText(x.getLast_name());
        if (x.getGender().equals("Male"))
            controllerr5.Male.setSelected(true);
        else controllerr5.Female.setSelected(true);
        controllerr5.age.setText(x.getAge());
        controllerr5.statusCmb.getSelectionModel().select(x.getStatusCorona());
        if (x.getForeignCase().equals("YES"))
            controllerr5.yes.setSelected(true);
        else controllerr5.no.setSelected(true);
        
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
            pop_up2.setOnHidden(e -> {
                loadDataPeople(x.getCity(),x.getAddDate());// pour rafraichir la table après la mise à jour d'une personne
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
   
    @FXML
    private void specificDateAction()
    {String sDate="";
        if (specificDate.getValue()!=null)
            sDate = specificDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        loadData(specificCity.getSelectionModel().getSelectedItem(),sDate);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        city_nameCol.setCellValueFactory(new PropertyValueFactory("cityName"));
        total_casesCol.setCellValueFactory(new PropertyValueFactory("totalCases"));
        new_casesCol.setCellValueFactory(new PropertyValueFactory("newCases"));
        total_deathsCol.setCellValueFactory(new PropertyValueFactory("totalDeaths"));
        new_deathsCol.setCellValueFactory(new PropertyValueFactory("NewDeaths"));
        total_recoveredCol.setCellValueFactory(new PropertyValueFactory("totalRecovered"));
        active_casesCol.setCellValueFactory(new PropertyValueFactory("activeCases"));
        serious_criticalCol.setCellValueFactory(new PropertyValueFactory("seriousCritical"));
        total_testsCol.setCellValueFactory(new PropertyValueFactory("totalTests"));
        date_updateCol.setCellValueFactory(new PropertyValueFactory("updateDate"));      
        editCol.setCellValueFactory(new PropertyValueFactory("editCity"));    
        deleteCol.setCellValueFactory(new PropertyValueFactory("deleteCity"));
        detailsCol.setCellValueFactory(new PropertyValueFactory("detailsPerson"));
     
        remplissage_comboboxCountries();  
        specificCity.getSelectionModel().select("All Cities");
        loadData(specificCity.getSelectionModel().getSelectedItem(),"");
      
}
    
}
