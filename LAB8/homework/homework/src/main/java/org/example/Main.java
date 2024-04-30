package org.example;

import DatabaseConnection.DatabaseManager;
import ImportData.Import;
import Models.Author;
import Models.Book;

import javax.sql.DataSource;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Import dataCSV = new Import("C:\\Users\\sargh\\Desktop\\AP_24_2A1_SARGHI_VLADUT_GABRIEL\\LAB8\\homework\\homework\\data.csv");
        DataSource dataSource = DatabaseManager.getDataSource();
        dataCSV.importData(dataSource);

//        Author author = new Author(dataSource);
//        author.add("Ion Creaga");
//
//        Book book = new Book(dataSource);
//        book.add("Iona", "drama", LocalDate.of(1970, 1, 1), "Ion Creaga");
    }
}