import { useDispatch, useSelector } from "react-redux";
import { getWriterInfoAction } from "../../../state/usecase/writer/actions";
import { State } from "../../../state/store";
import { useEffect } from "react";

export const useWriterInfo = (id: string) => {
    const dispatch = useDispatch();
    const info = useSelector((state: State) => state.writer.currentInfo);

    useEffect(() => {
        dispatch(getWriterInfoAction(id));
    },[id, dispatch]);

    return info;
}
