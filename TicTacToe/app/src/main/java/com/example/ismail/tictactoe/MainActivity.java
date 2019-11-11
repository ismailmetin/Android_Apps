package com.example.ismail.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {





    private Button butonlar[][]=new Button[3][3];

    private TextView t_p1,t_p2;

    private int p1_puan,p2_puan,dondurme_sayisi;
    private boolean p1=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t_p1=(TextView)findViewById(R.id.txt_p1);
        t_p2=(TextView)findViewById(R.id.txt_p2);

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String buton_adi="button_"+i+j;
                int ids=getResources().getIdentifier(buton_adi,"id",getPackageName());
                butonlar[i][j]=findViewById(ids);
                butonlar[i][j].setOnClickListener(this);
            }
        }


    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals(""))
        {
            return;
        }
        if(p1)
        {
            ((Button)v).setText("X");
        }
        else
        {
            ((Button)v).setText("O");
        }
        dondurme_sayisi++;

        if(kazanan_kontrolu())
        {
            if(p1)
            {
                Toast.makeText(this,"1. oyuncu kazandı",Toast.LENGTH_SHORT).show();
                p1_puan++;
                t_p1.setText("1. oyuncu = "+p1_puan);
                temizle();
            }
            else
            {
                Toast.makeText(this,"2. oyuncu kazandı",Toast.LENGTH_SHORT).show();
                p2_puan++;
                t_p2.setText("2. oyuncu = "+p2_puan);
                temizle();
            }
        }
        else if(dondurme_sayisi==9)
        {
            Toast.makeText(this,"Oyun berabere",Toast.LENGTH_SHORT).show();
            temizle();
        }
        else
        {
            p1=!p1;
        }

    }

    private  boolean kazanan_kontrolu()
    {
        String buton_ici[][]=new String[3][3];

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                buton_ici[i][j]=butonlar[i][j].getText().toString();
            }
        }

        for(int k=0;k<3;k++)
        {

            if(buton_ici[k][0].equals(buton_ici[k][1]) && buton_ici[k][0].equals(buton_ici[k][2]) && !buton_ici[k][0].equals(""))
            {
                return true;
            }

        }

        for(int k=0;k<3;k++)
        {

            if(buton_ici[0][k].equals(buton_ici[1][k]) && buton_ici[0][k].equals(buton_ici[2][k]) && !buton_ici[0][k].equals(""))
            {
                return true;
            }

        }

        if(buton_ici[0][0].equals(buton_ici[1][1]) && buton_ici[0][0].equals(buton_ici[2][2]) && !buton_ici[0][0].equals(""))
        {
            return true;
        }

        if(buton_ici[0][2].equals(buton_ici[1][1]) && buton_ici[0][2].equals(buton_ici[2][0]) && !buton_ici[0][2].equals(""))
        {
            return true;
        }

        return false;

    }

    private void temizle()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                butonlar[i][j].setText("");
            }
        }

        dondurme_sayisi=0;
        p1=true;

    }
}
