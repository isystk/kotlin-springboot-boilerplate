import * as React from "react";
import * as ReactDom from "react-dom";
import { createStore, applyMiddleware } from "redux";
import { Provider } from "react-redux";
import thunk from "redux-thunk";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { composeWithDevTools } from "redux-devtools-extension";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import { library } from '@fortawesome/fontawesome-svg-core';
import { fab } from "@fortawesome/free-brands-svg-icons";
import { far } from "@fortawesome/free-regular-svg-icons";
import { fas } from "@fortawesome/free-solid-svg-icons";
library.add(fab, far, fas);
import { URL } from "./common/constants/url";

import reducers from "./reducers";
import Layout from "./components/layout";
import PostsIndex from "./components/posts/posts_index";
import PostsShow from "./components/posts/posts_show";
import MemberIndex from "./components/member/member_index";
import MemberNew from "./components/member/member_new";
import MemberShow from "./components/member/member_show";
import EntryRegist from "./components/entry/entry_regist";
import EntryRegistConfirm from "./components/entry/entry_regist_confirm";
import EntryRegistMail from "./components/entry/entry_regist_mail";
import EntryRegistComplete from "./components/entry/entry_regist_complete";
import EntryRemind from "./components/entry/remind/entry_remind";
import EntryRemindMail from "./components/entry/remind/entry_remind_mail";
import EntryRemindConfig from "./components/entry/remind/entry_remind_config";
import EntryRemindConfigComplete from "./components/entry/remind/entry_remind_config_complete";
import AuthLogin from "./components/auth/auth_login";
import AuthCheck from "./components/auth/auth_check";
import { NotFound } from "./components/NotFound";

// 開発環境の場合は、redux-devtools-extension を利用できるようにする
const enhancer =
  process.env.NODE_ENV === "development"
    ? composeWithDevTools(applyMiddleware(thunk))
    : applyMiddleware(thunk);
const store = createStore(reducers, enhancer);

const Main = () => (
  <main>
    <Switch>
      <Route exact path={URL.HOME} component={PostsIndex} />
      <Route path={`${URL.POSTS}/:id`} component={PostsShow} />
      <Route path={URL.LOGIN} component={AuthLogin} />
      <Route exact path={URL.ENTRY_REGIST} component={EntryRegist} />
      <Route path={URL.ENTRY_REGIST_CONFIRM} component={EntryRegistConfirm} />
      <Route path={URL.ENTRY_REGIST_MAIL} component={EntryRegistMail} />
      <Route path={`${URL.ENTRY_REGIST}/:token`} component={EntryRegistComplete} />
      <Route exact path={URL.ENTRY_REMIND} component={EntryRemind} />
      <Route path={URL.ENTRY_REMIND_MAIL} component={EntryRemindMail} />
      <Route path={URL.ENTRY_REMIND_CONFIG_COMPLETE} component={EntryRemindConfigComplete} />
      <Route path={`${URL.ENTRY_REMIND_CONFIG}/:token`} component={EntryRemindConfig} />

      { /* ★ログインユーザー専用ここから */ }
      <AuthCheck>
        <Route exact path={URL.MEMBER} component={MemberIndex} />
        <Route path={URL.MEMBER_POSTS_NEW} component={MemberNew} />
        <Route path={`${URL.MEMBER_POSTS}/p:id`} component={MemberShow} />
      </AuthCheck>
      { /* ★ログインユーザー専用ここまで */ }

      <Route component={NotFound} />
    </Switch>
  </main>
)

ReactDom.render(
  <MuiThemeProvider>
    <Provider store={store}>
      <Router>
        <Layout>
          <Main />
        </Layout>
      </Router>
    </Provider>
  </MuiThemeProvider>,
  document.getElementById("root")
);
