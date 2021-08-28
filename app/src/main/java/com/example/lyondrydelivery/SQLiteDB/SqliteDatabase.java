package com.example.lyondrydelivery.SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.lyondrydelivery.Modal.DeliveryRequestComplited_Modal;
import com.example.lyondrydelivery.Modal.InsertOrder.GridTrans;
import com.example.lyondrydelivery.Modal.ItemModal;
import com.example.lyondrydelivery.Modal.LocalResponse;
import com.example.lyondrydelivery.Modal.PickupItemModal;
import com.example.lyondrydelivery.Modal.SelectItem.SelecteItem_data;

import java.util.ArrayList;
import java.util.List;

import static java.nio.file.attribute.AclEntryPermission.DELETE;

public class SqliteDatabase  extends SQLiteOpenHelper {

    public Context context;
    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "items_database";
    //Database Table name
    private static final String TABLE_NAME = "ITEMS_TABLE";
    public static final String TABLE_NAME1 = "IMAGE_TABLE";
    public static final String TABLE_NAME2 = "PICKUP_COMPLITE";
    public static final String TABLE_NAME3 = "Delivery_COMPLITE";
    //Table columns
    public static final String ID = "id";
    public static final String SNO = "sno";

    public static final String GROUPITEM = "gitem";
    public static final String QUANTITY = "quantity";
    public static final String AMOUNT = "amount";
    public static final String COLOR = "color";
    public static final String BRAND = "brand";
    public static final String UNITPRICE = "unitprice";
    public static final String DISCRIPTION = "discription";
    public static final String ITEMCODE = "ItemCode";
    public static final String ITEMSERVICEID = "ItemServiceID";
    public static final String ITEMSERVICETYPE = "ItemServiceType";
    public static final String ITEMSREMAKS2 = "ItemsRemaks2";
    public static final String ITEMSPECIALINSTRUCTION = "ItemsSpecialInstruction";
    public static final String ITEMPACKTYPE = "PackType";
    public static final String PICKUPID = "PickupId";
    public static final String KEY_ID = "id";
    public static final String KEY_IMG_URL = "image";
    public static final String ITEMS_ID = "items_id";

    public static final String PickupRequestId = "pickuprequestid";
    public static final String PickupRequestServiceType = "pickupRequestservicetype";
    public static final String PickupRequestServiceName = "pickuprequestservicename";
    public static final String PickupRequestStoreName = "pickuprequestrtorename";
    public static final String PickupRequestAddress = "pickuprequestaddress";
    public static final String PickupRequestDate = "pickuprequestdate";
    public static final String PickupRequestTime = "pickuprequesttime";
    public static final String PickupRequestStatus = "pickuprequeststatus";
    public static final String Locations = "location";
    public static final String CustomerName = "customername";
    public static final String CustomerId = "customerid";

    public static final String DeliveryRequestId = "deliveryrequestid";
    public static final String DeliveryRequestServiceType = "deliverydequestservicetype";
    public static final String DeliveryRequestServiceName = "deliveryrequestservicename";
    public static final String DeliveryRequestStoreName = "deliveryrequeststorename";
    public static final String DeliveryRequestAddress = "deliveryrequestaddress";
    public static final String DeliveryRequestDate = "deliveryrequestdate";
    public static final String DeliveryRequestDueDate = "deliveryrequestduedate";
    public static final String DeliveryRequestTime = "DeliveryRequestTime";
    public static final String DeliveryRequestStatus = "deliveryrequeststatus";
    public static final String DeliveryLocation = "location";
    public static final String DeliveryCustomerName = "customername";
    public static final String DeliveryCustomerId = "customerid";



    private SQLiteDatabase sqLiteDatabase;

    //****************creating table query in add new Items in recycleview*****************
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            GROUPITEM + " TEXT NOT NULL," +
            QUANTITY + " TEXT NOT NULL," +
            AMOUNT + " TEXT NOT NULL," +
            UNITPRICE + " TEXT NOT NULL," +
            ITEMCODE + " TEXT NOT NULL," +
            ITEMSERVICEID + " TEXT NOT NULL," +
            ITEMSERVICETYPE + " TEXT NOT NULL," +
            ITEMSPECIALINSTRUCTION + " TEXT NOT NULL," +
            ITEMPACKTYPE + " TEXT NOT NULL," +
            ITEMSREMAKS2 + " TEXT ," +
            PICKUPID + " TEXT NOT NULL," +
            DISCRIPTION + " TEXT );";


