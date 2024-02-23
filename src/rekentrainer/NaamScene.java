
package rekentrainer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;



public class NaamScene {
    TextField nameinput;
    public Scene scene;
    
    public NaamScene(Rekentrainer rekentrainer){
        
        VBox vbox = new VBox();
        vbox.alignmentProperty().set(Pos.CENTER);
        vbox.paddingProperty().set(new Insets(10, 10, 10, 10));
        vbox.setSpacing(20);
        
        scene = new Scene(vbox,800,600);
        
        Text label = new Text("Vul hieronder je naam in om de rekentrainer te starten.");
        nameinput =  new TextField("");
        nameinput.setMaxWidth(200);
        Button startbutton = new Button("Start");
        vbox.getChildren().add(label);
        vbox.getChildren().add(nameinput);
        vbox.getChildren().add(startbutton);
        
        
        startbutton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            rekentrainer.primaryStage.setScene(rekentrainer.keuzeScene.scene);
        });
        
    }
    
}
