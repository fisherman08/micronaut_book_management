import { applyMiddleware, combineReducers, compose, createStore } from "redux";
import thunk from "redux-thunk";
import { ROOT_TYPE, RootActionTypes } from "./RootAction";
import { bookReducer, BookState } from "./usecase/book/reducer";
import { writerReducer, WriterState } from "./usecase/writer/reducer";

export type RootState = Readonly<{
    book: BookState,
    writer: WriterState,
}>;

const appReducer = combineReducers<RootState>({
    book: bookReducer,
    writer: writerReducer,
});

const rootReducer = (state: RootState | undefined, action: RootActionTypes) => {
    if (action.type === ROOT_TYPE.RESET_ALL) {
        state = undefined;
    }
    return appReducer(state, action);
};

// redux dev tools
interface ExtendedWindow extends Window {
    __REDUX_DEVTOOLS_EXTENSION_COMPOSE__?: typeof compose; // eslint-disable-line
}

declare const window: ExtendedWindow;

const composeReduxDevToolsEnhancers = (typeof window === "object" && window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__) || compose;

// root state
export const Store = createStore(
    rootReducer,
    composeReduxDevToolsEnhancers(applyMiddleware(thunk))
);

export type State = ReturnType<typeof Store.getState>;
