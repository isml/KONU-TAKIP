package com.example.konutakip;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class islenenkonular extends AppCompatActivity {


    private Menu optionsMenu;

    String secilensinif;
    String secilenkod;
    String secilenders;
    String [] filtrelemesecimleri;

    ArrayAdapter arrayAdapter;

    ListView listviewislenenler;

    ArrayList<String> arraylistislenenler;
    String guncellemesecilen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_islenenkonular);

        filtrelemesecimleri=new String[5];

        Bundle extras = getIntent().getExtras();
        secilensinif=extras.getString("secilensinif");
        secilenkod=extras.getString("secilenkod");
        secilenders=extras.getString("secilenders");
        filtrelemesecimleri=extras.getStringArray("spinnersecimleri2");

        filtrelemesecimleri[0]=secilensinif;
        filtrelemesecimleri[1]=secilenkod;
        filtrelemesecimleri[2]=secilenders;

        listviewislenenler=findViewById(R.id.listviewislenenler);

        arraylistislenenler = new ArrayList<String>();

         islenenkonularlist();

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, arraylistislenenler);
        listviewislenenler.setAdapter(arrayAdapter);

            listviewislenenler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            guncellemesecilen=arraylistislenenler.get(position);
            Toast.makeText(islenenkonular.this, guncellemesecilen+" konusu işlenen konulardan kaldırıldı", Toast.LENGTH_SHORT).show();
            vtguncelleme();


            //islendimi den çıkarıldığında aktivitiyi yenileyip  tıklanan konuyu çıkarıyor
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);

        }
    });

    }


    public void vtguncelleme(){
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Mufredat", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS mufredat(sinif VARCHAR,kod VARCHAR,ders VARCHAR,konular VARCHAR,islendimi VARCHAR)");

            String sql="UPDATE mufredat SET islendimi='islenmedi' WHERE sinif=? AND kod=? AND ders=? AND konular=?";
            SQLiteStatement sqlitestatement = database.compileStatement(sql);
            sqlitestatement.bindString(1,secilensinif);
            sqlitestatement.bindString(2,secilenkod);
            sqlitestatement.bindString(3,secilenders);
            sqlitestatement.bindString(4,guncellemesecilen);
            sqlitestatement.execute();

        }catch (Exception e){

        }
    }

    public void islenenkonularlist(){
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Mufredat", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS mufredat(sinif VARCHAR,kod VARCHAR,ders VARCHAR,konular VARCHAR,islendimi VARCHAR)");


            Cursor cursor = database.rawQuery("SELECT * FROM mufredat  WHERE islendimi='islendi'AND sinif=? AND kod=? AND ders=? ",filtrelemesecimleri);
            // Cursor cursor = database.rawQuery("SELECT * FROM mufredat  WHERE islendimi='islendi'",null);

            int sinifix=cursor.getColumnIndex("sinif");
            int kodix=cursor.getColumnIndex("kod");
            int dersix=cursor.getColumnIndex("ders");
            int konuix=cursor.getColumnIndex("konular");
            int islendimiix=cursor.getColumnIndex("islendimi");
            while(cursor.moveToNext()){
                    arraylistislenenler.add(cursor.getString(konuix));

            }
        }catch (Exception e){

        }


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent =new Intent(islenenkonular.this,secim.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(islenenkonular.this);
        builder.setTitle("NASIL KULLANILIR");
        builder.setMessage("İşlediğiniz konular burada görebilirsiniz.Konuyu işlenmedi olarak değiştirmek isterseniz konunun üzerine tıklamanız yeterlidir");

        builder.show();

    }
}
