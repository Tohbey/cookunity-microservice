const mongoose = require("mongoose");
const objectId = mongoose.Schema.Types.ObjectId;

const negotiationSchema = new mongoose.Schema({
    booking:{
        type: objectId,
        ref: 'Booking',
        require: true
    },
    invites:{
        type: Number,
        require: true,
        min: 1
    },
    newPrice:{
        type: Number,
        require: true,
        min: 1
    },
    oldPrice:{
        type: Number,
        require: true,
        min: 1
    },
    currency:{
        type: String,
        default: "N"
    },
},{
    timestamps: true
});

const Negotiation = mongoose.model("BookingMeal", negotiationSchema);

module.exports = Negotiation;