const { JsonResponse } = require('../lib/apiResponse');
const { MSG_TYPES } = require('../constant/types');
const IngredientService = require('../services/IngredientService');
const {paginate} = require("../utils");
const validateIngredient = require("../request/validateIngredient");

/**
 * Create Ingredient
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.createIngredient = async (req, res, next) => {
    try {
        const {error} = validateIngredient(req.body);
        if (error) return JsonResponse(res, 400, error.details[0].message);

        let ingredient = await IngredientService.create(req.body);

        JsonResponse(res, 201, MSG_TYPES.CREATED, ingredient);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
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
        const {page, pageSize, skip } = paginate(req);

        const { ingredients, total} = await IngredientService.getIngredients(skip, pageSize);

        const meta = {
            total,
            pagination: {
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, ingredients, meta)
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
    }
}

/**
 * Get All Ingredient for menu item
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getAllIngredientsByMenuItem = async (req, res, next) => {
    try {
        let filter = {
            menuItem: req.params.menuItemId
        };

        const {page, pageSize, skip } = paginate(req);

        const { ingredients, total} = await IngredientService.getIngredients(skip, pageSize, filter);

        const meta = {
            total,
            pagination: {
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, ingredients, meta)
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
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
        let filter = {
            _id: req.params.ingredientId
        }
        const ingredient = await IngredientService.getIngredient(filter);

        JsonResponse(res,200,MSG_TYPES.FETCHED, ingredient);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
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
        const chefId = req.user._id;

        const ingredient = await IngredientService.updateIngredient(req.params.ingredientId, req.body, chefId);

        JsonResponse(res, 200, MSG_TYPES.UPDATED, ingredient);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
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
        const chefId = req.user._id;

        await IngredientService.deleteIngredient(chefId, req.params.ingredientId);

        JsonResponse(res, 200, MSG_TYPES.DELETED);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
    }
}