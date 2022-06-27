/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.AdminManagement;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author hp
 */
public class AdminRegistration {
    private long IDAdmin;
    private String first_name;
    private String last_name;
    private String date_birth;
    private String gender;   
    private String profession;
    private String address;
    private String email;
    private String login;
    private String passwrd;
    private ImageView profile_picture;
    private ImageView edit;    
    private ImageView delete;
    private Button ed;
    private Button dell;
    
    /**
     *
     */
    public AdminRegistration () //constructeur non paramétré
    {IDAdmin=0;
    first_name="";
    last_name="";
    date_birth="";
    gender="";
    profession="";
    address="";
    email="";
    login="";
    passwrd="";  
    profile_picture=new ImageView();
    edit = new ImageView();
    delete=new ImageView();
       
    }

    
    public AdminRegistration (long id, String f, String l, String d, String g, String p, String a, String e,String log, String pwd, ImageView pic) //constructeur paramétré,
    {IDAdmin=id;
    first_name=f;
    last_name=l;
    date_birth=d;
    gender=g;
    profession=p;
    address=a;
    email=e;
    login=log;
    passwrd=pwd; 
    profile_picture=pic;
    profile_picture.setFitWidth(70);
    profile_picture.setFitHeight(50);
    ImageView image1=new ImageView (new Image(getClass().getResourceAsStream("../../images/edit-100.png")));
    ImageView image2=new ImageView (new Image(getClass().getResourceAsStream("../../images/delete-100.png")));
    edit=image1;
    edit.setFitWidth(45);
    edit.setFitHeight(40);
    
     delete=image2;  
    delete.setFitWidth(45);
    delete.setFitHeight(40);
    
    ed=new Button();
    ed.setGraphic(edit);
    ed.setStyle("-fx-background-color:TRANSPARENT;");
    ed.setCursor(Cursor.HAND);
    dell=new Button();
    dell.setGraphic(delete);
    dell.setStyle("-fx-background-color:TRANSPARENT;");
    dell.setCursor(Cursor.HAND);
    
    
    }

    /**
     * @return the IDAdmin
     */
    public long getIDAdmin() {
        return IDAdmin;
    }

    /**
     * @param IDAdmin the IDAdmin to set
     */
    public void setIDAdmin(long IDAdmin) {
        this.IDAdmin = IDAdmin;
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
     * @return the date_birth
     */
    public String getDate_birth() {
        return date_birth;
    }

    /**
     * @param date_birth the date_birth to set
     */
    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
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
     * @return the profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * @param profession the profession to set
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the passwrd
     */
    public String getPasswrd() {
        return passwrd;
    }

    /**
     * @param passwrd the passwrd to set
     */
    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }  
    
      /**
     * @return the profile_picture
     */
    public ImageView getProfile_picture() {
        return profile_picture;
    }

    /**
     * @param profile_picture the profile_picture to set
     */
    public void setProfile_picture(ImageView profile_picture) {
        this.profile_picture = profile_picture;
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
     * @return the ed
     */
    public Button getEd() {
        return ed;
    }

    /**
     * @param ed the ed to set
     */
    public void setEd(Button ed) {
        this.ed = ed;
    }

    /**
     * @return the dell
     */
    public Button getDell() {
        return dell;
    }

    /**
     * @param dell the dell to set
     */
    public void setDell(Button dell) {
        this.dell = dell;
    }    
  
    @Override
    public String toString() {
        return "Admin Registration{"+"ID_Admin: " +getIDAdmin()+"FirstName: "+getFirst_name() +" LastName:= " + getLast_name() + " Date of Birth: "+getDate_birth()+" Gender: "+getGender()+" Profession: " + getProfession() + " Address: " + getAddress()+" Email: "+getEmail()+" Login: "+getLogin()+ " Password: " + getPasswrd()+ " Profile Picture Path: " + getProfile_picture()+'}';
    }


  

  

    
}