    private static final String CREATE_TABLE2 = "create table " + TABLE_NAME2 + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            PickupRequestId + " TEXT NOT NULL," +
            PickupRequestServiceType + " TEXT NOT NULL," +
            PickupRequestServiceName + " TEXT NOT NULL," +
            PickupRequestStoreName + " TEXT NOT NULL," +
            PickupRequestAddress + " TEXT NOT NULL," +
            PickupRequestDate + " TEXT NOT NULL," +
            PickupRequestTime + " TEXT NOT NULL," +
            PickupRequestStatus + " TEXT ," +
            Locations + " TEXT NOT NULL," +
            CustomerName + " TEXT NOT NULL," +
            CustomerId + " TEXT NOT NULL);";

    private static final String CREATE_TABLE3 = "create table " + TABLE_NAME3 + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DeliveryRequestId + " TEXT NOT NULL," +
            DeliveryRequestServiceType + " TEXT NOT NULL," +
            DeliveryRequestServiceName + " TEXT NOT NULL," +
            DeliveryRequestStoreName + " TEXT NOT NULL," +
            DeliveryRequestAddress + " TEXT NOT NULL," +
            DeliveryRequestDate + " TEXT NOT NULL," +
            DeliveryRequestDueDate + " TEXT NOT NULL," +
            DeliveryRequestTime + " TEXT ," +
            DeliveryRequestStatus + " TEXT NOT NULL," +
            DeliveryLocation + " TEXT NOT NULL," +
            DeliveryCustomerName + " TEXT NOT NULL," +
            DeliveryCustomerId + " TEXT NOT NULL);";

