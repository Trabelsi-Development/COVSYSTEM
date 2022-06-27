/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserPanel.UpdateUserAccount ;

import Register_SignInForms.ConnexionDB;
import Register_SignInForms.UserRegistration;
import java.io.File;
import java.io.IOException;
import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.sql.SQLException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class UpdateUserController implements Initializable {
        Connection con = null; 
        Statement stmt = null; 
        private int rs=0; 
        private double xOffset=0, yOffset=0;
        private String path="";
        
    @FXML
    private ComboBox <String> profession;
    @FXML
    private ImageView signIn;
    @FXML
    private ImageView signUp;
    @FXML
    private ImageView register;
    @FXML
    private ImageView back;
    @FXML
    private ImageView show_pwd;
    @FXML
    private ImageView hide_pwd;
    @FXML
    private ImageView show_vpwd;
    @FXML
    private ImageView hide_vpwd;    
    @FXML
    public Label deleteMyImage;
    @FXML
    private Label ErrFirstName;
    @FXML
    private Label ErrLastName;
    @FXML
    private Label ErrBirthDate;
    @FXML
    private Label ErrGender;
    @FXML
    private Label ErrProfession;
    @FXML
    private Label ErrAddress;
    @FXML
    private Label ErrEmail;
    @FXML
    private Label ErrLogin;
    @FXML
    private Label login_existance;
    @FXML
    private ImageView login_existance_png;
    @FXML
    private Label ErrPassword;
    @FXML
    private Label ErrvPassword;
    @FXML
    private Label ErrTerms;   
    @FXML
    private Label security;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;
    @FXML
    private TextField email;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private TextField passwordtxt;  
    @FXML
    private TextField vpasswordtxt;     
    @FXML
    private PasswordField vpassword;   
    @FXML
    private CheckBox terms;
    @FXML
    private DatePicker BirthDate;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private Label imageChooser;
    @FXML
    private ImageView profilePicture;
    @FXML
    private ImageView Delete;
    
    
    @FXML
    private final ObservableList <String> p = FXCollections.observableArrayList("Artist","Athlete","Barber","Bodyguard","Business Man","Carpenter","CEO","Chemist","Dentist","Doctor","Electrician","Engineer","Farmer","Housewife","Journalist","Lawyer","Manager","Musician","Nurse","Painter","Paramedic","Photographer","Plumber","Policeman","Priest","Professor","Programmer","Singer","Student","Taxi Driver","Technician","Veterinarian","Waiter","Writer","Other...");//une liste contenant toutes les professions qu'on va les affecter par la suite au combobox profession
    
    @FXML
    private void signinMouseEntered() {
        signIn.setLayoutY(30);
            }
     
    @FXML
     private void signinMouseExited() {
        signIn.setLayoutY(25);
    }
      @FXML
     private void signinMouseClicked(MouseEvent event) throws IOException {
       
     Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SIGNINForm.fxml"));
        
        Scene scene = new Scene(root,Color.TRANSPARENT);
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
           {stage.setX(event.getScreenX() - xOffset);
           stage.setY(event.getScreenY() - yOffset);
           
               
           }
        });
        
        stage.setScene(scene);  
        stage.centerOnScreen();
        stage.show();
     }
     
     @FXML
    private void deleteMyImage_MouseClicked(MouseEvent event) {
       profilePicture.setImage(new Image("file:/C:/Users/hp/Desktop/JAVAProject/src/images/addAdmin-100.png"));
      path="";      
      deleteMyImage.setVisible(false);
    }
    

     @FXML
    private void signupMouseEntered() {
        signUp.setLayoutY(30);
            }
 
    @FXML
     private void signupMouseExited() {
        signUp.setLayoutY(25);
    }
     
     
    @FXML
    private void registerMouseEntered() {
        register.setLayoutY(640);
            }
      
    @FXML
     private void registerMouseExited() {
        register.setLayoutY(645);
    }
     
    @FXML
    private void backMouseEntered() {
        back.setLayoutY(640);
            }
      
    @FXML
     private void backMouseExited() {
        back.setLayoutY(645);
    }
     
     @FXML
     private void backMouseClicked() {
        Stage stage = (Stage) back.getScene().getWindow();
         stage.close();
    }
     
       @FXML
     private void showpwdClicked() {
        hide_pwd.setVisible(true);
        show_pwd.setVisible(false);
        password.setVisible(false);
        passwordtxt.setText(password.getText());
        passwordtxt.setVisible(true);
       
    }
        @FXML
     private void hidepwdClicked() {
         
        hide_pwd.setVisible(false);
        show_pwd.setVisible(true);
        passwordtxt.setVisible(false);
        password.setText(passwordtxt.getText());
        password.setVisible(true);
     
    }
         @FXML
     private void showvpwdClicked() {
        hide_vpwd.setVisible(true);
        show_vpwd.setVisible(false);
        vpassword.setVisible(false);
        vpasswordtxt.setText(vpassword.getText());
        vpasswordtxt.setVisible(true);
    }
        @FXML
     private void hidevpwdClicked() {       
        hide_vpwd.setVisible(false);
        show_vpwd.setVisible(true);
        vpasswordtxt.setVisible(false);
        vpassword.setText(vpasswordtxt.getText());
        vpassword.setVisible(true);
        
    }
     
     @FXML
    private void passwordtxtKeyReleased(KeyEvent evt) { //Afin que le passwordfield détecte au fur et à mesure la modification faite au niveau du TextField du password       
     int numbers=0, letters=0; 
     password.setText(passwordtxt.getText());
       if (passwordtxt.getText().equals(""))
           security.setText("");
       else{
       
       if (evt.getCode()!=KeyCode.SPACE)
            {for (int i=0; i<password.getText().length();i++)
                {if (Character.isDigit(password.getText().charAt(i)))
                    numbers++;
                else if(Character.isAlphabetic(password.getText().charAt(i)))
                    letters++;
                }
            }
     if ( (numbers==2 && letters==0 ) || (numbers==0 && letters==2))
            security.setText("LOW");
        else  if  (numbers>1 && numbers <5 && letters>5 && letters<8)
            security.setText("MEDIUM");
             
        else  if  (numbers>4 && letters>7)
        security.setText("HIGH");
    
       }
    }
    
    @FXML
    private void vpasswordtxtKeyReleased(KeyEvent evt) { //Afin que le passwordfield détecte au fur et à mesure la modification faite au niveau du TextField du password       
     vpassword.setText(vpasswordtxt.getText());
      
            }
      
    @FXML
    private void passwordKeyReleased(KeyEvent evt) {
     int numbers=0, letters=0;
     if (password.getText().equals(""))
           security.setText("");
       else{
         if (evt.getCode()!=KeyCode.SPACE)
            {for (int i=0; i<password.getText().length();i++)
                {if (Character.isDigit(password.getText().charAt(i)))
                    numbers++;
                else if(Character.isAlphabetic(password.getText().charAt(i)))
                    letters++;
                }
            }     
        if ( (numbers==2 && letters==0 ) || (numbers==0 && letters==2))
            security.setText("LOW");
        else  if  (numbers>1 && numbers <5 && letters>5 && letters<8)
            security.setText("MEDIUM");
             
        else  if  (numbers>4 && letters>7)
        security.setText("HIGH");
    }
    }
    
    @FXML
    private void imageChooser_MouseClicked(MouseEvent event) {
        Stage stage = (Stage) imageChooser.getScene().getWindow();
       FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select The Profile Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPEG/JPG", "*.jpeg","*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );  
        File selectedFile = fileChooser.showOpenDialog(stage);
        path =selectedFile.toURI().toString();//pour utiliser path lors de l'inscription de cet admin et le stocker dans la BD
        Image image;
        if (selectedFile != null) {
            {image= new Image (selectedFile.toURI().toString());            
            deleteMyImage.setVisible(true);
            }
        profilePicture.setImage(image);
    }
       
    }
    
     @FXML
    private void login_KeyReleased(KeyEvent evt) {//évenement qui nous renseigne si le login saisi est utilisé par un utilisateur normal ou par un admin ou non 
        if (!login.getText().equals("") && !login.getText().contains(" "))
        {
     try{
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        Register_SignInForms.ConnexionDB conn2= new Register_SignInForms.ConnexionDB();
        
        String data="Select * from Administrators where LOGIN="+"'"+login.getText()+"'";
        String data2="Select * from Users where LOGIN="+"'"+login.getText()+"'";        
        ResultSet rs,rs2; 
        rs=conn.getStmt().executeQuery(data);
        rs2=conn2.getStmt().executeQuery(data2);
        if (rs.next()||rs2.next()) //si le login saisi existe déjà soit dans la table Administrators soit dans la table Users
        {
            login_existance.setText("USED");
            Image image1 =new Image (getClass().getResourceAsStream("/images/cross-48.png"));
            login_existance_png.setImage(image1);
        }
        else //Sinon
        {
             login_existance.setText("UNUSED");
            Image image2 =new Image (getClass().getResourceAsStream("/images/correct-48.png"));
            login_existance_png.setImage(image2);
        }
        conn.getStmt().close();
        conn.getConDB().close();
        conn2.getStmt().close();
        conn2.getConDB().close();
     }
     
     catch (Exception e)
     {
         Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
     }
    }
        else {login_existance.setText("");       
        login_existance_png.setImage(null);}
    }
    
      private void reinitialisation_des_champs()
    {
    firstName.setText("");
    firstName.setStyle("-fx-text-inner-color: black ;");
    lastName.setText("");
    lastName.setStyle("-fx-text-inner-color: black ;");
    BirthDate.setStyle("-fx-text-inner-color: black ;");
    BirthDate.setValue(null);
    male.setSelected(false);
    female.setSelected(false);
    profession.setValue(profession.getPromptText());
    email.setText("");
    email.setStyle("-fx-text-inner-color: black ;");
    address.setText("");
    address.setStyle("-fx-text-inner-color: black ;");
    password.setText("");
    password.setStyle("-fx-text-inner-color: black ;");
    passwordtxt.setText("");
    passwordtxt.setStyle("-fx-text-inner-color: black ;");
    security.setText("");
    vpassword.setText("");
    vpassword.setStyle("-fx-text-inner-color: black ;");
    vpasswordtxt.setText("");
    vpasswordtxt.setStyle("-fx-text-inner-color: black ;");
    }
    
    public static boolean verifAlpha(String ch) //méthode qui assure que toute la chaîne ne contient que des lettres
    { boolean test=true;
    if (ch.equals("")|| ch.equals(" "))
        test=false;
    else{
    for (int i=0;i<ch.length();i++)
    {
        if (!Character.isAlphabetic(ch.charAt(i)))
        {test=false;
        break;            
        }
    }
    }
    return test;
    }
    
    public static boolean verifLogin(String ch) //methode qui verifie le login saisi par l'utilisateur ou l'administrateur dans AddAdmin or UpdateAdmin aussi
   {boolean test=true;
        if (ch.equals("")|| ch.contains(" ")|| ch.length()<=6)
             test=false;
        else {
            for (int i=0;i<ch.length();i++)
         {
             if (ch.charAt(i)==' '|| ch.charAt(i)==':')
             {  test=false;
                 break;            
             }
         }
           
             } 
    return test;
    }
    
   private boolean verifDate(LocalDate d) 
   {boolean test=true; 
        if (d==null) //si l'utilisateur n'ai seléctionnée aucune date
            {ErrBirthDate.setText("Please select your birth date !");
            BirthDate.setStyle("-fx-text-inner-color: rgb(242,124,125) ;");     
            test=false;}        
        else {//Sinon
            LocalDate today=LocalDate.now();//créer un nouvel objet de type LocalDate contenant par défaut la date du Système
                 
        if (d.isAfter(today)||d.isEqual(today))//comparaison entre les deux dates
             {ErrBirthDate.setText("Please select a valid birth date !");
             BirthDate.setStyle("-fx-text-inner-color: rgb(242,124,125) ;");     
         test=false;}
         }
    return test;
    }
   
   public static boolean verifEmail(String ch) //fonction qui vérifie que l'email saisi est correct (il contient un @ suivi par un point)
    {boolean test=true, trouver_at=false; int nbrpoint=0;
    if (ch.equals("")|| ch.contains(" "))
        test=false;
    else
    {int i;        
        for (i=0;i<ch.length();i++)
        {   if (ch.charAt(i)=='@')
                {trouver_at=true;
                 break;}
        }
        if (trouver_at)
        {
            for(int j=i;j<ch.length();j++)
            {if (ch.charAt(j)=='.')
                {nbrpoint++;}//il faut trouver un et un seul point après @ 
                
            }
        }
    }
    if (!trouver_at || nbrpoint!=1)
        test=false;
    
    
    return test;
    }
      
    
    private boolean verifPSWD(String ch)//une fonction qui vérifie que le mot de passe saisi dans le champ Password et celui saisi dans le champ verify password sont identiques
    {boolean test=true;
    if (ch.equals("")||ch.equals(" "))
    {        test=false;
    }
    else {if (!ch.equals(password.getText()))
        test=false;
        }
    return test;
        
    }
    
    private void PrintInfo(){
      try {
                ConnexionDB cnx=new ConnexionDB() ;
                String sql ;
                    sql="select * from users where LOGIN ='"+ UserRegistration.loginUser +"'";
                    ResultSet rs=null;
                rs=cnx.getStmt().executeQuery(sql) ;//execute select query
                while(rs.next()){
                    if (rs.getString(11)!=null)
                        profilePicture.setImage(new Image(rs.getString(11)));
                    firstName.setText(rs.getString(2));
                    lastName.setText(rs.getString(3));
                    BirthDate.setValue(rs.getDate(4).toLocalDate());
                    
                        if (rs.getString(5)=="Male")
                            male.setSelected(true);
                        else
                            female.setSelected(true);

                    profession.setValue(rs.getString(6));
                    address.setText(rs.getString(7));
                    email.setText(rs.getString(8));
                    login.setText(rs.getString(9));

                }
                //close connexion
                   rs.close();
                   cnx.getConDB().close();
                   cnx.getStmt().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
           
    }   
    
     @FXML
     private void DeleteMouseClicked(MouseEvent event) throws IOException {
        try{
         //create ConnexionDB variable
        ConnexionDB cnx=new ConnexionDB() ;
        String sql="Delete from users where login='"+UserRegistration.loginUser+"'";
        rs=cnx.getStmt().executeUpdate(sql);
               if (rs==0)
               {Alert alert=new Alert(AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText("The removal has failed !");
               alert.showAndWait();
               }else
               {Alert alert=new Alert(AlertType.CONFIRMATION);
               alert.setTitle("CONFIRMATION DIALOG");
               Register_SignInForms.UserRegistration.loginUser=null ;
               alert.setContentText("We hope we see you again !");
               alert.showAndWait();
               //show sign in form
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/Register_SignInForms/SIGNINForm.fxml"));

                Scene scene = new Scene(root,Color.TRANSPARENT);
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
                   {stage.setX(event.getScreenX() - xOffset);
                   stage.setY(event.getScreenY() - yOffset);


           }
        });
        stage.setScene(scene); 
        stage.centerOnScreen();
        stage.show();
        
               }
        cnx.getStmt().close();
        cnx.getConDB().close();
        
        }    
        catch(SQLException e)
        {
                Alert alert=new Alert(AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
        
        }
     
     }
     
     @FXML
     private void registerMouseClicked() {
        int i=0;//ce compteur va nous renseigner après si les champs sont tous remplis correctement ou pas
        
         if (!verifAlpha(firstName.getText()))
        {i=1;
        ErrFirstName.setText("Please enter a correct first name !");
        firstName.setText("");
        firstName.setStyle("-fx-text-inner-color: rgb(242,124,125) ;");     
        }
        else {ErrFirstName.setText(""); 
        firstName.setStyle("-fx-text-inner-color: black ;");     
       }
         
        if (!verifAlpha(lastName.getText()))
        {i=1;
        ErrLastName.setText("Please enter a correct last name !");
        lastName.setText("");
        lastName.setStyle("-fx-text-inner-color: rgb(242,124,125) ;");     
        }
        else {ErrLastName.setText(""); 
        lastName.setStyle("-fx-text-inner-color: black ;");     
       }
       
        if (!verifDate(BirthDate.getValue()))
            {i=1;}
        else {ErrBirthDate.setText("");
        BirthDate.setStyle("-fx-text-inner-color: black ;");     
       }
         
          
        if ((!male.isSelected()) && (!female.isSelected()))
        {i=1;
        ErrGender.setText("Please select your gender !");                   
        }
        else {
            ErrGender.setText("");
            
        }
       
       if (profession.getValue()==null)
        {ErrProfession.setText("Pleaser select your profession !");
        i=1;}
        else 
        {ErrProfession.setText("");
        }
        
        if (address.getText().equals("")||address.getText().equals(" "))
        {i=1;
        ErrAddress.setText("Please enter your address correctly !");
        address.setText("");
        address.setStyle("-fx-text-inner-color: rgb(242,124,125) ;");     
        }
        else {ErrAddress.setText("");
        address.setStyle("-fx-text-inner-color: black ;");     
       }
        
       if (!verifEmail(email.getText()))
        {i=1;
        ErrEmail.setText("Please enter a correct email address !");
        email.setText("");
        email.setStyle("-fx-text-inner-color: rgb(242,124,125) ;");     
        }
        else {ErrEmail.setText("");
        email.setStyle("-fx-text-inner-color: black ;");     
       }

        
         if (password.getText().equals("") || password.getText().contains(" "))
         {i=1;
        ErrPassword.setText("Please enter a password !");
        password.setText("");
        security.setText("");
        password.setStyle("-fx-text-inner-color: rgb(242,124,125) ;"); 
        passwordtxt.setText("");
        passwordtxt.setStyle("-fx-text-inner-color: rgb(242,124,125) ;");     
        
        }
         else {ErrPassword.setText("");
         password.setStyle("-fx-text-inner-color: black ;");     
         passwordtxt.setStyle("-fx-text-inner-color: black ;");     
       
        }
         
        if (!verifPSWD(vpassword.getText()))
        {i=1;
        ErrvPassword.setText("Please enter the same password\nwritten above !");
        vpassword.setText("");
        vpassword.setStyle("-fx-text-inner-color: rgb(242,124,125) ;");  
        vpasswordtxt.setText("");
        vpasswordtxt.setStyle("-fx-text-inner-color: rgb(242,124,125) ;");     
        
        }
        else {ErrvPassword.setText("");
        vpassword.setStyle("-fx-text-inner-color: black ;");     
        vpasswordtxt.setStyle("-fx-text-inner-color: black ;");     
       }
        
        
        
    if (i==0)//si tous les champs sont correctement remplis
        {UserRegistration reg=new UserRegistration();
        
        reg.setFirst_name(firstName.getText());
        reg.setLast_name(lastName.getText());
        String birth_date = BirthDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        reg.setDate_birth(birth_date);
        System.out.println(birth_date);

            if (male.isSelected()==false )
                reg.setGender(female.getText());
            else
                reg.setGender(male.getText());

        reg.setProfession(profession.getValue());
        reg.setAddress(address.getText());
        reg.setEmail(email.getText());
        reg.setLogin(login.getText());
        reg.setPasswrd(password.getText());
    
    try{
         //create ConnexionDB variable
        ConnexionDB cnx=new ConnexionDB() ;
        String sql="UPDATE USERS SET FIRST_NAME= '"+reg.getFirst_name()+"', LAST_NAME='"+reg.getLast_name()+"',"
                + "birth_date='"+reg.getDate_birth()+"',gender='"+reg.getGender()+"',profession='"+reg.getProfession()+"',"
                + "address='"+reg.getAddress()+"',email='"+reg.getEmail()+"',login='"+reg.getLogin()+"',"
                + "password='"+reg.getPasswrd()+"',PROFILE_PICTURE='"+path+"'"
                + "where login='"+UserRegistration.loginUser+"'" ;
        rs=cnx.getStmt().executeUpdate(sql);
               if (rs==0)
               {Alert alert=new Alert(AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText("The registration has failed !");
               alert.showAndWait();
               }else
               {Alert alert=new Alert(AlertType.CONFIRMATION);
               Register_SignInForms.UserRegistration.loginUser=reg.getLogin() ;
               alert.setTitle("CONFIRMATION DIALOG");
               alert.setContentText("Congratulations !\nUpdate successful !");
               alert.showAndWait();}
        cnx.getStmt().close();
        cnx.getConDB().close();
        
        }    
        catch(SQLException e)
        {
                Alert alert=new Alert(AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
        
        }
            
        }  
      
       
    }
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        profession.setItems(p);
        login.setDisable(true);
        hide_pwd.setVisible(false);
        hide_vpwd.setVisible(false);
        passwordtxt.setVisible(false);
        vpasswordtxt.setVisible(false);
        if (profilePicture.getImage().impl_getUrl().equals("jar:file:/C:/Users/hp/Desktop/JAVAProject/dist/Project2020V2.jar!/images/addAdmin-100.png"))
           deleteMyImage.setVisible(false);
         else
            deleteMyImage.setVisible(true);
        PrintInfo();
                   
    }    
    
}
