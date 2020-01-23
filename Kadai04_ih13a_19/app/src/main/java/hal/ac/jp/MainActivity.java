package hal.ac.jp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

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
    private  double tempDouble;

    private  boolean plusminusFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button[] numBtn = new Button[10];

        numBtn[0] = findViewById(R. id.zero);
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

        // 演算ボタンが押されたときの処理
        // 加減乗除をまとめて記述する
        Button[] opSymbol = new Button[4];

        //インスタンス化
        opSymbol[0] = findViewById(R. id.plus);
        opSymbol[1] = findViewById(R. id.minus);
        opSymbol[2] = findViewById(R. id.multiplied);
        opSymbol[3] = findViewById(R. id.divided);

        // 演算クリック時の処理
        for(int i = 0; i < opSymbol.length; i++){
            opSymbol[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    opClicked(v);
                }
            });
        }

        Button equalSymbol = findViewById(R.id.equal);

        // インスタンス化
        equalSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalClicked(v);
            }
        });

        // クリアボタン判定
        Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClicked(v);
            }
        });

        // ドット判定
        Button dotBtn = findViewById(R.id.dot);
        dotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dotClicked(v);
            }
        });

        // ±ボタン
        Button plusMinus = findViewById(R.id.plusminus);
        plusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusMinusClicked(v);
            }
        });

        // backspace判定
        final Button backspace = findViewById(R.id.backspace);
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspaceClicked(v);
            }
        });

    }

    public  void numClicked(View v){
        // 何のウィジェット（部品）が押されたのか？を調べる
        // View → Button にキャストする
        Button btn = (Button) v;

        // ボタンに書かれている文字を取得する
        String str = btn.getText().toString();

        // これまでに入力されている内容と連結する
        // ここでは数値に変換して連結します
        /*
             ①現在表示されている値を取得する
             ②　①を数値(int)に変換する
             ③　②を１０倍する
             ④　③にボタン表示文字の数値を加算する
             ⑤　④を表示する
         */

        // フラグがfalseかtrueかで連結するかどうか判断する
        if(!opFlag) {
            if(dotFlag){
                TextView tv = findViewById(R.id.outputArea);
                String outputD = tv.getText().toString();
                Double nextDouble = Double.parseDouble(str) /dotMultple;
                if(plusminusFlag){
                    nextDouble = nextDouble * -1;
                }
                Double prevDouble = Double.parseDouble(outputD);

                Double outputDouble = nextDouble + prevDouble;

                tv.setText(String.valueOf(outputDouble));

                // 小数点のぶん動かす
                dotMultple = dotMultple * 10;

            }else {


                // ①
                TextView tv = findViewById(R.id.outputArea);
                String output = tv.getText().toString();

                // ②
                Integer nextNum = Integer.parseInt(str);
                if(plusminusFlag){
                    nextNum = nextNum * -1;
                }

                // ③
                Integer prevNum = Integer.parseInt(output) * 10;

                // ④
                Integer outputNum = prevNum + nextNum;

                // ⑤
                tv.setText(String.valueOf(outputNum));
            }
        }else {
            // 演算記号のあとだから
            TextView tv = findViewById(R.id.outputArea);
            tv.setText(str);
            opFlag = false;
            dotMultple = 10;
        }
    }

    public  void opClicked(View v){
        Button opBtn = (Button) v;
        symbol = opBtn.getText().toString();

        // 今outputAreaにある数値を取得する
        TextView tv = findViewById(R. id.outputArea);
        String str = tv.getText().toString();

        // フィールドとして保存する　→　下に記述する
        // フィールドの記述方法はどのメソッドにも入らない場所に記述する。
        // public class MainActivity(){の下
        if(dotFlag){
            tempDouble = Double.parseDouble(str);
            formula = String.valueOf(tempDouble) + symbol;
        }else {
            tempNum = Integer.parseInt(str);
            formula = String.valueOf(tempNum) + symbol;
        }

        TextView tv1 = findViewById(R.id.textView3);
        tv1.setText(formula);

        // 直前に押されたのは演算記号だよのフラグを立てる
        opFlag = true;  // フィールドにも宣言しておく
        plusminusFlag = false;
    }

    public  void equalClicked(View v){
        // 今outputAreaにある数値を取得する
        TextView tv = findViewById(R. id.outputArea);
        String str = tv.getText().toString();

        if(dotFlag){
            Double culNum = Double.parseDouble(str);

            formula = formula + String.valueOf(culNum);
            TextView tv1 = findViewById(R.id.textView3);
            tv1.setText(formula);


            if (symbol.equals("＋") == true) {
                BigDecimal prevNumBD = BigDecimal.valueOf(tempDouble);
                BigDecimal nextNumBD = BigDecimal.valueOf(culNum);
                BigDecimal addBD= prevNumBD.add(nextNumBD);
                Double val = addBD.doubleValue();
                tv.setText(String.valueOf(val));
            } else if (symbol.equals("ー") == true) {
                BigDecimal prevNumBD = BigDecimal.valueOf(tempDouble);
                BigDecimal nextNumBD = BigDecimal.valueOf(culNum);
                BigDecimal subtractBD = prevNumBD.subtract(nextNumBD);
                Double val = subtractBD.doubleValue();
                tv.setText(String.valueOf(val));
            } else if (symbol.equals("×") == true) {
                BigDecimal prevNumBD = BigDecimal.valueOf(tempDouble);
                BigDecimal nextNumBD = BigDecimal.valueOf(culNum);
                BigDecimal multiplyBD = prevNumBD.multiply(nextNumBD);
                Double val = multiplyBD.doubleValue();
                tv.setText(String.valueOf(val));
            } else if (symbol.equals("÷") == true) {
                BigDecimal prevNumBD = BigDecimal.valueOf(tempDouble);
                BigDecimal nextNumBD = BigDecimal.valueOf(culNum);
                BigDecimal divideBD = prevNumBD.divide(nextNumBD,5, BigDecimal.ROUND_HALF_UP);
                Double val = divideBD.doubleValue();
                tv.setText(String.valueOf(val));
            }

        }else {


            Integer culNum = Integer.parseInt(str);

            formula = formula + String.valueOf(culNum);
            TextView tv1 = findViewById(R.id.textView3);
            tv1.setText(formula);


            if (symbol.equals("＋") == true) {
                int result = tempNum + culNum;
                tv.setText(String.valueOf(result));
            } else if (symbol.equals("ー") == true) {
                int result = tempNum - culNum;
                tv.setText(String.valueOf(result));
            } else if (symbol.equals("×") == true) {
                int result = tempNum * culNum;
                tv.setText(String.valueOf(result));
            } else if (symbol.equals("÷") == true) {
                if(tempNum < culNum){
                    // 結果が実数になるように変更
                    BigDecimal prevNumBD = BigDecimal.valueOf(tempNum);
                    BigDecimal nextNumBD = BigDecimal.valueOf(culNum);
                    BigDecimal divide = prevNumBD.divide(nextNumBD,5, BigDecimal.ROUND_HALF_UP);
                    Double val = divide.doubleValue();
                    tv.setText(String.valueOf(val));

                }else if((tempNum % culNum) != 0){
                    // あまりがあるとき実数化
                    BigDecimal prevNumBD = BigDecimal.valueOf(tempNum);
                    BigDecimal nextNumBD = BigDecimal.valueOf(culNum);
                    BigDecimal divide = prevNumBD.divide(nextNumBD,5, BigDecimal.ROUND_HALF_UP);
                    Double val = divide.doubleValue();
                    tv.setText(String.valueOf(val));
                }else {
                    int result = tempNum / culNum;
                    tv.setText(String.valueOf(result));
                }
            }
        }
        //tv.setText(symbol);
    }

    // Clear　変数から表示領域も初期化
    public  void clearClicked(View v){
        tempNum = 0;
        symbol = "";
        opFlag = false;
        formula = "";
        dotFlag = false;
        TextView tv = findViewById(R.id.outputArea);
        tv.setText(String.valueOf(tempNum));
        TextView tv1 = findViewById(R.id.textView3);
        tv1.setText(formula);
        dotMultple = 10;
        plusminusFlag = false;
    }

    public  void dotClicked(View v){
        // ドットが押されたのでフラグをtrueに
        dotFlag = true;
        // 符号を押したあとだったら計算前の数値を実数に
        if(tempNum != 0){
            String str = String.valueOf(tempNum);
            tempDouble = Double.parseDouble(str);
        }
    }

    public  void  plusMinusClicked(View v){
        TextView tv = findViewById(R. id.outputArea);
        String str = tv.getText().toString();

        // 実数の場合とintの場合を分ける
        if(dotFlag){
            Double plusminusVal = -Double.parseDouble(str);
            tv.setText(String.valueOf(plusminusVal));
        }else {
            Integer plusminusVal = -Integer.parseInt(str);
            tv.setText(String.valueOf(plusminusVal));
        }
        // フラグを二回押されたときにtrueとfalseを変える
        if(!plusminusFlag){
            plusminusFlag = true;
        }else if(plusminusFlag){
            plusminusFlag = false;
        }
    }

    // backspace
    public  void backspaceClicked(View v){
        TextView tv = findViewById(R. id.outputArea);
        String str = tv.getText().toString();

        // 文字の長さを取得
        Integer strleng = str.length() - 1;

        //文字の最後を削除
        String str1= str.substring(0,strleng);
        if(str1.equals("")){
            // 空なら０を入れる
            str1 = "0";
        }else {
            // 最後の文字がドットならドットも消す
            String last = str1.substring(strleng - 1);
            if (last.equals(".")) {
                str1 = str.substring(0, strleng - 1);
                dotFlag = false;
                dotMultple = 10;
            }
        }

        tv.setText(str1);
    }
}