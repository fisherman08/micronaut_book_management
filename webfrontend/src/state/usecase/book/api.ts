import { ApiRequest } from "../../api/ApiRequest";
import { ApiPaths } from "../../api/ApiPaths";
import { ApiFailureHandler } from "../../api/FailureHandler";
import { ApiSuccessHandler } from "../../api/SuccessHandler";

export function getListApiCall(
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    ApiRequest.get(ApiPaths.book.getList, {}, successHandler, failureHandler);
}

export function registerApiCall(
    title: string,
    authorIds: string[],
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    const body = {
        title: title,
        authorIds: authorIds,
    };
    ApiRequest.post(ApiPaths.book.register, {}, body, successHandler, failureHandler);
}
