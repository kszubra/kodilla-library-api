package com.kodilla.libraryapi.enumerics;

import lombok.Getter;

@Getter
public enum Currency {

    PLN("polish zloty"),
    USD("american dollar"),
    EUR("euro"),
    GBP("great britain pound");

    private String description;
    Currency(String description) {this.description = description;}
    @Override
    public String toString() {
        return this.getDescription();
    }
}
