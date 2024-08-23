package com.session.entity

import com.session.dto.MovieDTO
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.io.Serializable

@Getter
@Setter
@Entity
@Table(name = "movies")
class MovieEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "title")
    var title: String? = null

    @Column(name = "synopsis")
    var synopsis: String? = null

    @Column(name = "duration")
    var duration: Long? = null

    @Column(name = "gender")
    var gender: String? = null

    @Column(name = "classification")
    var classification: String? = null

}

fun MovieEntity.toDTO(): MovieDTO {
    return MovieDTO(
        title = this.title ?: "",
        synopsis = this.synopsis ?: "",
        duration = this.duration ?: 0L,
        gender = this.gender ?: "",
        classification = this.classification ?: ""
    )
}