package yt.linkearn.faster;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.startup.*;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.*;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.divider.MaterialDivider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.Slider;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import java.text.DecimalFormat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.activity.OnBackPressedCallback;
import androidx.core.content.ContextCompat;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import yt.linkearn.faster.FCMNotificationSender;

public class TaskActivity extends AppCompatActivity {

private MaterialProgressDialog progressDialog;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton _fab;
	private String timeInSeconds = "";
	private String formattedTime = "";
	private String total_get = "";
	private String visit_time_total_get = "";
	private String default_worker = "";
	private String default_visits_times = "";
	private String default_fee = "";
	private String default_total = "";
	private boolean website_link_check = false;
	private boolean destination_link_check = false;
	private HashMap<String, Object> task1_map = new HashMap<>();
	private double main_balance = 0;
	private HashMap<String, Object> pay_map = new HashMap<>();
	private HashMap<String, Object> history_map = new HashMap<>();
	private String task1key = "";
	private String historykey = "";
	private HashMap<String, Object> task2_map = new HashMap<>();
	private String task2key = "";
	private HashMap<String, Object> notification_map = new HashMap<>();
	private String accessToken = "";
	private String accessTokenError = "";
	private String ProjectID = "";
	private HashMap<String, Object> notification_Map = new HashMap<>();
	private String onSuccess = "";
	private String onError = "";
	private boolean notificationSend = false;
	private boolean mode_change = false;
	private boolean access_token = false;
	private HashMap<String, Object> DeletionMap = new HashMap<>();
	private double task_one_num = 0;
	private double task_status_num = 0;
	private double task_two_num = 0;
	private double task_status2_num = 0;
	private String logoUrlStr = "";
	
	private ArrayList<String> country_list_string = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> task1_list_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> taskStatus = new ArrayList<>();
	
	private CollapsingToolbarLayout collapsingtoolbar1;
	private NestedScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout add_task;
	private LinearLayout your_task;
	private TextInputLayout title_tablayout;
	private TextInputLayout description_textlayout;
	private TextInputLayout website_link_tablayout;
	private TextInputLayout country_tablayout;
	private TextView textview5;
	private MaterialButtonToggleGroup category_body;
	private TextInputLayout destinationlink_textlayout;
	private TextView textview4;
	private Slider slider_workers;
	private LinearLayout slider2_body;
	private TextView textview24;
	private Slider slider_worker_pay;
	private LinearLayout slider3_body;
	private TextView textview2;
	private Slider slider_time;
	private LinearLayout slider1_body;
	private LinearLayout linear2;
	private Button button3;
	private EditText title_txt;
	private EditText description_txt;
	private EditText link_txt;
	private AutoCompleteTextView country_txt;
	private MaterialButton button1;
	private MaterialButton button2;
	private EditText destinationlink_txt;
	private TextView textview26;
	private TextView slider_worker_txt;
	private TextView textview27;
	private TextView slider_workerpay_txt;
	private TextView textview25;
	private LinearLayout linear12;
	private LinearLayout linear14;
	private LinearLayout linear13;
	private TextView textview28;
	private TextView slider_time_txt;
	private TextView textview29;
	private TextView timeInSecondTxt;
	private TextView textview6;
	private LinearLayout linear3;
	private LinearLayout linear5;
	private LinearLayout linear7;
	private MaterialDivider linear11;
	private LinearLayout linear9;
	private TextView textview7;
	private LinearLayout linear4;
	private TextView workers_total;
	private TextView textview9;
	private TextView textview10;
	private LinearLayout linear6;
	private TextView visit_time_total;
	private TextView textview12;
	private TextView textview13;
	private LinearLayout linear8;
	private TextView fee_total;
	private TextView textview15;
	private TextView textview16;
	private LinearLayout linear10;
	private TextView total_txt;
	private TextView textview18;
	private MaterialButtonToggleGroup Task_bt_body;
	private RecyclerView your_task_list;
	private RecyclerView worker_task_status_list;
	private MaterialButton Your_task_bt;
	private MaterialButton Worker_Task_Status_bt;
	
	private TimerTask timer;
	private Intent Timer = new Intent();
	private AlertDialog.Builder error;
	private DatabaseReference Task = _firebase.getReference("Task/task1");
	private ChildEventListener _Task_child_listener;
	private DatabaseReference Task2 = _firebase.getReference("Task/task2");
	private ChildEventListener _Task2_child_listener;
	private Calendar date_get = Calendar.getInstance();
	private FirebaseAuth authentication;
	private OnCompleteListener<AuthResult> _authentication_create_user_listener;
	private OnCompleteListener<AuthResult> _authentication_sign_in_listener;
	private OnCompleteListener<Void> _authentication_reset_password_listener;
	private OnCompleteListener<Void> authentication_updateEmailListener;
	private OnCompleteListener<Void> authentication_updatePasswordListener;
	private OnCompleteListener<Void> authentication_emailVerificationSentListener;
	private OnCompleteListener<Void> authentication_deleteUserListener;
	private OnCompleteListener<Void> authentication_updateProfileListener;
	private OnCompleteListener<AuthResult> authentication_phoneAuthListener;
	private OnCompleteListener<AuthResult> authentication_googleSignInListener;
	
