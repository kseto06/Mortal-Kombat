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
            //If hits, other player staggers back a few 'x' and cannot move for a bit
            System.out.println("Left punch hit");
            state.player2.movementDisabled = true;
            // state.player2.currentAnimationImg = state.player2.fighter.staggerRight;
            // state.player2.currentX += 20;
            state.player2.currentAction = "got punched";
            state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got punched,"+state.player2.movementDisabled); //Send data to the opponent
            return "Left punch hit";

        } else if (state.currentPlayer.equals(state.player1) 
            && (state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - punchWidth) <= state.player2.currentX + state.player2.fighter.WIDTH && state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - punchWidth) >= state.player2.currentX)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.punchRight)
            && (state.currentPlayer.currentY == state.player2.currentY)) {
            System.out.println("Right punch hit");
            state.player2.movementDisabled = true;
            // state.player2.currentAnimationImg = state.player2.fighter.staggerLeft;
            // state.player2.currentX -= 20;
            state.player2.currentAction = "got punched";
            state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got punched,"+state.player2.movementDisabled);
            return "Right punch hit";
        }

        if (state.currentPlayer.equals(state.player2) 
            && (state.currentPlayer.currentX + punchWidth >= state.player1.currentX && state.currentPlayer.currentX + punchWidth <= state.player1.currentX + state.player1.fighter.WIDTH)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.punchLeft) 
            && (state.currentPlayer.currentY == state.player1.currentY)) {
            //If hits, other player staggers back a few 'x' and cannot move for a bit
            System.out.println("Left punch hit");
            state.player1.movementDisabled = true;
            // state.player1.currentAnimationImg = state.player1.fighter.staggerRight;
            // state.player1.currentX += 20;
            state.player1.currentAction = "got punched";
            state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got punched,"+state.player1.movementDisabled); 
            return "Left punch hit";

        } else if (state.currentPlayer.equals(state.player2) 
            && (state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - punchWidth) <= state.player1.currentX + state.player1.fighter.WIDTH && state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - punchWidth) >= state.player1.currentX)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.punchRight) 
            && (state.currentPlayer.currentY == state.player1.currentY)) {
            System.out.println("Right punch hit");
            state.player1.movementDisabled = true;
            // state.player1.currentAnimationImg = state.player1.fighter.staggerLeft;
            // state.player1.currentX -= 20;
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

            //TODO: Wider kick hitbox different for Scorp
                if (state.currentPlayer.equals(state.player1)
                && (state.currentPlayer.currentX + kickWidth >= state.player2.currentX && state.currentPlayer.currentX + kickWidth <= state.player2.currentX + state.player2.fighter.WIDTH)  
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickLeft) 
                && (state.currentPlayer.currentY == state.player2.currentY)) {
                //If hits, other player staggers back a few 'x' and cannot move for a bit
                System.out.println("Left kick hit");
                state.player2.movementDisabled = true;
                // state.player2.currentAnimationImg = state.player2.fighter.staggerRight;
                // state.player2.currentX += 40;
                state.player2.currentAction = "got kicked";
                state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got kicked,"+state.player2.movementDisabled); //Send data to the opponent
                return "Left kick hit";

            } else if (state.currentPlayer.equals(state.player1) 
                && (state.currentPlayer.currentX - kickWidth <= state.player2.currentX + state.player2.fighter.WIDTH && state.currentPlayer.currentX - kickWidth >= state.player2.currentX)
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickRight)
                && (state.currentPlayer.currentY == state.player2.currentY)) {
                System.out.println("Right kick hit");
                state.player2.movementDisabled = true;
                // state.player2.currentAnimationImg = state.player2.fighter.staggerLeft;
                // state.player2.currentX -= 20;
                state.player2.currentAction = "got kicked";
                state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got kicked,"+state.player2.movementDisabled);
                return "Right kick hit";
            }

            if (state.currentPlayer.equals(state.player2) 
                && (state.currentPlayer.currentX + kickWidth >= state.player1.currentX && state.currentPlayer.currentX + kickWidth <= state.player1.currentX + state.player1.fighter.WIDTH)
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickLeft) 
                && (state.currentPlayer.currentY == state.player1.currentY)) {
                //If hits, other player staggers back a few 'x' and cannot move for a bit
                System.out.println("Left kick hit");
                state.player1.movementDisabled = true;
                // state.player1.currentAnimationImg = state.player1.fighter.staggerRight;
                // state.player1.currentX += 20;
                state.player1.currentAction = "got kicked";
                state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got kicked,"+state.player1.movementDisabled); 
                return "Left kick hit";

            } else if (state.currentPlayer.equals(state.player2) 
                && (state.currentPlayer.currentX - kickWidth <= state.player1.currentX + state.player1.fighter.WIDTH && state.currentPlayer.currentX - kickWidth >= state.player1.currentX)
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickRight) 
                && (state.currentPlayer.currentY == state.player1.currentY)) {
                System.out.println("Right kick hit");
                state.player1.movementDisabled = true;
                // state.player1.currentAnimationImg = state.player1.fighter.staggerLeft;
                // state.player1.currentX -= 20;
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
                //If hits, other player staggers back a few 'x' and cannot move for a bit
                System.out.println("Left kick hit");
                state.player2.movementDisabled = true;
                // state.player2.currentAnimationImg = state.player2.fighter.staggerRight;
                // state.player2.currentX += 40;
                state.player2.currentAction = "got kicked";
                state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got kicked,"+state.player2.movementDisabled); //Send data to the opponent
                return "Left kick hit";

            } else if (state.currentPlayer.equals(state.player1) 
                && (state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - kickWidth) <= state.player2.currentX + state.player2.fighter.WIDTH && state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - kickWidth) >= state.player2.currentX)
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickRight)
                && (state.currentPlayer.currentY == state.player2.currentY)) {
                System.out.println("Right kick hit");
                state.player2.movementDisabled = true;
                // state.player2.currentAnimationImg = state.player2.fighter.staggerLeft;
                // state.player2.currentX -= 20;
                state.player2.currentAction = "got kicked";
                state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got kicked,"+state.player2.movementDisabled);
                return "Right kick hit";
            }

            if (state.currentPlayer.equals(state.player2) 
                && (state.currentPlayer.currentX + kickWidth >= state.player1.currentX && state.currentPlayer.currentX + kickWidth <= state.player1.currentX + state.player1.fighter.WIDTH)
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickLeft) 
                && (state.currentPlayer.currentY == state.player1.currentY)) {
                //If hits, other player staggers back a few 'x' and cannot move for a bit
                System.out.println("Left kick hit");
                state.player1.movementDisabled = true;
                // state.player1.currentAnimationImg = state.player1.fighter.staggerRight;
                // state.player1.currentX += 20;
                state.player1.currentAction = "got kicked";
                state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got kicked,"+state.player1.movementDisabled); 
                return "Left kick hit";

            } else if (state.currentPlayer.equals(state.player2) 
                && (state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - kickWidth) <= state.player1.currentX + state.player1.fighter.WIDTH && state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - kickWidth) >= state.player1.currentX)
                && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickRight) 
                && (state.currentPlayer.currentY == state.player1.currentY)) {
                System.out.println("Right kick hit");
                state.player1.movementDisabled = true;
                // state.player1.currentAnimationImg = state.player1.fighter.staggerLeft;
                // state.player1.currentX -= 20;
                state.player1.currentAction = "got kicked";
                state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got kicked,"+state.player1.movementDisabled);
                return "Right kick hit";
            }
        }

        if (state.currentPlayer.equals(state.player1)
            && (state.currentPlayer.currentX + kickWidth >= state.player2.currentX && state.currentPlayer.currentX + kickWidth <= state.player2.currentX + state.player2.fighter.WIDTH)  
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickLeft) 
            && (state.currentPlayer.currentY == state.player2.currentY)) {
            //If hits, other player staggers back a few 'x' and cannot move for a bit
            System.out.println("Left kick hit");
            state.player2.movementDisabled = true;
            // state.player2.currentAnimationImg = state.player2.fighter.staggerRight;
            // state.player2.currentX += 40;
            state.player2.currentAction = "got kicked";
            state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got kicked,"+state.player2.movementDisabled); //Send data to the opponent
            return "Left kick hit";

        } else if (state.currentPlayer.equals(state.player1) 
            && (state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - kickWidth) <= state.player2.currentX + state.player2.fighter.WIDTH && state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - kickWidth) >= state.player2.currentX)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickRight)
            && (state.currentPlayer.currentY == state.player2.currentY)) {
            System.out.println("Right kick hit");
            state.player2.movementDisabled = true;
            // state.player2.currentAnimationImg = state.player2.fighter.staggerLeft;
            // state.player2.currentX -= 20;
            state.player2.currentAction = "got kicked";
            state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got kicked,"+state.player2.movementDisabled);
            return "Right kick hit";
        }

        if (state.currentPlayer.equals(state.player2) 
            && (state.currentPlayer.currentX + kickWidth >= state.player1.currentX && state.currentPlayer.currentX + kickWidth <= state.player1.currentX + state.player1.fighter.WIDTH)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickLeft) 
            && (state.currentPlayer.currentY == state.player1.currentY)) {
            //If hits, other player staggers back a few 'x' and cannot move for a bit
            System.out.println("Left kick hit");
            state.player1.movementDisabled = true;
            // state.player1.currentAnimationImg = state.player1.fighter.staggerRight;
            // state.player1.currentX += 20;
            state.player1.currentAction = "got kicked";
            state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got kicked,"+state.player1.movementDisabled); 
            return "Left kick hit";

        } else if (state.currentPlayer.equals(state.player2) 
            && (state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - kickWidth) <= state.player1.currentX + state.player1.fighter.WIDTH && state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - kickWidth) >= state.player1.currentX)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.kickRight) 
            && (state.currentPlayer.currentY == state.player1.currentY)) {
            System.out.println("Right kick hit");
            state.player1.movementDisabled = true;
            // state.player1.currentAnimationImg = state.player1.fighter.staggerLeft;
            // state.player1.currentX -= 20;
            state.player1.currentAction = "got kicked";
            state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got kicked,"+state.player1.movementDisabled);
            return "Right kick hit";
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
            //If hits, other player staggers back a few 'x' and cannot move for a bit
            System.out.println("Left uppercut hit");
            state.player2.movementDisabled = true;
            // state.player2.currentAnimationImg = state.player2.fighter.staggerRight;
            // state.player2.currentX += 20;
            state.player2.currentAction = "got uppercut";
            state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got uppercut,"+state.player2.movementDisabled); //Send data to the opponent
            return "Left uppercut hit";

        } else if (state.currentPlayer.equals(state.player1) 
            && (state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - uppercutWidth) <= state.player2.currentX + state.player2.fighter.WIDTH && state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - uppercutWidth) >= state.player2.currentX)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.uppercutRight)) {
            System.out.println("Right uppercut hit");
            state.player2.movementDisabled = true;
            // state.player2.currentAnimationImg = state.player2.fighter.staggerLeft;
            // state.player2.currentX -= 20;
            state.player2.currentAction = "got uppercut";
            state.ssm.sendText("client,"+state.player2.currentX+","+state.player2.currentY+","+state.player2.isAttacking+",got uppercut,"+state.player2.movementDisabled);
            return "Right uppercut hit";
        }

        if (state.currentPlayer.equals(state.player2) 
            && (state.currentPlayer.currentX + uppercutWidth >= state.player1.currentX && state.currentPlayer.currentX + uppercutWidth <= state.player1.currentX + state.player1.fighter.WIDTH)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.uppercutLeft)) {
            //If hits, other player staggers back a few 'x' and cannot move for a bit
            System.out.println("Left uppercut hit");
            state.player1.movementDisabled = true;
            // state.player1.currentAnimationImg = state.player1.fighter.staggerRight;
            // state.player1.currentX += 20;
            state.player1.currentAction = "got uppercut";
            state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got uppercut,"+state.player1.movementDisabled); 
            return "Left uppercut hit";

        } else if (state.currentPlayer.equals(state.player2) 
            && (state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - uppercutWidth) <= state.player1.currentX + state.player1.fighter.WIDTH && state.currentPlayer.currentX + (state.currentPlayer.fighter.WIDTH - uppercutWidth) >= state.player1.currentX)
            && state.currentPlayer.currentAnimationImg.equals(state.currentPlayer.fighter.uppercutRight)) {
            System.out.println("Right uppercut hit");
            state.player1.movementDisabled = true;
            // state.player1.currentAnimationImg = state.player1.fighter.staggerLeft;
            // state.player1.currentX -= 20;
            state.player1.currentAction = "got uppercut";
            state.ssm.sendText("host,"+state.player1.currentX+","+state.player1.currentY+","+state.player1.isAttacking+",got uppercut,"+state.player1.movementDisabled);
            return "Right uppercut hit";
        }   

        return "No uppercut hit"; //Default case
    }

    public void specialHitbox() {

    }

    //Constructor
    public Hitbox(GameState state) {
        this.state = state;
    }
}
