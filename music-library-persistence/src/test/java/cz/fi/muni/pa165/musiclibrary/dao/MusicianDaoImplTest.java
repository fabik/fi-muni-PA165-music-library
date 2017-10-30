/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * Unit tests for DAO layer of entity Musician.
 * 
 * @author Iva Liberova
 */
@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MusicianDaoImplTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private MusicianDao musicianDao;

    private Musician daya;
    private Musician taylor_swift;
    private Musician muse;
    
    @BeforeMethod
    public void setUp() {
        daya = new Musician();
        daya.setName("Daya");

        taylor_swift = new Musician();
        taylor_swift.setName("Taylor Swift");
        
        muse = new Musician();
        muse.setName("Muse");
    }
    
    @Test
    public void testCreate() {
        musicianDao.create(daya);
        Assert.assertEquals(musicianDao.findById(daya.getId()), daya);
    }
    
    @Test
    public void testUpdate() {
        musicianDao.create(daya);
        String oldName = daya.getName();
        daya.setName("Awesome Daya");
        musicianDao.update(daya);
        Musician currentDaya = musicianDao.findById(daya.getId());
        Assert.assertEquals(currentDaya, daya);
        Assert.assertEquals(currentDaya.getName(),daya.getName());
        Assert.assertNotEquals(oldName, currentDaya.getName());
    }
    
    @Test
    public void testRemove() {
        musicianDao.create(daya);
        musicianDao.remove(daya);
        List<Musician> result = musicianDao.findAll();
        Assert.assertEquals(0, result.size());
    }
    
    @Test
    public void testFindById() {
        musicianDao.create(daya);
        Assert.assertEquals(musicianDao.findById(daya.getId()), daya);
    }
    
    @Test
    public void testFindByName() {
        musicianDao.create(taylor_swift);
        Assert.assertEquals(musicianDao.findByName("Taylor").get(0), taylor_swift);
    }
    
    @Test
    public void testFindAll(){
        musicianDao.create(daya);
        musicianDao.create(taylor_swift);
        musicianDao.create(muse);
        
        List<Musician> expectedResult = new ArrayList<>();
        expectedResult.add(daya);
        expectedResult.add(taylor_swift);
        expectedResult.add(muse);
        
        List<Musician> result = musicianDao.findAll();
        
        Assert.assertEquals(expectedResult.size(), result.size());
        
        for(int i = 0; i < expectedResult.size(); i++){
            Assert.assertEquals(expectedResult.get(i), result.get(i));
        }
    }
}
