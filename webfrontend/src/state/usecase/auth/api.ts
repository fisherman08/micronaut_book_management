import { ApiRequest } from "../../api/ApiRequest";
import { ApiPaths } from "../../api/ApiPaths";
import { ApiFailureHandler } from "../../api/FailureHandler";
import { ApiSuccessHandler } from "../../api/SuccessHandler";
import { AuthRequiredApiRequest } from "../../api/AuthRequiredApiRequest";

export function login(
    email: string, password: string,
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    const body = { email: email, password: password };
    ApiRequest.post(ApiPaths.auth.login, {}, body, successHandler, failureHandler);
}

export function verify(
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    AuthRequiredApiRequest.post(ApiPaths.auth.verify, {}, null, successHandler, failureHandler);
}

export function refresh(
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    ApiRequest.post(
        ApiPaths.auth.refresh,
        {},
        null,
        successHandler,
        failureHandler
    );
}

export function logout(
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    AuthRequiredApiRequest.post(ApiPaths.auth.logout, {}, null, successHandler, failureHandler);
}
