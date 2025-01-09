package yt.linkearn.faster;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.startup.*;
import com.google.android.material.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class NoNetErrorActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private TextView textview1;
	private TextView textview2;
	private Button button1;
	
	private Intent Intent_go = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.no_net_error);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		button1 = findViewById(R.id.button1);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Intent_go.setClass(getApplicationContext(), MainActivity.class);
				Intent_go.putExtra("activity", "no net");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(NoNetErrorActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(Intent_go, options.toBundle());
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		try {
			if (getIntent().hasExtra("activity")) {
				if (getIntent().getStringExtra("activity").equals("no net")) {
					textview1.setText("No internet found!");
					textview2.setText("Connect your mobile to internet connection!");
				} else {
					if (getIntent().getStringExtra("activity").equals("double intent")) {
						textview1.setText("Double Intent!");
						textview2.setText("Database Error.Try it later!");
					}
				}
			}
		} catch (Exception e) {
			 
		}
	}
	
}