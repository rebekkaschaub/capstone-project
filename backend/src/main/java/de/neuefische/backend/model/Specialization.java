package de.neuefische.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.NoSuchElementException;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Specialization {
    ALL("ALL", "Alle Beratungsstellen"),
    PSYCHISCH("PSYCH", "Psychische Erkrankungen"),
    ESSSTOERUNG("ESS", "Essstörungen"),
    SUCHT("Sucht", "Suchtberatung"),
    TRAUMA("Tr", "Trauma"),
    KRISENINTERVENTION("KI", "Krisenintervention"),
    SOZIALBERATUNG("SozB", "Sozialberatung"),
    LEBENSBERATUNG("LBB", "Lebensberatung"),
    ALLEINERZIEHENDE("AMV", "Alleinerziehende"),
    ERZIEHUNGSBERATUNG("EZB", "Erziehung"),
    EHEBERATUNG("EB", "Ehe"),
    PARTNERSCHAFT("PB", "Partnerschaft"),
    TRENNUNG_UND_SCHEIDUNG("TS", "Trennung und Scheidung"),
    FAMILIENBERATUNG("FB", "Familie"),
    FRAUEN("Fr", "Frauen"),
    GEWALTOPFER("GewO", "Opfer jeglicher Gewalt"),
    GEWALTTAETER("GewT", "Gewalttäter*innen"),
    STI("STI", "HIV- und STI-Beratung"),
    SEXUALBERATUNG("Sex", "Sexualberatung"),
    JUGENDBERATUNG("JB", "Jugendberatung"),
    KINDER("KiJu", "Kinder und Jugendliche"),
    LSBTIQ("LSB", "LSBTI"),
    MIGRATION("Migr", "Migration, Flüchtlinge und Spätaussiedler*innen");

    public String abbreviation;
    public String description;

    Specialization(String abbreviation, String description) {
        this.abbreviation = abbreviation;
        this.description = description;
    }

    @JsonCreator
    public static Specialization findValue(@JsonProperty("abbreviation") String abbreviation, @JsonProperty("description") String description) {

        return Arrays.stream(Specialization.values())
                .filter(el -> el.abbreviation.equals(abbreviation))
                .filter(el -> el.description.equals(description))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

    }
}
