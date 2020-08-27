/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonCharacters;

import AttackPackage.Attack;
import AttackPackage.AttackFlyweightFactory;
import DungeonCharacters.DungeonCharacter;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;

/**
 *
 * @author Gingervitis
 */
public abstract class Hero extends DungeonCharacter {

    private double chanceToBlock;
    private int numTurns;
    private int healthPotions;
    private int visionPotions;
    private int pillars;
    private int cellX, cellY;
    protected AttackFlyweightFactory wFactory;

    public Hero(String name, int hitPoints, int attackSpeed, double chanceToHit, int damageMin, int damageMax, double chanceToBlock) {
        super(name, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax);
        this.chanceToBlock = chanceToBlock;
        readName();
        wFactory = new AttackFlyweightFactory();
    }
    
    public int getCellX(){
        return cellX;
    }

    public void setCellX(int c){
        cellX = c;
    }
    
    public int getCellY(){
        return cellY;
    }

    public void setCellY(int c){
        cellY = c;
    }
    
    public int getHealthPotions() {
        return healthPotions;
    }

    public void setHealthPotions(int healthPotions) {
        this.healthPotions = healthPotions;
    }

    public int getVisionPotions() {
        return visionPotions;
    }

    public void setVisionPotions(int visionPotions) {
        this.visionPotions = visionPotions;
    }

    public int getPillars() {
        return pillars;
    }

    public void setPillars(int pillars) {
        this.pillars = pillars;
    }

    public double getChanceToBlock() {
        return chanceToBlock;
    }

    public void setChanceToBlock(double chanceToBlock) {
        this.chanceToBlock = chanceToBlock;
    }

    public int getNumTurns() {
        return numTurns;
    }

    public void setNumTurns(int numTurns) {
        this.numTurns = numTurns;
    }

    public boolean defend() {
        return Math.random() <= chanceToBlock;

    }

    public void subtractHitPoints(int hitPoints) {
        if (defend()) {
            System.out.println(getName() + " BLOCKED the attack!");
        } else {
            super.subtractHitPoints(hitPoints);
        }

    }

    private void readName() {
        TextInputDialog getName = new TextInputDialog("Type name here");
        getName.setTitle("Create Character");
        getName.setHeaderText("Enter your character's name");
        getName.setContentText("Name");

        Optional<String> result = getName.showAndWait();
        if (result.isPresent()) {
            super.setName(result.get());
        } else {
            super.setName("The Adventurer");
        }
    }

    /*-------------------------------------------------------
battleChoices is a Template method that delegates to
subclasses about what attacks they should do. getSpecialAttack()
returns the name of the special attack. attack(opponent) 
prints out the attack then asks the super class for
calculations. getSpecialAttack.specialAttack() delegates to 
subclasses what the special attack calculation is and handleSpecial(opponent)
handles the integer that is returned from getSpecialAttack.specialAttack().
Step delegated to subclasses:
getSpecialAttackName()
getSpecialAttack()
handleSpecial(DungeonCharacter opponent)
---------------------------------------------------------*/
    protected abstract String getSpecialAttackName();

    protected abstract Attack getSpecialAttack();

    protected abstract void handleSpecial(DungeonCharacter opponent);

    public final void battleChoices(DungeonCharacter opponent) {
        numTurns = getAttackSpeed() / opponent.getAttackSpeed();

        if (numTurns == 0) {
            numTurns++;
        }

        System.out.println("Number of turns this round is: " + numTurns);

        String choice;
        //Scanner kb = new Scanner(System.in);

        do {
            System.out.println("1. Attack Opponent");
            System.out.println("2. " + getSpecialAttackName());
            System.out.print("Choose an option: ");
            choice = "1";

            if (choice.equals("1")) {
                attack(opponent);

            } else if (choice.equals("2")) {
                getSpecialAttack().specialAttack();
                handleSpecial(opponent);
            } else {
                System.out.println("invalid choice!");
            }

            setNumTurns(getNumTurns() - 1);
            if (getNumTurns() > 0) {
                System.out.println("Number of turns remaining is: " + getNumTurns());
            }

        } while (getNumTurns() > 0 && getHitPoints() > 0 && opponent.getHitPoints() > 0);

    }
    
}
