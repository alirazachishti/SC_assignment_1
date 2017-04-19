package alichisti.snakesandladders;                     // package of this project

import java.util.Random;                                // library of java for random function

public class SnakeAndLadders {

    private Random rand;                                // initialize random function
    Member computer;                                    // object of member class for computer
    Member human;                                       // object of member class for human
    int dice;                                           // for getting dice values
    boolean pre;                                        // to save previous move value for the player

/*----------------------------------- constructor to initialize the objects and attributes ------------------------------*/

    SnakeAndLadders() {                                
        computer = new Member("computer", false);
        human = new Member("ali", true);
        dice = 0;
        rand = new Random();
    }

/*--------------------------------------  to get computer object in return -----------------------------------*/

    Member getComputer() {  
        return computer;
    }

/*------------------------------------  to get human object in return -----------------------------------------*/

    Member getHuman() {
        return human;
    }

/*---------------------------------------  roll function for computer turn in bias form -------------------------*/

    int roll(int level) {
        if (level == 1) return rollForLevel1();                         // if computer is at level one and so on
        if (level == 2) return rollForLevel2();
        if (level == 3) return rollForLevel3();
        if (level == 4) return rollForLevel4();
        if (level == 5) return rollForLevel5();
        return 0;
    }

/*-------------------------------------- If there is human turn for a random number ------------------------------------*/
    
    int humanRoll(){
        return (rand.nextInt(6)+1);
    }

/* --------------------------------------- If computer is at level 1 ---------------------------------------------*/

    private int rollForLevel1() {
        int ans = rand.nextInt(10) + 1;             // create a random no between 1-10

        if(ans < 9)                                 // if no is less than 8 then create random else give 6 in return
            return (rand.nextInt(6) + 1);
        else
            return 6;
    }


/* --------------------------------------- If computer is at level 2 ---------------------------------------------*/

    private int rollForLevel2() {
        int ans = rand.nextInt(10) + 1;

        if(ans < 7)
            return (rand.nextInt(6) + 1);
        else
            return 6;
    }

/* --------------------------------------- If computer is at level 3 ---------------------------------------------*/

    private int rollForLevel3() {
        int ans = rand.nextInt(10) + 1;

        if(ans < 5)
            return (rand.nextInt(6) + 1);
        else
            return 6;

    }

/* --------------------------------------- If computer is at level 4 ---------------------------------------------*/

    private int rollForLevel4() {
        int ans = rand.nextInt(10) + 1;

        if(ans < 4)
            return (rand.nextInt(6) + 1);
        else
            return 6;

    }

/* --------------------------------------- If computer is at level 5 ---------------------------------------------*/

    private int rollForLevel5() {

        int ans = rand.nextInt(10) + 1;

        if(ans < 3)
            return (rand.nextInt(6) + 1);
        else
            return 6;

    }

/*----------------------------------------- checks whether move of the player is false or true, if dice valus is more 
than 6 then make its move true and return the new value of move of that player ------------------------------------*/

    Member checkPreConds(Member a, Member b) {

        if (!a.move) {                              // if the current player cannot move
            if (this.dice == 6) {                   // if dice value is 6
                a.move = true;                      // make its value true and make its turn true again
                a.turn = true;
                b.turn = false;
            }
        }
        return a;
    }

/*-------------------------------------- function to make new value of position of player ---------------------------*/

    void setPosition(Member a) {
        int check = 0;
        check = a.pos + this.dice;                              // make new value and save it too checkc
        if (check <= 100) a.pos = check;                        // if check is less than 100 then save it to player position
    }

/*------------------------- function to check post conditions after set new position of the player --------------------*/

    void checkPostConds(Member a, Member b) {

        int check = 0;
        check = checkSnakeAndLadders(a.pos);                // call to check snake and ladder functionality implementation

        if (check < a.pos) {                                // if there is snake at new value then 
            a.move = false;                                 // make its move false
            a.pos = check;                                  // give it a new value snake tail
        } 
        else {
            if (check > a.pos) {                            // if there is ladderr at new value
                a.turn = true;                              // give him turn again
                b.turn = false;                             
                a.move = true;                          
                a.pos = check;                              // give him a new value at top of ladder
            }
        }
    }

/*---------------------- function to check whether the new position is at snake mouth or ladder paddle ------------------*/

    private int checkSnakeAndLadders(int pos) {

        if (pos >= 50) {                                    // for last half board
            if (pos == 63) {
                pos = 81;
            } else {
                if (pos == 74) {
                    pos = 22;
                } else {
                    if (pos == 86) {
                        pos = 51;
                    } else {
                        if (pos == 99) {
                            pos = 39;
                        } else {
                            if (pos == 50) {
                                pos = 93;
                            }
                        }
                    }
                }
            }
        } 
        else {                                              // for 1st half board
            if (pos == 9) {
                pos = 31;
            } else {
                if (pos == 32) {
                    pos = 6;
                } else {
                    if (pos == 16) {
                        pos = 45;
                    } else {
                        if (pos == 18) {
                            pos = 64;
                        } else {
                            if (pos == 48) {
                                pos = 66;
                            }
                        }
                    }
                }
            }
        }

        return pos;                                                 // return position value
    }

/* ------------------------------ when dice is rolled then swap the turns of the players ----------------------------*/

    void swapTurns(Member a, Member b) {                           
        if (this.dice == 6) {                       // if dice is 6 then give turn again
            a.turn = true;
            b.turn = false;
        } 
        else {                                      // else give turn to other party
            a.turn = false;
            b.turn = true;
        }
    }

/*------------------------------ when a new level is formed with all new values ------------------------------------*/

    void reset() {
        computer = new Member("computer", false);
        human = new Member("ali", true);
        dice = 0;
    }

}
