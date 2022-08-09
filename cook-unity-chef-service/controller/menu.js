const { JsonResponse } = require('../lib/apiResponse');
const { MSG_TYPES } = require('../constant/types');
const MenuService = require('../services/MenuService');

/**
 * Create Menu
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.createMenu = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get All Menu
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getAllMenus = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}


/**
 * Get Menu
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getMenu = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Update Menu
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.updateMenu = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Delete Menu
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.deleteMenu = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}