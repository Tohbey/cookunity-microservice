const { JsonResponse } = require('../lib/apiResponse');
const { MSG_TYPES } = require('../constant/types');
const {validateBooking} = require("../request/validateBooking");
const BookingService = require("../service/BookingService");
const {paginate} = require("../utils");


exports.status = async (req, res, next) => {
    try {
        JsonResponse(res, 200,"book Service Working");
    }catch (error){
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Create Booking
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.create = async (req, res, next) => {
    try {
        const { error } = validateBooking(req.body);
        if (error) return JsonResponse(res, 400, error.details[0].message);

        let booking = await BookingService.create(req.body);

        JsonResponse(res, 201, MSG_TYPES.CREATED, booking);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get Booking
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getBooking = async (req, res, next) => {
    try {
        let filter = req.query;

        const booking = await BookingService.getBooking(filter);

        JsonResponse(res, 200, MSG_TYPES.FETCHED, booking);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get Booking by id
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getBookingById = async (req, res, next) => {
    try {
        let filter = {
            _id: req.params.bookingId
        };

        const booking = await BookingService.getBooking(filter);

        JsonResponse(res, 200, MSG_TYPES.FETCHED, booking);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get All Bookings for chef
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getBookingsForChef = async (req, res, next) => {
    try {
        const {page, pageSize, skip } = paginate(req);
        let filter = {
            chef: req.params.chefId
        }

        const {bookings, total} = await BookingService.getBookings(skip, pageSize, filter);


        const meta = {
            total,
            pagination:{
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, bookings, meta);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get All Bookings
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getBookings = async (req, res, next) => {
    try {
        const {page, pageSize, skip } = paginate(req);
        let filter = req.query

        const {bookings, total} = await BookingService.getBookings(skip, pageSize, filter);

        const meta = {
            total,
            pagination:{
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, bookings, meta);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

exports.cancelBooking = async (req, res, next) => {
    try {
        const bookingId = req.params.bookingId;

        const booking = await BookingService.cancelBooking(bookingId)

        JsonResponse(res, 200, MSG_TYPES.UPDATED, booking);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

exports.updateBooking = async (req, res, next) => {
    try {
        const bookingId = req.params.bookingId;

        const booking = await BookingService.updateBooking(bookingId, req.body);

        JsonResponse(res, 200, MSG_TYPES.UPDATED, booking);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}