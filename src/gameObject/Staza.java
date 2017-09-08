/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.paint.Color;

/**
 *
 * @author Drakulic
 */
public class Staza extends Group {

    List<Polje> staza = new ArrayList();

   public List<Polje> krajCrveni = new ArrayList();
 public   List<Polje> krajZeleni = new ArrayList();
 public   List<Polje> krajPurple = new ArrayList();
  public  List<Polje> krajOrange = new ArrayList();

 public   List<Polje> putanjaCrveni = new ArrayList();
 public   List<Polje> putanjaZeleni = new ArrayList();
 public   List<Polje> putanjaOrange = new ArrayList();
 public   List<Polje> putanjaPurple = new ArrayList();
    
    
    
    
public    Polje pocetakCrveni;
public    Polje pocetakZeleni;
 public   Polje pocetakPurple;
 public  Polje pocetakOrange;

    public Staza(float polje_precnik) {

        double razmak = 80;
        double xpoc = -razmak, zpoc = -410;
        for (int i = 0; i < 5; i++) {    //vert red
            Polje polje = new Polje(polje_precnik);
            polje.setTranslateX(xpoc);
            //=  zpoc=zpoc+i*razmak;
            polje.setTranslateZ(zpoc + i * razmak);
            if (i == 0) {
                pocetakPurple = polje;
                polje.setManjiKrug(Color.PURPLE);
            }
            staza.add(polje);
            getChildren().add(polje);
        }
        xpoc = xpoc - razmak;
        zpoc = zpoc + 4 * razmak;
        for (int i = 0; i < 4; i++) {  //h red
            Polje polje = new Polje(polje_precnik);

            polje.setTranslateX(xpoc - i * razmak);

            polje.setTranslateZ(zpoc);

            staza.add(polje);
            getChildren().add(polje);
        }

        xpoc = xpoc - 3 * razmak;
        zpoc = zpoc + razmak;
        Polje p = new Polje(polje_precnik);
        p.setTranslateX(xpoc);
        p.setTranslateZ(zpoc);
        staza.add(p);
        getChildren().add(p);
        
        for (int i = 1; i < 5; i++) {  //h red
            Polje polje = new Polje(polje_precnik);

            polje.setTranslateX(xpoc + i * razmak);

            polje.setTranslateZ(zpoc);
            polje.setColor(Color.GREEN);
            krajZeleni.add(polje);
            getChildren().add(polje);
        }
        
        
        zpoc = zpoc + razmak;

        for (int i = 0; i < 4; i++) {  //vertikalni red
            Polje polje = new Polje(polje_precnik);

            polje.setTranslateX(xpoc + i * razmak);

            polje.setTranslateZ(zpoc);

            if (i == 0) {
                pocetakZeleni = polje;
                polje.setManjiKrug(Color.GREEN);

            }

            staza.add(polje);
            getChildren().add(polje);
        }

        xpoc += 4 * razmak;

        for (int i = 0; i < 5; i++) {    //vert red
            Polje polje = new Polje(polje_precnik);
            polje.setTranslateX(xpoc);

            polje.setTranslateZ(zpoc + i * razmak);

            staza.add(polje);
            getChildren().add(polje);
        }

        zpoc += 4 * razmak;
        xpoc += razmak;

        Polje p1 = new Polje(polje_precnik);
        p1.setTranslateX(xpoc);
        p1.setTranslateZ(zpoc);
        staza.add(p1);
        getChildren().add(p1);

        
        for (int i = 1; i < 5; i++) {  //h red
            Polje polje = new Polje(polje_precnik);

            polje.setTranslateX(xpoc);

            polje.setTranslateZ(zpoc-i*razmak);
            polje.setColor(Color.SALMON);
            krajCrveni.add(polje);
            getChildren().add(polje);
        }
        
        
        
        xpoc += razmak;

        for (int i = 0; i < 5; i++) {    //vert red
            Polje polje = new Polje(polje_precnik);
            polje.setTranslateX(xpoc);

            polje.setTranslateZ(zpoc - i * razmak);
            if (i == 0) {
                pocetakCrveni = polje;
                polje.setManjiKrug(Color.SALMON);
            }
            staza.add(polje);
            getChildren().add(polje);
        }
        zpoc -= 4 * razmak;
        xpoc += razmak;

         for (int i = 0; i < 4; i++) {  //h red
            Polje polje = new Polje(polje_precnik);

            polje.setTranslateX(xpoc + i * razmak);

            polje.setTranslateZ(zpoc);

            staza.add(polje);
            getChildren().add(polje);
        }
         
        xpoc+=3*razmak;
        zpoc-=razmak;
        
        
        Polje p2 = new Polje(polje_precnik);
        p2.setTranslateX(xpoc);
        p2.setTranslateZ(zpoc);
        staza.add(p2);
        getChildren().add(p2);       
        
        
        
        for (int i = 1; i < 5; i++) {  //h red
            Polje polje = new Polje(polje_precnik);

            polje.setTranslateX(xpoc - i * razmak);

            polje.setTranslateZ(zpoc);
            polje.setColor(Color.YELLOW);
            krajOrange.add(polje);
            getChildren().add(polje);
        }
        
        zpoc-=razmak;
        
        
        for (int i = 0; i < 4; i++) {  //vertikalni red
            Polje polje = new Polje(polje_precnik);

            polje.setTranslateX(xpoc - i * razmak);

            polje.setTranslateZ(zpoc);

            if (i == 0) {
               pocetakOrange = polje;
                polje.setManjiKrug(Color.YELLOW);

            }

            staza.add(polje);
            getChildren().add(polje);
        }

        xpoc -= 4 * razmak;
        
        
        
         for (int i = 0; i < 5; i++) {    //vert red
            Polje polje = new Polje(polje_precnik);
            polje.setTranslateX(xpoc);

            polje.setTranslateZ(zpoc - i * razmak);
           
            staza.add(polje);
            getChildren().add(polje);
        }
        zpoc -= 4 * razmak;
        xpoc -= razmak;
        
        
        Polje p3 = new Polje(polje_precnik);
        p3.setTranslateX(xpoc);
        p3.setTranslateZ(zpoc);
        staza.add(p3);
        getChildren().add(p3);       
        
         for (int i = 1; i < 5; i++) {  //h red
            Polje polje = new Polje(polje_precnik);

            polje.setTranslateX(xpoc);

            polje.setTranslateZ(zpoc+i*razmak);
            polje.setColor(Color.PURPLE);
            krajPurple.add(polje);
            getChildren().add(polje);
        }
         
         
         
         //crveni
         int index=staza.indexOf(pocetakCrveni);
         for(int i=0;i<staza.size();i++){
             
             
             
             putanjaCrveni.add(staza.get(index));
             index++;
             index=index%staza.size();
         }
         for(int i=0;i<krajCrveni.size();i++){
             putanjaCrveni.add(krajCrveni.get(i));
         }
         //zeleni
          int index1=staza.indexOf(pocetakZeleni);
        for(int i=0;i<staza.size();i++){
             
            
             
             putanjaZeleni.add(staza.get(index1));
             index1++;
             index1=index1%staza.size();
         }
         for(int i=0;i<krajZeleni.size();i++){
             putanjaZeleni.add(krajZeleni.get(i));
         }
         //orange
         int index2=staza.indexOf(pocetakOrange);
         for(int i=0;i<staza.size();i++){
             
             
             
             putanjaOrange.add(staza.get(index2));
             index2++;
             index2=index2%staza.size();
         }
         for(int i=0;i<krajOrange.size();i++){
             putanjaOrange.add(krajOrange.get(i));
         }
         //purple
         int index3=staza.indexOf(pocetakPurple);
         for(int i=0;i<staza.size();i++){
             
             
             
             putanjaPurple.add(staza.get(index3));
             index3++;
             index3=index3%staza.size();
         }
         for(int i=0;i<krajPurple.size();i++){
             putanjaPurple.add(krajPurple.get(i));
         }
         
         
         
         
    }

}
