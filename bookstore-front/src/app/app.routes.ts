import { Routes } from '@angular/router';
import {BookList} from "./book-list/book-list";
import {BookForm} from "./book-form/book-form";
import {BookDetail} from "./book-detail/book-detail";

export const routes: Routes = [
  { path: '', component: BookList },  // Make the homepage show the BookList component by default
  { path: 'book-list', component: BookList },
  { path: 'book-form', component: BookForm },
  { path: 'book-detail/:bookId', component: BookDetail }
];
