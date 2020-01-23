package jp.ac.hal.kadai07_ih13a_19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    String str;
    Button btn2;
    String[][] quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quiz = new String[][]{{"「アホウドリ」の名前の由来は何でしょう？", "アホーと鳴くから", "人間にすぐ捕まるから", "あほみたいな顔をしているから", "阿波踊りをしているように飛ぶ姿から"},
                                {"お酢に卵を殻ごといれると卵はどうなるでしょう？", "透明な卵になる", "鏡のようになんでもうつる卵になる", "卵が溶けてなくなる", "卵が石のように堅くなる"},
                                {"しゃっくりはある調味料をなめると止まります。ある調味料とはなんでしょう？", "お酢", "砂糖", "醤油", "塩"}};
        
        Random random = new Random();
        final int randowValue = random.nextInt(3);
        TextView tv = findViewById(R.id.textView);
        tv.setText(quiz[randowValue][0]);
        RadioButton[] rb = new RadioButton[4];
        rb[0] = findViewById(R.id.radioButton);
        rb[1] = findViewById(R.id.radioButton2);
        rb[2] = findViewById(R.id.radioButton3);
        rb[3] = findViewById(R.id.radioButton4);
        for(int i = 0; i < 4 ;i++){
            rb[i].setText(quiz[randowValue][i+1]);
        }

        btn2 = findViewById(R.id.button2);

        RadioGroup rg = findViewById(R.id.rG);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                str = rb.getText().toString();
                btn2.setEnabled(true);
            }
        });


        if(str == null){
            btn2.setEnabled(false);
        }
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit(randowValue);
            }
        });



   }

    private void submit(int randowValue){
        Intent intent2 = new Intent(QuizActivity.this,AnsActivity.class);
        intent2.putExtra("ans",str);
        intent2.putExtra("quizNum",randowValue);
        startActivity(intent2);
    }
}
