/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.HospitalManagement;

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
public class HospitalManagementGeneralController implements Initializable {

   private ObservableList <HospitalRegistration> myList= FXCollections.observableArrayList ();
    private ObservableList <HospitalRegistration> myDetails= FXCollections.observableArrayList ();
   
    private double xOffset=0, yOffset=0,xOffsett=0, yOffsett=0;
    public static int nbrrrrr=0,nbr5=0, nombre=0;   
    private final ObservableList <String> hospitals=FXCollections.observableArrayList();
    private final ObservableList <String> citiesAdd = FXCollections.observableArrayList("Ariana","Beja","Ben Arous","Tunis","Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kasserine","Kebili","Kef","Mahdia","Manouba","Medenine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine","Tozeur","Zaghouan");//une liste contenant les 24 gouvernorats qui existent en Tunisie qu'on va les affecter par la suite au combobox city name   
  
    
    @FXML
    private ImageView add_button;
    @FXML
    private DatePicker specificDate;
    @FXML
    private ComboBox<String> specificHospital;
    @FXML
    public TableView <HospitalRegistration> myTableView;     
    @FXML
    private TableColumn <HospitalRegistration,String> hospital_nameCol; 
    @FXML
    private TableColumn <HospitalRegistration,String> hospitalized_casesCol;
    @FXML
    private TableColumn  <HospitalRegistration,String> available_bedsCol;
    @FXML
    private TableColumn <HospitalRegistration,String> doctors_numberCol;
    @FXML
    private TableColumn <HospitalRegistration,String> rooms_numberCol;
    @FXML
    private TableColumn <HospitalRegistration,String> total_testsCol;    
    @FXML
    private TableColumn <HospitalRegistration,String> city_nameCol;
    @FXML
    private TableColumn <HospitalRegistration,String> date_updateCol;
    @FXML
    private TableColumn <HospitalRegistration,Button>editCol;
    @FXML
    private TableColumn <HospitalRegistration,Button>deleteCol;
    @FXML
    private TableColumn <HospitalRegistration,Button>detailsCol;

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
    {   nbrrrrr++; 
    if (nbrrrrr==1)//pour ne pas ouvrir plusieurs fois la même fenêtre
    {
        FXMLLoader f1=new FXMLLoader(getClass().getResource("AddHospitalGeneral.fxml"));
        Parent root = f1.load();
        AddHospitalGeneralController controller1=f1.getController();
        controller1.CityName_cmb.setItems(citiesAdd);
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
            pop_up.setOnHidden(e -> {// pour rafraîchir la table après l'insertion d'un nouvel hôpital 
                String ch=specificHospital.getSelectionModel().getSelectedItem();
                remplissage_comboboxHospitals();
                specificHospital.getSelectionModel().select(ch);
                
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
    
    public void loadData(String selectedHospital, String selectedDate)//loading the data from the database into the tableview
    {Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
    String data="";
    if (selectedHospital!=null)
        {if (selectedHospital.equals("All Hospitals") && selectedDate.equals(""))
       {data="SELECT HOSPITALNAME, HOSPITALIZEDCASES, BEDSAVAILABLE, DOCTORSNUMBER, ROOMSNUMBER, TOTALTESTS, CITYNAME, DATEUPDATE FROM HOSPITALS ORDER BY DATEUPDATE DESC"; //pour afficher les hôpitaux ajoutés ou modifiés récemment de haut en bas                 
       }
       else if (!selectedHospital.equals("All Hospitals") && selectedDate.equals(""))
       {data="SELECT HOSPITALNAME, HOSPITALIZEDCASES, BEDSAVAILABLE, DOCTORSNUMBER, ROOMSNUMBER, TOTALTESTS, CITYNAME, DATEUPDATE FROM HOSPITALS WHERE HOSPITALNAME='"+selectedHospital+"'ORDER BY DATEUPDATE DESC";          
       }else if (selectedHospital.equals("All Hospitals") && !selectedDate.equals(""))
       {
           data="SELECT HOSPITALNAME, HOSPITALIZEDCASES, BEDSAVAILABLE, DOCTORSNUMBER, ROOMSNUMBER, TOTALTESTS, CITYNAME, DATEUPDATE FROM HOSPITALS WHERE DATEUPDATE=TO_DATE('"+selectedDate+"')";  
       }
       else if (!selectedHospital.equals("All Hospitals") && !selectedDate.equals(""))
       {
            data="SELECT HOSPITALNAME, HOSPITALIZEDCASES, BEDSAVAILABLE, DOCTORSNUMBER, ROOMSNUMBER, TOTALTESTS, CITYNAME, DATEUPDATE FROM HOSPITALS WHERE DATEUPDATE=TO_DATE('"+selectedDate+"') and HOSPITALNAME='"+selectedHospital+"'"; 
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
            myList.add(new HospitalRegistration (rs.getString("HOSPITALNAME"),rs.getString("HOSPITALIZEDCASES"),rs.getString("BEDSAVAILABLE"),rs.getString("DOCTORSNUMBER"),rs.getString("ROOMSNUMBER"),rs.getString("TOTALTESTS"),rs.getString("CITYNAME"),strDate));
                           
           }
            
        myTableView.setItems(myList);
        
        for (int i=0;i<myList.size();i++)
        {
        HospitalRegistration ad=myList.get(i);
            myList.get(i).getDeleteHospital().setOnMouseClicked(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {deleteRow(ad);
            
            }
            
        });
           myList.get(i).getEditHospital().setOnMouseClicked(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {  
                try {
                    updateRow(ad);
                } catch (IOException ex) {
                    Logger.getLogger(HospitalManagementGeneralController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            }
            
        });
        myList.get(i).getDetailsHospital().setOnMouseClicked(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {  try {
                    loadDetails(ad.getHospitalName(),ad.getUpdateDate(),ad.getDetailsHospital());
                } catch (IOException ex) {
                    Logger.getLogger(HospitalManagementGeneralController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        });
        }
        
        
        
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
    
    private void remplissage_comboboxHospitals()
    {
    try{    
    Register_SignInForms.ConnexionDB con=new Register_SignInForms.ConnexionDB();
    String data="SELECT DISTINCT HOSPITALNAME FROM HOSPITALS"; //pour remplir le combobox pour que l'admin puisse choisir un hôpital bien déterminé
        ResultSet rsc=con.getStmt().executeQuery(data);
        hospitals.clear();
         hospitals.add("All Hospitals");
        while (rsc.next())            
        { hospitals.add(rsc.getString("HOSPITALNAME"));}      
        specificHospital.setItems(hospitals);       
        con.getStmt().close();
        con.getConDB().close();
    }catch (Exception e){
           Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
       }
        
    }
    
    private void loadDetails(String hnn, String upd, Button b) throws IOException
    {nombre++;
        if (nombre==1)//pour ne pas ouvrir plusieurs fois la même fenêtre
        { b.setStyle("-fx-background-color:  #F3CBCA; -fx-background-radius: 10; -fx-text-fill:white; -fx-font-family:System; -fx-font-size:13px; -fx-font-weight:bold;");   
                   
            FXMLLoader fff = new FXMLLoader(getClass().getResource("HospitalManagementDetails.fxml"));
            Parent roott=fff.load();
            Stage pop_upp=new Stage();
            Scene scenee = new Scene(roott,Color.TRANSPARENT);
            pop_upp.initStyle(StageStyle.TRANSPARENT);
            HospitalManagementDetailsController controllerrr=fff.getController();
            controllerrr.supply_needsCol.setCellValueFactory(new PropertyValueFactory("supplyNeeds"));
            controllerrr.equip_needsCol.setCellValueFactory(new PropertyValueFactory("equipmentNeeds"));
            controllerrr.donation_needsCol.setCellValueFactory(new PropertyValueFactory("donationNeeds"));
            controllerrr.myTableViewDetails.setItems(remplissageDetails(hnn,upd));
            
            //to make the window draggable (you can move it)
            roott.setOnMousePressed(new EventHandler <MouseEvent>(){
                @Override
                public void handle(MouseEvent event)
                {xOffsett = event.getSceneX();
                yOffsett = event.getSceneY();

                }
            });
            roott.setOnMouseDragged(new EventHandler<MouseEvent>(){
               @Override
               public void handle(MouseEvent event)
               {pop_upp.setX(event.getScreenX() - xOffsett);
               pop_upp.setY(event.getScreenY() - yOffsett);

               }
            });
            pop_upp.setScene(scenee);               
            pop_upp.centerOnScreen();
            pop_upp.show();
                pop_upp.setOnHidden(e -> {// pour que la couleur du bouton sélectionné revient à sa couleur initiale
                    b.setStyle("-fx-background-color:  #F27C7C; -fx-background-radius: 10; -fx-text-fill:white; -fx-font-family:System; -fx-font-size:13px; -fx-font-weight:bold;");   
    
                    });

            new FadeIn(roott).play();//animation
        }
        else {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setContentText("There's a window already opened!\nPlease close it before!");
            alert.showAndWait();
        }        
            
    }
    
    private void updateRow(HospitalRegistration x) throws IOException
    { nbr5++; 
    if (nbr5==1)//pour ne pas ouvrir plusieurs fois la même fenêtre
    {        
        FXMLLoader f2 = new FXMLLoader(getClass().getResource("UpdateHospitalGeneral.fxml"));
        Parent root2=f2.load();
        Stage pop_up2=new Stage();
        Scene scene2 = new Scene(root2,Color.TRANSPARENT);
        pop_up2.initStyle(StageStyle.TRANSPARENT);
        UpdateHospitalGeneralController controller2=f2.getController();
        controller2.UPDatee=x.getUpdateDate();
        controller2.hospitalName.setText(x.getHospitalName()); 
        controller2.cityName.setText(x.getCityName()); 
        controller2.hospitalizedCases.setText(x.getHospitalizedCases());  
        controller2.availableBeds.setText(x.getAvailableBeds());
        controller2.doctorsNumber.setText(x.getDoctorsNumber());
        controller2.roomsNumber.setText(x.getRoomsNumber());
        controller2.totalTests.setText(x.getTotalTests());
       
        UpdateHospitalGeneralController.supply=remplissageDetails(x.getHospitalName(),x.getUpdateDate()).get(0).getSupplyNeeds();
        UpdateHospitalGeneralController.equip=remplissageDetails(x.getHospitalName(),x.getUpdateDate()).get(0).getEquipmentNeeds();
        UpdateHospitalGeneralController.donation=remplissageDetails(x.getHospitalName(),x.getUpdateDate()).get(0).getDonationNeeds();
        
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
                specificHospitalAction();
                HospitalManagementGeneralController.nbr5=0;
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
       
    private void deleteRow(HospitalRegistration x)
    {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION DIALOG");
        alert.setContentText("Are you sure to delete ?");
        Optional <ButtonType> action= alert.showAndWait();
        
        if (action.get()==ButtonType.OK)
            {try
                {
                    String hname=x.getHospitalName();
                    String ud=x.getUpdateDate();
                Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
                String delete="Delete from HOSPITALS where HOSPITALNAME='"+hname+"' and DATEUPDATE=TO_DATE('"+ud+"')";
                    
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
                       String ch=specificHospital.getSelectionModel().getSelectedItem();
                       remplissage_comboboxHospitals();
                       Boolean trouve=false;
                       for (int i=0;i<specificHospital.getItems().size();i++)
                       { if (ch.equals(specificHospital.getItems().get(i)))
                            {specificHospital.getSelectionModel().select(ch);
                            trouve=true;
                            break;}
                       }
                       if (!trouve)
                           specificHospital.getSelectionModel().select("All Hospitals");
                       
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
    
    private ObservableList<HospitalRegistration> remplissageDetails(String hospitalName, String dateUpdate)
    {try{    
    Register_SignInForms.ConnexionDB con=new Register_SignInForms.ConnexionDB();
    String data="SELECT SUPPLYNEEDS, EQUIPMENTNEEDS, DONATIONSNEEDS FROM HOSPITALS WHERE HOSPITALNAME='"+hospitalName+"' and DATEUPDATE=TO_DATE('"+dateUpdate+"')";    
    ResultSet rsc=con.getStmt().executeQuery(data);
        myDetails.clear();
        if (rsc.next())           
        { myDetails.add(new HospitalRegistration (rsc.getString("SUPPLYNEEDS"),rsc.getString("EQUIPMENTNEEDS"),rsc.getString("DONATIONSNEEDS")));       
        }  
        con.getStmt().close();
        con.getConDB().close();
    }catch (Exception e){
           Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
       }
    return myDetails;
    
            
    }
    
    
    @FXML
    private void resetDate_MouseClicked()
    {specificDate.setValue(null);
    }   
    
    @FXML
    private void specificDateAction()
    {String sDate="";
        if (specificDate.getValue()!=null)
            sDate = specificDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        loadData(specificHospital.getSelectionModel().getSelectedItem(),sDate);

    }
    
     @FXML
    private void specificHospitalAction()
    {String sDate="";
        if (specificDate.getValue()!=null)
            sDate = specificDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        loadData(specificHospital.getSelectionModel().getSelectedItem(),sDate);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        hospital_nameCol.setCellValueFactory(new PropertyValueFactory("hospitalName"));
        hospitalized_casesCol.setCellValueFactory(new PropertyValueFactory("hospitalizedCases"));
        available_bedsCol.setCellValueFactory(new PropertyValueFactory("availableBeds"));
        doctors_numberCol.setCellValueFactory(new PropertyValueFactory("doctorsNumber"));
        rooms_numberCol.setCellValueFactory(new PropertyValueFactory("roomsNumber"));
        total_testsCol.setCellValueFactory(new PropertyValueFactory("totalTests"));
        city_nameCol.setCellValueFactory(new PropertyValueFactory("cityName")); 
        date_updateCol.setCellValueFactory(new PropertyValueFactory("updateDate"));      
        editCol.setCellValueFactory(new PropertyValueFactory("editHospital"));    
        deleteCol.setCellValueFactory(new PropertyValueFactory("deleteHospital"));
        detailsCol.setCellValueFactory(new PropertyValueFactory("detailsHospital"));
     
        remplissage_comboboxHospitals();  
        specificHospital.getSelectionModel().select("All Hospitals");
        loadData(specificHospital.getSelectionModel().getSelectedItem(),"");
      
}
    
    
}
