package yt.linkearnfasterpanel.faster;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.startup.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.switchmaterial.SwitchMaterial;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class PanelActivity extends AppCompatActivity {

private MaterialProgressDialog progressDialog;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String fontName = "";
	private String typeace = "";
	private HashMap<String, Object> maintenance_Map = new HashMap<>();
	private double totalUsers_count = 0;
	private double totalUsers_num = 0;
	private double totalBlocked_count = 0;
	private double totalBlocked_num = 0;
	private double totalTask_num = 0;
	private double totalTask_count = 0;
	private double totalTask2_num = 0;
	private double totalTask2_count = 0;
	private double total_task1_get = 0;
	private double total_task2_get = 0;
	private double totalCompleteTask1_num = 0;
	private double totalCompleteTask1_count = 0;
	private double totalCompleteTask2_num = 0;
	private double totalCompleteTask2_count = 0;
	private double totalCompleteTask1_get = 0;
	private double totalCompleteTask2_get = 0;
	private double Withdraw_num = 0;
	private double Withdraw_count = 0;
	private double deposit_num = 0;
	private double deposit_count = 0;
	private double totalMethods_num = 0;
	private double totalMethods_count = 0;
	
	private ArrayList<HashMap<String, Object>> totalUsers_listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> totalBlocked_listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> totalTask_listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> totalTask2_listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> totalCompleteTask1_listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> totalCompleteTask2_listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> Withdraw_listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> deposit_listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> totalMethods_listmap = new ArrayList<>();
	
	private ScrollView vscroll2;
	private BottomNavigationView bottom_nav;
	private LinearLayout main;
	private LinearLayout dashboard_body;
	private LinearLayout settings_body;
	private MaterialCardView important_note;
	private MaterialCardView d1;
	private MaterialCardView d2;
	private MaterialCardView d3;
	private MaterialCardView d4;
	private MaterialCardView d6;
	private MaterialCardView d14;
	private MaterialCardView d8;
	private MaterialCardView d9;
	private MaterialCardView d10;
	private MaterialCardView d11;
	private MaterialCardView d12;
	private MaterialCardView d13;
	private LinearLayout linear65;
	private LinearLayout linear64;
	private LinearLayout linear63;
	private ImageView imageview22;
	private TextView textview17;
	private TextView textview18;
	private LinearLayout linear109;
	private LinearLayout linear46;
	private LinearLayout linear108;
	private LinearLayout linear48;
	private LinearLayout linear49;
	private ImageView imageview19;
	private TextView total_mony_txt;
	private TextView text;
	private LinearLayout linear21;
	private LinearLayout linear22;
	private LinearLayout linear25;
	private LinearLayout linear23;
	private LinearLayout linear24;
	private ImageView imageview15;
	private TextView total_user_txt;
	private TextView textview4;
	private LinearLayout linear155;
	private LinearLayout linear156;
	private LinearLayout linear157;
	private LinearLayout linear158;
	private LinearLayout linear159;
	private ImageView imageview46;
	private TextView text_blockeds;
	private TextView text_blocked;
	private LinearLayout linear27;
	private LinearLayout linear28;
	private LinearLayout linear29;
	private LinearLayout linear30;
	private LinearLayout linear31;
	private ImageView imageview16;
	private TextView total_task_txt;
	private TextView textview6;
	private LinearLayout linear97;
	private LinearLayout linear98;
	private LinearLayout linear99;
	private LinearLayout linear100;
	private LinearLayout linear101;
	private ImageView imageview28;
	private TextView total_task_completed;
	private TextView textview30;
	private LinearLayout linear175;
	private LinearLayout linear176;
	private LinearLayout linear177;
	private LinearLayout linear178;
	private LinearLayout linear179;
	private ImageView imageview51;
	private TextView total_methods_txt;
	private TextView textview53;
	private LinearLayout linear33;
	private LinearLayout linear34;
	private LinearLayout linear35;
	private LinearLayout linear36;
	private LinearLayout linear37;
	private ImageView imageview17;
	private TextView total_pending_pay_txt;
	private TextView txt2;
	private LinearLayout linear39;
	private LinearLayout linear40;
	private LinearLayout linear41;
	private LinearLayout linear42;
	private LinearLayout linear43;
	private ImageView imageview18;
	private TextView total_pay_succ_txt;
	private TextView textview10;
	private LinearLayout linear117;
	private LinearLayout linear118;
	private LinearLayout linear119;
	private LinearLayout linear120;
	private LinearLayout linear121;
	private ImageView imageview31;
	private TextView total_paynent_rejected;
	private TextView textview36;
	private LinearLayout linear51;
	private LinearLayout linear52;
	private LinearLayout linear53;
	private LinearLayout linear54;
	private LinearLayout linear55;
	private ImageView imageview20;
	private TextView total_pending_de_txt;
	private TextView textview14;
	private LinearLayout linear57;
	private LinearLayout linear58;
	private LinearLayout linear59;
	private LinearLayout linear60;
	private LinearLayout linear61;
	private ImageView imageview21;
	private TextView total_de_succ_txt;
	private TextView textview16;
	private LinearLayout linear111;
	private LinearLayout linear112;
	private LinearLayout linear113;
	private LinearLayout linear114;
	private LinearLayout linear115;
	private ImageView imageview30;
	private TextView total_de_rejected;
	private TextView textview34;
	private MaterialCardView linear164;
	private MaterialCardView linear84;
	private MaterialCardView linear126;
	private MaterialCardView linear130;
	private MaterialCardView linear134;
	private MaterialCardView linear138;
	private MaterialCardView linear142;
	private MaterialCardView linear146;
	private MaterialCardView linear160;
	private LinearLayout linear165;
	private LinearLayout linear166;
	private LinearLayout linear167;
	private ImageView imageview49;
	private TextView textview48;
	private TextView textview49;
	private LinearLayout linear86;
	private ImageView imageview26;
	private LinearLayout linear88;
	private LinearLayout linear89;
	private SwitchCompat switch1;
	private TextView textview26;
	private LinearLayout linear127;
	private ImageView imageview33;
	private LinearLayout linear128;
	private LinearLayout linear129;
	private ImageView imageview34;
	private TextView textview38;
	private LinearLayout linear131;
	private ImageView imageview35;
	private LinearLayout linear132;
	private LinearLayout linear133;
	private ImageView imageview36;
	private TextView textview39;
	private LinearLayout linear135;
	private ImageView imageview37;
	private LinearLayout linear136;
	private LinearLayout linear137;
	private ImageView imageview38;
	private TextView textview40;
	private LinearLayout linear139;
	private ImageView imageview39;
	private LinearLayout linear140;
	private LinearLayout linear141;
	private ImageView imageview40;
	private TextView textview41;
	private LinearLayout linear143;
	private ImageView imageview41;
	private LinearLayout linear144;
	private LinearLayout linear145;
	private ImageView imageview42;
	private TextView textview42;
	private LinearLayout linear147;
	private ImageView imageview43;
	private LinearLayout linear148;
	private LinearLayout linear149;
	private ImageView imageview44;
	private TextView textview43;
	private LinearLayout linear161;
	private ImageView imageview47;
	private LinearLayout linear162;
	private LinearLayout linear163;
	private ImageView imageview48;
	private TextView textview47;
	
	private Intent dIntent = new Intent();
	private Intent sIntent = new Intent();
	private DatabaseReference others = _firebase.getReference("others");
	private ChildEventListener _others_child_listener;
	private DatabaseReference db_accounts = _firebase.getReference("db_accounts");
	private ChildEventListener _db_accounts_child_listener;
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
	
	private DatabaseReference Task1 = _firebase.getReference("Task/task1");
	private ChildEventListener _Task1_child_listener;
	private DatabaseReference Task2 = _firebase.getReference("Task/task2");
	private ChildEventListener _Task2_child_listener;
	private DatabaseReference completedTask2 = _firebase.getReference("Task/completedTask2");
	private ChildEventListener _completedTask2_child_listener;
	private DatabaseReference completedTask1 = _firebase.getReference("Task/completedTask1");
	private ChildEventListener _completedTask1_child_listener;
	private TimerTask timer;
	private DatabaseReference withdraw = _firebase.getReference("financial/withdraw");
	private ChildEventListener _withdraw_child_listener;
	private DatabaseReference deposit = _firebase.getReference("financial/deposit");
	private ChildEventListener _deposit_child_listener;
	private DatabaseReference methods = _firebase.getReference("financial/methods");
	private ChildEventListener _methods_child_listener;
	private AlertDialog.Builder aboutDialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.panel);
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
		vscroll2 = findViewById(R.id.vscroll2);
		bottom_nav = findViewById(R.id.bottom_nav);
		main = findViewById(R.id.main);
		dashboard_body = findViewById(R.id.dashboard_body);
		settings_body = findViewById(R.id.settings_body);
		important_note = findViewById(R.id.important_note);
		d1 = findViewById(R.id.d1);
		d2 = findViewById(R.id.d2);
		d3 = findViewById(R.id.d3);
		d4 = findViewById(R.id.d4);
		d6 = findViewById(R.id.d6);
		d14 = findViewById(R.id.d14);
		d8 = findViewById(R.id.d8);
		d9 = findViewById(R.id.d9);
		d10 = findViewById(R.id.d10);
		d11 = findViewById(R.id.d11);
		d12 = findViewById(R.id.d12);
		d13 = findViewById(R.id.d13);
		linear65 = findViewById(R.id.linear65);
		linear64 = findViewById(R.id.linear64);
		linear63 = findViewById(R.id.linear63);
		imageview22 = findViewById(R.id.imageview22);
		textview17 = findViewById(R.id.textview17);
		textview18 = findViewById(R.id.textview18);
		linear109 = findViewById(R.id.linear109);
		linear46 = findViewById(R.id.linear46);
		linear108 = findViewById(R.id.linear108);
		linear48 = findViewById(R.id.linear48);
		linear49 = findViewById(R.id.linear49);
		imageview19 = findViewById(R.id.imageview19);
		total_mony_txt = findViewById(R.id.total_mony_txt);
		text = findViewById(R.id.text);
		linear21 = findViewById(R.id.linear21);
		linear22 = findViewById(R.id.linear22);
		linear25 = findViewById(R.id.linear25);
		linear23 = findViewById(R.id.linear23);
		linear24 = findViewById(R.id.linear24);
		imageview15 = findViewById(R.id.imageview15);
		total_user_txt = findViewById(R.id.total_user_txt);
		textview4 = findViewById(R.id.textview4);
		linear155 = findViewById(R.id.linear155);
		linear156 = findViewById(R.id.linear156);
		linear157 = findViewById(R.id.linear157);
		linear158 = findViewById(R.id.linear158);
		linear159 = findViewById(R.id.linear159);
		imageview46 = findViewById(R.id.imageview46);
		text_blockeds = findViewById(R.id.text_blockeds);
		text_blocked = findViewById(R.id.text_blocked);
		linear27 = findViewById(R.id.linear27);
		linear28 = findViewById(R.id.linear28);
		linear29 = findViewById(R.id.linear29);
		linear30 = findViewById(R.id.linear30);
		linear31 = findViewById(R.id.linear31);
		imageview16 = findViewById(R.id.imageview16);
		total_task_txt = findViewById(R.id.total_task_txt);
		textview6 = findViewById(R.id.textview6);
		linear97 = findViewById(R.id.linear97);
		linear98 = findViewById(R.id.linear98);
		linear99 = findViewById(R.id.linear99);
		linear100 = findViewById(R.id.linear100);
		linear101 = findViewById(R.id.linear101);
		imageview28 = findViewById(R.id.imageview28);
		total_task_completed = findViewById(R.id.total_task_completed);
		textview30 = findViewById(R.id.textview30);
		linear175 = findViewById(R.id.linear175);
		linear176 = findViewById(R.id.linear176);
		linear177 = findViewById(R.id.linear177);
		linear178 = findViewById(R.id.linear178);
		linear179 = findViewById(R.id.linear179);
		imageview51 = findViewById(R.id.imageview51);
		total_methods_txt = findViewById(R.id.total_methods_txt);
		textview53 = findViewById(R.id.textview53);
		linear33 = findViewById(R.id.linear33);
		linear34 = findViewById(R.id.linear34);
		linear35 = findViewById(R.id.linear35);
		linear36 = findViewById(R.id.linear36);
		linear37 = findViewById(R.id.linear37);
		imageview17 = findViewById(R.id.imageview17);
		total_pending_pay_txt = findViewById(R.id.total_pending_pay_txt);
		txt2 = findViewById(R.id.txt2);
		linear39 = findViewById(R.id.linear39);
		linear40 = findViewById(R.id.linear40);
		linear41 = findViewById(R.id.linear41);
		linear42 = findViewById(R.id.linear42);
		linear43 = findViewById(R.id.linear43);
		imageview18 = findViewById(R.id.imageview18);
		total_pay_succ_txt = findViewById(R.id.total_pay_succ_txt);
		textview10 = findViewById(R.id.textview10);
		linear117 = findViewById(R.id.linear117);
		linear118 = findViewById(R.id.linear118);
		linear119 = findViewById(R.id.linear119);
		linear120 = findViewById(R.id.linear120);
		linear121 = findViewById(R.id.linear121);
		imageview31 = findViewById(R.id.imageview31);
		total_paynent_rejected = findViewById(R.id.total_paynent_rejected);
		textview36 = findViewById(R.id.textview36);
		linear51 = findViewById(R.id.linear51);
		linear52 = findViewById(R.id.linear52);
		linear53 = findViewById(R.id.linear53);
		linear54 = findViewById(R.id.linear54);
		linear55 = findViewById(R.id.linear55);
		imageview20 = findViewById(R.id.imageview20);
		total_pending_de_txt = findViewById(R.id.total_pending_de_txt);
		textview14 = findViewById(R.id.textview14);
		linear57 = findViewById(R.id.linear57);
		linear58 = findViewById(R.id.linear58);
		linear59 = findViewById(R.id.linear59);
		linear60 = findViewById(R.id.linear60);
		linear61 = findViewById(R.id.linear61);
		imageview21 = findViewById(R.id.imageview21);
		total_de_succ_txt = findViewById(R.id.total_de_succ_txt);
		textview16 = findViewById(R.id.textview16);
		linear111 = findViewById(R.id.linear111);
		linear112 = findViewById(R.id.linear112);
		linear113 = findViewById(R.id.linear113);
		linear114 = findViewById(R.id.linear114);
		linear115 = findViewById(R.id.linear115);
		imageview30 = findViewById(R.id.imageview30);
		total_de_rejected = findViewById(R.id.total_de_rejected);
		textview34 = findViewById(R.id.textview34);
		linear164 = findViewById(R.id.linear164);
		linear84 = findViewById(R.id.linear84);
		linear126 = findViewById(R.id.linear126);
		linear130 = findViewById(R.id.linear130);
		linear134 = findViewById(R.id.linear134);
		linear138 = findViewById(R.id.linear138);
		linear142 = findViewById(R.id.linear142);
		linear146 = findViewById(R.id.linear146);
		linear160 = findViewById(R.id.linear160);
		linear165 = findViewById(R.id.linear165);
		linear166 = findViewById(R.id.linear166);
		linear167 = findViewById(R.id.linear167);
		imageview49 = findViewById(R.id.imageview49);
		textview48 = findViewById(R.id.textview48);
		textview49 = findViewById(R.id.textview49);
		linear86 = findViewById(R.id.linear86);
		imageview26 = findViewById(R.id.imageview26);
		linear88 = findViewById(R.id.linear88);
		linear89 = findViewById(R.id.linear89);
		switch1 = findViewById(R.id.switch1);
		textview26 = findViewById(R.id.textview26);
		linear127 = findViewById(R.id.linear127);
		imageview33 = findViewById(R.id.imageview33);
		linear128 = findViewById(R.id.linear128);
		linear129 = findViewById(R.id.linear129);
		imageview34 = findViewById(R.id.imageview34);
		textview38 = findViewById(R.id.textview38);
		linear131 = findViewById(R.id.linear131);
		imageview35 = findViewById(R.id.imageview35);
		linear132 = findViewById(R.id.linear132);
		linear133 = findViewById(R.id.linear133);
		imageview36 = findViewById(R.id.imageview36);
		textview39 = findViewById(R.id.textview39);
		linear135 = findViewById(R.id.linear135);
		imageview37 = findViewById(R.id.imageview37);
		linear136 = findViewById(R.id.linear136);
		linear137 = findViewById(R.id.linear137);
		imageview38 = findViewById(R.id.imageview38);
		textview40 = findViewById(R.id.textview40);
		linear139 = findViewById(R.id.linear139);
		imageview39 = findViewById(R.id.imageview39);
		linear140 = findViewById(R.id.linear140);
		linear141 = findViewById(R.id.linear141);
		imageview40 = findViewById(R.id.imageview40);
		textview41 = findViewById(R.id.textview41);
		linear143 = findViewById(R.id.linear143);
		imageview41 = findViewById(R.id.imageview41);
		linear144 = findViewById(R.id.linear144);
		linear145 = findViewById(R.id.linear145);
		imageview42 = findViewById(R.id.imageview42);
		textview42 = findViewById(R.id.textview42);
		linear147 = findViewById(R.id.linear147);
		imageview43 = findViewById(R.id.imageview43);
		linear148 = findViewById(R.id.linear148);
		linear149 = findViewById(R.id.linear149);
		imageview44 = findViewById(R.id.imageview44);
		textview43 = findViewById(R.id.textview43);
		linear161 = findViewById(R.id.linear161);
		imageview47 = findViewById(R.id.imageview47);
		linear162 = findViewById(R.id.linear162);
		linear163 = findViewById(R.id.linear163);
		imageview48 = findViewById(R.id.imageview48);
		textview47 = findViewById(R.id.textview47);
		auth = FirebaseAuth.getInstance();
		aboutDialog = new AlertDialog.Builder(this);
		
		d2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dIntent.setClass(getApplicationContext(), TotalActivity.class);
				dIntent.putExtra("activity", "users");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(dIntent, options.toBundle());
			}
		});
		
		d3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dIntent.setClass(getApplicationContext(), TotalActivity.class);
				dIntent.putExtra("activity", "blocked users");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(dIntent, options.toBundle());
			}
		});
		
		d14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dIntent.setClass(getApplicationContext(), TotalActivity.class);
				dIntent.putExtra("activity", "methods");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(dIntent, options.toBundle());
			}
		});
		
		d8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dIntent.setClass(getApplicationContext(), TotalActivity.class);
				dIntent.putExtra("activity", "withdraw");
				dIntent.putExtra("activity2", "pending payment");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(dIntent, options.toBundle());
			}
		});
		
		d9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dIntent.setClass(getApplicationContext(), TotalActivity.class);
				dIntent.putExtra("activity", "withdraw");
				dIntent.putExtra("activity2", "payment success");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(dIntent, options.toBundle());
			}
		});
		
		d10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dIntent.setClass(getApplicationContext(), TotalActivity.class);
				dIntent.putExtra("activity", "withdraw");
				dIntent.putExtra("activity2", "payment rejected");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(dIntent, options.toBundle());
			}
		});
		
		d11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dIntent.setClass(getApplicationContext(), TotalActivity.class);
				dIntent.putExtra("activity", "deposit");
				dIntent.putExtra("activity2", "pending deposit");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(dIntent, options.toBundle());
			}
		});
		
		d12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dIntent.setClass(getApplicationContext(), TotalActivity.class);
				dIntent.putExtra("activity", "deposit");
				dIntent.putExtra("activity2", "deposit success");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(dIntent, options.toBundle());
			}
		});
		
		d13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dIntent.setClass(getApplicationContext(), TotalActivity.class);
				dIntent.putExtra("activity", "deposit");
				dIntent.putExtra("activity2", "deposit rejected");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(dIntent, options.toBundle());
			}
		});
		
		linear126.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				sIntent.setClass(getApplicationContext(), SettingsActivity.class);
				sIntent.putExtra("activity", "live chat");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(sIntent, options.toBundle());
				SketchwareUtil.showMessage(getApplicationContext(), "Coming soon on Faster Panel Version 1.6 (fasterpanelv1.6)");
			}
		});
		
		linear130.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				sIntent.setClass(getApplicationContext(), SettingsActivity.class);
				sIntent.putExtra("activity", "update manager");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(sIntent, options.toBundle());
			}
		});
		
		linear134.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				sIntent.setClass(getApplicationContext(), NotificationManagerActivity.class);
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(sIntent, options.toBundle());
			}
		});
		
		linear138.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				sIntent.setClass(getApplicationContext(), SettingsActivity.class);
				sIntent.putExtra("activity", "firebase manager");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(sIntent, options.toBundle());
			}
		});
		
		linear142.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				sIntent.setClass(getApplicationContext(), SettingsActivity.class);
				sIntent.putExtra("activity", "storage manager");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(sIntent, options.toBundle());
				SketchwareUtil.showMessage(getApplicationContext(), "Coming soon on Faster Panel Version 1.6 (fasterpanelv1.6)");
			}
		});
		
		linear146.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				sIntent.setClass(getApplicationContext(), SecurityAndOtherManagerActivity.class);
				ActivityOptions options = ActivityOptions.makeCustomAnimation(PanelActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(sIntent, options.toBundle());
			}
		});
		
		linear160.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				MaterialAlertDialogBuilder aboutDialog = new MaterialAlertDialogBuilder(PanelActivity.this);
				aboutDialog.setTitle("Projects and creator Details");
				aboutDialog.setMessage("Personal Details\nAssalamu Alaikum!\nMy name is Takbir Hassan. I am an app, game, and website developer, as well as an ethical hacker. I am from Bangladesh and proudly support 🇵🇸 🇧🇩 🇸🇦 🇵🇰 🇮🇩 🇮🇶 🇮🇷 🇸🇾. My primary language is English, but I speak Bengali with my parents and relatives, although I am not very fluent in it.  \n\nI am passionate about technology and love creating innovative projects that solve real-world problems. My journey in app development has been challenging yet rewarding, and I aim to inspire others through my work and tutorials.  \n\nFaster Panel Version 1.0\nFaster Panel Version 1.0 is one of my most significant projects, and it took a considerable amount of time and dedication to develop. Your support means a lot to me, and you can help by donating or subscribing and liking my channel.  \n\nYou can find my channel, Faster Software Developer, on YouTube and Telegram. Stay updated with the latest tutorials and tips by following my Telegram username: @TakbirHassan.  \n\nWarning:\nPlease do not share the projects (SWB files or Android Studio project ZIP files) on any other YouTube channel. Unauthorized sharing violates the terms of use and disrespects the hard work behind this project.  \n\nImportant Note:\nYou are allowed to publish the Faster Panel V1 app and link-earn projects on the Play Store and other platforms.  \n\nYour support and encouragement help me continue developing innovative tools and content. Thank you for being a part of my journey!");
				aboutDialog.setIcon(R.drawable.icon_info_twotone);
				aboutDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						_dialog.dismiss();
					}
				});
				aboutDialog.create().show();
			}
		});
		
		switch1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try {
					if (switch1.isChecked()) {
						maintenance_Map = new HashMap<>();
						maintenance_Map.put("Maintenance Mode", "on");
						others.child("Maintenance").updateChildren(maintenance_Map);
						maintenance_Map.clear();
					} else {
						maintenance_Map = new HashMap<>();
						maintenance_Map.put("Maintenance Mode", "off");
						others.child("Maintenance").updateChildren(maintenance_Map);
						maintenance_Map.clear();
					}
				} catch (Exception e) {
					 
				}
			}
		});
		
		_others_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("Maintenance")) {
					if (_childValue.get("Maintenance Mode").toString().equals("on")) {
						switch1.setChecked(true);
					} else {
						switch1.setChecked(false);
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("Maintenance")) {
					if (_childValue.get("Maintenance Mode").toString().equals("on")) {
						switch1.setChecked(true);
					} else {
						switch1.setChecked(false);
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
		others.addChildEventListener(_others_child_listener);
		
		_db_accounts_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				_Material3Loader(false, "");
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
		db_accounts.addChildEventListener(_db_accounts_child_listener);
		
		_Task1_child_listener = new ChildEventListener() {
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
		Task1.addChildEventListener(_Task1_child_listener);
		
		_Task2_child_listener = new ChildEventListener() {
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
		Task2.addChildEventListener(_Task2_child_listener);
		
		_completedTask2_child_listener = new ChildEventListener() {
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
		completedTask2.addChildEventListener(_completedTask2_child_listener);
		
		_completedTask1_child_listener = new ChildEventListener() {
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
		completedTask1.addChildEventListener(_completedTask1_child_listener);
		
		_withdraw_child_listener = new ChildEventListener() {
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
		withdraw.addChildEventListener(_withdraw_child_listener);
		
		_deposit_child_listener = new ChildEventListener() {
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
		deposit.addChildEventListener(_deposit_child_listener);
		
		_methods_child_listener = new ChildEventListener() {
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
		methods.addChildEventListener(_methods_child_listener);
		
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
		progressDialog = new MaterialProgressDialog(PanelActivity.this);
		// Change the navigation bar color for Android 6.0 and above
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			            getWindow().setNavigationBarColor(getResources().getColor(R.color.DarkBlue, getTheme()));
			            }
		            
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			            getWindow().setStatusBarColor(getResources().getColor(R.color.DarkBlue, getTheme()));
			            }
		//      End part: Backend
		//      Start part: Bottom Navigation Bar
		bottom_nav.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
			    @Override
			    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				        int itemId = item.getItemId();
				        if (itemId == R.id.nav_dashboard) {
					dashboard_body.setVisibility(View.VISIBLE);
					settings_body.setVisibility(View.GONE);
					_autoTransitionScroll(main);
					if (getSupportActionBar() != null) {
						            getSupportActionBar().setSubtitle("Dashboard");
						        }
					        return true;
					        } else if (itemId == R.id.nav_settings) {
					dashboard_body.setVisibility(View.GONE);
					settings_body.setVisibility(View.VISIBLE);
					_autoTransitionScroll(main);
					if (getSupportActionBar() != null) {
						            getSupportActionBar().setSubtitle("Settings");
						        }
					        return true;
					        } else {
					            return false;
					        }
				    }
		});
		//      End part: Bottom Navigation Bar
		//      Start part: others
		_Material3Loader(true, "Calculating data...");
		_counter();
		//      End part: others
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			timer.cancel();
		} catch (Exception e) {
			 
		}
	}
	public void _TransitionManager(final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _autoTransitionScroll(final View _scroll) {
		android.transition.TransitionManager.beginDelayedTransition((LinearLayout)_scroll, new android.transition.AutoTransition());
	}
	
	
	public void _counter() {
		timer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						//      Start part: Total users
						totalUsers_listmap.clear();
						db_accounts.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								totalUsers_listmap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										totalUsers_listmap.add(_map);
									}
								} catch (Exception _e) {
									_e.printStackTrace();
								}
								totalUsers_num = 0;
								totalUsers_count = 0;
								for(int _repeat76 = 0; _repeat76 < (int)(totalUsers_listmap.size()); _repeat76++) {
									if (totalUsers_listmap.get((int)totalUsers_count).containsKey("uid")) {
										totalUsers_num++;
									}
									totalUsers_count++;
								}
								total_user_txt.setText(String.valueOf((long)(totalUsers_num)));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						//      End part: Total users
						//      Start part: Total Blocked Users
						totalBlocked_listmap.clear();
						db_accounts.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								totalBlocked_listmap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										totalBlocked_listmap.add(_map);
									}
								} catch (Exception _e) {
									_e.printStackTrace();
								}
								totalBlocked_num = 0;
								totalBlocked_count = 0;
								for(int _repeat156 = 0; _repeat156 < (int)(totalBlocked_listmap.size()); _repeat156++) {
									if (totalBlocked_listmap.get((int)totalBlocked_num).containsKey("risk type")) {
										if (totalBlocked_listmap.get((int)totalBlocked_num).get("risk type").toString().equals("banned")) {
											totalBlocked_count++;
										}
									}
									totalBlocked_num++;
								}
								text_blockeds.setText(String.valueOf((long)(totalBlocked_count)));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						//      End part: Total Blocked Users
						//      Start part: Total Task (Task1 + Task2)
						//      Start part: Total Task1 get
						totalTask_listmap.clear();
						Task1.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								totalTask_listmap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										totalTask_listmap.add(_map);
									}
								} catch (Exception _e) {
									_e.printStackTrace();
								}
								totalTask_num = 0;
								totalTask_count = 0;
								for(int _repeat198 = 0; _repeat198 < (int)(totalTask_listmap.size()); _repeat198++) {
									if (totalTask_listmap.get((int)totalTask_count).containsKey("user id")) {
										totalTask_num++;
									}
									totalTask_count++;
								}
								total_task1_get = totalTask_num;
								total_task_txt.setText(String.valueOf((long)(total_task1_get)));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						//      End part: Total Task1 get
						//      Start part: Total task2 get and final results
						totalTask2_listmap.clear();
						Task2.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								totalTask2_listmap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										totalTask2_listmap.add(_map);
									}
								} catch (Exception _e) {
									_e.printStackTrace();
								}
								totalTask2_num = 0;
								totalTask2_count = 0;
								for(int _repeat217 = 0; _repeat217 < (int)(totalTask2_listmap.size()); _repeat217++) {
									if (totalTask2_listmap.get((int)totalTask2_count).containsKey("user id")) {
										totalTask2_num++;
									}
									totalTask2_count++;
								}
								total_task2_get = totalTask2_num;
								total_task_txt.setText(String.valueOf((long)(total_task1_get + total_task2_get)));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						//      End part: Total task2 get and final results
						//      End part: Total Task (Task1 + Task2)
						//      Start part: Total Completed Task (Completed Task1 + Completed Task2)
						//      Start part: Total Completed Task1 get
						totalCompleteTask1_listmap.clear();
						completedTask1.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								totalCompleteTask1_listmap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										totalCompleteTask1_listmap.add(_map);
									}
								} catch (Exception _e) {
									_e.printStackTrace();
								}
								totalCompleteTask1_num = 0;
								totalCompleteTask1_count = 0;
								for(int _repeat285 = 0; _repeat285 < (int)(totalCompleteTask1_listmap.size()); _repeat285++) {
									if (totalCompleteTask1_listmap.get((int)totalCompleteTask1_count).containsKey("key")) {
										totalCompleteTask1_num++;
									}
									totalCompleteTask1_count++;
								}
								totalCompleteTask1_get = totalCompleteTask1_num;
								total_task_completed.setText(String.valueOf((long)(totalCompleteTask1_get)));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						//      End part: Total Completed Task1 get
						//      Start part: Total completed task2 get and final results
						totalCompleteTask2_listmap.clear();
						completedTask2.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								totalCompleteTask2_listmap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										totalCompleteTask2_listmap.add(_map);
									}
								} catch (Exception _e) {
									_e.printStackTrace();
								}
								totalCompleteTask2_num = 0;
								totalCompleteTask2_count = 0;
								for(int _repeat300 = 0; _repeat300 < (int)(totalCompleteTask2_listmap.size()); _repeat300++) {
									if (totalCompleteTask2_listmap.get((int)totalCompleteTask2_count).containsKey("key")) {
										totalCompleteTask2_num++;
									}
									totalCompleteTask2_count++;
								}
								totalCompleteTask2_get = totalCompleteTask2_num;
								total_task_completed.setText(String.valueOf((long)(totalCompleteTask1_get + totalCompleteTask2_get)));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						//      End part: Total completed task2 get and final results
						//      End part: Total Completed Task (Completed Task1 + Completed Task2)
						//      Start part: Withdrawal
						//      Start part: Pending Withdraw
						Withdraw_listmap.clear();
						withdraw.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								Withdraw_listmap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										Withdraw_listmap.add(_map);
									}
								} catch (Exception _e) {
									_e.printStackTrace();
								}
								Withdraw_num = 0;
								Withdraw_count = 0;
								for(int _repeat362 = 0; _repeat362 < (int)(Withdraw_listmap.size()); _repeat362++) {
									if (Withdraw_listmap.get((int)Withdraw_count).containsKey("status")) {
										if (Withdraw_listmap.get((int)Withdraw_count).get("status").toString().toLowerCase().equals("pending")) {
											Withdraw_num++;
										}
									}
									Withdraw_count++;
								}
								total_pending_pay_txt.setText(String.valueOf((long)(Withdraw_num)));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						//      End part: Pending Withdraw
						//      Start part: Success Withdraw
						Withdraw_listmap.clear();
						withdraw.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								Withdraw_listmap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										Withdraw_listmap.add(_map);
									}
								} catch (Exception _e) {
									_e.printStackTrace();
								}
								Withdraw_num = 0;
								Withdraw_count = 0;
								for(int _repeat419 = 0; _repeat419 < (int)(Withdraw_listmap.size()); _repeat419++) {
									if (Withdraw_listmap.get((int)Withdraw_count).containsKey("status")) {
										if (Withdraw_listmap.get((int)Withdraw_count).get("status").toString().toLowerCase().equals("success")) {
											Withdraw_num++;
										}
									}
									Withdraw_count++;
								}
								total_pay_succ_txt.setText(String.valueOf((long)(Withdraw_num)));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						//      End part: Success Withdraw
						//      Start part: Failed Withdraw
						Withdraw_listmap.clear();
						withdraw.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								Withdraw_listmap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										Withdraw_listmap.add(_map);
									}
								} catch (Exception _e) {
									_e.printStackTrace();
								}
								Withdraw_num = 0;
								Withdraw_count = 0;
								for(int _repeat439 = 0; _repeat439 < (int)(Withdraw_listmap.size()); _repeat439++) {
									if (Withdraw_listmap.get((int)Withdraw_count).containsKey("status")) {
										if (Withdraw_listmap.get((int)Withdraw_count).get("status").toString().toLowerCase().equals("failed")) {
											Withdraw_num++;
										}
									}
									Withdraw_count++;
								}
								total_paynent_rejected.setText(String.valueOf((long)(Withdraw_num)));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						//      End part: Failed Withdraw
						//      End part: Withdrawal
						//      Start part: Deposit
						//      Start part: Pending Deposit
						deposit_listmap.clear();
						deposit.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								deposit_listmap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										deposit_listmap.add(_map);
									}
								} catch (Exception _e) {
									_e.printStackTrace();
								}
								deposit_num = 0;
								deposit_count = 0;
								for(int _repeat482 = 0; _repeat482 < (int)(deposit_listmap.size()); _repeat482++) {
									if (deposit_listmap.get((int)deposit_count).containsKey("status")) {
										if (deposit_listmap.get((int)deposit_count).get("status").toString().toLowerCase().equals("pending")) {
											deposit_num++;
										}
									}
									deposit_count++;
								}
								total_pending_de_txt.setText(String.valueOf((long)(deposit_num)));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						//      End part: Pending Deposit
						//      Start part: Success Deposit
						deposit_listmap.clear();
						deposit.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								deposit_listmap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										deposit_listmap.add(_map);
									}
								} catch (Exception _e) {
									_e.printStackTrace();
								}
								deposit_num = 0;
								deposit_count = 0;
								for(int _repeat506 = 0; _repeat506 < (int)(deposit_listmap.size()); _repeat506++) {
									if (deposit_listmap.get((int)deposit_count).containsKey("status")) {
										if (deposit_listmap.get((int)deposit_count).get("status").toString().toLowerCase().equals("success")) {
											deposit_num++;
										}
									}
									deposit_count++;
								}
								total_de_succ_txt.setText(String.valueOf((long)(deposit_num)));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						//      End part: Success Deposit
						//      Start part: Failed Deposit
						deposit_listmap.clear();
						deposit.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								deposit_listmap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										deposit_listmap.add(_map);
									}
								} catch (Exception _e) {
									_e.printStackTrace();
								}
								deposit_num = 0;
								deposit_count = 0;
								for(int _repeat525 = 0; _repeat525 < (int)(deposit_listmap.size()); _repeat525++) {
									if (deposit_listmap.get((int)deposit_count).containsKey("status")) {
										if (deposit_listmap.get((int)deposit_count).get("status").toString().toLowerCase().equals("failed")) {
											deposit_num++;
										}
									}
									deposit_count++;
								}
								total_de_rejected.setText(String.valueOf((long)(deposit_num)));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						//      End part: Failed Deposit
						//      End part: Deposit
						//      Start part: Total Methods
						totalMethods_listmap.clear();
						methods.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								totalMethods_listmap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										totalMethods_listmap.add(_map);
									}
								} catch (Exception _e) {
									_e.printStackTrace();
								}
								totalMethods_num = 0;
								totalMethods_count = 0;
								for(int _repeat564 = 0; _repeat564 < (int)(totalMethods_listmap.size()); _repeat564++) {
									if (totalMethods_listmap.get((int)totalMethods_count).containsKey("key")) {
										totalMethods_num++;
									}
									totalMethods_count++;
								}
								total_methods_txt.setText(String.valueOf((long)(totalMethods_num)));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						//      End part: Total Methods
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(timer, (int)(0), (int)(1000));
	}
	
	
	public void _Material3Loader(final boolean _variable, final String _title) {
		if (_variable) {
			progressDialog.show(_title);
		} else {
			
			progressDialog.dismiss();
		}
	}
	
}