package yt.linkearn.faster;

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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.google.android.material.progressindicator.CircularProgressIndicator;
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
import android.os.Handler;
import android.os.Looper;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebViewClient;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.activity.OnBackPressedCallback;
import java.net.URISyntaxException;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

public class TaskWebviewActivity extends AppCompatActivity {
private Handler handler = new Handler(Looper.getMainLooper());
private int scrollY = 0;
private int contentHeight = 0;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double time = 0;
	private boolean alreadyCollect = false;
	private boolean timerIsRunning = false;
	private double position = 0;
	private String task1key = "";
	private String task2key = "";
	private String destinationLink = "";
	private boolean scrollDown = false;
	private String num_FastStr = "";
	private String num_LastStr = "";
	private String answerStr = "";
	private String PuzzleSelectNumPosition = "";
	private HashMap<String, Object> DollarsAddMap = new HashMap<>();
	private String MapTaskKey = "";
	private HashMap<String, Object> completedMap = new HashMap<>();
	private HashMap<String, Object> historyMap = new HashMap<>();
	private String HistoryKey = "";
	private HashMap<String, Object> visitCount = new HashMap<>();
	private double workersCounting = 0;
	private double mainBalance = 0;
	
	private ArrayList<String> blacklist = new ArrayList<>();
	
	private RelativeLayout progress_body;
	private RelativeLayout webview_body;
	private TextView textview1;
	private CircularProgressIndicator progressIndicator;
	private MaterialCardView top;
	private WebView advancedWebView;
	private LinearLayout linear_timer;
	private LinearLayout linear_puzzle;
	private TextView wait_txt;
	private TextView time_txt;
	private TextView s_txt;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private TextView textview2;
	private TextView num_Fast;
	private TextView textview3;
	private TextView num_Last;
	private TextView textview5;
	private Button select_1;
	private Button select_2;
	private Button select_3;
	private Button select_4;
	
	private DatabaseReference completedTask1 = _firebase.getReference("Task/completedTask1");
	private ChildEventListener _completedTask1_child_listener;
	private DatabaseReference completedTask2 = _firebase.getReference("Task/completedTask2");
	private ChildEventListener _completedTask2_child_listener;
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
	
