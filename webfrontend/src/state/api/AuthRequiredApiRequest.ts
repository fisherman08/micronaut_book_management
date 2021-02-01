import { ApiFailureHandler } from "./FailureHandler";
import { ApiSuccessHandler, defaultApiSuccessHandler } from "./SuccessHandler";
import { ApiRequest } from "./ApiRequest";
import { isServerError, isUnAuthorized } from "./type/HttpResponseStatus";
import { refresh } from "../usecase/auth/api";

export const AuthRequiredApiRequest: any = {

    get: (
        url: string,
        params: { [key: string]: any },
        successHandler: ApiSuccessHandler,
        failureHandler: ApiFailureHandler,
        headers: { [key: string]: string } = ApiRequest.defaultHeaders
    ) => {
        const originalApiCall = () => {
            ApiRequest.get(url, params, successHandler, failureHandler, headers);
        };
        ApiRequest.get(url, params, successHandler, AuthRequiredApiRequest.unauthorizedHandler(originalApiCall, failureHandler), headers);
    },

    post: (
        url: string,
        params: { [key: string]: any },
        body: any,
        successHandler: ApiSuccessHandler,
        failureHandler: ApiFailureHandler,
        headers: { [key: string]: string } = ApiRequest.defaultHeaders
    ) => {
        const originalApiCall = () => {
            ApiRequest.post(url, params, body, successHandler, failureHandler, headers);
        };
        ApiRequest.post(url, params, body, successHandler, AuthRequiredApiRequest.unauthorizedHandler(originalApiCall, failureHandler), headers);
    },

    unauthorizedHandler: (originalApiCall: () => void, originalFailureHandler: ApiFailureHandler): ApiFailureHandler => {
        return (response: Response) => {

            if (isUnAuthorized(response.status) || isServerError(response.status)) {
                // 未認証か、tokenのエラー(期限切れなど)ならリフレッシュ
                refresh(
                    defaultApiSuccessHandler(() => originalApiCall()),
                    originalFailureHandler
                );
            } else {
                originalFailureHandler(response);
            }
        };
    }

};
