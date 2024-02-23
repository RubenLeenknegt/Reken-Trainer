package rekentrainer;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class RekenScene {
    
    
    public Scene scene;
    int huidigeSomIndex = 0;
    Rekentrainer rekentrainer;
    Text text2 = null;
    Text TextAantalGoed = null;
    Text TextAantalFout = null;
    Text TextNogTeGaan = null;
    public int aantalFout = 0;
    public int aantalGoed = 0;
    public int aantalgemaaktesommen = 0;
    int aantalsommentemaken = 0;
    public Text text1;
    public TextField input;
    
    public RekenScene(Rekentrainer rekentrainer){
        this.rekentrainer = rekentrainer;
        Pane screen = new Pane();
        
        scene = new Scene(screen,800,500);
        
   
        text1 = new Text("Welkom " + rekentrainer.naamScene.nameinput.getText() + ", vul het antwoord van de volgende som in?");
        text1.relocate(200,50);
        
        
        text2 = new Text("8 + 12 =");
        text2.relocate(300,200);
        text2.setStyle("-fx-font: 40 arial;");
        
        input = new TextField();
        input.relocate(450,180);
        input.setMaxWidth(100);
        input.setMinHeight(50);
        
        
        TextAantalGoed = new Text("Aantal sommen tot nu toe goed: " + aantalGoed);
        TextAantalGoed.relocate(500,400);
        TextAantalGoed.setStyle("-fx-font-weight: bold");
        
        TextAantalFout = new Text("Aantal sommen tot nu toe fout: " + aantalFout );
        TextAantalFout.relocate(515,425);
        
        TextNogTeGaan = new Text("Nog " + 0 + " sommen te maken");
        TextNogTeGaan.relocate(575,450);
        
        
        
        Button volgendesom = new Button("Volgende Som");
        volgendesom.relocate(225,300);
        volgendesom.setMinWidth(350);
        volgendesom.setMinHeight(25);
        
        volgendesom.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Som som = rekentrainer.keuzeScene.gegenereerdeSommen.get(huidigeSomIndex);
            try{
                int answer = Integer.parseInt(input.getText());
                if(answer == som.antwoord){
                    aantalGoed++;
                    //goed gedaan
                }else{
                    aantalFout++;
                    //foute antwoord
                }

                if(huidigeSomIndex < rekentrainer.keuzeScene.gegenereerdeSommen.size() - 1){
                    huidigeSomIndex++;
                    laadSom();
                    input.textProperty().set("0");
                }else{
                    rekentrainer.primaryStage.setScene(rekentrainer.scoreScene.scene);
                    rekentrainer.scoreScene.onMount();
                }
                aantalgemaaktesommen++;
                updateUI();
            }catch(NumberFormatException nfe){
                
            }            
        });
        
        screen.getChildren().addAll(volgendesom, text1, text2, input, TextAantalGoed, TextAantalFout, TextNogTeGaan);
    }
    
    public void updateUI(){


        aantalsommentemaken = rekentrainer.keuzeScene.gegenereerdeSommen.size() - aantalgemaaktesommen;      
        TextAantalGoed.textProperty().set("Aantal sommen tot nu toe goed: " + aantalGoed);
        TextAantalFout.textProperty().set("Aantal sommen tot nu toe fout: " + aantalFout );
        TextNogTeGaan.textProperty().set("Nog " + aantalsommentemaken + " sommen te maken");
        text1.textProperty().set("Welkom " + rekentrainer.naamScene.nameinput.getText() + ", vul het antwoord van de volgende som in?");
    }
    
    public void onMount(){
        aantalGoed = 0;
        aantalFout = 0;
        aantalgemaaktesommen = 0;
        rekentrainer.rekenScene.huidigeSomIndex = 0;
        laadSom();
        updateUI();
        input.textProperty().set("0");
    }
    
    
    void laadSom(){
        
        Som som = rekentrainer.keuzeScene.gegenereerdeSommen.get(huidigeSomIndex);
        String symbol = "";
        if(som.somtype == Somtype.gedeeld){
            symbol = "/";
        }
        if(som.somtype == Somtype.keer){
            symbol = "*";
        }
        if(som.somtype == Somtype.plus){
            symbol = "+";
        }
        if(som.somtype == Somtype.min){
            symbol = "-";
        }
        
        text2.textProperty().set(String.format("%d %s %d", som.getal1,symbol,som.getal2));
    }
   
    
}
