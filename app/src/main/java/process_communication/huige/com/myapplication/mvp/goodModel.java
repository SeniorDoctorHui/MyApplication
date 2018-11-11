package process_communication.huige.com.myapplication.mvp;

import java.util.List;

/**
 * Created by Shinelon on 2018/11/11.
 */

public class goodModel {
    List<good> goodList;

    public void saveGoodList(List<good> goodList){
       this.goodList=goodList;
    }
    public List<good> getGoodList(){
        return goodList;
    }
}
