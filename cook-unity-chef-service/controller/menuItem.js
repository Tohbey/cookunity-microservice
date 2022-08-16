const { JsonResponse } = require('../lib/apiResponse');
const { MSG_TYPES } = require('../constant/types');
const MenuItemService = require('../services/MenuItemService');
const {paginate} = require("../utils");
const validateMenuItem = require("../request/validateMenuItem");

/**
 * Create MenuItem
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.createMenuItem = async (req, res, next) => {
    try {
        const {error} = validateMenuItem(req.body);
        if (error) return JsonResponse(res, 400, error.details[0].message);

        let menuItem = await MenuItemService.create(req.body);

        JsonResponse(res, 201,MSG_TYPES.CREATED, menuItem);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
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
        const {page, pageSize, skip } = paginate(req);

        const {menuItems, total} = await MenuItemService.getMenuItems(skip,pageSize);

        const meta = {
            total,
            pagination: {
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, menuItems, meta);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
    }
}


/**
 * Get All MenuItem on a menu
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getAllMenuOnMenu = async (req, res, next) => {
    try {
        let filter = {
            menu: req.params.menuId
        };

        const {page, pageSize, skip } = paginate(req);

        const {menuItems, total} = await MenuItemService.getMenuItems(skip,pageSize, filter);

        const meta = {
            total,
            pagination: {
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, menuItems, meta);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
    }
}

/**
 * Get All MenuItem of a chef
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getAllMenuByChef = async (req, res, next) => {
    try {
        let filter = {
            chef: req.params.chefId
        };

        const {page, pageSize, skip } = paginate(req);

        const {menuItems, total} = await MenuItemService.getMenuItems(skip,pageSize, filter);

        const meta = {
            total,
            pagination: {
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, menuItems, meta);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
    }
}

/**
 * Get MenuItem by id
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getMenuItemById = async (req, res, next) => {
    try {
        let filter = {
            _id: req.params.menuItemId
        };

        const menu = await MenuItemService.getMenuItem(filter);

        JsonResponse(res, 200, MSG_TYPES.FETCHED, menu);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
    }
}

/**
 * Get MenuItem by name
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getMenuItemByName = async (req, res, next) => {
    try {
        let filter = {
            _id: req.params.menuItemName
        }

        const menu = await MenuItemService.getMenuItem(filter);

        JsonResponse(res, 200, MSG_TYPES.FETCHED, menu);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
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
        const chefId = req.user._id;

        const menuItem = await MenuItemService.updateMenuItem(req.params.menuItemId, req.body, chefId);

        JsonResponse(res, 200, MSG_TYPES.UPDATED, menuItem);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg);
        next(error);
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
        const chefId = req.user._id;

        await MenuItemService.deleteMenuItem(chefId, req.params.menuItemId);

        JsonResponse(res, 200, MSG_TYPES.DELETED);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}