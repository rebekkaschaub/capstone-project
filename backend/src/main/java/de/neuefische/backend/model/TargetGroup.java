package de.neuefische.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TargetGroup {
    RELATIVES("Angehörige"),
    INDIVIDUAL("Betroffene");

    public String displayName;

    TargetGroup(String displayName) {
        this.displayName = displayName;
    }
}
