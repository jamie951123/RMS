package com.example.james.rms.NetWork;

/**
 * Created by Jamie on 16/4/2017.
 */

public class ServeProfile {
    public static String serve                                    = "http://123.203.112.174:1123/RMS_Serve/";
//    public static String serve                                    = "http://123.203.117.130:1123/";
//    public static String serve                                    = "http://192.168.43.36:1123/";
//public static String serve                                    = "http://10.16.81.188:1123/";
    //Login
    final static String login_findAll                                                    = "rms/login/findAll";
    final static String login_checklogin                                                 = "rms/login/checklogin";
    //Product
    final static String product_findAll                                                 = "rms/product/findAll";
    final static String product_findByPartyId                                            = "rms/product/findByPartyId";
    final static String product_insert                                                  = "rms/product/insertProduct";
    final static String product_updateWeightIdNullByWeightIdAndPartyId                  = "rms/product/updateWeightIdNullByWeightIdAndPartyId";
    final static String product_updateQuantityIdNullByQuantityIdAndPartyId                    = "rms/product/updateQuantityIdNullByQuantityIdAndPartyId";

    //Receiving
    final static String orderFindByPartyId                                      = "rms/receiving/order/findByPartyId";
    final static String itemFindByPartyId                                       = "rms/receiving/item/findByPartyId";
    final static String insertReceivingOrder                                    = "rms/receiving/order/insertReceivingOrder";
    final static String insertReceivingItem                                      = "rms/receiving/item/insertReceivingItem";
    final static String saveOrderAndItem                                     = "rms/receiving/orderitem/saveOrderAndItem";
    //WeightProfile
    final static String weight_findAll                                           = "rms/weight/findAll";
    final static String weight_findByPartyId                                  = "rms/weight/findByPartyId";
    final static String weight_delete                               ="rms/weight/delete";
    final static String weight_save                                 ="rms/weight/save";

    //QuantityProfile
    final static String quantity_findAll                                = "rms/quantity/findAll";
    final static String quantity_findByPartyId                        = "rms/quantity/findByPartyId";
    final static String quantity_delete                                 ="rms/quantity/delete";
    final static String quantity_save                                ="rms/quantity/save";
    final static String quantity_updateQtyByQuantityIdAndPartyIdAndQtyUnit                                ="rms/quantity/updateQtyByQuantityIdAndPartyIdAndQtyUnit";

    //Inventory
    final static String inventory_findAll                            ="rms/inventory/findAll";
    final static String inventory_findByPartyId                     ="rms/inventory/findByPartyId";
    final static String inventory_findByPartyIdAndStatus         ="rms/inventory/findByPartyIdAndStauts";
    final static String inventory_insertInventory                ="rms/inventory/insertInventory";
    final static String inventory_insertInventorys                ="rms/inventory/insertInventorys";

    //InventorySum
    final static String inventorySum_findByPartyIdAndStatus                      ="rms/inventorysum/findByPartyIdAndStatus";
    final static String inventorySum_findByPartyIdAndStatusOrderByProductId     ="rms/inventorysum/findByPartyIdAndStatusOrderProductId";

    public static String getInventory_findAll() {
        return inventory_findAll;
    }

    public static String getInventory_findByPartyId() {
        return inventory_findByPartyId;
    }

    public static String getInventory_insertInventory() {
        return inventory_insertInventory;
    }

    public static String getInventory_insertInventorys() {
        return inventory_insertInventorys;
    }

    public static String getSaveOrderAndItem() {
        return saveOrderAndItem;
    }

    public static String getInsertReceivingOrder() {
        return insertReceivingOrder;
    }

    public static String getInsertReceivingItem() {
        return insertReceivingItem;
    }
    public static String getProduct_findAll() {
        return product_findAll;
    }

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

    public static String getProduct_findByPartyId() {
        return product_findByPartyId;
    }

    public static String getProduct_insert() {
        return product_insert;
    }

    public static String getOrderFindByPartyId() {
        return orderFindByPartyId;
    }

    public static String getItemFindByPartyId() {
        return itemFindByPartyId;
    }

    public static String getWeight_findAll() {
        return weight_findAll;
    }

    public static String getWeight_findByPartyId() {
        return weight_findByPartyId;
    }

    public static String getQuantity_findAll() {
        return quantity_findAll;
    }

    public static String getQuantity_findByPartyId() {
        return quantity_findByPartyId;
    }

    public static String getInventory_findByPartyIdAndStatus() {
        return inventory_findByPartyIdAndStatus;
    }

    public static String getInventorySum_findByPartyIdAndStatus() {
        return inventorySum_findByPartyIdAndStatus;
    }

    public static String getInventorySum_findByPartyIdAndStatusOrderByProductId() {
        return inventorySum_findByPartyIdAndStatusOrderByProductId;
    }

    public static String getWeight_delete() {
        return weight_delete;
    }

    public static String getProduct_updateWeightIdNullByWeightIdAndPartyId() {
        return product_updateWeightIdNullByWeightIdAndPartyId;
    }

    public static String getProduct_updateQuantityIdNullByQuantityIdAndPartyId() {
        return product_updateQuantityIdNullByQuantityIdAndPartyId;
    }

    public static String getWeight_save() {
        return weight_save;
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
}
