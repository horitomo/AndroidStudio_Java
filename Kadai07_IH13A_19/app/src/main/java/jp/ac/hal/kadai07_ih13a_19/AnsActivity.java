package jp.ac.hal.kadai07_ih13a_19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ans);

        String val = getIntent().getStringExtra("ans");
        int title = getIntent().getIntExtra("quizNum",0);
        if(val == null){
            val = "答えを選択してから回答してください";
            TextView tv = findViewById(R.id.textView2);
            tv.setText(val);
        }else {
            if(title == 0){
                if(val.equals("人間にすぐ捕まるから")){
                    TextView tv = findViewById(R.id.textView2);
                    tv.setText("正解です");
                }else {
                    TextView tv = findViewById(R.id.textView2);
                    tv.setText("不正解です。");
                }
            }else if(title == 1){
                if(val.equals("透明な卵になる")){
                    TextView tv = findViewById(R.id.textView2);
                    tv.setText("正解です");
                }else {
                    TextView tv = findViewById(R.id.textView2);
                    tv.setText("不正解です。");
                }
            }else{
                if(val.equals("砂糖")){
                    TextView tv = findViewById(R.id.textView2);
                    tv.setText("正解です");
                }else {
                    TextView tv = findViewById(R.id.textView2);
                    tv.setText("不正解です。");
                }
            }
        }

        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
