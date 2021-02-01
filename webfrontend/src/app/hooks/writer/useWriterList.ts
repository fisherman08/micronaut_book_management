import { Writer } from "../../../domain/writer/Writer";
import { useDispatch, useSelector } from "react-redux";
import { State } from "../../../state/store";
import { useEffect } from "react";
import { getWriterListAction } from "../../../state/usecase/writer/actions";

export const useWriterList = (): Writer[] | null => {
    const dispatch = useDispatch();
    const list = useSelector((state: State) => state.writer.writerList);
    useEffect(() => {
        if (list === null) {
            dispatch(getWriterListAction());
        }
    });

    return list;
}
