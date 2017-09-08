/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package igra;

import gameObject.Figura;
import gameObject.Kockica;
import gameObject.Kucica;
import gameObject.Polje;
import gameObject.Staza;
import gameObject.Tabla;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.AmbientLight;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Drakulic
 */
public class Igra extends Application {

    private static final float TABLA_SIZE = 1000f;
    private static final float FIGURA_VISINA = 50f;
    private static final float KOCKICA_SIZE = 30f;
    private static final float FIGURA_UGAO = 20f;
    private static final float POLJE_PRECNIK = 30;
    private static final float KUCICA_SIZE = 100;

    private static final Rotate rx = new Rotate();
    private static final Rotate ry = new Rotate();
    private static final Rotate rz = new Rotate();
    private static final Translate t = new Translate();

    private Tabla tabla;
    private int vrKocke;
    private List<Figura> crveneFigure = new ArrayList(4);
    private List<Figura> zeleneFigure = new ArrayList(4);
    private List<Figura> orangeFigure = new ArrayList(4);
    private List<Figura> purpleFigure = new ArrayList(4);
    private String naPotezu = "green";
    private String fazaIgre = "baciKocku";
    private Kucica crvenaKuca;
    private Kucica zelenaKuca;
    private Kucica orangeKuca;
    private Kucica purpleKuca;
    Group figure;
    int pokusaj = 0;
    private Kockica kocka;
    Staza staza;
    Camera kamera ;
    private Group gameObjects;
    ParallelCamera paralelnaKamera;
    PerspectiveCamera perspektivnaKamera;
Group root;
    @Override
    public void start(Stage primaryStage) {

         root = new Group();
         perspektivnaKamera=napraviKameru();
         paralelnaKamera=napraviParalelnu();
       
         kamera = perspektivnaKamera;

        setUpGame();
        
        kocka.postaviBoju(Color.GREEN);
        kocka.setOnMousePressed(ev -> {
            if (fazaIgre.equals("baciKocku")) {
                vrKocke = kocka.baciKocku();
                fazaIgre = "pomeriFiguru";
                if (vrKocke == 6) {
                    pokusaj = 0;
                }
                
                switch (naPotezu) {
                    
                    case "green":
                        kocka.postaviBojuAfter(Color.GREEN);
                        if (zelenaKuca.sviKuci() && vrKocke != 6) {
                           
                            pokusaj++;
                            if(pokusaj==4)pokusaj=1;
                            if (pokusaj == 3) {
                                naPotezu = "red";
                                fazaIgre = "baciKocku";
                                kocka.postaviBojuAfter(Color.SALMON);
                            } else {
                                fazaIgre = "baciKocku";
                            }
                          //  break;
                        }
                        else{
                        boolean ima = false;
                        for (Figura f : zeleneFigure) {
                            f.mozeDaIgra = daLiMozeZeleni(f);
                            if (daLiMozeZeleni(f) == true) {
                                ima = true;
                            }
                        }
                        if (!ima) {
                            naPotezu = "red";
                            fazaIgre = "baciKocku";
                            kocka.postaviBojuAfter(Color.SALMON);
                        }
                        }
                        break;
                    case "red":
                        kocka.postaviBojuAfter(Color.SALMON);
                        if (crvenaKuca.sviKuci() && vrKocke != 6) {
                           
                            pokusaj++;
                            if(pokusaj==4)pokusaj=1;
                            if (pokusaj == 3) {
                                naPotezu = "yellow";
                                fazaIgre = "baciKocku";
                                kocka.postaviBojuAfter(Color.ORANGE);
                            } else {
                                fazaIgre = "baciKocku";
                            }
                           // break;
                        }else{
                        boolean ima1 = false;
                        for (Figura f : crveneFigure) {
                            f.mozeDaIgra = daLiMozeCrveni(f);
                            if (daLiMozeCrveni(f) == true) {
                                ima1= true;
                            }
                        }
                        if (!ima1) {
                            naPotezu = "yellow";
                            fazaIgre = "baciKocku";
                            kocka.postaviBojuAfter(Color.ORANGE);
                        }
                        }
                        break;
                    case "purple":
                        kocka.postaviBojuAfter(Color.PURPLE);
                        if (purpleKuca.sviKuci() && vrKocke != 6) {
                            
                            pokusaj++;if(pokusaj==4)pokusaj=1;
                            if (pokusaj == 3) {
                                naPotezu = "green";
                                fazaIgre = "baciKocku";
                                kocka.postaviBojuAfter(Color.GREEN);
                            } else {
                                fazaIgre = "baciKocku";
                            }
                          //  break;
                        }else{
                      boolean  ima2 = false;
                        for (Figura f : purpleFigure) {
                            f.mozeDaIgra = daLiMozePurple(f);
                            if (f.mozeDaIgra == true) {
                                ima2 = true;
                            }
                        }
                        if (!ima2) {
                            naPotezu = "green";
                            fazaIgre = "baciKocku";
                            kocka.postaviBojuAfter(Color.GREEN);
                        }
                        }
                        break;
                    case "yellow":
                        kocka.postaviBojuAfter(Color.ORANGE);
                        if (orangeKuca.sviKuci() && vrKocke != 6) {
                           
                            pokusaj++;if(pokusaj==4)pokusaj=1;
                            if (pokusaj == 3) {
                                naPotezu = "purple";
                                fazaIgre = "baciKocku";
                                kocka.postaviBojuAfter(Color.PURPLE);
                            } else {
                                fazaIgre = "baciKocku";
                            }
                           // break;
                        }else{
                        boolean ima3 = false;
                        for (Figura f : orangeFigure) {
                            f.mozeDaIgra = daLiMozeOrange(f);
                            if (f.mozeDaIgra == true) {
                                ima3 = true;
                            }
                        }
                        if (!ima3) {
                            naPotezu = "purple";
                            fazaIgre = "baciKocku";
                            kocka.postaviBojuAfter(Color.PURPLE);
                        }
                        }
                        break;

                }

            }
         
        });

     
        
        
        
        AmbientLight globalnoSvetlo = new AmbientLight(Color.WHITE);
        PointLight svetlo = new PointLight(Color.WHITESMOKE);

        svetlo.getScope().addAll(figure);

        svetlo.setTranslateY(-500);

        Group nosac = new Group(kamera);
        rx.setAxis(Rotate.X_AXIS);
        ry.setAxis(Rotate.Y_AXIS);
        rz.setAxis(Rotate.Z_AXIS);

        nosac.getTransforms().addAll(t, rx, ry, rz);

        root.getChildren().addAll(gameObjects, nosac, globalnoSvetlo, svetlo);

        Scene scene = new Scene(root, 1500, 1000, true, SceneAntialiasing.BALANCED);
        scene.setCamera(kamera);
        scene.setFill(Color.BLACK);
        obradaDogadjaja(scene);
        primaryStage.setTitle("ne ljuti se covece");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private PerspectiveCamera napraviKameru() {
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(5000);

        camera.setTranslateZ(-2000);
        camera.setTranslateY(-1000);
        camera.setRotationAxis(Rotate.X_AXIS);
        camera.setRotate(-25);

        return camera;
    }
    
    private ParallelCamera napraviParalelnu(){
        ParallelCamera camera=new ParallelCamera();
        
        camera.setTranslateY(-500);
        camera.setRotationAxis(Rotate.X_AXIS);
        camera.setRotate(-90);
        camera.setTranslateX(-750);
        camera.setTranslateZ(500);
        return camera;
    }
    
    

    private void setUpGame() {

        tabla = new Tabla(TABLA_SIZE);
        kocka = new Kockica(KOCKICA_SIZE);
        staza = new Staza(POLJE_PRECNIK);
        crvenaKuca = new Kucica(KUCICA_SIZE, POLJE_PRECNIK);
        zelenaKuca = new Kucica(KUCICA_SIZE, POLJE_PRECNIK);
        orangeKuca = new Kucica(KUCICA_SIZE, POLJE_PRECNIK);
        purpleKuca = new Kucica(KUCICA_SIZE, POLJE_PRECNIK);
        crvenaKuca.setTranslateX(TABLA_SIZE / 6 * 2);
        crvenaKuca.setTranslateZ(TABLA_SIZE / 6 * 2);
        zelenaKuca.setTranslateX(-TABLA_SIZE / 6 * 2);
        zelenaKuca.setTranslateZ(TABLA_SIZE / 6 * 2);
        orangeKuca.setTranslateX(TABLA_SIZE / 6 * 2);
        orangeKuca.setTranslateZ(-TABLA_SIZE / 6 * 2);
        purpleKuca.setTranslateX(-TABLA_SIZE / 6 * 2);
        purpleKuca.setTranslateZ(-TABLA_SIZE / 6 * 2);
        figure = new Group();
        for (int i = 0; i < 4; i++) {
            gameObjects = new Group();
            Figura f = new Figura(FIGURA_UGAO, FIGURA_VISINA, Color.GREEN);
            f.setOnMouseClicked(ev -> {

                if (naPotezu.equals("green") && fazaIgre.equals("pomeriFiguru") && f.mozeDaIgra) {
                    if (f.pozicija == -1) {
                        
                        zelenaKuca.removeFigura(f);
                        if(staza.putanjaZeleni.get(0).ImaFigura()){
                            
                            pojediFiguru(staza.putanjaZeleni.get(0).getFigura());
                        }
                        f.pozicija = 0;
                        pomeriFiguru(f, staza.putanjaZeleni.get(0));
                        naPotezu="red"; fazaIgre="baciKocku";
                    } else {
                        
                        staza.putanjaZeleni.get(f.pozicija).removeFigura();
                        f.pozicija += vrKocke;
                        
                        if(staza.putanjaZeleni.get(f.pozicija).ImaFigura()){
                             pojediFiguru(staza.putanjaZeleni.get(f.pozicija).getFigura());
                        }
                        
                        pomeriFiguru(f, staza.putanjaZeleni.get(f.pozicija));

                    }
                   naPotezu = "red";
                fazaIgre = "baciKocku";
                kocka.postaviBoju(Color.SALMON);
                for(Figura fig:zeleneFigure){
                    if(fig.mozeDaIgra)
                        fig.odigrao();
                }
                }
               
                  
               int k=0;
                for(int p=staza.putanjaZeleni.size()-4;p<staza.putanjaZeleni.size();p++){
                    if(staza.putanjaZeleni.get(p).ImaFigura()) k++;
                    
                    
                    }
                
                if(k==4){
                   fazaIgre="kraj";
                    System.out.println("pobednik je zeleni");
                    Text text = new Text();
                      text.setFont(new Font(50));
                      
                       text.setFill(Color.GREEN);
                       text.setTranslateX(-300);
                       text.setTranslateY(-200);
                       text.setText("POBEDNIK JE ZELENI");
                        RotateTransition tr=new RotateTransition(Duration.seconds(2), text);
                       tr.setAxis(Rotate.Y_AXIS);
                       tr.setFromAngle(0);
                       tr.setToAngle(360);
                       tr.setCycleCount(Timeline.INDEFINITE);
                       tr.play();
                       
                       root.getChildren().add(text);
                       
                }
                
            });
            zeleneFigure.add(f);
            figure.getChildren().add(f);
            zelenaKuca.addFigura(f);
                
        }

        for (int i = 0; i < 4; i++) {
            Figura f = new Figura(FIGURA_UGAO, FIGURA_VISINA, Color.PURPLE);
            
            f.setOnMouseClicked(ev -> {

                if (naPotezu.equals("purple") && fazaIgre.equals("pomeriFiguru") && f.mozeDaIgra) {
                    if (f.pozicija == -1) {
                        purpleKuca.removeFigura(f);
                        if(staza.putanjaPurple.get(0).ImaFigura())
                            pojediFiguru(staza.putanjaPurple.get(0).getFigura());
                        f.pozicija = 0;
                        pomeriFiguru(f, staza.putanjaPurple.get(0));
                        naPotezu="green"; fazaIgre="baciKocku";
                    } else {
                        staza.putanjaPurple.get(f.pozicija).removeFigura();
                        f.pozicija += vrKocke;
                        if(staza.putanjaPurple.get(f.pozicija).ImaFigura()){
                             pojediFiguru(staza.putanjaPurple.get(f.pozicija).getFigura());
                        }
                        
                        pomeriFiguru(f, staza.putanjaPurple.get(f.pozicija));

                    }
                  naPotezu = "green";
                fazaIgre = "baciKocku";
                kocka.postaviBoju(Color.GREEN);
                for(Figura fig:purpleFigure){
                    if(fig.mozeDaIgra)
                        fig.odigrao();
                }
                }
                
               int k=0;
                for(int p=staza.putanjaPurple.size()-4;p<staza.putanjaPurple.size();p++){
                    if(staza.putanjaPurple.get(p).ImaFigura()) k++;
                    
                    
                    }
                
                if(k==4){
                   fazaIgre="kraj";
                    System.out.println("pobednik je purple");
                    Text text = new Text();
                      text.setFont(new Font(50));
                      
                       text.setFill(Color.PURPLE);
                       text.setTranslateX(-300);
                       text.setTranslateY(-200);
                       text.setText("POBEDNIK JE LJUBICASTI");
                       
                        RotateTransition tr=new RotateTransition(Duration.seconds(2), text);
                       tr.setAxis(Rotate.Y_AXIS);
                       tr.setFromAngle(0);
                       tr.setToAngle(360);
                       tr.setCycleCount(Timeline.INDEFINITE);
                       tr.play();
                       root.getChildren().add(text);
                }
                
                
            });
            
            purpleFigure.add(f);
            purpleKuca.addFigura(f);
            figure.getChildren().add(f);
        }

        for (int i = 0; i < 4; i++) {
            Figura f = new Figura(FIGURA_UGAO, FIGURA_VISINA, Color.SALMON);
            
            f.setOnMouseClicked(ev -> {

                if (naPotezu.equals("red") && fazaIgre.equals("pomeriFiguru") && f.mozeDaIgra) {
                    if (f.pozicija == -1) {
                        crvenaKuca.removeFigura(f);
                        if(staza.putanjaCrveni.get(0).ImaFigura())
                            pojediFiguru(staza.putanjaCrveni.get(0).getFigura());
                        f.pozicija = 0;
                        pomeriFiguru(f, staza.putanjaCrveni.get(0));
                        naPotezu="green"; fazaIgre="baciKocku";
                    } else {
                          staza.putanjaCrveni.get(f.pozicija).removeFigura();
                        f.pozicija += vrKocke;
                        
                        if(staza.putanjaCrveni.get(f.pozicija).ImaFigura()){
                             pojediFiguru(staza.putanjaCrveni.get(f.pozicija).getFigura());
                        }
                        
                        pomeriFiguru(f, staza.putanjaCrveni.get(f.pozicija));

                    }
                   naPotezu = "yellow";
                fazaIgre = "baciKocku";
                kocka.postaviBoju(Color.ORANGE);
                for(Figura fig:crveneFigure){
                    if(fig.mozeDaIgra)
                        fig.odigrao();
                }
                }
                 
                int k=0;
                for(int p=staza.putanjaCrveni.size()-4;p<staza.putanjaCrveni.size();p++){
                    if(staza.putanjaCrveni.get(p).ImaFigura()) k++;
                    
                    
                    }
                
                if(k==4){
                   fazaIgre="kraj";
                    System.out.println("pobednik je crveni");
                    Text text = new Text();
                      text.setFont(new Font(50));
                      
                       text.setFill(Color.SALMON);
                       text.setTranslateX(-300);
                       text.setTranslateY(-200);
                       text.setText("POBEDNIK JE CRVENI");
                       
                       RotateTransition tr=new RotateTransition(Duration.seconds(2), text);
                       tr.setAxis(Rotate.Y_AXIS);
                       tr.setFromAngle(0);
                       tr.setToAngle(360);
                       tr.setCycleCount(Timeline.INDEFINITE);
                       tr.play();
                       
                       root.getChildren().add(text);
                }
                
            });
            
            crveneFigure.add(f);
            crvenaKuca.addFigura(f);
            figure.getChildren().add(f);
        }

        for (int i = 0; i < 4; i++) {
            Figura f = new Figura(FIGURA_UGAO, FIGURA_VISINA, Color.ORANGE);
            
             f.setOnMouseClicked(ev -> {

                if (naPotezu.equals("yellow") && fazaIgre.equals("pomeriFiguru") && f.mozeDaIgra) {
                    if (f.pozicija == -1) {
                        orangeKuca.removeFigura(f);
                        if(staza.putanjaOrange.get(0).ImaFigura())                          
                                pojediFiguru(staza.putanjaOrange.get(0).getFigura());
                        f.pozicija = 0;
                        pomeriFiguru(f, staza.putanjaOrange.get(0));
                        naPotezu="purple"; fazaIgre="baciKocku";
                    } else {
                        staza.putanjaOrange.get(f.pozicija).removeFigura();
                        f.pozicija += vrKocke;
                        
                         if(staza.putanjaOrange.get(f.pozicija).ImaFigura()){
                             pojediFiguru(staza.putanjaOrange.get(f.pozicija).getFigura());
                        }
                         
                        pomeriFiguru(f, staza.putanjaOrange.get(f.pozicija));

                    }
                  naPotezu = "purple";
                fazaIgre = "baciKocku";
                kocka.postaviBoju(Color.PURPLE);
                
                for(Figura fig:orangeFigure){
                    if(fig.mozeDaIgra)
                        fig.odigrao();
                }
                }
                
                int k=0;
                for(int p=staza.putanjaOrange.size()-4;p<staza.putanjaOrange.size();p++){
                    if(staza.putanjaOrange.get(p).ImaFigura()) k++;
                   
                    
                    }
                
                if(k==4){
                   fazaIgre="kraj";
                    System.out.println("pobednik je zuti");
                    Text text = new Text();
                      text.setFont(new Font(50));
                      
                       text.setFill(Color.ORANGE);
                       text.setTranslateX(-300);
                       text.setTranslateY(-200);
                       text.setText("POBEDNIK JE ZUTI");
                        RotateTransition tr=new RotateTransition(Duration.seconds(2), text);
                       tr.setAxis(Rotate.Y_AXIS);
                       tr.setFromAngle(0);
                       tr.setToAngle(360);
                       tr.setCycleCount(Timeline.INDEFINITE);
                       tr.play();
                       root.getChildren().add(text);
                }
                
                
                
            });
            
            orangeFigure.add(f);
            orangeKuca.addFigura(f);
            figure.getChildren().add(f);
        }

       

        gameObjects.getChildren().addAll(tabla, staza, kocka, crvenaKuca, zelenaKuca, orangeKuca, purpleKuca, figure);

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void obradaDogadjaja(Scene scene) {
        EventHandler<KeyEvent> r1 = dog -> {
            KeyCode k = dog.getCode();
            switch (k) {
                case LEFT:
                    ry.setAngle(ry.getAngle() + 5);
                    break;
                case RIGHT:
                    ry.setAngle(ry.getAngle() - 5);
                    break;
                    
                case UP:
                    if(rx.getAngle()>-25)
                    rx.setAngle(rx.getAngle()-5);
                    System.out.println(rx.getAngle());
                    break;
                case DOWN:  
                    if(rx.getAngle()<15)
                    rx.setAngle(rx.getAngle()+5);
                    System.out.println(rx.getAngle());
                    break;
                case DIGIT1: kamera=perspektivnaKamera; scene.setCamera(kamera); break;
                case DIGIT2: kamera=paralelnaKamera; scene.setCamera(kamera); break;
            }

        };

        scene.addEventHandler(KeyEvent.KEY_PRESSED, r1);

        EventHandler<MouseEvent> r2 = dog -> {

            PickResult pick = dog.getPickResult();

        };

        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, r2);

    }

    private void pomeriFiguru(Figura f, Polje p) {
       
       double y= f.getTranslateY();
     
        p.removeFigura();
        SequentialTransition st = new SequentialTransition(f);

        TranslateTransition ty1 = new TranslateTransition(Duration.seconds(0.5));
        ty1.setFromY(y);
        ty1.setByY(-100); ty1.setFromX(f.getTranslateX()); ty1.setFromZ(f.getTranslateZ());
        TranslateTransition t = new TranslateTransition(Duration.seconds(1));
        t.setFromX(f.getTranslateX());
        t.setToX(p.getTranslateX());

        t.setFromZ(f.getTranslateZ());
        t.setToZ(p.getTranslateZ());
        TranslateTransition ty2 = new TranslateTransition(Duration.seconds(0.5));
        ty2.setToY(y);

        st.getChildren().addAll(ty1, t, ty2);
        st.play();
        p.setFigura(f);
    }

     private void pomeriFiguruKuca(Figura f, Polje p) {
       
        
     
        p.removeFigura();
        SequentialTransition st = new SequentialTransition(f);

        TranslateTransition ty1 = new TranslateTransition(Duration.seconds(0.5));
        ty1.setByY(-100); ty1.setFromX(f.getTranslateX()); ty1.setFromZ(f.getTranslateZ());
        TranslateTransition t = new TranslateTransition(Duration.seconds(1));
        t.setFromX(f.getTranslateX());
        t.setToX(p.getLocalToSceneTransform().getTx());

        t.setFromZ(f.getTranslateZ());
        t.setToZ(p.getLocalToSceneTransform().getTz());
        TranslateTransition ty2 = new TranslateTransition(Duration.seconds(0.5));
        ty2.setByY(100);

        st.getChildren().addAll(ty1, t, ty2);
        st.play();
        p.setFigura(f);
    }
    
    
    private boolean daLiMozeZeleni(Figura f) {
        boolean moze = true;
        int trenutni = f.pozicija;
        if (trenutni == -1 && vrKocke != 6) {
            moze = false;
        }
        if (trenutni == -1 && vrKocke == 6) {

            for (Figura fig : zeleneFigure) {
                if (fig.pozicija == 0) {
                    moze = false; break;
                } else {
                    moze = true; 
                }
            }
        }
        if(trenutni!=-1){
            
        for (Figura fig : zeleneFigure) {
          
            if (fig.pozicija == (f.pozicija + vrKocke)) {
                                

                moze = false; break;
               
                
            }
        }

        if (f.pozicija + vrKocke >= staza.putanjaZeleni.size()) {
            moze = false;
        }
        }
        f.mozeDaIgra = moze;
        if(moze)
        f.omoguciIgranje();
        return moze;
    }

    private boolean daLiMozeCrveni(Figura f) {
        boolean moze = true;
        int trenutni = f.pozicija;
        if (trenutni == -1 && vrKocke != 6) {
            moze = false;
        }
        if (trenutni == -1 && vrKocke == 6) {

            for (Figura fig : crveneFigure) {
                if (fig.pozicija == 0) {
                    moze = false;break;
                } 
            }
        }
       if(trenutni!=-1){
        for (Figura fig : crveneFigure) {
            
            if (fig.pozicija == (f.pozicija + vrKocke)) {
                moze = false;
                
            }
        }

        if (f.pozicija + vrKocke >= staza.putanjaZeleni.size()) {
            moze = false;
        }
        }
        f.mozeDaIgra = moze;
        if(moze)
        f.omoguciIgranje();
        return moze;
    }

    private boolean daLiMozePurple(Figura f) {
        boolean moze = true;
        int trenutni = f.pozicija;
        if (trenutni == -1 && vrKocke != 6) {
            moze = false;
        }
        if (trenutni == -1 && vrKocke == 6) {

            for (Figura fig : purpleFigure) {
                if (fig.pozicija == 0) {
                    moze = false;break;
                } else {
                    moze = true;
                }
            }
        }
        if(trenutni!=-1){
        for (Figura fig : purpleFigure) {
           
            if (fig.pozicija == (f.pozicija + vrKocke)) {
                moze = false;
               
            }
        }

        if (f.pozicija + vrKocke >= staza.putanjaPurple.size()) {
            moze = false;
            
        }
        }
        f.mozeDaIgra = moze;
        if(moze)
        f.omoguciIgranje();
        return moze;
    }

    private boolean daLiMozeOrange(Figura f) {
        boolean moze = true;
        int trenutni = f.pozicija;
        if (trenutni == -1 && vrKocke != 6) {
            moze = false;
        }
        if (trenutni == -1 && vrKocke == 6) {

            for (Figura fig : orangeFigure) {
                if (fig.pozicija == 0) {
                    moze = false; break;
                } else {
                    moze = true;
                }
            }
        }
        if(trenutni!=-1){
        for (Figura fig : orangeFigure) {
           
            if (fig.pozicija == (f.pozicija + vrKocke)) {
                moze = false;
                
            }
        }

        if (f.pozicija + vrKocke >= staza.putanjaPurple.size()) {
            moze = false;
            
        }
        }
        f.mozeDaIgra = moze;
        if(moze)
        f.omoguciIgranje();
        return moze;
    }

    private void pojediFiguru(Figura f) {
        if (f.c == Color.GREEN) {
            staza.putanjaZeleni.get(f.pozicija).removeFigura();
            f.pozicija=-1;
            System.out.println(zelenaKuca.nadjiMesto().getTranslateX());
             System.out.println(zelenaKuca.nadjiMesto().getTranslateZ());
            pomeriFiguruKuca(f, zelenaKuca.nadjiMesto());
        }
        if (f.c == Color.PURPLE) {
            staza.putanjaPurple.get(f.pozicija).removeFigura();
            f.pozicija=-1;
            System.out.println(purpleKuca.nadjiMesto().getTranslateX());
             System.out.println(purpleKuca.nadjiMesto().getTranslateZ());
            pomeriFiguruKuca(f, purpleKuca.nadjiMesto());
        }
        if (f.c == Color.SALMON) {
            staza.putanjaCrveni.get(f.pozicija).removeFigura();
            f.pozicija=-1;
            System.out.println(crvenaKuca.nadjiMesto().getTranslateX());
             System.out.println(crvenaKuca.nadjiMesto().getTranslateZ());
            pomeriFiguruKuca(f, crvenaKuca.nadjiMesto());
        }
        if (f.c == Color.ORANGE) {
            staza.putanjaOrange.get(f.pozicija).removeFigura();
            f.pozicija=-1;
            System.out.println(orangeKuca.nadjiMesto().getTranslateX());
             System.out.println(orangeKuca.nadjiMesto().getTranslateZ());
            pomeriFiguruKuca(f, orangeKuca.nadjiMesto());
        }

    }
}
