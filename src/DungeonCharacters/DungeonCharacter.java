/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonCharacters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Gingervitis
 */
public abstract class DungeonCharacter {

    private String name;
    private int hitPoints;
    private int attackSpeed;
    private double chanceToHit;
    private int damageMin, damageMax;
    protected final ImageView image;

    public double getChanceToHit() {
        return chanceToHit;
    }

    public void setChanceToHit(double chanceToHit) {
        this.chanceToHit = chanceToHit;
    }

    public int getDamageMin() {
        return damageMin;
    }

    public void setDamageMin(int damageMin) {
        this.damageMin = damageMin;
    }

    public int getDamageMax() {
        return damageMax;
    }

    public void setDamageMax(int damageMax) {
        this.damageMax = damageMax;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }
    
    public Image getImage(){
        return image.getImage();
    }
    
    protected abstract Image setSuperImage();

    /*explicit constructor to initialize instance variables -- it is called
	 by derived classes*/
    public DungeonCharacter(String name, int hitPoints, int attackSpeed,
            double chanceToHit, int damageMin, int damageMax) {

        this.name = name;
        this.hitPoints = hitPoints;
        this.attackSpeed = attackSpeed;
        this.chanceToHit = chanceToHit;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.image = new ImageView(setSuperImage());
    }
    
    /*-------------------------------------------------------
    addHitPoints is used to increment the hitpoints a dungeon character has
    Receives: number of hit points to add
    Returns: nothing
    This method calls: nothing
    This method is called by: heal method of monsters and Sorceress
    ---------------------------------------------------------*/
    public void addHitPoints(int hitPoints) {
        if (hitPoints <= 0) {
            System.out.println("Hitpoint amount must be positive.");
        } else {
            this.hitPoints += hitPoints;
            //System.out.println("Remaining Hit Points: " + hitPoints);

        }
    }
    
    /*-------------------------------------------------------
    subtractHitPoints is used to decrement the hitpoints a dungeon character has.
    It also reports the damage and remaining hit points (these things could be
    done in separate methods to make code more modular ;-)
    Receives: number of hit points to subtract
    Returns: nothing
    This method calls: nothing
    This method is called by: overridden versions in Hero and Monster
    ---------------------------------------------------------*/
    public void subtractHitPoints(int hitPoints) {
        if (hitPoints < 0) {
            System.out.println("Hitpoint amount must be positive.");
        } else if (hitPoints > 0) {
            this.hitPoints -= hitPoints;
            if (this.hitPoints < 0) {
                this.hitPoints = 0;
            }
            System.out.println(getName() + " hit "
                    + " for <" + hitPoints + "> points damage.");
            System.out.println(getName() + " now has "
                    + getHitPoints() + " hit points remaining.");
            System.out.println();
        }

        if (this.hitPoints == 0) {
            System.out.println(name + " has been killed :-(");
        }

    }

    /*-------------------------------------------------------
    isAlive is used to see if a character is still alive by checking hit points
    Receives: nothing
    Returns: true is hero is alive, false otherwise
    This method calls: nothing
    This method is called by: unknown (intended for external use)
    ---------------------------------------------------------*/
    public boolean isAlive() {
        return (hitPoints > 0);
    }

    /*-------------------------------------------------------
    attack allows character to attempt attack on opponent.  First, chance to hit
    is considered.  If a hit can occur, then the damage is calculated based on
    character's damage range.  This damage is then applied to the opponenet.
    Receives: opponent being attacked
    Returns: nothing
    This method calls: Math.random(), subtractHitPoints()
    This method is called by: overridden versions of the method in monster and
    hero classes and externally
    ---------------------------------------------------------*/
    public void attack(DungeonCharacter opponent) {
        boolean canAttack;
        int damage;

        canAttack = Math.random() <= chanceToHit;

        if (canAttack) {
            damage = (int) (Math.random() * (damageMax - damageMin + 1))
                    + damageMin;
            opponent.subtractHitPoints(damage);

            System.out.println();
        } else {

            System.out.println(getName() + "'s attack on " + opponent.getName()
                    + " failed!");
            System.out.println();
        }

    }
}
