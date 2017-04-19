/*--------------------------- call to snakes and ladder package --------------------------------*/

package alichisti.snakesandladders;

/*---------------------- call to android libraries ----------------------------------*/

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*--------------------------- activity class for all android implementation -----------------------*/

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private SnakeAndLadders game = new SnakeAndLadders();                       // make object of snakes and ladder game
    private TextView human;                                                     // make test view object of human player
    private TextView computer;                                                  // make text view objecc of computer player
    private int CURRENT_LEVEL;                                                  // save current running level value 

/*----------------------------functiont of overriding create activity in the start of execution ---------------------------*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {                        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.dice).setOnClickListener(this);                        // initialize the click listener for user
        human = (TextView) findViewById(R.id.human);                             // make type cast value of human object
        computer = (TextView) findViewById(R.id.computer);                       // make type cast value of computer object

        CURRENT_LEVEL = 1;                                                       // initialze the cuurent level with 1
    }

/*------------------------------- function for setting of new level ---------------------------------*/

    private void nextLevel() {
        CURRENT_LEVEL++;                                                         // make increment in level value after its next level

        if (CURRENT_LEVEL > 5) {                                                // if level is more than 5 then game over
            Toast.makeText(GameActivity.this, "Game Over", Toast.LENGTH_SHORT).show();

            finish();                                                           // end of game
        } 
        else {
            ((TextView) findViewById(R.id.level)).setText("Level " + String.valueOf(CURRENT_LEVEL));    // print level value
            human.setText("Human\n0");                                          // set human text on screen with its positon
            computer.setText("Computer\n0");                                    // set computer text on screen with its positon
            game.reset();                                                       // call to reset all objects 

            Toast.makeText(GameActivity.this, "Level Up", Toast.LENGTH_SHORT).show();
        }
    }

/*;--------------------------- to get dice value at each turn ----------------------------------------*/

    private int getDice() {

        int dice = 0;

        if (game.human.turn) {                                          // if human turn then it will be random
            dice = game.humanRoll();
        } 
        else                                                            // if computer turn the make it bias
            dice = game.roll(CURRENT_LEVEL);

        int ids[] = {                                                   // array to show dive image
                R.drawable.dice_1,
                R.drawable.dice_2,
                R.drawable.dice_3,
                R.drawable.dice_4,
                R.drawable.dice_5,
                R.drawable.dice_6,
        };

        ((ImageView) findViewById(R.id.dice)).setImageResource(ids[dice - 1]);

        return dice;                                                    // reutrn dice value
    }

/*-------------------- override the onclick function for the user click -------------------------------*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dice:                                              // if dice is touched

                if (game.human.turn) {                                  // if its human's turn
                    Toast.makeText(GameActivity.this, "Human playing ...", Toast.LENGTH_SHORT).show();

                    game.dice = getDice();                              // get dice vaue
                    game.swapTurns(game.getHuman(), game.getComputer());// swap the turns of both players
                    game.pre = game.human.move;                         // save the previous value of move
                    game.checkPreConds(game.human, game.computer);      // check move condition for the current player
                    if (game.human.move && game.pre) {                  // if move is available
                        game.setPosition(game.human);                   // set new posiotion
                        game.checkPostConds(game.human, game.computer); // check its all constraints on new positoin
                    }

                    human.setText("Human\n" + String.valueOf(game.human.pos));  // uppdate the human value on screen

                    if (game.human.pos == 100) {                        // if human wins
                        Toast.makeText(GameActivity.this, "You win!", Toast.LENGTH_SHORT).show();
                        game.reset();                                   // reset for next level
                        nextLevel();                                    // set new level
                    }

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {                             // if its computer turn do same task in oppposite form
                            if (!game.human.turn) {
                                Toast.makeText(GameActivity.this, "Computer playing ...", Toast.LENGTH_SHORT).show();

                                game.dice = getDice();
                                game.swapTurns(game.computer, game.human);
                                game.pre = game.computer.move;
                                game.computer = game.checkPreConds(game.computer, game.human);
                                if (game.computer.move && game.pre) {
                                    game.setPosition(game.computer);
                                    game.checkPostConds(game.computer, game.human);
                                }

                                computer.setText("Computer\n" + String.valueOf(game.computer.pos));

                                if (game.computer.pos == 100) {         // if computer wins end the game
                                    Toast.makeText(GameActivity.this, "You lose!", Toast.LENGTH_SHORT).show();
                                }

                                if (game.computer.turn) {               // if it is 6 for the computer
                                    run();
                                }
                            }
                        }
                    }, 3000);                                           // computer waits for 3 seconds
                    break;
                }
        }
    }

}
