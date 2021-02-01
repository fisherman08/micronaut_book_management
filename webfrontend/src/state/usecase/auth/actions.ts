import { TYPE } from "./constants";
import { Dispatch } from "redux";
import { login, logout, verify } from "./api";
import { LoginSuccessResponseType, VerifySuccessResponseType } from "./type";
import { ApiSuccessHandler, defaultApiSuccessHandler } from "../../api/SuccessHandler";
import { ApiFailureHandler, defaultApiFailureHandler } from "../../api/FailureHandler";
import { ROOT_TYPE } from "../../RootAction";
import { ErrorResponseType } from "../../api/type/ErrorResponseType";

export const loginAction = (email: string, password: string) => (dispatch: Dispatch) => {
    dispatch({
        type: TYPE.LOGIN,
        payload: {},
    });
    login(email, password, loginSuccessAction(dispatch), loginFailureAction(dispatch));
};
const loginSuccessAction = (dispatch: Dispatch): ApiSuccessHandler => {
    return defaultApiSuccessHandler((data: LoginSuccessResponseType) => {
        dispatch({
            type: TYPE.LOGIN_SUCCESS,
            payload: {
                id: data.id,
                name: data.name,
            },
        });
    });
};
const loginFailureAction = (dispatch: Dispatch): ApiFailureHandler => {
    return defaultApiFailureHandler((data: ErrorResponseType) => {
        dispatch({
            type: TYPE.LOGIN_FAILURE,
            payload: {}
        });
    });
};
export const verifyAction = () => (dispatch: Dispatch) => {
    dispatch({
        type: TYPE.VERIFY,
        payload: {},
    });
    verify(verifySuccessAction(dispatch), verifyFailureAction(dispatch));
};
const verifySuccessAction = (dispatch: Dispatch): ApiSuccessHandler => {
    return defaultApiSuccessHandler((data: VerifySuccessResponseType) => {
        dispatch({
            type: TYPE.VERIFY_SUCCESS,
            payload: {
                id: data.id,
                name: data.name,
            },
        });
    });
};
const verifyFailureAction = (dispatch: Dispatch): ApiFailureHandler => {
    return () => {
        dispatch({
            type: TYPE.VERIFY_FAILURE,
        });
    };
};

export const logoutAction = () => (dispatch: Dispatch) => {
    dispatch({
        type: TYPE.LOGOUT,
        payload: {},
    });
    logout(logoutSuccessAction(dispatch), logoutFailureAction(dispatch));
};

const logoutSuccessAction = (dispatch: Dispatch): ApiSuccessHandler => {
    return defaultApiSuccessHandler(() => {
        dispatch({
            type: ROOT_TYPE.RESET_ALL,
            payload: {},
        });
    });
};

const logoutFailureAction = (dispatch: Dispatch): ApiFailureHandler => {
    return defaultApiFailureHandler(() => {
        dispatch({
            type: TYPE.LOGOUT_FAILURE,
        });
    });
};
