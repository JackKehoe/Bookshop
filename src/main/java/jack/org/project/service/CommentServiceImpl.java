package jack.org.project.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jack.org.project.model.Book;
import jack.org.project.model.Comment;
import jack.org.project.model.User;
import jack.org.project.repository.CommentRepository;


@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
	
	@Override
    public void save(Comment comment, User user, Book book) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		comment.setId(null);
        comment.setContent(comment.getContent()); 
        comment.setTimestamp(timestamp);
		comment.setUser(user);
		comment.setBook(book);
        commentRepository.save(comment);
    }
	
	@Override
	public List<Comment> findByBook(Book book){
		return commentRepository.findByBook(book);
	}
	
}