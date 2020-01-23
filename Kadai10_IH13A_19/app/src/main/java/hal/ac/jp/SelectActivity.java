package hal.ac.jp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        MyDatabaseHelper dbhelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String col[] = {"_id", "year","month","day","item_name","type","amount"};
        Cursor c = db.query("accounts",col,null,null,null,null,null);
        boolean b = c.moveToFirst();
        //データの取り出し
        TextView tv = findViewById(R.id.textView2);
        String str = "";
        while (b)
        {
            for(int i = 0; i < 7; i++) {
                if(i == 5){
                    if(Integer.parseInt(c.getString(i)) == 0){
                        str += "支出";
                    }else {
                        str += "収入";
                    }
                }else {
                    str += c.getString(i);
                }
            }
            str += "\n";
            b = c.moveToNext();
        }
        db.close();
        tv.setText(str);

    }
}
