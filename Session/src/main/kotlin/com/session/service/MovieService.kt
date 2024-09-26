package com.session.service

import com.session.controller.api.request.dto.MovieDTO
import com.session.controller.api.request.dto.toEntity
import com.session.entity.toDTO
import com.session.repository.MovieRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class MovieService(private val movieRepository: MovieRepository) {

    fun createMovie(movie: MovieDTO): MovieDTO {
        return movieRepository.save(movie.toEntity()).toDTO()
    }

    fun getById(id: Long): MovieDTO {
        val movie = movieRepository.findById(id).orElseThrow {
            EntityNotFoundException("Movie with ID $id not found")
        }
        return movie.toDTO()
    }

    fun listMovies(): List<MovieDTO> {
        return movieRepository.findAll().map { movie ->
            movie.toDTO()
        }
    }

    fun updateMovie(id: Long, movie: MovieDTO): MovieDTO {
        if (!movieRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        return movieRepository.save(movie.toEntity()).toDTO()
    }

    fun deleteMovie(id: Long): Boolean {
        if (!movieRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        movieRepository.deleteById(id)
        return true
    }
}