package com.session.service

import com.session.controller.api.request.dto.SessionDTO
import com.session.controller.api.request.dto.toEntity
import com.session.entity.SessionEntity
import com.session.entity.toDTO
import com.session.repository.SessionRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class SessionService(
    private val sessionRepository: SessionRepository
) {

    fun getAllSessions(): List<SessionDTO> {
        return sessionRepository.findAll().map { session ->
            session.toDTO()
        }
    }

    fun getSessionById(id: Long): SessionDTO? {
        val session = sessionRepository.findById(id).orElseThrow {
            EntityNotFoundException("Session with ID $id not found")
        }
        return session.toDTO()
    }

    fun createSession(sessionEntity: SessionDTO): SessionDTO {
        return sessionRepository.save(sessionEntity.toEntity()).toDTO()
    }

    fun updateSession(id: Long, sessionEntity: SessionDTO): SessionDTO? {
        if (!sessionRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        return sessionRepository.save(sessionEntity.toEntity()).toDTO()
    }

    fun deleteSession(id: Long) :Boolean {
        if (!sessionRepository.existsById(id)) {
            throw EntityNotFoundException("Movie with ID $id not found")
        }
        sessionRepository.deleteById(id)
        return true
    }

}