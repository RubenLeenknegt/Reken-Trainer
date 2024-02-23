package rekentrainer;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Ruben leenknegt
 */
public class Rekentrainer extends Application {
    
    Stage primaryStage;
    NaamScene naamScene;
    KeuzeScene keuzeScene;
    RekenScene rekenScene;
    ScoreScene scoreScene;
            
    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
        naamScene = new NaamScene(this);
        keuzeScene = new KeuzeScene(this);
        rekenScene = new RekenScene(this);
        scoreScene = new ScoreScene(this);  
        
        primaryStage.setTitle("Reken Trainer");
        primaryStage.setScene(naamScene.scene);
        primaryStage.show();
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
