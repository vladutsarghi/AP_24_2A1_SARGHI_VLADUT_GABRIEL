package org.example.com;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Currency;
import java.util.Locale;

public class Info {
    public static void execute(Locale locale) {
        System.out.println("Country: " + locale.getDisplayCountry());
        System.out.println("Language: " + locale.getDisplayLanguage());
        Currency currency = Currency.getInstance(locale);
        System.out.println("Currency: " + currency.getCurrencyCode() + " (" + currency.getDisplayName() + ")");
        DateFormatSymbols dfs = new DateFormatSymbols(locale);
        System.out.println("Week Days: " + String.join(", ", dfs.getWeekdays()));
        System.out.println("Months: " + String.join(", ", dfs.getMonths()));
        System.out.println("Today: " + DateFormat.getDateInstance(DateFormat.LONG, locale).format(new java.util.Date()));
    }
}
