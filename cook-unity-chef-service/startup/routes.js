const express = require('express');
const { JsonResponse } = require("../lib/apiResponse");
const chef = require('../routes/chef');

module.exports = function (app) {
    app.use(express.json({limit: '50mb'}));
    app.use(express.urlencoded({limit: '50mb'}));
    // app.use(express.static("public"));
    app.use('/api/v1/chef',chef);

    app.use((req, res, next) => {
        return JsonResponse(res, 404, "API endpoint not found")
    })
}