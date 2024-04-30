package Algorithm;

import DAO.BookAuthorsDAO;
import DAO.BookDAO;
import Models.Book;
import Models.ReadingList;
import lombok.Locked;

import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {
    Map<String, List<String>> genreBooks = new HashMap<>();
    Map<String, List<String>> authorsBooks = new HashMap<>();
    Map<String, Boolean> existAuthor = new HashMap<>();
    Map<String, String> existAuthorAndGenre = new HashMap<>();
    DataSource dataSource;

    public Algorithm(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void makeMaps() {
        BookDAO bookDAO = new BookDAO(dataSource);
        BookAuthorsDAO bookAuthorsDAO = new BookAuthorsDAO(dataSource);

        List<Book> books;
        books = bookDAO.getAllBooks();

        List<String> authorsOfBook;

        for (Book book : books) {
            if (!genreBooks.containsKey(book.getGenre())) {
                genreBooks.put(book.getGenre(), new ArrayList<>());
                List<String> temp = genreBooks.getOrDefault(book.getGenre(), new ArrayList<>());
                temp.add(book.getName());
                genreBooks.put(book.getGenre(), temp);
            } else {
                List<String> temp = genreBooks.getOrDefault(book.getGenre(), new ArrayList<>());
                temp.add(book.getName());
                genreBooks.put(book.getGenre(), temp);
            }

            authorsOfBook = bookAuthorsDAO.getBookAuthors(book.getId());
            for (String author : authorsOfBook) {
                if (!existAuthor.containsKey(author)) {
                    existAuthor.put(author, false);
                    existAuthorAndGenre.put(author, null);
                }
            }
        }
    }

    private Map<String, List<String>> sortGenresByBookCount() {
        return genreBooks.entrySet().stream()
                .sorted(Map.Entry.<String, List<String>>comparingByValue(Comparator.comparingInt(List::size)).reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    private void makeReadingList() {
        List<ReadingList> readingLists = new ArrayList<>();
        genreBooks = sortGenresByBookCount();
        List<List<String>> tables = new ArrayList<>();

        BookAuthorsDAO bookAuthorsDAO = new BookAuthorsDAO(dataSource);
        List<String> toAdd = new ArrayList<>();
        genreBooks.forEach((genre, books) -> { // 2587

            for (String book : books) {
                List<String> authors = new ArrayList<>();
                authors = bookAuthorsDAO.getAuthorsByBookTitle(book);
                boolean addCheck = false;
                for (String author : authors) {
                    if (!existAuthor.get(author)){
                        existAuthor.put(author, true);
                        existAuthorAndGenre.put(author, genre);
                        addCheck = true;

                    } else {
                        if(existAuthorAndGenre.get(author) == genre){
                            addCheck = true;
                        }
                    }
                }

                if(addCheck)
                    toAdd.add(book);
            }
            tables.add(toAdd);
            System.out.println(toAdd.size());
            toAdd.clear();
            System.out.println("Genre: " + genre + " has " + books.size() + " books.");
        });

        for(int i =0 ; i < tables.size(); i++){
            System.out.println(tables.get(i).size());
        }
    }

    public void output() {
        makeMaps();
        makeReadingList();

    }
}

