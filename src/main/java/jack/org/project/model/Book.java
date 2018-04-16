package jack.org.project.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Book {
	
	private Long id;
	private String title;
	private String author;
	private String image;
	private String genre;
	private double price;
	private int stockLevel;
	
	private User purchasedBy;
	
	private List<Comment> comments;
	private List<User> carts;
	
	private PurchaseHistory purchaseHistory;

	public Book() {
		
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public int getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(int stockLevel) {
		this.stockLevel = stockLevel;
	}

	@OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@ManyToMany(mappedBy = "booksInCart", fetch = FetchType.EAGER)
	public List<User> getCarts() {
		return carts;
	}

	public void setCarts(List<User> carts) {
		this.carts = carts;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getPurchasedBy() {
		return purchasedBy;
	}

	public void setPurchasedBy(User purchasedBy) {
		this.purchasedBy = purchasedBy;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="purchaseHistory_id")
	public PurchaseHistory getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(PurchaseHistory purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

}
