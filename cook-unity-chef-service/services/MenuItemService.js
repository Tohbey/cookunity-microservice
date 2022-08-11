const MenuItem = require('../models/MenuItem');
const { MSG_TYPES } = require('../constant/types');

class MenuItemService{

    /**
     * Create MenuItem
     * @param {Object} body MenuItem
     */
    static create(body){
        return new Promise(async (resolve, reject) => {
            try {
                const menuItem = await MenuItem.findOne({
                    chef: body.chef,
                    title: body.title
                });

                if(menuItem){
                    reject({ statusCode: 404, msg: MSG_TYPES.EXIST });
                    return;
                }

                const newMenuItem = new MenuItem(body)
                await newMenuItem.save();

                resolve(newMenuItem);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Get MenuItem
     * @param {Number} skip skip
     * @param pagesSize
     * @param {Object} filter filter
     */
    static getMenuItems(skip, pageSize, filter={}){
        return new Promise(async (resolve, reject) => {
            try {
                const menuItems = await MenuItem.find(filter)
                    .skip(skip).limit(pageSize);

                const total = await MenuItem.find(filter).countDocuments();

                resolve({menuItems, total});
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Get MenuItem
     * @param {Object} filter filter
     */
    static getMenuItem(filter){
        return new Promise(async (resolve, reject) => {
            try {
                const menuItem = await MenuItem.findOne(filter);
                if(!menuItem){
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                resolve(menuItem);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Update MenuItem
     * @param {object} menuItemId menu item's id
     * @param {Object} menuItemObject updated details
     */
    static updateMenuItem(menuItemId, menuItemObject){
        return new Promise(async (resolve, reject) => {
            try {
                const menuItem = await MenuItem.findById(menuItemId);

                if (!menuItem) {
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                await menuItem.updateOne(
                    {
                        $set: menuItemObject
                    }
                );
                resolve(menuItem);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Delete MenuItem
     * @param {Object} chef Chef
     * @param {Object} menuItemId menuItemId
     * */
    static deleteMenuItem(chef, menuItemId){
        return new Promise(async (resolve, reject) => {
            try {
                const menuItem = await MenuItem.findOne({
                    chef: chef,
                    _id: menuItemId
                });
                if (!menuItem) {
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                await menuItem.delete();

                resolve({ msg: MSG_TYPES.DELETED });
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }
}

module.exports = MenuItemService;