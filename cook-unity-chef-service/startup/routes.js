const express = require('express');
const { JsonResponse } = require("../lib/apiResponse");
const chef = require('../routes/chefRouter');
const menu = require('../routes/menuRouter');
const menuItem = require('../routes/MenuItemRouter');
const ingredient = require('../routes/ingredientRouter');

module.exports = function (app) {
    app.use(express.json({limit: '50mb'}));
    app.use(express.urlencoded({limit: '50mb'}));
    // app.use(express.static("public"));
    app.use('/api/v1/chef',chef);
    app.use('/api/v1/menu',menu);
    app.use('/api/v1/menu-item',menuItem);
    app.use('/api/v1/ingredient',ingredient);

    app.use((req, res, next) => {
        return JsonResponse(res, 404, "API endpoint not found")
    })
}