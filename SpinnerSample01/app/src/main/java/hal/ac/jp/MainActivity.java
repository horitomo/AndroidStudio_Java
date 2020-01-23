package hal.ac.jp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // メンバ変数としてadapterを宣言
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 各部品（TextView、EditText、Button）のインスタンス化
        Button btn = findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSpinner();
            }
        });

        // spinner
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        // Spinnerのインスタンス化
        Spinner sp = findViewById(R.id.spinner);

        // Spinnerとadapterを紐付ける
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        sp.setAdapter(adapter);

        //  adapterに追加することでspinnerに追加される
        adapter.add("清水さん");

        // spinnerが選択されたときの処理
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // AdapterViewをSpinnerに変換する
                Spinner spinner = (Spinner)parent;

                // 内容を取得する
                String str = spinner.getSelectedItem().toString();
                Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btn2 = findViewById(R.id.button3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delData();
            }
        });


    }

    private void setSpinner() {
        EditText et = findViewById(R.id.editText2);
        String str = et.getText().toString();

        // 空白ではないことを確認する
        if(!str.isEmpty()) {
            adapter.add(str);
        }else {
            Toast.makeText(MainActivity.this,"データを入力してください",Toast.LENGTH_LONG).show();
        }
    }

    private void delData(){
        EditText et = findViewById(R.id.editText2);
        String str = et.getText().toString();

        if(!str.isEmpty()) {
            adapter.remove(str);
        }else {
            Toast.makeText(MainActivity.this,"データを入力してください",Toast.LENGTH_LONG).show();
        }

    }
}
