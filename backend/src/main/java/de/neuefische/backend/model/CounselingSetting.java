package de.neuefische.backend.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum CounselingSetting {
    INPERSON("persönlich"),
    PHONE("telefonisch"),
    CHAT("chat");

    public String displayName;

    CounselingSetting(String displayName) {
        this.displayName = displayName;
    }

    @JsonCreator
    public static CounselingSetting findValue(@JsonProperty("displayName") String displayName) {
        return Arrays.stream(CounselingSetting.values()).filter(setting -> setting.displayName.equals(displayName)).findFirst().get();
    }

}
