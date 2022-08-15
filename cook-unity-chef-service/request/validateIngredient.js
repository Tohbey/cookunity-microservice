const Joi = require("joi");

function validateIngredient(body){
    const ingredientSchema = Joi.object({

    });
    return ingredientSchema.validate(body)
}

module.exports = validateIngredient