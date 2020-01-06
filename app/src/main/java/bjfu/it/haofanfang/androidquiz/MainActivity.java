package bjfu.it.haofanfang.androidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
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

    private boolean wasrunning;
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
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasrunning = savedInstanceState.getBoolean("wasrunning");
        }

        //启动秒表
        runTimer();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    public void onClickStart(View view) {
        listAdapter();
        running = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", count);
        outState.putBoolean("running", running);
        outState.putBoolean("wasrunning", wasrunning);
    }

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

    }

    private void runTimer() {
        timeView = findViewById(R.id.time);
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = count / 3600;
                int minutes = (count % 3600) / 60;
                int seconds = count % 60;
                String timeText = String.format("%d:%02d:%02d", hours, minutes, seconds);
                timeView.setText(timeText);
                if (running) {
                    count++;
                }
                handler.postDelayed(this, 1000);
            }
        });

    }

    //从Question类中读取数据，显示list
    private void listAdapter() {
        ArrayAdapter<Question> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,Question.questions);
        questionList = findViewById(R.id.question_list);
        questionList.setAdapter(listAdapter);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itemClickIntent = new Intent(MainActivity.this, Answer.class);
                itemClickIntent.putExtra(EXTRA_ID, (int) id);
                startActivity(itemClickIntent);
            }
        };
        questionList.setOnItemClickListener(itemClickListener);
    }

    public void onClickPlay(View view) {
        Intent mediaIntent = new Intent(this, MediaService.class);
        startService(mediaIntent);
        Log.v("music", "play music");
    }
}
