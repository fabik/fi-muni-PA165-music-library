package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * @author Kovarik Tomas
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SongDaoImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SongDao songDao;

	@Autowired
	private MusicianDao musicianDao;

	@Autowired
	private AlbumDao albumDao;

	@Autowired
	private GenreDao genreDao;


	public static final int SONGS_COUNT = 2;

	private Song song1;

	private Song song2;

	private Musician m1;

	private Genre g1;

	@BeforeMethod
	public void init() {

		Musician m = new Musician();
		Album a = new Album();
		Genre g = new Genre();
		song1 = new Song();

		m.setName("Musician 01");
		musicianDao.create(m);

		g.setName("Genre 01");
		genreDao.create(g);

		a.setTitle("Album 01");
		a.setReleaseDate(new Date());
		a.setCommentary("Comentary for album");
		albumDao.create(a);

		song1 = new Song();
		song1.setAlbum(a);
		a.addSong(song1);
		song1.setBitrate(1000);
		song1.setCommentary("Sample comentary");
		song1.setGenre(g);
		song1.setMusician(m);
		song1.setPosition(1);
		song1.setTitle("Song 01");
		songDao.create(song1);

		song2 = new Song();
		song2.setAlbum(a);
		a.addSong(song2);
		song2.setBitrate(1000);
		song2.setCommentary("Sample comentary 2");
		song2.setGenre(g);
		song2.setMusician(m);
		song2.setPosition(1);
		song2.setTitle("Song 02");
		songDao.create(song2);
	}

	@Test
	public void testCreateSong() {
		Musician m = new Musician();
		Album a = new Album();
		Genre g = new Genre();

		m.setName("Musician 03");
		musicianDao.create(m);

		g.setName("Genre 03");
		genreDao.create(g);

		a.setTitle("Album 03");
		a.setReleaseDate(new Date());
		a.setCommentary("Comentary for album");
		albumDao.create(a);


		Song song = new Song();
		song.setAlbum(a);
		a.addSong(song);
		song.setBitrate(1000);
		song.setCommentary("Sample comentary 3");
		song.setGenre(g);
		song.setMusician(m);
		song.setPosition(1);
		song.setTitle("Song 03");
		songDao.create(song);


		Assert.assertEquals(SONGS_COUNT + 1, songDao.findAll().size());
		Assert.assertSame(song, songDao.findByTitle(song.getTitle()).get(0));
		songDao.delete(song);
	}


	@Test(expectedExceptions = IllegalArgumentException.class,
			expectedExceptionsMessageRegExp = "Argument song must not be null.")
	public void testCreateWithNullSong() {
		songDao.create(null);
	}


	@Test(expectedExceptions = IllegalArgumentException.class,
			expectedExceptionsMessageRegExp = "The given song already exists.")
	public void testCreateExistingSong() {
		songDao.create(song1);
	}

	@Test
	public void testFindAllSong() {
		Assert.assertEquals(SONGS_COUNT, songDao.findAll().size());
	}

	@Test
	public void testFindById() {
		Assert.assertEquals(song1, songDao.findById(song1.getId()));
	}

	@Test(expectedExceptions = IllegalArgumentException.class,
			expectedExceptionsMessageRegExp = "Argument id must not be null.")
	public void testFindByIdNullId() {
		songDao.findById(null);
	}

	@Test
	public void testFindByTitle() {
		Assert.assertEquals(song1, songDao.findByTitle("Song 01").get(0));
	}

	@Test(expectedExceptions = IllegalArgumentException.class,
			expectedExceptionsMessageRegExp = "Argument titlePattern must not be null.")
	public void testFindByTitleNullTitle() {
		songDao.findByTitle(null);
	}

	@Test
	public void testUpdate() {
		m1 = new Musician();
		Album a = new Album();
		g1 = new Genre();

		m1.setName("Musician 02");
		musicianDao.create(m1);
		g1.setName("Genre 02");
		genreDao.create(g1);
		a.setTitle("Album 02");
		a.setReleaseDate(new Date());
		a.setCommentary("Comentary for album 2");
		albumDao.create(a);
		song1.setAlbum(a);
		a.addSong(song1);
		song1.setTitle("Edited song name");
		song1.setBitrate(100);
		song1.setCommentary("Sample comentary 1");
		song1.setMusician(m1);
		song1.setGenre(g1);
		songDao.update(song1);

		Song edittedSong = songDao.findById(song1.getId());
		Assert.assertEquals(song1, edittedSong);
	}

	@Test(expectedExceptions = IllegalArgumentException.class,
			expectedExceptionsMessageRegExp = "The given song does not exist.")
	public void testUpdateNotExistingSong() {
		Musician m = new Musician();
		Album a = new Album();
		Genre g = new Genre();

		m.setName("Musician 03");
		musicianDao.create(m);

		g.setName("Genre 03");
		genreDao.create(g);

		a.setTitle("Album 03");
		a.setReleaseDate(new Date());
		a.setCommentary("Comentary for album");
		albumDao.create(a);


		Song song = new Song();
		song.setAlbum(a);
		a.addSong(song);
		song.setBitrate(1000);
		song.setCommentary("Sample comentary 3");
		song.setGenre(g);
		song.setMusician(m);
		song.setPosition(1);
		song.setTitle("Song 03");
		songDao.update(song);
	}

	@Test(expectedExceptions = IllegalArgumentException.class,
			expectedExceptionsMessageRegExp = "Argument song must not be null.")
	public void testUpdateNullSong() {
		songDao.update(null);
	}

	@Test
	public void testFindByMusician() {
		testUpdate();
		Assert.assertEquals(1, songDao.findByMusician(m1).size());
		Assert.assertEquals(song1, songDao.findByMusician(m1).get(0));
	}

	@Test(expectedExceptions = IllegalArgumentException.class,
			expectedExceptionsMessageRegExp = "Argument musician must not be null.")
	public void testFindByMusicianNullMusician() {
		songDao.findByMusician(null);
	}

	@Test
	public void testFindByGenre() {
		testUpdate();
		Assert.assertEquals(1, songDao.findByGenre(g1).size());
		Assert.assertEquals(song1, songDao.findByGenre(g1).get(0));
	}

	@Test(expectedExceptions = IllegalArgumentException.class,
			expectedExceptionsMessageRegExp = "Argument genre must not be null.")
	public void testFindByGenreNullGenre() {
		songDao.findByGenre(null);
	}

	@Test
	public void testDelete() {
		songDao.delete(song2);
		Assert.assertEquals(1, songDao.findAll().size());
		Assert.assertEquals(song1, songDao.findAll().get(0));
	}

	@Test(expectedExceptions = IllegalArgumentException.class,
			expectedExceptionsMessageRegExp = "Argument song must not be null.")
	public void testDeleteNullSong() {
		songDao.delete(null);
	}
}
