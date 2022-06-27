/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserPanel.UserManagementCW;

/**
 *
 * @author selmi
 */
public class CoronaInfo {
    private String country  ;//country name
    private double Tc ;//Total cases
    private double Nc ;//new cases 
    private double Td ;//Total deaths
    private double Nd ;//new deaths
    private double Tr;//total recovered
    private double Ac ;//active cases
    private double Sc;//serious critical
    private double Tt ;//total Tests

    public CoronaInfo(String country, double Tc, double Nc, double Td, double Nd, double Tr, double Ac, double Sc, double Tt) {
        this.country = country;
        this.Tc = Tc;
        this.Nc = Nc;
        this.Td = Td;
        this.Nd = Nd;
        this.Tr = Tr;
        this.Ac = Ac;
        this.Sc = Sc;
        this.Tt = Tt;
    }

    public String getCountry() {
        return country;
    }

    public double getTc() {
        return Tc;
    }

    public double getNc() {
        return Nc;
    }

    public double getTd() {
        return Td;
    }

    public double getNd() {
        return Nd;
    }

    public double getTr() {
        return Tr;
    }

    public double getAc() {
        return Ac;
    }

    public double getSc() {
        return Sc;
    }

    public double getTt() {
        return Tt;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTc(double Tc) {
        this.Tc = Tc;
    }

    public void setNc(double Nc) {
        this.Nc = Nc;
    }

    public void setTd(double Td) {
        this.Td = Td;
    }

    public void setNd(double Nd) {
        this.Nd = Nd;
    }

    public void setTr(double Tr) {
        this.Tr = Tr;
    }

    public void setAc(double Ac) {
        this.Ac = Ac;
    }

    public void setSc(double Sc) {
        this.Sc = Sc;
    }

    public void setTt(double Tt) {
        this.Tt = Tt;
    }

    @Override
    public String toString() {
        return "CoronaInfo{" + "country=" + country + ", Tc=" + Tc + ", Nc=" + Nc + ", Td=" + Td + ", Nd=" + Nd + ", Tr=" + Tr + ", Ac=" + Ac + ", Sc=" + Sc + ", Tt=" + Tt + '}';
    }
    
    
    
    
}
