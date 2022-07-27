const router = require("express").Router();
const controller = require("../controller");


router.get('/status', controller.chef.status);


module.exports = router;