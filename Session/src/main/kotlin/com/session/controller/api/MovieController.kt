package com.session.controller.api

import com.session.controller.api.request.dto.MovieDTO
import com.session.service.MovieService
import com.session.controller.api.error.ResourceNotFoundException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/movies")
@Tag(name = "Movies", description = "Endpoints for managing movies")
class MovieController(private val movieService: MovieService) {

    @Operation(summary = "Create a new movie", description = "Add a new movie to the catalog")
    @PostMapping
    fun create(@RequestBody movie: MovieDTO): ResponseEntity<MovieDTO> {
        val newMovie = movieService.createMovie(movie)
        return ResponseEntity.status(HttpStatus.CREATED).body(newMovie)
    }

    @Operation(summary = "Get a movie by ID", description = "Retrieve a movie by its ID")
    @GetMapping("/{id}")
    fun getMovieById(@PathVariable id: Long): ResponseEntity<MovieDTO> {
        return movieService.getById(id).let { movie ->
            ResponseEntity.ok(movie)
        }
    }

    @Operation(summary = "List all movies", description = "Retrieve a list of all movies in the catalog")
    @GetMapping
    fun listMovies(): ResponseEntity<List<MovieDTO>> {
        val movies = movieService.listMovies()
        return ResponseEntity.ok(movies)
    }

    @Operation(summary = "Update a movie by ID", description = "Update an existing movie's details")
    @PutMapping("/{id}")
    fun updateMovie(@PathVariable id: Long, @RequestBody movie: MovieDTO): ResponseEntity<MovieDTO> {
        return movieService.updateMovie(id, movie).let { updatedMovie ->
            ResponseEntity.ok(updatedMovie)
        }
    }

    @Operation(summary = "Delete a movie by ID", description = "Remove a movie from the catalog")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return if (movieService.deleteMovie(id)) {
            ResponseEntity.noContent().build()
        } else {
            throw ResourceNotFoundException("Recurso com ID $id não foi encontrado.")
        }
    }
}