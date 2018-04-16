package jack.org.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jack.org.project.model.Book;
import jack.org.project.model.Comment;
import jack.org.project.model.PurchaseHistory;
import jack.org.project.model.User;
import jack.org.project.service.BookService;
import jack.org.project.service.CommentService;
import jack.org.project.service.PurchaseHistoryService;
import jack.org.project.service.SecurityService;
import jack.org.project.service.UserService;
import jack.org.project.validator.UserValidator;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private PurchaseHistoryService purchaseHistoryService;
	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String homepage(Model model, Principal p) {
		List<Book> bookList = bookService.findAll();
		User currentUser = userService.findByUsername(p.getName());

		int cartSize = currentUser.getBooksInCart().size();
		model.addAttribute("cartSize", cartSize);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("bookList", bookList);

		return "homepage";
	}

	@RequestMapping(value = "/homepage/{sort}", method = RequestMethod.GET)
	public String homepageSort(@PathVariable String sort, Model model, Principal p) {

		List<Book> bookList = bookService.findAll();
		int cartSize = userService.findByUsername(p.getName()).getBooksInCart().size();

		if (sort.equals("author")) {
			System.out.println(bookList);
			Collections.sort(bookList, (p1, p2) -> p1.getAuthor().compareTo(p2.getAuthor()));
			System.out.println(bookList);
		}
		if (sort.equals("category")) {
			System.out.println(bookList);
			Collections.sort(bookList, (p1, p2) -> p1.getGenre().compareTo(p2.getGenre()));
			System.out.println(bookList);
		}
		if (sort.equals("title")) {
			System.out.println(bookList);
			Collections.sort(bookList, (p1, p2) -> p1.getTitle().compareTo(p2.getTitle()));
			System.out.println(bookList);
		}

		model.addAttribute("cartSize", cartSize);
		model.addAttribute("currentUser", userService.findByUsername(p.getName()));
		model.addAttribute("bookList", bookList);

		return "homepage";
	}

	@RequestMapping(value = "/edituser", method = RequestMethod.GET)
	public String edituser(Model model, String error, Principal p) {
		String name = p.getName();
		User currentUser = userService.findByUsername(p.getName());
		int cartSize = currentUser.getBooksInCart().size();

		model.addAttribute("cartSize", cartSize);
		model.addAttribute("userForm", userService.findByUsername(name));
		model.addAttribute("currentUser", userService.findByUsername(name));

		return "edituser";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model,
			Principal principal) {
		String name = principal.getName();
		userService.update(userForm, userService.findByUsername(name));

		return "redirect:/customer/edituser";
	}

	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public String getBook(@PathVariable Long id, Model model, Principal p) {
		User currentUser = userService.findByUsername(p.getName());
		Book book = bookService.findById((long) 1);
		Comment commentForm = new Comment();
		commentForm.setId(null);

		List<Comment> comments = commentService.findByBook(book);
		int cartSize = currentUser.getBooksInCart().size();

		model.addAttribute("cartSize", cartSize);
		model.addAttribute("commentForm", commentForm);
		model.addAttribute("comments", commentService.findByBook(bookService.findById(id)));
		model.addAttribute("book", bookService.findById(id));

		return "book";
	}

	@RequestMapping(value = "/comment/{id}", method = RequestMethod.POST)
	public String postComment(@PathVariable Long id, Book book, @ModelAttribute("commentForm") Comment commentForm,
			BindingResult bindingResult, Model model, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "book";
		}

		String name = principal.getName();
		User cust = userService.findByUsername(name);
		book = bookService.findById(id);

		commentService.save(commentForm, cust, book);
		bookService.save(book);

		return "redirect:/customer/book/{id}";
	}

	@RequestMapping(value = "/book/addtocart/{id}", method = RequestMethod.POST)
	public String addToCart(@PathVariable Long id, Model model, Principal p) {

		Book book = bookService.findById(id);
		User currentUser = userService.findByUsername(p.getName());

		bookService.addBookToCart(book, currentUser);

		return "redirect:/customer/book/{id}";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cart(Model model, String error, Principal p) {
		User currentUser = userService.findByUsername(p.getName());
		List<Book> cartList = currentUser.getBooksInCart();
		int cartSize = currentUser.getBooksInCart().size();
		double total = 0;

		for (Book book : cartList) {
			total += book.getPrice();

		}

		model.addAttribute("total", total);

		model.addAttribute("cartSize", cartSize);
		model.addAttribute("cartList", cartList);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("total", total);

		return "cart";
	}

	@RequestMapping(value = "/cart/checkout", method = RequestMethod.GET)
	public String checkout(Model model, String error, Principal p) {
		User currentUser = userService.findByUsername(p.getName());
		List<Book> cartList = currentUser.getBooksInCart();
		int cartSize = currentUser.getBooksInCart().size();
		double total = 0;

		for (Book book : cartList) {
			total += book.getPrice();

		}

		model.addAttribute("total", total);

		model.addAttribute("cartSize", cartSize);
		model.addAttribute("cartList", cartList);
		model.addAttribute("userForm", currentUser);
		model.addAttribute("total", total);

		return "checkout";
	}

	@RequestMapping(value = "/cart/checkout", method = RequestMethod.POST)
	public String checkoutPost(@ModelAttribute User userForm, Model model, String error, Principal p) {
		User currentUser = userService.findByUsername(p.getName());
		userService.update(userForm, currentUser);
		userService.checkout(currentUser);

		return "redirect:/customer/cart";
	}

	@RequestMapping(value = { "/purchase/history" }, method = RequestMethod.GET)
	public String purchases(Model model, Principal principal) {
		User currentUser = userService.findByUsername(principal.getName());
		List<PurchaseHistory> purchases = purchaseHistoryService.findByUser(currentUser);
		int cartSize = currentUser.getBooksInCart().size();
		
		model.addAttribute("cartSize", cartSize);
		model.addAttribute("purchases", purchases);
		model.addAttribute("currentUser", currentUser);
		
		return "purchasehistory";
	}

	@RequestMapping(value = { "/purchase/{id}" }, method = RequestMethod.GET)
	public String purchase(@PathVariable Long id, Model model, Principal principal) {
		User currentUser = userService.findByUsername(principal.getName());
		PurchaseHistory purchases = purchaseHistoryService.findById(id);
		int cartSize = currentUser.getBooksInCart().size();
		List<Book> books = bookService.findByPurchaseHistory(purchases);
		
		model.addAttribute("cartSize", cartSize);
		model.addAttribute("purchases", purchases);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("books", books);
		
		return "purchase";
	}

	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public String search(@RequestParam("searchString") String searchString, Model model, Principal principal) {
		List<Book> books = bookService.findAll();
		List<Book> bookSearch = new ArrayList<>();
		User currentUser = userService.findByUsername(principal.getName());
		int cartSize = currentUser.getBooksInCart().size();

		for (Book book : books) {
			if (book.getTitle().toLowerCase().contains(searchString.toLowerCase())
					|| book.getAuthor().toLowerCase().contains(searchString.toLowerCase())
					|| book.getGenre().toLowerCase().contains(searchString.toLowerCase())) {
				bookSearch.add(book);
			}
		}

		model.addAttribute("cartSize", cartSize);
		model.addAttribute("bookList", bookSearch);

		return "homepage";
	}

}