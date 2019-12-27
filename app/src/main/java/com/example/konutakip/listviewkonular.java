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

public class listviewkonular extends AppCompatActivity {

    boolean islendimikontrol=false;

    ArrayList<String> listviewkonular;
    ArrayList<String> listviewislendimi;

    String[] listkonular;
    String islendisecilen;

    String secilensinif;
    String secilenkod;
    String secilenders;


    String []islendisecilendizi;

    String [] filtrelemedizisi;


    ArrayAdapter arrayAdapter;


    private Menu optionsMenu;


    ListView listview;
    //ListView listview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewkonular);

        listview = findViewById(R.id.listview);

        listkonular=new String[50];

        islendisecilendizi =new String[2];
        filtrelemedizisi =new String[5];

        listviewkonular = new ArrayList<String>();

        Bundle extras = getIntent().getExtras();
        listviewkonular=extras.getStringArrayList("listviewkonular");
        listviewislendimi=extras.getStringArrayList("listviewislendimi");
        secilensinif=extras.getString("secilensinif");
        secilenkod=extras.getString("secilenkod");
        secilenders=extras.getString("secilenders");
        filtrelemedizisi=extras.getStringArray("spinnersecimleri");


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, listviewkonular);

        listview.setAdapter(arrayAdapter);

       listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               islendisecilen= listviewkonular.get(position);
               Toast.makeText(listviewkonular.this, islendisecilen+" konusu işlenen konulara eklendi", Toast.LENGTH_SHORT).show();
               vtgüncelle();
       }
       });

    }

    public void vtgüncelle(){
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Mufredat", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS mufredat(sinif VARCHAR,kod VARCHAR,ders VARCHAR,konular VARCHAR,islendimi VARCHAR)");

            String sql="UPDATE mufredat SET islendimi='islendi' WHERE sinif=? AND kod=? AND ders=? AND konular=?";
            SQLiteStatement sqlitestatement = database.compileStatement(sql);
            sqlitestatement.bindString(1,secilensinif);
            sqlitestatement.bindString(2,secilenkod);
            sqlitestatement.bindString(3,secilenders);
            sqlitestatement.bindString(4,islendisecilen);
            sqlitestatement.execute();

        }catch (Exception e){

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent =new Intent(listviewkonular.this,secim.class);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(listviewkonular.this);
        builder.setTitle("NASIL KULLANILIR");
        builder.setMessage("İşlediğiniz konunun üzerine tıklayarak işlenen konular kısmına ekleyebilirsiniz.");

        builder.show();

    }

}
