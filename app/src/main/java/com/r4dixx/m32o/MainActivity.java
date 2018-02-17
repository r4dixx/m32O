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
        final ScrollView SCROLL_VIEW = findViewById(R.id.scrollView);
        final EditText PLAYER_NAME_FIELD = findViewById(R.id.name_field);
        final CheckBox Q4_CORRECT_1 = findViewById(R.id.checkbox1), Q4_CORRECT_2 = findViewById(R.id.checkbox2), Q4_CORRECT_3 = findViewById(R.id.checkbox3), Q4_CORRECT_4 = findViewById(R.id.checkbox4), Q5_CORRECT_1 = findViewById(R.id.checkbox6), Q5_CORRECT_2 = findViewById(R.id.checkbox8), Q5_NOT_CORRECT_1 = findViewById(R.id.checkbox5), Q5_NOT_CORRECT_2 = findViewById(R.id.checkbox7);
        final EditText Q10_FREE_FIELD = findViewById(R.id.free_field);
        final Button SUBMIT_BUTTON = findViewById(R.id.submit_button);
        final Button RESET_BUTTON = findViewById(R.id.reset_button);

        /*Let's start with the submit button. This method will check:
        - right answers within checkboxes
        - right answers withing radio buttons
        - if the name field is empty
        - if the free field is empty
        then it will calculate the score before changing the button to reset*/
        SUBMIT_BUTTON.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*I declare and initialize RadioButtons as I'll need to calculate the score*/
                final RadioButton Q1_CORRECT = findViewById(R.id.radio_button_2), Q2_CORRECT = findViewById(R.id.radio_button_4), Q3_CORRECT = findViewById(R.id.radio_button_7), Q6_CORRECT = findViewById(R.id.radio_button_13), Q7_CORRECT = findViewById(R.id.radio_button_17), Q8_CORRECT = findViewById(R.id.radio_button_21), Q9_CORRECT = findViewById(R.id.radio_button_24);
                /*For EditTexts, I need their length and content (you'll quickly see why below)*/
                final String PLAYER_NAME = PLAYER_NAME_FIELD.getEditableText().toString();
                final int PLAYER_NAME_LENGTH = PLAYER_NAME_FIELD.getText().length();
                final int Q10_FREE_FIELD_LENGTH = Q10_FREE_FIELD.getText().length();
                /* Each time the button gets clicked, score is initialized to 0*/
                score = 0;
                /*If player name is empty, return an error toast and go back to the top. Otherwise we can go on*/
                if (PLAYER_NAME_LENGTH == 0) {
                    ObjectAnimator.ofInt(SCROLL_VIEW, "scrollY", 0).setDuration(1500).start();
                    Toast.makeText(getApplicationContext(), R.string.toast_player_name_missing, Toast.LENGTH_SHORT).show();
                    /*Here I give focus to the player name field and force keyboard to appear */
                    PLAYER_NAME_FIELD.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                } else {
                    /* The "submit" button gets immediately replaced by the "reset" button*/
                    SUBMIT_BUTTON.setVisibility(View.INVISIBLE);
                    RESET_BUTTON.setVisibility(View.VISIBLE);
                    /*Now, score calculations. 1 point added for each correct answer*/
                    if (Q1_CORRECT.isChecked()) {
                        score++;
                    }
                    if (Q2_CORRECT.isChecked()) {
                        score++;
                    }
                    if (Q3_CORRECT.isChecked()) {
                        score++;
                    }
                    if (Q4_CORRECT_1.isChecked() & Q4_CORRECT_2.isChecked() & Q4_CORRECT_3.isChecked() & Q4_CORRECT_4.isChecked()) {
                        score++;
                    }
                    if (Q5_CORRECT_1.isChecked() & Q5_CORRECT_2.isChecked() & !(Q5_NOT_CORRECT_1.isChecked() || Q5_NOT_CORRECT_2.isChecked())) {
                        score++;
                    }
                    if (Q6_CORRECT.isChecked()) {
                        score++;
                    }
                    if (Q7_CORRECT.isChecked()) {
                        score++;
                    }
                    if (Q8_CORRECT.isChecked()) {
                        score++;
                    }
                    if (Q9_CORRECT.isChecked()) {
                        score++;
                    }
                    /*One point added if the free field is not empty. That's the final point out of 10*/
                    if (Q10_FREE_FIELD_LENGTH >= 1) {
                        score++;
                    }

                    /* Now that the score has been calculated, let's display the result in a toast, where we use the toast message as string concatenated with the score and player name*/
                    /*If it equals 10 (i.e. everything is correct)*/
                    if (score == 10) {
                        final String TOAST_PERFECT = getApplicationContext().getString(R.string.toast_perfect, score, PLAYER_NAME);
                        Toast.makeText(getApplicationContext(), TOAST_PERFECT, Toast.LENGTH_LONG).show();
                     /*If it equals 9 (i.e. everything is almost correct)*/
                    } else if (score == 9) {
                        final String TOAST_PERFECT_ALMOST = getApplicationContext().getString(R.string.toast_perfect_almost, score, PLAYER_NAME);
                        Toast.makeText(getApplicationContext(), TOAST_PERFECT_ALMOST, Toast.LENGTH_LONG).show();
                    /*If it equals 7 - 8 (i.e. good score)*/
                    } else if (score >= 7) {
                        final String TOAST_GOOD = getApplicationContext().getString(R.string.toast_good, score, PLAYER_NAME);
                        Toast.makeText(getApplicationContext(), TOAST_GOOD, Toast.LENGTH_LONG).show();
                    /*If it equals 5 - 6 (i.e. medium score)*/
                    } else if (score >= 5) {
                        final String TOAST_MEDIUM = getApplicationContext().getString(R.string.toast_medium, score, PLAYER_NAME);
                        Toast.makeText(getApplicationContext(), TOAST_MEDIUM, Toast.LENGTH_LONG).show();
                    /*If it equals 3 - 4 (i.e. bad score)*/
                    } else if (score >= 3) {
                        final String TOAST_BAD = getApplicationContext().getString(R.string.toast_bad, score, PLAYER_NAME);
                        Toast.makeText(getApplicationContext(), TOAST_BAD, Toast.LENGTH_LONG).show();
                    /*If it equals 1 - 2 (i.e. mediocre score)*/
                    } else if (score >= 1) {
                        final String TOAST_MEDIOCRE = getApplicationContext().getString(R.string.toast_mediocre, score, PLAYER_NAME);
                        Toast.makeText(getApplicationContext(), TOAST_MEDIOCRE, Toast.LENGTH_LONG).show();
                    /*If it equals 0 (i.e. nothing is correct)*/
                    } else {
                        final String TOAST_ZERO = getApplicationContext().getString(R.string.toast_zero, score, PLAYER_NAME);
                        Toast.makeText(getApplicationContext(), TOAST_ZERO, Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

        /* If you followed closely, the "submit" button changed to a "reset" button at the beginning of the previous method (line 62). This is the "reset" button*/
        RESET_BUTTON.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 /*Let's scroll to the top*/
                ObjectAnimator.ofInt(SCROLL_VIEW, "scrollY", 0).setDuration(1500).start();
                /*Name field gets reset, get focused and shows keyboard*/
                PLAYER_NAME_FIELD.setText(null);
                PLAYER_NAME_FIELD.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);                /*Button changes back to the "submit" button*/
                /*reset button becomes submit button*/
                RESET_BUTTON.setVisibility(View.INVISIBLE);
                SUBMIT_BUTTON.setVisibility(View.VISIBLE);
                /*Displays the "reset" toast*/
                Toast.makeText(getApplicationContext(), R.string.toast_reset, Toast.LENGTH_SHORT).show();
               /* RadioGroups get declared, initialized and reset (no global scope because different than RadioButtons, I want incorrect answers to be reset too)*/
                final RadioGroup RADIO_GROUP1 = findViewById(R.id.radio_group_1);
                RADIO_GROUP1.clearCheck();
                final RadioGroup RADIO_GROUP2 = findViewById(R.id.radio_group_2);
                RADIO_GROUP2.clearCheck();
                final RadioGroup RADIO_GROUP3 = findViewById(R.id.radio_group_3);
                RADIO_GROUP3.clearCheck();
                final RadioGroup RADIO_GROUP4 = findViewById(R.id.radio_group_4);
                RADIO_GROUP4.clearCheck();
                final RadioGroup RADIO_GROUP5 = findViewById(R.id.radio_group_5);
                RADIO_GROUP5.clearCheck();
                final RadioGroup RADIO_GROUP6 = findViewById(R.id.radio_group_6);
                RADIO_GROUP6.clearCheck();
                final RadioGroup RADIO_GROUP7 = findViewById(R.id.radio_group_7);
                RADIO_GROUP7.clearCheck();
                /*No need to declare checkboxes as they're in the global scope (we needed them for the first method)*/
                Q4_CORRECT_1.setChecked(false);
                Q4_CORRECT_2.setChecked(false);
                Q4_CORRECT_3.setChecked(false);
                Q4_CORRECT_4.setChecked(false);
                Q5_CORRECT_1.setChecked(false);
                Q5_CORRECT_2.setChecked(false);
                Q5_NOT_CORRECT_1.setChecked(false);
                Q5_NOT_CORRECT_2.setChecked(false);
                /* Let's reset the free field as well*/
                Q10_FREE_FIELD.setText(null);
                /* And of course, score goes back to 0*/
                score = 0;

            }
        });
    }
}
