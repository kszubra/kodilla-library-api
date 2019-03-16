package com.kodilla.libraryapi.enumerics;

import lombok.Getter;

@Getter
public enum BookInstanceStatus {

    IN_USE("in use"),
    DESTROYED("destroyed"),
    LOST("lost"),
    IN_RENOVATION("in renovation");

    private String description;
    BookInstanceStatus(String description) {this.description = description;}
    @Override
    public String toString() {
        return this.getDescription();
    }

}
