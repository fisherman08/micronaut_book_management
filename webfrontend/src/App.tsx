import React from "react";
import "./App.scss";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import { Paths } from "./Paths";
import { BookListPage } from "./app/view/book/BookListPage";

function App() {
    return (
        <BrowserRouter>
            <div className="App">
                <div className={"pages"}>
                    <Switch>
                        <Route exact path={Paths.index}>
                            <BookListPage/>
                        </Route>
                    </Switch>
                </div>
            </div>
        </BrowserRouter>
    );
}

export default App;
