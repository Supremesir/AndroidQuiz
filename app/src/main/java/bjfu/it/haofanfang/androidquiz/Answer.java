package bjfu.it.haofanfang.androidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Answer extends AppCompatActivity {

    public static final String EXTRA_ID="questionid";
    public int questionid;
    TextView quesText;
    EditText anweText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Intent intent = getIntent();
        questionid = (Integer) intent.getExtras().get(EXTRA_ID);
        Question question = Question.questions[questionid];

        quesText = findViewById(R.id.ques);
        quesText.setText(question.getQuestion());

        if (question.getAnswer() != "") {
            anweText = findViewById(R.id.answ);
            anweText.setText(question.getAnswer());
        }
    }

    public void onClickSave(View view) {
        anweText = (EditText) findViewById(R.id.answ);
        String answer = anweText.getText().toString();
        Question.questions[questionid].setAnswer(answer);
        finish();
    }
}
