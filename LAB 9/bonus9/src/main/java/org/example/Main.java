package org.example;

import Algorithm.BookSetAlgorithm;
import DAO.DAOFactory;
import Entities.Book;
import Repositories.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;


public class Main {
    public static void main(String[] args) {


        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String daoType = props.getProperty("dao.type");
        DAOFactory daoFactory = null;

        if ("JPA".equalsIgnoreCase(daoType)) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab9 persistence unit");
            EntityManager em = emf.createEntityManager();
            daoFactory = DAOFactory.getDAOFactory("JPA", em);
        } else if ("JDBC".equalsIgnoreCase(daoType)) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "6385");
                daoFactory = DAOFactory.getDAOFactory("JDBC", connection);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        if (daoFactory != null) {


            System.out.println("Working with: " + daoType);
//            for(Book book : books){
//                System.out.println(book);
//            }
            BookSetAlgorithm algorithm = new BookSetAlgorithm(new BookRepository());
            int k = 30;
            int p = 3;

            algorithm.findAndPrintBooksStartingWithSameLetter(k,p);

        }
    }


}
