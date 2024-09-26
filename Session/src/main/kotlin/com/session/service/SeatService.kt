package com.session.service

import com.session.controller.api.request.dto.SeatDTO
import com.session.controller.api.request.dto.toEntity
import com.session.entity.toDTO
import com.session.repository.SeatRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class SeatService(
    private val seatRepository: SeatRepository
) {

    fun getAllSeats(): List<SeatDTO> {
        return seatRepository.findAll().map { seat ->
            seat.toDTO()
        }
    }

    fun getSeatById(id: Long): SeatDTO? {
        val seat = seatRepository.findById(id).orElseThrow {
            EntityNotFoundException("Seat with ID $id not found")
        }
        return seat.toDTO()
    }

    fun createSeat(seatEntity: SeatDTO): SeatDTO {
        return seatRepository.save(seatEntity.toEntity()).toDTO()
    }

    fun updateSeat(id: Long, seatEntity: SeatDTO): SeatDTO? {
        return if (seatRepository.existsById(id)) {
            seatRepository.save(seatEntity.toEntity()).toDTO()
        } else {
            null
        }
    }

    fun deleteSeat(id: Long): Boolean {
        if (!seatRepository.existsById(id)) {
            throw EntityNotFoundException("Seat with ID $id not found")
        }
        seatRepository.deleteById(id)
        return true
    }
}