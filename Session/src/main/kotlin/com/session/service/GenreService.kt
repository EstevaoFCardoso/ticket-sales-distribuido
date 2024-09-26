package com.session.service

import com.session.dto.GenreDTO
import com.session.controller.api.request.dto.toEntity
import com.session.entity.toDTO
import com.session.repository.GenreRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class GenreService(
    private val genreRepository: GenreRepository
) {

    fun getAllGenres(): List<GenreDTO> {
        return genreRepository.findAll().map { genre ->
            genre.toDTO()
        }
    }

    fun getGenreById(id: Long): GenreDTO? {
        val genre = genreRepository.findById(id).orElseThrow {
            EntityNotFoundException("Genre with ID $id not found")
        }
        return genre.toDTO()
    }

    fun createGenre(genreDTO: GenreDTO): GenreDTO {
        return genreRepository.save(genreDTO.toEntity()).toDTO()
    }

    fun updateGenre(id: Long, genreDTO: GenreDTO): GenreDTO? {
        if (!genreRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        return genreRepository.save(genreDTO.toEntity()).toDTO()
    }

    fun deleteGenre(id: Long) : Boolean {
        if (!genreRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        genreRepository.deleteById(id)
        return true
    }
}