package hal.ac.jp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        Button search = findViewById(R.id.button2);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    private   void register() {
        // 登録内容の取得
        EditText etState = findViewById(R.id.state);
        EditText etSpecial = findViewById(R.id.special);
        EditText etPrice = findViewById(R.id.price);
        EditText etComment = findViewById(R.id.comment);

        // 文字列に代入
        String stateName = etState.getText().toString();
        String specialtyName = etSpecial.getText().toString();
        int price = Integer.parseInt(etPrice.getText().toString());
        String comment = etComment.getText().toString();

        // ContentValueを使って登録
        ContentValues cv = new ContentValues();
        cv.put("state",stateName);
        cv.put("name",specialtyName);
        cv.put("price",price);
        cv.put("comment",comment);

        // テーブルに挿入
        MyDatabaseHelper dbhelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.insert("localspecialty",null,cv);
        //db.close();

        //中身をクリア
        cv.clear();

        etState.setText("");
        etSpecial.setText("");
        etPrice.setText("");
        etComment.setText("");

        Toast.makeText(MainActivity.this,"登録完了",Toast.LENGTH_LONG).show();

        String col[] = {"_id", "state", "name","price","comment"};
        String[] whereArgs = {"長野","%さ%"};
        Cursor c = db.query("localspecialty",col,"state = ? AND name Like ?",whereArgs,null,null,null);
//        Cursor c = db.query("localspecialty",col, null, null, null,null,null);

        boolean b = c.moveToFirst();
        String list = "";
        //データの取り出し
        while (b)
        {
            //名産品名を取り出す
            String name = c.getString(1) + "の名産品は" + c.getString(2);
            list += name + "\n";
            b = c.moveToNext();
        }

        TextView tv = findViewById(R.id.textView6);
        tv.setText(list);

        db.close();
    }

    private void search(){
        EditText etState = findViewById(R.id.state);
        String stateName = etState.getText().toString();

        MyDatabaseHelper dbhelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        String col[] = {"_id", "state", "name","price","comment"};
        String whereStr = "%" + stateName + "%";
        String[] whereArgs = {whereStr};
        Cursor c = db.query("localspecialty",col,"state Like ?",whereArgs,null,null,null);

        boolean b = c.moveToFirst();
        String list = "";
        //データの取り出し
        while (b)
        {
            //名産品名を取り出す
            String name = c.getString(1) + "の名産品は" + c.getString(2);
            list += name + "\n";
            b = c.moveToNext();
        }

        TextView tv = findViewById(R.id.textView6);
        tv.setText(list);

        db.close();
    }
}
