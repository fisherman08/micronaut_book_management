import { Book } from "../../../domain/book/Book";
import { useSelector } from "react-redux";
import { State } from "../../../state/store";

export const useBookList = (): Book[] | null => {
    const list = useSelector((state: State) => state.book.bookList);
    return list;
}
