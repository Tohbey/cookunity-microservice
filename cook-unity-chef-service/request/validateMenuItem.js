const Joi = require("joi");

function validateMenuItem(body){
    const menuItemSchema = Joi.object({

    });
    return menuItemSchema.validate(body)
}

module.exports = validateMenuItem