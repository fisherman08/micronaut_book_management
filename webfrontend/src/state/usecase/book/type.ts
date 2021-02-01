import { TYPE } from "./constants";

export type GetListActionType = {
    type: typeof TYPE.GET_LIST,
    payload: {}
};


export type ActionTypes = GetListActionType
