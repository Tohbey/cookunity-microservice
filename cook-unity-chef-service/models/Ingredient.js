const mongoose = require("mongoose");
const objectId = mongoose.Schema.Types.ObjectId;


const ingredientSchema = new mongoose.Schema({
    chef:{
        type: objectId,
        ref:'Chef'
    },
    menuItem:{
        type: objectId,
        ref:'MenuItem'
    },
    title:{
        type: String,
        require: true
    },
    type:{
        type: String,
        require: true
    },
    quantity:{
        type: Number,
        require: true
    },
    unit:{
        type: Number,
        require: true
    },
    content:{
        type: String,
        require: true
    }
},{
    timestamps: true
});

const Ingredient = mongoose.model("Ingredient", ingredientSchema);

module.exports = Ingredient;