import * as React from "react";
import { connect, MapStateToProps, MapDispatchToProps } from "react-redux";
import { Field, reduxForm, getFormValues } from "redux-form";
import { Link } from "react-router-dom";
import RaisedButton from "material-ui/RaisedButton";
import TextField from "material-ui/TextField";
import * as _ from "lodash";
import { readConst } from "../../actions";
import { URL } from "../../common/constants/url";

import { registMail } from "../../actions";
import { Consts, User } from "../../store/StoreTypes";

interface IProps {
  consts: {
    sex: Consts,
    prefecture: Consts,
  };
  entry: User;
  readConst;
  registMail;
  history;
  error;
  handleSubmit;
  pristine;
  submitting;
  invalid;
}

interface IState {
}

export class EntryRegistConfirm extends React.Component<IProps, IState> {
  constructor(props) {
    super(props);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentDidMount(): void {
    this.props.readConst();
  }

  async onSubmit(values): Promise<void> {
    await this.props.registMail(values);
    this.props.history.push(URL.ENTRY_REGIST_COMPLETE);
  }

  renderFields(label, value): JSX.Element {
    return (
      <React.Fragment>
        <p><label>{label}</label></p>
        {value}
      </React.Fragment>
    )
  }

  render(): JSX.Element {
    // pristineは、フォームが未入力状態の場合にtrueを返す
    // submittingは、既にSubmit済みの場合にtrueを返す
    const { error, handleSubmit, pristine, submitting, invalid, consts, entry } = this.props;
    const style = {
      margin: 12,
    };

    if (error) {
      // エラーが有る場合は入力画面に戻る
      this.props.history.push(URL.ENTRY_REGIST);
    }

    return (
      <React.Fragment>
        <section>
          <div className="entry-header">
            <h1 className="entry-title">会員登録確認</h1>
          </div>
          <div className="entry-content">
            <form onSubmit={handleSubmit(this.onSubmit)}>
              {error && <div className="error">{error}</div>}
              <div>
                <p><label>お名前</label></p>
                {this.renderFields('姓', entry.familyName)}
                {this.renderFields('名', entry.name)}
              </div>
              <div>
                <p><label>お名前カナ</label></p>
                {this.renderFields('セイ', entry.familyNameKana)}
                {this.renderFields('メイ', entry.nameKana)}
              </div>
              <div>
                {this.renderFields('メールアドレス', entry.email)}
              </div>
              <div>
                {this.renderFields('性別', entry.sex && _.mapKeys(consts.sex.data, "code")[entry.sex].text)}
              </div>
              <div>
                {this.renderFields('郵便番号', entry.zip)}
                {this.renderFields('都道府県', entry.prefectureId && _.mapKeys(consts.prefecture.data, "code")[entry.prefectureId].text)}
                {this.renderFields('市区町村', entry.area)}
                {this.renderFields('町名番地', entry.address)}
                {this.renderFields('建物名', entry.building)}
              </div>
              <div>
                {this.renderFields('電話番号', entry.tel)}
              </div>
              <div>
                {this.renderFields('誕生日', entry.birthday)}
              </div>
              <RaisedButton
                label="戻る"
                style={style}
                containerElement={<Link to={URL.ENTRY_REGIST}>戻る</Link>}
              />
              <RaisedButton
                label="登録する"
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

const mapStateToProps = (state, ownProps) => {
  return {
    consts: state.consts,
    initialValues: state.entry,
    entry: state.entry
  };
};

const mapDispatchToProps = { readConst, registMail };

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(reduxForm({ form: "entryRegistConfirmForm", enableReinitialize: true })(EntryRegistConfirm));
