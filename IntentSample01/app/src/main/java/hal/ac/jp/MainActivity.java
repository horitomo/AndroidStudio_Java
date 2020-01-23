package hal.ac.jp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ImageViewのインスタンス化
        ImageView iv = findViewById(R.id.imageView);

//        // Intentクラスのインスタンス化
//        Intent intent =  new Intent(MainActivity.this,SubActivity.class);
//        // Activityの遷移
//        startActivity(intent);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }

            private void changeActivity() {

                // Intentクラスのインスタンス化
                Intent intent =  new Intent(MainActivity.this,SubActivity.class);

                // 値をセットする
                intent.putExtra("kazuaki",19);
                intent.putExtra("sample","shimizu");

                // Activityの遷移
                startActivity(intent);
            }
        });

    }
}
