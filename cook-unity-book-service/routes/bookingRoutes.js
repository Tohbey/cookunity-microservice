const router = require("express").Router();
const controller = require("../controller");


router.get('/status', controller.booking.status);


module.exports = router;