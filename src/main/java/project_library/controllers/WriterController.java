package project_library.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import project_library.controllers.utils.RESTError;
import project_library.enteties.WriterEntity;
import project_library.repositories.WriterRepository;

@RestController
@RequestMapping(path = "/api/library/writers")
public class WriterController {

	@Autowired
	private WriterRepository writerRepository;

	private final Logger logger = LoggerFactory.getLogger(WriterController.class);

	@RequestMapping(method = RequestMethod.POST, path = "/newWriter")
	public ResponseEntity<?> addWriter(@Valid @RequestBody WriterEntity newWriter) {
		try {
			WriterEntity writer = new WriterEntity();
			writer.setName(newWriter.getName());
			writer.setLastname(newWriter.getLastname());
			writer.setBiography(newWriter.getBiography());
			writerRepository.save(writer);
			logger.info("New writer is created");
			return new ResponseEntity<>(writer, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("You have an error while create a new writer!");
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.GET, path = "/allWriters")
	public ResponseEntity<?> allWriters() {
		try {
			logger.info("You got a list of all writers");
			return new ResponseEntity<>(writerRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("You have an error while get a list of writers!");
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/byId/{writerId}")
	public ResponseEntity<?> writerById(@PathVariable Integer writerId) {
		try {
			WriterEntity writer = writerRepository.findById(writerId).get();
			if (writer == null) {
				return new ResponseEntity<>(new RESTError(1, "Writer is not found"), HttpStatus.BAD_REQUEST);
			}
			logger.info("You got a data of writer");
			return new ResponseEntity<>(writer, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("You have an error while get writer!");
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/changeWriterById/{writerId}")
	public ResponseEntity<?> modifyWriter(@RequestBody WriterEntity updatedWriter, @PathVariable Integer writerId) {
		WriterEntity writer = writerRepository.findById(writerId).get();
		try {
			if (writer == null) {
				return new ResponseEntity<>(new RESTError(1, "Writer is not found"), HttpStatus.BAD_REQUEST);
			}
			if (updatedWriter.getName() != null) {
				writer.setName(updatedWriter.getName());
			}
			if (updatedWriter.getLastname() != null) {
				writer.setLastname(updatedWriter.getLastname());
			}
			if (updatedWriter.getBiography() != null) {
				writer.setBiography(updatedWriter.getBiography());
			}
			writerRepository.save(writer);
			logger.info("You are modify a writer");
			return new ResponseEntity<>(writer, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("You have an error while modifing writer!");
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/deleteById/{writerId}")
	public ResponseEntity<?> deleteById(@PathVariable Integer writerId) {

		try {
			WriterEntity writer = writerRepository.findById(writerId).get();
			if (writer == null) {
				return new ResponseEntity<>(new RESTError(1, "Writer is not found"), HttpStatus.BAD_REQUEST);
			}
			writerRepository.delete(writer);
			logger.info("Writer is deleted from DB");
			return new ResponseEntity<>(writer, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("You have an error while deleting writer!");
			return new ResponseEntity<>(new RESTError(2, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/byName/{name}")
	public ResponseEntity<?> findByName(@PathVariable String name) {
		try {
			List<WriterEntity> writers = writerRepository.findByName(name);
			if (writers == null) {
				return new ResponseEntity<>(new RESTError(1, "Writers are not found"), HttpStatus.BAD_REQUEST);
			}
			logger.info("This is list of all writers with that name");
			return new ResponseEntity<>(writers, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("You have an error while getting writers with that name!");
			return new ResponseEntity<>(new RESTError(2, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/byLastname/{lastname}")
	public ResponseEntity<?> findByLastname(@PathVariable String lastname) {
		try {
			List<WriterEntity> writers = writerRepository.findByLastname(lastname);
			if (writers == null) {
				return new ResponseEntity<>(new RESTError(1, "Writers are not found"), HttpStatus.BAD_REQUEST);
			}
			logger.info("This is list of all writers with that lastname");
			return new ResponseEntity<>(writers, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("You have an error while getting writers with that lastname!");
			return new ResponseEntity<>(new RESTError(2, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
}
