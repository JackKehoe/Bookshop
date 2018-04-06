package jack.org.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import jack.org.project.model.Book;
import jack.org.project.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	List<Comment> findByBook(Book book);

}