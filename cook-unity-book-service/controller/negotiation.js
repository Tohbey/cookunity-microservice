const { JsonResponse } = require('../lib/apiResponse');
const { MSG_TYPES } = require('../constant/types');
const {validateNegotiation} = require("../request/validateNegotiation");
const NegotiationService = require("../service/NegotiationService");
const {paginate} = require("../utils");


/**
 * Create Negotiation
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.create = async (req, res, next) => {
    try {
        const {error} = validateNegotiation(req.body);
        if (error) return JsonResponse(res, 400, error.details[0].message);

        let negotiation = await NegotiationService.create(req.body);

        JsonResponse(res, 201, MSG_TYPES.CREATED, negotiation);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get Negotiations
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getNegotiations = async (req, res, next) => {
    try {
        const {page, pageSize, skip } = paginate(req);
        let filter = req.query;

        const { negotiations, total } = await NegotiationService.getAllNegotiations(skip, pageSize, filter);

        const meta = {
            total,
            pagination:{
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, negotiations, meta);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get Negotiations for booking
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getNegotiationsForBooking = async (req, res, next) => {
    try {
        const {page, pageSize, skip } = paginate(req);
        let filter = {
            booking: req.params.bookingId
        };

        const { negotiations, total } = await NegotiationService.getAllNegotiations(skip, pageSize, filter);

        const meta = {
            total,
            pagination:{
                pageSize, page
            }
        };

        JsonResponse(res, 200, MSG_TYPES.FETCHED, negotiations, meta);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get Negotiation
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getNegotiation = async (req, res, next) => {
    try {
        let filter = req.params;

        const negotiation = await NegotiationService.getNegotiation(filter);

        JsonResponse(res, 200, MSG_TYPES.FETCHED, negotiation);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}

/**
 * Get Negotiation
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.getNegotiationById = async (req, res, next) => {
    try {
        let filter = {
            _id: req.params.negotiationId
        };

        const negotiation = await NegotiationService.getNegotiation(filter);

        JsonResponse(res, 200, MSG_TYPES.FETCHED, negotiation);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}


/**
 * Update Negotiation
 * @param {*} req
 * @param {*} res
 * @param next
 */
exports.updateNegotiation = async (req, res, next) => {
    try {
        const negotiation = await NegotiationService.updateNegotiation(req.params.negotiationId, req.body);

        JsonResponse(res, 200, MSG_TYPES.UPDATED, negotiation);
    }catch (error) {
        JsonResponse(res, error.statusCode, error.msg)
        next(error)
    }
}