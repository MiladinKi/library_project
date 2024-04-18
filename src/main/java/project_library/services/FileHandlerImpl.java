package project_library.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project_library.controllers.UploadController;

@Service
public class FileHandlerImpl implements FileHandler {

	public static String UPLOADED_FOLDER = "D:\\Programiranje\\sts\\library_project\\upload\\";
	private final Logger logger = LoggerFactory.getLogger(UploadController.class);
//	public static String UPLOADED_FOLDER = "D:/Programiranje/sts/library_project/upload/";


	@Override
	public String singleFileUpload(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}
		try {

			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");
		} catch (IOException e) {
			e.getMessage();
		}
		return "redirect:/uploadStatus";
	}

}
