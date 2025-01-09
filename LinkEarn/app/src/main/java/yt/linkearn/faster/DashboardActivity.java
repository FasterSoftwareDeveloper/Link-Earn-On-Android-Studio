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
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.google.android.material.search.SearchBar;
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
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.text.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DashboardActivity extends AppCompatActivity {

private MaterialProgressDialog progressDialog;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private DrawerLayout _drawer;
	private double task_one_num = 0;
	private double task_two_num = 0;
	private String app_version = "";
	private String database_app_version = "";
	private boolean maintenance = false;
	private boolean appUpdate = false;
	private String privacy_policy_url = "";
	private String app_about = "";
	private String telegram_url = "";
	private String terms_url = "";
	private HashMap<String, Object> tokenUpdate = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> task1_list_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> accounts_list = new ArrayList<>();
	private ArrayList<String> complete_list = new ArrayList<>();
	private ArrayList<String> account_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> task2_list_map = new ArrayList<>();
	private ArrayList<String> complete_list2 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> TaskStatusListView = new ArrayList<>();
	
	private NestedScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear4;
	private BottomNavigationView bottom_nav;
	private LinearLayout linear2;
	private SearchBar search_bar;
	private RecyclerView task1;
	private RecyclerView task2;
	private RecyclerView task_status;
	private LinearLayout _drawer_background;
	private LinearLayout _drawer_HeaderBg;
	private NavigationView _drawer_navigationView;
	private CircleImageView _drawer_logo;
	private LinearLayout _drawer_ln_3;
	private TextView _drawer_Full_Name;
	private TextView _drawer_Balance;
	
	private Intent yourtask_intent = new Intent();
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
	
	private DatabaseReference db_accounts = _firebase.getReference("db_accounts");
	private ChildEventListener _db_accounts_child_listener;
	private DatabaseReference Task = _firebase.getReference("Task/task1");
	private ChildEventListener _Task_child_listener;
	private DatabaseReference Task2 = _firebase.getReference("Task/task2");
	private ChildEventListener _Task2_child_listener;
	private Intent task1_intent = new Intent();
	private Intent task2_intent = new Intent();
	private Intent logout_Intent = new Intent();
	private DatabaseReference CompletedTask1 = _firebase.getReference("Task/completedTask1");
	private ChildEventListener _CompletedTask1_child_listener;
	private DatabaseReference CompletedTask2 = _firebase.getReference("Task/completedTask2");
	private ChildEventListener _CompletedTask2_child_listener;
	private Intent riskIntent = new Intent();
	private Intent account_intent = new Intent();
	private Intent leaderboard_intent = new Intent();
	private SharedPreferences save_data;
	private Intent backIntent = new Intent();
	private Intent othersIntent = new Intent();
	private DatabaseReference others = _firebase.getReference("others");
	private ChildEventListener _others_child_listener;
	private DatabaseReference history = _firebase.getReference("history");
	private ChildEventListener _history_child_listener;
	private Intent others2intent = new Intent();
	private Intent searchviewIntent = new Intent();
	private Intent wallet_intent = new Intent();
	private Intent privacyIntent = new Intent();
	private Intent termsIntent = new Intent();
	private Intent telegramIntent = new Intent();
	private AlertDialog.Builder successDialog;
	private AlertDialog.Builder shareIntent;
	private Intent share = new Intent();
	private DatabaseReference fasterpanel = _firebase.getReference("fasterpanel");
	private ChildEventListener _fasterpanel_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.dashboard);
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
		_drawer = findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(DashboardActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = findViewById(R.id._nav_view);
		
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		linear4 = findViewById(R.id.linear4);
		bottom_nav = findViewById(R.id.bottom_nav);
		linear2 = findViewById(R.id.linear2);
		search_bar = findViewById(R.id.search_bar);
		task1 = findViewById(R.id.task1);
		task2 = findViewById(R.id.task2);
		task_status = findViewById(R.id.task_status);
		_drawer_background = _nav_view.findViewById(R.id.background);
		_drawer_HeaderBg = _nav_view.findViewById(R.id.HeaderBg);
		_drawer_navigationView = _nav_view.findViewById(R.id.navigationView);
		_drawer_logo = _nav_view.findViewById(R.id.logo);
		_drawer_ln_3 = _nav_view.findViewById(R.id.ln_3);
		_drawer_Full_Name = _nav_view.findViewById(R.id.Full_Name);
		_drawer_Balance = _nav_view.findViewById(R.id.Balance);
		authentication = FirebaseAuth.getInstance();
		save_data = getSharedPreferences("save_data", Activity.MODE_PRIVATE);
		successDialog = new AlertDialog.Builder(this);
		shareIntent = new AlertDialog.Builder(this);
		
		search_bar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				searchviewIntent.setClass(getApplicationContext(), SearchviewActivity.class);
				searchviewIntent.putExtra("activity", "task");
				_StartTransitionActivity(searchviewIntent, search_bar, "searchBar");
			}
		});
		
		_db_accounts_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				try {
					_Material3Loader(false, "");
				} catch (Exception e) {
					 
				}
				if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
					if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						if (_childValue.containsKey("balance")) {
							_drawer_Balance.setText("Balance: ".concat(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("balance").toString()))))).concat("$"));
						}
						if (_childValue.containsKey("name")) {
							_drawer_Full_Name.setText(_childValue.get("name").toString());
						}
						if (_childValue.containsKey("risk type")) {
							if (_childValue.get("risk type").toString().equals("banned")) {
								riskIntent.setClass(getApplicationContext(), AccountBlockAndSuspendActivity.class);
								ActivityOptions options = ActivityOptions.makeCustomAnimation(DashboardActivity.this, R.anim.fade_in, R.anim.fade_out);
								startActivity(riskIntent, options.toBundle());
								finish();
							}
						}
						if (_childValue.containsKey("logo url")) {
							if (_childValue.get("logo url").toString().equals("")) {
								_drawer_logo.setImageResource(R.drawable.profile_logo);
							} else {
								Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo url").toString())).into(_drawer_logo);
							}
						}
						if (!_childValue.get("notification token").toString().equals(save_data.getString("notification token user", ""))) {
							try {
								tokenUpdate = new HashMap<>();
								tokenUpdate.put("notification token", save_data.getString("notification token user", ""));
								db_accounts.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(tokenUpdate);
								tokenUpdate.clear();
							} catch (Exception e) {
								 
							}
						}
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
					if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						if (_childValue.containsKey("balance")) {
							_drawer_Balance.setText("Balance: ".concat(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("balance").toString()))))).concat("$"));
						}
						if (_childValue.containsKey("name")) {
							_drawer_Full_Name.setText(_childValue.get("name").toString());
						}
						if (_childValue.containsKey("risk type")) {
							if (_childValue.get("risk type").toString().equals("banned")) {
								riskIntent.setClass(getApplicationContext(), AccountBlockAndSuspendActivity.class);
								ActivityOptions options = ActivityOptions.makeCustomAnimation(DashboardActivity.this, R.anim.fade_in, R.anim.fade_out);
								startActivity(riskIntent, options.toBundle());
								finish();
							}
						}
						if (_childValue.containsKey("logo url")) {
							if (_childValue.get("logo url").toString().equals("")) {
								_drawer_logo.setImageResource(R.drawable.profile_logo);
							} else {
								Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo url").toString())).into(_drawer_logo);
							}
						}
						if (!_childValue.get("notification token").toString().equals(save_data.getString("notification token user", ""))) {
							try {
								tokenUpdate = new HashMap<>();
								tokenUpdate.put("notification token", save_data.getString("notification token user", ""));
								db_accounts.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(tokenUpdate);
								tokenUpdate.clear();
							} catch (Exception e) {
								 
							}
						}
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
		
		_Task_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				task1_list_map.add(_childValue);
				task1.setAdapter(new Task1Adapter(task1_list_map));
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				task_one_num = 0;
				for(int _repeat63 = 0; _repeat63 < (int)(task1_list_map.size()); _repeat63++) {
					if (task1_list_map.get((int)task_one_num).get("task1 key").toString().equals(_childKey)) {
						task1_list_map.remove((int)(task_one_num));
						task1_list_map.add((int)task_one_num, _childValue);
					}
					task_one_num++;
				}
				task1.setAdapter(new Task1Adapter(task1_list_map));
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				task_one_num = 0;
				for(int _repeat11 = 0; _repeat11 < (int)(task1_list_map.size()); _repeat11++) {
					if (task1_list_map.get((int)task_one_num).get("task1 key").toString().equals(_childKey)) {
						task1_list_map.remove((int)(task_one_num));
						task1_list_map.add((int)task_one_num, _childValue);
					}
					task_one_num++;
				}
				task1.setAdapter(new Task1Adapter(task1_list_map));
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
				task2_list_map.add(_childValue);
				task2.setAdapter(new Task2Adapter(task2_list_map));
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				task_two_num = 0;
				for(int _repeat11 = 0; _repeat11 < (int)(task2_list_map.size()); _repeat11++) {
					if (task2_list_map.get((int)task_two_num).get("task2 key").toString().equals(_childKey)) {
						task2_list_map.remove((int)(task_two_num));
						task2_list_map.add((int)task_two_num, _childValue);
					}
					task_two_num++;
				}
				task2.setAdapter(new Task2Adapter(task2_list_map));
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
				for(int _repeat11 = 0; _repeat11 < (int)(task2_list_map.size()); _repeat11++) {
					if (task2_list_map.get((int)task_two_num).get("task2 key").toString().equals(_childKey)) {
						task2_list_map.remove((int)(task_two_num));
						task2_list_map.add((int)task_two_num, _childValue);
					}
					task_two_num++;
				}
				task2.setAdapter(new Task2Adapter(task2_list_map));
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		Task2.addChildEventListener(_Task2_child_listener);
		
		_CompletedTask1_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					complete_list.add(_childValue.get("task_key").toString());
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					complete_list.add(_childValue.get("task_key").toString());
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
		CompletedTask1.addChildEventListener(_CompletedTask1_child_listener);
		
		_CompletedTask2_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					complete_list2.add(_childValue.get("task_key").toString());
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					complete_list.add(_childValue.get("task_key").toString());
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
		CompletedTask2.addChildEventListener(_CompletedTask2_child_listener);
		
		_others_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("Maintenance")) {
					if (_childValue.get("Maintenance Mode").toString().equals("off")) {
						maintenance = false;
					} else {
						if (_childValue.get("Maintenance Mode").toString().equals("on")) {
							maintenance = true;
						}
					}
				}
				if (_childKey.equals("Update Manager")) {
					database_app_version = _childValue.get("version").toString();
					if ((Double.parseDouble(app_version) == Double.parseDouble(database_app_version)) || (Double.parseDouble(app_version) > Double.parseDouble(database_app_version))) {
						appUpdate = false;
					} else {
						if (Double.parseDouble(app_version) < Double.parseDouble(database_app_version)) {
							appUpdate = true;
						}
					}
				}
				if (maintenance && appUpdate) {
					othersIntent.setClass(getApplicationContext(), AppMaintenanceActivity.class);
					ActivityOptions maintenanceoptions = ActivityOptions.makeCustomAnimation(DashboardActivity.this, R.anim.fade_in, R.anim.fade_out);
					startActivity(othersIntent, maintenanceoptions.toBundle());
					finish();
				} else {
					if (maintenance) {
						others2intent.setClass(getApplicationContext(), AppMaintenanceActivity.class);
						ActivityOptions maintenancetwooptions = ActivityOptions.makeCustomAnimation(DashboardActivity.this, R.anim.fade_in, R.anim.fade_out);
						startActivity(others2intent, maintenancetwooptions.toBundle());
						finish();
					} else {
						if (appUpdate) {
							othersIntent.setClass(getApplicationContext(), AppUpdateActivity.class);
							ActivityOptions updateoptions = ActivityOptions.makeCustomAnimation(DashboardActivity.this, R.anim.fade_in, R.anim.fade_out);
							startActivity(othersIntent, updateoptions.toBundle());
							finish();
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
		
		_history_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				history.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						TaskStatusListView = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								TaskStatusListView.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						task_status.setAdapter(new Task_statusAdapter(TaskStatusListView));
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
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
		
		_fasterpanel_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("others")) {
					if (_childValue.containsKey("privacy policy url")) {
						privacy_policy_url = _childValue.get("privacy policy url").toString();
					}
					if (_childValue.containsKey("terms url")) {
						terms_url = _childValue.get("terms url").toString();
					}
					if (_childValue.containsKey("telegram url")) {
						telegram_url = _childValue.get("telegram url").toString();
					}
					if (_childValue.containsKey("app about")) {
						app_about = _childValue.get("app about").toString();
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
		//      Start part: Navigation Drawer
		progressDialog = new MaterialProgressDialog(DashboardActivity.this);
		final LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		_nav_view.setBackgroundColor(android.graphics.Color.TRANSPARENT);
		
		NavigationView navigationView = findViewById(R.id.navigationView);
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			    @Override
			    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				        int itemId = item.getItemId();
				        if (itemId == R.id.account_item) {
					            account_intent.setClass(getApplicationContext(), AccountActivity.class);
					            account_intent.putExtra("user id", FirebaseAuth.getInstance().getCurrentUser().getUid());
					            ActivityOptions accountOptions = ActivityOptions.makeCustomAnimation(DashboardActivity.this, R.anim.fade_in, R.anim.fade_out);
					            startActivity(account_intent, accountOptions.toBundle());
					            finish();
					        } else if (itemId == R.id.wallet_item) {
					            wallet_intent.setClass(getApplicationContext(), WalletActivity.class);
					            ActivityOptions walletOptions = ActivityOptions.makeCustomAnimation(DashboardActivity.this, R.anim.fade_in, R.anim.fade_out);
					            startActivity(wallet_intent, walletOptions.toBundle());
					            finish();
					        } else if (itemId == R.id.logout_item) {
					            FirebaseAuth.getInstance().signOut();
					            logout_Intent.setClass(getApplicationContext(), MainActivity.class);
					            ActivityOptions logoutOptions = ActivityOptions.makeCustomAnimation(DashboardActivity.this, R.anim.fade_in, R.anim.fade_out);
					            startActivity(logout_Intent, logoutOptions.toBundle());
					            finish();
					        } else if (itemId == R.id.about_4) {
					            MaterialAlertDialogBuilder successDialog = new MaterialAlertDialogBuilder(DashboardActivity.this);
					            successDialog.setTitle("App about.");
					            successDialog.setMessage(app_about);
					            successDialog.setIcon(R.drawable.icon_error_twotone);
					            successDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
						            @Override
						            public void onClick(DialogInterface _dialog, int _which) {
							            _dialog.dismiss();
							            }
						            });
					            successDialog.create().show();
					            
					        } else if (itemId == R.id.telegram_3) {
					            if (!telegram_url.equals("")) {
						            telegramIntent.setAction(Intent.ACTION_VIEW);
						            telegramIntent.setData(Uri.parse("https://".concat(telegram_url)));
						            startActivity(telegramIntent);
						            }
					            
					        } else if (itemId == R.id.terms_two) {
					            if (!terms_url.equals("")) {
						            termsIntent.setAction(Intent.ACTION_VIEW);
						            termsIntent.setData(Uri.parse("https://".concat(terms_url)));
						            startActivity(termsIntent);
						            }
					            
					        } else if (itemId == R.id.privacy_one) {
					            if (!privacy_policy_url.equals("")) {
						            privacyIntent.setAction(Intent.ACTION_VIEW);
						            privacyIntent.setData(Uri.parse("https://".concat(privacy_policy_url)));
						            startActivity(privacyIntent);
						            }
					            
					        } else if (itemId == R.id.yourtask_item) {
					            yourtask_intent.setClass(getApplicationContext(), TaskActivity.class);
					            ActivityOptions yourTaskOptions = ActivityOptions.makeCustomAnimation(DashboardActivity.this, R.anim.fade_in, R.anim.fade_out);
					            startActivity(yourtask_intent, yourTaskOptions.toBundle());
					            finish();
					        } else if (itemId == R.id.leaderboard_item) {
					            leaderboard_intent.setClass(getApplicationContext(), LeaderboardActivity.class);
					            ActivityOptions leaderboardOptions = ActivityOptions.makeCustomAnimation(DashboardActivity.this, R.anim.fade_in, R.anim.fade_out);
					            startActivity(leaderboard_intent, leaderboardOptions.toBundle());
					            finish();
					        }
				        _drawer.closeDrawer(GravityCompat.START);
				        return true;
				    }
		});
		_toolbar.setVisibility(View.GONE);
		//      End part: Navigation Drawer
		//      Start part: Bottom Navigation Bar
		bottom_nav.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
			    @Override
			    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				        int itemId = item.getItemId();
				        if (itemId == R.id.nav_WebsiteVisit) {
					            task1.setVisibility(View.VISIBLE);
					            task2.setVisibility(View.GONE);
					            task_status.setVisibility(View.GONE);
					            return true;
					        } else if (itemId == R.id.nav_shortlink) {
					            task1.setVisibility(View.GONE);
					            task2.setVisibility(View.VISIBLE);
					            task_status.setVisibility(View.GONE);
					            return true;
					        } else if (itemId == R.id.nav_taskstatus) {
					            task1.setVisibility(View.GONE);
					            task2.setVisibility(View.GONE);
					            task_status.setVisibility(View.VISIBLE);
					            return true;
					        } else {
					            return false;
					        }
				    }
		});
		//      End part: Bottom Navigation Bar
		//      Start part: search bar
		search_bar.setNavigationOnClickListener(v -> {
			            if (_drawer.isDrawerOpen(GravityCompat.START)) {
				                _drawer.closeDrawer(GravityCompat.START);
				            } else {
				                _drawer.openDrawer(GravityCompat.START);
				            }
			        });
		//      End part: search bar
		//      Start part: Others
		_GetTransition(search_bar, "backIntent");
		app_version = getString(R.string.app_version_name);
		_Material3Loader(true, "Getting Data...");
		task1.setLayoutManager(new LinearLayoutManager(this));
		task2.setLayoutManager(new LinearLayoutManager(this));
		task_status.setLayoutManager(new LinearLayoutManager(this));
		if (save_data.getString("anonymously check", "").equals("true")) {
			backIntent.setClass(getApplicationContext(), AuthenticationActivity.class);
			ActivityOptions options = ActivityOptions.makeCustomAnimation(DashboardActivity.this, R.anim.fade_in, R.anim.fade_out);
			startActivity(backIntent, options.toBundle());
			try {
				FirebaseAuth.getInstance().signOut();
			} catch (Exception e) {
				 
			}
			finish();
		}
		//      End part: Others
	}
	
	
	@Override
	public void onBackPressed() {
		if (_drawer.isDrawerOpen(GravityCompat.START)) {
			_drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}
	public void _Material3Loader(final boolean _variable, final String _title) {
		if (_variable) {
			progressDialog.show(_title);
		} else {
			
			progressDialog.dismiss();
		}
	}
	
	
	public void _TransitionManager(final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _StartTransitionActivity(final Intent _intent, final View _view, final String _name) {
		_view.setTransitionName(_name); 
		android.app.ActivityOptions optionsCompat = android.app.ActivityOptions.makeSceneTransitionAnimation(DashboardActivity.this, _view, _name); 
		startActivity(_intent, optionsCompat.toBundle());
	}
	
	
	public void _GetTransition(final View _view, final String _name) {
		_view.setTransitionName(_name); 
	}
	
	
	public void _ShareText(final Intent _intent, final String _text) {
		    _intent.setAction(Intent.ACTION_SEND);
		    _intent.setType("text/plain");
		    _intent.putExtra(Intent.EXTRA_TEXT, _text);
		    Intent chooser = Intent.createChooser(_intent, "Share via");
		    
		    if (_intent.resolveActivity(getPackageManager()) != null) {
			        startActivity(chooser);
			    }
	}
	
	public class Task1Adapter extends RecyclerView.Adapter<Task1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Task1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				if (_data.get((int)_position).get("user id").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					main_body.setVisibility(View.GONE);
				} else {
					if (_data.get((int)_position).containsKey("Deletion Timer")) {
						main_body.setVisibility(View.GONE);
					} else {
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
							main_body.setVisibility(View.GONE);
							person_txt.setVisibility(View.GONE);
							person.setText("Completed!");
							progressbar2.setMax((int)Double.parseDouble(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("worker max").toString())))));
							progressbar2.setProgress((int)Double.parseDouble(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("workers counting").toString())))));
						} else {
							main_body.setVisibility(View.VISIBLE);
							person_txt.setVisibility(View.VISIBLE);
							person.setText(_data.get((int)_position).get("workers counting").toString().concat("/".concat(_data.get((int)_position).get("worker max").toString())));
							progressbar2.setMax((int)Double.parseDouble(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("worker max").toString())))));
							progressbar2.setProgress((int)Double.parseDouble(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("workers counting").toString())))));
						}
						//      End part: Progress bar settings
						//      Start part: task complete check
						if (complete_list.contains(_data.get((int)_position).get("task1 key").toString())) {
							main_body.setVisibility(View.GONE);
						} else {
							main_body.setVisibility(View.VISIBLE);
						}
						//      End part: task complete check
						//      Start part: Go next activity
						main_body.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								if (!_data.get((int)_position).get("workers counting").toString().equals(_data.get((int)_position).get("worker max").toString())) {
									task1_intent.setClass(getApplicationContext(), TaskViewActivity.class);
									task1_intent.putExtra("key", _data.get((int)_position).get("task1 key").toString());
									ActivityOptions options = ActivityOptions.makeCustomAnimation(DashboardActivity.this, R.anim.fade_in, R.anim.fade_out);
									startActivity(task1_intent, options.toBundle());
									finish();
								}
							}
						});
						main_body.setOnLongClickListener(new View.OnLongClickListener() {
							@Override
							public boolean onLongClick(View _view) {
								MaterialAlertDialogBuilder shareIntent = new MaterialAlertDialogBuilder(DashboardActivity.this);
								shareIntent.setTitle("Share the task with friends!");
								shareIntent.setMessage("Just Share, The person you give this URL to must have this app installed on their mobile!");
								shareIntent.setIcon(R.drawable.icon_ios_share);
								shareIntent.setPositiveButton("Share now!", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										_dialog.dismiss();
										_ShareText(share, getString(R.string.taskDeepLinkDomain).concat(_data.get((int)_position).get("task1 key").toString()));
									}
								});
								shareIntent.create().show();
								return true;
							}
						});
						//      End part: Go next activity
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
	
	public class Task2Adapter extends RecyclerView.Adapter<Task2Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Task2Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				if (_data.get((int)_position).get("user id").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					main_body.setVisibility(View.GONE);
				} else {
					if (_data.get((int)_position).containsKey("Deletion Timer")) {
						main_body.setVisibility(View.GONE);
					} else {
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
						second.setText("Unlimited");
						if (_data.get((int)_position).containsKey("title")) {
							title.setText(_data.get((int)_position).get("title").toString());
						}
						if (_data.get((int)_position).containsKey("worker pay")) {
							dollar.setText(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_data.get((int)_position).get("worker pay").toString())))).concat("$"));
						}
						//      End part: Show additional
						//      Start part: Progress bar settings
						if (_data.get((int)_position).get("workers counting").toString().equals(_data.get((int)_position).get("worker max").toString())) {
							main_body.setVisibility(View.GONE);
							person_txt.setVisibility(View.GONE);
							person.setText("Completed!");
							progressbar2.setMax((int)Double.parseDouble(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("worker max").toString())))));
							progressbar2.setProgress((int)Double.parseDouble(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("workers counting").toString())))));
						} else {
							main_body.setVisibility(View.VISIBLE);
							person_txt.setVisibility(View.VISIBLE);
							person.setText(_data.get((int)_position).get("workers counting").toString().concat("/".concat(_data.get((int)_position).get("worker max").toString())));
							progressbar2.setMax((int)Double.parseDouble(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("worker max").toString())))));
							progressbar2.setProgress((int)Double.parseDouble(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("workers counting").toString())))));
						}
						//      End part: Progress bar settings
						//      Start part: task complete check
						if (complete_list2.contains(_data.get((int)_position).get("task2 key").toString())) {
							main_body.setVisibility(View.GONE);
						} else {
							main_body.setVisibility(View.VISIBLE);
						}
						//      End part: task complete check
						//      Start part: Go next activity
						main_body.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								if (!_data.get((int)_position).get("workers counting").toString().equals(_data.get((int)_position).get("worker max").toString())) {
									task2_intent.setClass(getApplicationContext(), TaskViewActivity.class);
									task2_intent.putExtra("key", _data.get((int)_position).get("task2 key").toString());
									ActivityOptions options = ActivityOptions.makeCustomAnimation(DashboardActivity.this, R.anim.fade_in, R.anim.fade_out);
									startActivity(task2_intent, options.toBundle());
									finish();
								}
							}
						});
						main_body.setOnLongClickListener(new View.OnLongClickListener() {
							@Override
							public boolean onLongClick(View _view) {
								MaterialAlertDialogBuilder shareIntent = new MaterialAlertDialogBuilder(DashboardActivity.this);
								shareIntent.setTitle("Share the task with friends!");
								shareIntent.setMessage("Just Share, The person you give this URL to must have this app installed on their mobile!");
								shareIntent.setIcon(R.drawable.icon_ios_share);
								shareIntent.setPositiveButton("Share now!", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										_dialog.dismiss();
										_ShareText(share, getString(R.string.taskDeepLinkDomain).concat(_data.get((int)_position).get("task2 key").toString()));
									}
								});
								shareIntent.create().show();
								return true;
							}
						});
						//      End part: Go next activity
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
	
	public class Task_statusAdapter extends RecyclerView.Adapter<Task_statusAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Task_statusAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
			
			logo.setVisibility(View.GONE);
			second_txt.setVisibility(View.GONE);
			second.setVisibility(View.GONE);
			linear_gone.setVisibility(View.GONE);
			person_txt.setVisibility(View.GONE);
			progressbar2.setVisibility(View.GONE);
			try {
				if (_data.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					main_body.setVisibility(View.VISIBLE);
					if (_data.get((int)_position).containsKey("title")) {
						title.setText(_data.get((int)_position).get("title").toString());
					}
					if (_data.get((int)_position).containsKey("balance")) {
						dollar.setText(_data.get((int)_position).get("balance").toString().concat("$"));
					}
					if (_data.get((int)_position).containsKey("date")) {
						person.setText("You completed! Date: ".concat(_data.get((int)_position).get("date").toString()));
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