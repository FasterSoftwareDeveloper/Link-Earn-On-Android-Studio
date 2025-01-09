package yt.linkearnfasterpanel.faster;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
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
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.startup.*;
import com.google.android.material.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.*;
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

public class SecurityAndOtherManagerActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private HashMap<String, Object> map = new HashMap<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private TextView textview1;
	private TextInputLayout textinput_privacy_policy;
	private TextView textview2;
	private TextInputLayout textinput_terms;
	private TextView textview3;
	private TextInputLayout textinput_tg;
	private TextView textview4;
	private TextInputLayout textinputlayout_about;
	private Button button10;
	private TextInputEditText edittext_privacy;
	private TextInputEditText edittext_terms;
	private TextInputEditText edittext_tg;
	private TextInputEditText edittext_about;
	
	private DatabaseReference fasterpanel = _firebase.getReference("fasterpanel");
	private ChildEventListener _fasterpanel_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.security_and_other_manager);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		textinput_privacy_policy = findViewById(R.id.textinput_privacy_policy);
		textview2 = findViewById(R.id.textview2);
		textinput_terms = findViewById(R.id.textinput_terms);
		textview3 = findViewById(R.id.textview3);
		textinput_tg = findViewById(R.id.textinput_tg);
		textview4 = findViewById(R.id.textview4);
		textinputlayout_about = findViewById(R.id.textinputlayout_about);
		button10 = findViewById(R.id.button10);
		edittext_privacy = findViewById(R.id.edittext_privacy);
		edittext_terms = findViewById(R.id.edittext_terms);
		edittext_tg = findViewById(R.id.edittext_tg);
		edittext_about = findViewById(R.id.edittext_about);
		
		button10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext_privacy.getText().toString().equals("") && (edittext_terms.getText().toString().equals("") && (edittext_tg.getText().toString().equals("") && edittext_about.getText().toString().equals("")))) {
					SketchwareUtil.showMessage(getApplicationContext(), "Fill all!");
				} else {
					map = new HashMap<>();
					map.put("privacy policy url", edittext_privacy.getText().toString());
					map.put("terms url", edittext_terms.getText().toString());
					map.put("telegram url", edittext_tg.getText().toString());
					map.put("app about", edittext_about.getText().toString());
					fasterpanel.child("others").updateChildren(map);
					map.clear();
					SketchwareUtil.showMessage(getApplicationContext(), "Done!");
				}
			}
		});
		
		_fasterpanel_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("others")) {
					if (_childValue.containsKey("privacy policy url")) {
						edittext_privacy.setText(_childValue.get("privacy policy url").toString());
					}
					if (_childValue.containsKey("terms url")) {
						edittext_terms.setText(_childValue.get("terms url").toString());
					}
					if (_childValue.containsKey("telegram url")) {
						edittext_tg.setText(_childValue.get("telegram url").toString());
					}
					if (_childValue.containsKey("app about")) {
						edittext_about.setText(_childValue.get("app about").toString());
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				fasterpanel.addChildEventListener(_fasterpanel_child_listener);
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				fasterpanel.addChildEventListener(_fasterpanel_child_listener);
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		fasterpanel.addChildEventListener(_fasterpanel_child_listener);
	}
	
	private void initializeLogic() {
	}
	
}