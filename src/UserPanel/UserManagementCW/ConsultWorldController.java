/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserPanel.UserManagementCW;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Register_SignInForms.ConnexionDB;
/**
 * FXML Controller class
 *
 * @author hp
 */
public class ConsultWorldController implements Initializable {

    private String Buttonselected  ;//wich button is selected between world and Tunis governement 
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ImageView exit;
    @FXML
    private TableView<CoronaInfo> mytable_view;
    @FXML
    private TableColumn country;
    @FXML
    private TableColumn TotalCases;
    @FXML
    private TableColumn NewCases;
    @FXML
    private TableColumn TotalDeaths;
    @FXML
    private TableColumn NewDeaths;
    @FXML
    private TableColumn TotalRecovered;
    @FXML
    private TableColumn ActiveCases;
    @FXML
    private TableColumn SeriousCritical;
    @FXML
    private TableColumn TotalTests;
    @FXML
    private ComboBox cmb_search ;
    @FXML
    private Label Lab_title ;
    @FXML
    private Button btn_world ;
    @FXML
    private Button btn_TunisG ;
    @FXML
    private ImageView btn_print ;
    @FXML
    private DatePicker date;
    @FXML 
    private AnchorPane ap;
    @FXML
     private void exitMouseEntered() {
       exit.setLayoutY(15);
    }
     
     @FXML
     private void exitMouseExited() {
        exit.setLayoutY(10);
    }
    
    @FXML
     private void exitMouseClicked() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
     
     
     
