import React, { ChangeEvent } from "react";

export const BookEditForm = (props: BookEditFormProps) => {
    const { name, onNameChanged } = props;

    const handleNameChanged = (e: ChangeEvent<HTMLInputElement>) => {
        onNameChanged(e.target.value);
    };

    return (
        <div className={"book_edit_form"}>
            <div>
                <label>
                    タイトル
                    <input type={"text"} value={name} onChange={handleNameChanged}/>
                </label>
            </div>
        </div>
    );
};

export type BookEditFormProps = {
    name: string,
    onNameChanged: (newName: string) => void,
};
