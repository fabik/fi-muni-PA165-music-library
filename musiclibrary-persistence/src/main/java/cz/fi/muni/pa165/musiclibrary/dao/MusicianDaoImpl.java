package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author David Koncak
 */
@Repository
public class MusicianDaoImpl implements cz.fi.muni.pa165.musiclibrary.dao.MusicianDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Musician m) {
		em.persist(m);
	}

	@Override
	public void remove(Musician m) throws IllegalArgumentException {
		em.remove(findById(m.getId()));
	}

	@Override
	public void update(Musician m) {
		em.merge(m);
	}

	@Override
	public Musician findById(Long id) {
		return em.find(Musician.class, id);
	}

	@Override
	public List<Musician> findByName(String namePattern) {
		return em.createQuery("SELECT m FROM Musician m WHERE m.name like :name ",
				Musician.class).setParameter("name", "%" + namePattern + "%").getResultList();
	}

	@Override
	public List<Musician> findAll() {
		return em.createQuery("select m from Musician m", Musician.class)
				.getResultList();
	}

}
