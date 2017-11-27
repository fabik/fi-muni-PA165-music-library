package cz.fi.muni.pa165.musiclibrary.service.facade;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.dto.SongDTO;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import cz.fi.muni.pa165.musiclibrary.facade.SongFacade;
import cz.fi.muni.pa165.musiclibrary.service.BeanMappingService;
import cz.fi.muni.pa165.musiclibrary.service.SongService;
import cz.fi.muni.pa165.musiclibrary.service.config.ServiceConfiguration;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author David
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SongFacadeImplTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private SongFacade songFacade;
    
    @PersistenceContext
    private EntityManager em;
    
    private AlbumDTO album;
    
    @Test
    public void testCreate() {
        album = new AlbumDTO();
        album.setTitle("album");
        em.persist(album);
        SongDTO songDTO = new SongDTO();
        songDTO.setTitle("Pisnicka");
        songDTO.setBitrate(120);
        songDTO.setPosition(2);
        songDTO.setAlbum(album);

        songFacade.create(songDTO);

        List<Song> songs = em.createQuery("SELECT s FROM Song s", Song.class).getResultList();
        Assert.assertEquals(1, songs.size());
        Assert.assertEquals("Pisnicka", songs.get(0).getTitle());
    }
    
}
