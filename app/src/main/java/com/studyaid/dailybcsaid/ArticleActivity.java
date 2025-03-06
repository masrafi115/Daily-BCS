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
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.content.Intent;
import android.net.Uri;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.bumptech.glide.Glide;
import android.graphics.Typeface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class ArticleActivity extends  AppCompatActivity  { 
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private double length = 0;
	private double r = 0;
	private String value1 = "";
	private String save = "";
	private String ref = "";
	private double n = 0;
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> mapvar = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	private ArrayList<String> tags = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> tagged = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> tmp = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear2;
	private GridView grid;
	
	private DatabaseReference Post = _firebase.getReference("post");
	private ChildEventListener _Post_child_listener;
	private Intent i = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.article);
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
		hscroll1 = (HorizontalScrollView) findViewById(R.id.hscroll1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		grid = (GridView) findViewById(R.id.grid);
		
		_Post_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("status").toString().equals("verified")) {
					list.add(_childValue);
					_SortMap(list, "time", true, false);
					listview1.setAdapter(new Listview1Adapter(list));
					((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
		Post.addChildEventListener(_Post_child_listener);
	}
	
	private void initializeLogic() {
		com.google.android.material.appbar.AppBarLayout appBarLayout = (com.google.android.material.appbar.AppBarLayout) _toolbar.getParent();
		appBarLayout.setStateListAnimator(null);
		Post.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				list = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						list.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				save = new Gson().toJson(list);
				_Search(getIntent().getStringExtra("category"), save, list);
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		map = new Gson().fromJson(getIntent().getStringExtra("data"), new TypeToken<HashMap<String, Object>>(){}.getType());
		//map = (HashMap<String,Object>)mapvar.get("sub");
		getAllValuesFromMap(map, tags);
		for(int _repeat55 = 0; _repeat55 < (int)(tags.size()); _repeat55++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("tag", tags.get((int)(n)));
				tagged.add(_item);
			}
			
			n++;
		}
		
		grid.setLayoutParams(new LinearLayout.LayoutParams(tagged.size()*(int)getDip(100), GridLayout.LayoutParams.WRAP_CONTENT));
		
		grid.setNumColumns(tagged.size());
		
		grid.setBackgroundColor(Color.WHITE);
		
		grid.setVerticalSpacing(5);
		
		grid.setHorizontalSpacing(35);
		grid.setColumnWidth(150);
		grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		
		grid.setAdapter(new GridAdapter(tagged));
		
		/*grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	@Override
	public void onItemClick(AdapterView parent, View view, int _pos, long id) {
		showMessage(String.valueOf(_pos));
	}
});*/
		
		
		
		
		
		/*

*/
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _Search (final String _Keyword, final String _save, final ArrayList<HashMap<String, Object>> _list) {
		list = new Gson().fromJson(_save, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		length = list.size();
		r = length - 1;
		for(int _repeat17 = 0; _repeat17 < (int)(length); _repeat17++) {
			value1 = list.get((int)r).get("category").toString();
			if (!(_Keyword.length() > value1.length()) && value1.toLowerCase().contains(_Keyword.toLowerCase())) {
				
			}
			else {
				list.remove((int)(r));
				listview1.setAdapter(new Listview1Adapter(list));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
			r--;
		}
		listview1.setAdapter(new Listview1Adapter(list));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
	}
	
	
	public void _SortMap (final ArrayList<HashMap<String, Object>> _listMap, final String _key, final boolean _isNumber, final boolean _Ascending) {
		Collections.sort(_listMap, new Comparator<HashMap<String,Object>>(){
			public int compare(HashMap<String,Object> _compareMap1, HashMap<String,Object> _compareMap2){
				if (_isNumber) {
					long _count1 = Long.valueOf(_compareMap1.get(_key).toString());
					long _count2 = Long.valueOf(_compareMap2.get(_key).toString());
					if (_Ascending) {
						return _count1 < _count2 ? -1 : _count1 < _count2 ? 1 : 0;
					}
					else {
						return _count1 > _count2 ? -1 : _count1 > _count2 ? 1 : 0;
					}
				}
				else {
					if (_Ascending) {
						return (_compareMap1.get(_key).toString()).compareTo(_compareMap2.get(_key).toString());
					}
					else {
						return (_compareMap2.get(_key).toString()).compareTo(_compareMap1.get(_key).toString());
					}
				}
			}});
	}
	
	
	public void _radius_to (final View _view, final double _radius, final double _shadow, final String _color) {
		android.graphics.drawable.GradientDrawable ab = new android.graphics.drawable.GradientDrawable();
		
		ab.setColor(Color.parseColor(_color));
		ab.setCornerRadius((float) _radius);
		_view.setElevation((float) _shadow);
		_view.setBackground(ab);
	}
	
	
	public String _millisToDate (final double _millis) {
		return DateFormat.getDateInstance(DateFormat.LONG).format(_millis);
		    //You can use DateFormat.LONG instead of SHORT
		
	}
	
	
	public void _getAllValuesFromMap () {
	} public static void getAllValuesFromMap(Map<String, Object> map, ArrayList<String> output) {
						if (output == null) return;
						output.clear();
		
						if (map == null || map.size() <= 0) return;
		
						Iterator itr = map.entrySet().iterator();
						while (itr.hasNext()) {
									Map.Entry<String, String> entry = (Map.Entry) itr.next();
									output.add(entry.getValue());
						}
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
				_view = _inflater.inflate(R.layout.post, null);
			}
			
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final androidx.cardview.widget.CardView cardview2 = (androidx.cardview.widget.CardView) _view.findViewById(R.id.cardview2);
			final LinearLayout linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			final LinearLayout linear4 = (LinearLayout) _view.findViewById(R.id.linear4);
			final TextView textview2 = (TextView) _view.findViewById(R.id.textview2);
			final TextView textview3 = (TextView) _view.findViewById(R.id.textview3);
			
			imageview1.setImageResource(R.drawable.icon_splash);
			textview1.setText(list.get((int)_position).get("title").toString());
			if (list.get((int)_position).containsKey("img")) {
				Glide.with(getApplicationContext()).load(Uri.parse(list.get((int)_position).get("img").toString())).into(imageview1);
			}
			else {
				imageview1.setImageResource(R.drawable.icon_splash);
			}
			cardview2.setCardBackgroundColor(0xFFFFFFFF);
			cardview2.setRadius((float)15);
			cardview2.setCardElevation((float)0);
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/manrope_bold.ttf"), 0);
			textview2.setText(list.get((int)_position).get("category").toString());
			textview3.setText(_millisToDate(Double.parseDouble(list.get((int)_position).get("time").toString())));
			linear2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.putExtra("key", new Gson().toJson(list.get((int)(_position))));
					if (getIntent().hasExtra("extra")) {
						i.putExtra("extra", getIntent().getStringExtra("extra"));
					}
					i.setClass(getApplicationContext(), ViewArticleActivity.class);
					startActivity(i);
				}
			});
			
			return _view;
		}
	}
	
	public class GridAdapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public GridAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.tag, null);
			}
			
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final TextView text = (TextView) _view.findViewById(R.id.text);
			
			text.setText(tagged.get((int)_position).get("tag").toString());
			_radius_to(linear2, 45, 5, "#2196F3");
			linear2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					Post.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							list = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									list.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							save = new Gson().toJson(list);
							_Search(tagged.get((int)_position).get("tag").toString(), save, list);
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			});
			
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