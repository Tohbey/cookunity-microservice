const router = require("express").Router();
const controller = require("../controller");

router.post('/', controller.menu.createMenu);

router.get('/', controller.menu.getAllMenus);

router.get('/:meunId', controller.menu.getMenuById);

router.get('/title/:menuTitle', controller.menu.getMenuByTitle);

router.get('/menu-items/:meunId', controller.menu.getAllMenuItemsOnMenu);

router.patch('/:meunId', controller.menu.updateMenu);

router.delete('/:meunId', controller.menu.deleteMenu);

module.exports = router;