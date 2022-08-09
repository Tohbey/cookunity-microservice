const { JsonResponse } = require('../lib/apiResponse');
const { MSG_TYPES } = require('../constant/types');
const IngredientService = require('../services/IngredientService');

/**
 * Create Ingredient
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.createIngredient = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get All Ingredient
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getAllIngredients = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}


/**
 * Get Ingredient
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getIngredient = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Update Ingredient
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.updateIngredient = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Delete Ingredient
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.deleteIngredient = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}