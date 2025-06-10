const express = require("express");
const { authRouter } = require("./authRoutes.js");
const { userRoutes } = require("./userRoutes.js");
const { rawMaterialsRouter } = require("./rawMaterialsRoutes.js");
const { supplierRouter } = require("./supplierRoutes.js");
const { categoryRoutes } = require("./categoryRoutes.js");
const { clientRouter } = require("./clientRouter.js");
const { productsRouter } = require("./productsRouter.js")
const { inventoryRouter } = require("./inventoryRouter.js")
const { orderRoutes } = require("./orderRoutes.js");
const { salesRoutes } = require("./salesRoutes.js");
const { detailsalesRoutes } = require("./detailsaleRoutes.js");
const { historicalPricesRouter } = require("./historicalPricesRoutes.js");
const { historicalPricesMaterialsRouter } = require("./historicalPricesMaterialsRoutes.js");
const { rawCategoryRouter } = require("./rawCategoryRouter.js");

BigInt.prototype.toJSON = function () {
  return this.toString();
};

function routerApi(app) {
  const routes = express.Router();
  app.use("/api/v1", routes);
  routes.use("/auth", authRouter);
  routes.use("/user", userRoutes);
  routes.use("/rawMaterials", rawMaterialsRouter);
  routes.use("/supplier", supplierRouter);
  routes.use("/categories", categoryRoutes);
  routes.use("/clients", clientRouter);
  routes.use("/products", productsRouter);
  routes.use("/inventory", inventoryRouter);
  routes.use("/order", orderRoutes);
  routes.use("/sales", salesRoutes);
  routes.use("/detail-sales", detailsalesRoutes);
  routes.use("/historical-prices", historicalPricesRouter);
  routes.use("/historical-prices-materials", historicalPricesMaterialsRouter);
  routes.use("/raw-categories", rawCategoryRouter);
}

module.exports = { routerApi };
