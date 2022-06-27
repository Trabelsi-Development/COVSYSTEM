/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserPanel.UserManagementCH;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import Register_SignInForms.ConnexionDB;


/**
 * FXML Controller class
 *
 * @author selmi
 */



public class ConsultHospitalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView exit2;
    //Hospital info Table view definition
    @FXML
    private TableView<HospitalsInfo> mytable_view;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn city;
    @FXML
    private TableColumn AvailableCases;
    @FXML
    private TableColumn AvailableBeds;
    @FXML
    private TableColumn Doctors;
    @FXML
    private TableColumn Rooms;
    @FXML
    private TableColumn TotalTests;
    @FXML
    private TableColumn Needs;
    @FXML
    private ComboBox cmb_search ;
    @FXML
    private Label Lab_title ;
    @FXML
    private DatePicker date;
    @FXML
    AnchorPane p ;
    //Needs Table view definition
    @FXML
    private TableView<Needs> Needs_view;
    @FXML
    private TableColumn SUPPLYNEEDS;
    @FXML
    private TableColumn EQUIPMENTNEEDS;
    @FXML
    private TableColumn DONATIONSNEEDS;
    @FXML
    private ImageView btn_print ;

     
     
     @FXML
     private void exit2MouseClicked() {
        Needs_view.setVisible(false);
        exit2.setVisible(false);
        
        mytable_view.setEffect(null);
        
    }
         protected void extractInfo(String HospitalName){//extract world's informmation from the database
        try{
        //create connexionDb variable
        ConnexionDB cnx=new ConnexionDB() ;
        String sql ;
        String dateV = date.getValue().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));//get the current datepicker value 
        if(HospitalName=="All"){
            sql="select * from Hospitals where to_char(DateUpdate,'YYYY-MM-DD') ='"+dateV+"'" ;
        }else{
            sql="select * from Hospitals where HospitalName='"+HospitalName +"' and  to_char(DateUpdate,'YYYY-MM-DD') ='"+dateV+"'" ;
        }
        ResultSet rs=null;
        rs=cnx.getStmt().executeQuery(sql) ;//execute select query
        while(rs.next()){
            //create an instance of HospitalsInfo to represent a row from the returned result 
                    HospitalsInfo row = new HospitalsInfo(rs.getString(1),rs.getString(5)
                            ,rs.getDouble(2),rs.getDouble(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6),ConsultButton(rs.getString(1))) ; ;
                    mytable_view.getItems().add(row);
   
        }
        //close connexion
           rs.close();
           cnx.getConDB().close();
           cnx.getStmt().close();
        }catch(SQLException e)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
               alert.setTitle("WARNING !");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
        }
    } 
    
        protected void extractAllHospitalName(){//extract world's informmation from the database
        try{
        //create connexionDb variable
        ConnexionDB cnx=new ConnexionDB() ;
        String sql="select HospitalName from Hospitals" ;
        ResultSet rs=null;
        rs=cnx.getStmt().executeQuery(sql) ;//execute select query
        while(rs.next()){
            if(! cmb_search.getItems().contains(rs.getString(1))){
                cmb_search.getItems().addAll(rs.getString(1)) ;
            }
        }
        //close connexion
           rs.close();
           cnx.getConDB().close();
           cnx.getStmt().close();
        }catch(SQLException e)
        {
           Alert alert=new Alert(Alert.AlertType.WARNING);
               alert.setTitle("WARNING !");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
        }
    }
           
      protected void extractInfoNeeds(String HospitalName){//extract Needs's informmation from the database
        try{
        //create connexionDb variable
        ConnexionDB cnx=new ConnexionDB() ;
        String sql ;
        sql="select EquipmentNeeds,SupplyNeeds,DonationsNeeds from Hospitals where HospitalName='"+HospitalName+"'" ;
        
        ResultSet rs=null;
        rs=cnx.getStmt().executeQuery(sql) ;//execute select query
        while(rs.next()){
                    Needs row = new Needs(rs.getString(1),rs.getString(2),rs.getString(3))  ;
                    Needs_view.getItems().add(row) ;
   }
           //close connexion
        // rs.close(); do not close it because it well throw "Closed ResultSet" exception  
           cnx.getConDB().close();
           cnx.getStmt().close();
        
    
        }catch(SQLException e)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
               alert.setTitle("WARNING !");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
        }
    } 
    

    
    
    
    
    
    
    protected void initializeColumns(){
         //assign each column its equivalent in the HospitalsInfo class
        name.setCellValueFactory(new PropertyValueFactory("Name"));
        AvailableCases.setCellValueFactory(new PropertyValueFactory("Hc"));
        AvailableBeds.setCellValueFactory(new PropertyValueFactory("Bav"));
        city.setCellValueFactory(new PropertyValueFactory("City"));
        Needs.setCellValueFactory(new PropertyValueFactory("btn"));
        //assign each column its equivalent in the HospitalsInfo class
        EQUIPMENTNEEDS.setCellValueFactory(new PropertyValueFactory("EQUIPMENTNEEDS"));
        DONATIONSNEEDS.setCellValueFactory(new PropertyValueFactory("DONATIONSNEEDS"));
        SUPPLYNEEDS.setCellValueFactory(new PropertyValueFactory("SUPPLYNEEDS"));
        TotalTests.setCellValueFactory(new PropertyValueFactory("Tt"));
        Doctors.setCellValueFactory(new PropertyValueFactory("nbrDoctor"));
        Rooms.setCellValueFactory(new PropertyValueFactory("nbrrooms"));
       }
    private Button ConsultButton(String HospitalName){
        Button btn=new Button() ;
        btn.setText("Consult needs");
        btn.setOnAction((ActionEvent event) -> {
            Needs_view.getItems().clear();
            DropShadow d=new DropShadow() ;
            Needs_view.setVisible(true);
            Needs_view.setEffect(d);
            exit2.setEffect(d);
            exit2.setVisible(true);
            Bloom b=new Bloom(0.2) ;
            mytable_view.setEffect(b);
            extractInfoNeeds(HospitalName);
            
        });
        return btn ;
    }
    
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Needs_view.setVisible(false);
        exit2.setVisible(false);
        initializeColumns(); 
        //the first thing that will be displayed is the world informations 
        //set default value datepicker = current date
        date.setValue(LocalDate.now());
        //add a first item to the combobox and make it default item
        cmb_search.getItems().addAll("All") ;
        cmb_search.getSelectionModel().selectFirst();
        //fill the combobox with the name of all countries exist in database  
        extractAllHospitalName();
        extractInfo(cmb_search.getSelectionModel().getSelectedItem().toString());//get the selected item of cmb_search

    }    
    
    @FXML   
    private void btn_SearchClick(ActionEvent event){
        mytable_view.getItems().clear();//clear the table view
        extractInfo(cmb_search.getSelectionModel().getSelectedItem().toString());
        
    } 
    
    @FXML
    private void btn_printMouseClicked() throws IOException{
    File filechooser = new FileChooser().showSaveDialog(mytable_view.getScene().getWindow());
    Writer writer = null;
    try {
        File file = new File(filechooser.getPath()+".txt");
        writer = new BufferedWriter(new FileWriter(file));
        String text = "County Total cases New cases Total deaths New deaths Total recovered active cases "
                + "Serious  Total tests"+"\n";
        writer.write(text);
        for (int i = 0; i < mytable_view.getItems().size(); i++) {
        for (int j = 0; j < mytable_view.getColumns().size(); j++) {
            
            text = mytable_view.getColumns().get(j).getCellData(i).toString()+"      ";
            writer.write(text);
        }
        writer.write("\n");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    finally {
       writer.flush();
       writer.close();
    } 
}

    
}