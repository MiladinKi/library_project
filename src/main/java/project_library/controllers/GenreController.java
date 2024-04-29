package project_library.controllers;

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
import jakarta.websocket.server.PathParam;
import project_library.controllers.utils.RESTError;
import project_library.enteties.GenreEntity;
import project_library.repositories.GenreRepository;

@RestController
@RequestMapping(path = "/api/library/genres")
public class GenreController {
	private final Logger logger = LoggerFactory.getLogger(GenreController.class);
	@Autowired
	private GenreRepository genreRepository;

	@RequestMapping(method = RequestMethod.POST, path = "/newGenre")
	public ResponseEntity<?> addGenre(@Valid@RequestBody GenreEntity newGenre) {

		try {
			GenreEntity genre = new GenreEntity();
			genre.setNameOfGenre(newGenre.getNameOfGenre());
			genre.setDescribeGenre(newGenre.getDescribeGenre());
			genreRepository.save(genre);
			logger.info("New genre is created");
			return new ResponseEntity<>(genre, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("You have an error while create a new genre!");
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/allGenres")
	public ResponseEntity<?> allGenres() {
		try {
			logger.info("You have a list og all genres");
			return new ResponseEntity<>(genreRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/modifyById/{genreId}")
	public ResponseEntity<?> modifyGenreByID(@PathVariable Integer genreId, @RequestBody GenreEntity updatedGenre) {
		GenreEntity genre = genreRepository.findById(genreId).get();
		try {
			if (genre == null) {
				return new ResponseEntity<>(new RESTError(1, "Genre not found"), HttpStatus.NOT_FOUND);
			}
			if (updatedGenre.getNameOfGenre() != null) {
				genre.setNameOfGenre(updatedGenre.getNameOfGenre());
			}
			if (updatedGenre.getDescribeGenre() != null) {
				genre.setDescribeGenre(updatedGenre.getDescribeGenre());
			}
			genreRepository.save(genre);
			logger.info("You are successfully modify genre!");
			return new ResponseEntity<>(genre, HttpStatus.OK);

		} catch (Exception e) {
			logger.warn("You have a problem with modifing user by ID");
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/deleteById/{genreId}")
	public ResponseEntity<?> deleteById(@PathVariable Integer genreId) {
		try {
			GenreEntity genre = genreRepository.findById(genreId).get();
			if (genre == null) {
				return new ResponseEntity<>(new RESTError(1, "Genre not found"), HttpStatus.NOT_FOUND);
			}
			genreRepository.delete(genre);
			logger.info("You are successfully delete genre by Id!");
			return new ResponseEntity<>(genre, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(2, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findById/{genreId}")
	public ResponseEntity<?> findById(@PathVariable Integer genreId) {
		try {
			GenreEntity genre = genreRepository.findById(genreId).get();
			if (genre == null) {
				return new ResponseEntity<>(new RESTError(1, "Genre not found"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(genre, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(2, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/nameOfGenre")
	public ResponseEntity<?> findByName(@RequestParam String nameOfGenre) {
		try {
			GenreEntity genre = genreRepository.findByNameOfGenre(nameOfGenre);
			if (genre == null) {
				return new ResponseEntity<>(new RESTError(1, "Genre not found"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(genre, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(2, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

}
