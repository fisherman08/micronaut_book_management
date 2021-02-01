import React, { ChangeEvent } from "react";

export const WriterEditForm = (props: WriterEditFormProps) => {
    const { name, onNameChanged } = props;

    const handleNameChanged = (e: ChangeEvent<HTMLInputElement>) => {
        onNameChanged(e.target.value);
    };

    return (
        <div className={"writer_edit_form"}>
            <div>
                <label>
                    名前
                    <input type={"text"} value={name} onChange={handleNameChanged}/>
                </label>
            </div>
        </div>
    );
};

export type WriterEditFormProps = {
    name: string,
    onNameChanged: (newName: string) => void,
};
