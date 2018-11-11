package process_communication.huige.com.myapplication.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.List;

import process_communication.huige.com.myapplication.R;

public class GoodActivity extends MVPBaseActivity<goodViewInterface,goodPresenter> implements goodViewInterface{
    String TAG="GoodActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);
        initView();
    }
    public void initView(){
        Log.i(TAG, "mPresenter=" + mPresenter);
        mPresenter.getGoods();
        mPresenter.loadGoodsListFromDb();
    }

    @Override
    public void showProgress() {
        Log.i(TAG, "showProgress");

    }

    @Override
    public void hideProgress() {
        Log.i(TAG, "hideProgress");
    }

    @Override
    public void showGoodsList(List<good> goodList) {
        for(int i=0;i<goodList.size();i++){
            good good = goodList.get(i);
            Log.i(TAG, "good.name=" + good.getProname());
        }
    }

    @Override
    protected goodPresenter createPresenter() {
        mPresenter = new goodPresenter(this);
        return mPresenter;
    }
}
