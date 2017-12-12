package cz.fi.muni.pa165.musiclibrary.service.config;

import cz.fi.muni.pa165.musiclibrary.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import cz.fi.muni.pa165.musiclibrary.dto.SongDTO;
import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import cz.fi.muni.pa165.musiclibrary.facade.AplicationUserFacade;
import cz.fi.muni.pa165.musiclibrary.facade.GenreFacade;
import cz.fi.muni.pa165.musiclibrary.facade.MusicianFacade;
import cz.fi.muni.pa165.musiclibrary.facade.SongFacade;
import cz.fi.muni.pa165.musiclibrary.service.AlbumServiceImpl;
import cz.fi.muni.pa165.musiclibrary.service.AplicationUserServiceImpl;
import cz.fi.muni.pa165.musiclibrary.service.GenreServiceImpl;
import cz.fi.muni.pa165.musiclibrary.service.MusicianServiceImpl;
import cz.fi.muni.pa165.musiclibrary.service.SongServiceImpl;
import cz.fi.muni.pa165.musiclibrary.service.facade.AlbumFacadeImpl;
import cz.fi.muni.pa165.musiclibrary.service.facade.AplicationUserFacadeImpl;
import cz.fi.muni.pa165.musiclibrary.service.facade.GenreFacadeImpl;
import cz.fi.muni.pa165.musiclibrary.service.facade.SongFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses = {
	AlbumServiceImpl.class,
	AplicationUserServiceImpl.class,
	GenreServiceImpl.class,
	MusicianServiceImpl.class,
	SongServiceImpl.class,
	AlbumFacade.class,
	AplicationUserFacade.class,
	GenreFacade.class,
	MusicianFacade.class,
	SongFacade.class,
	AlbumFacadeImpl.class,
	AplicationUserFacadeImpl.class,
	GenreFacadeImpl.class,
	MusicianServiceImpl.class,
	SongFacadeImpl.class
}, basePackages = {
	"cz.fi.muni.pa165.musiclibrary.facade",
	"cz.fi.muni.pa165.musiclibrary.service",
	"cz.fi.muni.pa165.musiclibrary.service.facade"
})
public class ServiceConfiguration {


	@Bean
	public Mapper dozer() {
		DozerBeanMapper dozer = new DozerBeanMapper();
		dozer.addMapping(new DozerCustomConfig());
		return dozer;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Custom config for Dozer if needed
	 *
	 * @author nguyen
	 */
	public class DozerCustomConfig extends BeanMappingBuilder {
		@Override
		protected void configure() {
			mapping(Album.class, AlbumDTO.class);
			mapping(Genre.class, GenreDTO.class);
			mapping(Musician.class, MusicianDTO.class);
			mapping(Song.class, SongDTO.class);
		}
	}

}

