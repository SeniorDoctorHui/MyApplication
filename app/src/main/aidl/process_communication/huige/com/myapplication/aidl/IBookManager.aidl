// IBookManager.aidl
package process_communication.huige.com.myapplication.aidl;

// Declare any non-default types here with import statements
import process_communication.huige.com.myapplication.Book;
import process_communication.huige.com.myapplication.IOnNewBookArrivedListener;

interface IBookManager {
  List<Book> getBookList();
  void addBook(in Book book);
  void registerListener(IOnNewBookArrivedListener listener);
  void unregisterListener(IOnNewBookArrivedListener listener);
}
