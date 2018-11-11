package process_communication.huige.com.myapplication.mvp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Shinelon on 2018/11/11.
 */

public class goodApi{
    public void fetchGoodList(DataListener<List<good>> m){
        String result="[{\"proname\":\"天天特价S925银渐变天鹅项链吊坠女纯银锁骨链韩版简约生日送女友\",\"price\":158,\"img\":\"img/y.jpg\",\"type\":\"饰品\"},\n" +
                "{\"proname\":\"羊剪绒外套短款女仿皮草大衣连帽皮毛一体狐狸毛领\",\"price\":120,\"img\":\"img/s1s.jpg\",\"type\":\"女装\"},\n" +
                "{\"proname\":\"星星的你同款皮草大衣女中长款狐狸毛内胆仿皮草外套\",\"price\":278,\"img\":\"img/s1.jpg\",\"type\":\"女装\"},\n" +
                "{\"proname\":\"天天特价男士棉服冬季韩版潮流面包服男款棉衣冬天袄子男冬装外套\",\"price\":88,\"img\":\"img/s1.jpg\",\"type\":\"男装\"},\n" +
                "{\"proname\":\"天天特价花花公子贵宾马甲男秋冬季外套羽绒棉背心休闲马夹坎肩潮\",\"price\":69,\"img\":\"img/s2.jpg\",\"type\":\"男装\"},\n" +
                "{\"proname\":\"天天特价马丁靴女秋冬女鞋2017新款短靴女百搭英伦风裸靴平底女靴\",\"price\":79,\"img\":\"img/s3.jpg\",\"type\":\"鞋包\"}\n" +
                "]";
        Gson gson=new Gson();
        List<good> goodList=gson.fromJson(result,new TypeToken<List<good>>(){}.getType());
        m.onComplete(goodList);
    }
}
