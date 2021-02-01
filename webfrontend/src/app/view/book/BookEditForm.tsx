import React, { ChangeEvent, useState } from "react";
import { Writer } from "../../../domain/writer/Writer";
import { Loading } from "../common/Loading";
import { useWriterList } from "../../hooks/writer/useWriterList";

export const BookEditForm = (props: BookEditFormProps) => {
    const { name, onNameChanged, authorIds, onAuthorsChanged } = props;

    const writers = useWriterList();

    const handleNameChanged = (e: ChangeEvent<HTMLInputElement>) => {
        onNameChanged(e.target.value);
    };

    const handleAuthorChecked = (e: ChangeEvent<HTMLInputElement>) => {
        const checked = e.target.checked;
        const id = e.target.value;

        if (!checked && authorIds.includes(id)) {
            onAuthorsChanged(
                [...authorIds].filter( it => it !== id)
            )
        }

        if (checked && !authorIds.includes(id)) {
            onAuthorsChanged(
                [...authorIds, id]
            )
        }
    }

    const renderAuthor = (writer: Writer) => {
        return (
            <label key={writer.id}>
                <input type={"checkbox"} value={writer.id} checked={authorIds.includes(writer.id)} onChange={handleAuthorChecked}/>
                <span>{writer.name}</span>
            </label>
        );
    }

    if (!writers) return <Loading/>;
    return (
        <div className={"book_edit_form"}>
            <div>
                <label>
                    タイトル:
                    <input type={"text"} value={name} onChange={handleNameChanged}/>
                </label>
            </div>
            <div>
                <span>著者: </span>
                {writers.map(writer => renderAuthor(writer))}
            </div>
        </div>
    );
};

export type BookEditFormProps = {
    name: string,
    onNameChanged: (newName: string) => void,
    authorIds: string[],
    onAuthorsChanged: (newAuthorIds: string[]) => void
};
