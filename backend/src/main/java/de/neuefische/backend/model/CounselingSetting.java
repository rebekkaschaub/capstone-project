package de.neuefische.backend.model;


import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CounselingSetting {
    INPERSON("persönlich"),
    PHONE("telefonisch"),
    CHAT("chat");

    public String displayName;

    CounselingSetting(String displayName) {
        this.displayName = displayName;
    }
}
