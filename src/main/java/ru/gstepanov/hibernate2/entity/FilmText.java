package ru.gstepanov.hibernate2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(
        schema = "movie",
        name = "film_text",
        indexes = {
                @Index(columnList = "language_id,description", name = "idx_title_description")
        }
)
public class FilmText {

    @Id
    @Column(name = "film_id")
    private Short Id;

    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "text", nullable = false)
    @Type(type = "text")
    private String description;
}
