package com.example.android_user.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by android_user on 2019/11/22.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    //DB名、バージョン、create文を定義
    private final static String DB_NAME ="sampleDatabase";
    private final static int DB_VERSION = 1;
    private final static String CLEATE_LS_TABLE ="create table localspecialty(_id integer primary key autoincrement, name text not null,price integer,comment text)";

    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //DB作成
        db.execSQL(CLEATE_LS_TABLE);

        //データの挿入
        db.execSQL("insert into localspecialty values(null,'落花生',280,'茹でたら酒によくあう!')");
        db.execSQL("insert into localspecialty values(null,'梨',180,'千葉のウリ！')");
        db.execSQL("insert into localspecialty values(null,'ハマグリ',300,'浜焼きで！！')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
