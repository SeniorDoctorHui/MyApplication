// IBookManager.aidl
package process_communication.huige.com.myapplication.aidl;

// Declare any non-default types here with import statements
import process_communication.huige.com.myapplication.Book;

interface IBookManager {
  List<Book> getBookList();
  void addBook(in Book book);
}
