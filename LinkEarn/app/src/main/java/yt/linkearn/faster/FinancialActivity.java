package yt.linkearn.faster;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.widget.ImageView;
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
import androidx.startup.*;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.card.*;
import com.google.android.material.divider.MaterialDivider;
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
import de.hdodenhof.circleimageview.*;
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
import androidx.activity.OnBackPressedCallback;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import yt.linkearn.faster.FCMNotificationSender;

public class FinancialActivity extends AppCompatActivity {

private MaterialProgressDialog progressDialog;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String dollar_str = "";
	private String convert_str = "";
	private String currency_str = "";
	private String fees_str = "";
	private String selectPosition = "";
	private String img_url = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String methodKey = "";
	private String key = "";
	private double balance_main = 0;
	private HashMap<String, Object> balanceMap = new HashMap<>();
	private HashMap<String, Object> notification_map = new HashMap<>();
	private String ProjectID = "";
	private String accessToken = "";
	private String onSuccess = "";
	private String onError = "";
	private HashMap<String, Object> notification_Map = new HashMap<>();
	private String accessTokenError = "";
	
	private ArrayList<HashMap<String, Object>> methods_listmap = new ArrayList<>();
	
	private CollapsingToolbarLayout collapsingtoolbar1;
	private NestedScrollView vscroll1;
	private LinearLayout linear1;
	private RelativeLayout withdraw_body;
	private MaterialCardView important_note;
	private TextInputLayout number_tablayout;
	private TextInputLayout amount_tablayout;
	private TextInputLayout orderid_tablayout;
	private Button apply_button;
	private LinearLayout linear2;
	private GridView methods_listview;
	private LinearLayout linear65;
	private LinearLayout linear64;
	private LinearLayout linear63;
	private ImageView imageview22;
	private TextView textview17;
	private TextView textview18;
	private EditText number_txt;
	private EditText amount_txt;
	private EditText orderid_txt;
	private TextView txt;
	private LinearLayout linear88;
	private LinearLayout linear3;
	private LinearLayout linear7;
	private MaterialDivider linear11;
	private LinearLayout linear9;
	private LinearLayout linear78;
	private LinearLayout linear_selected_Body;
	private TextView money_txt;
	private LinearLayout linear_equal;
	private TextView convert_txt;
	private MaterialCardView equal1;
	private MaterialCardView linear36;
	private TextView textview7;
	private LinearLayout linear4;
	private TextView amount_txt2;
	private TextView textview9;
	private TextView textview13;
	private LinearLayout linear8;
	private TextView fee_txt;
	private TextView textview15;
	private TextView textview16;
	private LinearLayout linear10;
	private TextView total_txt;
	private TextView textview19;
	private TextView available_main_txt;
	private LinearLayout linear79;
	private TextView available_txt;
	private TextView textview38;
	private TextView textview39;
	private MaterialCardView bg;
	private LinearLayout linear67;
	private CircleImageView img2;
	private LinearLayout linear90;
	private TextView money_name2;
	private TextView receive_id2;
	
