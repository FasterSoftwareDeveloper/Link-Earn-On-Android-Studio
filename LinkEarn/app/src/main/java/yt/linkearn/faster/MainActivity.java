package yt.linkearn.faster;

import android.animation.*;
import android.animation.ObjectAnimator;
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
import android.view.View.*;
import android.view.animation.*;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.startup.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.*;
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
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;



public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private boolean maintenance_boolean = false;
	private boolean maintenance_load = false;
	private boolean update_load = false;
	private boolean update_boolean = false;
	private String database_app_version = "";
	private boolean new_version_available = false;
	private boolean maintenance_mode = false;
	private String app_version = "";
	private String token = "";
	
	private RelativeLayout body;
	private ImageView logo;
	private ImageView app_name;
	
	private ObjectAnimator splashAnimation = new ObjectAnimator();
	private ObjectAnimator splashAnimation_applogo = new ObjectAnimator();
	private Intent Internet_Maintenance = new Intent();
	private Intent Intent_Update = new Intent();
	private Intent Intent_Authentication = new Intent();
	private Intent Intent_Welcome = new Intent();
	private Intent Intent_Dashboard = new Intent();
	private FirebaseAuth fb_authentication;
	private OnCompleteListener<AuthResult> _fb_authentication_create_user_listener;
	private OnCompleteListener<AuthResult> _fb_authentication_sign_in_listener;
	private OnCompleteListener<Void> _fb_authentication_reset_password_listener;
	private OnCompleteListener<Void> fb_authentication_updateEmailListener;
	private OnCompleteListener<Void> fb_authentication_updatePasswordListener;
	private OnCompleteListener<Void> fb_authentication_emailVerificationSentListener;
	private OnCompleteListener<Void> fb_authentication_deleteUserListener;
	private OnCompleteListener<Void> fb_authentication_updateProfileListener;
	private OnCompleteListener<AuthResult> fb_authentication_phoneAuthListener;
	private OnCompleteListener<AuthResult> fb_authentication_googleSignInListener;
	
	private SharedPreferences save_data;
	private RequestNetwork RequestNet;
	private RequestNetwork.RequestListener _RequestNet_request_listener;
	private Intent Intent_net = new Intent();
	private TimerTask timer;
	private DatabaseReference others = _firebase.getReference("others");
	private ChildEventListener _others_child_listener;
	
	private OnCompleteListener cloudMassaging_onCompleteListener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		logo = findViewById(R.id.logo);
		app_name = findViewById(R.id.app_name);
		fb_authentication = FirebaseAuth.getInstance();
		save_data = getSharedPreferences("save_data", Activity.MODE_PRIVATE);
		RequestNet = new RequestNetwork(this);
		
		_RequestNet_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_others_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("Maintenance")) {
					maintenance_load = true;
					if (_childValue.get("Maintenance Mode").toString().equals("on")) {
						maintenance_mode = true;
					} else {
						maintenance_mode = false;
					}
				}
				if (_childKey.equals("Update Manager")) {
					update_load = true;
					database_app_version = _childValue.get("version").toString();
					if ((Double.parseDouble(app_version) == Double.parseDouble(database_app_version)) || (Double.parseDouble(app_version) > Double.parseDouble(database_app_version))) {
						new_version_available = false;
					} else {
						if (Double.parseDouble(app_version) < Double.parseDouble(database_app_version)) {
							new_version_available = true;
						}
					}
				}
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
		
		fb_authentication_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fb_authentication_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fb_authentication_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fb_authentication_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fb_authentication_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		fb_authentication_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fb_authentication_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_fb_authentication_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_fb_authentication_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_fb_authentication_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		//      Start part: Firebase Massaging
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
			    FirebaseMessaging.getInstance().subscribeToTopic("user")
			        .addOnCompleteListener(new OnCompleteListener<Void>() {
				            @Override
				            public void onComplete(@NonNull Task<Void> task) {
					                if (task.isSuccessful()) {
						                    // Successfully subscribed to the topic
						                    Log.d("TAG", "Subscribed to topic: user");
						                } else {
						                    // Handle failure
						                    Log.e("TAG", "Failed to subscribe to topic", task.getException());
						                }
					            }
				        });
		} else {
			    // Handle no internet connection
			    Log.e("TAG", "No internet connection, cannot subscribe to topic");
		}
		if (SketchwareUtil.isConnected(getApplicationContext())) {
			    FirebaseMessaging.getInstance().subscribeToTopic("all")
			        .addOnCompleteListener(new OnCompleteListener<Void>() {
				            @Override
				            public void onComplete(@NonNull Task<Void> task) {
					                if (task.isSuccessful()) {
						                    // Successfully subscribed to the topic
						                    Log.d("TAG", "Subscribed to topic: user");
						                } else {
						                    // Handle failure
						                    Log.e("TAG", "Failed to subscribe to topic", task.getException());
						                }
					            }
				        });
		} else {
			    // Handle no internet connection
			    Log.e("TAG", "No internet connection, cannot subscribe to topic");
		}
		FirebaseMessaging.getInstance().getToken()
				.addOnCompleteListener(new OnCompleteListener<String>() {
					@Override
					public void onComplete(@NonNull Task<String> task) {
						if (task.isSuccessful()) {
							// Get the token
							String token = task.getResult();
							save_data.edit().putString("notification token user", token).commit();
							Log.d("TAG", "Notification token: " + token);
						} else {
							// Handle failure
							Log.e("TAG", "Failed to get notification token", task.getException());
						}
					}
				});

		//      End part: Firebase Massaging
		//      Start part: Backend
		app_version = getString(R.string.app_version_name);
		//      End part: Backend
		//      Start part: Image Animations
		splashAnimation_applogo.setTarget(logo);
		splashAnimation_applogo.setPropertyName("alpha");
		splashAnimation_applogo.setFloatValues((float)(0), (float)(1));
		splashAnimation_applogo.setDuration((int)(2500));
		splashAnimation_applogo.start();
		splashAnimation.setTarget(app_name);
		splashAnimation.setPropertyName("alpha");
		splashAnimation.setFloatValues((float)(0), (float)(1));
		splashAnimation.setDuration((int)(2500));
		splashAnimation.start();
		//      End part: Image Animations
		//      Start part: Go Another Activitys Task
		maintenance_load = false;
		update_load = false;
		if (SketchwareUtil.isConnected(getApplicationContext())) {
			timer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (maintenance_load && update_load) {
								_splash();
							} else {
								_splash();
							}
						}
					});
				}
			};
			_timer.schedule(timer, (int)(1000));
		} else {
			Intent_net.setClass(getApplicationContext(), NoNetErrorActivity.class);
			Intent_net.putExtra("activity", "no net");
			ActivityOptions options = ActivityOptions.makeCustomAnimation(MainActivity.this, R.anim.fade_in, R.anim.fade_out);
			startActivity(Intent_net, options.toBundle());
			finish();
		}
		//      End part: Go Another Activitys Task
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			timer.cancel();
		} catch (Exception e) {
			 
		}
	}
	public void _splash() {
		try {
			timer.cancel();
		} catch (Exception e) {
			 
		}
		if (maintenance_mode && new_version_available) {
			Internet_Maintenance.setClass(getApplicationContext(), AppMaintenanceActivity.class);
			ActivityOptions options = ActivityOptions.makeCustomAnimation(MainActivity.this, R.anim.fade_in, R.anim.fade_out);
			startActivity(Internet_Maintenance, options.toBundle());
			finish();
		} else {
			if (maintenance_mode) {
				Internet_Maintenance.setClass(getApplicationContext(), AppMaintenanceActivity.class);
				ActivityOptions options = ActivityOptions.makeCustomAnimation(MainActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(Internet_Maintenance, options.toBundle());
				finish();
			} else {
				if (new_version_available) {
					Intent_Update.setClass(getApplicationContext(), AppUpdateActivity.class);
					ActivityOptions UpdateOptions = ActivityOptions.makeCustomAnimation(MainActivity.this, R.anim.fade_in, R.anim.fade_out);
					startActivity(Intent_Update, UpdateOptions.toBundle());
					finish();
				} else {
					if (save_data.getString("welcome page", "").equals("")) {
						Intent_Welcome.setClass(getApplicationContext(), WelcomeActivity.class);
						ActivityOptions options = ActivityOptions.makeCustomAnimation(MainActivity.this, R.anim.fade_in, R.anim.fade_out);
						startActivity(Intent_Welcome, options.toBundle());
						finish();
					} else {
						if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
							Intent_Dashboard.setClass(getApplicationContext(), DashboardActivity.class);
							ActivityOptions options = ActivityOptions.makeCustomAnimation(MainActivity.this, R.anim.fade_in, R.anim.fade_out);
							startActivity(Intent_Dashboard, options.toBundle());
							finish();
						} else {
							Intent_Authentication.setClass(getApplicationContext(), AuthenticationActivity.class);
							ActivityOptions options = ActivityOptions.makeCustomAnimation(MainActivity.this, R.anim.fade_in, R.anim.fade_out);
							startActivity(Intent_Authentication, options.toBundle());
							finish();
						}
					}
				}
			}
		}
	}
	
}
