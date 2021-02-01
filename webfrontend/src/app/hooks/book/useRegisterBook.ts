import { useDispatch } from "react-redux";
import { registerBookAction } from "../../../state/usecase/book/actions";

export const useRegisterBook = () => {
    const dispatch = useDispatch();

    return (title: string, authorIds: string[], onSuccess: () => void, onFailure: () => void) => {
        dispatch(registerBookAction(title, authorIds, onSuccess, onFailure))
    };
}
