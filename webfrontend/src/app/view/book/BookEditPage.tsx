import React, { useEffect, useState } from "react";
import { BackButton } from "../common/BackButton";
import { BookEditForm } from "./BookEditForm";
import { useHistory, useParams } from "react-router-dom";
import { Paths } from "../../../Paths";
import { useBookInfo } from "../../hooks/book/useBookInfo";
import { Loading } from "../common/Loading";
import { useUpdateBook } from "../../hooks/book/useUpdateBook";

interface BookEditPagePathParams {
    id: string,
}

export const BookEditPage = () => {
    const { id } = useParams<BookEditPagePathParams>();
    const book = useBookInfo(id);

    const update = useUpdateBook();

    const history = useHistory();

    const [name, setName] = useState("");

    useEffect(() => {
        if (book) {
            setName(book.title)
        }
    }, [book])

    const handleNameChanged = (newName: string) => {
        setName(newName);
    };

    const handleUpdateButtonClicked = () => {
        if (!name) {
            alert("タイトルを入力してください");
            return;
        }
        update(
            id,
            name,
            [],
            () => {
                window.alert("更新しました");
                history.push(Paths.book.list)
            },
            () => {
                window.alert("更新できませんでした");
            }
        );
    };

    if (!book) return <Loading/>;

    return (
        <div className={"book_edit_page"}>
            <div className={"page_title"}>書籍編集</div>
            <BackButton/>
            <div>
                <BookEditForm name={name} onNameChanged={handleNameChanged}/>
                <div>
                    <button type={"button"} onClick={handleUpdateButtonClicked}>更新する</button>
                </div>
            </div>
        </div>
    );
}
