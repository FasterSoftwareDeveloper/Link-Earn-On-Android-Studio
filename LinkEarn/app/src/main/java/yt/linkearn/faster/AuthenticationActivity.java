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
import com.google.android.material.card.*;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.datepicker.MaterialDatePicker;
import java.util.Calendar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.activity.OnBackPressedCallback;
import androidx.core.content.ContextCompat;

public class AuthenticationActivity extends AppCompatActivity {
private ArrayList<HashMap<String, Object>> listmapl = new ArrayList<>();
private double nl = 0;
private double lengthl = 0;
private double portionl = 0;

private MaterialProgressDialog progressDialog;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String YourSpannableString = "";
	private HashMap<String, Object> account_map = new HashMap<>();
	private String device_id = "";
	private boolean email_check = false;
	private boolean social_check = false;
	private boolean singingMode = false;
	private String anonymously_check = "";
	
	private LinearLayout linear11;
	private ScrollView vscroll2;
	private LinearLayout linear1;
	private ImageView imageview1;
	private TextView textview1;
	private TextView textview2;
	private LinearLayout account_body;
	private TextView T1;
	private TextInputLayout name_tablayout;
	private TextInputLayout email_tablayout;
	private TextInputLayout socialmedia_tablayout;
	private TextInputLayout password_textinput;
	private MaterialCardView date;
	private Button button1;
	private TextView account_error;
	private LinearLayout gone;
	private LinearLayout linear23;
	private EditText name_txt;
	private EditText email_txt;
	private EditText socialmedia_txt;
	private EditText password_txt;
	private TextView date_txt;
	private TextView textview4;
	private Button button_authentication;
	private TextView spannableText;
	private LinearLayout linear24;
	private TextView textview5;
	private TextView textview6;
	
	private Intent termsIn = new Intent();
	private Intent privacyIn = new Intent();
	private DatabaseReference db_accounts = _firebase.getReference("db_accounts");
	private ChildEventListener _db_accounts_child_listener;
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
	
