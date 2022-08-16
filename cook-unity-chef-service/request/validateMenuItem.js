const Joi = require("joi");

function validateMenuItem(body){
    const menuItemSchema = Joi.object({
        chef: Joi.string().regex(/^[0-9a-fA-F]{24}$/).required(),
        menu: Joi.string().regex(/^[0-9a-fA-F]{24}$/).required(),
        name: Joi.string().required(),
        description: Joi.string().required(),
        price: Joi.number().required(),
        recipe: Joi.string().required(),
        quantity: Joi.number().required(),
        currency: Joi.string().required(),
        cooking: Joi.boolean().required(),
        content: Joi.string().required()
    });
    return menuItemSchema.validate(body)
}

module.exports = validateMenuItem