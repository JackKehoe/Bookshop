package jack.org.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import jack.org.project.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	
}
