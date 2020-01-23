package com.example.android_user.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DBhelperのインスタンス化
        MyDatabaseHelper helper = new MyDatabaseHelper(this);
        //SQLiteDatabaseのインスタンス化
        SQLiteDatabase db = helper.getWritableDatabase();
        //テーブルを紹介する問い合わせ（query）を発行する
        //今回は全件検索
        //戻り値はCursor
        String[] col = {"_id", "name","price","comment"};
        Cursor c = db.query("localspecialty",col, null, null, null,null,null);

        //Cursorを先頭にセットする
       boolean b = c.moveToFirst();
       String list = "";
       //データの取り出し
        while (b)
        {
            //名産品名を取り出す
            String name = c.getString(1);
            list += name;
            //TwxtViewに表示する
            TextView tv = findViewById(R.id.sample);
            tv.setText(list);
            b = c.moveToNext();
        }
    }
}
