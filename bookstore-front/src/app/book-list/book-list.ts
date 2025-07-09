import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'bs-book-list',
  imports: [CommonModule, RouterLink],
  templateUrl: './book-list.html',
  styles: ``
})
export class BookList {
  numBooks: number = 2;

  books = [
    {
      id: "1",
      title: 'The Great Gatsby',
      description: 'A classic novel about the American Dream',
      imageURL: 'https://images.penguinrandomhouse.com/cover/9780743273565',
      author: 'F. Scott Fitzgerald',
      price: 10.99
    },
    {
      id: "2",
      title: '1984',
      description: 'A dystopian novel about a totalitarian society',
      imageURL: 'https://images.penguinrandomhouse.com/cover/9780451524935',
      author: 'George Orwell',
      price: 12.99
    }
  ];
}
