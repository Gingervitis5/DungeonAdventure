/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonCharacters;

import AttackPackage.Attack;
import javafx.scene.image.Image;

/**
 *
 * @author Gingervitis
 */
public class Warrior extends Hero {

    private Attack specialAttack;

    public Warrior() {

        super("Warrior", 125, 4, .8, 35, 60, .2);
        this.specialAttack = wFactory.getAttack("Warrior");

    }

    public void attack(DungeonCharacter opponent) {
        System.out.println(getName() + " swings a mighty sword at "
                + opponent.getName() + ":");
        super.attack(opponent);
    }

    @Override
    protected Attack getSpecialAttack() {
        return this.specialAttack;
    }

    @Override
    protected String getSpecialAttackName() {
        return "Crushing Blow";
    }

    @Override
    protected void handleSpecial(DungeonCharacter opponent) {
        int blowPoints = this.specialAttack.specialAttack();
        if (blowPoints != 0) {
            System.out.println(getName() + " lands a CRUSHING BLOW for " + blowPoints
                    + " damage!");
            opponent.subtractHitPoints(blowPoints);
        } else {
            System.out.println(getName() + " failed to land a crushing blow");
            System.out.println();
        }

    }

    @Override
    protected Image setSuperImage() {
        return new Image("Pixel Art/Warrior.png");
    }

}
