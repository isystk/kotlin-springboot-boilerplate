import * as React from "react";
import { connect, MapStateToProps, MapDispatchToProps } from "react-redux";
import * as _ from "lodash";

import { showOverlay, hideOverlay } from "../../actions";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const Overlay = (props) => {

  const { parts } = props;

  const onClose = (e) => {
    e.preventDefault();
    props.hideOverlay();
  }

  return (
    <React.Fragment>
        {
          (parts.isShowOverlay) &&  (
            <div id="overlay-background"></div>
          )
        }
        <div className={`isystk-overlay ${(parts.isShowOverlay) ? 'open' : ''}`}>
          <a href="#" onClick={onClose}><span className="close"><FontAwesomeIcon icon={['far', 'times-circle']}/></span></a>
          { props.children }
        </div>
    </React.Fragment>
  )
}

const mapStateToProps = (state, ownProps) => {
  return {
    parts: state.parts,
  };
};

const mapDispatchToProps = { showOverlay, hideOverlay };

export default connect(mapStateToProps, mapDispatchToProps)(Overlay);


