package com.pluralsight.bookstore.web;

import com.pluralsight.bookstore.repository.BookRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-book/*")
public class DeleteBookController extends HttpServlet {

    @Inject
    private BookRepository bookRepository;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        Long id = Long.parseLong(pathInfo.substring(1));
        bookRepository.delete(id);
        response.sendRedirect(request.getContextPath() + "/");
    }
}
