package hal.ac.jp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 登録・表示に使うEditText
    EditText etTitle,etContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 各部品のインスタンス化
        etTitle = findViewById(R.id.title_area);
        etContents = findViewById(R.id.contents_area);
        Button registerBtn = findViewById(R.id.register);
        Button displayBtn = findViewById(R.id.display);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 登録メソッドの実行
                register();
            }
        });

        displayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 表示メソッドの実行
                display();
            }
        });
    }

    // SharedPreferenceへの登録
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
    }

    // SharedPreferencesからの取得
    private void display(){
        // 検索キーの取得
        String title = etTitle.getText().toString();

        // SharedPreferencesの取得
        SharedPreferences sp = getSharedPreferences("memo",MODE_PRIVATE);

        // キーを検索し見つかったら取得する
        String contents = sp.getString(title,"見つかりませんでした。");

        // 取得した文字列をセットする
        etContents.setText(contents);

        // SharedPreferencesからデータを消去
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(title);
        editor.commit();
    }
}
