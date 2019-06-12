package com.tecnatox.fishchoice.views;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;

import com.tecnatox.fishchoice.R;
import com.tecnatox.fishchoice.views.selectionFragments.FrequentSpices;
import com.tecnatox.fishchoice.views.selectionFragments.NutrientsResultFragment;

public class ShowResults extends FragmentActivity {

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);
        TextView logo = findViewById(R.id.logo);
        FragmentTabHost host = findViewById(R.id.tabHost);
        host.setup(this,getSupportFragmentManager(), android.R.id.tabcontent);

        //Set TextView Nutrients with first letter blue
//        SpannableString text = new SpannableString("FISHCHOICE");
//        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blueAccent)), 0, 1, 0);
//        logo.setText(text, TextView.BufferType.SPANNABLE);

        //Tab Nutrients
        host.addTab(host.newTabSpec("Nutrients").setIndicator("Nutrients"), NutrientsResultFragment.class, null);

        //Tab Pollutants
        host.addTab(host.newTabSpec("Pollutants").setIndicator("Pollutants"), FrequentSpices.class, null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
