import { TYPE } from "./constants";
import { Book } from "../../../domain/book/Book";

export type GetListActionType = {
    type: typeof TYPE.GET_LIST,
    payload: {}
};

export type GetListSuccessActionType = {
    type: typeof TYPE.GET_LIST_SUCCESS,
    payload: {
        list: Book[]
    }
};

export type GetListSuccessResponseType = {
    id: string,
    title: string,
    authors: {
        id: string,
        name: string,
    }[]
}[];

export type GetListFailureActionType = {
    type: typeof TYPE.GET_LIST_FAILURE,
    payload: {}
};

export type RegisterActionType = {
    type: typeof TYPE.REGISTER,
    payload: {}
};

export type RegisterSuccessActionType = {
    type: typeof TYPE.REGISTER_SUCCESS,
    payload: {}
};

export type RegisterFailureActionType = {
    type: typeof TYPE.REGISTER_FAILURE,
    payload: {}
};

export type ActionTypes = GetListActionType |
    GetListSuccessActionType |
    GetListFailureActionType |
    RegisterActionType |
    RegisterSuccessActionType |
    RegisterFailureActionType;
