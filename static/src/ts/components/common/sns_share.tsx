import * as React from "react";

const SnsShare = (props) => {

  return (
    <React.Fragment>
      <div className="wrap">
        <ul className="sns-buttons">
          <li className="share-twitter">
            <a href={`http://twitter.com/intent/tweet?text=${document.title}%20${location.href}`} target="_blank" rel="noreferrer">Twitter</a>
          </li>
          <li className="share-facebook">
            <a href={`https://www.facebook.com/sharer/sharer.php?u=${location.href}`} target="_blank" rel="noreferrer">Facebook</a>
          </li>
          <li className="share-hatena">
            <a href={`http://b.hatena.ne.jp/add?mode=confirm&amp;url=${location.href}&amp;title=${document.title}`} target="_blank" rel="noreferrer">はてブ</a>
          </li>
          <li className="share-pocket">
            <a href={`http://getpocket.com/edit?url=${location.href}`} target="_blank" rel="noreferrer">Pocket</a>
          </li>
          <li className="share-line">
            <a href={`http://line.me/R/msg/text/?${document.title}%0D%0A${location.href}`} target="_blank" rel="noreferrer">LINE</a>
          </li>
        </ul>
        <div className="clearfix"></div>
      </div>
    </React.Fragment>
  )
}

export default SnsShare;


