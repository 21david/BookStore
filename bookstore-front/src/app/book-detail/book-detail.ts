import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { BookApi } from '../service';
import { Book } from '../service/model/Book';
import { map, switchMap, finalize } from 'rxjs/operators';

@Component({
  selector: 'bs-book-detail',
  imports: [FormsModule],
  templateUrl: './book-detail.html',
  styles: ``
})
export class BookDetail implements OnInit {
  book: Book = new Book();

  constructor(private router: Router, private bookService: BookApi, private route: ActivatedRoute) {}
  // ActivatedRoute API gives us details of the current route

  ngOnInit() {
    // Get the parameter (id) passed through the URI
    this.route.params
      .pipe(
        map((params: { [x: string]: any; }) => params['bookId']),
        switchMap((id: number) => this.bookService.getBook(id))
      )
      .subscribe((book: any) => this.book = book);
  }

  delete() {
    if (!this.book.id) {
      this.router.navigate(['/book-list']);
      return;
    }
    
    // Invoke our REST API to delete the book
    this.bookService.deleteBook(this.book.id)
      .pipe(
        // When done, navigate back to the list of books through the router
        finalize(() => this.router.navigate(['/book-list']))
      )
      .subscribe();
  }
}
