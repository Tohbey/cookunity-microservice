const { MSG_TYPES } = require('../constant/types');
const Booking = require('../model/booking');

class BookingService{

    /**
     * Create Bookings
     * @param {Object} body Booking
     */
    static create(body){
        return new Promise(async (resolve, reject) => {
            try{
                const booking = await Booking.findOne({
                    customerId: body.customerId,
                    chefId: body.chefId,
                    status: 'Open'
                });

                if(booking){
                    reject({statusCode: 404, msg: MSG_TYPES.EXIST});
                    return;
                }

                const newBooking = new Booking(body);
                await newBooking.save();

                resolve(newBooking);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
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
                const bookings = await Booking.find(filter)
                    .skip(skip).limit(pageSize);

                const total = await Booking.find(filter).countDocuments();

                resolve({bookings, total});
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
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
                const booking = await Booking.findOne(filter);
                if(!booking){
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                resolve(booking);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
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
                const booking = await Booking.findById(bookingId);

                if (!booking) {
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                await booking.updateOne(
                    {
                        $set: bookingObject
                    }
                );
                resolve(booking);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
            }
        })
    }

    /**
     * Cancel Booking
     * @param {Object} bookingId booking's id
     * @param {Object} chefId chef's id
     * @param {Object} customerId customer's id
     */
    static cancelBooking(bookingId, chefId = {}, customerId ={}){
        return new Promise(async (resolve, reject) => {
            try{
                const booking = await Booking.findOne({
                    _id: bookingId
                });

                if(!booking){
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                booking.status = "Close";
                await booking.save();

                resolve(booking);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
            }
        })
    }
}

module.exports = BookingService;