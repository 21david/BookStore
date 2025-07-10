import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Book } from '../service/model/Book';
import { BookApi } from '../service/api/BookApi';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'bs-book-form',
  imports: [FormsModule, RouterLink],
  templateUrl: './book-form.html',
  styles: ``
})
export class BookForm {
  book: Book = new Book();

  constructor(private router: Router, private bookService: BookApi) { }

  ngOnInit() { }

  create() {
    // Create a copy of the book without the ID (since it's auto-generated)
    // const bookToCreate = { ...this.book };
    // delete bookToCreate.id;
    // this.bookService.createBook(bookToCreate)
    this.bookService.createBook(this.book)
      .pipe(
        // When done, navigate back to the list of books through the router
        finalize(() => this.router.navigate(['/book-list']))
      )
      .subscribe(
        (book) => {
          console.log('Book created successfully:', book);
        },
        (error) => {
          console.error('Error creating book:', error);
        }
      );
  }
}
