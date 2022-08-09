const Ingredient = require('../models/Ingredient');
const { MSG_TYPES } = require('../constant/types');

class IngredientService{

    static create(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    static getIngredients(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    static getIngredient(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    static updateIngredient(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    static deleteIngredient(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }
}

module.exports = IngredientService;