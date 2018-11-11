package process_communication.huige.com.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Shinelon on 2018/10/27.
 */

public class BaseActivity extends AppCompatActivity {
    protected Context context;
    protected String TAG;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        TAG=getClass().getSimpleName();
        initView();
    }
    public void initView(){

    }
}
