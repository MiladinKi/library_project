package project_library.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import project_library.controllers.utils.RESTError;
import project_library.enteties.BookEntity;
import project_library.enteties.IssueEntity;
import project_library.enteties.UserEntity;
import project_library.repositories.BookRepository;
import project_library.repositories.IssueRepository;
import project_library.repositories.UserRepository;

@Service
public class IssueServiceImpl implements IssueService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private UserRepository userRepository;

//	@Override
//	public ResponseEntity<?> issueBook(IssueEntity reservation, Integer bookId, Integer userId) {
//		// Pronađite knjigu po ID-u iz baze podataka
//		try {
//			BookEntity book = bookRepository.findById(bookId).get();
//			if (book == null) {
//				return new ResponseEntity<>(new RESTError(1, "Book not found"), HttpStatus.NOT_FOUND);
//			}
//
//			Integer availableCopies = book.getNumberOfCopies();
//			if (availableCopies > 0) {
//				// Pronađite korisnika po ID-u iz baze podataka
//				UserEntity user = userRepository.findById(userId).get();
//				if (user == null) {
//					return new ResponseEntity<>(new RESTError(2, "User not found"), HttpStatus.NOT_FOUND);
//				}
//
//				// Smanjite broj dostupnih kopija za jedan
//				book.setNumberOfCopies(availableCopies - 1);
//
//				// Kreirajte novi entitet za izdavanje knjige
//				IssueEntity issue = new IssueEntity();
//				issue.setDateOfIssue(LocalDate.now());
//				issue.setNumberOfReservationDays(reservation.getNumberOfReservationDays());
//				issue.setReturnDate(LocalDate.now().plusDays(reservation.getNumberOfReservationDays()));
//
//				// Postavite korisnika koji uzima knjigu
//				issue.setUser(user);
//
//				// Dodajte knjigu u entitet izdavanja
//				issue.getBooks().add(book);
//
//				// Sačuvajte izdavanje knjige i ažurirajte knjigu u repozitorijumima
//				issueRepository.save(issue);
//				book.setReservation(issue);
//				bookRepository.save(book);
//				return new ResponseEntity<>(book, HttpStatus.OK);
//			} else {
//				throw new IllegalStateException("No copies available for the book with ID: " + bookId);
//			}
//		} catch (Exception e) {
//			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
//					HttpStatus.BAD_REQUEST);
//		}
//	}
	
	@Override
	public ResponseEntity<?> issueBook(IssueEntity reservation, Integer bookId, Integer userId) {
	    try {
	        BookEntity book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));

	        Integer availableCopies = book.getNumberOfCopies();
	        if (availableCopies > 0) {
	            UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

	            // Isključite validaciju prilikom ažuriranja knjige
	            book.setNumberOfCopies(availableCopies - 1);

	            IssueEntity issue = new IssueEntity();
	            issue.setDateOfIssue(LocalDate.now());
	            issue.setNumberOfReservationDays(reservation.getNumberOfReservationDays());
	            issue.setReturnDate(LocalDate.now().plusDays(reservation.getNumberOfReservationDays()));
	            issue.setUser(user);
	            issue.getBooks().add(book);

	            issueRepository.save(issue);
	            book.setReservation(issue);
	            bookRepository.save(book);
	            return new ResponseEntity<>("Book successfully issued", HttpStatus.CREATED);
	        } else {
	            throw new IllegalStateException("No copies available for the book with ID: " + bookId);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(new RESTError(1, "Exception occurred: " + e.getMessage()), HttpStatus.BAD_REQUEST);
	    }
	}


}
