const router = require("express").Router();
const controller = require("../controller");
const {Auth} = require("../middlewares/auth");


router.get('/status', Auth, controller.booking.status);

router.post("/", Auth,controller.booking.create);

router.get("/", Auth,controller.booking.getBooking);

router.get("/:bookingId", Auth,controller.booking.getBookingById);

router.get("/chef/:chefId", Auth,controller.booking.getBookingsForChef);

router.get("/", Auth,controller.booking.getBookings);

router.patch("cancel/:bookingId", Auth,controller.booking.cancelBooking);

router.patch("/:bookingId", Auth,controller.booking.updateBooking);

module.exports = router;