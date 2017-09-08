/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author Drakulic
 */
public class Figura extends Group{
    
    PointLight pl;
    public int pozicija=-1;
    public boolean mozeDaIgra=false;
    Sphere glava;
   
    public Color c;
    
    public Figura(float radius,float visina,Color color){
        c=color;
        
        pl=new PointLight(color);
        PhongMaterial mat=new PhongMaterial(color);
        Group telo=makeCone(radius,visina,mat);
        telo.setRotationAxis(Rotate.X_AXIS);
        telo.setRotate(90);
        glava=new Sphere(radius/3*2);
        glava.setMaterial(mat);
        glava.setTranslateY(-visina+radius/3*3);
        setTranslateY(-visina/2);
        getChildren().addAll(telo,glava);
       
    }
  
    
    private Group makeCone(float radius, float height, PhongMaterial material) {
        Group cone = new Group();

        int numberOfTriangles = 360;

        float[] points = new float[numberOfTriangles * 3 * 3];
        float[] textCoords = {
                0.5f, 0,
                0, 1,
                1, 1
        };
        int[] faces = new int[numberOfTriangles * 3 * 2];

        for (int i = 0; i < numberOfTriangles; i++) {
            int index = i * 9;
            points[index] = 0;
            points[index + 1] = 0;
            points[index + 2] = height / 2;
            points[index + 3] = (float)Math.cos(Math.toRadians(i)) * radius;
            points[index + 4] = (float)Math.sin(Math.toRadians(i)) * radius;
            points[index + 5] = - height / 2;
            points[index + 6] = (float)Math.cos(Math.toRadians(i + 1)) * radius;
            points[index + 7] = (float)Math.sin(Math.toRadians(i + 1)) * radius;
            points[index + 8] = - height / 2;
        }

        for (int i = 0; i < numberOfTriangles; i++) {
            int index = i * 6;
            faces[index] = i * 3;
            faces[index + 1] = 0;
            faces[index + 2] = i * 3 + 1;
            faces[index + 3] = 1;
            faces[index + 4] = i * 3 + 2;
            faces[index + 5] = 2;
        }

        TriangleMesh mesh = new TriangleMesh();
        mesh.getPoints().addAll(points);
        mesh.getTexCoords().addAll(textCoords);
        mesh.getFaces().addAll(faces);

        MeshView meshView = new MeshView();
        meshView.setMesh(mesh);
        meshView.setMaterial(material);

        Cylinder circle = new Cylinder(radius, 0.1);
        circle.setMaterial(material);
        circle.setTranslateZ(- height / 2);
        circle.setRotationAxis(Rotate.X_AXIS);
        circle.setRotate(90);


        cone.getChildren().addAll(meshView, circle);

        return cone;
    }
    
   
    public void omoguciIgranje(){
     
     PhongMaterial m=new PhongMaterial(Color.CADETBLUE);
     glava.setMaterial(m);
        mozeDaIgra=true;
              
    }
    public void odigrao(){
        mozeDaIgra=false;
        PhongMaterial m=new PhongMaterial(c);
        glava.setMaterial(m); 
        
     
    }
    
    
    
    
}
