const Ingredient = require('../models/Ingredient');
const { MSG_TYPES } = require('../constant/types');
const MenuItem = require("../models/MenuItem");

class IngredientService{

    /**
     * Create Ingredient
     * @param {Object} body Ingredient
     */
    static create(body){
        return new Promise(async (resolve, reject) => {
            try {
                const ingredient = await Ingredient.findOne({
                    chef: body.chef,
                    title: body.title
                });

                if(ingredient){
                    reject({ statusCode: 404, msg: MSG_TYPES.EXIST });
                    return;
                }

                const newIngredient = new ingredient(body)
                await newIngredient.save();

                resolve(newIngredient);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Get Ingredient
     * @param {Number} skip skip
     * @param pageSize
     * @param {Object} filter filter
     */
    static getIngredients(skip, pageSize, filter={}){
        return new Promise(async (resolve, reject) => {
            try {
                const ingredients = await Ingredient.find(filter)
                    .skip(skip).limit(pageSize);

                const total = await Ingredient.find(filter).countDocuments();

                resolve({ingredients, total});
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Get Ingredient
     * @param {Object} filter filter
     */
    static getIngredient(filter){
        return new Promise(async (resolve, reject) => {
            try {
                const ingredient = await Ingredient.findOne(filter);
                if(!ingredient){
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                resolve(ingredient);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Update Ingredient
     * @param {object} ingredientId Ingredient id
     * @param {Object} ingredientObject updated details
     */
    static updateIngredient(ingredientId, ingredientObject){
        return new Promise(async (resolve, reject) => {
            try {
                const ingredient = await Ingredient.findById(ingredientId);

                if (!ingredient) {
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                await ingredient.updateOne(
                    {
                        $set: ingredientObject
                    }
                );
                resolve(ingredient);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Delete Ingredient
     * @param {Object} chef Chef
     * @param {Object} ingredientId Ingredient
     * */
    static deleteIngredient(chef, ingredientId){
        return new Promise(async (resolve, reject) => {
            try {
                const ingredient = await Ingredient.findOne({
                    chef: chef,
                    _id: ingredientId
                });
                if (!ingredient) {
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                await ingredient.delete();

                resolve({ msg: MSG_TYPES.DELETED });
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }
}

module.exports = IngredientService;