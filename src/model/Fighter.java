package model;

import java.awt.image.BufferedImage;

public class Fighter {
    // Properties
    public int HP = 1000;
    public String name;
    public BufferedImage idleLeft, idleRight, punchLeft, punchRight, kickLeft, kickRight, uppercutLeft, uppercutRight, specialLeft, specialRight, staggerLeft = null, staggerRight = null, blockLeft = null, blockRight = null;
    public int WIDTH, HEIGHT;
    public boolean isSpecialBeingUsed = false;
    public int punchDamage, kickDamage, uppercutDamage, specialDamage;
    
    // Constructor
    public Fighter() {
        // TO-DO: Implement constructor
    }

    public Fighter(int HP) {
        this.HP = HP;
    }
}
