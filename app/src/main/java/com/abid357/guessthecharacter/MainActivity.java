package com.abid357.guessthecharacter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    List<String> urls;
    List<String> names;
    String answer;

    class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        ImageView image;

        public ImageDownloader(ImageView image){
            this.image = image;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream reader = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(reader);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            image.setImageBitmap(result);
        }
    }


    public void checkAnswer(View view){
        Button b = (Button) findViewById(view.getId());
        if (b.getText().toString().equals(answer))
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Wrong! It was " + answer, Toast.LENGTH_SHORT).show();
        nextCharacter();
    }

    public void nextCharacter(){
        Random r = new Random();
        int index = r.nextInt(urls.size());

        ImageDownloader nextImage = new ImageDownloader(image);
        nextImage.execute(urls.get(index));

        answer = names.get(index);

        List<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < urls.size(); i++)
            indices.add(i);
        indices.remove(index);
        Collections.shuffle(indices);
        Log.i("Collection", indices.toString());

        List<Integer> options = new ArrayList<Integer>();
        options.add(index);
        options.add(indices.get(0));
        options.add(indices.get(1));
        options.add(indices.get(2));
        Collections.shuffle(options);

        button1.setText(names.get(options.get(0)));
        button2.setText(names.get(options.get(1)));
        button3.setText(names.get(options.get(2)));
        button4.setText(names.get(options.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        String raw = "<p>It’s a great time to be a Dragon Ball Z fan. With the upcoming English release of Dragon Ball: Resurrection ‘F’, and the new TV series starting in Japan, there’s some Dragon Ball Z out there for everyone to love. With the hype hitting all new highs, we thought it a great time to rank our top characters from Dragon Ball Z. Of course the series is full of dozens of great heroes and villains, but we wanted to narrow it down to our 13 favorites, so lets begin!</p> <p><!-- poilib start --></p> <h2 class=\"articleSubHeader icon-pointy\">13: Goten</h2> <p><!-- poilib end --></p> <a href=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Goten.jpg\"  rel=\"nofollow\"><div class=\"img-div img-div-center\"><img class=\"aligncenter size-full wp-image-1347592\" alt=\"Goten\" src=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Goten.jpg\" width=\"512\" height=\"384\" /></div></a></p> <p>The second son of Goku, Goten is the most like his father. Goten, and his friend Trunks often serve as comic relief, even though he is a very competent fighter. In fact, he is so strong that he can go Super Saiyan at almost any time, without putting much thought or effort into it. Goten makes it on our list for encapsulating what Dragon Ball Z is all about. He is a light hearted, silly character who can hand out a beating to any person who he thinks deserves to be punished. This is most clear when he is off on wild adventures with Trunks, or in battle when he often mispronounces the series most signature move, and instead calls it the KameKameha.</p> <p><!-- poilib start --></p> <h2 class=\"articleSubHeader icon-pointy\">12: Trunks</h2> <p><!-- poilib end --></p> <a href=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Trunks.jpg\"  rel=\"nofollow\"><div class=\"img-div img-div-center\"><img class=\"aligncenter size-full wp-image-1347596\" alt=\"Trunks\" src=\"http://assets1.ignimgs.com/2015/05/27/contentplaceholderpng-967b4c.png\" data-original=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Trunks.jpg\" width=\"500\" height=\"376\" /></div></a></p> <p>Trunks is the son of Vegeta and Bulma, and much like Goten, is often serving as comic relief later in the series, as he goes on adventures with his friend and fuses with him to form Gotenks, one of the strongest, and childish fighters in the series. However, the real reason we put him at number 12 is thanks to his future iteration. In an alternate reality, there was a Trunks who saw the world destroyed by Android 17 and 18. As a last ditch effort, he went back in time to try and save the world, and he did. Not only did he kill Mecha-Frieza, but Future Trunks was instrumental in saving Goku’s life, and warning the world of the evil Androids that would soon awaken.</p> <p><!-- poilib start --></p> <h2 class=\"articleSubHeader icon-pointy\">11: Bardock</h2> <p><!-- poilib end --></p> <p style=\"text-align: left\"><a href=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Bardock.jpg\"  rel=\"nofollow\"><div class=\"img-div img-div-center\"><img class=\"aligncenter  wp-image-1347585\" alt=\"Bardock\" src=\"http://assets1.ignimgs.com/2015/05/27/contentplaceholderpng-967b4c.png\" data-original=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Bardock.jpg\" width=\"400\" /></div></a> Goku’s Saiyan father, Bardock is only ever seen in the movies and expanded literature. His impact in the overall story though, is quite a bit larger. Through a twist of fate, Bardock learns that his entire race will soon be destroyed by Frieza, and in a vain attempt to save his people, he confronts the evil emperor. Ultimately he fails, but not before giving his absolute all to save the Saiyan race, a virtue that we can see was passed on to his son Goku. Here’s to hoping we somehow see him return, and that father and son might fight together in the future for the betterment of mankind!</p> <p><!-- poilib start --></p> <h2 class=\"articleSubHeader icon-pointy\">10: Broly</h2> <p><!-- poilib end --></p> <a href=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Broly.jpg\"  rel=\"nofollow\"><div class=\"img-div img-div-center\"><img class=\"aligncenter size-full wp-image-1347586\" alt=\"Broly\" src=\"http://assets1.ignimgs.com/2015/05/27/contentplaceholderpng-967b4c.png\" data-original=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Broly.jpg\" width=\"480\" height=\"397\" /></div></a></p> <p>Broly is another movie related character. A fearsome villain, Broly is one of the few enemies in the Dragon Ball universe who truly feel intimidating, due to his immense size and power. When Goku and company first fight him, the massive Saiyan manhandles them, nearly wiping out the entire crew, and laughing in the face of Goku’s Kamehameha. What could possibly drive him to be so evil? Goku’s crying. That’s right, as children, Goku and Broly laid side by side in their Saiyan cradles, and Broly never forgot just how annoying the sound of Goku’s constant crying was. Although he would be defeated, Broly would appear again, screaming for revenge, only to be defeated by a triple Kamehameha from Goku, Gohan and Goten in one of the best scenes in any of the movies.</p> <p><!-- poilib start --></p> <h2 class=\"articleSubHeader icon-pointy\">9: Master Roshi</h2> <p><!-- poilib end --></p> <a href=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Master-Roshi.jpg\"  rel=\"nofollow\"><div class=\"img-div img-div-center\"><img class=\"aligncenter size-full wp-image-1347594\" alt=\"Master Roshi\" src=\"http://assets1.ignimgs.com/2015/05/27/contentplaceholderpng-967b4c.png\" data-original=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Master-Roshi.jpg\" width=\"546\" height=\"467\" /></div></a></p> <p>Switching gears from one of the series most evil villains, Master Roshi comes in at number 9. Although his place in Dragon Ball Z is that of comic relief, Roshi has been around longer than almost any other character, as his true age is much higher than anyone would suspect. He is also the man who invented the Kamehameha, the franchise's most recognizable move and Goku’s signature technique. When necessary, he can power up and lay down a serious beating. However, at this point, he is far more content to be on the side lines while Goku and the others save the Earth. Maybe more than any other thing though, Roshi is most famous for his lewd sensibilities, and his constant attempts to catch the female characters of the series in revealing positions.</p> <p><!-- poilib start --></p> <h2 class=\"articleSubHeader icon-pointy\">8: Buu</h2> <p><!-- poilib end --></p> <a href=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Buu.jpg\"  rel=\"nofollow\"><div class=\"img-div img-div-center\"><img class=\"aligncenter size-full wp-image-1347587\" alt=\"Buu\" src=\"http://assets1.ignimgs.com/2015/05/27/contentplaceholderpng-967b4c.png\" data-original=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Buu.jpg\" width=\"338\" height=\"280\" /></div></a></p> <p>It was hard to pick just one form of Buu for our list, so we just decided to add them all! Starting with his Fat Buu form, he was often more concerned with eating chocolate then doing battle. However, he would eventually transform, and it was this Super Buu form that became a true force to be afraid of, and if that wasn’t enough, he would transform again, ultimately ending up as Kid Buu. This was his most pure and evil form, and it took everything Goku and Vegeta had to kill him, and even then it wasn’t enough. To defeat this enemy, Goku had to channel the largest Spirit Bomb up until that point of the series, using not only his own power, but the accumulated power of every person living on Earth. The Buu forms made it on our list not just because of their epic battles though, but also in thanks to the limits he forced our heroes to push themselves beyond. We saw many new attacks, transformations and even character growth during the Buu Saga, all thanks to this hungry pink enemy.</p> <p><!-- poilib start --></p> <h2 class=\"articleSubHeader icon-pointy\">7: Cell</h2> <p><!-- poilib end --></p> <p style=\"text-align: center\"><a href=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Cell.jpg\"  rel=\"nofollow\"><div class=\"img-div img-div-center\"><img class=\"aligncenter  wp-image-1347588\" alt=\"Cell\" src=\"http://assets1.ignimgs.com/2015/05/27/contentplaceholderpng-967b4c.png\" data-original=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Cell.jpg\" width=\"400\" /></div></a></p> <p>The ultimate creation of Dr.Gero, Cell is a creature who has near endless growth potential. What is most interesting about him though, is that he is willing to wait for characters to reach their full potential. He could simply kill everyone with his immense power, but often times he is seen stalling, and waiting, as he wants to battle the heroes of Earth when they are at their true potential. There is one moment above all else though, that elevates Cell so highly on our list. That occurs when he and Teen Gohan fight, in what is one of the best scenes in the series. Even with the world on the brink, Gohan still struggles with his feelings, and Cell is only moments away from destroying everyone before Goku must step in and give his son the last bit of encouragement he needed to destroy Cell.</p> <p><!-- poilib start --></p> <h2 class=\"articleSubHeader icon-pointy\">6: Piccolo</h2> <p><!-- poilib end --></p> <a href=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Piccolo.jpg\"  rel=\"nofollow\"><div class=\"img-div img-div-center\"><img class=\"aligncenter size-full wp-image-1347595\" alt=\"Piccolo\" src=\"http://assets1.ignimgs.com/2015/05/27/contentplaceholderpng-967b4c.png\" data-original=\"http://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/Piccolo.jpg\" width=\"398\" height=\"412\" /></div></a></p> <p>No list would be complete without Piccolo, the stoic but powerful friend of Goku. Although his power can never quite match that of the Saiyan warriors, he is always prepared to go into battle, no matter what the outcome might be. For this reason, he is involved in nearly every fight in the series, starting all the way back with Raditz, as he used the Special Beam Cannon to kill the evil Saiyan, and Goku as well, who was holding Raditz in place. It’s not just his fighting prowess that got him to number 6 on our list though. Piccolo has also been instrumental in raising Gohan in times when Goku was dead, keeping the lookout safe as it’s guardian after fusing with Kami, and helping the warriors train during the Buu saga. Something unique about Piccolo, is his transformation of character. Dragon Ball is known for having very static character growth, but Piccolo is one of the few to see true evolutions. Starting as an evil villain, then becoming a rival of Goku’s, he would slowly become friendlier as time went on. This would eventually culminate when he fused with Kami, an act that would make him pure of heart.</p>";
        urls = new ArrayList<String>();
        names = new ArrayList<String>();
        Pattern p1 = Pattern.compile("href=\\\"([^\\\"]+)");
        Pattern p2 = Pattern.compile("alt=\\\"([^\\\"]+)");
        Matcher m = p1.matcher(raw);
        while(m.find())
            urls.add(m.group(1));
          m = p2.matcher(raw);
        while(m.find())
            names.add(m.group(1));

        nextCharacter();
    }
}
