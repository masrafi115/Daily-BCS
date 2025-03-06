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
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class ExamActivity extends  AppCompatActivity  { 
	
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private  ArrayList<Model> dataList;
	private String title = "";
	private HashMap<String, Object> map = new HashMap<>();
	private  TextView timerText;
	private  CountDownTimer timer;
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
	private ListView listView;
	private LinearLayout linear1;
	private Button button1;
	
	private Intent i = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.exam);
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
		listView = (ListView) findViewById(R.id.listView);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		button1 = (Button) findViewById(R.id.button1);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				timer.cancel();
				i.setClass(getApplicationContext(), ResultViewActivity.class);
				map.put("questions", new Gson().toJson(dataList));
				i.putExtra("data", new Gson().toJson(map));
				i.putExtra("key", "online");
				startActivity(i);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		map = new Gson().fromJson(getIntent().getStringExtra("data"), new TypeToken<HashMap<String, Object>>(){}.getType());
		list = new Gson().fromJson(map.get("questions").toString().substring((int)(1), (int)(map.get("questions").toString().length() - 1)), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		
		dataList = new ArrayList<Model>();
		for(int i=0;i<list.size();i++){
				Model m = new Model(list.get(i).get("ques").toString(),list.get(i).get("ans").toString(),list.get(i).get("a").toString(),list.get(i).get("b").toString(),list.get(i).get("c").toString(),list.get(i).get("d").toString(),list.get(i).get("def").toString());
				dataList.add(m);
				
		}
		
		
		CustomListAdapter cadapter = new CustomListAdapter(ExamActivity.this, dataList);
		listView.setAdapter(cadapter);
		listView.invalidate();
		
		
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		//dialog and submit before backing
		timer.cancel();
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.time, menu);
		
		MenuItem timerItem = menu.findItem(R.id.break_timer);
		timerText = (TextView) androidx.core.view.MenuItemCompat.getActionView(timerItem);
		
		timerText.setPadding(10, 10, 10, 10); //Or something like that...
		
		startTimer(30000, 1000); //One tick every second for 30 seconds
		return super.onCreateOptionsMenu(menu);
	}
	public void _extra () {
	} private void startTimer(long duration, long interval) {
		
		    timer = new CountDownTimer(duration, interval) {
			
			        @Override
			        public void onFinish() {
				            //TODO Whatever's meant to happen when it finishes
				        
				i.setClass(getApplicationContext(), ResultViewActivity.class);
				map.put("questions", new Gson().toJson(dataList));
				i.putExtra("data", new Gson().toJson(map));
				i.putExtra("key", "online");
				startActivity(i);
				finish();
			}
			
			        @Override
			        public void onTick(long millisecondsLeft) {
				            int secondsLeft = (int) Math.round((millisecondsLeft / (double) 1000));
				            timerText.setText(secondsToString(secondsLeft));
				        }
			    };
		
		    timer.start();
	}
	
	private String secondsToString(int improperSeconds) {
		
		    //Seconds must be fewer than are in a day
		
		    android.text.format.Time secConverter = new android.text.format.Time();
		
		    secConverter.hour = 0;
		    secConverter.minute = 0;
		    secConverter.second = 0;
		
		    secConverter.second = improperSeconds;
		    secConverter.normalize(true);
		
		    String hours = String.valueOf(secConverter.hour);
		    String minutes = String.valueOf(secConverter.minute);
		    String seconds = String.valueOf(secConverter.second);
		
		    if (seconds.length() < 2) {
			        seconds = "0" + seconds;
			    }
		    if (minutes.length() < 2) {
			        minutes = "0" + minutes;
			    }
		    if (hours.length() < 2) {
			        hours = "0" + hours;
			    }
		
		    String timeString = hours + ":" + minutes + ":" + seconds;
		    return timeString;
		
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