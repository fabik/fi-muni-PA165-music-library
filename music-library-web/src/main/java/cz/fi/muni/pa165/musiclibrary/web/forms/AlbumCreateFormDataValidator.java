package cz.fi.muni.pa165.musiclibrary.web.forms;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Iva Liberova
 * @author Jan-Sebastian Fabik
 */
public class AlbumCreateFormDataValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AlbumCreateFormData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AlbumCreateFormData albumData = (AlbumCreateFormData) target;

		if (albumData.getTitle() == null || albumData.getTitle().isEmpty()) {
			errors.rejectValue("title", "AlbumCreateFormValidator.title.required");
		}

		if (!albumData.isReleaseDateFilled()) {
			errors.rejectValue("releaseDate", "AlbumCreateFormValidator.releaseDate.required");
		}

		if (!albumData.isReleaseDateValid()) {
			errors.rejectValue("releaseDate", "AlbumCreateFormValidator.releaseDate.invalid");
		}

		if (albumData.getAlbumArt() == null || albumData.getAlbumArt().length == 0) {
			errors.rejectValue("albumArt", "AlbumCreateFormValidator.albumArt.required");
		}
	}
}
