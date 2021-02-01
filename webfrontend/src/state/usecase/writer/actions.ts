import { TYPE } from "./constants";
import { Dispatch } from "redux";
import { GetInfoSuccessResponseType, GetListSuccessResponseType } from "./type";
import { ApiSuccessHandler, defaultApiSuccessHandler } from "../../api/SuccessHandler";
import { ApiFailureHandler, defaultApiFailureHandler } from "../../api/FailureHandler";
import { deleteApiCall, getInfoApiCall, getListApiCall, registerApiCall, updateApiCall } from "./api";
import { Writer } from "../../../domain/writer/Writer";


export const getWriterListAction = () => (dispatch: Dispatch) => {
    dispatch({
        type: TYPE.GET_LIST,
        payload: {}
    });
    getListApiCall(getWriterListSuccessAction(dispatch), getWriterListFailureAction(dispatch));
}

const getWriterListSuccessAction = (dispatch: Dispatch): ApiSuccessHandler => {
    return defaultApiSuccessHandler((books: GetListSuccessResponseType) => {
       dispatch({
           type: TYPE.GET_LIST_SUCCESS,
           payload: {
               list: books.map( writer => {
                   return new Writer(
                       writer.id,
                       writer.name,
                   )
               })
           }
       });
    });
};

const getWriterListFailureAction = (dispatch: Dispatch): ApiFailureHandler => {
    return defaultApiFailureHandler((data) => {
        dispatch({
            type: TYPE.GET_LIST_FAILURE,
            payload: {}
        });
    });
}

export const getWriterInfoAction = (id: string) => (dispatch: Dispatch) => {
    dispatch({
        type: TYPE.GET_INFO,
        payload: {}
    });
    getInfoApiCall(id, getWriterInfoSuccessAction(dispatch), getWriterInfoFailureAction(dispatch));
}

const getWriterInfoSuccessAction = (dispatch: Dispatch): ApiSuccessHandler => {
    return defaultApiSuccessHandler((writer: GetInfoSuccessResponseType) => {
        dispatch({
            type: TYPE.GET_INFO_SUCCESS,
            payload: {
                info: new Writer(
                    writer.id,
                    writer.name,
                )
            }
        });
    });
};

const getWriterInfoFailureAction = (dispatch: Dispatch): ApiFailureHandler => {
    return defaultApiFailureHandler((data) => {
        dispatch({
            type: TYPE.GET_INFO_FAILURE,
            payload: {}
        });
    });
}

export const registerWriterAction = (title: string, onSuccess: () => void, onFailure: () => void) => (dispatch: Dispatch) => {
    dispatch({
        type: TYPE.REGISTER,
        payload: {}
    });
    registerApiCall(title, registerWriterSuccessAction(dispatch, onSuccess), registerWriterFailureAction(dispatch, onFailure));
}

const registerWriterSuccessAction = (dispatch: Dispatch, onSuccess: () => void): ApiSuccessHandler => {
    return defaultApiSuccessHandler(() => {
        dispatch({
            type: TYPE.REGISTER_SUCCESS,
            payload: {}
        });
        onSuccess();
    });
};

const registerWriterFailureAction = (dispatch: Dispatch, onFailure: () => void): ApiSuccessHandler => {
    return defaultApiSuccessHandler(() => {
        dispatch({
            type: TYPE.REGISTER_FAILURE,
            payload: {}
        });
        onFailure();
    });
};

export const updateWriterAction = (id: string, title: string, onSuccess: () => void, onFailure: () => void) => (dispatch: Dispatch) => {
    dispatch({
        type: TYPE.UPDATE,
        payload: {}
    });
    updateApiCall(id, title, updateWriterSuccessAction(dispatch, onSuccess), updateWriterFailureAction(dispatch, onFailure));
}

const updateWriterSuccessAction = (dispatch: Dispatch, onSuccess: () => void): ApiSuccessHandler => {
    return defaultApiSuccessHandler(() => {
        dispatch({
            type: TYPE.UPDATE_SUCCESS,
            payload: {}
        });
        onSuccess();
    });
};

const updateWriterFailureAction = (dispatch: Dispatch, onFailure: () => void): ApiSuccessHandler => {
    return defaultApiSuccessHandler(() => {
        dispatch({
            type: TYPE.UPDATE_FAILURE,
            payload: {}
        });
        onFailure();
    });
};

export const deleteWriterAction = (id: string) => (dispatch: Dispatch) => {
    dispatch({
        type: TYPE.DELETE,
        payload: {}
    });
    deleteApiCall(id, deleteWriterSuccessAction(dispatch), deleteWriterFailureAction(dispatch));
}

const deleteWriterSuccessAction = (dispatch: Dispatch): ApiSuccessHandler => {
    return defaultApiSuccessHandler(() => {
        dispatch({
            type: TYPE.DELETE_SUCCESS,
            payload: {}
        });
    });
};

const deleteWriterFailureAction = (dispatch: Dispatch): ApiSuccessHandler => {
    return defaultApiSuccessHandler(() => {
        dispatch({
            type: TYPE.DELETE_FAILURE,
            payload: {}
        });
    });
};
