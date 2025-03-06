package com.studyaid.dailybcsaid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
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
import androidx.cardview.widget.CardView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import java.util.Timer;
import java.util.TimerTask;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.google.gson.Gson;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.widget.NestedScrollView;

public class RoutineFragmentActivity extends  Fragment  { 
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private String timeStr = "";
	private double timeInt = 0;
	private double duration = 0;
	private double sec = 0;
	private String ref = "";
	private HashMap<String, Object> mapdata = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> temp = new ArrayList<>();
	
	private FrameLayout linear3;
	private LinearLayout linear2;
	private CardView cardview1;
	private TextView textview5;
	private LinearLayout linear5;
	private TextView textview3;
	private NestedScrollView linear4;
	private LinearLayout linear1;
	private TextView textview4;
	private TextView textview2;
	private CardView cardview2;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private TextView textview1;
	private LinearLayout linear8;
	private ImageView imageview1;
	private TextView textview6;
	private ImageView imageview2;
	private TextView textview7;
	private ImageView imageview3;
	private TextView textview8;
	private ListView listview1;
	
	private TimerTask time;
	private DatabaseReference Routine = _firebase.getReference("+routine+");
	private ChildEventListener _Routine_child_listener;
	private Calendar c = Calendar.getInstance();
	private Intent i = new Intent();
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.routine_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		com.google.firebase.FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		
		linear3 = (FrameLayout) _view.findViewById(R.id.linear3);
		linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
		cardview1 = (CardView) _view.findViewById(R.id.cardview1);
		textview5 = (TextView) _view.findViewById(R.id.textview5);
		linear5 = (LinearLayout) _view.findViewById(R.id.linear5);
		textview3 = (TextView) _view.findViewById(R.id.textview3);
		linear4 = (NestedScrollView) _view.findViewById(R.id.linear4);
		linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
		textview4 = (TextView) _view.findViewById(R.id.textview4);
		textview2 = (TextView) _view.findViewById(R.id.textview2);
		cardview2 = (CardView) _view.findViewById(R.id.cardview2);
		linear6 = (LinearLayout) _view.findViewById(R.id.linear6);
		linear7 = (LinearLayout) _view.findViewById(R.id.linear7);
		textview1 = (TextView) _view.findViewById(R.id.textview1);
		linear8 = (LinearLayout) _view.findViewById(R.id.linear8);
		imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
		textview6 = (TextView) _view.findViewById(R.id.textview6);
		imageview2 = (ImageView) _view.findViewById(R.id.imageview2);
		textview7 = (TextView) _view.findViewById(R.id.textview7);
		imageview3 = (ImageView) _view.findViewById(R.id.imageview3);
		textview8 = (TextView) _view.findViewById(R.id.textview8);
		listview1 = (ListView) _view.findViewById(R.id.listview1);
		
