package Register_SignInForms;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author hp
 */
public class UserRegistration {
    private long IDUser;
    private ImageView profile_picture;
    private String first_name;
    private String last_name;
    private String date_birth;
    private String gender;   
    private String profession;
    private String address;
    private String email;
    private String login;
    private String passwrd;
    private ImageView edit;
    private ImageView delete;
    private Button editt;
    private Button deletee;
    public static String loginUser;
    
    /**
     *
     */
    public UserRegistration () //constructeur non paramétré
    {IDUser=0;
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

    
    public UserRegistration (long id, String f, String l, String d, String g, String p, String a, String e,String log, String pwd, ImageView pic) //constructeur paramétré
    {IDUser=id;
    profile_picture=pic;
    profile_picture.setFitWidth(70);
    profile_picture.setFitHeight(50);
    first_name=f;
    last_name=l;
    date_birth=d;
    gender=g;
    profession=p;
    address=a;
    email=e;
    login=log;
    passwrd=pwd;  
    ImageView image1=new ImageView (new Image(getClass().getResourceAsStream("../images/editUser-100.png")));
    ImageView image2=new ImageView (new Image(getClass().getResourceAsStream("../images/deleteUser-100.png")));
    edit=image1;
    edit.setFitWidth(45);
    edit.setFitHeight(45);
    
     delete=image2;  
    delete.setFitWidth(45);
    delete.setFitHeight(45);
    
    editt=new Button();
    editt.setGraphic(edit);
    editt.setStyle("-fx-background-color:TRANSPARENT;");
    editt.setCursor(Cursor.HAND);
    deletee=new Button();
    deletee.setGraphic(delete);
    deletee.setStyle("-fx-background-color:TRANSPARENT;");
    deletee.setCursor(Cursor.HAND);
    
    }
    
    /**
     * @return the IDUser
     */
    public long getIDUser() {
        return IDUser;
    }

    /**
     * @param IDUser the IDUser to set
     */
    public void setIDUser(long IDUser) {
        this.IDUser = IDUser;
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
     * @return the editt
     */
    public Button getEditt() {
        return editt;
    }

    /**
     * @param editt the editt to set
     */
    public void setEditt(Button editt) {
        this.editt = editt;
    }

    /**
     * @return the deletee
     */
    public Button getDeletee() {
        return deletee;
    }

    /**
     * @param deletee the deletee to set
     */
    public void setDeletee(Button deletee) {
        this.deletee = deletee;
    }

    
    @Override
    public String toString() {
        return "User Registration{" +"IDUser: "+getIDUser()+"FirstName: "+getFirst_name() +" LastName:= " + getLast_name() + " Date of Birth: "+getDate_birth()+" Gender: "+getGender()+" Profession: " + getProfession() + " Address: " + getAddress()+" Email: "+getEmail()+ " Password: " + getPasswrd()+ '}';
    }

    
}