	private Intent backIntent = new Intent();
	private DatabaseReference financialWithdraw = _firebase.getReference("financial/withdraw");
	private ChildEventListener _financialWithdraw_child_listener;
	private DatabaseReference financial = _firebase.getReference("financial/deposit");
	private ChildEventListener _financial_child_listener;
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
	private Intent successIntent = new Intent();
	private SharedPreferences save_data;
	private RequestNetwork requestNet;
	private RequestNetwork.RequestListener _requestNet_request_listener;
	private DatabaseReference financialMethods = _firebase.getReference("financial/methods");
	private ChildEventListener _financialMethods_child_listener;
	private AlertDialog.Builder error;
	private Calendar date_get = Calendar.getInstance();
	private TimerTask timer;
	private Intent success = new Intent();
	private TimerTask timer2;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.financial);
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
		linear1 = findViewById(R.id.linear1);
		withdraw_body = findViewById(R.id.withdraw_body);
		important_note = findViewById(R.id.important_note);
		number_tablayout = findViewById(R.id.number_tablayout);
		amount_tablayout = findViewById(R.id.amount_tablayout);
		orderid_tablayout = findViewById(R.id.orderid_tablayout);
		apply_button = findViewById(R.id.apply_button);
		linear2 = findViewById(R.id.linear2);
		methods_listview = findViewById(R.id.methods_listview);
		linear65 = findViewById(R.id.linear65);
		linear64 = findViewById(R.id.linear64);
		linear63 = findViewById(R.id.linear63);
		imageview22 = findViewById(R.id.imageview22);
		textview17 = findViewById(R.id.textview17);
		textview18 = findViewById(R.id.textview18);
		number_txt = findViewById(R.id.number_txt);
		amount_txt = findViewById(R.id.amount_txt);
		orderid_txt = findViewById(R.id.orderid_txt);
		txt = findViewById(R.id.txt);
		linear88 = findViewById(R.id.linear88);
		linear3 = findViewById(R.id.linear3);
		linear7 = findViewById(R.id.linear7);
		linear11 = findViewById(R.id.linear11);
		linear9 = findViewById(R.id.linear9);
		linear78 = findViewById(R.id.linear78);
		linear_selected_Body = findViewById(R.id.linear_selected_Body);
		money_txt = findViewById(R.id.money_txt);
		linear_equal = findViewById(R.id.linear_equal);
		convert_txt = findViewById(R.id.convert_txt);
		equal1 = findViewById(R.id.equal1);
		linear36 = findViewById(R.id.linear36);
		textview7 = findViewById(R.id.textview7);
		linear4 = findViewById(R.id.linear4);
		amount_txt2 = findViewById(R.id.amount_txt2);
		textview9 = findViewById(R.id.textview9);
		textview13 = findViewById(R.id.textview13);
		linear8 = findViewById(R.id.linear8);
		fee_txt = findViewById(R.id.fee_txt);
		textview15 = findViewById(R.id.textview15);
		textview16 = findViewById(R.id.textview16);
		linear10 = findViewById(R.id.linear10);
		total_txt = findViewById(R.id.total_txt);
		textview19 = findViewById(R.id.textview19);
		available_main_txt = findViewById(R.id.available_main_txt);
		linear79 = findViewById(R.id.linear79);
		available_txt = findViewById(R.id.available_txt);
		textview38 = findViewById(R.id.textview38);
		textview39 = findViewById(R.id.textview39);
		bg = findViewById(R.id.bg);
		linear67 = findViewById(R.id.linear67);
		img2 = findViewById(R.id.img2);
		linear90 = findViewById(R.id.linear90);
		money_name2 = findViewById(R.id.money_name2);
		receive_id2 = findViewById(R.id.receive_id2);
		authentication = FirebaseAuth.getInstance();
		save_data = getSharedPreferences("save_data", Activity.MODE_PRIVATE);
		requestNet = new RequestNetwork(this);
		error = new AlertDialog.Builder(this);
		
		apply_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (number_txt.getText().toString().equals("")) {
					_MaterialErrorDialog("Fill Number/id");
				} else {
					if (amount_txt.getText().toString().equals("")) {
						_MaterialErrorDialog("Fill Amount.");
					} else {
						if (getIntent().getStringExtra("activity").equals("deposit")) {
							if (orderid_txt.getText().toString().equals("")) {
								_MaterialErrorDialog("Fill this item");
							} else {
								_Material3Loader(true, "Saving...");
								key = financial.push().getKey();
								map = new HashMap<>();
								map.put("num", number_txt.getText().toString());
								map.put("amount", amount_txt.getText().toString());
								map.put("orderid", orderid_txt.getText().toString());
								map.put("key", key);
								map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
								map.put("status", "pending");
								map.put("method key", methodKey);
								date_get = Calendar.getInstance();
								map.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm").format(date_get.getTime()));
								financial.child(key).updateChildren(map);
								map.clear();
								_notification("New deposit request has arrived!", amount_txt.getText().toString().concat("$"), img_url, "", "admin", "deposit pending");
								timer = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												_Material3Loader(false, "");
												success.setClass(getApplicationContext(), SuccessActivity.class);
												success.putExtra("activity2", "Deposit Request Added. Deposit money will be added to your account within 1 to 5 hours. (Admin Verify)");
												ActivityOptions options = ActivityOptions.makeCustomAnimation(FinancialActivity.this, R.anim.fade_in, R.anim.fade_out);
												startActivity(success, options.toBundle());
												timer.cancel();
												finish();
											}
										});
									}
								};
								_timer.schedule(timer, (int)(3000));
							}
						} else {
							if ((balance_main == Double.parseDouble(total_txt.getText().toString())) || (balance_main > Double.parseDouble(total_txt.getText().toString()))) {
								_Material3Loader(true, "Saving...");
								key = financialWithdraw.push().getKey();
								map = new HashMap<>();
								map.put("num", number_txt.getText().toString());
								map.put("amount", amount_txt.getText().toString());
								map.put("fees", fee_txt.getText().toString());
								map.put("key", key);
								map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
								map.put("status", "pending");
								map.put("method key", methodKey);
								date_get = Calendar.getInstance();
								map.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm").format(date_get.getTime()));
								financialWithdraw.child(key).updateChildren(map);
								map.clear();
								//      Start part: Balance -
								balanceMap = new HashMap<>();
								balanceMap.put("balance", new DecimalFormat("0.000").format(Double.parseDouble(total_txt.getText().toString()) - balance_main).replace("-", ""));
								db_accounts.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(balanceMap);
								balanceMap.clear();
								//      End part: Balance -
								_notification("New deposit request has arrived!", amount_txt.getText().toString().concat("$"), "", "", "admin", "withdraw pending");
								timer = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												_Material3Loader(false, "");
												success.setClass(getApplicationContext(), SuccessActivity.class);
												success.putExtra("activity2", "Withdraw Request Added. Withdraw money will be added to your account within 1 to 5 hours. (Admin Verify)");
												ActivityOptions options = ActivityOptions.makeCustomAnimation(FinancialActivity.this, R.anim.fade_in, R.anim.fade_out);
												startActivity(success, options.toBundle());
												timer.cancel();
												finish();
											}
										});
									}
								};
								_timer.schedule(timer, (int)(3000));
							} else {
								_MaterialErrorDialog("Not enough money! please deposit some money.");
							}
						}
					}
				}
			}
		});
		
		amount_txt.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				amount_txt2.setText(_charSeq.concat("$"));
				try {
					total_txt.setText(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(fees_str) + Double.parseDouble(_charSeq)))));
					if (getIntent().getStringExtra("activity").equals("withdraw")) {
						timer2 = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										available_txt.setText(new DecimalFormat("0.000").format(Double.parseDouble(total_txt.getText().toString()) - balance_main).replace("-", ""));
										timer2.cancel();
									}
								});
							}
						};
						_timer.schedule(timer2, (int)(300));
					} else {
						available_txt.setText(_charSeq.concat("$"));
					}
				} catch (Exception e) {
					 
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		_financialWithdraw_child_listener = new ChildEventListener() {
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
		financialWithdraw.addChildEventListener(_financialWithdraw_child_listener);
		
		_financial_child_listener = new ChildEventListener() {
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
		financial.addChildEventListener(_financial_child_listener);
		
		_db_accounts_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					_Material3Loader(false, "");
					if (_childValue.containsKey("balance")) {
						balance_main = Double.parseDouble(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("balance").toString())))));
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("balance")) {
						balance_main = Double.parseDouble(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("balance").toString())))));
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
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("balance")) {
						balance_main = Double.parseDouble(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("balance").toString())))));
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
		
		_requestNet_request_listener = new RequestNetwork.RequestListener() {
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
		
		_financialMethods_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				methods_listmap.add(_childValue);
				methods_listview.setAdapter(new Methods_listviewAdapter(methods_listmap));
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
		financialMethods.addChildEventListener(_financialMethods_child_listener);
		
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
		progressDialog = new MaterialProgressDialog(FinancialActivity.this);
		//      Start part: On back
		getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
				            @Override
				            public void handleOnBackPressed() {
				backIntent.setClass(getApplicationContext(), WalletActivity.class);
				ActivityOptions options = ActivityOptions.makeCustomAnimation(FinancialActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(backIntent, options.toBundle());
				finish();
						            }
				        });
		//      End part: On back
		//      End part: Backend
		//      Start part: others
		_Material3Loader(true, "Getting financial data...");
		if (getIntent().getStringExtra("activity").equals("withdraw")) {
			orderid_tablayout.setVisibility(View.GONE);
			amount_tablayout.setEnabled(false);
			number_tablayout.setEnabled(false);
			apply_button.setEnabled(false);
			if (getSupportActionBar() != null) {
				    getSupportActionBar().setTitle("Withdraw your money");
			}
			txt.setText("Withdraw!");
			available_main_txt.setText("Available Balance:");
		} else {
			if (getIntent().getStringExtra("activity").equals("deposit")) {
				amount_tablayout.setEnabled(false);
				number_tablayout.setEnabled(false);
				orderid_tablayout.setEnabled(false);
				apply_button.setEnabled(false);
				if (getSupportActionBar() != null) {
					    getSupportActionBar().setTitle("Deposit your money");
				}
				txt.setText("Deposit!");
				available_main_txt.setText("Receive real money:");
			}
		}
		//      End part: others
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			timer.cancel();
		} catch (Exception e) {
			 
		}
		try {
			timer2.cancel();
		} catch (Exception e) {
			 
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
		MaterialAlertDialogBuilder error = new MaterialAlertDialogBuilder(FinancialActivity.this);
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
	
	
	public void _TransitionManager(final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
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
	
	public class Methods_listviewAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Methods_listviewAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.methods_cus, null);
			}
			
			final com.google.android.material.card.MaterialCardView bg = _view.findViewById(R.id.bg);
			final LinearLayout linear67 = _view.findViewById(R.id.linear67);
			final de.hdodenhof.circleimageview.CircleImageView img = _view.findViewById(R.id.img);
			final TextView money_name = _view.findViewById(R.id.money_name);
			final TextView receive_id = _view.findViewById(R.id.receive_id);
			
			if (_data.get((int)_position).containsKey("currency symbol")) {
				money_name.setText(_data.get((int)_position).get("currency symbol").toString().concat(" Only"));
			}
			if (_data.get((int)_position).containsKey("id")) {
				receive_id.setText(_data.get((int)_position).get("id").toString());
			}
			if (_data.get((int)_position).containsKey("img url")) {
				Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("img url").toString())).into(img);
			} else {
				img.setImageResource(R.drawable.profile_logo);
			}
			bg.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (_data.get((int)_position).containsKey("id")) {
						receive_id2.setText(_data.get((int)_position).get("id").toString());
						((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", _data.get((int)_position).get("id").toString()));
						SketchwareUtil.showMessage(getApplicationContext(), "Number/Id Copy Success.");
					}
					if (_data.get((int)_position).containsKey("dollar")) {
						dollar_str = _data.get((int)_position).get("dollar").toString().concat("$");
						money_txt.setText(_data.get((int)_position).get("dollar").toString());
					}
					if (_data.get((int)_position).containsKey("convert currency")) {
						convert_str = _data.get((int)_position).get("convert currency").toString();
						convert_txt.setText(_data.get((int)_position).get("convert currency").toString().concat("".concat(_data.get((int)_position).get("currency symbol").toString())));
						money_name2.setText(_data.get((int)_position).get("convert currency").toString().concat("".concat(_data.get((int)_position).get("currency symbol").toString())));
					}
					if (_data.get((int)_position).containsKey("currency symbol")) {
						currency_str = _data.get((int)_position).get("currency symbol").toString();
					}
					if (_data.get((int)_position).containsKey("fees")) {
						fee_txt.setText(_data.get((int)_position).get("fees").toString().concat("$"));
						fees_str = _data.get((int)_position).get("fees").toString();
					}
					if (_data.get((int)_position).containsKey("img url")) {
						img_url = _data.get((int)_position).get("img url").toString();
						Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("img url").toString())).into(img2);
					}
					if (_data.get((int)_position).containsKey("key")) {
						methodKey = _data.get((int)_position).get("key").toString();
					}
					orderid_tablayout.setEnabled(true);
					amount_tablayout.setEnabled(true);
					number_tablayout.setEnabled(true);
					apply_button.setEnabled(true);
					linear_selected_Body.setVisibility(View.VISIBLE);
					//      Start part: Access Notification Token
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
								            AccessTokenGenerator.generateToken(inputStream, new AccessTokenGenerator.OnTokenResponse() {
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
					
					//      End part: Access Notification Token
				}
			});
			
			return _view;
		}
	}
}