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

export type ActionTypes = GetListActionType |
    GetListSuccessActionType |
    GetListFailureActionType;
