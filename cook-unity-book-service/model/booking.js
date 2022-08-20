const mongoose = require("mongoose");
const objectId = mongoose.Schema.Types.ObjectId;

const locationSchema = new mongoose.Schema({
   state:{
       type: String,
       require: true
   },
    country:{
        type: String,
        require: true
    },
    LGA:{
        type: String,
        require: true
    },
    address:{
        type: String,
        require: true
    }
});

const bookingSchema = new mongoose.Schema({
    customerId:{
        type: String,
        require: true
    },
    chefId:{
        type: objectId,
        require: true,
        ref:'Chef'
    },
    occasion:{
        type: String,
        require: true
    },
    occasionStartDate:{
        type: Date,
        require: true
    },
    occasionStartTime:{
        type: String,
        require: true
    },
    occasionEndDate:{
        type: Date,
        require: true
    },
    occasionEndTime:{
        type: String,
        require: true
    },
    status:{
        type: String,
        require: true,
        enum:["Open", "Accepted", "Negotiating"]
    },
    price:{
        type:Number,
        require: true,
        min: 1
    },
    currency:{
      type: String,
      default: "N"
    },
    invites:{
        type:Number,
        require: true,
        min: 1
    },
    location: locationSchema,
    bookingCode:{
        type: String,
        require: true
    }
},{
    timestamps: true
});

const Booking = mongoose.model("Booking", bookingSchema);

module.exports = Booking;