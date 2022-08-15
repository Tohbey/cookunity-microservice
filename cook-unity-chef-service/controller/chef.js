const { JsonResponse } = require('../lib/apiResponse');
const { MSG_TYPES } = require('../constant/types');
const ChefService = require('../services/ChefService');
const {validateChef} = require("../request/validateChef");
const bcrypt = require('bcrypt');
const {paginate} = require("../utils");

exports.status = async (req, res, next) => {
    try {
        JsonResponse(res, 200,"chef Service Working");
    }catch (error){
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Create Chef
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.createChef = async (req, res, next) => {
    try {
        const { error } = validateChef(req.body);
        if (error) return JsonResponse(res, 400, error.details[0].message);

        const salt = await  bcrypt.genSalt(10);
        req.body.password = await bcrypt.hash(req.body.password, salt);

        let chef = await ChefService.create(req.body);
        JsonResponse(res, 201, MSG_TYPES.CREATED, chef);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get All Chef
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getAllChefs = async (req, res, next) => {
    try {
        const {page, pageSize, skip } = paginate(req);
        let filter = {
            status: "Active"
        };

        const {chefs, total} = await ChefService.getAllChefs(skip, pageSize, filter);

        const meta = {
            total,
            pagination:{
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, chefs, meta);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}


/**
 * Get All Chef By Types
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getAllChefsByType = async (req, res, next) => {
    try {
        const {page, pageSize, skip } = paginate(req);
        let filter = {
            status: "Active",
            type: req.params.chefType
        };

        const {chefs, total} = await ChefService.getAllChefs(skip, pageSize, filter);

        const meta = {
            total,
            pagination:{
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, chefs, meta);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get Chef
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getChefById = async (req, res, next) => {
    try {
        let filter = {
            _id: req.params.chefId
        }

        const chef = await ChefService.getChef(filter);

        JsonResponse(res, 200, MSG_TYPES.FETCHED, chef);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Update Chef
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.updateChef = async (req, res, next) => {
    try {
        const chefId = req.user._id;

        await ChefService.update(chefId, req.body);

        JsonResponse(res, 200, MSG_TYPES.UPDATED);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Terminate Chef
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.terminateChef = async (req, res, next) => {
    try {
        await ChefService.terminateChef(req.user);

        JsonResponse(res, 200, MSG_TYPES.DELETED);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}