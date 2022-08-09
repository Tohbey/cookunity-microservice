const MenuItem = require('../models/MenuItem');
const { MSG_TYPES } = require('../constant/types');

class MenuItemService{

    static create(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    static getMenuItems(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    static getMenuItem(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    static updateMenuItem(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    static deleteMenuItem(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }
}

module.exports = MenuItemService;