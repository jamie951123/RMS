package com.example.james.rms.network

/**
 * Created by Jamie on 16/4/2017.
 * a class to store all server path.
 */

object ServeProfile {
    //    public static String serve = "http://123.203.119.83:1123/";    // HOME

    var serve = "http://192.168.1.100:8080/" //Mobile

    object Login {
        const val login_findAll = "rms/login/findAll"
        const val login_findByPartyId = "rms/login/findByPartyId"
        const val login_checklogin = "rms/login/checklogin"
        const val login_findByFacebookId = "rms/login/findByFacebookId"
        const val login_save = "rms/login/save"
    }

    //    Facebook
    object Facebook {
        const val facebook_findAll = "rms/facebook/findAll"
        const val facebook_findByFacebookId = "rms/facebook/findByFacebookId"
        const val facebook_countFacebookId = "rms/facebook/countFacebookId"
    }

    //Product
    object Product {
        const val product_findAll = "rms/product/findAll"
        const val product_findByPartyId = "rms/product/findByPartyId"
        const val product_findByProductId = "rms/product/findByProductId"
        const val product_save = "rms/product/save"
        const val product_deleteByProductId = "rms/product/deleteByProductId"
        const val product_delete = "rms/product/delete"
        const val product_updateWeightIdNullByWeightIdAndPartyId = "rms/product/updateWeightIdNullByWeightIdAndPartyId"
        const val product_updateQuantityIdNullByQuantityIdAndPartyId = "rms/product/updateQuantityIdNullByQuantityIdAndPartyId"
        const val product_updateQuantityIdAndWeightIdNullByProductId = "rms/product/updateQuantityIdAndWeightIdNullByProductId"
        const val product_uploadImage = "rms/product/uploadProductImage"
    }

    //ReceivingOrder
    object ReceivingOrder {
        const val re_order_findAll = "rms/receiving/order/findAll"
        const val re_order_findByPartyId = "rms/receiving/order/findByPartyId"
        const val re_order_findByPartyIdAndStatus = "rms/receiving/order/findByPartyIdAndStatus"
        const val re_order_findByOrderIdAndStatus = "rms/receiving/order/findByOrderIdAndStatus"
        const val re_order_findByOrderId = "rms/receiving/order/findByOrderId"
        const val re_order_delete = "rms/receiving/order/delete"
        const val re_order_save = "rms/receiving/order/save"
        const val re_saveOrderAndItem = "rms/receiving/order/saveOrderAndItem"
    }

    //ReceivingItem
    object ReceivingItem {
        const val re_item_findAll = "rms/receiving/item/findAll"
        const val re_item_findByPartyId = "rms/receiving/item/findByPartyId"
        const val re_item_findByReceivingID = "rms/receiving/item/findByReceivingID"
        const val re_item_save = "rms/receiving/item/save"
        const val re_item_saves = "rms/receiving/item/saves"
        const val re_item_deleteByOrderId = "rms/receiving/item/deleteByOrderId"
        const val re_item_updateOrderIdNullByOrderId = "rms/receiving/item/updateOrderIdNullByOrderId"
        const val re_item_deleteByProductId = "rms/receiving/item/deleteByProductId"
        const val re_item_delete = "rms/receiving/item/delete"
        const val re_item_deletes = "rms/receiving/item/deletes"
        const val re_item_deleteByReceivingIds = "rms/receiving/item/deleteByReceivingIds"
    }

    //WeightProfile
    object WeightProfile {
        const val weight_findAll = "rms/weight/findAll"
        const val weight_findByPartyId = "rms/weight/findByPartyId"
        const val weight_delete = "rms/weight/delete"
        const val weight_save = "rms/weight/save"
    }

    //QuantityProfile
    object QuantityProfile {
        const val quantity_findAll = "rms/quantity/findAll"
        const val quantity_findByPartyId = "rms/quantity/findByPartyId"
        const val quantity_delete = "rms/quantity/delete"
        const val quantity_save = "rms/quantity/save"
        const val quantity_updateQtyByQuantityIdAndPartyIdAndQtyUnit = "rms/quantity/updateQtyByQuantityIdAndPartyIdAndQtyUnit"
    }

    //Inventory
    object Inventory {
        const val inventory_findAll = "rms/inventory/findAll"
        const val inventory_findByPartyId = "rms/inventory/findByPartyId"
        const val inventory_findByPartyIdAndStatus = "rms/inventory/findByPartyIdAndStauts"
        const val inventory_findByPartyIdAndStatusOrderByProductIdAsc = "rms/inventory/findByPartyIdAndStatusOrderByProductIdAsc"
        const val inventory_save = "rms/inventory/save"
        const val inventory_saves = "rms/inventory/saves"
        const val inventory_deleteByProductId = "rms/inventory/deleteByProductId"
    }

    //InventorySum
    object InventorySum {
        const val inventorySum_findByPartyIdAndStatus = "rms/inventorysum/findByPartyIdAndStatus"
        const val inventorySum_findByPartyIdAndStatusOrderByProductId = "rms/inventorysum/findByPartyIdAndStatusOrderProductId"
    }

    //DeliveryOrderFrag
    object DeliveryOrder {
        const val de_order_findAll = "rms/delivery/order/findAll"
        const val de_order_findByPartyIdAndStatus = "rms/delivery/order/findByPartyIdAndStauts"
        const val de_order_findByOrderIdAndStatus = "rms/delivery/order/findByOrderIdAndStatus"
        const val de_order_delete = "rms/delivery/order/delete"
        const val de_order_save = "rms/delivery/order/save"
        const val de_saveOrderAndItem = "rms/delivery/order/saveOrderAndItem"

    }

    //DeliveryItemFrag
    object DeliveryItem {
        const val de_item_findAll = "rms/delivery/item/findAll"
        const val de_item_findByPartyIdAndStatus = "rms/delivery/item/findByPartyIdAndStauts"
        const val de_item_findByOrderIdAndStatus = "rms/delivery/item/findByOrderIdAndStatus"
        const val de_item_delete = "rms/delivery/item/delete"
        const val de_item_save = "rms/delivery/item/save"
        const val de_item_saves = "rms/delivery/item/save"
    }
}
