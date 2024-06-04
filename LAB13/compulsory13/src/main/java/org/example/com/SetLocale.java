package org.example.com;

import java.util.Locale;

public class SetLocale {
    private static Locale currentLocale = Locale.getDefault();

    public static void execute(String languageTag) {
        currentLocale = Locale.forLanguageTag(languageTag);
        System.out.printf("Locale set to: %s%n", currentLocale.getDisplayName());
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }
}
