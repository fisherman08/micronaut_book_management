import React from "react";
import { useBookList } from "../../hooks/book/useBookList";
import { Loading } from "../common/Loading";
import { Book } from "../../../domain/book/Book";

export const BookListPage = () => {

    const bookList = useBookList();

    if (!bookList) return <Loading/>;

    return (
        <div>
            <div>Book List</div>
            <table>
                <thead>
                <tr>
                    <th>タイトル</th>
                    <th>著者</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {bookList.map( book => {
                    return renderBook(book);
                })}
                </tbody>
            </table>
        </div>
    );
};

const renderBook = (book: Book) => {
    return (
        <tr key={book.id}>
            <td>{book.title}</td>
            <td>{book.authors.map( author => <p>{author.name}</p>)}</td>
            <td></td>
        </tr>
    );
};
