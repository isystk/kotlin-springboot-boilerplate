import * as React from "react";
import { connect, MapStateToProps, MapDispatchToProps } from "react-redux";
import { Field, FieldArray, reduxForm, formValueSelector } from "redux-form";
import { Link } from "react-router-dom";
import * as _ from "lodash";
import RaisedButton from "material-ui/RaisedButton";
import TextField from "material-ui/TextField";
import Checkbox from "material-ui/Checkbox";
import FileUpload from "../common/file_upload";
import { readConst, postMemberPost } from "../../actions";
import { URL } from "../../common/constants/url";

interface IProps {
  readConst;
  postMemberPost;
  history;
  error;
  handleSubmit;
  pristine;
  submitting;
  invalid;
  memberNewForm;
  memberPost;
  consts;
}

interface IState {
}

export class MemberNew extends React.Component<IProps, IState> {

  constructor(props) {
    super(props);
    this.onSubmit = this.onSubmit.bind(this);
    this.setImageList = this.setImageList.bind(this);
  }

  componentDidMount(): void {
    this.props.readConst();
  }

  async onSubmit(values): Promise<void> {
    // 入力フォームをサーバーに送信する
    await this.props.postMemberPost(this.props.memberPost);
    // マイページTOPに画面遷移する
    this.props.history.push(URL.MEMBER);
  }

  // 画像アップロード後のデータ更新処理
  setImageList(data) {
    const imageList = _.map(data, (image) => {
      return {
        imageId: image.imageId,
        imageUrl: image.imageUrlSquare
      }
    });
    // 画像を追加
    this.props.memberPost.imageList = _.concat(this.props.memberPost.imageList, imageList);
    // TODO 自動でレンダリングされないので回避
    this.forceUpdate();
  }

  renderField(field): JSX.Element {
    const {
      input,
      label,
      type,
      meta: { touched, error },
    } = field;
    return (
      <TextField
        hintText={label}
        floatingLabelText={label}
        type={type}
        errorText={touched && error}
        {...input}
        fullWidth={true}
      />
    );
  }

  renderCheckbox(field): JSX.Element {

    const {
      input,
      label,
      type,
      meta: { touched, error },
      ...custom
    } = field;

    return (
      <React.Fragment>
        <Checkbox 
          name={input.name}
          label={label}
          checked={custom.checked}
          onCheck={input.onChange}
          value={custom.code}
        />
      </React.Fragment>
    );
  }

  render(): JSX.Element {
    // pristineは、フォームが未入力状態の場合にtrueを返す
    // submittingは、既にSubmit済みの場合にtrueを返す
    const { error, handleSubmit, pristine, submitting, invalid, memberPost } = this.props;

    const style = {
      margin: 12,
    };
    return (
      <React.Fragment>
        <section>
          <div className="entry-header">
            <h1 className="entry-title">投稿登録</h1>
          </div>
          <div className="entry-content">
            <form onSubmit={handleSubmit(this.onSubmit)}>
              {error && <div className="error">{error}</div>}
              <div>
                <Field
                  label="タイトル"
                  name="title"
                  type="text"
                  component={this.renderField}
                />
              </div>
              <div>
                <Field
                  label="本文"
                  name="text"
                  type="text"
                  component={this.renderField}
                />
              </div>
              <div>
                <FieldArray
                  label="画像"
                  name="imageList"
                  component={FileUpload}
                  props={{ imageList: memberPost && memberPost.imageList }}
                  setImageList={this.setImageList}
                />
              </div>
              <div>
                <p><label>タグ</label></p>
                <div>
                  {
                    this.props.consts.postTag && (
                      _.map(this.props.consts.postTag.data, (e, index) => (
                        <label key={`postTag${index}`}>
                          <Field
                            name="tagList.tagId"
                            label={e.text}
                            component={this.renderCheckbox}
                            code={e.code}
                            checked={memberPost && _.includes(_.map(memberPost.tagList, 'tagId'), e.code)}
                            onChange={(event) => {
                              if (event.target.checked) {
                                this.props.memberPost.tagList.push({tagId: e.code});
                              } else {
                                this.props.memberPost.tagList = _.filter(memberPost.tagList, (tag) => tag.tagId != e.code);
                              }
                            }}
                            style={{width: '20px'}}
                          />{' '}
                        </label>
                      ))
                    )
                  }
                </div>
              </div>
              <div style={{margin: '20px 0'}}>
                <RaisedButton
                  label="キャンセル"
                  style={style}
                  containerElement={<Link to="/member">キャンセル</Link>}
                />
                <RaisedButton
                  label="登録"
                  type="submit"
                  style={style}
                  disabled={submitting || invalid}
                />
              </div>
            </form>
          </div>
        </section>
      </React.Fragment>
    );
  }
}

const validate = (values) => {
  const errors = {
    title: "",
    text: "",
    imageList: "",
  };
  if (!values.title) errors.title = "タイトルを入力して下さい";
  if (!values.text) errors.text = "本文を入力して下さい";
  if (!values.imageList) errors.imageList = "画像を選択して下さい";
  return errors;
};

const mapStateToProps = (state, ownProps) => {
  const { memberNewForm } = state.form;
  const memberPost = (memberNewForm) ? memberNewForm.values : {
    title: '',
    text: '',
    imageList: [],
    tagList: [],
  };
  return {
    initialValues: memberPost,
    memberPost,
    consts: state.consts
  };
};

const mapDispatchToProps = { readConst, postMemberPost };

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(reduxForm({ validate, form: "memberNewForm" })(MemberNew));
