package com.kgisl.springPureJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookDAO bookDAO;

    @GetMapping("/list")
    public String listBooks(Model model) throws SQLException {
        List<Book> books = bookDAO.listAllBooks();
        model.addAttribute("books", books);
        return "book";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "bookInsert";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) throws SQLException {
        bookDAO.insertBooks(book);
        return "redirect:/books/list";
    }


    @RequestMapping("/update/{id}") /*(value = "/update/{id}", method = RequestMethod.GET*/
    public String showUpdateForm(@PathVariable("id") int id, Model model) throws SQLException {
        Book book = bookDAO.getBook(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "editBook";
        } else {
            return "redirect:/books/list";
        }
    }
    @RequestMapping("/update") /*(value = "/update" , method = RequestMethod.POST*/
    public String updateBook(@ModelAttribute("book") Book book) throws SQLException {
        bookDAO.updateBook(book);
        return "redirect:/books/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") int id) throws SQLException {
        bookDAO.delete(id);
        return "redirect:/books/list";
    }
}
