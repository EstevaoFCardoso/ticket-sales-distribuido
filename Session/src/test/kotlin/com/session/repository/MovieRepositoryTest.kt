package com.session.repository

import com.session.controller.api.request.dto.MovieDTO
import com.session.controller.api.request.dto.toEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@ExtendWith(MockitoExtension::class)
@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    private lateinit var movieRepository: MovieRepository

    @Test
    fun `should save a movie`() {
        val movie = MovieDTO("Title", "Synopsis", 180, "Comédia", "L").toEntity()
        val savedMovie = movieRepository.save(movie)

        assertEquals(movie.title, savedMovie.title)
        assertTrue(savedMovie.id > 0)
    }

    @Test
    fun `should find a movie by ID`() {
        val movie = MovieDTO("Title", "Synopsis", 180, "Comédia", "L").toEntity()
        val savedMovie = movieRepository.save(movie)

        val foundMovie = movieRepository.findById(savedMovie.id)
        assertTrue(foundMovie.isPresent)
        assertEquals(savedMovie.title, foundMovie.get().title)
    }

    @Test
    fun `should find all movies`() {
        val movie1 = MovieDTO("Title", "Synopsis", 180, "Comédia", "L").toEntity()
        val movie2 = MovieDTO("Title2", "Synopsis2", 180, "Comédia2", "L").toEntity()
        movieRepository.save(movie1)
        movieRepository.save(movie2)

        val movies = movieRepository.findAll()
        assertEquals(2, movies.size)
    }

    @Test
    fun `should update a movie`() {
        val movie = MovieDTO("Title", "Synopsis", 180, "Comédia", "L").toEntity()
        val savedMovie = movieRepository.save(movie)

        savedMovie.title = "Updated Title"
        val updatedMovie = movieRepository.save(savedMovie)

        assertEquals("Updated Title", updatedMovie.title)
    }

    @Test
    fun `should delete a movie by ID`() {
        val movie = MovieDTO("Title", "Synopsis", 180, "Comédia", "L").toEntity()
        val savedMovie = movieRepository.save(movie)

        movieRepository.deleteById(savedMovie.id)
        val deletedMovie = movieRepository.findById(savedMovie.id)

        assertTrue(deletedMovie.isEmpty)
    }
}