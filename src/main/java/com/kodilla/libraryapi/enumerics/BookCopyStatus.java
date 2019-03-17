package com.kodilla.libraryapi.enumerics;

import lombok.Getter;

@Getter
public enum BookCopyStatus {

    IN_USE("in use"),
    DESTROYED("destroyed"),
    LOST("lost"),
    IN_RENOVATION("in renovation");

    private String description;
    BookCopyStatus(String description) {this.description = description;}
    @Override
    public String toString() {
        return this.getDescription();
    }

}
