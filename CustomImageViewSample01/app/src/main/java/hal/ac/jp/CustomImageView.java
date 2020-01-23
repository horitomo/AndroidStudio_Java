package hal.ac.jp;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class CustomImageView extends AppCompatImageView {
    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 描画リソースの保存用
    private int resId;
    // setImageResource()をオーバライド

    @Override
    public void setImageResource(int resId) {
        // resIdを保存しておく
        this.resId = resId;
        super.setImageResource(resId);
    }

    public int getResId() {
        return resId;
    }
}
