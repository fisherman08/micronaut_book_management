import React from "react";
import { useHistory } from "react-router-dom";

export function BackButton() {

    const history = useHistory();

    const handleBackButtonClicked = () => {
        history.goBack();
    };

    if (!history.length) return null;

    return (
        <div className={"back_button"}>
            <button className={"back_button__button"} type="button" onClick={handleBackButtonClicked}>前のページに戻る</button>
        </div>
    );
}
