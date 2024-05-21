package project_library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import project_library.controllers.utils.RESTError;
import project_library.enteties.IssueEntity;
import project_library.services.BookService;
import project_library.services.IssueService;

@RestController
@RequestMapping(path = "/api/library/issues")
public class IssueController {

	@Autowired
	private IssueService issueService;

	@Transactional
	@RequestMapping(method = RequestMethod.POST, path = "/newIssue/{bookId}/{userId}")
	public ResponseEntity<?> issueBook(@RequestBody IssueEntity newIssue, @PathVariable Integer bookId,
			@PathVariable Integer userId) {
		try {
			issueService.issueBook(newIssue, bookId, userId);
			return new ResponseEntity<>("Books successfully issued", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occurred: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

}
