const mariadb = require("mariadb");
const { config } = require("../config/config.js");

const options = {
  host: config.dbHost,
  user: config.dbHost,
  password: config.dbPassword,
  database: config.dbName,
  connectionLimit: 10,
};

const pool = mariadb.createPool(options);

module.exports = { pool };
