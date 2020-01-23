package hal.ac.jp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // adapter
    ArrayAdapter<String> adapter;
    // EditText
    EditText etTitle,etContents;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // spinner
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        // Spinnerのインスタンス化
        spinner = findViewById(R.id.spinner);

        // Spinnerとadapterを紐付ける
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spinner.setAdapter(adapter);

        //  adapterに追加することでspinnerに追加される
        adapter.add("タイトルを選択してください");
        SharedPreferences preference = getSharedPreferences("memo",MODE_PRIVATE);
        Map<String,?> map = preference.getAll();
        for( Map.Entry<String, ?> entry : map.entrySet() )
        {
            // キー
            String key = entry.getKey();
            adapter.add(key);
        }

        // インスタンス化
        etTitle = findViewById(R.id.title_area);
        etContents = findViewById(R.id.contents_area);
        Button registerBtn =findViewById(R.id.register);
        Button removeBtn = findViewById(R.id.remove);

        // ボタンを押されたら
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 登録処理
                register();
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 削除処理
                remove();
            }
        });

        // spinnerが選択されたときの処理
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // AdapterViewをSpinnerに変換する
                Spinner spinner = (Spinner) parent;

                // 内容を取得する
                if(position != 0) {
                    String str = spinner.getSelectedItem().toString();
                    display(str);
                }else {
                    etTitle.setText("");
                    etContents.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void register(){
        // 登録内容の取得
        String title = etTitle.getText().toString();
        String contents = etContents.getText().toString();

        // SharedPreferencesのインスタンス化
        // getSharedPreferences(テーブル名,どういうモードで使用するか)
        SharedPreferences sp = getSharedPreferences("memo",MODE_PRIVATE);

        // SharedPreferencesに保存するためにはEditorクラスを利用する
        SharedPreferences.Editor editor = sp.edit();
        // putで登録内容を入れる
        editor.putString(title,contents);
        if(editor.commit()){
            Toast.makeText(MainActivity.this,"登録しました",Toast.LENGTH_LONG).show();
        }
        adapter.remove(title);
        adapter.add(title);
    }

    // SharedPreferencesからの取得
    private void display(String str){
        // SharedPreferencesの取得
        SharedPreferences sp = getSharedPreferences("memo",MODE_PRIVATE);

        // キーを検索し見つかったら取得する
        String contents = sp.getString(str,"見つかりませんでした。");

        // 取得した文字列をセットする
        etTitle.setText(str);
        etContents.setText(contents);


    }

    // 削除処理
    private void remove(){
        String title = etTitle.getText().toString();

        // SharedPreferencesの取得
        SharedPreferences sp = getSharedPreferences("memo",MODE_PRIVATE);
        // SharedPreferencesからデータを消去
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(title);
        editor.commit();

        adapter.remove(title);
        spinner.setSelection(0);

        etTitle.setText("");
        etContents.setText("");
    }
}
