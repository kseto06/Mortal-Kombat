package model;

import java.awt.image.BufferedImage;

/**Player class that stores the Player's attributes throughout the game*/
public class Player {
    // Properties

    /**Name of the Player*/
    public String name = "";

    /**Chosen fighter of the Player*/
    public Fighter fighter;

    /**Sets disabling and enabling movement for the player*/
    public boolean movementDisabled = false;

    /**Tells if the player is pressing a key or not*/
    public boolean isKeyPressed = false;

    /**Tells if the player is attacking or not*/
    public boolean isAttacking = false;

    /**Tells if the player is blocking or not*/
    public boolean isBlocking = false;
    
    /**Checks if a stagger has run*/
    public boolean hasRun = false; 

    /**X and Y positions of the player's fighter*/
    public int currentX, currentY;

    /**Stores the current animation image of the player in the game*/
    public BufferedImage currentAnimationImg;

    /**Stores the current action of the player, ex: punch/got punched*/
    public String currentAction;
    
    // Methods

    /**
     * Function to choose the fighter in CharacterSelectionScreen
     * @param fighterName name of the Fighter
     */
    public void chooseFighter(String fighterName) {
        if (fighterName.equals("Scorpion")) {
            fighter = new ScorpionFighter();
            fighter.name = "Scorpion";
        } else if (fighterName.equals("Subzero")) {
            fighter = new SubzeroFighter();
            fighter.name = "Subzero";
        }
    }

    /**
     * Function to subtract the fighter's HP when they take damage
     * @param damage Amount of damage taken 
     */ 
    public void takeDamage(int damage) {
        this.fighter.HP -= damage;
    }

    /**
     * Function to track if a player punches
     * @param opponent opponent player parameter
     */
    public void punch(Player opponent) {
        if (currentX <= opponent.currentX) {
            currentAnimationImg = fighter.punchLeft;
            isAttacking = true;
        } else if (currentX > opponent.currentX) {
            currentAnimationImg = fighter.punchRight;
            isAttacking = true;
        }
    }

    /**
     * Function to track if a player kicks
     * @param opponent opponent player parameter
     */
    public void kick(Player opponent) {
        if (currentX <= opponent.currentX) {
            currentAnimationImg = fighter.kickLeft;
            isAttacking = true;
        } else if (currentX > opponent.currentX) {
            currentAnimationImg = fighter.kickRight;
            isAttacking = true;
        }
    }

    /**
     * Function to track if a player uppercuts
     * @param opponent opponent player parameter
     */
    public void uppercut(Player opponent) {
        if (currentX <= opponent.currentX) {
            currentAnimationImg = fighter.uppercutLeft;
            isAttacking = true;
        } else if (currentX > opponent.currentX) {
            currentAnimationImg = fighter.uppercutRight;
            isAttacking = true;
        }
    }

    /**
     * Function to track if a player uses their special move
     * @param opponent opponent player parameter
     */
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

    /**
     * Function to track if a player blocks
     * @param opponent opponent player parameter
     */
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

    /**
     * Function to track if a player stops blocking
     * @param opponent opponent player parameter
     */
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
    
    /**
     * Constructor to create the player
     * @param name Name of the player
     */
    public Player(String name) {
        this.name = name;
    }
}
