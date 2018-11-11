package process_communication.huige.com.myapplication.mvp;

import android.util.Log;

import java.util.List;

/**
 * Created by Shinelon on 2018/11/11.
 */

public class goodPresenter extends BasePresenter<goodViewInterface> {
    goodViewInterface mGoodViewInterface;
    goodModel mGoodModel=new goodModel();
    goodApi mGoodApi=new goodApi();

    public goodPresenter(goodViewInterface goodViewInterface){
        Log.i("TAG", "goodPresenter is created");
        mGoodViewInterface=goodViewInterface;
    }
    //模拟从网络获取数据
    public void getGoods(){
        mGoodViewInterface.showProgress();
        mGoodApi.fetchGoodList(new DataListener<List<good>>() {
            @Override
            public void onComplete(List<good> goods) {
                mGoodModel.saveGoodList(goods);
            }
        });

    }
    public void loadGoodsListFromDb(){
        mGoodViewInterface.hideProgress();
        mGoodViewInterface.showGoodsList(mGoodModel.getGoodList());

    }

}
