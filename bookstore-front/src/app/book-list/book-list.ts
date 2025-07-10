import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Book } from '../service/model/Book';
import { BookApi } from '../service/api/BookApi';

@Component({
  selector: 'bs-book-list',
  imports: [CommonModule, RouterLink],
  templateUrl: './book-list.html',
  styles: ``
})
export class BookList implements OnInit { 
    public numBooks: number = 0;
    public books: Book[] = [];

    constructor(private bookService: BookApi) { }

    ngOnInit() {
      // Make calls to the Java EE backend using the BookApi.ts service and set the properties of this class
      this.bookService.countBooks().subscribe((numBooks: number) => this.numBooks = numBooks);
      this.bookService.getBooks().subscribe((books: Book[]) => this.books = books);
      // Note: I had to add the CorsFilter.java file to the rest folder in the backend to allow cross-origin 
      // requests from localhost:4200 since they were being blocked because localhost:4200 is a different origin.
    }
}
