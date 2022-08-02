const mongoose = require("mongoose");
const objectId = mongoose.Schema.Types.ObjectId;

const orderSchema = new mongoose.Schema({
    customerId:{
        type: Number,
        require: true
    },
    tax:{
        type: Number,
        require: true,
        min:1
    },
    total:{
        type: Number,
        require: true,
        min:1
    },
    discount:{
        type: Number,
        require: true,
        min:0
    },
    status:{
        type:String,
        require: true,
        enum:["Closed", "Open"]
    },
    orderItem:[{
        type: objectId,
        ref: 'OrderItem'
    }]
},{
    timestamps: true
});

const Order = mongoose.model("Order", orderSchema);

module.exports = Order;

// - customerId
// - chefId
// - mealId
// - tax.
// - shipping.
// - total.
// - discount.
// - delivery Address.
// - status.