const { MSG_TYPES } = require('../constant/types');

class BookingService{

    /**
     * Create Bookings
     * @param {Object} body Booking
     */
    static create(body){
        return new Promise(async (resolve, reject) => {
            try{

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Get Bookings
     * @param {Number} skip skip
     * @param pageSize
     * @param {Object} filter filter
     */
    static getBookings(skip,pageSize,filter={}){
        return new Promise(async (resolve, reject) => {
            try{

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Get Booking
     * @param {Object} filter filter
     */
    static getBooking(filter){
        return new Promise(async (resolve, reject) => {
            try{

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Update Booking
     * @param {Object} bookingId booking's id
     * @param {Object} bookingObject updated details
     */
    static updateBooking(bookingId, bookingObject){
        return new Promise(async (resolve, reject) => {
            try{

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Cancel Booking
     * @param {Object} bookingId booking's id
     * @param {Object} bookingObject updated details
     */
    static cancelBooking(bookingId){
        return new Promise(async (resolve, reject) => {
            try{

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }
}

module.exports = BookingService;