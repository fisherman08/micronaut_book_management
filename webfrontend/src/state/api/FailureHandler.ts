import { ApiRequest } from "./ApiRequest";

export type ApiFailureHandler = (response: Response) => void;

export function defaultApiFailureHandler(nextAction: ((data: any) => void) | null): ApiFailureHandler {

    return (response: Response) => {
        if (nextAction == null) return;

        if (ApiRequest.isJsonResponse(response.clone())) {
            response.json().then((data) => nextAction(data));
        } else {
            nextAction({});
        }
        //TODO: ちゃんと実装する
    };
}
