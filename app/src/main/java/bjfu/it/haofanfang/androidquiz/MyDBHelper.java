package bjfu.it.haofanfang.androidquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author HaoFan Fang
 * @date 2020-01-01 22:57
 */

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="AndroidQuzi";
    private static final int DB_VERSION=1;

    public MyDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RECORD(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        +"SNO TEXT,"
        +"SECOND INTEGER,"
        +"ANW1 TEXT,"
        +"ANW2 TEXT,"
        +"ANW3 TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
