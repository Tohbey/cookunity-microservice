const Joi = require("joi");

function validateNegotiation(body) {
    const negotiationSchema = Joi.object({
        booking: Joi.string().regex(/^[0-9a-fA-F]{24}$/).required(),
        quality:Joi.number().required(),
        name: Joi.string().required()
    });

    return negotiationSchema.validate(body);
}

module.exports = {
    validateNegotiation
}