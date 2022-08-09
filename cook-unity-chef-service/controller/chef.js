const { JsonResponse } = require('../lib/apiResponse');
const { MSG_TYPES } = require('../constant/types');
const ChefService = require('../services/ChefService');

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
exports.getChef = async (req, res, next) => {
    try {

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

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}