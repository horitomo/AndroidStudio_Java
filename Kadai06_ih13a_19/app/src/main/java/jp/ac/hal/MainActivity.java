package jp.ac.hal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CustomImageView[][] civ = new CustomImageView[3][3];

    // どっちのターン？
    private boolean isMaru = true;
    String winP = "";
    private  boolean end = false;
    private int gameCnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        civ[0][0] = findViewById(R.id.customImageView0);
        civ[0][1] = findViewById(R.id.customImageView1);
        civ[0][2] = findViewById(R.id.customImageView2);
        civ[1][0] = findViewById(R.id.customImageView3);
        civ[1][1] = findViewById(R.id.customImageView4);
        civ[1][2] = findViewById(R.id.customImageView5);
        civ[2][0] = findViewById(R.id.customImageView6);
        civ[2][1] = findViewById(R.id.customImageView7);
        civ[2][2] = findViewById(R.id.customImageView8);

        init(civ);

        for(int i =0; i < civ.length ;i++){
            for(int j = 0; j < civ[i].length ;j++) {
                civ[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imageClick(v);
                    }
                });
            }
        }

        Button resetBtn = findViewById(R.id.reset);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init(civ);
            }
        });
    }

    private  void imageClick(View v){
        gameCnt++;
        CustomImageView civC = (CustomImageView)v;
        if(civC.getResId() == R.drawable.blank && !end) {
            if(isMaru) {
                civC.setImageResource(R.drawable.maru);
            }else {
                civC.setImageResource(R.drawable.batu);
            }
            isMaru = !isMaru;
        }else if(end || gameCnt >=9){
            Toast.makeText(MainActivity.this,"ゲーム終了",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this,"もう入ってるよ",Toast.LENGTH_SHORT).show();;
        }


            judge(civ);
            TextView tv = findViewById(R.id.resultArea);
            if (!winP.equals("")) {
                end = true;
                if (winP.equals("○")) {
                    tv.setText("○の勝");
                } else {
                    tv.setText("×の勝");
                }
            }
            if (gameCnt == 9) {
                tv.setText("引き分けでした。");
            }

    }

    private  void init(CustomImageView[][] civ){
        // 空白画像の設定する
        for(int i =0; i < civ.length ;i++){
            for(int j = 0; j < civ[i].length ;j++) {
                civ[i][j].setImageResource(R.drawable.blank);
            }
        }
        winP = "";
        isMaru = true;
        end = false;
        TextView tv = findViewById(R.id.resultArea);
        tv.setText("");
        gameCnt = 0;
    }

    private void judge(CustomImageView[][] civ){
        int yoko ;
        int tate;

        // 横の判定
        for(int i =0; i < 3 ;i++){
            yoko = 0;
            for(int j = 0; j < 3 ;j++) {
                if(civ[i][j].getResId() == R.drawable.maru){
                    yoko += 1;
                }else if(civ[i][j].getResId() == R.drawable.batu) {
                    yoko -= 1;
                }

                if(yoko == 3){
                    winP = "○";
                    return;
                }else if(yoko == -3){
                    winP = "×";
                    return;
                }
            }
        }

        // 縦の判定
        for(int i =0; i < 3 ;i++){
            tate = 0;
            for(int j = 0; j < 3 ;j++) {
                if(civ[j][i].getResId() == R.drawable.maru){
                    tate += 1;
                }else if(civ[j][i].getResId() == R.drawable.batu) {
                    tate -= 1;
                }

                if(tate== 3){
                    winP = "○";
                    return;
                }else if(tate == -3){
                    winP = "×";
                    return;
                }
            }
        }
        int naname = 0;
        // 斜め
        for(int i =0; i < civ.length; i++){
            if(civ[i][i].getResId() == R.drawable.maru){
                naname++;
            }else if(civ[i][i].getResId() == R.drawable.batu){
                naname--;
            }

            if(naname == 3){
                winP = "○";
                return;
            }else if(naname == -3){
                winP = "×";
                return;
            }
        }


        int naname1 = 0;
        for(int i =0; i < civ.length; i++){
            if(civ[i][2-i].getResId() == R.drawable.maru){
                naname1++;
            }else if(civ[i][2-i].getResId() == R.drawable.batu){
                naname1--;
            }

            if(naname1 == 3){
                winP = "○";
                return;
            }else if(naname1 == -3){
                winP = "×";
                return;
            }
        }
    }
}
