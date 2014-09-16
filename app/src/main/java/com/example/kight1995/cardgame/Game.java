package com.example.kight1995.cardgame;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import java.util.Random;



public class Game extends Activity {

    int[] cards1 = new int[6];
    int[] cards2 = new int[6];
    int[] rancards =  new int[6];
    int[] allcards =  new int[12];
    int[] opencards =  new int[12];
    //listed arrays
    int select1 = -1,select2 = -1;
    View lastselectedcard;

    public void generateCard() {
        for(int i=0; i<rancards.length; i++){
            rancards[i] = i;
        }
        for(int i=0; i<opencards.length; i++){
            opencards[i] = 0;
        }
        shuffleArray(rancards);
        cards1 = rancards;
        cards2 = rancards;
        System.arraycopy(cards1, 0, allcards, 0, cards1.length);
        System.arraycopy(cards2, 0, allcards, cards1.length, cards2.length);
        shuffleArray(allcards);
    }
    public void shuffleArray(int[] ar)
    {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        generateCard();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
//declaring cards' image buttons
        ImageButton card1 = (ImageButton) findViewById(R.id.card1);
        ImageButton card2 = (ImageButton) findViewById(R.id.card2);
        ImageButton card3 = (ImageButton) findViewById(R.id.card3);
        ImageButton card4 = (ImageButton) findViewById(R.id.card4);
        ImageButton card5 = (ImageButton) findViewById(R.id.card5);
        ImageButton card6 = (ImageButton) findViewById(R.id.card6);
        ImageButton card7 = (ImageButton) findViewById(R.id.card7);
        ImageButton card8 = (ImageButton) findViewById(R.id.card8);
        ImageButton card9 = (ImageButton) findViewById(R.id.card9);
        ImageButton card10 = (ImageButton) findViewById(R.id.card10);
        ImageButton card11 = (ImageButton) findViewById(R.id.card11);
        ImageButton card12 = (ImageButton) findViewById(R.id.card12);

//click listener on cards' button
        card1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectCard(1,v);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectCard(2,v);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectCard(3,v);
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectCard(4,v);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectCard(5,v);
            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectCard(6,v);
            }
        });
        card7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectCard(7,v);
            }
        });
        card8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectCard(8,v);
            }
        });
        card9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectCard(9,v);
            }
        });
        card10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectCard(10,v);
            }
        });
        card11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectCard(11,v);
            }
        });
        card12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectCard(12,v);
            }
        });
    }

    public void selectCard(int cardno,View v){
        final ImageButton button = (ImageButton) v;
        cardno = cardno - 1;
        int matchcard1 = -1;
        int matchcard2 = -1;
        for(int i=0; i<allcards.length; i++){
            if(allcards[i]==allcards[cardno]) {
                if (matchcard1 == -1) matchcard1 = i;
                else {matchcard2 = i;}
            }
        }
        switch(allcards[matchcard1]) {
            case 0: button.setImageResource(R.drawable.kspade); break;
            case 1: button.setImageResource(R.drawable.acespade); break;
            case 2: button.setImageResource(R.drawable.qspade); break;
            case 3: button.setImageResource(R.drawable.joker); break;
            case 4: button.setImageResource(R.drawable.aceheart); break;
            case 5: button.setImageResource(R.drawable.diamond); break;

            /* case 0: button.setImageResource(R.drawable.kspade); break;
             case 1: button.setImageResource(R.drawable.kheart); break;
             case 2: button.setImageResource(R.drawable.qspade); break;
             case 3: button.setImageResource(R.drawable.qheart); break;
             case 4: button.setImageResource(R.drawable.jspade); break;
             case 5: button.setImageResource(R.drawable.jheart); break;*/
            default: break;
        }
        if(opencards[cardno]==1){
            Toast.makeText(Game.this, "You already matched this card!", Toast.LENGTH_SHORT).show();
        }
        else if(select1 == -1)  {
            select1 = cardno;
            lastselectedcard = v;
        }
        else {
            if (select2 == -1) { // check match
                select2 = cardno;
                if (select1 == select2) {
                    Toast.makeText(Game.this, "You cannot pick the same card!", Toast.LENGTH_SHORT).show();
                    select2 = -1;
                } else {
                    if ((select1 == matchcard1 || select1 == matchcard2) && (select2 == matchcard1 || select2 == matchcard2)) {
                        Toast.makeText(Game.this, "Correct!", Toast.LENGTH_SHORT).show();
                        opencards[select1] = 1;
                        opencards[select2] = 1;
                        int matchedopen=0;
                        for(int i=0; i<opencards.length; i++){
                            if(opencards[i] ==1){
                                matchedopen++;
                            }
                        }
                        if(matchedopen==12) {
                            Toast.makeText(Game.this, "You win!!!", Toast.LENGTH_SHORT).show();
                            Intent s = new Intent(Game.this, End.class);
                            startActivity(s);

                        }
                    } else {
                        Toast.makeText(Game.this, "Try again eiei!", Toast.LENGTH_SHORT).show();
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        button.setImageResource(R.drawable.card);
                                        ImageButton lastbutton = (ImageButton) lastselectedcard;
                                        lastbutton.setImageResource(R.drawable.card);
                                    }
                                },
                                1000);

                    }
                    select1 = -1;
                    select2 = -1;
                }
            }
        }
       // Toast.makeText(Game.this,"Click on card noooo."+cardno+" | match no."+allcards[matchcard1]+" | match to card no."+matchcard1+"/"+matchcard2 , Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
