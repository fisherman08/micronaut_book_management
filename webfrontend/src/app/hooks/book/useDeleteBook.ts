import { useDispatch } from "react-redux";
import { deleteBookAction } from "../../../state/usecase/book/actions";

export const useDeleterBook = () => {
    const dispatch = useDispatch();

    return (id: string) => {
        dispatch(deleteBookAction(id));
    };
}
