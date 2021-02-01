import { TYPE } from "./constants";

export type LoginActionType = {
    type: typeof TYPE.LOGIN,
    payload: {}
};

export type LoginSuccessResponseType = {
    id: string,
    name: string,
    role: string,
}

export type LoginSuccessActionType = {
    type: typeof TYPE.LOGIN_SUCCESS,
    payload: {
        id: string,
        name: string,
    }
};

export type LoginFailureActionType = {
    type: typeof TYPE.LOGIN_FAILURE,
    payload: {}
};

export type VerifyActionType = {
    type: typeof TYPE.VERIFY,
    payload: {}
};

export type VerifySuccessResponseType = {
    id: string,
    name: string,
    role: string
}

export type VerifySuccessActionType = {
    type: typeof TYPE.VERIFY_SUCCESS,
    payload: {
        id: string,
        name: string,
    }
};

export type VerifyFailureActionType = {
    type: typeof TYPE.VERIFY_FAILURE,
    payload: {}
};

export type LogoutActionType = {
    type: typeof TYPE.LOGOUT,
    payload: {}
};

export type LogoutFailureActionType = {
    type: typeof TYPE.LOGOUT_FAILURE,
    payload: {}
};

export type ActionTypes = LoginActionType |
    LoginSuccessActionType |
    LoginFailureActionType |
    VerifyActionType |
    VerifySuccessActionType |
    VerifyFailureActionType |
    LogoutActionType |
    LogoutFailureActionType;
