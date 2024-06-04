package model;

import java.awt.image.BufferedImage;

public class Player {
    // Properties
    public String name = "";
    public Fighter fighter;
    public boolean movementDisabled = false;
    public boolean isKeyPressed = false;
    public boolean isAttacking = false;
    public int currentX, currentY;
    public BufferedImage currentAnimationImg;
    
    // Methods
    public void chooseFighter(String fighterName) {
        if (fighterName.equals("Scorpion")) {
            fighter = new ScorpionFighter();
            fighter.name = "Scorpion";
        } else if (fighterName.equals("Subzero")) {
            fighter = new SubzeroFighter();
            fighter.name = "Subzero";
        }
    }

    public void punch(Player opponent) {
        if (currentX <= opponent.currentX) {
            currentAnimationImg = fighter.punchLeft;
            isAttacking = true;
        } else if (currentX > opponent.currentX) {
            currentAnimationImg = fighter.punchRight;
            isAttacking = true;
        }
    }

    public void kick(Player opponent) {
        if (currentX <= opponent.currentX) {
            currentAnimationImg = fighter.kickLeft;
            isAttacking = true;
        } else if (currentX > opponent.currentX) {
            currentAnimationImg = fighter.kickRight;
            isAttacking = true;
        }
    }

    public void uppercut(Player opponent) {
        if (currentX <= opponent.currentX) {
            currentAnimationImg = fighter.uppercutLeft;
            isAttacking = true;
        } else if (currentX > opponent.currentX) {
            currentAnimationImg = fighter.uppercutRight;
            isAttacking = true;
        }
    }

    public void specialMove(Player opponent) {
        if (currentX <= opponent.currentX) {
            currentAnimationImg = fighter.specialLeft;
            isAttacking = true;
        } else if (currentX > opponent.currentX) {
            currentAnimationImg = fighter.specialRight;
            isAttacking = true;
        }
    }

    //Constructor
    public Player(String name) {
        this.name = name;
    }
}
