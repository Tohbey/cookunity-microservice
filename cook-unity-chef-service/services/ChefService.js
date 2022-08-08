const Chef = require('../models/Chef');
const { MSG_TYPES } = require('../constant/types');

class ChefService{
    static create(){
        return new Promise(async (resolve, reject) => {
            try {

            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Get chefs
     * @param {Number} skip skip
     * @param {Number} pageSize page size
     * @param {Object} filter filter
     */
    static getAllChefs(skip, pagesSize, filter = {}){
        return new Promise(async (resolve, reject) => {
            try {
                const chefs = await Chef.find(filter)
                    .skip(skip).limit(pagesSize)

                const total = await Chef.find(filter).countDocuments()

                resolve({chefs, total})
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Get chef
     * @param {Object} filter filter
     */
    static getChef(filter){
        return new Promise(async (resolve, reject) => {
            try {
                const chef = await Chef.findOne(filter)
                if(!chef){
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND })
                }

                resolve(chef)
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Terminate User
     * @param {Object} chef Chef
     */
    static terminateChef(chef){
        return new Promise(async (resolve, reject) => {
            try {
                const currentChef = await Chef.findOne({
                    _id: chef._id,
                    status: "Active"
                })
                if (!currentChef) {
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND })
                }

                currentChef.status = "Terminated";
                await currentChef.save();

                resolve({ msg: MSG_TYPES.DELETED })
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }

    /**
     * Update Chef
     * @param {object} chefId chef's id
     * @param {Object} chefObject updated details
     */
    static update(chefId, chefObject){
        return new Promise(async (resolve, reject) => {
            try {
                const chef = await Chef.findById(chefId);

                if (!chef) {
                    return reject({ statusCode: 404, msg: MSG_TYPES.NOT_FOUND })
                }

                await chef.updateOne(
                    {
                        $set: chefObject
                    }
                )
                resolve(chef)
            }catch (error) {
                reject({statusCode:500, msg: MSG_TYPES.SERVER_ERROR, error})
            }
        })
    }
}

module.exports = ChefService;