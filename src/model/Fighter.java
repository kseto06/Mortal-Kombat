package model;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Fighter {
    // Properties
    public int HP = 1000;
    public String name;
    public BufferedImage idleLeft, idleRight, punchLeft, punchRight, kickLeft, kickRight, uppercutLeft, uppercutRight, specialLeft, specialRight, staggerLeft = null, staggerRight = null, blockLeft = null, blockRight = null;
    public int WIDTH, HEIGHT;
    public boolean isSpecialBeingUsed = false;
    public int baseDmg = 100;
    public int punchDamage, kickDamage, uppercutDamage;
    public int specialDamage = 100;
    
    // Methods
    public void loadData(String name) {
        // read src/data/fighters.csv
        // format: name, hp, baseDmg, specialDmg
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/data/fighters.csv"));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(name)) {
                    this.HP = Integer.parseInt(parts[1]);
                    this.baseDmg = Integer.parseInt(parts[2]);
                    this.specialDamage = Integer.parseInt(parts[3]);
                    break;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.punchDamage = (int)(this.baseDmg * 0.5);
        this.kickDamage = (int)(this.baseDmg * 0.7);
        this.uppercutDamage = (int)(this.baseDmg * 1.4);
    }
}
