var path = require('path')
var HtmlWebpackPlugin = require('html-webpack-plugin')

var baseDir = path.resolve(__dirname, '../../../..')
var tmplDir = path.resolve(baseDir, 'public')

module.exports = require('./scalajs.webpack.config')

module.exports.devServer = {
  contentBase: [
    path.resolve('.'),
    path.resolve(baseDir)
  ]
}

module.exports.plugins = [
  new HtmlWebpackPlugin({ template: path.resolve(tmplDir, 'index-fastopt.html'), inject: false })
]
