import "react-app-polyfill/ie11";
import "babel-polyfill";
import React from "react";
import ReactDOM from "react-dom";
import "destyle.css";
import "./index.scss";
import "./styles/styles.scss";
import App from "./App";
import * as serviceWorker from "./serviceWorker";
import { Provider } from "react-redux";
import { Store } from "./state/store";
import dayjs from "dayjs";
import "dayjs/locale/ja";
dayjs.locale("ja");

ReactDOM.render(
    <React.StrictMode>
        <Provider store={Store}>
            <App/>
        </Provider>
    </React.StrictMode>,
    document.getElementById("root")
);
// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
