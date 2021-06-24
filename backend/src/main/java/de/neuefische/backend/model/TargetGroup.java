package de.neuefische.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.NoSuchElementException;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)

public enum TargetGroup {

    RELATIVES("Angehörige"),
    INDIVIDUAL("Betroffene");

    public String displayName;

    TargetGroup(String displayName) {
        this.displayName = displayName;
    }

    @JsonCreator
    public static TargetGroup findValue(@JsonProperty("displayName") String displayName) {

        return Arrays.stream(TargetGroup.values())
                .filter(el -> el.displayName.equals(displayName))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

    }


}
