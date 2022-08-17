const router = require("express").Router();
const controller = require("../controller");

router.post('/',controller.ingredient.createIngredient);

router.get('/', controller.ingredient.getAllIngredients);

router.get('/:ingredientId', controller.ingredient.getIngredient);

router.patch('/:ingredientId', controller.ingredient.updateIngredient);

router.delete('/:ingredientId', controller.ingredient.deleteIngredient);

module.exports = router;