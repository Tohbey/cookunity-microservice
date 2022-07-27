const router = require("express").Router();
const controller = require("../controller");


router.get('/status', controller.book.status);


module.exports = router;