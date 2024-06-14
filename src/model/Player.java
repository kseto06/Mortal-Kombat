package model;

import java.awt.image.BufferedImage;

import view.MainView;

public class Player {
    // Properties
    public String name = "";
    public Fighter fighter;
    public boolean movementDisabled = false;
    public boolean isKeyPressed = false;
    public boolean isAttacking = false;
    public boolean isBlocking = false;
    public boolean hasRun = false; //check if stagger has run
    public int currentX, currentY;
    public BufferedImage currentAnimationImg;
    public String currentAction;
    
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

    public void takeDamage(int damage) {
        this.fighter.HP -= damage;
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
            this.movementDisabled = true;
        } else if (currentX > opponent.currentX) {
            currentAnimationImg = fighter.specialRight;
            isAttacking = true;
            this.movementDisabled = true;
        }
    }

    public void block(Player opponent) {
        if (currentX <= opponent.currentX) {
            currentAnimationImg = fighter.blockLeft;
        } else if (currentX > opponent.currentX) {
            currentAnimationImg = fighter.blockRight;
        }
        currentAction = "block";
        movementDisabled = true; 
        isBlocking = true;
    }

    public void blockRelease(Player opponent) {
        if (currentX <= opponent.currentX) {
            currentAnimationImg = fighter.idleLeft;
        } else if (currentX > opponent.currentX) {
            currentAnimationImg = fighter.idleRight;
        }
        currentAction = "idle";
        movementDisabled = false; 
        isBlocking = false;
    }
    
    //Constructor
    public Player(String name) {
        this.name = name;
    }
}
