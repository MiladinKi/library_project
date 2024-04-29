package project_library.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import project_library.controllers.utils.RESTError;
import project_library.enteties.BookEntity;
import project_library.enteties.GenreEntity;
import project_library.enteties.WriterEntity;
import project_library.repositories.BookRepository;
import project_library.repositories.GenreRepository;
import project_library.repositories.WriterRepository;

@RestController
@RequestMapping(path = "/api/library/books")
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private WriterRepository writerRepository;
	@Autowired
	private GenreRepository genreRepository;

	private final Logger logger = LoggerFactory.getLogger(BookController.class);

	@RequestMapping(method = RequestMethod.POST, path = "/newBook/genre/{genreId}/byWriter/{writerId}")
	public ResponseEntity<?> addBook(@Valid @RequestBody BookEntity newBook, @PathVariable Integer genreId,
			@PathVariable Integer writerId) {

		try {
			GenreEntity genre = genreRepository.findById(genreId).get();
			List<WriterEntity> writers = (List<WriterEntity>) writerRepository
					.findAllById(Collections.singletonList(writerId));
			BookEntity book = new BookEntity();
			book.setName(newBook.getName());
			book.setYearOfPublication(newBook.getYearOfPublication());
			book.setSerialNumber(newBook.getSerialNumber());
			book.setNumberOfCopies(newBook.getNumberOfCopies());
			book.setGenre(genre);
			book.setWriters(writers);
			bookRepository.save(book);
			logger.info("You are add new book!");
			return new ResponseEntity<>(book, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("You have an error while create a new writer!");
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/allBooks")
	public ResponseEntity<?> getAllBooks() {
		try {
			List<BookEntity> books = (List<BookEntity>) bookRepository.findAll();
			logger.info("This is all books in library!");
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("You have an error while getting a all books!");
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/byId/{bookId}")
	public ResponseEntity<?> getById(@PathVariable Integer bookId) {
		try {
			BookEntity book = bookRepository.findById(bookId).orElse(null);
			if (book == null) {
				logger.info("There is no book with that ID");
				return new ResponseEntity<>(new RESTError(1, "Book not found"), HttpStatus.NOT_FOUND);
			}
			logger.info("This is book with that ID");
			return new ResponseEntity<>(book, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("You have an error while getting book with that ID!");
			return new ResponseEntity<>(new RESTError(2, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/modifyById/{bookId}")
	public ResponseEntity<?> modifyBookById(@RequestBody BookEntity updatedBook, @PathVariable Integer bookId) {
		BookEntity book = bookRepository.findById(bookId).orElse(null);
		try {
			if (book == null) {
				logger.info("There is no book with that ID");
				return new ResponseEntity<>(new RESTError(1, "Book not found"), HttpStatus.NOT_FOUND);
			}
			if (updatedBook.getName() != null) {
				book.setName(updatedBook.getName());
			}
			if (updatedBook.getYearOfPublication() != null) {
				book.setYearOfPublication(updatedBook.getYearOfPublication());
			}
			if (updatedBook.getSerialNumber() != null) {
				book.setSerialNumber(updatedBook.getSerialNumber());
			}
			if (updatedBook.getNumberOfCopies() != null) {
				book.setNumberOfCopies(updatedBook.getNumberOfCopies());
			}
			bookRepository.save(book);
			logger.info("You are modify book");
			return new ResponseEntity<>(book, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("You have an error while modifing book with that ID!");
			return new ResponseEntity<>(new RESTError(2, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/deleteById/{bookId}")
	public ResponseEntity<?> deleteById(@PathVariable Integer bookId) {
		BookEntity book = bookRepository.findById(bookId).orElse(null);
		try {
			if (book == null) {
				logger.info("There is no book with that ID");
				return new ResponseEntity<>(new RESTError(1, "Book not found"), HttpStatus.NOT_FOUND);
			}
			bookRepository.delete(book);
			logger.info("You are delete book");
			return new ResponseEntity<>(book, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("You have an error while deleting book with that ID!");
			return new ResponseEntity<>(new RESTError(2, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findByBookName")
	public ResponseEntity<?> findByName(@RequestParam(value = "book Name") String bookName) {
		List<BookEntity> books = bookRepository.findByName(bookName);
		try {
			if(books == null) {
				logger.info("There is no books with that name");
				return new ResponseEntity<>(new RESTError(1, "Books are not found"), HttpStatus.NOT_FOUND);
			}
			logger.info("This are books with that name");
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("You have an error while getting books with that name!");
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findByYearOfPublication")
	public ResponseEntity<?> findByYearOfPublication(@RequestParam(value = "year") String yearBook) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		LocalDate year = LocalDate.of(Integer.parseInt(yearBook), 1, 1);
		List<BookEntity> books = bookRepository.findByYearOfPublication(year);

		try {
			if(books == null) {
				logger.info("No one books is not publish in that year");
				return new ResponseEntity<>(new RESTError(1, "Books are not found"), HttpStatus.NOT_FOUND);
			}
			logger.info("This books are publish in that year");
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("You have an error while getting books witch is publish in that year!");
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findBySerialNumber/{serialNumber}")
	public ResponseEntity<?> findBySerialNumber(@PathVariable String serialNumber) {
		BookEntity book = bookRepository.findBySerialNumber(serialNumber);
		try {
			if(book == null) {
				logger.info("There is not book with that serial number!");
				return new ResponseEntity<>(new RESTError(1, "Book is not found"), HttpStatus.NOT_FOUND);
			}
			logger.info("This is book with that serial number!");
			return new ResponseEntity<>(book, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("You have an error while getting book with this serial number!");
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
}
