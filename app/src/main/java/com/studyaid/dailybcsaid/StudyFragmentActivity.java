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
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class StudyFragmentActivity extends  Fragment  { 
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> mapdata = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listdata = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private ListView listview1;
	
	private Intent i = new Intent();
	private DatabaseReference Study = _firebase.getReference("Questions");
	private ChildEventListener _Study_child_listener;
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.study_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		com.google.firebase.FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		
		listview1 = (ListView) _view.findViewById(R.id.listview1);
		
		_Study_child_listener = new ChildEventListener() {
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
		Study.addChildEventListener(_Study_child_listener);
	}
	
	private void initializeLogic() {
		Study.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				listdata = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						listdata.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				for(HashMap<String,Object> hmap: listdata){
						for(Object str:hmap.keySet()){
								if (hmap.get(str) instanceof HashMap) {
										//SketchwareUtil.showMessage(getContext(),"true");
										map = (HashMap<String, Object>) hmap.get(str);
										
							mapdata = new HashMap<>();
							mapdata.put("title", map.get("title").toString());
							mapdata.put("material", map.get("material").toString());
							mapdata.put("date", _millisToDate(Double.parseDouble(map.get("date").toString())));
							list.add(mapdata);
						}
						}
				}
				for(HashMap<String,Object> hmap: list){
						int i = 0;
						mapdata = new HashMap<>();
						for(HashMap<String,Object> map:list){
								
								if(hmap.get("date").toString().equals(map.get("date").toString())){
										
										
										//mapdata.put("syllabus",map.get("syllabus"));
										//mapdata.put("title",map.get("title"));
										//mapdata.put("date", map.get("date"));
										mapdata.put("date", map.get("date"));
										mapdata.put("title".concat(Integer.valueOf(i).toString()), map.get("title"));
										mapdata.put("material".concat(Integer.valueOf(i).toString()), map.get("material"));
								
									
										i++;
										
								}
								
								
								
						}
						if (!listmap.contains(mapdata)) {
								//SketchwareUtil.showMessage(getContext(),"true");
								listmap.add(mapdata);
								
								
						}
						
				}
					
				listview1.setAdapter(new Listview1Adapter(listmap));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public String _millisToDate (final double _millis) {
		return DateFormat.getDateInstance(DateFormat.LONG).format(_millis);
		    //You can use DateFormat.LONG instead of SHORT
		
	}
	
	
	public void _shapeRadius (final View _v, final String _color, final double _radius) {
		android.graphics.drawable.GradientDrawable shape = new android.graphics.drawable.GradientDrawable();
		  shape.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
		
		shape.setCornerRadius((int)_radius);
		
		shape.setColor(Color.parseColor(_color));
		_v.setBackgroundDrawable(shape);
	}
	
	
	public void _radius_to (final View _view, final double _radius, final double _shadow, final String _color) {
		android.graphics.drawable.GradientDrawable ab = new android.graphics.drawable.GradientDrawable();
		
		ab.setColor(Color.parseColor(_color));
		ab.setCornerRadius((float) _radius);
		_view.setElevation((float) _shadow);
		_view.setBackground(ab);
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
				_view = _inflater.inflate(R.layout.material, null);
			}
			
			final LinearLayout header = (LinearLayout) _view.findViewById(R.id.header);
			final androidx.cardview.widget.CardView cardview1 = (androidx.cardview.widget.CardView) _view.findViewById(R.id.cardview1);
			final TextView textview = (TextView) _view.findViewById(R.id.textview);
			final TableLayout linear_main = (TableLayout) _view.findViewById(R.id.linear_main);
			final TableRow linear1 = (TableRow) _view.findViewById(R.id.linear1);
			final TableRow linear2 = (TableRow) _view.findViewById(R.id.linear2);
			final TableRow linear3 = (TableRow) _view.findViewById(R.id.linear3);
			final TableRow linear4 = (TableRow) _view.findViewById(R.id.linear4);
			final TableRow linear5 = (TableRow) _view.findViewById(R.id.linear5);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			final com.google.android.material.button.MaterialButton materialbutton1 = (com.google.android.material.button.MaterialButton) _view.findViewById(R.id.materialbutton1);
			final TextView textview2 = (TextView) _view.findViewById(R.id.textview2);
			final com.google.android.material.button.MaterialButton materialbutton2 = (com.google.android.material.button.MaterialButton) _view.findViewById(R.id.materialbutton2);
			final TextView textview3 = (TextView) _view.findViewById(R.id.textview3);
			final com.google.android.material.button.MaterialButton materialbutton3 = (com.google.android.material.button.MaterialButton) _view.findViewById(R.id.materialbutton3);
			final TextView textview4 = (TextView) _view.findViewById(R.id.textview4);
			final com.google.android.material.button.MaterialButton materialbutton4 = (com.google.android.material.button.MaterialButton) _view.findViewById(R.id.materialbutton4);
			final TextView textview5 = (TextView) _view.findViewById(R.id.textview5);
			final com.google.android.material.button.MaterialButton materialbutton5 = (com.google.android.material.button.MaterialButton) _view.findViewById(R.id.materialbutton5);
			
			linear1.setVisibility(View.GONE);
			linear2.setVisibility(View.GONE);
			linear3.setVisibility(View.GONE);
			linear4.setVisibility(View.GONE);
			linear5.setVisibility(View.GONE);
			textview.setText(_data.get((int)_position).get("date").toString());
			if (listmap.get((int)_position).containsKey("title0")) {
				linear1.setVisibility(View.VISIBLE);
				textview1.setText(_data.get((int)_position).get("title0").toString());
			}
			if (listmap.get((int)_position).containsKey("title1")) {
				linear2.setVisibility(View.VISIBLE);
				textview2.setText(_data.get((int)_position).get("title1").toString());
			}
			if (listmap.get((int)_position).containsKey("title2")) {
				linear3.setVisibility(View.VISIBLE);
				textview3.setText(_data.get((int)_position).get("title2").toString());
			}
			if (listmap.get((int)_position).containsKey("title3")) {
				linear4.setVisibility(View.VISIBLE);
				textview4.setText(_data.get((int)_position).get("title3").toString());
			}
			if (listmap.get((int)_position).containsKey("title4")) {
				linear5.setVisibility(View.VISIBLE);
				textview5.setText(_data.get((int)_position).get("title4").toString());
			}
			_shapeRadius(header, "#3F67B2", 30);
			
			return _view;
		}
	}
	
	
}