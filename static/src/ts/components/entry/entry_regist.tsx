import * as React from "react";
import { connect, MapStateToProps, MapDispatchToProps } from "react-redux";
import { Field, reduxForm, getFormValues } from "redux-form";
import { Link } from "react-router-dom";
import RaisedButton from "material-ui/RaisedButton";
import TextField from "material-ui/TextField";
import * as _ from "lodash";
import { readConst } from "../../actions";
import { URL } from "../../common/constants/url";

import { registConfirm } from "../../actions";
import { Consts, User } from "../../store/StoreTypes";

// ↓ 表示用のデータ型
interface IProps {
  consts: {
    sex: Consts,
    prefecture: Consts,
  };
  entry: User;
  readConst;
  registConfirm;
  history;
  error;
  handleSubmit;
  pristine;
  submitting;
  invalid;
}

interface IState {
}

export class EntryRegist extends React.Component<IProps, IState> {
  constructor(props) {
    super(props);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentDidMount(): void {
    this.props.readConst();
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
    await this.props.registConfirm(values);
    this.props.history.push(URL.ENTRY_REGIST_CONFIRM);
  }

  render(): JSX.Element {
    // pristineは、フォームが未入力状態の場合にtrueを返す
    // submittingは、既にSubmit済みの場合にtrueを返す
    const { error, handleSubmit, pristine, submitting, invalid, entry } = this.props;
    const style = {
      margin: 12,
    };

    return (
      <React.Fragment>
        <section>
          <div className="entry-header">
            <h1 className="entry-title">会員登録</h1>
          </div>
          <div className="entry-content">
            <form onSubmit={handleSubmit(this.onSubmit)}>
              {error && <div className="error">{error}</div>}
              <div>
                <p><label>お名前</label></p>
                <Field
                  label="姓"
                  name="familyName"
                  type="text"
                  component={this.renderField}
                />
                <Field
                  label="名"
                  name="name"
                  type="text"
                  component={this.renderField}
                />
              </div>
              <div>
                <p><label>お名前カナ</label></p>
                <Field
                  label="セイ"
                  name="familyNameKana"
                  type="text"
                  component={this.renderField}
                />
                <Field
                  label="メイ"
                  name="nameKana"
                  type="text"
                  component={this.renderField}
                />
              </div>
              <div>
                <Field
                  label="メールアドレス"
                  name="email"
                  type="email"
                  component={this.renderField}
                />
              </div>
              <div>
                <Field
                  label="パスワード"
                  name="password"
                  type="password"
                  component={this.renderField}
                />
                <Field
                  label="パスワード確認"
                  name="passwordConf"
                  type="password"
                  component={this.renderField}
                />
              </div>
              <div>
                <p><label>性別</label></p>
                <div>
                  {
                    this.props.consts.sex && (
                      _.map(this.props.consts.sex.data, (e, index) => (
                        <label key={`sex${index}`}>
                          <Field
                            name="sex"
                            value={`${e.code}`}
                            type="radio"
                            component="input"
                            style={{width: '20px'}}
                          />{' '}
                          {e.text}
                        </label>
                      ))
                    )
                  }
                </div>
              </div>
              <div>
                <Field
                  label="郵便番号"
                  name="zip"
                  type="text"
                  component={this.renderField}
                />
                <p><label>都道府県</label></p>
                <Field
                  name="prefectureId"
                  component="select"
                  onChange={e => console.log(e.target.value)}
                >
                  <option value=''>----</option>
                  {
                    this.props.consts.prefecture && (
                      _.map(this.props.consts.prefecture.data, (e, index) => (
                        <option value={e.code} key={`prefecture${index}`}>{e.text}</option>
                      ))
                    )
                  }
                </Field>
                <Field
                  label="市区町村"
                  name="area"
                  type="text"
                  component={this.renderField}
                />
                <Field
                  label="町名番地"
                  name="address"
                  type="text"
                  component={this.renderField}
                />
                <Field
                  label="建物名"
                  name="building"
                  type="text"
                  component={this.renderField}
                />
              </div>
              <div>
                <Field
                  label="電話番号"
                  name="tel"
                  type="tel"
                  component={this.renderField}
                />
              </div>
              <div>
                <Field
                  label="誕生日"
                  name="birthday"
                  type="date"
                  component={this.renderField}
                />
              </div>
              <RaisedButton
                label="キャンセル"
                style={style}
                containerElement={<Link to="/login">キャンセル</Link>}
              />
              <RaisedButton
                label="確認画面へ進む"
                type="submit"
                style={style}
                disabled={submitting || invalid}
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
    familyName: "",
    name: "",
    familyNameKana: "",
    nameKana: "",
    email: "",
    password: "",
    passwordConf: "",
    sex: "",
    zip: "",
    prefectureId: "",
    area: "",
    address: "",
    building: "",
    tel: "",
    birthday: "",
  };
  if (!values.familyName) errors.familyName = "お名前(姓)を入力して下さい";
  if (!values.name) errors.name = "お名前(名)を入力して下さい";
  if (!values.familyNameKana) errors.familyNameKana = "お名前カナ(セイ)を入力して下さい";
  if (!values.nameKana) errors.nameKana = "お名前カナ(メイ)を入力して下さい";
  if (!values.email) errors.email = "メールアドレスを入力して下さい";
  if (!values.password) errors.password = "パスワードを入力して下さい";
  if (!values.passwordConf) errors.passwordConf = "パスワード確認を入力して下さい";
  if (values.password !== values.passwordConf) errors.passwordConf = "入力されたパスワードが一致しません";
  if (!values.sex) errors.sex = "性別を選択して下さい";
  if (!values.zip) errors.zip = "郵便番号を入力して下さい";
  if (!values.prefectureId) errors.prefectureId = "都道府県を選択して下さい";
  if (!values.area) errors.area = "市区町村を入力して下さい";
  if (!values.address) errors.address = "町名番地を入力して下さい";
  if (!values.building) errors.building = "建物名を入力して下さい";
  if (!values.tel) errors.tel = "電話番号を入力して下さい";
  if (!values.birthday) errors.birthday = "誕生日を入力して下さい";
  return errors;
};

const mapStateToProps = (state, ownProps) => {
  return {
    consts: state.consts,
    initialValues: state.entry,
    entry: state.entry,
  };
};

const mapDispatchToProps = { readConst, registConfirm };

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(reduxForm({ validate, form: "entryRegistForm", enableReinitialize: true })(EntryRegist));
