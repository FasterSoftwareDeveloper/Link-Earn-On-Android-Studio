package yt.linkearn.faster;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.google.android.material.card.*;
import com.google.android.material.search.SearchBar;
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
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.datepicker.MaterialDatePicker;
import java.util.Calendar;
import androidx.activity.OnBackPressedCallback;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class AccountActivity extends AppCompatActivity {

private MaterialProgressDialog progressDialog;
	
	public final int REQ_CD_IMAGEPICKER = 101;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double deposit_balance = 0;
	private double main_balance = 0;
	private String accountSecret = "";
	private String path = "";
	private HashMap<String, Object> saveMap = new HashMap<>();
	private String imgUrl = "";
	private String socialMedia = "";
	private String deepLink = "";
	private String replaceUrl = "";
	private boolean modePass = false;
	private String user_id = "";
	private boolean socialCheck = false;
	
	private ArrayList<HashMap<String, Object>> taskMap = new ArrayList<>();
	private ArrayList<String> complete_list1 = new ArrayList<>();
	private ArrayList<String> complete_list2 = new ArrayList<>();
	
	private ScrollView vscroll2;
	private LinearLayout body;
	private RelativeLayout accountEditBody;
	private LinearLayout accountBody;
	private TextInputLayout FullName_textinput;
	private TextInputLayout SocialMedia_textinput;
	private MaterialCardView date;
	private LinearLayout linear3;
	private TextInputLayout CorrectPass_textinput;
	private TextInputLayout New_Pass_textinput;
	private TextView textview1;
	private LinearLayout linear_icon;
	private TextInputLayout bio_textinput;
	private TextInputEditText FullName_editText;
	private TextInputEditText SocialMedia_edittext;
	private TextView date_txt;
	private Button button1;
	private Button button2;
	private TextInputEditText Currect_Passwordedittext;
	private TextInputEditText NewPassword_edittext;
	private CircleImageView circleimageview1;
	private TextInputEditText bio_edittext;
	private SearchBar search_bar;
	private CircleImageView iconAccount;
	private LinearLayout linear4;
	private TextView bio;
	private LinearLayout linear_Dollar;
	private LinearLayout interaction_lay;
	private MaterialCardView about_lay;
	private MaterialCardView linear11;
	private RecyclerView recyclerview1;
	private TextView name;
	private LinearLayout linear18;
	private MaterialCardView linear19;
	private MaterialCardView linear20;
	private LinearLayout linear22;
	private ImageView imageview11;
	private TextView Dollars;
	private LinearLayout linear23;
	private ImageView imageview12;
	private TextView depositDollars;
	private MaterialCardView ShareProfile;
	private MaterialCardView message;
	private MaterialCardView edit_Bt;
	private LinearLayout linear13;
	private ImageView imageview5;
	private TextView textview14;
	private LinearLayout linear12;
	private ImageView imageview6;
	private TextView textview15;
	private LinearLayout linear16;
	private ImageView imageview2;
	private LinearLayout linear14;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear21;
	private ImageView imageview9;
	private TextView textview16;
	private TextView email_txt;
	private ImageView imageview10;
	private TextView textview18;
	private TextView birt_txt;
	private ImageView imageview13;
	private TextView textview25;
	private TextView joinedUs_txt;
	private LinearLayout linear15;
	private TextView textview24;
	
	private DatabaseReference Task1 = _firebase.getReference("Task/task1");
	private ChildEventListener _Task1_child_listener;
	private DatabaseReference Task2 = _firebase.getReference("Task/task2");
	private ChildEventListener _Task2_child_listener;
	private DatabaseReference db_accounts = _firebase.getReference("db_accounts");
	private ChildEventListener _db_accounts_child_listener;
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
	
	private Intent imagePicker = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent task1_intent = new Intent();
	private Intent task2_intent = new Intent();
	private DatabaseReference completedTask1 = _firebase.getReference("Task/completedTask1");
	private ChildEventListener _completedTask1_child_listener;
	private DatabaseReference completedTask2 = _firebase.getReference("Task/completedTask2");
	private ChildEventListener _completedTask2_child_listener;
	private Intent backIntent = new Intent();
	private Intent viewIntent = new Intent();
	private TimerTask saveTimer;
	private AlertDialog.Builder error;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.account);
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
		vscroll2 = findViewById(R.id.vscroll2);
		body = findViewById(R.id.body);
		accountEditBody = findViewById(R.id.accountEditBody);
		accountBody = findViewById(R.id.accountBody);
		FullName_textinput = findViewById(R.id.FullName_textinput);
		SocialMedia_textinput = findViewById(R.id.SocialMedia_textinput);
		date = findViewById(R.id.date);
		linear3 = findViewById(R.id.linear3);
		CorrectPass_textinput = findViewById(R.id.CorrectPass_textinput);
		New_Pass_textinput = findViewById(R.id.New_Pass_textinput);
		textview1 = findViewById(R.id.textview1);
		linear_icon = findViewById(R.id.linear_icon);
		bio_textinput = findViewById(R.id.bio_textinput);
		FullName_editText = findViewById(R.id.FullName_editText);
		SocialMedia_edittext = findViewById(R.id.SocialMedia_edittext);
		date_txt = findViewById(R.id.date_txt);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		Currect_Passwordedittext = findViewById(R.id.Currect_Passwordedittext);
		NewPassword_edittext = findViewById(R.id.NewPassword_edittext);
		circleimageview1 = findViewById(R.id.circleimageview1);
		bio_edittext = findViewById(R.id.bio_edittext);
		search_bar = findViewById(R.id.search_bar);
		iconAccount = findViewById(R.id.iconAccount);
		linear4 = findViewById(R.id.linear4);
		bio = findViewById(R.id.bio);
		linear_Dollar = findViewById(R.id.linear_Dollar);
		interaction_lay = findViewById(R.id.interaction_lay);
		about_lay = findViewById(R.id.about_lay);
		linear11 = findViewById(R.id.linear11);
		recyclerview1 = findViewById(R.id.recyclerview1);
		name = findViewById(R.id.name);
		linear18 = findViewById(R.id.linear18);
		linear19 = findViewById(R.id.linear19);
		linear20 = findViewById(R.id.linear20);
		linear22 = findViewById(R.id.linear22);
		imageview11 = findViewById(R.id.imageview11);
		Dollars = findViewById(R.id.Dollars);
		linear23 = findViewById(R.id.linear23);
		imageview12 = findViewById(R.id.imageview12);
		depositDollars = findViewById(R.id.depositDollars);
		ShareProfile = findViewById(R.id.ShareProfile);
		message = findViewById(R.id.message);
		edit_Bt = findViewById(R.id.edit_Bt);
		linear13 = findViewById(R.id.linear13);
		imageview5 = findViewById(R.id.imageview5);
		textview14 = findViewById(R.id.textview14);
		linear12 = findViewById(R.id.linear12);
		imageview6 = findViewById(R.id.imageview6);
		textview15 = findViewById(R.id.textview15);
		linear16 = findViewById(R.id.linear16);
		imageview2 = findViewById(R.id.imageview2);
		linear14 = findViewById(R.id.linear14);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		linear21 = findViewById(R.id.linear21);
		imageview9 = findViewById(R.id.imageview9);
		textview16 = findViewById(R.id.textview16);
		email_txt = findViewById(R.id.email_txt);
		imageview10 = findViewById(R.id.imageview10);
		textview18 = findViewById(R.id.textview18);
		birt_txt = findViewById(R.id.birt_txt);
		imageview13 = findViewById(R.id.imageview13);
		textview25 = findViewById(R.id.textview25);
		joinedUs_txt = findViewById(R.id.joinedUs_txt);
		linear15 = findViewById(R.id.linear15);
		textview24 = findViewById(R.id.textview24);
		authentication = FirebaseAuth.getInstance();
		imagePicker.setType("image/*");
		imagePicker.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		error = new AlertDialog.Builder(this);
		
		SocialMedia_edittext.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.contains("https://")) {
					SocialMedia_textinput.setErrorEnabled(true);
					SocialMedia_textinput.setError("Don't use https");
					socialCheck = false;
				} else {
					SocialMedia_textinput.setErrorEnabled(false);
					if (_charSeq.contains("http://")) {
						SocialMedia_textinput.setErrorEnabled(true);
						SocialMedia_textinput.setError("Don't use http");
						socialCheck = false;
					} else {
						SocialMedia_textinput.setErrorEnabled(false);
						if (_charSeq.contains(" ")) {
							SocialMedia_textinput.setErrorEnabled(true);
							SocialMedia_textinput.setError("Don't use space");
							socialCheck = false;
						} else {
							SocialMedia_textinput.setErrorEnabled(false);
							if (_charSeq.contains(".")) {
								SocialMedia_textinput.setErrorEnabled(false);
								socialCheck = true;
							} else {
								SocialMedia_textinput.setErrorEnabled(true);
								SocialMedia_textinput.setError("Domain not found.");
								socialCheck = false;
							}
						}
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		date_txt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
				        .setTitleText("Select your birthday")
				        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
				        .build();
				
				datePicker.addOnPositiveButtonClickListener(selection -> {
					    Calendar calendar = Calendar.getInstance();
					    calendar.setTimeInMillis(selection);
					    String selectedDate = calendar.get(Calendar.YEAR) + "-" +
					                          (calendar.get(Calendar.MONTH) + 1) + "-" +
					                          calendar.get(Calendar.DAY_OF_MONTH);
					    
					    date_txt.setText(selectedDate);
				});
				
				datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				backIntent.setClass(getApplicationContext(), DashboardActivity.class);
				ActivityOptions options = ActivityOptions.makeCustomAnimation(AccountActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(backIntent, options.toBundle());
				finish();
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Material3Loader(true, "Saving....");
				if (path.equals("")) {
					_save();
				} else {
					ImageUploader.uploadImage(path, new ImageUploader.UploadCallback() {
							@Override
							public void onUploadComplete(String imageUrl) {
							imgUrl = imageUrl;
							_save();
						}
						
						 @Override
						public void onUploadError(String errorMessage) {
								
								
								
							SketchwareUtil.showMessage(getApplicationContext(), "Something went wrong");
						}
					});
					
				}
			}
		});
		
		circleimageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(imagePicker, REQ_CD_IMAGEPICKER);
			}
		});
		
		edit_Bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				accountBody.setVisibility(View.GONE);
				accountEditBody.setVisibility(View.VISIBLE);
			}
		});
		
		_Task1_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				taskMap.add(_childValue);
				recyclerview1.setAdapter(new Recyclerview1Adapter(taskMap));
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
				taskMap.add(_childValue);
				recyclerview1.setAdapter(new Recyclerview1Adapter(taskMap));
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
				if (replaceUrl.equals("")) {
					if (getIntent().hasExtra("user id")) {
						if (_childKey.equals(getIntent().getStringExtra("user id"))) {
							//      Start part: Part1
							if (_childValue.containsKey("balance")) {
								Dollars.setText("Main balance: ".concat(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("balance").toString()))))));
							}
							if (_childValue.containsKey("deposit balance")) {
								depositDollars.setText("Deposit balance: ".concat(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("deposit balance").toString()))))));
							}
							if (_childValue.containsKey("name")) {
								name.setText(_childValue.get("name").toString());
								FullName_editText.setText(_childValue.get("name").toString());
							}
							if (_childValue.containsKey("social_media")) {
								SocialMedia_edittext.setText(_childValue.get("social_media").toString());
								socialMedia = _childValue.get("social_media").toString();
								message.setOnClickListener(new View.OnClickListener() {
									@Override
									public void onClick(View _view) {
										viewIntent.setAction(Intent.ACTION_VIEW);
										viewIntent.setData(Uri.parse("https://".concat(_childValue.get("social_media").toString())));
										startActivity(viewIntent);
									}
								});
							}
							if (_childValue.containsKey("account link")) {
								ShareProfile.setOnClickListener(new View.OnClickListener() {
									@Override
									public void onClick(View _view) {
										_ShareText(viewIntent, _childValue.get("account link").toString());
									}
								});
							}
							if (_childKey.equals(getIntent().getStringExtra("user id"))) {
								edit_Bt.setVisibility(View.VISIBLE);
							} else {
								edit_Bt.setVisibility(View.GONE);
							}
							if (_childValue.containsKey("birthday")) {
								date_txt.setText(_childValue.get("birthday").toString());
								birt_txt.setText(_childValue.get("birthday").toString());
							}
							if (_childValue.containsKey("account password")) {
								accountSecret = _childValue.get("account password").toString();
							}
							if (_childValue.containsKey("register date")) {
								joinedUs_txt.setText(_childValue.get("register date").toString());
							}
							if (_childValue.containsKey("email")) {
								email_txt.setText(_childValue.get("email").toString());
							}
							if (_childValue.containsKey("bio")) {
								if (_childValue.get("bio").toString().equals("")) {
									bio.setText("No bio available.");
								} else {
									bio.setText(_childValue.get("bio").toString());
									bio_edittext.setText(_childValue.get("bio").toString());
								}
							} else {
								bio.setText("No bio available.");
							}
							if (_childValue.containsKey("logo url")) {
								Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo url").toString())).into(iconAccount);
								Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo url").toString())).into(circleimageview1);
							} else {
								iconAccount.setImageResource(R.drawable.profile_logo);
								circleimageview1.setImageResource(R.drawable.profile_logo);
							}
							//      End part: Part1
						}
					}
				} else {
					//      Start part: Part2
					if (_childKey.equals(replaceUrl)) {
						if (_childValue.containsKey("balance")) {
							Dollars.setText("Main balance: ".concat(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("balance").toString()))))));
						}
						if (_childValue.containsKey("deposit balance")) {
							depositDollars.setText("Deposit balance: ".concat(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("deposit balance").toString()))))));
						}
						if (_childValue.containsKey("name")) {
							name.setText(_childValue.get("name").toString());
							FullName_editText.setText(_childValue.get("name").toString());
						}
						if (_childValue.containsKey("social_media")) {
							SocialMedia_edittext.setText(_childValue.get("social_media").toString());
							socialMedia = _childValue.get("social_media").toString();
							message.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View _view) {
									viewIntent.setAction(Intent.ACTION_VIEW);
									viewIntent.setData(Uri.parse("https://".concat(_childValue.get("social_media").toString())));
									startActivity(viewIntent);
								}
							});
						}
						if (_childValue.containsKey("account link")) {
							ShareProfile.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View _view) {
									_ShareText(viewIntent, _childValue.get("account link").toString());
								}
							});
						}
						if (_childKey.equals(replaceUrl)) {
							edit_Bt.setVisibility(View.VISIBLE);
						} else {
							edit_Bt.setVisibility(View.GONE);
						}
						if (_childValue.containsKey("birthday")) {
							date_txt.setText(_childValue.get("birthday").toString());
							birt_txt.setText(_childValue.get("birthday").toString());
						}
						if (_childValue.containsKey("account password")) {
							accountSecret = _childValue.get("account password").toString();
						}
						if (_childValue.containsKey("register date")) {
							joinedUs_txt.setText(_childValue.get("register date").toString());
						}
						if (_childValue.containsKey("email")) {
							email_txt.setText(_childValue.get("email").toString());
						}
						if (_childValue.containsKey("bio")) {
							if (_childValue.get("bio").toString().equals("")) {
								bio.setText("No bio available.");
							} else {
								bio.setText(_childValue.get("bio").toString());
								bio_edittext.setText(_childValue.get("bio").toString());
							}
						} else {
							bio.setText("No bio available.");
						}
						if (_childValue.containsKey("logo url")) {
							Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo url").toString())).into(iconAccount);
							Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo url").toString())).into(circleimageview1);
						} else {
							iconAccount.setImageResource(R.drawable.profile_logo);
							circleimageview1.setImageResource(R.drawable.profile_logo);
						}
					}
					//      End part: Part2
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (replaceUrl.equals("")) {
					if (getIntent().hasExtra("user id")) {
						if (_childKey.equals(getIntent().getStringExtra("user id"))) {
							//      Start part: Part1
							if (_childValue.containsKey("balance")) {
								Dollars.setText("Main balance: ".concat(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("balance").toString()))))));
							}
							if (_childValue.containsKey("deposit balance")) {
								depositDollars.setText("Deposit balance: ".concat(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("deposit balance").toString()))))));
							}
							if (_childValue.containsKey("name")) {
								name.setText(_childValue.get("name").toString());
								FullName_editText.setText(_childValue.get("name").toString());
							}
							if (_childValue.containsKey("social_media")) {
								SocialMedia_edittext.setText(_childValue.get("social_media").toString());
								socialMedia = _childValue.get("social_media").toString();
								message.setOnClickListener(new View.OnClickListener() {
									@Override
									public void onClick(View _view) {
										viewIntent.setAction(Intent.ACTION_VIEW);
										viewIntent.setData(Uri.parse("https://".concat(_childValue.get("social_media").toString())));
										startActivity(viewIntent);
									}
								});
							}
							if (_childValue.containsKey("account link")) {
								ShareProfile.setOnClickListener(new View.OnClickListener() {
									@Override
									public void onClick(View _view) {
										_ShareText(viewIntent, _childValue.get("account link").toString());
									}
								});
							}
							if (_childKey.equals(getIntent().getStringExtra("user id"))) {
								edit_Bt.setVisibility(View.VISIBLE);
							} else {
								edit_Bt.setVisibility(View.GONE);
							}
							if (_childValue.containsKey("birthday")) {
								date_txt.setText(_childValue.get("birthday").toString());
								birt_txt.setText(_childValue.get("birthday").toString());
							}
							if (_childValue.containsKey("account password")) {
								accountSecret = _childValue.get("account password").toString();
							}
							if (_childValue.containsKey("register date")) {
								joinedUs_txt.setText(_childValue.get("register date").toString());
							}
							if (_childValue.containsKey("email")) {
								email_txt.setText(_childValue.get("email").toString());
							}
							if (_childValue.containsKey("bio")) {
								if (_childValue.get("bio").toString().equals("")) {
									bio.setText("No bio available.");
								} else {
									bio.setText(_childValue.get("bio").toString());
									bio_edittext.setText(_childValue.get("bio").toString());
								}
							} else {
								bio.setText("No bio available.");
							}
							if (_childValue.containsKey("logo url")) {
								Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo url").toString())).into(iconAccount);
								Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo url").toString())).into(circleimageview1);
							} else {
								iconAccount.setImageResource(R.drawable.profile_logo);
								circleimageview1.setImageResource(R.drawable.profile_logo);
							}
							//      End part: Part1
						}
					}
				} else {
					//      Start part: Part2
					if (_childKey.equals(replaceUrl)) {
						if (_childValue.containsKey("balance")) {
							Dollars.setText("Main balance: ".concat(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("balance").toString()))))));
						}
						if (_childValue.containsKey("deposit balance")) {
							depositDollars.setText("Deposit balance: ".concat(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("deposit balance").toString()))))));
						}
						if (_childValue.containsKey("name")) {
							name.setText(_childValue.get("name").toString());
							FullName_editText.setText(_childValue.get("name").toString());
						}
						if (_childValue.containsKey("social_media")) {
							SocialMedia_edittext.setText(_childValue.get("social_media").toString());
							socialMedia = _childValue.get("social_media").toString();
							message.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View _view) {
									viewIntent.setAction(Intent.ACTION_VIEW);
									viewIntent.setData(Uri.parse("https://".concat(_childValue.get("social_media").toString())));
									startActivity(viewIntent);
								}
							});
						}
						if (_childValue.containsKey("account link")) {
							ShareProfile.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View _view) {
									_ShareText(viewIntent, _childValue.get("account link").toString());
								}
							});
						}
						if (_childKey.equals(replaceUrl)) {
							edit_Bt.setVisibility(View.VISIBLE);
						} else {
							edit_Bt.setVisibility(View.GONE);
						}
						if (_childValue.containsKey("birthday")) {
							date_txt.setText(_childValue.get("birthday").toString());
							birt_txt.setText(_childValue.get("birthday").toString());
						}
						if (_childValue.containsKey("account password")) {
							accountSecret = _childValue.get("account password").toString();
						}
						if (_childValue.containsKey("register date")) {
							joinedUs_txt.setText(_childValue.get("register date").toString());
						}
						if (_childValue.containsKey("email")) {
							email_txt.setText(_childValue.get("email").toString());
						}
						if (_childValue.containsKey("bio")) {
							if (_childValue.get("bio").toString().equals("")) {
								bio.setText("No bio available.");
							} else {
								bio.setText(_childValue.get("bio").toString());
								bio_edittext.setText(_childValue.get("bio").toString());
							}
						} else {
							bio.setText("No bio available.");
						}
						if (_childValue.containsKey("logo url")) {
							Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo url").toString())).into(iconAccount);
							Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("logo url").toString())).into(circleimageview1);
						} else {
							iconAccount.setImageResource(R.drawable.profile_logo);
							circleimageview1.setImageResource(R.drawable.profile_logo);
						}
					}
					//      End part: Part2
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
		
		_completedTask1_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(user_id)) {
					complete_list1.add(_childValue.get("task_key").toString());
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
		completedTask1.addChildEventListener(_completedTask1_child_listener);
		
		_completedTask2_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(user_id)) {
					complete_list2.add(_childValue.get("task_key").toString());
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
		completedTask2.addChildEventListener(_completedTask2_child_listener);
		
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
				if (_success) {
					saveMap.put("account password", NewPassword_edittext.getText().toString().trim());
					db_accounts.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(saveMap);
					saveMap.clear();
					_Material3Loader(false, "");
					saveTimer.cancel();
					modePass = false;
				} else {
					_MaterialErrorDialog(_errorMessage);
					modePass = false;
					_Material3Loader(false, "");
					saveTimer.cancel();
					saveMap.clear();
				}
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
		progressDialog = new MaterialProgressDialog(AccountActivity.this);
		//      Start part: On back
		getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
				            @Override
				            public void handleOnBackPressed() {
				backIntent.setClass(getApplicationContext(), DashboardActivity.class);
				ActivityOptions backoptions = ActivityOptions.makeCustomAnimation(AccountActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(backIntent, backoptions.toBundle());
				finish();
						            }
				        });
		//      End part: On back
		//      End part: Navigation Drawer
		//      Start part: User Login check. and others code
		socialCheck = false;
		modePass = false;
		recyclerview1.setLayoutManager(new LinearLayoutManager(this));
		if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
			_Material3Loader(true, "Checking Data.....");
		} else {
			MaterialAlertDialogBuilder error = new MaterialAlertDialogBuilder(AccountActivity.this);
			error.setTitle("Error!");
			error.setMessage("\"Login fast! Access your account instantly with a secure and streamlined authentication process.");
			error.setIcon(R.drawable.icon_error_twotone);
			error.setNegativeButton("Close", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					_dialog.dismiss();
					finishAffinity();
				}
			});
			error.setCancelable(false);
			error.create().show();
		}
		//      End part: User Login check. and others code
		//      Start part: deep link filter
		if (getIntent().hasExtra("user id")) {
			if (!getIntent().getStringExtra("user id").equals("")) {
				user_id = getIntent().getStringExtra("user id");
			}
		}
		try {
			if (!getIntent().getDataString().equals("")) {
				deepLink = getIntent().getDataString();
				user_id = deepLink;
				if (getIntent().getDataString().contains("http://")) {
					replaceUrl = getIntent().getDataString().replace(getString(R.string.httpAccountDeepLinkDomain), "");
					user_id = replaceUrl;
				} else {
					if (getIntent().getDataString().contains("https://")) {
						replaceUrl = getIntent().getDataString().replace(getString(R.string.accountDeepLinkDomain), "");
						user_id = replaceUrl;
					} else {
						if (getIntent().getDataString().contains("/")) {
							replaceUrl = getIntent().getDataString().replace("/", "");
							user_id = replaceUrl;
						}
					}
				}
			}
		} catch (Exception e) {
			 
		}
		//      End part: deep link filter
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_IMAGEPICKER:
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
				circleimageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(path, 1024, 1024));
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			saveTimer.cancel();
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
	
	
	public void _ShareText(final Intent _intent, final String _text) {
		    _intent.setAction(Intent.ACTION_SEND);
		    _intent.setType("text/plain");
		    _intent.putExtra(Intent.EXTRA_TEXT, _text);
		    Intent chooser = Intent.createChooser(_intent, "Share via");
		    
		    if (_intent.resolveActivity(getPackageManager()) != null) {
			        startActivity(chooser);
			    }
	}
	
	
	public void _MaterialErrorDialog(final String _massage) {
		MaterialAlertDialogBuilder error = new MaterialAlertDialogBuilder(AccountActivity.this);
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
	
	
	public void _save() {
		saveMap = new HashMap<>();
		if (!path.equals("")) {
			saveMap.put("logo url", imgUrl);
		}
		if (!name.getText().toString().equals(FullName_editText.getText().toString().trim())) {
			saveMap.put("name", FullName_editText.getText().toString().trim());
		}
		saveMap.put("bio", bio_edittext.getText().toString().trim());
		if (socialCheck) {
			if (!socialMedia.equals(SocialMedia_edittext.getText().toString().trim())) {
				saveMap.put("social_media", SocialMedia_edittext.getText().toString().trim());
			}
		}
		if (!birt_txt.getText().toString().equals(date_txt.getText().toString())) {
			saveMap.put("birthday", date_txt.getText().toString());
		}
		if (!Currect_Passwordedittext.getText().toString().trim().equals("")) {
			if (!NewPassword_edittext.getText().toString().trim().equals("")) {
				if (NewPassword_edittext.getText().toString().trim().length() > 7) {
					if (accountSecret.equals(Currect_Passwordedittext.getText().toString().trim())) {
						FirebaseAuth.getInstance().getCurrentUser().updatePassword(NewPassword_edittext.getText().toString().trim()).addOnCompleteListener(authentication_updatePasswordListener);
						modePass = true;
					} else {
						_MaterialErrorDialog("The old password does not match. Try again");
					}
				} else {
					_MaterialErrorDialog("A minimum 8-digit password must be provided.");
				}
			}
		}
		if (!modePass) {
			db_accounts.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(saveMap);
			saveMap.clear();
			_Material3Loader(false, "");
		}
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				if (_data.get((int)_position).get("user id").toString().equals(user_id)) {
					main_body.setVisibility(View.VISIBLE);
					if (_data.get((int)_position).containsKey("Deletion Timer")) {
						main_body.setVisibility(View.GONE);
					} else {
						//      Start part: logo get
						if (_data.get((int)_position).containsKey("img url")) {
							if (_data.get((int)_position).get("img url").toString().equals("")) {
								logo.setImageResource(R.drawable.icon_account);
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
						if (_data.get((int)_position).containsKey("task1 key")) {
							if (_data.get((int)_position).containsKey("time")) {
								second.setText(_data.get((int)_position).get("time").toString().concat(" Second"));
							}
						} else {
							if (_data.get((int)_position).containsKey("task2 key")) {
								second.setText("Unlimited");
							}
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
						//      Start part: Go next activity
						main_body.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								if (!_data.get((int)_position).get("workers counting").toString().equals(_data.get((int)_position).get("worker max").toString())) {
									if (_data.get((int)_position).containsKey("task1 key")) {
										if (!complete_list1.contains(_data.get((int)_position).get("task1 key").toString())) {
											if (!_data.get((int)_position).get("user id").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
												task1_intent.setClass(getApplicationContext(), TaskViewActivity.class);
												task1_intent.putExtra("key", _data.get((int)_position).get("task1 key").toString());
												ActivityOptions options = ActivityOptions.makeCustomAnimation(AccountActivity.this, R.anim.fade_in, R.anim.fade_out);
												startActivity(task1_intent, options.toBundle());
												finish();
											}
										}
									} else {
										if (_data.get((int)_position).containsKey("task2 key")) {
											if (!complete_list2.contains(_data.get((int)_position).get("task2 key").toString())) {
												if (!_data.get((int)_position).get("user id").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
													task2_intent.setClass(getApplicationContext(), TaskViewActivity.class);
													task2_intent.putExtra("key", _data.get((int)_position).get("task2 key").toString());
													ActivityOptions options = ActivityOptions.makeCustomAnimation(AccountActivity.this, R.anim.fade_in, R.anim.fade_out);
													startActivity(task2_intent, options.toBundle());
													finish();
												}
											}
										}
									}
								}
							}
						});
						//      End part: Go next activity
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