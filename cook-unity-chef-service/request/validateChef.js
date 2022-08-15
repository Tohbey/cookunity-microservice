const Joi = require("joi");
const passwordComplexity = require("joi-password-complexity");

const complexityOption = {
    min:6,
    max:20,
    lowerCase:1,
    upperCase: 1,
    numeric: 1,
    symbol: 1,
    requirementCount: 2,
}


function validateChef(body){
    const chefSchema = Joi.object({

    });
    return chefSchema.validate(body)
}

module.exports = {
    validateChef
}