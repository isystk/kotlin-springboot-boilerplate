import * as React from "react";
import { connect, MapStateToProps, MapDispatchToProps } from "react-redux";
import { Field, FieldArray, reduxForm } from "redux-form";
import { Link } from "react-router-dom";
import * as _ from "lodash";
import RaisedButton from "material-ui/RaisedButton";
import TextField from "material-ui/TextField";
import Checkbox from "material-ui/Checkbox";
import FileUpload from "../common/file_upload";
import { readConst, getMemberPost, deleteMemberPost, putMemberPost } from "../../actions";
import { URL } from "../../common/constants/url";

interface IProps {
  readConst;
  getMemberPost;
  deleteMemberPost;
  putMemberPost;
  match;
  history;

  error;
  handleSubmit;
  pristine;
  submitting;
  invalid;
  memberPost;
  consts;
}

interface IState {
}

export class MemberShow extends React.Component<IProps, IState> {

  constructor(props) {
    super(props);
    this.onSubmit = this.onSubmit.bind(this);
    this.onDeleteClick = this.onDeleteClick.bind(this);
    this.setImageList = this.setImageList.bind(this);
  }

  async componentDidMount() {
    
    this.props.readConst();

    // パスの投稿IDから自分の投稿データを取得する
    const { id } = this.props.match.params;
    if (id) await this.props.getMemberPost(id);
  }

  async onDeleteClick() {
    // 投稿を削除する
    const { id } = this.props.match.params;
    await this.props.deleteMemberPost(id);
    // マイページTOPに画面遷移する
    this.props.history.push(URL.MEMBER);
  }

  async onSubmit(values): Promise<void> {
    // 入力フォームをサーバーに送信する
    await this.props.putMemberPost(this.props.memberPost);
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
      <React.Fragment>
        <p>{label}</p>
        <TextField
          hintText={label}
          type={type}
          errorText={touched && error}
          {...input}
          fullWidth={true}
        />
      </React.Fragment>
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
            <h1 className="entry-title">投稿編集</h1>
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
                              // TODO 自動でレンダリングされないので回避
                              this.forceUpdate();
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
                  label="削除"
                  style={style}
                  onClick={this.onDeleteClick}
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
  const initial = state.memberPosts[ownProps.match.params.id];
  const { memberShowForm } = state.form;
  return {
    initialValues: initial,
    memberPost: (memberShowForm) ? memberShowForm.values : initial,
    consts: state.consts
  };
};

const mapDispatchToProps = { readConst, getMemberPost, deleteMemberPost, putMemberPost };

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(
  // enableReinitialize をtrueにすると別ユーザーによってデータが変更されている場合でも常に最新のデータを取得して表示できる。
  reduxForm({ validate, form: "memberShowForm", enableReinitialize: true })(
    MemberShow
  )
);
