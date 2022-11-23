package com.michalszekalski.bootcamp_zad21;

public enum Category {
    FOOD ("artykuły spożywcze"),
    HOUSEHOLD ("artykuły gospodarstwa domowego"),
    OTHER ("inne");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
