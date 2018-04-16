package jack.org.project.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jack.org.project.model.User;
import jack.org.project.model.Book;
import jack.org.project.model.Comment;
import jack.org.project.model.Role;
import jack.org.project.repository.UserRepository;
import jack.org.project.repository.BookRepository;
import jack.org.project.repository.RoleRepository;
import jack.org.project.service.UserService;

@Transactional
@Service
public class testData {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private BookRepository bookRepository;
	@Autowired 
	private BookService bookService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void Init() {

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		Set<GrantedAuthority> grantedAuthoritiesAdmin = new HashSet<>();
		grantedAuthoritiesAdmin.add(new SimpleGrantedAuthority(roleAdmin.getName()));

		Role roleCustomer = new Role();
		roleCustomer.setName("ROLE_CUSTOMER");
		roleRepository.save(roleCustomer);

		Set<GrantedAuthority> grantedAuthoritiesCustomer = new HashSet<>();
		grantedAuthoritiesCustomer.add(new SimpleGrantedAuthority(roleCustomer.getName()));

		User user = new User();
		String password = "test";
		user.setUsername("jack");
		user.setPassword(bCryptPasswordEncoder.encode(password));

		HashSet<Role> roles = new HashSet<>();
		roles.add(roleCustomer);
		user.setRoles(roles);
		userRepository.save(user);

		User user1 = new User();
		String password1 = "test";
		user1.setUsername("admin");
		user1.setPassword(bCryptPasswordEncoder.encode(password1));

		HashSet<Role> roles1 = new HashSet<>();
		roles1.add(roleAdmin);
		user1.setRoles(roles1);
		userRepository.save(user1);
		
		Book book = new Book();
		book.setTitle("Harry Potter");
		book.setAuthor("J.K. Rowling");
		book.setGenre("Fantasy");
		book.setPrice(12);
		book.setStockLevel(20);
		bookService.save(book);
		
		Comment comment = new Comment();
		comment.setContent("This is a comment");

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		comment.setTimestamp(timestamp);
		commentService.save(comment, user, book);

	}
}