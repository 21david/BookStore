package com.pluralsight.bookstore.model;

import javax.persistence.Entity;

/**
 * Created by david1 on 6/21/25.
 */
@Entity
public class Book {

    private Long id;

    private String title;

    private String description;

    private Float unitCost;

    private String isbn;

    private Date publicationDate;

    private Integer nbOfPages;

    private String imageUrl;
}
