package com.pluralsight.bookstore.web;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.repository.BookRepository;
import org.thymeleaf.context.WebContext;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book-details/*")
public class BookDetailsController extends HttpServlet {

    @Inject
    private BookRepository bookRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        Long id = Long.parseLong(pathInfo.substring(1));
        Book book = bookRepository.find(id);

        WebContext context = new WebContext(request, response, getServletContext());
        context.setVariable("book", book);
        TemplateEngineUtil.getTemplateEngine(getServletContext()).process("bookdetails.html", context, response.getWriter());
    }
}
