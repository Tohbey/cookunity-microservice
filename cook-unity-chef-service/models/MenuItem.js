const mongoose = require("mongoose");


const menuItemSchema = new mongoose.Schema({

},{
    timestamps: true
});

const MenuItem = mongoose.model("Menu", menuItemSchema);

module.exports = MenuItem;