package com.session.controller.api

import com.session.controller.api.request.dto.CineRoomDTO
import com.session.service.CineRoomService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cine-rooms")
class CineRoomController(
    private val cineRoomService: CineRoomService
) {

    @GetMapping
    fun getAllCineRooms(): ResponseEntity<List<CineRoomDTO>> {
        return ResponseEntity.ok(cineRoomService.getAllCineRooms())
    }

    @GetMapping("/{id}")
    fun getCineRoomById(@PathVariable id: Long): ResponseEntity<CineRoomDTO> {
        val cineRoom = cineRoomService.getCineRoomById(id)
        return if (cineRoom != null) {
            ResponseEntity.ok(cineRoom)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createCineRoom(@RequestBody cineRoomDTO: CineRoomDTO): ResponseEntity<CineRoomDTO> {
        val newCineRoom = cineRoomService.createCineRoom(cineRoomDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(newCineRoom)
    }

    @PutMapping("/{id}")
    fun updateCineRoom(@PathVariable id: Long, @RequestBody cineRoomDTO: CineRoomDTO): ResponseEntity<CineRoomDTO> {
        return cineRoomService.updateCineRoom(id, cineRoomDTO).let { updateCineRoom ->
            ResponseEntity.ok(updateCineRoom)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteCineRoom(@PathVariable id: Long): ResponseEntity<Void> {
        return if (cineRoomService.deleteCineRoom(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}