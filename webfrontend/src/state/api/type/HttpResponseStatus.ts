
export type StatusCode = number;

export function isOk(statusCode: StatusCode): boolean {
    return (200 <= statusCode && statusCode < 300);
}

export function isUnAuthorized(statusCode: StatusCode): boolean {
    return (statusCode === 401);
}

export function isServerError(statusCode: StatusCode): boolean {
    return (500 <= statusCode && statusCode < 600);
}
