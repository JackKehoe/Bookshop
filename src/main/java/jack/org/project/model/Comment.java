package jack.org.project.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

	private Long commentId;
	private String content;
	private Date timestamp;
	private User user;
	private Book book;

	public Comment() {

	}

	public Comment(String content, Date timestamp, User user) {
		super();
		this.content = content;
		this.timestamp = timestamp;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return commentId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public void setId(Long commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id")
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}