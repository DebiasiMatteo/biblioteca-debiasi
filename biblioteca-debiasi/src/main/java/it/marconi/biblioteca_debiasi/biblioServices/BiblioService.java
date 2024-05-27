package it.marconi.biblioteca_debiasi.biblioServices;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.marconi.biblioteca_debiasi.biblioDomains.BookForm;

@Service
public class BiblioService {
    private ArrayList<BookForm> books = new ArrayList<>();

    public ArrayList<BookForm> getBooks() {
        return books;
    }

    public void addBook(BookForm newBook) {
        books.add(newBook);
    }

    public Optional<BookForm> getBookByIsbn(int isbn) {
        for (BookForm b : books)
            if(b.getIsbn() == isbn)
                return Optional.of(b);
            
        return Optional.empty();
    }

    public void empty() {
        books.clear();
    }

    public void deleteBookByIsbn(int isbn) {
        for (BookForm b : books){
            if(b.getIsbn() == isbn)
                books.remove(b);
                return;
            }
        }
}
