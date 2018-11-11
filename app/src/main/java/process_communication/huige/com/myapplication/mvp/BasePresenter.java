package process_communication.huige.com.myapplication.mvp;

import android.util.Log;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Shinelon on 2018/11/9.
 */

public abstract class BasePresenter<T> {
    protected Reference<T> mViewRef;

    public void attachView(T view){
        Log.i("TAG", "attachView");
        mViewRef = new WeakReference<T>(view);
    }
    public T getView(){
        return mViewRef.get();
    }
    public boolean isViewAttached(){
        return mViewRef!=null&&mViewRef.get()!=null;
    }
    public void detachView(){
        if(mViewRef!=null){
            mViewRef.clear();
            mViewRef=null;
        }
    }
}
