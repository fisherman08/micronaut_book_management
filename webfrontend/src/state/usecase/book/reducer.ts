import { Reducer } from "redux";
import { TYPE } from "./constants";
import { ActionTypes } from "./type";

export type BookState = Readonly<{

}>;
const bookInitialState: BookState = {

};
export const bookReducer: Reducer<BookState, ActionTypes> = (state: BookState = bookInitialState, action): BookState => {
    switch (action.type) {
        case TYPE.GET_LIST: {
            return {
                ...state,
            };
        }
        default: {
            return state;
        }
    }
};
