package hal.ac.jp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // 値を受け取る
        int val = getIntent().getIntExtra("kazuaki",999);
        String str = getIntent().getStringExtra("sample");

        Toast.makeText(SubActivity.this,String.valueOf(val),Toast.LENGTH_SHORT).show();;
        Toast.makeText(SubActivity.this,str,Toast.LENGTH_SHORT).show();;

        ImageView iv = findViewById(R.id.imageView2);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 呼び出し元に戻る
                finish();
                //changeActivity();
            }
        });
    }

//    private void changeActivity(){
//        // Intentクラスのインスタンス化
//        Intent intent =  new Intent(SubActivity.this,MainActivity.class);
//        // Activityの遷移
//        startActivity(intent);
//
//
//        finishActivity(R.layout.activity_main);
//    }
}
