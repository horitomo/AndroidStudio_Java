package hal.ac.jp;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SelectActivity extends AppCompatActivity {
    ArrayAdapter<String> adapterYear;
    ArrayAdapter<String> adapterMonth;
    Spinner spYear;
    Spinner spMonth;
    int selectYear;
    int selectMonth;
    ListView listView;
    String str2 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        date();
        listView = (ListView) findViewById(R.id.listaccounts);

        spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = adapterYear.getItem(position);
                selectYear = Integer.parseInt(str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = adapterMonth.getItem(position);
                selectMonth = Integer.parseInt(str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAccounts();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 入力フィールド作成
                ListView ls = (ListView)parent;
                String str = (String) ls.getItemAtPosition(position);
                final String[] strings = str.split(" ");
                View inputview;
                //Dialog用レイアウトの読み込み
                LayoutInflater factory = LayoutInflater.from(SelectActivity.this);
                inputview = factory.inflate(R.layout.layout_layout, null);
                final EditText year = inputview.findViewById(R.id.editText);
                final EditText month = inputview.findViewById(R.id.editText2);
                final EditText day = inputview.findViewById(R.id.editText3);
                final EditText amount = inputview.findViewById(R.id.editText6);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectActivity.this, android.R.layout.simple_spinner_item);

                // Spinnerのインスタンス化
                Spinner sp = inputview.findViewById(R.id.spinner4);

                // Spinnerとadapterを紐付ける
                adapter.setDropDownViewResource(
                        android.R.layout.simple_spinner_dropdown_item
                );
                sp.setAdapter(adapter);
                MyDatabaseHelper dbhelper = new MyDatabaseHelper(SelectActivity.this);
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                String col[] = {"_id", "item"};
                Cursor c = db.query("item",col,null,null,null,null,null);
                boolean b = c.moveToFirst();
                int pozi = 0;
                int cnt = 0;
                //データの取り出し
                while (b)
                {
                    if(strings[4].equals(c.getString(1))){
                        pozi = cnt;
                    }
                    String stra = c.getString(1);
                    adapter.add(stra);
                    b = c.moveToNext();
                    cnt++;
                }
                db.close();
                sp.setSelection(pozi);

                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Spinner spi = (Spinner)parent;
                        str2 = (String) spi.getSelectedItem();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                year.setText(strings[1]);
                month.setText(strings[2]);
                day.setText(strings[3]);
                amount.setText(strings[6]);
                final  RadioGroup rg = inputview.findViewById(R.id.radiogroup2);
                int selectedrg = 0;
                if(strings[5].equals("支出")){
                    selectedrg = R.id.type3;
                }else {
                    selectedrg = R.id.type4;
                }
                rg.check(selectedrg);


                new AlertDialog.Builder(SelectActivity.this)
                        .setTitle("登録内容の編集")
                        .setMessage("修正してください。年・月・日・金額は数値で変更してください")
                        .setView(inputview)
                        .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(SelectActivity.this,year.getText().toString(),Toast.LENGTH_SHORT).show();
                                MyDatabaseHelper dbhelper = new MyDatabaseHelper(SelectActivity.this);
                                SQLiteDatabase db = dbhelper.getWritableDatabase();
                                ContentValues cv = new ContentValues();
                                cv.put("year", Integer.parseInt(year.getText().toString()));
                                cv.put("month", Integer.parseInt(month.getText().toString()));
                                cv.put("day",Integer.parseInt(day.getText().toString()));
                                cv.put("item_name",str2);
                                int rBtn = rg.getCheckedRadioButtonId();
                                int type2 = 0;
                                if (rBtn == R.id.type4) {
                                    type2 = 1;
                                }
                                cv.put("type",type2);
                                cv.put("amount",Integer.parseInt(amount.getText().toString()));
                                String[] where = {strings[0]};
                                db.update("accounts", cv, "_id = ?", where);
                                db.close();
                                selectAccounts();
                            }
                        })
                        .setNegativeButton("削除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MyDatabaseHelper dbhelper = new MyDatabaseHelper(SelectActivity.this);
                                SQLiteDatabase db = dbhelper.getWritableDatabase();
                                String[] whereArgs = {strings[0]};
                                db.delete("accounts","_id = ?",whereArgs);
                                db.close();
                                selectAccounts();
                            }
                        })
                        .show();
            }
        });

        Button finbtn = findViewById(R.id.button3);
        finbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void date(){
        // spinner
        adapterYear = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        // Spinnerのインスタンス化
        spYear = findViewById(R.id.spinner2);
        // Spinnerとadapterを紐付ける
        adapterYear.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spYear.setAdapter(adapterYear);
        Calendar cal = Calendar.getInstance();	//[1]
        int year = cal.get(Calendar.YEAR);
        int cnt = -5;
        for (int i = 0 ; i < 10; i++){
            int addyear = year + cnt + i;
            adapterYear.add(String.valueOf(addyear));
        }
        spYear.setSelection(5);
        String yearItem = adapterYear.getItem(5);
        selectYear = Integer.parseInt(yearItem);

        // spinner
        adapterMonth = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        // Spinnerのインスタンス化
        spMonth = findViewById(R.id.spinner3);
        // Spinnerとadapterを紐付ける
        adapterMonth.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spMonth.setAdapter(adapterMonth);
        for(int i = 1 ; i < 13; i++){
            adapterMonth.add(String.valueOf(i));
        }
        int month = cal.get(Calendar.MONTH);
        spMonth.setSelection(month);
        String monthItem = adapterMonth.getItem(month);
        selectMonth = Integer.parseInt(monthItem);
    }

    private void selectAccounts(){
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        // 要素の追加・・・（1）
        MyDatabaseHelper dbhelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String col[] = {"_id", "year","month","day","item_name","type","amount"};
        String[] whereArgs = {String.valueOf(selectYear),String.valueOf(selectMonth)};
        Cursor c = db.query("accounts",col,"year = ? AND month = ?",whereArgs,null,null,null);
        boolean b = c.moveToFirst();
        //データの取り出し
        while (b)
        {
            String str = "";
            for(int i = 0; i < 7; i++) {
                if(i == 5){
                    if(Integer.parseInt(c.getString(i)) == 0){
                        str += "支出 ";
                    }else {
                        str += "収入 ";
                    }
                }else {
                    str += c.getString(i) + " ";
                }
            }
            adapter2.add(str);
            b = c.moveToNext();
        }
        db.close();
        listView.setAdapter(adapter2);
    }
}
