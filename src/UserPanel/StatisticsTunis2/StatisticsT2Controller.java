/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserPanel.StatisticsTunis2 ;

import Register_SignInForms.ConnexionDB;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author selmi
 */
public class StatisticsT2Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    AnchorPane p ;
    @FXML
    HBox hb2 ;
    @FXML
    HBox hb ;
    @FXML
    VBox vb ;
    @FXML
    RadioButton gender ;
    @FXML
    RadioButton age ;
    @FXML
    RadioButton foreign ;
    @FXML
    RadioButton cases ;
    @FXML
    RadioButton BedsAv ;
    @FXML
    RadioButton HospitalCases ;
    @FXML
    RadioButton HospitalInfo ;
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // add radio button to toggleGroup
        ToggleGroup tg=new ToggleGroup() ;
        gender.setToggleGroup(tg);
        age.setToggleGroup(tg);
        foreign.setToggleGroup(tg);
        cases.setToggleGroup(tg);
        BedsAv.setToggleGroup(tg);
        HospitalCases.setToggleGroup(tg);
        HospitalInfo.setToggleGroup(tg);
        //set graphs 
        tg.selectedToggleProperty().addListener(
   (observable, oldToggle, newToggle) -> {
       if(gender.isSelected()){
           GenderStat() ;
       }
       if(age.isSelected()){
           AgeStat() ;
       }
       if(foreign.isSelected()){
           ForeignStat() ; 
       }
       if(cases.isSelected()){
           CasesStat();
       }
       if(BedsAv.isSelected()){
           BedsAv();
       }
       if(HospitalCases.isSelected()){
           HospitalCases() ;
       }
       if(HospitalInfo.isSelected()){
           HospitalInfo() ;
       }
    }
);
    }

    private void GenderStat() {
             ArrayList<PieChart.Data> data=new ArrayList<PieChart.Data>() ;
        try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select gender,count(gender) from Person group by gender " ;
                ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                   data.add(new PieChart.Data(rs.getString(1),rs.getDouble(2))) ;
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(data);
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Tunis Gender Statistics ");
        chart.setLabelLineLength(10);
        //chart.setLegendSide(Side.LEFT);
        for (PieChart.Data d : chart.getData()){
            d.nameProperty().set(d.getName()+ " : "+(int)d.getPieValue()+ " cases");
            
        }
        chart.setPrefSize(500, 500) ;
        chart.setMinSize(500, 500) ;
        chart.setMaxSize(500, 500) ;
        hb2.getChildren().setAll(chart) ;

    }

    private void AgeStat() {
         final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<>(xAxis,yAxis);
        bc.setTitle("Tunis Age Statistics");
        xAxis.setLabel("Age");       
        yAxis.setLabel("Cases");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2020");  
         try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select Age,count(Age) from person group by Age order by Age ";
                    ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                     series1.getData().add(new XYChart.Data(String.valueOf(rs.getInt(1)) ,rs.getInt(2)));
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        bc.setPrefSize(500, 500) ;
        bc.setMinSize(500, 500) ;
        bc.setMaxSize(500, 500) ;
        hb2.getChildren().setAll(bc) ;
    }

    private void ForeignStat() {        
             ArrayList<PieChart.Data> data=new ArrayList<PieChart.Data>() ;
        try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select foreigncase  ,count(foreigncase) from person group by foreigncase " ;
                ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                    if(rs.getString(1)=="Y"){
                   data.add(new PieChart.Data("imported cases",rs.getInt(2))) ;
                    }
                    else{
                   data.add(new PieChart.Data("Domestic cases",rs.getInt(2))) ;
                    }
                    
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(data);
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Tunis Imported/Domestic Cases ");
        chart.setLabelLineLength(10);
        //chart.setLegendSide(Side.LEFT);
        for (PieChart.Data d : chart.getData()){
            d.nameProperty().set(d.getName()+ " : "+(int)d.getPieValue()+ " cases");
            
        }
        chart.setPrefSize(500, 500) ;
        chart.setMinSize(500, 500) ;
        chart.setMaxSize(500, 500) ;
        hb2.getChildren().setAll(chart) ;

    }
    
    
    private void CasesStat() {
         final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<>(xAxis,yAxis);
        bc.setTitle("Tunis Cases Statistics");
        xAxis.setLabel("States");       
        yAxis.setLabel("Cases");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2020");  
         try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select Statuscorona,count(Statuscorona) from person group by Statuscorona ";
                    ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                     series1.getData().add(new XYChart.Data(rs.getString(1) ,rs.getInt(2)));
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        bc.setPrefSize(500, 500) ;
        bc.setMinSize(500, 500) ;
        bc.setMaxSize(500, 500) ;
        hb2.getChildren().setAll(bc) ;
    }

    private void BedsAv() {
             ArrayList<PieChart.Data> data=new ArrayList<PieChart.Data>() ;
        try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select hospitalname , bedsAvailable from hospitals "
                            + "where to_char(dateupdate,'dd-mm-yyyy') = to_char(sysdate,'dd-mm-yyyy') " ;
                ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                        data.add(new PieChart.Data(rs.getString(1),rs.getInt(2))) ;
                    
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(data);
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Tunis Beds Available ");
        chart.setLabelLineLength(10);
        //chart.setLegendSide(Side.LEFT);
        for (PieChart.Data d : chart.getData()){
            d.nameProperty().set(d.getName()+ " : "+(int)d.getPieValue()+ " beds");
            
        }
        chart.setPrefSize(500, 500) ;
        chart.setMinSize(500, 500) ;
        chart.setMaxSize(500, 500) ;
        hb2.getChildren().setAll(chart) ;

    }

    private void HospitalCases() {
             final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<>(xAxis,yAxis);
        bc.setTitle("Tunis Hospital Cases");
        xAxis.setLabel("Hospital Name");       
        yAxis.setLabel("Cases");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2020");  
         try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select hospitalname , hospitalizedcases from hospitals "
                            + "where to_char(dateupdate,'dd-mm-yyyy') = to_char(sysdate,'dd-mm-yyyy')  ";
                    ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                     series1.getData().add(new XYChart.Data(rs.getString(1) ,rs.getInt(2)));
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        bc.setPrefSize(500, 500) ;
        bc.setMinSize(500, 500) ;
        bc.setMaxSize(500, 500) ;
        hb2.getChildren().setAll(bc) ;
    
    }

    private void HospitalInfo() {
             final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<>(xAxis,yAxis);
        bc.setTitle("Tunis Hospital States");
        xAxis.setLabel("Hospital Info");       
        yAxis.setLabel("Number");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2020");  
         try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select doctorsnumber ,Roomsnumber,totaltests from hospitals "
                            + "where to_char(dateupdate,'dd-mm-yyyy') = to_char(sysdate,'dd-mm-yyyy')   ";
                    ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                     series1.getData().add(new XYChart.Data("Doctors" ,rs.getInt(1)));
                     series1.getData().add(new XYChart.Data("Rooms" ,rs.getInt(2)));
                     series1.getData().add(new XYChart.Data("Total Tests" ,rs.getInt(3)));
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        bc.setPrefSize(500, 500) ;
        bc.setMinSize(500, 500) ;
        bc.setMaxSize(500, 500) ;
        hb2.getChildren().setAll(bc) ;
    
        
    }
}
