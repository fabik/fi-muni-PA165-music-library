package cz.fi.muni.pa165.musiclibrary.facade;

import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import cz.fi.muni.pa165.musiclibrary.dto.SongCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.SongDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author David
 */
@Service
public interface SongFacade {

	void create(SongCreateDTO song);

	void delete(Long id);

	SongDTO findById(Long id);

	List<SongDTO> findByMusician(MusicianDTO musician);

	List<SongDTO> findByGenre(GenreDTO genre);

	List<SongDTO> findByTitle(String titlePattern);

	List<SongDTO> findAll();
}
