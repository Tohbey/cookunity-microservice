const router = require("express").Router();
const controller = require("../controller");
const {Auth} = require("../middlewares/auth");

router.post("/", Auth, controller.negotiation.create);

router.get("/", Auth, controller.negotiation.getNegotiations);

router.get("/single", Auth, controller.negotiation.getNegotiation);

router.get("/:negotiationId", Auth, controller.negotiation.getNegotiationById);

router.get("/find", Auth, controller.negotiation.getNegotiations);

router.patch("/:negotiationId", Auth, controller.negotiation.updateNegotiation);

module.exports = router;