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

export type GetListFailureActionType = {
    type: typeof TYPE.GET_LIST_FAILURE,
    payload: {}
};

export type GetListSuccessResponseType = {
    id: string,
    title: string,
    authors: {
        id: string,
        name: string,
    }[]
}[];

export type GetInfoActionType = {
    type: typeof TYPE.GET_INFO,
    payload: {}
};

export type GetInfoSuccessActionType = {
    type: typeof TYPE.GET_INFO_SUCCESS,
    payload: {
        info: Book
    }
};

export type GetInfoSuccessResponseType = {
    id: string,
    title: string,
    authors: {
        id: string,
        name: string,
    }[]
};

export type GetInfoFailureActionType = {
    type: typeof TYPE.GET_INFO_FAILURE,
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

export type UpdateActionType = {
    type: typeof TYPE.UPDATE,
    payload: {}
};

export type UpdateSuccessActionType = {
    type: typeof TYPE.UPDATE_SUCCESS,
    payload: {}
};

export type UpdateFailureActionType = {
    type: typeof TYPE.UPDATE_FAILURE,
    payload: {}
};

export type DeleteActionType = {
    type: typeof TYPE.DELETE,
    payload: {}
};

export type DeleteSuccessActionType = {
    type: typeof TYPE.DELETE_SUCCESS,
    payload: {}
};

export type DeleteFailureActionType = {
    type: typeof TYPE.DELETE_FAILURE,
    payload: {}
};


export type ActionTypes = GetListActionType |
    GetListSuccessActionType |
    GetListFailureActionType |
    GetInfoActionType |
    GetInfoSuccessActionType |
    GetInfoFailureActionType |
    RegisterActionType |
    RegisterSuccessActionType |
    RegisterFailureActionType |
    UpdateActionType |
    UpdateSuccessActionType |
    UpdateFailureActionType |
    DeleteActionType |
    DeleteSuccessActionType |
    DeleteFailureActionType;
