package com.session.controller.api.request.dto

import com.session.entity.CineRoomEntity

data class CineRoomDTO(
    val numberRoom: Long?,
    val name: String?,
    val sessionId: Long?
)

fun CineRoomDTO.toEntity(): CineRoomEntity {
    val cineRoomEntity = CineRoomEntity()
    cineRoomEntity.name = name?: ""
    cineRoomEntity.numberRoom= numberRoom?: 0L
    cineRoomEntity.sessionId = sessionId?: 0L
    return cineRoomEntity
}