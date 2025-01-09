package yt.linkearn.faster;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
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
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.startup.*;
import com.airbnb.lottie.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.*;
import com.google.android.material.card.*;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.activity.OnBackPressedCallback;

public class TaskViewActivity extends AppCompatActivity {

private MaterialProgressDialog progressDialog;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String endString = "";
	private double transactionNum = 0;
	private String seperated = "";
	private String replaceUrl = "";
	private String deepLink = "";
	private HashMap<String, Object> location_map = new HashMap<>();
	private String Location_String = "";
	private String workersCounting = "";
	private String workerMax = "";
	private String task1Key = "";
	private String task2Key = "";
	private String UserId = "";
	private String OwnerNotificationKey = "";
	private String taskLink = "";
	private String destinationLink = "";
	private boolean vpnInUse = false;
	
	private ArrayList<String> complete_list1 = new ArrayList<>();
	private ArrayList<String> complete2_list = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout body;
	private RelativeLayout task_body;
	private LinearLayout error_body;
	private LottieAnimationView lottie1;
	private TextView title_txt;
	private TextView description_txt;
	private MaterialCardView linear1;
	private MaterialCardView linear3;
	private MaterialCardView linear5;
	private MaterialCardView linear7;
	private MaterialCardView linear9;
	private LinearLayout linear11;
	private LinearLayout linear2;
	private TextView textview1;
	private TextView country_txt;
	private LinearLayout linear4;
	private TextView textview4;
	private ImageView imageview1;
	private LinearLayout linear6;
	private TextView textview5;
	private TextView time_txt;
	private TextView textview14;
	private LinearLayout linear8;
	private TextView textview7;
	private TextView completed_txt;
	private TextView textview12;
	private LinearLayout linear10;
	private TextView textview9;
	private TextView dollars;
	private TextView textview11;
	private Button button1;
	private Button button3;
	private TextView error_txt;
	private Button error_bt_txt;
	
