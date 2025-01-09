package yt.linkearn.faster;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.android.material.chip.*;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.divider.MaterialDivider;
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
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class SearchviewActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double searchLengthNum = 0;
	private double LengthNum = 0;
	private String filterType = "";
	private double searchLengthNum2 = 0;
	private double LengthNum2 = 0;
	private double searchLengthNum3 = 0;
	private double LengthNum3 = 0;
	
	private ArrayList<HashMap<String, Object>> db_accountsList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> filteredList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> task1Listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> task2Listview = new ArrayList<>();
	private ArrayList<String> complete_list1 = new ArrayList<>();
	private ArrayList<String> complete_list2 = new ArrayList<>();
	
	private RelativeLayout relativelayout1;
	private LinearLayout searchlayout;
	private MaterialDivider divider;
	private ChipGroup filterGroup;
	private RecyclerView task1listview;
	private RecyclerView task2listview;
	private RecyclerView peoplelistview;
	private ImageView imageview1;
	private EditText Search_edittext;
	private ImageView imageview2;
	private Chip linear2;
	private Chip linear3;
	private Chip linear4;
	
	private Intent backIntent = new Intent();
	private DatabaseReference db_accounts = _firebase.getReference("db_accounts");
	private ChildEventListener _db_accounts_child_listener;
	private DatabaseReference task1 = _firebase.getReference("Task/task1");
	private ChildEventListener _task1_child_listener;
	private DatabaseReference completedTask1 = _firebase.getReference("Task/completedTask1");
	private ChildEventListener _completedTask1_child_listener;
	private DatabaseReference completedTask2 = _firebase.getReference("Task/completedTask2");
	private ChildEventListener _completedTask2_child_listener;
	private Intent task1_intent = new Intent();
	private Intent task2_intent = new Intent();
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
	
	private DatabaseReference task2 = _firebase.getReference("Task/task2");
	private ChildEventListener _task2_child_listener;
	private Intent intentViewProfile = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.searchview);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		relativelayout1 = findViewById(R.id.relativelayout1);
		searchlayout = findViewById(R.id.searchlayout);
		divider = findViewById(R.id.divider);
		filterGroup = findViewById(R.id.filterGroup);
		task1listview = findViewById(R.id.task1listview);
		task2listview = findViewById(R.id.task2listview);
		peoplelistview = findViewById(R.id.peoplelistview);
		imageview1 = findViewById(R.id.imageview1);
		Search_edittext = findViewById(R.id.Search_edittext);
		imageview2 = findViewById(R.id.imageview2);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		auth = FirebaseAuth.getInstance();
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				backIntent.setClass(getApplicationContext(), DashboardActivity.class);
				_StartTransitionActivity(backIntent, searchlayout, "backIntent");
				finish();
			}
		});
		
		Search_edittext.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.equals("")) {
					task1listview.setVisibility(View.GONE);
					task2listview.setVisibility(View.GONE);
					peoplelistview.setVisibility(View.GONE);
				} else {
					if (filterType.equals("web")) {
						task1.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								task1Listmap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										task1Listmap.add(_map);
									}
								} catch (Exception _e) {
									_e.printStackTrace();
								}
								task1listview.setVisibility(View.VISIBLE);
								task2listview.setVisibility(View.GONE);
								peoplelistview.setVisibility(View.GONE);
								if (_charSeq.trim().length() > 0) {
									searchLengthNum = task1Listmap.size() - 1;
									LengthNum = task1Listmap.size();
									for(int _repeat609 = 0; _repeat609 < (int)(LengthNum); _repeat609++) {
										if (task1Listmap.get((int)searchLengthNum).get("title").toString().toLowerCase().contains(_charSeq.trim().toLowerCase())) {
											
										} else {
											task1Listmap.remove((int)(searchLengthNum));
										}
										searchLengthNum--;
									}
								}
								task1listview.setAdapter(new Task1listviewAdapter(task1Listmap));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
					} else {
						if (filterType.equals("short")) {
							task2.addListenerForSingleValueEvent(new ValueEventListener() {
								@Override
								public void onDataChange(DataSnapshot _dataSnapshot) {
									task2Listview = new ArrayList<>();
									try {
										GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
										for (DataSnapshot _data : _dataSnapshot.getChildren()) {
											HashMap<String, Object> _map = _data.getValue(_ind);
											task2Listview.add(_map);
										}
									} catch (Exception _e) {
										_e.printStackTrace();
									}
									task1listview.setVisibility(View.GONE);
									task2listview.setVisibility(View.VISIBLE);
									peoplelistview.setVisibility(View.GONE);
									if (_charSeq.trim().length() > 0) {
										searchLengthNum2 = task2Listview.size() - 1;
										LengthNum2 = task2Listview.size();
										for(int _repeat411 = 0; _repeat411 < (int)(LengthNum2); _repeat411++) {
											if (task2Listview.get((int)searchLengthNum2).get("title").toString().toLowerCase().contains(_charSeq.trim().toLowerCase())) {
												
											} else {
												task2Listview.remove((int)(searchLengthNum2));
											}
											searchLengthNum2--;
										}
									}
									task2listview.setAdapter(new Task2listviewAdapter(task2Listview));
								}
								@Override
								public void onCancelled(DatabaseError _databaseError) {
								}
							});
						} else {
							if (filterType.equals("people")) {
								db_accounts.addListenerForSingleValueEvent(new ValueEventListener() {
									@Override
									public void onDataChange(DataSnapshot _dataSnapshot) {
										db_accountsList = new ArrayList<>();
										try {
											GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
											for (DataSnapshot _data : _dataSnapshot.getChildren()) {
												HashMap<String, Object> _map = _data.getValue(_ind);
												db_accountsList.add(_map);
											}
										} catch (Exception _e) {
											_e.printStackTrace();
										}
										task1listview.setVisibility(View.GONE);
										task2listview.setVisibility(View.GONE);
										peoplelistview.setVisibility(View.VISIBLE);
										if (_charSeq.trim().length() > 0) {
											searchLengthNum3 = db_accountsList.size() - 1;
											LengthNum3 = db_accountsList.size();
											for(int _repeat675 = 0; _repeat675 < (int)(LengthNum3); _repeat675++) {
												if (db_accountsList.get((int)searchLengthNum3).get("name").toString().toLowerCase().contains(_charSeq.trim().toLowerCase())) {
													
												} else {
													db_accountsList.remove((int)(searchLengthNum3));
												}
												searchLengthNum3--;
											}
										}
										peoplelistview.setAdapter(new PeoplelistviewAdapter(db_accountsList));
									}
									@Override
									public void onCancelled(DatabaseError _databaseError) {
									}
								});
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
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Search_edittext.setText("");
			}
		});
		
		linear2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				task1listview.setVisibility(View.VISIBLE);
				task2listview.setVisibility(View.GONE);
				peoplelistview.setVisibility(View.GONE);
				Search_edittext.setHint("Search website visit tasks");
				filterType = "web";
			}
		});
		
		linear3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				task1listview.setVisibility(View.GONE);
				task2listview.setVisibility(View.VISIBLE);
				peoplelistview.setVisibility(View.GONE);
				Search_edittext.setHint("Search short link tasks");
				filterType = "short";
			}
		});
		
		linear4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				task1listview.setVisibility(View.GONE);
				task2listview.setVisibility(View.GONE);
				peoplelistview.setVisibility(View.VISIBLE);
				Search_edittext.setHint("Search profiles");
				filterType = "people";
			}
		});
		
		_db_accounts_child_listener = new ChildEventListener() {
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
		db_accounts.addChildEventListener(_db_accounts_child_listener);
		
		_task1_child_listener = new ChildEventListener() {
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
		task1.addChildEventListener(_task1_child_listener);
		
		_completedTask1_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
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
				if (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
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
		
		_task2_child_listener = new ChildEventListener() {
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
		task2.addChildEventListener(_task2_child_listener);
		
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
		//      Start part: others
		_GetTransition(searchlayout, "searchBar");
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			            getWindow().setStatusBarColor(getResources().getColor(R.color.background, getTheme()));
			            }
		task1listview.setLayoutManager(new LinearLayoutManager(this));
		task2listview.setLayoutManager(new LinearLayoutManager(this));
		peoplelistview.setLayoutManager(new LinearLayoutManager(this));
		//      End part: others
		//      Start part: intent filter
		if (getIntent().hasExtra("activity")) {
			if (getIntent().getStringExtra("activity").equals("task")) {
				Search_edittext.setHint("search website visit task");
				filterType = "web";
			} else {
				if (getIntent().getStringExtra("activity").equals("people")) {
					Search_edittext.setHint("search profiles");
					filterType = "people";
				}
			}
		} else {
			
		}
		//      End part: intent filter
	}
	
	public void _GetTransition(final View _view, final String _name) {
		_view.setTransitionName(_name); 
	}
	
	
	public void _StartTransitionActivity(final Intent _intent, final View _view, final String _name) {
		_view.setTransitionName(_name); 
		android.app.ActivityOptions optionsCompat = android.app.ActivityOptions.makeSceneTransitionAnimation(SearchviewActivity.this, _view, _name); 
		startActivity(_intent, optionsCompat.toBundle());
	}
	
	
	public void _TransitionManager(final View _view, final double _duration) {
		RelativeLayout viewgroup =(RelativeLayout) _view;
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	public class Task1listviewAdapter extends RecyclerView.Adapter<Task1listviewAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Task1listviewAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
							if (!_data.get((int)_position).get("user id").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
								if (complete_list1.contains(_data.get((int)_position).get("task1 key").toString())) {
									
								} else {
									if (!_data.get((int)_position).get("workers counting").toString().equals(_data.get((int)_position).get("worker max").toString())) {
										task1_intent.setClass(getApplicationContext(), TaskViewActivity.class);
										task1_intent.putExtra("key", _data.get((int)_position).get("task1 key").toString());
										ActivityOptions options = ActivityOptions.makeCustomAnimation(SearchviewActivity.this, R.anim.fade_in, R.anim.fade_out);
										startActivity(task1_intent, options.toBundle());
										finish();
									}
								}
							}
						}
					});
					//      End part: Go next activity
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
	
	public class Task2listviewAdapter extends RecyclerView.Adapter<Task2listviewAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Task2listviewAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
						person.setText("Completed!");
						progressbar2.setMax((int)Double.parseDouble(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("worker max").toString())))));
						progressbar2.setProgress((int)Double.parseDouble(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("workers counting").toString())))));
						person_txt.setVisibility(View.GONE);
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
							if (!_data.get((int)_position).get("user id").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
								if (complete_list2.contains(_data.get((int)_position).get("task2 key").toString())) {
									
								} else {
									if (!_data.get((int)_position).get("workers counting").toString().equals(_data.get((int)_position).get("worker max").toString())) {
										task2_intent.setClass(getApplicationContext(), TaskViewActivity.class);
										task2_intent.putExtra("key", _data.get((int)_position).get("task2 key").toString());
										ActivityOptions options = ActivityOptions.makeCustomAnimation(SearchviewActivity.this, R.anim.fade_in, R.anim.fade_out);
										startActivity(task2_intent, options.toBundle());
										finish();
									}
								}
							}
						}
					});
					//      End part: Go next activity
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
	
	public class PeoplelistviewAdapter extends RecyclerView.Adapter<PeoplelistviewAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public PeoplelistviewAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.leaderboard_custom, null);
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
			final Button button_view_profile = _view.findViewById(R.id.button_view_profile);
			
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
			button_view_profile.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					intentViewProfile.setClass(getApplicationContext(), AccountActivity.class);
					intentViewProfile.putExtra("user id", _data.get((int)_position).get("uid").toString());
					ActivityOptions options = ActivityOptions.makeCustomAnimation(SearchviewActivity.this, R.anim.fade_in, R.anim.fade_out);
					startActivity(intentViewProfile, options.toBundle());
					finish();
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