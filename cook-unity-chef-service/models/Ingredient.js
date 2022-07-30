const mongoose = require("mongoose");


const ingredientSchema = new mongoose.Schema({

},{
    timestamps: true
});

const Ingredient = mongoose.model("Ingredient", ingredientSchema);

module.exports = Ingredient;