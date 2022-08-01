const mongoose = require("mongoose");
const objectId = mongoose.Types.ObjectId;

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


const chefSchema = new mongoose.Schema({
    userId:{
        type: String,
        require: true
    },
    emailChef:{
        type: String,
        require: true
    },
    password:{
        type: String,
        require: true
    },
    certificate:{
        type: Boolean,
        require: true
    },
    isVerified:{
        type: Boolean,
        default: false
    },
    address:{
        type: String,
        require: true
    },
    chefShortCode:{
        type: String,
        require: true
    },
    menu:[{
        type: objectId,
        ref:'Menu'
    }],
    bookings:[{

    }],
    profileImage: assetSchema
},{
    timestamps: true
});

const Chef = mongoose.model("Chef", chefSchema);

module.exports = Chef;