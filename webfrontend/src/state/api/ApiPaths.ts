const apiPrefix = "/api";
const apiPrefixAuth = "authentication";
const apiPrefixBook = "book"
const apiPrefixWriter = "writer"

export const ApiPaths = {
    auth: {
        login: `${apiPrefix}/${apiPrefixAuth}/login`,
        verify: `${apiPrefix}/${apiPrefixAuth}/verify`,
        refresh: `${apiPrefix}/${apiPrefixAuth}/refresh`,
        logout: `${apiPrefix}/${apiPrefixAuth}/logout`,
    },
    book: {
        getList: `${apiPrefix}/${apiPrefixBook}/list`,
        getInfo: `${apiPrefix}/${apiPrefixBook}/{id}`,
        register: `${apiPrefix}/${apiPrefixBook}`,
        update: `${apiPrefix}/${apiPrefixBook}/{id}`,
        delete: `${apiPrefix}/${apiPrefixBook}/{id}`,
    },
    writer: {
        getList: `${apiPrefix}/${apiPrefixWriter}/list`,
        getInfo: `${apiPrefix}/${apiPrefixWriter}/{id}`,
        register: `${apiPrefix}/${apiPrefixWriter}`,
        update: `${apiPrefix}/${apiPrefixWriter}/{id}`,
        delete: `${apiPrefix}/${apiPrefixWriter}/{id}`,
    }
};
