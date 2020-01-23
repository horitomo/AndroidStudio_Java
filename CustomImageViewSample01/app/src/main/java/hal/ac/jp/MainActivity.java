package hal.ac.jp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Customクラス
    CustomImageView civ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Customクラスのインスタンス化
        civ = findViewById(R.id.customImageView);

        // 初期化メソッドを作成する
        init();

        civ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected();
            }
        });

        Button clearBtn = findViewById(R.id.button);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();;
            }
        });

    }

    private  void selected(){
        // 今の画像がblankだったら「○」にする
        if(civ.getResId() == R.drawable.blank){
            civ.setImageResource(R.drawable.maru);
        }else {
            Toast.makeText(MainActivity.this,"2",Toast.LENGTH_SHORT).show();;
        }
    }

    private  void init(){
        // 空白画像の設定する
        civ.setImageResource(R.drawable.blank);
    }
}
