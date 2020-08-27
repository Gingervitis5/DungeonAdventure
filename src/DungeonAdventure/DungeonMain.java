/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonAdventure;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Gingervitis
 */
public class DungeonMain extends Application {
    
    private Dungeon dungeon;
    private VBox itemList;
    private Label healthPotions, visionPotions, strengthPotions, numPillars;
    private Label textBox;
    private ImageView healthPotionImage = new ImageView(new Image("Pixel Art/HealthPotion.png"));
    private ImageView visionPotionImage = new ImageView(new Image("Pixel Art/VisionPotion.png"));
    private ImageView strengthPotionImage = new ImageView(new Image("Pixel Art/StrengthPotion.png"));
    private ImageView pillarOfOOImage = new ImageView(new Image("Pixel Art/PillarOfOO.png"));
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        healthPotionImage.setFitHeight(20);
        healthPotionImage.setFitWidth(20);
        visionPotionImage.setFitHeight(20);
        visionPotionImage.setFitWidth(20);
        strengthPotionImage.setFitHeight(20);
        strengthPotionImage.setFitWidth(20);
        pillarOfOOImage.setFitHeight(20);
        pillarOfOOImage.setFitWidth(20);
        dungeon = new Dungeon();
        healthPotions = new Label("x0 Health Potions");
        healthPotions.setFont(new Font(15));
        visionPotions = new Label("x0 Vision Potions");
        visionPotions.setFont(new Font(15));
        strengthPotions = new Label("x0 Strength Potions ");
        strengthPotions.setFont(new Font(15));
        numPillars = new Label("x0 Pillars of OO");
        numPillars.setFont(new Font(15));
        HBox healthPotionListItem = new HBox(healthPotionImage, healthPotions);
        HBox visionPotionListItem = new HBox(visionPotionImage, visionPotions);
        HBox strengthPotionListItem = new HBox(strengthPotionImage, strengthPotions);
        HBox pillarPotionListItem = new HBox(pillarOfOOImage, numPillars);
        itemList = new VBox(5, healthPotionListItem, visionPotionListItem, strengthPotionListItem, pillarPotionListItem);
        textBox = new Label("You awaken in a dungeon.....");
        textBox.setFont(new Font(20));
        
        BorderPane root = new BorderPane();
        root.setRight(itemList);
        root.setLeft(new VBox(dungeon.getCanvas(), textBox));
        
        Scene scene = new Scene(root, 700, 650);       
        
        primaryStage.setTitle("Dungeon Adventure");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