    //****************creating table query in add new Items in recycleview*****************

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE1);
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE3);
    }

    //**********************************Only add image in recycleview in Fram************************
    public static final String CREATE_TABLE1 = "CREATE TABLE " + TABLE_NAME1 + "(" + KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_IMG_URL + " TEXT" + ", " +ITEMS_ID+" TEXT "+")";

    //**********************drop table in Image only******************************
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME1 + "";

    //**********************drop table in Image only******************************
    //*****************************Add new Items all deta in recycleveiw **********************
    //Add Items Data
   public void imgAdd(LocalResponse localResponse){
       ContentValues contentValues =new ContentValues();
       contentValues.put(SqliteDatabase.KEY_IMG_URL,localResponse.getImage());
       contentValues.put(SqliteDatabase.ITEMS_ID,localResponse.getItemid());
       System.out.println("*************getItemid**********"+localResponse.getItemid());
       sqLiteDatabase = this.getWritableDatabase();
       sqLiteDatabase.insert(SqliteDatabase.TABLE_NAME1, null, contentValues);
   }

    public void addItems(ItemModal itemModal) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SqliteDatabase.GROUPITEM, itemModal.getItems());
        contentValues.put(SqliteDatabase.QUANTITY, itemModal.getQty());
        contentValues.put(SqliteDatabase.AMOUNT, itemModal.getRate());
        contentValues.put(SqliteDatabase.UNITPRICE,itemModal.getUnitprice());
        contentValues.put(SqliteDatabase.ITEMCODE,itemModal.getODItemCode());
        contentValues.put(SqliteDatabase.ITEMSERVICEID,itemModal.getODServiceId());
        contentValues.put(SqliteDatabase.ITEMSERVICETYPE,itemModal.getODServiceType());
        contentValues.put(SqliteDatabase.ITEMSPECIALINSTRUCTION,itemModal.getODSpecialInstruction());
        contentValues.put(SqliteDatabase.ITEMPACKTYPE,itemModal.getODDeliveryPackType());
        contentValues.put(SqliteDatabase.ITEMSREMAKS2,itemModal.getODRemarks2());
        contentValues.put(SqliteDatabase.DISCRIPTION, itemModal.getDiscription());
        contentValues.put(SqliteDatabase.PICKUPID,itemModal.getPickupId());

        Log.i("SNO","******sno**********" + itemModal.getSerialNo());
        Log.i("ID","*******id*********" + itemModal.getId());
        Log.i("GROUPITEM", "" + itemModal.getItems());
        Log.i("QUANTITY", "" + itemModal.getQty());
        Log.i("AMOUNT", "" + itemModal.getRate());
        Log.i("DISCRIPTION", "" + itemModal.getDiscription());
        Log.i("ITEMCODE", "" + itemModal.getODItemCode());

        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(SqliteDatabase.TABLE_NAME, null, contentValues);
    }

    public void addPickupitem(PickupItemModal pickupItemModal) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SqliteDatabase.PickupRequestId, pickupItemModal.getPickupRequestId());
        contentValues.put(SqliteDatabase.PickupRequestServiceType, pickupItemModal.getPickupRequestServiceType());
        contentValues.put(SqliteDatabase.PickupRequestServiceName, pickupItemModal.getPickupRequestServiceName());
        contentValues.put(SqliteDatabase.PickupRequestStoreName,pickupItemModal.getPickupRequestStoreName());
        contentValues.put(SqliteDatabase.PickupRequestAddress,pickupItemModal.getPickupRequestAddress());
        contentValues.put(SqliteDatabase.PickupRequestDate,pickupItemModal.getPickupRequestDate());
        contentValues.put(SqliteDatabase.PickupRequestTime,pickupItemModal.getPickupRequestTime());
        contentValues.put(SqliteDatabase.PickupRequestStatus,pickupItemModal.getPickupRequestStatus());
        contentValues.put(SqliteDatabase.Locations,pickupItemModal.getLocation());
        contentValues.put(SqliteDatabase.CustomerName,pickupItemModal.getCustomerName());
        contentValues.put(SqliteDatabase.CustomerId, pickupItemModal.getCustomerId());


        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(SqliteDatabase.TABLE_NAME2, null, contentValues);
    }


    public void addPickupitem(DeliveryRequestComplited_Modal deliveryRequestComplitedModal) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SqliteDatabase.PickupRequestId, deliveryRequestComplitedModal.getDeliveryRequestId());
        contentValues.put(SqliteDatabase.PickupRequestServiceType, deliveryRequestComplitedModal.getDeliveryRequestServiceType());
        contentValues.put(SqliteDatabase.PickupRequestServiceName, deliveryRequestComplitedModal.getDeliveryRequestServiceName());
        contentValues.put(SqliteDatabase.PickupRequestStoreName,deliveryRequestComplitedModal.getDeliveryRequestStoreName());
        contentValues.put(SqliteDatabase.PickupRequestAddress,deliveryRequestComplitedModal.getDeliveryRequestAddress());
        contentValues.put(SqliteDatabase.PickupRequestDate,deliveryRequestComplitedModal.getDeliveryRequestDate());
        contentValues.put(SqliteDatabase.PickupRequestTime,deliveryRequestComplitedModal.getDeliveryRequestTime());
        contentValues.put(SqliteDatabase.PickupRequestStatus,deliveryRequestComplitedModal.getDeliveryRequestStatus());
        contentValues.put(SqliteDatabase.Locations,deliveryRequestComplitedModal.getDeliveryLocation());
        contentValues.put(SqliteDatabase.CustomerName,deliveryRequestComplitedModal.getDeliveryCustomerName());
        contentValues.put(SqliteDatabase.CustomerId, deliveryRequestComplitedModal.getDeliveryCustomerId());


        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(SqliteDatabase.TABLE_NAME3, null, contentValues);
    }
    //*****************************Add new Items all deta in recycleveiw **********************


    //*****************************View all deta in recycleveiw**********************




    public List<ItemModal> getPickupIdList() {
        //String sql = "select * from "+ TABLE_NAME ;
        String sql = "select (select count(*) from " + TABLE_NAME + " a  where a.id >= b.id)sno,b.id,gitem,quantity,amount,unitprice,ItemCode,ItemServiceID,ItemServiceType,ItemsSpecialInstruction,PackType,ItemsRemaks2,discription,PickupId\n" +
                " from " + TABLE_NAME + " b order by sno";
        sqLiteDatabase = this.getReadableDatabase();
        List<ItemModal> itemModals = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            itemModals.clear();
            do {
                int sno = Integer.parseInt(String.valueOf(cursor.getString(0)));
                int id = Integer.parseInt(String.valueOf(cursor.getInt(1)));
                String gitems = cursor.getString(2);
                String quantity = cursor.getString(3);
                String amount = cursor.getString(4);
                String unnitprice =cursor.getString(5);
                String itemcode = cursor.getString(6);
                String itemserviceid = cursor.getString(7);
                String itemservicetype = cursor.getString(8);
                String itemspecialinstruction = cursor.getString(9);
                String itemspacktype = cursor.getString(10);
                String itemremakes2 = cursor.getString(11);
                String discription = cursor.getString(12);
                String pickupid = cursor.getString(13);

                itemModals.add(new ItemModal(id,sno, gitems, quantity,amount,unnitprice,itemcode,itemserviceid,itemservicetype,itemspecialinstruction,itemspacktype,itemremakes2, discription,pickupid));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return itemModals;
    }

    public List<PickupItemModal> getRequestPickup() {
        //String sql = "select * from "+ TABLE_NAME ;
        String sql = "select (select count(*) from " + TABLE_NAME2 + " a  where a.id >= b.id)sno,b.id,pickuprequestid,pickupRequestservicetype,pickuprequestservicename,pickuprequestrtorename,pickuprequestaddress,pickuprequestdate,pickuprequesttime,pickuprequeststatus,location,customername,customerid\n" +
                " from " + TABLE_NAME2 + " b order by sno";
        sqLiteDatabase = this.getReadableDatabase();
        List<PickupItemModal> pickupItemModals = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            pickupItemModals.clear();
            do {
                int sno = Integer.parseInt(String.valueOf(cursor.getString(0)));
                int id = Integer.parseInt(String.valueOf(cursor.getInt(1)));
                String PickupRequestId = cursor.getString(2);
                String PickupRequestServiceType = cursor.getString(3);
                String PickupRequestServiceName = cursor.getString(4);
                String PickupRequestStoreName =cursor.getString(5);
                String PickupRequestAddress = cursor.getString(6);
                String PickupRequestDate = cursor.getString(7);
                String PickupRequestTime = cursor.getString(8);
                String PickupRequestStatus = cursor.getString(9);
                String Locations = cursor.getString(10);
                String CustomerName = cursor.getString(11);
                String CustomerId = cursor.getString(12);

                pickupItemModals.add(new PickupItemModal( id,sno, PickupRequestId,PickupRequestServiceType, PickupRequestServiceName, PickupRequestStoreName,PickupRequestAddress,PickupRequestDate,PickupRequestTime,PickupRequestStatus,Locations,CustomerName,CustomerId));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return pickupItemModals;
    }

    public List<DeliveryRequestComplited_Modal> getRequestDelivery() {
        //String sql = "select * from "+ TABLE_NAME ;
        String sql = "select (select count(*) from " + TABLE_NAME2 + " a  where a.id >= b.id)sno,b.id,pickuprequestid,pickupRequestservicetype,pickuprequestservicename,pickuprequestrtorename,pickuprequestaddress,pickuprequestdate,pickuprequesttime,pickuprequeststatus,location,customername,customerid\n" +
                " from " + TABLE_NAME2 + " b order by sno";
        sqLiteDatabase = this.getReadableDatabase();
        List<DeliveryRequestComplited_Modal> deliveryRequestComplitedModals = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            deliveryRequestComplitedModals.clear();
            do {
                int sno = Integer.parseInt(String.valueOf(cursor.getString(0)));
                int id = Integer.parseInt(String.valueOf(cursor.getInt(1)));
                String DeliveryRequestId = cursor.getString(2);
                String DeliveryRequestServiceType = cursor.getString(3);
                String DeliveryRequestServiceName = cursor.getString(4);
                String DeliveryRequestStoreName =cursor.getString(5);
                String DeliveryRequestAddress = cursor.getString(6);
                String DeliveryRequestDate = cursor.getString(7);
                String DeliveryRequestDueDate = cursor.getString(8);
                String DeliveryRequestTime = cursor.getString(9);
                String DeliveryRequestStatus = cursor.getString(10);
                String DeliveryLocation = cursor.getString(11);
                String DeliveryCustomerName = cursor.getString(12);
                String DeliveryCustomerId = cursor.getString(13);

                deliveryRequestComplitedModals.add(new DeliveryRequestComplited_Modal( id,sno, DeliveryRequestId,DeliveryRequestServiceType, DeliveryRequestServiceName, DeliveryRequestStoreName,DeliveryRequestAddress,DeliveryRequestDate,DeliveryRequestDueDate,DeliveryRequestTime,DeliveryRequestStatus,DeliveryLocation,DeliveryCustomerName,DeliveryCustomerId));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return deliveryRequestComplitedModals;
    }
    //*****************************View all deta in recycleveiw**********************
    //*****************************update items in recycleview all deta**********************
    public void updateItems(ItemModal itemModal) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SqliteDatabase.GROUPITEM, itemModal.getItems());
        contentValues.put(SqliteDatabase.AMOUNT, itemModal.getRate());
        contentValues.put(SqliteDatabase.QUANTITY, itemModal.getQty());
        contentValues.put(SqliteDatabase.UNITPRICE,itemModal.getUnitprice());
        contentValues.put(SqliteDatabase.DISCRIPTION, itemModal.getDiscription());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + " = ?", new String[]
                {String.valueOf(itemModal.getId())});

        Log.i("GROUPITEM", "**********sqlupdate******************" + itemModal.getItems());
        Log.i("AMOUNT", "" + itemModal.getRate());
        Log.i("QUANTITY", "" + itemModal.getQty());
//        Log.i("COLOR", "" + itemModal.getColor());
//        Log.i("BRAND", "" + itemModal.getBrand());
        Log.i("DISCRIPTION", "" + itemModal.getDiscription());
    }


    public boolean updateItem(String id, String gItem, String quantity, String amount,String unitprice,String itemCode,String itemServiceId,String itemServicesType,String itemSpecialInstruction,String itemPackType,String itemRemark, String discription) {
        ContentValues contentValues = new ContentValues(  );
        if(!ID.isEmpty() && !GROUPITEM.isEmpty() && !QUANTITY.isEmpty() && !AMOUNT.isEmpty() && !UNITPRICE.isEmpty()  && !QUANTITY.isEmpty()){
            contentValues.put( ID, id );
            contentValues.put( GROUPITEM, gItem );
            contentValues.put( QUANTITY, quantity );
            contentValues.put( AMOUNT, amount );
            contentValues.put(UNITPRICE,unitprice);
            contentValues.put(ITEMCODE,itemCode);
            contentValues.put(ITEMSERVICEID,itemServiceId);
            contentValues.put(ITEMSERVICETYPE,itemServicesType);
            contentValues.put(ITEMSPECIALINSTRUCTION,itemSpecialInstruction);
            contentValues.put(ITEMPACKTYPE,itemPackType);
            contentValues.put(ITEMSREMAKS2,itemRemark);
            contentValues.put( DISCRIPTION, discription);

            Log.i("ID", "*************id***************" +id);
            Log.i("GROUPITEM", "**********groupitem******************" +gItem);
            Log.i("AMOUNT", "**************amount*****************" + amount);
        }
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update( TABLE_NAME, contentValues, ID + " = ?", new String[]{String.valueOf(id)} );
        return true;
    }
    //*****************************update items in recycleview all deta**********************
//*****************************delete items in recycleview all deta**********************
    public void deleteItems(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }
    //*****************************delete items in recycleview all deta**********************

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    //*****************************delete image only**********************

    public void deleteEntry(long row) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME1, KEY_ID + "=" + row, null);
    }
    //*****************************delete image only******** **************

}
