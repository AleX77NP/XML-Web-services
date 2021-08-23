/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devserver;

/**
 *
 * @author aleksandarmilanovic
 */
public class PLanguage {
    private String naziv;
    private String kompanija;
    private String imgurl;
    private String opis;
    
    PLanguage(String n, String k, String i, String o) {
        this.naziv = n;
        this.kompanija = k;
        this.imgurl = i;
        this.opis = o;
    }
    
    public String getNaziv() {
        return this.naziv;
    }
    public String getKompanija() {
        return this.kompanija;
    }
    public String getImgurl() {
        return this.imgurl;
    }
    public String getOpis() {
        return this.opis;
    }
    
    @Override
    public String toString() { 
        return String.format(naziv + "#" + kompanija + "#" + imgurl + "#" + opis); 
    } 
}
