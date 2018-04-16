package jack.org.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import jack.org.project.model.Book;
import jack.org.project.model.PurchaseHistory;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByPurchaseHistory(PurchaseHistory purchaseHistory);

}
