package de.neuefische.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum TargetGroup {
    RELATIVES("Angehörige"),
    INDIVIDUAL("Betroffene");

    public String displayName;

    TargetGroup(String displayName) {
        this.displayName = displayName;
    }

    @JsonCreator
    public static TargetGroup findValue(@JsonProperty("displayName") String displayName) {
        return Arrays.stream(TargetGroup.values()).filter(group -> group.displayName.equals(displayName)).findFirst().get();
    }



}
