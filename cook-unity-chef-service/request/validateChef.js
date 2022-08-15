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
        emailChef: Joi.string().email().max(50).required(),
        password: passwordComplexity(complexityOption).required(),
        chefType: Joi.string().required(),
        address: Joi.string().required(),
        userId: Joi.string().required()
    });
    return chefSchema.validate(body)
}

module.exports = {
    validateChef
}