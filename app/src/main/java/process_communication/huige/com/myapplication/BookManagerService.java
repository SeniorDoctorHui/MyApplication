package process_communication.huige.com.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import process_communication.huige.com.myapplication.aidl.IBookManager;

/**
 * Created by Shinelon on 2018/10/18.
 */

public class BookManagerService extends Service {
    private static final String TAG = "BMS";
    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList = new CopyOnWriteArrayList<>();
    private Binder mBinder=new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            Log.i(TAG, Thread.currentThread().getName());
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            if(!mListenerList.contains(listener)){
                mListenerList.add(listener);
            }else{
                Log.i(TAG, "listener is already registered!");
            }
            Log.i(TAG, "registerListener, size:" + mListenerList.size());

        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            if(mListenerList.contains(listener)){
                mListenerList.remove(listener);
            }else{
                Log.i(TAG, "no found,listener can not unregister.");
            }
            Log.i(TAG, "unregisterListener, size:" + mListenerList.size());

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "Ios"));
        new Thread(new ServiceWorker()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed.set(true);
        super.onDestroy();
    }

    private void onNewBookArrived(Book book) throws RemoteException{
        mBookList.add(book);
        Log.i(TAG, "onNewBookArrived, notify listeners:" + mListenerList.size());
        for(int i=0;i<mListenerList.size();i++){
            IOnNewBookArrivedListener listener = mListenerList.get(i);
            Log.i(TAG, "onNewBookArrived,notify listner:" + listener);
            listener.onNewBookArrived(book);
        }
    }

    private class ServiceWorker implements Runnable{

        @Override
        public void run() {
            while(!mIsServiceDestoryed.get()){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId=mBookList.size()+1;
                Book newBook = new Book(bookId, "new book#" + bookId);
                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
