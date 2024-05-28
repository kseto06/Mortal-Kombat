package model;

public class Player {
    // Properties
    public String name;
    public Fighter fighter;
    public boolean movementDisabled = false;
    
    // Methods
    public void chooseFighter(String fighterName) {
        if (fighterName.equals("Scorpion")) {
            fighter = new ScorpionFighter();
        } else if (fighterName.equals("Subzero")) {
            fighter = new SubzeroFighter();
        }
    }

    //Constructor
    public Player(String name, Fighter fighter, boolean movementDisabled) {
        this.name = name;
        this.fighter = fighter;
        this.movementDisabled = movementDisabled;
    }
}
