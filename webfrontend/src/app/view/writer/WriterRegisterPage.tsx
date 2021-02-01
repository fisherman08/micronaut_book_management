import React, { useState } from "react";
import { BackButton } from "../common/BackButton";
import { WriterEditForm } from "./WriterEditForm";
import { useRegisterWriter } from "../../hooks/writer/useRegisterWriter";
import { useHistory } from "react-router-dom";
import { Paths } from "../../../Paths";

export const WriterRegisterPage = () => {
    const history = useHistory();
    const [name, setName] = useState("");
    const handleNameChanged = (newName: string) => {
        setName(newName);
    };

    const register = useRegisterWriter();

    const handleRegisterButtonClicked = () => {
        if (!name) {
            alert("名前を入力してください");
            return;
        }

        register(
            name,
            () => {
                window.alert("登録しました");
                history.push(Paths.writer.list)
            },
            () => {
                window.alert("登録できませんでした");
            }
        );
    };

    return (
        <div className={"writer_register_page"}>
            <div className={"page_title"}>著者追加</div>
            <BackButton/>
            <div>
                <WriterEditForm name={name} onNameChanged={handleNameChanged}/>
                <div>
                    <button type={"button"} onClick={handleRegisterButtonClicked}>追加する</button>
                </div>
            </div>
        </div>
    );
}
