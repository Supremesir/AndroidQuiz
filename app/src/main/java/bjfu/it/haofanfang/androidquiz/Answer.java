package bjfu.it.haofanfang.androidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Answer extends AppCompatActivity {

    public static final String EXTRA_ID="questionid";
    public static int tnum = 0;
    public int questionid;
    TextView quesText;
    EditText anweText;
    Question question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Intent intent = getIntent();
        questionid = (Integer) intent.getExtras().get(EXTRA_ID);

        question = Question.questions[questionid];

        //问题栏显示读取的问题
        quesText = findViewById(R.id.ques);
        quesText.setText(question.getQuestion());

        //如果已经填写过答案，将其显示出来
        if (question.getAnswer() != "") {
            anweText = findViewById(R.id.answ);
            anweText.setText(question.getAnswer());
        }
    }

    public void onClickSave(View view) {

        //获取用户填写的答案
        anweText = (EditText) findViewById(R.id.answ);
        String answer = anweText.getText().toString();

        //判断填入的答案与正确答案相同的个数
        Question.questions[questionid].setAnswer(answer);
        if (answer.equals(question.getTanswer())) {
            tnum++;
        }
        //点击保存之后，关闭该activity
        finish();
    }
}
