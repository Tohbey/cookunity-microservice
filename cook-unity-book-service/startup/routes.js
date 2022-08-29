const express = require('express');
const { JsonResponse } = require("../lib/apiResponse");
const booking = require('../routes/bookingRoutes');
const bookingMeal = require('../routes/MealRoutes');
const negotiation = require('../routes/negotiationRoutes')

module.exports = function (app) {
    app.use(express.json({limit: '50mb'}));
    app.use(express.urlencoded({limit: '50mb'}));
    // app.use(express.static("public"));
    app.use('/api/v1/bookings',booking);
    app.use('/api/v1/booking-meals',bookingMeal);
    app.use('/api/v1/negotiation',negotiation);


    app.use((req, res, next) => {
        return JsonResponse(res, 404, "API endpoint not found")
    })
}