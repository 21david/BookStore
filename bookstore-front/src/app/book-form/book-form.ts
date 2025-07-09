import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'bs-book-form',
  imports: [FormsModule],
  templateUrl: './book-form.html',
  styles: ``
})
export class BookForm {
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
