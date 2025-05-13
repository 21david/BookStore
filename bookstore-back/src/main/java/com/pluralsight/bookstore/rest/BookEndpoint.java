package com.pluralsight.bookstore.rest;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.repository.BookRepository;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/books")
public class BookEndpoint {

    @Inject
    private BookRepository bookRepository;


    @GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)  // Produce a JSON object of the book
    public Response getBook(@PathParam("id") @Min(1) Long id) {
        Book book = bookRepository.find(id);

        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(book).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)  // Consume the JSON that is sent in
    public Response createBook(Book book, @Context UriInfo uriInfo) {
        book = bookRepository.create(book);
        URI createdURI = uriInfo.getAbsolutePathBuilder().path(book.getId().toString()).build();
        return Response.created(createdURI).build();
    }

    @DELETE
    @Path("/{id : \\d+}")  // Extract the id that the user typed in the URL
    public Response deleteBook(@PathParam("id") @Min(1) Long id) {
        bookRepository.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    // Return all the books in the database in JSON format
    public Response getBooks() {
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty())
            return Response.noContent().build();

        return Response.ok(books).build();
    }

    @GET
    @Path("/count")
    @Produces(TEXT_PLAIN)
    public Response countBooks() {
        Long numOfBooks = bookRepository.countAll();

        if (numOfBooks == 0)
            return Response.noContent().build();

        return Response.ok(numOfBooks).build();
    }
}
