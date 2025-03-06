package com.studyaid.dailybcsaid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class BreakingActivity extends  AppCompatActivity  { 
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private double day = 0;
	private double mon = 0;
	private double year = 0;
	private String search = "";
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> temp = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	private LinearLayout linear2;
	private AdView adview1;
	
	private DatabaseReference news = _firebase.getReference("news");
	private ChildEventListener _news_child_listener;
	private AlertDialog.Builder d;
	private Calendar c = Calendar.getInstance();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.breaking);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_app_bar = (AppBarLayout) findViewById(R.id._app_bar);
		_coordinator = (CoordinatorLayout) findViewById(R.id._coordinator);
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		listview1 = (ListView) findViewById(R.id.listview1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		adview1 = (AdView) findViewById(R.id.adview1);
		d = new AlertDialog.Builder(this);
		
		_news_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				list.add(_childValue);
				listview1.setAdapter(new Listview1Adapter(list));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
		news.addChildEventListener(_news_child_listener);
	}
	
	private void initializeLogic() {
		adview1.loadAd(new AdRequest.Builder().build());
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _extra () {
	}
	public static class ReadMoreOption {
		
		private Context context;
		private boolean labelUnderLine;
		private boolean textIsSelectable;
		private String lessLabel;
		private int lessLabelColor;
		private String moreLabel;
		private int moreLabelColor;
		private int textLength;
		
		public static class Builder {
			
			private Context context;
			private boolean labelUnderLine = false;
			private boolean textIsSelectable = false;
			private String lessLabel = "Less";
			private int lessLabelColor = Color.parseColor("#000000");
			private String moreLabel = "More";
			private int moreLabelColor = Color.parseColor("#000000");
			private int textLength = 100;
			
			public Builder(Context context) {
				context = context;
			}
			
			public Builder textLength(int length) {
				this.textLength = length;
				return this;
			}
			
			public Builder moreLabel(String moreLabel) {
				this.moreLabel = moreLabel;
				return this;
			}
			
			public Builder lessLabel(String lessLabel) {
				this.lessLabel = lessLabel;
				return this;
			}
			
			public Builder moreLabelColor(int moreLabelColor) {
				this.moreLabelColor = moreLabelColor;
				return this;
			}
			
			public Builder lessLabelColor(int lessLabelColor) {
				this.lessLabelColor = lessLabelColor;
				return this;
			}
			
			public Builder labelUnderLine(boolean labelUnderLine) {
				this.labelUnderLine = labelUnderLine;
				return this;
			}
			
			public Builder textIsSelectable(boolean textIsSelectable) {
				this.textIsSelectable = textIsSelectable;
				return this;
			}
			
			public ReadMoreOption build() {
				return new ReadMoreOption(this);
			}
		}
		
		private ReadMoreOption(Builder builder) {
			
			context = builder.context;
			textLength = builder.textLength;
			moreLabel = builder.moreLabel;
			lessLabel = builder.lessLabel;
			moreLabelColor = builder.moreLabelColor;
			lessLabelColor = builder.lessLabelColor;
			labelUnderLine = builder.labelUnderLine;
			textIsSelectable = builder.textIsSelectable;
		}
		
		public void addReadMoreTo(final TextView textView, final String text) {
			textView.setTextIsSelectable(textIsSelectable);
			if (text.length() <= textLength) {
				textView.setText(text);
				return;
			}
			SpannableString ss = new SpannableString(text.substring(0, textLength) + "... " + moreLabel);
			ss.setSpan(new ClickableSpan() {
				public void onClick(View view) {
					addReadLess(textView, text);
				}
				
				public void updateDrawState(TextPaint ds) {
					super.updateDrawState(ds);
					ds.setUnderlineText(labelUnderLine);
					ds.setColor(moreLabelColor);
				}
			}, ss.length() - moreLabel.length(), ss.length(), 33);
			textView.setText(ss);
			textView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
		}
		
		private void addReadLess(final TextView textView, final String text) {
			SpannableString ss = new SpannableString(text + " " + lessLabel);
			ss.setSpan(new ClickableSpan() {
				public void onClick(View view) {
					new Handler().post(new Runnable() {
						public void run() {
							addReadMoreTo(textView, text);
						}
					});
				}
				
				public void updateDrawState(TextPaint ds) {
					super.updateDrawState(ds);
					ds.setUnderlineText(labelUnderLine);
					ds.setColor(lessLabelColor);
				}
			}, ss.length() - lessLabel.length(), ss.length(), 33);
			textView.setText(ss);
			textView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
		}
	}
	{
	}
	
	
	public void _addmenu () {
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
			
		menu.add(0, 0, 0,"").setIcon(R.drawable.ic_event_white).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case 0:
			_datePickerDialog(d, c);
			break;
		}
		return
		super.onOptionsItemSelected(item);
		
	}
	
	
	public void _datePickerDialog (final AlertDialog.Builder _d, final Calendar _calendar) {
		 LinearLayout mylayout = new LinearLayout(this); 
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT); 
		mylayout.setLayoutParams(params);
		mylayout.setOrientation(LinearLayout.VERTICAL);
		 DatePicker dp=new DatePicker (BreakingActivity.this);
		final EditText myedittext = new EditText(this);  
		mylayout.addView(dp);
		mylayout.setBackgroundColor(0xffffffff); 
		dp.init(_calendar.get(Calendar.YEAR), _calendar.get(Calendar.MONTH),_calendar.get(Calendar.DAY_OF_MONTH),new DatePicker.OnDateChangedListener(){
				
				@Override public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
						day = datePicker.getDayOfMonth(); 
						mon = datePicker.getMonth()+1;
						year = datePicker.getYear(); 
				} });
		
		_d.setView(mylayout);
		_d.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				search = String.valueOf((long)(day)).concat("-".concat(String.valueOf((long)(mon)).concat("-".concat(String.valueOf((long)(year)).substring((int)(2), (int)(4))))));
				list.clear();
				news.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						temp = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								temp.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						for(HashMap<String,Object> hmap:temp){
								
								if (hmap.get("date").equals(search)) {
										list.add(hmap);
										
								}
								
								
						}
						
						listview1.setAdapter(new Listview1Adapter(list));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
		});
		_d.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		_d.create().show();
	}
	
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.news, null);
			}
			
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final LinearLayout linear7 = (LinearLayout) _view.findViewById(R.id.linear7);
			final LinearLayout linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final LinearLayout linear5 = (LinearLayout) _view.findViewById(R.id.linear5);
			final LinearLayout linear6 = (LinearLayout) _view.findViewById(R.id.linear6);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			final TextView cat = (TextView) _view.findViewById(R.id.cat);
			
			textview1.setText(list.get((int)_position).get("title").toString());
			cat.setText(list.get((int)_position).get("desc").toString());
			ReadMoreOption readMoreOption = new ReadMoreOption.Builder(BreakingActivity.this)
			
			.textLength(100) 
			.moreLabel("More")
			.lessLabel("Less")
			.moreLabelColor(Color.RED)
			.lessLabelColor(Color.BLUE)
			.labelUnderLine(false)
			.textIsSelectable(true)
			.build();
			
			readMoreOption.addReadMoreTo(cat, _data.get((int)_position).get("desc").toString());
			
			cat.setSelectAllOnFocus(false);
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}