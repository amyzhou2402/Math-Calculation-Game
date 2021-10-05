package au.edu.unsw.infs3634.mathit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetView();
    }

//    import timer info and set up variables
    StopWatch stopWatch = new StopWatch();

    int num1 = -1;
    int num2 = -1;
    int num3 = -1;
    boolean quit = false;

    String[] operatorArray = new String[]{"+", "*", "-", "/"};
    String randomOperator = "";

//    calculating expected answer so we can check against userInput
    public double calculateExpected() {
        double expectedAns = 0;

        if (randomOperator.equals("+")) {expectedAns = num1 + num2;}
        if (randomOperator.equals("-")) {expectedAns = num1 - num2;}
        if (randomOperator.equals("*")) {expectedAns = num1 * num2;}
        if (randomOperator.equals("/")) {expectedAns = num1 / num2;}

        return expectedAns;
    }

    public void startGame(View v) {
        resetView();
        stopWatch.start();

        Button btSubmitAnswer = findViewById(R.id.btSubmitAnswer);
        btSubmitAnswer.setVisibility(View.VISIBLE);
        TextView tvGameDesc = findViewById(R.id.tvGameDesc);
        tvGameDesc.setVisibility(View.GONE);
        Button btGoToGame = findViewById(R.id.btGoToGame);
        btGoToGame.setVisibility(View.GONE);
        TextView tvUserInput = findViewById(R.id.tvUserInput);
        tvUserInput.setVisibility(View.VISIBLE);

        num1 = getRandomNumber(0,101);
        num2 = getRandomNumber(0,101);
        num3 = getRandomNumber(0,4);

        randomOperator = operatorArray[num3];

        String number1 = String.valueOf(num1);
        String number2 = String.valueOf(num2);

//        formatting gameQuestion on home page
        TextView gameQuestion = findViewById(R.id.tvGameQuestion);
        gameQuestion.setText(String.format("%s %s %s", number1, randomOperator, number2));
    }

//    signals completion of round by ending timer and checks userInput against expectedAnswer
    public void checkAnswer (View v){
        stopWatch.stop();
        String tvTimer = String.valueOf(stopWatch.getElapsedTime());

//        Relevant textViews are made visible so user can decide to either play another round or end the game
        TextView tvStopWatch = findViewById(R.id.tvStopWatch);
        tvStopWatch.setVisibility(View.VISIBLE);
        tvStopWatch.setText(tvTimer);

        Button btGoToGame = findViewById(R.id.btGoToGame);
        btGoToGame.setVisibility(View.VISIBLE);
        btGoToGame.setText("Play Again");

        Button btQuitGame = findViewById(R.id.btQuitGame);
        btQuitGame.setVisibility(View.VISIBLE);

        Button btSubmitAnswer = findViewById(R.id.btSubmitAnswer);
        btSubmitAnswer.setVisibility(View.GONE);

        double expectedAnswer = calculateExpected();

        EditText userInput = findViewById(R.id.tvUserInput);
        String userInputStr = userInput.getText().toString();
        double userInputD = Double.parseDouble(userInputStr);

//        checking against expectedAnswer, changing success message and calculating point system.
        if(userInputD == expectedAnswer){
            TextView successOutput = findViewById(R.id.tvSuccessOutput);
            successOutput.setText("Congratulations, your answer is correct! You're an absolute genius!");
//            int point = pointSystem();
//            tvPointSystem.setText("Point: " + point);
        }

        else if (userInputStr.equals("")){
            TextView emptyOutput = findViewById(R.id.tvSuccessOutput);
            emptyOutput.setText("i tried so hard to make the game can you at least attempt the question :(");
            emptyOutput.setVisibility(View.VISIBLE);
        }

        else{
            TextView wrongOutput = findViewById(R.id.tvSuccessOutput);
            wrongOutput.setText("Oh noes, seems like your answer was wrong... Press restart to generate a new question or quit to end the session.");
            wrongOutput.setVisibility(View.VISIBLE);
        }
    }

//    clears out all irrelevant elements so that home page is clean
    public void resetView() {
        Button btGoToGame = findViewById(R.id.btGoToGame);
        btGoToGame.setVisibility(View.VISIBLE);

        TextView resetGameQuestion = findViewById(R.id.tvGameQuestion);
        resetGameQuestion.setText("");

        TextView tvStopWatch = findViewById(R.id.tvStopWatch);
        tvStopWatch.setText("");
        tvStopWatch.setVisibility(View.GONE);

        EditText resetUserInput = findViewById(R.id.tvUserInput);
        resetUserInput.setText("");

        TextView resetSuccessOutput = findViewById(R.id.tvSuccessOutput);
        resetSuccessOutput.setText("");

        Button btSubmitAnswer = findViewById(R.id.btSubmitAnswer);
        btSubmitAnswer.setVisibility(View.GONE);

        Button btQuitGame = findViewById(R.id.btQuitGame);
        btQuitGame.setVisibility(View.GONE);

        TextView tvUserInput = findViewById(R.id.tvUserInput);
        tvUserInput.setVisibility(View.GONE);

        TextView tvPointSystem = findViewById(R.id.tvPointSystem);
        tvPointSystem.setVisibility(View.GONE);
    }

//    Method to generate random numbers
    public int getRandomNumber(int min, int max){
        return (int) ((Math.random() * (max-min)) + min);
    }

//    method to be run when game ends, resets views and renames button to start game.
    public void quitGame(View v) {
        resetView();

        TextView tvGameDesc = findViewById(R.id.tvGameDesc);
        tvGameDesc.setVisibility(View.VISIBLE);

        Button btGoToGame = findViewById(R.id.btGoToGame);
        btGoToGame.setVisibility(View.VISIBLE);
        btGoToGame.setText("Start Game");
    }

//    implementing a point system
//    public int pointSystem(){
//        int point = 0;
//        return point++;
//    }

}

