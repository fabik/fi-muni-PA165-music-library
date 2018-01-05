package cz.fi.muni.pa165.musiclibrary.service.facade;


import cz.fi.muni.pa165.musiclibrary.dto.GenreCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.facade.GenreFacade;
import cz.fi.muni.pa165.musiclibrary.service.BeanMappingService;
import cz.fi.muni.pa165.musiclibrary.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kovarik Tomas
 */
@Service
@Transactional
public class GenreFacadeImpl implements GenreFacade {

	@Autowired
	private GenreService genreService;

	@Autowired
	private BeanMappingService beanMappingService;

	@Override
	public Long create(GenreCreateDTO g) {
		Genre mappedGenre = beanMappingService.mapTo(g, Genre.class);
		//save genre
		Genre newGenre = genreService.create(mappedGenre);
		return newGenre.getId();
	}

	@Override
	public void delete(Long id) {
		genreService.delete(genreService.findById(id));
	}

	@Override
	public void update(GenreDTO genre) {
		genreService.update(beanMappingService.mapTo(genre, Genre.class));
	}

	@Override
	public GenreDTO findById(Long id) {
		Genre genre = genreService.findById(id);
		return (genre == null) ? null : beanMappingService.mapTo(genre, GenreDTO.class);
	}

	@Override
	public GenreDTO findByName(String name) {
		Genre genre = genreService.findByName(name);
		return (genre == null) ? null : beanMappingService.mapTo(genre, GenreDTO.class);
	}

	@Override
	public List<GenreDTO> findByNameLike(String query) {
		List<Genre> genres = genreService.findByNameLike(query);
		return (genres == null) ? null : beanMappingService.mapTo(genres, GenreDTO.class);
	}

	@Override
	public List<GenreDTO> findAll() {
		return beanMappingService.mapTo(genreService.findAll(), GenreDTO.class);
	}
}
