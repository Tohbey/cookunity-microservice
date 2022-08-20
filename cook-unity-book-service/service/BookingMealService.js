const { MSG_TYPES } = require('../constant/types');
const BookingMeal = require('../model/meals');

class BookingMealService{
    /**
     * Create/Add Booking Meals
     * @param {Object} body Booking
     */
    static create(body){
        return new Promise(async (resolve, reject) => {
            try{

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
            }
        })
    }

    /**
     * Get Booking Meals
     * @param {Number} skip skip
     * @param pageSize
     * @param {Object} filter filter
     */
    static getBookingMeals(skip,pageSize,filter={}){
        return new Promise(async (resolve, reject) => {
            try{

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
            }
        })
    }

    /**
     * Get Booking Meal
     * @param {Object} filter filter
     */
    static getBookingMeal(filter){
        return new Promise(async (resolve, reject) => {
            try{

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
            }
        })
    }

    /**
     * Update Booking Meal
     * @param {Object} bookingMealId booking meal's id
     * @param {Object} bookingMealObject updated details
     */
    static updateBookingMeal(bookingMealId, bookingMealObject){
        return new Promise(async (resolve, reject) => {
            try{

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
            }
        })
    }

    /**
     * Remove Booking Meal
     * @param {Object} bookingMealId booking meal's id
     */
    static cancelBookingMeal(bookingMealId){
        return new Promise(async (resolve, reject) => {
            try{

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
            }
        })
    }
}

module.exports = BookingMealService;