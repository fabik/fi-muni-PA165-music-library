package cz.fi.muni.pa165.musiclibrary.web.forms;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Iva Liberova
 */
public class AlbumDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AlbumDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AlbumDTO albumCreateDTO = (AlbumDTO) target;
        if (albumCreateDTO.getTitle() == null) return;
    }
}
