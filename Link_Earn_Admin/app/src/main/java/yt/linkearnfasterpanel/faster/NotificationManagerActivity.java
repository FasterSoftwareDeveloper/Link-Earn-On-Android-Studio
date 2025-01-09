package yt.linkearnfasterpanel.faster;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.card.*;
import com.google.android.material.textfield.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.*;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import yt.linkearnfasterpanel.faster.FCMNotificationSender;


public class NotificationManagerActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String fontName = "";
	private String typeace = "";
	private HashMap<String, Object> notification_map = new HashMap<>();
	private String accessToken = "";
	private String accessTokenError = "";
	private String ProjectID = "";
	private String ContextActivity = "";
	private String onSuccess = "";
	private String onError = "";
	private String Token = "";
	
	private ArrayList<HashMap<String, Object>> terminal_map = new ArrayList<>();
	private ArrayList<String> terminal_string = new ArrayList<>();
	
	private CollapsingToolbarLayout collapsingtoolbar1;
	private NestedScrollView vscroll1;
	private LinearLayout linear1;
	private MaterialCardView important_note;
	private TextView textview1;
	private TextInputLayout title_tablayout;
	private TextInputLayout body_tablayout;
	private LinearLayout linear2;
	private SwitchCompat U_switch;
	private TextInputLayout url_tablayout;
	private Button button3;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear65;
	private LinearLayout linear64;
	private LinearLayout linear63;
	private ImageView imageview22;
	private TextView textview17;
	private TextView textview18;
	private EditText title_txt;
	private EditText body_txt;
	private TextInputLayout extravalue_tablayout;
	private TextInputLayout topic_tablayout;
	private EditText extravalue_txt;
	private EditText topic_txt;
	private EditText url_txt;
	private Button button1;
	private Button button2;
	private TextView textview2;
	private RecyclerView recyclerview1;
	
	
	private OnCompleteListener cloudMassaging_onCompleteListener;
	private Intent igo = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.notification_manager);
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
		important_note = findViewById(R.id.important_note);
		textview1 = findViewById(R.id.textview1);
		title_tablayout = findViewById(R.id.title_tablayout);
		body_tablayout = findViewById(R.id.body_tablayout);
		linear2 = findViewById(R.id.linear2);
		U_switch = findViewById(R.id.U_switch);
		url_tablayout = findViewById(R.id.url_tablayout);
		button3 = findViewById(R.id.button3);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear65 = findViewById(R.id.linear65);
		linear64 = findViewById(R.id.linear64);
		linear63 = findViewById(R.id.linear63);
		imageview22 = findViewById(R.id.imageview22);
		textview17 = findViewById(R.id.textview17);
		textview18 = findViewById(R.id.textview18);
		title_txt = findViewById(R.id.title_txt);
		body_txt = findViewById(R.id.body_txt);
		extravalue_tablayout = findViewById(R.id.extravalue_tablayout);
		topic_tablayout = findViewById(R.id.topic_tablayout);
		extravalue_txt = findViewById(R.id.extravalue_txt);
		topic_txt = findViewById(R.id.topic_txt);
		url_txt = findViewById(R.id.url_txt);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		textview2 = findViewById(R.id.textview2);
		recyclerview1 = findViewById(R.id.recyclerview1);
		
		U_switch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (U_switch.isChecked()) {
					url_tablayout.setEnabled(true);
				} else {
					url_tablayout.setEnabled(false);
				}
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try {
					    try (InputStream originalInputStream = getAssets().open("service-account.json")) {
						        byte[] inputStreamBytes = SketchwareUtil.copyFromInputStream(originalInputStream).getBytes();
						
						        try (InputStream inputStream = new ByteArrayInputStream(inputStreamBytes)) {
							            String jsonString = SketchwareUtil.copyFromInputStream(inputStream);
							            HashMap<String, Object> map = new Gson().fromJson(
							                jsonString,
							                new TypeToken<HashMap<String, Object>>() {}.getType()
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
										
										                        runOnUiThread(() -> {
											                            // Add project ID to terminal map
											                            HashMap<String, Object> projectItem = new HashMap<>();
											                            projectItem.put("terminal", "fp add project id: ".concat(ProjectID));
											                            terminal_map.add(projectItem);
											
											                            // Add access token to terminal map
											                            HashMap<String, Object> tokenItem = new HashMap<>();
											                            tokenItem.put("terminal", "fp add access token: ".concat(accessToken));
											                            terminal_map.add(tokenItem);
											
											                            // Update UI
											                            button1.setVisibility(View.GONE);
											                            button2.setEnabled(true);
											                            button3.setVisibility(View.GONE);
											                            recyclerview1.setAdapter(new Recyclerview1Adapter(terminal_map));
											                        });
										                    } else {
										                        accessToken = "Error getting accessToken";
										                    }
									                }
								
								                @Override
								                public void onError(String error) {
									                    accessTokenError = error;
									
									                    runOnUiThread(() -> {
										                        // Update UI on error
										                        button1.setVisibility(View.VISIBLE);
										                        button2.setEnabled(false);
										                        // Log error to terminal map
										                        HashMap<String, Object> errorItem = new HashMap<>();
										                        errorItem.put("terminal", "fp 404 ".concat(accessTokenError));
										                        terminal_map.add(errorItem);
										
										                        recyclerview1.setAdapter(new Recyclerview1Adapter(terminal_map));
										                    });
									                }
								            });
							        }
						    } catch (IOException e) {
						        accessTokenError = "Error reading service account file: " + e.getMessage();
						
						        runOnUiThread(() -> {
							            // Update UI on file read error
							            button1.setVisibility(View.VISIBLE);
							            button2.setEnabled(false);
							
							            // Log error to terminal map
							            HashMap<String, Object> errorItem = new HashMap<>();
							            errorItem.put("terminal", "fp 404 ".concat(accessTokenError));
							            terminal_map.add(errorItem);
							
							            recyclerview1.setAdapter(new Recyclerview1Adapter(terminal_map));
							        });
						    }
				} catch (Exception e) {
					    runOnUiThread(() -> {
						        // Handle unexpected errors
						        HashMap<String, Object> errorItem = new HashMap<>();
						        errorItem.put("terminal", "fp 404 ".concat(e.getMessage()));
						        terminal_map.add(errorItem);
						
						        // Update RecyclerView
						        recyclerview1.setAdapter(new Recyclerview1Adapter(terminal_map));
						
						        // Ensure buttons are disabled
						        button1.setVisibility(View.VISIBLE);
						        button2.setEnabled(false);
						    });
				}
				
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				igo.setClass(getApplicationContext(), TotalActivity.class);
				igo.putExtra("activity", "notification sender selector");
				ActivityOptions options = ActivityOptions.makeCustomAnimation(NotificationManagerActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(igo, options.toBundle());
				finish();
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (title_txt.getText().toString().equals("")) {
					title_tablayout.setErrorEnabled(true);
					title_tablayout.setError("Fill It");
				} else {
					title_tablayout.setErrorEnabled(false);
					if (body_txt.getText().toString().equals("")) {
						body_tablayout.setError("Fill It");
						body_tablayout.setErrorEnabled(true);
					} else {
						body_tablayout.setErrorEnabled(false);
						if (topic_txt.getText().toString().equals("")) {
							topic_tablayout.setErrorEnabled(true);
							topic_tablayout.setError("Fill It");
						} else {
							topic_tablayout.setErrorEnabled(false);
							notification_map = new HashMap<>();
							notification_map.put("title", title_txt.getText().toString());
							notification_map.put("body", body_txt.getText().toString());
							notification_map.put("image", url_txt.getText().toString());
							notification_map.put("extraData", extravalue_txt.getText().toString());
							if (getIntent().hasExtra("activity")) { 
								    if (getIntent().getStringExtra("activity").equals("selected")) { 
									        // Start part: Send With token
									        String token = getIntent().getStringExtra("token");
									        String topic = "";
									        String projectId = ProjectID;
									        String tokenaccess = accessToken; 
									        FCMNotificationSender.sendNotification(notification_map, topic, token, projectId, tokenaccess, new FCMNotificationSender.OnResponse() {
										            @Override
										            public void onSuccess(String response) {
											                onSuccess = response;
											
											                runOnUiThread(() -> {
												                    HashMap<String, Object> item;
												
												                    item = new HashMap<>();
												                    item.put("terminal", "fp notification title:- " + title_txt.getText().toString());
												                    terminal_map.add(item);
												
												                    item = new HashMap<>();
												                    item.put("terminal", "fp notification body:- " + body_txt.getText().toString());
												                    terminal_map.add(item);
												
												                    if (!url_txt.getText().toString().isEmpty()) {
													                        item = new HashMap<>();
													                        item.put("terminal", "fp notification image url:- " + url_txt.getText().toString());
													                        terminal_map.add(item);
													                    }
												
												                    if (!extravalue_txt.getText().toString().isEmpty()) {
													                        item = new HashMap<>();
													                        item.put("terminal", "fp notification extra value:- " + extravalue_txt.getText().toString());
													                        terminal_map.add(item);
													                    }
												
												                    item = new HashMap<>();
												                    item.put("terminal", "fp notification token:- " + topic_txt.getText().toString());
												                    terminal_map.add(item);
												
												                    item = new HashMap<>();
												                    item.put("terminal", "fp notification sending success response:- " + onSuccess);
												                    terminal_map.add(item);
												
												                    recyclerview1.setAdapter(new Recyclerview1Adapter(terminal_map));
												                });
											            }
										
										            @Override
										            public void onError(String error) {
											                onError = error;
											
											                runOnUiThread(() -> {
												                    HashMap<String, Object> item = new HashMap<>();
												                    item.put("terminal", "fp 404 " + onError);
												                    terminal_map.add(item);
												
												                    recyclerview1.setAdapter(new Recyclerview1Adapter(terminal_map));
												                });
											            }
										        });
									        // End part: Send With token 
									    } 
							} else { 
								    // Start part: Send with topic
								    String topic = topic_txt.getText().toString();
								    String token = "";
								    String projectId = ProjectID;
								    String tokenaccess = accessToken; 
								    FCMNotificationSender.sendNotification(notification_map, topic, token, projectId, tokenaccess, new FCMNotificationSender.OnResponse() {
									        @Override
									        public void onSuccess(String response) {
										            onSuccess = response;
										
										            runOnUiThread(() -> {
											                HashMap<String, Object> item;
											
											                item = new HashMap<>();
											                item.put("terminal", "fp notification title:- " + title_txt.getText().toString());
											                terminal_map.add(item);
											
											                item = new HashMap<>();
											                item.put("terminal", "fp notification body:- " + body_txt.getText().toString());
											                terminal_map.add(item);
											
											                if (!url_txt.getText().toString().isEmpty()) {
												                    item = new HashMap<>();
												                    item.put("terminal", "fp notification image url:- " + url_txt.getText().toString());
												                    terminal_map.add(item);
												                }
											
											                if (!extravalue_txt.getText().toString().isEmpty()) {
												                    item = new HashMap<>();
												                    item.put("terminal", "fp notification extra value:- " + extravalue_txt.getText().toString());
												                    terminal_map.add(item);
												                }
											
											                item = new HashMap<>();
											                item.put("terminal", "fp notification topic:- " + topic_txt.getText().toString());
											                terminal_map.add(item);
											
											                item = new HashMap<>();
											                item.put("terminal", "fp notification sending success response:- " + onSuccess);
											                terminal_map.add(item);
											
											                recyclerview1.setAdapter(new Recyclerview1Adapter(terminal_map));
											            });
										        }
									
									        @Override
									        public void onError(String error) {
										            onError = error;
										
										            runOnUiThread(() -> {
											                HashMap<String, Object> item = new HashMap<>();
											                item.put("terminal", "fp 404 " + onError);
											                terminal_map.add(item);
											
											                recyclerview1.setAdapter(new Recyclerview1Adapter(terminal_map));
											            });
										        }
									    });
								    // End part: Send with topic 
							}
							
						}
					}
				}
			}
		});
		//addd
	}
	
	private void initializeLogic() {
		//      Start part: Backend
		// Change the navigation bar color for Android 6.0 and above
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			            getWindow().setNavigationBarColor(getResources().getColor(R.color.DarkBlue, getTheme()));
			            }
		            
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			            getWindow().setStatusBarColor(getResources().getColor(R.color.DarkBlue, getTheme()));
			            }
		//      End part: Backend
		//      Start part: codes
		_changeActivityFont("regular_txt");
		url_tablayout.setEnabled(false);
		U_switch.setChecked(false);
		button2.setEnabled(false);
		recyclerview1.setLayoutManager(new LinearLayoutManager(this));
		//      End part: codes
		//      Start part: Notification
		//      Start part: subscribe the topic
		if (SketchwareUtil.isConnected(getApplicationContext())) {
			    FirebaseMessaging.getInstance().subscribeToTopic("admin")
			        .addOnCompleteListener(new OnCompleteListener<Void>() {
				            @Override
				            public void onComplete(@NonNull Task<Void> task) {
					                if (task.isSuccessful()) {
						{
							HashMap<String, Object> _item = new HashMap<>();
							_item.put("terminal", "fp add default notification topic: admin");
							terminal_map.add(_item);
						}
						recyclerview1.setAdapter(new Recyclerview1Adapter(terminal_map));
						                    Log.d("TAG", "Subscribed to topic: user");
						                } else {
						{
							HashMap<String, Object> _item = new HashMap<>();
							_item.put("terminal", "fp add default notification topic: news");
							terminal_map.add(_item);
						}
						recyclerview1.setAdapter(new Recyclerview1Adapter(terminal_map));
						                    Log.e("TAG", "Failed to subscribe to topic", task.getException());
						                }
					            }
				        });
		} else {
			    // Handle no internet connection
			    Log.e("TAG", "No internet connection, cannot subscribe to topic");
		}
		//      End part: subscribe the topic
		//      Start part: get token
		FirebaseMessaging.getInstance().getToken()
				.addOnCompleteListener(new OnCompleteListener<String>() {
					@Override
					public void onComplete(@NonNull Task<String> task) {
						if (task.isSuccessful()) {
							// Get the token
							String token = task.getResult();

							// Add the token to your terminal map
							HashMap<String, Object> _item = new HashMap<>();
							_item.put("terminal", "fp generate a notification token:- ".concat(token));
							terminal_map.add(_item);

							// Update the RecyclerView adapter
							recyclerview1.setAdapter(new Recyclerview1Adapter(terminal_map));

							Log.d("TAG", "Notification token: " + token);
						} else {
							// Handle failure
							HashMap<String, Object> _item = new HashMap<>();
							_item.put("terminal", "fp 404 Error Getting Token");
							terminal_map.add(_item);

							// Update the RecyclerView adapter
							recyclerview1.setAdapter(new Recyclerview1Adapter(terminal_map));

							Log.e("TAG", "Failed to get notification token", task.getException());
						}
					}
				});
		//      End part: get token
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			    // SDK > TIRAMISU
			if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == android.content.pm.PackageManager.PERMISSION_DENIED) {
				//Denied
				requestPermissions(new String[] {android.Manifest.permission.POST_NOTIFICATIONS}, 1111);
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("terminal", "fp permission:- Notification Permission Denied");
					terminal_map.add(_item);
				}
				recyclerview1.setAdapter(new Recyclerview1Adapter(terminal_map));
			}else {
				//Granted
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("terminal", "fp permission:- Notification Permission Granted");
					terminal_map.add(_item);
				}
				recyclerview1.setAdapter(new Recyclerview1Adapter(terminal_map));
			}
		}else {
			//SDK < TIRAMISU
			 
		}
		if (getIntent().hasExtra("activity")) {
			if (getIntent().getStringExtra("activity").equals("selected")) {
				button1.setVisibility(View.GONE);
				button2.setText("Send a Specify User");
				topic_txt.setText(getIntent().getStringExtra("token"));
				topic_txt.setHint("Notification Token");
				topic_tablayout.setEnabled(false);
			} else {
				
			}
		}
		//      End part: Notification
	}
	
	public void _changeActivityFont(final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".ttf"));
		overrideFonts(this,getWindow().getDecorView()); 
	} 
	private void overrideFonts(final android.content.Context context, final View v) {
		
		try {
			Typeface 
			typeace = Typeface.createFromAsset(getAssets(), fontName);;
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0;
				i < vg.getChildCount();
				i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			} else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				} else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					} else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}
		catch(Exception e)
		
		{
			SketchwareUtil.showMessage(getApplicationContext(), "Error Loading Font");
		};
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.terminal, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final TextView txt = _view.findViewById(R.id.txt);
			final TextView show = _view.findViewById(R.id.show);
			
			show.setText(terminal_map.get((int)_position).get("terminal").toString());
			linear5.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", terminal_map.get((int)_position).get("terminal").toString()));
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
