import { useDispatch, useSelector } from "react-redux";
import { getBookInfoAction } from "../../../state/usecase/book/actions";
import { State } from "../../../state/store";
import { useEffect } from "react";

export const useBookInfo = (id: string) => {
    const dispatch = useDispatch();
    const info = useSelector((state: State) => state.book.currentInfo);

    useEffect(() => {
        dispatch(getBookInfoAction(id));
    },[id, dispatch]);

    return info;
}
