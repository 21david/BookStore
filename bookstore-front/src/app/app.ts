import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { BookList } from './book-list/book-list';
import { BookDetail } from './book-detail/book-detail';
import { BookForm } from './book-form/book-form';

@Component({  // Decorator marking this component as a graphical component
  selector: 'bs-root',  // name of the component, used in the html file like <bs-root></bs-root>
  imports: [RouterOutlet, BookList, BookDetail, BookForm],
  templateUrl: './app.html',  // html file of this component
  styles: [],
})
export class App {
  protected title = 'Nile BookStore';
}
