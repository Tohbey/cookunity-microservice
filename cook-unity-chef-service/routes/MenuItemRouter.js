const router = require("express").Router();
const controller = require("../controller");

router.post('/', controller.menuItem.createMenuItem);

router.get('/', controller.menuItem.getAllMenuItems);

router.get('/:menuItemId', controller.menuItem.getMenuItemById);

router.get('/search/:menuItemName', controller.menuItem.getMenuItemByName);

router.patch('/:menuItemId', controller.menuItem.updateMenuItem);

router.delete('/:menuItemId', controller.menuItem.deleteMenuItem);

module.exports = router;