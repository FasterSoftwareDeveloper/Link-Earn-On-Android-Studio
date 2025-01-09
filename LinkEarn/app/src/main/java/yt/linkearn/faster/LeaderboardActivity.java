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
import java.util.regex.*;
import org.json.*;
import androidx.activity.OnBackPressedCallback;

public class LeaderboardActivity extends AppCompatActivity {

private MaterialProgressDialog progressDialog;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private ArrayList<HashMap<String, Object>> leaderboardListView = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear21;
	private TextView top_leader;
	private LinearLayout linear61;
	private LinearLayout layout5;
	private LinearLayout linear59;
	private LinearLayout linear62;
	private RecyclerView recyclerview1;
	private LinearLayout linear_l2;
	private LinearLayout linear_l1;
	private LinearLayout linear_l3;
	private TextView rank2;
	private CircleImageView profile2;
	private TextView name2;
	private TextView balance2;
	private TextView rank1;
	private CircleImageView profile1;
	private TextView name1;
	private TextView balance1;
	private ImageView crown;
	private TextView rank3;
	private CircleImageView profile3;
	private TextView name3;
	private TextView balance3;
	private TextView textview1;
	
	private DatabaseReference db_accounts = _firebase.getReference("db_accounts");
	private ChildEventListener _db_accounts_child_listener;
	private Intent intentViewProfile = new Intent();
	private Intent backIntent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.leaderboard);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = findViewById(R.id.vscroll1);
		linear21 = findViewById(R.id.linear21);
		top_leader = findViewById(R.id.top_leader);
		linear61 = findViewById(R.id.linear61);
		layout5 = findViewById(R.id.layout5);
		linear59 = findViewById(R.id.linear59);
		linear62 = findViewById(R.id.linear62);
		recyclerview1 = findViewById(R.id.recyclerview1);
		linear_l2 = findViewById(R.id.linear_l2);
		linear_l1 = findViewById(R.id.linear_l1);
		linear_l3 = findViewById(R.id.linear_l3);
		rank2 = findViewById(R.id.rank2);
		profile2 = findViewById(R.id.profile2);
		name2 = findViewById(R.id.name2);
		balance2 = findViewById(R.id.balance2);
		rank1 = findViewById(R.id.rank1);
		profile1 = findViewById(R.id.profile1);
		name1 = findViewById(R.id.name1);
		balance1 = findViewById(R.id.balance1);
		crown = findViewById(R.id.crown);
		rank3 = findViewById(R.id.rank3);
		profile3 = findViewById(R.id.profile3);
		name3 = findViewById(R.id.name3);
		balance3 = findViewById(R.id.balance3);
		textview1 = findViewById(R.id.textview1);
		
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
	}
	
	private void initializeLogic() {
		//      Start part: backend
		progressDialog = new MaterialProgressDialog(LeaderboardActivity.this);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			            getWindow().setStatusBarColor(getResources().getColor(R.color.background, getTheme()));
			            }
		recyclerview1.setLayoutManager(new LinearLayoutManager(this));
		//      Start part: On back
		getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
				            @Override
				            public void handleOnBackPressed() {
				backIntent.setClass(getApplicationContext(), DashboardActivity.class);
				ActivityOptions options = ActivityOptions.makeCustomAnimation(LeaderboardActivity.this, R.anim.fade_in, R.anim.fade_out);
				startActivity(backIntent, options.toBundle());
				finish();
						            }
				        });
		//      End part: On back
		_leaderboard();
		//      End part: backend
	}
	
	public void _Material3Loader(final boolean _variable, final String _title) {
		if (_variable) {
			progressDialog.show(_title);
		} else {
			
			progressDialog.dismiss();
		}
	}
	
	
	public void _leaderboard() {
		leaderboardListView.clear();
		db_accounts.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				leaderboardListView = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						leaderboardListView.add(_map);
					}
				} catch (Exception _e) {
					_e.printStackTrace();
				}
				recyclerview1.setAdapter(new Recyclerview1Adapter(leaderboardListView));
				try {
					//      Start part: Part 1
					name1.setText(leaderboardListView.get((int)0).get("name").toString());
					balance1.setText(new DecimalFormat("0.000").format(Double.parseDouble(leaderboardListView.get((int)0).get("balance").toString())).concat("$"));
					if (leaderboardListView.get((int)0).containsKey("logo url")) {
						Glide.with(getApplicationContext()).load(Uri.parse(leaderboardListView.get((int)0).get("logo url").toString())).into(profile1);
					} else {
						profile1.setImageResource(R.drawable.profile_logo);
					}
					//      End part: Part 1
					//      Start part: Part 2
					name2.setText(leaderboardListView.get((int)1).get("name").toString());
					balance2.setText(new DecimalFormat("0.000").format(Double.parseDouble(leaderboardListView.get((int)1).get("balance").toString())).concat("$"));
					if (leaderboardListView.get((int)1).containsKey("logo url")) {
						Glide.with(getApplicationContext()).load(Uri.parse(leaderboardListView.get((int)1).get("logo url").toString())).into(profile2);
					} else {
						profile2.setImageResource(R.drawable.profile_logo);
					}
					//      End part: Part 2
					//      Start part: Part 3
					name3.setText(leaderboardListView.get((int)2).get("name").toString());
					balance3.setText(new DecimalFormat("0.000").format(Double.parseDouble(leaderboardListView.get((int)2).get("balance").toString())).concat("$"));
					if (leaderboardListView.get((int)2).containsKey("logo url")) {
						Glide.with(getApplicationContext()).load(Uri.parse(leaderboardListView.get((int)2).get("logo url").toString())).into(profile3);
					} else {
						profile3.setImageResource(R.drawable.profile_logo);
					}
					//      End part: Part 3
				} catch (Exception e) {
					 
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
			if ((_position == 0) || ((_position == 1) || (_position == 2))) {
				mainBody.setVisibility(View.GONE);
			}
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
					ActivityOptions options = ActivityOptions.makeCustomAnimation(LeaderboardActivity.this, R.anim.fade_in, R.anim.fade_out);
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