package com.example.james.rms.common;

/**
 * Created by jamie on 2017/6/2.
 */

public class StartActivityForResultKey {
    //NavController
    final public static int navNull = 0000;
    final public static int navProduct = 0001;
    final public static int navReceiving = 0002;
    final public static int navInventory = 0003;
    final public static int navDelivery = 0004;


    //
    final public static String navigationController = "NavigationAct";

    //    Product
    final public static String productModel = "ProductModel";

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

    //MovementRecord
    final public static String movementRecord = "MovementRecord";

    //    Operation
    final public static String operationContainer = "OperationAct";
    final public static String productIncrease = "ProductIncrease";
    final public static String receivingIncrease = "ReceivingIncreaseAct";
    final public static String deliveryIncrease = "DeliveryIncreaseAct";


}
