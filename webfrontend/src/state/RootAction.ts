const RESET_ALL = "@@Monitoring/Action/Root/ResetALL";
export const ROOT_TYPE = {
    RESET_ALL: RESET_ALL as typeof RESET_ALL,
};

export type ResetAllActionType = {
    type: typeof ROOT_TYPE.RESET_ALL,
    payload: {}
};

export type RootActionTypes = ResetAllActionType;
