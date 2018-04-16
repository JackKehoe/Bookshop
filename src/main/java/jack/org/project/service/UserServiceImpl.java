package jack.org.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import jack.org.project.model.Book;
import jack.org.project.model.Role;
import jack.org.project.model.User;
import jack.org.project.repository.RoleRepository;
import jack.org.project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BookService bookService;
	@Autowired
	private PurchaseHistoryService purchaseHistoryService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		if (user.isAdmin() == true) {
			Role admin = roleRepository.findByName("ROLE_ADMIN");
			Set<Role> adminRoles = new HashSet<>();
			adminRoles.add(admin);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setRoles(adminRoles);
			userRepository.save(user);
		}
		else {
			Role cust = roleRepository.findByName("ROLE_CUSTOMER");
			Set<Role> custRoles = new HashSet<>();
			custRoles.add(cust);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setRoles(custRoles);
			user.setComments(user.getComments());
			user.setDebitCard(user.getDebitCard());
			user.setAddress(user.getAddress());
			userRepository.save(user);
		}

	}

	@Override
	public void update(User userForm, User user) {
		if (user.isAdmin() == true) {
			user.setPassword(user.getPassword());
			Role admin = roleRepository.findByName("ROLE_ADMIN");
			Set<Role> adminRoles = new HashSet<>();
			adminRoles.add(admin);
			user.setRoles(adminRoles);
			userRepository.save(user);
		} 
		else {
			user.setPassword(user.getPassword());
			Role cust = roleRepository.findByName("ROLE_CUSTOMER");
			Set<Role> custRoles = new HashSet<>();
			custRoles.add(cust);
			user.setRoles(custRoles);
			user.setComments(user.getComments());
			user.setDebitCard(userForm.getDebitCard());
			user.setAddress(userForm.getAddress());
			user.setFirstName(userForm.getFirstName());
			user.setLastName(userForm.getLastName());
			user.setPurchaseHistory(user.getPurchaseHistory());
			userRepository.save(user);
		}

	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findUser(long id) {
		return userRepository.getOne(id);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	@Override
	public void checkout(User user) {
		user.getBooksPurchased().addAll(user.getBooksInCart());
		user.getBooksInCart().clear();
		List<Book> books = user.getBooksPurchased();
		
		for(Book book : books) {
			int stock=book.getStockLevel() - 1;
			book.setStockLevel(stock);
			bookService.save(book);
	
		}
	}

}
