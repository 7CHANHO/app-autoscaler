module.exports = function(callback) {

  var fs = require('fs');
  var path = require('path');
  var Sequelize = require('sequelize');
  var settings = JSON.parse(fs.readFileSync(path.join(__dirname, '../../config/settings.json'), 'utf8'));
  var dbConnectionInfo = require(path.join(__dirname, '../../lib/util/dbConnectionInfo.js'))(settings.dbUri);
  var sequelize = new Sequelize(dbConnectionInfo.dbUri, { logging: false });

  sequelize.authenticate()
    .then(function() {
      if (callback) {
        callback();
      }
    })
    .catch(function(error) {
      console.log('DB Connection failed ', error);
      if (callback) {
        callback(error);
      }
    });
  var db = {};

  fs
    .readdirSync(__dirname)
    .filter(function(file) {
      return file.indexOf('.') !== 0 && file !== 'index.js';
    })
    .forEach(function(file) {
      var model = sequelize.import(path.join(__dirname, file));
      db[model.name] = model;
    });

  db.sequelize = sequelize;
  return db;

}