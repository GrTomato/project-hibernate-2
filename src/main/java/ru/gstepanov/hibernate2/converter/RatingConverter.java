package ru.gstepanov.hibernate2.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.gstepanov.hibernate2.entity.FilmRating;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<FilmRating, String> {

    @Override
    public String convertToDatabaseColumn(FilmRating filmRating) {
        return filmRating.getRating();
    }

    @Override
    public FilmRating convertToEntityAttribute(String s) {
        return FilmRating.findByValue(s);
    }
}
