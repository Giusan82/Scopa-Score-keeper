package com.example.android.scopascorekeeper;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import static com.example.android.scopascorekeeper.R.id.cardsP1;
import static com.example.android.scopascorekeeper.R.id.cardsP2;
import static com.example.android.scopascorekeeper.R.id.coinsP1;
import static com.example.android.scopascorekeeper.R.id.coinsP2;
import static com.example.android.scopascorekeeper.R.id.scopaP1;
import static com.example.android.scopascorekeeper.R.id.scopaP2;


public class MainActivity extends AppCompatActivity {


    int score = 0;
    int numberWinP1 = 0;
    int numberWinP2 = 0;
    int counted = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** This add a logo in ActionBar **/
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.scopa);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main);


        Spinner dropdown = (Spinner) findViewById(R.id.prime_tilesP1);
        // Spinner click listener

        // Creating adapter for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.spinner_array, android.R.layout.simple_spinner_item);

        // Drop down layout style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        dropdown.setAdapter(adapter);
    }

    // Calculate the score of the deal with error message if the requirement are not respected
    public void count(View view) {
        if (cardsP1() + cardsP2() > 40) {
            Toast.makeText(this, getString(R.string.errorCards), Toast.LENGTH_SHORT).show();
        } else if (cardsP1() + cardsP2() < 40) {
            Toast.makeText(this, getString(R.string.errorCards), Toast.LENGTH_SHORT).show();
        } else if (coinsP1() + coinsP2() > 10) {
            Toast.makeText(this, getString(R.string.errorCoins), Toast.LENGTH_SHORT).show();
        } else if (coinsP1() + coinsP2() < 10) {
            Toast.makeText(this, getString(R.string.errorCoins), Toast.LENGTH_SHORT).show();
        } else if (tilesMenuP1() == tilesMenuP2() && tilesMenuP1() != 0 && tilesMenuP2() != 0 || heartsMenuP1() == heartsMenuP2() && heartsMenuP1() != 0 && heartsMenuP2() != 0 || pikesMenuP1() == pikesMenuP2() && pikesMenuP1() != 0 && pikesMenuP2() != 0 || cloversMenuP1() == cloversMenuP2() && cloversMenuP1() != 0 && cloversMenuP2() != 0) {
            Toast.makeText(this, getString(R.string.errorPrimeSame), Toast.LENGTH_SHORT).show();
        } else if (setteBelloP1() == false && setteBelloP2() == false) {
            Toast.makeText(this, getString(R.string.errorSetteBello), Toast.LENGTH_SHORT).show();
        } else {
            calculateScoreP1(score, 0, 0, scopaP1());
            calculateScoreP2(score, 0, 0, scopaP2());
            counted = 1;

            if (calculateScoreP1(score, 0, 0, scopaP1()) > calculateScoreP2(score, 0, 0, scopaP2())) {
                Toast.makeText(this, getString(R.string.winnerP1), Toast.LENGTH_SHORT).show();
                TextView scoreViewP1 = (TextView) findViewById(R.id.P1_Deal_score);
                scoreViewP1.setTextColor(Color.YELLOW);
                TextView scoreViewP2 = (TextView) findViewById(R.id.P2_Deal_score);
                scoreViewP2.setTextColor(getResources().getColor(R.color.textApp));
            }
            if (calculateScoreP1(score, 0, 0, scopaP1()) < calculateScoreP2(score, 0, 0, scopaP2())) {
                Toast.makeText(this, getString(R.string.winnerP2), Toast.LENGTH_SHORT).show();
                TextView scoreViewP2 = (TextView) findViewById(R.id.P2_Deal_score);
                scoreViewP2.setTextColor(Color.YELLOW);
                TextView scoreViewP1 = (TextView) findViewById(R.id.P1_Deal_score);
                scoreViewP1.setTextColor(getResources().getColor(R.color.textApp));
            }

        }


    }
    // Calculate the Wins score
    public void numberWin(View view) {
        if (counted == 0) {
            Toast.makeText(this, getString(R.string.errorScoreCount), Toast.LENGTH_SHORT).show();
        } else {

            if (calculateScoreP1(score, 0, 0, scopaP1()) > calculateScoreP2(score, 0, 0, scopaP2())) {
                numberWinP1 = numberWinP1 + 1;
                displayNumWinP1(numberWinP1);
            }

            if (calculateScoreP1(score, 0, 0, scopaP1()) < calculateScoreP2(score, 0, 0, scopaP2())) {
                numberWinP2 = numberWinP2 + 1;
                displayNumWinP2(numberWinP2);
            }
            //here the color of the score number change to yellow if the player are winning
            if (numberWinP1 < numberWinP2) {
                TextView winViewP2 = (TextView) findViewById(R.id.P2_NumWin);
                winViewP2.setTextColor(Color.YELLOW);
                TextView winViewP1 = (TextView) findViewById(R.id.P1_NumWin);
                winViewP1.setTextColor(getResources().getColor(R.color.textApp));
                TextView scoreViewP1 = (TextView) findViewById(R.id.P1_Deal_score);
                scoreViewP1.setTextColor(getResources().getColor(R.color.textApp));
                TextView scoreViewP2 = (TextView) findViewById(R.id.P2_Deal_score);
                scoreViewP2.setTextColor(getResources().getColor(R.color.textApp));
            } else if (numberWinP1 > numberWinP2) {
                TextView winViewP1 = (TextView) findViewById(R.id.P1_NumWin);
                winViewP1.setTextColor(Color.YELLOW);
                TextView winViewP2 = (TextView) findViewById(R.id.P2_NumWin);
                winViewP2.setTextColor(getResources().getColor(R.color.textApp));
                TextView scoreViewP1 = (TextView) findViewById(R.id.P1_Deal_score);
                scoreViewP1.setTextColor(getResources().getColor(R.color.textApp));
                TextView scoreViewP2 = (TextView) findViewById(R.id.P2_Deal_score);
                scoreViewP2.setTextColor(getResources().getColor(R.color.textApp));
            } else {
                TextView winViewP1 = (TextView) findViewById(R.id.P1_NumWin);
                winViewP1.setTextColor(getResources().getColor(R.color.textApp));
                TextView winViewP2 = (TextView) findViewById(R.id.P2_NumWin);
                winViewP2.setTextColor(getResources().getColor(R.color.textApp));
                TextView scoreViewP1 = (TextView) findViewById(R.id.P1_Deal_score);
                scoreViewP1.setTextColor(getResources().getColor(R.color.textApp));
                TextView scoreViewP2 = (TextView) findViewById(R.id.P2_Deal_score);
                scoreViewP2.setTextColor(getResources().getColor(R.color.textApp));
            }
            clearData();
            score = 0;
            counted = 0;
            displayForP1(score);
            displayForP2(score);
        }
    }

    // Calculate the score of single deal for player 1
    private int calculateScoreP1(int score, int setteB1, int setteB2, int addScopa) {

        int setteB = 0;
        int addCard = 0;
        int addCoins = 0;
        int addPrime = 0;


        if (setteBelloP1() == true) {
            setteB1 = setteB1 + 1;
        }
        if (setteBelloP2() == true) {
            setteB2 = setteB2 + 1;
        }
        if (setteB1 > setteB2) {
            setteB = 1;
        }
        if (cardsP1() > cardsP2()) {
            addCard = 1;
        }
        if (coinsP1() > coinsP2()) {
            addCoins = 1;
        }
        if (calculatePrimeP1() > calculatePrimeP2()) {
            addPrime = 1;
        }


        score = score + setteB + addCard + addCoins + addScopa + addPrime;
        displayForP1(score);
        return score;
    }
    // Calculate the score of single deal for player 1
    private int calculateScoreP2(int score, int setteB1, int setteB2, int addScopa) {

        int setteB = 0;
        int addCard = 0;
        int addCoins = 0;
        int addPrime = 0;


        if (setteBelloP1() == true) {
            setteB1 = setteB1 + 1;
        }
        if (setteBelloP2() == true) {
            setteB2 = setteB2 + 1;
        }
        if (setteB1 < setteB2) {
            setteB = 1;
        }
        if (cardsP1() < cardsP2()) {
            addCard = 1;
        }
        if (coinsP1() < coinsP2()) {
            addCoins = 1;
        }
        if (calculatePrimeP1() < calculatePrimeP2()) {
            addPrime = 1;
        }


        score = score + setteB + addCard + addCoins + addScopa + addPrime;
        displayForP2(score);

        return score;
    }

    // Find the total value got from Prime
    private int calculatePrimeP1() {

        int scorePrime = 0;
        if (tilesMenuP1() != 0 && heartsMenuP1() != 0 && pikesMenuP1() != 0 && cloversMenuP1() != 0) {
            scorePrime = tilesMenuP1() + heartsMenuP1() + pikesMenuP1() + cloversMenuP1();
        }
        return scorePrime;
    }

    private int calculatePrimeP2() {


        int scorePrime = 0;
        if (tilesMenuP2() != 0 && heartsMenuP2() != 0 && pikesMenuP2() != 0 && cloversMenuP2() != 0) {
            scorePrime = tilesMenuP2() + heartsMenuP2() + pikesMenuP2() + cloversMenuP2();
        }
        return scorePrime;
    }

    // Get boolean value of Sette Bello
    private Boolean setteBelloP1() {
        RadioButton setteBelloP1 = (RadioButton) findViewById(R.id.sette_belloP1);
        Boolean setteBelloP1Ck = setteBelloP1.isChecked();
        return setteBelloP1Ck;
    }

    private Boolean setteBelloP2() {
        RadioButton setteBelloP1 = (RadioButton) findViewById(R.id.sette_belloP2);
        Boolean setteBelloP1Ck = setteBelloP1.isChecked();
        return setteBelloP1Ck;
    }

    // Get the value of Cards in the EditText
    private int cardsP1() {
        EditText getCards = (EditText) findViewById(cardsP1);
        String addCards = getCards.getText().toString();
        int intCards = 0;
        if (!addCards.matches("")) {
            intCards = Integer.parseInt(addCards);
            return intCards;
        }
        return intCards;
    }

    private int cardsP2() {
        EditText getCards = (EditText) findViewById(cardsP2);
        String addCards = getCards.getText().toString();
        int intCards = 0;
        if (!addCards.matches("")) {
            intCards = Integer.parseInt(addCards);
            return intCards;
        }
        return intCards;
    }

    // Get the value of Coins in the EditText
    private int coinsP1() {
        EditText getCoins = (EditText) findViewById(coinsP1);
        String addCoins = getCoins.getText().toString();
        int intCoins = 0;
        if (!addCoins.matches("")) {
            intCoins = Integer.parseInt(addCoins);
            return intCoins;
        }
        return intCoins;
    }

    private int coinsP2() {
        EditText getCoins = (EditText) findViewById(coinsP2);
        String addCoins = getCoins.getText().toString();
        int intCoins = 0;
        if (!addCoins.matches("")) {

            intCoins = Integer.parseInt(addCoins);
            return intCoins;
        }

        return intCoins;
    }

    // Get the value of Scopa in the EditText
    private int scopaP1() {
        EditText getScopa = (EditText) findViewById(scopaP1);
        String addScopa = getScopa.getText().toString();
        int intScopa = 0;
        if (!addScopa.matches("")) {

            intScopa = Integer.parseInt(addScopa);
            return intScopa;
        }
        return intScopa;
    }

    private int scopaP2() {
        EditText getScopa = (EditText) findViewById(scopaP2);
        String addScopa = getScopa.getText().toString();
        int intScopa = 0;
        if (!addScopa.matches("")) {

            intScopa = Integer.parseInt(addScopa);
            return intScopa;
        }
        return intScopa;
    }

    // Get the value of Prime selected in the Spinners for player 1
    private int tilesMenuP1() {

        Spinner spinner = (Spinner) findViewById(R.id.prime_tilesP1);
        String text = spinner.getSelectedItem().toString();

        int value = 0;
        if (text == getString(R.string.none)) {
            value = 0;
        }
        if (text == getString(R.string.seven)) {
            value = 21;
        }
        if (text == getString(R.string.six)) {
            value = 18;
        }
        if (text == getString(R.string.ace)) {
            value = 16;
        }
        if (text == getString(R.string.five)) {
            value = 15;
        }
        if (text == getString(R.string.four)) {
            value = 14;
        }
        if (text == getString(R.string.three)) {
            value = 13;
        }
        if (text == getString(R.string.two)) {
            value = 12;
        }
        if (text == getString(R.string.face_cards)) {
            value = 10;
        }
        return value;

    }

    private int heartsMenuP1() {

        Spinner spinner = (Spinner) findViewById(R.id.prime_heartsP1);
        String text = spinner.getSelectedItem().toString();

        int value = 0;
        if (text == getString(R.string.none)) {
            value = 0;
        }
        if (text == getString(R.string.seven)) {
            value = 21;
        }
        if (text == getString(R.string.six)) {
            value = 18;
        }
        if (text == getString(R.string.ace)) {
            value = 16;
        }
        if (text == getString(R.string.five)) {
            value = 15;
        }
        if (text == getString(R.string.four)) {
            value = 14;
        }
        if (text == getString(R.string.three)) {
            value = 13;
        }
        if (text == getString(R.string.two)) {
            value = 12;
        }
        if (text == getString(R.string.face_cards)) {
            value = 10;
        }
        return value;

    }

    private int pikesMenuP1() {

        Spinner spinner = (Spinner) findViewById(R.id.prime_pikesP1);
        String text = spinner.getSelectedItem().toString();

        int value = 0;
        if (text == getString(R.string.none)) {
            value = 0;
        }
        if (text == getString(R.string.seven)) {
            value = 21;
        }
        if (text == getString(R.string.six)) {
            value = 18;
        }
        if (text == getString(R.string.ace)) {
            value = 16;
        }
        if (text == getString(R.string.five)) {
            value = 15;
        }
        if (text == getString(R.string.four)) {
            value = 14;
        }
        if (text == getString(R.string.three)) {
            value = 13;
        }
        if (text == getString(R.string.two)) {
            value = 12;
        }
        if (text == getString(R.string.face_cards)) {
            value = 10;
        }
        return value;

    }

    private int cloversMenuP1() {

        Spinner spinner = (Spinner) findViewById(R.id.prime_cloversP1);
        String text = spinner.getSelectedItem().toString();

        int value = 0;
        if (text == getString(R.string.none)) {
            value = 0;
        }
        if (text == getString(R.string.seven)) {
            value = 21;
        }
        if (text == getString(R.string.six)) {
            value = 18;
        }
        if (text == getString(R.string.ace)) {
            value = 16;
        }
        if (text == getString(R.string.five)) {
            value = 15;
        }
        if (text == getString(R.string.four)) {
            value = 14;
        }
        if (text == getString(R.string.three)) {
            value = 13;
        }
        if (text == getString(R.string.two)) {
            value = 12;
        }
        if (text == getString(R.string.face_cards)) {
            value = 10;
        }
        return value;

    }
    // Get the value of Prime selected in the Spinners for player 2
    private int tilesMenuP2() {

        Spinner spinner = (Spinner) findViewById(R.id.prime_tilesP2);
        String text = spinner.getSelectedItem().toString();

        int value = 0;
        if (text == getString(R.string.none)) {
            value = 0;
        }
        if (text == getString(R.string.seven)) {
            value = 21;
        }
        if (text == getString(R.string.six)) {
            value = 18;
        }
        if (text == getString(R.string.ace)) {
            value = 16;
        }
        if (text == getString(R.string.five)) {
            value = 15;
        }
        if (text == getString(R.string.four)) {
            value = 14;
        }
        if (text == getString(R.string.three)) {
            value = 13;
        }
        if (text == getString(R.string.two)) {
            value = 12;
        }
        if (text == getString(R.string.face_cards)) {
            value = 10;
        }
        return value;

    }

    private int heartsMenuP2() {

        Spinner spinner = (Spinner) findViewById(R.id.prime_heartsP2);
        String text = spinner.getSelectedItem().toString();

        int value = 0;
        if (text == getString(R.string.none)) {
            value = 0;
        }
        if (text == getString(R.string.seven)) {
            value = 21;
        }
        if (text == getString(R.string.six)) {
            value = 18;
        }
        if (text == getString(R.string.ace)) {
            value = 16;
        }
        if (text == getString(R.string.five)) {
            value = 15;
        }
        if (text == getString(R.string.four)) {
            value = 14;
        }
        if (text == getString(R.string.three)) {
            value = 13;
        }
        if (text == getString(R.string.two)) {
            value = 12;
        }
        if (text == getString(R.string.face_cards)) {
            value = 10;
        }
        return value;

    }

    private int pikesMenuP2() {

        Spinner spinner = (Spinner) findViewById(R.id.prime_pikesP2);
        String text = spinner.getSelectedItem().toString();

        int value = 0;
        if (text == getString(R.string.none)) {
            value = 0;
        }
        if (text == getString(R.string.seven)) {
            value = 21;
        }
        if (text == getString(R.string.six)) {
            value = 18;
        }
        if (text == getString(R.string.ace)) {
            value = 16;
        }
        if (text == getString(R.string.five)) {
            value = 15;
        }
        if (text == getString(R.string.four)) {
            value = 14;
        }
        if (text == getString(R.string.three)) {
            value = 13;
        }
        if (text == getString(R.string.two)) {
            value = 12;
        }
        if (text == getString(R.string.face_cards)) {
            value = 10;
        }
        return value;

    }

    private int cloversMenuP2() {

        Spinner spinner = (Spinner) findViewById(R.id.prime_cloversP2);
        String text = spinner.getSelectedItem().toString();

        int value = 0;
        if (text == getString(R.string.none)) {
            value = 0;
        }
        if (text == getString(R.string.seven)) {
            value = 21;
        }
        if (text == getString(R.string.six)) {
            value = 18;
        }
        if (text == getString(R.string.ace)) {
            value = 16;
        }
        if (text == getString(R.string.five)) {
            value = 15;
        }
        if (text == getString(R.string.four)) {
            value = 14;
        }
        if (text == getString(R.string.three)) {
            value = 13;
        }
        if (text == getString(R.string.two)) {
            value = 12;
        }
        if (text == getString(R.string.face_cards)) {
            value = 10;
        }
        return value;

    }

    //set number of score shown by TextView
    //This shows on the small number the score got after counting the points got on current deal for the player 1
    private void displayForP1(int score) {
        TextView scoreView = (TextView) findViewById(R.id.P1_Deal_score);
        scoreView.setText(String.valueOf(score));
    }
    //This shows on the small number the score got after counting the points got on current deal for the player 2
    private void displayForP2(int score) {
        TextView scoreView = (TextView) findViewById(R.id.P2_Deal_score);
        scoreView.setText(String.valueOf(score));
    }
    //This shows on the big number the Wins score for the player 1
    private void displayNumWinP1(int score) {
        TextView scoreView = (TextView) findViewById(R.id.P1_NumWin);
        scoreView.setText(String.valueOf(score));
    }
    //This shows on the big number the Wins score for the player 2
    private void displayNumWinP2(int score) {
        TextView scoreView = (TextView) findViewById(R.id.P2_NumWin);
        scoreView.setText(String.valueOf(score));
    }

    //Reset all value of all variable to 0, all fields and show the result
    public void resetScore(View view) {
        clearData();
        score = 0;
        numberWinP1 = 0;
        numberWinP2 = 0;
        displayForP1(score);
        displayForP2(score);
        displayNumWinP1(numberWinP1);
        displayNumWinP2(numberWinP2);

    }
    //Clear all fields
    private void clearData() {
        EditText getCardsP1 = (EditText) findViewById(cardsP1);
        getCardsP1.setText("");
        EditText getCardsP2 = (EditText) findViewById(cardsP2);
        getCardsP2.setText("");
        EditText getCoinsP1 = (EditText) findViewById(coinsP1);
        getCoinsP1.setText("");
        EditText getCoinsP2 = (EditText) findViewById(coinsP2);
        getCoinsP2.setText("");
        EditText getScopaP1 = (EditText) findViewById(scopaP1);
        getScopaP1.setText("");
        EditText getScopaP2 = (EditText) findViewById(scopaP2);
        getScopaP2.setText("");
        EditText firstEditText1 = (EditText) findViewById(R.id.cardsP1);
        firstEditText1.setText("");
        EditText firstEditText2 = (EditText) findViewById(R.id.cardsP2);
        firstEditText2.setText("");

        RadioGroup setteBelloP1 = (RadioGroup) findViewById(R.id.radiogroup);
        setteBelloP1.clearCheck();

        Spinner spinnerTP1 = (Spinner) findViewById(R.id.prime_tilesP1);
        spinnerTP1.setSelection(0);
        Spinner spinnerHP1 = (Spinner) findViewById(R.id.prime_heartsP1);
        spinnerHP1.setSelection(0);
        Spinner spinnerPP1 = (Spinner) findViewById(R.id.prime_pikesP1);
        spinnerPP1.setSelection(0);
        Spinner spinnerCP1 = (Spinner) findViewById(R.id.prime_cloversP1);
        spinnerCP1.setSelection(0);

        Spinner spinnerTP2 = (Spinner) findViewById(R.id.prime_tilesP2);
        spinnerTP2.setSelection(0);
        Spinner spinnerHP2 = (Spinner) findViewById(R.id.prime_heartsP2);
        spinnerHP2.setSelection(0);
        Spinner spinnerPP2 = (Spinner) findViewById(R.id.prime_pikesP2);
        spinnerPP2.setSelection(0);
        Spinner spinnerCP2 = (Spinner) findViewById(R.id.prime_cloversP2);
        spinnerCP2.setSelection(0);

        TextView scoreViewP1 = (TextView) findViewById(R.id.P1_Deal_score);
        scoreViewP1.setTextColor(getResources().getColor(R.color.textApp));
        TextView scoreViewP2 = (TextView) findViewById(R.id.P2_Deal_score);
        scoreViewP2.setTextColor(getResources().getColor(R.color.textApp));
    }
}
