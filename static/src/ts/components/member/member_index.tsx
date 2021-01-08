import * as React from "react";
import { connect, MapStateToProps, MapDispatchToProps } from "react-redux";
import * as _ from "lodash";
import moment from "moment";
import { Link } from "react-router-dom";
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn,
} from "material-ui/Table";
import { URL } from "../../common/constants/url";

import { readMemberPosts } from "../../actions";
import { Posts } from "../../store/StoreTypes";

interface IProps {
  posts: Posts;
  readMemberPosts;
  history;
}

interface IState {
}

export class MemberIndex extends React.Component<IProps, IState> {

  componentDidMount(): void {
    // 自分の投稿データを取得する
    this.props.readMemberPosts();
  }

  renderPosts(): JSX.Element {
    const photoStyle = {
      display: 'flex',
      flexDirection: 'row',
    }

    return _.map(this.props.posts, (post) => {
      return (
      <TableRow key={post.postId}>
        <TableRowColumn width="30px">{post.postId}</TableRowColumn>
        <TableRowColumn width="100px">{post.title}</TableRowColumn>
        <TableRowColumn>
          <div style={photoStyle as React.CSSProperties}>
          {
            _.map(post.imageList, (image, index) => (
              <span style={{ marginLeft: '10px' }} key={`image${index}`} >
                <img src={image.imageUrl} width="100px" />
              </span>
            ))
          }
          </div>
        </TableRowColumn>
        <TableRowColumn width="100px">{post.registTime}</TableRowColumn>
        <TableRowColumn width="100px">
          <input type="button" onClick={(e)=> { e.preventDefault(); this.props.history.push(`${URL.MEMBER_POSTS}/p${post.postId}`); } } value="詳細" />
        </TableRowColumn>
      </TableRow>
      )
    });
  }

  render(): JSX.Element {
    return (
      <React.Fragment>
        <section>
          <div className="entry-header">
            <h1 className="entry-title">投稿一覧</h1>
          </div>
          <div className="entry-content">
            <p>
              <input type="button" onClick={(e)=> { e.preventDefault(); this.props.history.push(URL.MEMBER_POSTS_NEW); } } value="新規登録" />
            </p>
            <Table>
              <TableHeader displaySelectAll={false} adjustForCheckbox={false}>
                <TableRow>
                  <TableRowColumn width="30px">ID</TableRowColumn>
                  <TableRowColumn width="100px">タイトル</TableRowColumn>
                  <TableRowColumn>画像</TableRowColumn>
                  <TableRowColumn width="100px">投稿日時</TableRowColumn>
                  <TableRowColumn width="100px"><br/></TableRowColumn>
                </TableRow>
              </TableHeader>
              <TableBody displayRowCheckbox={false}>
                {this.renderPosts()}
              </TableBody>
            </Table>
          </div>
        </section>
      </React.Fragment>
    );
  }
}


const mapStateToProps = (state, ownProps) => {
  return {
    posts: _.map(state.memberPosts, function (post) {
      return {
        postId: post.postId,
        tagName: (post.tagNameList && 0<post.tagNameList.length) ? post.tagNameList[0] : '',
        title: post.title,
        text: post.text,
        registTime: moment(post.registTime).format('yyyy/MM/DD HH:mm:ss'),
        imageList: (post.imageList && 0<post.imageList.length) ? post.imageList : []
      };
    })
  };
};

const mapDispatchToProps = { readMemberPosts };

export default connect(mapStateToProps, mapDispatchToProps)(MemberIndex);
