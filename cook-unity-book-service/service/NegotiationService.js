const { MSG_TYPES } = require('../constant/types');
const Negotiation = require('../model/negotiation');

class NegotiationService{
    /**
     * Create/Add Negotiation
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
     * Get Negotiation
     * @param {Number} skip skip
     * @param pageSize
     * @param {Object} filter filter
     */
    static getNegotiation(skip,pageSize,filter={}){
        return new Promise(async (resolve, reject) => {
            try{

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
            }
        })
    }

    /**
     * Get Negotiation
     * @param {Object} filter filter
     */
    static getNegotiation(filter){
        return new Promise(async (resolve, reject) => {
            try{

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
            }
        })
    }

    /**
     * Update Negotiation
     * @param {Object} negotiationId negotiation's id
     * @param {Object} negotiationIdObject updated details
     */
    static updateNegotiation(negotiationId, negotiationIdObject){
        return new Promise(async (resolve, reject) => {
            try{

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
            }
        })
    }

    /**
     * Remove Negotiation
     * @param {Object} negotiationId negotiation's id
     */
    static cancelNegotiation(negotiationId){
        return new Promise(async (resolve, reject) => {
            try{

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
            }
        })
    }
}

module.exports = NegotiationService;