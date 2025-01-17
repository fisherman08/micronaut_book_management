import React, { useState } from "react";
import { BackButton } from "../common/BackButton";
import { BookEditForm } from "./BookEditForm";
import { useRegisterBook } from "../../hooks/book/useRegisterBook";
import { useHistory } from "react-router-dom";
import { Paths } from "../../../Paths";

export const BookRegisterPage = () => {
    const history = useHistory();

    const [name, setName] = useState("");
    const handleNameChanged = (newName: string) => {
        setName(newName);
    };

    const [authorIds, setAuthorIds] = useState<string[]>([]);
    const handleAuthorChanged = (newAuthorIds: string[]) => {
        setAuthorIds(newAuthorIds);
    };

    const register = useRegisterBook();

    const handleRegisterButtonClicked = () => {
        if (!name) {
            alert("タイトルを入力してください");
            return;
        }

        register(
            name,
            authorIds,
            () => {
                window.alert("登録しました");
                history.push(Paths.book.list)
            },
            () => {
                window.alert("登録できませんでした");
            }
        );
    };

    return (
        <div className={"book_register_page"}>
            <div className={"page_title"}>書籍追加</div>
            <BackButton/>
            <div>
                <BookEditForm name={name} onNameChanged={handleNameChanged} authorIds={authorIds}
                              onAuthorsChanged={handleAuthorChanged}/>
                <div>
                    <button type={"button"} onClick={handleRegisterButtonClicked}>追加する</button>
                </div>
            </div>
        </div>
    );
}
