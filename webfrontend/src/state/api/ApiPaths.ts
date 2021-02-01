const apiPrefix = "/api";
const apiPrefixAuth = "authentication";
const apiPrefixBook = "book"

export const ApiPaths = {
    auth: {
        login: `${apiPrefix}/${apiPrefixAuth}/login`,
        verify: `${apiPrefix}/${apiPrefixAuth}/verify`,
        refresh: `${apiPrefix}/${apiPrefixAuth}/refresh`,
        logout: `${apiPrefix}/${apiPrefixAuth}/logout`,
    },
    book: {
        getList: `${apiPrefix}/${apiPrefixBook}/list`
    }
};
