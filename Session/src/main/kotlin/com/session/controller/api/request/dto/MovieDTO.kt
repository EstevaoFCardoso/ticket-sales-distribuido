package com.session.controller.api.request.dto

import com.session.entity.MovieEntity
import com.session.enums.GenreEnum

data class MovieDTO(
    val title: String?,
    val synopsis: String?,
    val duration: Long?,
    val genre: String?,
    val classification: String?
)

fun MovieDTO.toEntity(): MovieEntity {
    val movieEntity = MovieEntity()
    movieEntity.title = this.title
    movieEntity.synopsis = this.synopsis
    movieEntity.duration = this.duration
    movieEntity.genre = GenreEnum.fromName(this.genre.toString()).toString()
    movieEntity.classification = this.classification
    return movieEntity
}
