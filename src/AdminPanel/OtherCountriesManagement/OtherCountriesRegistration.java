/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.OtherCountriesManagement;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author hp
 */
public class OtherCountriesRegistration {
    private String countryName;
    private String totalCases;
    private String newCases;
    private String totalDeaths;
    private String NewDeaths;
    private String totalRecovered;
    private String activeCases;
    private String seriousCritical;
    private String totalTests;
    private String updateDate;
    private ImageView edit;    
    private ImageView delete;
    private Button editCountry;
    private Button deleteCountry;

    public OtherCountriesRegistration()    
    {countryName="";
    totalCases="";
    newCases="";
    totalDeaths="";
    NewDeaths="";
    totalRecovered="";
    activeCases="";
    seriousCritical="";
    totalTests="";
    updateDate="";
    
    }
    
    public OtherCountriesRegistration(String cn, String tc,String nc, String td,String nd,String tr,String ac,String sc,String tt,String uD)
    {
    countryName=cn;
    totalCases=tc;
    newCases=nc;
    totalDeaths=td;
    NewDeaths=nd;
    totalRecovered=tr;
    activeCases=ac;
    seriousCritical=sc;
    totalTests=tt;
    updateDate=uD;
    ImageView image1=new ImageView (new Image(getClass().getResourceAsStream("../../images/editWorld-100.png")));
    ImageView image2=new ImageView (new Image(getClass().getResourceAsStream("../../images/deleteWorld-100.png")));
    edit=image1;
    edit.setFitWidth(45);
    edit.setFitHeight(40);
    
     delete=image2;  
    delete.setFitWidth(45);
    delete.setFitHeight(40);
    
    editCountry=new Button();
    editCountry.setGraphic(edit);
    editCountry.setStyle("-fx-background-color:TRANSPARENT;");
    editCountry.setCursor(Cursor.HAND);
    deleteCountry=new Button();
    deleteCountry.setGraphic(delete);
    deleteCountry.setStyle("-fx-background-color:TRANSPARENT;");
    deleteCountry.setCursor(Cursor.HAND);
    }
    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the totalCases
     */
    public String getTotalCases() {
        return totalCases;
    }

    /**
     * @param totalCases the totalCases to set
     */
    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    /**
     * @return the newCases
     */
    public String getNewCases() {
        return newCases;
    }

    /**
     * @param newCases the newCases to set
     */
    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    /**
     * @return the totalDeaths
     */
    public String getTotalDeaths() {
        return totalDeaths;
    }

    /**
     * @param totalDeaths the totalDeaths to set
     */
    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    /**
     * @return the NewDeaths
     */
    public String getNewDeaths() {
        return NewDeaths;
    }

    /**
     * @param NewDeaths the NewDeaths to set
     */
    public void setNewDeaths(String NewDeaths) {
        this.NewDeaths = NewDeaths;
    }

    /**
     * @return the totalRecovered
     */
    public String getTotalRecovered() {
        return totalRecovered;
    }

    /**
     * @param totalRecovered the totalRecovered to set
     */
    public void setTotalRecovered(String totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    /**
     * @return the activeCases
     */
    public String getActiveCases() {
        return activeCases;
    }

    /**
     * @param activeCases the activeCases to set
     */
    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    /**
     * @return the seriousCritical
     */
    public String getSeriousCritical() {
        return seriousCritical;
    }

    /**
     * @param seriousCritical the seriousCritical to set
     */
    public void setSeriousCritical(String seriousCritical) {
        this.seriousCritical = seriousCritical;
    }

    /**
     * @return the totalTests
     */
    public String getTotalTests() {
        return totalTests;
    }

    /**
     * @param totalTests the totalTests to set
     */
    public void setTotalTests(String totalTests) {
        this.totalTests = totalTests;
    }

    /**
     * @return the updateDate
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return the editCountry
     */
    public Button getEditCountry() {
        return editCountry;
    }

    /**
     * @param editCountry the editCountry to set
     */
    public void setEditCountry(Button editCountry) {
        this.editCountry = editCountry;
    }

    /**
     * @return the deleteCountry
     */
    public Button getDeleteCountry() {
        return deleteCountry;
    }

    /**
     * @param deleteCountry the deleteCountry to set
     */
    public void setDeleteCountry(Button deleteCountry) {
        this.deleteCountry = deleteCountry;
    }
    
    
}
