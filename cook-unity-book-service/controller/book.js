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