import { Reducer } from "redux";
import { TYPE } from "./constants";
import { ActionTypes } from "./type";
import { Writer } from "../../../domain/writer/Writer";

export type WriterState = Readonly<{
    writerList: Writer[] | null,
    currentInfo: Writer | null,
}>;
const writerInitialState: WriterState = {
    writerList: null,
    currentInfo: null,
};
export const writerReducer: Reducer<WriterState, ActionTypes> = (state: WriterState = writerInitialState, action): WriterState => {
    switch (action.type) {
        case TYPE.GET_LIST: {
            return {
                ...state,
                writerList: null,
            }
        }
        case TYPE.GET_LIST_SUCCESS: {
            const { list } = action.payload;
            return {
                ...state,
                writerList: [...list],
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
                writerList: null,
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
                writerList: null,
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
                writerList: null,
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
