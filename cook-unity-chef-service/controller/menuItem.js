const { JsonResponse } = require('../lib/apiResponse');
const { MSG_TYPES } = require('../constant/types');
const MenuItemService = require('../services/MenuItemService');

/**
 * Create MenuItem
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.createMenuItem = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get All MenuItem
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getAllMenuItems = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}


/**
 * Get MenuItem
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getMenuItem = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Update MenuItem
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.updateMenuItem = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Delete MenuItem
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.deleteMenuItem = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}