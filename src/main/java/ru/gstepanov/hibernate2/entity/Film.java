package ru.gstepanov.hibernate2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import ru.gstepanov.hibernate2.converter.RatingConverter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(
        schema = "movie",
        name = "film",
        indexes = {
                @Index(columnList = "language_id", name = "idx_fk_language_id"),
                @Index(columnList = "original_language_id", name = "idx_fk_original_language_id"),
                @Index(columnList = "title", name = "idx_title")
        }
)
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short id;
    @Column(name = "title", length = 128, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "text")
    @Type(type = "text")
    private String description;

    @Column(name = "release_year", columnDefinition = "year")
    private LocalDateTime releaseYear;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Language language;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Language originalLanguage;

    @Column(name = "rental_duration", nullable = false)
    @ColumnDefault(value = "3")
    private Byte rentalDuration;

    @Column(name = "rental_rate", precision = 4, scale = 2, nullable = false)
    @ColumnDefault(value = "4.99")
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;
    @Column(name = "replacement_cost", precision = 5, scale = 2, nullable = false)
    @ColumnDefault(value = "19.99")
    private BigDecimal replacementCost;

    @Column(name = "rating", columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    @Convert(converter = RatingConverter.class)
    @ColumnDefault(value = "G")
    private FilmRating rating;

    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id")
    )
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ToString.Exclude
    private Collection<Actor> actors;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    )
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ToString.Exclude
    private Collection<Category> categories;

}

