/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.TunisiaGovernorateManagement.SickPeopleManagement;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author hp
 */
public class PersonRegistration {
    private String cin;
    private String first_name;
    private String last_name;
    private String gender;
    private String age;
    private String city;
    private String addDate;
    private String updateDate;
    private String statusCorona;
    private String foreignCase;
    private ImageView edit;  
    private Button editPerson;
    
    public PersonRegistration()
    {cin="";
    first_name="";
    last_name="";
    age="";
    city="";
    addDate="";
    updateDate="";
    statusCorona="";
        
    }
    
    public PersonRegistration(String c,String f,String l,String g,String age, String city, String s,String fc,String add,String update)
    {cin=c;
    first_name=f;
    last_name=l;
    gender=g;
    this.age=age;
    this.city=city;   
    statusCorona=s;
    foreignCase=fc;
    addDate=add;     
    updateDate=update;
    
    ImageView image1=new ImageView (new Image(getClass().getResourceAsStream("../../../images/editTunisia-100.png")));
    edit=image1;
    edit.setFitWidth(45);
    edit.setFitHeight(40);
    
    editPerson=new Button();
    editPerson.setGraphic(edit);
    editPerson.setStyle("-fx-background-color:TRANSPARENT;");
    editPerson.setCursor(Cursor.HAND);
        
    }

    /**
     * @return the cin
     */
    public String getCin() {
        return cin;
    }

    /**
     * @param cin the cin to set
     */
    public void setCin(String cin) {
        this.cin = cin;
    }

    /**
     * @return the first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return the last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    
     /**
     * @return the addDate
     */
    public String getAddDate() {
        return addDate;
    }

    /**
     * @param addDate the addDate to set
     */
    public void setAddDate(String addDate) {
        this.addDate = addDate;
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
     * @return the statusCorona
     */
    public String getStatusCorona() {
        return statusCorona;
    }

    /**
     * @param statusCorona the statusCorona to set
     */
    public void setStatusCorona(String statusCorona) {
        this.statusCorona = statusCorona;
    }

    /**
     * @return the foreignCase
     */
    public String getForeignCase() {
        return foreignCase;
    }

    /**
     * @param foreignCase the foreignCase to set
     */
    public void setForeignCase(String foreignCase) {
        this.foreignCase = foreignCase;
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
     * @return the editPerson
     */
    public Button getEditPerson() {
        return editPerson;
    }

    /**
     * @param editPerson the editPerson to set
     */
    public void setEditPerson(Button editPerson) {
        this.editPerson = editPerson;
    }

   
}