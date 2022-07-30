const mongoose = require("mongoose");


const menuSchema = new mongoose.Schema({

},{
    timestamps: true
});

const Menu = mongoose.model("Menu", menuSchema);

module.exports = Menu;