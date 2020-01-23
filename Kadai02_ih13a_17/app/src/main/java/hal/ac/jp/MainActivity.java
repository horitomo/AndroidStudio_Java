package hal.ac.jp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //アプリ起動時に実行されるメソッド
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //使う部品を紐づける（インスタンス化）
        //身長・体重の入力欄（EditText）
        final EditText etHeight = findViewById(R .id .hValue);
        final EditText etWeight = findViewById(R .id .wValue);
        //計算ボタン（Button）
        Button btCalc = findViewById(R .id .calculate);
        //計算結果の表示欄（TextView）
        final TextView tvResult = findViewById(R .id .resultText);

        //クリックイベントの取得
        btCalc. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //クリック時の処理を記述する
                //入力された身長と体重を取得する
                String str = etHeight.getText().toString();
                //取得した文字列を数値(float)に変換
                float height = Float.parseFloat(str);

                String str1 = etWeight.getText().toString();
                float weight = Float.parseFloat(str1);

                float changeHeight = height/100;
                float vheight = changeHeight*changeHeight;

                float resultBmi = weight/vheight;
                float ave = vheight*22;


                //例　結果表示欄のメッセージを変える
                if(resultBmi<18.5) {
                    tvResult.setText("BMI:" + String.format("%.2f", resultBmi)+"\n"+"標準体重"+String.format("%.2f",ave)+"\nあなたは低体重です");
                }else if(resultBmi<25) {
                    tvResult.setText("BMI:" + String.format("%.2f", resultBmi)+"\n"+"標準体重"+String.format("%.2f",ave)+"\nあなたは標準体重です");
                }else if(resultBmi<30) {
                    tvResult.setText("BMI:" + String.format("%.2f", resultBmi)+"\n"+"標準体重"+String.format("%.2f",ave)+"\nあなたは肥満（１度）です");
                }else if(resultBmi<35) {
                    tvResult.setText("BMI:" + String.format("%.2f", resultBmi)+"\n"+"標準体重"+String.format("%.2f",ave)+"\nあなたは肥満（２度）です");
                }else if(resultBmi<40) {
                    tvResult.setText("BMI:" + String.format("%.2f", resultBmi)+"\n"+"標準体重"+String.format("%.2f",ave)+"\nあなたは肥満（３度）です");
                }else if(resultBmi>=40){
                    tvResult.setText("BMI:" + String.format("%.2f", resultBmi)+"\n"+"標準体重"+String.format("%.2f",ave)+"\nあなたは肥満（４度）です");
                }else{
                    tvResult.setText("ERROR");
                }
            }
        });
    }
}
