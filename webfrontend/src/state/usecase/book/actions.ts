import { TYPE } from "./constants";
import { Dispatch } from "redux";
import { GetListSuccessResponseType } from "./type";
import { ApiSuccessHandler, defaultApiSuccessHandler } from "../../api/SuccessHandler";
import { ApiFailureHandler, defaultApiFailureHandler } from "../../api/FailureHandler";
import { getListApiCall } from "./api";
import { Book } from "../../../domain/book/Book";
import { Writer } from "../../../domain/writer/Writer";


export const getBookListAction = () => (dispatch: Dispatch) => {
    dispatch({
        type: TYPE.GET_LIST,
        payload: {}
    });
    getListApiCall(getBookListSuccessAction(dispatch), getBookListFailureAction(dispatch));
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
