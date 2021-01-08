import * as React from "react";
import { connect, MapStateToProps, MapDispatchToProps } from "react-redux";
import { Field, reduxForm, getFormValues } from "redux-form";
import { Link } from "react-router-dom";
import RaisedButton from "material-ui/RaisedButton";
import TextField from "material-ui/TextField";
import * as _ from "lodash";
import { remindMail } from "../../../actions";
import { URL } from "../../../common/constants/url";

import { Consts, User } from "../../../store/StoreTypes";

interface IProps {
  remindMail;
  history;
  error;
  handleSubmit;
  pristine;
  submitting;
  invalid;
}

interface IState {
}

export class EntryRemind extends React.Component<IProps, IState> {
  constructor(props) {
    super(props);
    this.onSubmit = this.onSubmit.bind(this);
  }

  renderField(field): JSX.Element {
    const {
      input,
      label,
      type,
      meta: { touched, error },
    } = field;
    return (
      <React.Fragment>
        <p><label>{label}</label></p>
        <TextField
          type={type}
          errorText={touched && error}
          {...input}
          fullWidth={true}
        />
      </React.Fragment>
    );
  }

  async onSubmit(values): Promise<void> {
    await this.props.remindMail(values);
    this.props.history.push(URL.ENTRY_REMIND_MAIL);
  }

  render(): JSX.Element {
    // pristineは、フォームが未入力状態の場合にtrueを返す
    // submittingは、既にSubmit済みの場合にtrueを返す
    const { error, handleSubmit, pristine, submitting, invalid } = this.props;
    const style = {
      margin: 12,
    };

    return (
      <React.Fragment>
        <section>
          <div className="entry-header">
            <h1 className="entry-title">パスワードリマインダ</h1>
          </div>
          <div className="entry-content">

						<p>下記のメールアドレスに、パスワード再設定ページのURLを記載したメールを送信します。<br />
							メール受信後7時間以内に、パスワード再設定を完了させてください。<br />
							7時間後にURLは無効になります。</p>

            <form onSubmit={handleSubmit(this.onSubmit)}>
              {error && <div className="error">{error}</div>}
              <div>
                <Field
                  label="メールアドレス"
                  name="email"
                  type="email"
                  component={this.renderField}
                />
              </div>
              <RaisedButton
                label="キャンセル"
                style={style}
                containerElement={<Link to={URL.LOGIN}>キャンセル</Link>}
              />
              <RaisedButton
                label="送信する"
                type="submit"
                style={style}
                disabled={pristine || submitting || invalid}
              />
            </form>
          </div>
        </section>
      </React.Fragment>
    );
  }
}

const validate = (values) => {
  const errors = {
    email: "",
  };
  if (!values.email) errors.email = "メールアドレスを入力して下さい";
  return errors;
};

const mapDispatchToProps = { remindMail };

export default connect(
  null,
  mapDispatchToProps
)(reduxForm({ validate, form: "entryRemindForm", enableReinitialize: true })(EntryRemind));
