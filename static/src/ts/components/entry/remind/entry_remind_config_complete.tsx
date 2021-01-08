import * as React from "react";
import { connect, MapStateToProps, MapDispatchToProps } from "react-redux";
import { Field, reduxForm, getFormValues } from "redux-form";
import { Link } from "react-router-dom";
import RaisedButton from "material-ui/RaisedButton";
import TextField from "material-ui/TextField";
import * as _ from "lodash";
import { URL } from "../../../common/constants/url";
import { registComplete } from "../../../actions";

interface IProps {
  registComplete;
  match;
}

interface IState {
}

export class EntryRemindConfigComplete extends React.Component<IProps, IState> {
  constructor(props) {
    super(props);
  }

  async componentWillMount() {
    const { token } = this.props.match.params;
    if (token) await this.props.registComplete(token);
  }

  render(): JSX.Element {
    return (
      <React.Fragment>
        <section>
          <div className="entry-header">
					  <h1 className="entry-title">会員登録完了</h1>
					</div>
					<div className="entry-content">

						<p>会員登録が完了しました！<br />引き続きお楽しみください。</p>

						<div className="form">
							<ul className="lists links">
								<li><Link to={URL.MEMBER}>マイページに移動する</Link></li>
								<li><Link to={URL.HOME}>サイトトップに戻る</Link></li>
							</ul>
						</div>

					</div>
        </section>
      </React.Fragment>
    );
  }
}

const mapDispatchToProps = { registComplete };

export default connect(
  null,
  mapDispatchToProps
)(EntryRemindConfigComplete);
