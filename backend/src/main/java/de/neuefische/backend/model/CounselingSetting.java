package de.neuefische.backend.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.NoSuchElementException;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CounselingSetting {
    INPERSON("persönlich"),
    PHONE("telefonisch"),
    CHAT("chat"),
    GROUP("Gruppenarbeit");

    public String displayName;

    CounselingSetting(String displayName) {
        this.displayName = displayName;
    }

    @JsonCreator
    public static CounselingSetting findValue(@JsonProperty("displayName") String displayName) {
        return Arrays.stream(CounselingSetting.values())
                .filter(el -> el.displayName.equals(displayName))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
