package org.example;

import Algorithm.Algorithm;
import DatabaseConnection.DatabaseManager;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
//      Import dataCSV = new Import("C:\\Users\\sargh\\Desktop\\AP_24_2A1_SARGHI_VLADUT_GABRIEL\\LAB8\\homework\\homework\\data.csv");
        DataSource dataSource = DatabaseManager.getDataSource();
//      dataCSV.importData(dataSource);

        Algorithm algorithm = new Algorithm(dataSource);
        algorithm.output();
    }
}