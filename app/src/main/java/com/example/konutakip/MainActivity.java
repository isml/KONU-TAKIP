package com.example.konutakip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;

    EditText emailtext;
    EditText passwordtext;

    String email;
    String password;

    boolean vtkontrol=false;
    boolean vtolusması=false;
    boolean giriskontrol=false;

    Button girisbutton;
    Button kayitbutton;

    private Menu optionsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailtext=findViewById(R.id.email);
        passwordtext=findViewById(R.id.password);

        kayitbutton=findViewById(R.id.kayitbutton);
        girisbutton=findViewById(R.id.girisbutton);

        firebaseAuth=FirebaseAuth.getInstance();

        girisbutton.setVisibility(View.INVISIBLE);



        //burada daha önceden kayıt olduysa uygulama her açıldığında kayıt ekranı gelmemesi için kısım diğer aktiviteye geçiyor direk
            FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
            if(firebaseUser!=null){
                Intent intent =new Intent(MainActivity.this,secim.class);
                startActivity(intent);
            }

    }
    //ACTİON BAR İÇİN BU KODLAR
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
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("NASIL KULLANILIR");
        builder.setMessage("Bu kısımda bir defalık üye olup giriş yapmanız gerekmektedir.Bu üyelik daha sonra hiçbir işe yaramayacaktır.");

        builder.show();

    }

    //kayıt butonu
    public void kayit(final View view){
        //Burada firebase kullanarak üye girişi kısmı yapıldı
        //kullanıcıdan aldığımız text bilgilerini stringe dönüştürdük
         email=emailtext.getText().toString();
         password=passwordtext.getText().toString();

        //bu kısımda firebase de kullanıcı oluşturma kısmı
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                vtkontrol=true;
                vtolusması=true;
                kayitbutton.setVisibility(View.INVISIBLE);
                girisbutton.setVisibility(View.VISIBLE);

                Toast.makeText(MainActivity.this, "KAYIT BAŞARILI GİRİŞ TUŞUNA BASINIZ", Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(MainActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "KAYIT HATALI KONTROL EDİNİZ", Toast.LENGTH_SHORT).show();

            }
        });

    }
    //giriş butonu
    public void giris(View view){

        if(vtkontrol==true) {
            girisbutton.setVisibility(View.INVISIBLE);
            giriskontrol=true;
            Toast.makeText(this, "LÜTFEN BEKLEYİNİZ ", Toast.LENGTH_LONG).show();
            //bu kısımda SQLite ile veri tabanı oluşturuluyor
            try {
                SQLiteDatabase database = this.openOrCreateDatabase("Mufredat", MODE_PRIVATE, null);
                database.execSQL("CREATE TABLE IF NOT EXISTS mufredat(sinif VARCHAR,kod VARCHAR,ders VARCHAR,konular VARCHAR,islendimi VARCHAR)");




                //9.sınıf A şubesi matematik
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Önermeler ve Bileşik Önermeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Kümelerde Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Kümelerde İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Sayı Kümeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Bölünebilme Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Birinci Dereceden Denklemler ve Eşitsizlikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Üslü İfadeler ve Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Denklemler ve Eşitsizliklerle İlgili Uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Üçgenlerde Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Üçgenlerde Eşlik ve Benzerlik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Üçgenlerin Yardımcı Elemanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Dik Üçgen ve Trigonometri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Üçgenin Alanı\t','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Merkezî Eğilim ve Yayılım Ölçüleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Matematik','Verilerin Grafikle Gösterilmesi','islenmedi')");
                //9.sınıf B şubesi matamatik
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Önermeler ve Bileşik Önermeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Kümelerde Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Kümelerde İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Sayı Kümeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Bölünebilme Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Birinci Dereceden Denklemler ve Eşitsizlikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Üslü İfadeler ve Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Denklemler ve Eşitsizliklerle İlgili Uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Üçgenlerde Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Üçgenlerde Eşlik ve Benzerlik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Üçgenlerin Yardımcı Elemanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Dik Üçgen ve Trigonometri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Üçgenin Alanı\t','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Merkezî Eğilim ve Yayılım Ölçüleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Matematik','Verilerin Grafikle Gösterilmesi','islenmedi')");
                //9.sınıf C şubesi matamatik
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Önermeler ve Bileşik Önermeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Kümelerde Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Kümelerde İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Sayı Kümeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Bölünebilme Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Birinci Dereceden Denklemler ve Eşitsizlikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Üslü İfadeler ve Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Denklemler ve Eşitsizliklerle İlgili Uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Üçgenlerde Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Üçgenlerde Eşlik ve Benzerlik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Üçgenlerin Yardımcı Elemanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Dik Üçgen ve Trigonometri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Üçgenin Alanı\t','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Merkezî Eğilim ve Yayılım Ölçüleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Matematik','Verilerin Grafikle Gösterilmesi','islenmedi')");
                //9.sınıf D şubesi matamatik
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Önermeler ve Bileşik Önermeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Kümelerde Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Kümelerde İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Sayı Kümeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Bölünebilme Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Birinci Dereceden Denklemler ve Eşitsizlikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Üslü İfadeler ve Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Denklemler ve Eşitsizliklerle İlgili Uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Üçgenlerde Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Üçgenlerde Eşlik ve Benzerlik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Üçgenlerin Yardımcı Elemanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Dik Üçgen ve Trigonometri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Üçgenin Alanı\t','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Merkezî Eğilim ve Yayılım Ölçüleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Matematik','Verilerin Grafikle Gösterilmesi','islenmedi')");

                //9.sınıf E şubesi matamatik
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Önermeler ve Bileşik Önermeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Kümelerde Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Kümelerde İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Sayı Kümeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Bölünebilme Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Birinci Dereceden Denklemler ve Eşitsizlikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Üslü İfadeler ve Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Denklemler ve Eşitsizliklerle İlgili Uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Üçgenlerde Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Üçgenlerde Eşlik ve Benzerlik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Üçgenlerin Yardımcı Elemanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Dik Üçgen ve Trigonometri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Üçgenin Alanı\t','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Merkezî Eğilim ve Yayılım Ölçüleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Matematik','Verilerin Grafikle Gösterilmesi','islenmedi')");
                //9.sınıf F şubesi matamatik
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Önermeler ve Bileşik Önermeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Kümelerde Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Kümelerde İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Sayı Kümeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Bölünebilme Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Birinci Dereceden Denklemler ve Eşitsizlikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Üslü İfadeler ve Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Denklemler ve Eşitsizliklerle İlgili Uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Üçgenlerde Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Üçgenlerde Eşlik ve Benzerlik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Üçgenlerin Yardımcı Elemanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Dik Üçgen ve Trigonometri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Üçgenin Alanı\t','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Merkezî Eğilim ve Yayılım Ölçüleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Matematik','Verilerin Grafikle Gösterilmesi','islenmedi')");





                //9.sınıf A şubesi fizik
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','Fizik Bilimine Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','Madde ve Özkütle','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','Katılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','Akışkanlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','Bir Boyutta Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','Kuvvet','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','Newton’un Hareket Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','İş, Enerji ve Güç','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','Mekanik Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','Enerjinin Korunumu ve Enerji Dönüşümleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','Verim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik',' Isı, Sıcaklık ve İç Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','Hal Değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','Isıl Denge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','Enerji İletim Yolları ve Enerji İletim Hızı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Fizik','Genleşme','islenmedi')");
                //9.sınıf B şubesi fizik
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','Fizik Bilimine Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','Madde ve Özkütle','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','Katılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','Akışkanlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','Bir Boyutta Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','Kuvvet','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','Newton’un Hareket Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','İş, Enerji ve Güç','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','Mekanik Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','Enerjinin Korunumu ve Enerji Dönüşümleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','Verim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik',' Isı, Sıcaklık ve İç Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','Hal Değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','Isıl Denge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','Enerji İletim Yolları ve Enerji İletim Hızı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Fizik','Genleşme','islenmedi')");
                //9.sınıf C şubesi fizik
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','Fizik Bilimine Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','Madde ve Özkütle','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','Katılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','Akışkanlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','Bir Boyutta Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','Kuvvet','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','Newton’un Hareket Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','İş, Enerji ve Güç','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','Mekanik Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','Enerjinin Korunumu ve Enerji Dönüşümleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','Verim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik',' Isı, Sıcaklık ve İç Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','Hal Değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','Isıl Denge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','Enerji İletim Yolları ve Enerji İletim Hızı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Fizik','Genleşme','islenmedi')");
                //9.sınıf D şubesi fizik
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','Fizik Bilimine Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','Madde ve Özkütle','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','Katılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','Akışkanlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','Bir Boyutta Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','Kuvvet','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','Newton’un Hareket Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','İş, Enerji ve Güç','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','Mekanik Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','Enerjinin Korunumu ve Enerji Dönüşümleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','Verim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik',' Isı, Sıcaklık ve İç Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','Hal Değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','Isıl Denge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','Enerji İletim Yolları ve Enerji İletim Hızı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Fizik','Genleşme','islenmedi')");

                //9.sınıf E şubesi fizik
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','Fizik Bilimine Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','Madde ve Özkütle','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','Katılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','Akışkanlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','Bir Boyutta Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','Kuvvet','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','Newton’un Hareket Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','İş, Enerji ve Güç','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','Mekanik Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','Enerjinin Korunumu ve Enerji Dönüşümleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','Verim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik',' Isı, Sıcaklık ve İç Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','Hal Değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','Isıl Denge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','Enerji İletim Yolları ve Enerji İletim Hızı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Fizik','Genleşme','islenmedi')");
                //9.sınıf F şubesi fizik
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','Fizik Bilimine Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','Madde ve Özkütle','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','Katılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','Akışkanlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','Bir Boyutta Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','Kuvvet','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','Newton’un Hareket Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','İş, Enerji ve Güç','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','Mekanik Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','Enerjinin Korunumu ve Enerji Dönüşümleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','Verim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik',' Isı, Sıcaklık ve İç Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','Hal Değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','Isıl Denge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','Enerji İletim Yolları ve Enerji İletim Hızı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Fizik','Genleşme','islenmedi')");

                //9.sınıf A şubesi kimya
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Kimya','Simyadan Kimyaya','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Kimya','Kimya Disiplinleri ve Kimyacıların Çalışma Alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Kimya','Kimyanın Sembolik Dili','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Kimya','Kimya Uygulamalarında İş Sağlığı ve Güvenliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Kimya','Atom Modelleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Kimya','Atomun Yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Kimya','Periyodik Sistem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Kimya','Kimyasal Tür','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Kimya','Kimyasal Türler Arası Etkileşimlerin Sınıflandırılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Kimya','Güçlü Etkileşimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Kimya','Zayıf Etkileşimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Kimya','Fiziksel ve Kimyasal Değişimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Kimya','Maddenin Fiziksel Hâlleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Kimya','Katı,Sıvı,Gaz','islenmedi')");
                //9.sınıf B şubesi kimya
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Kimya','Simyadan Kimyaya','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Kimya','Kimya Disiplinleri ve Kimyacıların Çalışma Alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Kimya','Kimyanın Sembolik Dili','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Kimya','Kimya Uygulamalarında İş Sağlığı ve Güvenliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Kimya','Atom Modelleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Kimya','Atomun Yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Kimya','Periyodik Sistem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Kimya','Kimyasal Tür','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Kimya','Kimyasal Türler Arası Etkileşimlerin Sınıflandırılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Kimya','Güçlü Etkileşimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Kimya','Zayıf Etkileşimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Kimya','Fiziksel ve Kimyasal Değişimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Kimya','Maddenin Fiziksel Hâlleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Kimya','Katı,Sıvı,Gaz','islenmedi')");
                //9.sınıf C şubesi kimya
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Kimya','Simyadan Kimyaya','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Kimya','Kimya Disiplinleri ve Kimyacıların Çalışma Alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Kimya','Kimyanın Sembolik Dili','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Kimya','Kimya Uygulamalarında İş Sağlığı ve Güvenliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Kimya','Atom Modelleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Kimya','Atomun Yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Kimya','Periyodik Sistem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Kimya','Kimyasal Tür','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Kimya','Kimyasal Türler Arası Etkileşimlerin Sınıflandırılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Kimya','Güçlü Etkileşimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Kimya','Zayıf Etkileşimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Kimya','Fiziksel ve Kimyasal Değişimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Kimya','Maddenin Fiziksel Hâlleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Kimya','Katı,Sıvı,Gaz','islenmedi')");
                //9.sınıf D şubesi kimya
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Kimya','Simyadan Kimyaya','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Kimya','Kimya Disiplinleri ve Kimyacıların Çalışma Alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Kimya','Kimyanın Sembolik Dili','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Kimya','Kimya Uygulamalarında İş Sağlığı ve Güvenliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Kimya','Atom Modelleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Kimya','Atomun Yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Kimya','Periyodik Sistem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Kimya','Kimyasal Tür','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Kimya','Kimyasal Türler Arası Etkileşimlerin Sınıflandırılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Kimya','Güçlü Etkileşimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Kimya','Zayıf Etkileşimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Kimya','Fiziksel ve Kimyasal Değişimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Kimya','Maddenin Fiziksel Hâlleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Kimya','Katı,Sıvı,Gaz','islenmedi')");
                //9.sınıf E şubesi kimya
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Kimya','Simyadan Kimyaya','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Kimya','Kimya Disiplinleri ve Kimyacıların Çalışma Alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Kimya','Kimyanın Sembolik Dili','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Kimya','Kimya Uygulamalarında İş Sağlığı ve Güvenliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Kimya','Atom Modelleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Kimya','Atomun Yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Kimya','Periyodik Sistem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Kimya','Kimyasal Tür','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Kimya','Kimyasal Türler Arası Etkileşimlerin Sınıflandırılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Kimya','Güçlü Etkileşimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Kimya','Zayıf Etkileşimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Kimya','Fiziksel ve Kimyasal Değişimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Kimya','Maddenin Fiziksel Hâlleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Kimya','Katı,Sıvı,Gaz','islenmedi')");
                //9.sınıf F şubesi kimya
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Kimya','Simyadan Kimyaya','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Kimya','Kimya Disiplinleri ve Kimyacıların Çalışma Alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Kimya','Kimyanın Sembolik Dili','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Kimya','Kimya Uygulamalarında İş Sağlığı ve Güvenliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Kimya','Atom Modelleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Kimya','Atomun Yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Kimya','Periyodik Sistem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Kimya','Kimyasal Tür','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Kimya','Kimyasal Türler Arası Etkileşimlerin Sınıflandırılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Kimya','Güçlü Etkileşimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Kimya','Zayıf Etkileşimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Kimya','Fiziksel ve Kimyasal Değişimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Kimya','Maddenin Fiziksel Hâlleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Kimya','Katı,Sıvı,Gaz','islenmedi')");



                //9.sınıf A şubesi biyoloji
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Biyoloji','Bilimsel Bilginin Doğası ve Biyoloji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Biyoloji','Canlıların Ortak Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Biyoloji','Canlıların Yapısında Bulunan Temel Bileşikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Biyoloji','Canlılığın Temel Birimi Hücre','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Biyoloji','Canlıların Çeşitliliği ve Sınıflandırılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Biyoloji','Canlı Âlemleri ve Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Biyoloji','Güncel Çevre Sorunları ve İnsan','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Biyoloji','Doğal Kaynaklar ve Biyolojik Çeşitliliğin Korunması','islenmedi')");
                //9.sınıf B şubesi biyoloji
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Biyoloji','Bilimsel Bilginin Doğası ve Biyoloji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Biyoloji','Canlıların Ortak Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Biyoloji','Canlıların Yapısında Bulunan Temel Bileşikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Biyoloji','Canlılığın Temel Birimi Hücre','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Biyoloji','Canlıların Çeşitliliği ve Sınıflandırılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Biyoloji','Canlı Âlemleri ve Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Biyoloji','Güncel Çevre Sorunları ve İnsan','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Biyoloji','Doğal Kaynaklar ve Biyolojik Çeşitliliğin Korunması','islenmedi')");
                //9.sınıf C şubesi biyoloji
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Biyoloji','Bilimsel Bilginin Doğası ve Biyoloji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Biyoloji','Canlıların Ortak Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Biyoloji','Canlıların Yapısında Bulunan Temel Bileşikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Biyoloji','Canlılığın Temel Birimi Hücre','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Biyoloji','Canlıların Çeşitliliği ve Sınıflandırılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Biyoloji','Canlı Âlemleri ve Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Biyoloji','Güncel Çevre Sorunları ve İnsan','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Biyoloji','Doğal Kaynaklar ve Biyolojik Çeşitliliğin Korunması','islenmedi')");
                //9.sınıf D şubesi biyoloji
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Biyoloji','Bilimsel Bilginin Doğası ve Biyoloji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Biyoloji','Canlıların Ortak Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Biyoloji','Canlıların Yapısında Bulunan Temel Bileşikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Biyoloji','Canlılığın Temel Birimi Hücre','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Biyoloji','Canlıların Çeşitliliği ve Sınıflandırılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Biyoloji','Canlı Âlemleri ve Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Biyoloji','Güncel Çevre Sorunları ve İnsan','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Biyoloji','Doğal Kaynaklar ve Biyolojik Çeşitliliğin Korunması','islenmedi')");
                //9.sınıf E şubesi biyoloji
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Biyoloji','Bilimsel Bilginin Doğası ve Biyoloji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Biyoloji','Canlıların Ortak Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Biyoloji','Canlıların Yapısında Bulunan Temel Bileşikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Biyoloji','Canlılığın Temel Birimi Hücre','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Biyoloji','Canlıların Çeşitliliği ve Sınıflandırılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Biyoloji','Canlı Âlemleri ve Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Biyoloji','Güncel Çevre Sorunları ve İnsan','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Biyoloji','Doğal Kaynaklar ve Biyolojik Çeşitliliğin Korunması','islenmedi')");
                //9.sınıf F şubesi biyoloji
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Biyoloji','Bilimsel Bilginin Doğası ve Biyoloji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Biyoloji','Canlıların Ortak Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Biyoloji','Canlıların Yapısında Bulunan Temel Bileşikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Biyoloji','Canlılığın Temel Birimi Hücre','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Biyoloji','Canlıların Çeşitliliği ve Sınıflandırılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Biyoloji','Canlı Âlemleri ve Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Biyoloji','Güncel Çevre Sorunları ve İnsan','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Biyoloji','Doğal Kaynaklar ve Biyolojik Çeşitliliğin Korunması','islenmedi')");




                //9.sınıf A şubesi tarih
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Tarih','TARİH BİLİMİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Tarih','UYGARLIĞIN DOĞUŞU VE İLK UYGARLIKLAR','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Tarih','İLK TÜRK DEVLETLERİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Tarih','İSLAM TARİHİ VE UYGARLIĞI (13. YÜZYILA KADAR)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Tarih','TÜRK-İSLAM DEVLETLERİ (11–13. YÜZYILLAR)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','A','Tarih','TÜRKİYE TARİHİ (11 – 13.YÜZYIL)','islenmedi')");
                //9.sınıf B şubesi tarih
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Tarih','TARİH BİLİMİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Tarih','UYGARLIĞIN DOĞUŞU VE İLK UYGARLIKLAR','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Tarih','İLK TÜRK DEVLETLERİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Tarih','İSLAM TARİHİ VE UYGARLIĞI (13. YÜZYILA KADAR)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Tarih','TÜRK-İSLAM DEVLETLERİ (11–13. YÜZYILLAR)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','B','Tarih','TÜRKİYE TARİHİ (11 – 13.YÜZYIL)','islenmedi')");
                //9.sınıf C şubesi tarih
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Tarih','TARİH BİLİMİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Tarih','UYGARLIĞIN DOĞUŞU VE İLK UYGARLIKLAR','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Tarih','İLK TÜRK DEVLETLERİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Tarih','İSLAM TARİHİ VE UYGARLIĞI (13. YÜZYILA KADAR)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Tarih','TÜRK-İSLAM DEVLETLERİ (11–13. YÜZYILLAR)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','C','Tarih','TÜRKİYE TARİHİ (11 – 13.YÜZYIL)','islenmedi')");
                //9.sınıf D şubesi tarih
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Tarih','TARİH BİLİMİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Tarih','UYGARLIĞIN DOĞUŞU VE İLK UYGARLIKLAR','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Tarih','İLK TÜRK DEVLETLERİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Tarih','İSLAM TARİHİ VE UYGARLIĞI (13. YÜZYILA KADAR)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Tarih','TÜRK-İSLAM DEVLETLERİ (11–13. YÜZYILLAR)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','D','Tarih','TÜRKİYE TARİHİ (11 – 13.YÜZYIL)','islenmedi')");
                //9.sınıf E şubesi tarih
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Tarih','TARİH BİLİMİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Tarih','UYGARLIĞIN DOĞUŞU VE İLK UYGARLIKLAR','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Tarih','İLK TÜRK DEVLETLERİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Tarih','İSLAM TARİHİ VE UYGARLIĞI (13. YÜZYILA KADAR)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Tarih','TÜRK-İSLAM DEVLETLERİ (11–13. YÜZYILLAR)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','E','Tarih','TÜRKİYE TARİHİ (11 – 13.YÜZYIL)','islenmedi')");
                //9.sınıf F şubesi tarih
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Tarih','TARİH BİLİMİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Tarih','UYGARLIĞIN DOĞUŞU VE İLK UYGARLIKLAR','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Tarih','İLK TÜRK DEVLETLERİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Tarih','İSLAM TARİHİ VE UYGARLIĞI (13. YÜZYILA KADAR)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Tarih','TÜRK-İSLAM DEVLETLERİ (11–13. YÜZYILLAR)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi)VALUES ('9','F','Tarih','TÜRKİYE TARİHİ (11 – 13.YÜZYIL)','islenmedi')");



                //9.sınıf A şubesi coğrafya
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Doğa-insan etkileşimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Coğrafyanın bölümlenmesi ve ilişkili olduğu disiplinler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Coğrafya biliminin gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Dünyanın şekli ve hareketlerinin etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Koordinat sistemini oluşturan unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Mutlak ve göreceli konum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Harita Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Atmosferin ve iklim elemanlarının genel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Dünyada ve Türkiye’de görülen iklim tipleri ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Yerleşmelerin yer seçimini ve gelişimini etkileyen faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Yerleşme doku ve tiplerinin oluşumunda etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Türkiye’de yerleşmelerin dağılışını etkileyen faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Yerleşmelerin fonksiyonel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Bölge belirlemede kullanılan kriterler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Dünyadaki farklı bölge örnekleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Bölge sınırlarının amaca göre değişebilirliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Çeşitli coğrafi kriterlerle belirlenmiş bölgelerde bulunan ülkeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','İnsanların doğal çevreyi kullanma biçimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Coğrafya','Doğal ortamda insan etkisiyle meydana gelen değişimler','islenmedi')");
                //9.sınıf B şubesi coğrafya
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Doğa-insan etkileşimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Coğrafyanın bölümlenmesi ve ilişkili olduğu disiplinler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Coğrafya biliminin gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Dünyanın şekli ve hareketlerinin etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Koordinat sistemini oluşturan unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Mutlak ve göreceli konum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Harita Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Atmosferin ve iklim elemanlarının genel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Dünyada ve Türkiye’de görülen iklim tipleri ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Yerleşmelerin yer seçimini ve gelişimini etkileyen faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Yerleşme doku ve tiplerinin oluşumunda etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Türkiye’de yerleşmelerin dağılışını etkileyen faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Yerleşmelerin fonksiyonel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Bölge belirlemede kullanılan kriterler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Dünyadaki farklı bölge örnekleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Bölge sınırlarının amaca göre değişebilirliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Çeşitli coğrafi kriterlerle belirlenmiş bölgelerde bulunan ülkeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','İnsanların doğal çevreyi kullanma biçimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Coğrafya','Doğal ortamda insan etkisiyle meydana gelen değişimler','islenmedi')");
                //9.sınıf C şubesi coğrafya
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Doğa-insan etkileşimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Coğrafyanın bölümlenmesi ve ilişkili olduğu disiplinler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Coğrafya biliminin gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Dünyanın şekli ve hareketlerinin etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Koordinat sistemini oluşturan unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Mutlak ve göreceli konum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Harita Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Atmosferin ve iklim elemanlarının genel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Dünyada ve Türkiye’de görülen iklim tipleri ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Yerleşmelerin yer seçimini ve gelişimini etkileyen faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Yerleşme doku ve tiplerinin oluşumunda etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Türkiye’de yerleşmelerin dağılışını etkileyen faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Yerleşmelerin fonksiyonel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Bölge belirlemede kullanılan kriterler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Dünyadaki farklı bölge örnekleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Bölge sınırlarının amaca göre değişebilirliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Çeşitli coğrafi kriterlerle belirlenmiş bölgelerde bulunan ülkeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','İnsanların doğal çevreyi kullanma biçimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Coğrafya','Doğal ortamda insan etkisiyle meydana gelen değişimler','islenmedi')");
                //9.sınıf D şubesi coğrafya
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Doğa-insan etkileşimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Coğrafyanın bölümlenmesi ve ilişkili olduğu disiplinler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Coğrafya biliminin gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Dünyanın şekli ve hareketlerinin etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Koordinat sistemini oluşturan unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Mutlak ve göreceli konum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Harita Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Atmosferin ve iklim elemanlarının genel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Dünyada ve Türkiye’de görülen iklim tipleri ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Yerleşmelerin yer seçimini ve gelişimini etkileyen faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Yerleşme doku ve tiplerinin oluşumunda etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Türkiye’de yerleşmelerin dağılışını etkileyen faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Yerleşmelerin fonksiyonel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Bölge belirlemede kullanılan kriterler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Dünyadaki farklı bölge örnekleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Bölge sınırlarının amaca göre değişebilirliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Çeşitli coğrafi kriterlerle belirlenmiş bölgelerde bulunan ülkeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','İnsanların doğal çevreyi kullanma biçimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Coğrafya','Doğal ortamda insan etkisiyle meydana gelen değişimler','islenmedi')");
                //9.sınıf E şubesi coğrafya
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Doğa-insan etkileşimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Coğrafyanın bölümlenmesi ve ilişkili olduğu disiplinler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Coğrafya biliminin gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Dünyanın şekli ve hareketlerinin etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Koordinat sistemini oluşturan unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Mutlak ve göreceli konum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Harita Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Atmosferin ve iklim elemanlarının genel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Dünyada ve Türkiye’de görülen iklim tipleri ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Yerleşmelerin yer seçimini ve gelişimini etkileyen faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Yerleşme doku ve tiplerinin oluşumunda etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Türkiye’de yerleşmelerin dağılışını etkileyen faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Yerleşmelerin fonksiyonel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Bölge belirlemede kullanılan kriterler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Dünyadaki farklı bölge örnekleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Bölge sınırlarının amaca göre değişebilirliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Çeşitli coğrafi kriterlerle belirlenmiş bölgelerde bulunan ülkeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','İnsanların doğal çevreyi kullanma biçimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Coğrafya','Doğal ortamda insan etkisiyle meydana gelen değişimler','islenmedi')");
                //9.sınıf F şubesi coğrafya
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Doğa-insan etkileşimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Coğrafyanın bölümlenmesi ve ilişkili olduğu disiplinler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Coğrafya biliminin gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Dünyanın şekli ve hareketlerinin etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Koordinat sistemini oluşturan unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Mutlak ve göreceli konum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Harita Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Atmosferin ve iklim elemanlarının genel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Dünyada ve Türkiye’de görülen iklim tipleri ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Yerleşmelerin yer seçimini ve gelişimini etkileyen faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Yerleşme doku ve tiplerinin oluşumunda etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Türkiye’de yerleşmelerin dağılışını etkileyen faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Yerleşmelerin fonksiyonel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Bölge belirlemede kullanılan kriterler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Dünyadaki farklı bölge örnekleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Bölge sınırlarının amaca göre değişebilirliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Çeşitli coğrafi kriterlerle belirlenmiş bölgelerde bulunan ülkeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','İnsanların doğal çevreyi kullanma biçimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Coğrafya','Doğal ortamda insan etkisiyle meydana gelen değişimler','islenmedi')");



                //9.sınıf A şubesi edebiyat
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Dil Bilgisi: Ses Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Dil Bilgisi: Yazım Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Dil Bilgisi: Şekil Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Dil Bilgisi: Noktalama Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Diksiyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Dil Bilgisi: Kelimede Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Makale','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Dil Bilgisi: Kelime Türleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Senaryo','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Masal/Fabl','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Mektup/E-Posta','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','A','Edebiyat','Günlük/Blog','islenmedi')");
                //9.sınıf B şubesi edebiyat
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Dil Bilgisi: Ses Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Dil Bilgisi: Yazım Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Dil Bilgisi: Şekil Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Dil Bilgisi: Noktalama Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Diksiyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Dil Bilgisi: Kelimede Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Makale','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Dil Bilgisi: Kelime Türleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Senaryo','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Masal/Fabl','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Mektup/E-Posta','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','B','Edebiyat','Günlük/Blog','islenmedi')");
                //9.sınıf C şubesi edebiyat
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Dil Bilgisi: Ses Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Dil Bilgisi: Yazım Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Dil Bilgisi: Şekil Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Dil Bilgisi: Noktalama Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Diksiyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Dil Bilgisi: Kelimede Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Makale','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Dil Bilgisi: Kelime Türleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Senaryo','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Masal/Fabl','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Mektup/E-Posta','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','C','Edebiyat','Günlük/Blog','islenmedi')");
                //9.sınıf D şubesi edebiyat
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Dil Bilgisi: Ses Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Dil Bilgisi: Yazım Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Dil Bilgisi: Şekil Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Dil Bilgisi: Noktalama Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Diksiyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Dil Bilgisi: Kelimede Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Makale','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Dil Bilgisi: Kelime Türleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Senaryo','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Masal/Fabl','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Mektup/E-Posta','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','D','Edebiyat','Günlük/Blog','islenmedi')");
                //9.sınıf E şubesi edebiyat
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Dil Bilgisi: Ses Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Dil Bilgisi: Yazım Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Dil Bilgisi: Şekil Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Dil Bilgisi: Noktalama Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Diksiyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Dil Bilgisi: Kelimede Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Makale','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Dil Bilgisi: Kelime Türleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Senaryo','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Masal/Fabl','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Mektup/E-Posta','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','E','Edebiyat','Günlük/Blog','islenmedi')");
                //9.sınıf F şubesi edebiyat
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Dil Bilgisi: Ses Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Dil Bilgisi: Yazım Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Dil Bilgisi: Şekil Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Dil Bilgisi: Noktalama Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Diksiyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Dil Bilgisi: Kelimede Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Makale','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Dil Bilgisi: Kelime Türleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Senaryo','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Masal/Fabl','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Mektup/E-Posta','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('9','F','Edebiyat','Günlük/Blog','islenmedi')");




                //10.SINIF A ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Matematik','Sıralama ve Seçme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Matematik','Basit Olayların Olasılıkları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Matematik','Fonksiyon Kavramı ve Gösterimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Matematik','İki Fonksiyonun Bileşkesi ve Bir Fonksiyonun Tersi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Matematik','Polinom Kavramı ve Polinomlarla İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Matematik','Polinomların Çarpanlara Ayrılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Matematik','İkinci Dereceden Bir Bilinmeyenli Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Matematik','Çokgenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Matematik','Dörtgenler ve Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Matematik','Özel Dörtgenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Matematik','Katı Cisimler','islenmedi')");
                //10.SINIF B ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Matematik','Sıralama ve Seçme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Matematik','Basit Olayların Olasılıkları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Matematik','Fonksiyon Kavramı ve Gösterimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Matematik','İki Fonksiyonun Bileşkesi ve Bir Fonksiyonun Tersi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Matematik','Polinom Kavramı ve Polinomlarla İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Matematik','Polinomların Çarpanlara Ayrılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Matematik','İkinci Dereceden Bir Bilinmeyenli Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Matematik','Çokgenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Matematik','Dörtgenler ve Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Matematik','Özel Dörtgenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Matematik','Katı Cisimler','islenmedi')");
                //10.SINIF C ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Matematik','Sıralama ve Seçme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Matematik','Basit Olayların Olasılıkları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Matematik','Fonksiyon Kavramı ve Gösterimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Matematik','İki Fonksiyonun Bileşkesi ve Bir Fonksiyonun Tersi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Matematik','Polinom Kavramı ve Polinomlarla İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Matematik','Polinomların Çarpanlara Ayrılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Matematik','İkinci Dereceden Bir Bilinmeyenli Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Matematik','Çokgenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Matematik','Dörtgenler ve Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Matematik','Özel Dörtgenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Matematik','Katı Cisimler','islenmedi')");
                //10.SINIF D ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Matematik','Sıralama ve Seçme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Matematik','Basit Olayların Olasılıkları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Matematik','Fonksiyon Kavramı ve Gösterimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Matematik','İki Fonksiyonun Bileşkesi ve Bir Fonksiyonun Tersi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Matematik','Polinom Kavramı ve Polinomlarla İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Matematik','Polinomların Çarpanlara Ayrılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Matematik','İkinci Dereceden Bir Bilinmeyenli Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Matematik','Çokgenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Matematik','Dörtgenler ve Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Matematik','Özel Dörtgenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Matematik','Katı Cisimler','islenmedi')");
                //10.SINIF E ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Matematik','Sıralama ve Seçme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Matematik','Basit Olayların Olasılıkları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Matematik','Fonksiyon Kavramı ve Gösterimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Matematik','İki Fonksiyonun Bileşkesi ve Bir Fonksiyonun Tersi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Matematik','Polinom Kavramı ve Polinomlarla İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Matematik','Polinomların Çarpanlara Ayrılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Matematik','İkinci Dereceden Bir Bilinmeyenli Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Matematik','Çokgenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Matematik','Dörtgenler ve Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Matematik','Özel Dörtgenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Matematik','Katı Cisimler','islenmedi')");
                //10.SINIF F ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Matematik','Sıralama ve Seçme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Matematik','Basit Olayların Olasılıkları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Matematik','Fonksiyon Kavramı ve Gösterimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Matematik','İki Fonksiyonun Bileşkesi ve Bir Fonksiyonun Tersi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Matematik','Polinom Kavramı ve Polinomlarla İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Matematik','Polinomların Çarpanlara Ayrılması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Matematik','İkinci Dereceden Bir Bilinmeyenli Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Matematik','Çokgenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Matematik','Dörtgenler ve Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Matematik','Özel Dörtgenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Matematik','Katı Cisimler','islenmedi')");



                //10.SINIF A ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Basınç ve Kaldırma Kuvveti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Elektrik Yükleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Akım, Potansiyel Fark, Direnç','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Elektrik Devreleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Mıknatıslar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Akım ve Manyetik Alan İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Dalga ve Dalga Hareketinin Temel Değişkenleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Su Dalgası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Deprem Dalgaları ve Dalgaların Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Aydınlanma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Gölge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Yansıma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Düz Aynalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Küresel Aynalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Kırılma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Mercekler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Prizmalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Fizik','Renk','islenmedi')");
                //10.SINIF B ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Basınç ve Kaldırma Kuvveti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Elektrik Yükleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Akım, Potansiyel Fark, Direnç','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Elektrik Devreleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Mıknatıslar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Akım ve Manyetik Alan İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Dalga ve Dalga Hareketinin Temel Değişkenleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Su Dalgası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Deprem Dalgaları ve Dalgaların Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Aydınlanma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Gölge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Yansıma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Düz Aynalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Küresel Aynalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Kırılma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Mercekler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Prizmalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Fizik','Renk','islenmedi')");
                //10.SINIF C ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Basınç ve Kaldırma Kuvveti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Elektrik Yükleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Akım, Potansiyel Fark, Direnç','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Elektrik Devreleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Mıknatıslar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Akım ve Manyetik Alan İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Dalga ve Dalga Hareketinin Temel Değişkenleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Su Dalgası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Deprem Dalgaları ve Dalgaların Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Aydınlanma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Gölge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Yansıma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Düz Aynalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Küresel Aynalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Kırılma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Mercekler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Prizmalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Fizik','Renk','islenmedi')");
                //10.SINIF D ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Basınç ve Kaldırma Kuvveti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Elektrik Yükleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Akım, Potansiyel Fark, Direnç','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Elektrik Devreleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Mıknatıslar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Akım ve Manyetik Alan İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Dalga ve Dalga Hareketinin Temel Değişkenleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Su Dalgası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Deprem Dalgaları ve Dalgaların Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Aydınlanma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Gölge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Yansıma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Düz Aynalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Küresel Aynalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Kırılma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Mercekler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Prizmalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Fizik','Renk','islenmedi')");
                //10.SINIF E ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Basınç ve Kaldırma Kuvveti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Elektrik Yükleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Akım, Potansiyel Fark, Direnç','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Elektrik Devreleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Mıknatıslar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Akım ve Manyetik Alan İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Dalga ve Dalga Hareketinin Temel Değişkenleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Su Dalgası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Deprem Dalgaları ve Dalgaların Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Aydınlanma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Gölge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Yansıma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Düz Aynalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Küresel Aynalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Kırılma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Mercekler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Prizmalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Fizik','Renk','islenmedi')");
                //10.SINIF F ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Basınç ve Kaldırma Kuvveti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Elektrik Yükleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Akım, Potansiyel Fark, Direnç','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Elektrik Devreleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Mıknatıslar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Akım ve Manyetik Alan İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Dalga ve Dalga Hareketinin Temel Değişkenleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Su Dalgası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Deprem Dalgaları ve Dalgaların Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Aydınlanma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Gölge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Yansıma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Düz Aynalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Küresel Aynalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Kırılma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Mercekler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Prizmalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Fizik','Renk','islenmedi')");



                //10.SINIF A ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Kimya','Homojen ve Heterojen Karışımlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Kimya','Ayırma ve Saflaştırma Teknikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Kimya','Asitler ve Bazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Kimya','Asitlerin ve Bazların Tepkimeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Kimya','Hayatımızda Asitler ve Bazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Kimya','Tuzlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Kimya','Yaygın Günlük Hayat Kimyasalları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Kimya','Gıdalar','islenmedi')");
                //10.SINIF B ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Kimya','Homojen ve Heterojen Karışımlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Kimya','Ayırma ve Saflaştırma Teknikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Kimya','Asitler ve Bazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Kimya','Asitlerin ve Bazların Tepkimeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Kimya','Hayatımızda Asitler ve Bazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Kimya','Tuzlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Kimya','Yaygın Günlük Hayat Kimyasalları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Kimya','Gıdalar','islenmedi')");
                //10.SINIF C ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Kimya','Homojen ve Heterojen Karışımlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Kimya','Ayırma ve Saflaştırma Teknikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Kimya','Asitler ve Bazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Kimya','Asitlerin ve Bazların Tepkimeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Kimya','Hayatımızda Asitler ve Bazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Kimya','Tuzlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Kimya','Yaygın Günlük Hayat Kimyasalları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Kimya','Gıdalar','islenmedi')");
                //10.SINIF D ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Kimya','Homojen ve Heterojen Karışımlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Kimya','Ayırma ve Saflaştırma Teknikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Kimya','Asitler ve Bazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Kimya','Asitlerin ve Bazların Tepkimeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Kimya','Hayatımızda Asitler ve Bazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Kimya','Tuzlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Kimya','Yaygın Günlük Hayat Kimyasalları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Kimya','Gıdalar','islenmedi')");
                //10.SINIF E ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Kimya','Homojen ve Heterojen Karışımlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Kimya','Ayırma ve Saflaştırma Teknikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Kimya','Asitler ve Bazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Kimya','Asitlerin ve Bazların Tepkimeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Kimya','Hayatımızda Asitler ve Bazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Kimya','Tuzlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Kimya','Yaygın Günlük Hayat Kimyasalları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Kimya','Gıdalar','islenmedi')");
               //10.SINIF F ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Kimya','Homojen ve Heterojen Karışımlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Kimya','Ayırma ve Saflaştırma Teknikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Kimya','Asitler ve Bazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Kimya','Asitlerin ve Bazların Tepkimeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Kimya','Hayatımızda Asitler ve Bazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Kimya','Tuzlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Kimya','Yaygın Günlük Hayat Kimyasalları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Kimya','Gıdalar','islenmedi')");



                //10.SINIF A ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Biyoloji','Mitoz ve Eşeysiz Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Biyoloji','Mayoz ve Eşeyli Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Biyoloji','Büyüme ve Gelişme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Biyoloji','Kalıtım ve Biyolojik Çeşitlilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Biyoloji','Ekosistem Ekolojisi','islenmedi')");
                //10.SINIF B ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Biyoloji','Mitoz ve Eşeysiz Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Biyoloji','Mayoz ve Eşeyli Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Biyoloji','Büyüme ve Gelişme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Biyoloji','Kalıtım ve Biyolojik Çeşitlilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Biyoloji','Ekosistem Ekolojisi','islenmedi')");
                //10.SINIF C ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Biyoloji','Mitoz ve Eşeysiz Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Biyoloji','Mayoz ve Eşeyli Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Biyoloji','Büyüme ve Gelişme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Biyoloji','Kalıtım ve Biyolojik Çeşitlilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Biyoloji','Ekosistem Ekolojisi','islenmedi')");
                //10.SINIF D ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Biyoloji','Mitoz ve Eşeysiz Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Biyoloji','Mayoz ve Eşeyli Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Biyoloji','Büyüme ve Gelişme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Biyoloji','Kalıtım ve Biyolojik Çeşitlilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Biyoloji','Ekosistem Ekolojisi','islenmedi')");
                //10.SINIF E ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Biyoloji','Mitoz ve Eşeysiz Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Biyoloji','Mayoz ve Eşeyli Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Biyoloji','Büyüme ve Gelişme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Biyoloji','Kalıtım ve Biyolojik Çeşitlilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Biyoloji','Ekosistem Ekolojisi','islenmedi')");

                //10.SINIF F ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Biyoloji','Mitoz ve Eşeysiz Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Biyoloji','Mayoz ve Eşeyli Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Biyoloji','Büyüme ve Gelişme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Biyoloji','Kalıtım ve Biyolojik Çeşitlilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Biyoloji','Ekosistem Ekolojisi','islenmedi')");



                //10.SINIF A ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Tarih','Yerleşme ve Devletleşme Sürecinde Selçuklu Devleti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Tarih','Beylikten Devlete Osmanlı Siyaseti (1302-1453)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Tarih','Devletleşme Sürecinde Savaşçılar ve Askerler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Tarih','Beylikten Devlete Osmanlı Medeniyeti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Tarih','Dünya Gücü Osmanlı Devleti (1453-1595)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Tarih','Sultan ve Osmanlı Merkez Teşkilatı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Tarih','Klasik Çağda Osmanlı Toplum Düzeni','islenmedi')");
                //10.SINIF B ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Tarih','Yerleşme ve Devletleşme Sürecinde Selçuklu Devleti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Tarih','Beylikten Devlete Osmanlı Siyaseti (1302-1453)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Tarih','Devletleşme Sürecinde Savaşçılar ve Askerler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Tarih','Beylikten Devlete Osmanlı Medeniyeti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Tarih','Dünya Gücü Osmanlı Devleti (1453-1595)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Tarih','Sultan ve Osmanlı Merkez Teşkilatı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Tarih','Klasik Çağda Osmanlı Toplum Düzeni','islenmedi')");
                //10.SINIF C ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Tarih','Yerleşme ve Devletleşme Sürecinde Selçuklu Devleti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Tarih','Beylikten Devlete Osmanlı Siyaseti (1302-1453)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Tarih','Devletleşme Sürecinde Savaşçılar ve Askerler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Tarih','Beylikten Devlete Osmanlı Medeniyeti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Tarih','Dünya Gücü Osmanlı Devleti (1453-1595)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Tarih','Sultan ve Osmanlı Merkez Teşkilatı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Tarih','Klasik Çağda Osmanlı Toplum Düzeni','islenmedi')");
                //10.SINIF D ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Tarih','Yerleşme ve Devletleşme Sürecinde Selçuklu Devleti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Tarih','Beylikten Devlete Osmanlı Siyaseti (1302-1453)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Tarih','Devletleşme Sürecinde Savaşçılar ve Askerler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Tarih','Beylikten Devlete Osmanlı Medeniyeti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Tarih','Dünya Gücü Osmanlı Devleti (1453-1595)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Tarih','Sultan ve Osmanlı Merkez Teşkilatı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Tarih','Klasik Çağda Osmanlı Toplum Düzeni','islenmedi')");
                //10.SINIF E ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Tarih','Yerleşme ve Devletleşme Sürecinde Selçuklu Devleti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Tarih','Beylikten Devlete Osmanlı Siyaseti (1302-1453)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Tarih','Devletleşme Sürecinde Savaşçılar ve Askerler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Tarih','Beylikten Devlete Osmanlı Medeniyeti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Tarih','Dünya Gücü Osmanlı Devleti (1453-1595)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Tarih','Sultan ve Osmanlı Merkez Teşkilatı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Tarih','Klasik Çağda Osmanlı Toplum Düzeni','islenmedi')");
                //10.SINIF F ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Tarih','Yerleşme ve Devletleşme Sürecinde Selçuklu Devleti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Tarih','Beylikten Devlete Osmanlı Siyaseti (1302-1453)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Tarih','Devletleşme Sürecinde Savaşçılar ve Askerler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Tarih','Beylikten Devlete Osmanlı Medeniyeti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Tarih','Dünya Gücü Osmanlı Devleti (1453-1595)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Tarih','Sultan ve Osmanlı Merkez Teşkilatı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Tarih','Klasik Çağda Osmanlı Toplum Düzeni','islenmedi')");




                //10.SINIF A ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Dünya’nın tektonik oluşumu ve iç yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Jeolojik zamanlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Türkiye’nin jeolojik geçmişi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Yeryüzü şekillerinin oluşum süreçlerinde iç ve dış kuvvetlerin etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Yeryüzü şekillerinin oluşum süreçlerinde kayaçların etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Türkiye’de iç ve dış kuvvetlerin yeryüzü şekillerinin oluşumuna etkisi ve ana yer şekilleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Yeryüzünün su varlığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Türkiye’nin su varlığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Yeryüzündeki toprak ve bitki çeşitliliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Türkiye’nin toprak ve bitki çeşitliliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Nüfus özellikleri ve nüfusun önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Dünya nüfusunun tarihsel süreçteki değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Nüfusun dağılışı üzerinde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Nüfus piramitlerinden yararlanarak nüfusun yapısıyla ilgili çıkarımlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Türkiye’de nüfusun tarihsel seyri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Türkiye nüfusunun dağılışında etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Türkiye nüfusunun yapısal özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Tarihsel süreçteki göçlerin nedenleri ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Türkiye’deki göçlerin sebep ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Göçün mekânsal etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Ekonomik faaliyetleri temel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Ülkelerin gelişmişlik düzeyleriyle olan ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Dünya üzerinde bulunan önemli ulaşım hatlarının bölgesel ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Afetlerin oluşum nedenleri ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Dünyada ve Türkiye’de afetlerin dağılışları ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Coğrafya','Afetlerden korunma yöntemleri','islenmedi')");
                //10.SINIF B ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Dünya’nın tektonik oluşumu ve iç yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Jeolojik zamanlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Türkiye’nin jeolojik geçmişi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Yeryüzü şekillerinin oluşum süreçlerinde iç ve dış kuvvetlerin etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Yeryüzü şekillerinin oluşum süreçlerinde kayaçların etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Türkiye’de iç ve dış kuvvetlerin yeryüzü şekillerinin oluşumuna etkisi ve ana yer şekilleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Yeryüzünün su varlığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Türkiye’nin su varlığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Yeryüzündeki toprak ve bitki çeşitliliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Türkiye’nin toprak ve bitki çeşitliliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Nüfus özellikleri ve nüfusun önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Dünya nüfusunun tarihsel süreçteki değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Nüfusun dağılışı üzerinde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Nüfus piramitlerinden yararlanarak nüfusun yapısıyla ilgili çıkarımlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Türkiye’de nüfusun tarihsel seyri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Türkiye nüfusunun dağılışında etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Türkiye nüfusunun yapısal özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Tarihsel süreçteki göçlerin nedenleri ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Türkiye’deki göçlerin sebep ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Göçün mekânsal etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Ekonomik faaliyetleri temel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Ülkelerin gelişmişlik düzeyleriyle olan ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Dünya üzerinde bulunan önemli ulaşım hatlarının bölgesel ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Afetlerin oluşum nedenleri ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Dünyada ve Türkiye’de afetlerin dağılışları ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Coğrafya','Afetlerden korunma yöntemleri','islenmedi')");
                //10.SINIF C ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Dünya’nın tektonik oluşumu ve iç yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Jeolojik zamanlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Türkiye’nin jeolojik geçmişi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Yeryüzü şekillerinin oluşum süreçlerinde iç ve dış kuvvetlerin etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Yeryüzü şekillerinin oluşum süreçlerinde kayaçların etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Türkiye’de iç ve dış kuvvetlerin yeryüzü şekillerinin oluşumuna etkisi ve ana yer şekilleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Yeryüzünün su varlığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Türkiye’nin su varlığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Yeryüzündeki toprak ve bitki çeşitliliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Türkiye’nin toprak ve bitki çeşitliliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Nüfus özellikleri ve nüfusun önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Dünya nüfusunun tarihsel süreçteki değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Nüfusun dağılışı üzerinde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Nüfus piramitlerinden yararlanarak nüfusun yapısıyla ilgili çıkarımlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Türkiye’de nüfusun tarihsel seyri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Türkiye nüfusunun dağılışında etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Türkiye nüfusunun yapısal özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Tarihsel süreçteki göçlerin nedenleri ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Türkiye’deki göçlerin sebep ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Göçün mekânsal etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Ekonomik faaliyetleri temel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Ülkelerin gelişmişlik düzeyleriyle olan ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Dünya üzerinde bulunan önemli ulaşım hatlarının bölgesel ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Afetlerin oluşum nedenleri ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Dünyada ve Türkiye’de afetlerin dağılışları ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Coğrafya','Afetlerden korunma yöntemleri','islenmedi')");
                //10.SINIF D ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Dünya’nın tektonik oluşumu ve iç yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Jeolojik zamanlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Türkiye’nin jeolojik geçmişi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Yeryüzü şekillerinin oluşum süreçlerinde iç ve dış kuvvetlerin etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Yeryüzü şekillerinin oluşum süreçlerinde kayaçların etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Türkiye’de iç ve dış kuvvetlerin yeryüzü şekillerinin oluşumuna etkisi ve ana yer şekilleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Yeryüzünün su varlığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Türkiye’nin su varlığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Yeryüzündeki toprak ve bitki çeşitliliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Türkiye’nin toprak ve bitki çeşitliliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Nüfus özellikleri ve nüfusun önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Dünya nüfusunun tarihsel süreçteki değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Nüfusun dağılışı üzerinde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Nüfus piramitlerinden yararlanarak nüfusun yapısıyla ilgili çıkarımlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Türkiye’de nüfusun tarihsel seyri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Türkiye nüfusunun dağılışında etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Türkiye nüfusunun yapısal özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Tarihsel süreçteki göçlerin nedenleri ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Türkiye’deki göçlerin sebep ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Göçün mekânsal etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Ekonomik faaliyetleri temel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Ülkelerin gelişmişlik düzeyleriyle olan ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Dünya üzerinde bulunan önemli ulaşım hatlarının bölgesel ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Afetlerin oluşum nedenleri ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Dünyada ve Türkiye’de afetlerin dağılışları ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Coğrafya','Afetlerden korunma yöntemleri','islenmedi')");
                //10.SINIF E ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Dünya’nın tektonik oluşumu ve iç yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Jeolojik zamanlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Türkiye’nin jeolojik geçmişi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Yeryüzü şekillerinin oluşum süreçlerinde iç ve dış kuvvetlerin etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Yeryüzü şekillerinin oluşum süreçlerinde kayaçların etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Türkiye’de iç ve dış kuvvetlerin yeryüzü şekillerinin oluşumuna etkisi ve ana yer şekilleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Yeryüzünün su varlığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Türkiye’nin su varlığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Yeryüzündeki toprak ve bitki çeşitliliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Türkiye’nin toprak ve bitki çeşitliliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Nüfus özellikleri ve nüfusun önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Dünya nüfusunun tarihsel süreçteki değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Nüfusun dağılışı üzerinde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Nüfus piramitlerinden yararlanarak nüfusun yapısıyla ilgili çıkarımlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Türkiye’de nüfusun tarihsel seyri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Türkiye nüfusunun dağılışında etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Türkiye nüfusunun yapısal özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Tarihsel süreçteki göçlerin nedenleri ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Türkiye’deki göçlerin sebep ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Göçün mekânsal etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Ekonomik faaliyetleri temel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Ülkelerin gelişmişlik düzeyleriyle olan ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Dünya üzerinde bulunan önemli ulaşım hatlarının bölgesel ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Afetlerin oluşum nedenleri ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Dünyada ve Türkiye’de afetlerin dağılışları ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Coğrafya','Afetlerden korunma yöntemleri','islenmedi')");
                //10.SINIF F ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Dünya’nın tektonik oluşumu ve iç yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Jeolojik zamanlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Türkiye’nin jeolojik geçmişi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Yeryüzü şekillerinin oluşum süreçlerinde iç ve dış kuvvetlerin etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Yeryüzü şekillerinin oluşum süreçlerinde kayaçların etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Türkiye’de iç ve dış kuvvetlerin yeryüzü şekillerinin oluşumuna etkisi ve ana yer şekilleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Yeryüzünün su varlığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Türkiye’nin su varlığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Yeryüzündeki toprak ve bitki çeşitliliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Türkiye’nin toprak ve bitki çeşitliliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Nüfus özellikleri ve nüfusun önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Dünya nüfusunun tarihsel süreçteki değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Nüfusun dağılışı üzerinde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Nüfus piramitlerinden yararlanarak nüfusun yapısıyla ilgili çıkarımlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Türkiye’de nüfusun tarihsel seyri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Türkiye nüfusunun dağılışında etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Türkiye nüfusunun yapısal özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Tarihsel süreçteki göçlerin nedenleri ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Türkiye’deki göçlerin sebep ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Göçün mekânsal etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Ekonomik faaliyetleri temel özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Ülkelerin gelişmişlik düzeyleriyle olan ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Dünya üzerinde bulunan önemli ulaşım hatlarının bölgesel ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Afetlerin oluşum nedenleri ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Dünyada ve Türkiye’de afetlerin dağılışları ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Coğrafya','Afetlerden korunma yöntemleri','islenmedi')");



                //10.SINIF A ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Edebiyat','Edebiyat-Tarih İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Edebiyat','Edebiyat-Din İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Edebiyat','Türklerin Kullandığı Alfabeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Edebiyat','Yazım Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Edebiyat','Noktalama İşaretleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Edebiyat',' Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Edebiyat','Fiilimsiler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Edebiyat','Destan / Efsane','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Edebiyat','Sıfat Tamlamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Edebiyat',' Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Edebiyat','Anı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Edebiyat','Haber Metni','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Edebiyat','Gezi Yazısı','islenmedi')");
                //10.SINIF B ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Edebiyat','Edebiyat-Tarih İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Edebiyat','Edebiyat-Din İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Edebiyat','Türklerin Kullandığı Alfabeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Edebiyat','Yazım Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Edebiyat','Noktalama İşaretleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Edebiyat',' Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Edebiyat','Fiilimsiler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Edebiyat','Destan / Efsane','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Edebiyat','Sıfat Tamlamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Edebiyat',' Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Edebiyat','Anı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Edebiyat','Haber Metni','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Edebiyat','Gezi Yazısı','islenmedi')");
                //10.SINIF C ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Edebiyat','Edebiyat-Tarih İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Edebiyat','Edebiyat-Din İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Edebiyat','Türklerin Kullandığı Alfabeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Edebiyat','Yazım Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Edebiyat','Noktalama İşaretleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Edebiyat',' Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Edebiyat','Fiilimsiler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Edebiyat','Destan / Efsane','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Edebiyat','Sıfat Tamlamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Edebiyat',' Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Edebiyat','Anı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Edebiyat','Haber Metni','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Edebiyat','Gezi Yazısı','islenmedi')");
                //10.SINIF D ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Edebiyat','Edebiyat-Tarih İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Edebiyat','Edebiyat-Din İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Edebiyat','Türklerin Kullandığı Alfabeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Edebiyat','Yazım Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Edebiyat','Noktalama İşaretleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Edebiyat',' Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Edebiyat','Fiilimsiler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Edebiyat','Destan / Efsane','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Edebiyat','Sıfat Tamlamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Edebiyat',' Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Edebiyat','Anı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Edebiyat','Haber Metni','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Edebiyat','Gezi Yazısı','islenmedi')");

                //10.SINIF E ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Edebiyat','Edebiyat-Tarih İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Edebiyat','Edebiyat-Din İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Edebiyat','Türklerin Kullandığı Alfabeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Edebiyat','Yazım Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Edebiyat','Noktalama İşaretleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Edebiyat',' Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Edebiyat','Fiilimsiler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Edebiyat','Destan / Efsane','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Edebiyat','Sıfat Tamlamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Edebiyat',' Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Edebiyat','Anı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Edebiyat','Haber Metni','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Edebiyat','Gezi Yazısı','islenmedi')");
                //10.SINIF F ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Edebiyat','Edebiyat-Tarih İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Edebiyat','Edebiyat-Din İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Edebiyat','Türklerin Kullandığı Alfabeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Edebiyat','Yazım Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Edebiyat','Noktalama İşaretleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Edebiyat',' Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Edebiyat','Fiilimsiler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Edebiyat','Destan / Efsane','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Edebiyat','Sıfat Tamlamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Edebiyat',' Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Edebiyat','Anı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Edebiyat','Haber Metni','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Edebiyat','Gezi Yazısı','islenmedi')");



                //10.SINIF A ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Felsefe Nedir?','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Düşünmenin Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Felsefi Düşünce','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Felsefi Düşüncenin Ortaya Çıkışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Felsefi Düşüncenin Nitelikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Felsefenin İnsan ve Toplum Hayatındaki Rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Felsefenin Bireysel ve Toplumsal İşlevi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Felsefe- Hayat İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Akıl Yürütmeye ve Düşünmeye İlişkin Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Akıl Yürütme ve Argümantasyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Dili Doğru Kullanmanın Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Dilin İşlev','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Felsefi Sorular Nitelikleri ve Diğer Sorulardan Farkı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Felsefi Soru Oluşturma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Görüş Veya Argümanların Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Felsefenin Temel Konuları ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Varlık Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Varlığın Hakikati ve Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Varlığın Mahiyeti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Evrende Amaçlılık','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Bilgi Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Doğru Bilginin İmkanı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Bilginin Kaynağı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Bilginin Sınırları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Bilim Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Bilim ve Bilimsel Yöntem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Bilimin Değeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Bilim ve Felsefe İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Ahlak Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','İyi ve Kötü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Özgürlük ve Sorumluluk','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Evrensel Ahlak Yasası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Din Felsefesinin Konusu ve Soruları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Teoloji ve Din Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Tanrı’nın Varlığı İle İlgili Görüşler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Siyaset Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Hak, Adalet, Özgürlük','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','İktidarın Kaynağı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','İdeal Devlet Düzeni','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Temel Hak ve Özgürlükler Açısından Egemenlik Sorunu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Sanat Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Güzellik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Sanat','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Sanat Eserinin Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Felsefi Metin Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Alternatif Görüş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Geliştirme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Felsefi Deneme Yazma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Akıl Yürütme Becerileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','A','Felsefe','Felsefi Akıl Yürütme Becerilerinin Diğer Alanlarda Kullanımı','islenmedi')");
                //10.SINIF B ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Felsefe Nedir?','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Düşünmenin Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Felsefi Düşünce','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Felsefi Düşüncenin Ortaya Çıkışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Felsefi Düşüncenin Nitelikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Felsefenin İnsan ve Toplum Hayatındaki Rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Felsefenin Bireysel ve Toplumsal İşlevi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Felsefe- Hayat İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Akıl Yürütmeye ve Düşünmeye İlişkin Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Akıl Yürütme ve Argümantasyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Dili Doğru Kullanmanın Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Dilin İşlev','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Felsefi Sorular Nitelikleri ve Diğer Sorulardan Farkı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Felsefi Soru Oluşturma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Görüş Veya Argümanların Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Felsefenin Temel Konuları ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Varlık Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Varlığın Hakikati ve Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Varlığın Mahiyeti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Evrende Amaçlılık','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Bilgi Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Doğru Bilginin İmkanı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Bilginin Kaynağı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Bilginin Sınırları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Bilim Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Bilim ve Bilimsel Yöntem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Bilimin Değeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Bilim ve Felsefe İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Ahlak Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','İyi ve Kötü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Özgürlük ve Sorumluluk','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Evrensel Ahlak Yasası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Din Felsefesinin Konusu ve Soruları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Teoloji ve Din Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Tanrı’nın Varlığı İle İlgili Görüşler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Siyaset Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Hak, Adalet, Özgürlük','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','İktidarın Kaynağı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','İdeal Devlet Düzeni','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Temel Hak ve Özgürlükler Açısından Egemenlik Sorunu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Sanat Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Güzellik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Sanat','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Sanat Eserinin Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Felsefi Metin Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Alternatif Görüş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Geliştirme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Felsefi Deneme Yazma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Akıl Yürütme Becerileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','B','Felsefe','Felsefi Akıl Yürütme Becerilerinin Diğer Alanlarda Kullanımı','islenmedi')");
                //10.SINIF C ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Felsefe Nedir?','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Düşünmenin Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Felsefi Düşünce','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Felsefi Düşüncenin Ortaya Çıkışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Felsefi Düşüncenin Nitelikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Felsefenin İnsan ve Toplum Hayatındaki Rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Felsefenin Bireysel ve Toplumsal İşlevi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Felsefe- Hayat İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Akıl Yürütmeye ve Düşünmeye İlişkin Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Akıl Yürütme ve Argümantasyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Dili Doğru Kullanmanın Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Dilin İşlev','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Felsefi Sorular Nitelikleri ve Diğer Sorulardan Farkı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Felsefi Soru Oluşturma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Görüş Veya Argümanların Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Felsefenin Temel Konuları ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Varlık Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Varlığın Hakikati ve Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Varlığın Mahiyeti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Evrende Amaçlılık','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Bilgi Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Doğru Bilginin İmkanı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Bilginin Kaynağı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Bilginin Sınırları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Bilim Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Bilim ve Bilimsel Yöntem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Bilimin Değeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Bilim ve Felsefe İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Ahlak Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','İyi ve Kötü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Özgürlük ve Sorumluluk','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Evrensel Ahlak Yasası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Din Felsefesinin Konusu ve Soruları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Teoloji ve Din Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Tanrı’nın Varlığı İle İlgili Görüşler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Siyaset Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Hak, Adalet, Özgürlük','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','İktidarın Kaynağı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','İdeal Devlet Düzeni','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Temel Hak ve Özgürlükler Açısından Egemenlik Sorunu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Sanat Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Güzellik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Sanat','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Sanat Eserinin Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Felsefi Metin Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Alternatif Görüş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Geliştirme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Felsefi Deneme Yazma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Akıl Yürütme Becerileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','C','Felsefe','Felsefi Akıl Yürütme Becerilerinin Diğer Alanlarda Kullanımı','islenmedi')");

                //10.SINIF D ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Felsefe Nedir?','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Düşünmenin Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Felsefi Düşünce','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Felsefi Düşüncenin Ortaya Çıkışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Felsefi Düşüncenin Nitelikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Felsefenin İnsan ve Toplum Hayatındaki Rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Felsefenin Bireysel ve Toplumsal İşlevi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Felsefe- Hayat İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Akıl Yürütmeye ve Düşünmeye İlişkin Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Akıl Yürütme ve Argümantasyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Dili Doğru Kullanmanın Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Dilin İşlev','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Felsefi Sorular Nitelikleri ve Diğer Sorulardan Farkı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Felsefi Soru Oluşturma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Görüş Veya Argümanların Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Felsefenin Temel Konuları ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Varlık Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Varlığın Hakikati ve Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Varlığın Mahiyeti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Evrende Amaçlılık','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Bilgi Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Doğru Bilginin İmkanı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Bilginin Kaynağı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Bilginin Sınırları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Bilim Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Bilim ve Bilimsel Yöntem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Bilimin Değeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Bilim ve Felsefe İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Ahlak Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','İyi ve Kötü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Özgürlük ve Sorumluluk','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Evrensel Ahlak Yasası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Din Felsefesinin Konusu ve Soruları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Teoloji ve Din Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Tanrı’nın Varlığı İle İlgili Görüşler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Siyaset Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Hak, Adalet, Özgürlük','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','İktidarın Kaynağı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','İdeal Devlet Düzeni','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Temel Hak ve Özgürlükler Açısından Egemenlik Sorunu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Sanat Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Güzellik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Sanat','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Sanat Eserinin Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Felsefi Metin Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Alternatif Görüş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Geliştirme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Felsefi Deneme Yazma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Akıl Yürütme Becerileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','D','Felsefe','Felsefi Akıl Yürütme Becerilerinin Diğer Alanlarda Kullanımı','islenmedi')");
                //10.SINIF E ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Felsefe Nedir?','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Düşünmenin Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Felsefi Düşünce','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Felsefi Düşüncenin Ortaya Çıkışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Felsefi Düşüncenin Nitelikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Felsefenin İnsan ve Toplum Hayatındaki Rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Felsefenin Bireysel ve Toplumsal İşlevi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Felsefe- Hayat İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Akıl Yürütmeye ve Düşünmeye İlişkin Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Akıl Yürütme ve Argümantasyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Dili Doğru Kullanmanın Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Dilin İşlev','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Felsefi Sorular Nitelikleri ve Diğer Sorulardan Farkı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Felsefi Soru Oluşturma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Görüş Veya Argümanların Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Felsefenin Temel Konuları ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Varlık Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Varlığın Hakikati ve Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Varlığın Mahiyeti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Evrende Amaçlılık','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Bilgi Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Doğru Bilginin İmkanı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Bilginin Kaynağı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Bilginin Sınırları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Bilim Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Bilim ve Bilimsel Yöntem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Bilimin Değeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Bilim ve Felsefe İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Ahlak Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','İyi ve Kötü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Özgürlük ve Sorumluluk','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Evrensel Ahlak Yasası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Din Felsefesinin Konusu ve Soruları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Teoloji ve Din Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Tanrı’nın Varlığı İle İlgili Görüşler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Siyaset Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Hak, Adalet, Özgürlük','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','İktidarın Kaynağı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','İdeal Devlet Düzeni','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Temel Hak ve Özgürlükler Açısından Egemenlik Sorunu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Sanat Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Güzellik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Sanat','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Sanat Eserinin Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Felsefi Metin Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Alternatif Görüş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Geliştirme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Felsefi Deneme Yazma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Akıl Yürütme Becerileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','E','Felsefe','Felsefi Akıl Yürütme Becerilerinin Diğer Alanlarda Kullanımı','islenmedi')");
                //10.SINIF F ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Felsefe Nedir?','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Düşünmenin Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Felsefi Düşünce','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Felsefi Düşüncenin Ortaya Çıkışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Felsefi Düşüncenin Nitelikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Felsefenin İnsan ve Toplum Hayatındaki Rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Felsefenin Bireysel ve Toplumsal İşlevi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Felsefe- Hayat İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Akıl Yürütmeye ve Düşünmeye İlişkin Temel Kavramlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Akıl Yürütme ve Argümantasyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Dili Doğru Kullanmanın Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Dilin İşlev','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Felsefi Sorular Nitelikleri ve Diğer Sorulardan Farkı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Felsefi Soru Oluşturma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Görüş Veya Argümanların Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Felsefenin Temel Konuları ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Varlık Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Varlığın Hakikati ve Bilgisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Varlığın Mahiyeti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Evrende Amaçlılık','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Bilgi Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Doğru Bilginin İmkanı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Bilginin Kaynağı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Bilginin Sınırları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Bilim Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Bilim ve Bilimsel Yöntem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Bilimin Değeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Bilim ve Felsefe İlişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Ahlak Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','İyi ve Kötü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Özgürlük ve Sorumluluk','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Evrensel Ahlak Yasası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Din Felsefesinin Konusu ve Soruları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Teoloji ve Din Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Tanrı’nın Varlığı İle İlgili Görüşler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Siyaset Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Hak, Adalet, Özgürlük','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','İktidarın Kaynağı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','İdeal Devlet Düzeni','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Temel Hak ve Özgürlükler Açısından Egemenlik Sorunu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Sanat Felsefesinin Konusu ve Problemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Güzellik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Sanat','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Sanat Eserinin Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Felsefi Metin Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Alternatif Görüş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Geliştirme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Felsefi Deneme Yazma','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Akıl Yürütme Becerileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('10','F','Felsefe','Felsefi Akıl Yürütme Becerilerinin Diğer Alanlarda Kullanımı','islenmedi')");








                //11.SINIF A ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','Yönlü Açılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','Trigonometrik Fonksiyonlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','Doğrunun Analitik İncelemesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','Modüler Artimetik ve İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','Fonksiyonlarla İlgili Uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','İkinci Dereceden Fonksiyonlar ve Grafikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','Fonksiyonların Dönüşümleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','İkinci Dereceden İki Bilinmeyenli Denklem Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','İkinci Dereceden Bir Bilinmeyenli Eşitsizlikler ve Eşitsizlik Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','Çemberin Temel Elemanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','Çemberde Açılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','Çemberde Teğet','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','Dairenin Çevresi ve Alanı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','Katı Cisimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Matematik','Olasılık','islenmedi')");


                //11.SINIF B ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','Yönlü Açılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','Trigonometrik Fonksiyonlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','Doğrunun Analitik İncelemesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','Modüler Artimetik ve İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','Fonksiyonlarla İlgili Uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','İkinci Dereceden Fonksiyonlar ve Grafikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','Fonksiyonların Dönüşümleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','İkinci Dereceden İki Bilinmeyenli Denklem Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','İkinci Dereceden Bir Bilinmeyenli Eşitsizlikler ve Eşitsizlik Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','Çemberin Temel Elemanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','Çemberde Açılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','Çemberde Teğet','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','Dairenin Çevresi ve Alanı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','Katı Cisimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Matematik','Olasılık','islenmedi')");
                //11.SINIF C ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','Yönlü Açılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','Trigonometrik Fonksiyonlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','Doğrunun Analitik İncelemesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','Modüler Artimetik ve İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','Fonksiyonlarla İlgili Uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','İkinci Dereceden Fonksiyonlar ve Grafikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','Fonksiyonların Dönüşümleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','İkinci Dereceden İki Bilinmeyenli Denklem Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','İkinci Dereceden Bir Bilinmeyenli Eşitsizlikler ve Eşitsizlik Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','Çemberin Temel Elemanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','Çemberde Açılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','Çemberde Teğet','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','Dairenin Çevresi ve Alanı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','Katı Cisimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Matematik','Olasılık','islenmedi')");
                //11.SINIF D ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','Yönlü Açılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','Trigonometrik Fonksiyonlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','Doğrunun Analitik İncelemesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','Modüler Artimetik ve İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','Fonksiyonlarla İlgili Uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','İkinci Dereceden Fonksiyonlar ve Grafikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','Fonksiyonların Dönüşümleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','İkinci Dereceden İki Bilinmeyenli Denklem Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','İkinci Dereceden Bir Bilinmeyenli Eşitsizlikler ve Eşitsizlik Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','Çemberin Temel Elemanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','Çemberde Açılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','Çemberde Teğet','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','Dairenin Çevresi ve Alanı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','Katı Cisimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Matematik','Olasılık','islenmedi')");
                //11.SINIF E ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','Yönlü Açılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','Trigonometrik Fonksiyonlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','Doğrunun Analitik İncelemesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','Modüler Artimetik ve İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','Fonksiyonlarla İlgili Uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','İkinci Dereceden Fonksiyonlar ve Grafikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','Fonksiyonların Dönüşümleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','İkinci Dereceden İki Bilinmeyenli Denklem Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','İkinci Dereceden Bir Bilinmeyenli Eşitsizlikler ve Eşitsizlik Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','Çemberin Temel Elemanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','Çemberde Açılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','Çemberde Teğet','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','Dairenin Çevresi ve Alanı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','Katı Cisimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Matematik','Olasılık','islenmedi')");

                //11.SINIF F ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','Yönlü Açılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','Trigonometrik Fonksiyonlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','Doğrunun Analitik İncelemesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','Modüler Artimetik ve İşlemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','Fonksiyonlarla İlgili Uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','İkinci Dereceden Fonksiyonlar ve Grafikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','Fonksiyonların Dönüşümleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','İkinci Dereceden İki Bilinmeyenli Denklem Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','İkinci Dereceden Bir Bilinmeyenli Eşitsizlikler ve Eşitsizlik Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','Çemberin Temel Elemanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','Çemberde Açılar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','Çemberde Teğet','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','Dairenin Çevresi ve Alanı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','Katı Cisimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Matematik','Olasılık','islenmedi')");


                //11.SINIF A ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','Vektörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','Bağıl Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','Newton’ın Hareket Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','Bir Boyutta Sabit İvmeli Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','İki Boyutta Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','Enerji ve Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','İtme ve Çizgisel Momentum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','Tork','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','Denge ve Denge Şartları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','Basit Makineler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','Elektriksel Kuvvet ve Elektrik Alan','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','Elektriksel Potansiyel','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','Düzgün Elektrik Alan ve Sığa','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','Manyetizma ve Elektromanyetik İndüklenme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','Alternatif Akım','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Fizik','Transförmatörler','islenmedi')");


                //11.SINIF B ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','Vektörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','Bağıl Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','Newton’ın Hareket Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','Bir Boyutta Sabit İvmeli Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','İki Boyutta Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','Enerji ve Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','İtme ve Çizgisel Momentum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','Tork','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','Denge ve Denge Şartları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','Basit Makineler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','Elektriksel Kuvvet ve Elektrik Alan','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','Elektriksel Potansiyel','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','Düzgün Elektrik Alan ve Sığa','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','Manyetizma ve Elektromanyetik İndüklenme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','Alternatif Akım','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Fizik','Transförmatörler','islenmedi')");
                //11.SINIF C ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','Vektörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','Bağıl Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','Newton’ın Hareket Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','Bir Boyutta Sabit İvmeli Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','İki Boyutta Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','Enerji ve Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','İtme ve Çizgisel Momentum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','Tork','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','Denge ve Denge Şartları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','Basit Makineler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','Elektriksel Kuvvet ve Elektrik Alan','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','Elektriksel Potansiyel','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','Düzgün Elektrik Alan ve Sığa','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','Manyetizma ve Elektromanyetik İndüklenme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','Alternatif Akım','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Fizik','Transförmatörler','islenmedi')");
                //11.SINIF D ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','Vektörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','Bağıl Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','Newton’ın Hareket Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','Bir Boyutta Sabit İvmeli Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','İki Boyutta Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','Enerji ve Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','İtme ve Çizgisel Momentum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','Tork','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','Denge ve Denge Şartları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','Basit Makineler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','Elektriksel Kuvvet ve Elektrik Alan','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','Elektriksel Potansiyel','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','Düzgün Elektrik Alan ve Sığa','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','Manyetizma ve Elektromanyetik İndüklenme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','Alternatif Akım','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Fizik','Transförmatörler','islenmedi')");

                //11.SINIF E ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','Vektörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','Bağıl Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','Newton’ın Hareket Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','Bir Boyutta Sabit İvmeli Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','İki Boyutta Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','Enerji ve Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','İtme ve Çizgisel Momentum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','Tork','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','Denge ve Denge Şartları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','Basit Makineler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','Elektriksel Kuvvet ve Elektrik Alan','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','Elektriksel Potansiyel','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','Düzgün Elektrik Alan ve Sığa','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','Manyetizma ve Elektromanyetik İndüklenme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','Alternatif Akım','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Fizik','Transförmatörler','islenmedi')");
                //11.SINIF F ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','Vektörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','Bağıl Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','Newton’ın Hareket Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','Bir Boyutta Sabit İvmeli Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','İki Boyutta Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','Enerji ve Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','İtme ve Çizgisel Momentum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','Tork','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','Denge ve Denge Şartları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','Basit Makineler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','Elektriksel Kuvvet ve Elektrik Alan','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','Elektriksel Potansiyel','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','Düzgün Elektrik Alan ve Sığa','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','Manyetizma ve Elektromanyetik İndüklenme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','Alternatif Akım','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Fizik','Transförmatörler','islenmedi')");

                //11.SINIF A ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Atomun Kuantum Modeli','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Periyodik Sistem ve Elektron Dizilimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Periyodik Özellikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Elementleri Tanıyalım','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Yükseltgenme Basamakları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Gazların Özellikleri ve Gaz Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','İdeal Gaz Yasası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Gazlarda Kinetik Teori','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Gaz Karışımları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Gerçek Gazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Çözücü Çözünen Etkileşimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Derişim Birimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Koligatif Özellikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Çözünürlük','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Çözünürlüğe Etki Eden Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Tepkimelerde Isı Değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Oluşum Entalpisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Bağ Enerjileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Tepkime Isılarının Toplanabilirliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Tepkime Hızları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Tepkime Hızını Etkileyen Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Kimyasal Denge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Dengeyi Etkileyen Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Kimya','Sulu Çözelti Dengeleri','islenmedi')");

                //11.SINIF B ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Atomun Kuantum Modeli','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Periyodik Sistem ve Elektron Dizilimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Periyodik Özellikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Elementleri Tanıyalım','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Yükseltgenme Basamakları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Gazların Özellikleri ve Gaz Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','İdeal Gaz Yasası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Gazlarda Kinetik Teori','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Gaz Karışımları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Gerçek Gazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Çözücü Çözünen Etkileşimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Derişim Birimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Koligatif Özellikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Çözünürlük','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Çözünürlüğe Etki Eden Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Tepkimelerde Isı Değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Oluşum Entalpisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Bağ Enerjileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Tepkime Isılarının Toplanabilirliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Tepkime Hızları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Tepkime Hızını Etkileyen Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Kimyasal Denge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Dengeyi Etkileyen Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Kimya','Sulu Çözelti Dengeleri','islenmedi')");
                //11.SINIF C ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Atomun Kuantum Modeli','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Periyodik Sistem ve Elektron Dizilimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Periyodik Özellikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Elementleri Tanıyalım','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Yükseltgenme Basamakları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Gazların Özellikleri ve Gaz Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','İdeal Gaz Yasası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Gazlarda Kinetik Teori','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Gaz Karışımları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Gerçek Gazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Çözücü Çözünen Etkileşimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Derişim Birimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Koligatif Özellikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Çözünürlük','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Çözünürlüğe Etki Eden Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Tepkimelerde Isı Değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Oluşum Entalpisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Bağ Enerjileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Tepkime Isılarının Toplanabilirliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Tepkime Hızları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Tepkime Hızını Etkileyen Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Kimyasal Denge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Dengeyi Etkileyen Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Kimya','Sulu Çözelti Dengeleri','islenmedi')");
                //11.SINIF D ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Atomun Kuantum Modeli','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Periyodik Sistem ve Elektron Dizilimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Periyodik Özellikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Elementleri Tanıyalım','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Yükseltgenme Basamakları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Gazların Özellikleri ve Gaz Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','İdeal Gaz Yasası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Gazlarda Kinetik Teori','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Gaz Karışımları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Gerçek Gazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Çözücü Çözünen Etkileşimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Derişim Birimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Koligatif Özellikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Çözünürlük','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Çözünürlüğe Etki Eden Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Tepkimelerde Isı Değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Oluşum Entalpisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Bağ Enerjileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Tepkime Isılarının Toplanabilirliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Tepkime Hızları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Tepkime Hızını Etkileyen Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Kimyasal Denge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Dengeyi Etkileyen Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Kimya','Sulu Çözelti Dengeleri','islenmedi')");

                //11.SINIF E ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Atomun Kuantum Modeli','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Periyodik Sistem ve Elektron Dizilimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Periyodik Özellikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Elementleri Tanıyalım','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Yükseltgenme Basamakları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Gazların Özellikleri ve Gaz Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','İdeal Gaz Yasası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Gazlarda Kinetik Teori','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Gaz Karışımları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Gerçek Gazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Çözücü Çözünen Etkileşimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Derişim Birimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Koligatif Özellikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Çözünürlük','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Çözünürlüğe Etki Eden Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Tepkimelerde Isı Değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Oluşum Entalpisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Bağ Enerjileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Tepkime Isılarının Toplanabilirliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Tepkime Hızları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Tepkime Hızını Etkileyen Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Kimyasal Denge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Dengeyi Etkileyen Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Kimya','Sulu Çözelti Dengeleri','islenmedi')");
                //11.SINIF F ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Atomun Kuantum Modeli','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Periyodik Sistem ve Elektron Dizilimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Periyodik Özellikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Elementleri Tanıyalım','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Yükseltgenme Basamakları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Gazların Özellikleri ve Gaz Yasaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','İdeal Gaz Yasası','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Gazlarda Kinetik Teori','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Gaz Karışımları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Gerçek Gazlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Çözücü Çözünen Etkileşimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Derişim Birimleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Koligatif Özellikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Çözünürlük','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Çözünürlüğe Etki Eden Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Tepkimelerde Isı Değişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Oluşum Entalpisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Bağ Enerjileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Tepkime Isılarının Toplanabilirliği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Tepkime Hızları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Tepkime Hızını Etkileyen Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Kimyasal Denge','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Dengeyi Etkileyen Faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Kimya','Sulu Çözelti Dengeleri','islenmedi')");

                //11.SINIF A ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Biyoloji','Denetleyici ve Düzeleyici Sistem, Duyu Organları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Biyoloji','Destek ve Hareket Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Biyoloji','Sindirim Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Biyoloji','Dolaşım Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Biyoloji','Solunum Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Biyoloji','Üriner Sistem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Biyoloji','Üreme Sistemi ve Embriyonik Gelişim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Biyoloji','Komünite Ekolojisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Biyoloji','Popülasyon Ekolojisi','islenmedi')");

                //11.SINIF B ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Biyoloji','Denetleyici ve Düzeleyici Sistem, Duyu Organları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Biyoloji','Destek ve Hareket Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Biyoloji','Sindirim Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Biyoloji','Dolaşım Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Biyoloji','Solunum Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Biyoloji','Üriner Sistem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Biyoloji','Üreme Sistemi ve Embriyonik Gelişim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Biyoloji','Komünite Ekolojisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Biyoloji','Popülasyon Ekolojisi','islenmedi')");
                //11.SINIF C ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Biyoloji','Denetleyici ve Düzeleyici Sistem, Duyu Organları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Biyoloji','Destek ve Hareket Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Biyoloji','Sindirim Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Biyoloji','Dolaşım Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Biyoloji','Solunum Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Biyoloji','Üriner Sistem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Biyoloji','Üreme Sistemi ve Embriyonik Gelişim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Biyoloji','Komünite Ekolojisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Biyoloji','Popülasyon Ekolojisi','islenmedi')");
                //11.SINIF D ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Biyoloji','Denetleyici ve Düzeleyici Sistem, Duyu Organları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Biyoloji','Destek ve Hareket Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Biyoloji','Sindirim Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Biyoloji','Dolaşım Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Biyoloji','Solunum Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Biyoloji','Üriner Sistem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Biyoloji','Üreme Sistemi ve Embriyonik Gelişim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Biyoloji','Komünite Ekolojisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Biyoloji','Popülasyon Ekolojisi','islenmedi')");
                //11.SINIF E ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Biyoloji','Denetleyici ve Düzeleyici Sistem, Duyu Organları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Biyoloji','Destek ve Hareket Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Biyoloji','Sindirim Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Biyoloji','Dolaşım Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Biyoloji','Solunum Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Biyoloji','Üriner Sistem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Biyoloji','Üreme Sistemi ve Embriyonik Gelişim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Biyoloji','Komünite Ekolojisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Biyoloji','Popülasyon Ekolojisi','islenmedi')");
                //11.SINIF F ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Biyoloji','Denetleyici ve Düzeleyici Sistem, Duyu Organları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Biyoloji','Destek ve Hareket Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Biyoloji','Sindirim Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Biyoloji','Dolaşım Sistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Biyoloji','Solunum Sistemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Biyoloji','Üriner Sistem','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Biyoloji','Üreme Sistemi ve Embriyonik Gelişim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Biyoloji','Komünite Ekolojisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Biyoloji','Popülasyon Ekolojisi','islenmedi')");



                //11.SINIF A ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Tarih','DEĞİŞEN DÜNYA DENGELERİ KARŞISINDA OSMANLI SİYASETİ (1595-1774)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Tarih','DEĞİŞİM ÇAĞINDA AVRUPA VE OSMANLI','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Tarih','ULUSLARARASI İLİŞKİLERDE DENGE STRATEJİSİ (1774-1914)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Tarih','DEVRİMLER ÇAĞINDA DEĞİŞEN DEVLET-TOPLUM İLİŞKİLERİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Tarih','SERMAYE VE EMEK','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Tarih','XIX VE XX. YÜZYILDA DEĞİŞEN GÜNDELİK HAYAT','islenmedi')");

                //11.SINIF B ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Tarih','DEĞİŞEN DÜNYA DENGELERİ KARŞISINDA OSMANLI SİYASETİ (1595-1774)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Tarih','DEĞİŞİM ÇAĞINDA AVRUPA VE OSMANLI','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Tarih','ULUSLARARASI İLİŞKİLERDE DENGE STRATEJİSİ (1774-1914)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Tarih','DEVRİMLER ÇAĞINDA DEĞİŞEN DEVLET-TOPLUM İLİŞKİLERİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Tarih','SERMAYE VE EMEK','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Tarih','XIX VE XX. YÜZYILDA DEĞİŞEN GÜNDELİK HAYAT','islenmedi')");
                //11.SINIF C ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Tarih','DEĞİŞEN DÜNYA DENGELERİ KARŞISINDA OSMANLI SİYASETİ (1595-1774)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Tarih','DEĞİŞİM ÇAĞINDA AVRUPA VE OSMANLI','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Tarih','ULUSLARARASI İLİŞKİLERDE DENGE STRATEJİSİ (1774-1914)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Tarih','DEVRİMLER ÇAĞINDA DEĞİŞEN DEVLET-TOPLUM İLİŞKİLERİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Tarih','SERMAYE VE EMEK','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Tarih','XIX VE XX. YÜZYILDA DEĞİŞEN GÜNDELİK HAYAT','islenmedi')");
                //11.SINIF D ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Tarih','DEĞİŞEN DÜNYA DENGELERİ KARŞISINDA OSMANLI SİYASETİ (1595-1774)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Tarih','DEĞİŞİM ÇAĞINDA AVRUPA VE OSMANLI','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Tarih','ULUSLARARASI İLİŞKİLERDE DENGE STRATEJİSİ (1774-1914)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Tarih','DEVRİMLER ÇAĞINDA DEĞİŞEN DEVLET-TOPLUM İLİŞKİLERİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Tarih','SERMAYE VE EMEK','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Tarih','XIX VE XX. YÜZYILDA DEĞİŞEN GÜNDELİK HAYAT','islenmedi')");
                //11.SINIF E ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Tarih','DEĞİŞEN DÜNYA DENGELERİ KARŞISINDA OSMANLI SİYASETİ (1595-1774)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Tarih','DEĞİŞİM ÇAĞINDA AVRUPA VE OSMANLI','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Tarih','ULUSLARARASI İLİŞKİLERDE DENGE STRATEJİSİ (1774-1914)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Tarih','DEVRİMLER ÇAĞINDA DEĞİŞEN DEVLET-TOPLUM İLİŞKİLERİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Tarih','SERMAYE VE EMEK','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Tarih','XIX VE XX. YÜZYILDA DEĞİŞEN GÜNDELİK HAYAT','islenmedi')");
                //11.SINIF F ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Tarih','DEĞİŞEN DÜNYA DENGELERİ KARŞISINDA OSMANLI SİYASETİ (1595-1774)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Tarih','DEĞİŞİM ÇAĞINDA AVRUPA VE OSMANLI','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Tarih','ULUSLARARASI İLİŞKİLERDE DENGE STRATEJİSİ (1774-1914)','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Tarih','DEVRİMLER ÇAĞINDA DEĞİŞEN DEVLET-TOPLUM İLİŞKİLERİ','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Tarih','SERMAYE VE EMEK','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Tarih','XIX VE XX. YÜZYILDA DEĞİŞEN GÜNDELİK HAYAT','islenmedi')");



                //11.SINIF A ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Biyoçeşitliliğin oluşumunda ve azalmasında etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Ekosistemi oluşturan unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Madde döngüleri ve enerji akışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Su ekosistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Ülkelerin farklı dönemlerine ait nüfus politikaları ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Türkiye’nin uyguladığı nüfus politikaları ve gerekçeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Türkiye’nin nüfus projeksiyonlarına bağlı oluşabilecek senaryolar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Şehirlerin fonksiyonel özellikleri ile küresel ve bölgesel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Türkiye’de fonksiyonel özelliklerine göre şehirler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Türkiye’deki kır yerleşme tipleri; üretim, dağıtım ve tüketimi etkileyen doğal ve beşerî unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Üretim, dağıtım ve tüketim sektörlerinin ekonomiye etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Dünyada ve Türkiye’de doğal kaynak- ekonomi ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Türkiye’de uygulanan ekonomi politikaları ve ekonominin sektörel dağılımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Türkiye’de tarım sektörünün özellikleri ve ekonomideki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Türkiye’nin madenleri ve enerji kaynakları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Türkiye’de sanayi sektörünün özellikleri ve ekonomideki konuları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Dünya üzerinde ilk kültür merkezleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Farklı kültürel bölgelerin yeryüzünde yayılışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Türk kültürünün yayılış alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Türkiye’nin tarih boyunca medeniyetler merkezi olmasının nedenleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Ülkeler ve bölgeler arasındaki ticaretle ham madde, üretim ve pazar alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Ülkeler arası etkileşimde turizm faaliyetlerinin rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Sanayileşmiş bir ülkenin sanayileşme süreci','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Farklı gelişmişlik düzeylerine sahip ülkelerde tarım-ekonomi ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Bölgesel ve küresel ölçekte örgütler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Oluşum şekillerine göre çevre sorunları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Maden ve enerji kaynakları kullanımının çevre üzerindeki etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Yenilenemeyen kaynakların kullanımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Farklı gelişmişliğe sahip ülkelerdeki doğal kaynak kullanımının çevresel sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Arazi kullanımına ilişkin farklı uygulamaların çevre üzerindeki etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Çevre sorunlarının oluşum, yayılma süreçleri ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Coğrafya','Doğal kaynakların sürdürülebilir kullanımı','islenmedi')");


                //11.SINIF B ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Biyoçeşitliliğin oluşumunda ve azalmasında etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Ekosistemi oluşturan unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Madde döngüleri ve enerji akışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Su ekosistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Ülkelerin farklı dönemlerine ait nüfus politikaları ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Türkiye’nin uyguladığı nüfus politikaları ve gerekçeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Türkiye’nin nüfus projeksiyonlarına bağlı oluşabilecek senaryolar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Şehirlerin fonksiyonel özellikleri ile küresel ve bölgesel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Türkiye’de fonksiyonel özelliklerine göre şehirler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Türkiye’deki kır yerleşme tipleri; üretim, dağıtım ve tüketimi etkileyen doğal ve beşerî unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Üretim, dağıtım ve tüketim sektörlerinin ekonomiye etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Dünyada ve Türkiye’de doğal kaynak- ekonomi ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Türkiye’de uygulanan ekonomi politikaları ve ekonominin sektörel dağılımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Türkiye’de tarım sektörünün özellikleri ve ekonomideki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Türkiye’nin madenleri ve enerji kaynakları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Türkiye’de sanayi sektörünün özellikleri ve ekonomideki konuları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Dünya üzerinde ilk kültür merkezleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Farklı kültürel bölgelerin yeryüzünde yayılışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Türk kültürünün yayılış alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Türkiye’nin tarih boyunca medeniyetler merkezi olmasının nedenleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Ülkeler ve bölgeler arasındaki ticaretle ham madde, üretim ve pazar alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Ülkeler arası etkileşimde turizm faaliyetlerinin rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Sanayileşmiş bir ülkenin sanayileşme süreci','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Farklı gelişmişlik düzeylerine sahip ülkelerde tarım-ekonomi ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Bölgesel ve küresel ölçekte örgütler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Oluşum şekillerine göre çevre sorunları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Maden ve enerji kaynakları kullanımının çevre üzerindeki etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Yenilenemeyen kaynakların kullanımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Farklı gelişmişliğe sahip ülkelerdeki doğal kaynak kullanımının çevresel sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Arazi kullanımına ilişkin farklı uygulamaların çevre üzerindeki etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Çevre sorunlarının oluşum, yayılma süreçleri ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Coğrafya','Doğal kaynakların sürdürülebilir kullanımı','islenmedi')");
                //11.SINIF C ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Biyoçeşitliliğin oluşumunda ve azalmasında etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Ekosistemi oluşturan unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Madde döngüleri ve enerji akışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Su ekosistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Ülkelerin farklı dönemlerine ait nüfus politikaları ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Türkiye’nin uyguladığı nüfus politikaları ve gerekçeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Türkiye’nin nüfus projeksiyonlarına bağlı oluşabilecek senaryolar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Şehirlerin fonksiyonel özellikleri ile küresel ve bölgesel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Türkiye’de fonksiyonel özelliklerine göre şehirler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Türkiye’deki kır yerleşme tipleri; üretim, dağıtım ve tüketimi etkileyen doğal ve beşerî unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Üretim, dağıtım ve tüketim sektörlerinin ekonomiye etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Dünyada ve Türkiye’de doğal kaynak- ekonomi ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Türkiye’de uygulanan ekonomi politikaları ve ekonominin sektörel dağılımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Türkiye’de tarım sektörünün özellikleri ve ekonomideki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Türkiye’nin madenleri ve enerji kaynakları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Türkiye’de sanayi sektörünün özellikleri ve ekonomideki konuları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Dünya üzerinde ilk kültür merkezleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Farklı kültürel bölgelerin yeryüzünde yayılışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Türk kültürünün yayılış alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Türkiye’nin tarih boyunca medeniyetler merkezi olmasının nedenleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Ülkeler ve bölgeler arasındaki ticaretle ham madde, üretim ve pazar alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Ülkeler arası etkileşimde turizm faaliyetlerinin rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Sanayileşmiş bir ülkenin sanayileşme süreci','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Farklı gelişmişlik düzeylerine sahip ülkelerde tarım-ekonomi ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Bölgesel ve küresel ölçekte örgütler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Oluşum şekillerine göre çevre sorunları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Maden ve enerji kaynakları kullanımının çevre üzerindeki etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Yenilenemeyen kaynakların kullanımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Farklı gelişmişliğe sahip ülkelerdeki doğal kaynak kullanımının çevresel sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Arazi kullanımına ilişkin farklı uygulamaların çevre üzerindeki etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Çevre sorunlarının oluşum, yayılma süreçleri ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Coğrafya','Doğal kaynakların sürdürülebilir kullanımı','islenmedi')");
                //11.SINIF D ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Biyoçeşitliliğin oluşumunda ve azalmasında etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Ekosistemi oluşturan unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Madde döngüleri ve enerji akışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Su ekosistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Ülkelerin farklı dönemlerine ait nüfus politikaları ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Türkiye’nin uyguladığı nüfus politikaları ve gerekçeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Türkiye’nin nüfus projeksiyonlarına bağlı oluşabilecek senaryolar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Şehirlerin fonksiyonel özellikleri ile küresel ve bölgesel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Türkiye’de fonksiyonel özelliklerine göre şehirler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Türkiye’deki kır yerleşme tipleri; üretim, dağıtım ve tüketimi etkileyen doğal ve beşerî unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Üretim, dağıtım ve tüketim sektörlerinin ekonomiye etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Dünyada ve Türkiye’de doğal kaynak- ekonomi ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Türkiye’de uygulanan ekonomi politikaları ve ekonominin sektörel dağılımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Türkiye’de tarım sektörünün özellikleri ve ekonomideki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Türkiye’nin madenleri ve enerji kaynakları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Türkiye’de sanayi sektörünün özellikleri ve ekonomideki konuları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Dünya üzerinde ilk kültür merkezleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Farklı kültürel bölgelerin yeryüzünde yayılışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Türk kültürünün yayılış alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Türkiye’nin tarih boyunca medeniyetler merkezi olmasının nedenleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Ülkeler ve bölgeler arasındaki ticaretle ham madde, üretim ve pazar alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Ülkeler arası etkileşimde turizm faaliyetlerinin rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Sanayileşmiş bir ülkenin sanayileşme süreci','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Farklı gelişmişlik düzeylerine sahip ülkelerde tarım-ekonomi ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Bölgesel ve küresel ölçekte örgütler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Oluşum şekillerine göre çevre sorunları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Maden ve enerji kaynakları kullanımının çevre üzerindeki etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Yenilenemeyen kaynakların kullanımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Farklı gelişmişliğe sahip ülkelerdeki doğal kaynak kullanımının çevresel sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Arazi kullanımına ilişkin farklı uygulamaların çevre üzerindeki etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Çevre sorunlarının oluşum, yayılma süreçleri ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Coğrafya','Doğal kaynakların sürdürülebilir kullanımı','islenmedi')");

                //11.SINIF E ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Biyoçeşitliliğin oluşumunda ve azalmasında etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Ekosistemi oluşturan unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Madde döngüleri ve enerji akışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Su ekosistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Ülkelerin farklı dönemlerine ait nüfus politikaları ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Türkiye’nin uyguladığı nüfus politikaları ve gerekçeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Türkiye’nin nüfus projeksiyonlarına bağlı oluşabilecek senaryolar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Şehirlerin fonksiyonel özellikleri ile küresel ve bölgesel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Türkiye’de fonksiyonel özelliklerine göre şehirler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Türkiye’deki kır yerleşme tipleri; üretim, dağıtım ve tüketimi etkileyen doğal ve beşerî unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Üretim, dağıtım ve tüketim sektörlerinin ekonomiye etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Dünyada ve Türkiye’de doğal kaynak- ekonomi ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Türkiye’de uygulanan ekonomi politikaları ve ekonominin sektörel dağılımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Türkiye’de tarım sektörünün özellikleri ve ekonomideki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Türkiye’nin madenleri ve enerji kaynakları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Türkiye’de sanayi sektörünün özellikleri ve ekonomideki konuları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Dünya üzerinde ilk kültür merkezleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Farklı kültürel bölgelerin yeryüzünde yayılışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Türk kültürünün yayılış alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Türkiye’nin tarih boyunca medeniyetler merkezi olmasının nedenleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Ülkeler ve bölgeler arasındaki ticaretle ham madde, üretim ve pazar alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Ülkeler arası etkileşimde turizm faaliyetlerinin rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Sanayileşmiş bir ülkenin sanayileşme süreci','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Farklı gelişmişlik düzeylerine sahip ülkelerde tarım-ekonomi ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Bölgesel ve küresel ölçekte örgütler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Oluşum şekillerine göre çevre sorunları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Maden ve enerji kaynakları kullanımının çevre üzerindeki etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Yenilenemeyen kaynakların kullanımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Farklı gelişmişliğe sahip ülkelerdeki doğal kaynak kullanımının çevresel sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Arazi kullanımına ilişkin farklı uygulamaların çevre üzerindeki etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Çevre sorunlarının oluşum, yayılma süreçleri ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Coğrafya','Doğal kaynakların sürdürülebilir kullanımı','islenmedi')");
                //11.SINIF F ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Biyoçeşitliliğin oluşumunda ve azalmasında etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Ekosistemi oluşturan unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Madde döngüleri ve enerji akışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Su ekosistemleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Ülkelerin farklı dönemlerine ait nüfus politikaları ve sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Türkiye’nin uyguladığı nüfus politikaları ve gerekçeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Türkiye’nin nüfus projeksiyonlarına bağlı oluşabilecek senaryolar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Şehirlerin fonksiyonel özellikleri ile küresel ve bölgesel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Türkiye’de fonksiyonel özelliklerine göre şehirler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Türkiye’deki kır yerleşme tipleri; üretim, dağıtım ve tüketimi etkileyen doğal ve beşerî unsurlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Üretim, dağıtım ve tüketim sektörlerinin ekonomiye etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Dünyada ve Türkiye’de doğal kaynak- ekonomi ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Türkiye’de uygulanan ekonomi politikaları ve ekonominin sektörel dağılımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Türkiye’de tarım sektörünün özellikleri ve ekonomideki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Türkiye’nin madenleri ve enerji kaynakları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Türkiye’de sanayi sektörünün özellikleri ve ekonomideki konuları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Dünya üzerinde ilk kültür merkezleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Farklı kültürel bölgelerin yeryüzünde yayılışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Türk kültürünün yayılış alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Türkiye’nin tarih boyunca medeniyetler merkezi olmasının nedenleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Ülkeler ve bölgeler arasındaki ticaretle ham madde, üretim ve pazar alanları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Ülkeler arası etkileşimde turizm faaliyetlerinin rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Sanayileşmiş bir ülkenin sanayileşme süreci','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Farklı gelişmişlik düzeylerine sahip ülkelerde tarım-ekonomi ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Bölgesel ve küresel ölçekte örgütler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Oluşum şekillerine göre çevre sorunları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Maden ve enerji kaynakları kullanımının çevre üzerindeki etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Yenilenemeyen kaynakların kullanımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Farklı gelişmişliğe sahip ülkelerdeki doğal kaynak kullanımının çevresel sonuçları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Arazi kullanımına ilişkin farklı uygulamaların çevre üzerindeki etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Çevre sorunlarının oluşum, yayılma süreçleri ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Coğrafya','Doğal kaynakların sürdürülebilir kullanımı','islenmedi')");
                //11.SINIF A ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Edebiyat','Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Edebiyat','İmla ve Noktalama Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Edebiyat','Cümlelerin Ögeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Edebiyat','Makale','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Edebiyat','Sohbet ve Fıkra','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Edebiyat','Anlatım Bozuklukları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Edebiyat','Eleştiri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Edebiyat','Mülakat/Röportaj','islenmedi')");

                //11.SINIF B ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Edebiyat','Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Edebiyat','İmla ve Noktalama Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Edebiyat','Cümlelerin Ögeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Edebiyat','Makale','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Edebiyat','Sohbet ve Fıkra','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Edebiyat','Anlatım Bozuklukları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Edebiyat','Eleştiri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Edebiyat','Mülakat/Röportaj','islenmedi')");
                //11.SINIF C ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Edebiyat','Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Edebiyat','İmla ve Noktalama Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Edebiyat','Cümlelerin Ögeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Edebiyat','Makale','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Edebiyat','Sohbet ve Fıkra','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Edebiyat','Anlatım Bozuklukları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Edebiyat','Eleştiri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Edebiyat','Mülakat/Röportaj','islenmedi')");
                //11.SINIF D ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Edebiyat','Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Edebiyat','İmla ve Noktalama Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Edebiyat','Cümlelerin Ögeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Edebiyat','Makale','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Edebiyat','Sohbet ve Fıkra','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Edebiyat','Anlatım Bozuklukları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Edebiyat','Eleştiri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Edebiyat','Mülakat/Röportaj','islenmedi')");
                //11.SINIF E ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Edebiyat','Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Edebiyat','İmla ve Noktalama Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Edebiyat','Cümlelerin Ögeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Edebiyat','Makale','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Edebiyat','Sohbet ve Fıkra','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Edebiyat','Anlatım Bozuklukları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Edebiyat','Eleştiri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Edebiyat','Mülakat/Röportaj','islenmedi')");
                //11.SINIF F ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Edebiyat','Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Edebiyat','İmla ve Noktalama Kuralları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Edebiyat','Cümlelerin Ögeleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Edebiyat','Makale','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Edebiyat','Sohbet ve Fıkra','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Edebiyat','Anlatım Bozuklukları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Edebiyat','Eleştiri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Edebiyat','Mülakat/Röportaj','islenmedi')");



                //11.SINIF A ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','Felsefenin Ortaya Çıkışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','MÖ 6. Yüzyıl- MS 2. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','MÖ 6. Yüzyıl- MS 2. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Felsefesini Hazırlayan Düşünce Ortamı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','15. Yüzyıl – 17. Yüzyıl Felsefesi’ni Hazırlayan Düşünce Ortamı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','15. Yüzyıl – 17. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','15. Yüzyıl-17. Yüzyıl Felsefesinin 18. Yüzyıl -19. Yüzyıl Felsefesi Üzerindeki Etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','18. Yüzyıl -19. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','18. Yüzyıl -19. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','18. Yüzyıl -19. Yüzyıl Felsefesinin 20. Yüzyıl Felsefi Akımları Üzerindeki Etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','20. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','20. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','A','Felsefe','20. Yüzyıl Felsefesi Örnek Düşünce ve Argümanlar','islenmedi')");


                //11.SINIF B ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','Felsefenin Ortaya Çıkışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','MÖ 6. Yüzyıl- MS 2. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','MÖ 6. Yüzyıl- MS 2. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Felsefesini Hazırlayan Düşünce Ortamı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','15. Yüzyıl – 17. Yüzyıl Felsefesi’ni Hazırlayan Düşünce Ortamı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','15. Yüzyıl – 17. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','15. Yüzyıl-17. Yüzyıl Felsefesinin 18. Yüzyıl -19. Yüzyıl Felsefesi Üzerindeki Etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','18. Yüzyıl -19. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','18. Yüzyıl -19. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','18. Yüzyıl -19. Yüzyıl Felsefesinin 20. Yüzyıl Felsefi Akımları Üzerindeki Etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','20. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','20. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','B','Felsefe','20. Yüzyıl Felsefesi Örnek Düşünce ve Argümanlar','islenmedi')");
                //11.SINIF C ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','Felsefenin Ortaya Çıkışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','MÖ 6. Yüzyıl- MS 2. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','MÖ 6. Yüzyıl- MS 2. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Felsefesini Hazırlayan Düşünce Ortamı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','15. Yüzyıl – 17. Yüzyıl Felsefesi’ni Hazırlayan Düşünce Ortamı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','15. Yüzyıl – 17. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','15. Yüzyıl-17. Yüzyıl Felsefesinin 18. Yüzyıl -19. Yüzyıl Felsefesi Üzerindeki Etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','18. Yüzyıl -19. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','18. Yüzyıl -19. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','18. Yüzyıl -19. Yüzyıl Felsefesinin 20. Yüzyıl Felsefi Akımları Üzerindeki Etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','20. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','20. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','C','Felsefe','20. Yüzyıl Felsefesi Örnek Düşünce ve Argümanlar','islenmedi')");
                //11.SINIF D ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','Felsefenin Ortaya Çıkışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','MÖ 6. Yüzyıl- MS 2. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','MÖ 6. Yüzyıl- MS 2. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Felsefesini Hazırlayan Düşünce Ortamı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','15. Yüzyıl – 17. Yüzyıl Felsefesi’ni Hazırlayan Düşünce Ortamı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','15. Yüzyıl – 17. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','15. Yüzyıl-17. Yüzyıl Felsefesinin 18. Yüzyıl -19. Yüzyıl Felsefesi Üzerindeki Etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','18. Yüzyıl -19. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','18. Yüzyıl -19. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','18. Yüzyıl -19. Yüzyıl Felsefesinin 20. Yüzyıl Felsefi Akımları Üzerindeki Etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','20. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','20. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','D','Felsefe','20. Yüzyıl Felsefesi Örnek Düşünce ve Argümanlar','islenmedi')");
                //11.SINIF E ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','Felsefenin Ortaya Çıkışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','MÖ 6. Yüzyıl- MS 2. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','MÖ 6. Yüzyıl- MS 2. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Felsefesini Hazırlayan Düşünce Ortamı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','15. Yüzyıl – 17. Yüzyıl Felsefesi’ni Hazırlayan Düşünce Ortamı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','15. Yüzyıl – 17. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','15. Yüzyıl-17. Yüzyıl Felsefesinin 18. Yüzyıl -19. Yüzyıl Felsefesi Üzerindeki Etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','18. Yüzyıl -19. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','18. Yüzyıl -19. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','18. Yüzyıl -19. Yüzyıl Felsefesinin 20. Yüzyıl Felsefi Akımları Üzerindeki Etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','20. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','20. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','E','Felsefe','20. Yüzyıl Felsefesi Örnek Düşünce ve Argümanlar','islenmedi')");
                //11.SINIF F ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','Felsefenin Ortaya Çıkışı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','MÖ 6. Yüzyıl- MS 2. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','MÖ 6. Yüzyıl- MS 2. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Felsefesini Hazırlayan Düşünce Ortamı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','MS 2. Yüzyıl – MS 15. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','15. Yüzyıl – 17. Yüzyıl Felsefesi’ni Hazırlayan Düşünce Ortamı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','15. Yüzyıl – 17. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','15. Yüzyıl-17. Yüzyıl Felsefesinin 18. Yüzyıl -19. Yüzyıl Felsefesi Üzerindeki Etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','18. Yüzyıl -19. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','18. Yüzyıl -19. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','18. Yüzyıl -19. Yüzyıl Felsefesinin 20. Yüzyıl Felsefi Akımları Üzerindeki Etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','20. Yüzyıl Felsefesinin Karakteristik Özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','20. Yüzyıl Filozoflarının Felsefi Görüşleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('11','F','Felsefe','20. Yüzyıl Felsefesi Örnek Düşünce ve Argümanlar','islenmedi')");

//12.sınıf a şubesi matematik
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Üstel Fonksiyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Logaritma Fonksiyonu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Üstel, Logaritmik Denklemler ve Eşitsizlikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Gerçek Sayı Dizileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Toplam-Fark ve İki Kat Açı Formülleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Trigonometrik Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Analitik Düzlemde Temel Dönüşümler\n','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Limit ve Süreklilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Anlık Değişim Oranı ve Türev','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Türevin Uygulamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Belirsiz İntegral','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Belirli İntegral ve Uygulamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Üslü ve Köklü İfadeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Bilinçli Tüketici Aritmetiği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Veri Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Çevre, Alan ve Hacim Ölçme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Matematik','Küre ve Silindir','islenmedi')");

                //12.SINIF B ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Üstel Fonksiyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Logaritma Fonksiyonu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Üstel, Logaritmik Denklemler ve Eşitsizlikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Gerçek Sayı Dizileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Toplam-Fark ve İki Kat Açı Formülleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Trigonometrik Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Analitik Düzlemde Temel Dönüşümler\n','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Limit ve Süreklilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Anlık Değişim Oranı ve Türev','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Türevin Uygulamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Belirsiz İntegral','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Belirli İntegral ve Uygulamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Üslü ve Köklü İfadeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Bilinçli Tüketici Aritmetiği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Veri Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Çevre, Alan ve Hacim Ölçme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Matematik','Küre ve Silindir','islenmedi')");
                //12.SINIF C ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Üstel Fonksiyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Logaritma Fonksiyonu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Üstel, Logaritmik Denklemler ve Eşitsizlikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Gerçek Sayı Dizileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Toplam-Fark ve İki Kat Açı Formülleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Trigonometrik Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Analitik Düzlemde Temel Dönüşümler\n','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Limit ve Süreklilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Anlık Değişim Oranı ve Türev','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Türevin Uygulamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Belirsiz İntegral','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Belirli İntegral ve Uygulamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Üslü ve Köklü İfadeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Bilinçli Tüketici Aritmetiği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Veri Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Çevre, Alan ve Hacim Ölçme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Matematik','Küre ve Silindir','islenmedi')");
                //12.SINIF D ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Üstel Fonksiyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Logaritma Fonksiyonu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Üstel, Logaritmik Denklemler ve Eşitsizlikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Gerçek Sayı Dizileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Toplam-Fark ve İki Kat Açı Formülleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Trigonometrik Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Analitik Düzlemde Temel Dönüşümler\n','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Limit ve Süreklilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Anlık Değişim Oranı ve Türev','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Türevin Uygulamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Belirsiz İntegral','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Belirli İntegral ve Uygulamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Üslü ve Köklü İfadeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Bilinçli Tüketici Aritmetiği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Veri Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Çevre, Alan ve Hacim Ölçme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Matematik','Küre ve Silindir','islenmedi')");
                //12.SINIF E ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Üstel Fonksiyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Logaritma Fonksiyonu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Üstel, Logaritmik Denklemler ve Eşitsizlikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Gerçek Sayı Dizileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Toplam-Fark ve İki Kat Açı Formülleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Trigonometrik Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Analitik Düzlemde Temel Dönüşümler\n','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Limit ve Süreklilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Anlık Değişim Oranı ve Türev','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Türevin Uygulamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Belirsiz İntegral','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Belirli İntegral ve Uygulamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Üslü ve Köklü İfadeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Bilinçli Tüketici Aritmetiği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Veri Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Çevre, Alan ve Hacim Ölçme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Matematik','Küre ve Silindir','islenmedi')");
                //12.SINIF F ŞUBESİ MATEMATİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Üstel Fonksiyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Logaritma Fonksiyonu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Üstel, Logaritmik Denklemler ve Eşitsizlikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Gerçek Sayı Dizileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Toplam-Fark ve İki Kat Açı Formülleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Trigonometrik Denklemler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Analitik Düzlemde Temel Dönüşümler\n','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Limit ve Süreklilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Anlık Değişim Oranı ve Türev','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Türevin Uygulamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Belirsiz İntegral','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Belirli İntegral ve Uygulamaları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Üslü ve Köklü İfadeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Bilinçli Tüketici Aritmetiği','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Veri Analizi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Çevre, Alan ve Hacim Ölçme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Matematik','Küre ve Silindir','islenmedi')");


                //12.SINIF A ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Düzgün Çembersel Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Dönerek Öteleme Hareketi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Açısal Momentum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Kütle Çekim Kuvveti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Kepler Kanunları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Basit Harmonik Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Dalgalarda Kırınım, Girişim ve Doppler Olayı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Elektromanyetik Dalgalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Atom Kavramının Tarihsel Gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Büyük Patlama ve Evrenin Oluşumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Radyoaktivite','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Özel Görelilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Kuantum Fiziğine Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Fotoelektrik Olayı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Compton Saçılması ve De Broglie Dalga Boyu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Görüntüleme Teknolojileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Yarı İletken Teknolojisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Süper İletkenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Nanoteknoloji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Fizik','Laser Işınları','islenmedi')");

                //12.SINIF B ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Düzgün Çembersel Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Dönerek Öteleme Hareketi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Açısal Momentum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Kütle Çekim Kuvveti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Kepler Kanunları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Basit Harmonik Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Dalgalarda Kırınım, Girişim ve Doppler Olayı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Elektromanyetik Dalgalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Atom Kavramının Tarihsel Gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Büyük Patlama ve Evrenin Oluşumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Radyoaktivite','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Özel Görelilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Kuantum Fiziğine Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Fotoelektrik Olayı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Compton Saçılması ve De Broglie Dalga Boyu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Görüntüleme Teknolojileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Yarı İletken Teknolojisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Süper İletkenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Nanoteknoloji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Fizik','Laser Işınları','islenmedi')");
                //12.SINIF C ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Düzgün Çembersel Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Dönerek Öteleme Hareketi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Açısal Momentum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Kütle Çekim Kuvveti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Kepler Kanunları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Basit Harmonik Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Dalgalarda Kırınım, Girişim ve Doppler Olayı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Elektromanyetik Dalgalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Atom Kavramının Tarihsel Gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Büyük Patlama ve Evrenin Oluşumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Radyoaktivite','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Özel Görelilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Kuantum Fiziğine Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Fotoelektrik Olayı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Compton Saçılması ve De Broglie Dalga Boyu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Görüntüleme Teknolojileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Yarı İletken Teknolojisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Süper İletkenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Nanoteknoloji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Fizik','Laser Işınları','islenmedi')");
                //12.SINIF D ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Düzgün Çembersel Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Dönerek Öteleme Hareketi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Açısal Momentum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Kütle Çekim Kuvveti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Kepler Kanunları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Basit Harmonik Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Dalgalarda Kırınım, Girişim ve Doppler Olayı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Elektromanyetik Dalgalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Atom Kavramının Tarihsel Gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Büyük Patlama ve Evrenin Oluşumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Radyoaktivite','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Özel Görelilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Kuantum Fiziğine Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Fotoelektrik Olayı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Compton Saçılması ve De Broglie Dalga Boyu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Görüntüleme Teknolojileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Yarı İletken Teknolojisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Süper İletkenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Nanoteknoloji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Fizik','Laser Işınları','islenmedi')");
                //12.SINIF E ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Düzgün Çembersel Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Dönerek Öteleme Hareketi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Açısal Momentum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Kütle Çekim Kuvveti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Kepler Kanunları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Basit Harmonik Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Dalgalarda Kırınım, Girişim ve Doppler Olayı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Elektromanyetik Dalgalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Atom Kavramının Tarihsel Gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Büyük Patlama ve Evrenin Oluşumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Radyoaktivite','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Özel Görelilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Kuantum Fiziğine Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Fotoelektrik Olayı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Compton Saçılması ve De Broglie Dalga Boyu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Görüntüleme Teknolojileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Yarı İletken Teknolojisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Süper İletkenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Nanoteknoloji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Fizik','Laser Işınları','islenmedi')");
                //12.SINIF F ŞUBESİ FİZİK
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Düzgün Çembersel Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Dönerek Öteleme Hareketi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Açısal Momentum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Kütle Çekim Kuvveti','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Kepler Kanunları','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Basit Harmonik Hareket','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Dalgalarda Kırınım, Girişim ve Doppler Olayı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Elektromanyetik Dalgalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Atom Kavramının Tarihsel Gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Büyük Patlama ve Evrenin Oluşumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Radyoaktivite','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Özel Görelilik','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Kuantum Fiziğine Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Fotoelektrik Olayı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Compton Saçılması ve De Broglie Dalga Boyu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Görüntüleme Teknolojileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Yarı İletken Teknolojisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Süper İletkenler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Nanoteknoloji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Fizik','Laser Işınları','islenmedi')");


                //12.SINIF A ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','İndirgenme-Yükseltgenme Tepkimelerinde Elektrik Akımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Elektrotlar ve Elektrokimyasal Hücreler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Elektrot Potansiyelleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Kimyasallardan Elektrik Üretimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Elektroliz','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Korozyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Anorganik ve Organik Bileşikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Basit Formül ve Molekül Formülü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Doğada Karbon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Lewis Formülleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Hibritleşme-Molekül Geometrileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Hidrokarbonlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Fonksiyonel Gruplar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Alkoller','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Eterler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Karbonil Bileşikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Karboksilik Asitler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Kimya','Esterler','islenmedi')");


                //12.SINIF B ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','İndirgenme-Yükseltgenme Tepkimelerinde Elektrik Akımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Elektrotlar ve Elektrokimyasal Hücreler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Elektrot Potansiyelleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Kimyasallardan Elektrik Üretimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Elektroliz','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Korozyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Anorganik ve Organik Bileşikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Basit Formül ve Molekül Formülü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Doğada Karbon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Lewis Formülleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Hibritleşme-Molekül Geometrileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Hidrokarbonlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Fonksiyonel Gruplar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Alkoller','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Eterler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Karbonil Bileşikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Karboksilik Asitler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Kimya','Esterler','islenmedi')");
                //12.SINIF C ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','İndirgenme-Yükseltgenme Tepkimelerinde Elektrik Akımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Elektrotlar ve Elektrokimyasal Hücreler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Elektrot Potansiyelleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Kimyasallardan Elektrik Üretimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Elektroliz','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Korozyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Anorganik ve Organik Bileşikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Basit Formül ve Molekül Formülü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Doğada Karbon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Lewis Formülleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Hibritleşme-Molekül Geometrileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Hidrokarbonlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Fonksiyonel Gruplar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Alkoller','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Eterler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Karbonil Bileşikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Karboksilik Asitler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Kimya','Esterler','islenmedi')");
                //12.SINIF D ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','İndirgenme-Yükseltgenme Tepkimelerinde Elektrik Akımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Elektrotlar ve Elektrokimyasal Hücreler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Elektrot Potansiyelleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Kimyasallardan Elektrik Üretimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Elektroliz','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Korozyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Anorganik ve Organik Bileşikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Basit Formül ve Molekül Formülü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Doğada Karbon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Lewis Formülleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Hibritleşme-Molekül Geometrileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Hidrokarbonlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Fonksiyonel Gruplar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Alkoller','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Eterler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Karbonil Bileşikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Karboksilik Asitler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Kimya','Esterler','islenmedi')");
                //12.SINIF E ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','İndirgenme-Yükseltgenme Tepkimelerinde Elektrik Akımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Elektrotlar ve Elektrokimyasal Hücreler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Elektrot Potansiyelleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Kimyasallardan Elektrik Üretimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Elektroliz','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Korozyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Anorganik ve Organik Bileşikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Basit Formül ve Molekül Formülü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Doğada Karbon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Lewis Formülleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Hibritleşme-Molekül Geometrileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Hidrokarbonlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Fonksiyonel Gruplar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Alkoller','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Eterler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Karbonil Bileşikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Karboksilik Asitler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Kimya','Esterler','islenmedi')");
                //12.SINIF F ŞUBESİ KİMYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','İndirgenme-Yükseltgenme Tepkimelerinde Elektrik Akımı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Elektrotlar ve Elektrokimyasal Hücreler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Elektrot Potansiyelleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Kimyasallardan Elektrik Üretimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Elektroliz','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Korozyon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Anorganik ve Organik Bileşikler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Basit Formül ve Molekül Formülü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Doğada Karbon','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Lewis Formülleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Hibritleşme-Molekül Geometrileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Hidrokarbonlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Fonksiyonel Gruplar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Alkoller','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Eterler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Karbonil Bileşikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Karboksilik Asitler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Kimya','Esterler','islenmedi')");




                //12.SINIF A ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Biyoloji','Nükleik Asitlerin Keşfi ve Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Biyoloji','Genetik Şifre ve Protein Sentezi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Biyoloji','Canlılık ve Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Biyoloji','Fotosentez','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Biyoloji','Kemosentez','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Biyoloji','Hücresel Solunum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Biyoloji','Bitkilerin Yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Biyoloji','Bitkilerde Madde Taşınması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Biyoloji','Bitkilerde Eşeyli Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Biyoloji','Canlılar ve Çevre','islenmedi')");

                //12.SINIF B ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Biyoloji','Nükleik Asitlerin Keşfi ve Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Biyoloji','Genetik Şifre ve Protein Sentezi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Biyoloji','Canlılık ve Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Biyoloji','Fotosentez','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Biyoloji','Kemosentez','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Biyoloji','Hücresel Solunum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Biyoloji','Bitkilerin Yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Biyoloji','Bitkilerde Madde Taşınması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Biyoloji','Bitkilerde Eşeyli Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Biyoloji','Canlılar ve Çevre','islenmedi')");
                //12.SINIF C ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Biyoloji','Nükleik Asitlerin Keşfi ve Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Biyoloji','Genetik Şifre ve Protein Sentezi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Biyoloji','Canlılık ve Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Biyoloji','Fotosentez','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Biyoloji','Kemosentez','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Biyoloji','Hücresel Solunum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Biyoloji','Bitkilerin Yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Biyoloji','Bitkilerde Madde Taşınması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Biyoloji','Bitkilerde Eşeyli Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Biyoloji','Canlılar ve Çevre','islenmedi')");
                //12.SINIF D ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Biyoloji','Nükleik Asitlerin Keşfi ve Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Biyoloji','Genetik Şifre ve Protein Sentezi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Biyoloji','Canlılık ve Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Biyoloji','Fotosentez','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Biyoloji','Kemosentez','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Biyoloji','Hücresel Solunum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Biyoloji','Bitkilerin Yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Biyoloji','Bitkilerde Madde Taşınması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Biyoloji','Bitkilerde Eşeyli Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Biyoloji','Canlılar ve Çevre','islenmedi')");
                //12.SINIF E ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Biyoloji','Nükleik Asitlerin Keşfi ve Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Biyoloji','Genetik Şifre ve Protein Sentezi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Biyoloji','Canlılık ve Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Biyoloji','Fotosentez','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Biyoloji','Kemosentez','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Biyoloji','Hücresel Solunum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Biyoloji','Bitkilerin Yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Biyoloji','Bitkilerde Madde Taşınması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Biyoloji','Bitkilerde Eşeyli Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Biyoloji','Canlılar ve Çevre','islenmedi')");
                //12.SINIF F ŞUBESİ BİYOLOJİ
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Biyoloji','Nükleik Asitlerin Keşfi ve Önemi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Biyoloji','Genetik Şifre ve Protein Sentezi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Biyoloji','Canlılık ve Enerji','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Biyoloji','Fotosentez','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Biyoloji','Kemosentez','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Biyoloji','Hücresel Solunum','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Biyoloji','Bitkilerin Yapısı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Biyoloji','Bitkilerde Madde Taşınması','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Biyoloji','Bitkilerde Eşeyli Üreme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Biyoloji','Canlılar ve Çevre','islenmedi')");


                //12.SINIF A ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Tarih','12.1 20. YÜZYIL BAŞLARINDA OSMANLI DEVLETİ VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Tarih','12.2 MİLLÎ MÜCADELE','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Tarih','12.3 ATATÜRKÇÜLÜK VE TÜRK İNKILABI','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Tarih','12.4 İKİ SAVAŞ ARASINDAKİ DÖNEMDE TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Tarih','12.5 II. DÜNYA SAVAŞI SÜRECİNDE TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Tarih','12.6 II. DÜNYA SAVAŞI SONRASINDA TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Tarih','12.7 TOPLUMSAL DEVRİM ÇAĞINDA DÜNYA VE TÜRKİYE','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Tarih','12.8 21. YÜZYILIN EŞİĞİNDE TÜRKİYE VE DÜNYA','islenmedi')");


                //12.SINIF B ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Tarih','12.1 20. YÜZYIL BAŞLARINDA OSMANLI DEVLETİ VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Tarih','12.2 MİLLÎ MÜCADELE','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Tarih','12.3 ATATÜRKÇÜLÜK VE TÜRK İNKILABI','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Tarih','12.4 İKİ SAVAŞ ARASINDAKİ DÖNEMDE TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Tarih','12.5 II. DÜNYA SAVAŞI SÜRECİNDE TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Tarih','12.6 II. DÜNYA SAVAŞI SONRASINDA TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Tarih','12.7 TOPLUMSAL DEVRİM ÇAĞINDA DÜNYA VE TÜRKİYE','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Tarih','12.8 21. YÜZYILIN EŞİĞİNDE TÜRKİYE VE DÜNYA','islenmedi')");
                //12.SINIF C ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Tarih','12.1 20. YÜZYIL BAŞLARINDA OSMANLI DEVLETİ VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Tarih','12.2 MİLLÎ MÜCADELE','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Tarih','12.3 ATATÜRKÇÜLÜK VE TÜRK İNKILABI','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Tarih','12.4 İKİ SAVAŞ ARASINDAKİ DÖNEMDE TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Tarih','12.5 II. DÜNYA SAVAŞI SÜRECİNDE TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Tarih','12.6 II. DÜNYA SAVAŞI SONRASINDA TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Tarih','12.7 TOPLUMSAL DEVRİM ÇAĞINDA DÜNYA VE TÜRKİYE','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Tarih','12.8 21. YÜZYILIN EŞİĞİNDE TÜRKİYE VE DÜNYA','islenmedi')");
                //12.SINIF D ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Tarih','12.1 20. YÜZYIL BAŞLARINDA OSMANLI DEVLETİ VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Tarih','12.2 MİLLÎ MÜCADELE','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Tarih','12.3 ATATÜRKÇÜLÜK VE TÜRK İNKILABI','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Tarih','12.4 İKİ SAVAŞ ARASINDAKİ DÖNEMDE TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Tarih','12.5 II. DÜNYA SAVAŞI SÜRECİNDE TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Tarih','12.6 II. DÜNYA SAVAŞI SONRASINDA TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Tarih','12.7 TOPLUMSAL DEVRİM ÇAĞINDA DÜNYA VE TÜRKİYE','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Tarih','12.8 21. YÜZYILIN EŞİĞİNDE TÜRKİYE VE DÜNYA','islenmedi')");
                //12.SINIF E ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Tarih','12.1 20. YÜZYIL BAŞLARINDA OSMANLI DEVLETİ VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Tarih','12.2 MİLLÎ MÜCADELE','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Tarih','12.3 ATATÜRKÇÜLÜK VE TÜRK İNKILABI','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Tarih','12.4 İKİ SAVAŞ ARASINDAKİ DÖNEMDE TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Tarih','12.5 II. DÜNYA SAVAŞI SÜRECİNDE TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Tarih','12.6 II. DÜNYA SAVAŞI SONRASINDA TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Tarih','12.7 TOPLUMSAL DEVRİM ÇAĞINDA DÜNYA VE TÜRKİYE','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Tarih','12.8 21. YÜZYILIN EŞİĞİNDE TÜRKİYE VE DÜNYA','islenmedi')");
                //12.SINIF F ŞUBESİ TARİH
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Tarih','12.1 20. YÜZYIL BAŞLARINDA OSMANLI DEVLETİ VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Tarih','12.2 MİLLÎ MÜCADELE','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Tarih','12.3 ATATÜRKÇÜLÜK VE TÜRK İNKILABI','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Tarih','12.4 İKİ SAVAŞ ARASINDAKİ DÖNEMDE TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Tarih','12.5 II. DÜNYA SAVAŞI SÜRECİNDE TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Tarih','12.6 II. DÜNYA SAVAŞI SONRASINDA TÜRKİYE VE DÜNYA','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Tarih','12.7 TOPLUMSAL DEVRİM ÇAĞINDA DÜNYA VE TÜRKİYE','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Tarih','12.8 21. YÜZYILIN EŞİĞİNDE TÜRKİYE VE DÜNYA','islenmedi')");



                //12.SINIF A ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Ekstrem doğa olayları ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Gelecekte doğal sistemlerde meydana gelebilecek değişimler ve bu değişimlerin canlı yaşamı üzerindeki etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Bir bölgedeki baskın ekonomik faaliyet türünün sosyal ve kültürel hayata olan etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Şehirleşme, göç ve sanayileşme olgularının birbirleriyle olan ilişkisi ve toplumsal etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Nüfus, yerleşme ve ekonomik faaliyetlerde gelecekte olabilecek değişimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Ülkemizdeki işlevsel bölgeler ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Türkiye’deki bölgesel kalkınma projeleri ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Hizmet sektörünün Türkiye ekonomisindeki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Ulaşım sisteminin gelişiminde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Ulaşım ağları ile yerleşme ve ekonomik faaliyetlerin','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Türkiye’deki ulaşım sisteminin gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Dünya ticaret merkezleri ve ağlarının küresel ekonomideki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Türkiye’deki ticaret merkezlerinin ticarete konu olan ürünlere ve akış yönlerine etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Tarihî ticaret yolları ve Türkiye’nin konumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Türkiye’nin dış ticareti ve dünya pazarlarındaki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Türkiye’deki doğal ve kültürel sembollerin mekânla ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Tarihsel süreçte kıtaların ve okyanusların konumsal önemindeki değişim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Ülkelerin konumunun bölgesel ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Tarihsel süreçte Türkiye’nin jeopolitik konumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Türkiye’nin içinde yer aldığı jeopolitik bölgeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Türk kültür bölgeleri ile ülkemiz arasındaki tarihi ve','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','kültürel bağlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Teknolojik gelişmelerin bölgeler ve ülkeler arası kültürel ve ekonomik etkileşimdeki rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Ülkelerin gelişmişlik seviyelerinin belirlenmesinde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Gelişmiş ve gelişmekte olan ülkelerin sosyal ve ekonomik özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Doğal kaynak potansiyelinin ülkelerin bölgesel ve küresel','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','ilişkilerine olan etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Dünyadaki başlıca enerji nakil hatlarının bölge ve ülkelere etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Doğal çevrenin sınırlılığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Farklı gelişmişlik düzeyine sahip ülkelerin çevre sorunlarının önlenmesine yönelik politika ve uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Çevresel örgüt ve anlaşmaların çevre yönetimi ve korunmasına etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Coğrafya','Ortak doğal ve kültürel mirasa yönelik tehditler','islenmedi')");


                //12.SINIF B ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Ekstrem doğa olayları ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Gelecekte doğal sistemlerde meydana gelebilecek değişimler ve bu değişimlerin canlı yaşamı üzerindeki etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Bir bölgedeki baskın ekonomik faaliyet türünün sosyal ve kültürel hayata olan etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Şehirleşme, göç ve sanayileşme olgularının birbirleriyle olan ilişkisi ve toplumsal etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Nüfus, yerleşme ve ekonomik faaliyetlerde gelecekte olabilecek değişimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Ülkemizdeki işlevsel bölgeler ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Türkiye’deki bölgesel kalkınma projeleri ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Hizmet sektörünün Türkiye ekonomisindeki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Ulaşım sisteminin gelişiminde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Ulaşım ağları ile yerleşme ve ekonomik faaliyetlerin','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Türkiye’deki ulaşım sisteminin gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Dünya ticaret merkezleri ve ağlarının küresel ekonomideki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Türkiye’deki ticaret merkezlerinin ticarete konu olan ürünlere ve akış yönlerine etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Tarihî ticaret yolları ve Türkiye’nin konumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Türkiye’nin dış ticareti ve dünya pazarlarındaki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Türkiye’deki doğal ve kültürel sembollerin mekânla ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Tarihsel süreçte kıtaların ve okyanusların konumsal önemindeki değişim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Ülkelerin konumunun bölgesel ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Tarihsel süreçte Türkiye’nin jeopolitik konumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Türkiye’nin içinde yer aldığı jeopolitik bölgeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Türk kültür bölgeleri ile ülkemiz arasındaki tarihi ve','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','kültürel bağlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Teknolojik gelişmelerin bölgeler ve ülkeler arası kültürel ve ekonomik etkileşimdeki rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Ülkelerin gelişmişlik seviyelerinin belirlenmesinde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Gelişmiş ve gelişmekte olan ülkelerin sosyal ve ekonomik özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Doğal kaynak potansiyelinin ülkelerin bölgesel ve küresel','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','ilişkilerine olan etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Dünyadaki başlıca enerji nakil hatlarının bölge ve ülkelere etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Doğal çevrenin sınırlılığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Farklı gelişmişlik düzeyine sahip ülkelerin çevre sorunlarının önlenmesine yönelik politika ve uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Çevresel örgüt ve anlaşmaların çevre yönetimi ve korunmasına etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Coğrafya','Ortak doğal ve kültürel mirasa yönelik tehditler','islenmedi')");
                //12.SINIF C ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Ekstrem doğa olayları ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Gelecekte doğal sistemlerde meydana gelebilecek değişimler ve bu değişimlerin canlı yaşamı üzerindeki etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Bir bölgedeki baskın ekonomik faaliyet türünün sosyal ve kültürel hayata olan etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Şehirleşme, göç ve sanayileşme olgularının birbirleriyle olan ilişkisi ve toplumsal etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Nüfus, yerleşme ve ekonomik faaliyetlerde gelecekte olabilecek değişimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Ülkemizdeki işlevsel bölgeler ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Türkiye’deki bölgesel kalkınma projeleri ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Hizmet sektörünün Türkiye ekonomisindeki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Ulaşım sisteminin gelişiminde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Ulaşım ağları ile yerleşme ve ekonomik faaliyetlerin','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Türkiye’deki ulaşım sisteminin gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Dünya ticaret merkezleri ve ağlarının küresel ekonomideki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Türkiye’deki ticaret merkezlerinin ticarete konu olan ürünlere ve akış yönlerine etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Tarihî ticaret yolları ve Türkiye’nin konumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Türkiye’nin dış ticareti ve dünya pazarlarındaki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Türkiye’deki doğal ve kültürel sembollerin mekânla ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Tarihsel süreçte kıtaların ve okyanusların konumsal önemindeki değişim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Ülkelerin konumunun bölgesel ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Tarihsel süreçte Türkiye’nin jeopolitik konumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Türkiye’nin içinde yer aldığı jeopolitik bölgeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Türk kültür bölgeleri ile ülkemiz arasındaki tarihi ve','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','kültürel bağlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Teknolojik gelişmelerin bölgeler ve ülkeler arası kültürel ve ekonomik etkileşimdeki rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Ülkelerin gelişmişlik seviyelerinin belirlenmesinde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Gelişmiş ve gelişmekte olan ülkelerin sosyal ve ekonomik özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Doğal kaynak potansiyelinin ülkelerin bölgesel ve küresel','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','ilişkilerine olan etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Dünyadaki başlıca enerji nakil hatlarının bölge ve ülkelere etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Doğal çevrenin sınırlılığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Farklı gelişmişlik düzeyine sahip ülkelerin çevre sorunlarının önlenmesine yönelik politika ve uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Çevresel örgüt ve anlaşmaların çevre yönetimi ve korunmasına etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Coğrafya','Ortak doğal ve kültürel mirasa yönelik tehditler','islenmedi')");
                //12.SINIF D ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Ekstrem doğa olayları ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Gelecekte doğal sistemlerde meydana gelebilecek değişimler ve bu değişimlerin canlı yaşamı üzerindeki etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Bir bölgedeki baskın ekonomik faaliyet türünün sosyal ve kültürel hayata olan etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Şehirleşme, göç ve sanayileşme olgularının birbirleriyle olan ilişkisi ve toplumsal etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Nüfus, yerleşme ve ekonomik faaliyetlerde gelecekte olabilecek değişimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Ülkemizdeki işlevsel bölgeler ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Türkiye’deki bölgesel kalkınma projeleri ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Hizmet sektörünün Türkiye ekonomisindeki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Ulaşım sisteminin gelişiminde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Ulaşım ağları ile yerleşme ve ekonomik faaliyetlerin','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Türkiye’deki ulaşım sisteminin gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Dünya ticaret merkezleri ve ağlarının küresel ekonomideki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Türkiye’deki ticaret merkezlerinin ticarete konu olan ürünlere ve akış yönlerine etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Tarihî ticaret yolları ve Türkiye’nin konumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Türkiye’nin dış ticareti ve dünya pazarlarındaki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Türkiye’deki doğal ve kültürel sembollerin mekânla ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Tarihsel süreçte kıtaların ve okyanusların konumsal önemindeki değişim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Ülkelerin konumunun bölgesel ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Tarihsel süreçte Türkiye’nin jeopolitik konumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Türkiye’nin içinde yer aldığı jeopolitik bölgeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Türk kültür bölgeleri ile ülkemiz arasındaki tarihi ve','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','kültürel bağlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Teknolojik gelişmelerin bölgeler ve ülkeler arası kültürel ve ekonomik etkileşimdeki rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Ülkelerin gelişmişlik seviyelerinin belirlenmesinde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Gelişmiş ve gelişmekte olan ülkelerin sosyal ve ekonomik özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Doğal kaynak potansiyelinin ülkelerin bölgesel ve küresel','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','ilişkilerine olan etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Dünyadaki başlıca enerji nakil hatlarının bölge ve ülkelere etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Doğal çevrenin sınırlılığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Farklı gelişmişlik düzeyine sahip ülkelerin çevre sorunlarının önlenmesine yönelik politika ve uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Çevresel örgüt ve anlaşmaların çevre yönetimi ve korunmasına etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Coğrafya','Ortak doğal ve kültürel mirasa yönelik tehditler','islenmedi')");

                //12.SINIF E ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Ekstrem doğa olayları ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Gelecekte doğal sistemlerde meydana gelebilecek değişimler ve bu değişimlerin canlı yaşamı üzerindeki etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Bir bölgedeki baskın ekonomik faaliyet türünün sosyal ve kültürel hayata olan etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Şehirleşme, göç ve sanayileşme olgularının birbirleriyle olan ilişkisi ve toplumsal etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Nüfus, yerleşme ve ekonomik faaliyetlerde gelecekte olabilecek değişimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Ülkemizdeki işlevsel bölgeler ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Türkiye’deki bölgesel kalkınma projeleri ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Hizmet sektörünün Türkiye ekonomisindeki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Ulaşım sisteminin gelişiminde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Ulaşım ağları ile yerleşme ve ekonomik faaliyetlerin','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Türkiye’deki ulaşım sisteminin gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Dünya ticaret merkezleri ve ağlarının küresel ekonomideki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Türkiye’deki ticaret merkezlerinin ticarete konu olan ürünlere ve akış yönlerine etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Tarihî ticaret yolları ve Türkiye’nin konumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Türkiye’nin dış ticareti ve dünya pazarlarındaki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Türkiye’deki doğal ve kültürel sembollerin mekânla ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Tarihsel süreçte kıtaların ve okyanusların konumsal önemindeki değişim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Ülkelerin konumunun bölgesel ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Tarihsel süreçte Türkiye’nin jeopolitik konumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Türkiye’nin içinde yer aldığı jeopolitik bölgeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Türk kültür bölgeleri ile ülkemiz arasındaki tarihi ve','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','kültürel bağlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Teknolojik gelişmelerin bölgeler ve ülkeler arası kültürel ve ekonomik etkileşimdeki rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Ülkelerin gelişmişlik seviyelerinin belirlenmesinde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Gelişmiş ve gelişmekte olan ülkelerin sosyal ve ekonomik özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Doğal kaynak potansiyelinin ülkelerin bölgesel ve küresel','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','ilişkilerine olan etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Dünyadaki başlıca enerji nakil hatlarının bölge ve ülkelere etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Doğal çevrenin sınırlılığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Farklı gelişmişlik düzeyine sahip ülkelerin çevre sorunlarının önlenmesine yönelik politika ve uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Çevresel örgüt ve anlaşmaların çevre yönetimi ve korunmasına etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Coğrafya','Ortak doğal ve kültürel mirasa yönelik tehditler','islenmedi')");
                //12.SINIF F ŞUBESİ COĞRAFYA
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Ekstrem doğa olayları ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Gelecekte doğal sistemlerde meydana gelebilecek değişimler ve bu değişimlerin canlı yaşamı üzerindeki etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Bir bölgedeki baskın ekonomik faaliyet türünün sosyal ve kültürel hayata olan etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Şehirleşme, göç ve sanayileşme olgularının birbirleriyle olan ilişkisi ve toplumsal etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Nüfus, yerleşme ve ekonomik faaliyetlerde gelecekte olabilecek değişimler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Ülkemizdeki işlevsel bölgeler ve özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Türkiye’deki bölgesel kalkınma projeleri ve etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Hizmet sektörünün Türkiye ekonomisindeki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Ulaşım sisteminin gelişiminde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Ulaşım ağları ile yerleşme ve ekonomik faaliyetlerin','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Türkiye’deki ulaşım sisteminin gelişimi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Dünya ticaret merkezleri ve ağlarının küresel ekonomideki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Türkiye’deki ticaret merkezlerinin ticarete konu olan ürünlere ve akış yönlerine etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Tarihî ticaret yolları ve Türkiye’nin konumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Türkiye’nin dış ticareti ve dünya pazarlarındaki yeri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Türkiye’deki doğal ve kültürel sembollerin mekânla ilişkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Tarihsel süreçte kıtaların ve okyanusların konumsal önemindeki değişim','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Ülkelerin konumunun bölgesel ve küresel etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Tarihsel süreçte Türkiye’nin jeopolitik konumu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Türkiye’nin içinde yer aldığı jeopolitik bölgeler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Türk kültür bölgeleri ile ülkemiz arasındaki tarihi ve','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','kültürel bağlar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Teknolojik gelişmelerin bölgeler ve ülkeler arası kültürel ve ekonomik etkileşimdeki rolü','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Ülkelerin gelişmişlik seviyelerinin belirlenmesinde etkili olan faktörler','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Gelişmiş ve gelişmekte olan ülkelerin sosyal ve ekonomik özellikleri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Doğal kaynak potansiyelinin ülkelerin bölgesel ve küresel','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','ilişkilerine olan etkisi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Dünyadaki başlıca enerji nakil hatlarının bölge ve ülkelere etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Doğal çevrenin sınırlılığı','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Farklı gelişmişlik düzeyine sahip ülkelerin çevre sorunlarının önlenmesine yönelik politika ve uygulamalar','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Çevresel örgüt ve anlaşmaların çevre yönetimi ve korunmasına etkileri','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Coğrafya','Ortak doğal ve kültürel mirasa yönelik tehditler','islenmedi')");


                //12.SINIF A ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Edebiyat',' Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Edebiyat','Kelimede Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Edebiyat','Paragrafta Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Edebiyat',' Deneme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Edebiyat','Söylev (Nutuk)','islenmedi')");


                //12.SINIF B ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Edebiyat',' Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Edebiyat','Kelimede Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Edebiyat','Paragrafta Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Edebiyat',' Deneme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Edebiyat','Söylev (Nutuk)','islenmedi')");
                //12.SINIF C ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Edebiyat',' Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Edebiyat','Kelimede Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Edebiyat','Paragrafta Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Edebiyat',' Deneme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Edebiyat','Söylev (Nutuk)','islenmedi')");
                //12.SINIF D ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Edebiyat',' Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Edebiyat','Kelimede Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Edebiyat','Paragrafta Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Edebiyat',' Deneme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Edebiyat','Söylev (Nutuk)','islenmedi')");
                //12.SINIF E ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Edebiyat',' Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Edebiyat','Kelimede Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Edebiyat','Paragrafta Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Edebiyat',' Deneme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Edebiyat','Söylev (Nutuk)','islenmedi')");
                //12.SINIF F ŞUBESİ EDEBİYAT
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Edebiyat',' Türk Edebiyatına Giriş','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Edebiyat','Kelimede Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Edebiyat','Hikaye','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Edebiyat','Şiir','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Edebiyat','Roman','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Edebiyat','Paragrafta Anlam','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Edebiyat','Tiyatro','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Edebiyat',' Deneme','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Edebiyat','Söylev (Nutuk)','islenmedi')");


                //12.SINIF A ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Felsefe','Felsefe’nin Konusu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Felsefe','Bilgi Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Felsefe','Varlık Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Felsefe','Ahlak Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Felsefe','Sanat Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Felsefe','Din Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Felsefe','Siyaset Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','A','Felsefe','Bilim Felsefesi','islenmedi')");



                //12.SINIF B ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Felsefe','Felsefe’nin Konusu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Felsefe','Bilgi Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Felsefe','Varlık Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Felsefe','Ahlak Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Felsefe','Sanat Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Felsefe','Din Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Felsefe','Siyaset Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','B','Felsefe','Bilim Felsefesi','islenmedi')");
                //12.SINIF C ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Felsefe','Felsefe’nin Konusu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Felsefe','Bilgi Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Felsefe','Varlık Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Felsefe','Ahlak Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Felsefe','Sanat Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Felsefe','Din Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Felsefe','Siyaset Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','C','Felsefe','Bilim Felsefesi','islenmedi')");

                //12.SINIF D ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Felsefe','Felsefe’nin Konusu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Felsefe','Bilgi Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Felsefe','Varlık Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Felsefe','Ahlak Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Felsefe','Sanat Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Felsefe','Din Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Felsefe','Siyaset Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','D','Felsefe','Bilim Felsefesi','islenmedi')");
                //12.SINIF E ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Felsefe','Felsefe’nin Konusu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Felsefe','Bilgi Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Felsefe','Varlık Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Felsefe','Ahlak Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Felsefe','Sanat Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Felsefe','Din Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Felsefe','Siyaset Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','E','Felsefe','Bilim Felsefesi','islenmedi')");
                //12.SINIF F ŞUBESİ FELSEFE
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Felsefe','Felsefe’nin Konusu','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Felsefe','Bilgi Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Felsefe','Varlık Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Felsefe','Ahlak Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Felsefe','Sanat Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Felsefe','Din Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Felsefe','Siyaset Felsefesi','islenmedi')");
                database.execSQL("INSERT INTO mufredat(sinif,kod,ders,konular,islendimi) VALUES ('12','F','Felsefe','Bilim Felsefesi','islenmedi')");

            } catch (Exception e) {
                e.printStackTrace();
            }


            Intent intent = new Intent(MainActivity.this, secim.class);
            startActivity(intent);

        }
        else{
            Toast.makeText(this, "HATALI KAYIT", Toast.LENGTH_SHORT).show();
        }
    }

}
