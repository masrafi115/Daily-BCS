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
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class SolutionActivity extends  AppCompatActivity  { 
	
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	
	private ArrayList<HashMap<String, Object>> all = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> correct = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> wrong = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> skipped = new ArrayList<>();
	
	private TabLayout tablayout1;
	private ViewPager viewpager1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.solution);
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
		tablayout1 = (TabLayout) findViewById(R.id.tablayout1);
		viewpager1 = (ViewPager) findViewById(R.id.viewpager1);
	}
	
	private void initializeLogic() {
		all = new Gson().fromJson(getIntent().getStringExtra("all"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		correct = new Gson().fromJson(getIntent().getStringExtra("correct"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		wrong = new Gson().fromJson(getIntent().getStringExtra("wrong"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		skipped = new Gson().fromJson(getIntent().getStringExtra("skipped"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		tablayout1.setupWithViewPager(viewpager1);
		viewpager1.setAdapter(new MyFragmentAdapter(getApplicationContext(), getSupportFragmentManager(), 4));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public class MyFragmentAdapter extends FragmentStatePagerAdapter {
		Context context;
		int tabCount;
		
		public MyFragmentAdapter(Context context, FragmentManager fm, int tabCount) {
			super(fm);
			this.context = context;
			this.tabCount = tabCount;
		}
		
		@Override
		public int getCount(){
			return tabCount;
		}
		
		@Override
		public CharSequence getPageTitle(int _position) {
			if (_position == 0) {
				return "All";
			}
			if (_position == 1) {
				return "Correct";
			}
			if (_position == 2) {
				return "Wrong";
			}
			if (_position == 3) {
				return "Skipped";
			}
			return null;
		}
		
		@Override
		public Fragment getItem(int _position) {
			if (_position == 0) {
				Bundle bundle = new Bundle();
				AllFragmentActivity frag1 = new AllFragmentActivity();
				bundle.putSerializable("all",all);
				frag1.setArguments(bundle);
				return frag1;
				
			}
			if (_position == 1) {
				Bundle bundle = new Bundle();
				CorrectFragmentActivity frag2 = new CorrectFragmentActivity();
				bundle.putSerializable("correct", correct);
				frag2.setArguments(bundle);
				return frag2;
			}
			if (_position == 2) {
				Bundle bundle = new Bundle();
				WrongFragmentActivity frag3 = new WrongFragmentActivity();
				bundle.putSerializable("wrong",wrong);
				frag3.setArguments(bundle);
				return frag3;
			}
			if (_position == 3) {
				Bundle bundle = new Bundle();
				SkippedFragmentActivity frag4 = new SkippedFragmentActivity();
				bundle.putSerializable("skipped", skipped);
				frag4.setArguments(bundle);
				return frag4;
			}
			return null;
		}
		
	}
	
	@Override
	public void onBackPressed() {
		finish();
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