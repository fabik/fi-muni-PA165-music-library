package cz.fi.muni.pa165.musiclibrary.service;

import java.util.List;
import org.springframework.stereotype.Service;

import cz.fi.muni.pa165.musiclibrary.entity.Genre;

/**
 * @author Kovarik Tomas
 */

@Service
public interface GenreService {
	public Genre findById(Long id);
	public List<Genre> findByPattern(String pattern);
	public List<Genre> findAll();
	public Genre createGenre(Genre g);
	public void deleteGenre(Genre g);
}