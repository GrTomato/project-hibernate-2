package ru.gstepanov.hibernate2.service;

import ru.gstepanov.hibernate2.entity.Film;
import ru.gstepanov.hibernate2.entity.FilmRating;
import ru.gstepanov.hibernate2.entity.Language;

import java.math.BigDecimal;
import java.util.List;

public class FilmService {

    public Film getInstance(String title, Language language, Byte duration, BigDecimal rate, BigDecimal replacementCost, FilmRating filmRating, List actors, List categories){
        Film film = new Film();
        film.setTitle(title);
        film.setLanguage(language);
        film.setRentalDuration(duration);
        film.setRentalRate(rate);
        film.setReplacementCost(replacementCost);
        film.setActors(actors);
        film.setCategories(categories);
        film.setRating(filmRating);
        return film;
    }



}
