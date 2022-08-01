const mongoose = require("mongoose");
const objectId = mongoose.Schema.Types.ObjectId;


const assetSchema = new mongoose.Schema({
    type:{
        type: String,
        require: true
    },
    encodedFile: {
        type: String,
        require: true
    },
    extension: {
        type: String,
        require: true
    },
    fileName:{
        type: String,
        require: true
    },
    id:{
        type: Number,
        require: true
    }
})

const menuItemSchema = new mongoose.Schema({
    menu:{
        type: objectId,
        ref:'Menu'
    },
    chef:{
        type: objectId,
        ref:'Chef'
    },
    title:{
        type: String,
        require: true
    },
    description:{
        type: String,
        require: true
    },
    price:{
        type: Number,
        require: true
    },
    recipe:{
        type: String,
        require: true
    },
    discount:{
        type: Number,
        require: true
    },
    quantity:{
        type: Number,
        require: true
    },
    currency:{
        type: String,
        require: true
    },
    cooking:{
        type: Boolean,
        require: true
    },
    content:{
        type: String,
        require: true
    },
    picture:[assetSchema]
},{
    timestamps: true
});

const MenuItem = mongoose.model("MenuItem", menuItemSchema);

module.exports = MenuItem;