         protected void extractInfo(String CountryName){//extract world's informmation from the database
         try{
        //create connexionDb variable
        ConnexionDB cnx=new ConnexionDB() ;
        String sql ;
        String dateV = date.getValue().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));//get the current datepicker value 
        if(CountryName=="World"){
            sql="select * from CoronaWorld where to_char(DateUpdate,'YYYY-MM-DD') ='"+dateV+"'" ;
        }else{
            sql="select * from CoronaWorld where country='"+CountryName+"' and  to_char(DateUpdate,'YYYY-MM-DD') ='"+dateV+"'" ;
        }
        ResultSet rs=null;
        rs=cnx.getStmt().executeQuery(sql) ;//execute select query
        while(rs.next()){
            //create an instance of CoranaInfo to represent a row from the returned result 
                    CoronaInfo row = new CoronaInfo(rs.getString(1),rs.getDouble(2),rs.getDouble(3),rs.getDouble(4),rs.getDouble(5),
                    rs.getDouble(6),rs.getDouble(7),rs.getDouble(8),rs.getDouble(9));
                    mytable_view.getItems().add(row) ;
   
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
    
        protected void extractAllCountyName(){//extract world's informmation from the database
        try{
        //create connexionDb variable
        ConnexionDB cnx=new ConnexionDB() ;
        String sql="select country from CoronaWorld" ;
        ResultSet rs=null;
        rs=cnx.getStmt().executeQuery(sql) ;//execute select query
        while(rs.next()){
           //Country.addItem(rs.getString(1));
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
    
    
    
    protected void extractInfoTunis(String cityName){//extract world's informmation from the database
        try{
        //create connexionDb variable
        ConnexionDB cnx=new ConnexionDB() ;
        String sql ;
        String dateV = date.getValue().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));//get the current datepicker value 
        if(cityName=="All"){
            sql="select * from CoronaTunis where to_char(DateUpdate,'YYYY-MM-DD') ='"+dateV+"'" ;
        }else{
            sql="select * from CoronaTunis where city='"+cityName+"' and to_char(DateUpdate,'YYYY-MM-DD') ='"+dateV+"'" ;
        }
        ResultSet rs=null;
        rs=cnx.getStmt().executeQuery(sql) ;//execute select query
        while(rs.next()){
            //create an instance of CoranaInfo to represent a row from the returned result 
                    CoronaInfo row = new CoronaInfo(rs.getString(1),rs.getDouble(2),rs.getDouble(3),rs.getDouble(4),rs.getDouble(5),
                    rs.getDouble(6),rs.getDouble(7),rs.getDouble(8),rs.getDouble(9));
                    mytable_view.getItems().add(row) ;
    
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
    
    
        protected void extractAllCityName(){//extract Tunis informmation from the database
        try{
        //create connexionDb variable
        ConnexionDB cnx=new ConnexionDB() ;
        String sql="select city from CoronaTunis" ;
        ResultSet rs=null;
        rs=cnx.getStmt().executeQuery(sql) ;//execute select query
        while(rs.next()){
           //Country.addItem(rs.getString(1));
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

     
     protected void initializeColumns(){
         //assign each column its equivalent in the CoronaInfo class
        country.setCellValueFactory(new PropertyValueFactory("country"));
        TotalCases.setCellValueFactory(new PropertyValueFactory("Tc"));
        NewCases.setCellValueFactory(new PropertyValueFactory("Nc"));
        TotalDeaths.setCellValueFactory(new PropertyValueFactory("Td"));
        NewDeaths.setCellValueFactory(new PropertyValueFactory("Nd"));
        TotalRecovered.setCellValueFactory(new PropertyValueFactory("Td"));
        ActiveCases.setCellValueFactory(new PropertyValueFactory("Ac"));
        SeriousCritical.setCellValueFactory(new PropertyValueFactory("Sc"));
        TotalTests.setCellValueFactory(new PropertyValueFactory("Tt")) ;
     }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {//l'initialisation de tous les contrôles du window
        // TODO
        initializeColumns() ;
        //the first thing that will be displayed is the world informations 
        Buttonselected="World" ;
        //set default value datepicker = current date
        date.setValue(LocalDate.now());
        //add a first item to the combobox and make it default item
        cmb_search.getItems().addAll("World") ;
        cmb_search.getSelectionModel().selectFirst();
        //fill the combobox with the name of all countries exist in database  
        extractAllCountyName();
        extractInfo(cmb_search.getSelectionModel().getSelectedItem().toString());//get the selected item of cmb_search
        btn_world.setStyle("-fx-background-color: #db8484;");

    }    
    
    
    /**
     * search button
     * 
     **/
     @FXML
        private void btn_SearchClick(ActionEvent event){
        if(Buttonselected=="World"){
        mytable_view.getItems().clear();//clear the table view
        extractInfo(cmb_search.getSelectionModel().getSelectedItem().toString());
       }else if(Buttonselected=="Tunis"){
        mytable_view.getItems().clear();//clear the table view
        extractInfoTunis(cmb_search.getSelectionModel().getSelectedItem().toString());//fill the JTable1 with tunis information
       }
     } 
     
     /**
      * Tunis governement button
      * 
      **/
     @FXML
     private void btn_TunisGClick(ActionEvent event){
         
        mytable_view.getItems().clear();//clear the table view
        cmb_search.getItems().clear();//removing all items from combobox
        //add a first item to the combobox and make it default item
        cmb_search.getItems().addAll("All");//to desplay all the tunis city information
        cmb_search.getSelectionModel().selectFirst();
        country.setText("City");//change country text
        extractAllCityName();//fill the combobox with tunis city name
        extractInfoTunis("All");//fill the table with tunis information
        Lab_title.setText("choose a specific city");//change Label text
        Buttonselected="Tunis" ;//specify the button selected for the search button
        //change button color 
        btn_TunisG.setStyle("-fx-background-color: #db8484;");
        btn_world.setStyle("-fx-background-color: #ffd2d2;");
        
       }
     
     
     
     /**
      * World button
      * 
      **/
     @FXML
     private void btn_worldClick(ActionEvent event){
        mytable_view.getItems().clear();//clear the table view
        cmb_search.getItems().clear();//removing all items from combobox
        //add a first item to the combobox and make it default item
        cmb_search.getItems().addAll("World");//to desplay all the world country information
        cmb_search.getSelectionModel().selectFirst();
        country.setText("Country");//change country text
        extractAllCountyName();//fill the combobox with world name
        extractInfo("World");//fill the table with world information
        Lab_title.setText("choose a specific country");//change Label text
        Buttonselected="World" ;//specify the button selected for the search button
        //change button color
        btn_world.setStyle("-fx-background-color: #db8484;");
        btn_TunisG.setStyle("-fx-background-color: #ffd2d2;");
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


