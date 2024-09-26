package com.session.entity

import com.session.controller.api.request.dto.CineRoomDTO
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.io.Serializable

@Getter
@Setter
@Entity
@Table(name = "session")
class CineRoomEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "number_room")
    var numberRoom: Long = 0

    @Column(name = "name")
    var name: String? = null

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    var sessionId: Long? = null
}

fun CineRoomEntity.toDTO(): CineRoomDTO {
    return CineRoomDTO(
        numberRoom = numberRoom,
        name = name,
        sessionId = sessionId
    )
}