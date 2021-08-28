package com.example.lyondrydelivery.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyondrydelivery.Activity.AddNewItemActivity;
import com.example.lyondrydelivery.Activity.EditeAddNewItemsActivity;
import com.example.lyondrydelivery.Adapter.LocalDataBaseAdapter;
import com.example.lyondrydelivery.Modal.LocalResponse;
import com.example.lyondrydelivery.R;
import com.example.lyondrydelivery.SQLiteDB.SqliteDatabase;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class EditFragment extends Fragment {


    public EditFragment() {
        // Required empty public constructor
    }


    private static final int CAMERA_REQUEST = 1888;
    TextView update;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    //Bitmap photo;
    String photo;
    SqliteDatabase databaseHandler;
    private SQLiteDatabase db;
    Bitmap theImage;

    RecyclerView recyclerView;
    private SqliteDatabase myDatabase;
    private ArrayList singleRowArrayList;
    private LocalResponse singleRow;
    String image;
    int uid, itemid;
    Cursor cursor;
    String strtext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_edit,container,false);

        update = view.findViewById(R.id.updateimage);
        recyclerView = view.findViewById(R.id.recyclerview1);
        myDatabase = new SqliteDatabase(getContext());
        db = myDatabase.getWritableDatabase();
        setData();

        databaseHandler = new SqliteDatabase(getContext());
        update.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        return view;
    }
    private void setDataToDataBase() {
        db = databaseHandler.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(databaseHandler.KEY_IMG_URL,photo);

        long id = db.insert(databaseHandler.TABLE_NAME1, null, cv);
        if (id < 0) {
            Toast.makeText(getContext(), "Something went wrong. Please try again later...", Toast.LENGTH_LONG).show();
        } else {
//            EditeAddNewItemsActivity editeAddNewItemsActivity = (EditeAddNewItemsActivity) getActivity();
//            editeAddNewItemsActivity.loadFragment1(new camerafagment(),true);
               ((EditeAddNewItemsActivity) getContext()).loadFragment2(new EditFragment(), true);

            Toast.makeText(getContext(), "update successful", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            theImage = (Bitmap) data.getExtras().get("data");
            photo=getEncodedString(theImage);

            setDataToDataBase();
            // setDataToDataBase1();
        }
    }


    private String getEncodedString(Bitmap bitmap){

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG,100, os);

      /* or use below if you want 32 bit images
       bitmap.compress(Bitmap.CompressFormat.PNG, (0–100 compression), os);*/
        byte[] imageArr = os.toByteArray();
        return Base64.encodeToString(imageArr, Base64.URL_SAFE);
    }

    private void setData() {
        db = myDatabase.getWritableDatabase();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        singleRowArrayList = new ArrayList<>();
        String[] columns = {SqliteDatabase.KEY_ID, SqliteDatabase.KEY_IMG_URL,SqliteDatabase.ITEMS_ID};
        cursor = db.query(SqliteDatabase.TABLE_NAME1, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {

            int index1 = cursor.getColumnIndex(SqliteDatabase.KEY_ID);
            int index2 = cursor.getColumnIndex(SqliteDatabase.KEY_IMG_URL);
            int index3 = cursor.getColumnIndex(SqliteDatabase.ITEMS_ID);
            uid = cursor.getInt(index1);
            image = cursor.getString(index2);
            itemid =cursor.getInt(index3);

            singleRow = new LocalResponse(image, uid);
            singleRowArrayList.add(singleRow);
        }
        if (singleRowArrayList.size() == 0) {
            //empty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            LocalDataBaseAdapter localDataBaseResponse = new LocalDataBaseAdapter(getContext(), singleRowArrayList, db, myDatabase);
            recyclerView.setAdapter(localDataBaseResponse);
        }
    }
}