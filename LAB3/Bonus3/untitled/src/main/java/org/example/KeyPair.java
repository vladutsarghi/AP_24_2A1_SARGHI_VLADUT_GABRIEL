package org.example;

import java.time.LocalDate;

// Clasa KeyPair care combină un LocalDate și un String ca și cheie
public class KeyPair {
    private final LocalDate localDate;
    private final String eventName;

    public KeyPair(LocalDate localDate, String eventName) {
        this.localDate = localDate;
        this.eventName = eventName;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getEventName() {
        return eventName;
    }

    // Suprascriem metodele equals() și hashCode() pentru a asigura o comportare corectă în Map
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        KeyPair keyPair = (KeyPair) obj;
        return localDate.equals(keyPair.localDate) && eventName.equals(keyPair.eventName);
    }

    @Override
    public int hashCode() {
        int result = localDate.hashCode();
        result = 31 * result + eventName.hashCode();
        return result;
    }
}
