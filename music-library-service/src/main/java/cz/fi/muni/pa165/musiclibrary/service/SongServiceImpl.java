package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.SongDao;
import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import cz.fi.muni.pa165.musiclibrary.utils.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author David
 */
@Service
public class SongServiceImpl implements SongService {

	private final SongDao songDao;

	@Autowired
	public SongServiceImpl(SongDao songDao) {
		this.songDao = songDao;
	}

	@Override
	public Song findById(Long id) {
		return songDao.findById(id);
	}

	@Override
	public Song create(Song song) {
		songDao.create(song);
		return song;
	}

	@Override
	public void remove(Song song) {
		songDao.delete(song);
	}

	@Override
	public List<Song> findAll() {
		return songDao.findAll();
	}

	@Override
	public List<Song> findByMusician(Musician musician) {
		return songDao.findByMusician(musician);
	}

	@Override
	public List<Song> findByAlbum(Album album) {
		return songDao.findByAlbum(album);
	}

	@Override
	public List<Song> findByGenre(Genre genre) {
		return songDao.findByGenre(genre);
	}

	@Override
	public List<Song> findByTitle(String query) {
		List<String> patterns = SearchHelper.splitSearchQuery(query);
		return songDao.findByTitle(patterns);
	}

}
