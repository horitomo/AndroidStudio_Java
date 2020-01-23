package hal.ac.jp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    // ここに書くとフィールドにとなり、すべてのメソッド
    // から参照が可能になる

    // 計算する前の数値を入れる変数
    private  int tempNum;

    private  String symbol;

    // 演算記号を判定するフラグの変数
    private  boolean opFlag = false;

    // 式を入れる変数
    private  String formula;

    // dotの判定するフラグの変数
    private  boolean dotFlag = false;
    private  int dotMultple = 10;

    // 計算する前の実数を入れる変数
    private  float tempFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button[] numBtn = new Button[10];

        numBtn[0] = find
        numBtn[1] = findViewById(R. id. one);
        numBtn[2] = findViewById(R. id. two);
        numBtn[3] = findViewById(R. id. three);
        numBtn[4] = findViewById(R. id. four);
        numBtn[5] = findViewById(R. id. five);
        numBtn[6] = findViewById(R. id. six);
        numBtn[7] = findViewById(R. id. seven);
        numBtn[8] = findViewById(R. id. eight);
        numBtn[9] = findViewById(R. id. nine);

        for (int i = 0; i < numBtn.length;i++){
            numBtn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ディスプレイに数字を出力
                    // viewを引数にして表示メソッドに渡す

                    numClicked(v);
                }
            });
        }

    }
}
