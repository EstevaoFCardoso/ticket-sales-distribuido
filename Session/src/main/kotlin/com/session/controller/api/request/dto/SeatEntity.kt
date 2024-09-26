package com.session.controller.api.request.dto

import com.session.entity.SeatEntity

data class SeatDTO(
    val name: String?
)

fun SeatDTO.toEntity(): SeatEntity {
    val seatEntity = SeatEntity()
    seatEntity.name = this.name
    return seatEntity
}
