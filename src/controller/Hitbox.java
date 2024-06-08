package controller;

import model.GameState;

//TODO: Create special move hitbox logic 
public class Hitbox {
    //Properties
    GameState state;

    //Methods
    public String punchHitbox() {
        //Differentiate based on fighter, position, and opponent width
        int punchWidth = 0;
        if (state.currentPlayer.fighter.name == "Scorpion")  {
            punchWidth = 84;
        } else if (state.currentPlayer.fighter.name == "Subzero") {
            punchWidth = 74;
        }

        if (state.currentPlayer.equals(state.player1)
            && (state.currentPlayer.currentX + punchWidth >= state.player2.currentX && state.currentPlayer.currentX + punchWidth <= state.player2.currentX + state.player2.fighter.WIDTH)  
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.punchLeft) 
            && (state.currentPlayer.currentY == state.player2.currentY)) {

            //Check for blocking
            if (state.player2.isBlocking) {
                //TODO: Reduced damage if opponent blocking
                return "punch blocked";
            }
            
            //If hits, other player staggers back a few 'x' and cannot move for a bit
            System.out.println("Left punch hit");
            state.player2.movementDisabled = true;
            state.player2.currentAction = "got punched";
            state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got punched,"+state.player2.movementDisabled); //Send data to the opponent
            return "Left punch hit";

        } else if (state.currentPlayer.equals(state.player1) 
            && (state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - punchWidth) <= state.player2.currentX + state.player2.fighter.WIDTH && state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - punchWidth) >= state.player2.currentX)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.punchRight)
            && (state.currentPlayer.currentY == state.player2.currentY)) {

            //Check for blocking
            if (state.player2.isBlocking) {
                //TODO: Reduced damage if opponent blocking
                return "punch blocked";
            }
            
            System.out.println("Right punch hit");
            state.player2.movementDisabled = true;
            state.player2.currentAction = "got punched";
            state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got punched,"+state.player2.movementDisabled);
            return "Right punch hit";
        }

        if (state.currentPlayer.equals(state.player2) 
            && (state.currentPlayer.currentX + punchWidth >= state.player1.currentX && state.currentPlayer.currentX + punchWidth <= state.player1.currentX + state.player1.fighter.WIDTH)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.punchLeft) 
            && (state.currentPlayer.currentY == state.player1.currentY)) {

            //Check for blocking
            if (state.player1.isBlocking) {
                //TODO: Reduced damage if opponent blocking
                return "punch blocked";
            }

            System.out.println("Left punch hit");
            state.player1.movementDisabled = true;
            state.player1.currentAction = "got punched";
            state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got punched,"+state.player1.movementDisabled); 
            return "Left punch hit";

        } else if (state.currentPlayer.equals(state.player2) 
            && (state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - punchWidth) <= state.player1.currentX + state.player1.fighter.WIDTH && state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - punchWidth) >= state.player1.currentX)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.punchRight) 
            && (state.currentPlayer.currentY == state.player1.currentY)) {

            //Check for blocking
            if (state.player1.isBlocking) {
                //TODO: Reduced damage if opponent blocking
                return "punch blocked";
            }

            System.out.println("Right punch hit");
            state.player1.movementDisabled = true;
            state.player1.currentAction = "got punched";
            state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got punched,"+state.player1.movementDisabled);
            return "Right punch hit";
        }   

        return "No punch hit"; //Default case
    }

    public String kickHitbox() {
        //Differentiate based on fighter, position, and opponent width
        int kickWidth = 0;
        if (state.currentPlayer.fighter.name == "Scorpion")  {
            kickWidth = 152;

            //NOTE: Wider kick hitbox different for Scorpion so logic changes based on character
                if (state.currentPlayer.equals(state.player1)
                && (state.currentPlayer.currentX + kickWidth >= state.player2.currentX && state.currentPlayer.currentX + kickWidth <= state.player2.currentX + state.player2.fighter.WIDTH)  
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickLeft) 
                && (state.currentPlayer.currentY == state.player2.currentY)) {

                //Check for blocking
                if (state.player2.isBlocking) {
                    //TODO: Reduced damage if opponent blocking
                    return "kick blocked";
                }

                System.out.println("Left kick hit");
                state.player2.movementDisabled = true;
                state.player2.currentAction = "got kicked";
                state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got kicked,"+state.player2.movementDisabled); //Send data to the opponent
                return "Left kick hit";

            } else if (state.currentPlayer.equals(state.player1) 
                && (state.currentPlayer.currentX - kickWidth <= state.player2.currentX + state.player2.fighter.WIDTH && state.currentPlayer.currentX - kickWidth >= state.player2.currentX)
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickRight)
                && (state.currentPlayer.currentY == state.player2.currentY)) {

                //Check for blocking
                if (state.player2.isBlocking) {
                    //TODO: Reduced damage if opponent blocking
                    return "kick blocked";
                }

                System.out.println("Right kick hit");
                state.player2.movementDisabled = true;
                state.player2.currentAction = "got kicked";
                state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got kicked,"+state.player2.movementDisabled);
                return "Right kick hit";
            }

            if (state.currentPlayer.equals(state.player2) 
                && (state.currentPlayer.currentX + kickWidth >= state.player1.currentX && state.currentPlayer.currentX + kickWidth <= state.player1.currentX + state.player1.fighter.WIDTH)
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickLeft) 
                && (state.currentPlayer.currentY == state.player1.currentY)) {

                //Check for blocking
                if (state.player1.isBlocking) {
                    //TODO: Reduced damage if opponent blocking
                    return "kick blocked";
                }

                System.out.println("Left kick hit");
                state.player1.movementDisabled = true;
                state.player1.currentAction = "got kicked";
                state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got kicked,"+state.player1.movementDisabled); 
                return "Left kick hit";

            } else if (state.currentPlayer.equals(state.player2) 
                && (state.currentPlayer.currentX - kickWidth <= state.player1.currentX + state.player1.fighter.WIDTH && state.currentPlayer.currentX - kickWidth >= state.player1.currentX)
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickRight) 
                && (state.currentPlayer.currentY == state.player1.currentY)) {

                //Check for blocking
                if (state.player1.isBlocking) {
                    //TODO: Reduced damage if opponent blocking
                    return "kick blocked";
                }

                System.out.println("Right kick hit");
                state.player1.movementDisabled = true;
                state.player1.currentAction = "got kicked";
                state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got kicked,"+state.player1.movementDisabled);
                return "Right kick hit";
            }

        } else if (state.currentPlayer.fighter.name == "Subzero") {
            kickWidth = 86;

            if (state.currentPlayer.equals(state.player1)
                && (state.currentPlayer.currentX + kickWidth >= state.player2.currentX && state.currentPlayer.currentX + kickWidth <= state.player2.currentX + state.player2.fighter.WIDTH)  
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickLeft) 
                && (state.currentPlayer.currentY == state.player2.currentY)) {

                //Check for blocking
                if (state.player2.isBlocking) {
                    //TODO: Reduced damage if opponent blocking
                    return "kick blocked";
                }

                System.out.println("Left kick hit");
                state.player2.movementDisabled = true;
                state.player2.currentAction = "got kicked";
                state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got kicked,"+state.player2.movementDisabled); //Send data to the opponent
                return "Left kick hit";

            } else if (state.currentPlayer.equals(state.player1) 
                && (state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - kickWidth) <= state.player2.currentX + state.player2.fighter.WIDTH && state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - kickWidth) >= state.player2.currentX)
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickRight)
                && (state.currentPlayer.currentY == state.player2.currentY)) {

                //Check for blocking
                if (state.player2.isBlocking) {
                    //TODO: Reduced damage if opponent blocking
                    return "kick blocked";
                }
                
                System.out.println("Right kick hit");
                state.player2.movementDisabled = true;
                state.player2.currentAction = "got kicked";
                state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got kicked,"+state.player2.movementDisabled);
                return "Right kick hit";
            }

            if (state.currentPlayer.equals(state.player2) 
                && (state.currentPlayer.currentX + kickWidth >= state.player1.currentX && state.currentPlayer.currentX + kickWidth <= state.player1.currentX + state.player1.fighter.WIDTH)
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickLeft) 
                && (state.currentPlayer.currentY == state.player1.currentY)) {

                //Check for blocking
                if (state.player1.isBlocking) {
                    //TODO: Reduced damage if opponent blocking
                    return "kick blocked";
                }

                System.out.println("Left kick hit");
                state.player1.movementDisabled = true;
                state.player1.currentAction = "got kicked";
                state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got kicked,"+state.player1.movementDisabled); 
                return "Left kick hit";

            } else if (state.currentPlayer.equals(state.player2) 
                && (state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - kickWidth) <= state.player1.currentX + state.player1.fighter.WIDTH && state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - kickWidth) >= state.player1.currentX)
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickRight) 
                && (state.currentPlayer.currentY == state.player1.currentY)) {

                //Check for blocking
                if (state.player1.isBlocking) {
                    //TODO: Reduced damage if opponent blocking
                    return "kick blocked";
                }

                System.out.println("Right kick hit");
                state.player1.movementDisabled = true;
                state.player1.currentAction = "got kicked";
                state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got kicked,"+state.player1.movementDisabled);
                return "Right kick hit";
            }
        }

        return "No kick hit"; //Default case
    }

    public String uppercutHitbox() {
        //Differentiate based on fighter, position, and opponent width
        int uppercutWidth = 0;
        if (state.currentPlayer.fighter.name == "Scorpion")  {
            uppercutWidth = 50;
        } else if (state.currentPlayer.fighter.name == "Subzero") {
            uppercutWidth = 42;
        }

        if (state.currentPlayer.equals(state.player1)
            && (state.currentPlayer.currentX + uppercutWidth >= state.player2.currentX && state.currentPlayer.currentX + uppercutWidth <= state.player2.currentX + state.player2.fighter.WIDTH)  
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.uppercutLeft)) {

            //Check for blocking
            if (state.player2.isBlocking) {
                //TODO: Reduced damage if opponent blocking
                return "uppercut blocked";
            }

            System.out.println("Left uppercut hit");
            state.player2.movementDisabled = true;
            state.player2.currentAction = "got uppercut";
            state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got uppercut,"+state.player2.movementDisabled); //Send data to the opponent
            return "Left uppercut hit";

        } else if (state.currentPlayer.equals(state.player1) 
            && (state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - uppercutWidth) <= state.player2.currentX + state.player2.fighter.WIDTH && state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - uppercutWidth) >= state.player2.currentX)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.uppercutRight)) {

            //Check for blocking
            if (state.player2.isBlocking) {
                //TODO: Reduced damage if opponent blocking
                return "uppercut blocked";
            }

            System.out.println("Right uppercut hit");
            state.player2.movementDisabled = true;
            state.player2.currentAction = "got uppercut";
            state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got uppercut,"+state.player2.movementDisabled);
            return "Right uppercut hit";
        }

        if (state.currentPlayer.equals(state.player2) 
            && (state.currentPlayer.currentX + uppercutWidth >= state.player1.currentX && state.currentPlayer.currentX + uppercutWidth <= state.player1.currentX + state.player1.fighter.WIDTH)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.uppercutLeft)) {

            //Check for blocking
            if (state.player1.isBlocking) {
                //TODO: Reduced damage if opponent blocking
                return "uppercut blocked";
            }

            System.out.println("Left uppercut hit");
            state.player1.movementDisabled = true;
            state.player1.currentAction = "got uppercut";
            state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got uppercut,"+state.player1.movementDisabled); 
            return "Left uppercut hit";

        } else if (state.currentPlayer.equals(state.player2) 
            && (state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - uppercutWidth) <= state.player1.currentX + state.player1.fighter.WIDTH && state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - uppercutWidth) >= state.player1.currentX)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.uppercutRight)) {

            //Check for blocking
            if (state.player1.isBlocking) {
                //TODO: Reduced damage if opponent blocking
                return "uppercut blocked";
            }

            System.out.println("Right uppercut hit");
            state.player1.movementDisabled = true;
            state.player1.currentAction = "got uppercut";
            state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got uppercut,"+state.player1.movementDisabled);
            return "Right uppercut hit";
        }   

        return "No uppercut hit"; //Default case
    }

    /**
     * Calculates the hitbox for an ice ball hit (exclusive to a SubZero fighter)
     */
    public String IceBallHitbox() {
        //Differentiate based on fighter, position, and opponent width
        if (!state.currentPlayer.fighter.name.equals("Subzero")) { return ""; }

        if (state.currentPlayer.equals(state.player1)
            && (state.iceBall1.iceBallX + state.iceBall1.WIDTH >= state.player2.currentX && state.iceBall1.iceBallX + state.iceBall1.WIDTH <= state.player2.currentX+state.player2.fighter.WIDTH)  
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.specialLeft)
            && !state.player2.currentAction.equals("jump")) {

                //Check for blocking
                if (state.player2.isBlocking) {
                    //TODO: Reduced damage if opponent blocking
                    return "ice ball blocked";
                }

                System.out.println("Left special hit");
                state.player2.movementDisabled = true;
                state.player2.currentAction = "got special";
                state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got special,"+state.player2.movementDisabled); //Send data to the opponent
                return "Left special hit";

        } else if (state.currentPlayer.equals(state.player1) 
            && (state.iceBall1.iceBallX <= state.player2.currentX + state.player2.fighter.WIDTH && state.iceBall1.iceBallX >= state.player2.currentX)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.specialRight)
            && !state.player2.currentAction.equals("jump")) {

                //Check for blocking
                if (state.player2.isBlocking) {
                    //TODO: Reduced damage if opponent blocking
                    return "ice ball blocked";
                }

                System.out.println("Right special hit");
                state.player2.movementDisabled = true;
                state.player2.currentAction = "got special";
                state.ssm.sendText("client,"+state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",got special,"+state.currentPlayer.movementDisabled);
                return "Right special hit";
        }

        if (state.currentPlayer.equals(state.player2) 
            && (state.iceBall2.iceBallX + state.iceBall2.WIDTH >= state.player1.currentX && state.iceBall2.iceBallX + state.iceBall2.WIDTH <= state.player1.currentX+state.player1.fighter.WIDTH)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.specialLeft)
            && !state.player1.currentAction.equals("jump")) {

                //Check for blocking
                if (state.player1.isBlocking) {
                    //TODO: Reduced damage if opponent blocking
                    return "ice ball blocked";
                }

                System.out.println("Left special hit");
                state.player1.movementDisabled = true;
                state.player1.currentAction = "got special";
                state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got special,"+state.player1.movementDisabled); 
                return "Left special hit";

        } else if (state.currentPlayer.equals(state.player2) 
            && (state.iceBall2.iceBallX >= state.player2.currentX && state.iceBall2.iceBallX <= state.player2.currentX+state.player2.fighter.WIDTH)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.specialRight)
            && !state.player1.currentAction.equals("jump")) {

                //Check for blocking
                if (state.player1.isBlocking) {
                    //TODO: Reduced damage if opponent blocking
                    return "ice ball blocked";
                }

                System.out.println("Right special hit");
                state.player1.movementDisabled = true;
                state.player1.currentAction = "got special";
                state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got special,"+state.player1.movementDisabled);
                return "Right special hit";
        }   
        
        return "No ice ball hit"; //Default case
    }

    //Constructor
    public Hitbox(GameState state) {
        this.state = state;
    }
}
