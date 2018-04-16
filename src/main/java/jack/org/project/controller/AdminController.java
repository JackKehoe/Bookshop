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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jack.org.project.model.Book;
import jack.org.project.model.Comment;
import jack.org.project.model.User;
import jack.org.project.service.BookService;
import jack.org.project.service.CommentService;
import jack.org.project.service.SecurityService;
import jack.org.project.service.UserService;
import jack.org.project.validator.UserValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private BookService bookService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String homepage(Model model, Principal p) {
		List<Book> bookList = bookService.findAll();

		Collections.sort(bookList, (p1, p2) -> p1.getAuthor().compareTo(p2.getAuthor()));
		System.out.println(bookList);

		model.addAttribute("currentUser", userService.findByUsername(p.getName()));
		model.addAttribute("bookList", bookList);

		return "homepage";
	}

	@RequestMapping(value = "/homepage/{sort}", method = RequestMethod.GET)
	public String homepage(@PathVariable String sort, Model model, Principal p) {

		List<Book> bookList = bookService.findAll();

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

		model.addAttribute("currentUser", userService.findByUsername(p.getName()));
		model.addAttribute("bookList", bookList);

		return "homepage";
	}

	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String get(Model model, Principal p) {

		model.addAttribute("currentUser", userService.findByUsername(p.getName()));
		model.addAttribute("bookForm", new Book());

		return "addbook";
	}

	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String add(@ModelAttribute("bookForm") Book book, Model model, Principal p)
			throws IllegalStateException, IOException {

		bookService.save(book);

		return "redirect:/admin/homepage";
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public String getBook(@PathVariable Long id, Model model, Principal p) {
		
		model.addAttribute("commentForm", new Comment());
		model.addAttribute("book", bookService.findById(id));
		
		return "book";
	}

	@RequestMapping(value = "/book/edit/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("bookForm") Book book, Model model, Principal p) {
		book.setImage(bookService.findById(book.getId()).getImage());

		System.out.println(book.getImage());
		bookService.save(book);

		return "editbook";

	}

	@RequestMapping(value = "/comment/{id}", method = RequestMethod.POST)
	public String postComment(@PathVariable Long id, Book book, @ModelAttribute("commentForm") Comment commentForm,
			BindingResult bindingResult, Model model, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "book";
		}
		book = bookService.findById(id);
		String name = principal.getName();
		User cust = userService.findByUsername(name);

		bookService.save(book);
		commentService.save(commentForm, cust, book);

		return "redirect:/comment/{postTextId}";
	}

	
	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public String search(@RequestParam("searchString") String searchString, Model model, Principal principal) {
		List<Book> allList = bookService.findAll();
		List<Book> searchList = new ArrayList<>();
		
		for (Book book : allList) {
			if (book.getTitle().toLowerCase().contains(searchString.toLowerCase()) || book.getAuthor().toLowerCase().contains(searchString.toLowerCase()) || book.getGenre().toLowerCase().equals(searchString.toLowerCase())) {
			searchList.add(book);
			}
		}
		
		model.addAttribute("bookList", searchList);
		
		return "homepage";
	}

}