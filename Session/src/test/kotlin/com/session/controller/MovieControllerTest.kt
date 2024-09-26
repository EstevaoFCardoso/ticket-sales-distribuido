package com.session.controller

import com.session.controller.api.MovieController
import com.session.controller.api.request.dto.MovieDTO
import com.session.service.MovieService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@ExtendWith(MockitoExtension::class)
class MovieControllerTest {

    @Mock
    private lateinit var movieService: MovieService

    private lateinit var movieController: MovieController

    @BeforeEach
    fun setUp() {
        movieController = MovieController(movieService)
    }

    @Test
    fun `should create a new movie`() {
        val movieDTO = MovieDTO("Title", "Synopsis", 180, "Comédia", "L")
        `when`(movieService.createMovie(movieDTO)).thenReturn(movieDTO)

        val response: ResponseEntity<MovieDTO> = movieController.create(movieDTO)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(movieDTO, response.body)
        verify(movieService, times(1)).createMovie(movieDTO)
    }

    @Test
    fun `should return a movie by ID`() {
        val movieDTO = MovieDTO("Title", "Synopsis", 180, "Comédia", "L")
        `when`(movieService.getById(1)).thenReturn(movieDTO)

        val response: ResponseEntity<MovieDTO> = movieController.getMovieById(1L)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(movieDTO, response.body)
        verify(movieService, times(1)).getById(1)
    }

    @Test
    fun `should list all movies`() {
        val movies = listOf(
            MovieDTO("Title", "Synopsis", 180, "Comédia", "L"),
            MovieDTO("Title2", "Synopsis2", 180, "Comédia2", "L")
        )
        `when`(movieService.listMovies()).thenReturn(movies)


        val response: ResponseEntity<List<MovieDTO>> = movieController.listMovies()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(movies, response.body)
        verify(movieService, times(1)).listMovies()

    }

    @Test
    fun `should return 404 when deleting a non-existent movie`() {
        `when`(movieService.deleteMovie(1L)).thenReturn(false)

        val response: ResponseEntity<Void> = movieController.delete(1L)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        verify(movieService, times(1)).deleteMovie(1L)
        assertEquals(response.statusCode, HttpStatus.NOT_FOUND)
    }
}