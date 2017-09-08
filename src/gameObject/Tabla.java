/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Drakulic
 */
public class Tabla extends Group{
    
    
    public Tabla(float f){
        
        Box tabla=new Box(f, 50, f);
        tabla.setTranslateY(40);
        PhongMaterial mat=new PhongMaterial();
        mat.setDiffuseMap(new Image("resources/zid.jpg"));
        tabla.setMaterial(mat);
        
        Rectangle ploca=new Rectangle(f, f);
        ploca.setTranslateY(-f/2+12);
        ploca.setTranslateX(-f/2);
        ploca.setRotationAxis(Rotate.X_AXIS);
        ploca.setRotate(90);
        ploca.setFill(new ImagePattern(new Image("resources/ploca.jpg")));
        this.getChildren().addAll(tabla,ploca);
    }
    
    
}
