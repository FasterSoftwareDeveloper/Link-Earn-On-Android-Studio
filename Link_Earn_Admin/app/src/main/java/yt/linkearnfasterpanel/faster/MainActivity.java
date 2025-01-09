package yt.linkearnfasterpanel.faster;

import android.animation.*;
import android.animation.ObjectAnimator;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.*;
import com.google.android.material.textfield.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import androidx.activity.OnBackPressedCallback;
import com.google.firebase.messaging.FirebaseMessaging;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

private MaterialProgressDialog progressDialog;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String fontName = "";
	private String typeace = "";
	private boolean Sutter_animation = false;
	private String c1 = "";
	private String c2 = "";
	private String c3 = "";
	private String c4 = "";
	private String c5 = "";
	private String c6 = "";
	private String Answer = "";
	private HashMap<String, Object> createData = new HashMap<>();
	private String adName = "";
	private String adPass = "";
	private String adCode = "";
	private HashMap<String, Object> update_map = new HashMap<>();
	private HashMap<String, Object> maintenance_Map = new HashMap<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear5;
	private RelativeLayout relativelayout2;
	private RelativeLayout BodyLogin;
	private ImageView logo;
	private ImageView app_name;
	private ImageView panel_name;
	private LinearLayout dot;
	private LinearLayout linear8;
	private LinearLayout linear10;
	private Button button2;
	private TextView textview1;
	private ImageView imageview1;
	private TextInputLayout textInput_username;
	private TextInputLayout textinput_pasowrd;
	private TextInputLayout textinput_secret_code;
	private LinearLayout BG_CAPTCHA;
	private LinearLayout linear9;
	private Button Done;
	private TextInputEditText edittext_username;
	private TextInputEditText password_edittext;
	private TextInputEditText edittext_secreteCode;
	private LinearLayout div1;
	private TextView capta1;
	private TextView capta2;
	private TextView capta3;
	private TextView capta4;
	private TextView capta5;
	private TextView capta6;
	private LinearLayout div2;
	private ImageView IC_REFRESH_CAPTCHA;
	private TextInputLayout textinput_Captcha;
	private Button button1;
	private TextInputEditText edittext_Captcha;
	
	private ObjectAnimator Ami = new ObjectAnimator();
	private DatabaseReference fasterpanel = _firebase.getReference("fasterpanel");
	private ChildEventListener _fasterpanel_child_listener;
	private Intent Gopanel = new Intent();
	private DatabaseReference others = _firebase.getReference("others");
	private ChildEventListener _others_child_listener;
	private FirebaseAuth anonymously;
	private OnCompleteListener<AuthResult> _anonymously_create_user_listener;
	private OnCompleteListener<AuthResult> _anonymously_sign_in_listener;
	private OnCompleteListener<Void> _anonymously_reset_password_listener;
	private OnCompleteListener<Void> anonymously_updateEmailListener;
	private OnCompleteListener<Void> anonymously_updatePasswordListener;
	private OnCompleteListener<Void> anonymously_emailVerificationSentListener;
	private OnCompleteListener<Void> anonymously_deleteUserListener;
	private OnCompleteListener<Void> anonymously_updateProfileListener;
	private OnCompleteListener<AuthResult> anonymously_phoneAuthListener;
	private OnCompleteListener<AuthResult> anonymously_googleSignInListener;
	
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = findViewById(R.id.vscroll1);
		linear5 = findViewById(R.id.linear5);
		relativelayout2 = findViewById(R.id.relativelayout2);
		BodyLogin = findViewById(R.id.BodyLogin);
		logo = findViewById(R.id.logo);
		app_name = findViewById(R.id.app_name);
		panel_name = findViewById(R.id.panel_name);
		dot = findViewById(R.id.dot);
		linear8 = findViewById(R.id.linear8);
		linear10 = findViewById(R.id.linear10);
		button2 = findViewById(R.id.button2);
		textview1 = findViewById(R.id.textview1);
		imageview1 = findViewById(R.id.imageview1);
		textInput_username = findViewById(R.id.textInput_username);
		textinput_pasowrd = findViewById(R.id.textinput_pasowrd);
		textinput_secret_code = findViewById(R.id.textinput_secret_code);
		BG_CAPTCHA = findViewById(R.id.BG_CAPTCHA);
		linear9 = findViewById(R.id.linear9);
		Done = findViewById(R.id.Done);
		edittext_username = findViewById(R.id.edittext_username);
		password_edittext = findViewById(R.id.password_edittext);
		edittext_secreteCode = findViewById(R.id.edittext_secreteCode);
		div1 = findViewById(R.id.div1);
		capta1 = findViewById(R.id.capta1);
		capta2 = findViewById(R.id.capta2);
		capta3 = findViewById(R.id.capta3);
		capta4 = findViewById(R.id.capta4);
		capta5 = findViewById(R.id.capta5);
		capta6 = findViewById(R.id.capta6);
		div2 = findViewById(R.id.div2);
		IC_REFRESH_CAPTCHA = findViewById(R.id.IC_REFRESH_CAPTCHA);
		textinput_Captcha = findViewById(R.id.textinput_Captcha);
		button1 = findViewById(R.id.button1);
		edittext_Captcha = findViewById(R.id.edittext_Captcha);
		anonymously = FirebaseAuth.getInstance();
		
		linear10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_sutter_Bottom();
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try {
					createData = new HashMap<>();
					createData.put("adName", "fasterpanelv1");
					createData.put("adPass", "fasterpanelv1");
					createData.put("adCode", "fasterpanelv1");
					fasterpanel.child("fasterpanel").updateChildren(createData);
					createData.clear();
					update_map = new HashMap<>();
					update_map.put("app url", "https:www.google.com");
					update_map.put("version", "1.0");
					update_map.put("version code", "1");
					update_map.put("what's new", "Faster Panel Default");
					others.child("Update Manager").updateChildren(update_map);
					update_map.clear();
					maintenance_Map = new HashMap<>();
					maintenance_Map.put("Maintenance Mode", "off");
					others.child("Maintenance").updateChildren(maintenance_Map);
					maintenance_Map.clear();
					button2.setVisibility(View.GONE);
				} catch (Exception e) {
					 
				}
			}
		});
		
		Done.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext_username.getText().toString().equals("")) {
					textInput_username.setErrorEnabled(true);
					textInput_username.setError("Fill it");
				} else {
					textInput_username.setErrorEnabled(false);
					if (password_edittext.getText().toString().equals("")) {
						textinput_pasowrd.setErrorEnabled(true);
						textinput_pasowrd.setError("Fill it");
					} else {
						textinput_pasowrd.setErrorEnabled(false);
						if (edittext_secreteCode.getText().toString().equals("")) {
							textinput_secret_code.setErrorEnabled(true);
							textinput_secret_code.setError("Fill it");
						} else {
							textinput_secret_code.setErrorEnabled(false);
							if (edittext_username.getText().toString().equals(adName)) {
								textInput_username.setErrorEnabled(false);
								if (password_edittext.getText().toString().equals(adPass)) {
									textinput_pasowrd.setErrorEnabled(false);
									if (edittext_secreteCode.getText().toString().equals(adCode)) {
										textinput_secret_code.setErrorEnabled(false);
										Gopanel.setClass(getApplicationContext(), PanelActivity.class);
										ActivityOptions options = ActivityOptions.makeCustomAnimation(MainActivity.this, R.anim.fade_in, R.anim.fade_out);
										startActivity(Gopanel, options.toBundle());
										finish();
									} else {
										textinput_secret_code.setError("Wrong Secret Code");
										textinput_secret_code.setErrorEnabled(true);
									}
								} else {
									textinput_pasowrd.setErrorEnabled(true);
									textinput_pasowrd.setError("Wrong Password");
								}
							} else {
								textInput_username.setError("Wrong Username");
								textInput_username.setErrorEnabled(true);
							}
						}
					}
				}
			}
		});
		
		IC_REFRESH_CAPTCHA.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_NewCaptcha();
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext_Captcha.getText().toString().equals(Answer)) {
					SketchwareUtil.showMessage(getApplicationContext(), "Success!");
					Done.setEnabled(true);
				} else {
					Done.setEnabled(false);
					SketchwareUtil.showMessage(getApplicationContext(), "Wrong! ");
					_NewCaptcha();
					edittext_Captcha.setText("");
				}
			}
		});
		
		_fasterpanel_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("fasterpanel")) {
					adName = _childValue.containsKey("adName") && _childValue.get("adName") != null ? _childValue.get("adName").toString() : "";
					adPass = _childValue.containsKey("adPass") && _childValue.get("adPass") != null ? _childValue.get("adPass").toString() : "";
					adCode = _childValue.containsKey("adCode") && _childValue.get("adCode") != null ? _childValue.get("adCode").toString() : "";
					button2.setVisibility(View.GONE);
				} else {
					button2.setVisibility(View.VISIBLE);
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
		
		_others_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
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
		
		anonymously_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		anonymously_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		anonymously_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		anonymously_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		anonymously_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		anonymously_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		anonymously_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_anonymously_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_anonymously_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					_Material3Loader(false, "");
				} else {
					_Material3Loader(false, "");
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
					SketchwareUtil.showMessage(getApplicationContext(), "No account found");
					finishAffinity();
				}
			}
		};
		
		_anonymously_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		//      Start part: Backend Code
		progressDialog = new MaterialProgressDialog(MainActivity.this);
		getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
				            @Override
				            public void handleOnBackPressed() {
						                finishAffinity();
						            }
				        });
		
		// Change the navigation bar color for Android 6.0 and above
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			            getWindow().setNavigationBarColor(getResources().getColor(R.color.background, getTheme()));
			            }
		            
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			            getWindow().setStatusBarColor(getResources().getColor(R.color.DarkBlue, getTheme()));
			            }
		_NewCaptcha();
		//      End part: Backend Code
		//      Start part: othes
		anonymously.signInWithEmailAndPassword("anonymously@gmail.com", "anonymously1234").addOnCompleteListener(MainActivity.this, _anonymously_sign_in_listener);
		_Material3Loader(true, "Data Checking....");
		// For BG_CAPTCHA
		GradientDrawable BG_CAPTCHAd = new GradientDrawable();
		BG_CAPTCHAd.setColor(ContextCompat.getColor(this, R.color.layout_dot));
		BG_CAPTCHAd.setCornerRadius(8f);
		BG_CAPTCHAd.setStroke(2, ContextCompat.getColor(this, R.color.md_theme_light_tertiary));
		
		RippleDrawable BG_CAPCHAgd = new RippleDrawable(
		    new ColorStateList(new int[][]{new int[]{}}, new int[]{ContextCompat.getColor(this, R.color.md_theme_light_tertiaryContainer)}),
		    BG_CAPTCHAd, null
		);
		BG_CAPTCHA.setBackground(BG_CAPCHAgd);
		BG_CAPTCHA.setElevation(2f);
		
		// For SketchUi
		GradientDrawable SketchUi = new GradientDrawable();
		int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
		int[] clrs = {
			    ContextCompat.getColor(this, R.color.background),
			    ContextCompat.getColor(this, R.color.background)
		};
		SketchUi = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, clrs);
		SketchUi.setCornerRadii(new float[]{50, 50, 50, 50, 0, 0, 0, 0});
		SketchUi.setStroke(0, ContextCompat.getColor(this, R.color.background));
		
		BodyLogin.setElevation(d * 2);
		RippleDrawable SketchUiRD = new RippleDrawable(
		    new ColorStateList(new int[][]{new int[]{}}, new int[]{ContextCompat.getColor(this, R.color.background)}),
		    SketchUi, null
		);
		BodyLogin.setBackground(SketchUiRD);
		BodyLogin.setClickable(false);
		
		// For dot
		GradientDrawable dotd = new GradientDrawable();
		dotd.setColor(ContextCompat.getColor(this, R.color.layout_dot));
		dotd.setCornerRadius(20f);
		dotd.setStroke(0, ContextCompat.getColor(this, R.color.md_theme_light_tertiary));
		
		RippleDrawable dotgd = new RippleDrawable(
		    new ColorStateList(new int[][]{new int[]{}}, new int[]{ContextCompat.getColor(this, R.color.layout_dot)}),
		    dotd, null
		);
		dot.setBackground(dotgd);
		dot.setElevation(2f);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			    // SDK > TIRAMISU
			if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == android.content.pm.PackageManager.PERMISSION_DENIED) {
				//Denied
				requestPermissions(new String[] {android.Manifest.permission.POST_NOTIFICATIONS}, 1111);
			}else {
				//Granted
				 
			}
		}else {
			//SDK < TIRAMISU
			 
		}
		if (SketchwareUtil.isConnected(getApplicationContext())) {
			    FirebaseMessaging.getInstance().subscribeToTopic("admin")
			        .addOnCompleteListener(new OnCompleteListener<Void>() {
				            @Override
				            public void onComplete(@NonNull Task<Void> task) {
					                if (task.isSuccessful()) {
						                    Log.d("TAG", "Subscribed to topic: user");
						                } else {
						                    Log.e("TAG", "Failed to subscribe to topic", task.getException());
						                }
					            }
				        });
		} else {
			    Log.e("TAG", "No internet connection, cannot subscribe to topic");
		}
		//      End part: othes
	}
	
	public void _sutter_Bottom() {
		if (Sutter_animation) {
			Sutter_animation = false;
			Ami.setTarget(BodyLogin);
			Ami.setPropertyName("translationY");
			Ami.setFloatValues((float)(800.0d), (float)(0.0d));
			Ami.setDuration((int)(400));
			Ami.setRepeatMode(ValueAnimator.REVERSE);
			Ami.setRepeatCount((int)(0));
			Ami.setInterpolator(new LinearInterpolator());
			Ami.start();
		} else {
			Sutter_animation = true;
			Ami.setTarget(BodyLogin);
			Ami.setPropertyName("translationY");
			Ami.setFloatValues((float)(0.0d), (float)(800.0d));
			Ami.setDuration((int)(200));
			Ami.setRepeatMode(ValueAnimator.REVERSE);
			Ami.setRepeatCount((int)(0));
			Ami.setInterpolator(new LinearInterpolator());
			Ami.start();
		}
	}
	
	
	public void _NewCaptcha() {
		capta1.setText(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))));
		capta2.setText(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))));
		capta3.setText(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))));
		capta4.setText(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))));
		capta5.setText(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))));
		capta6.setText(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))));
		c1 = capta1.getText().toString();
		c2 = capta2.getText().toString();
		c3 = capta3.getText().toString();
		c4 = capta4.getText().toString();
		c5 = capta5.getText().toString();
		c6 = capta6.getText().toString();
		Answer = c1.concat(c2.concat(c3.concat(c4.concat(c5).concat(c6))));
	}
	
	
	public void _Material3Loader(final boolean _variable, final String _title) {
		if (_variable) {
			progressDialog.show(_title);
		} else {
			
			progressDialog.dismiss();
		}
	}
	
}