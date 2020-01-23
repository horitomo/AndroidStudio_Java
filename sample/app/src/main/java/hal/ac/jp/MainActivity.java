package hal.ac.jp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    EditText titleEd , contentEd ;
    Spinner sp;
    int positionSet = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEd = findViewById(R.id.memoTitle);
        contentEd = findViewById(R.id.memoContent);
        // spinner
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        // Spinnerのインスタンス化
        sp = findViewById(R.id.spinner);

        // Spinnerとadapterを紐付ける
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        sp.setAdapter(adapter);
        adapter.add("--メモ一覧--");
        MyDatabaseHelper dbhelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String col[] = {"_id", "title", "content"};
        Cursor c = db.query("memo",col,null,null,null,null,null);
        boolean b = c.moveToFirst();
        TextView tv = findViewById(R.id.textView);
        //データの取り出し
        while (b)
        {
            String str = c.getString(1);
            adapter.add(str);
            b = c.moveToNext();
        }
        db.close();

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!titleEd.getText().toString().isEmpty() && !contentEd.getText().toString().isEmpty()) {
                    register();
                    sp.setSelection(0);
                }
            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // AdapterViewをSpinnerに変換する
                Spinner spinner = (Spinner) parent;

                // 内容を取得する
                if(position != 0) {
                    String str = spinner.getSelectedItem().toString();
                    positionSet = position;
                    search(str,position);
                }else {
                    positionSet = 0;
                    titleEd.setText("");
                    contentEd.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void register(){
        String title = titleEd.getText().toString();
        String content = contentEd.getText().toString();

        // ContentValueを使って登録
        ContentValues cv = new ContentValues();
        cv.put("title",title);
        cv.put("content",content);
        // テーブルに挿入
        MyDatabaseHelper dbhelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        if(positionSet == 0) {
            db.insert("memo", null, cv);
            String col[] = {"_id", "title", "content"};
            Cursor c = db.query("memo",col,null,null,null,null,null);
            boolean b = c.moveToLast();
            //データの取り出し
            while (b)
            {
                String str = c.getString(1);
                adapter.add(str);
                b = c.moveToNext();
            }
            titleEd.setText("");
            contentEd.setText("");
        }else {
            String id = String.valueOf(positionSet);
            String[] whereArgs = {id};
            db.update("memo",cv,"_id = ?", whereArgs);
        }
        db.close();

    }

    private void search(String searchTitle, int position){
        MyDatabaseHelper dbhelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String col[] = {"_id", "title", "content"};
        String[] whereArgs = {String.valueOf(position),searchTitle};
        Cursor c = db.query("memo",col,"_id = ? AND title = ?",whereArgs,null,null,null);
        c.moveToFirst();
        titleEd.setText(c.getString(1));
        contentEd.setText(c.getString(2));
    }

    private  void delete(){
        if(positionSet != 0) {
            MyDatabaseHelper dbhelper = new MyDatabaseHelper(this);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            String col[] = {"_id", "title", "content"};
            Cursor c = db.query("memo",col,null,null,null,null,null);
            int a =c.getCount();
            String[] whereArgs2 = {String.valueOf(positionSet)};
            db.delete("memo","_id = ?",whereArgs2);
            for(int i = positionSet+1;i <= a;i++){
                ContentValues cv = new ContentValues();
                cv.put("_id", i-1);
                String[] whereArgs3 = {String.valueOf(i)};
                db.update("memo",cv,"_id = ?",whereArgs3);
            }
            db.execSQL("ALTER TABLE memo AUTO_INCREMENT=?",new Object[]{a-1});
            String col1[] = {"_id", "title", "content"};
            Cursor c1 = db.query("memo",col1,null,null,null,null,null);
            adapter.clear();
            boolean b = c.moveToFirst();
            while (b)
            {
                String str = c.getString(1);
                adapter.add(str);
                b = c.moveToNext();
            }
            db.close();

        }
    }
}
