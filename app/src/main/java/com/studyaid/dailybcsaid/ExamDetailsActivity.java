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
import android.widget.ScrollView;
import androidx.cardview.widget.CardView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
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
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class ExamDetailsActivity extends  AppCompatActivity  { 
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private HashMap<String, Object> map = new HashMap<>();
	private boolean isParticipated = false;
	private double n = 0;
	private String share = "";
	private boolean isGuest = false;
	private double points = 0;
	private double costPoints = 0;
	private HashMap<String, Object> mapdata = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> questions = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> participated = new ArrayList<>();
	
	private LinearLayout linear1;
	private ScrollView vscroll2;
	private LinearLayout linear21;
	private CardView cardview2;
	private CardView cardview1;
	private LinearLayout linear2;
	private TextView textview8;
	private LinearLayout linear3;
	private CardView cardview3;
	private LinearLayout linear12;
	private LinearLayout linear7;
	private Button join;
	private ImageView imageview1;
	private TextView textview2;
	private LinearLayout linear9;
	private ImageView imageview2;
	private TextView textview3;
	private LinearLayout linear10;
	private ImageView imageview3;
	private TextView textview4;
	private LinearLayout linear22;
	private LinearLayout linear23;
	private ScrollView vscroll1;
	private ImageView imageview8;
	private TextView textview10;
	private TextView textview1;
	private LinearLayout linear5;
	private LinearLayout linear4;
	private LinearLayout linear6;
	private ImageView imageview5;
	private TextView textview6;
	private ImageView imageview6;
	private TextView textview7;
	private ImageView imageview4;
	private TextView textview5;
	private ImageView imageview7;
	private LinearLayout linear8;
	private Button button1;
	private EditText edittext1;
	private LinearLayout linear13;
	private TextView textview9;
	private LinearLayout linear15;
	private LinearLayout linear25;
	private LinearLayout linear26;
	private LinearLayout linear28;
	private LinearLayout linear30;
	private FloatingActionButton fab1;
	private TextView textview12;
	private FloatingActionButton fab2;
	private TextView textview13;
	private FloatingActionButton fab3;
	private TextView textview14;
	private FloatingActionButton fab4;
	private TextView textview15;
	
	private Intent i = new Intent();
	private AlertDialog.Builder dialog;
	private Intent in = new Intent();
	private Calendar c = Calendar.getInstance();
	private RewardedVideoAd video;
	private RewardedVideoAdListener  video_listener;
	private Intent intent = new Intent();
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
	private Intent login = new Intent();
	private SharedPreferences Database;
	private Intent read = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.exam_details);
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
		vscroll2 = (ScrollView) findViewById(R.id.vscroll2);
		linear21 = (LinearLayout) findViewById(R.id.linear21);
		cardview2 = (CardView) findViewById(R.id.cardview2);
		cardview1 = (CardView) findViewById(R.id.cardview1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		textview8 = (TextView) findViewById(R.id.textview8);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		cardview3 = (CardView) findViewById(R.id.cardview3);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		join = (Button) findViewById(R.id.join);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		textview3 = (TextView) findViewById(R.id.textview3);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		textview4 = (TextView) findViewById(R.id.textview4);
		linear22 = (LinearLayout) findViewById(R.id.linear22);
		linear23 = (LinearLayout) findViewById(R.id.linear23);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		imageview8 = (ImageView) findViewById(R.id.imageview8);
		textview10 = (TextView) findViewById(R.id.textview10);
		textview1 = (TextView) findViewById(R.id.textview1);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		imageview5 = (ImageView) findViewById(R.id.imageview5);
		textview6 = (TextView) findViewById(R.id.textview6);
		imageview6 = (ImageView) findViewById(R.id.imageview6);
		textview7 = (TextView) findViewById(R.id.textview7);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		textview5 = (TextView) findViewById(R.id.textview5);
		imageview7 = (ImageView) findViewById(R.id.imageview7);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		button1 = (Button) findViewById(R.id.button1);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		textview9 = (TextView) findViewById(R.id.textview9);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear25 = (LinearLayout) findViewById(R.id.linear25);
		linear26 = (LinearLayout) findViewById(R.id.linear26);
		linear28 = (LinearLayout) findViewById(R.id.linear28);
		linear30 = (LinearLayout) findViewById(R.id.linear30);
		fab1 = (FloatingActionButton) findViewById(R.id.fab1);
		textview12 = (TextView) findViewById(R.id.textview12);
		fab2 = (FloatingActionButton) findViewById(R.id.fab2);
		textview13 = (TextView) findViewById(R.id.textview13);
		fab3 = (FloatingActionButton) findViewById(R.id.fab3);
		textview14 = (TextView) findViewById(R.id.textview14);
		fab4 = (FloatingActionButton) findViewById(R.id.fab4);
		textview15 = (TextView) findViewById(R.id.textview15);
		dialog = new AlertDialog.Builder(this);
		video = MobileAds.getRewardedVideoAdInstance(this);
		auth = FirebaseAuth.getInstance();
		Database = getSharedPreferences("data", Activity.MODE_PRIVATE);
		
		join.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				/*
if (_ListMapContainsValue(participated, 0, map.get("title").toString())) {

}
else {

}
*/
				if (!isGuest) {
					if ((Double.parseDouble(map.get("date").toString()) - c.getTimeInMillis()) < 86400000) {
						if (costPoints < points) {
							mapdata = new HashMap<>();
							mapdata.put("points", String.valueOf((long)(points - costPoints)));
							user.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(mapdata);
							mapdata = new HashMap<>();
							mapdata.put("participants", String.valueOf((long)(0 + 1)));
							
							in.setClass(getApplicationContext(), ExamActivity.class);
							in.putExtra("data", new Gson().toJson(map));
							startActivity(in);
						}
						else {
							SketchwareUtil.showMessage(getApplicationContext(), "You Don't Have Enough Points");
						}
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "পরীক্ষা শুরু হয়নি");
					}
				}
				else {
					login.setClass(getApplicationContext(), AuthenticationActivity.class);
					startActivity(login);
				}
			}
		});
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), RankingActivity.class);
				i.putExtra("key", map.get("title").toString());
				startActivity(i);
			}
		});
		
		linear4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (isParticipated) {
					i.setClass(getApplicationContext(), ResultViewActivity.class);
					i.putExtra("key", new Gson().toJson(participated));
					startActivity(i);
				}
				else {
					dialog.setTitle("Not Participated");
					dialog.setMessage("You didn't participated this exam");
					dialog.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					dialog.create().show();
				}
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (isParticipated) {
					i.setClass(getApplicationContext(), ResultViewActivity.class);
					i.putExtra("key", new Gson().toJson(questions));
					startActivity(i);
				}
				else {
					dialog.setTitle("Not Participated");
					dialog.setMessage("You didn't participated this exam");
					dialog.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					dialog.create().show();
				}
			}
		});
		
		fab1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (video.isLoaded() && !isGuest) {
					video.show();
				}
				else {
					if (!isGuest) {
						SketchwareUtil.showMessage(getApplicationContext(), "video ads is not ready yet !");
					}
					else {
						login.setClass(getApplicationContext(), AuthenticationActivity.class);
						startActivity(login);
					}
				}
			}
		});
		
		fab2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!isGuest) {
					read.setClass(getApplicationContext(), NextActivity.class);
					read.putExtra("key", "প্রিলি প্রস্তুতি");
					startActivity(read);
				}
				else {
					login.setClass(getApplicationContext(), AuthenticationActivity.class);
					startActivity(login);
				}
			}
		});
		
		fab3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!isGuest) {
					share = "Daily BCS Aid App is the best learning platform for job  preparation ever. I'm using it daily. You can join now with my referral code to prepare for - ".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
					
					Intent i = new Intent(android.content.Intent.ACTION_SEND); i.setType("text/plain"); i.putExtra(android.content.Intent.EXTRA_TEXT, share); startActivity(Intent.createChooser(i,"Send using"));
				}
				else {
					login.setClass(getApplicationContext(), AuthenticationActivity.class);
					startActivity(login);
				}
			}
		});
		
		fab4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!isGuest) {
					intent.setClass(getApplicationContext(), WriteActivity.class);
					startActivity(intent);
					finish();
				}
				else {
					login.setClass(getApplicationContext(), AuthenticationActivity.class);
					startActivity(login);
				}
			}
		});
		
		video_listener = new RewardedVideoAdListener(){
			@Override
			public void onRewarded(RewardItem rewardItem){
				final int _rewardItem = rewardItem.getAmount();
				SketchwareUtil.showMessage(getApplicationContext(), String.valueOf((long)(_rewardItem)));
			}
			
			@Override
			public void onRewardedVideoAdLoaded() {
				
			}
			
			@Override
			public void onRewardedVideoAdFailedToLoad(int errorCode) {
				final int _errorCode = errorCode;
				
			}
			
			@Override
			public void onRewardedVideoAdOpened() {
				
			}
			
			@Override
			public void onRewardedVideoAdClosed() {
				
			}
			@Override
			public void onRewardedVideoAdLeftApplication() {
			}
			
			@Override
			public void onRewardedVideoStarted() {
			}
			
			@Override
				public void onRewardedVideoCompleted() {
			}
		};
		
		_user_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					points = Double.parseDouble(_childValue.get("points").toString());
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					points = Double.parseDouble(_childValue.get("points").toString());
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
		join.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_enter,0,0,0);
		fab1.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#FFEEF7FF")));
		
		fab2.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#FFEEF7FF")));
		
		fab3.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#FFEEF7FF")));
		
		fab4.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#FFEEF7FF")));
		
		
		_RoundAndBorder(linear7, "#FFFFFF", 2, "#E0E0E0", 30);
		_setBackground(button1, 5, 0, "#2196F3", true);
		_setBackground(linear5, 15, 0, "#FFFFFF", true);
		_setBackground(linear4, 15, 0, "#FFFFFF", true);
		_setBackground(linear6, 15, 0, "#FFFFFF", true);
		_setBackground(join, 15, 5, "#2196F3", true);
		map = new Gson().fromJson(getIntent().getStringExtra("key"), new TypeToken<HashMap<String, Object>>(){}.getType());
		textview8.setText(map.get("title").toString());
		textview1.setText(map.get("syllabus").toString());
		textview2.setText(map.get("participants").toString());
		questions = new Gson().fromJson(map.get("questions").toString().substring((int)(1), (int)(map.get("questions").toString().length() - 1)), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		textview3.setText(String.valueOf((long)(questions.size())));
		textview4.setText("00:".concat(String.valueOf((long)(questions.size()))));
		isGuest = Database.getString("isGuest", "").equals("true");
		costPoints = 50;
		if (questions.size() == 25) {
			costPoints = 30;
		}
		else {
			if (questions.size() == 75) {
				costPoints = 100;
			}
			else {
				if (questions.size() == 100) {
					costPoints = 150;
				}
			}
		}
		MobileAds.initialize(this, "ca-app-pub-1909912566027899~9734100819");
		video.loadAd("ca-app-pub-1909912566027899/1316554749", new com.google.android.gms.ads.AdRequest.Builder().build());
		if (isGuest) {
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
	public void onResume() {
		super.onResume();
		video.resume();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		video.pause();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		video.destroy();
	}
	public void _shapeRadius (final View _v, final String _color, final double _radius) {
		android.graphics.drawable.GradientDrawable shape = new android.graphics.drawable.GradientDrawable();
		  shape.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
		
		shape.setCornerRadius((int)_radius);
		
		shape.setColor(Color.parseColor(_color));
		_v.setBackgroundDrawable(shape);
	}
	
	
	public void _RoundAndBorder (final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}
	
	
	public void _setBackground (final View _view, final double _radius, final double _shadow, final String _color, final boolean _ripple) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setElevation((int)_shadow);
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9e9e9e")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		}
		else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setBackground(gd);
			_view.setElevation((int)_shadow);
		}
	}
	
	
	public boolean _ListMapContainsValue (final ArrayList<HashMap<String, Object>> _listmap, final double _n, final String _value) {
		return _listmap.get((int)_n).containsValue(_value);
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