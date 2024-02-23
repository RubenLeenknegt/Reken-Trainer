package rekentrainer;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.util.Collections;

public class KeuzeScene {

    public Random rng = new Random(0);
    public Scene scene;
    public ArrayList<Moeilijkheidsgraad> moeilijkheid;
    public ArrayList<Som> gegenereerdeSommen = null;
    
    
    public KeuzeScene(Rekentrainer rekentrainer){
        moeilijkheid = new ArrayList<Moeilijkheidsgraad>();
        moeilijkheid.add(new Moeilijkheidsgraad(true,true,false,false,1,20,1,1));//3
        moeilijkheid.add(new Moeilijkheidsgraad(true,true,false,false,1,30,1,1));//4
        moeilijkheid.add(new Moeilijkheidsgraad(true,true,false,false,1,40,1,1));//5
        moeilijkheid.add(new Moeilijkheidsgraad(true,true,true,true,1,50,1,10));//6
        moeilijkheid.add(new Moeilijkheidsgraad(true,true,true,true,1,60,1,10));//7
        moeilijkheid.add(new Moeilijkheidsgraad(true,true,true,true,1,70,1,10));//8
        
        
        Pane keuzescreen = new Pane();
        
        scene = new Scene(keuzescreen,800,500);
        
        Text text1 = new Text("Hoeveel sommen wil je maken ?");
        text1.relocate(400,50);
        
        TextField somhoeveelheid = new TextField();
        somhoeveelheid.textProperty().set("4");
        somhoeveelheid.relocate(625,50);
        somhoeveelheid.setMaxWidth(75);
        
        
        Text text2 = new Text("Wil je de sommen door elkaar heen ?");
        text2.relocate(400,150);


        ToggleGroup ToggleGroupMixed = new ToggleGroup();
        RadioButton somhussel = new RadioButton("Ja");
        somhussel.relocate(655,150);
        somhussel.setUserData(true);
        somhussel.setToggleGroup(ToggleGroupMixed);


        RadioButton somhusse2 = new RadioButton("Nee");
        somhusse2.relocate(655,175);
        somhusse2.setUserData(false);
        somhusse2.setToggleGroup(ToggleGroupMixed);
        somhusse2.setSelected(true);

        Text text3 = new Text("Kies je groep:");
        text3.relocate(75,50);
         
 
        ToggleGroup klasselectie = new ToggleGroup();

        RadioButton groep3 = new RadioButton("Groep 3");
        groep3.relocate(75,75);
        groep3.setToggleGroup(klasselectie);
        groep3.setSelected(true);
        groep3.setUserData(0);

        RadioButton groep4 = new RadioButton("Groep 4");
        groep4.relocate(75,100);
        groep4.setToggleGroup(klasselectie);
        groep4.setUserData(1);

        RadioButton groep5 = new RadioButton("Groep 5");
        groep5.relocate(75,125);
        groep5.setToggleGroup(klasselectie);
        groep5.setUserData(2);

        RadioButton groep6 = new RadioButton("Groep 6");
        groep6.relocate(75,150);
        groep6.setToggleGroup(klasselectie);
        groep6.setUserData(3);

        RadioButton groep7 = new RadioButton("Groep 7");
        groep7.relocate(75,175);
        groep7.setToggleGroup(klasselectie);
        groep7.setUserData(4);

        RadioButton groep8 = new RadioButton("Groep 8");
        groep8.relocate(75,200);
        groep8.setToggleGroup(klasselectie);
        groep8.setUserData(5);
         
        
        Button starttrainer = new Button("Start rekentrainer");
        starttrainer.relocate(350,350);
        starttrainer.setMinWidth(350);
        starttrainer.setMinHeight(25);
         
        
        keuzescreen.getChildren().addAll(starttrainer, text1, somhoeveelheid, text2, somhussel, somhusse2, text3, groep3, groep4, groep5, groep6, groep7, groep8);
        
   
        starttrainer.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

     
            int index = (int)klasselectie.getSelectedToggle().getUserData();
            int aantal = Integer.parseInt(somhoeveelheid.textProperty().get());
            boolean mixed = (boolean)ToggleGroupMixed.getSelectedToggle().getUserData();
            gegenereerdeSommen = generateRandomSommen(moeilijkheid.get(index),aantal,mixed); 
            
            
            rekentrainer.primaryStage.setScene(rekentrainer.rekenScene.scene);
            rekentrainer.rekenScene.onMount();
        });
        
    }
    
    ArrayList<Som> generateRandomSommen(Moeilijkheidsgraad moeilijkheidsgraad,int aantal,boolean mixed){
        
        int verschillendesommen = 0;
        if(moeilijkheidsgraad.aftrekken){
            verschillendesommen++;
        }
        if(moeilijkheidsgraad.optellen){
            verschillendesommen++;
        }
        if(moeilijkheidsgraad.vermenigvuldigen){
            verschillendesommen++;
        }
        if(moeilijkheidsgraad.delen){
            verschillendesommen++;
        }
        
        
        int aantalpersom = aantal / verschillendesommen;
        int remainder = aantal % verschillendesommen;
        ArrayList<Som> result = new ArrayList<Som>();
        
        for(int i = 0; i < aantalpersom && moeilijkheidsgraad.optellen; i++){
            // +
            int getal1 = randomRange(moeilijkheidsgraad.mingetalopaf, moeilijkheidsgraad.maxgetalopaf);
            int getal2 = randomRange(moeilijkheidsgraad.mingetalopaf, moeilijkheidsgraad.maxgetalopaf);
            Som plussom = new Som(Somtype.plus,getal1,getal2,getal1 + getal2);
            result.add(plussom);
        }
        for(int i = 0; i < aantalpersom && moeilijkheidsgraad.aftrekken; i++){
            //-
            int getal1 = randomRange(moeilijkheidsgraad.mingetalopaf + 10, moeilijkheidsgraad.maxgetalopaf);
            int getal2 = randomRange(moeilijkheidsgraad.mingetalopaf, getal1 - 1);
            Som minsom = new Som(Somtype.min,getal1,getal2,getal1 - getal2);
            result.add(minsom);
        }
        for(int i = 0; i < aantalpersom && moeilijkheidsgraad.vermenigvuldigen; i++){
            //*
            int getal1 = randomRange(moeilijkheidsgraad.mingetaldelver + 5, moeilijkheidsgraad.maxgetaldelver);
            int getal2 = randomRange(moeilijkheidsgraad.mingetaldelver, moeilijkheidsgraad.maxgetaldelver);
            Som keersom = new Som(Somtype.keer,getal1,getal2,getal1 * getal2);
            result.add(keersom);
        }
        
        // delen
        for(int i = 0; i < aantalpersom + remainder && moeilijkheidsgraad.delen; i++){
            int second = randomRange(moeilijkheidsgraad.mingetaldelver, moeilijkheidsgraad.maxgetaldelver);
            int antwoord = randomRange(moeilijkheidsgraad.mingetaldelver + 1, moeilijkheidsgraad.maxgetaldelver);
            int getal1 = second * antwoord;
            int getal2 = second;
            Som deelsom = new Som(Somtype.gedeeld,getal1,getal2,antwoord);
            result.add(deelsom);
        }
        
        if(mixed){
            Collections.shuffle(result);
        }

        return result;
    }
    
    int randomRange(int min,int max){
        return (int)((rng.nextFloat() * (max - min)) + min);
    }
}




