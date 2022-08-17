const router = require("express").Router();
const controller = require("../controller");


router.get('/status', controller.chef.status);

router.get('/', controller.chef.getAllChefs);

router.post('/', controller.chef.createChef);

router.get('/menus', controller.menu.getAllMenusByChef);

router.get('/menu-items', controller.menu.getAllMenusByChef);

router.get('/type/:chefType', controller.chef.getAllChefsByType);

router.get('/:chefId', controller.chef.getChefById);

router.patch('/:chefId', controller.chef.updateChef);

router.patch('/terminate/:chefId', controller.chef.terminateChef);

module.exports = router;