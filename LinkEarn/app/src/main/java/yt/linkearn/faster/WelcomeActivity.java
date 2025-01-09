package yt.linkearn.faster;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.startup.*;
import com.google.android.material.*;
import com.google.android.material.card.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class WelcomeActivity extends AppCompatActivity {
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private ImageView imageview1;
	private TextView textview1;
	private TextView textview2;
	private MaterialCardView featureAcccount;
	private MaterialCardView feature1;
	private MaterialCardView futures2;
	private MaterialCardView feature3;
	private LinearLayout linear14;
	private Button button1;
	private LinearLayout linear20;
	private LinearLayout linear21;
	private LinearLayout linear22;
	private ImageView imageview6;
	private TextView textview11;
	private TextView textview12;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private ImageView imageview2;
	private TextView textview3;
	private TextView textview4;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private ImageView imageview3;
	private TextView textview5;
	private TextView textview6;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private ImageView imageview4;
	private TextView textview7;
	private TextView textview8;
	
	private SharedPreferences save_data;
	private Intent goIntent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.welcome);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		featureAcccount = findViewById(R.id.featureAcccount);
		feature1 = findViewById(R.id.feature1);
		futures2 = findViewById(R.id.futures2);
		feature3 = findViewById(R.id.feature3);
		linear14 = findViewById(R.id.linear14);
		button1 = findViewById(R.id.button1);
		linear20 = findViewById(R.id.linear20);
		linear21 = findViewById(R.id.linear21);
		linear22 = findViewById(R.id.linear22);
		imageview6 = findViewById(R.id.imageview6);
		textview11 = findViewById(R.id.textview11);
		textview12 = findViewById(R.id.textview12);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		imageview2 = findViewById(R.id.imageview2);
		textview3 = findViewById(R.id.textview3);
		textview4 = findViewById(R.id.textview4);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		imageview3 = findViewById(R.id.imageview3);
		textview5 = findViewById(R.id.textview5);
		textview6 = findViewById(R.id.textview6);
		linear11 = findViewById(R.id.linear11);
		linear12 = findViewById(R.id.linear12);
		linear13 = findViewById(R.id.linear13);
		imageview4 = findViewById(R.id.imageview4);
		textview7 = findViewById(R.id.textview7);
		textview8 = findViewById(R.id.textview8);
		save_data = getSharedPreferences("save_data", Activity.MODE_PRIVATE);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save_data.edit().putString("welcome page", "Done").commit();
				goIntent.setClass(getApplicationContext(), AuthenticationActivity.class);
				ActivityOptions options = ActivityOptions.makeCustomAnimation(WelcomeActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(goIntent, options.toBundle());
				finish();
			}
		});
	}
	
	private void initializeLogic() {
	}
	
}