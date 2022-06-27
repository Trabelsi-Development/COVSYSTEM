/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.HospitalManagement;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author hp
 */
public class HospitalRegistration {
      private String hospitalName;
    private String hospitalizedCases;
    private String availableBeds;
    private String doctorsNumber;
    private String roomsNumber;
    private String totalTests;    
    private String supplyNeeds;
    private String equipmentNeeds;
    private String donationNeeds;
    private String cityName;
    private String updateDate;
    private ImageView edit;    
    private ImageView delete;
    private Button editHospital;
    private Button deleteHospital;    
    private Button detailsHospital;

    public HospitalRegistration()    
    {hospitalName="";
    hospitalizedCases="";
    availableBeds="";
    doctorsNumber="";
    roomsNumber="";
    totalTests="";
    supplyNeeds="";
    equipmentNeeds="";
    donationNeeds="";
    cityName="";
    updateDate="";
    
    }
    
    public HospitalRegistration(String hn, String hc,String ab, String dn,String rn,String tt,String cn,String uD)
    {
    hospitalName=hn;
    hospitalizedCases=hc;
    availableBeds=ab;
    doctorsNumber=dn;
    roomsNumber=rn;
    totalTests=tt;
    cityName=cn;
    updateDate=uD;
    ImageView image1=new ImageView (new Image(getClass().getResourceAsStream("../../images/editHospital-100.png")));
    ImageView image2=new ImageView (new Image(getClass().getResourceAsStream("../../images/deleteHospital-100.png")));
    edit=image1;
    edit.setFitWidth(45);
    edit.setFitHeight(40);
    
    delete=image2;  
    delete.setFitWidth(45);
    delete.setFitHeight(40);
    
    editHospital=new Button();
    editHospital.setGraphic(edit);
    editHospital.setStyle("-fx-background-color:TRANSPARENT;");
    editHospital.setCursor(Cursor.HAND);
    
    deleteHospital=new Button();
    deleteHospital.setGraphic(delete);
    deleteHospital.setStyle("-fx-background-color:TRANSPARENT;");
    deleteHospital.setCursor(Cursor.HAND);
    
    detailsHospital=new Button();    
    detailsHospital.setStyle("-fx-background-color:  #F27C7C; -fx-background-radius: 10; -fx-text-fill:white; -fx-font-family:System; -fx-font-size:13px; -fx-font-weight:bold;");   
    detailsHospital.setText("Consult details");
    detailsHospital.setCursor(Cursor.HAND);
    }
    
    public HospitalRegistration(String sn, String en,String dn)
    {supplyNeeds=sn;
    equipmentNeeds=en;
    donationNeeds=dn;
    }

    /**
     * @return the hospitalName
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     * @param hospitalName the hospitalName to set
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    /**
     * @return the hospitalizedCases
     */
    public String getHospitalizedCases() {
        return hospitalizedCases;
    }

    /**
     * @param hospitalizedCases the hospitalizedCases to set
     */
    public void setHospitalizedCases(String hospitalizedCases) {
        this.hospitalizedCases = hospitalizedCases;
    }

    /**
     * @return the availableBeds
     */
    public String getAvailableBeds() {
        return availableBeds;
    }

    /**
     * @param availableBeds the availableBeds to set
     */
    public void setAvailableBeds(String availableBeds) {
        this.availableBeds = availableBeds;
    }

    /**
     * @return the doctorsNumber
     */
    public String getDoctorsNumber() {
        return doctorsNumber;
    }

    /**
     * @param doctorsNumber the doctorsNumber to set
     */
    public void setDoctorsNumber(String doctorsNumber) {
        this.doctorsNumber = doctorsNumber;
    }

    /**
     * @return the roomsNumber
     */
    public String getRoomsNumber() {
        return roomsNumber;
    }

    /**
     * @param roomsNumber the roomsNumber to set
     */
    public void setRoomsNumber(String roomsNumber) {
        this.roomsNumber = roomsNumber;
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
     * @return the supplyNeeds
     */
    public String getSupplyNeeds() {
        return supplyNeeds;
    }

    /**
     * @param supplyNeeds the supplyNeeds to set
     */
    public void setSupplyNeeds(String supplyNeeds) {
        this.supplyNeeds = supplyNeeds;
    }

    /**
     * @return the equipmentNeeds
     */
    public String getEquipmentNeeds() {
        return equipmentNeeds;
    }

    /**
     * @param equipmentNeeds the equipmentNeeds to set
     */
    public void setEquipmentNeeds(String equipmentNeeds) {
        this.equipmentNeeds = equipmentNeeds;
    }

    /**
     * @return the donationNeeds
     */
    public String getDonationNeeds() {
        return donationNeeds;
    }

    /**
     * @param donationNeeds the donationNeeds to set
     */
    public void setDonationNeeds(String donationNeeds) {
        this.donationNeeds = donationNeeds;
    }

    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
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
     * @return the edit
     */
    public ImageView getEdit() {
        return edit;
    }

    /**
     * @param edit the edit to set
     */
    public void setEdit(ImageView edit) {
        this.edit = edit;
    }

    /**
     * @return the delete
     */
    public ImageView getDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(ImageView delete) {
        this.delete = delete;
    }

    /**
     * @return the editHospital
     */
    public Button getEditHospital() {
        return editHospital;
    }

    /**
     * @param editHospital the editHospital to set
     */
    public void setEditHospital(Button editHospital) {
        this.editHospital = editHospital;
    }

    /**
     * @return the deleteHospital
     */
    public Button getDeleteHospital() {
        return deleteHospital;
    }

    /**
     * @param deleteHospital the deleteHospital to set
     */
    public void setDeleteHospital(Button deleteHospital) {
        this.deleteHospital = deleteHospital;
    }

    /**
     * @return the detailsHospital
     */
    public Button getDetailsHospital() {
        return detailsHospital;
    }

    /**
     * @param detailsHospital the detailsHospital to set
     */
    public void setDetailsHospital(Button detailsHospital) {
        this.detailsHospital = detailsHospital;
    }
    
    
}
