const { JsonResponse } = require('../lib/apiResponse');
const { MSG_TYPES } = require('../constant/types');
const MenuService = require('../services/MenuService');
const {paginate} = require("../utils");
const validateMenu = require("../request/validateMenu");

/**
 * Create Menu
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.createMenu = async (req, res, next) => {
    try {
        const { error } = validateMenu(req.body);
        if (error) return JsonResponse(res, 400, error.details[0].message);

        let menu = await MenuService.create(req.body);

        JsonResponse(res, 201, MSG_TYPES.CREATED, menu);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
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
        const {page, pageSize, skip } = paginate(req);

        const {menus, total} = await MenuService.getMenus(skip,pageSize);

        const meta = {
            total,
            pagination: {
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, menus, meta);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
    }
}


/**
 * Get All Menus of a chef
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getAllMenusByChef = async (req, res, next) => {
    try {
        let filter = {
            chef: req.user._id
        }
        const {page, pageSize, skip } = paginate(req);

        const {menus, total} = await MenuService.getMenus(skip,pageSize, filter);

        const meta = {
            total,
            pagination: {
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, menus, meta);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
    }
}


/**
 * Get Menu by Id
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getMenuById = async (req, res, next) => {
    try {
        let filter = {
            _id: req.params.meunId
        };

        const menu = await MenuService.getMenu(filter);

        JsonResponse(res, 200, MSG_TYPES.FETCHED, menu);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
    }
}


/**
 * Get Menu by Title
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getMenuByTitle = async (req, res, next) => {
    try {
        let filter = {
            title: req.params.menuTitle
        };

        const menu = await MenuService.getMenu(filter);

        JsonResponse(res, 200, MSG_TYPES.FETCHED, menu);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
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
        const chefId = req.user._id;

        const menu  = await MenuService.updateMenu(req.params.mealId, req.body, chefId);

        JsonResponse(res, 200, MSG_TYPES.UPDATED, menu);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
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
        const chefId = req.user._id;

        await MenuService.deleteMenu(chefId,req.params.menuId);

        JsonResponse(res, 200, MSG_TYPES.DELETED);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
    }
}