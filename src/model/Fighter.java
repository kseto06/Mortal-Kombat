package model;

import java.awt.image.BufferedImage;

public class Fighter {
    // Properties
    public int HP = 1000;
    public String name;
    public BufferedImage idleLeft, idleRight, punchLeft, punchRight, kickLeft, kickRight, uppercutLeft, uppercutRight, specialLeft, specialRight;
    public int WIDTH, HEIGHT;
    
    // Methods
    public void punch(Fighter opponent) {
        // TO-DO: Implement attack method
    }

    public void kick(Fighter opponent) {
        // TO-DO: Implement attack method
    }

    public void uppercut(Fighter opponent) {
        // TO-DO: Implement uppercut method
    }

    public void block() {
        // TO-DO: Implement block method
    }

    public void takeDamage(int damage) {
        // TO-DO: Implement takeDamage method
    }
    
    // Constructor
    public Fighter() {
        // TO-DO: Implement constructor
    }

    public Fighter(int HP) {
        this.HP = HP;
    }
}
