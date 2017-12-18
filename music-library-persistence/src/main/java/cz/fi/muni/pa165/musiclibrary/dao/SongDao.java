package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;

import java.util.List;

/**
 * SongsDao provides an abstract interface to a database of songs.
 *
 * @author Jan-Sebastian Fabík
 */
public interface SongDao {

	/**
	 * Adds the given song to the database.
	 *
	 * @param song the song
	 */
	void create(Song song);

	/**
	 * Updates the given song in the database.
	 *
	 * @param song the song
	 */
	void update(Song song);

	/**
	 * Deletes the given song from the database.
	 *
	 * @param song the song
	 */
	void delete(Song song);

	/**
	 * Gets a song with the given ID.
	 *
	 * @param id the song ID
	 * @return the song, or null if does not exist
	 */
	Song findById(Long id);

	/**
	 * Gets songs with the given musician.
	 *
	 * @param musician the musician
	 * @return the songs
	 */
	List<Song> findByMusician(Musician musician);

	/**
	 * Gets songs with the given album.
	 *
	 * @param album the album
	 * @return the songs
	 */
	List<Song> findByAlbum(Album album);

	/**
	 * Gets songs with the given genre.
	 *
	 * @param genre the genre
	 * @return the songs
	 */
	List<Song> findByGenre(Genre genre);

	/**
	 * Gets songs with a title matching all the given patterns.
	 *
	 * @param patterns the patterns
	 * @return the songs
	 */
	List<Song> findByTitle(List<String> patterns);

	/**
	 * Gets all songs.
	 *
	 * @return the songs
	 */
	List<Song> findAll();
}

