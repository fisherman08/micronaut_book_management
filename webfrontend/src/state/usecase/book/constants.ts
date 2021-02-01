const GET_LIST = "@@ReactTemplate/Action/Book/GetList";
const GET_LIST_SUCCESS = "@@ReactTemplate/Action/Book/GetListSuccess";
const GET_LIST_FAILURE = "@@ReactTemplate/Action/Book/GetListFailure";
const REGISTER = "@@ReactTemplate/Action/Book/Register";
const REGISTER_SUCCESS = "@@ReactTemplate/Action/Book/RegisterSuccess";
const REGISTER_FAILURE = "@@ReactTemplate/Action/Book/RegisterFailure";
const DELETE = "@@ReactTemplate/Action/Book/Delete";
const DELETE_SUCCESS = "@@ReactTemplate/Action/Book/DeleteSuccess";
const DELETE_FAILURE = "@@ReactTemplate/Action/Book/DeleteFailure";

export const TYPE = {
    GET_LIST: GET_LIST as typeof GET_LIST,
    GET_LIST_SUCCESS: GET_LIST_SUCCESS as typeof GET_LIST_SUCCESS,
    GET_LIST_FAILURE: GET_LIST_FAILURE as typeof GET_LIST_FAILURE,    
    REGISTER: REGISTER as typeof REGISTER,
    REGISTER_SUCCESS: REGISTER_SUCCESS as typeof REGISTER_SUCCESS,
    REGISTER_FAILURE: REGISTER_FAILURE as typeof REGISTER_FAILURE,
    DELETE: DELETE as typeof DELETE,
    DELETE_SUCCESS: DELETE_SUCCESS as typeof DELETE_SUCCESS,
    DELETE_FAILURE: DELETE_FAILURE as typeof DELETE_FAILURE,
};
