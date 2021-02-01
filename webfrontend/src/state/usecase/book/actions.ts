import { TYPE } from "./constants";
import { Dispatch } from "redux";
import { GetInfoSuccessResponseType, GetListSuccessResponseType } from "./type";
import { ApiSuccessHandler, defaultApiSuccessHandler } from "../../api/SuccessHandler";
import { ApiFailureHandler, defaultApiFailureHandler } from "../../api/FailureHandler";
import { deleteApiCall, getInfoApiCall, getListApiCall, registerApiCall, updateApiCall } from "./api";
import { Book } from "../../../domain/book/Book";
import { Writer } from "../../../domain/writer/Writer";


export const getBookListAction = (authorIds: string[]) => (dispatch: Dispatch) => {
    dispatch({
        type: TYPE.GET_LIST,
        payload: {}
    });
    getListApiCall(authorIds, getBookListSuccessAction(dispatch), getBookListFailureAction(dispatch));
}

const getBookListSuccessAction = (dispatch: Dispatch): ApiSuccessHandler => {
    return defaultApiSuccessHandler((books: GetListSuccessResponseType) => {
       dispatch({
           type: TYPE.GET_LIST_SUCCESS,
           payload: {
               list: books.map( b => {
                   return new Book(
                       b.id,
                       b.title,
                       b.authors.map( au => new Writer(au.id, au.name))
                   )
               })
           }
       });
    });
};

const getBookListFailureAction = (dispatch: Dispatch): ApiFailureHandler => {
    return defaultApiFailureHandler((data) => {
        dispatch({
            type: TYPE.GET_LIST_FAILURE,
            payload: {}
        });
    });
}

export const getBookInfoAction = (id: string) => (dispatch: Dispatch) => {
    dispatch({
        type: TYPE.GET_INFO,
        payload: {}
    });
    getInfoApiCall(id, getBookInfoSuccessAction(dispatch), getBookInfoFailureAction(dispatch));
}

const getBookInfoSuccessAction = (dispatch: Dispatch): ApiSuccessHandler => {
    return defaultApiSuccessHandler((book: GetInfoSuccessResponseType) => {
        dispatch({
            type: TYPE.GET_INFO_SUCCESS,
            payload: {
                info: new Book(
                    book.id,
                    book.title,
                    book.authors.map( au => new Writer(au.id, au.name))
                )
            }
        });
    });
};

const getBookInfoFailureAction = (dispatch: Dispatch): ApiFailureHandler => {
    return defaultApiFailureHandler((data) => {
        dispatch({
            type: TYPE.GET_INFO_FAILURE,
            payload: {}
        });
    });
}

export const registerBookAction = (title: string, authorIds: string[], onSuccess: () => void, onFailure: () => void) => (dispatch: Dispatch) => {
    dispatch({
        type: TYPE.REGISTER,
        payload: {}
    });
    registerApiCall(title, authorIds, registerBookSuccessAction(dispatch, onSuccess), registerBookFailureAction(dispatch, onFailure));
}

const registerBookSuccessAction = (dispatch: Dispatch, onSuccess: () => void): ApiSuccessHandler => {
    return defaultApiSuccessHandler(() => {
        dispatch({
            type: TYPE.REGISTER_SUCCESS,
            payload: {}
        });
        onSuccess();
    });
};

const registerBookFailureAction = (dispatch: Dispatch, onFailure: () => void): ApiSuccessHandler => {
    return defaultApiSuccessHandler(() => {
        dispatch({
            type: TYPE.REGISTER_FAILURE,
            payload: {}
        });
        onFailure();
    });
};

export const updateBookAction = (id: string, title: string, authorIds: string[], onSuccess: () => void, onFailure: () => void) => (dispatch: Dispatch) => {
    dispatch({
        type: TYPE.UPDATE,
        payload: {}
    });
    updateApiCall(id, title, authorIds, updateBookSuccessAction(dispatch, onSuccess), updateBookFailureAction(dispatch, onFailure));
}

const updateBookSuccessAction = (dispatch: Dispatch, onSuccess: () => void): ApiSuccessHandler => {
    return defaultApiSuccessHandler(() => {
        dispatch({
            type: TYPE.UPDATE_SUCCESS,
            payload: {}
        });
        onSuccess();
    });
};

const updateBookFailureAction = (dispatch: Dispatch, onFailure: () => void): ApiSuccessHandler => {
    return defaultApiSuccessHandler(() => {
        dispatch({
            type: TYPE.UPDATE_FAILURE,
            payload: {}
        });
        onFailure();
    });
};

export const deleteBookAction = (id: string) => (dispatch: Dispatch) => {
    dispatch({
        type: TYPE.DELETE,
        payload: {}
    });
    deleteApiCall(id, deleteBookSuccessAction(dispatch), deleteBookFailureAction(dispatch));
}

const deleteBookSuccessAction = (dispatch: Dispatch): ApiSuccessHandler => {
    return defaultApiSuccessHandler(() => {
        dispatch({
            type: TYPE.DELETE_SUCCESS,
            payload: {}
        });
    });
};

const deleteBookFailureAction = (dispatch: Dispatch): ApiSuccessHandler => {
    return defaultApiSuccessHandler(() => {
        dispatch({
            type: TYPE.DELETE_FAILURE,
            payload: {}
        });
    });
};
