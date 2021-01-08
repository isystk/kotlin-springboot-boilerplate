import * as React from "react";
import { connect, MapStateToProps, MapDispatchToProps } from "react-redux";
import * as _ from "lodash";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { URL } from "../../common/constants/url";

import { toggleMenu, closeMenu, authLogout } from "../../actions";

interface IProps {
    auth;
    toggleMenu;
    closeMenu;
    authLogout;
    parts;
}

interface IState {
}

class CommonHeader extends React.Component<IProps, IState> {

  constructor(props) {
    super(props);
    this.logoutClick = this.logoutClick.bind(this);
  }

  async logoutClick() {
    await this.props.authLogout();
    location.reload();
  }

  logoutLink(): JSX.Element {

    const {auth} = this.props;

    if (auth.isLogin) {
      return (<a onClick={this.logoutClick}>ログアウト</a>);
    }
    return (<Link to={URL.LOGIN} onClick={this.props.closeMenu}>ログイン</Link>);
  }

  render(): JSX.Element {

    const { parts } = this.props;

    let isSideMenuOpen = parts.isSideMenuOpen;
    let sideMenuClass = isSideMenuOpen ? "open" : "";
    let menuBtnClass = isSideMenuOpen ? "menu-btn on" : "menu-btn";
    let layerPanelClass = isSideMenuOpen ? "on" : "";

    return (
      <React.Fragment>
        <header className="header">
          <div className="wrapper">
              <div className="header-logo"><Link to={URL.HOME}>Isystk's Frontend Sample</Link></div>
              <div className="nav">
                <div className="search">
                  <form role="search" method="get" action="#">
                    <FontAwesomeIcon className="search-icon" icon="search" />
                    <label>
                      <input type="search" placeholder="検索..." defaultValue="" name="s"/>
                    </label>
                  </form>
                </div>
                <div className={menuBtnClass} onClick={(e) => {
                  e.preventDefault();
                  this.props.toggleMenu();
                }}><figure></figure><figure></figure><figure></figure></div>
                <div id="side-menu" className={sideMenuClass}>
                  <div className="side-menu-header">
                    <div className="search">
                      <form role="search" method="get" action="#">
                        <FontAwesomeIcon className="search-icon" icon="search" />
                        <label>
                          <input type="search" placeholder="検索..." defaultValue="" name="s"/>
                        </label>
                      </form>
                    </div>
                  </div>
                  <nav>
                    <ul>
                      <li><Link to={URL.HOME} onClick={this.props.closeMenu}>HOME</Link></li>
                      <li><Link to={URL.MEMBER} onClick={this.props.closeMenu}>マイページ</Link></li>
                      <li >{this.logoutLink()}</li>
                    </ul>
                  </nav>
                </div>
                <div id="layer-panel" className={layerPanelClass}></div>
              </div>
          </div>
        </header>
      </React.Fragment>
    );
  }
}

const mapStateToProps = (state, ownProps) => {
  return {
    parts: state.parts,
    auth: state.auth
  };
};

const mapDispatchToProps = { toggleMenu, closeMenu, authLogout };

export default connect(mapStateToProps, mapDispatchToProps)(CommonHeader);
