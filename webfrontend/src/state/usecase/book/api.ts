import { ApiRequest } from "../../api/ApiRequest";
import { ApiPaths } from "../../api/ApiPaths";
import { ApiFailureHandler } from "../../api/FailureHandler";
import { ApiSuccessHandler } from "../../api/SuccessHandler";

export function getListApiCall(
    authorIds: string[],
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    const params = {
        authorIds: authorIds,
    };
    ApiRequest.get(ApiPaths.book.getList, params, successHandler, failureHandler);
}

export function getInfoApiCall(
    id: string,
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    ApiRequest.get(ApiPaths.book.getInfo.replace(/{id}/g, id), {}, successHandler, failureHandler);
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

export function updateApiCall(
    id: string,
    title: string,
    authorIds: string[],
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    const body = {
        title: title,
        authorIds: authorIds,
    };
    ApiRequest.post(ApiPaths.book.update.replace(/{id}/g, id), {}, body, successHandler, failureHandler);
}

export function deleteApiCall(
    id: string,
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    ApiRequest.delete(ApiPaths.book.delete.replace(/{id}/g, id), {}, {}, successHandler, failureHandler);
}
