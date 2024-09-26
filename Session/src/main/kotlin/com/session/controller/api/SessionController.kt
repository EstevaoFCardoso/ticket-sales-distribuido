package com.session.controller.api

import com.session.controller.api.request.dto.SessionDTO
import com.session.service.SessionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sessions")
class SessionController(
    private val sessionService: SessionService
) {

    @GetMapping
    fun getAllSessions(): ResponseEntity<List<SessionDTO>> {
        return ResponseEntity.ok(sessionService.getAllSessions())
    }

    @GetMapping("/{id}")
    fun getSessionById(@PathVariable id: Long): ResponseEntity<SessionDTO> {
        val session = sessionService.getSessionById(id)
        return if (session != null) {
            ResponseEntity.ok(session)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createSession(@RequestBody sessionDTO: SessionDTO): ResponseEntity<SessionDTO> {
        val newSession = sessionService.createSession(sessionDTO)
        return ResponseEntity(newSession, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateSession(@PathVariable id: Long, @RequestBody sessionDTO: SessionDTO): ResponseEntity<SessionDTO> {
        return sessionService.updateSession(id, sessionDTO).let { updatedSession ->
            ResponseEntity.ok(updatedSession)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteSession(@PathVariable id: Long): ResponseEntity<Void> {
        return if (sessionService.deleteSession(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}