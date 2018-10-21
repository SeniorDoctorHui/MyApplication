// IOnNewBookArrivedListener.aidl
package process_communication.huige.com.myapplication;

// Declare any non-default types here with import statements
import process_communication.huige.com.myapplication.Book;

interface IOnNewBookArrivedListener {
  void onNewBookArrived(in Book newBook);
}
