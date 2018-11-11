package process_communication.huige.com.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class BitmapMemoryTestActivity extends BaseActivity {
//    private static final String TAG = "BitmapMemoryActivity";
    ImageView ivBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_memory_test);
        context=this;
        Log.i(TAG, "30 dp truns px=" + dip2px(context, 30));

    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    public void initView(){

    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
