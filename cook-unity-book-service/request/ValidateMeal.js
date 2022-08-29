const Joi = require("joi");

function validateBookingMeal(body) {
    const bookingMealSchema = Joi.object({
        booking: Joi.string().regex(/^[0-9a-fA-F]{24}$/).required(),
        quality:Joi.number().required(),
        name: Joi.string().required()
    });

    return bookingMealSchema.validate(body);
}

module.exports = {
    validateBookingMeal
}