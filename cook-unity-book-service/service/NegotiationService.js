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
                const negotiation = await Negotiation.findOne({
                    booking: body.booking
                })
                if(negotiation){
                    const updatedNegotiation = await this.updateNegotiation(body._id, body)
                    resolve(updatedNegotiation);
                }

                const newNegotiation = new Negotiation(body);
                await newNegotiation.save();

                resolve(newNegotiation);
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
    static getAllNegotiations(skip,pageSize,filter={}){
        return new Promise(async (resolve, reject) => {
            try{
                const negotiations = await Negotiation.find(filter)
                    .skip(skip).limit(pageSize);

                const total = await Negotiation.find(filter).countDocuments();

                resolve({negotiations, total});
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
                const negotiation = await Negotiation.findOne(filter);
                if(!negotiation){
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                resolve(negotiation);
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
                const negotiation = await Negotiation.findById(negotiationId);

                if (!negotiation) {
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND });
                }

                await negotiation.updateOne(
                    {
                        $set: negotiationIdObject
                    }
                );
                resolve(negotiation);
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error});
            }
        })
    }
}

module.exports = NegotiationService;