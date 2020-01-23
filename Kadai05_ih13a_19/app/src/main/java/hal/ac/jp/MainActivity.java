package hal.ac.jp;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String msg = "";
    int masterid = 0;
    int masterPid =0;
    int winCnt = 0;
    int loseCnt = 0;
    int drawCnt = 0;
    String history = "結果：";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 自分の手を選択する画像のインスタンス化
        ImageView[] player = new ImageView[3];
        player[0] = findViewById(R.id.imageView2);
        player[1] = findViewById(R.id.imageView3);
        player[2] = findViewById(R.id.imageView4);

        // クリックしたらじゃんけん！
        for(int i = 0; i < player.length; i++){
            player[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // どれが押されたか判定
                    viewClick(v);
                }
            });
        }

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear(v);
            }
        });
    }

    public void viewClick(View v){
        // ImageViewにキャストする
        ImageView piv = (ImageView)v;

        // 商品のIDを取得する
        int pid = piv.getId();

        // switch
        switch (pid) {
            case R.id.imageView2:
                msg = "グー";
                break;
            case R.id.imageView3:
                msg = "ちょき";
                break;
            case R.id.imageView4:
                msg = "パー";
                break;
             default:
                    msg = "どれでもない";
        }
//        if(pid == R.id.imageView2){
//            msg = "グー";
//        }else if(pid == R.id.imageView3){
//            msg = "ちょき";
//        }else if( pid == R.id.imageView4){
//            msg = "パー";
//        }
        Toast.makeText(MainActivity.this,"あなたの手は"+msg+"です",Toast.LENGTH_SHORT).show();
        // コンピューターの手作成
        random();
        ImageView iv = findViewById(R.id.imageView);
        ImageView aaa = new ImageView(this);
        iv.setImageResource(masterPid);
        Toast.makeText(MainActivity.this,"コンピューターの手は"+msg+"です",Toast.LENGTH_SHORT).show();

        // 判定する結果を返す
        int judge = judge(pid,masterid);
        String resultmsg = "";
        switch (judge){
            case 0:
                resultmsg = "あなたの勝ちです";
                winCnt += 1;
                history = history + "勝";
                TextView historyArea = findViewById(R.id.historyArea);
                historyArea.setText(history);
                TextView tv = findViewById(R.id.winCnt);
                tv.setText(String.valueOf(winCnt));
                break;
            case 1:
                resultmsg = "あなたの負けです";
                loseCnt += 1;
                history = history + "敗";
                TextView historyArea1 = findViewById(R.id.historyArea);
                historyArea1.setText(history);
                TextView tv1 = findViewById(R.id.defeatCnt);
                tv1.setText(String.valueOf(loseCnt));
                break;
            case 2:
                resultmsg = "引き分けでした";
                drawCnt += 1;
                history = history + "引";
                TextView historyArea2 = findViewById(R.id.historyArea);
                historyArea2.setText(history);
                TextView tv2 = findViewById(R.id.drawCnt);
                tv2.setText(String.valueOf(drawCnt));
                break;
        }
        Toast.makeText(MainActivity.this,resultmsg,Toast.LENGTH_SHORT).show();
    }

    public  void random(){
        Random random = new Random();
        int randomValue = random.nextInt(3);
        switch (randomValue){
            case 0:
                masterid = R.id.imageView2;
                masterPid = R.drawable.rock;
                msg = "グー";
                break;
            case 1:
                masterid = R.id.imageView3;
                masterPid = R.drawable.scissors;
                msg = "ちょき";
                break;
            case 2:
                masterid = R.id.imageView4;
                masterPid = R.drawable.paper;
                msg = "パー";
                break;
        }

    }

    public int judge(int player, int master){
        int result = 0;

        // 判定
        if(player == master){
            result = 2;
        }else {
            switch (player){
                case R.id.imageView2:
                    if(master == R.id.imageView3){
                        result = 0;
                    }else if(master == R.id.imageView4){
                        result = 1;
                    }
                    break;
                case R.id.imageView3:
                    if(master == R.id.imageView2){
                        result = 1;
                    }else if(master == R.id.imageView4){
                        result = 0;
                    }
                    break;
                case R.id.imageView4:
                    if(master == R.id.imageView2){
                        result = 0;
                    }else if(master == R.id.imageView3){
                        result = 1;
                    }
                    break;
            }
        }
        return result;
    }

    private void clear(View v){
        msg = "";
        masterid = 0;
        masterPid =0;
        winCnt = 0;
        loseCnt = 0;
        drawCnt = 0;
        TextView tv = findViewById(R.id.winCnt);
        tv.setText(String.valueOf(winCnt));
        TextView tv1 = findViewById(R.id.defeatCnt);
        tv1.setText(String.valueOf(loseCnt));
        TextView tv2 = findViewById(R.id.drawCnt);
        tv2.setText(String.valueOf(drawCnt));
        TextView historyArea = findViewById(R.id.historyArea);
        historyArea.setText("結果：");
    }
}
