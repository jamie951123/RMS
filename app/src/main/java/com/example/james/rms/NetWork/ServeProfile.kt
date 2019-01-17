package com.example.james.rms.NetWork

/**
 * Created by Jamie on 16/4/2017.
 * a class to store all server path.
 */

object ServeProfile {
    //    public static String serve = "http://123.203.119.83:1123/";    // HOME

    var serve = "http://192.168.1.100:8080/" //Mobile
    //Login
    val login_findAll = "rms/login/findAll"
    val login_findByPartyId = "rms/login/findByPartyId"
    val login_checklogin = "rms/login/checklogin"
    val login_findByFacebookId = "rms/login/findByFacebookId"
    val login_save = "rms/login/save"

    //    Facebook
    val facebook_findAll = "rms/facebook/findAll"
    val facebook_findByFacebookId = "rms/facebook/findByFacebookId"
    val facebook_countFacebookId = "rms/facebook/countFacebookId"

    //Product
    val product_findAll = "rms/product/findAll"
    val product_findByPartyId = "rms/product/findByPartyId"
    val product_findByProductId = "rms/product/findByProductId"
    val product_save = "rms/product/save"
    val product_deleteByProductId = "rms/product/deleteByProductId"
    val product_delete = "rms/product/delete"
    val product_updateWeightIdNullByWeightIdAndPartyId = "rms/product/updateWeightIdNullByWeightIdAndPartyId"
    val product_updateQuantityIdNullByQuantityIdAndPartyId = "rms/product/updateQuantityIdNullByQuantityIdAndPartyId"
    val product_updateQuantityIdAndWeightIdNullByProductId = "rms/product/updateQuantityIdAndWeightIdNullByProductId"

    //ReceivingOrder
    val re_order_findAll = "rms/receiving/order/findAll"
    val re_order_findByPartyId = "rms/receiving/order/findByPartyId"
    val re_order_findByPartyIdAndStatus = "rms/receiving/order/findByPartyIdAndStatus"
    val re_order_findByOrderIdAndStatus = "rms/receiving/order/findByOrderIdAndStatus"
    val re_order_findByOrderId = "rms/receiving/order/findByOrderId"
    val re_order_delete = "rms/receiving/order/delete"
    val re_order_save = "rms/receiving/order/save"
    val re_saveOrderAndItem = "rms/receiving/order/saveOrderAndItem"

    //ReceivingItem
    val re_item_findAll = "rms/receiving/item/findAll"
    val re_item_findByPartyId = "rms/receiving/item/findByPartyId"
    val re_item_findByReceivingID = "rms/receiving/item/findByReceivingID"
    val re_item_save = "rms/receiving/item/save"
    val re_item_saves = "rms/receiving/item/saves"
    val re_item_deleteByOrderId = "rms/receiving/item/deleteByOrderId"
    val re_item_updateOrderIdNullByOrderId = "rms/receiving/item/updateOrderIdNullByOrderId"
    val re_item_deleteByProductId = "rms/receiving/item/deleteByProductId"
    val re_item_delete = "rms/receiving/item/delete"
    val re_item_deletes = "rms/receiving/item/deletes"
    val re_item_deleteByReceivingIds = "rms/receiving/item/deleteByReceivingIds"

    //WeightProfile
    val weight_findAll = "rms/weight/findAll"
    val weight_findByPartyId = "rms/weight/findByPartyId"
    val weight_delete = "rms/weight/delete"
    val weight_save = "rms/weight/save"

    //QuantityProfile
    val quantity_findAll = "rms/quantity/findAll"
    val quantity_findByPartyId = "rms/quantity/findByPartyId"
    val quantity_delete = "rms/quantity/delete"
    val quantity_save = "rms/quantity/save"
    val quantity_updateQtyByQuantityIdAndPartyIdAndQtyUnit = "rms/quantity/updateQtyByQuantityIdAndPartyIdAndQtyUnit"

    //Inventory
    val inventory_findAll = "rms/inventory/findAll"
    val inventory_findByPartyId = "rms/inventory/findByPartyId"
    val inventory_findByPartyIdAndStatus = "rms/inventory/findByPartyIdAndStauts"
    val inventory_findByPartyIdAndStatusOrderByProductIdAsc = "rms/inventory/findByPartyIdAndStatusOrderByProductIdAsc"
    val inventory_save = "rms/inventory/save"
    val inventory_saves = "rms/inventory/saves"
    val inventory_deleteByProductId = "rms/inventory/deleteByProductId"

    //InventorySum
    val inventorySum_findByPartyIdAndStatus = "rms/inventorysum/findByPartyIdAndStatus"
    val inventorySum_findByPartyIdAndStatusOrderByProductId = "rms/inventorysum/findByPartyIdAndStatusOrderProductId"

    //Delivery_Order
    val de_order_findAll = "rms/delivery/order/findAll"
    val de_order_findByPartyIdAndStatus = "rms/delivery/order/findByPartyIdAndStauts"
    val de_order_findByOrderIdAndStatus = "rms/delivery/order/findByOrderIdAndStatus"
    val de_order_delete = "rms/delivery/order/delete"
    val de_order_save = "rms/delivery/order/save"
    val de_saveOrderAndItem = "rms/delivery/order/saveOrderAndItem"


    //Delivery_Item
    val de_item_findAll = "rms/delivery/item/findAll"
    val de_item_findByPartyIdAndStatus = "rms/delivery/item/findByPartyIdAndStauts"
    val de_item_findByOrderIdAndStatus = "rms/delivery/item/findByOrderIdAndStatus"
    val de_item_delete = "rms/delivery/item/delete"
    val de_item_save = "rms/delivery/item/save"
    val de_item_saves = "rms/delivery/item/save"
}
