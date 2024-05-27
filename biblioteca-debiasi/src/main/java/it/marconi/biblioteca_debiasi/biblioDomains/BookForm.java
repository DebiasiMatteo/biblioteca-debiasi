package it.marconi.biblioteca_debiasi.biblioDomains;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookForm {
    private long isbn;
    private String titolo;
    private String autore;
    private String genere;
    private int annoPubblicazione;

    public BookForm(long isbn, String titolo, String autore, String genere, int annoPubblicazione) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.autore = autore;
        this.genere = genere;
        this.annoPubblicazione = annoPubblicazione;
    }

    /* 
    private autoreSinstassi(String autore){

        return null;
    };
    */
}