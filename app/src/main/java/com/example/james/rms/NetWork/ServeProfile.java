package com.example.james.rms.NetWork;

/**
 * Created by Jamie on 16/4/2017.
 */

public class ServeProfile {
    public static String serve = "http://123.203.119.83:1123/";

//    public static String serve = "http://123.203.112.174:1123/";
//    public static String serve                                    = "http://123.203.112.174:1123/RMS_Serve/";

    //    public static String serve                                    = "http://123.203.117.130:1123/";
//    public static String serve                                    = "http://192.168.43.36:1123/";
//public static String serve                                    = "http://10.16.81.188:1123/";
    //Login
    final static String login_findAll = "rms/login/findAll";
    final static String login_checklogin = "rms/login/checklogin";
    //Product
    final static String product_findAll = "rms/product/findAll";
    final static String product_findByPartyId = "rms/product/findByPartyId";
    final static String product_findByProductId = "rms/product/findByProductId";
    final static String product_save = "rms/product/save";
    final static String product_deleteByProductId = "rms/product/deleteByProductId";
    final static String product_delete = "rms/product/delete";
    final static String product_updateWeightIdNullByWeightIdAndPartyId = "rms/product/updateWeightIdNullByWeightIdAndPartyId";
    final static String product_updateQuantityIdNullByQuantityIdAndPartyId = "rms/product/updateQuantityIdNullByQuantityIdAndPartyId";
    final static String product_updateQuantityIdAndWeightIdNullByProductId = "rms/product/updateQuantityIdAndWeightIdNullByProductId";

    //ReceivingOrder
    final static String re_order_findAll = "rms/receiving/order/findAll";
    final static String re_order_findByPartyId = "rms/receiving/order/findByPartyId";
    final static String re_order_findByOrderId = "rms/receiving/order/findByOrderId";
    final static String re_order_delete = "rms/receiving/order/delete";
    final static String re_order_save = "rms/receiving/order/save";
    final static String re_saveOrderAndItem = "rms/receiving/order/saveOrderAndItem";

    //ReceivingItem
    final static String re_item_findAll = "rms/receiving/item/findAll";
    final static String re_item_findByPartyId = "rms/receiving/item/findByPartyId";
    final static String re_item_findByReceivingID = "rms/receiving/item/findByReceivingID";
    final static String re_item_save = "rms/receiving/item/save";
    final static String re_item_saves = "rms/receiving/item/saves";
    final static String re_item_deleteByOrderId = "rms/receiving/item/deleteByOrderId";
    final static String re_item_updateOrderIdNullByOrderId = "rms/receiving/item/updateOrderIdNullByOrderId";
    final static String re_item_deleteByProductId = "rms/receiving/item/deleteByProductId";
    final static String re_item_delete= "rms/receiving/item/delete";

    //WeightProfile
    final static String weight_findAll = "rms/weight/findAll";
    final static String weight_findByPartyId = "rms/weight/findByPartyId";
    final static String weight_delete = "rms/weight/delete";
    final static String weight_save = "rms/weight/save";

    //QuantityProfile
    final static String quantity_findAll = "rms/quantity/findAll";
    final static String quantity_findByPartyId = "rms/quantity/findByPartyId";
    final static String quantity_delete = "rms/quantity/delete";
    final static String quantity_save = "rms/quantity/save";
    final static String quantity_updateQtyByQuantityIdAndPartyIdAndQtyUnit = "rms/quantity/updateQtyByQuantityIdAndPartyIdAndQtyUnit";

    //Inventory
    final static String inventory_findAll = "rms/inventory/findAll";
    final static String inventory_findByPartyId = "rms/inventory/findByPartyId";
    final static String inventory_findByPartyIdAndStatus = "rms/inventory/findByPartyIdAndStauts";
    final static String inventory_insertInventory = "rms/inventory/insertInventory";
    final static String inventory_insertInventorys = "rms/inventory/insertInventorys";
    final static String inventory_deleteByProductId = "rms/inventory/deleteByProductId";

    //InventorySum
    final static String inventorySum_findByPartyIdAndStatus = "rms/inventorysum/findByPartyIdAndStatus";
    final static String inventorySum_findByPartyIdAndStatusOrderByProductId = "rms/inventorysum/findByPartyIdAndStatusOrderProductId";

    //Delivery_Order
    final static String de_order_findAll = "rms/delivery/order/findAll";
    final static String de_order_findByOrderIdAndStatus = "rms/delivery/order/findByOrderIdAndStatus";

    //Delivery_Item
    final static String de_item_findAll = "rms/delivery/item/findAll";
    final static String de_item_findByOrderIdAndStatus = "rms/delivery/item/findByOrderIdAndStatus";


