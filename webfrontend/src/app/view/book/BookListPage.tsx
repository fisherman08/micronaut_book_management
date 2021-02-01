import React, { ChangeEvent, useEffect, useState } from "react";
import { useBookList } from "../../hooks/book/useBookList";
import { Loading } from "../common/Loading";
import { Book } from "../../../domain/book/Book";
import { Link } from "react-router-dom";
import { Paths } from "../../../Paths";
import { useDeleterBook } from "../../hooks/book/useDeleteBook";
import { useWriterList } from "../../hooks/writer/useWriterList";
import { Writer } from "../../../domain/writer/Writer";
import { useReloadBookList } from "../../hooks/book/useReloadBookList";

export const BookListPage = () => {

    const [authorIds, setAuthorIds] = useState<string[]>([]);

    const bookList = useBookList();
    const deleteBook = useDeleterBook();
    const writerList = useWriterList();

    const reload = useReloadBookList();

    useEffect(() => {
        reload(authorIds);
    }, [])

    const handleDelete = (id: string) => {
        if (!window.confirm("削除しますか?")) return;
        deleteBook(id);
    };

    const handleAuthorChecked = (e: ChangeEvent<HTMLInputElement>) => {
        const checked = e.target.checked;
        const id = e.target.value;
        if (!checked && authorIds.includes(id)) {
            setAuthorIds(
                [...authorIds].filter( it => it !== id)
            )
        }

        if (checked && !authorIds.includes(id)) {
            setAuthorIds(
                [...authorIds, id]
            )
        }
    }

    const handleReloadClicked = () => {
        reload(authorIds);
    };

    const renderAuthor = (writer: Writer) => {
        return (
            <label key={writer.id}>
                <input type={"checkbox"} value={writer.id} checked={authorIds.includes(writer.id)} onChange={handleAuthorChecked}/>
                <span>{writer.name}</span>
            </label>
        );
    }

    if (!bookList || !writerList) return <Loading/>;

    const renderBook = (book: Book) => {
        return (
            <tr key={book.id}>
                <td>{book.title}</td>
                <td>{book.authors.map( author => <p key={author.id}>{author.name}</p>)}</td>
                <td>
                    <div>
                        <button type={"button"} onClick={ () => handleDelete(book.id)}>削除</button>
                        <Link to={Paths.book.edit.replace(/:id/g, book.id)}>編集</Link>
                    </div>
                </td>
            </tr>
        );
    };

    return (
        <div>
            <div className={"page_title"}>書籍一覧</div>
            <div>
                <Link to={Paths.book.register}>書籍を追加する</Link>
            </div>
            <div>
                <p>検索条件</p>
                <div>
                    <h5>著者</h5>
                    {writerList.map(writer => renderAuthor(writer))}
                </div>
                <div>
                    <button type={"button"} onClick={handleReloadClicked}>検索</button>
                </div>
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

