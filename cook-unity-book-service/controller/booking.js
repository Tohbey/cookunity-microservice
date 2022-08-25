const { JsonResponse } = require('../lib/apiResponse');
const { MSG_TYPES } = require('../constant/types');


exports.status = async (req, res, next) => {
    try {
        JsonResponse(res, 200,"book Service Working");
    }catch (error){
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

exports.create = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

exports.getBooking = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

exports.getBookingById = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

exports.getBookingsForChef = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

exports.getBookings = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

exports.cancelBooking = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

exports.updateBooking = async (req, res, next) => {
    try {

    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}