import React, { useEffect, useState } from "react";
import { BackButton } from "../common/BackButton";
import { WriterEditForm } from "./WriterEditForm";
import { useHistory, useParams } from "react-router-dom";
import { Paths } from "../../../Paths";
import { useWriterInfo } from "../../hooks/writer/useWriterInfo";
import { Loading } from "../common/Loading";
import { useUpdateWriter } from "../../hooks/writer/useUpdateWriter";

interface WriterEditPagePathParams {
    id: string,
}

export const WriterEditPage = () => {
    const { id } = useParams<WriterEditPagePathParams>();
    const writer = useWriterInfo(id);

    const update = useUpdateWriter();

    const history = useHistory();

    const [name, setName] = useState("");

    useEffect(() => {
        if (writer) {
            setName(writer.name)
        }
    }, [writer])

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
            () => {
                window.alert("更新しました");
                history.push(Paths.writer.list)
            },
            () => {
                window.alert("更新できませんでした");
            }
        );
    };

    if (!writer) return <Loading/>;

    return (
        <div className={"writer_edit_page"}>
            <div className={"page_title"}>書籍編集</div>
            <BackButton/>
            <div>
                <WriterEditForm name={name} onNameChanged={handleNameChanged}/>
                <div>
                    <button type={"button"} onClick={handleUpdateButtonClicked}>更新する</button>
                </div>
            </div>
        </div>
    );
}
