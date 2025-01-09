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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.startup.*;
import com.google.android.material.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.card.*;
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
import androidx.core.widget.NestedScrollView;

public class SettingsActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String fontName = "";
	private String typeace = "";
	private HashMap<String, Object> update_map = new HashMap<>();
	
	private CollapsingToolbarLayout collapsingtoolbar1;
	private NestedScrollView vscroll1;
	private LinearLayout linear2;
	private LinearLayout update_manager_body;
	private LinearLayout live_chat_body;
	private LinearLayout firebase_manager_body;
	private LinearLayout storage_manager;
	private MaterialCardView important_note;
	private TextView textview1;
	private MaterialCardView linear84;
	private TextInputLayout U_app_url_tablayout;
	private LinearLayout linear94;
	private TextInputLayout U_whats_new_tablayout;
	private Button button1;
	private LinearLayout linear65;
	private LinearLayout linear64;
	private LinearLayout linear63;
	private ImageView imageview22;
	private TextView textview17;
	private TextView textview18;
	private LinearLayout linear86;
	private ImageView imageview26;
	private LinearLayout linear88;
	private LinearLayout linear89;
	private SwitchCompat U_switch;
	private TextView textview26;
	private EditText U_app_url_txt;
	private TextInputLayout U_v_code_tablayout;
	private TextInputLayout U_v_tablayout;
	private EditText U_app_v_code;
	private EditText U_v;
	private EditText U_whats_new;
	private WebView webview1;
	private TextView textview3;
	private TextView textview4;
	
	private DatabaseReference others = _firebase.getReference("others");
	private ChildEventListener _others_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.settings);
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
		collapsingtoolbar1 = findViewById(R.id.collapsingtoolbar1);
		vscroll1 = findViewById(R.id.vscroll1);
		linear2 = findViewById(R.id.linear2);
		update_manager_body = findViewById(R.id.update_manager_body);
		live_chat_body = findViewById(R.id.live_chat_body);
		firebase_manager_body = findViewById(R.id.firebase_manager_body);
		storage_manager = findViewById(R.id.storage_manager);
		important_note = findViewById(R.id.important_note);
		textview1 = findViewById(R.id.textview1);
		linear84 = findViewById(R.id.linear84);
		U_app_url_tablayout = findViewById(R.id.U_app_url_tablayout);
		linear94 = findViewById(R.id.linear94);
		U_whats_new_tablayout = findViewById(R.id.U_whats_new_tablayout);
		button1 = findViewById(R.id.button1);
		linear65 = findViewById(R.id.linear65);
		linear64 = findViewById(R.id.linear64);
		linear63 = findViewById(R.id.linear63);
		imageview22 = findViewById(R.id.imageview22);
		textview17 = findViewById(R.id.textview17);
		textview18 = findViewById(R.id.textview18);
		linear86 = findViewById(R.id.linear86);
		imageview26 = findViewById(R.id.imageview26);
		linear88 = findViewById(R.id.linear88);
		linear89 = findViewById(R.id.linear89);
		U_switch = findViewById(R.id.U_switch);
		textview26 = findViewById(R.id.textview26);
		U_app_url_txt = findViewById(R.id.U_app_url_txt);
		U_v_code_tablayout = findViewById(R.id.U_v_code_tablayout);
		U_v_tablayout = findViewById(R.id.U_v_tablayout);
		U_app_v_code = findViewById(R.id.U_app_v_code);
		U_v = findViewById(R.id.U_v);
		U_whats_new = findViewById(R.id.U_whats_new);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		textview3 = findViewById(R.id.textview3);
		textview4 = findViewById(R.id.textview4);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (U_app_url_txt.getText().toString().equals("")) {
					U_app_url_tablayout.setErrorEnabled(true);
					U_app_url_tablayout.setError("Fill It");
				} else {
					U_app_url_tablayout.setErrorEnabled(false);
					if (U_app_v_code.getText().toString().equals("")) {
						U_v_code_tablayout.setError("Fill It");
						U_v_code_tablayout.setErrorEnabled(true);
					} else {
						U_v_code_tablayout.setErrorEnabled(false);
						if (U_v.getText().toString().equals("")) {
							U_v_tablayout.setErrorEnabled(true);
							U_v_tablayout.setError("Fill It");
						} else {
							U_v_tablayout.setErrorEnabled(false);
							if (U_whats_new.getText().toString().equals("")) {
								U_whats_new_tablayout.setErrorEnabled(true);
								U_whats_new_tablayout.setError("Fill It");
							} else {
								U_whats_new_tablayout.setErrorEnabled(false);
								update_map = new HashMap<>();
								update_map.put("app url", U_app_url_txt.getText().toString());
								update_map.put("version", U_v.getText().toString());
								update_map.put("version code", U_app_v_code.getText().toString());
								update_map.put("what's new", U_whats_new.getText().toString());
								others.child("Update Manager").updateChildren(update_map);
								update_map.clear();
							}
						}
					}
				}
			}
		});
		
		U_switch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (U_switch.isChecked()) {
					_u_switch_checked();
				} else {
					_u_switch_unck();
					try {
						others.child("Update Manager").removeValue();
					} catch (Exception e) {
						 
					}
				}
			}
		});
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		_others_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("Update Manager")) {
					U_app_url_txt.setText(_childValue.containsKey("app url") && _childValue.get("app url") != null ? _childValue.get("app url").toString() : "");
					U_app_v_code.setText(_childValue.containsKey("version code") && _childValue.get("version code") != null ? _childValue.get("version code").toString() : "");
					U_v.setText(_childValue.containsKey("version") && _childValue.get("version") != null ? _childValue.get("version").toString() : "");
					U_whats_new.setText(_childValue.containsKey("what's new") && _childValue.get("what's new") != null ? _childValue.get("what's new").toString() : "");
					U_switch.setChecked(true);
					_u_switch_checked();
				} else {
					_u_switch_unck();
					U_switch.setChecked(false);
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("Update Manager")) {
					U_app_url_txt.setText(_childValue.containsKey("app url") && _childValue.get("app url") != null ? _childValue.get("app url").toString() : "");
					U_app_v_code.setText(_childValue.containsKey("version code") && _childValue.get("version code") != null ? _childValue.get("version code").toString() : "");
					U_v.setText(_childValue.containsKey("version") && _childValue.get("version") != null ? _childValue.get("version").toString() : "");
					U_whats_new.setText(_childValue.containsKey("what's new") && _childValue.get("what's new") != null ? _childValue.get("what's new").toString() : "");
					_u_switch_checked();
					U_switch.setChecked(true);
				} else {
					_u_switch_unck();
					U_switch.setChecked(false);
				}
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
		//      Start part: Backend
		// Change the navigation bar color for Android 6.0 and above
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			            getWindow().setNavigationBarColor(getResources().getColor(R.color.DarkBlue, getTheme()));
			            }
		            
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			            getWindow().setStatusBarColor(getResources().getColor(R.color.DarkBlue, getTheme()));
			            }
		//      End part: Backend
		_changeActivityFont("regular_txt");
		try {
			if (getIntent().getStringExtra("activity").equals("live chat")) {
				if (getSupportActionBar() != null) {
					    getSupportActionBar().setTitle("Live Chat");
				}
				live_chat_body.setVisibility(View.VISIBLE);
				update_manager_body.setVisibility(View.GONE);
				firebase_manager_body.setVisibility(View.GONE);
				storage_manager.setVisibility(View.GONE);
			} else {
				if (getIntent().getStringExtra("activity").equals("update manager")) {
					if (getSupportActionBar() != null) {
						    getSupportActionBar().setTitle("Update Manager");
					}
					live_chat_body.setVisibility(View.GONE);
					update_manager_body.setVisibility(View.VISIBLE);
					firebase_manager_body.setVisibility(View.GONE);
					storage_manager.setVisibility(View.GONE);
				} else {
					if (getIntent().getStringExtra("activity").equals("firebase manager")) {
						if (getSupportActionBar() != null) {
							    getSupportActionBar().setTitle("Firebase Manager");
						}
						live_chat_body.setVisibility(View.GONE);
						update_manager_body.setVisibility(View.GONE);
						firebase_manager_body.setVisibility(View.VISIBLE);
						storage_manager.setVisibility(View.GONE);
						webview1.loadUrl(getString(R.string.firebase_database_url));
					} else {
						if (getIntent().getStringExtra("activity").equals("storage manager")) {
							if (getSupportActionBar() != null) {
								    getSupportActionBar().setTitle("Storage Manger");
							}
							live_chat_body.setVisibility(View.GONE);
							update_manager_body.setVisibility(View.GONE);
							firebase_manager_body.setVisibility(View.GONE);
							storage_manager.setVisibility(View.VISIBLE);
						}
					}
				}
			}
			_u_switch_unck();
		} catch (Exception e) {
			 
		}
	}
	
	public void _changeActivityFont(final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".ttf"));
		overrideFonts(this,getWindow().getDecorView()); 
	} 
	private void overrideFonts(final android.content.Context context, final View v) {
		
		try {
			Typeface 
			typeace = Typeface.createFromAsset(getAssets(), fontName);;
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0;
				i < vg.getChildCount();
				i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			} else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				} else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					} else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}
		catch(Exception e)
		
		{
			SketchwareUtil.showMessage(getApplicationContext(), "Error Loading Font");
		};
	}
	
	
	public void _u_switch_checked() {
		U_app_url_tablayout.setEnabled(true);
		U_whats_new_tablayout.setEnabled(true);
		U_v_code_tablayout.setEnabled(true);
		U_v_tablayout.setEnabled(true);
		button1.setEnabled(true);
	}
	
	
	public void _u_switch_unck() {
		U_app_url_tablayout.setEnabled(false);
		U_whats_new_tablayout.setEnabled(false);
		U_v_code_tablayout.setEnabled(false);
		U_v_tablayout.setEnabled(false);
		button1.setEnabled(false);
	}
	
}