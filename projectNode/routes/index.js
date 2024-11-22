const express = require("express");
const { authRouter } = require("./authRoutes.js");
const { userRoutes } = require("./userRoutes.js");
const { suppliesRouter } = require("./suppliesRouter.js");
const { clientRouter } = require("./clientRouter.js");
const { productsRouter } = require("./productsRouter.js")

BigInt.prototype.toJSON = function () {
  return this.toString();
};

function routerApi(app) {
  const routes = express.Router();
  app.use("/api/v1", routes);
  routes.use("/auth", authRouter);
  routes.use("/user", userRoutes);
  routes.use("/supplies", suppliesRouter);
  routes.use("/clients", clientRouter);
  routes.use("/products", productsRouter);
}

module.exports = { routerApi };
