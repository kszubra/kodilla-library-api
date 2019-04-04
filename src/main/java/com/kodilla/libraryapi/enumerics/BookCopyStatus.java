package com.kodilla.libraryapi.enumerics;

public enum BookCopyStatus {

    IN_USE("IN USE"),
    IN_RENOVATION("IN RENOVATION"),
    LOST("LOST"),
    DESTROYED("DESTROYED");

    private String description;

    BookCopyStatus(String description) {
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