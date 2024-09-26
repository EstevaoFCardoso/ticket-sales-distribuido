package com.session.service

import com.session.controller.api.request.dto.CineRoomDTO
import com.session.controller.api.request.dto.toEntity
import com.session.entity.CineRoomEntity
import com.session.entity.toDTO
import com.session.repository.CineRoomRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class CineRoomService(
    private val cineRoomRepository: CineRoomRepository
) {

    fun getAllCineRooms(): List<CineRoomDTO> {
        return cineRoomRepository.findAll().map { cineRoom ->
            cineRoom.toDTO()
        }
    }

    fun getCineRoomById(id: Long): CineRoomDTO? {
        val cineRoomDTO = cineRoomRepository.findById(id).orElseThrow {
            EntityNotFoundException("CineRoom with ID $id not found")
        }
        return cineRoomDTO.toDTO()
    }

    fun createCineRoom(cineRoomDTO: CineRoomDTO): CineRoomDTO {
        return cineRoomRepository.save(cineRoomDTO.toEntity()).toDTO()
    }

    fun updateCineRoom(id: Long, cineRoomDTO: CineRoomDTO): CineRoomDTO? {
        if (!cineRoomRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        return cineRoomRepository.save(cineRoomDTO.toEntity()).toDTO()
    }

    fun deleteCineRoom(id: Long): Boolean {
        if (!cineRoomRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        cineRoomRepository.deleteById(id)
        return true
    }
}