	private SharedPreferences save_data;
	private DatabaseReference history = _firebase.getReference("history");
	private ChildEventListener _history_child_listener;
	private Intent success = new Intent();
	private TimerTask timer2;
	private RequestNetwork Request_Connection;
	private RequestNetwork.RequestListener _Request_Connection_request_listener;
	private DatabaseReference db_accounts = _firebase.getReference("db_accounts");
	private ChildEventListener _db_accounts_child_listener;
	private AlertDialog.Builder confirmDialog;
	private Intent successTask2 = new Intent();
	private AlertDialog.Builder successDialog;
	private TimerTask timer3;
	private AlertDialog.Builder deleteDialog;
	private Intent editDialog = new Intent();
	private Intent backIntent = new Intent();
	private TimerTask DeleteTimer;
	private Intent successIntent = new Intent();
	private TimerTask deleteTimer2;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.task);
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
		_fab = findViewById(R.id._fab);
		collapsingtoolbar1 = findViewById(R.id.collapsingtoolbar1);
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		add_task = findViewById(R.id.add_task);
		your_task = findViewById(R.id.your_task);
		title_tablayout = findViewById(R.id.title_tablayout);
		description_textlayout = findViewById(R.id.description_textlayout);
		website_link_tablayout = findViewById(R.id.website_link_tablayout);
		country_tablayout = findViewById(R.id.country_tablayout);
		textview5 = findViewById(R.id.textview5);
		category_body = findViewById(R.id.category_body);
		destinationlink_textlayout = findViewById(R.id.destinationlink_textlayout);
		textview4 = findViewById(R.id.textview4);
		slider_workers = findViewById(R.id.slider_workers);
		slider2_body = findViewById(R.id.slider2_body);
		textview24 = findViewById(R.id.textview24);
		slider_worker_pay = findViewById(R.id.slider_worker_pay);
		slider3_body = findViewById(R.id.slider3_body);
		textview2 = findViewById(R.id.textview2);
		slider_time = findViewById(R.id.slider_time);
		slider1_body = findViewById(R.id.slider1_body);
		linear2 = findViewById(R.id.linear2);
		button3 = findViewById(R.id.button3);
		title_txt = findViewById(R.id.title_txt);
		description_txt = findViewById(R.id.description_txt);
		link_txt = findViewById(R.id.link_txt);
		country_txt = findViewById(R.id.country_txt);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		destinationlink_txt = findViewById(R.id.destinationlink_txt);
		textview26 = findViewById(R.id.textview26);
		slider_worker_txt = findViewById(R.id.slider_worker_txt);
		textview27 = findViewById(R.id.textview27);
		slider_workerpay_txt = findViewById(R.id.slider_workerpay_txt);
		textview25 = findViewById(R.id.textview25);
		linear12 = findViewById(R.id.linear12);
		linear14 = findViewById(R.id.linear14);
		linear13 = findViewById(R.id.linear13);
		textview28 = findViewById(R.id.textview28);
		slider_time_txt = findViewById(R.id.slider_time_txt);
		textview29 = findViewById(R.id.textview29);
		timeInSecondTxt = findViewById(R.id.timeInSecondTxt);
		textview6 = findViewById(R.id.textview6);
		linear3 = findViewById(R.id.linear3);
		linear5 = findViewById(R.id.linear5);
		linear7 = findViewById(R.id.linear7);
		linear11 = findViewById(R.id.linear11);
		linear9 = findViewById(R.id.linear9);
		textview7 = findViewById(R.id.textview7);
		linear4 = findViewById(R.id.linear4);
		workers_total = findViewById(R.id.workers_total);
		textview9 = findViewById(R.id.textview9);
		textview10 = findViewById(R.id.textview10);
		linear6 = findViewById(R.id.linear6);
		visit_time_total = findViewById(R.id.visit_time_total);
		textview12 = findViewById(R.id.textview12);
		textview13 = findViewById(R.id.textview13);
		linear8 = findViewById(R.id.linear8);
		fee_total = findViewById(R.id.fee_total);
		textview15 = findViewById(R.id.textview15);
		textview16 = findViewById(R.id.textview16);
		linear10 = findViewById(R.id.linear10);
		total_txt = findViewById(R.id.total_txt);
		textview18 = findViewById(R.id.textview18);
		Task_bt_body = findViewById(R.id.Task_bt_body);
		your_task_list = findViewById(R.id.your_task_list);
		worker_task_status_list = findViewById(R.id.worker_task_status_list);
		Your_task_bt = findViewById(R.id.Your_task_bt);
		Worker_Task_Status_bt = findViewById(R.id.Worker_Task_Status_bt);
		error = new AlertDialog.Builder(this);
		authentication = FirebaseAuth.getInstance();
		save_data = getSharedPreferences("save_data", Activity.MODE_PRIVATE);
		Request_Connection = new RequestNetwork(this);
		confirmDialog = new AlertDialog.Builder(this);
		successDialog = new AlertDialog.Builder(this);
		deleteDialog = new AlertDialog.Builder(this);
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (title_txt.getText().toString().equals("")) {
					title_tablayout.setErrorEnabled(true);
					title_tablayout.setError("Enter some text!");
				} else {
					title_tablayout.setErrorEnabled(false);
					if (title_txt.getText().toString().length() > title_tablayout.getCounterMaxLength()) {
						_MaterialErrorDialog("Text exceeds maximum length!");
					} else {
						if (description_txt.getText().toString().equals("")) {
							description_textlayout.setErrorEnabled(true);
							description_textlayout.setError("Enter some text!");
						} else {
							description_textlayout.setErrorEnabled(false);
							if (description_txt.getText().toString().length() > description_textlayout.getCounterMaxLength()) {
								_MaterialErrorDialog("Text exceeds maximum length!");
							} else {
								if (link_txt.getText().toString().equals("")) {
									website_link_tablayout.setErrorEnabled(true);
									website_link_tablayout.setError("Enter some text!");
								} else {
									if (website_link_check) {
										website_link_tablayout.setErrorEnabled(false);
										if (country_txt.getText().toString().equals("")) {
											country_tablayout.setErrorEnabled(true);
											country_tablayout.setError("Enter some text!");
										} else {
											country_tablayout.setErrorEnabled(false);
											if (slider1_body.getVisibility() == View.VISIBLE) {
												if (SketchwareUtil.isConnected(getApplicationContext())) {
													if ((main_balance == Double.parseDouble(total_txt.getText().toString())) || (main_balance > Double.parseDouble(total_txt.getText().toString()))) {
														MaterialAlertDialogBuilder confirmDialog = new MaterialAlertDialogBuilder(TaskActivity.this);
														confirmDialog.setTitle("Are you sure?");
														confirmDialog.setMessage("Do you want to confirm the payment and publish the task?");
														confirmDialog.setIcon(R.drawable.icon_balance_wallet);
														confirmDialog.setPositiveButton("Yes, confirm!", new DialogInterface.OnClickListener() {
															@Override
															public void onClick(DialogInterface _dialog, int _which) {
																_dialog.dismiss();
																_Material3Loader(true, "Adding in our database...");
																task1key = Task.push().getKey();
																//      Start part: Task 1
																task1_map = new HashMap<>();
																task1_map.put("user id", FirebaseAuth.getInstance().getCurrentUser().getUid());
																task1_map.put("img url", logoUrlStr);
																task1_map.put("task share", getString(R.string.taskDeepLinkDomain).concat(task1key));
																task1_map.put("title", title_txt.getText().toString().trim());
																task1_map.put("description", description_txt.getText().toString().trim());
																task1_map.put("task link", link_txt.getText().toString().trim());
																task1_map.put("country", country_txt.getText().toString().trim());
																task1_map.put("worker max", slider_worker_txt.getText().toString().trim());
																task1_map.put("worker pay", slider_workerpay_txt.getText().toString().trim());
																task1_map.put("workers counting", "0");
																task1_map.put("owner notification key", save_data.getString("notification token user", ""));
																task1_map.put("time", timeInSeconds);
																task1_map.put("task1 key", task1key);
																date_get = Calendar.getInstance();
																task1_map.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm").format(date_get.getTime()));
																Task.child(task1key).updateChildren(task1_map);
																task1_map.clear();
																//      End part: Task 1
																//      Start part: Payment
																pay_map = new HashMap<>();
																pay_map.put("deposit balance", new DecimalFormat("0.000").format(Double.parseDouble(total_txt.getText().toString()) - main_balance).replace("-", ""));
																db_accounts.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(pay_map);
																pay_map.clear();
																//      End part: Payment
																//      Start part: History
																historykey = history.push().getKey();
																history_map = new HashMap<>();
																history_map.put("title", "New task added! #".concat(historykey));
																date_get = Calendar.getInstance();
																history_map.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm").format(date_get.getTime()));
																history_map.put("balance", "-".concat(new DecimalFormat("0.000").format(Double.parseDouble(total_txt.getText().toString()))));
																history_map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
																history_map.put("key", historykey);
																history.child(historykey).updateChildren(history_map);
																history_map.clear();
																_notification("New \"Website Visit\" Task added.", "Get the task done now and earn!", "", "", "all", "https://linkearntask.com/".concat(task1key));
																//      End part: History
																//      Start part: Task 1 all setup completed! now go success activity
																timer2 = new TimerTask() {
																	@Override
																	public void run() {
																		runOnUiThread(new Runnable() {
																			@Override
																			public void run() {
																				_Material3Loader(false, "");
																				success.setClass(getApplicationContext(), SuccessActivity.class);
																				success.putExtra("activity", "added task1");
																				ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskActivity.this, R.anim.fade_in, R.anim.fade_out);
																				startActivity(success, options.toBundle());
																				timer2.cancel();
																				finish();
																			}
																		});
																	}
																};
																_timer.schedule(timer2, (int)(3000));
																//      End part: Task 1 all setup completed! now go success activity
															}
														});
														confirmDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
															@Override
															public void onClick(DialogInterface _dialog, int _which) {
																_dialog.dismiss();
															}
														});
														confirmDialog.create().show();
													} else {
														_MaterialErrorDialog("Not enough money! please deposit some money.");
													}
												} else {
													_MaterialErrorDialog("No internet connection found!");
												}
											} else {
												if (destinationlink_txt.getText().toString().equals("")) {
													destinationlink_textlayout.setErrorEnabled(true);
													destinationlink_textlayout.setError("Enter some text!");
												} else {
													if (destination_link_check) {
														destinationlink_textlayout.setErrorEnabled(false);
														if (SketchwareUtil.isConnected(getApplicationContext())) {
															if ((main_balance == Double.parseDouble(total_txt.getText().toString())) || (main_balance > Double.parseDouble(total_txt.getText().toString()))) {
																MaterialAlertDialogBuilder confirmDialog = new MaterialAlertDialogBuilder(TaskActivity.this);
																confirmDialog.setTitle("Are you sure?");
																confirmDialog.setMessage("Do you want to confirm the payment and publish the task?");
																confirmDialog.setIcon(R.drawable.icon_balance_wallet);
																confirmDialog.setPositiveButton("Yes, confirm!", new DialogInterface.OnClickListener() {
																	@Override
																	public void onClick(DialogInterface _dialog, int _which) {
																		_dialog.dismiss();
																		_Material3Loader(true, "Adding in our database...");
																		task2key = Task2.push().getKey();
																		//      Start part: Task 2
																		task2_map = new HashMap<>();
																		task2_map.put("user id", FirebaseAuth.getInstance().getCurrentUser().getUid());
																		task2_map.put("img url", logoUrlStr);
																		task2_map.put("task share", getString(R.string.taskDeepLinkDomain).concat(task2key));
																		task2_map.put("title", title_txt.getText().toString().trim());
																		task2_map.put("description", description_txt.getText().toString().trim());
																		task2_map.put("task link", link_txt.getText().toString().trim());
																		task2_map.put("country", country_txt.getText().toString().trim());
																		task2_map.put("destination link", "https://".concat(destinationlink_txt.getText().toString()));
																		task2_map.put("worker max", slider_worker_txt.getText().toString().trim());
																		task2_map.put("worker pay", slider_workerpay_txt.getText().toString().trim());
																		task2_map.put("workers counting", "0");
																		task2_map.put("owner notification key", save_data.getString("notification token user", ""));
																		task2_map.put("task2 key", task2key);
																		date_get = Calendar.getInstance();
																		task2_map.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm").format(date_get.getTime()));
																		Task2.child(task2key).updateChildren(task2_map);
																		task2_map.clear();
																		//      End part: Task 2
																		//      Start part: Payment
																		pay_map = new HashMap<>();
																		pay_map.put("deposit balance", new DecimalFormat("0.000").format(Double.parseDouble(total_txt.getText().toString()) - main_balance).replace("-", ""));
																		db_accounts.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(pay_map);
																		pay_map.clear();
																		//      End part: Payment
																		//      Start part: History
																		historykey = history.push().getKey();
																		history_map = new HashMap<>();
																		history_map.put("title", "New task added! #".concat(historykey));
																		date_get = Calendar.getInstance();
																		history_map.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm").format(date_get.getTime()));
																		history_map.put("balance", "-".concat(new DecimalFormat("0.000").format(Double.parseDouble(total_txt.getText().toString()))));
																		history_map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
																		history_map.put("key", historykey);
																		history.child(historykey).updateChildren(history_map);
																		history_map.clear();
																		_notification("New \"Short Link\" Task added.", "Get the task done now and earn!", "", "", "all", "https://linkearntask.com/".concat(task2key));
																		//      End part: History
																		//      Start part: Task 1 all setup completed! now go success activity
																		timer2 = new TimerTask() {
																			@Override
																			public void run() {
																				runOnUiThread(new Runnable() {
																					@Override
																					public void run() {
																						_Material3Loader(false, "");
																						success.setClass(getApplicationContext(), SuccessActivity.class);
																						success.putExtra("activity", "added task2");
																						ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskActivity.this, R.anim.fade_in, R.anim.fade_out);
																						startActivity(success, options.toBundle());
																						timer2.cancel();
																						finish();
																					}
																				});
																			}
																		};
																		_timer.schedule(timer2, (int)(3000));
																		//      End part: Task 1 all setup completed! now go success activity
																	}
																});
																confirmDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
																	@Override
																	public void onClick(DialogInterface _dialog, int _which) {
																		_dialog.dismiss();
																	}
																});
																confirmDialog.create().show();
															} else {
																_MaterialErrorDialog("Not enough money! please deposit some money.");
															}
														} else {
															_MaterialErrorDialog("No internet connection found!");
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		});
		
		link_txt.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.contains("https://") || _charSeq.contains("http://")) {
					website_link_tablayout.setErrorEnabled(false);
					if (_charSeq.contains(" ")) {
						website_link_tablayout.setErrorEnabled(true);
						website_link_tablayout.setError("Don't use space");
						website_link_check = false;
					} else {
						website_link_tablayout.setErrorEnabled(false);
						if (_charSeq.contains(".")) {
							website_link_tablayout.setErrorEnabled(false);
							website_link_check = true;
						} else {
							website_link_tablayout.setErrorEnabled(true);
							website_link_tablayout.setError("Domain not found.");
							website_link_check = false;
						}
					}
				} else {
					website_link_tablayout.setErrorEnabled(true);
					website_link_tablayout.setError("http:// https:// not found in start");
					website_link_check = false;
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				slider1_body.setVisibility(View.VISIBLE);
				slider_time.setVisibility(View.VISIBLE);
				textview2.setVisibility(View.VISIBLE);
				linear5.setVisibility(View.VISIBLE);
				destinationlink_textlayout.setVisibility(View.GONE);
				total_get = new DecimalFormat("0.000").format(Double.parseDouble(workers_total.getText().toString()) + Double.parseDouble(visit_time_total.getText().toString()));
				total_txt.setText(new DecimalFormat("0.000").format(Double.parseDouble(total_get) + Double.parseDouble(fee_total.getText().toString())));
				_TransitionManager(add_task, 500);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				slider1_body.setVisibility(View.GONE);
				slider_time.setVisibility(View.GONE);
				textview2.setVisibility(View.GONE);
				linear5.setVisibility(View.GONE);
				destinationlink_textlayout.setVisibility(View.VISIBLE);
				total_txt.setText(new DecimalFormat("0.000").format(Double.parseDouble(workers_total.getText().toString()) + Double.parseDouble(fee_total.getText().toString())));
				_TransitionManager(add_task, 500);
			}
		});
		
		destinationlink_txt.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.contains("https://") || _charSeq.contains("http://")) {
					destinationlink_textlayout.setErrorEnabled(false);
					if (_charSeq.contains(" ")) {
						destinationlink_textlayout.setErrorEnabled(true);
						destinationlink_textlayout.setError("Don't use space");
						destination_link_check = false;
					} else {
						destinationlink_textlayout.setErrorEnabled(false);
						if (_charSeq.contains(".")) {
							destinationlink_textlayout.setErrorEnabled(false);
							destination_link_check = true;
						} else {
							destinationlink_textlayout.setErrorEnabled(true);
							destinationlink_textlayout.setError("Domain not found.");
							destination_link_check = false;
						}
					}
				} else {
					destinationlink_textlayout.setErrorEnabled(true);
					destinationlink_textlayout.setError("Don't use https");
					destination_link_check = false;
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		Your_task_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				your_task_list.setVisibility(View.VISIBLE);
				worker_task_status_list.setVisibility(View.GONE);
			}
		});
		
		Worker_Task_Status_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				your_task_list.setVisibility(View.GONE);
				worker_task_status_list.setVisibility(View.VISIBLE);
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (mode_change) {
					your_task.setVisibility(View.GONE);
					add_task.setVisibility(View.VISIBLE);
					if (access_token) {
						try {
							    try (InputStream originalInputStream = getAssets().open("service-account.json")) {
								        byte[] inputStreamBytes = SketchwareUtil.copyFromInputStream(originalInputStream).getBytes();
								
								        try (InputStream inputStream = new ByteArrayInputStream(inputStreamBytes)) {
									            String jsonString = SketchwareUtil.copyFromInputStream(inputStream);
									            HashMap<String, Object> map = new Gson().fromJson(
									                jsonString,
									                new TypeToken<HashMap<String, Object>>(){}.getType()
									            );
									            ProjectID = map.get("project_id").toString();
									        }
								
								        try (InputStream inputStream = new ByteArrayInputStream(inputStreamBytes)) {
									            AccessTokenGenerator.generateToken(inputStream, new AccessTokenGenerator.OnTokenResponse() {  // Fix here
										                @Override
										                public void onSuccess(String token) {
											                    JsonObject jsonResponse = JsonParser.parseString(token).getAsJsonObject();
											                    if (jsonResponse.has("access_token")) {
												                        accessToken = jsonResponse.get("access_token").getAsString();
												                    } else {
												                        accessToken = "error getting accessToken";
												                    }
											                }
										
										                @Override
										                public void onError(String error) {
											                    accessTokenError = error;
											                    _MaterialErrorDialog(accessTokenError);
											                }
										            });
									        }
								    } catch (IOException e) {
								        accessTokenError = "Error reading service account file: " + e.getMessage();
								        _MaterialErrorDialog(accessTokenError);
								    }
						} catch (Exception e) {
							    _MaterialErrorDialog("Unexpected error: " + e.getMessage());
						}
						
						access_token = false;
					}
					_fab.setImageResource(R.drawable.icon_cancel_outline);
					mode_change = false;
				} else {
					your_task.setVisibility(View.VISIBLE);
					add_task.setVisibility(View.GONE);
					_fab.setImageResource(R.drawable.icon_plus_round);
					mode_change = true;
				}
			}
		});
		
		_Task_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				task1_list_map.add(_childValue);
				your_task_list.setAdapter(new Your_task_listAdapter(task1_list_map));
				taskStatus.add(_childValue);
				worker_task_status_list.setAdapter(new Worker_task_status_listAdapter(taskStatus));
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				task_one_num = 0;
				for(int _repeat121 = 0; _repeat121 < (int)(task1_list_map.size()); _repeat121++) {
					if (task1_list_map.get((int)task_one_num).get("task1 key").toString().equals(_childKey)) {
						task1_list_map.remove((int)(task_one_num));
						task1_list_map.add((int)task_one_num, _childValue);
					}
					task_one_num++;
				}
				your_task_list.setAdapter(new Your_task_listAdapter(task1_list_map));
				task_status_num = 0;
				for(int _repeat137 = 0; _repeat137 < (int)(taskStatus.size()); _repeat137++) {
					if (taskStatus.get((int)task_status_num).get("task1 key").toString().equals(_childKey)) {
						taskStatus.remove((int)(task_status_num));
						taskStatus.add((int)task_status_num, _childValue);
					}
					task_status_num++;
				}
				worker_task_status_list.setAdapter(new Worker_task_status_listAdapter(taskStatus));
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				task_status_num = 0;
				for(int _repeat11 = 0; _repeat11 < (int)(taskStatus.size()); _repeat11++) {
					if (taskStatus.get((int)task_status_num).get("task1 key").toString().equals(_childKey)) {
						taskStatus.remove((int)(task_status_num));
						taskStatus.add((int)task_status_num, _childValue);
					}
					task_status_num++;
				}
				worker_task_status_list.setAdapter(new Worker_task_status_listAdapter(taskStatus));
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		Task.addChildEventListener(_Task_child_listener);
		
		_Task2_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				task1_list_map.add(_childValue);
				your_task_list.setAdapter(new Your_task_listAdapter(task1_list_map));
				taskStatus.add(_childValue);
				worker_task_status_list.setAdapter(new Worker_task_status_listAdapter(taskStatus));
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				task_two_num = 0;
				for(int _repeat11 = 0; _repeat11 < (int)(task1_list_map.size()); _repeat11++) {
					if (task1_list_map.get((int)task_two_num).get("task2 key").toString().equals(_childKey)) {
						task1_list_map.remove((int)(task_two_num));
						task1_list_map.add((int)task_two_num, _childValue);
					}
					task_two_num++;
				}
				your_task_list.setAdapter(new Your_task_listAdapter(task1_list_map));
				task_status2_num = 0;
				for(int _repeat26 = 0; _repeat26 < (int)(taskStatus.size()); _repeat26++) {
					if (taskStatus.get((int)task_status2_num).get("task2 key").toString().equals(_childKey)) {
						taskStatus.remove((int)(task_status2_num));
						taskStatus.add((int)task_status2_num, _childValue);
					}
					task_status2_num++;
				}
				worker_task_status_list.setAdapter(new Worker_task_status_listAdapter(taskStatus));
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				task_two_num = 0;
				for(int _repeat11 = 0; _repeat11 < (int)(task1_list_map.size()); _repeat11++) {
					if (task1_list_map.get((int)task_two_num).get("task2 key").toString().equals(_childKey)) {
						task1_list_map.remove((int)(task_two_num));
						task1_list_map.add((int)task_two_num, _childValue);
					}
					task_two_num++;
				}
				your_task_list.setAdapter(new Your_task_listAdapter(task1_list_map));
				task_status2_num = 0;
				for(int _repeat26 = 0; _repeat26 < (int)(taskStatus.size()); _repeat26++) {
					if (taskStatus.get((int)task_status2_num).get("task2 key").toString().equals(_childKey)) {
						taskStatus.remove((int)(task_status2_num));
						taskStatus.add((int)task_status2_num, _childValue);
					}
					task_status2_num++;
				}
				worker_task_status_list.setAdapter(new Worker_task_status_listAdapter(taskStatus));
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		Task2.addChildEventListener(_Task2_child_listener);
		
		_history_child_listener = new ChildEventListener() {
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
		history.addChildEventListener(_history_child_listener);
		
		_Request_Connection_request_listener = new RequestNetwork.RequestListener() {
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
		
		_db_accounts_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				_Material3Loader(false, "");
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("deposit balance")) {
						main_balance = Double.parseDouble(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("deposit balance").toString())))));
					}
					if (_childValue.containsKey("logo url")) {
						logoUrlStr = _childValue.get("logo url").toString();
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("deposit balance")) {
						main_balance = Double.parseDouble(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("deposit balance").toString())))));
					}
					if (_childValue.containsKey("logo url")) {
						logoUrlStr = _childValue.get("logo url").toString();
					}
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
		db_accounts.addChildEventListener(_db_accounts_child_listener);
		
		authentication_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		authentication_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		authentication_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		authentication_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		authentication_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		authentication_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		authentication_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_authentication_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_authentication_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_authentication_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		//      Start part: Backend
		progressDialog = new MaterialProgressDialog(TaskActivity.this);
		// Change the navigation bar color for Android 6.0 and above
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
				backIntent.setClass(getApplicationContext(), DashboardActivity.class);
				ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(backIntent, options.toBundle());
				finish();
						            }
				        });
		//      End part: On back
		//      End part: Backend
		//      Start part: Material 3 AutoCompleteTextView Setup
		country_list_string.add("All Countries");
		country_list_string.add("Bangladesh");
		country_list_string.add("Saudi Arabia");
		country_list_string.add("Indonesia");
		country_list_string.add("United Kingdom");
		country_list_string.add("United States");
		country_list_string.add("Canada");
		country_list_string.add("Russia");
		country_list_string.add("Japan");
		country_list_string.add("India");
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_dropdown_item, country_list_string);
		country_txt.setAdapter(adapter);
		//      End part: Material 3 AutoCompleteTextView Setup
		//      Start part: others
		access_token = true;
		mode_change = true;
		website_link_check = false;
		destination_link_check = false;
		your_task_list.setLayoutManager(new LinearLayoutManager(this));
		worker_task_status_list.setLayoutManager(new LinearLayoutManager(this));
		_Material3Loader(true, "Getting Data...");
		your_task_list.setLayoutManager(new LinearLayoutManager(this));
		worker_task_status_list.setLayoutManager(new LinearLayoutManager(this));
		//      End part: others
		//      Start part: Material 3 Slider Setup
		default_worker = "0.015";
		default_visits_times = "0.000";
		default_fee = "0.0018";
		default_total = "0.017";
		timeInSeconds = "15";
		workers_total.setText(default_worker);
		visit_time_total.setText(default_visits_times);
		fee_total.setText(default_fee);
		total_txt.setText(default_total);
		slider_workers.addOnChangeListener((slider, value, fromUser) -> {
			            int workers = (int) value;
			            slider_worker_txt.setText("" + workers);
			try {
				workers_total.setText(new DecimalFormat("0.000").format(Double.parseDouble(slider_worker_txt.getText().toString()) * Double.parseDouble(slider_workerpay_txt.getText().toString())));
				fee_total.setText(new DecimalFormat("0.0000").format(Double.parseDouble(slider_worker_txt.getText().toString()) * 0.0006d));
				if (slider1_body.getVisibility() == View.VISIBLE) {
					total_get = new DecimalFormat("0.000").format(Double.parseDouble(workers_total.getText().toString()) + Double.parseDouble(visit_time_total.getText().toString()));
					total_txt.setText(new DecimalFormat("0.000").format(Double.parseDouble(total_get) + Double.parseDouble(fee_total.getText().toString())));
				} else {
					total_txt.setText(new DecimalFormat("0.000").format(Double.parseDouble(workers_total.getText().toString()) + Double.parseDouble(fee_total.getText().toString())));
				}
			} catch (Exception e) {
				 
			}
		});
		DecimalFormat decimalFormat = new DecimalFormat("#.###");
		slider_worker_pay.addOnChangeListener((slider, value, fromUser) -> {
			            String formattedValue = decimalFormat.format(value);
			            slider_workerpay_txt.setText("" + formattedValue);
			try {
				workers_total.setText(new DecimalFormat("0.000").format(Double.parseDouble(slider_worker_txt.getText().toString()) * Double.parseDouble(slider_workerpay_txt.getText().toString())));
				fee_total.setText(new DecimalFormat("0.0000").format(Double.parseDouble(slider_worker_txt.getText().toString()) * 0.0006d));
				if (slider1_body.getVisibility() == View.VISIBLE) {
					total_get = new DecimalFormat("0.000").format(Double.parseDouble(workers_total.getText().toString()) + Double.parseDouble(visit_time_total.getText().toString()));
					total_txt.setText(new DecimalFormat("0.000").format(Double.parseDouble(total_get) + Double.parseDouble(fee_total.getText().toString())));
				} else {
					total_txt.setText(new DecimalFormat("0.000").format(Double.parseDouble(workers_total.getText().toString()) + Double.parseDouble(fee_total.getText().toString())));
				}
			} catch (Exception e) {
				 
			}
		});
		slider_time.addOnChangeListener((slider, value, fromUser) -> {
			    int totalSeconds = (int) value;
			    int minutes = totalSeconds / 60;
			    int seconds = totalSeconds % 60;
			
			    String formattedTime = String.format("%d:%02d", minutes, seconds);
			    slider_time_txt.setText(formattedTime);
			
			    timeInSeconds = String.valueOf(totalSeconds);
			    timeInSecondTxt.setText(timeInSeconds);
			try {
				visit_time_total_get = new DecimalFormat("0.000").format(Double.parseDouble(timeInSeconds) / 3000);
				visit_time_total.setText(new DecimalFormat("0.000").format(Double.parseDouble(visit_time_total_get) - 0.005d));
				total_get = new DecimalFormat("0.000").format(Double.parseDouble(workers_total.getText().toString()) + Double.parseDouble(visit_time_total.getText().toString()));
				total_txt.setText(new DecimalFormat("0.000").format(Double.parseDouble(total_get) + Double.parseDouble(fee_total.getText().toString())));
			} catch (Exception e) {
				 
			}
		});
		//      End part: Material 3 Slider Setup
		//      Start part: Notification permission
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
		//      End part: Notification permission
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			timer.cancel();
			timer2.cancel();
			DeleteTimer.cancel();
			deleteTimer2.cancel();
		} catch (Exception e) {
			 
		}
	}
	public void _TransitionManager(final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _MaterialErrorDialog(final String _massage) {
		MaterialAlertDialogBuilder error = new MaterialAlertDialogBuilder(TaskActivity.this);
		error.setTitle("Error!");
		error.setMessage(_massage);
		error.setIcon(R.drawable.icon_error_twotone);
		error.setNegativeButton("Close", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				_dialog.dismiss();
			}
		});
		error.create().show();
	}
	
	
	public void _Material3Loader(final boolean _variable, final String _title) {
		if (_variable) {
			progressDialog.show(_title);
		} else {
			
			progressDialog.dismiss();
		}
	}
	
	
	public void _notification(final String _title, final String _body, final String _imageUrl, final String _token, final String _topic, final String _extraText) {
		notification_map = new HashMap<>();
		notification_map.put("title", _title);
		notification_map.put("body", _body);
		if (!_imageUrl.equals("")) {
			notification_map.put("image", _imageUrl);
		}
		if (!_extraText.equals("")) {
			notification_map.put("extraData", _token);
		}
		if (_token.equals("")) {
			    String topic = _topic;
			    String token = "";
			    String projectId = ProjectID;
			    String tokenaccess = accessToken; 
			    FCMNotificationSender.sendNotification(notification_map, topic, token, projectId, tokenaccess, new FCMNotificationSender.OnResponse() {
				        @Override
				        public void onSuccess(String response) {
					            onSuccess = response;
					        }
				
				        @Override
				        public void onError(String error) {
					            onError = error;
					            _MaterialErrorDialog(onError);
					        }
				    });
		} else {
			    String token = _token;
			    String topic = "";
			    String projectId = ProjectID;
			    String tokenaccess = accessToken; 
			    FCMNotificationSender.sendNotification(notification_Map, topic, token, projectId, tokenaccess, new FCMNotificationSender.OnResponse() {
				        @Override
				        public void onSuccess(String response) {
					            onSuccess = response;
					        }
				
				        @Override
				        public void onError(String error) {
					            onError = error;
					            _MaterialErrorDialog(onError);
					        }
				    });
		}
	}
	
	
	public void _style(final View _view, final double _radius, final double _shadow, final String _color, final boolean _ripple) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setElevation((int)_shadow);
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9e9e9e")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		} else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setBackground(gd);
			_view.setElevation((int)_shadow);
		}
	}
	
	
	public void _DeleteNow(final double _position, final ArrayList<HashMap<String, Object>> _ListMap) {
		//      Start part: Show bottom sheet dialog
		final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(TaskActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.edit_delete_bottom_sheet,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				if (bottomSheetDialog.getWindow() != null) {
			        bottomSheetDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
			        }
		        
		        LinearLayout edit_button = (LinearLayout) bottomSheetView.findViewById(R.id.edit_button);
		        LinearLayout delete_button = (LinearLayout) bottomSheetView.findViewById(R.id.delete_button);
		        LinearLayout top = (LinearLayout) bottomSheetView.findViewById(R.id.top);
		        LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
		        MaterialDivider divider = (MaterialDivider) bottomSheetView.findViewById(R.id.divider);
		        TextView delete_txt = (TextView) bottomSheetView.findViewById(R.id.delete_txt);
		_style(top, 360, 0, "#E0E0E0", false);
		_style(bg, 50, 0, "#182027", false);
		_style(delete_button, 20, 0, "#182027", true);
		edit_button.setVisibility(View.GONE);
		divider.setVisibility(View.GONE);
		delete_txt.setText("Delete Now");
		    	delete_button.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
								bottomSheetDialog.dismiss();
				//      Start part: delete_button
				try {
					_Material3Loader(true, "Deleting......");
					if (_ListMap.get((int)_position).containsKey("task1 key")) {
						Task.child(_ListMap.get((int)_position).get("task1 key").toString()).removeValue();
					} else {
						if (_ListMap.get((int)_position).containsKey("task2 key")) {
							Task2.child(_ListMap.get((int)_position).get("task2 key").toString()).removeValue();
						}
					}
					deleteTimer2 = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_Material3Loader(false, "");
									successIntent.setClass(getApplicationContext(), SuccessActivity.class);
									successIntent.putExtra("activity", "deleted task");
									ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskActivity.this, R.anim.fade_in, R.anim.fade_out);
									startActivity(successIntent, options.toBundle());
									deleteTimer2.cancel();
									finish();
								}
							});
						}
					};
					_timer.schedule(deleteTimer2, (int)(3000));
				} catch (Exception e) {
					 
				}
				//      End part: delete_button
						}
				});
				bottomSheetDialog.setCancelable(true);
				bottomSheetDialog.show();
		//      End part: Show bottom sheet dialog
	}
	
	public class Your_task_listAdapter extends RecyclerView.Adapter<Your_task_listAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Your_task_listAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.task_cus, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout main_body = _view.findViewById(R.id.main_body);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final ImageView logo = _view.findViewById(R.id.logo);
			final TextView title = _view.findViewById(R.id.title);
			final com.google.android.material.card.MaterialCardView dollar_layout = _view.findViewById(R.id.dollar_layout);
			final TextView dollar = _view.findViewById(R.id.dollar);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final TextView second_txt = _view.findViewById(R.id.second_txt);
			final TextView second = _view.findViewById(R.id.second);
			final LinearLayout linear_gone = _view.findViewById(R.id.linear_gone);
			final TextView person = _view.findViewById(R.id.person);
			final TextView person_txt = _view.findViewById(R.id.person_txt);
			final ProgressBar progressbar2 = _view.findViewById(R.id.progressbar2);
			
			try {
				if (_data.get((int)_position).containsKey("Deletion Timer")) {
					main_body.setVisibility(View.GONE);
				} else {
					if (_data.get((int)_position).get("user id").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						//      Start part: logo get
						if (_data.get((int)_position).containsKey("img url")) {
							if (_data.get((int)_position).get("img url").toString().equals("")) {
								logo.setImageResource(R.drawable.profile_logo);
							} else {
								Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("img url").toString())).into(logo);
							}
						}
						//      End part: logo get
						//      Start part: Show additional
						if (_data.get((int)_position).containsKey("time")) {
							second.setText(_data.get((int)_position).get("time").toString().concat(" Second"));
						} else {
							second.setText("Unlimited");
						}
						if (_data.get((int)_position).containsKey("title")) {
							title.setText(_data.get((int)_position).get("title").toString());
						}
						if (_data.get((int)_position).containsKey("worker pay")) {
							dollar.setText(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_data.get((int)_position).get("worker pay").toString())))).concat("$"));
						}
						//      End part: Show additional
						//      Start part: Progress bar settings
						if (_data.get((int)_position).get("workers counting").toString().equals(_data.get((int)_position).get("worker max").toString())) {
							person_txt.setVisibility(View.GONE);
							person.setText("Completed!");
							progressbar2.setMax((int)Double.parseDouble(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("worker max").toString())))));
							progressbar2.setProgress((int)Double.parseDouble(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("workers counting").toString())))));
						} else {
							person_txt.setVisibility(View.VISIBLE);
							person.setText(_data.get((int)_position).get("workers counting").toString().concat("/".concat(_data.get((int)_position).get("worker max").toString())));
							progressbar2.setMax((int)Double.parseDouble(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("worker max").toString())))));
							progressbar2.setProgress((int)Double.parseDouble(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("workers counting").toString())))));
						}
						//      End part: Progress bar settings
						//      Start part: Go next activity
						main_body.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								if (_data.get((int)_position).get("workers counting").toString().equals(_data.get((int)_position).get("worker max").toString())) {
									//      Start part: Material 3 Bottom Sheet Dialog
									final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(TaskActivity.this);
											
											View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.edit_delete_bottom_sheet,null );
											bottomSheetDialog.setContentView(bottomSheetView);
											
											if (bottomSheetDialog.getWindow() != null) {
										        bottomSheetDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
										        }
									
									        LinearLayout edit_button = (LinearLayout) bottomSheetView.findViewById(R.id.edit_button);
									        LinearLayout delete_button = (LinearLayout) bottomSheetView.findViewById(R.id.delete_button);
									        LinearLayout top = (LinearLayout) bottomSheetView.findViewById(R.id.top);
									        LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
									        MaterialDivider divider = (MaterialDivider) bottomSheetView.findViewById(R.id.divider);
									_style(top, 360, 0, "#E0E0E0", false);
									_style(bg, 50, 0, "#182027", false);
									_style(delete_button, 20, 0, "#182027", true);
									edit_button.setVisibility(View.GONE);
									divider.setVisibility(View.GONE);
									    	delete_button.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
															bottomSheetDialog.dismiss();
											//      Start part: Delete Button Setup
											MaterialAlertDialogBuilder deleteDialog = new MaterialAlertDialogBuilder(TaskActivity.this);
											deleteDialog.setTitle("Are you sure you want to delete this task?");
											deleteDialog.setMessage("If there is any pending task then pay it first. So if you forget to delete it, we can't give it anymore.");
											deleteDialog.setIcon(R.drawable.icon_delete);
											deleteDialog.setPositiveButton("Ok, Delete", new DialogInterface.OnClickListener() {
												@Override
												public void onClick(DialogInterface _dialog, int _which) {
													_dialog.dismiss();
												}
											});
											deleteDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
												@Override
												public void onClick(DialogInterface _dialog, int _which) {
													_dialog.dismiss();
												}
											});
											deleteDialog.create().show();
											//      End part: Delete Button Setup
													}
											});
											bottomSheetDialog.setCancelable(true);
											bottomSheetDialog.show();
									//      End part: Material 3 Bottom Sheet Dialog
								} else {
									//      Start part: Material 3 Bottom Sheet Dialog
									final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(TaskActivity.this);
											
											View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.edit_delete_bottom_sheet,null );
											bottomSheetDialog.setContentView(bottomSheetView);
											
									        if (bottomSheetDialog.getWindow() != null) {
										        bottomSheetDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
										        }
									
									        LinearLayout edit_button = (LinearLayout) bottomSheetView.findViewById(R.id.edit_button);
									        LinearLayout delete_button = (LinearLayout) bottomSheetView.findViewById(R.id.delete_button);
									        LinearLayout top = (LinearLayout) bottomSheetView.findViewById(R.id.top);
									        LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
									_style(top, 360, 0, "#E0E0E0", false);
									_style(bg, 50, 0, "#182027", false);
									_style(edit_button, 20, 0, "#182027", true);
									_style(delete_button, 20, 0, "#182027", true);
									edit_button.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
															 bottomSheetDialog.dismiss();
											//      Start part: Edit Button Setup
											//      Start part: Material 3 text input layout alert dialog
											MaterialAlertDialogBuilder editDialog = new MaterialAlertDialogBuilder(TaskActivity.this);
											editDialog.setTitle("Are you sure you want to some change's this task?");
											editDialog.setMessage("Type your task title and description.");
											editDialog.setIcon(R.drawable.icon_mode_edit);
											View customView = LayoutInflater.from(TaskActivity.this).inflate(R.layout.m3_two_edittext_dialog, null);
											
											final EditText editText1 = customView.findViewById(R.id.editTextTaskId);
											final EditText editText2 = customView.findViewById(R.id.editTextReason);
											final TextInputLayout textinputlayout1 = customView.findViewById(R.id.textinputlayout1);
											final TextInputLayout textinputlayout2 = customView.findViewById(R.id.textinputlayout2);
											
											editDialog.setView(customView);
											editText1.setText(_data.get((int)_position).get("title").toString());
											editText2.setText(_data.get((int)_position).get("description").toString());
											//      Start part: Ok button
											editDialog.setPositiveButton("Ok, Change", new DialogInterface.OnClickListener() {
													@Override
													public void onClick(DialogInterface _dialog, int _which) {
													
													_dialog.dismiss();
													if (editText1.getText().toString().equals("")) {
														_MaterialErrorDialog("Enter some text!!");
													} else {
														if (editText1.getText().toString().length() > textinputlayout1.getCounterMaxLength()) {
															_MaterialErrorDialog("Text exceeds maximum length!");
														} else {
															if (editText2.getText().toString().equals("")) {
																_MaterialErrorDialog("Enter some text!!");
															} else {
																if (editText2.getText().toString().length() > textinputlayout2.getCounterMaxLength()) {
																	_MaterialErrorDialog("Text exceeds maximum length!");
																} else {
																	if (_data.get((int)_position).containsKey("task1 key")) {
																		_Material3Loader(true, "Changing....");
																		task1_map = new HashMap<>();
																		task1_map.put("title", editText1.getText().toString());
																		task1_map.put("description", editText2.getText().toString());
																		Task.child(_data.get((int)_position).get("task1 key").toString()).updateChildren(task1_map);
																		task1_map.clear();
																		timer2 = new TimerTask() {
																			@Override
																			public void run() {
																				runOnUiThread(new Runnable() {
																					@Override
																					public void run() {
																						_Material3Loader(false, "");
																						success.setClass(getApplicationContext(), SuccessActivity.class);
																						success.putExtra("activity2", "Your task changes has been success!");
																						ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskActivity.this, R.anim.fade_in, R.anim.fade_out);
																						startActivity(success, options.toBundle());
																						timer2.cancel();
																						finish();
																					}
																				});
																			}
																		};
																		_timer.schedule(timer2, (int)(3000));
																	} else {
																		if (_data.get((int)_position).containsKey("task2 key")) {
																			_Material3Loader(true, "Changing....");
																			task2_map = new HashMap<>();
																			task2_map.put("title", editText1.getText().toString());
																			task2_map.put("description", editText2.getText().toString());
																			Task2.child(_data.get((int)_position).get("task2 key").toString()).updateChildren(task2_map);
																			task2_map.clear();
																			timer2 = new TimerTask() {
																				@Override
																				public void run() {
																					runOnUiThread(new Runnable() {
																						@Override
																						public void run() {
																							_Material3Loader(false, "");
																							success.setClass(getApplicationContext(), SuccessActivity.class);
																							success.putExtra("activity2", "Your task changes has been success!");
																							ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskActivity.this, R.anim.fade_in, R.anim.fade_out);
																							startActivity(success, options.toBundle());
																							timer2.cancel();
																							finish();
																						}
																					});
																				}
																			};
																			_timer.schedule(timer2, (int)(3000));
																		}
																	}
																}
															}
														}
													}
													//      End part: Ok button
													//      Start part: Close button
													}
											});
											
											editDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
													@Override
													public void onClick(DialogInterface _dialog, int _which) {
															_dialog.dismiss();
													
													//      End part: Close button
													}
											});
											
											editDialog.create().show();
											
											//      End part: Material 3 text input layout alert dialog
											//      End part: Edit Button Setup
													}
											});
									    	delete_button.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
															bottomSheetDialog.dismiss();
											//      Start part: Delete Button Setup
											MaterialAlertDialogBuilder deleteDialog = new MaterialAlertDialogBuilder(TaskActivity.this);
											deleteDialog.setTitle("Are you sure you want to delete this task?");
											deleteDialog.setMessage("If there is any pending task then pay it first. So if you forget to delete it, we can't give it anymore. Admin will review the task and then delete it completely.");
											deleteDialog.setIcon(R.drawable.icon_delete);
											deleteDialog.setPositiveButton("Ok, Delete", new DialogInterface.OnClickListener() {
												@Override
												public void onClick(DialogInterface _dialog, int _which) {
													DeletionMap = new HashMap<>();
													date_get = Calendar.getInstance();
													date_get.add(Calendar.HOUR, (int)(1));
													DeletionMap.put("Deletion Timer", String.valueOf((long)(date_get.getTimeInMillis())));
													if (_data.get((int)_position).containsKey("task1 key")) {
														Task.child(_data.get((int)_position).get("task1 key").toString()).updateChildren(DeletionMap);
													} else {
														if (_data.get((int)_position).containsKey("task2 key")) {
															Task2.child(_data.get((int)_position).get("task2 key").toString()).updateChildren(DeletionMap);
														}
													}
													DeletionMap.clear();
													main_body.setVisibility(View.GONE);
													_dialog.dismiss();
												}
											});
											deleteDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
												@Override
												public void onClick(DialogInterface _dialog, int _which) {
													_dialog.dismiss();
												}
											});
											deleteDialog.create().show();
											//      End part: Delete Button Setup
													}
											});
											bottomSheetDialog.setCancelable(true);
											bottomSheetDialog.show();
									//      End part: Material 3 Bottom Sheet Dialog
								}
							}
						});
						//      End part: Go next activity
					} else {
						main_body.setVisibility(View.GONE);
					}
				}
			} catch (Exception e) {
				 
			}
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}
	
	public class Worker_task_status_listAdapter extends RecyclerView.Adapter<Worker_task_status_listAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Worker_task_status_listAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.task_cus, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout main_body = _view.findViewById(R.id.main_body);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final ImageView logo = _view.findViewById(R.id.logo);
			final TextView title = _view.findViewById(R.id.title);
			final com.google.android.material.card.MaterialCardView dollar_layout = _view.findViewById(R.id.dollar_layout);
			final TextView dollar = _view.findViewById(R.id.dollar);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final TextView second_txt = _view.findViewById(R.id.second_txt);
			final TextView second = _view.findViewById(R.id.second);
			final LinearLayout linear_gone = _view.findViewById(R.id.linear_gone);
			final TextView person = _view.findViewById(R.id.person);
			final TextView person_txt = _view.findViewById(R.id.person_txt);
			final ProgressBar progressbar2 = _view.findViewById(R.id.progressbar2);
			
			try {
				if (_data.get((int)_position).containsKey("Deletion Timer")) {
					main_body.setVisibility(View.VISIBLE);
					if (_data.get((int)_position).get("user id").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						//      Start part: logo get
						if (_data.get((int)_position).containsKey("img url")) {
							if (_data.get((int)_position).get("img url").toString().equals("")) {
								logo.setImageResource(R.drawable.profile_logo);
							} else {
								Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("img url").toString())).into(logo);
							}
						}
						//      End part: logo get
						//      Start part: Show additional
						if (_data.get((int)_position).containsKey("title")) {
							title.setText(_data.get((int)_position).get("title").toString());
						}
						if (_data.get((int)_position).containsKey("worker pay")) {
							dollar.setText(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_data.get((int)_position).get("worker pay").toString())))).concat("$"));
						}
						progressbar2.setVisibility(View.GONE);
						second_txt.setVisibility(View.GONE);
						second.setVisibility(View.GONE);
						linear_gone.setVisibility(View.GONE);
						DeleteTimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										if (_data.get((int)_position).get("Deletion Timer").toString().equals("none")) {
											person_txt.setVisibility(View.GONE);
											person.setText("You can delete it now!");
											person.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSurface));
										} else {
											date_get = Calendar.getInstance();
											if (date_get.getTimeInMillis() < Double.parseDouble(_data.get((int)_position).get("Deletion Timer").toString())) {
												person_txt.setVisibility(View.GONE);
												person.setText("You can delete it completely after an hour.");
												person.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorError));
											} else {
												DeleteTimer.cancel();
												DeletionMap = new HashMap<>();
												DeletionMap.put("Deletion Timer", "none");
												if (_data.get((int)_position).containsKey("task1 key")) {
													Task.child(_data.get((int)_position).get("task1 key").toString()).updateChildren(DeletionMap);
												} else {
													if (_data.get((int)_position).containsKey("task2 key")) {
														Task2.child(_data.get((int)_position).get("task2 key").toString()).updateChildren(DeletionMap);
													}
												}
												DeletionMap.clear();
												person_txt.setVisibility(View.GONE);
												person.setText("You can delete it now!");
												person.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSurface));
											}
										}
									}
								});
							}
						};
						_timer.scheduleAtFixedRate(DeleteTimer, (int)(0), (int)(1000));
						//      End part: Show additional
						//      Start part: Go next
						main_body.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								if (_data.get((int)_position).get("Deletion Timer").toString().equals("none")) {
									_DeleteNow(_position, _data);
								} else {
									date_get = Calendar.getInstance();
									if (!(date_get.getTimeInMillis() < Double.parseDouble(_data.get((int)_position).get("Deletion Timer").toString()))) {
										DeleteTimer.cancel();
										_DeleteNow(_position, _data);
									}
								}
							}
						});
						//      End part: Go next
					} else {
						main_body.setVisibility(View.GONE);
					}
				} else {
					main_body.setVisibility(View.GONE);
				}
			} catch (Exception e) {
				 
			}
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}
}