package bjfu.it.haofanfang.androidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static bjfu.it.haofanfang.androidquiz.Answer.EXTRA_ID;
import static bjfu.it.haofanfang.androidquiz.Answer.tnum;

public class MainActivity extends AppCompatActivity {

    private boolean running;
    private int count = 0;

    TextView timeView;
    ListView questionList;
    EditText snoText;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //从保存的状态里读取数据
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

        //启动秒表
        runTimer();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    //点击开始答题时的响应
    public void onClickStart(View view) {
        //点击开始答题时才显示list
        listAdapter();
        running = true;
    }

    //将当前程序运行状态保存起来
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", count);
        outState.putBoolean("running", running);
    }


    //点击答题完毕的响应
    public void onClickStop(View view) {

        snoText = findViewById(R.id.sno);
        String xuehao = snoText.getText().toString();
        if (xuehao.equals("")) {
            Toast.makeText(this, "请输入学号！", Toast.LENGTH_SHORT).show();
        } else {
            running = false;
            Toast.makeText(this, "答对了" + tnum + "道题！", Toast.LENGTH_SHORT).show();
            ContentValues questionValues = new ContentValues();
            questionValues.put("SNO", xuehao);
            questionValues.put("SECOND", count);
            questionValues.put("ANW1", Question.questions[0].getAnswer());
            questionValues.put("ANW2", Question.questions[1].getAnswer());
            questionValues.put("ANW3", Question.questions[2].getAnswer());

            SQLiteOpenHelper MyDBHelper = new MyDBHelper(this);
            try {
                SQLiteDatabase db = MyDBHelper.getWritableDatabase();
                db.insert("RECORD", null, questionValues);
            } catch (SQLException e) {
                Toast.makeText(this, "Database unavailable!", Toast.LENGTH_SHORT).show();
            }
        }

        //点击结束答题后清零计时器
        count = 0;

    }

    //计时器计时函数
    private void runTimer() {
        timeView = findViewById(R.id.time);

        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = count / 3600;
                int minutes = (count % 3600) / 60;
                int seconds = count % 60;
                //时间显示的文本
                String timeText = String.format("%d:%02d:%02d", hours, minutes, seconds);
                timeView.setText(timeText);
                //如果running为1，才开始计数
                if (running) {
                    count++;
                }
                //不能不写
                handler.postDelayed(this, 1000);
            }
        });
    }

    //从Question类中读取数据，显示list
    private void listAdapter() {

        questionList = findViewById(R.id.question_list);
        questionList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Question.questions));
        questionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itemClickIntent = new Intent(MainActivity.this, Answer.class);
                itemClickIntent.putExtra(EXTRA_ID, (int) id);
                startActivity(itemClickIntent);
            }
        });

//        ArrayAdapter<Question> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Question.questions);
//        questionList = findViewById(R.id.question_list);
//        questionList.setAdapter(listAdapter);
//        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent itemClickIntent = new Intent(MainActivity.this, Answer.class);
//                itemClickIntent.putExtra(EXTRA_ID, (int) id);
//                startActivity(itemClickIntent);
//            }
//        };
//        questionList.setOnItemClickListener(itemClickListener);
    }

    //点击播放须知的响应函数
    public void onClickPlay(View view) {
        Intent mediaIntent = new Intent(this, MediaService.class);
        startService(mediaIntent);
        Log.v("music", "play music");
    }
}
