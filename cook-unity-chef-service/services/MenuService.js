const Menu = require('../models/Menu');
const { MSG_TYPES } = require('../constant/types');

class MenuService{

    /**
     * Create Menu
     * @param {Object} body Menu
     */
    static create(body){
        return new Promise(async (resolve, reject) => {
            try {
                const menu = await Menu.findOne({
                    chef: body.chef,
                    title: body.title
                });

                if(menu){
                    reject({ statusCode: 404, msg: MSG_TYPES.EXIST });
                    return;
                }

                const newMenu = new Menu(body)
                await newMenu.save();

                resolve(newMenu);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Get Menus
     * @param {Number} skip skip
     * @param pagesSize
     * @param {Object} filter filter
     */
    static getMenus(skip, pageSize, filter={}){
        return new Promise(async (resolve, reject) => {
            try {
                const menus = await Menu.find(filter)
                    .skip(skip).limit(pageSize);

                const total = await Menu.find(filter).countDocuments();

                resolve({menus, total});
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Get Menu
     * @param {Object} filter filter
     */
    static getMenu(filter){
        return new Promise(async (resolve, reject) => {
            try {
                const menu = await Menu.findOne(filter);
                if(!menu){
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                resolve(menu);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Update Menu
     * @param {object} menuId menu's id
     * @param {Object} menuObject updated details
     * @param chefId
     */
    static updateMenu(menuId, menuObject, chefId){
        return new Promise(async (resolve, reject) => {
            try {
                const menu = await Menu.findOne({
                    chef: chefId,
                    _id: menuId
                });

                if (!menu) {
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                await menu.updateOne(
                    {
                        $set: menuObject
                    }
                );
                resolve(menu);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }


    /**
     * Delete Menu
     * @param {Object} chef Chef
     * @param {Object} menuId menuId
     * */
    static deleteMenu(chef, menuId){
        return new Promise(async (resolve, reject) => {
            try {
                const menu = await Menu.findOne({
                    chef: chef,
                    _id: menuId
                });
                if (!menu) {
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                await menu.delete();

                resolve({ msg: MSG_TYPES.DELETED });
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }
}

module.exports = MenuService;