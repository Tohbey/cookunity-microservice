const Joi = require("joi");

function validateMenu(body){
    const menuSchema = Joi.object({

    });
    return menuSchema.validate(body)
}

module.exports = validateMenu