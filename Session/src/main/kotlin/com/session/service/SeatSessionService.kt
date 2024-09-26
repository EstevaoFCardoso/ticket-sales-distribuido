package com.session.service

import com.session.controller.api.request.dto.SeatSessionDTO
import com.session.controller.api.request.dto.toEntity
import com.session.entity.SeatSessionEntity
import com.session.entity.toDTO
import com.session.repository.SeatSessionRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class SeatSessionService(
    private val seatSessionRepository: SeatSessionRepository
) {

    fun getAllSeatSessions(): List<SeatSessionDTO> {
        return seatSessionRepository.findAll().map { seatSession ->
            seatSession.toDTO()
        }
    }

    fun getSeatSessionById(id: Long): SeatSessionDTO? {
        val seatSession = seatSessionRepository.findById(id).orElseThrow {
            EntityNotFoundException("SeatSession with ID $id not found")
        }
        return seatSession.toDTO()
    }

    fun createSeatSession(seatSessionDTO: SeatSessionDTO): SeatSessionDTO {
        return seatSessionRepository.save(seatSessionDTO.toEntity()).toDTO()    }

    fun updateSeatSession(id: Long, seatSessionDTO: SeatSessionDTO) : SeatSessionDTO {
        if (!seatSessionRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        return seatSessionRepository.save(seatSessionDTO.toEntity()).toDTO()
    }

    fun deleteSeatSession(id: Long): Boolean{
        if (!seatSessionRepository.existsById(id)) {
            throw EntityNotFoundException("SeatSession with ID $id not found")
        }
        seatSessionRepository.deleteById(id)
        return true
    }
}