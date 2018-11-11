package process_communication.huige.com.myapplication.mvp;

/**
 * Created by Shinelon on 2018/11/11.
 */

public interface DataListener<M> {
    void onComplete(M m);
}
