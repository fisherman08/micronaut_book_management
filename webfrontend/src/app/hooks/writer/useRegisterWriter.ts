import { useDispatch } from "react-redux";
import { registerWriterAction } from "../../../state/usecase/writer/actions";

export const useRegisterWriter = () => {
    const dispatch = useDispatch();

    return (name: string, onSuccess: () => void, onFailure: () => void) => {
        dispatch(registerWriterAction(name, onSuccess, onFailure))
    };
}
