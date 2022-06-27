/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserPanel.UserManagementCH;

/**
 *
 * @author selmi
 */
public class Needs {
        private String SUPPLYNEEDS ; 
	private String EQUIPMENTNEEDS ; 
        private String DONATIONSNEEDS ; 

    public Needs(String SUPPLYNEEDS, String EQUIPMENTNEEDS, String DONATIONSNEEDS) {
        this.SUPPLYNEEDS = SUPPLYNEEDS;
        this.EQUIPMENTNEEDS = EQUIPMENTNEEDS;
        this.DONATIONSNEEDS = DONATIONSNEEDS;
    }

    public String getSUPPLYNEEDS() {
        return SUPPLYNEEDS;
    }

    public String getEQUIPMENTNEEDS() {
        return EQUIPMENTNEEDS;
    }

    public void setSUPPLYNEEDS(String SUPPLYNEEDS) {
        this.SUPPLYNEEDS = SUPPLYNEEDS;
    }

    public void setEQUIPMENTNEEDS(String EQUIPMENTNEEDS) {
        this.EQUIPMENTNEEDS = EQUIPMENTNEEDS;
    }

    public void setDONATIONSNEEDS(String DONATIONSNEEDS) {
        this.DONATIONSNEEDS = DONATIONSNEEDS;
    }

    
    public String getDONATIONSNEEDS() {
        return DONATIONSNEEDS;
    }

    @Override
    public String toString() {
        return "Needs{" + "SUPPLYNEEDS=" + SUPPLYNEEDS + ", EQUIPMENTNEEDS=" + EQUIPMENTNEEDS + ", DONATIONSNEEDS=" + DONATIONSNEEDS + '}';
    }
    
}
