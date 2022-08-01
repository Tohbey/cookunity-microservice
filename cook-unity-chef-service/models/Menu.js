const mongoose = require("mongoose");
const objectId = mongoose.Schema.Types.ObjectId;


const menuSchema = new mongoose.Schema({
    chef:{
        type: objectId,
        ref:'Chef'
    },
    title:{
        type: String,
        require: true
    },
    summary:{
        type: String,
        require: true
    }
},{
    timestamps: true
});

const Menu = mongoose.model("Menu", menuSchema);

module.exports = Menu;