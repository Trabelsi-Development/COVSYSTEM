/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserPanel.UserManagementCH;

import javafx.scene.control.Button;

/**
 *
 * @author selmi
 */
public class HospitalsInfo {
    private String Name ;//hospital name
    private String City ;//city name
    private double Hc ;//hospital cases
    private double Bav ;//beds available
    private int nbrDoctor ;//number of doctors 
    private int nbrrooms ;//number of romms 
    private double Tt ;//totlal tests
    private Button btn ;//btn consult needs

    public HospitalsInfo(String Name, String City, double Hc, double Bav, int nbrDoctor, int nbrrooms, double Tt, Button btn) {
        this.Name = Name;
        this.City = City;
        this.Hc = Hc;
        this.Bav = Bav;
        this.nbrDoctor = nbrDoctor;
        this.nbrrooms = nbrrooms;
        this.Tt = Tt;
        this.btn = btn;
    }

   
    public String getCity() {
        return City;
    }

   
    public String getName() {
        return Name;
    }

    public double getHc() {
        return Hc;
    }

    public double getBav() {
        return Bav;
    }

    public Button getBtn() {
        return btn;
    }

    public int getNbrDoctor() {
        return nbrDoctor;
    }

    public int getNbrrooms() {
        return nbrrooms;
    }

    public double getTt() {
        return Tt;
    }

    public void setNbrDoctor(int nbrDoctor) {
        this.nbrDoctor = nbrDoctor;
    }

    public void setNbrrooms(int nbrrooms) {
        this.nbrrooms = nbrrooms;
    }

    public void setTt(double Tt) {
        this.Tt = Tt;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setHc(double Hc) {
        this.Hc = Hc;
    }

    public void setBav(double Bav) {
        this.Bav = Bav;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public void setCity(String City) {
        this.City = City;
    }

    @Override
    public String toString() {
        return "HospitalsInfo{" + "Name=" + Name + ", Hc=" + Hc + ", Bav=" + Bav + ", btn=" + btn + '}';
    }
 
    
    
    
    
}
