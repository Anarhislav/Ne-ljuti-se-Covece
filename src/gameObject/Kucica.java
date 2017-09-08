/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Drakulic
 */
public class Kucica extends Group {

    
    Polje polje1 ;
    Polje polje2 ;
    Polje polje3;
    Polje polje4 ;
    
    
    
    public Kucica(float r, float p) {
        Circle kuca = new Circle(r);
        kuca.setTranslateY(6);
        kuca.setRotationAxis(Rotate.X_AXIS);
        kuca.setRotate(90);
      polje1 = new Polje(p);
       polje2 = new Polje(p);
       polje3 = new Polje(p);
        polje4 = new Polje(p);
        polje1.setTranslateX(r / 2);
        polje2.setTranslateX(-r / 2);
        polje3.setTranslateZ(r / 2);
        polje4.setTranslateZ(-r / 2);
        Image slika = new Image("resources/kuca.jpg");
        kuca.setFill(new ImagePattern(slika));
        getChildren().addAll(kuca, polje1, polje2, polje3, polje4);

    }
    
    
    public void addFigura(Figura figura){
        
        if(!polje1.ImaFigura()){
            polje1.setFigura(figura);
        }else
            if(!polje2.ImaFigura()){
            polje2.setFigura(figura);
        }else
                if(!polje4.ImaFigura()){
            polje4.setFigura(figura);
        }else
       if(!polje3.ImaFigura()){
            polje3.setFigura(figura);
        }  
    }

    public boolean sviKuci() {
        if(polje1.ImaFigura()&&polje2.ImaFigura()&&polje3.ImaFigura()&&polje4.ImaFigura())
            return true;
        else return false;
    }
    public Polje nadjiMesto(){
        Polje p=null;
        if(!polje1.ImaFigura())
            p=polje1;
        if(!polje2.ImaFigura())
            p=polje2;
        if(!polje3.ImaFigura())
            p=polje3;
        if(!polje4.ImaFigura())
            p=polje4;
        
        return p;
    }
   
     public void removeFigura(Figura f){
         if(polje1.getFigura()==f) polje1.removeFigura();
         if(polje2.getFigura()==f) polje2.removeFigura();
         if(polje3.getFigura()==f) polje3.removeFigura();
         if(polje4.getFigura()==f) polje4.removeFigura();
     }

}
