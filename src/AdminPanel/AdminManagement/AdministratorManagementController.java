/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.AdminManagement;

import animatefx.animation.FadeIn;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AdministratorManagementController implements Initializable {

    private ObservableList <AdminRegistration> myList= FXCollections.observableArrayList ();
    private double xOffset=0, yOffset=0;
    public static int nbr=0,nbr2=0;     
    
    @FXML
    private ImageView add_button;
   
          
    @FXML
    public TableView <AdminRegistration> myTableView; //pour pouvoir la rafraîchir après chaque modification dans la BD (insertion/mise à jour/suppression)
    
    @FXML
    private TableColumn <AdminRegistration,Long> IdCol; 
    @FXML
    private TableColumn <AdminRegistration,String> first_nameCol; 
    @FXML
    private TableColumn <AdminRegistration,String> last_nameCol;
    @FXML
    private TableColumn  <AdminRegistration,String> birthdateCol;
    @FXML
    private TableColumn <AdminRegistration,String> genderCol;
    @FXML
    private TableColumn <AdminRegistration,String> professionCol;
    @FXML
    private TableColumn <AdminRegistration,String> addressCol;    
    @FXML
    private TableColumn <AdminRegistration,String> emailCol;
    @FXML
    private TableColumn <AdminRegistration,String> loginCol;       
    @FXML
   private TableColumn <AdminRegistration,String> passwordCol;
    @FXML
    private TableColumn <AdminRegistration,ImageView> profilePictureCol;
    @FXML
    private TableColumn <AdminRegistration,Button>editCol;
    @FXML
    private TableColumn <AdminRegistration,Button>deleteCol;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void add_button_MouseEntered()
    {
     add_button.setLayoutY(180);
    }
  
    @FXML
    private void add_button_MouseExited()
    {
     add_button.setLayoutY(190);
    }
    
    
     @FXML
    private void add_button_MouseClicked() throws IOException
    {   nbr++; 
    if (nbr==1)//pour ne pas ouvrir plusieurs fois la même fenêtre
    {
        FXMLLoader f1=new FXMLLoader(getClass().getResource("AddAdmin.fxml"));
        Parent root = f1.load();
        AddAdminController controller1=f1.getController();
        if (!controller1.backClicked)
        {
            try// initialisation du champ ID Administrator 
    {Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
       
        String data="SELECT ADMINS_AUTO_INCREMENT.NEXTVAL FROM DUAL";
        ResultSet rs; 
        rs=conn.getStmt().executeQuery(data);
        rs.next();
        
        controller1.idadminn=Long.parseLong(rs.getString("NEXTVAL"));
         
        conn.getConDB().close();
        conn.getStmt().close();
        
    }    
        catch (SQLException e){
           Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR Dialog");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
       } 
        }
        controller1.id_admin_txt.setText(Long.toString(controller1.idadminn));
        Stage pop_up=new Stage();
        Scene scene = new Scene(root,Color.TRANSPARENT);
        pop_up.initStyle(StageStyle.TRANSPARENT);
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
           {pop_up.setX(event.getScreenX() - xOffset);
           pop_up.setY(event.getScreenY() - yOffset);
                         
           }
        });
        pop_up.setScene(scene);               
        pop_up.centerOnScreen();
        pop_up.show();
            pop_up.setOnHidden(e -> {// pour rafraîchir la table après l'insertion d'un administrateur
                loadData();
                });
            
        new FadeIn(root).play();//animation
    }
     else {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION DIALOG");
        alert.setContentText("You have already opened this window !");
        alert.showAndWait();
    }
    
    }
    
    public void loadData()//loading the data from the database into the tableview
    {
        try{
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        String data="SELECT * FROM Administrators ORDER BY ID_ADMIN";
        ResultSet rs; 
        rs=conn.getStmt().executeQuery(data);
        myList.clear();
            
        ImageView img;
        
        
            
           while(rs.next())
           {
            if (rs.getString("PROFILE_PICTURE")!=null)
               img=new ImageView(new Image(rs.getString("PROFILE_PICTURE")));
            else img=new ImageView();
            
            Date d =rs.getDate("BIRTH_DATE");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDate = dateFormat.format(d);  
            myList.add(new AdminRegistration (rs.getLong("ID_ADMIN"),rs.getString("FIRST_NAME"),rs.getString("LAST_NAME"),strDate,rs.getString("GENDER"),rs.getString("PROFESSION"),rs.getString("ADDRESS"),rs.getString("EMAIL"),rs.getString("LOGIN"),rs.getString("PASSWORD"),img));
                           
           }
            
        myTableView.setItems(myList);
        
        for (int i=0;i<myList.size();i++)
        {
        AdminRegistration ad=myList.get(i);
            myList.get(i).getDell().setOnMouseClicked(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {deleteRow(ad);
            
            }
            
        });
           myList.get(i).getEd().setOnMouseClicked(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {   try {
                updateRow(ad);
                } catch (IOException ex) {
                    Logger.getLogger(AdministratorManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            
        });}
        conn.getStmt().close();
        conn.getConDB().close();          

       }catch (Exception e){
           Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText(e.getMessage());
               alert.showAndWait();
       }
        
    }
    
    private void updateRow(AdminRegistration x) throws IOException
    { nbr2++; 
    if (nbr2==1)//pour ne pas ouvrir plusieurs fois la même fenêtre
    {        
        FXMLLoader f2 = new FXMLLoader(getClass().getResource("UpdateAdmin.fxml"));
        Parent root2=f2.load();
        Stage pop_up2=new Stage();
        Scene scene2 = new Scene(root2,Color.TRANSPARENT);
        pop_up2.initStyle(StageStyle.TRANSPARENT);
        UpdateAdminController controller2=f2.getController();
       
        if (x.getProfile_picture().getImage()==null)
        {controller2.profilePicture.setImage(new Image("file:/C:/Users/hp/Desktop/JAVAProject/src/images/addAdmin-100.png"));
        controller2.deleteMyImage.setVisible(false);}
        else {controller2.profilePicture.setImage(x.getProfile_picture().getImage());
        controller2.deleteMyImage.setVisible(true);
        }
        controller2.id_admin_txt.setText(Long.toString(x.getIDAdmin()));
     controller2.firstName.setText(x.getFirst_name()); 
     controller2.lastName.setText(x.getLast_name());
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
   //convert String to LocalDate
    LocalDate localDate = LocalDate.parse(x.getDate_birth(), formatter);
     controller2.BirthDate.setValue(localDate);
     
     if (x.getGender().equals("Male"))
         controller2.male.setSelected(true);
     else controller2.female.setSelected(true);
     
     controller2.profession.setValue(x.getProfession());
     controller2.address.setText(x.getAddress());
     controller2.email.setText(x.getEmail());
     controller2.login.setText(x.getLogin());
     controller2.ancienLogin=x.getLogin();
     controller2.password.setText(x.getPasswrd());
     controller2.passwordtxt.setText(x.getPasswrd());
     if (x.getProfile_picture().getImage()!=null)
     controller2.path=x.getProfile_picture().getImage().impl_getUrl();
        //to make the window draggable (you can move it)
        root2.setOnMousePressed(new EventHandler <MouseEvent>(){
            @Override
            public void handle(MouseEvent event)
            {xOffset = event.getSceneX();
            yOffset = event.getSceneY();
                
            }
        });
        root2.setOnMouseDragged(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event)
           {pop_up2.setX(event.getScreenX() - xOffset);
           pop_up2.setY(event.getScreenY() - yOffset);
                         
           }
        });
        pop_up2.setScene(scene2);               
        pop_up2.centerOnScreen();
        pop_up2.show();
            pop_up2.setOnHidden(e -> {// pour rafraichir la table après la mise à jour d'un administrateur
                loadData();
                });
            
        new FadeIn(root2).play();//animation
    }
    else {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION DIALOG");
        alert.setContentText("There's a window already opened!\nPlease close it before!");
        alert.showAndWait();
    }
        
    }
       
    private void deleteRow(AdminRegistration x)
    {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION DIALOG");
        alert.setContentText("Are you sure to delete ?");
        Optional <ButtonType> action= alert.showAndWait();
        
        if (action.get()==ButtonType.OK)
            {try
                {
                Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
                String delete="Delete from Administrators where ID_ADMIN="+x.getIDAdmin();
                    
                int rs; 
                rs=conn.getStmt().executeUpdate(delete);

                if (rs==0)
                       {Alert alert2=new Alert(Alert.AlertType.ERROR);
                       alert2.setTitle("ERROR DIALOG");
                       alert2.setContentText("The removal has failed !");
                       alert2.showAndWait();
                       }else
                       { Alert alert3=new Alert(Alert.AlertType.CONFIRMATION);
                       alert3.setTitle("CONFIRMATION DIALOG");
                       alert3.setContentText("Congratulations !\nThe removal has succeeded !");
                       alert3.showAndWait();
                       loadData();
                       if (Register_SignInForms.UserRegistration.loginUser.equals(x.getLogin()))
                        {Alert alert2=new Alert(Alert.AlertType.INFORMATION);
                       alert2.setTitle("INFORMATION DIALOG");
                       alert2.setContentText("We hope we see you again !");
                       alert2.showAndWait(); Register_SignInForms.UserRegistration.loginUser=null;
               
                       Stage stage = (Stage) add_button.getScene().getWindow();
                        stage.close();
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
                                       }

                conn.getStmt().close();
                conn.getConDB().close();
    }
        
    catch (Exception e){
           Alert alert4=new Alert(Alert.AlertType.ERROR);
               alert4.setTitle("ERROR DIALOG");
               alert4.setContentText(e.getMessage());
               alert4.showAndWait();
       }
            }
    }
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        IdCol.setCellValueFactory(new PropertyValueFactory("IDAdmin"));       
        first_nameCol.setCellValueFactory(new PropertyValueFactory("first_name"));
        last_nameCol.setCellValueFactory(new PropertyValueFactory("last_name"));
        birthdateCol.setCellValueFactory(new PropertyValueFactory("date_birth"));
        genderCol.setCellValueFactory(new PropertyValueFactory("gender"));
        professionCol.setCellValueFactory(new PropertyValueFactory("profession"));
        addressCol.setCellValueFactory(new PropertyValueFactory("address"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        loginCol.setCellValueFactory(new PropertyValueFactory("login"));
        passwordCol.setCellValueFactory(new PropertyValueFactory("passwrd"));
        profilePictureCol.setCellValueFactory(new PropertyValueFactory("profile_picture"));      
        editCol.setCellValueFactory(new PropertyValueFactory("ed"));    
        deleteCol.setCellValueFactory(new PropertyValueFactory("dell"));
     
        loadData();
      
}
}
    

