const mongoose = require("mongoose");

const negotiationSchema = new mongoose.Schema({

},{
    timestamps: true
});

const Negotiation = mongoose.model("BookingMeal", negotiationSchema);

module.exports = Negotiation;