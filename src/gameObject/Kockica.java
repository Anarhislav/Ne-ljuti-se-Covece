/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author Drakulic
 */
public class Kockica extends Group {
    
    MeshView kocka;
    public Color color=Color.GREEN;
    
    public Kockica (float size){
        
        float[] temena={
            
            -size/2f,size/2f,-size/2f, //t1
            size/2f,size/2f,-size/2f, //t2
            size/2f,size/2f,size/2f, //t3
            -size/2f,size/2f,size/2f, //t4
            
             -size/2f,-size/2f,-size/2f, //t5
            size/2f,-size/2f,-size/2f, //t6
            size/2f,-size/2f,size/2f, //t7
            -size/2f,-size/2f,size/2f //t8
            
        };
        
        float[] texture={
            
            0f,0.33f,   //v1
            0f,0.66f,   //v2
            0.25f,0.66f, //v3
            0.25f,0.33f, //v4
            0.5f,0.66f, //v5
            0.5f,0.33f, //v6
            0.75f,0.66f, //v7
            0.75f,0.33f, //v8
            1f,0.66f, //v9
            1f,0.33f, //v10
            0.5f,1f, //v11
            0.75f,1f, //v12
            0.5f,0f, //v13
            0.75f,0f //v14
                   
        };
        
        
        int[] stranice={
            
            5,7,4,5,0,4,0,4,1,6,5,7, //strana 1
            6,9,5,7,1,6,1,6,2,8,6,9, //strana 4
            7,12,4,5,5,7,5,7,6,13,7,12, //strana 5
            4,5,7,3,3,2,3,2,0,4,4,5, //strana 3
            0,4,3,10,2,11,2,11,1,6,0,4, //strana 2
            7,3,6,0,2,1,2,1,3,2,7,3 //strana 6
            
        };
        
        TriangleMesh mreza=new  TriangleMesh(); 
        mreza.getPoints().addAll(temena);
        mreza.getTexCoords().addAll(texture);
        mreza.getFaces().addAll(stranice);
        
        kocka=new MeshView();
        kocka.setMesh(mreza);
        PhongMaterial mat=new PhongMaterial(Color.BLANCHEDALMOND);
        Image image = new Image("resources/dice.png");
        mat.setDiffuseMap(image);
        mat.setSpecularColor(Color.WHITE);
        kocka.setTranslateY(-size/2);
        kocka.setMaterial(mat);
       this.getChildren().add(kocka);
    }
    
    
    
    public void postaviBoju(Color boja){
        
        PhongMaterial mat=new PhongMaterial(boja);
        mat.setDiffuseMap(new Image("resources/dice.png"));
        kocka.setMaterial(mat);
    } 
    
    
    public int baciKocku(){
        
        int broj=5;
        
       broj=(int) (Math.random()*6)+1;
       
        
        SequentialTransition st=new SequentialTransition(this);
       
        
     
        
       
       int smerx=90;
       int smerz=90;
        
        
        for(int i=0;i<6;i++){
          
            if(i==0){
                RotateTransition rx1=new RotateTransition(Duration.seconds(0.2), this);
                 rx1.setAxis(Rotate.X_AXIS);
                 rx1.setFromAngle(0);
                  rx1.setByAngle(smerx);
                 st.getChildren().add(rx1);
                RotateTransition rz1=new RotateTransition(Duration.seconds(0.2), this);
                  rz1.setAxis(Rotate.Z_AXIS); 
                st.getChildren().add(rz1);
               rz1.setFromAngle(0);
               rz1.setByAngle(smerz);
               continue;
            }
            
            
            
           
                RotateTransition rx1=new RotateTransition(Duration.seconds(0.2), this);
                 rx1.setAxis(Rotate.X_AXIS);
                 
                  rx1.setByAngle(smerx);
                 st.getChildren().add(rx1);
           
            
                 RotateTransition rz1=new RotateTransition(Duration.seconds(0.2), this);
                  rz1.setAxis(Rotate.Z_AXIS); 
                st.getChildren().add(rz1);
               
               rz1.setByAngle(smerz);
               
               if(i==5){
                   RotateTransition rz2=new RotateTransition(Duration.seconds(0.2), this);
                  rz2.setAxis(Rotate.Z_AXIS); 
                st.getChildren().add(rz2);
               
               rz2.setByAngle(smerz);
               }
           
        }
        
       switch(broj){
           
           case 1:RotateTransition rz1=new RotateTransition(Duration.seconds(0.5), this);
                  rz1.setAxis(Rotate.X_AXIS); 
                st.getChildren().add(rz1);
                if(smerx==-90)
               rz1.setByAngle(-90);
                else rz1.setByAngle(3*90);
               break;
               
           case 2:    
            RotateTransition rz2=new RotateTransition(Duration.seconds(0.5), this);
                  rz2.setAxis(Rotate.X_AXIS); 
                st.getChildren().add(rz2);
               if(smerx==-90)
               rz2.setByAngle(-180);
               else{
                   rz2.setByAngle(180);
               }
               break;
               
           case 6: RotateTransition rz6=new RotateTransition(Duration.seconds(0.5), this);
                  rz6.setAxis(Rotate.X_AXIS); 
                st.getChildren().add(rz6);
               if(smerx==-90)
               rz6.setByAngle(-180-90);
               else rz6.setByAngle(90);
               break;
               
               
           case 3:   RotateTransition rz3=new RotateTransition(Duration.seconds(0.5), this);
                  rz3.setAxis(Rotate.Z_AXIS); 
                st.getChildren().add(rz3);
               if(smerz==-90)
               rz3.setByAngle(-3*90);
               else rz3.setByAngle(90);
               break; 
             
             case 4:   RotateTransition rz4=new RotateTransition(Duration.seconds(0.5), this);
                  rz4.setAxis(Rotate.Z_AXIS); 
                st.getChildren().add(rz4);
               if(smerz==-90)
               rz4.setByAngle(-90);
               else rz4.setByAngle(3*90);
               break;    
               
               
          
       }
        
        
               
      
        
       
        st.setOnFinished(ev->{
            postaviBoju(color);   
        });
        
        st.play();
        
        
       
        
        
        
        
        return broj;
    }

    public void postaviBojuAfter(Color c) {
        if(c!=null)
       color=c;
    }
    
    
    
    
    
}
