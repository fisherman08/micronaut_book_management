import { Reducer } from "redux";
import { TYPE } from "./constants";
import { ActionTypes } from "./type";
import { Book } from "../../../domain/book/Book";

export type BookState = Readonly<{
    bookList: Book[] | null,
    currentInfo: Book | null,
}>;
const bookInitialState: BookState = {
    bookList: null,
    currentInfo: null,
};
export const bookReducer: Reducer<BookState, ActionTypes> = (state: BookState = bookInitialState, action): BookState => {
    switch (action.type) {
        case TYPE.GET_LIST: {
            return {
                ...state,
                bookList: null,
            }
        }
        case TYPE.GET_LIST_SUCCESS: {
            const { list } = action.payload;
            return {
                ...state,
                bookList: [...list],
            }
        }
        case TYPE.GET_LIST_FAILURE: {
            return {
                ...state,
            }
        }
        case TYPE.GET_INFO: {
            return {
                ...state,
                currentInfo: null,
            }
        }
        case TYPE.GET_INFO_SUCCESS: {
            const { info } = action.payload;
            return {
                ...state,
                currentInfo: info,
            }
        }
        case TYPE.GET_INFO_FAILURE: {
            return {
                ...state,
            }
        }
        case TYPE.REGISTER: {
            return {
                ...state
            }
        }
        case TYPE.REGISTER_SUCCESS: {
            return {
                ...state,
                bookList: null,
            }
        }
        case TYPE.UPDATE_FAILURE: {
            return {
                ...state
            }
        }
        case TYPE.UPDATE: {
            return {
                ...state
            }
        }
        case TYPE.UPDATE_SUCCESS: {
            return {
                ...state,
                bookList: null,
                currentInfo: null,
            }
        }
        case TYPE.REGISTER_FAILURE: {
            return {
                ...state
            }
        }
        case TYPE.DELETE: {
            return {
                ...state
            }
        }
        case TYPE.DELETE_SUCCESS: {
            return {
                ...state,
                bookList: null,
            }
        }
        case TYPE.DELETE_FAILURE: {
            return {
                ...state
            }
        }
        default: {
            return state;
        }
    }
};
