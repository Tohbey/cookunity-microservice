const router = require("express").Router();
const controller = require("../controller");
const {Auth} = require("../middlewares/auth");

router.post("/", Auth, controller.bookingMeal.create)

router.get("/single", Auth, controller.bookingMeal.getBookingMeal)

router.get("/:bookingMealId", Auth, controller.bookingMeal.getBookingMealById)

router.get("/find", Auth, controller.bookingMeal.getBookingMeals)

router.delete("/:bookingMealId/booking/:bookingId", Auth, controller.bookingMeal.removeBookingMeal)

router.patch("/:bookingMealId", Auth, controller.bookingMeal.updateMeal)

module.exports = router;