package hal.ac.jp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // 受け取る
        Student student = (Student) getIntent().getExtras().get("student");
        // 表示するTextViewをインスタンス化する
        TextView tv = findViewById(R.id.textView);
        TextView tv1 = findViewById(R.id.textView5);
        TextView tv2 = findViewById(R.id.textView6);
        tv.setText(String.valueOf(student.getStudentNum()));
        tv1.setText(student.getClassName());
        tv2.setText(student.getStudentName());
    }
}
