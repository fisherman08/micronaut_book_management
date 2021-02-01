import { Reducer } from "redux";
import { TYPE } from "./constants";
import { ActionTypes } from "./type";
import { Book } from "../../../domain/book/Book";

export type BookState = Readonly<{
    bookList: Book[] | null
}>;
const bookInitialState: BookState = {
    bookList: null,
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
        case TYPE.REGISTER_FAILURE: {
            return {
                ...state
            }
        }
        default: {
            return state;
        }
    }
};
