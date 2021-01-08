import * as React from "react";
import { connect, MapStateToProps, MapDispatchToProps } from "react-redux";
import { Field, reduxForm, getFormValues } from "redux-form";
import { Link } from "react-router-dom";
import RaisedButton from "material-ui/RaisedButton";
import TextField from "material-ui/TextField";
import * as _ from "lodash";
import { URL } from "../../common/constants/url";

interface IProps {
}

interface IState {
}

export class EntryRegistMail extends React.Component<IProps, IState> {
  constructor(props) {
    super(props);
  }

  render(): JSX.Element {
    return (
      <React.Fragment>
        <section>
          <div className="entry-header">
					  <h1 className="entry-title">会員登録メール送信完了</h1>
					</div>
					<div className="entry-content">

						<p>ご登録いただいたメールアドレスに、会員登録用のメールをお送りしました。<br />メールに書かれているURLをクリックすると会員登録が完了します。</p>
						<p><Link to={URL.HOME}>サイトトップに戻る &raquo;</Link></p>

					</div>
        </section>
      </React.Fragment>
    );
  }
}

export default EntryRegistMail
