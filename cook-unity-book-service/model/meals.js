const mongoose = require("mongoose");

const mealSchema = new mongoose.Schema({

},{
    timestamps: true
});

const BookingMeal = mongoose.model("BookingMeal", mealSchema);

module.exports = BookingMeal;