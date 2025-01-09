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
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class AppUpdateActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String updateAppLinkStr = "";
	
	private RelativeLayout relativelayout1;
	private MaterialCardView linearAppName;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private ImageView imageview1;
	private LinearLayout linear6;
	private TextView textview2;
	private TextView textview3;
	private TextView versionandcode_txt;
	private TextView whatsNew;
	private TextView whatsNew_txt;
	private Button button1;
	private Button button2;
	
	private Intent ViewLink = new Intent();
	private DatabaseReference others = _firebase.getReference("others");
	private ChildEventListener _others_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.app_update);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		relativelayout1 = findViewById(R.id.relativelayout1);
		linearAppName = findViewById(R.id.linearAppName);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		imageview1 = findViewById(R.id.imageview1);
		linear6 = findViewById(R.id.linear6);
		textview2 = findViewById(R.id.textview2);
		textview3 = findViewById(R.id.textview3);
		versionandcode_txt = findViewById(R.id.versionandcode_txt);
		whatsNew = findViewById(R.id.whatsNew);
		whatsNew_txt = findViewById(R.id.whatsNew_txt);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finishAffinity();
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ViewLink.setAction(Intent.ACTION_VIEW);
				ViewLink.setData(Uri.parse(updateAppLinkStr));
				startActivity(ViewLink);
			}
		});
		
		_others_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("Update Manager")) {
					whatsNew_txt.setText(_childValue.containsKey("what's new") && _childValue.get("what's new") != null ? _childValue.get("what's new").toString() : "");
					if (!(_childValue.get("version").toString().equals("") && _childValue.get("version code").toString().equals(""))) {
						versionandcode_txt.setText(_childValue.get("version").toString().concat("  App version & ".concat("Version code ".concat(_childValue.get("version code").toString()))));
					}
					updateAppLinkStr = _childValue.containsKey("app url") && _childValue.get("app url") != null ? _childValue.get("app url").toString() : "";
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				others.addChildEventListener(_others_child_listener);
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		others.addChildEventListener(_others_child_listener);
	}
	
	private void initializeLogic() {
	}
	
}