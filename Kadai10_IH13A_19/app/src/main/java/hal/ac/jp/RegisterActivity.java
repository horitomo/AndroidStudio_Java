package hal.ac.jp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterYear;
    ArrayAdapter<String> adapterMonth;
    ArrayAdapter<String> adapterDay;
    Spinner sp;
    Spinner spYear;
    Spinner spMonth;
    Spinner spDay;
    int positionSet = 0;
    int yearRegister = 0;
    int monthRegister = 0;
    int dayRegister = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        date();

        // spinner
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        // Spinnerのインスタンス化
        sp = findViewById(R.id.spinner);

        // Spinnerとadapterを紐付ける
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        sp.setAdapter(adapter);
        adapter.add("--項目一覧--");
        MyDatabaseHelper dbhelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String col[] = {"_id", "item"};
        Cursor c = db.query("item",col,null,null,null,null,null);
        boolean b = c.moveToFirst();
        //データの取り出し
        while (b)
        {
            String str = c.getString(1);
            adapter.add(str);
            b = c.moveToNext();
        }
        db.close();

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // AdapterViewをSpinnerに変換する
                Spinner spinner = (Spinner) parent;
                positionSet = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = adapterYear.getItem(position);
                yearRegister = Integer.parseInt(str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = adapterMonth.getItem(position);
                monthRegister = Integer.parseInt(str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = adapterDay.getItem(position);
                dayRegister = Integer.parseInt(str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button accountsRegisterBtn = findViewById(R.id.accountsRegister);
        accountsRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountsRegister();
            }
        });

        Button categoryRegisterBtn = findViewById(R.id.categoryRegisterButton);
        categoryRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryRegister();
            }
        });

        Button btnCancel = findViewById(R.id.button2);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void accountsRegister(){
        if(positionSet != 0) {
            EditText amount = findViewById(R.id.amount);
            int amountRegister = Integer.parseInt(amount.getText().toString());
            if(amountRegister != 0) {
                String item_name = adapter.getItem(positionSet);
                RadioGroup rg = findViewById(R.id.radiogroup);
                int rBtn = rg.getCheckedRadioButtonId();
                int type = 0;
                if (rBtn == R.id.type2) {
                    type = 1;
                }

                // ContentValueを使って登録
                ContentValues cv = new ContentValues();
                cv.put("year", yearRegister);
                cv.put("month", monthRegister);
                cv.put("day", dayRegister);
                cv.put("item_name", item_name);
                cv.put("type", type);
                cv.put("amount", amountRegister);
                // テーブルに挿入
                MyDatabaseHelper dbhelper = new MyDatabaseHelper(this);
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                db.insert("accounts", null, cv);
                db.close();
                Toast.makeText(RegisterActivity.this, "登録に成功しました。", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(RegisterActivity.this, "金額を入力してください。", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(RegisterActivity.this, "項目を選択してください。", Toast.LENGTH_LONG).show();
        }
    }

    private void date(){
        // spinner
        adapterYear = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        // Spinnerのインスタンス化
        spYear = findViewById(R.id.spinnerYear);
        // Spinnerとadapterを紐付ける
        adapterYear.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spYear.setAdapter(adapterYear);
        Calendar cal = Calendar.getInstance();	//[1]
        int year = cal.get(Calendar.YEAR);
        yearRegister = cal.get(Calendar.YEAR);
        int cnt = -5;
        for (int i = 0 ; i < 10; i++){
            int addyear = year + cnt + i;
            adapterYear.add(String.valueOf(addyear));
        }
        spYear.setSelection(5);

        // spinner
        adapterMonth = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        // Spinnerのインスタンス化
        spMonth = findViewById(R.id.spinnerMonth);
        // Spinnerとadapterを紐付ける
        adapterMonth.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spMonth.setAdapter(adapterMonth);
        for(int i = 1 ; i < 13; i++){
            adapterMonth.add(String.valueOf(i));
        }
        int month = cal.get(Calendar.MONTH);
        monthRegister = month+1;
        spMonth.setSelection(month);

        // spinner
        adapterDay = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        // Spinnerのインスタンス化
        spDay = findViewById(R.id.spinnerDay);
        // Spinnerとadapterを紐付ける
        adapterDay.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spDay.setAdapter(adapterDay);
        for(int j = 1 ; j < 32; j++){
            adapterDay.add(String.valueOf(j));
        }
        int day = cal.get(Calendar.DATE);
        dayRegister = day;
        spDay.setSelection(day-1);
    }

    private void categoryRegister(){
        EditText category = findViewById(R.id.category);
        String categoryRegister = category.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put("item",categoryRegister);
        MyDatabaseHelper dbhelper1 = new MyDatabaseHelper(this);
        SQLiteDatabase db = dbhelper1.getWritableDatabase();
        db.insert("item", null, cv);
        adapter.clear();
        adapter.add("--項目一覧--");
        String col[] = {"_id", "item"};
        Cursor c = db.query("item",col,null,null,null,null,null);
        boolean b = c.moveToFirst();
        //データの取り出し
        while (b)
        {
            String str = c.getString(1);
            adapter.add(str);
            b = c.moveToNext();
        }
        db.close();
        Toast.makeText(RegisterActivity.this,"項目の登録に成功しました。",Toast.LENGTH_LONG).show();
    }
}
