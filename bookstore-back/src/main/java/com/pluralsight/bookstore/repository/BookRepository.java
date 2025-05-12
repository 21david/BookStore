package com.pluralsight.bookstore.repository;

import com.pluralsight.bookstore.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)  // (JTA) All read-only methods will only do a transaction if they are being called by another one
public class BookRepository {

    @PersistenceContext(unitName = "bookStorePU")  // This is the persistence-unit in persistence.xml
    private EntityManager em;  // Used to access the database.

    public Book find(Long id) { // (R)ead, like get
        return em.find(Book.class, id);
    }

    @Transactional(REQUIRED)  // (JTA) Required because this is a write operation
    public Book create(Book book) {  // (C)reate, like post
        em.persist(book);
        return book;
    }

    @Transactional(REQUIRED)  // (JTA) Required because this is a write operation
    public void delete(Long id) {  // (D)elete
        em.remove(em.getReference(Book.class, id));
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b order by b.title", Book.class);
        return query.getResultList(); // Actually runs the query above
    }

    public Long countAll() {
        TypedQuery<Long> query = em.createQuery("select count(b) from Book b", Long.class);
        return query.getSingleResult();

    }
}