    public static String getServe() {
        return serve;
    }

    public static void setServe(String serve) {
        ServeProfile.serve = serve;
    }

    public static String getLogin_findAll() {
        return login_findAll;
    }

    public static String getLogin_checklogin() {
        return login_checklogin;
    }

    public static String getProduct_findAll() {
        return product_findAll;
    }

    public static String getProduct_findByPartyId() {
        return product_findByPartyId;
    }

    public static String getProduct_findByProductId() {
        return product_findByProductId;
    }

    public static String getProduct_save() {
        return product_save;
    }

    public static String getProduct_deleteByProductId() {
        return product_deleteByProductId;
    }

    public static String getProduct_delete() {
        return product_delete;
    }

    public static String getProduct_updateWeightIdNullByWeightIdAndPartyId() {
        return product_updateWeightIdNullByWeightIdAndPartyId;
    }

    public static String getProduct_updateQuantityIdNullByQuantityIdAndPartyId() {
        return product_updateQuantityIdNullByQuantityIdAndPartyId;
    }

    public static String getProduct_updateQuantityIdAndWeightIdNullByProductId() {
        return product_updateQuantityIdAndWeightIdNullByProductId;
    }

    public static String getRe_order_findAll() {
        return re_order_findAll;
    }

    public static String getRe_order_findByPartyId() {
        return re_order_findByPartyId;
    }

    public static String getRe_order_findByOrderId() {
        return re_order_findByOrderId;
    }

    public static String getRe_order_delete() {
        return re_order_delete;
    }

    public static String getRe_order_save() {
        return re_order_save;
    }

    public static String getRe_saveOrderAndItem() {
        return re_saveOrderAndItem;
    }

    public static String getRe_item_findAll() {
        return re_item_findAll;
    }

    public static String getRe_item_findByPartyId() {
        return re_item_findByPartyId;
    }

    public static String getRe_item_findByReceivingID() {
        return re_item_findByReceivingID;
    }

    public static String getRe_item_save() {
        return re_item_save;
    }

    public static String getRe_item_saves() {
        return re_item_saves;
    }

    public static String getRe_item_deleteByOrderId() {
        return re_item_deleteByOrderId;
    }

    public static String getRe_item_updateOrderIdNullByOrderId() {
        return re_item_updateOrderIdNullByOrderId;
    }

    public static String getRe_item_deleteByProductId() {
        return re_item_deleteByProductId;
    }

    public static String getWeight_findAll() {
        return weight_findAll;
    }

    public static String getWeight_findByPartyId() {
        return weight_findByPartyId;
    }

    public static String getWeight_delete() {
        return weight_delete;
    }

    public static String getWeight_save() {
        return weight_save;
    }

    public static String getQuantity_findAll() {
        return quantity_findAll;
    }

    public static String getQuantity_findByPartyId() {
        return quantity_findByPartyId;
    }

    public static String getQuantity_delete() {
        return quantity_delete;
    }

    public static String getQuantity_save() {
        return quantity_save;
    }

    public static String getQuantity_updateQtyByQuantityIdAndPartyIdAndQtyUnit() {
        return quantity_updateQtyByQuantityIdAndPartyIdAndQtyUnit;
    }

    public static String getInventory_findAll() {
        return inventory_findAll;
    }

    public static String getInventory_findByPartyId() {
        return inventory_findByPartyId;
    }

    public static String getInventory_findByPartyIdAndStatus() {
        return inventory_findByPartyIdAndStatus;
    }

    public static String getInventory_insertInventory() {
        return inventory_insertInventory;
    }

    public static String getInventory_insertInventorys() {
        return inventory_insertInventorys;
    }

    public static String getInventory_deleteByProductId() {
        return inventory_deleteByProductId;
    }

    public static String getInventorySum_findByPartyIdAndStatus() {
        return inventorySum_findByPartyIdAndStatus;
    }

    public static String getInventorySum_findByPartyIdAndStatusOrderByProductId() {
        return inventorySum_findByPartyIdAndStatusOrderByProductId;
    }

    public static String getRe_item_delete() {
        return re_item_delete;
    }

    public static String getDe_order_findAll() {
        return de_order_findAll;
    }

    public static String getDe_order_findByOrderIdAndStatus() {
        return de_order_findByOrderIdAndStatus;
    }

    public static String getDe_item_findAll() {
        return de_item_findAll;
    }

    public static String getDe_item_findByOrderIdAndStatus() {
        return de_item_findByOrderIdAndStatus;
    }
}
