package jack.org.project.model;

import java.util.List;
import java.util.Set;
import jack.org.project.model.Role;
import javax.persistence.*;


@Entity
@Table(name = "user")
public class User {
	
	private Long id;
	private boolean admin = false;
	private String username;
	private String email;
	private String password;
	private String passwordConfirm;
	private String firstName;
	private String lastName;
	private String address;
	private String debitCard;
	private Set<Role> roles;
	
	private List<Comment> comments;
	private List<Book> booksInCart;
	private List<Book> booksPurchased;
	private List<PurchaseHistory> purchaseHistory;
	
	public User() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDebitCard() {
		return debitCard;
	}

	public void setDebitCard(String debitCard) {
		this.debitCard = debitCard;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_cart", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
	public List<Book> getBooksInCart() {
		return booksInCart;
	}

	public void setBooksInCart(List<Book> booksInCart) {
		this.booksInCart = booksInCart;
	}
	
	@OneToMany(mappedBy = "purchasedBy", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	public List<Book> getBooksPurchased() {
		return booksPurchased;
	}

	public void setBooksPurchased(List<Book> booksPurchased) {
		this.booksPurchased = booksPurchased;
	}
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	public List<PurchaseHistory> getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(List<PurchaseHistory> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

}