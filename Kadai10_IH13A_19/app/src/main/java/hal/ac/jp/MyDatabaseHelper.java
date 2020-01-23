package hal.ac.jp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public MyDatabaseHelper(Context context) {
        super(context, "Kadai10", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // tableを作成する
        // メソッドはSQLite
        String sql = "create table accounts(_id integer primary key autoincrement, year integer not null, month integer not null, day integer not null, item_name text not null, type integer not null, \n" +
                "amount integer not null)";
        String sql2 = "create table item(_id integer primary key autoincrement, item text not null)";
        db.execSQL(sql);
        db.execSQL(sql2);

        // ContentValueを使って登録
        ContentValues cv = new ContentValues();
        cv.put("item","アルバイト");
        db.insert("item",null,cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
