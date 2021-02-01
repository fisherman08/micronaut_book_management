import { useDispatch } from "react-redux";
import { getBookListAction } from "../../../state/usecase/book/actions";

export const useReloadBookList = () => {
    const dispatch = useDispatch();
    return (authorIds: string[]) => {
        dispatch(getBookListAction(authorIds));
    }
}
