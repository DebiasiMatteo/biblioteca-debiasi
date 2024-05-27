package it.marconi.biblioteca_debiasi.biblioControllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.marconi.biblioteca_debiasi.biblioDomains.BookForm;
import it.marconi.biblioteca_debiasi.biblioServices.BiblioService;

@Controller
@RequestMapping("/")
public class BiblioController {
    private int originalId;

    @Autowired
    BiblioService biblioService;

    @GetMapping("/biblio")
    public ModelAndView viewBiblio() {
        return new ModelAndView("biblio-page").addObject("books", biblioService.getBooks());
    }

    @GetMapping("/svuota")
    public ModelAndView emptyBiblio() {
        biblioService.empty();

        return new ModelAndView("redirect:/biblio");
    }

    @GetMapping("/libro/{isbn}/dettagli")
    public ModelAndView bookDetails(@PathVariable("isbn") int isbn) {
        Optional<BookForm> book = biblioService.getBookByIsbn(isbn);
        return new ModelAndView("book-details").addObject("book", book.get());
    }

    @GetMapping("/libro/{isbn}")
    public ModelAndView bookRecap(@PathVariable("isbn") int isbn) {
        Optional<BookForm> book = biblioService.getBookByIsbn(isbn);

        if(book.isPresent())
            return new ModelAndView("book-recap").addObject("book", book.get());

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro non trovato");
    }

    @GetMapping("/libro/nuovo")
    public ModelAndView addNewBook() {
        return new ModelAndView("new-book").addObject("bookForm", new BookForm());
    }

    @PostMapping("/libro/nuovo/handler")
    public ModelAndView handlerNewBook(@ModelAttribute BookForm bookForm) {
        biblioService.addBook(bookForm);

        return new ModelAndView("redirect:/biblio");
    }

    /* 
    @GetMapping("/prodotto/modifica/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id) {
        originalId = id;

        return new ModelAndView("edit-product").addObject("productForm", new ProductForm());
    }

    
    @PostMapping("/prodotto/modifica/handler")
    public ModelAndView handlerEditedProduct(@ModelAttribute ProductForm productForm) {
        ProductForm product = catalogService.getProductById(originalId).get();
        product.editProduct(productForm);

        return new ModelAndView("redirect:/prodotto/" + product.getId());
    }
    */

    @GetMapping("/libro/elimina/{isbn}")
    public ModelAndView deleteBook(
        @PathVariable("isbn") int isbn,
        RedirectAttributes attr
    ) {
        biblioService.deleteBookByIsbn(isbn);

        attr.addFlashAttribute("deleted", true);
        return new ModelAndView("redirect:/biblio");
    }
}