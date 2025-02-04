package com.example.assignment2;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import java.util.Random;
import android.widget.TextView;
import android.util.Log;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    // Arrays to hold the images for suitcase open state and rewards
    public int[] originalSuitcaseImages = {R.drawable.suitcase_position_1, R.drawable.suitcase_position_2, R.drawable.suitcase_position_3, R.drawable.suitcase_position_4, R.drawable.suitcase_position_5, R.drawable.suitcase_position_6, R.drawable.suitcase_position_7, R.drawable.suitcase_position_8, R.drawable.suitcase_position_9, R.drawable.suitcase_position_10};
    public int[] openSuitcaseImages = {R.drawable.suitcase_open_1, R.drawable.suitcase_open_10, R.drawable.suitcase_open_50, R.drawable.suitcase_open_100, R.drawable.suitcase_open_300, R.drawable.suitcase_open_1000, R.drawable.suitcase_open_10000, R.drawable.suitcase_open_50000, R.drawable.suitcase_open_100000, R.drawable.suitcase_open_500000};
    public int[] rewardOpenImages = {R.drawable.reward_open_1, R.drawable.reward_open_10, R.drawable.reward_open_50, R.drawable.reward_open_100, R.drawable.reward_open_300, R.drawable.reward_open_1000, R.drawable.reward_open_10000, R.drawable.reward_open_50000, R.drawable.reward_open_100000, R.drawable.reward_open_500000};
    public ImageView[] rewardImageViews = new ImageView[10];
    public int[] rewardValues = {1, 10, 50, 100, 300, 1000, 10000, 50000, 100000, 500000};

    public boolean[] usedIndexes = new boolean[10]; // Array to track used indexes
    public int clickCounter = 0; // Counter to track button clicks

    private double bankOffer = 0;// Declare the bankOffer variable
    private int roundCount = 1; //Declare the round variable
    public TextView txtCasesLeft;
    public Button buttonReset,buttonDeal,buttonNoDeal;
    public boolean isFinal = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCasesLeft = findViewById(R.id.txtCasesLeft);
        buttonReset = findViewById(R.id.buttonReset);
        buttonDeal = findViewById(R.id.buttonDeal); // Reference to the "Deal" button
        buttonNoDeal = findViewById(R.id.buttonNoDeal); // Reference to the "No Deal" button
        //updateCasesLeftText(0, bankOffer); // Update text to display bank's offer
        buttonDeal.setVisibility(View.GONE);
        buttonNoDeal.setVisibility(View.GONE);

        initializeButtons();
        buttonReset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
        //Deal Button
        buttonDeal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonDeal.setVisibility(View.GONE);
                buttonNoDeal.setVisibility(View.GONE);
                txtCasesLeft.setText(String.format("You won: $%,.0f!", calculateBankDeal(calculateTotalReward())));
            }
        });


        //No Deal Button
        buttonNoDeal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonDeal.setVisibility(View.GONE);
                buttonNoDeal.setVisibility(View.GONE);
                clickCounter = 0;
                if (roundCount == 2) {
                    //start final round
                    txtCasesLeft.setText("Choose 1 Case");
                    gameResume();
                    isFinal = true;
                } else { // starts second round
                    roundCount++;
                    updateCasesLeftText(4, 0);
                    gameResume();
                }
            }
        });
    }



    // Method to reset the game
    private void resetGame() {
        clickCounter = 0; // Reset click counter
        // Reset used indexes array
        for (int i = 0; i < usedIndexes.length; i++) {
            usedIndexes[i] = false;
        }
        gameResume();
        buttonEnable();
        roundCount = 1;
        isFinal = false;
        txtCasesLeft.setText("Choose 4 Cases");
        buttonDeal.setVisibility(View.GONE);
        buttonNoDeal.setVisibility(View.GONE);

        // Clear foreground images from rewards
        for (ImageView imageView : rewardImageViews) {
            imageView.setForeground(null);
        }
        // Clear foreground images from buttons cases
        for (int i = 0; i < originalSuitcaseImages.length; i++) {
            Button button = findViewById(getButtonId(i));
            button.setForeground(getDrawable(originalSuitcaseImages[i]));
        }
        // Reset the rewardValues array
        resetRewardValues();
        // Show toast message indicating game reset
        Toast.makeText(this, "Game reset", Toast.LENGTH_SHORT).show();

        //Reset the buttons
        initializeButtons();
    }
    // Method to reset the rewardValues array
    private void resetRewardValues() {
        rewardValues = new int[]{1, 10, 50, 100, 300, 1000, 10000, 50000, 100000, 500000};
    }





    // Method to initialize all buttons
    private void initializeButtons() {
        Button suitcase_position_1 = findViewById(R.id.suitcase_position_1);
        Button suitcase_position_2 = findViewById(R.id.suitcase_position_2);
        Button suitcase_position_3 = findViewById(R.id.suitcase_position_3);
        Button suitcase_position_4 = findViewById(R.id.suitcase_position_4);
        Button suitcase_position_5 = findViewById(R.id.suitcase_position_5);
        Button suitcase_position_6 = findViewById(R.id.suitcase_position_6);
        Button suitcase_position_7 = findViewById(R.id.suitcase_position_7);
        Button suitcase_position_8 = findViewById(R.id.suitcase_position_8);
        Button suitcase_position_9 = findViewById(R.id.suitcase_position_9);
        Button suitcase_position_10 = findViewById(R.id.suitcase_position_10);

        // Add more buttons here as needed

        rewardImageViews[0] = findViewById(R.id.reward_1);
        rewardImageViews[1] = findViewById(R.id.reward_10);
        rewardImageViews[2] = findViewById(R.id.reward_50);
        rewardImageViews[3] = findViewById(R.id.reward_100);
        rewardImageViews[4] = findViewById(R.id.reward_300);
        rewardImageViews[5] = findViewById(R.id.reward_1000);
        rewardImageViews[6] = findViewById(R.id.reward_10000);
        rewardImageViews[7] = findViewById(R.id.reward_50000);
        rewardImageViews[8] = findViewById(R.id.reward_100000);
        rewardImageViews[9] = findViewById(R.id.reward_500000);

        // Initialize usedIndexes array
        for (int i = 0; i < usedIndexes.length; i++) {
            usedIndexes[i] = false;
        }

        // Set OnClickListener for each button
        suitcase_position_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFinal) {onButtonClick(0);} else {
                    finalClick(1);
                }
            }
        });

        suitcase_position_2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFinal) {onButtonClick(1);} else {
                    finalClick(1);
                }
            }
        });
