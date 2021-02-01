import React from "react";
import { Link } from "react-router-dom";
import { Paths } from "../../Paths";

export function IndexPage() {
    return (
        <div>
            <div className={"page_title"}>ようこそ</div>
            <div>
                <Link to={Paths.book.list}>書籍一覧</Link>
                <Link to={Paths.writer.list}>著者一覧</Link>
            </div>
        </div>
    );
}
