package jack.org.project.service;

import java.util.List;

import jack.org.project.model.User;
import jack.org.project.model.Book;
import jack.org.project.model.Comment;

public interface CommentService {
	
	 void save(Comment comment, User user, Book book);

	List<Comment> findByBook(Book book);

}
