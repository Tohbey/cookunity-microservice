const express = require('express');
const { JsonResponse } = require("../lib/apiResponse");
const booking = require('../routes/bookingRoutes');

module.exports = function (app) {
    app.use(express.json({limit: '50mb'}));
    app.use(express.urlencoded({limit: '50mb'}));
    // app.use(express.static("public"));
    app.use('/api/v1/bookings',booking);

    app.use((req, res, next) => {
        return JsonResponse(res, 404, "API endpoint not found")
    })
}