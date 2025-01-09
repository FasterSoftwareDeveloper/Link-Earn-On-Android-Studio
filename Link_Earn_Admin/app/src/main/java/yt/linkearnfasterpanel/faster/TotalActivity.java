package yt.linkearnfasterpanel.faster;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.google.android.material.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.*;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.card.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.text.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import androidx.core.content.ContextCompat;
import androidx.activity.OnBackPressedCallback;

public class TotalActivity extends AppCompatActivity {

private MaterialProgressDialog progressDialog;
	
	public final int REQ_CD_IMGPICKER = 101;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton _fab;
	private String fontName = "";
	private String typeace = "";
	private double totalUsersAndBlockedCount = 0;
	private HashMap<String, Object> totalBlocked_Map = new HashMap<>();
	private String uid = "";
	private String riskTypeStr = "";
	private String notificationTokenStr = "";
	private String balanceEdittext = "";
	private String depositEdittext = "";
	private HashMap<String, Object> money_Map = new HashMap<>();
	private String path = "";
	private String MethodSKey = "";
	private HashMap<String, Object> methods_map = new HashMap<>();
	private String keyMethods = "";
	private String methodImageSTR = "";
	private String imgUrl = "";
	private String uid2 = "";
	private double withdraw_count = 0;
	private String financialKey = "";
	private String MethodKey2 = "";
	private double amount = 0;
	private double dollar = 0;
	private HashMap<String, Object> withdraw_map = new HashMap<>();
	private HashMap<String, Object> deposit_map = new HashMap<>();
	private HashMap<String, Object> balanceMap = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> totalTask_listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> totalUsers_listmap = new ArrayList<>();
	private ArrayList<String> moneyName_list_string = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> methods_Listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> withdraw_listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> deposit_listmap = new ArrayList<>();
	
	private CollapsingToolbarLayout collapsingtoolbar1;
	private NestedScrollView vscroll2;
	private LinearLayout main;
	private LinearLayout Total_list_Body;
	private LinearLayout Total_show_body;
	private LinearLayout total_methods_body;
	private RecyclerView totalUsersAndBlocked_listview;
	private RecyclerView financial_listview;
	private RecyclerView methods_listview;
	private CardView linear6;
	private MaterialCardView linear_email;
	private MaterialCardView linear_balance;
	private MaterialCardView linear_deposit;
	private MaterialCardView linear_birthday;
	private MaterialCardView linear_social_media;
	private MaterialCardView linear_register_date;
	private MaterialCardView linear_device_id;
	private MaterialCardView date2_body;
	private LinearLayout linear_financial;
	private LinearLayout linear_confirmation;
	private MaterialButtonToggleGroup linear_risk_type;
	private LinearLayout linear_button1;
	private MaterialCardView linear_name_logo;
	private LinearLayout linear4;
	private ImageView imageview1;
	private TextView textright1;
	private TextView textleft1;
	private LinearLayout linear8;
	private LinearLayout linear26;
	private LinearLayout linear25;
	private TextView textright2;
	private TextView textleft2;
	private LinearLayout linear32;
	private TextView textright3;
	private TextInputLayout balance_tablayout;
	private EditText balance_edittext;
	private LinearLayout linear31;
	private TextView textrightDeposit;
	private TextInputLayout deposit_tablayout;
	private EditText deposit_edittext;
	private LinearLayout linear16;
	private TextView textleft6;
	private TextView textright6;
	private LinearLayout linear14;
	private TextView textleft5;
	private TextView textright5;
	private LinearLayout linear12;
	private TextView textleft4;
	private TextView textright4;
	private LinearLayout linear18;
	private TextView textleft7;
	private TextView textright7;
	private LinearLayout linear53;
	private TextView textview15;
	private TextView date2_txt;
	private MaterialCardView currency_linear;
	private MaterialCardView Amount_linear;
	private MaterialCardView orderid_linear;
	private MaterialCardView number_linear;
	private MaterialCardView status_linear;
	private MaterialCardView copyOrderId_linear;
	private LinearLayout linear88;
	private LinearLayout linear39;
	private TextView textview2;
	private TextView currency_txt;
	private CircleImageView currency_logo;
	private LinearLayout linear43;
	private TextView textview5;
	private TextView amount_txt;
	private LinearLayout linear45;
	private TextView textview7;
	private TextView order_id;
	private LinearLayout linear47;
	private TextView textview9;
	private TextView num_txt;
	private LinearLayout linear49;
	private TextView textview11;
	private TextView status_txt;
	private LinearLayout linear51;
	private TextView textview13;
	private TextView textview14;
	private TextView money_txt3;
	private LinearLayout linear54;
	private TextView convert_txt2;
	private MaterialCardView linear55;
	private MaterialCardView linear56;
	private Button button10;
	private Button button9;
	private MaterialButton blocked_bt;
	private MaterialButton unblock_bt;
	private Button button7;
	private Button button_checkHistory;
	private LinearLayout linear33;
	private TextInputLayout moneyName_tablayout;
	private LinearLayout linear34;
	private TextInputLayout withdrawDepositFee_tablayout;
	private TextInputLayout id_tablayout;
	private Button button_addMethod;
	private CircleImageView methodImage;
	private AutoCompleteTextView moneyName_txt;
	private TextInputLayout dollars_tablayout;
	private LinearLayout linear_equal;
	private TextInputLayout convert_tablayout;
	private EditText dollars_txt;
	private MaterialCardView equal1;
	private MaterialCardView linear36;
	private EditText convert_txt;
	private EditText fee_txt;
	private EditText id_txt;
	
