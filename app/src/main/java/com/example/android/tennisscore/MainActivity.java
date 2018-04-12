package com.example.android.tennisscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int scorePlayerOne;    // Tracks the score for Player One
    int scorePlayerTwo;    // Tracks the score for Player Two
    int set1PlayerOne;     // Track the score set for Player One
    int set2PlayerOne;
    int set3PlayerOne;
    int set1PlayerTwo;     // Track the score set for Player Two
    int set2PlayerTwo;
    int set3PlayerTwo;
    int setOfTheGame = 1;      // Tracks the current set of the game

    TextView p1faultView;      //  Serve to link p1DoubleFault Button
    TextView p2faultView;      //  Serve to link p2DoubleFault Button
    boolean newGame = false;
    boolean endOfGame = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // A user interface element the user can tap to perform an action.
        // If The user will tap the player2FaultButton, it will add a point to player one.
        Button p1DoubleFaultButton = findViewById(R.id.player_one_double_fault);
        p1DoubleFaultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Player one you have committed a double fault, you lost the score!",
                        Toast.LENGTH_LONG).show();
                scorePlayerTwo = scorePlayerTwo + 1;
                displayForPlayerTwo(scorePlayerTwo);
            }
        });
        // A user interface element the user can tap to perform an action.
        // If The user will tap the player2FaultButton, it will add a point to player one.
        Button p2DoubleFaultButton = findViewById(R.id.player_two_double_fault);
        p2DoubleFaultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Player Two you have committed a double fault, you lost the score!",
                        Toast.LENGTH_LONG).show();
                scorePlayerOne = scorePlayerOne + 1;
                displayForPlayerOne(scorePlayerOne);
            }
        });
        // A user interface element the user can tap to perform an action.
        // If The user will tap the p1OutButton, it will add a point to player two.
        Button p1OutButton = findViewById(R.id.player_one_out_button);
        p1OutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Out! You lost the score!",
                        Toast.LENGTH_LONG).show();
                scorePlayerTwo = scorePlayerTwo + 1;
                displayForPlayerTwo(scorePlayerTwo);
            }
        });
        // A user interface element the user can tap to perform an action.
        // If The user will tap the p2OutButton, it will add a point to player one.
        Button p2OutButton = findViewById(R.id.player_two_out_button);
        p2OutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Out! You lost the score!"
                        , Toast.LENGTH_LONG).show();
                scorePlayerOne = scorePlayerOne + 1;
                displayForPlayerOne(scorePlayerOne);
            }
        });

    }

    public void addOneFaultToPlayerOne(View view) {
        if (!endOfGame) {
            p1faultView = findViewById(R.id.player_one_fault);
            Toast.makeText(getApplicationContext(),
                    "Player One you have committed a fault!", Toast.LENGTH_LONG).show();
        }
    }

    public void addOneFaultToPlayerTwo(View view) {
        if (!endOfGame) {
            p2faultView = findViewById(R.id.player_two_fault);
            Toast.makeText(getApplicationContext(),
                    "Player Two you have committed a fault!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Increase the score for Player One.
     */
    public void addScoreForPlayerOne(View v) {

        if (!endOfGame) {
            scorePlayerOne = scorePlayerOne + 1;
            displayForPlayerOne(scorePlayerOne);
        } else if (set1PlayerOne >= 6 && set2PlayerOne >= 6) {
            Toast.makeText(getApplicationContext(),
                    "Player One congratulations, you win!", Toast.LENGTH_LONG).show();
            scorePlayerOne = 0;
            scorePlayerTwo = 0;
            displayForPlayerOne(scorePlayerOne);
            displayForPlayerTwo(scorePlayerTwo);
            resetScore(v);
        } else if (set3PlayerOne >= 6 && set3PlayerOne - set3PlayerTwo >= 2
                || set3PlayerOne == 7) {
            Toast.makeText(getApplicationContext(),
                    "Player One congratulations, you win!", Toast.LENGTH_LONG).show();
            scorePlayerOne = 0;
            scorePlayerTwo = 0;
            displayForPlayerOne(scorePlayerOne);
            displayForPlayerTwo(scorePlayerTwo);
            resetScore(v);
        } else {
            scorePlayerOne = 0;
            scorePlayerTwo = 0;
            endOfGame = false;
            displayForPlayerOne(scorePlayerOne);
            displayForPlayerTwo(scorePlayerTwo);
        }
    }

    /**
     * Increase the score of the set for Player One by 1 point.
     */
    public void addSetScoreForPlayerOne(View v) {

        if (setOfTheGame == 1) {
            if ((set1PlayerOne == 6 && set1PlayerOne - set1PlayerTwo >= 2)
                    || (set1PlayerOne == 7)) {
                setOfTheGame = 2;
                set2PlayerOne++;
                TextView scoreView = findViewById(R.id.player_one_score_set_2);
                scoreView.setText(String.valueOf(set2PlayerOne));
            } else {
                set1PlayerOne++;
            }
            newGame = false;
            TextView scoreView = findViewById(R.id.player_one_score_set_1);
            scoreView.setText(String.valueOf(set1PlayerOne));
        } else if (setOfTheGame == 2) {
            if ((set2PlayerOne == 6 && set2PlayerOne - set2PlayerTwo >= 2)
                    || (set2PlayerOne == 7)) {
                setOfTheGame = 3;
                set3PlayerOne++;
                TextView scoreView = findViewById(R.id.player_one_score_set_3);
                scoreView.setText(String.valueOf(set3PlayerOne));
            } else {
                set2PlayerOne++;
            }
            newGame = false;
            TextView scoreView = findViewById(R.id.player_one_score_set_2);
            scoreView.setText(String.valueOf(set2PlayerOne));
        } else if (setOfTheGame == 3) {
            if ((set3PlayerOne >= 6 && set3PlayerOne - set3PlayerTwo >= 2)
                    || (set3PlayerOne == 7)
                    || (set3PlayerTwo == 6 && set3PlayerTwo - set3PlayerOne >= 2)
                    || (set3PlayerTwo == 7)) {
                setOfTheGame = 4;
            } else {
                set3PlayerOne++;
            }
            newGame = false;
            TextView scoreView = findViewById(R.id.player_one_score_set_3);
            scoreView.setText(String.valueOf(set3PlayerOne));
        }
    }

    /**
     * Displays the given score for Player One.
     */
    public void displayForPlayerOne(int score) {
        String scoreName = "";
        switch (score) {
            case 0: {
                scoreName = "0";
                break;
            }
            case 1: {
                scoreName = "15";
                break;
            }
            case 2: {
                scoreName = "30";
                break;
            }
            case 3: {
                if (scorePlayerTwo == 3) {
                    scoreName = "deuce";
                    TextView scoreView = findViewById(R.id.player_two_score);
                    scoreView.setText(String.valueOf(scoreName));
                } else {
                    scoreName = "40";
                }
                break;
            }
            case 4: {
                if (score - scorePlayerTwo > 2) {
                    scoreName = "GAME";
                    endOfGame = true;
                    newGame = true;
                } else if (score == scorePlayerTwo) {
                    scorePlayerOne = 3;
                    scorePlayerTwo = 3;
                    scoreName = "deuce";
                    TextView scoreView = findViewById(R.id.player_two_score);
                    scoreView.setText(String.valueOf(scoreName));
                } else {
                    scoreName = "adv";
                    TextView scoreView = findViewById(R.id.player_two_score);
                    scoreView.setText(String.valueOf(40));
                }
                break;
            }
            case 5: {
                if (endOfGame) {
                    newGame = false;
                } else {
                    scoreName = "GAME";
                    endOfGame = true;
                    newGame = true;
                }
                break;
            }
            default:
                break;
        }
        if (newGame) {
            addSetScoreForPlayerOne(null);
        }
        TextView scoreView = findViewById(R.id.player_one_score);
        scoreView.setText(String.valueOf(scoreName));
    }

    /**
     * Increase the score for Player Two.
     */

    public void addScoreForPlayerTwo(View v) {
        if (!endOfGame) {
            scorePlayerTwo = scorePlayerTwo + 1;
            displayForPlayerTwo(scorePlayerTwo);
        } else if (set1PlayerTwo >= 6 && set2PlayerTwo >= 6) {
            Toast.makeText(getApplicationContext(),
                    "Player Two congratulations, you win!", Toast.LENGTH_LONG).show();
            scorePlayerTwo = 0;
            scorePlayerOne = 0;
            displayForPlayerTwo(scorePlayerTwo);
            displayForPlayerOne(scorePlayerOne);
            resetScore(v);
        } else if (set3PlayerTwo >= 6 && set3PlayerTwo - set3PlayerOne >= 2
                || set3PlayerTwo == 7) {
            Toast.makeText(getApplicationContext(),
                    "Player Two congratulations, you win!", Toast.LENGTH_LONG).show();
            scorePlayerTwo = 0;
            scorePlayerOne = 0;
            displayForPlayerTwo(scorePlayerTwo);
            displayForPlayerOne(scorePlayerOne);
            resetScore(v);
        } else {
            scorePlayerOne = 0;
            scorePlayerTwo = 0;
            endOfGame = false;
            displayForPlayerOne(scorePlayerOne);
            displayForPlayerTwo(scorePlayerTwo);
        }
    }

    /**
     * Increase the score of the set for Player Two by 1 point.
     */
    public void addSetScoreForPlayerTwo(View v) {

        if (setOfTheGame == 1) {
            if ((set1PlayerTwo == 6 && set1PlayerTwo - set1PlayerOne >= 2)
                    || (set1PlayerTwo == 7)) {
                setOfTheGame = 2;
                set2PlayerTwo++;
                TextView scoreView = findViewById(R.id.player_two_score_set_2);
                scoreView.setText(String.valueOf(set2PlayerTwo));
            } else {
                set1PlayerTwo++;
            }
            newGame = false;
            TextView scoreView = findViewById(R.id.player_two_score_set_1);
            scoreView.setText(String.valueOf(set1PlayerTwo));
        } else if (setOfTheGame == 2) {
            if ((set2PlayerTwo == 6 && set2PlayerTwo - set2PlayerOne >= 2)
                    || (set2PlayerTwo == 7)) {
                setOfTheGame = 3;
                set3PlayerTwo++;
                TextView scoreView = findViewById(R.id.player_two_score_set_3);
                scoreView.setText(String.valueOf(set3PlayerTwo));
            } else {
                set2PlayerTwo++;
            }
            newGame = false;
            TextView scoreView = findViewById(R.id.player_two_score_set_2);
            scoreView.setText(String.valueOf(set2PlayerTwo));
        } else if (setOfTheGame == 3) {
            if ((set3PlayerTwo >= 6 && set3PlayerTwo - set3PlayerOne >= 2)
                    || (set3PlayerTwo == 7)
                    || (set3PlayerOne == 6 && set3PlayerOne - set3PlayerTwo >= 2)
                    || (set3PlayerOne == 7)) {
                setOfTheGame = 4;
            } else {
                set3PlayerTwo++;
            }
            newGame = false;
            TextView scoreView = findViewById(R.id.player_two_score_set_3);
            scoreView.setText(String.valueOf(set3PlayerTwo));
        }
    }

    /**
     * Displays the given score for Player Two.
     */
    public void displayForPlayerTwo(int score) {
        String scoreName = "";
        switch (score) {
            case 0: {
                scoreName = "0";
                break;
            }
            case 1: {
                scoreName = "15";
                break;
            }
            case 2: {
                scoreName = "30";
                break;
            }
            case 3: {
                if (scorePlayerOne == 3) {
                    scoreName = "deuce";
                    TextView scoreView = findViewById(R.id.player_one_score);
                    scoreView.setText(String.valueOf(scoreName));
                } else {
                    scoreName = "40";
                }
                break;
            }
            case 4: {
                if (score - scorePlayerOne > 2) {
                    scoreName = "GAME";
                    endOfGame = true;
                    newGame = true;
                } else if (score == scorePlayerOne) {
                    scorePlayerOne = 3;
                    scorePlayerTwo = 3;
                    scoreName = "deuce";
                    TextView scoreView = findViewById(R.id.player_one_score);
                    scoreView.setText(String.valueOf(scoreName));
                } else {
                    scoreName = "adv";
                    TextView scoreView = findViewById(R.id.player_one_score);
                    scoreView.setText(String.valueOf(40));
                }
                break;
            }
            case 5: {
                if (endOfGame) {
                    newGame = false;
                } else {
                    scoreName = "GAME";
                    endOfGame = true;
                    newGame = true;
                }
                break;
            }
            default:
                break;
        }
        if (newGame) {
            addSetScoreForPlayerTwo(null);
        }
        TextView scoreView = findViewById(R.id.player_two_score);
        scoreView.setText(String.valueOf(scoreName));
    }

    /**
     * Resets all scores for Player One and for Player Two to 0.
     */
    public void resetScore(View v) {
        scorePlayerOne = 0;
        scorePlayerTwo = 0;
        endOfGame = false;
        newGame = false;
        set1PlayerOne = 0;
        set2PlayerOne = 0;
        set3PlayerOne = 0;
        set1PlayerTwo = 0;
        set2PlayerTwo = 0;
        set3PlayerTwo = 0;
        setOfTheGame = 1;
        displayForPlayerOne(scorePlayerOne);
        displayForPlayerTwo(scorePlayerTwo);
        TextView scoreView = findViewById(R.id.player_one_score_set_1);
        scoreView.setText(String.valueOf(set1PlayerOne));
        scoreView = findViewById(R.id.player_one_score_set_2);
        scoreView.setText(String.valueOf(set2PlayerOne));
        scoreView = findViewById(R.id.player_one_score_set_3);
        scoreView.setText(String.valueOf(set3PlayerOne));
        scoreView = findViewById(R.id.player_two_score_set_1);
        scoreView.setText(String.valueOf(set1PlayerTwo));
        scoreView = findViewById(R.id.player_two_score_set_2);
        scoreView.setText(String.valueOf(set2PlayerTwo));
        scoreView = findViewById(R.id.player_two_score_set_3);
        scoreView.setText(String.valueOf(set3PlayerTwo));
    }
}
