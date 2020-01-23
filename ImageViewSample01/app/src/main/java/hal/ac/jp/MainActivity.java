package hal.ac.jp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // フィールドの宣言
    ImageView iv;

    // クリックフラグ宣言
    boolean clickFlag ;
    Integer id;
    String msg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Imageviewでインスタンス化
        iv = findViewById(R. id. imageView);

        // 初期設定の画像を設定する
        iv.setImageResource(R.drawable.akagi2019);

        // 画像(iv)がクリックされたら・・・
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChange();
            }
        });
    }

    private  void imageChange(){
        //  画像を切り替える
        if(!clickFlag) {
            id = R.drawable.no;
            msg = "アンサーチェック";
        }else if(clickFlag){
            id = R.drawable.akagi2019;
            // Toastでメッセージをだす
            msg = "アカギからの提案です";
        }
        iv.setImageResource(id);
        
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();;
        // フラグの反転
        clickFlag = !clickFlag;
    }
}