	private DatabaseReference db_accounts = _firebase.getReference("db_accounts");
	private ChildEventListener _db_accounts_child_listener;
	private DatabaseReference financial = _firebase.getReference("financial/withdraw");
	private ChildEventListener _financial_child_listener;
	private DatabaseReference financialDeposit = _firebase.getReference("financial/deposit");
	private ChildEventListener _financialDeposit_child_listener;
	private TimerTask timer;
	private DatabaseReference methods = _firebase.getReference("financial/methods");
	private ChildEventListener _methods_child_listener;
	private Intent imgPicker = new Intent(Intent.ACTION_GET_CONTENT);
	private DatabaseReference accounts = _firebase.getReference("db_accounts");
	private ChildEventListener _accounts_child_listener;
	private Intent nextIntent = new Intent();
	private DatabaseReference financialMethod2 = _firebase.getReference("financial/methods");
	private ChildEventListener _financialMethod2_child_listener;
	private DatabaseReference db_accounts2 = _firebase.getReference("db_accounts");
	private ChildEventListener _db_accounts2_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.total);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
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
		vscroll2 = findViewById(R.id.vscroll2);
		main = findViewById(R.id.main);
		Total_list_Body = findViewById(R.id.Total_list_Body);
		Total_show_body = findViewById(R.id.Total_show_body);
		total_methods_body = findViewById(R.id.total_methods_body);
		totalUsersAndBlocked_listview = findViewById(R.id.totalUsersAndBlocked_listview);
		financial_listview = findViewById(R.id.financial_listview);
		methods_listview = findViewById(R.id.methods_listview);
		linear6 = findViewById(R.id.linear6);
		linear_email = findViewById(R.id.linear_email);
		linear_balance = findViewById(R.id.linear_balance);
		linear_deposit = findViewById(R.id.linear_deposit);
		linear_birthday = findViewById(R.id.linear_birthday);
		linear_social_media = findViewById(R.id.linear_social_media);
		linear_register_date = findViewById(R.id.linear_register_date);
		linear_device_id = findViewById(R.id.linear_device_id);
		date2_body = findViewById(R.id.date2_body);
		linear_financial = findViewById(R.id.linear_financial);
		linear_confirmation = findViewById(R.id.linear_confirmation);
		linear_risk_type = findViewById(R.id.linear_risk_type);
		linear_button1 = findViewById(R.id.linear_button1);
		linear_name_logo = findViewById(R.id.linear_name_logo);
		linear4 = findViewById(R.id.linear4);
		imageview1 = findViewById(R.id.imageview1);
		textright1 = findViewById(R.id.textright1);
		textleft1 = findViewById(R.id.textleft1);
		linear8 = findViewById(R.id.linear8);
		linear26 = findViewById(R.id.linear26);
		linear25 = findViewById(R.id.linear25);
		textright2 = findViewById(R.id.textright2);
		textleft2 = findViewById(R.id.textleft2);
		linear32 = findViewById(R.id.linear32);
		textright3 = findViewById(R.id.textright3);
		balance_tablayout = findViewById(R.id.balance_tablayout);
		balance_edittext = findViewById(R.id.balance_edittext);
		linear31 = findViewById(R.id.linear31);
		textrightDeposit = findViewById(R.id.textrightDeposit);
		deposit_tablayout = findViewById(R.id.deposit_tablayout);
		deposit_edittext = findViewById(R.id.deposit_edittext);
		linear16 = findViewById(R.id.linear16);
		textleft6 = findViewById(R.id.textleft6);
		textright6 = findViewById(R.id.textright6);
		linear14 = findViewById(R.id.linear14);
		textleft5 = findViewById(R.id.textleft5);
		textright5 = findViewById(R.id.textright5);
		linear12 = findViewById(R.id.linear12);
		textleft4 = findViewById(R.id.textleft4);
		textright4 = findViewById(R.id.textright4);
		linear18 = findViewById(R.id.linear18);
		textleft7 = findViewById(R.id.textleft7);
		textright7 = findViewById(R.id.textright7);
		linear53 = findViewById(R.id.linear53);
		textview15 = findViewById(R.id.textview15);
		date2_txt = findViewById(R.id.date2_txt);
		currency_linear = findViewById(R.id.currency_linear);
		Amount_linear = findViewById(R.id.Amount_linear);
		orderid_linear = findViewById(R.id.orderid_linear);
		number_linear = findViewById(R.id.number_linear);
		status_linear = findViewById(R.id.status_linear);
		copyOrderId_linear = findViewById(R.id.copyOrderId_linear);
		linear88 = findViewById(R.id.linear88);
		linear39 = findViewById(R.id.linear39);
		textview2 = findViewById(R.id.textview2);
		currency_txt = findViewById(R.id.currency_txt);
		currency_logo = findViewById(R.id.currency_logo);
		linear43 = findViewById(R.id.linear43);
		textview5 = findViewById(R.id.textview5);
		amount_txt = findViewById(R.id.amount_txt);
		linear45 = findViewById(R.id.linear45);
		textview7 = findViewById(R.id.textview7);
		order_id = findViewById(R.id.order_id);
		linear47 = findViewById(R.id.linear47);
		textview9 = findViewById(R.id.textview9);
		num_txt = findViewById(R.id.num_txt);
		linear49 = findViewById(R.id.linear49);
		textview11 = findViewById(R.id.textview11);
		status_txt = findViewById(R.id.status_txt);
		linear51 = findViewById(R.id.linear51);
		textview13 = findViewById(R.id.textview13);
		textview14 = findViewById(R.id.textview14);
		money_txt3 = findViewById(R.id.money_txt3);
		linear54 = findViewById(R.id.linear54);
		convert_txt2 = findViewById(R.id.convert_txt2);
		linear55 = findViewById(R.id.linear55);
		linear56 = findViewById(R.id.linear56);
		button10 = findViewById(R.id.button10);
		button9 = findViewById(R.id.button9);
		blocked_bt = findViewById(R.id.blocked_bt);
		unblock_bt = findViewById(R.id.unblock_bt);
		button7 = findViewById(R.id.button7);
		button_checkHistory = findViewById(R.id.button_checkHistory);
		linear33 = findViewById(R.id.linear33);
		moneyName_tablayout = findViewById(R.id.moneyName_tablayout);
		linear34 = findViewById(R.id.linear34);
		withdrawDepositFee_tablayout = findViewById(R.id.withdrawDepositFee_tablayout);
		id_tablayout = findViewById(R.id.id_tablayout);
		button_addMethod = findViewById(R.id.button_addMethod);
		methodImage = findViewById(R.id.methodImage);
		moneyName_txt = findViewById(R.id.moneyName_txt);
		dollars_tablayout = findViewById(R.id.dollars_tablayout);
		linear_equal = findViewById(R.id.linear_equal);
		convert_tablayout = findViewById(R.id.convert_tablayout);
		dollars_txt = findViewById(R.id.dollars_txt);
		equal1 = findViewById(R.id.equal1);
		linear36 = findViewById(R.id.linear36);
		convert_txt = findViewById(R.id.convert_txt);
		fee_txt = findViewById(R.id.fee_txt);
		id_txt = findViewById(R.id.id_txt);
		imgPicker.setType("image/*");
		imgPicker.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		textview14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", num_txt.getText().toString()));
				SketchwareUtil.showMessage(getApplicationContext(), "done");
			}
		});
		
		button10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (getIntent().getStringExtra("activity").equals("withdraw")) {
					withdraw_map = new HashMap<>();
					withdraw_map.put("status", "success");
					financial.child(financialKey).updateChildren(withdraw_map);
					withdraw_map.clear();
					SketchwareUtil.showMessage(getApplicationContext(), "success");
					finish();
				} else {
					if (getIntent().getStringExtra("activity").equals("deposit")) {
						deposit_map = new HashMap<>();
						deposit_map.put("status", "success");
						financialDeposit.child(financialKey).updateChildren(deposit_map);
						deposit_map.clear();
						//      Start part: Deposit Balance +
						balanceMap = new HashMap<>();
						balanceMap.put("deposit balance", new DecimalFormat("0.000").format(amount + Double.parseDouble(depositEdittext)).replace("-", ""));
						db_accounts.child(uid2).updateChildren(balanceMap);
						balanceMap.clear();
						//      End part: Deposit Balance +
						SketchwareUtil.showMessage(getApplicationContext(), "success");
						finish();
					}
				}
			}
		});
		
		button9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (getIntent().getStringExtra("activity").equals("withdraw")) {
					withdraw_map = new HashMap<>();
					withdraw_map.put("status", "failed");
					financial.child(financialKey).updateChildren(withdraw_map);
					withdraw_map.clear();
					SketchwareUtil.showMessage(getApplicationContext(), "success");
					finish();
				} else {
					if (getIntent().getStringExtra("activity").equals("deposit")) {
						deposit_map = new HashMap<>();
						deposit_map.put("status", "failed");
						financialDeposit.child(financialKey).updateChildren(deposit_map);
						deposit_map.clear();
						SketchwareUtil.showMessage(getApplicationContext(), "success");
						finish();
					}
				}
			}
		});
		
		blocked_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (getIntent().getStringExtra("activity").equals("users") || getIntent().getStringExtra("activity").equals("blocked users")) {
					if (!uid.equals("")) {
						totalBlocked_Map = new HashMap<>();
						totalBlocked_Map.put("risk type", "banned");
						db_accounts.child(uid).updateChildren(totalBlocked_Map);
						totalBlocked_Map.clear();
					}
				}
			}
		});
		
		unblock_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (getIntent().getStringExtra("activity").equals("users") || getIntent().getStringExtra("activity").equals("blocked users")) {
					if (!uid.equals("")) {
						totalBlocked_Map = new HashMap<>();
						totalBlocked_Map.put("risk type", "none");
						db_accounts.child(uid).updateChildren(totalBlocked_Map);
						totalBlocked_Map.clear();
					}
				}
			}
		});
		
		button7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (getIntent().getStringExtra("activity").equals("users") || getIntent().getStringExtra("activity").equals("blocked users")) {
					if (!uid.equals("")) {
						if (balance_edittext.getText().toString().equals("") || deposit_edittext.getText().toString().equals("")) {
							balance_edittext.setText(balanceEdittext);
							deposit_edittext.setText(depositEdittext);
						} else {
							money_Map = new HashMap<>();
							money_Map.put("balance", new DecimalFormat("0.000").format(Double.parseDouble(balance_edittext.getText().toString())));
							money_Map.put("deposit balance", new DecimalFormat("0.000").format(Double.parseDouble(deposit_edittext.getText().toString())));
							db_accounts.child(uid).updateChildren(money_Map);
							money_Map.clear();
						}
					}
				}
			}
		});
		
		button_checkHistory.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				nextIntent.setClass(getApplicationContext(), HistoryActivity.class);
				nextIntent.putExtra("uid", uid);
				ActivityOptions options = ActivityOptions.makeCustomAnimation(TotalActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(nextIntent, options.toBundle());
			}
		});
		
		button_addMethod.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (moneyName_txt.getText().toString().equals("Select currency symbol")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Select currency symbol");
				} else {
					if (dollars_txt.getText().toString().equals("")) {
						SketchwareUtil.showMessage(getApplicationContext(), "Enter Money");
					} else {
						if (convert_txt.getText().toString().equals("")) {
							SketchwareUtil.showMessage(getApplicationContext(), "Enter Convert Money");
						} else {
							if (fee_txt.getText().toString().equals("")) {
								SketchwareUtil.showMessage(getApplicationContext(), "Enter Fees");
							} else {
								_Material3Loader(true, "Saving....");
								if (methodImageSTR.equals("")) {
									if (path.equals("")) {
										SketchwareUtil.showMessage(getApplicationContext(), "Select a image");
									} else {
										ImageUploader.uploadImage(path, new ImageUploader.UploadCallback() {
												@Override
												public void onUploadComplete(String imageUrl) {
												if (keyMethods.equals("")) {
													MethodSKey = methods.push().getKey();
												} else {
													MethodSKey = keyMethods;
												}
												methods_map = new HashMap<>();
												methods_map.put("img url", imageUrl);
												methods_map.put("currency symbol", moneyName_txt.getText().toString());
												methods_map.put("dollar", new DecimalFormat("0.000").format(Double.parseDouble(dollars_txt.getText().toString())));
												methods_map.put("convert currency", new DecimalFormat("0.000").format(Double.parseDouble(convert_txt.getText().toString())));
												methods_map.put("fees", new DecimalFormat("0.000").format(Double.parseDouble(fee_txt.getText().toString())));
												methods_map.put("id", id_txt.getText().toString());
												methods_map.put("key", MethodSKey);
												methods.child(MethodSKey).updateChildren(methods_map);
												methods_map.clear();
												SketchwareUtil.showMessage(getApplicationContext(), "Success");
												_Material3Loader(false, "");
												finish();
											}
											
											 @Override
											public void onUploadError(String errorMessage) {
													
													
													
												SketchwareUtil.showMessage(getApplicationContext(), "Something went wrong");
											}
										});
										
									}
								} else {
									methods_map = new HashMap<>();
									methods_map.put("img url", methodImageSTR);
									methods_map.put("currency symbol", moneyName_txt.getText().toString());
									methods_map.put("dollar", new DecimalFormat("0.000").format(Double.parseDouble(dollars_txt.getText().toString())));
									methods_map.put("convert currency", new DecimalFormat("0.000").format(Double.parseDouble(convert_txt.getText().toString())));
									methods_map.put("fees", new DecimalFormat("0.000").format(Double.parseDouble(fee_txt.getText().toString())));
									methods_map.put("id", id_txt.getText().toString());
									methods_map.put("key", keyMethods);
									methods.child(keyMethods).updateChildren(methods_map);
									methods_map.clear();
									SketchwareUtil.showMessage(getApplicationContext(), "Success");
									_Material3Loader(false, "");
									finish();
								}
							}
						}
					}
				}
			}
		});
		
		methodImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(imgPicker, REQ_CD_IMGPICKER);
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_fab.hide();
				Total_list_Body.setVisibility(View.GONE);
				total_methods_body.setVisibility(View.VISIBLE);
			}
		});
		
		_db_accounts_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("activity").equals("users") || getIntent().getStringExtra("activity").equals("notification sender selector")) {
					totalUsers_listmap.add(_childValue);
					totalUsersAndBlocked_listview.setAdapter(new TotalUsersAndBlocked_listviewAdapter(totalUsers_listmap));
				} else {
					if (getIntent().getStringExtra("activity").equals("blocked users")) {
						if (_childValue.get("risk type").toString().equals("banned")) {
							totalUsers_listmap.add(_childValue);
							totalUsersAndBlocked_listview.setAdapter(new TotalUsersAndBlocked_listviewAdapter(totalUsers_listmap));
						}
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("activity").equals("users") || getIntent().getStringExtra("activity").equals("notification sender selector")) {
					totalUsersAndBlockedCount = 0;
					for(int _repeat27 = 0; _repeat27 < (int)(totalUsers_listmap.size()); _repeat27++) {
						if (totalUsers_listmap.get((int)totalUsersAndBlockedCount).get("uid").toString().equals(_childKey)) {
							totalUsers_listmap.remove((int)(totalUsersAndBlockedCount));
							totalUsers_listmap.add((int)totalUsersAndBlockedCount, _childValue);
						}
						totalUsersAndBlockedCount++;
					}
					totalUsersAndBlocked_listview.setAdapter(new TotalUsersAndBlocked_listviewAdapter(totalUsers_listmap));
				} else {
					if (getIntent().getStringExtra("activity").equals("blocked users")) {
						if (_childValue.get("risk type").toString().equals("banned")) {
							totalUsersAndBlockedCount = 0;
							for(int _repeat42 = 0; _repeat42 < (int)(totalUsers_listmap.size()); _repeat42++) {
								if (totalUsers_listmap.get((int)totalUsersAndBlockedCount).get("uid").toString().equals(_childKey)) {
									totalUsers_listmap.remove((int)(totalUsersAndBlockedCount));
									totalUsers_listmap.add((int)totalUsersAndBlockedCount, _childValue);
								}
								totalUsersAndBlockedCount++;
							}
							totalUsersAndBlocked_listview.setAdapter(new TotalUsersAndBlocked_listviewAdapter(totalUsers_listmap));
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
				if (getIntent().getStringExtra("activity").equals("users") || getIntent().getStringExtra("activity").equals("notification sender selector")) {
					totalUsersAndBlockedCount = 0;
					for(int _repeat14 = 0; _repeat14 < (int)(totalUsers_listmap.size()); _repeat14++) {
						if (totalUsers_listmap.get((int)totalUsersAndBlockedCount).get("uid").toString().equals(_childKey)) {
							totalUsers_listmap.remove((int)(totalUsersAndBlockedCount));
							totalUsers_listmap.add((int)totalUsersAndBlockedCount, _childValue);
						}
						totalUsersAndBlockedCount++;
					}
					totalUsersAndBlocked_listview.setAdapter(new TotalUsersAndBlocked_listviewAdapter(totalUsers_listmap));
				} else {
					if (getIntent().getStringExtra("activity").equals("blocked users")) {
						if (_childValue.get("risk type").toString().equals("banned")) {
							totalUsersAndBlockedCount = 0;
							for(int _repeat36 = 0; _repeat36 < (int)(totalUsers_listmap.size()); _repeat36++) {
								if (totalUsers_listmap.get((int)totalUsersAndBlockedCount).get("uid").toString().equals(_childKey)) {
									totalUsers_listmap.remove((int)(totalUsersAndBlockedCount));
									totalUsers_listmap.add((int)totalUsersAndBlockedCount, _childValue);
								}
								totalUsersAndBlockedCount++;
							}
							totalUsersAndBlocked_listview.setAdapter(new TotalUsersAndBlocked_listviewAdapter(totalUsers_listmap));
						}
					}
				}
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		db_accounts.addChildEventListener(_db_accounts_child_listener);
		
		_financial_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				try {
					if (getIntent().hasExtra("activity2")) {
						if (getIntent().getStringExtra("activity2").equals("pending payment")) {
							if (_childValue.get("status").toString().equals("pending")) {
								withdraw_listmap.add(_childValue);
								financial_listview.setAdapter(new Financial_listviewAdapter(withdraw_listmap));
							}
						} else {
							if (getIntent().getStringExtra("activity2").equals("payment success")) {
								if (_childValue.get("status").toString().equals("success")) {
									withdraw_listmap.add(_childValue);
									financial_listview.setAdapter(new Financial_listviewAdapter(withdraw_listmap));
								}
								linear_confirmation.setVisibility(View.GONE);
							} else {
								if (getIntent().getStringExtra("activity2").equals("payment rejected")) {
									if (_childValue.get("status").toString().equals("failed")) {
										withdraw_listmap.add(_childValue);
										financial_listview.setAdapter(new Financial_listviewAdapter(withdraw_listmap));
									}
									linear_confirmation.setVisibility(View.GONE);
								}
							}
						}
					}
				} catch (Exception e) {
					 
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
		financial.addChildEventListener(_financial_child_listener);
		
		_financialDeposit_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				try {
					if (getIntent().hasExtra("activity2")) {
						if (getIntent().getStringExtra("activity2").equals("pending deposit")) {
							if (_childValue.get("status").toString().equals("pending")) {
								deposit_listmap.add(_childValue);
								financial_listview.setAdapter(new Financial_listviewAdapter(deposit_listmap));
							}
						} else {
							if (getIntent().getStringExtra("activity2").equals("deposit success")) {
								if (_childValue.get("status").toString().equals("success")) {
									deposit_listmap.add(_childValue);
									financial_listview.setAdapter(new Financial_listviewAdapter(deposit_listmap));
								}
								linear_confirmation.setVisibility(View.GONE);
							} else {
								if (getIntent().getStringExtra("activity2").equals("pending deposit")) {
									if (_childValue.get("status").toString().equals("failed")) {
										deposit_listmap.add(_childValue);
										financial_listview.setAdapter(new Financial_listviewAdapter(deposit_listmap));
									}
									linear_confirmation.setVisibility(View.GONE);
								}
							}
						}
					}
				} catch (Exception e) {
					 
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
		financialDeposit.addChildEventListener(_financialDeposit_child_listener);
		
		_methods_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (getIntent().getStringExtra("activity").equals("methods")) {
					methods_Listmap.add(_childValue);
					methods_listview.setAdapter(new Methods_listviewAdapter(methods_Listmap));
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
		methods.addChildEventListener(_methods_child_listener);
		
		_accounts_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (!uid.equals("")) {
					if (_childKey.equals(uid)) {
						if (_childValue.containsKey("logo url")) {
							if (_childValue.get("logo url").toString().equals("")) {
								Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo url").toString())).into(imageview1);
							}
						} else {
							imageview1.setImageResource(R.drawable.app_logo);
						}
						if (_childValue.containsKey("name")) {
							textleft1.setText(_childValue.get("name").toString());
						}
						if (_childValue.containsKey("email")) {
							textleft2.setText(_childValue.get("email").toString());
						}
						if (_childValue.containsKey("balance")) {
							balance_edittext.setText(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("balance").toString())))));
							balanceEdittext = String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("balance").toString()))));
						}
						if (_childValue.containsKey("deposit balance")) {
							deposit_edittext.setText(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("deposit balance").toString())))));
							depositEdittext = String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("deposit balance").toString()))));
						}
						if (_childValue.containsKey("register date")) {
							textleft4.setText(_childValue.get("register date").toString());
						}
						if (_childValue.containsKey("social_media")) {
							textleft5.setText(_childValue.get("social_media").toString());
						}
						if (_childValue.containsKey("birthday")) {
							textleft6.setText(_childValue.get("birthday").toString());
						}
						if (_childValue.containsKey("device id")) {
							textleft7.setText(_childValue.get("device id").toString());
						}
						if (_childValue.containsKey("notification token")) {
							notificationTokenStr = _childValue.get("notification token").toString();
						}
						if (_childValue.containsKey("risk type")) {
							riskTypeStr = _childValue.get("risk type").toString();
							if (_childValue.get("risk type").toString().equals("banned")) {
								blocked_bt.setChecked(true);
								unblock_bt.setChecked(false);
							} else {
								if (_childValue.get("risk type").toString().equals("none")) {
									blocked_bt.setChecked(false);
									unblock_bt.setChecked(true);
								}
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
				accounts.addChildEventListener(_accounts_child_listener);
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				accounts.addChildEventListener(_accounts_child_listener);
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		accounts.addChildEventListener(_accounts_child_listener);
		
		_financialMethod2_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (!MethodKey2.equals("")) {
					if (_childKey.equals(MethodKey2)) {
						if (_childValue.containsKey("img url")) {
							Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("img url").toString())).into(currency_logo);
						} else {
							currency_logo.setImageResource(R.drawable.profile_logo);
						}
					}
					if (_childValue.containsKey("dollar")) {
						dollar = Double.parseDouble(_childValue.get("dollar").toString());
						money_txt3.setText(_childValue.get("dollar").toString().concat("$"));
					}
					if (_childValue.containsKey("currency symbol")) {
						currency_txt.setText(_childValue.get("currency symbol").toString().concat(" only"));
					}
					if (_childValue.containsKey("convert currency")) {
						convert_txt2.setText(_childValue.get("convert currency").toString());
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (!MethodKey2.equals("")) {
					if (_childKey.equals(MethodKey2)) {
						if (_childValue.containsKey("img url")) {
							Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("img url").toString())).into(currency_logo);
						} else {
							currency_logo.setImageResource(R.drawable.profile_logo);
						}
					}
					if (_childValue.containsKey("dollar")) {
						dollar = Double.parseDouble(_childValue.get("dollar").toString());
						money_txt3.setText(_childValue.get("dollar").toString().concat("$"));
					}
					if (_childValue.containsKey("currency symbol")) {
						currency_txt.setText(_childValue.get("currency symbol").toString().concat(" only"));
					}
					if (_childValue.containsKey("convert currency")) {
						convert_txt2.setText(_childValue.get("convert currency").toString());
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
				if (!MethodKey2.equals("")) {
					if (_childKey.equals(MethodKey2)) {
						if (_childValue.containsKey("img url")) {
							Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("img url").toString())).into(currency_logo);
						} else {
							currency_logo.setImageResource(R.drawable.profile_logo);
						}
					}
					if (_childValue.containsKey("dollar")) {
						dollar = Double.parseDouble(_childValue.get("dollar").toString());
						money_txt3.setText(_childValue.get("dollar").toString().concat("$"));
					}
					if (_childValue.containsKey("currency symbol")) {
						currency_txt.setText(_childValue.get("currency symbol").toString().concat(" only"));
					}
					if (_childValue.containsKey("convert currency")) {
						convert_txt2.setText(_childValue.get("convert currency").toString());
					}
				}
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		financialMethod2.addChildEventListener(_financialMethod2_child_listener);
		
		_db_accounts2_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (!uid2.equals("")) {
					if (_childKey.equals(uid2)) {
						if (_childValue.containsKey("logo url")) {
							if (_childValue.get("logo url").toString().equals("")) {
								Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo url").toString())).into(imageview1);
							}
						} else {
							imageview1.setImageResource(R.drawable.app_logo);
						}
						if (_childValue.containsKey("name")) {
							textleft1.setText(_childValue.get("name").toString());
						}
						if (_childValue.containsKey("email")) {
							textleft2.setText(_childValue.get("email").toString());
						}
					}
					if (_childValue.containsKey("deposit balance")) {
						depositEdittext = String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("deposit balance").toString()))));
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (!uid2.equals("")) {
					if (_childKey.equals(uid2)) {
						if (_childValue.containsKey("logo url")) {
							if (_childValue.get("logo url").toString().equals("")) {
								Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo url").toString())).into(imageview1);
							}
						} else {
							imageview1.setImageResource(R.drawable.app_logo);
						}
						if (_childValue.containsKey("name")) {
							textleft1.setText(_childValue.get("name").toString());
						}
						if (_childValue.containsKey("email")) {
							textleft2.setText(_childValue.get("email").toString());
						}
					}
					if (_childValue.containsKey("deposit balance")) {
						depositEdittext = String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("deposit balance").toString()))));
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
				if (!uid2.equals("")) {
					if (_childKey.equals(uid2)) {
						if (_childValue.containsKey("logo url")) {
							if (_childValue.get("logo url").toString().equals("")) {
								Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo url").toString())).into(imageview1);
							}
						} else {
							imageview1.setImageResource(R.drawable.app_logo);
						}
						if (_childValue.containsKey("name")) {
							textleft1.setText(_childValue.get("name").toString());
						}
						if (_childValue.containsKey("email")) {
							textleft2.setText(_childValue.get("email").toString());
						}
					}
					if (_childValue.containsKey("deposit balance")) {
						depositEdittext = String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("deposit balance").toString()))));
					}
				}
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		db_accounts2.addChildEventListener(_db_accounts2_child_listener);
	}
	
	private void initializeLogic() {
		//      Start part: Backend
		progressDialog = new MaterialProgressDialog(TotalActivity.this);
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
				if (getIntent().getStringExtra("activity").equals("users") || getIntent().getStringExtra("activity").equals("blocked users")) {
					if (uid.equals("")) {
						finish();
					} else {
						Total_list_Body.setVisibility(View.VISIBLE);
						Total_show_body.setVisibility(View.GONE);
						uid = "";
					}
				} else {
					if (getIntent().getStringExtra("activity").equals("methods")) {
						if (total_methods_body.getVisibility() == View.GONE) {
							finish();
						} else {
							Total_list_Body.setVisibility(View.VISIBLE);
							total_methods_body.setVisibility(View.GONE);
						}
					} else {
						if (getIntent().getStringExtra("activity").equals("withdraw") || getIntent().getStringExtra("activity").equals("deposit")) {
							if (uid2.equals("")) {
								finish();
							} else {
								Total_list_Body.setVisibility(View.VISIBLE);
								Total_show_body.setVisibility(View.GONE);
								uid2 = "";
							}
						}
					}
				}
						            }
				        });
		//      End part: On back
		//      End part: Backend
		//      Start part: Material 3 AutoCompleteTextView Setup
		dollars_tablayout.setEnabled(false);
		_m3AutoCompleteText();
		//      End part: Material 3 AutoCompleteTextView Setup
		//      Start part: Mathods
		totalUsersAndBlocked_listview.setLayoutManager(new LinearLayoutManager(this));
		financial_listview.setLayoutManager(new LinearLayoutManager(this));
		methods_listview.setLayoutManager(new LinearLayoutManager(this));
		_totalShow();
		try {
			if (getIntent().getStringExtra("activity").equals("users")) {
				if (getSupportActionBar() != null) {
					            getSupportActionBar().setTitle("Total Users");
					        }
				//      Start part: Total listview
				Total_list_Body.setVisibility(View.VISIBLE);
				Total_show_body.setVisibility(View.GONE);
				total_methods_body.setVisibility(View.GONE);
				financial_listview.setVisibility(View.GONE);
				methods_listview.setVisibility(View.GONE);
				_fab.hide();
				//      End part: Total listview
				//      Start part: Total show layout
				date2_body.setVisibility(View.GONE);
				orderid_linear.setVisibility(View.GONE);
				linear_confirmation.setVisibility(View.GONE);
				linear_financial.setVisibility(View.GONE);
				//      End part: Total show layout
			} else {
				if (getIntent().getStringExtra("activity").equals("blocked users")) {
					//      Start part: Total listview
					Total_show_body.setVisibility(View.GONE);
					Total_list_Body.setVisibility(View.VISIBLE);
					total_methods_body.setVisibility(View.GONE);
					financial_listview.setVisibility(View.GONE);
					methods_listview.setVisibility(View.GONE);
					_fab.hide();
					//      End part: Total listview
					//      Start part: Total show layout
					date2_body.setVisibility(View.GONE);
					orderid_linear.setVisibility(View.GONE);
					linear_confirmation.setVisibility(View.GONE);
					linear_financial.setVisibility(View.GONE);
					//      End part: Total show layout
					if (getSupportActionBar() != null) {
						            getSupportActionBar().setTitle("Total Blocked Users");
						        }
				} else {
					if (getIntent().getStringExtra("activity").equals("withdraw")) {
						if (getSupportActionBar() != null) {
							            getSupportActionBar().setTitle("Total Withdraw");
							        }
						Total_show_body.setVisibility(View.GONE);
						Total_list_Body.setVisibility(View.VISIBLE);
						methods_listview.setVisibility(View.GONE);
						total_methods_body.setVisibility(View.GONE);
						financial_listview.setVisibility(View.VISIBLE);
						_fab.hide();
						linear_financial.setVisibility(View.VISIBLE);
						//      Start part: Total show layout
						linear_balance.setVisibility(View.GONE);
						linear_deposit.setVisibility(View.GONE);
						linear_birthday.setVisibility(View.GONE);
						linear_register_date.setVisibility(View.GONE);
						linear_social_media.setVisibility(View.GONE);
						orderid_linear.setVisibility(View.GONE);
						button_checkHistory.setVisibility(View.GONE);
						linear_risk_type.setVisibility(View.GONE);
						linear_button1.setVisibility(View.GONE);
						linear_device_id.setVisibility(View.GONE);
						//      End part: Total show layout
					} else {
						if (getIntent().getStringExtra("activity").equals("deposit")) {
							if (getSupportActionBar() != null) {
								            getSupportActionBar().setTitle("Total Deposit");
								        }
							Total_show_body.setVisibility(View.GONE);
							Total_list_Body.setVisibility(View.VISIBLE);
							methods_listview.setVisibility(View.GONE);
							total_methods_body.setVisibility(View.GONE);
							financial_listview.setVisibility(View.VISIBLE);
							_fab.hide();
							linear_financial.setVisibility(View.VISIBLE);
							//      Start part: Total show layout
							linear_device_id.setVisibility(View.GONE);
							linear_balance.setVisibility(View.GONE);
							linear_deposit.setVisibility(View.GONE);
							linear_birthday.setVisibility(View.GONE);
							linear_register_date.setVisibility(View.GONE);
							linear_social_media.setVisibility(View.GONE);
							button_checkHistory.setVisibility(View.GONE);
							linear_risk_type.setVisibility(View.GONE);
							linear_button1.setVisibility(View.GONE);
							//      End part: Total show layout
						} else {
							if (getIntent().getStringExtra("activity").equals("methods")) {
								if (getSupportActionBar() != null) {
									            getSupportActionBar().setTitle("Total Methods");
									        }
								Total_list_Body.setVisibility(View.VISIBLE);
								Total_show_body.setVisibility(View.GONE);
								total_methods_body.setVisibility(View.GONE);
								linear_financial.setVisibility(View.GONE);
								_fab.show();
							} else {
								if (getIntent().getStringExtra("activity").equals("notification sender selector")) {
									if (getSupportActionBar() != null) {
										            getSupportActionBar().setTitle("Notification Sender Selector");
										        }
									Total_list_Body.setVisibility(View.VISIBLE);
									Total_show_body.setVisibility(View.GONE);
									total_methods_body.setVisibility(View.GONE);
									linear_financial.setVisibility(View.GONE);
									_fab.hide();
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			 
		}
		//      End part: Mathods
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_IMGPICKER:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				path = _filePath.get((int)(0));
				methodImage.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(path, 1024, 1024));
				methodImageSTR = "";
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	public void _totalShow() {
		
	}
	
	
	public void _usersShowLayout() {
		
	}
	
	
	public void _Material3Loader(final boolean _variable, final String _title) {
		if (_variable) {
			progressDialog.show(_title);
		} else {
			
			progressDialog.dismiss();
		}
	}
	
	
	public void _m3AutoCompleteText() {
		moneyName_list_string.add("USDT");
		moneyName_list_string.add("SAR");
		moneyName_list_string.add("BDT");
		moneyName_list_string.add("PKR");
		moneyName_list_string.add("INR");
		moneyName_list_string.add("KRW");
		moneyName_list_string.add("JPY");
		moneyName_list_string.add("IDR");
		moneyName_list_string.add("CAD");
		moneyName_list_string.add("BRL");
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_dropdown_item, moneyName_list_string);
		moneyName_txt.setAdapter(adapter);
	}
	
	public class TotalUsersAndBlocked_listviewAdapter extends RecyclerView.Adapter<TotalUsersAndBlocked_listviewAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public TotalUsersAndBlocked_listviewAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.usersandblocked_cus, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView mainBody = _view.findViewById(R.id.mainBody);
			final LinearLayout linear21 = _view.findViewById(R.id.linear21);
			final LinearLayout linear19 = _view.findViewById(R.id.linear19);
			final LinearLayout linear20 = _view.findViewById(R.id.linear20);
			final TextView Ranktext = _view.findViewById(R.id.Ranktext);
			final de.hdodenhof.circleimageview.CircleImageView icon = _view.findViewById(R.id.icon);
			final TextView name = _view.findViewById(R.id.name);
			final TextView balance = _view.findViewById(R.id.balance);
			final Button button_unblock = _view.findViewById(R.id.button_unblock);
			
			Ranktext.setText(String.valueOf((long)(_position + 1)));
			if (_data.get((int)_position).containsKey("name")) {
				name.setText(_data.get((int)_position).get("name").toString());
			}
			if (_data.get((int)_position).containsKey("balance")) {
				balance.setText(new DecimalFormat("0.000").format(Double.parseDouble(_data.get((int)_position).get("balance").toString())));
			}
			if (_data.get((int)_position).containsKey("logo url")) {
				if (_data.get((int)_position).get("logo url").toString().equals("")) {
					icon.setImageResource(R.drawable.profile_logo);
				} else {
					Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("logo url").toString())).into(icon);
				}
			} else {
				icon.setImageResource(R.drawable.profile_logo);
			}
			if (getIntent().getStringExtra("activity").equals("users")) {
				button_unblock.setVisibility(View.GONE);
			} else {
				if (getIntent().getStringExtra("activity").equals("blocked users")) {
					button_unblock.setVisibility(View.VISIBLE);
				} else {
					if (getIntent().getStringExtra("activity").equals("notification sender selector")) {
						button_unblock.setVisibility(View.VISIBLE);
						button_unblock.setText("Select");
					}
				}
			}
			if (getIntent().getStringExtra("activity").equals("notification sender selector")) {
				button_unblock.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						if (_data.get((int)_position).containsKey("notification token")) {
							nextIntent.setClass(getApplicationContext(), NotificationManagerActivity.class);
							nextIntent.putExtra("activity", "selected");
							nextIntent.putExtra("token", _data.get((int)_position).get("notification token").toString());
							ActivityOptions noptions = ActivityOptions.makeCustomAnimation(TotalActivity.this, R.anim.fade_in, R.anim.fade_out);
							startActivity(nextIntent, noptions.toBundle());
							finish();
						}
					}
				});
			} else {
				button_unblock.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						mainBody.setVisibility(View.GONE);
						totalBlocked_Map = new HashMap<>();
						totalBlocked_Map.put("risk type", "none");
						db_accounts.child(_data.get((int)_position).get("uid").toString()).updateChildren(totalBlocked_Map);
						totalBlocked_Map.clear();
					}
				});
				mainBody.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						uid = _data.get((int)_position).get("uid").toString();
						Total_list_Body.setVisibility(View.GONE);
						Total_show_body.setVisibility(View.VISIBLE);
						accounts.addChildEventListener(_accounts_child_listener);
					}
				});
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
	
	public class Financial_listviewAdapter extends RecyclerView.Adapter<Financial_listviewAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Financial_listviewAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.financial_cus, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView linear_main = _view.findViewById(R.id.linear_main);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final de.hdodenhof.circleimageview.CircleImageView logo = _view.findViewById(R.id.logo);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView currency = _view.findViewById(R.id.currency);
			final TextView date = _view.findViewById(R.id.date);
			final TextView money = _view.findViewById(R.id.money);
			final TextView status = _view.findViewById(R.id.status);
			
			logo.setVisibility(View.GONE);
			if (_data.get((int)_position).containsKey("amount")) {
				money.setText(_data.get((int)_position).get("amount").toString().concat("$"));
			}
			if (getIntent().getStringExtra("activity").equals("deposit")) {
				currency.setText("New Deposit Request!");
			} else {
				if (getIntent().getStringExtra("activity").equals("withdraw")) {
					currency.setText("New Withdraw Request!");
				}
			}
			if (_data.get((int)_position).containsKey("date")) {
				date.setText(_data.get((int)_position).get("date").toString());
			}
			if (_data.get((int)_position).containsKey("status")) {
				if (_data.get((int)_position).get("status").toString().equals("pending")) {
					status.setText(_data.get((int)_position).get("status").toString());
					status.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.md_theme_light_errorContainer));
				} else {
					if (_data.get((int)_position).get("status").toString().equals("failed")) {
						status.setText(_data.get((int)_position).get("status").toString());
						status.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorError));
					} else {
						if (_data.get((int)_position).get("status").toString().equals("success")) {
							status.setText(_data.get((int)_position).get("status").toString());
							status.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
						}
					}
				}
			}
			linear_main.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					Total_list_Body.setVisibility(View.GONE);
					Total_show_body.setVisibility(View.VISIBLE);
					if (_data.get((int)_position).containsKey("uid")) {
						uid2 = _data.get((int)_position).get("uid").toString();
					}
					if (_data.get((int)_position).containsKey("key")) {
						financialKey = _data.get((int)_position).get("key").toString();
					}
					if (_data.get((int)_position).containsKey("method key")) {
						MethodKey2 = _data.get((int)_position).get("method key").toString();
					}
					if (getIntent().getStringExtra("activity").equals("deposit")) {
						if (_data.get((int)_position).containsKey("amount")) {
							amount_txt.setText(_data.get((int)_position).get("amount").toString());
							amount = Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_data.get((int)_position).get("amount").toString())));
						}
						if (_data.get((int)_position).containsKey("status")) {
							status_txt.setText(_data.get((int)_position).get("status").toString());
						}
						if (_data.get((int)_position).containsKey("date")) {
							date2_txt.setText(_data.get((int)_position).get("date").toString());
						}
						if (_data.get((int)_position).containsKey("num")) {
							num_txt.setText(_data.get((int)_position).get("num").toString());
						}
						if (_data.get((int)_position).containsKey("orderid")) {
							order_id.setText(_data.get((int)_position).get("orderid").toString());
						}
						financialMethod2.addChildEventListener(_financialMethod2_child_listener);
						db_accounts2.addChildEventListener(_db_accounts2_child_listener);
					} else {
						if (getIntent().getStringExtra("activity").equals("withdraw")) {
							if (_data.get((int)_position).containsKey("amount")) {
								amount_txt.setText(_data.get((int)_position).get("amount").toString());
							}
							if (_data.get((int)_position).containsKey("status")) {
								status_txt.setText(_data.get((int)_position).get("status").toString());
							}
							if (_data.get((int)_position).containsKey("date")) {
								date2_txt.setText(_data.get((int)_position).get("date").toString());
							}
							if (_data.get((int)_position).containsKey("num")) {
								num_txt.setText(_data.get((int)_position).get("num").toString());
							}
							financialMethod2.addChildEventListener(_financialMethod2_child_listener);
							db_accounts2.addChildEventListener(_db_accounts2_child_listener);
						}
					}
				}
			});
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
	
	public class Methods_listviewAdapter extends RecyclerView.Adapter<Methods_listviewAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Methods_listviewAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.methods_cus, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView mainBody = _view.findViewById(R.id.mainBody);
			final LinearLayout linear21 = _view.findViewById(R.id.linear21);
			final LinearLayout linear20 = _view.findViewById(R.id.linear20);
			final de.hdodenhof.circleimageview.CircleImageView icon = _view.findViewById(R.id.icon);
			final TextView balance = _view.findViewById(R.id.balance);
			final Button button_unblock = _view.findViewById(R.id.button_unblock);
			
			try {
				balance.setText(_data.get((int)_position).get("dollar").toString().concat(" = ".concat(_data.get((int)_position).get("convert currency").toString().concat(" ".concat(_data.get((int)_position).get("currency symbol").toString())))));
			} catch (Exception e) {
				 
			}
			if (_data.get((int)_position).containsKey("img url")) {
				Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("img url").toString())).into(icon);
			}
			button_unblock.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (_data.get((int)_position).containsKey("key")) {
						methods.child(_data.get((int)_position).get("key").toString()).removeValue();
						mainBody.setVisibility(View.GONE);
					}
				}
			});
			mainBody.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					Total_list_Body.setVisibility(View.GONE);
					total_methods_body.setVisibility(View.VISIBLE);
					button_addMethod.setText("Update This Method");
					keyMethods = _data.get((int)_position).get("key").toString();
					if (_data.get((int)_position).containsKey("dollar")) {
						dollars_txt.setText(_data.get((int)_position).get("dollar").toString());
					}
					if (_data.get((int)_position).containsKey("convert currency")) {
						convert_txt.setText(_data.get((int)_position).get("convert currency").toString());
					}
					if (_data.get((int)_position).containsKey("currency symbol")) {
						moneyName_txt.setText(_data.get((int)_position).get("currency symbol").toString());
					}
					if (_data.get((int)_position).containsKey("fees")) {
						fee_txt.setText(_data.get((int)_position).get("fees").toString());
					}
					if (_data.get((int)_position).containsKey("img url")) {
						methodImageSTR = _data.get((int)_position).get("img url").toString();
						Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("img url").toString())).into(methodImage);
					}
					if (_data.get((int)_position).containsKey("id")) {
						id_txt.setText(_data.get((int)_position).get("id").toString());
					}
					_m3AutoCompleteText();
					_fab.hide();
				}
			});
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