import { Component } from '@angular/core';

@Component({
  selector: 'bs-book-detail',
  imports: [],
  templateUrl: './book-detail.html',
  styles: ``
})
export class BookDetail {
  book = {
    "title": "Dummy Book",
    "description": "This is a dummy book",
    "unitCost": "123",
    "isbn": "123-3456-789",
    "numOfPages": "456",
    "language": "English",
    "imageURL": "https://images.penguinrandomhouse.com/cover/9780743273565",
    "author": "Dummy Author",
  }
}
