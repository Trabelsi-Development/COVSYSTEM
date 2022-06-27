/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.AdminManagement;

import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class UpdateAdminController implements Initializable {

    @FXML
    private final ObservableList <String> p = FXCollections.observableArrayList("Artist","Athlete","Barber","Bodyguard","Business Man","Carpenter","CEO","Chemist","Dentist","Doctor","Electrician","Engineer","Farmer","Housewife","Journalist","Lawyer","Manager","Musician","Nurse","Painter","Paramedic","Photographer","Plumber","Policeman","Priest","Professor","Programmer","Singer","Student","Taxi Driver","Technician","Veterinarian","Waiter","Writer","Other...");//une liste contenant toutes les professions qu'on va les affecter par la suite au combobox profession
    private Connection conDB;
   private Statement stmt;
   public String path="";
   public String ancienLogin;
   
    @FXML
    private ImageView rightSide;
    @FXML
    public TextField login;
    @FXML
    private ImageView hide_pwd;
    @FXML
    public TextField passwordtxt;
    @FXML
    public Label passwordd;
    @FXML
    public Label deleteMyImage;
    @FXML
    public TextField email;
    @FXML
    public PasswordField password;
    @FXML
    private Label emaill;
    @FXML
    public TextField address;
    @FXML
    private Label addresss;
    @FXML
    public ComboBox <String> profession;
    @FXML
    private Label professionn;
    @FXML
    public RadioButton female;
    @FXML
    private ToggleGroup groupButton;
    @FXML
    public RadioButton male;
    @FXML
    private Label gender;
    @FXML
    public DatePicker BirthDate;
    @FXML
    private Label birthdate;
    @FXML
    public TextField lastName;
    @FXML
    private Label last_name;
    @FXML
    public TextField firstName;
    @FXML
    public TextField id_admin_txt;
    @FXML
    private Label firstname;
    @FXML
    private ImageView back;
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
    private Label ErrPassword;
    @FXML
    private Label security;
    @FXML
    public ImageView profilePicture;
    @FXML
    private ImageView login_existance_png;
    @FXML
    private ImageView show_pwd;
    @FXML
    private Button saveUpdate;
    @FXML
    private Label imageChooser;

    
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
   
    @FXML
    private void hidepwdClicked(MouseEvent event) {
                
        hide_pwd.setVisible(false);
        show_pwd.setVisible(true);
        passwordtxt.setVisible(false);
        password.setText(passwordtxt.getText());
        password.setVisible(true);
    }

    @FXML
    private void showpwdClicked(MouseEvent event) {
         hide_pwd.setVisible(true);
        show_pwd.setVisible(false);
        password.setVisible(false);
        passwordtxt.setText(password.getText());
        passwordtxt.setVisible(true);
       
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
    private void backMouseEntered() {
        back.setLayoutY(650);
            }
      
    @FXML
     private void backMouseExited() {
        back.setLayoutY(660);
    }
     
     @FXML
     private void backMouseClicked() {
        AdministratorManagementController.nbr2=0;
        Stage stage = (Stage) back.getScene().getWindow();
         stage.close();
         
    }
     
    @FXML
    private void login_KeyReleased(KeyEvent evt) {//évenement qui nous renseigne si le login saisi est utilisé par un utilisateur normal ou par un admin ou non 
        if (!login.getText().equals("") && !login.getText().contains(" "))
        {
     try{
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        Register_SignInForms.ConnexionDB conn2= new Register_SignInForms.ConnexionDB();
        
        String data="Select * from Administrators where LOGIN="+"'"+login.getText()+"' and LOGIN!='"+ancienLogin+"'";//l'ancien login doit etre accepté c'est à dire l'utilisateur n'ap changé de login
        String data2="Select * from Users where LOGIN="+"'"+login.getText()+"' and LOGIN!='"+ancienLogin+"'";//l'ancien login doit etre accepté c'est à dire l'utilisateur n'ap changé de login   
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
        {if (login.getText().equals(ancienLogin))
            {login_existance.setText("");
            login_existance_png.setImage(null);
            }
        else
        { login_existance.setText("UNUSED");
            Image image2 =new Image (getClass().getResourceAsStream("/images/correct-48.png"));
            login_existance_png.setImage(image2);}
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
    private void deleteMyImage_MouseClicked(MouseEvent event) {
       profilePicture.setImage(new Image("file:/C:/Users/hp/Desktop/JAVAProject/src/images/addAdmin-100.png"));
      path="";
      deleteMyImage.setVisible(false);
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
    private void saveUpdateButton_MouseEntered(MouseEvent event) {
         saveUpdate.setLayoutY(638);
       
    }
    
      @FXML
    private void saveUpdateButton_MouseExited(MouseEvent event) {
         saveUpdate.setLayoutY(648);
       
    }
    
    
        @FXML
    private void saveUpdateButton_MouseClicked(MouseEvent event) {
         int i=0;//ce compteur va nous renseigner après si les champs sont tous remplis correctement ou pas
        
        
         if (!Register_SignInForms.RegisterFormController.verifAlpha(firstName.getText()))
        {i=1;
        ErrFirstName.setText("Please enter a correct first name !");
        firstName.setText("");
        firstName.setStyle("-fx-text-inner-color: rgb(130,145,255) ;");     
        }
        else {ErrFirstName.setText(""); 
        firstName.setStyle("-fx-text-inner-color: black ;");     
       }
         
        if (!Register_SignInForms.RegisterFormController.verifAlpha(lastName.getText()))
        {i=1;
        ErrLastName.setText("Please enter a correct last name !");
        lastName.setText("");
        lastName.setStyle("-fx-text-inner-color: rgb(130,145,255) ;");     
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
        ErrGender.setText("Please select his/her gender !");                   
        }
        else {
            ErrGender.setText("");
            
        }
       
       if (profession.getValue()==null)
        {ErrProfession.setText("Please select his/her new profession !");
        i=1;}
        else 
        {ErrProfession.setText("");
        }
        
        if (address.getText().equals("")||address.getText().equals(" "))
        {i=1;
        ErrAddress.setText("Please enter his/her new address correctly !");
        address.setText("");
        address.setStyle("-fx-text-inner-color: rgb(130,145,255) ;");     
        }
        else {ErrAddress.setText("");
        address.setStyle("-fx-text-inner-color: black ;");     
       }
        
       if (!Register_SignInForms.RegisterFormController.verifEmail(email.getText()))
        {i=1;
        ErrEmail.setText("Please enter a correct email address !");
        email.setText("");
        email.setStyle("-fx-text-inner-color: rgb(130,145,255) ;");     
        }
        else {ErrEmail.setText("");
        email.setStyle("-fx-text-inner-color: black ;");     
       }
       
       if ((!Register_SignInForms.RegisterFormController.verifLogin(login.getText()))||login_existance.getText().equals("USED")) //soit le login est mal saisi (ne satisfait pas les conditions) soit il est déjà utilisé que ce soit par un admin ou un utilisateur normal
         {i=1;
         ErrLogin.setText("Please enter a valid \nand UNUSED LOGIN!\nLength > 6 and NO spaces or :");
        login.setText("");        
        login_existance.setText("");       
        login_existance_png.setImage(null);
        login.setStyle("-fx-text-inner-color: rgb(130,145,255) ;");     
        }
        else {
        ErrLogin.setText("");
        login.setStyle("-fx-text-inner-color: black ;");     
       }
        
         if (password.getText().equals("") || password.getText().contains(" "))
         {i=1;
        ErrPassword.setText("Please enter a valid password !");
        password.setText("");
        security.setText("");
        password.setStyle("-fx-text-inner-color: rgb(130,145,255) ;"); 
        passwordtxt.setText("");
        passwordtxt.setStyle("-fx-text-inner-color: rgb(130,145,255) ;");     
        
        }
         else {ErrPassword.setText("");
         password.setStyle("-fx-text-inner-color: black ;");     
         passwordtxt.setStyle("-fx-text-inner-color: black ;");     
       
        }
         if (i==0)//tous les champs sont remplis correctement
         {AdminRegistration regA=new AdminRegistration();
         regA.setIDAdmin(Long.parseLong(id_admin_txt.getText()));
        regA.setFirst_name(firstName.getText());
        regA.setLast_name(lastName.getText());

        String birth_date = BirthDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        regA.setDate_birth(birth_date);

            if (male.isSelected()==false )
                regA.setGender(female.getText());
            else
                regA.setGender(male.getText());

        regA.setProfession(profession.getValue());
        regA.setAddress(address.getText());
        regA.setEmail(email.getText());
        regA.setLogin(login.getText());
        regA.setPasswrd(password.getText());
        
             try{//update de l'administrateur dans la BD puis rafraîchissement de la tableview 
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        String row="UPDATE ADMINISTRATORS SET FIRST_NAME='"+regA.getFirst_name()+"',"
                + "LAST_NAME='"+regA.getLast_name()+"',"
                + "BIRTH_DATE=TO_DATE('"+regA.getDate_birth()+"'),"
                + "GENDER='"+regA.getGender()+"',"
                + "PROFESSION='"+regA.getProfession()+"',"
                + "ADDRESS='"+regA.getAddress()+"',"
                + "EMAIL='"+regA.getEmail()+"',"
                + "LOGIN='"+regA.getLogin()+"',"
                + "PASSWORD='"+regA.getPasswrd()+"',"
                + "PROFILE_PICTURE='"+path+"' where ID_ADMIN="+regA.getIDAdmin();
        
                
        int rss=conn.getStmt().executeUpdate(row); 
        
        if (rss==0)
               {Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText("The update has failed !");
               alert.showAndWait();
               }else
               { Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
               alert.setTitle("CONFIRMATION DIALOG");
               alert.setContentText("Congratulations !\nThe update has succeeded !");
               alert.showAndWait();
              if (Register_SignInForms.UserRegistration.loginUser.equals(regA.getLogin()))                     
                {System.out.println(path);
                    if (path!=null)
                    profilePicture.setImage(new Image (path));
                else profilePicture.setImage(new Image(getClass().getResourceAsStream("../images/administrator-male-100.png")));
                }
               AdministratorManagementController.nbr2=0;//lorsque tout est bon et la modification est effectuée avec succès la fenêtre update administrator va être fermée et le compteur nbr (qui nous renseigne sur le nombre de fois la fenêtre update administrator est ouverte) sera initialisé à 0
               Stage stage = (Stage) saveUpdate.getScene().getWindow();
               stage.close(); 
               
               }
      
        conn.getStmt().close();
        conn.getConDB().close();
             }
             catch (Exception e){
           Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
                 e.printStackTrace();
       }
             
         }
         
       
    }
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hide_pwd.setVisible(false);        
        profession.setItems(p);
        passwordtxt.setVisible(false);
     
    }
}
