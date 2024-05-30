package model;

public class Player {
    // Properties
    public String name = "";
    public Fighter fighter;
    public boolean movementDisabled = false;
    
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

    //Constructor
    public Player(String name) {
        this.name = name;
    }
}
