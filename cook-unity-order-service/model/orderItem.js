const mongoose = require("mongoose");
const objectId = mongoose.Schema.Types.ObjectId;

const orderItemSchema = new mongoose.Schema({
    quantity:{
        type: Number,
        require: true
    },
    customerId:{
        type: Number,
        require: true
    },
    chefId:{
        type: objectId,
        require: true,
        ref:'Chef'
    },
    mealId:{
        type: objectId,
        require: true,
        ref:'MenuItem'
    },
    price:{
        type: Number,
        require: true,
        min:1
    },
    currency:{
        type:String,
        require: true
    },
    tax:{
        type: Number,
        require: true,
    },
    status:{
        type:String,
        require: true,
        enum:["Closed", "Pending", "Paid"]
    },
},{
    timestamps: true
});

const OrderItem = mongoose.model("OrderItem", orderItemSchema);

module.exports = OrderItem;