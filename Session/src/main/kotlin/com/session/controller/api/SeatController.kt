package com.session.controller.api

import com.session.controller.api.request.dto.SeatDTO
import com.session.service.SeatService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/seats")
class SeatController(
    private val seatService: SeatService
) {

    @GetMapping
    fun getAllSeats(): ResponseEntity<List<SeatDTO>> {
        return ResponseEntity.ok(seatService.getAllSeats())
    }

    @GetMapping("/{id}")
    fun getSeatById(@PathVariable id: Long): ResponseEntity<SeatDTO> {
        val seat = seatService.getSeatById(id)
        return if (seat != null) {
            ResponseEntity.ok(seat)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createSeat(@RequestBody seatDTO: SeatDTO): ResponseEntity<SeatDTO> {
        val newSeat = seatService.createSeat(seatDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(newSeat)
    }

    @PutMapping("/{id}")
    fun updateSeat(@PathVariable id: Long, @RequestBody seatDTO: SeatDTO): ResponseEntity<SeatDTO> {
        return seatService.updateSeat(id, seatDTO).let { updateSeat ->
            ResponseEntity.ok(updateSeat)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteSeat(@PathVariable id: Long): ResponseEntity<Void> {
        return if (seatService.deleteSeat(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}