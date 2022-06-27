/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserPanel.StatisticsTunis;


import Register_SignInForms.ConnexionDB;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author selmi
 */
public class StatisticsTController implements Initializable {

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
    RadioButton tc ;
    @FXML
    RadioButton td ;
    @FXML
    RadioButton tr ;
    @FXML
    RadioButton tsc ;
    @FXML
    RadioButton nc ;
    @FXML
    RadioButton nd ;
    @FXML
    RadioButton ac ;
    @FXML
    RadioButton dvr ;
    @FXML
    RadioButton dis ;
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // add radio button to toggleGroup
        ToggleGroup tg=new ToggleGroup() ;
        tc.setToggleGroup(tg);
        td.setToggleGroup(tg);
        tr.setToggleGroup(tg);
        tsc.setToggleGroup(tg);
        ac.setToggleGroup(tg);
        nc.setToggleGroup(tg);
        nd.setToggleGroup(tg);
        dvr.setToggleGroup(tg);
        dis.setToggleGroup(tg);
        //set graphs 
        tg.selectedToggleProperty().addListener(
   (observable, oldToggle, newToggle) -> {
       if(tc.isSelected()){
           TotalCases() ;
       }
       if(td.isSelected()){
           TotalDeaths();
       }
       if(tr.isSelected()){
           TotalRecovered(); 
       }
       if(tsc.isSelected()){
           TotalSeriousCritical();
       }
       if(ac.isSelected()){
           TotalActiveCases();
       }
       if(nc.isSelected()){
           NewCases();
       }
       if(nd.isSelected()){
           NewDeaths();
       }
       if(dis.isSelected()){
          Distribution();
       }
       
       if(dvr.isSelected()){
           DeathsVSRecovered() ;
       }
    }
);
     }
    
    
    
    
    
    
    
    
    
    
    

    private void TotalSeriousCritical(){
                final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final AreaChart<String,Number> ac = 
            new AreaChart<>(xAxis,yAxis);
        ac.setTitle("Total Serious Critical");
 
        XYChart.Series seriesApril= new XYChart.Series();
        seriesApril.setName("April");
        try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                sql="select sum(SERIOUSCRITICAL),to_char(dateupdate,'dd-month'),dateupdate " +
                    "from coronaTunis " +
                    "group by dateupdate " +    
                    "order by dateupdate " ;
        
                ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                    seriesApril.getData().add(new XYChart.Data(rs.getString(2),rs.getDouble(1)));
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        Scene scene  = new Scene(ac,800,600);
        ac.getData().addAll(seriesApril);
        ac.setPrefSize(500, 500) ;
        ac.setMinSize(500, 500) ;
        ac.setMaxSize(500, 500) ;
        hb2.getChildren().setAll(ac) ;
    }
    private void TotalActiveCases(){
                final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final AreaChart<String,Number> ac = 
            new AreaChart<>(xAxis,yAxis);
        ac.setTitle("Total Active Cases");
 
        XYChart.Series seriesApril= new XYChart.Series();
        seriesApril.setName("2020");
        try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select sum(ACTIVECASES),to_char(dateupdate,'dd-month'),dateupdate " +
                    "from coronaTunis " +
                    "group by dateupdate " +    
                    "order by dateupdate " ;
        
                ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                    seriesApril.getData().add(new XYChart.Data(rs.getString(2),rs.getDouble(1)));
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        Scene scene  = new Scene(ac,800,600);
        ac.getData().addAll(seriesApril);
        ac.setPrefSize(500, 500) ;
        ac.setMinSize(500, 500) ;
        ac.setMaxSize(500, 500) ;
        hb2.getChildren().setAll(ac) ;
    }
    private void TotalDeaths(){
                final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final AreaChart<String,Number> ac = 
            new AreaChart<>(xAxis,yAxis);
        ac.setTitle("Total Deaths");
 
        XYChart.Series seriesApril= new XYChart.Series();
        seriesApril.setName("2020");
        try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select sum(TOTALDEATHS),to_char(dateupdate,'dd-month'),dateupdate " +
                    "from coronaTunis " +
                    "group by dateupdate " +    
                    "order by dateupdate " ;
        
                ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                    seriesApril.getData().add(new XYChart.Data(rs.getString(2),rs.getDouble(1)));
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        Scene scene  = new Scene(ac,800,600);
        ac.setPrefSize(500, 500) ;
        ac.setMinSize(500, 500) ;
        ac.setMaxSize(500, 500) ;
        ac.getData().addAll(seriesApril);
        hb2.getChildren().setAll(ac) ;
    }
    
    private void NewDeaths(){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final AreaChart<String,Number> ac = 
            new AreaChart<>(xAxis,yAxis);
        ac.setTitle("Daily Deaths");
 
        XYChart.Series seriesApril= new XYChart.Series();
        seriesApril.setName("2020");
        try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select sum(NEWDEATHS),to_char(dateupdate,'dd-month'),dateupdate " +
                    "from coronaTunis " +
                    "group by dateupdate " +    
                    "order by dateupdate " ;
        
                ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                    seriesApril.getData().add(new XYChart.Data(rs.getString(2),rs.getDouble(1)));
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        Scene scene  = new Scene(ac,800,600);
        ac.getData().addAll(seriesApril);
        ac.setPrefSize(500, 500) ;
        ac.setMinSize(500, 500) ;
        ac.setMaxSize(500, 500) ;
        hb2.getChildren().setAll(ac) ;
    }
    private void NewCases(){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final AreaChart<String,Number> ac = 
            new AreaChart<>(xAxis,yAxis);
        ac.setTitle("Daily cases");
        ac.setPrefSize(500, 500) ;
        ac.setMinSize(500, 500) ;
        ac.setMaxSize(500, 500) ;
        
        XYChart.Series seriesApril= new XYChart.Series();
        seriesApril.setName("2020");
        try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select sum(NEWCASES),to_char(dateupdate,'dd-month'),dateupdate " +
                    "from coronaTunis " +
                    "group by dateupdate " +    
                    "order by dateupdate " ;
        
                ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                    seriesApril.getData().add(new XYChart.Data(rs.getString(2),rs.getDouble(1)));
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        Scene scene  = new Scene(ac,800,600);

        ac.setPrefSize(500, 500) ;
        ac.setMinSize(500, 500) ;
        ac.setMaxSize(500, 500) ;
        ac.getData().addAll(seriesApril);
        hb2.getChildren().setAll(ac) ;
    }
    
    private void Distribution(){
        ArrayList<PieChart.Data> data=new ArrayList<PieChart.Data>() ;
        try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select city,TotalCases from coronaTunis " 
                            + "where to_char(dateupdate,'dd-mm-yyyy') = to_char(sysdate,'dd-mm-yyyy') " ;
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
        chart.setTitle("Distribution cases");
        chart.setLabelLineLength(10);
        //chart.setLegendSide(Side.LEFT);
        for (PieChart.Data d : chart.getData()){
            d.nameProperty().set(d.getName()+ " : "+(int)d.getPieValue()+ " cases");
            d.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    JOptionPane.showMessageDialog(null,"Country -- "+ d.getName());
                }});
        }
        
        chart.setPrefSize(500, 500) ;
        chart.setMinSize(500, 500) ;
        chart.setMaxSize(500, 500) ;
        hb2.getChildren().setAll(chart) ;
    }
    
    
    
    
    private void TotalRecovered(){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final AreaChart<String,Number> ac = 
            new AreaChart<>(xAxis,yAxis);
        ac.setTitle("Total Recovered");
 
        XYChart.Series seriesApril= new XYChart.Series();
        seriesApril.setName("2020");
        try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    
                    sql="select sum(totalcases),to_char(dateupdate,'dd-month'),dateupdate " +
                    "from coronaTunis " +
                    "group by dateupdate " +    
                    "order by dateupdate " ;
                ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                    seriesApril.getData().add(new XYChart.Data(rs.getString(2),rs.getDouble(1)));
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        Scene scene  = new Scene(ac,800,600);
        ac.getData().addAll(seriesApril);
        ac.setPrefSize(500, 500) ;
        ac.setMinSize(500, 500) ;
        ac.setMaxSize(500, 500) ;
        hb2.getChildren().setAll(ac) ;
    }
    
    
    private void TotalCases() {
         //defining the axes
        final CategoryAxis  xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("");
        //creating the chart
        final LineChart<String,Number> ac = 
              new LineChart<String,Number>(xAxis,yAxis);
        ac.setTitle("Total Cases");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        //populating the series with data
        try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select sum(totalcases),to_char(dateupdate,'dd-month'),dateupdate " +
                    "from coronaTunis " +
                    "group by dateupdate " +    
                    "order by dateupdate " ;
                ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                    series.getData().add(new XYChart.Data(rs.getString(2),rs.getDouble(1)));
                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        Scene scene  = new Scene(ac,800,600);
        ac.getData().add(series) ;
        ac.setPrefSize(500, 500) ;
        ac.setMinSize(500, 500) ;
        ac.setMaxSize(500, 500) ;
        hb2.getChildren().setAll(ac); 
    }

    private void DeathsVSRecovered() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<>(xAxis,yAxis);
        bc.setTitle("Deaths VS Recovered");
        xAxis.setLabel("Status");       
        yAxis.setLabel("Cases");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2020");  
         try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select sum(TotalDeaths),sum(TotalRecovered) from coronaTunis "
                            + "where to_char(dateupdate,'dd-mm-yyyy') = to_char(sysdate,'dd-mm-yyyy') " ;
                    ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                if(rs.next()){
                     series1.getData().add(new XYChart.Data("Deaths",rs.getDouble(1)));
                     series1.getData().add(new XYChart.Data("Recovered",rs.getDouble(2)));
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
