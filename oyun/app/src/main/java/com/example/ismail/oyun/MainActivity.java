package com.example.ismail.oyun;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    ImageView ımageView1,ımageView2,ımageView3,ımageView4,ımageView5,ımageView6,ımageView7,ımageView8,ımageView9,ımageView10,ımageView11,ımageView12;
    ImageView[] ımageViews;
    int geri;
    TextView tv;
    Random r = new Random();
    int [] idler;
    int [] konumlar = new int[12];
    int [] resimler1=new int[6];
    int [] resimler2=new int[6];
    int m;
    int hamle_sayisi =0;
    int sayac=6;
    int durum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        geri=-1;
        durum=0;
        hamle_sayisi=0;
        tv = (TextView)findViewById(R.id.tv);
        ımageView1 = (ImageView)findViewById(R.id.imageView);
        ımageView2 = (ImageView)findViewById(R.id.imageView2);
        ımageView3 = (ImageView)findViewById(R.id.imageView3);
        ımageView4 = (ImageView)findViewById(R.id.imageView4);
        ımageView5 = (ImageView)findViewById(R.id.imageView5);
        ımageView6 = (ImageView)findViewById(R.id.imageView6);
        ımageView7 = (ImageView)findViewById(R.id.imageView7);
        ımageView8 = (ImageView)findViewById(R.id.imageView8);
        ımageView9 = (ImageView)findViewById(R.id.imageView9);
        ımageView10 = (ImageView)findViewById(R.id.imageView10);
        ımageView11 = (ImageView)findViewById(R.id.imageView11);
        ımageView12 = (ImageView)findViewById(R.id.imageView12);
        ımageViews = new ImageView[]{ ımageView1,ımageView2,ımageView3,ımageView4,ımageView5,ımageView6,ımageView7,ımageView8,ımageView9,ımageView10,ımageView11,ımageView12};
        for(ImageView ımage: ımageViews){
            ımage.setVisibility(View.VISIBLE);}
        idler = new int[]{R.drawable.r1,R.drawable.r2,R.drawable.r3,R.drawable.r4,R.drawable.r5,R.drawable.r6};

        //RandomAT();

        for(int l=0;l<6;l++)
        {
            resimler1[l]=0;
        }
        for(int l=0;l<6;l++)
        {
            resimler1[2]=0;
        }



        for(m=0;m<6;m++)
        {
            resimler1[m]=idler[r.nextInt(6)];
            for(int z=0;z<6;z++)
            {
                if(m==z)
                    continue;
                if(resimler1[m]==resimler1[z])
                {
                    m=m-1;
                    break;
                }
            }
        }
        for(m=0;m<6;m++)
        {
            resimler2[m]=idler[r.nextInt(6)];
            for(int z=0;z<6;z++)
            {
                if(m==z)
                    continue;
                if(resimler2[m]==resimler2[z])
                {
                    m=m-1;
                    break;
                }
            }
        }

        for(int i=0;i<6;i++){
            konumlar[i]=resimler1[i];
        }
        for(int i=0;i<6;i++){
            konumlar[i+6]=resimler2[i];
        }

        /*
        int p=0;
        for(ImageView ımage: ımageViews){
            ımage.setImageResource(konumlar[p]);
            p++;
        }
        */
    }


    public void ResimDegistir(final int no){

        if(no!=geri)
        {

            hamle_sayisi++;
            durum=durum+1;
            tv.setText("Hamle Sayısı: "+hamle_sayisi);


            if(durum==2){
                new CountDownTimer(500,10) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        ımageViews[no].setImageResource(konumlar[no]);
                        for(ImageView ımage: ımageViews){
                            ımage.setClickable(false);
                        }
                    }

                    @Override
                    public void onFinish() {

                        if(konumlar[no]==konumlar[geri]){
                            sayac--;

                            Toast.makeText(getApplicationContext(),"Kalan Cift :"+sayac,Toast.LENGTH_LONG).show();
                            ımageViews[no].setImageResource(0);
                            ımageViews[geri].setImageResource(0);
                            ımageViews[no].setVisibility(View.INVISIBLE);
                            ımageViews[geri].setVisibility(View.INVISIBLE);
                            durum=0;
                            geri=-1;
                            if(sayac==0){
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("Oyun Bitti");
                                builder.setMessage("Oyunu "+hamle_sayisi+" hamlede tamamladınız.");
                                builder.setNegativeButton("Çık", new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                        System.exit(0);

                                    }
                                });


                                builder.setPositiveButton("Yeniden Oyna", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);

                                    }
                                });


                                builder.show();

                            }
                        }
                        else{
                            ımageViews[no].setImageResource(R.drawable.soru);
                            ımageViews[geri].setImageResource(R.drawable.soru);
                            durum=0;
                            geri=-1;
                        }
                        for(ImageView ımage: ımageViews){
                            ımage.setClickable(true);
                        }
                    }
                }.start();
            }
            else{
                geri = no;
            }




        }




    }

    public void b1(View view){
        ımageView1.setImageResource(konumlar[0]);
        ResimDegistir(0);
    }
    public void b2(View view){
        ımageView2.setImageResource(konumlar[1]);
        ResimDegistir(1);
    }
    public void b3(View view){
        ımageView3.setImageResource(konumlar[2]);
        ResimDegistir(2);
    }
    public void b4(View view){
        ımageView4.setImageResource(konumlar[3]);
        ResimDegistir(3);
    }
    public void b5(View view){
        ımageView5.setImageResource(konumlar[4]);
        ResimDegistir(4);
    }
    public void b6(View view){
        ımageView6.setImageResource(konumlar[5]);
        ResimDegistir(5);
    }
    public void b7(View view){
        ımageView7.setImageResource(konumlar[6]);
        ResimDegistir(6);
    }
    public void b8(View view){
        ımageView8.setImageResource(konumlar[7]);
        ResimDegistir(7);
    }
    public void b9(View view){
        ımageView9.setImageResource(konumlar[8]);
        ResimDegistir(8);
    }
    public void b10(View view){
        ımageView10.setImageResource(konumlar[9]);
        ResimDegistir(9);
    }
    public void b11(View view){
        ımageView11.setImageResource(konumlar[10]);
        ResimDegistir(10);
    }
    public void b12(View view){
        ımageView12.setImageResource(konumlar[11]);
        ResimDegistir(11);
    }




}