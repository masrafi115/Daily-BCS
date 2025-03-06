package com.studyaid.dailybcsaid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.widget.LinearLayout;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.google.android.material.navigation.NavigationView;

import android.content.Intent;
import android.net.Uri;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.app.Activity;
import android.content.SharedPreferences;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class DashboardActivity extends  AppCompatActivity  { 
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private DrawerLayout _drawer;
	private HashMap<String, Object> map = new HashMap<>();
	private double points = 0;
	private  TextView t1;
	
	private LinearLayout linear1;
	private LinearLayout main;
	private BottomNavigationView bottomnavigation1;
	private NavigationView _drawer_navigation;
	
	private Intent i = new Intent();
	private DatabaseReference user = _firebase.getReference("user");
	private ChildEventListener _user_child_listener;
	private FirebaseAuth auth;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private SharedPreferences Database;
	private Calendar c = Calendar.getInstance();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.dashboard);
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
		_drawer = (DrawerLayout) findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(DashboardActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		main = (LinearLayout) findViewById(R.id.main);
		bottomnavigation1 = (BottomNavigationView) findViewById(R.id.bottomnavigation1);
		_drawer_navigation = (NavigationView) _nav_view.findViewById(R.id.navigation);
		auth = FirebaseAuth.getInstance();
		Database = getSharedPreferences("data", Activity.MODE_PRIVATE);
		
		bottomnavigation1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(MenuItem item) {
				final int _itemId = item.getItemId();
				if (_itemId == 0) {
					_loadFragment(new HomeFragmentActivity());
				}
				if (_itemId == 1) {
					_loadFragment(new StudyFragmentActivity());
				}
				if (_itemId == 2) {
					_loadFragment(new AllRoutineFragmentActivity());
				}
				if (_itemId == 3) {
					_loadFragment(new ProfileFragmentActivity());
				}
				return true;
			}
		});
		
		_user_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					Database.edit().putString("name", _childValue.get("name").toString()).commit();
					points = Double.parseDouble(_childValue.get("points").toString());
					t1.setText(String.valueOf((long)(points)));
					if (_childValue.containsKey("checkin")) {
						if (!_childValue.get("checkin").toString().equals(new SimpleDateFormat("d-M-yy").format(c.getTime()))) {
							_Dialog("Daily Checkin", "Get Daily Checkin Bonus  20 Coin");
						}
					}
					else {
						_Dialog("Daily Checkin", "Get Daily Checkin Bonus  20 Coin");
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					points = Double.parseDouble(_childValue.get("points").toString());
					t1.setText(String.valueOf((long)(points)));
				}
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
		user.addChildEventListener(_user_child_listener);
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		t1= (TextView)_toolbar.findViewById(R.id.text);
		LinearLayout l1= (LinearLayout)_toolbar.findViewById(R.id.lay);
		//To prevent same class name colliding
		bottomnavigation1.getMenu().add(0, 0, 0, "Home").setIcon(R.drawable.ic_home);
		bottomnavigation1.getMenu().add(0, 1, 0, "Study").setIcon(R.drawable.ic_study);
		bottomnavigation1.getMenu().add(0, 2, 0, "Routine").setIcon(R.drawable.ic_policy);
		bottomnavigation1.getMenu().add(0, 3, 0, "Profile").setIcon(R.drawable.ic_perm_identity_grey);
		//_drawer_navigation.addHeaderView(_drawer_linear1);
		_drawer_navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() { 
				@Override public boolean onNavigationItemSelected(MenuItem item) {
						int id = item.getItemId();
						if (id == R.id.bookmark){
					i.setClass(getApplicationContext(), BookmarkActivity.class);
					startActivity(i);
					SketchwareUtil.showMessage(getApplicationContext(), "first one");
				} else if (id == R.id.buy_coins){
					SketchwareUtil.showMessage(getApplicationContext(), "second one");
				} else if (id == R.id.share){
					_drawer.closeDrawer(GravityCompat.START);
				} else if (id == R.id.rate){
					_drawer.closeDrawer(GravityCompat.START);
				} else if (id == R.id.feedback){
					i.setClass(getApplicationContext(), FeedbackActivity.class);
					startActivity(i);
					_drawer.closeDrawer(GravityCompat.START);
				}
				_drawer.closeDrawer(GravityCompat.START);
				return false; 
						
				} 
		});
		
		_setDrawerWidth(250);
		_loadFragment(new HomeFragmentActivity());
		if (Database.getString("isGuest", "").equals("true")) {
			user.removeEventListener(_user_child_listener);
		}
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
		if (_drawer.isDrawerOpen(GravityCompat.START)) {
			_drawer.closeDrawer(GravityCompat.START);
		}
		else {
			super.onBackPressed();
		}
	}
	public void _setDrawerWidth (final double _num) {
		LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		
		_nav_view.setBackgroundColor(Color.parseColor("#FFFFFF"));
		
		androidx.drawerlayout.widget.DrawerLayout.LayoutParams params = (androidx.drawerlayout.widget.DrawerLayout.LayoutParams)_nav_view.getLayoutParams();
		
		params.width = (int)getDip((int)_num);
		
		params.height = androidx.drawerlayout.widget.DrawerLayout.LayoutParams.MATCH_PARENT;
		
		_nav_view.setLayoutParams(params);
	}
	
	
	public void _loadFragment (final Fragment _fragment) {
		androidx.fragment.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		        transaction.replace(R.id.main, _fragment);
		transaction.commit();
	}
	
	
	public void _Dialog (final String _title, final String _message) {
		final AlertDialog d = new AlertDialog.Builder(DashboardActivity.this).create();
		LayoutInflater inflater = getLayoutInflater();
		View convertVie = (View) inflater.inflate(R.layout.daily_checkin, null);
		
		TextView text1 = (TextView) convertVie.findViewById(R.id.textview1);
		text1.setText(_title);
		
		TextView text2 = (TextView) convertVie.findViewById(R.id.textview2);
		text2.setText(_message);
		
		
		LinearLayout dismiss = (LinearLayout) convertVie.findViewById(R.id.linear3);
		
		android.graphics.drawable.GradientDrawable ind = new android.graphics.drawable.GradientDrawable();  ind.setColor(Color.parseColor("#2196F3"));
		ind.setCornerRadius(30);
		dismiss.setBackground(ind);
		dismiss.setElevation((int)5);
		
		dismiss.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
						
						
				map = new HashMap<>();
				map.put("points", String.valueOf((long)(points + 20)));
				map.put("checkin", new SimpleDateFormat("d-M-yy").format(c.getTime()));
				user.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
				d.dismiss();
			}
		});
		
		
		LinearLayout inear = (LinearLayout) convertVie.findViewById(R.id.box);
		
		android.graphics.drawable.GradientDrawable ine = new android.graphics.drawable.GradientDrawable();  ine.setColor(Color.parseColor("#ffffff"));
		ine.setCornerRadius(15);
		inear.setBackground(ine);
		
		
		d.setView(convertVie);
		//d.setCancelable(false);
		
		d.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
		d.show();
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