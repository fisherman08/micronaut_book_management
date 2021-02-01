import { useDispatch } from "react-redux";
import { updateWriterAction } from "../../../state/usecase/writer/actions";

export const useUpdateWriter = () => {
    const dispatch = useDispatch();

    return (id: string, name: string, onSuccess: () => void, onFailure: () => void) => {
        dispatch(updateWriterAction(id, name, onSuccess, onFailure))
    };
}
