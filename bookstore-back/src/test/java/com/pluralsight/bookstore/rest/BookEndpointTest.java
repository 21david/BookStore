package com.pluralsight.bookstore.rest;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.model.Language;
import com.pluralsight.bookstore.repository.BookRepository;
import com.pluralsight.bookstore.util.IsbnGenerator;
import com.pluralsight.bookstore.util.NumberGenerator;
import com.pluralsight.bookstore.util.TextUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.Date;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@RunAsClient
public class BookEndpointTest {
    
    @Test
    public void createBook(@ArquillianResource URL baseURL) throws Exception {
        // Create a REST client
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseURL.toURI()).path("api/books");

        // Test counting books
        Response response = webTarget.path("count").request().get();  // Get count of books
        assertEquals(NO_CONTENT.getStatusCode(), response.getStatus());

        // Test find all
        response = webTarget.request(APPLICATION_JSON).get();  // Get all books I think
        assertEquals(NO_CONTENT.getStatusCode(), response.getStatus());

        // Create a book
        Book book = new Book("isbn",
                "The  Title",
                12F,
                123,
                Language.ENGLISH,
                new Date(),
                "http://example.com/image",
                "This is an example description of an awesome book.");
        response = webTarget.request(APPLICATION_JSON).post(Entity.entity(book, APPLICATION_JSON));
        assertEquals(CREATED.getStatusCode(), response.getStatus());
        
        client.close();
    }

    @Deployment(testable = false)  // Telling ShrinkWrap not to package the test class inside the archive (.war)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClass(BookRepository.class)
                .addClass(Book.class)
                .addClass(Language.class)
                .addClass(TextUtil.class)
                .addClass(NumberGenerator.class)
                .addClass(IsbnGenerator.class)
                .addClass(BookEndpoint.class)
                .addClass(JAXRSConfiguration.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("META-INF/persistence.xml", "classes/META-INF/persistence.xml");
    }
}
