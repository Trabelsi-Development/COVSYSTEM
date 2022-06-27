/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Register_SignInForms;
  
import javax.mail.internet.InternetAddress;   
import javax.mail.Transport;   
import javax.mail.Session;  
import javax.mail.internet.*;
import java.util.Random;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.User;
import java.awt.Desktop;
import java.io.IOException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.net.URI;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SIGNINFormController implements Initializable {
    private double xOffset=0, yOffset=0;
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField login;    
    @FXML
    private PasswordField password;
    @FXML
    private TextField passwordtxt;
    @FXML
    private ImageView signIn;
    @FXML
    private ImageView SignUp;
    @FXML
    private ImageView back;
    @FXML
    private ImageView signinn;
    @FXML
    private ImageView facebook;    
    @FXML
    private ImageView twitter;
    @FXML
    private ImageView google;
    @FXML
    private ImageView show_pwd;
    @FXML
    private ImageView hide_pwd;
    @FXML
    private Label ErrLogin;
    @FXML
    private Label ErrPwd;
    @FXML
    private Hyperlink forgotpwd;
      
    
    @FXML
    private void signinMouseEntered() {
        signIn.setLayoutY(30);
            }
    
    
    @FXML
     private void signinMouseExited() {
        signIn.setLayoutY(25);
    }
     
     @FXML
    private void signupMouseEntered() {
        SignUp.setLayoutY(30);
            }
    
    
    @FXML
     private void signupMouseExited() {
        SignUp.setLayoutY(25);
    }
    
     @FXML
     private void signupMouseClicked(MouseEvent event) throws IOException {
         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("RegisterForm.fxml"));
        
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
    private void signin_saveMouseEntered() {
        signinn.setLayoutY(535);
            }
      
    @FXML
     private void signin_saveMouseExited() {
        signinn.setLayoutY(540);
    }
     
     
    @FXML
    private void backMouseEntered() {
        back.setLayoutY(545);
            }
      
    @FXML
     private void backMouseExited() {
        back.setLayoutY(550);
    }
     
     @FXML
     private void backMouseClicked() {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }
     
      @FXML
     private void facebookMouseEntered() {
         facebook.setLayoutY(165);

    }
     
     @FXML
     private void facebookMouseExited() {
        facebook.setLayoutY(170);
    }
     
       @FXML
     private void facebookMouseClicked() throws Exception{
       /*Desktop d =Desktop.getDesktop();
       d.browse(new URI("https://www.facebook.com"));*/
       String domain="https://www.facebook.com";
       String appID="1136782776694059";
       String authURL="https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appID+"&redirect_uri="+domain+"&scope=email";
       
       System.setProperty("webdriver.edge.driver", "C:\\Users\\hp\\Desktop\\JAVAProject\\msedgedriver.exe");
       WebDriver driver=new EdgeDriver();
       driver.get(authURL);
       String accessToken="EAADJ5d2XdSsBADUsxUMudZC1wdptouVCMPBoWBt4uRdX5QlM4roZ4C6RGOHz3WJBXUwN8qwJZAf3YRCg8umjb4TbtlbIcxugGd8GbgZA25WdAR0bNZAuQgsrUt2tZAIt3YZBAc1OfqriPfeeTWuvh9ZCK9KpzvY1at5uZAGjZAsCbX6p7SjkzZB6Ex7JDDSJWhfjjZCEFSDXGX5mMRtZAcLZCb9dUa";
       while(driver!=null)
       {
           if (driver.getCurrentUrl().contains("facebook.com/#access_token"))
           {
               String Url=driver.getCurrentUrl();
                driver.quit();
               FacebookClient fbclient = new DefaultFacebookClient(accessToken,Version.LATEST);
               User user=fbclient.fetchObject("me", User.class,Parameter.with("fields", "email"));
               if (user != null)//parfois l'utilisateur ne donne pas l'accès à l'application d'accèder à son profil donc je récupère seulement son email et je remplis manuellement les autres champs
               {
               UserRegistration uR=new UserRegistration();
               String ch="",log="";
                 for (int i=0;i<user.getEmail().length();i++)
                 {if (user.getEmail().charAt(i)=='@')
                     break;
                 else ch=ch+user.getEmail().charAt(i);}
                 uR.setFirst_name(ch);   
                 uR.setLast_name(ch);
                 uR.setDate_birth("04/12/1999");
                 uR.setGender("Male");
                 uR.setAddress("Somewhere");
                 uR.setProfession("Other");
                 uR.setEmail(user.getEmail());
                 
    Boolean existe=false;
    Random rand = new Random();
                 int nombreAleatoire = rand.nextInt(100000);
                 log=ch+nombreAleatoire;
        try{
        ConnexionDB conn= new ConnexionDB();
        ConnexionDB conn2= new ConnexionDB();
        
        String data="Select * from Administrators where LOGIN="+"'"+log+"'";
        String data2="Select * from Users where LOGIN="+"'"+log+"'";        
        ResultSet rs,rs2; 
        rs=conn.getStmt().executeQuery(data);
        rs2=conn2.getStmt().executeQuery(data2);
        if (rs.next()||rs2.next()) //si le login saisi existe déjà soit dans la table Administrators soit dans la table Users
        {
           existe=true;
        }
        
       if (!existe)
       {
           uR.setLogin(log);
            uR.setPasswrd(log);
             String sql="INSERT INTO USERS (ID_USER,FIRST_NAME,LAST_NAME,BIRTH_DATE,GENDER,PROFESSION,ADDRESS,EMAIL,LOGIN,PASSWORD,PROFILE_PICTURE)"+"VALUES("+"Users_auto_increment.nextval"+",'"+uR.getFirst_name()+"','"+uR.getLast_name()+"', TO_DATE('"+uR.getDate_birth()+"'),'"+uR.getGender()+"','"+uR.getProfession()+"','"+uR.getAddress()+"','"+uR.getEmail()+"','"+uR.getLogin()+"','"+uR.getPasswrd()+"',"+null+")";
            int rss=conn.getStmt().executeUpdate(sql);
               if (rss==0)
               {Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText("The connexion has failed !");
               alert.showAndWait();
               }else
               {Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
               alert.setTitle("CONFIRMATION DIALOG");
               alert.setContentText("Congratulations !\nThe connexion has been established !");
               alert.showAndWait();
                Stage stage1 = new Stage();
                   FXMLLoader fuser=new FXMLLoader(getClass().getResource("/UserPanel/UserDashboard.fxml"));
                    Parent root = fuser.load();
                    UserPanel.UserDashboardController controllerUser=fuser.getController();
                    Register_SignInForms.UserRegistration.loginUser=rs.getString("LOGIN");
                  
Scene scene = new Scene(root,Color.TRANSPARENT);
                    
                    stage1.setScene(scene);  
                    stage1.centerOnScreen();
                    stage1.show();
               Stage stage = (Stage) back.getScene().getWindow();
               
               stage.close();
               //ouvrir le panel de user 
                   
               
        }
        conn.getStmt().close();
        conn.getConDB().close();
        conn2.getStmt().close();
        conn2.getConDB().close();
     }
        }
     catch (Exception e)
     {
         Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
     }    
                     
       }
       
              driver=null;
     }
           
       }
     }
               
      @FXML
     private void twitterMouseEntered() {
        twitter.setLayoutY(165);
    }
     
     @FXML
     private void twitterMouseExited() {
        twitter.setLayoutY(170);
    }
    
    @FXML
     private void twitterMouseClicked() throws Exception{
       Desktop d =Desktop.getDesktop();
         d.browse(new URI("https://twitter.com"));
    }
    
      @FXML
     private void googleMouseEntered() {
        google.setLayoutY(165);
    }
     
     @FXML
     private void googleMouseExited() {
        google.setLayoutY(170);
    }
     
      @FXML
     private void googleMouseClicked() throws Exception{
       Desktop d =Desktop.getDesktop();
         d.browse(new URI("https://www.google.com/"));
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
    private void passwordtxtKeyReleased(KeyEvent evt) { //Afin que le passwordfield détecte au fur et à mesure la modification faite au niveau du TextField du password       
 
       password.setText(passwordtxt.getText());
    }
    
    @FXML
     private void forgot_pwdClicked() throws MessagingException { 
         if (Register_SignInForms.RegisterFormController.verifLogin(login.getText()))
        {
         try{
         //create ConnexionDB variable
        ConnexionDB cnx=new ConnexionDB() ;
        ConnexionDB cnx2=new ConnexionDB() ;
        String sql="SELECT * FROM Users WHERE LOGIN='"+login.getText()+"'";
        String sql2="SELECT * FROM Administrators WHERE LOGIN='"+login.getText()+"'";
        
        ResultSet rs,rs2; 
        rs=cnx.getStmt().executeQuery(sql);       
        rs2=cnx2.getStmt().executeQuery(sql2);
        
           if (rs.next()){//ouvrir le panel des users
           String to = "ameniselmi456@gmail.com";
  
        //Get the session object  
         Properties properties = System.getProperties(); 
         // Setup mail server        
         properties.setProperty("mail.smtp.auth", "true");
         properties.setProperty("mail.smtp.starttls.enable", "true");
         properties.setProperty("mail.smtp.port", "587");
         properties.setProperty("mail.smtp.host", "smtp.gmail.com");
          // Get the default Session object
         
         Session session = Session.getDefaultInstance(properties, new Authenticator(){
             
             public PasswordAuthentication getPasswordAuthetication()
             {return new PasswordAuthentication("trabelsy2@gmail.com","semsem9630");
                 
             }
         });  
        session.setDebug(true); // Enable the debug mode

        //compose the message      
            MimeMessage message = new MimeMessage(session);  
            message.setFrom(new InternetAddress("trabelsy2@gmail.com"));  
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));  
            message.setSubject("PASSWORD FORGOTTEN IN COVSYSTEM");  
            message.setText("Hello, this is your forgotten password:\n"+rs.getString("PASSWORD"));  

         // Send message  
         Transport transport;
         transport = session.getTransport("smtps");
        transport.connect("smtp.gmail.com",465,"trabelsy2@gmail.com", "semsem9630");
        transport.sendMessage(message,message.getAllRecipients());
        transport.close();
        
         // Tansport.send(message);   
    
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("CHECKING IN ...");
               alert.setContentText("Dear User, we've sent you an email containing your forgotten password !");
               alert.showAndWait();
        }
                 
       
           else if (rs2.next())
        {       String to = rs2.getString("EMAIL");
          
        //Get the session object  
         Properties properties = System.getProperties();  
         properties.setProperty("mail.smtp.auth", "true");
         properties.setProperty("mail.smtp.starttls.enable", "true");
         properties.setProperty("mail.smtp.port", "587");
         properties.setProperty("mail.smtp.host", "smtp.gmail.com");
         Session session = Session.getDefaultInstance(properties, new Authenticator(){
             
             public PasswordAuthentication getPasswordAuthetication()
             {return new PasswordAuthentication("trabelsy2@gmail.com","semsem9630");
                 
             }
         });  
        session.setDebug(true); // Enable the debug mode

        //compose the message      
            MimeMessage message = new MimeMessage(session);  
            message.setFrom(new InternetAddress("trabelsy2@gmail.com"));  
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
            message.setSubject("PASSWORD FORGOTTEN");  
            message.setText("Hello, this is your forgotten password: "+rs2.getString("PASSWORD"));  

         // Send message  
         Transport transport;
         transport = session.getTransport("smtps");
        transport.connect("smtp.gmail.com",465,"trabelsy2@gmail.com", "semsem9630");
        transport.sendMessage(message,message.getAllRecipients());
        transport.close();
        
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("CHECKING IN ...");
               alert.setContentText("Dear Administrator, we've sent you an email containing your forgotten password !");
               alert.showAndWait();
        }
               }
        catch(SQLException | MessagingException e)
        {
           Alert alert=new Alert(Alert.AlertType.ERROR);
           alert.setTitle("ERROR DIALOG");
           alert.setContentText(e.getMessage());
           alert.showAndWait();
           e.printStackTrace();
        
        }
        }
         else {//le login est mal saisi
                 Alert alert=new Alert(Alert.AlertType.ERROR);
           alert.setTitle("ERROR DIALOG");
           alert.setContentText("Please check up your LOGIN before !");
           alert.showAndWait();
                 }
     
    }
     
     
     
    
     @FXML
     private void signin_saveMouseClicked(MouseEvent event) throws IOException {
        int i=0;//ce compteur va nous renseigner après si les champs sont tous remplis correctement ou pas
        
        if (!Register_SignInForms.RegisterFormController.verifLogin(login.getText()))
        {i=1;
        ErrLogin.setText("Please enter a correct LOGIN !");
        login.setText("");
        login.setStyle("-fx-text-inner-color: rgb(242,124,125) ;");     
        }
        else {ErrLogin.setText(""); 
        login.setStyle("-fx-text-inner-color: black ;");     
       } 
         if (password.getText().equals("")||password.getText().contains(" "))
        {i=1;
        ErrPwd.setText("Please enter a correct PASSWORD !");
        password.setText("");
        password.setStyle("-fx-text-inner-color: rgb(242,124,125) ;");     
        }
        else {ErrPwd.setText(""); 
        password.setStyle("-fx-text-inner-color: black ;");     
       } 
         if (i==0)//si tous les champs sont correctement remplis on va tester si cet utilisateur existe réellement dans la BD
         { try{
         //create ConnexionDB variable
        ConnexionDB cnx=new ConnexionDB() ;
        ConnexionDB cnx2=new ConnexionDB() ;
        String sql="select * from users where LOGIN='"+login.getText()+"' and password='"+password.getText()+"'";
        String sql2="select * from administrators where LOGIN='"+login.getText()+"' and password='"+password.getText()+"'";
        
        ResultSet rs,rs2; 
        rs=cnx.getStmt().executeQuery(sql);       
        rs2=cnx2.getStmt().executeQuery(sql2);
               if (rs.next()){//s'il est utilisateur normal
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                   FXMLLoader fuser=new FXMLLoader(getClass().getResource("/UserPanel/UserDashboard.fxml"));
                    Parent root = fuser.load();
                    UserPanel.UserDashboardController controllerUser=fuser.getController();
                    Register_SignInForms.UserRegistration.loginUser=rs.getString("LOGIN");
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
               else//sinon soit il est admin ou bien il n'existe pas
               {if(rs2.next()){
                   Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                   FXMLLoader fadmin=new FXMLLoader(getClass().getResource("/AdminPanel/AdminDashboard.fxml"));
                    Parent root = fadmin.load();
                    AdminPanel.AdminDashboardController controlleradmin=fadmin.getController();
                    Register_SignInForms.UserRegistration.loginUser=rs2.getString("LOGIN");
                    if (rs2.getString("PROFILE_PICTURE")!=null)
                    controlleradmin.profilePicture.setImage(new Image (rs2.getString("PROFILE_PICTURE")));
                    else controlleradmin.profilePicture.setImage(new Image(getClass().getResourceAsStream("../images/administrator-male-100.png")));
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
               else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR DIALOG");
                    alert.setContentText("This user does not exist !\nPlease sign up before !");
                    alert.showAndWait();}
        cnx.getStmt().close();
        cnx.getConDB().close();
        
        }  
         }
        catch(SQLException e)
        {
           Alert alert=new Alert(Alert.AlertType.ERROR);
           alert.setTitle("ERROR DIALOG");
           alert.setContentText(e.getMessage());
           alert.showAndWait();
        
        }

             
         }
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hide_pwd.setVisible(false);
         passwordtxt.setVisible(false);
       
    }    
    
}
