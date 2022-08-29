const { JsonResponse } = require('../lib/apiResponse');
const { MSG_TYPES } = require('../constant/types');
const {validateBookingMeal} = require("../request/ValidateMeal");
const BookingMealService = require("../service/BookingMealService");
const {paginate} = require("../utils");

/**
 * Create Booking meal
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.create = async (req, res, next)=> {
    try {
        const { error } = validateBookingMeal(req.body);
        if (error) return JsonResponse(res, 400, error.details[0].message);

        let meal = await BookingMealService.create(req.body)

        JsonResponse(res, 201, MSG_TYPES.CREATED, meal)
    }catch (error){
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}


/**
 * Get Booking meal
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getBookingMeal = async (req, res, next)=> {
    try {
        let filter = req.query;

        const bookingMeal = await BookingMealService.getBookingMeal(filter);

        JsonResponse(res, 200, MSG_TYPES.FETCHED, bookingMeal);
    }catch (error){
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get Booking meal by id
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getBookingMealById = async (req, res, next)=> {
    try {
        let filter = {
            _id: req.params.bookingMealId
        };

        const bookingMeal = await BookingMealService.getBookingMeal(filter);

        JsonResponse(res, 200, MSG_TYPES.FETCHED, bookingMeal);
    }catch (error){
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get Booking meals For Booking
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getBookingMealsForBooking = async (req, res, next)=> {
    try {
        const {page, pageSize, skip } = paginate(req);
        let filter = {
            booking: req.params.bookingId
        }

        const { bookingMeals, total } = await BookingMealService.getBookingMeals(skip,pageSize, filter);

        const meta = {
            total,
            pagination:{
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, bookingMeals, meta);
    }catch (error){
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get Booking meals
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getBookingMeals = async (req, res, next)=> {
    try {
        const {page, pageSize, skip } = paginate(req);
        let filter = req.query

        const { bookingMeals, total } = await BookingMealService.getBookingMeals(skip,pageSize, filter);

        const meta = {
            total,
            pagination:{
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, bookingMeals, meta);
    }catch (error){
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}


/**
 * Remove Booking meal from booking
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.removeBookingMeal = async (req, res, next)=> {
    try {
        const bookingId = req.params.bookingId;
        const bookingMealId = req.params.bookingMealId;

        await BookingMealService.removeBookingMeal(bookingMealId, bookingId);

        JsonResponse(res, 200, MSG_TYPES.DELETED);
    }catch (error){
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Remove Booking meal from booking
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.updateMeal = async (req, res, next)=> {
    try {
        const bookingMealId = req.params.bookingMealId;

        const meal = await BookingMealService.updateBookingMeal(bookingMealId, req.body);

        JsonResponse(res, 200, MSG_TYPES.UPDATED, meal);
    }catch (error){
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}