	private DatabaseReference history = _firebase.getReference("history");
	private ChildEventListener _history_child_listener;
	private AlertDialog.Builder error;
	private Intent back_intent = new Intent();
	private RequestNetwork ReqNet;
	private RequestNetwork.RequestListener _ReqNet_request_listener;
	private AlertDialog.Builder back;
	private TimerTask MainTimer;
	private Calendar calendar = Calendar.getInstance();
	private Intent SuccessIntent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.task_webview);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		progress_body = findViewById(R.id.progress_body);
		webview_body = findViewById(R.id.webview_body);
		textview1 = findViewById(R.id.textview1);
		progressIndicator = findViewById(R.id.progressIndicator);
		top = findViewById(R.id.top);
		advancedWebView = findViewById(R.id.advancedWebView);
		advancedWebView.getSettings().setJavaScriptEnabled(true);
		advancedWebView.getSettings().setSupportZoom(true);
		linear_timer = findViewById(R.id.linear_timer);
		linear_puzzle = findViewById(R.id.linear_puzzle);
		wait_txt = findViewById(R.id.wait_txt);
		time_txt = findViewById(R.id.time_txt);
		s_txt = findViewById(R.id.s_txt);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		textview2 = findViewById(R.id.textview2);
		num_Fast = findViewById(R.id.num_Fast);
		textview3 = findViewById(R.id.textview3);
		num_Last = findViewById(R.id.num_Last);
		textview5 = findViewById(R.id.textview5);
		select_1 = findViewById(R.id.select_1);
		select_2 = findViewById(R.id.select_2);
		select_3 = findViewById(R.id.select_3);
		select_4 = findViewById(R.id.select_4);
		authentication = FirebaseAuth.getInstance();
		error = new AlertDialog.Builder(this);
		ReqNet = new RequestNetwork(this);
		back = new AlertDialog.Builder(this);
		
		advancedWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		select_1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (select_1.getText().toString().equals(answerStr)) {
					if (position == 1) {
						_collectDollars(position, getIntent().getStringExtra("task1 key"));
					} else {
						_collectDollars(position, getIntent().getStringExtra("task2 key"));
					}
				} else {
					_NewPuzzle();
				}
			}
		});
		
		select_2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (select_2.getText().toString().equals(answerStr)) {
					if (position == 1) {
						_collectDollars(position, getIntent().getStringExtra("task1 key"));
					} else {
						_collectDollars(position, getIntent().getStringExtra("task2 key"));
					}
				} else {
					_NewPuzzle();
				}
			}
		});
		
		select_3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (select_3.getText().toString().equals(answerStr)) {
					if (position == 1) {
						_collectDollars(position, getIntent().getStringExtra("task1 key"));
					} else {
						_collectDollars(position, getIntent().getStringExtra("task2 key"));
					}
				} else {
					_NewPuzzle();
				}
			}
		});
		
		select_4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (select_4.getText().toString().equals(answerStr)) {
					if (position == 1) {
						_collectDollars(position, getIntent().getStringExtra("task1 key"));
					} else {
						_collectDollars(position, getIntent().getStringExtra("task2 key"));
					}
				} else {
					_NewPuzzle();
				}
			}
		});
		
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
		
		_Task1_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (position == 1) {
					if (_childKey.equals(task1key)) {
						workersCounting = Double.parseDouble(_childValue.containsKey("workers counting") && _childValue.get("workers counting") != null ? _childValue.get("workers counting").toString() : "");
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
				if (position == 2) {
					if (_childKey.equals(task2key)) {
						workersCounting = Double.parseDouble(_childValue.containsKey("workers counting") && _childValue.get("workers counting") != null ? _childValue.get("workers counting").toString() : "");
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
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("balance")) {
						mainBalance = Double.parseDouble(String.valueOf(Double.parseDouble(new DecimalFormat("0.000").format(Double.parseDouble(_childValue.get("balance").toString())))));
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				db_accounts.addChildEventListener(_db_accounts_child_listener);
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
		
		_ReqNet_request_listener = new RequestNetwork.RequestListener() {
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
		//      Start part: backed
		//      End part: backed
		//      Start part: Important
		ReqNet.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "B", _ReqNet_request_listener);
		try {
			time = 0;
			alreadyCollect = false;
			timerIsRunning = false;
			position = Double.parseDouble(getIntent().getStringExtra("position"));
			task1key = getIntent().getStringExtra("task1 key");
			task2key = getIntent().getStringExtra("task2 key");
			if (position == 1) {
				time = Double.parseDouble(getIntent().getStringExtra("time"));
			} else {
				destinationLink = getIntent().getStringExtra("destination link");
				time_txt.setVisibility(View.GONE);
				s_txt.setVisibility(View.GONE);
				wait_txt.setText(getIntent().getStringExtra("description"));
				wait_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSurface));
			}
		} catch (Exception e) {
			 
		}
		advancedWebView.clearCache(true);
		advancedWebView.clearHistory();
		//      End part: Important
		//      Start part: Do not add anything below this Block
		//      Start part: blacklist sites
		blacklist.add("malicious.com");
		blacklist.add("phishing-site.com");
		blacklist.add("dangerous.net");
		blacklist.add("example-malware.com");
		blacklist.add("badwebsite.net");
		blacklist.add("unsecure-site.org");
		blacklist.add("fake-login-page.com");
		blacklist.add("scam-site.co");
		blacklist.add("phishingscam.net");
		blacklist.add("malwaredomain.com");
		blacklist.add("ransomware-site.org");
		blacklist.add("trojansite.com");
		blacklist.add("spyware-link.net");
		blacklist.add("adware-site.info");
		blacklist.add("dangerous-link.com");
		blacklist.add("fraudulent-site.biz");
		blacklist.add("fake-bank-login.net");
		blacklist.add("identity-theft.org");
		blacklist.add("virus-download.com");
		blacklist.add("fake-prize-link.net");
		blacklist.add("suspicious-site.co");
		blacklist.add("untrusted-source.org");
		blacklist.add("fake-update.com");
		blacklist.add("clickbait-site.net");
		blacklist.add("credential-theft.biz");
		blacklist.add("fraudulent-payment.com");
		blacklist.add("hacked-website.org");
		blacklist.add("fake-promotion.com");
		blacklist.add("unsafe-content.net");
		blacklist.add("unverified-link.org");
		blacklist.add("malicious-download.com");
		blacklist.add("harmful-page.net");
		//      End part: blacklist sites
		//      Start part: OnBackPressedDispatcher
		getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
				            @Override
				            public void handleOnBackPressed() {
				if (advancedWebView.canGoBack()) {
					advancedWebView.goBack();
				} else {
					MaterialAlertDialogBuilder back = new MaterialAlertDialogBuilder(TaskWebviewActivity.this);
					back.setTitle("Go back?");
					back.setMessage("Don't you want to do this? Want to get out?");
					back.setIcon(R.drawable.icon_error_twotone);
					back.setPositiveButton("Go back", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							_dialog.dismiss();
							back_intent.setClass(getApplicationContext(), MainActivity.class);
							ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskWebviewActivity.this, R.anim.fade_in, R.anim.fade_out);
							startActivity(back_intent, options.toBundle());
							finish();
						}
					});
					back.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							_dialog.dismiss();
						}
					});
					back.create().show();
				}
						            }
				        });
		//      End part: OnBackPressedDispatcher
		//      Start part: Advance Webview Setup
		        WebSettings webSettings = advancedWebView.getSettings();
		        webSettings.setJavaScriptEnabled(true);
		        webSettings.setDomStorageEnabled(true);
		        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
		        webSettings.setUseWideViewPort(true);
		        webSettings.setLoadWithOverviewMode(true);
		        webSettings.setMediaPlaybackRequiresUserGesture(true);
		        webSettings.setSupportMultipleWindows(true);
		        webSettings.setUserAgentString("Mozilla/5.0 (Linux; Android 10; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Mobile Safari/537.36");
		    
		        
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			            webSettings.setSafeBrowsingEnabled(true);
			        }
		        
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
			        }
		        
		        CookieManager.getInstance().setAcceptThirdPartyCookies(advancedWebView, true);
		
		        advancedWebView.setOnLongClickListener(v -> true);
		        advancedWebView.setHapticFeedbackEnabled(false);
		        advancedWebView.setWebViewClient(new SecureWebViewClient());
		        advancedWebView.setWebChromeClient(new SecureWebChromeClient());
		advancedWebView.loadUrl(getIntent().getStringExtra("task link"));
		    }
	    private class SecureWebViewClient extends WebViewClient {
		        @Override
		        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
			            String url = request.getUrl().toString();
			
			            for (String blocked : blacklist) {
				                if (url.contains(blocked)) {
					try {
						MainTimer.cancel();
					} catch (Exception e) {
						 
					}
					_MaterialErrorDialog("Harmful site detected!");
					return true;
					                }
				            }
			            
			            if (url.startsWith("intent://")) {
				                try {
					                    Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
					                    if (intent.resolveActivity(getPackageManager()) != null) {
						                        startActivity(intent);
						                    } else {
						                        Toast.makeText(TaskWebviewActivity.this, "No app found to handle this intent", Toast.LENGTH_SHORT).show();
						                    }
					                } catch (URISyntaxException | ActivityNotFoundException e) {
					                    Toast.makeText(TaskWebviewActivity.this, "Error handling intent: " + e.getMessage(), Toast.LENGTH_SHORT).show();
					                }
				                return true; // Prevent loading in WebView
				            } else if (url.startsWith("mailto:")) {
				                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
				                startActivity(intent);
				                return true;
				            } else if (url.startsWith("tel:")) {
				                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
				                startActivity(intent);
				                return true;
				            } else if (url.startsWith("sms:")) {
				                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
				                startActivity(intent);
				                return true;
				            }
			            
			            view.loadUrl(url);
			            return true;
			        }
		        
		    @Override
		        public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
			            super.onPageStarted(view, url, favicon);
			//      Start part: Page Started
			 
			//      End part: Page Started
			        }
		
		        @Override
		        public void onPageFinished(WebView view, String url) {
			            super.onPageFinished(view, url);
			            advancedWebView.evaluateJavascript(
			            "document.body.scrollHeight", 
			            value -> {
				                try {
					                    contentHeight = Integer.parseInt(value.replace("\"", "")); // Remove quotes and parse as int
					                } catch (NumberFormatException e) {
					                    contentHeight = 0; // Fallback in case of error
					                }
				            }
			        );
			//      Start part: Page Loaded
			progress_body.setVisibility(View.GONE);
			webview_body.setVisibility(View.VISIBLE);
			_loaded();
			_NewPuzzle();
			if (position == 1) {
				_AutoScrollWebview();
			}
			//      End part: Page Loaded
		}
		    }
	
	    private class SecureWebChromeClient extends WebChromeClient {
		        @Override
		        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, android.os.Message resultMsg) {
			            Toast.makeText(TaskWebviewActivity.this, "Pop-up opened", Toast.LENGTH_SHORT).show();
			            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
			        }
		//      End part: Advance Webview Setup
		//      End part: Do not add anything below this Block
	}
	
	
	@Override
	public void onPause() {
		super.onPause();
		try {
			if (position == 1) {
				if (advancedWebView != null) {
					            advancedWebView.destroy();
					        }
				progress_body.setVisibility(View.GONE);
				webview_body.setVisibility(View.VISIBLE);
				time_txt.setVisibility(View.GONE);
				s_txt.setVisibility(View.GONE);
				linear_timer.setVisibility(View.VISIBLE);
				linear_puzzle.setVisibility(View.GONE);
				advancedWebView.setVisibility(View.GONE);
				wait_txt.setText("You are cheating so you cannot complete this task. And no money will be paid for this work. So Go Back.");
				wait_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorError));
				try {
					MainTimer.cancel();
				} catch (Exception e) {
					 
				}
				time = 0;
				timerIsRunning = true;
				alreadyCollect = true;
			}
		} catch (Exception e) {
			 
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (advancedWebView != null) {
			    
			        advancedWebView.clearCache(true);
			        advancedWebView.clearHistory();
			        
			            advancedWebView.destroy();
			        }
	}
	public void _MaterialErrorDialog(final String _massage) {
		MaterialAlertDialogBuilder error = new MaterialAlertDialogBuilder(TaskWebviewActivity.this);
		error.setTitle("Error!");
		error.setMessage(_massage);
		error.setIcon(R.drawable.icon_error_twotone);
		error.setPositiveButton("Go back", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				_dialog.dismiss();
				back_intent.setClass(getApplicationContext(), MainActivity.class);
				ActivityOptions options = ActivityOptions.makeCustomAnimation(TaskWebviewActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(back_intent, options.toBundle());
				finish();
			}
		});
		error.setCancelable(false);
		error.create().show();
	}
	
	
	public void _loaded() {
		if (position == 1) {
			if (!timerIsRunning) {
				linear_timer.setVisibility(View.VISIBLE);
				time_txt.setText(String.valueOf((long)(time)));
				MainTimer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								if (timerIsRunning) {
									if ((time == 0) || (time < 0)) {
										MainTimer.cancel();
										time = 0;
										timerIsRunning = false;
										time_txt.setText(String.valueOf((long)(time)));
										linear_timer.setVisibility(View.GONE);
										linear_puzzle.setVisibility(View.VISIBLE);
									} else {
										time_txt.setText(String.valueOf((long)(time)));
										time--;
									}
								} else {
									timerIsRunning = true;
									if ((time == 0) || (time < 0)) {
										MainTimer.cancel();
										time = 0;
										timerIsRunning = false;
										time_txt.setText(String.valueOf((long)(time)));
										linear_timer.setVisibility(View.GONE);
										linear_puzzle.setVisibility(View.VISIBLE);
									} else {
										time_txt.setText(String.valueOf((long)(time)));
										time--;
									}
								}
							}
						});
					}
				};
				_timer.scheduleAtFixedRate(MainTimer, (int)(1000), (int)(1000));
			}
		} else {
			if (advancedWebView.getUrl().contains(destinationLink)) {
				linear_timer.setVisibility(View.GONE);
				linear_puzzle.setVisibility(View.VISIBLE);
			}
		}
	}
	
	
	public void _AutoScrollWebview() {
		
		handler.postDelayed(new Runnable() {
			    @Override
			    public void run() {
				        if (scrollDown) {
					            scrollY += 15; // Adjust scroll speed
					        } else {
					            scrollY -= 18;
					        }
				
				        // Scroll the WebView
				        advancedWebView.scrollTo(0, scrollY);
				
				        // Calculate visible height of WebView
				        int webViewHeight = advancedWebView.getHeight();
				
				        // Adjust scroll direction at boundaries
				        if (scrollY >= contentHeight - webViewHeight) {
					            scrollDown = false;
					        } else if (scrollY <= 0) {
					            scrollDown = true;
					        }
				
				        // Post the runnable again for smooth scrolling
				        handler.postDelayed(this, 10); // Adjust delay as needed
				    }
		}, 10);
		
	}
	
	
	public void _NewPuzzle() {
		num_Fast.setText(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(20)))));
		num_Last.setText(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))));
		num_FastStr = num_Fast.getText().toString();
		num_LastStr = num_Last.getText().toString();
		answerStr = String.valueOf((long)(Double.parseDouble(num_FastStr) + Double.parseDouble(num_LastStr)));
		PuzzleSelectNumPosition = String.valueOf((long)(SketchwareUtil.getRandom((int)(1), (int)(4))));
		if (PuzzleSelectNumPosition.equals("1")) {
			select_1.setText(answerStr);
		} else {
			select_1.setText(String.valueOf((long)(SketchwareUtil.getRandom((int)(2), (int)(20)))));
			if (PuzzleSelectNumPosition.equals("2")) {
				select_2.setText(answerStr);
			} else {
				select_2.setText(String.valueOf((long)(SketchwareUtil.getRandom((int)(3), (int)(9)))));
				if (PuzzleSelectNumPosition.equals("3")) {
					select_3.setText(answerStr);
				} else {
					select_3.setText(String.valueOf((long)(SketchwareUtil.getRandom((int)(5), (int)(20)))));
					if (PuzzleSelectNumPosition.equals("4")) {
						select_4.setText(answerStr);
					} else {
						select_4.setText(String.valueOf((long)(SketchwareUtil.getRandom((int)(1), (int)(13)))));
					}
				}
			}
		}
	}
	
	
	public void _collectDollars(final double _position, final String _taskKey) {
		try {
			MainTimer.cancel();
		} catch (Exception e) {
			 
		}
		if (!timerIsRunning) {
			if (!alreadyCollect) {
				calendar = Calendar.getInstance();
				//      Start part: Balance Add
				alreadyCollect = true;
				DollarsAddMap = new HashMap<>();
				DollarsAddMap.put("balance", new DecimalFormat("0.000").format(Double.parseDouble(getIntent().getStringExtra("dollars")) + mainBalance));
				db_accounts.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(DollarsAddMap);
				DollarsAddMap.clear();
				//      End part: Balance Add
				//      Start part: Completed
				if (_position == 1) {
					MapTaskKey = completedTask1.push().getKey();
				} else {
					MapTaskKey = completedTask2.push().getKey();
				}
				completedMap = new HashMap<>();
				completedMap.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				completedMap.put("task_key", _taskKey);
				completedMap.put("key", MapTaskKey);
				if (_position == 1) {
					completedTask1.child(MapTaskKey).updateChildren(completedMap);
				} else {
					completedTask2.child(MapTaskKey).updateChildren(completedMap);
				}
				completedMap.clear();
				//      End part: Completed
				//      Start part: History
				HistoryKey = history.push().getKey();
				historyMap = new HashMap<>();
				if (_position == 1) {
					historyMap.put("title", "Website Visit ".concat("#".concat(_taskKey)));
				} else {
					historyMap.put("title", "Short Link".concat("#".concat(_taskKey)));
				}
				historyMap.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(calendar.getTime()));
				historyMap.put("balance", "+".concat(new DecimalFormat("0.000").format(Double.parseDouble(getIntent().getStringExtra("dollars")))));
				historyMap.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				historyMap.put("key", HistoryKey);
				history.child(HistoryKey).updateChildren(historyMap);
				historyMap.clear();
				//      End part: History
				//      Start part: workers counting
				visitCount = new HashMap<>();
				visitCount.put("workers counting", String.valueOf((long)(workersCounting + 1)));
				if (_position == 1) {
					Task1.child(_taskKey).updateChildren(visitCount);
				} else {
					Task2.child(_taskKey).updateChildren(visitCount);
				}
				visitCount.clear();
				//      End part: workers counting
				//      Start part: Done
				SuccessIntent.setClass(getApplicationContext(), SuccessActivity.class);
				SuccessIntent.putExtra("activity2", "Your Money has been sent for Your Account! ".concat(" Date : ".concat(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(calendar.getTime()).concat(" Dollars: ".concat(new DecimalFormat("0.000").format(Double.parseDouble(getIntent().getStringExtra("dollars"))))))));
				ActivityOptions Noptions = ActivityOptions.makeCustomAnimation(TaskWebviewActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(SuccessIntent, Noptions.toBundle());
				finish();
				//      End part: Done
			}
		}
	}
	
}