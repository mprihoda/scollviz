var path = require('path')
var HtmlWebpackPlugin = require('html-webpack-plugin')

var baseDir = path.resolve(__dirname, '../../../..')
var tmplDir = path.resolve(baseDir, 'public')

module.exports = require('./scalajs.webpack.config')

module.exports.plugins = [
  new HtmlWebpackPlugin({ template: path.resolve(tmplDir, 'index-dev.html'), inject: false })
]
