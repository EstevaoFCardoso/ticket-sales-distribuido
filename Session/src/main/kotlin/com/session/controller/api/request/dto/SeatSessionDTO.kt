package com.session.controller.api.request.dto

import com.session.entity.SeatSessionEntity

data class SeatSessionDTO(
    val seatId: Long?,
    val availableSeats: Long?,
    val sessionId: Long?
)

fun SeatSessionDTO.toEntity(): SeatSessionEntity {
    val seatSessionEntity = SeatSessionEntity()
    seatSessionEntity.seatId = this.seatId
    seatSessionEntity.availableSeats = this.availableSeats
    seatSessionEntity.sessionId = this.sessionId ?: 0
    return seatSessionEntity
}