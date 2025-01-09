package yt.linkearnfasterpanel.faster;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
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
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.android.material.*;
import com.google.android.material.button.*;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.FirebaseApp;
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
import java.util.regex.*;
import org.json.*;
import androidx.core.content.ContextCompat;

public class HistoryActivity extends AppCompatActivity {

private MaterialProgressDialog progressDialog;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double task_num = 0;
	private double withdraw_num = 0;
	private double deposit_num = 0;
	
	private ArrayList<HashMap<String, Object>> task_history_listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> withdraw_history_listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> deposit_history_listmap = new ArrayList<>();
	
	private NestedScrollView vscroll1;
	private RelativeLayout relativelayout1;
	private MaterialButtonToggleGroup history_bt_body;
	private RecyclerView task_history_listview;
	private RecyclerView withdraw_history_list;
	private RecyclerView deposit_history_list;
	private MaterialButton task_history_bt;
	private MaterialButton withdraw_bt;
	private MaterialButton deposit_bt;
	
	private DatabaseReference history = _firebase.getReference("history");
	private ChildEventListener _history_child_listener;
	private DatabaseReference financial = _firebase.getReference("financial/withdraw");
	private ChildEventListener _financial_child_listener;
	private DatabaseReference financialDeposit = _firebase.getReference("financial/deposit");
	private ChildEventListener _financialDeposit_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.history);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = findViewById(R.id.vscroll1);
		relativelayout1 = findViewById(R.id.relativelayout1);
		history_bt_body = findViewById(R.id.history_bt_body);
		task_history_listview = findViewById(R.id.task_history_listview);
		withdraw_history_list = findViewById(R.id.withdraw_history_list);
		deposit_history_list = findViewById(R.id.deposit_history_list);
		task_history_bt = findViewById(R.id.task_history_bt);
		withdraw_bt = findViewById(R.id.withdraw_bt);
		deposit_bt = findViewById(R.id.deposit_bt);
		
		task_history_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				task_history_listview.setVisibility(View.VISIBLE);
				withdraw_history_list.setVisibility(View.GONE);
				deposit_history_list.setVisibility(View.GONE);
			}
		});
		
		withdraw_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				task_history_listview.setVisibility(View.GONE);
				withdraw_history_list.setVisibility(View.VISIBLE);
				deposit_history_list.setVisibility(View.GONE);
			}
		});
		
		deposit_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				task_history_listview.setVisibility(View.GONE);
				withdraw_history_list.setVisibility(View.GONE);
				deposit_history_list.setVisibility(View.VISIBLE);
			}
		});
		
		_history_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				_Material3Loader(false, "");
				task_history_listmap.add(_childValue);
				task_history_listview.setAdapter(new Task_history_listviewAdapter(task_history_listmap));
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				task_num = 0;
				for(int _repeat28 = 0; _repeat28 < (int)(task_history_listmap.size()); _repeat28++) {
					if (task_history_listmap.get((int)task_num).get("key").toString().equals(_childKey)) {
						task_history_listmap.remove((int)(task_num));
						task_history_listmap.add((int)task_num, _childValue);
					}
					task_num++;
				}
				task_history_listview.setAdapter(new Task_history_listviewAdapter(task_history_listmap));
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				task_num = 0;
				for(int _repeat11 = 0; _repeat11 < (int)(task_history_listmap.size()); _repeat11++) {
					if (task_history_listmap.get((int)task_num).get("key").toString().equals(_childKey)) {
						task_history_listmap.remove((int)(task_num));
						task_history_listmap.add((int)task_num, _childValue);
					}
					task_num++;
				}
				task_history_listview.setAdapter(new Task_history_listviewAdapter(task_history_listmap));
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		history.addChildEventListener(_history_child_listener);
		
		_financial_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				withdraw_history_listmap.add(_childValue);
				withdraw_history_list.setAdapter(new Withdraw_history_listAdapter(withdraw_history_listmap));
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				withdraw_num = 0;
				for(int _repeat15 = 0; _repeat15 < (int)(withdraw_history_listmap.size()); _repeat15++) {
					if (withdraw_history_listmap.get((int)withdraw_num).get("key").toString().equals(_childKey)) {
						withdraw_history_listmap.remove((int)(withdraw_num));
						withdraw_history_listmap.add((int)withdraw_num, _childValue);
					}
					withdraw_num++;
				}
				withdraw_history_list.setAdapter(new Withdraw_history_listAdapter(withdraw_history_listmap));
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				withdraw_num = 0;
				for(int _repeat15 = 0; _repeat15 < (int)(withdraw_history_listmap.size()); _repeat15++) {
					if (withdraw_history_listmap.get((int)withdraw_num).get("key").toString().equals(_childKey)) {
						withdraw_history_listmap.remove((int)(withdraw_num));
						withdraw_history_listmap.add((int)withdraw_num, _childValue);
					}
					withdraw_num++;
				}
				withdraw_history_list.setAdapter(new Withdraw_history_listAdapter(withdraw_history_listmap));
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
				deposit_history_listmap.add(_childValue);
				deposit_history_list.setAdapter(new Deposit_history_listAdapter(deposit_history_listmap));
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				deposit_num = 0;
				for(int _repeat11 = 0; _repeat11 < (int)(deposit_history_listmap.size()); _repeat11++) {
					if (deposit_history_listmap.get((int)deposit_num).get("key").toString().equals(_childKey)) {
						deposit_history_listmap.remove((int)(deposit_num));
						deposit_history_listmap.add((int)deposit_num, _childValue);
					}
					deposit_num++;
				}
				deposit_history_list.setAdapter(new Deposit_history_listAdapter(deposit_history_listmap));
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				deposit_num = 0;
				for(int _repeat15 = 0; _repeat15 < (int)(deposit_history_listmap.size()); _repeat15++) {
					if (deposit_history_listmap.get((int)deposit_num).get("key").toString().equals(_childKey)) {
						deposit_history_listmap.remove((int)(deposit_num));
						deposit_history_listmap.add((int)deposit_num, _childValue);
					}
					deposit_num++;
				}
				deposit_history_list.setAdapter(new Deposit_history_listAdapter(deposit_history_listmap));
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		financialDeposit.addChildEventListener(_financialDeposit_child_listener);
	}
	
	private void initializeLogic() {
		//      Start part: bakend
		progressDialog = new MaterialProgressDialog(HistoryActivity.this);
		//      End part: bakend
		//      Start part: others
		task_history_listview.setLayoutManager(new LinearLayoutManager(this));
		withdraw_history_list.setLayoutManager(new LinearLayoutManager(this));
		deposit_history_list.setLayoutManager(new LinearLayoutManager(this));
		_Material3Loader(true, "Getting data...");
		//      End part: others
	}
	
	public void _Material3Loader(final boolean _variable, final String _title) {
		if (_variable) {
			progressDialog.show(_title);
		} else {
			
			progressDialog.dismiss();
		}
	}
	
	public class Task_history_listviewAdapter extends RecyclerView.Adapter<Task_history_listviewAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Task_history_listviewAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
			
			logo.setVisibility(View.GONE);
			second_txt.setVisibility(View.GONE);
			second.setVisibility(View.GONE);
			linear_gone.setVisibility(View.GONE);
			person_txt.setVisibility(View.GONE);
			progressbar2.setVisibility(View.GONE);
			try {
				if (_data.get((int)_position).get("uid").toString().equals(getIntent().getStringExtra("uid"))) {
					main_body.setVisibility(View.VISIBLE);
					if (_data.get((int)_position).containsKey("title")) {
						title.setText(_data.get((int)_position).get("title").toString());
					}
					if (_data.get((int)_position).containsKey("balance")) {
						dollar.setText(_data.get((int)_position).get("balance").toString().concat("$"));
					}
					if (_data.get((int)_position).containsKey("date")) {
						person.setText("You completed! Date: ".concat(_data.get((int)_position).get("date").toString()));
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
	
	public class Withdraw_history_listAdapter extends RecyclerView.Adapter<Withdraw_history_listAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Withdraw_history_listAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
			
			logo.setVisibility(View.GONE);
			second_txt.setVisibility(View.GONE);
			second.setVisibility(View.GONE);
			linear_gone.setVisibility(View.GONE);
			person_txt.setVisibility(View.GONE);
			progressbar2.setVisibility(View.GONE);
			try {
				if (_data.get((int)_position).get("uid").toString().equals(getIntent().getStringExtra("uid"))) {
					main_body.setVisibility(View.VISIBLE);
					if (_data.get((int)_position).containsKey("name")) {
						title.setText(_data.get((int)_position).get("name").toString());
					}
					if (_data.get((int)_position).containsKey("money")) {
						dollar.setText(_data.get((int)_position).get("money").toString().concat("$"));
					}
					if (_data.get((int)_position).containsKey("status")) {
						if (_data.get((int)_position).get("status").toString().equals("pending")) {
							person.setText("Withdrawal is pending. Date: ".concat(_data.get((int)_position).get("date").toString()));
							person.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.md_theme_light_errorContainer));
						} else {
							if (_data.get((int)_position).get("status").toString().equals("failed")) {
								person.setText("Withdrawal is failed. Date: ".concat(_data.get((int)_position).get("date").toString()));
								person.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorError));
							} else {
								if (_data.get((int)_position).get("status").toString().equals("success")) {
									person.setText("Withdrawal is success. Date: ".concat(_data.get((int)_position).get("date").toString()));
									person.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
								}
							}
						}
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
	
	public class Deposit_history_listAdapter extends RecyclerView.Adapter<Deposit_history_listAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Deposit_history_listAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
			
			logo.setVisibility(View.GONE);
			second_txt.setVisibility(View.GONE);
			second.setVisibility(View.GONE);
			linear_gone.setVisibility(View.GONE);
			person_txt.setVisibility(View.GONE);
			progressbar2.setVisibility(View.GONE);
			try {
				if (_data.get((int)_position).get("uid").toString().equals(getIntent().getStringExtra("uid"))) {
					main_body.setVisibility(View.VISIBLE);
					if (_data.get((int)_position).containsKey("name")) {
						title.setText(_data.get((int)_position).get("name").toString());
					}
					if (_data.get((int)_position).containsKey("money")) {
						dollar.setText(_data.get((int)_position).get("money").toString().concat("$"));
					}
					if (_data.get((int)_position).containsKey("status")) {
						if (_data.get((int)_position).get("status").toString().equals("pending")) {
							person.setText("Withdrawal is pending. Date: ".concat(_data.get((int)_position).get("date").toString()));
							person.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.md_theme_light_errorContainer));
						} else {
							if (_data.get((int)_position).get("status").toString().equals("failed")) {
								person.setText("Withdrawal is failed. Date: ".concat(_data.get((int)_position).get("date").toString()));
								person.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorError));
							} else {
								if (_data.get((int)_position).get("status").toString().equals("success")) {
									person.setText("Withdrawal is success. Date: ".concat(_data.get((int)_position).get("date").toString()));
									person.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
								}
							}
						}
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