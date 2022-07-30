const mongoose = require("mongoose");


const chefSchema = new mongoose.Schema({

},{
    timestamps: true
});

const Chef = mongoose.model("Chef", chefSchema);

module.exports = Chef;