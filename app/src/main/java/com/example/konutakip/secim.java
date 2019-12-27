package com.example.konutakip;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;

public class secim extends AppCompatActivity {
    Spinner spinnersinif;
    Spinner spinnerkod;
    Spinner spinnerders;

    ArrayList<String> listviewkonular;
    ArrayList<String> listviewislendimi;
    String [] listkonular;

    public String[] siniflar = {"9", "10", "11", "12"};
    public String[] kod = {"A", "B", "C", "D", "E", "F"};
    public String[] dersler = {"Matematik", "Fizik", "Kimya", "Biyoloji", "Tarih", "Coğrafya", "Edebiyat", "Felsefe"};

    public String[] spinnersecimleri =new String[3];

    public String secilensinif;
    public String secilenkod;
    public String secilenders;

    public Button okbutton;
    public TextView text;


    private Menu optionsMenu;

    public ArrayAdapter<String> sinifadapter;
    public ArrayAdapter<String> kodadepter;
    public ArrayAdapter<String> dersadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secim);



        listviewislendimi = new ArrayList<String>();
        listviewkonular = new ArrayList<String>();

        listkonular =new String[50];

        okbutton = findViewById(R.id.okbutton);
        text = findViewById(R.id.text);

        spinnersinif = findViewById(R.id.sinifsecimi);
        spinnerkod = findViewById(R.id.spinnerkod);
        spinnerders = findViewById(R.id.spinnerders);


        sinifadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siniflar);
        kodadepter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kod);
        dersadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dersler);

        sinifadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kodadepter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dersadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnersinif.setAdapter(sinifadapter);
        spinnerkod.setAdapter(kodadepter);
        spinnerders.setAdapter(dersadapter);


        spinnersinif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        secilensinif = siniflar[0];
                        break;
                    case 1:
                        secilensinif = siniflar[1];
                        break;
                    case 2:
                        secilensinif = siniflar[2];
                        break;
                    case 3:
                        secilensinif = siniflar[3];
                        break;


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        spinnerkod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        secilenkod = kod[0];
                        break;
                    case 1:
                        secilenkod = kod[1];
                        break;
                    case 2:
                        secilenkod = kod[2];
                        break;
                    case 3:
                        secilenkod = kod[3];
                        break;
                    case 4:
                        secilenkod = kod[4];
                        break;
                    case 5:
                        secilenkod = kod[5];
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerders.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        secilenders = dersler[0];
                        break;
                    case 1:
                        secilenders = dersler[1];
                        break;
                    case 2:
                        secilenders = dersler[2];
                        break;
                    case 3:
                        secilenders = dersler[3];
                        break;
                    case 4:
                        secilenders = dersler[4];
                        break;
                    case 5:
                        secilenders = dersler[5];
                        break;
                    case 6:
                        secilenders = dersler[6];
                        break;
                    case 7:
                        secilenders = dersler[7];
                        break;


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void okbutton(View view) {

        spinnersecimleri[0]=secilensinif;
        spinnersecimleri[1]=secilenkod;
        spinnersecimleri[2]=secilenders;


        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Mufredat",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS mufredat(sinif VARCHAR,kod VARCHAR,ders VARCHAR,konular VARCHAR,islendimi VARCHAR)");

            Cursor cursor = database.rawQuery("SELECT * FROM mufredat WHERE sinif=? AND kod=? AND ders =? ",spinnersecimleri);
            int sinifix=cursor.getColumnIndex("sinif");
            int kodix=cursor.getColumnIndex("kod");
            int dersix=cursor.getColumnIndex("ders");
            int konuix=cursor.getColumnIndex("konular");
            int islendimiix=cursor.getColumnIndex("islendimi");


            while(cursor.moveToNext()){

                listviewkonular.add(cursor.getString(konuix));
                listviewislendimi.add(cursor.getString(islendimiix));
            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        Intent intent = new Intent(secim.this, listviewkonular.class);
        intent.putExtra("listviewkonular", listviewkonular);
        intent.putExtra("listviewislendimi", listviewislendimi);

        intent.putExtra("secilensinif",secilensinif);
        intent.putExtra("secilenkod",secilenkod);
        intent.putExtra("secilenders",secilenders);
        intent.putExtra("spinnersecimleri",spinnersecimleri);

        //System.out.println(listviewkonular);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        secim.this.startActivity(intent);
    }

    public void islenenkonularclick(View view){

        Intent intent=new Intent(secim.this,islenenkonular.class);

        intent.putExtra("secilensinif",secilensinif);
        intent.putExtra("secilenkod",secilenkod);
        intent.putExtra("secilenders",secilenders);
        intent.putExtra("spinnersecimleri2",spinnersecimleri);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            secim.this.startActivity(intent);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finishAffinity();
        }
        return super.onKeyDown(keyCode, event);
    }

//ACTİON BAR İÇİN BU    KODLAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Menüyü oluşturuyoruz
        this.optionsMenu = menu;

        MenuInflater inflater = getMenuInflater();// inflater herhangi bir view ın java objesine dönüştürülüp düzenlenmesinde yardımcı olur.Burda menü düzenlenmesi için kullanacağız
        inflater.inflate(R.menu.menu, menu);//Xml olarak oluşturduğumuz menü yü alıyoruz

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {// Action Bar itemden herhangi biri tıklandığında

        switch (item.getItemId()) {
            case R.id.action_help://help iconu

                help();

                break;
        }
        return true;
    }
    public void help(){
    AlertDialog.Builder builder = new AlertDialog.Builder(secim.this);
    builder.setTitle("NASIL KULLANILIR");
    builder.setMessage("Ders işlediğiniz Sınıfı,Şubeyi,Dersi seçip tüm konular seceneğine tıklarsanız tüm konuları görürsünüz.İşlenen konular seceneğine tıklarsanız daha önceden işlediğiniz konuları görebilirsiniz.");

    builder.show();

}





}
