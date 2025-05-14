package com.pluralsight.bookstore.web;

import com.pluralsight.bookstore.repository.BookRepository;
import org.thymeleaf.context.WebContext;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class BookController extends HttpServlet {

    @Inject
    private BookRepository bookRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebContext context = new WebContext(request, response, getServletContext());
        context.setVariable("books", bookRepository.findAll());

        // Pass the books to ThymeLeaf booklist.html page
        TemplateEngineUtil.getTemplateEngine(getServletContext()).process("booklist.html", context, response.getWriter());
    }
}