package jack.org.project.service;

import java.util.List;

import jack.org.project.model.Book;
import jack.org.project.model.PurchaseHistory;
import jack.org.project.model.User;

public interface BookService {
	
	Book findById(Long id);
	void save(Book book);
	List<Book> findAll();
	void addBookToCart(Book book, User user);
	List<Book> findByPurchaseHistory(PurchaseHistory purchaseHistory);
}
