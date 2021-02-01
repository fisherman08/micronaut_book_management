import React from "react";
import "./App.scss";
import { BrowserRouter, Link, Route, Switch } from "react-router-dom";
import { Paths } from "./Paths";
import { BookListPage } from "./app/view/book/BookListPage";
import { BookRegisterPage } from "./app/view/book/BookRegisterPage";
import { BookEditPage } from "./app/view/book/BookEditPage";
import { IndexPage } from "./app/view/IndexPage";
import { WriterListPage } from "./app/view/writer/WriterListPage";
import { WriterRegisterPage } from "./app/view/writer/WriterRegisterPage";
import { WriterEditPage } from "./app/view/writer/WriterEditPage";

function App() {
    return (
        <BrowserRouter>
            <div className="App">
                <div className={"pages"}>
                    <Link to={Paths.index}>TOPへもどる</Link>
                    <Switch>
                        <Route exact path={Paths.index}>
                            <IndexPage/>
                        </Route>
                        <Route exact path={Paths.book.list}>
                            <BookListPage/>
                        </Route>
                        <Route exact path={Paths.book.register}>
                            <BookRegisterPage/>
                        </Route>
                        <Route path={Paths.book.edit}>
                            <BookEditPage/>
                        </Route>
                        <Route exact path={Paths.writer.list}>
                            <WriterListPage/>
                        </Route>
                        <Route exact path={Paths.writer.register}>
                            <WriterRegisterPage/>
                        </Route>
                        <Route path={Paths.writer.edit}>
                            <WriterEditPage/>
                        </Route>
                    </Switch>
                </div>
            </div>
        </BrowserRouter>
    );
}

export default App;
