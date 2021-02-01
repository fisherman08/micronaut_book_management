const GET_LIST = "@@ReactTemplate/Action/Writer/GetList";
const GET_LIST_SUCCESS = "@@ReactTemplate/Action/Writer/GetListSuccess";
const GET_LIST_FAILURE = "@@ReactTemplate/Action/Writer/GetListFailure";
const GET_INFO = "@@ReactTemplate/Action/Writer/GetInfo";
const GET_INFO_SUCCESS = "@@ReactTemplate/Action/Writer/GetInfoSuccess";
const GET_INFO_FAILURE = "@@ReactTemplate/Action/Writer/GetInfoFailure";
const REGISTER = "@@ReactTemplate/Action/Writer/Register";
const REGISTER_SUCCESS = "@@ReactTemplate/Action/Writer/RegisterSuccess";
const REGISTER_FAILURE = "@@ReactTemplate/Action/Writer/RegisterFailure";
const UPDATE = "@@ReactTemplate/Action/Writer/Update";
const UPDATE_SUCCESS = "@@ReactTemplate/Action/Writer/UpdateSuccess";
const UPDATE_FAILURE = "@@ReactTemplate/Action/Writer/UpdateFailure";
const DELETE = "@@ReactTemplate/Action/Writer/Delete";
const DELETE_SUCCESS = "@@ReactTemplate/Action/Writer/DeleteSuccess";
const DELETE_FAILURE = "@@ReactTemplate/Action/Writer/DeleteFailure";

export const TYPE = {
    GET_LIST: GET_LIST as typeof GET_LIST,
    GET_LIST_SUCCESS: GET_LIST_SUCCESS as typeof GET_LIST_SUCCESS,
    GET_LIST_FAILURE: GET_LIST_FAILURE as typeof GET_LIST_FAILURE,
    GET_INFO: GET_INFO as typeof GET_INFO,
    GET_INFO_SUCCESS: GET_INFO_SUCCESS as typeof GET_INFO_SUCCESS,
    GET_INFO_FAILURE: GET_INFO_FAILURE as typeof GET_INFO_FAILURE,
    REGISTER: REGISTER as typeof REGISTER,
    REGISTER_SUCCESS: REGISTER_SUCCESS as typeof REGISTER_SUCCESS,
    REGISTER_FAILURE: REGISTER_FAILURE as typeof REGISTER_FAILURE,
    UPDATE: UPDATE as typeof UPDATE,
    UPDATE_SUCCESS: UPDATE_SUCCESS as typeof UPDATE_SUCCESS,
    UPDATE_FAILURE: UPDATE_FAILURE as typeof UPDATE_FAILURE,
    DELETE: DELETE as typeof DELETE,
    DELETE_SUCCESS: DELETE_SUCCESS as typeof DELETE_SUCCESS,
    DELETE_FAILURE: DELETE_FAILURE as typeof DELETE_FAILURE,
};