// Set OnClickListener for suitcase_position_3
        suitcase_position_3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFinal) {onButtonClick(2);} else {
                    finalClick(2);
                }
            }
        });

// Set OnClickListener for suitcase_position_4
        suitcase_position_4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFinal) {onButtonClick(3);} else {
                    finalClick(3);
                }
            }
        });

// Set OnClickListener for suitcase_position_5
        suitcase_position_5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFinal) {onButtonClick(4);} else {
                    finalClick(4);
                }
            }
        });

// Set OnClickListener for suitcase_position_6
        suitcase_position_6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFinal) {onButtonClick(5);} else {
                    finalClick(5);
                }
            }
        });

// Set OnClickListener for suitcase_position_7
        suitcase_position_7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFinal) {onButtonClick(6);} else {
                    finalClick(6);
                }
            }
        });

// Set OnClickListener for suitcase_position_8
        suitcase_position_8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFinal) {onButtonClick(7);} else {
                    finalClick(7);
                }
            }
        });

// Set OnClickListener for suitcase_position_9
        suitcase_position_9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFinal) {onButtonClick(8);} else {
                    finalClick(8);
                }
            }
        });

// Set OnClickListener for suitcase_position_10
        suitcase_position_10.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFinal) {onButtonClick(9);} else {
                    finalClick(9);
                }
            }
        });


    }
    private void updateCasesLeftText(int casesLeft, double bankOffer) {
        if (casesLeft > 0) {
            // Show "Choose X cases" message if cases are remaining
            txtCasesLeft.setText("Choose " + casesLeft + " cases");
            // Hide the "Deal" and "No Deal" buttons
            buttonDeal.setVisibility(View.GONE);
            buttonNoDeal.setVisibility(View.GONE);
        } else {
            // Show the bank offer when all cases are chosen
            txtCasesLeft.setText(String.format("Bank's Offer: $%,.0f", bankOffer));
            // Show the "Deal" and "No Deal" buttons
            buttonDeal.setVisibility(View.VISIBLE);
            buttonNoDeal.setVisibility(View.VISIBLE);
            gamePause();
        }
    }


    // Method to handle button click
    private void onButtonClick(int index) {
        int clicksLeft = 4 - clickCounter;

        // Generate a random index that hasn't been used
        int randomIndex = getRandomUnusedIndex();
        // Set the selected image as foreground of button
        Button button = findViewById(getButtonId(index));
        button.setForeground(getDrawable(openSuitcaseImages[randomIndex]));
        rewardImageViews[randomIndex].setForeground(getDrawable(rewardOpenImages[randomIndex]));

        // Remove the reward value from the array
        int selectedRewardValue = rewardValues[randomIndex];
        removeRewardValue(selectedRewardValue);

        // turns the case button off
        button.setEnabled(false);
        // Increase click counter
        clickCounter++;
        // Update remaining cases text
        updateCasesLeftText(4 - clickCounter, 0);
        if (clicksLeft <= 1) {
            // Calculate bank's offer when no clicks left
            int total = calculateTotalReward();
            double bankOffer = calculateBankDeal(total);
            updateCasesLeftText(0, bankOffer); // Update text to display bank's offer
            Toast.makeText(this, "No clicks left", Toast.LENGTH_SHORT).show(); // Show toast
        } else {
            Toast.makeText(this, "You have " + (clicksLeft - 1) + " click(s) left", Toast.LENGTH_SHORT).show(); // Show remaining clicks toast
        }
    }

    private void finalClick(int index){
        // Generate a random index that hasn't been used
        gamePause();
        int randomIndex = getRandomUnusedIndex();
        int selectedRewardValue = rewardValues[randomIndex];
        removeRewardValue(selectedRewardValue);
        txtCasesLeft.setText(String.format("You won: $%,d", selectedRewardValue));
        Button chosen = findViewById(getButtonId(index));
        int lostAmount = getRandomUnusedIndex();

        //searches for last button
        for (int i = 0; i <10;i++) {
            Button check = findViewById(getButtonId(i));
            if (check.isEnabled() && check.getId() != chosen.getId()) {
                check.setForeground(getDrawable(openSuitcaseImages[lostAmount]));
                rewardImageViews[lostAmount].setForeground(getDrawable(rewardOpenImages[lostAmount]));
            }
        }

    }


    // Method to remove a reward value from the array
    private void removeRewardValue(int value) {
        // Calculate total sum before removal
        int totalSumBeforeRemoval = calculateTotalReward();
        int index = -1;
        // Find the index of the reward value to remove
        for (int i = 0; i < rewardValues.length; i++) {
            if (rewardValues[i] == value) {
                index = i;
                rewardValues[i] = 0;
                break;
            }
        }


        // If the value to remove is found
        if (index != -1) {
            // Subtract the value from the total sum
            int difference = rewardValues[index];
            int totalSumAfterRemoval = totalSumBeforeRemoval - difference;

            // Log the total reward after removal and the difference in total reward
            Log.d("TotalRewardAfterRemoval", "Total reward after removal: " + totalSumAfterRemoval);
            Log.d("DifferenceAfterRemoval", "Difference in total reward after removal: " + difference);
        }
    }

    // Method to calculate the total sum of reward values in the array
    private int calculateTotalReward() {
        int total = 0;
        for (int rewardValue : rewardValues) {
            total += rewardValue;
        }
        return total;
    }
    private double calculateBankDeal(int total) {
        // Calculate the bank's offer as 60% of the remaining total
        return (double) (total * 0.6);
    }


    // Method to get a random index that hasn't been used
    private int getRandomUnusedIndex() {
        Random random = new Random();
        int index;
        do {
            index = random.nextInt(10);
        } while (usedIndexes[index]);
        usedIndexes[index] = true;
        return index;
    }

    // Method to get the button ID based on the index

    private int getButtonId(int index) {
        switch (index) {
            case 0:
                return R.id.suitcase_position_1;
            case 1:
                return R.id.suitcase_position_2;
            case 2:
                return R.id.suitcase_position_3;
            case 3:
                return R.id.suitcase_position_4;
            case 4:
                return R.id.suitcase_position_5;
            case 5:
                return R.id.suitcase_position_6;
            case 6:
                return R.id.suitcase_position_7;
            case 7:
                return R.id.suitcase_position_8;
            case 8:
                return R.id.suitcase_position_9;
            case 9:
                return R.id.suitcase_position_10;
            default:
                return 0; // Return 0 for unknown index (should not happen)
        }


    }

    private void gamePause() { //Turns off the ability to select a box
        for (int i = 0; i < usedIndexes.length; i++) {
           // if (usedIndexes[i]) {
                Button button = findViewById(getButtonId(i));
                button.setClickable(false);
           // }
        }
    }

    //Turns back on ability to select boxes
    private void gameResume() {
        for (int i = 0; i < usedIndexes.length; i++) {
                Button button = findViewById(getButtonId(i));
                button.setClickable(true);
        }

    }

    //re-enables all buttons
    private void buttonEnable() {
        for (int i = 0; i < usedIndexes.length; i++) {
            Button button = findViewById(getButtonId(i));
            button.setEnabled(true);
        }
    }
}
