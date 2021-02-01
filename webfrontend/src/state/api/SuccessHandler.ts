import { ApiRequest } from "./ApiRequest";

export type ApiSuccessHandler = (response: Response) => void;

export function defaultApiSuccessHandler(nextAction: ((data: any) => void) | null): ApiSuccessHandler {
    return (response: Response) => {
        if (nextAction == null) return;

        if (ApiRequest.isJsonResponse(response.clone())) {
            response.json().then((data) => nextAction(data));
        } else {
            nextAction({});
        }
    };
}
