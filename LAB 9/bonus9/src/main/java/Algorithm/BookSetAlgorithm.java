package Algorithm;

import Entities.Book;
import Repositories.BookRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookSetAlgorithm {
    private BookRepository bookDAO;

    public BookSetAlgorithm(BookRepository bookDAO) {
        this.bookDAO = bookDAO;
    }

    public void findAndPrintBooksStartingWithSameLetter(int k, int p) {
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            List<Book> books = bookDAO.findBooksByFirstLetter(ch);
            if (books.size() < k) continue;

            books.sort(Comparator.comparingInt(book -> Integer.parseInt(book.getPublicationDate())));


            for (int i = 0; i <= books.size() - k; i++) {
                List<Book> selectedBooks = new ArrayList<>();
                selectedBooks.add(books.get(i));
                int minYear = Integer.parseInt(books.get(i).getPublicationDate());
                for (int j = i + 1; j < books.size(); j++) {
                    int currentYear = Integer.parseInt(books.get(j).getPublicationDate());
                    if (currentYear - minYear <= p) {
                        selectedBooks.add(books.get(j));
                        if (selectedBooks.size() >= k) {
                            System.out.println("Solution found for letter " + ch + ":");
                            for (Book book : selectedBooks) {
                                System.out.println(book);
                            }
                            return;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        System.out.println("No valid set of books found.");
    }


}
