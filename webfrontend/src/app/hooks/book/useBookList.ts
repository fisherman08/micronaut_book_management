import { Book } from "../../../domain/book/Book";
import { useDispatch, useSelector } from "react-redux";
import { State } from "../../../state/store";
import { useEffect } from "react";
import { getBookListAction } from "../../../state/usecase/book/actions";

export const useBookList = (): Book[] | null => {
    const dispatch = useDispatch();
    const list = useSelector((state: State) => state.book.bookList);
    useEffect(() => {
        if (list === null) {
            dispatch(getBookListAction());
        }
    });

    return list;
}
