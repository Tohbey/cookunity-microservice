const Menu = require('../models/Menu');
const { MSG_TYPES } = require('../constant/types');

class MenuService{

    static create(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    static getMenus(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    static getMenu(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    static updateMenu(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    static deleteMenu(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }
}

module.exports = MenuService;