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
                const meal = await BookingMeal.findOne({
                    name: body.name,
                    booking: body.booking
                });

                if(meal){
                    reject({ statusCode: 404, msg: MSG_TYPES.EXIST });
                    return;
                }

                const newMeal = new BookingMeal(body);
                await newMeal.save();

                resolve(newMeal);
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
                const meals = await BookingMeal.find(filter)
                    .skip(skip).limit(pageSize);

                const total = await BookingMeal.find(filter).countDocuments();

                resolve({meals, total});
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
                const meal = await BookingMeal.findOne(filter);
                if(!meal){
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                resolve(meal);
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
                const meal = await BookingMeal.findById(bookingMealId);

                if (!meal) {
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                await meal.updateOne(
                    {
                        $set: bookingMealObject
                    }
                );
                resolve(meal);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
            }
        })
    }

    /**
     * Remove Booking Meal
     * @param {Object} bookingMealId booking meal's id
     */
    static removeBookingMeal(bookingMealId){
        return new Promise(async (resolve, reject) => {
            try{
                const bookingMeal = await BookingMeal.findOne({
                    _id: bookingMealId
                });
                if (!bookingMeal) {
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                await bookingMeal.delete();

                resolve({ msg: MSG_TYPES.DELETED });
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
            }
        })
    }
}

module.exports = BookingMealService;