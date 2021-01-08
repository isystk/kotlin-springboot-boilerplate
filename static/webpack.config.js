module.exports = {
  mode: process.env.NODE_ENV,

  devtool: "source-map",

  output: {
    filename: "bundle.js",
    path: __dirname + "/dist/js",
  },

  resolve: {
    extensions: [".ts", ".tsx", ".js", ".json"],
  },

  module: {
    rules: [
      {
        test: /\.scss$/,
        loader: "sass-loader",
      },

      {
        test: /\.tsx?$/,
        loader: "awesome-typescript-loader",
      },

      /* ↓ここから react-slick で使用 */
      {
        test: /\.(sass|less|css)$/,
        loaders: ['style-loader', 'css-loader', 'less-loader']
      },

      {
        test: /\.(jpg|jpeg|gif|png|svg)$/,
        use: {
          loader: 'file-loader',
          options: {
            name: '[name].[ext]',
            publicPath: 'images',
            outputPath: 'images',
          }
        }
      },

      {
        test: /\.(eot|ttf|woff|woff2)$/,
        use: {
          loader: 'file-loader',
          options: {
            name: '[name].[ext]',
            publicPath: 'fonts',
            outputPath: 'fonts',
          }
        }
      },
      /* ↑ここまで react-slick で使用 */

    ],
  },

};
