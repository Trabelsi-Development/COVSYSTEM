/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminPanel.HospitalManagement;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class UpdateHospitalDetailsController implements Initializable {

    public String hcasesu="",bavailableu="",doctorsnu="",roomsnu="",totaltestsu="",UPDate="";    
    public static boolean successUpdate=false; 
    
    @FXML
    private Label donation_needs;

    @FXML
    private Label supply_needs;

    @FXML
    private Label cityname;

    @FXML
    private Label update_title;

    @FXML
    private ImageView back;

    @FXML
    private ImageView rightSide;

    @FXML
    public TextField hospitalName;

    @FXML
    public TextArea supply_NeedsArea;

    @FXML
    private Label general_informations;

    @FXML
    public TextField cityName;

    @FXML
    public TextArea Equipment_NeedsArea;
    @FXML
    public TextArea Donation_NeedsArea;

    @FXML
    private Button saveUpdate;

    @FXML
    private Label hospitalname;

    
    @FXML
    void saveUpdate_MouseClicked(MouseEvent event) throws IOException {
         HospitalRegistration regH=new HospitalRegistration(); 
        regH.setHospitalName(hospitalName.getText());
        
        regH.setHospitalizedCases(hcasesu);
        
        if (!bavailableu.equals("")) 
        regH.setAvailableBeds(bavailableu);   
        else regH.setAvailableBeds(null);
        
        if (!doctorsnu.equals("")) 
        regH.setDoctorsNumber(doctorsnu);
        else regH.setDoctorsNumber(null);  
        
       if (!roomsnu.equals("")) 
        regH.setRoomsNumber(roomsnu); 
       else regH.setRoomsNumber(null);
        
        if (!totaltestsu.equals(""))
        regH.setTotalTests(totaltestsu);
        else   regH.setTotalTests(null);
        
        if (!cityName.getText().equals(""))
        regH.setCityName(cityName.getText());
        else   regH.setCityName(null);
        
        if (supply_NeedsArea.getText()!=null)
            regH.setSupplyNeeds(supply_NeedsArea.getText());
        if (Equipment_NeedsArea.getText()!=null)
            regH.setEquipmentNeeds(Equipment_NeedsArea.getText());        
        if (Donation_NeedsArea.getText()!=null)
            regH.setDonationNeeds(Donation_NeedsArea.getText());

        Date d=new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String strDate = dateFormat.format(d);  
        regH.setUpdateDate(strDate);  
        
        
             try{//insertion du nouvel hôpital dans la BD puis rafraîchissement de la tableview 
        Register_SignInForms.ConnexionDB conn= new Register_SignInForms.ConnexionDB();
        Boolean trouve;
        String check ="SELECT * FROM HOSPITALS WHERE HOSPITALNAME='"+regH.getHospitalName()+"' and DATEUPDATE=TO_DATE('"+strDate+"')";
        ResultSet rs= conn.getStmt().executeQuery(check);
            if (!rs.next())//pour verifier si l'admin a déjà effectué une mise à jour à cet hôpital aujourd'hui ou pas sinon il y aura une erreur de duplication au niveau des clés primaires (COUNTRY et DATEUPDATE) 
                trouve=false;
            else trouve=true;

            String row; 
            
            if (!UPDate.equals(strDate) && !trouve)    
            {row="INSERT INTO HOSPITALS (HOSPITALNAME, HOSPITALIZEDCASES, BEDSAVAILABLE, DOCTORSNUMBER, ROOMSNUMBER, TOTALTESTS,SUPPLYNEEDS, EQUIPMENTNEEDS, DONATIONSNEEDS, DATEUPDATE, CITYNAME) VALUES ('"+regH.getHospitalName()+"',"+regH.getHospitalizedCases()+','+regH.getAvailableBeds()+','+regH.getDoctorsNumber()+','+regH.getRoomsNumber()+','+regH.getTotalTests()+",'"+regH.getSupplyNeeds()+"','"+regH.getEquipmentNeeds()+"','"+regH.getDonationNeeds()+"', TO_DATE('"+strDate+"'),'"+regH.getCityName()+"')";
            }
        
            else //update de la nouvelle mise à jour d'un hôpital bien déterminé puisque ce n'est pas sa première mise à jour aujourd'hui dans la BD puis rafraîchissement de la tableview
            {           
            row="UPDATE HOSPITALS SET HOSPITALIZEDCASES="+regH.getHospitalizedCases()+','
                    + "BEDSAVAILABLE="+regH.getAvailableBeds()+','
                    + "DOCTORSNUMBER="+regH.getDoctorsNumber()+','
                    + "ROOMSNUMBER="+regH.getRoomsNumber()+','
                    + "TOTALTESTS="+regH.getTotalTests()+','
                    + "SUPPLYNEEDS='"+regH.getSupplyNeeds()+"',"
                    + "EQUIPMENTNEEDS='"+regH.getEquipmentNeeds()+"',"
                    + "DONATIONSNEEDS='"+regH.getDonationNeeds()+"',"                    
                    + "DATEUPDATE= TO_DATE('"+strDate+"') where HOSPITALNAME='"+regH.getHospitalName()+"' and DATEUPDATE= TO_DATE('"+strDate+"')";

                 }
            
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
               successUpdate=true;
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
       }
        
       
    }

    @FXML
    void saveUpdate_MouseEntered(MouseEvent event) {
          saveUpdate.setLayoutY(622);
    }

    @FXML
    void saveUpdate_MouseExited(MouseEvent event) {
          saveUpdate.setLayoutY(632);
    }

    @FXML
    void backMouseClicked(MouseEvent event) {
        UpdateHospitalGeneralController.supply=supply_NeedsArea.getText();
        UpdateHospitalGeneralController.equip=Equipment_NeedsArea.getText();
        UpdateHospitalGeneralController.donation=Donation_NeedsArea.getText();
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
