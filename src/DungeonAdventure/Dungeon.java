/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonAdventure;

import DungeonCharacters.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Gingervitis
 */
public class Dungeon {
    
    private Canvas mCanvas;
    private GraphicsContext mGc;
    private int cellW;
    private File dungeonFile;
    private int[][] dungeon;
    private Hero hero;
    
    public Dungeon() throws FileNotFoundException{
        mCanvas = new Canvas(530, 530);
        mGc = mCanvas.getGraphicsContext2D();
        mGc.setFill(Color.GREY);
        mGc.fillRect(0, 0, mCanvas.getWidth(), mCanvas.getHeight());
        cellW = 75;
        dungeon = new int[7][7];
        dungeonFile = new File("Dungeon1.txt");
        hero = new Warrior();
        setDungeon();
        drawCanvas();
    }
    
    public Canvas getCanvas(){
        return mCanvas;
    }
    
    private void setDungeon() throws FileNotFoundException{
        Scanner inp = new Scanner(dungeonFile);
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 7; j++){
                if (inp.hasNext()){
                    dungeon[i][j] = inp.nextInt();
                    if (dungeon[i][j] == 8){
                        hero.setCellX(j);
                        hero.setCellY(i);
                    }
                }
            }
        }
        inp.close();
    }
    
    private void drawCanvas(){
        mGc.save();
        for (int i = 0; i < 7; i++){
            mGc.save();
            for (int j = 0; j < 7; j++){
                drawCell();
                mGc.translate(cellW, 0);
            }
            mGc.restore();
            mGc.translate(0, cellW);
        }
        mGc.restore();
        mGc.save();
        mGc.drawImage(hero.getImage(), hero.getCellX()*cellW, hero.getCellY()*cellW);
        mGc.restore();
    }
    
    private void drawCell(){
        mGc.save();
        mGc.setFill(Color.BLACK);
        mGc.setLineWidth(4.0);
        mGc.translate(2, 2);
        mGc.strokeLine(0, 0, cellW, 0);
        mGc.strokeLine(0, 0, 0, cellW);
        mGc.strokeLine(cellW, 0, cellW, cellW);
        mGc.strokeLine(0, cellW, cellW, cellW);
        mGc.restore();
    }
}
