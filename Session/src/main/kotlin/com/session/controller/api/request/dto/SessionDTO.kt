package com.session.controller.api.request.dto

import com.session.entity.SessionEntity
import java.time.LocalDateTime

data class SessionDTO(
    val movieId: Long?,
    val startSession: LocalDateTime?,
    val endSession: LocalDateTime?,
    val initRangeTime: LocalDateTime?,
    val endRangeTime: LocalDateTime?
)

fun SessionDTO.toEntity(): SessionEntity {
    val sessionEntity = SessionEntity()
    sessionEntity.movieId = this.movieId
    sessionEntity.startSession = this.startSession
    sessionEntity.endSession = this.endSession
    sessionEntity.initRangeTime = this.initRangeTime
    sessionEntity.endRangeTime = this.endRangeTime
    return sessionEntity
}