const Joi = require("joi");

function validateBooking(body) {
    const bookingSchema = Joi.object({
        customerId: Joi.string().regex(/^[0-9a-fA-F]{24}$/).required(),
        chefId: Joi.string().regex(/^[0-9a-fA-F]{24}$/).required(),
        occasion: Joi.string().required(),
        occasionStartDate: Joi.date().required(),
        occasionStartTime: Joi.required(),
        occasionEndDate: Joi.date().required(),
        occasionEndTime: Joi.required(),
        status: Joi.string().valid("Open", "Accepted","Negotiating", "Close").required(),
        price: Joi.number().required(),
        invites: Joi.number().required(),
        location: Joi.object().required(),
    });

    return bookingSchema.validate(body);
}

module.exports = {
    validateBooking
}