	private AlertDialog.Builder error;
	private AlertDialog.Builder success;
	private SharedPreferences save_data;
	private Intent authentication_intent = new Intent();
	private Calendar authentication_calender = Calendar.getInstance();
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
		setContentView(R.layout.authentication);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear11 = findViewById(R.id.linear11);
		vscroll2 = findViewById(R.id.vscroll2);
		linear1 = findViewById(R.id.linear1);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		account_body = findViewById(R.id.account_body);
		T1 = findViewById(R.id.T1);
		name_tablayout = findViewById(R.id.name_tablayout);
		email_tablayout = findViewById(R.id.email_tablayout);
		socialmedia_tablayout = findViewById(R.id.socialmedia_tablayout);
		password_textinput = findViewById(R.id.password_textinput);
		date = findViewById(R.id.date);
		button1 = findViewById(R.id.button1);
		account_error = findViewById(R.id.account_error);
		gone = findViewById(R.id.gone);
		linear23 = findViewById(R.id.linear23);
		name_txt = findViewById(R.id.name_txt);
		email_txt = findViewById(R.id.email_txt);
		socialmedia_txt = findViewById(R.id.socialmedia_txt);
		password_txt = findViewById(R.id.password_txt);
		date_txt = findViewById(R.id.date_txt);
		textview4 = findViewById(R.id.textview4);
		button_authentication = findViewById(R.id.button_authentication);
		spannableText = findViewById(R.id.spannableText);
		linear24 = findViewById(R.id.linear24);
		textview5 = findViewById(R.id.textview5);
		textview6 = findViewById(R.id.textview6);
		fb_authentication = FirebaseAuth.getInstance();
		error = new AlertDialog.Builder(this);
		success = new AlertDialog.Builder(this);
		save_data = getSharedPreferences("save_data", Activity.MODE_PRIVATE);
		anonymously = FirebaseAuth.getInstance();
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (name_tablayout.getVisibility() == View.VISIBLE) {
					if (name_txt.getText().toString().equals("")) {
						name_tablayout.setErrorEnabled(true);
						name_tablayout.setError("Fill it");
					} else {
						name_tablayout.setErrorEnabled(false);
						if (email_txt.getText().toString().equals("")) {
							email_tablayout.setErrorEnabled(true);
							email_tablayout.setError("Fill it");
						} else {
							email_tablayout.setErrorEnabled(false);
							if (socialmedia_txt.getText().toString().equals("")) {
								socialmedia_tablayout.setErrorEnabled(true);
								socialmedia_tablayout.setError("Fill it");
							} else {
								email_tablayout.setErrorEnabled(false);
								if (password_txt.getText().toString().equals("")) {
									password_textinput.setErrorEnabled(true);
									password_textinput.setError("Fill it");
								} else {
									password_textinput.setErrorEnabled(false);
									if (date_txt.getText().toString().equals("Select your birthday")) {
										date_txt.performClick();
									} else {
										if (email_check && social_check) {
											fb_authentication.createUserWithEmailAndPassword(email_txt.getText().toString().trim(), password_txt.getText().toString().trim()).addOnCompleteListener(AuthenticationActivity.this, _fb_authentication_create_user_listener);
											_Material3Loader(true, "Creating...");
										} else {
											
										}
									}
								}
							}
						}
					}
				} else {
					if (email_txt.getText().toString().equals("")) {
						email_tablayout.setErrorEnabled(true);
						email_tablayout.setError("Fill it");
					} else {
						email_tablayout.setErrorEnabled(false);
						if (password_txt.getText().toString().equals("")) {
							password_textinput.setErrorEnabled(true);
							password_textinput.setError("Fill it");
						} else {
							password_textinput.setErrorEnabled(false);
							if (email_check) {
								fb_authentication.signInWithEmailAndPassword(email_txt.getText().toString().trim(), password_txt.getText().toString().trim()).addOnCompleteListener(AuthenticationActivity.this, _fb_authentication_sign_in_listener);
								_Material3Loader(true, "Login...");
							} else {
								
							}
						}
					}
				}
			}
		});
		
		email_txt.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (name_tablayout.getVisibility() == View.VISIBLE) {
					portionl = 0;
					db_accounts.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
									listmapl = new ArrayList<>();
									try {
											GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
											for (DataSnapshot _data : _dataSnapshot.getChildren()) {
													HashMap<String, Object> _map = _data.getValue(_ind);
													listmapl.add(_map);
											}
									}
									catch (Exception _e) {
											_e.printStackTrace();
									}
									if (_charSeq.length() > 0) {
								if (!(listmapl.size() == 0)) {
												nl = listmapl.size() - 1;
												lengthl = listmapl.size();
								} else { email_tablayout.setErrorEnabled(false);
									email_check = true; }
								
											for(int _repeat56 = 0; _repeat56 < (int)(lengthl); _repeat56++) {
													if (listmapl.get((int)nl).containsKey("email") && listmapl.get((int)nl).get("email").toString().toLowerCase().equals(_charSeq.toLowerCase())) {
															email_tablayout.setErrorEnabled(true);
										email_tablayout.setError("Email address is already existed");
													}
													else {
															listmapl.remove((int)(nl));
													}
													nl--;
									if ((portionl == 0) && (listmapl.size() == 0)) {
															email_tablayout.setErrorEnabled(false);
										email_check = true;
													}
											}
									}
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
					});
				} else {
					email_check = true;
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		socialmedia_txt.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.contains("https://")) {
					socialmedia_tablayout.setErrorEnabled(true);
					socialmedia_tablayout.setError("Don't use https");
				} else {
					socialmedia_tablayout.setErrorEnabled(false);
					if (_charSeq.contains("http://")) {
						socialmedia_tablayout.setErrorEnabled(true);
						socialmedia_tablayout.setError("Don't use http");
					} else {
						socialmedia_tablayout.setErrorEnabled(false);
						if (_charSeq.contains(" ")) {
							socialmedia_tablayout.setErrorEnabled(true);
							socialmedia_tablayout.setError("Don't use space");
						} else {
							socialmedia_tablayout.setErrorEnabled(false);
							if (_charSeq.contains(".")) {
								socialmedia_tablayout.setErrorEnabled(false);
								portionl = 0;
								db_accounts.addListenerForSingleValueEvent(new ValueEventListener() {
										@Override
										public void onDataChange(DataSnapshot _dataSnapshot) {
												listmapl = new ArrayList<>();
												try {
														GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
														for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																HashMap<String, Object> _map = _data.getValue(_ind);
																listmapl.add(_map);
														}
												}
												catch (Exception _e) {
														_e.printStackTrace();
												}
												if (_charSeq.length() > 0) {
											if (!(listmapl.size() == 0)) {
															nl = listmapl.size() - 1;
															lengthl = listmapl.size();
											} else { socialmedia_tablayout.setErrorEnabled(false);
												social_check = true; }
											
														for(int _repeat56 = 0; _repeat56 < (int)(lengthl); _repeat56++) {
																if (listmapl.get((int)nl).containsKey("social_media") && listmapl.get((int)nl).get("social_media").toString().toLowerCase().equals(_charSeq.toLowerCase())) {
																		socialmedia_tablayout.setErrorEnabled(true);
													socialmedia_tablayout.setError("Link is already existed");
																}
																else {
																		listmapl.remove((int)(nl));
																}
																nl--;
												if ((portionl == 0) && (listmapl.size() == 0)) {
																		socialmedia_tablayout.setErrorEnabled(false);
													social_check = true;
																}
														}
												}
										}
										@Override
										public void onCancelled(DatabaseError _databaseError) {
										}
								});
							} else {
								socialmedia_tablayout.setErrorEnabled(true);
								socialmedia_tablayout.setError("Domain not found.");
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
		
		button_authentication.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (name_tablayout.getVisibility() == View.VISIBLE) {
					name_tablayout.setVisibility(View.GONE);
					socialmedia_tablayout.setVisibility(View.GONE);
					date.setVisibility(View.GONE);
					T1.setText("Login your existing account!");
					button1.setText("Login now");
					button_authentication.setText("Create account");
				} else {
					name_tablayout.setVisibility(View.VISIBLE);
					socialmedia_tablayout.setVisibility(View.VISIBLE);
					date.setVisibility(View.VISIBLE);
					T1.setText("Create your new account!");
					button1.setText("Create Account");
					button_authentication.setText("Login");
				}
				_TransitionManager(account_body, 500);
			}
		});
		
		textview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				termsIn.setAction(Intent.ACTION_VIEW);
				termsIn.setData(Uri.parse(getString(R.string.termsUrl)));
				startActivity(termsIn);
			}
		});
		
		textview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				privacyIn.setAction(Intent.ACTION_VIEW);
				privacyIn.setData(Uri.parse(getString(R.string.privacyUrl)));
				startActivity(privacyIn);
			}
		});
		
		_db_accounts_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				try {
					portionl = 0;
					db_accounts.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
									listmapl = new ArrayList<>();
									try {
											GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
											for (DataSnapshot _data : _dataSnapshot.getChildren()) {
													HashMap<String, Object> _map = _data.getValue(_ind);
													listmapl.add(_map);
											}
									}
									catch (Exception _e) {
											_e.printStackTrace();
									}
									if (android.provider.Settings.Secure.getString(getApplicationContext().getContentResolver(),android.provider.Settings.Secure.ANDROID_ID).length() > 0) {
								if (!(listmapl.size() == 0)) {
												nl = listmapl.size() - 1;
												lengthl = listmapl.size();
								} else { account_error.setVisibility(View.GONE);
									gone.setVisibility(View.VISIBLE); }
								
											for(int _repeat56 = 0; _repeat56 < (int)(lengthl); _repeat56++) {
													if (listmapl.get((int)nl).containsKey("device id") && listmapl.get((int)nl).get("device id").toString().toLowerCase().equals(android.provider.Settings.Secure.getString(getApplicationContext().getContentResolver(),android.provider.Settings.Secure.ANDROID_ID).toLowerCase())) {
															name_tablayout.setVisibility(View.GONE);
										socialmedia_tablayout.setVisibility(View.GONE);
										date.setVisibility(View.GONE);
										T1.setText("Login your existing account!");
										button1.setText("Login now");
										button_authentication.setText("Create account");
										_TransitionManager(account_body, 500);
										account_error.setVisibility(View.VISIBLE);
										gone.setVisibility(View.GONE);
													}
													else {
															listmapl.remove((int)(nl));
													}
													nl--;
									if ((portionl == 0) && (listmapl.size() == 0)) {
															account_error.setVisibility(View.GONE);
										gone.setVisibility(View.VISIBLE);
													}
											}
									}
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
					});
				} catch (Exception e) {
					 
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				try {
					portionl = 0;
					db_accounts.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
									listmapl = new ArrayList<>();
									try {
											GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
											for (DataSnapshot _data : _dataSnapshot.getChildren()) {
													HashMap<String, Object> _map = _data.getValue(_ind);
													listmapl.add(_map);
											}
									}
									catch (Exception _e) {
											_e.printStackTrace();
									}
									if (android.provider.Settings.Secure.getString(getApplicationContext().getContentResolver(),android.provider.Settings.Secure.ANDROID_ID).length() > 0) {
								if (!(listmapl.size() == 0)) {
												nl = listmapl.size() - 1;
												lengthl = listmapl.size();
								} else { account_error.setVisibility(View.GONE);
									gone.setVisibility(View.VISIBLE); }
								
											for(int _repeat56 = 0; _repeat56 < (int)(lengthl); _repeat56++) {
													if (listmapl.get((int)nl).containsKey("device id") && listmapl.get((int)nl).get("device id").toString().toLowerCase().equals(android.provider.Settings.Secure.getString(getApplicationContext().getContentResolver(),android.provider.Settings.Secure.ANDROID_ID).toLowerCase())) {
															name_tablayout.setVisibility(View.GONE);
										socialmedia_tablayout.setVisibility(View.GONE);
										date.setVisibility(View.GONE);
										T1.setText("Login your existing account!");
										button1.setText("Login now");
										button_authentication.setText("Create account");
										_TransitionManager(account_body, 500);
										account_error.setVisibility(View.VISIBLE);
										gone.setVisibility(View.GONE);
													}
													else {
															listmapl.remove((int)(nl));
													}
													nl--;
									if ((portionl == 0) && (listmapl.size() == 0)) {
															account_error.setVisibility(View.GONE);
										gone.setVisibility(View.VISIBLE);
													}
											}
									}
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
					});
				} catch (Exception e) {
					 
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
				if (_success) {
					//      Start part: create user database
					authentication_calender = Calendar.getInstance();
					account_map = new HashMap<>();
					account_map.put("name", name_txt.getText().toString().trim());
					account_map.put("email", email_txt.getText().toString().trim());
					account_map.put("account password", password_txt.getText().toString().trim());
					account_map.put("balance", "0.000");
					account_map.put("deposit balance", "0.000");
					account_map.put("account link", getString(R.string.accountDeepLinkDomain).concat(FirebaseAuth.getInstance().getCurrentUser().getUid()));
					account_map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					account_map.put("social_media", socialmedia_txt.getText().toString().trim());
					account_map.put("birthday", date_txt.getText().toString().trim());
					account_map.put("device id", android.provider.Settings.Secure.getString(getApplicationContext().getContentResolver(),android.provider.Settings.Secure.ANDROID_ID));
					account_map.put("risk type", "none");
					account_map.put("notification token", save_data.getString("notification token user", ""));
					account_map.put("register date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(authentication_calender.getTime()));
					db_accounts.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(account_map);
					account_map.clear();
					//      End part: create user database
					//      Start part: Login function
					name_tablayout.setVisibility(View.GONE);
					socialmedia_tablayout.setVisibility(View.GONE);
					date.setVisibility(View.GONE);
					T1.setText("Login your existing account!");
					button1.setText("Login now");
					button_authentication.setText("Create account");
					_TransitionManager(account_body, 500);
					FirebaseAuth.getInstance().signOut();
					anonymously.signInWithEmailAndPassword("anonymously@gmail.com", "anonymously1234").addOnCompleteListener(AuthenticationActivity.this, _anonymously_sign_in_listener);
					//      End part: Login function
					//      Start part: Dialog
					MaterialAlertDialogBuilder success = new MaterialAlertDialogBuilder(AuthenticationActivity.this);
					success.setTitle("Email send success!");
					success.setMessage("The verification link has been sent to your email, check the email inbox or spam box and verify it quickly!");
					success.setIcon(R.drawable.icon_done_outline_round);
					success.setNegativeButton("Close", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							_dialog.dismiss();
						}
					});
					success.setCancelable(false);
					success.create().show();
					//      End part: Dialog
				} else {
					//      Start part: login anonymously
					anonymously.signInWithEmailAndPassword("anonymously@gmail.com", "anonymously1234").addOnCompleteListener(AuthenticationActivity.this, _anonymously_sign_in_listener);
					//      End part: login anonymously
					MaterialAlertDialogBuilder error = new MaterialAlertDialogBuilder(AuthenticationActivity.this);
					error.setTitle("Account creating error!");
					error.setMessage(_errorMessage);
					error.setIcon(R.drawable.icon_error_twotone);
					error.setCancelable(false);
					error.setNegativeButton("Close", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							_dialog.dismiss();
						}
					});
					error.create().show();
				}
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
				if (_success) {
					FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(fb_authentication_emailVerificationSentListener);
				} else {
					//      Start part: login anonymously
					anonymously.signInWithEmailAndPassword("anonymously@gmail.com", "anonymously1234").addOnCompleteListener(AuthenticationActivity.this, _anonymously_sign_in_listener);
					//      End part: login anonymously
					MaterialAlertDialogBuilder error = new MaterialAlertDialogBuilder(AuthenticationActivity.this);
					error.setTitle("Account creating error!");
					error.setMessage(_errorMessage);
					error.setIcon(R.drawable.icon_error_twotone);
					error.setCancelable(false);
					error.setNegativeButton("Close", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							_dialog.dismiss();
						}
					});
					error.create().show();
				}
			}
		};
		
		_fb_authentication_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					if (FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {
						authentication_intent.setClass(getApplicationContext(), DashboardActivity.class);
						ActivityOptions options = ActivityOptions.makeCustomAnimation(AuthenticationActivity.this, R.anim.fade_in, R.anim.fade_out);
						startActivity(authentication_intent, options.toBundle());
						save_data.edit().putString("anonymously check", "false").commit();
						_Material3Loader(false, "");
						finish();
					} else {
						MaterialAlertDialogBuilder error = new MaterialAlertDialogBuilder(AuthenticationActivity.this);
						error.setTitle("Email not verified!");
						error.setMessage("The verification link was sent to your email earlier. From there verify and fill in the login information and login!");
						error.setIcon(R.drawable.icon_error_twotone);
						error.setNegativeButton("Close", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								_dialog.dismiss();
							}
						});
						error.create().show();
						FirebaseAuth.getInstance().signOut();
						//      Start part: login anonymously
						anonymously.signInWithEmailAndPassword("anonymously@gmail.com", "anonymously1234").addOnCompleteListener(AuthenticationActivity.this, _anonymously_sign_in_listener);
						//      End part: login anonymously
					}
				} else {
					//      Start part: login anonymously
					anonymously.signInWithEmailAndPassword("anonymously@gmail.com", "anonymously1234").addOnCompleteListener(AuthenticationActivity.this, _anonymously_sign_in_listener);
					//      End part: login anonymously
					MaterialAlertDialogBuilder error = new MaterialAlertDialogBuilder(AuthenticationActivity.this);
					error.setTitle("Account login error!");
					error.setMessage(_errorMessage);
					error.setIcon(R.drawable.icon_error_twotone);
					error.setCancelable(false);
					error.setNegativeButton("Close", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							_dialog.dismiss();
						}
					});
					error.create().show();
				}
			}
		};
		
		_fb_authentication_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
		
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
					try {
						_Material3Loader(false, "");
						save_data.edit().putString("anonymously check", "true").commit();
					} catch (Exception e) {
						 
					}
					try {
						db_accounts.addChildEventListener(_db_accounts_child_listener);
					} catch (Exception e) {
						 
					}
				} else {
					_Material3Loader(false, "");
					save_data.edit().putString("anonymously check", "false").commit();
					MaterialAlertDialogBuilder error = new MaterialAlertDialogBuilder(AuthenticationActivity.this);
					error.setTitle("Getting data error!");
					error.setMessage(_errorMessage);
					error.setIcon(R.drawable.icon_error_twotone);
					error.setNegativeButton("Try again", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							_dialog.dismiss();
							authentication_intent.setClass(getApplicationContext(), MainActivity.class);
							ActivityOptions options = ActivityOptions.makeCustomAnimation(AuthenticationActivity.this, R.anim.fade_in, R.anim.fade_out);
							startActivity(authentication_intent, options.toBundle());
							finish();
						}
					});
					error.setCancelable(false);
					error.create().show();
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
		//      Start part: others
		progressDialog = new MaterialProgressDialog(AuthenticationActivity.this);
		
		
		// Ensure that SketchUi and d are properly initialized before use
		int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
		
		// Initialize the GradientDrawable
		android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
		int clrs[] = {ContextCompat.getColor(getApplicationContext(), R.color.RichBlack), 
			              ContextCompat.getColor(getApplicationContext(), R.color.RichBlack)};
		
		SketchUi = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.LEFT_RIGHT, clrs);
		SketchUi.setCornerRadii(new float[]{0, 0, 0, 0, 50, 50, 0, 0});
		
		// Apply stroke with ContextCompat
		SketchUi.setStroke(d * 0, ContextCompat.getColor(getApplicationContext(), R.color.Text_Color));
		
		// Set elevation conditionally for Android Lollipop and above
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
			    linear1.setElevation(d * 3);
		}
		
		android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(
		    new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ContextCompat.getColor(getApplicationContext(), R.color.bottom_sheet)}),
		    SketchUi, null
		);
		
		linear1.setBackground(SketchUiRD);
		linear1.setClickable(false);
		
		device_id = android.provider.Settings.Secure.getString(getApplicationContext().getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
		email_check = false;
		social_check = false;
		//      Start part: On back
		getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
				            @Override
				            public void handleOnBackPressed() {
				if (save_data.getString("anonymously check", "").equals("true")) {
					save_data.edit().putString("anonymously check", "false").commit();
					try {
						FirebaseAuth.getInstance().signOut();
					} catch (Exception e) {
						 
					}
				}
				finishAffinity();
						            }
				        });
		//      End part: On back
		//      End part: others
		//      Start part: login anonymously
		anonymously.signInWithEmailAndPassword("anonymously@gmail.com", "anonymously1234").addOnCompleteListener(AuthenticationActivity.this, _anonymously_sign_in_listener);
		_Material3Loader(true, "Getting Data...");
		//      End part: login anonymously
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (save_data.getString("anonymously check", "").equals("true")) {
			save_data.edit().putString("anonymously check", "false").commit();
			try {
				FirebaseAuth.getInstance().signOut();
			} catch (Exception e) {
				 
			}
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
	
}