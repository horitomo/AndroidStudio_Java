package hal.ac.jp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submitBtn = findViewById(R.id.button);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveActivity();
            }
        });

    }

    public  void moveActivity(){
        // 入力内容をStudentクラスに格納してIntent
        // EditTextの入力内容を取得するためインスタンス化
        EditText etStudentNum = findViewById(R.id.studentNum);
        EditText etClassNum = findViewById(R.id.className);
        EditText etStudentName = findViewById(R.id.studentName);

        // Studentクラスを生成、インスタンス化
        Student student = new Student();

        // StudentNum, className, studentNameの値をEditTextの値でセットする
        student.setStudentNum(Integer.parseInt(etStudentNum.getText().toString()));
        student.setClassName(etClassNum.getText().toString());
        student.setStudentName(etStudentName.getText().toString());

        Intent intent = new Intent(MainActivity.this,SubActivity.class);
        intent.putExtra("student",student);
        startActivity(intent);
    }
}
