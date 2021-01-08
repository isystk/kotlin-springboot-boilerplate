import React from 'react'
import { connect, MapStateToProps, MapDispatchToProps } from "react-redux";
import * as _ from "lodash";
import { API_ENDPOINT } from "../../common/constants/api";
import { API } from "../../utilities";
import { showLoading, hideLoading } from "../../actions";

interface IProps {
  imageList;
  fields;
  label;
  type;
  meta;
  setImageList;
  showLoading;
  hideLoading;
}

interface IState {
  files: any[];
}

class FileUpload extends React.Component<IProps, IState> {

  constructor(props) {
    super(props);
    this.onChange = this.onChange.bind(this)
    this.fileUpload = this.fileUpload.bind(this)
  }

  onChange(e) {
    // ファイル選択時に、サーバーにアップロードする。
    this.fileUpload(e.target.files, (response)=>{
      // アップロードが完了したら画像IDを親コンポーネントにセットする。
      this.props.setImageList(response.data.data);
    })
  }

  // 画像アップロード処理
  async fileUpload(files, callback){
    const config = {
      headers: {
        'content-type': 'multipart/form-data'
      }
    }
    this.props.showLoading();
    for(let file of files) {
      let response = await API.post(API_ENDPOINT.MEMBER_FILE_UPLOAD, {
        'imageFile': file
      }, config);
      callback(response);
    };
    this.props.hideLoading();
  }

  render() {

    const {
      fields,
      label,
      type,
      meta: { touched, error },
    } = this.props;

    const style = {
      textAlign: 'left' as const,
      margin: 10
    };

    return (
      <React.Fragment>
        <p style={style}>{label}</p>
        <input type="file"
              multiple
              onChange={this.onChange} />
        <div style={{ margin: '10px 0', display: 'flex' }} >
        {
          _.map(this.props.imageList, (image, index) => (
            <div style={{ paddingLeft: '10px' }} key={`image${index}`}>
              <img src={image.imageUrl} width="100px" />
              <input type="hidden" name={`${fields.name}[${index}].imageId`} defaultValue={image.imageId}  />
            </div>
          ))
        }
        </div>
      </React.Fragment>
   )
  }
}


const mapDispatchToProps = { showLoading, hideLoading };

export default connect(null, mapDispatchToProps)(FileUpload);
