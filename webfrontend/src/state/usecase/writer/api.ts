import { ApiRequest } from "../../api/ApiRequest";
import { ApiPaths } from "../../api/ApiPaths";
import { ApiFailureHandler } from "../../api/FailureHandler";
import { ApiSuccessHandler } from "../../api/SuccessHandler";

export function getListApiCall(
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    ApiRequest.get(ApiPaths.writer.getList, {}, successHandler, failureHandler);
}

export function getInfoApiCall(
    id: string,
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    ApiRequest.get(ApiPaths.writer.getInfo.replace(/{id}/g, id), {}, successHandler, failureHandler);
}

export function registerApiCall(
    name: string,
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    const body = {
        name: name,
    };
    ApiRequest.post(ApiPaths.writer.register, {}, body, successHandler, failureHandler);
}

export function updateApiCall(
    id: string,
    name: string,
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    const body = {
        name: name,
    };
    ApiRequest.post(ApiPaths.writer.update.replace(/{id}/g, id), {}, body, successHandler, failureHandler);
}

export function deleteApiCall(
    id: string,
    successHandler: ApiSuccessHandler,
    failureHandler: ApiFailureHandler
) {
    ApiRequest.delete(ApiPaths.writer.delete.replace(/{id}/g, id), {}, {}, successHandler, failureHandler);
}
