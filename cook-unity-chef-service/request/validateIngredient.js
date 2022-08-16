const Joi = require("joi");

function validateIngredient(body){
    const ingredientSchema = Joi.object({
        chef: Joi.string().regex(/^[0-9a-fA-F]{24}$/).required(),
        menuItem: Joi.string().regex(/^[0-9a-fA-F]{24}$/).required(),
        title: Joi.string().required(),
        type: Joi.string().required(),
        quantity: Joi.number().required(),
        unit: Joi.string().required(),
        content: Joi.string().required(),

    });
    return ingredientSchema.validate(body)
}

module.exports = validateIngredient