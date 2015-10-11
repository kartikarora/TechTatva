package chipset.techtatva.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Random;

import chipset.techtatva.R;

/**
 * Developer: chipset
 * Package : chipset.techtatva.activities
 * Project : Techtatva15
 * Date : 3/10/15
 */
public class FoodStallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_stall);
        Calendar rightNow = Calendar.getInstance();
        Calendar startTTDay1 = Calendar.getInstance();
        startTTDay1.set(2015, Calendar.OCTOBER, 7, 12, 0);
        Calendar endTTDay1 = Calendar.getInstance();
        endTTDay1.set(2015, Calendar.OCTOBER, 7, 22, 0);
        Calendar startTTDay2 = Calendar.getInstance();
        startTTDay2.set(2015, Calendar.OCTOBER, 8, 12, 0);
        Calendar endTTDay2 = Calendar.getInstance();
        endTTDay2.set(2015, Calendar.OCTOBER, 8, 22, 0);
        Calendar startTTDay3 = Calendar.getInstance();
        startTTDay3.set(2015, Calendar.OCTOBER, 9, 12, 0);
        Calendar endTTDay3 = Calendar.getInstance();
        endTTDay3.set(2015, Calendar.OCTOBER, 9, 22, 0);
        Calendar startTTDay4 = Calendar.getInstance();
        startTTDay4.set(2015, Calendar.OCTOBER, 10, 12, 0);
        Calendar endTTDay4 = Calendar.getInstance();
        endTTDay4.set(2015, Calendar.OCTOBER, 10, 22, 0);
        if (rightNow.compareTo(startTTDay1) == 1 && rightNow.compareTo(endTTDay1) == -1) {
            displayAd();
        }else if(rightNow.compareTo(startTTDay2) == 1 && rightNow.compareTo(endTTDay2) == -1){
            displayAd();
        }else if(rightNow.compareTo(startTTDay3) == 1 && rightNow.compareTo(endTTDay3) == -1){
            displayAd();
        }else if(rightNow.compareTo(startTTDay4) == 1 && rightNow.compareTo(endTTDay4) == -1){
            displayAd();
        }
        else {
            this.finish();
        }
    }
    private void displayAd() {

        int images[] = {R.drawable.pizza, R.drawable.shawarma, R.drawable.gola, R.drawable.chaat, R.drawable.flavours, R.drawable.teaze};
        String titles[] = {"Pizza Hut", "Shawarma", "Gola", "Chaat", "Flavours 24", "Teaze"};
        String description[] = {
                "Enjoy \"One on One Free\" fresh and hot PIZZA from Pizza Hut Delivery at special TechTatva rates",
                "Enjoy hot and tasty authentic Arabic SHAWARMA this TechTatva",
                "Miss ICE GOLA in Manipal? Enjoy them at TechTatva and beat the heat!",
                "Missing the Bombay Chat or Calcutta Pani Puri? Satisfy your street food craving in our CHAAT Stall.",
                "Want to beat the heat and the calories? Enjoy FROZEN YOGURT from Flavors 24.",
                "Taste Manipal's favorite beverage Outlet TEAZE's amazing Blends this TechTatva."
        };

        Random random = new Random();
        int selected = random.nextInt(6);

        ImageView foodImageView = (ImageView) findViewById(R.id.food_image_view);
        TextView titleTextView = (TextView) findViewById(R.id.title_text_view);
        TextView descriptionTextView = (TextView) findViewById(R.id.description_text_view);

        Picasso.with(getApplicationContext()).load(images[selected]).into(foodImageView);
        titleTextView.setText(titles[selected]);
        descriptionTextView.setText(description[selected]);
    }
}
