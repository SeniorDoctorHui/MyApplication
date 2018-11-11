package process_communication.huige.com.myapplication;

import android.util.Log;

/**
 * Created by Shinelon on 2018/10/24.
 */

public class Outter {

    int l=3;
    public Outter(){
        l=new Inner().j;
        Log.i("TAG","l="+l);

    }
    class Inner{
      private  int j=7;
    }
}
