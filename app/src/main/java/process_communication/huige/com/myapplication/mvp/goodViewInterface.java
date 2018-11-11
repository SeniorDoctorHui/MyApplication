package process_communication.huige.com.myapplication.mvp;

import java.util.List;

/**
 * Created by Shinelon on 2018/11/11.
 */

public interface goodViewInterface {
    public void showProgress();

    public void hideProgress();

    public void showGoodsList(List<good> goodList);
}
