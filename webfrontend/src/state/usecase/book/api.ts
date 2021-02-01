import { ApiRequest } from "../../api/ApiRequest";
import { ApiPaths } from "../../api/ApiPaths";
import { ApiFailureHandler } from "../../api/FailureHandler";
import { ApiSuccessHandler } from "../../api/SuccessHandler";

export function getListApiCall(
    email: string, password: string,
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    const body = { email: email, password: password };
    ApiRequest.post(ApiPaths.auth.login, {}, body, successHandler, failureHandler);
}
