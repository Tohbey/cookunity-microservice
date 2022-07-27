const router = require("express").Router();
const controller = require("../controller");


router.get('/status', controller.order.status);


module.exports = router;