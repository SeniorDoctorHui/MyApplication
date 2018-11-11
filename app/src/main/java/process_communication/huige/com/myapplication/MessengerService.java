package process_communication.huige.com.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Stack;

/**
 * Created by Shinelon on 2018/10/15.
 */

public class MessengerService extends Service {
    private static final String TAG = "MessengerService";
    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Stack<Integer> stack=new Stack<>();
            super.handleMessage(msg);
            switch (msg.what){
                case MyConstants.MSG_FROM_CLIENT:
                    Log.i(TAG, "receive msg from Client: " + msg.getData().getString("msg"));
                    //要获取对方的Messenger，才能往对方发消息
                    Messenger client=msg.replyTo;
                    Message replyMessage = Message.obtain(null, MyConstants.MSG_FROM_SEVER);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply","好的，您的消息我已经收到，稍后在回复您！");
                    Log.i(TAG, Thread.currentThread().getName());
                    replyMessage.setData(bundle);
                    try {
                        client.send(replyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
