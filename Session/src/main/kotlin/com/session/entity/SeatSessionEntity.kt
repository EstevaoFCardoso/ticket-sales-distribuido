package com.session.entity

import com.session.controller.api.request.dto.SeatSessionDTO
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.io.Serializable

@Getter
@Setter
@Entity
@Table(name = "seat_session")
class SeatSessionEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "seat_id")
    var seatId: Long? = null

    @Column(name = "available_seats")
    var availableSeats: Long? = null

    @ManyToOne
    @Column(name = "session_id")
    var sessionId: Long? = null
}

fun SeatSessionEntity.toDTO(): SeatSessionDTO {
    return SeatSessionDTO(
        availableSeats = this.availableSeats?: 0L,
        sessionId = this.sessionId ?: 0L,
        seatId = this.seatId ?: 0
        )
}