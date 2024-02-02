package com.example.assignment2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import java.util.Random;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // Arrays to hold the images for suitcase open state and rewards
    public int[] originalSuitcaseImages = {R.drawable.suitcase_position_1, R.drawable.suitcase_position_2, R.drawable.suitcase_position_3, R.drawable.suitcase_position_4, R.drawable.suitcase_position_5, R.drawable.suitcase_position_6, R.drawable.suitcase_position_7, R.drawable.suitcase_position_8, R.drawable.suitcase_position_9, R.drawable.suitcase_position_10};
    public int[] openSuitcaseImages = {R.drawable.suitcase_open_1, R.drawable.suitcase_open_10, R.drawable.suitcase_open_50, R.drawable.suitcase_open_100, R.drawable.suitcase_open_300, R.drawable.suitcase_open_1000, R.drawable.suitcase_open_10000, R.drawable.suitcase_open_50000, R.drawable.suitcase_open_100000, R.drawable.suitcase_open_500000};
    public int[] rewardOpenImages = {R.drawable.reward_open_1, R.drawable.reward_open_10, R.drawable.reward_open_50, R.drawable.reward_open_100, R.drawable.reward_open_300, R.drawable.reward_open_1000, R.drawable.reward_open_10000, R.drawable.reward_open_50000, R.drawable.reward_open_100000, R.drawable.reward_open_500000};
    public ImageView[] rewardImageViews = new ImageView[10];
    public int[] rewardValues = {1, 10, 50, 100, 300, 1000, 10000, 50000, 100000, 500000};

    public boolean[] usedIndexes = new boolean[10]; // Array to track used indexes
    public int clickCounter = 0; // Counter to track button clicks
    public TextView txtCasesLeft;
    public Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCasesLeft = findViewById(R.id.txtCasesLeft);
        buttonReset = findViewById(R.id.buttonReset);
        updateCasesLeftText(4);

        initializeButtons();
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
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
        updateCasesLeftText(4); // Update text to display 4 cases left

        // Clear foreground images from rewards
        for (ImageView imageView : rewardImageViews) {
            imageView.setForeground(null);
        }
        // Clear foreground images from buttons cases
        for (int i = 0; i < originalSuitcaseImages.length; i++) {
            Button button = findViewById(getButtonId(i));
            button.setForeground(getDrawable(originalSuitcaseImages[i]));
        }

        // Show toast message indicating game reset
        Toast.makeText(this, "Game reset", Toast.LENGTH_SHORT).show();
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
        suitcase_position_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(0);
            }
        });

        suitcase_position_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(1);
            }
        });
// Set OnClickListener for suitcase_position_3
        suitcase_position_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(2);
            }
        });

// Set OnClickListener for suitcase_position_4
        suitcase_position_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(3);
            }
        });

// Set OnClickListener for suitcase_position_5
        suitcase_position_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(4);
            }
        });

// Set OnClickListener for suitcase_position_6
        suitcase_position_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(5);
            }
        });

// Set OnClickListener for suitcase_position_7
        suitcase_position_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(6);
            }
        });

// Set OnClickListener for suitcase_position_8
        suitcase_position_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(7);
            }
        });

// Set OnClickListener for suitcase_position_9
        suitcase_position_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(8);
            }
        });

// Set OnClickListener for suitcase_position_10
        suitcase_position_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(9);
            }
        });


    }
    private void updateCasesLeftText(int casesLeft) {
        txtCasesLeft.setText("Choose " + casesLeft + " cases");
    }

    // Method to handle button click
    private void onButtonClick(int index) {
        int clicksLeft = 4 - clickCounter;
        if (clicksLeft <= 0) {
            Toast.makeText(this, "No clicks left", Toast.LENGTH_SHORT).show();
            return; // Exit the method if the limit is reached
        }
        // Generate a random index that hasn't been used
        int randomIndex = getRandomUnusedIndex();
        // Set the selected image as foreground of button
        Button button = findViewById(getButtonId(index));
        button.setForeground(getDrawable(openSuitcaseImages[randomIndex]));
        rewardImageViews[randomIndex].setForeground(getDrawable(rewardOpenImages[randomIndex]));

        // Mark the selected index as used
        usedIndexes[randomIndex] = true;
        //make click counter go up
        clickCounter++;
        updateCasesLeftText(4 - clickCounter);
        Toast.makeText(this, "You have " + (clicksLeft - 1) + " click(s) left", Toast.LENGTH_SHORT).show();

    }

    // Method to get a random index that hasn't been used
    private int getRandomUnusedIndex() {
        Random random = new Random();
        int index;
        do {
            index = random.nextInt(10);
        } while (usedIndexes[index]);
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

}
