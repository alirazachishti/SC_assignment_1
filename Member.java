package alichisti.snakesandladders;

public class Member {                                       // class for the players of board game
    protected String name;                                  // save its name
    protected boolean turn;                                 // save its turn value
    protected boolean move;                                 // save its move condition
    protected int pos;                                      // save its positon value

/*--------------- constructor to initialize all variables ----------------------------------*/

    protected Member(String name, boolean turn) {
        this.name = name;
        this.turn = turn;
        this.move = true;
        this.pos = 0;
    }
}
