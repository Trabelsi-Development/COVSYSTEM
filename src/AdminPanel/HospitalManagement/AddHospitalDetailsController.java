/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.HospitalManagement;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddHospitalDetailsController implements Initializable {

    public String hcases="",bavailable="",doctorsn="",roomsn="",totaltests="";    
    public static boolean success=false; 
    public static String supp="",equip="",donation="";
    
    @FXML
    private Label donation_needs;
    @FXML
    private Label supply_needs;
    @FXML
    private Button saveAdd;
    @FXML
    public TextArea EquipmentNeedsArea;
    @FXML
    private Label cityname;
    @FXML
    private ImageView back;
    @FXML
    private ImageView rightSide;
    @FXML
    public TextField hospitalName;
    @FXML
    public TextArea supplyNeedsArea;
    @FXML
    public TextField cityName;
    @FXML
    private Label equipement_needs;
    @FXML
    private Label add_title;
    @FXML
    private Label hospitalname;
    @FXML
    public TextArea DonationNeedsArea;

    
    @FXML
    void saveAdd_MouseClicked(MouseEvent event) throws IOException {
         HospitalRegistration regH=new HospitalRegistration(); 
        regH.setHospitalName(hospitalName.getText());
        
        regH.setHospitalizedCases(hcases);
        
        if (!bavailable.equals("")) 
        regH.setAvailableBeds(bavailable);   
        else regH.setAvailableBeds(null);
        
        if (!doctorsn.equals("")) 
        regH.setDoctorsNumber(doctorsn);
        else regH.setDoctorsNumber(null);  
        
       if (!roomsn.equals("")) 
        regH.setRoomsNumber(roomsn); 
       else regH.setRoomsNumber(null);
        
        if (!totaltests.equals(""))
        regH.setTotalTests(totaltests);
        else   regH.setTotalTests(null);
        
        if (!cityName.getText().equals(""))
        regH.setCityName(cityName.getText());
        else   regH.setCityName(null);
        
        
        regH.setSupplyNeeds(supplyNeedsArea.getText());
        regH.setEquipmentNeeds(EquipmentNeedsArea.getText());
        regH.setDonationNeeds(DonationNeedsArea.getText());

        Date d=new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String strDate = dateFormat.format(d);  
        regH.setUpdateDate(strDate);  
        
        
             try{//insertion du nouvel hôpital dans la BD puis rafraîchissement de la tableview 
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        String row="INSERT INTO HOSPITALS (HOSPITALNAME, HOSPITALIZEDCASES, BEDSAVAILABLE, DOCTORSNUMBER, ROOMSNUMBER, TOTALTESTS, SUPPLYNEEDS, EQUIPMENTNEEDS,DONATIONSNEEDS, DATEUPDATE, CITYNAME) VALUES ('"+regH.getHospitalName()+"',"+regH.getHospitalizedCases()+','+regH.getAvailableBeds()+','+regH.getDoctorsNumber()+','+regH.getRoomsNumber()+','+regH.getTotalTests()+",'"+regH.getSupplyNeeds()+"','"+regH.getEquipmentNeeds()+"','"+regH.getDonationNeeds()+"', TO_DATE('"+strDate+"'),'"+regH.getCityName()+"')";
                       
        int rss=conn.getStmt().executeUpdate(row); 
        
        if (rss==0)
               {Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR DIALOG");
               alert.setContentText("The registration has failed !");
               alert.showAndWait();
               }else
               { Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
               alert.setTitle("CONFIRMATION DIALOG");
               alert.setContentText("Congratulations !\nThe registration has succeeded !");
               alert.showAndWait();
               success=true;
               Stage stage = (Stage) saveAdd.getScene().getWindow();
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
       }
        
       
    }

    @FXML
    void saveAdd_MouseEntered(MouseEvent event) {
          saveAdd.setLayoutY(622);
    }

    @FXML
    void saveAdd_MouseExited(MouseEvent event) {
          saveAdd.setLayoutY(632);
    }

    @FXML
    void backMouseClicked(MouseEvent event) {
        supp=supplyNeedsArea.getText();
        equip=EquipmentNeedsArea.getText();
        donation=DonationNeedsArea.getText();
        Stage stage = (Stage) back.getScene().getWindow();
         stage.close();
    }

    @FXML
    void backMouseEntered(MouseEvent event) {
        back.setLayoutY(627);
    }

    @FXML
    void backMouseExited(MouseEvent event) {
        back.setLayoutY(637);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
