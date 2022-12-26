package ru.gstepanov.hibernate2.entity;

public enum FilmRating {

    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");

    private final String rating;

    FilmRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public static FilmRating findByValue(String value){
        if (value.isEmpty() || value.isBlank()){
            return null;
        }
        for (FilmRating rating: FilmRating.values()) {
            if (rating.getRating().equalsIgnoreCase(value)){
                return rating;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return rating ;
    }
}
