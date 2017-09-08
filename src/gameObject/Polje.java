/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Drakulic
 */
public class Polje extends Group {
    
    
    Figura figura=null;
    boolean imaFigura=false;
    float r;
    Circle krug;
    
    public Polje(float radius){
        r=radius;
        krug=new Circle(radius);
        Image slika=new Image("resources/polje.jpg");
        krug.setFill(new ImagePattern(slika));
        krug.setRotationAxis(Rotate.X_AXIS);
        krug.setRotate(90);
        getChildren().add(krug);
    }

    public Figura getFigura() {
        return figura;
    }

    public void setFigura(Figura figura) {    
        this.figura = figura;
       figura.setTranslateX(this.getLocalToSceneTransform().getTx());
       figura.setTranslateZ(this.getLocalToSceneTransform().getTz());
        imaFigura=true;
    }

    public boolean ImaFigura() {
        return imaFigura;
    }

    public void setColor(Color color){
        krug.setFill(color);  
    }
    
    public void setManjiKrug(Color c){
        double rad=r;
        Circle k=new Circle(rad/2);
        k.setFill(c);
        k.setTranslateY(-2);
        k.setRotationAxis(Rotate.X_AXIS);
        k.setRotate(90);
        getChildren().add(k);
    }
    public void removeFigura(){
        imaFigura=false;
        figura=null;
    }
    
}
