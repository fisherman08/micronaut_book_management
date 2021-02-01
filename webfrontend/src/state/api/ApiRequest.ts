import { ApiFailureHandler } from "./FailureHandler";
import { ApiSuccessHandler } from "./SuccessHandler";
import "whatwg-fetch";

export const ApiRequest: any = {
    defaultHeaders: {
        "Content-Type": "application/json;charset=UTF-8" // eslint-disable-line
    },
    get: (
        url: string,
        params: { [key: string]: any },
        successHandler: ApiSuccessHandler,
        failureHandler: ApiFailureHandler,
        headers: { [key: string]: string } = ApiRequest.defaultHeaders
    ) => {
        ApiRequest.fetch(url, params, "GET", null, successHandler, failureHandler, headers);
    },
    post: (
        url: string,
        params: { [key: string]: any },
        body: any,
        successHandler: ApiSuccessHandler,
        failureHandler: ApiFailureHandler,
        headers: { [key: string]: string } = ApiRequest.defaultHeaders
    ) => {
        ApiRequest.fetch(url, params, "POST", body, successHandler, failureHandler, headers);
    },
    delete: (
        url: string,
        params: { [key: string]: any },
        body: any,
        successHandler: ApiSuccessHandler,
        failureHandler: ApiFailureHandler,
        headers: { [key: string]: string } = ApiRequest.defaultHeaders
    ) => {
        ApiRequest.fetch(url, params, "DELETE", body, successHandler, failureHandler, headers);
    },
    fetch: async (
        url: string,
        params: { [key: string]: any },
        method: string = "GET",
        body: any = null,
        successHandler: ApiSuccessHandler,
        failureHandler: ApiFailureHandler,
        headers: { [key: string]: string } = ApiRequest.defaultHeaders
    ) => {
        const paramString = Object.entries(params).map((entry) => {
            const key = entry[0];
            const rawValue = entry[1];
            let value = "";
            if (Array.isArray(rawValue)) {
                value = rawValue.map(v => encodeURIComponent(v.toString())).join(",");
            } else {
                value = encodeURIComponent(rawValue.toString());
            }
            return `${key}=${value}`;
        }).join("&");
        return await fetch(
            (paramString !== "") ? `${url}?${paramString}` : url,
            (method === "POST") ?
                {
                    method: method,
                    body: JSON.stringify(body),
                    headers: headers
                } :
                {
                    method: method,
                    headers: headers
                }
        ).then((response) => {
            if (response && response.ok) {
                successHandler(response);
            } else {
                failureHandler(response);
            }
        }
        ).catch(((reason) => {
            //TODO: ちゃんと実装する
            console.log("api call error", reason);
        }));
    },
    isJsonResponse: (response: Response): boolean => {
        if (!(response.headers.get("content-type") || "").includes("application/json")) return false;
        return true;
    }
};
