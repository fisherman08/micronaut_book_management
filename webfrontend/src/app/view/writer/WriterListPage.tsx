import React from "react";
import { useWriterList } from "../../hooks/writer/useWriterList";
import { Loading } from "../common/Loading";
import { Writer } from "../../../domain/writer/Writer";
import { Link } from "react-router-dom";
import { Paths } from "../../../Paths";
import { useDeleterWriter } from "../../hooks/writer/useDeleteWriter";

export const WriterListPage = () => {

    const writerList = useWriterList();
    const deleteWriter = useDeleterWriter();

    const handleDelete = (id: string) => {
        if (!window.confirm("削除しますか?")) return;
        deleteWriter(id);
    };

    if (!writerList) return <Loading/>;

    const renderWriter = (writer: Writer) => {
        return (
            <tr key={writer.id}>
                <td>{writer.name}</td>
                <td>
                    <div>
                        <button type={"button"} onClick={ () => handleDelete(writer.id)}>削除</button>
                        <Link to={Paths.writer.edit.replace(/:id/g, writer.id)}>編集</Link>
                    </div>
                </td>
            </tr>
        );
    };

    return (
        <div>
            <div className={"page_title"}>著者一覧</div>
            <div>
                <Link to={Paths.writer.register}>著者を追加する</Link>
            </div>
            <table>
                <thead>
                <tr>
                    <th>タイトル</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {writerList.map( writer => {
                    return renderWriter(writer);
                })}
                </tbody>
            </table>
        </div>
    );
};

