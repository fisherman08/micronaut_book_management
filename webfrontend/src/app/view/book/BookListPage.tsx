import React from "react";
import { useBookList } from "../../hooks/book/useBookList";
import { Loading } from "../common/Loading";
import { Book } from "../../../domain/book/Book";
import { Link } from "react-router-dom";
import { Paths } from "../../../Paths";

export const BookListPage = () => {

    const bookList = useBookList();

    if (!bookList) return <Loading/>;

    return (
        <div>
            <div className={"page_title"}>書籍一覧</div>
            <div>
                <Link to={Paths.book.register}>書籍を追加する</Link>
            </div>
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
