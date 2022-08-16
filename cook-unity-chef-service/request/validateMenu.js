const Joi = require("joi");

function validateMenu(body){
    const menuSchema = Joi.object({
        chef: Joi.string().regex(/^[0-9a-fA-F]{24}$/).required(),
        title: Joi.string().required(),
        summary: Joi.string().required()
    });
    return menuSchema.validate(body)
}

module.exports = validateMenu