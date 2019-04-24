package com.kodilla.libraryapi.enumerics;

public enum CurrencyEnum {
    USD("USD"),
    CAD("CAD"),
    EUR("EUR"),
    GBP("GBP"),
    PLN("PLN");

    private String description;

    CurrencyEnum(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
}
