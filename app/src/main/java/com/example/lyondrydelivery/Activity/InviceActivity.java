package com.example.lyondrydelivery.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyondrydelivery.Adapter.InviceAdapter;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Modal.InviceMaster.GridTran_data_invice;
import com.example.lyondrydelivery.Modal.InviceMaster.InviceModal;
import com.example.lyondrydelivery.Modal.InviceMaster.InvoiceMaster_resonce_modal;
import com.example.lyondrydelivery.Modal.InviceMaster.MainInviceMaster_reponce_modal;
import com.example.lyondrydelivery.R;
import com.example.lyondrydelivery.RetrofiltClient.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class InviceActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    InviceAdapter inviceAdapter;
    List<InviceModal> inviceModalList;
    Context context;
    Button buttonPDF;
    int pageHeight = 1120;
    int pagewidth = 792;
    Bitmap bmp, scaledbmp;
    // constant code for runtime permission
    private static final int PERMISSION_REQUEST_CODE = 200;


    SharedPrefManager sharedPrefManager;
    String token, UserName;
    InvoiceMaster_resonce_modal invoiceMaster_resonce_modals;
    GridTran_data_invice gridTran_data_invice1;
    List<GridTran_data_invice> gridTran_data_invice = new ArrayList<>();
    TextView CustomerName,GstInviceNo,GstInviceDate,Address,City,State;
    TextView DiscountAmount,AfterDiscountAmount,CGST,SGST,IGST,GST,RoundOff,TotalAmountAfterTax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invice);
        CustomerName = findViewById(R.id.customerName);
        GstInviceNo = findViewById(R.id.gstInviceNo);
        GstInviceDate = findViewById(R.id.gstInviceDate);
        Address = findViewById(R.id.address);
        City = findViewById(R.id.city);
        State = findViewById(R.id.state);

        DiscountAmount = findViewById(R.id.discountAmount);
        AfterDiscountAmount = findViewById(R.id.afterDiscountAmount);
        CGST = findViewById(R.id.cgst);
        SGST = findViewById(R.id.sgst);
        IGST = findViewById(R.id.igst);
        GST = findViewById(R.id.gst);
        RoundOff = findViewById(R.id.roundOff);
        TotalAmountAfterTax = findViewById(R.id.totalAmountAfterTax);



        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        UserName = sharedPrefManager.getLoginUser().getUserName();

        recyclerView = findViewById(R.id.invice_item_recycleview);
        buttonPDF = findViewById(R.id.idBtnGeneratePDF);

        inviceModalList = new ArrayList<InviceModal>();
        inviceModalList.add(new InviceModal("Ladies Top - Dry Cleaning - Folded - None","00999712","PCS","2","110","220"));
        inviceModalList.add(new InviceModal("Ladies Top - Dry Cleaning - Folded - None","00999712","PCS","2","110","220"));
        inviceModalList.add(new InviceModal("Ladies Top - Dry Cleaning - Folded - None","00999712","PCS","2","110","220"));
        inviceModalList.add(new InviceModal("Ladies Top - Dry Cleaning - Folded - None","00999712","PCS","2","110","220"));
        inviceModalList.add(new InviceModal("Ladies Top - Dry Cleaning - Folded - None","00999712","PCS","2","110","220"));
        inviceModalList.add(new InviceModal("Ladies Top - Dry Cleaning - Folded - None","00999712","PCS","2","110","220"));
        inviceModalList.add(new InviceModal("Ladies Top - Dry Cleaning - Folded - None","00999712","PCS","2","110","220"));
        inviceModalList.add(new InviceModal("Ladies Top - Dry Cleaning - Folded - None","00999712","PCS","2","110","220"));

        Log.e("gridTran_data_invice",""+gridTran_data_invice);

        inviceAdapter = new InviceAdapter(gridTran_data_invice,context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(inviceAdapter);
        inviceAdapter.notifyDataSetChanged();

        InviceMaster();
        // initializing our variables.

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.dlogo_icon);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);

        // below code is used for
        // checking our permissions.
        if (checkPermission()) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }

        buttonPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling method to
                // generate our PDF file.
                generatePDF();
            }
        });

    }

    private void InviceMaster() {
        Call<MainInviceMaster_reponce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .InviceMaster(token, UserName);

        call.enqueue(new Callback<MainInviceMaster_reponce_modal>() {
            @Override
            public void onResponse(@NotNull Call<MainInviceMaster_reponce_modal> call, Response<MainInviceMaster_reponce_modal> response) {

                if (response.isSuccessful()) {
                    MainInviceMaster_reponce_modal  mainInviceMaster_reponce_modal = response.body();

                    if (mainInviceMaster_reponce_modal.getSuccess()) {
                        invoiceMaster_resonce_modals = mainInviceMaster_reponce_modal.getInvoiceMaster();
                        String InvoiceNo = invoiceMaster_resonce_modals.getInvoiceNo();
                        String InvoiceDateString =invoiceMaster_resonce_modals.getInvoiceDateString();
                        int InvoiceCustomerId =invoiceMaster_resonce_modals.getInvoiceCustomerId();
                        String InvoiceCustomerCode =invoiceMaster_resonce_modals.getInvoiceCustomerCode();
                        String InvoiceCustomerGstNo =invoiceMaster_resonce_modals.getInvoiceCustomerGstNo();
                        int InvoiceRequestId =invoiceMaster_resonce_modals.getInvoiceRequestId();
                        String InvoiceOrderNo =invoiceMaster_resonce_modals.getInvoiceOrderNo();
                        Double InvoiceTotalAmount =invoiceMaster_resonce_modals.getInvoiceTotalAmount();
                        Double InvoiceDiscountPercentage =invoiceMaster_resonce_modals.getInvoiceDiscountPercentage();
                        Double InvoiceDiscountAmount =invoiceMaster_resonce_modals.getInvoiceDiscountAmount();
                        Double InvoiceTaxPercentage =invoiceMaster_resonce_modals.getInvoiceTaxPercentage();
                        Double InvoiceCgst =invoiceMaster_resonce_modals.getInvoiceCgst();
                        Double InvoiceSgst =invoiceMaster_resonce_modals.getInvoiceSgst();
                        Double InvoiceIgst =invoiceMaster_resonce_modals.getInvoiceIgst();
                        Double InvoiceTotalTax =invoiceMaster_resonce_modals.getInvoiceTotalTax();
                        Double InvoiceRoundOff =invoiceMaster_resonce_modals.getInvoiceRoundOff();
                        Double InvoiceNetAmount =invoiceMaster_resonce_modals.getInvoiceNetAmount();
                        String InvoicePickupBy =invoiceMaster_resonce_modals.getInvoicePickupBy();

                        //DiscountAmount,AfterDiscountAmount,CGST,SGST,IGST,GST,RoundOff,TotalAmountAfterTax;
                        DiscountAmount.setText(String.valueOf(InvoiceDiscountAmount));
                        AfterDiscountAmount.setText(String.valueOf(InvoiceDiscountPercentage));
                        CGST.setText(String.valueOf(InvoiceCgst));
                        SGST.setText(String.valueOf(InvoiceSgst));
                        IGST.setText(String.valueOf(InvoiceIgst));


                        gridTran_data_invice =  invoiceMaster_resonce_modals.getGridTrans();

                        for (int i =0; i<gridTran_data_invice.size(); i++){
                            gridTran_data_invice1 = (GridTran_data_invice)gridTran_data_invice.get(i);
                            String InvoiceTranInvoiceNo = gridTran_data_invice1.getInvoiceTranInvoiceNo();
                            int InvoiceTranSlNo = gridTran_data_invice1.getInvoiceTranSlNo();
                            String InvoiceTranItemCode = gridTran_data_invice1.getInvoiceTranItemCode();
                            Double InvoiceTranItemUnitPrice = gridTran_data_invice1.getInvoiceTranItemUnitPrice();
                            int InvoiceTranItemUomId = gridTran_data_invice1.getInvoiceTranItemUomId();
                            int InvoiceTranItemNos = gridTran_data_invice1.getInvoiceTranItemNos();
                            Double InvoiceTranAmount = gridTran_data_invice1.getInvoiceTranAmount();
                            Double InvoiceTranDiscPercentage = gridTran_data_invice1.getInvoiceTranDiscPercentage();
                            Double InvoiceTranDiscount = gridTran_data_invice1.getInvoiceTranDiscount() ;
                            Double InvoiceTranTaxPercentage = gridTran_data_invice1.getInvoiceTranTaxPercentage();
                            Double InvoiceTranCgst = gridTran_data_invice1.getInvoiceTranCgst();
                            Double InvoiceTranSgst = gridTran_data_invice1.getInvoiceTranSgst();
                            Double InvoiceTranIgst = gridTran_data_invice1.getInvoiceTranIgst();
                            Double InvoiceTranTotalTax = gridTran_data_invice1.getInvoiceTranTotalTax();
                            Double InvoiceTranNetAmount = gridTran_data_invice1.getInvoiceTranNetAmount();

                        }

                    }




                    else {
                        Toast.makeText(getApplicationContext(), mainInviceMaster_reponce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MainInviceMaster_reponce_modal> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void generatePDF() {
        // creating an object variable
        // for our PDF document.
        PdfDocument pdfDocument = new PdfDocument();

        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        Paint paint = new Paint();
        Paint title = new Paint();

        // we are adding page info to our PDF file
        // in which we will be passing our pageWidth,
        // pageHeight and number of pages and after that
        // we are calling it to create our PDF.
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();

        // below line is used for setting
        // start page for our PDF file.
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        // creating a variable for canvas
        // from our page of PDF.
        Canvas canvas = myPage.getCanvas();

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        canvas.drawBitmap(scaledbmp, 56, 40, paint);

        // below line is used for adding typeface for
        // our text which we will be adding in our PDF file.
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // below line is used for setting text size
        // which we will be displaying in our PDF file.
        title.setTextSize(15);

        // below line is sued for setting color
        // of our text inside our PDF file.
        title.setColor(ContextCompat.getColor(this, R.color.purple_200));

        // below line is used to draw text in our PDF file.
        // the first parameter is our text, second parameter
        // is position from start, third parameter is position from top
        // and then we are passing our variable of paint which is title.
        canvas.drawText("#301, Lyon Square Ground Floor, 14th B Cross, 7th Main, 6th Sector,\n" +
                "HSR Layout, Bangalore - 560102, Karnataka, India.", 209, 140, title);
        canvas.drawText("Hygiene Fabric & Shoe Care", 209, 120, title);
        canvas.drawText("LYONDRY (A Unit of Bapuji Surgicals).", 209, 100, title);
        canvas.drawText("TAX INVOICE", 209, 80, title);

        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.purple_200));
        title.setTextSize(15);

        // below line is used for setting
        // our text to center of PDF.
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("This is sample document which we have created.", 396, 560, title);

        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);

        // below line is used to set the name of
        // our PDF file and its path.
        File file = new File(Environment.getExternalStorageDirectory(), "GFG.pdf");

        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(new FileOutputStream(file));

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(InviceActivity.this, "PDF file generated succesfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close();
    }

    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denined.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }

    }


}