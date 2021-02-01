import { useDispatch } from "react-redux";
import { deleteWriterAction } from "../../../state/usecase/book/actions";

export const useDeleterWriter = () => {
    const dispatch = useDispatch();

    return (id: string) => {
        dispatch(deleteWriterAction(id));
    };
}
