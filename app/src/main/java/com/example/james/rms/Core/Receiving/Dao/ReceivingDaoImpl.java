package com.example.james.rms.Core.Receiving.Dao;

import android.util.Log;

import com.example.james.rms.Core.Receiving.Model.V_ReceivingItemModel;
import com.example.james.rms.Core.Receiving.Model.ReceivingOrderModel;
import com.example.james.rms.Core.Receiving.ReceivingPHPPath;
import com.example.james.rms.NetWork.HttpPostAsync;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by james on 26/3/2017.
 */

public class ReceivingDaoImpl implements ReceivingDao {
    ReceivingSplit receivingSplit = new ReceivingSplit();
    @Override
    public List<V_ReceivingItemModel> findReceivingItemByPartyId(String json) {
        Log.d("asd","findReceivingItemByPartyId(Request--JSON):" + json);
        String result = "";
        try {
            result = new HttpPostAsync().execute(ReceivingPHPPath.findReceivingItemByPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<V_ReceivingItemModel> VReceivingItemModel = receivingSplit.convertReceivingItem(result);
        Log.d("asd","findReceivingItemByPartyId(Response) :" + VReceivingItemModel.toString());
        return VReceivingItemModel;
    }

    @Override
    public List<ReceivingOrderModel> findReceivingOrderByPartyId(String json) {
        String result = "";
        Log.d("asd","findReceivingOrderByPartyId(Request--JSON) :" + json);
        try {
            result = new HttpPostAsync().execute(ReceivingPHPPath.findReceivingOrderByPartyId(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<ReceivingOrderModel> receivingOrderModel = receivingSplit.convertReceivingOrder(result);
        Log.d("asd","findReceivingOrderByPartyId(Response) :" + receivingOrderModel.toString());
        return receivingOrderModel;
    }

    @Override
    public List<ReceivingOrderModel> findReceivingOrderByPartyIdAndCreateDate(String json) {
        String result = "";
        Log.d("asd","findReceivingOrderByPartyIdAndCreateDate(Request--JSON) :" + json);
        try {
            result = new HttpPostAsync().execute(ReceivingPHPPath.findOrderIdByPartyIdAndCreateDate(),json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<ReceivingOrderModel> receivingOrderModel = receivingSplit.convertReceivingOrder(result);
        Log.d("asd","findReceivingOrderByPartyIdAndCreateDate(Response) :" + receivingOrderModel.toString());
        return receivingOrderModel;
    }

    @Override
    public String insertIntoReceivingOrder(String json) {
        String result ="";
        Log.d("asd","insertIntoReceivingOrder(Request--JSON) :" + json);
        try{
            result = new HttpPostAsync().execute(ReceivingPHPPath.insertToReceivingOrder(),json).get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","insertIntoReceivingOrder(Response):" + result);
        return result;
    }

    @Override
    public String insertIntoReceivingItem(String json) {
        String result ="";
        Log.d("asd","insertIntoReceivingItem(Request--JSON) :" + json);
        try{
            result = new HttpPostAsync().execute(ReceivingPHPPath.insertToReceivingItem(),json).get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd","insertIntoReceivingItem(Response):" + result);
        return result;
    }
}
