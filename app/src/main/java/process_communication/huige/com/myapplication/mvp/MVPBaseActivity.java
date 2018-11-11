package process_communication.huige.com.myapplication.mvp;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Shinelon on 2018/11/10.
 */

public abstract class MVPBaseActivity<V,T extends BasePresenter<V>> extends Activity {
    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();

}
