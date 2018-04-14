/*Package name*/
package com.r4dixx.m32o;

/*Import statements*/

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

/*Default extension*/
public class MainActivity extends AppCompatActivity {

    /*First I need to declare the score variable as an int*/ int score;

    /*Default @Override statement*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*I  declare and initialize the parent ScrollView, the name field, checkboxes, buttons and free field*/
        final ScrollView scrollView = findViewById(R.id.scrollView);
        final EditText playerNameField = findViewById(R.id.name_field);
        final CheckBox q4Correct1 = findViewById(R.id.checkbox1), q4Correct2 = findViewById(R.id.checkbox2), q4Correct3 = findViewById(R.id.checkbox3), q4Correct4 = findViewById(R.id.checkbox4), q5Correct1 = findViewById(R.id.checkbox6), q5Correct2 = findViewById(R.id.checkbox8), q5NotCorrect1 = findViewById(R.id.checkbox5), q5NotCorrect2 = findViewById(R.id.checkbox7);
        final EditText q10FreeField = findViewById(R.id.free_field);
        final Button submitButton = findViewById(R.id.submit_button);
        final Button resetButton = findViewById(R.id.reset_button);

		/*Let's start with the submit button. This method will check:
		- right answers within checkboxes
		- right answers withing radio buttons
		- if the name field is empty
		- if the free field is empty
		then it will calculate the score before changing the button to reset*/
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*I declare and initialize RadioButtons as I'll need to calculate the score*/
                final RadioButton q1Correct = findViewById(R.id.radio_button_2), q2Correct = findViewById(R.id.radio_button_4), q3Correct = findViewById(R.id.radio_button_7), q6Correct = findViewById(R.id.radio_button_13), q7Correct = findViewById(R.id.radio_button_17), q8Correct = findViewById(R.id.radio_button_21), q9Correct = findViewById(R.id.radio_button_24);
                /*For EditTexts, I need their length and content (you'll quickly see why below)*/
                final String playerName = playerNameField.getEditableText().toString();
                final int playerNameLength = playerNameField.getText().length();
                final int q10FreeFieldLength = q10FreeField.getText().length();
                /* Each time the button gets clicked, score is initialized to 0*/
                score = 0;
                /*If player name is empty, return an error toast and go back to the top. Otherwise we can go on*/
                if (playerNameLength == 0) {
                    ObjectAnimator.ofInt(scrollView, "scrollY", 0).setDuration(1500).start();
                    Toast.makeText(getApplicationContext(), R.string.toast_player_name_missing, Toast.LENGTH_SHORT).show();
                    /*Here I give focus to the player name field and force keyboard to appear */
                    playerNameField.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                } else {
                    /* The "submit" button gets immediately replaced by the "reset" button*/
                    submitButton.setVisibility(View.INVISIBLE);
                    resetButton.setVisibility(View.VISIBLE);
                    /*Now, score calculations. 1 point added for each correct answer*/
                    if (q1Correct.isChecked()) {
                        score++;
                    }
                    if (q2Correct.isChecked()) {
                        score++;
                    }
                    if (q3Correct.isChecked()) {
                        score++;
                    }
                    if (q4Correct1.isChecked() & q4Correct2.isChecked() & q4Correct3.isChecked() & q4Correct4.isChecked()) {
                        score++;
                    }
                    if (q5Correct1.isChecked() & q5Correct2.isChecked() & !(q5NotCorrect1.isChecked() || q5NotCorrect2.isChecked())) {
                        score++;
                    }
                    if (q6Correct.isChecked()) {
                        score++;
                    }
                    if (q7Correct.isChecked()) {
                        score++;
                    }
                    if (q8Correct.isChecked()) {
                        score++;
                    }
                    if (q9Correct.isChecked()) {
                        score++;
                    }
                    /*One point added if the free field is not empty. That's the final point out of 10*/
                    if (q10FreeFieldLength >= 1) {
                        score++;
                    }

                    /* Now that the score has been calculated, let's display the result in a toast, where we use the toast message as string concatenated with the score and player name*/
                    /*If it equals 10 (i.e. everything is correct)*/
                    if (score == 10) {
                        final String toastPerfect = getApplicationContext().getString(R.string.toast_perfect, score, playerName);
                        Toast.makeText(getApplicationContext(), toastPerfect, Toast.LENGTH_LONG).show();
                        /*If it equals 9 (i.e. everything is almost correct)*/
                    } else if (score == 9) {
                        final String toastPerfectAlmost = getApplicationContext().getString(R.string.toast_perfect_almost, score, playerName);
                        Toast.makeText(getApplicationContext(), toastPerfectAlmost, Toast.LENGTH_LONG).show();
                        /*If it equals 7 - 8 (i.e. good score)*/
                    } else if (score >= 7) {
                        final String toastGood = getApplicationContext().getString(R.string.toast_good, score, playerName);
                        Toast.makeText(getApplicationContext(), toastGood, Toast.LENGTH_LONG).show();
                        /*If it equals 5 - 6 (i.e. medium score)*/
                    } else if (score >= 5) {
                        final String toastMedium = getApplicationContext().getString(R.string.toast_medium, score, playerName);
                        Toast.makeText(getApplicationContext(), toastMedium, Toast.LENGTH_LONG).show();
                        /*If it equals 3 - 4 (i.e. bad score)*/
                    } else if (score >= 3) {
                        final String toastBad = getApplicationContext().getString(R.string.toast_bad, score, playerName);
                        Toast.makeText(getApplicationContext(), toastBad, Toast.LENGTH_LONG).show();
                        /*If it equals 1 - 2 (i.e. mediocre score)*/
                    } else if (score >= 1) {
                        final String toastMediocre = getApplicationContext().getString(R.string.toast_mediocre, score, playerName);
                        Toast.makeText(getApplicationContext(), toastMediocre, Toast.LENGTH_LONG).show();
                        /*If it equals 0 (i.e. nothing is correct)*/
                    } else {
                        final String toastZero = getApplicationContext().getString(R.string.toast_zero, score, playerName);
                        Toast.makeText(getApplicationContext(), toastZero, Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

        /* If you followed closely, the "submit" button changed to a "reset" button at the beginning of the previous method (line 62). This is the "reset" button*/
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*Let's scroll to the top*/
                ObjectAnimator.ofInt(scrollView, "scrollY", 0).setDuration(1500).start();
                /*Name field gets reset, get focused and shows keyboard*/
                playerNameField.setText(null);
                playerNameField.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);                /*Button changes back to the "submit" button*/
                /*reset button becomes submit button*/
                resetButton.setVisibility(View.INVISIBLE);
                submitButton.setVisibility(View.VISIBLE);
                /*Displays the "reset" toast*/
                Toast.makeText(getApplicationContext(), R.string.toast_reset, Toast.LENGTH_SHORT).show();
                /* RadioGroups get declared, initialized and reset (no global scope because different than RadioButtons, I want incorrect answers to be reset too)*/
                final RadioGroup radioGroup1 = findViewById(R.id.radio_group_1);
                radioGroup1.clearCheck();
                final RadioGroup radioGroup2 = findViewById(R.id.radio_group_2);
                radioGroup2.clearCheck();
                final RadioGroup radioGroup3 = findViewById(R.id.radio_group_3);
                radioGroup3.clearCheck();
                final RadioGroup radioGroup4 = findViewById(R.id.radio_group_4);
                radioGroup4.clearCheck();
                final RadioGroup radioGroup5 = findViewById(R.id.radio_group_5);
                radioGroup5.clearCheck();
                final RadioGroup radioGroup6 = findViewById(R.id.radio_group_6);
                radioGroup6.clearCheck();
                final RadioGroup radioGroup7 = findViewById(R.id.radio_group_7);
                radioGroup7.clearCheck();
                /*No need to declare checkboxes as they're in the global scope (we needed them for the first method)*/
                q4Correct1.setChecked(false);
                q4Correct2.setChecked(false);
                q4Correct3.setChecked(false);
                q4Correct4.setChecked(false);
                q5Correct1.setChecked(false);
                q5Correct2.setChecked(false);
                q5NotCorrect1.setChecked(false);
                q5NotCorrect2.setChecked(false);
                /* Let's reset the free field as well*/
                q10FreeField.setText(null);
                /* And of course, score goes back to 0*/
                score = 0;

            }
        });
    }
}
