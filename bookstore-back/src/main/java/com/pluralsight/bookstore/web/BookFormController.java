package com.pluralsight.bookstore.web;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.model.Language;
import com.pluralsight.bookstore.repository.BookRepository;
import com.pluralsight.bookstore.util.IsbnGenerator;
import org.thymeleaf.context.WebContext;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/book-form")
public class BookFormController extends HttpServlet {

    @Inject
    private BookRepository bookRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebContext context = new WebContext(request, response, getServletContext());
        context.setVariable("languages", Language.values());
        TemplateEngineUtil.getTemplateEngine(getServletContext()).process("bookform.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create a new book object from the request parameters
        // Each parameter corresponds to the 'name' attribute in the input elements in the form
        Book book = new Book();
        book.setTitle(request.getParameter("title"));
        book.setDescription(request.getParameter("description"));
        book.setUnitCost(Float.parseFloat(request.getParameter("unitCost")));
        book.setNbOfPages(Integer.parseInt(request.getParameter("nbOfPages")));
        book.setImageUrl(request.getParameter("imageUrl"));
        book.setLanguage(Language.valueOf(request.getParameter("language")));
        
        // For ISBN and Publication Date, let's set some defaults for now
        book.setIsbn(new IsbnGenerator().generateNumber());
        book.setPublicationDate(new Date());

        bookRepository.create(book);

        // Redirect to the book list page to see the new book
        response.sendRedirect(request.getContextPath() + "/");
    }
}
