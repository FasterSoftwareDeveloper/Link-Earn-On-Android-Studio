package yt.linkearnfasterpanel.faster;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class DebugActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String errorLog = getIntent().getStringExtra("error");
        ScrollView scrollView = new ScrollView(this);
        TextView textView = new TextView(this);
        textView.setText(errorLog != null ? errorLog : "No error details available.");
        textView.setTextSize(16);
        textView.setPadding(16, 16, 16, 16);
        textView.setTextIsSelectable(true);
        scrollView.addView(textView);
        setContentView(scrollView);
    }
}