package process_communication.huige.com.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import process_communication.huige.com.myapplication.aidl.IBookManager;

public class BookManagerActivity extends Activity {
    private static final String TAG = "BookManagerActivity";
    private static final int MESSAGE_NEW_BOOK_ARRIVED=1;

    private IBookManager mRemoteBookManager;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.i(TAG, "receive  new book :" + msg.obj);
                    break;

            }
        }
    };


    //Activity和Service建立连接成功会回调onServiceConnected的方法  此时BookManagerService和Activity绑定，若Activity销毁，则Service也会停止运行
    private ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            try {
                mRemoteBookManager=bookManager;
                List<Book> list = bookManager.getBookList();
                Log.i(TAG, Thread.currentThread().getName());
                Log.i(TAG, "query book list,list type:" + list.getClass().getCanonicalName());
                Log.i(TAG, "query book list:" + list.toString());
                Book newBook = new Book(3, "Android开发艺术探索");
                bookManager.addBook(newBook);
                List<Book> newList=bookManager.getBookList();
                Log.i(TAG, "query book newList:" + newList.toString());
                bookManager.registerListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }
    IOnNewBookArrivedListener mOnNewBookArrivedListener=new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook).sendToTarget();
        }
    };

    @Override
    protected void onDestroy() {
        if(mRemoteBookManager!=null&&mRemoteBookManager.asBinder().isBinderAlive()){
            try {
                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
                Log.i(TAG, "unregister listener:" + mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mConnection);
        super.onDestroy();
    }
}
