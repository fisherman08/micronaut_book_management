const LOGIN = "@@ReactTemplate/Action/Auth/Login";
const LOGIN_SUCCESS = "@@ReactTemplate/Action/Auth/LoginSuccess";
const LOGIN_FAILURE = "@@ReactTemplate/Action/Auth/LoginFailure";

const VERIFY = "@@ReactTemplate/Action/Auth/Verify";
const VERIFY_SUCCESS = "@@ReactTemplate/Action/Auth/VerifySuccess";
const VERIFY_FAILURE = "@@ReactTemplate/Action/Auth/VerifyFailure";

const LOGOUT = "@@ReactTemplate/Action/Auth/Logout";
const LOGOUT_FAILURE = "@@ReactTemplate/Action/Auth/LogoutFailure";

export const TYPE = {
    LOGIN: LOGIN as typeof LOGIN,
    LOGIN_SUCCESS: LOGIN_SUCCESS as typeof LOGIN_SUCCESS,
    LOGIN_FAILURE: LOGIN_FAILURE as typeof LOGIN_FAILURE,
    VERIFY: VERIFY as typeof VERIFY,
    VERIFY_SUCCESS: VERIFY_SUCCESS as typeof VERIFY_SUCCESS,
    VERIFY_FAILURE: VERIFY_FAILURE as typeof VERIFY_FAILURE,
    LOGOUT: LOGOUT as typeof LOGOUT,
    LOGOUT_FAILURE: LOGOUT_FAILURE as typeof LOGOUT_FAILURE,
};
