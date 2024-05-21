package project_library.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import project_library.enteties.IssueEntity;

public interface IssueService {
	public ResponseEntity<?> issueBook(IssueEntity reservation, Integer bookId, Integer userId);
}
