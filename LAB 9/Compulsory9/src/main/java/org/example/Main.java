package org.example;

import Entities.Book;
import Repositories.AuthorRepository;
import Repositories.BookRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepository();
        AuthorRepository authorRepository = new AuthorRepository();
        List<Book> books = bookRepository.findByAuthorId(3);
        for(Book book : books){
            System.out.println(book);
        }


    }
}