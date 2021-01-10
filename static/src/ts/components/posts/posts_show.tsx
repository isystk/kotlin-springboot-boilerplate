import * as React from "react";
import { connect, MapStateToProps, MapDispatchToProps } from "react-redux";
import * as _ from "lodash";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { getPost } from "../../actions";
import { Post } from "../../store/StoreTypes";
import { URL } from "../../common/constants/url";
import { SimpleSlider } from "../common/slider";
import SnsShare from "../common/sns_share";

// ↓ 表示用のデータ型
interface IProps {
  post: Post;
  getPost;
  match;
  history;
}

interface IState {
}

export class PostsShow extends React.Component<IProps, IState> {
  constructor(props) {
    super(props);
  }

  async componentDidMount() {
    // パスの投稿IDから投稿データを取得する
    const { id } = this.props.match.params;
    if (id) await this.props.getPost(id);

    // TITLEタグを設定
    document.title = this.props.post.title;
  }

  render(): JSX.Element {

    const { post } = this.props;

    return (
      <React.Fragment>
        <div className="contents">
          <div className="wrapper">
            <main>

              <section>

                {//<!-- パンくず -->
                }
                <nav className="breadcrumb">
                  <ul>
                    <li>
                      <Link to={URL.HOME}>
                        <FontAwesomeIcon icon="home" /><span>HOME</span>
                      </Link>
                    </li>
                    <li>
                      {post && post.title}
                    </li>
                  </ul>
                </nav>

                <div className="entry-header">
                  <h1 className="entry-title">{post && post.title}</h1>
                  <div className="article-img">
                    <SimpleSlider>
                      {post && (
                        _.map(post.imageList, (image, index) => (
                          <img alt="sample1" width="644" src={image.imageUrl} key={index} />
                        ))
                      )}
                    </SimpleSlider>
                  </div>
                  <div className=" clearfix"></div>
                </div>
                <div className="entry-content">
                  <p>{post && post.text}</p>
                </div>
                <div className="clearfix"></div>
                <div className="entry-meta">
                  <FontAwesomeIcon icon="clock" />
                  {post && post.registTimeMMDD}
                </div>
                <div className="entry-tags">
                  <div className="section-tag">
                    <ul>
                    <li>タグ： </li>
                    {post && (
                      _.map(post.tagList, (tag, index) => (
                        <li><a href="#" rel="tag" key={index}>{tag.tagName}</a></li>
                      ))
                    )}
                    </ul>
                  </div>
                </div>

                <SnsShare />

              </section>

            </main>

          </div>
        </div>

      </React.Fragment>
    );
  }
}

const mapStateToProps = (state, ownProps) => {
  const post = state.posts[ownProps.match.params.id];
  return {
    post
  };
};

const mapDispatchToProps = { getPost };

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PostsShow);