	private TimerTask transTimer;
	private DatabaseReference Task1 = _firebase.getReference("Task/task1");
	private ChildEventListener _Task1_child_listener;
	private DatabaseReference Task2 = _firebase.getReference("Task/task2");
	private ChildEventListener _Task2_child_listener;
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	
	private Intent back_intent = new Intent();
	private Intent website_intent = new Intent();
	private Intent login_intent = new Intent();
	private RequestNetwork GET;
	private RequestNetwork.RequestListener _GET_request_listener;
	private RequestNetwork AdBlockerChecker;
	private RequestNetwork.RequestListener _AdBlockerChecker_request_listener;
	private TimerTask for_net;
	private TimerTask for_blocker;
	private AlertDialog.Builder error;
	private Intent Intent_net = new Intent();
	private Intent do_it_intent = new Intent();
	private DatabaseReference completedTask1 = _firebase.getReference("Task/completedTask1");
	private ChildEventListener _completedTask1_child_listener;
	private DatabaseReference completedTask2 = _firebase.getReference("Task/completedTask2");
	private ChildEventListener _completedTask2_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.task_view);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = findViewById(R.id.vscroll1);
		body = findViewById(R.id.body);
		task_body = findViewById(R.id.task_body);
		error_body = findViewById(R.id.error_body);
		lottie1 = findViewById(R.id.lottie1);
		title_txt = findViewById(R.id.title_txt);
		description_txt = findViewById(R.id.description_txt);
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		linear5 = findViewById(R.id.linear5);
		linear7 = findViewById(R.id.linear7);
		linear9 = findViewById(R.id.linear9);
		linear11 = findViewById(R.id.linear11);
		linear2 = findViewById(R.id.linear2);
		textview1 = findViewById(R.id.textview1);
		country_txt = findViewById(R.id.country_txt);
		linear4 = findViewById(R.id.linear4);
		textview4 = findViewById(R.id.textview4);
		imageview1 = findViewById(R.id.imageview1);
		linear6 = findViewById(R.id.linear6);
		textview5 = findViewById(R.id.textview5);
		time_txt = findViewById(R.id.time_txt);
		textview14 = findViewById(R.id.textview14);
		linear8 = findViewById(R.id.linear8);
		textview7 = findViewById(R.id.textview7);
		completed_txt = findViewById(R.id.completed_txt);
		textview12 = findViewById(R.id.textview12);
		linear10 = findViewById(R.id.linear10);
		textview9 = findViewById(R.id.textview9);
		dollars = findViewById(R.id.dollars);
		textview11 = findViewById(R.id.textview11);
		button1 = findViewById(R.id.button1);
		button3 = findViewById(R.id.button3);
		error_txt = findViewById(R.id.error_txt);
		error_bt_txt = findViewById(R.id.error_bt_txt);
		auth = FirebaseAuth.getInstance();
		GET = new RequestNetwork(this);
		AdBlockerChecker = new RequestNetwork(this);
		error = new AlertDialog.Builder(this);
		
		linear3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", description_txt.getText().toString()));
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (replaceUrl.equals("")) {
					back_intent.setClass(getApplicationContext(), DashboardActivity.class);
					ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskViewActivity.this, R.anim.fade_in, R.anim.fade_out);
					startActivity(back_intent, options.toBundle());
					finish();
				} else {
					back_intent.setClass(getApplicationContext(), MainActivity.class);
					ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskViewActivity.this, R.anim.fade_in, R.anim.fade_out);
					startActivity(back_intent, options.toBundle());
					finish();
				}
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (task1Key.equals("")) {
					if (!task2Key.equals("")) {
						if (complete2_list.contains(task2Key)) {
							_MaterialErrorDialog("You already completed this task!");
						} else {
							_ok_do_it();
						}
					}
				} else {
					if (complete_list1.contains(task1Key)) {
						_MaterialErrorDialog("You already completed this task!");
					} else {
						_ok_do_it();
					}
				}
			}
		});
		
		error_bt_txt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				login_intent.setClass(getApplicationContext(), MainActivity.class);
				ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskViewActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(login_intent, options.toBundle());
				finish();
			}
		});
		
		_Task1_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().hasExtra("key")) {
					if (_childKey.equals(getIntent().getStringExtra("key"))) {
						//      Start part: Get data
						_Material3Loader(false, "");
						task_body.setVisibility(View.VISIBLE);
						error_body.setVisibility(View.GONE);
						try {
							_TypeWriterEffect(_childValue.containsKey("title") && _childValue.get("title") != null ? _childValue.get("title").toString() : "", title_txt, 100);
						} catch (Exception e) {
							 
						}
						description_txt.setText(_childValue.containsKey("description") && _childValue.get("description") != null ? _childValue.get("description").toString() : "");
						country_txt.setText(_childValue.containsKey("country") && _childValue.get("country") != null ? _childValue.get("country").toString() : "");
						time_txt.setText(_childValue.containsKey("time") && _childValue.get("time") != null ? _childValue.get("time").toString() : "");
						dollars.setText(_childValue.containsKey("worker pay") && _childValue.get("worker pay") != null ? _childValue.get("worker pay").toString() : "");
						workersCounting = _childValue.containsKey("workers counting") && _childValue.get("workers counting") != null ? _childValue.get("workers counting").toString() : "";
						workerMax = _childValue.containsKey("worker max") && _childValue.get("worker max") != null ? _childValue.get("worker max").toString() : "";
						task1Key = _childValue.containsKey("task1 key") && _childValue.get("task1 key") != null ? _childValue.get("task1 key").toString() : "";
						UserId = _childValue.containsKey("user id") && _childValue.get("user id") != null ? _childValue.get("user id").toString() : "";
						OwnerNotificationKey = _childValue.containsKey("owner notification key") && _childValue.get("owner notification key") != null ? _childValue.get("owner notification key").toString() : "";
						taskLink = _childValue.containsKey("task link") && _childValue.get("task link") != null ? _childValue.get("task link").toString() : "";
						completed_txt.setText(workersCounting.concat("/".concat(workerMax)));
						//      End part: Get data
						//      Start part: Checking
						if (_childValue.containsKey("Deletion Timer")) {
							task_body.setVisibility(View.GONE);
							error_body.setVisibility(View.VISIBLE);
							error_txt.setText("This task is pending deletion so cannot complete it!");
							error_bt_txt.setText("Go back");
							_Material3Loader(false, "");
						} else {
							if (Double.parseDouble(_childValue.get("workers counting").toString()) == Double.parseDouble(_childValue.get("worker max").toString())) {
								task_body.setVisibility(View.GONE);
								error_body.setVisibility(View.VISIBLE);
								error_txt.setText("Task Is already full.");
								error_bt_txt.setText("Go back");
								_Material3Loader(false, "");
							} else {
								if (!_childValue.get("country").toString().equals("All Countries")) {
									if (!_childValue.get("country").toString().equals(Location_String)) {
										task_body.setVisibility(View.GONE);
										error_body.setVisibility(View.VISIBLE);
										error_txt.setText("This task is not available in your country!");
										error_bt_txt.setText("Go back");
										_Material3Loader(false, "");
									}
								}
							}
						}
						//      End part: Checking
					}
				} else {
					if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
						if (!deepLink.equals("")) {
							if (_childKey.equals(replaceUrl)) {
								//      Start part: Get data
								_Material3Loader(false, "");
								task_body.setVisibility(View.VISIBLE);
								error_body.setVisibility(View.GONE);
								try {
									_TypeWriterEffect(_childValue.containsKey("title") && _childValue.get("title") != null ? _childValue.get("title").toString() : "", title_txt, 100);
								} catch (Exception e) {
									 
								}
								description_txt.setText(_childValue.containsKey("description") && _childValue.get("description") != null ? _childValue.get("description").toString() : "");
								country_txt.setText(_childValue.containsKey("country") && _childValue.get("country") != null ? _childValue.get("country").toString() : "");
								time_txt.setText(_childValue.containsKey("time") && _childValue.get("time") != null ? _childValue.get("time").toString() : "");
								dollars.setText(_childValue.containsKey("worker pay") && _childValue.get("worker pay") != null ? _childValue.get("worker pay").toString() : "");
								workersCounting = _childValue.containsKey("workers counting") && _childValue.get("workers counting") != null ? _childValue.get("workers counting").toString() : "";
								workerMax = _childValue.containsKey("worker max") && _childValue.get("worker max") != null ? _childValue.get("worker max").toString() : "";
								task1Key = _childValue.containsKey("task1 key") && _childValue.get("task1 key") != null ? _childValue.get("task1 key").toString() : "";
								UserId = _childValue.containsKey("user id") && _childValue.get("user id") != null ? _childValue.get("user id").toString() : "";
								OwnerNotificationKey = _childValue.containsKey("owner notification key") && _childValue.get("owner notification key") != null ? _childValue.get("owner notification key").toString() : "";
								taskLink = _childValue.containsKey("task link") && _childValue.get("task link") != null ? _childValue.get("task link").toString() : "";
								completed_txt.setText(workersCounting.concat("/".concat(workerMax)));
								//      End part: Get data
								//      Start part: Checking
								if (_childValue.containsKey("Deletion Timer")) {
									task_body.setVisibility(View.GONE);
									error_body.setVisibility(View.VISIBLE);
									error_txt.setText("This task is pending deletion so cannot complete it!");
									error_bt_txt.setText("Go back");
									_Material3Loader(false, "");
								} else {
									if (Double.parseDouble(_childValue.get("workers counting").toString()) == Double.parseDouble(_childValue.get("worker max").toString())) {
										task_body.setVisibility(View.GONE);
										error_body.setVisibility(View.VISIBLE);
										error_txt.setText("Task Is already full.");
										error_bt_txt.setText("Go back");
										_Material3Loader(false, "");
									} else {
										if (!_childValue.get("country").toString().equals("All Countries")) {
											if (!_childValue.get("country").toString().equals(Location_String)) {
												task_body.setVisibility(View.GONE);
												error_body.setVisibility(View.VISIBLE);
												error_txt.setText("This task is not available in your country!");
												error_bt_txt.setText("Go back");
												_Material3Loader(false, "");
											}
										}
									}
								}
								//      End part: Checking
							}
						}
					} else {
						task_body.setVisibility(View.GONE);
						error_body.setVisibility(View.VISIBLE);
						error_txt.setText("You are not logged into any account so login quickly and enter the link.");
						error_bt_txt.setText("Authentication Now!");
						_Material3Loader(false, "");
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				Task1.addChildEventListener(_Task1_child_listener);
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				Task1.addChildEventListener(_Task1_child_listener);
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		Task1.addChildEventListener(_Task1_child_listener);
		
		_Task2_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().hasExtra("key")) {
					if (_childKey.equals(getIntent().getStringExtra("key"))) {
						//      Start part: Get data
						_Material3Loader(false, "");
						task_body.setVisibility(View.VISIBLE);
						error_body.setVisibility(View.GONE);
						try {
							_TypeWriterEffect(_childValue.containsKey("title") && _childValue.get("title") != null ? _childValue.get("title").toString() : "", title_txt, 100);
						} catch (Exception e) {
							 
						}
						description_txt.setText(_childValue.containsKey("description") && _childValue.get("description") != null ? _childValue.get("description").toString() : "");
						country_txt.setText(_childValue.containsKey("country") && _childValue.get("country") != null ? _childValue.get("country").toString() : "");
						time_txt.setText("Unlimited (Short Link Task)");
						dollars.setText(_childValue.containsKey("worker pay") && _childValue.get("worker pay") != null ? _childValue.get("worker pay").toString() : "");
						dollars.setText(_childValue.containsKey("worker pay") && _childValue.get("worker pay") != null ? _childValue.get("worker pay").toString() : "");
						workersCounting = _childValue.containsKey("workers counting") && _childValue.get("workers counting") != null ? _childValue.get("workers counting").toString() : "";
						workerMax = _childValue.containsKey("worker max") && _childValue.get("worker max") != null ? _childValue.get("worker max").toString() : "";
						task2Key = _childValue.containsKey("task2 key") && _childValue.get("task2 key") != null ? _childValue.get("task2 key").toString() : "";
						UserId = _childValue.containsKey("user id") && _childValue.get("user id") != null ? _childValue.get("user id").toString() : "";
						OwnerNotificationKey = _childValue.containsKey("owner notification key") && _childValue.get("owner notification key") != null ? _childValue.get("owner notification key").toString() : "";
						taskLink = _childValue.containsKey("task link") && _childValue.get("task link") != null ? _childValue.get("task link").toString() : "";
						destinationLink = _childValue.containsKey("destination link") && _childValue.get("destination link") != null ? _childValue.get("destination link").toString() : "";
						completed_txt.setText(workersCounting.concat("/".concat(workerMax)));
						//      End part: Get data
						//      Start part: Checking
						if (_childValue.containsKey("Deletion Timer")) {
							task_body.setVisibility(View.GONE);
							error_body.setVisibility(View.VISIBLE);
							error_txt.setText("This task is pending deletion so cannot complete it!");
							error_bt_txt.setText("Go back");
							_Material3Loader(false, "");
						} else {
							if (Double.parseDouble(_childValue.get("workers counting").toString()) == Double.parseDouble(_childValue.get("worker max").toString())) {
								task_body.setVisibility(View.GONE);
								error_body.setVisibility(View.VISIBLE);
								error_txt.setText("Task Is already full.");
								error_bt_txt.setText("Go back");
								_Material3Loader(false, "");
							} else {
								if (!_childValue.get("country").toString().equals("All Countries")) {
									if (!_childValue.get("country").toString().equals(Location_String)) {
										task_body.setVisibility(View.GONE);
										error_body.setVisibility(View.VISIBLE);
										error_txt.setText("This task is not available in your country!");
										error_bt_txt.setText("Go back");
										_Material3Loader(false, "");
									}
								}
							}
						}
						//      End part: Checking
					}
				} else {
					if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
						if (!deepLink.equals("")) {
							if (_childKey.equals(replaceUrl)) {
								//      Start part: Get data
								_Material3Loader(false, "");
								task_body.setVisibility(View.VISIBLE);
								error_body.setVisibility(View.GONE);
								try {
									_TypeWriterEffect(_childValue.containsKey("title") && _childValue.get("title") != null ? _childValue.get("title").toString() : "", title_txt, 100);
								} catch (Exception e) {
									 
								}
								description_txt.setText(_childValue.containsKey("description") && _childValue.get("description") != null ? _childValue.get("description").toString() : "");
								country_txt.setText(_childValue.containsKey("country") && _childValue.get("country") != null ? _childValue.get("country").toString() : "");
								time_txt.setText("Unlimited (Short Link Task)");
								dollars.setText(_childValue.containsKey("worker pay") && _childValue.get("worker pay") != null ? _childValue.get("worker pay").toString() : "");
								dollars.setText(_childValue.containsKey("worker pay") && _childValue.get("worker pay") != null ? _childValue.get("worker pay").toString() : "");
								workersCounting = _childValue.containsKey("workers counting") && _childValue.get("workers counting") != null ? _childValue.get("workers counting").toString() : "";
								workerMax = _childValue.containsKey("worker max") && _childValue.get("worker max") != null ? _childValue.get("worker max").toString() : "";
								task2Key = _childValue.containsKey("task2 key") && _childValue.get("task2 key") != null ? _childValue.get("task2 key").toString() : "";
								UserId = _childValue.containsKey("user id") && _childValue.get("user id") != null ? _childValue.get("user id").toString() : "";
								OwnerNotificationKey = _childValue.containsKey("owner notification key") && _childValue.get("owner notification key") != null ? _childValue.get("owner notification key").toString() : "";
								taskLink = _childValue.containsKey("task link") && _childValue.get("task link") != null ? _childValue.get("task link").toString() : "";
								destinationLink = _childValue.containsKey("destination link") && _childValue.get("destination link") != null ? _childValue.get("destination link").toString() : "";
								completed_txt.setText(workersCounting.concat("/".concat(workerMax)));
								//      End part: Get data
								//      Start part: Checking
								if (_childValue.containsKey("Deletion Timer")) {
									task_body.setVisibility(View.GONE);
									error_body.setVisibility(View.VISIBLE);
									error_txt.setText("This task is pending deletion so cannot complete it!");
									error_bt_txt.setText("Go back");
									_Material3Loader(false, "");
								} else {
									if (Double.parseDouble(_childValue.get("workers counting").toString()) == Double.parseDouble(_childValue.get("worker max").toString())) {
										task_body.setVisibility(View.GONE);
										error_body.setVisibility(View.VISIBLE);
										error_txt.setText("Task Is already full.");
										error_bt_txt.setText("Go back");
										_Material3Loader(false, "");
									} else {
										if (!_childValue.get("country").toString().equals("All Countries")) {
											if (!_childValue.get("country").toString().equals(Location_String)) {
												task_body.setVisibility(View.GONE);
												error_body.setVisibility(View.VISIBLE);
												error_txt.setText("This task is not available in your country!");
												error_bt_txt.setText("Go back");
												_Material3Loader(false, "");
											}
										}
									}
								}
								//      End part: Checking
							}
						}
					} else {
						task_body.setVisibility(View.GONE);
						error_body.setVisibility(View.VISIBLE);
						error_txt.setText("You are not logged into any account so login quickly and enter the link.");
						error_bt_txt.setText("Authentication Now!");
						_Material3Loader(false, "");
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				Task2.addChildEventListener(_Task2_child_listener);
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				Task2.addChildEventListener(_Task2_child_listener);
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		Task2.addChildEventListener(_Task2_child_listener);
		
		_GET_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				location_map = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
				if (location_map.containsKey("country")) {
					   Location_String = location_map.get("country").toString();
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_AdBlockerChecker_request_listener = new RequestNetwork.RequestListener() {
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
		
		_completedTask1_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				complete_list1.add(_childValue.get("task_key").toString());
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
		completedTask1.addChildEventListener(_completedTask1_child_listener);
		
		_completedTask2_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				complete2_list.add(_childValue.get("task_key").toString());
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
		completedTask2.addChildEventListener(_completedTask2_child_listener);
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		//      Start part: Backend
		progressDialog = new MaterialProgressDialog(TaskViewActivity.this);
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			            getWindow().setNavigationBarColor(getResources().getColor(R.color.DarkBlue, getTheme()));
			            }
		            
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			            getWindow().setStatusBarColor(getResources().getColor(R.color.DarkBlue, getTheme()));
			            }
		//      Start part: On back
		getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
				            @Override
				            public void handleOnBackPressed() {
				back_intent.setClass(getApplicationContext(), DashboardActivity.class);
				ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskViewActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(back_intent, options.toBundle());
				finish();
						            }
				        });
		//      End part: On back
		//      End part: Backend
		//      Start part: Additional
		_Material3Loader(true, "Getting Data......");
		GET.startRequestNetwork(RequestNetworkController.GET, "https://ipwho.is/", "", _GET_request_listener);
		//      End part: Additional
		//      Start part: DeepLink
		try {
			if (!getIntent().getDataString().equals("")) {
				deepLink = getIntent().getDataString();
				if (getIntent().getDataString().contains("http://")) {
					replaceUrl = getIntent().getDataString().replace(getString(R.string.httpTaskDeepLink), "");
				} else {
					if (getIntent().getDataString().contains("https://")) {
						replaceUrl = getIntent().getDataString().replace(getString(R.string.taskDeepLinkDomain), "");
					} else {
						if (getIntent().getDataString().contains("/")) {
							replaceUrl = getIntent().getDataString().replace("/", "");
						}
					}
				}
			}
		} catch (Exception e) {
			 
		}
		//      End part: DeepLink
		//      Start part: Ad blocker check
		for_net = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						AdBlockerChecker.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com/", "N", _AdBlockerChecker_request_listener);
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(for_net, (int)(0), (int)(100));
		for_blocker = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (SketchwareUtil.isConnected(getApplicationContext())) {
							try{
								android.net.ConnectivityManager cm = (android.net.ConnectivityManager) TaskViewActivity.this.getSystemService(android.content.Context.CONNECTIVITY_SERVICE);
								
								Network activeNetwork = cm.getActiveNetwork();
								NetworkCapabilities caps = cm.getNetworkCapabilities(activeNetwork);
								
								vpnInUse = caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
							}catch(Exception e){
								
							}
							if (vpnInUse) {
								_MaterialErrorDialog("We’ve detected an ad blocker. Please disable it to continue supporting us.\n\nThank you!");
								for_blocker.cancel();
								for_net.cancel();
							}
						} else {
							Intent_net.setClass(getApplicationContext(), NoNetErrorActivity.class);
							Intent_net.putExtra("activity", "no net");
							ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskViewActivity.this, R.anim.fade_in, R.anim.fade_out);
							startActivity(Intent_net, options.toBundle());
							for_blocker.cancel();
							for_net.cancel();
							finish();
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(for_blocker, (int)(0), (int)(100));
		//      End part: Ad blocker check
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			transTimer.cancel();
			for_blocker.cancel();
			for_net.cancel();
		} catch (Exception e) {
			 
		}
	}
	
	public void _TypeWriterEffect(final String _Text, final TextView _tw, final double _delay) {
		endString = "";
		transactionNum = 0;
		transTimer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (!(transactionNum == _Text.length())) {
							String jg = ""+_Text;
							char ug = jg.charAt((int) transactionNum);
							endString = endString+""+ug;
							_tw.setText(endString);
							transactionNum++;
						} else {
							transTimer.cancel();
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(transTimer, (int)(_delay), (int)(_delay));
	}
	
	
	public void _seperatedAnim(final String _res) {
		try {
			seperated = new org.json.JSONArray(_res).getJSONArray(0).getJSONArray(0).getString(0);
		} catch (org.json.JSONException e) {
			seperated = "Server sent an invalid response";
		}
	}
	
	
	public void _Material3Loader(final boolean _variable, final String _title) {
		if (_variable) {
			progressDialog.show(_title);
		} else {
			
			progressDialog.dismiss();
		}
	}
	
	
	public void _MaterialErrorDialog(final String _massage) {
		MaterialAlertDialogBuilder error = new MaterialAlertDialogBuilder(TaskViewActivity.this);
		error.setTitle("Error!");
		error.setMessage(_massage);
		error.setIcon(R.drawable.icon_error_twotone);
		error.setPositiveButton("Go back", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				_dialog.dismiss();
				back_intent.setClass(getApplicationContext(), MainActivity.class);
				ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskViewActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(back_intent, options.toBundle());
				finish();
			}
		});
		error.setCancelable(false);
		error.create().show();
	}
	
	
	public void _ok_do_it() {
		do_it_intent.setClass(getApplicationContext(), TaskWebviewActivity.class);
		do_it_intent.putExtra("title", title_txt.getText().toString());
		do_it_intent.putExtra("description", description_txt.getText().toString());
		do_it_intent.putExtra("country", country_txt.getText().toString());
		do_it_intent.putExtra("dollars", dollars.getText().toString());
		if (task1Key.equals("")) {
			if (!task2Key.equals("")) {
				do_it_intent.putExtra("task2 key", task2Key);
				do_it_intent.putExtra("destination link", destinationLink);
				do_it_intent.putExtra("position", "2");
			}
		} else {
			do_it_intent.putExtra("task1 key", task1Key);
			do_it_intent.putExtra("time", time_txt.getText().toString());
			do_it_intent.putExtra("position", "1");
		}
		do_it_intent.putExtra("user id", UserId);
		do_it_intent.putExtra("owner notification key", OwnerNotificationKey);
		do_it_intent.putExtra("task link", taskLink);
		ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskViewActivity.this, R.anim.fade_in, R.anim.fade_out);
		startActivity(do_it_intent, options.toBundle());
		finish();
	}
	
}