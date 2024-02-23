package rekentrainer;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ScoreScene {
    int aantalFout = 5;
    int aantalGoed = 7;
    Scene scene;
    public Text text1;
    public Rekentrainer rekentrainer;
    public Text text3;
    public Text text4;
    public Text text5;
    
    public ScoreScene(Rekentrainer rekentrainer){
        this.rekentrainer = rekentrainer;
        
        Pane screen = new Pane();
        
        scene = new Scene(screen,800,600);
        
        
        text1 = new Text("Je hebt de opdracht afgerond " +  rekentrainer.naamScene.nameinput.getText());
        text1.relocate(250,75);
        text1.setStyle("-fx-font-weight: bold");
        
        Text text2 = new Text("hieronder zie je wat je resultaat is:" );
        text2.relocate(265,100);
        text2.setStyle("-fx-font-weight: bold");
        
        text3 = new Text("Aantal sommen goed:     " + aantalGoed);
        text3.relocate(100,250);
        
        text4 = new Text("Aantal sommen fout:      " + aantalGoed);
        text4.relocate(100,275);
        
        
        text5 = new Text("Score:                              100%" );
        text5.relocate(100,300);
        
        Button restart = new Button("Nog een keer");
        restart.relocate(50,500);
        restart.setMinWidth(200);
        
        Button exit = new Button("Stoppen");
        exit.relocate(550,500);
        exit.setMinWidth(200);
        
        
        restart.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            rekentrainer.primaryStage.setScene(rekentrainer.naamScene.scene);
            
            
        });
        
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                System.exit(0);
        });
            
        
        screen.getChildren().addAll(restart, text1, text2, exit, text3, text4, text5);
    }
    
    public void onMount(){
        text1.textProperty().set("Je hebt de opdracht afgerond " +  rekentrainer.naamScene.nameinput.getText());
        text3.textProperty().set("Aantal sommen goed:     " + rekentrainer.rekenScene.aantalGoed);
        text4.textProperty().set("Aantal sommen fout:      " + rekentrainer.rekenScene.aantalFout);
        text5.textProperty().set("Score:                              " + rekentrainer.rekenScene.aantalGoed / (float)rekentrainer.keuzeScene.gegenereerdeSommen.size() * 100 +"%" );
        
    }
    
}
