package com.example.james.rms.CommonProfile;

/**
 * Created by jamie on 2017/6/2.
 */

public class StartActivityForResultKey {
    //NavController
    final public static int navNull = 0000;
    final public static int navProduct = 0001;
    final public static int navReceiving = 0002;
    final public static int navInventory = 0003;

//    Product
    final public static String productModel = "ProductModel";
    final public static int editProduct = 1000;

//    ReceivingOrder
    final public static String receivingOrderModel = "ReceivingOrderModel";

//    ReceivingItem
    final public static String receivingItemModel = "ReceivingItemModel";


    //    Inventory
    final public static String inventoryModel = "InventoryModel";


    //    DeliveryOrder
    final public static String deliveryOrderModel = "DeliveryOrderModel";

    //  DeliveryItem
    final public static String deliveryItemModel = "DeliveryItemModel";

    //    Operation
    final public static String productIncrease = "ProductIncrease";
    final public static String receivingIncrease = "ReceivingIncrease";
    final public static String deliveryIncrease = "DeliveryIncrease";


}
