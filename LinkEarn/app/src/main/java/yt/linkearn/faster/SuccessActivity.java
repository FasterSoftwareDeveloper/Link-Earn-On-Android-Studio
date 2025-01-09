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
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.startup.*;
import com.airbnb.lottie.*;
import com.google.android.material.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class SuccessActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private RelativeLayout relativelayout1;
	private LottieAnimationView lottie1;
	private TextView title;
	private TextView description;
	private Button button1;
	
	private TimerTask timer;
	private Intent go = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.success);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		relativelayout1 = findViewById(R.id.relativelayout1);
		lottie1 = findViewById(R.id.lottie1);
		title = findViewById(R.id.title);
		description = findViewById(R.id.description);
		button1 = findViewById(R.id.button1);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				go.setClass(getApplicationContext(), MainActivity.class);
				ActivityOptions options = ActivityOptions.makeCustomAnimation(SuccessActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(go, options.toBundle());
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			            getWindow().setNavigationBarColor(getResources().getColor(R.color.DarkBlue, getTheme()));
			            }
		            
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			            getWindow().setStatusBarColor(getResources().getColor(R.color.DarkBlue, getTheme()));
			            }
		button1.setEnabled(false);
		button1.setText("0/7");
		timer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						button1.setText("1/7");
						timer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										button1.setText("2/7");
										timer = new TimerTask() {
											@Override
											public void run() {
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														button1.setText("3/7");
														timer = new TimerTask() {
															@Override
															public void run() {
																runOnUiThread(new Runnable() {
																	@Override
																	public void run() {
																		button1.setText("4/7");
																		timer = new TimerTask() {
																			@Override
																			public void run() {
																				runOnUiThread(new Runnable() {
																					@Override
																					public void run() {
																						button1.setText("5/7");
																						timer = new TimerTask() {
																							@Override
																							public void run() {
																								runOnUiThread(new Runnable() {
																									@Override
																									public void run() {
																										button1.setText("6/7");
																										timer = new TimerTask() {
																											@Override
																											public void run() {
																												runOnUiThread(new Runnable() {
																													@Override
																													public void run() {
																														button1.setText("7/7");
																														timer = new TimerTask() {
																															@Override
																															public void run() {
																																runOnUiThread(new Runnable() {
																																	@Override
																																	public void run() {
																																		button1.setText("OK! THANKS");
																																		button1.setEnabled(true);
																																		timer.cancel();
																																	}
																																});
																															}
																														};
																														_timer.schedule(timer, (int)(200));
																													}
																												});
																											}
																										};
																										_timer.schedule(timer, (int)(1000));
																									}
																								});
																							}
																						};
																						_timer.schedule(timer, (int)(1000));
																					}
																				});
																			}
																		};
																		_timer.schedule(timer, (int)(1000));
																	}
																});
															}
														};
														_timer.schedule(timer, (int)(1000));
													}
												});
											}
										};
										_timer.schedule(timer, (int)(1000));
									}
								});
							}
						};
						_timer.schedule(timer, (int)(1000));
					}
				});
			}
		};
		_timer.schedule(timer, (int)(1000));
		try {
			if (getIntent().hasExtra("activity2")) {
				description.setText(getIntent().getStringExtra("activity2"));
			} else {
				if (getIntent().hasExtra("activity")) {
					if (getIntent().getStringExtra("activity").equals("added task1")) {
						description.setText("Your \"Website Visit\" task has been successfully paid and added to our database. Published!");
					} else {
						if (getIntent().getStringExtra("activity").equals("added task2")) {
							description.setText("Your \"Short Link\" task has been successfully paid and added to our database. Published!");
						} else {
							if (getIntent().getStringExtra("activity").equals("deleted task")) {
								description.setText("Your task has been deleted from our database!");
							} else {
								
							}
						}
					}
				}
			}
		} catch (Exception e) {
			 
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			timer.cancel();
		} catch (Exception e) {
			 
		}
	}
}