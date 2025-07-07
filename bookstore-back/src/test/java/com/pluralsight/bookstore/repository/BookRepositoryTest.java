package com.pluralsight.bookstore.repository;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.model.Language;
import com.pluralsight.bookstore.util.IsbnGenerator;
import com.pluralsight.bookstore.util.NumberGenerator;
import com.pluralsight.bookstore.util.TextUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)  // Integration tests, tested with 'mvn test' (maven)
public class BookRepositoryTest {

    @Inject
    private BookRepository bookRepository;

    @Test(expected = Exception.class)
    public void findWithInvalidId() {
        bookRepository.find(null);
    }

    @Test(expected = Exception.class) // Expecting an exception
    public void createInvalidBook() {
        Book book = new Book("isbn",
                null,  // null title shouldn't be allowed
                12F,
                123,
                Language.ENGLISH,
                new Date(),
                "http://example.com/image",
                "This is an example description of an awesome book.");
        book = bookRepository.create(book);  // persist to database
    }

    @Test
    public void create() throws Exception {
        // Test counting books
        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());

        // Create a book
        Book book = new Book("isbn",
                "The  Title",  // 2 spaces to test textUtil.sanitize()
                12F,
                123,
                Language.ENGLISH,
                new Date(),
                "http://example.com/image",
                "This is an example description of an awesome book.");
        book = bookRepository.create(book);  // persist to database
        Long bookId = book.getId();  // get ID that was created

        // Check if the book was created in the database
        assertNotNull(bookId);

        // Search for created book in the repository
        Book bookFound = bookRepository.find(bookId);

        // Validate the found book
        assertNotNull(bookFound);
        assertEquals("The Title", bookFound.getTitle());
        assertTrue(bookFound.getIsbn().startsWith("13"));

        // There should be 1 book now
        assertEquals(Long.valueOf(1), bookRepository.countAll());
        assertEquals(1, bookRepository.findAll().size());

        // Delete the book
        bookRepository.delete(bookId);

        // There should be 0 books now
        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());

    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BookRepository.class)
                .addClass(Book.class)
                .addClass(Language.class)
                .addClass(TextUtil.class)
                .addClass(NumberGenerator.class)
                .addClass(IsbnGenerator.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
    }

}
