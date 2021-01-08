import * as React from "react";
import { connect, MapStateToProps, MapDispatchToProps } from "react-redux";

interface IProps {
  error;
}

interface IState {
}

export class constEntryRemindMail extends React.Component<IProps, IState> {
  constructor(props) {
    super(props);
  }

  render(): JSX.Element {
    return (
      <React.Fragment>
        <section>
          <div className="entry-header">
					  <h1 className="entry-title">パスワードリマインダ　確認メール送信完了</h1>
					</div>
					<div className="entry-content">

						<p>パスワード再設定ページのURLを記載したメールを送信しました。<br />
							メール受信後7時間以内に、パスワード再設定を完了させてください。<br />
							7時間後にURLは無効になります。</p>

					</div>
        </section>
      </React.Fragment>
    );
  }
}

export default connect(
  null,
  null
)(constEntryRemindMail);
