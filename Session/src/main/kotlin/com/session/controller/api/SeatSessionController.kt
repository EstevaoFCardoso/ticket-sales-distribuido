package com.session.controller.api

import com.session.controller.api.request.dto.SeatSessionDTO
import com.session.entity.SeatSessionEntity
import com.session.entity.toDTO
import com.session.service.SeatSessionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/seat-sessions")
class SeatSessionController(
    private val seatSessionService: SeatSessionService
) {

    @GetMapping
    fun getAllSeatSessions(): ResponseEntity<List<SeatSessionDTO>> {
        return ResponseEntity.ok(seatSessionService.getAllSeatSessions())
    }

    @GetMapping("/{id}")
    fun getSeatSessionById(@PathVariable id: Long): ResponseEntity<SeatSessionDTO> {
        val seatSession = seatSessionService.getSeatSessionById(id)
        return if (seatSession != null) {
            ResponseEntity.ok(seatSession)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createSeatSession(@RequestBody seatSessionEntity: SeatSessionEntity): ResponseEntity<SeatSessionDTO> {
        val newSeatSession = seatSessionService.createSeatSession(seatSessionEntity.toDTO())
        return ResponseEntity(newSeatSession, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateSeatSession(@PathVariable id: Long, @RequestBody seatSessionEntity: SeatSessionEntity): ResponseEntity<SeatSessionDTO> {
        return seatSessionService.updateSeatSession(id, seatSessionEntity.toDTO()).let { updateSeatSession ->
            ResponseEntity.ok(updateSeatSession)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteSeatSession(@PathVariable id: Long): ResponseEntity<Void> {
        return if (seatSessionService.deleteSeatSession(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}