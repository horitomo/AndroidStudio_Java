package jp.ac.hal;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class CustomImageView extends AppCompatImageView {
    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // リソースIDの保存先
    private int resId;

    @Override
    public void setImageResource(int resId) {
        this.resId = resId;
        super.setImageResource(resId);
    }

    public int getResId(){
        return resId;
    }
}
