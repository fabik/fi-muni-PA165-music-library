package cz.fi.muni.pa165.musiclibrary.web.controllers.rest;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.InvalidParameterException;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.ResourceAlreadyExistsException;
import cz.fi.muni.pa165.musiclibrary.web.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for administering albums.
 *
 * @author Iva Liberova
 */
@Controller
@RestController
@RequestMapping("/rest/albums")
public class AlbumRestController {

	final static Logger log = LoggerFactory.getLogger(AlbumRestController.class);

	@Autowired
	private AlbumFacade albumFacade;

	/**
	 * Get list of Albums
	 * curl -i -X GET http://localhost:8080/pa165/rest/albums
	 *
	 * @return AlbumDTO
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<AlbumDTO> findAll() {
		log.debug("findAll()");
		return albumFacade.findAll();
	}

	/**
	 * Get Album by identifier id
	 * curl -i -X GET http://localhost:8080/pa165/rest/albums/1
	 *
	 * @param id identifier for a album
	 * @return AlbumDTO
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final AlbumDTO find(@PathVariable("id") long id) throws Exception {
		log.debug("find({})", id);

		try {
			AlbumDTO albumDTO = albumFacade.findById(id);
			return albumDTO;
		} catch (Exception ex) {
			throw new ResourceNotFoundException();
		}
	}

	/**
	 * Delete one album by id
	 * curl -i -X DELETE http://localhost:8080/pa165/rest/albums/1
	 *
	 * @param id identifier for album
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public final void delete(@PathVariable("id") long id) throws Exception {
		log.debug("delete({})", id);
		try {
			albumFacade.delete(id);
		} catch (Exception ex) {
			throw new ResourceNotFoundException();
		}
	}

	/**
	 * Create a new album by POST method
	 * <p>
	 * curl -X POST -i -H "Content-Type: application/json" --data '{"title":"test"}' http://localhost:8080/pa165/rest/albums/create
	 *
	 * @param album AlbumCreateDTO with required fields for creation
	 * @return void
	 * @throws ResourceAlreadyExistsException
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public final AlbumDTO create(@RequestBody AlbumCreateDTO album) throws Exception {

		log.debug("create({})", album);

		try {
			Long id = albumFacade.create(album);
			return albumFacade.findById(id);
		} catch (Exception ex) {
			throw new ResourceAlreadyExistsException();
		}
	}

	/**
	 * Update an album with the given ID by PUT method
	 * curl -X PUT -i -H "Content-Type: application/json" --data '{"title":"UpdatedName"}' http://localhost:8080/pa165/rest/albums/1
	 *
	 * @param id    identified of the album to be updated
	 * @param album required fields as specified in AlbumDTO
	 * @return the updated album AlbumDTO
	 * @throws InvalidParameterException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public final AlbumDTO update(@PathVariable("id") long id, @RequestBody AlbumDTO album) throws Exception {

		log.debug("update({}, {})", id, album);

		if (albumFacade.findById(id) == null) {
			throw new ResourceNotFoundException();
		}

		try {
			album.setId(id);
			albumFacade.update(album);
			return albumFacade.findById(id);
		} catch (Exception ex) {
			throw new ResourceAlreadyExistsException();
		}
	}

	@RequestMapping(value = "/by_musician/{musician_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<AlbumDTO> findByMusician(@PathVariable("musician_id") long musicianId) {
		return albumFacade.findByMusician(musicianId);
	}

	@RequestMapping(value = "/by_genre/{genre_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<AlbumDTO> findByGenre(@PathVariable("genre_id") long genreId) {
		return albumFacade.findByGenre(genreId);
	}

	@RequestMapping(value = "/by_title", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<AlbumDTO> findByTitle(@RequestParam("title") String title) {
		return albumFacade.findByTitleLike(title);
	}
}