		cardview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getContext(), ExamDetailsActivity.class);
				i.putExtra("key", new Gson().toJson(mapdata));
				startActivity(i);
			}
		});
		
		_Routine_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (c.getTimeInMillis() < Double.parseDouble(_childValue.get("date").toString())) {
					list.add(_childValue);
					_SortMap(list, "date", true, false);
					listview1.setAdapter(new Listview1Adapter(list));
					((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					for(HashMap<String, Object> map : list) {
						if ((Double.parseDouble(map.get("date").toString()) - c.getTimeInMillis()) < 86400000) {
							mapdata = map;
							cardview2.setVisibility(View.VISIBLE);
							_CountdownTextView(textview8, Double.parseDouble(map.get("date").toString()));
							textview1.setText(map.get("title").toString());
							textview6.setText(map.get("participants").toString());
							textview7.setText(map.get("points").toString());
						}
					}
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
		Routine.addChildEventListener(_Routine_child_listener);
	}
	
	private void initializeLogic() {
		/*
_data();
*/
		Routine.removeEventListener(_Routine_child_listener);
		Bundle bundle = getArguments();
		ref = "Questions/".concat(bundle.getString("key"));
		Routine =
		_firebase.getReference(ref);
		Routine.addChildEventListener(_Routine_child_listener);
		cardview2.setVisibility(View.GONE);
	}
	
	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _data () {
		map = new HashMap<>();
		map.put("participants", "100");
		map.put("points", "500");
		map.put("title", "BCS Written");
		list.add(map);
		map = new HashMap<>();
		map.put("participants", "250");
		map.put("points", "100");
		map.put("title", "BCS Viva");
		list.add(map);
		map = new HashMap<>();
		map.put("participants", "120");
		map.put("points", "50");
		map.put("title", "BCS Police Cader");
		list.add(map);
		map = new HashMap<>();
		map.put("participants", "300");
		map.put("points", "200");
		map.put("title", "BCS Non Cader");
		list.add(map);
		map = new HashMap<>();
		map.put("participants", "340");
		map.put("points", "460");
		map.put("title", "220");
		list.add(map);
	}
	
	
	public void _SetTimerText (final double _sec) {
		if (((_sec % 600) / 10) == 59) {
			
		}
		else {
			sec = 60 - ((_sec % 600) / 10);
			timeStr = "00:".concat(String.valueOf((long)(sec)));
		}
	}
	
	
	public String _millisToDate (final double _millis) {
		return DateFormat.getDateInstance(DateFormat.LONG).format(_millis);
		    //You can use DateFormat.LONG instead of SHORT
		
	}
	
	
	public void _CountdownTextView (final TextView _text, final double _duration) {
		new android.os.CountDownTimer((long)_duration - c.getTimeInMillis(), 1000) {
				
				public void onTick(long duration) {
						//tTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
						//here you can have your logic to set text to edittext resource id
						// Duration
						long hour = ( duration / ( 1000 * 60 * 60 )) % 100;
						long minute = ( duration / ( 1000 * 60 )) % 60;
						long second = ( duration / 1000 ) % 60;
						_text.setText(String.format("%02d:%02d:%02d",
						java.util.concurrent.TimeUnit.MILLISECONDS.toHours(duration)%60,
						java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(duration)%60,
						java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(duration) % 60));
						
				}
				
				public void onFinish() {
						_text.setText("00:00:00");
				}
				
		}.start();
		
	}
	
	
	public String _DateTimeFormat (final double _millisecond, final String _format) {
		return new SimpleDateFormat(_format).format(_millisecond);
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
			LayoutInflater _inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.custom, null);
			}
			
			final androidx.cardview.widget.CardView cardview1 = (androidx.cardview.widget.CardView) _view.findViewById(R.id.cardview1);
			final LinearLayout linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final TextView textview2 = (TextView) _view.findViewById(R.id.textview2);
			final ImageView imageview2 = (ImageView) _view.findViewById(R.id.imageview2);
			final TextView textview3 = (TextView) _view.findViewById(R.id.textview3);
			final ImageView imageview3 = (ImageView) _view.findViewById(R.id.imageview3);
			final TextView textview4 = (TextView) _view.findViewById(R.id.textview4);
			
			if ((Double.parseDouble(list.get((int)_position).get("date").toString()) - c.getTimeInMillis()) < 86400000) {
				_CountdownTextView(textview4, Double.parseDouble(list.get((int)_position).get("date").toString()));
			}
			else {
				textview4.setText(_DateTimeFormat(Double.parseDouble(list.get((int)_position).get("date").toString()), "dd-MM-yy"));
			}
			/*
if ((Double.parseDouble(list.get((int)_position).get("date").toString()) - c.getTimeInMillis()) < 86400000) {
list.remove((int)(_position));
cardview1.setVisibility(View.GONE);
}
*/
			textview1.setText(list.get((int)_position).get("title").toString());
			textview2.setText(list.get((int)_position).get("participants").toString());
			textview3.setText(list.get((int)_position).get("points").toString());
			cardview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setClass(getContext(), ExamDetailsActivity.class);
					i.putExtra("key", new Gson().toJson(list.get((int)(_position))));
					startActivity(i);
				}
			});
			
			return _view;
		}
	}
	
	
}