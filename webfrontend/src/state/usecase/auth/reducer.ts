import { Reducer } from "redux";
import { TYPE } from "./constants";
import { AuthenticationStatus } from "../../../domain/auth/AuthenticationStatus";
import { ActionTypes } from "./type";

export type AuthState = Readonly<{
    id: string,
    name: string,
    authenticated: AuthenticationStatus | null,
    processing: number,
}>;
const authInitialState: AuthState = {
    id: "",
    name: "",
    authenticated: null,
    processing: 0,
};
export const authReducer: Reducer<AuthState, ActionTypes> = (state: AuthState = authInitialState, action): AuthState => {
    switch (action.type) {
        case TYPE.LOGIN: {
            return {
                ...state,
                processing: state.processing + 1,
            };
        }
        case TYPE.LOGIN_SUCCESS: {
            const { payload } = action;
            const { id, name } = payload;
            return {
                ...state,
                id: id,
                name: name,
                authenticated: AuthenticationStatus.AUTHENTICATED,
                processing: state.processing - 1,
            };
        }
        case TYPE.LOGIN_FAILURE: {
            return {
                ...state,
                authenticated: AuthenticationStatus.UNAUTHENTICATED,
                processing: state.processing - 1,
            };
        }
        case TYPE.VERIFY: {
            return {
                ...state,
                processing: state.processing + 1,
                authenticated: AuthenticationStatus.PENDING,
            };
        }
        case TYPE.VERIFY_SUCCESS: {
            const { payload } = action;
            const { id, name } = payload;
            return {
                ...state,
                id: id,
                name: name,
                authenticated: AuthenticationStatus.AUTHENTICATED,
                processing: state.processing - 1,
            };
        }
        case TYPE.VERIFY_FAILURE: {
            return {
                ...state,
                authenticated: AuthenticationStatus.UNAUTHENTICATED,
                processing: state.processing - 1,
            };
        }
        case TYPE.LOGOUT: {
            return {
                ...state,
            };
        }
        case TYPE.LOGOUT_FAILURE: {
            return {
                ...state,
            };
        }
        default: {
            return state;
        }
    }
};
