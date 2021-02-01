import { useDispatch } from "react-redux";
import { registerBookAction, updateBookAction } from "../../../state/usecase/book/actions";

export const useUpdateBook = () => {
    const dispatch = useDispatch();

    return (id: string, title: string, authorIds: string[], onSuccess: () => void, onFailure: () => void) => {
        dispatch(updateBookAction(id, title, authorIds, onSuccess, onFailure))
    };
}
