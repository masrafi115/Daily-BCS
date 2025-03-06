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
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class ProfileFragmentActivity extends  Fragment  { 
	
	
	private HashMap<String, Object> map = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
	private LinearLayout linear1;
	private TextView textview1;
	private ListView listview1;
	private LinearLayout linear3;
	private LinearLayout linear2;
	private ImageView imageview1;
	private TextView textview2;
	private TextView textview3;
	
	private SharedPreferences examData;
	private Intent i = new Intent();
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
	private RewardedVideoAd video;
	private RewardedVideoAdListener  video_listener;
	private SharedPreferences Database;
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.profile_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		com.google.firebase.FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		
		linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
		textview1 = (TextView) _view.findViewById(R.id.textview1);
		listview1 = (ListView) _view.findViewById(R.id.listview1);
		linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
		linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
		imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
		textview2 = (TextView) _view.findViewById(R.id.textview2);
		textview3 = (TextView) _view.findViewById(R.id.textview3);
		examData = getContext().getSharedPreferences("examData", Activity.MODE_PRIVATE);
		auth = FirebaseAuth.getInstance();
		video = MobileAds.getRewardedVideoAdInstance(getContext());
		Database = getContext().getSharedPreferences("data", Activity.MODE_PRIVATE);
		
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
		_shapeRadius(linear3, "#3F67B2", 10);
		_RoundAndBorder(linear1, "#FFFFFF", 2, "#3F67B2", 10);
		if (!examData.getString("key", "").equals("")) {
			list = new Gson().fromJson(examData.getString("key", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			listview1.setAdapter(new Listview1Adapter(list));
			((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		}
		/*
_data();
*/
		if (!Database.getString("isGuest", "").equals("true")) {
			textview2.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
			textview3.setText("UID ".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()));
		}
		MobileAds.initialize(getContext(), "ca-app-pub-1909912566027899~9734100819");
		video.loadAd("ca-app-pub-1909912566027899/1316554749", new com.google.android.gms.ads.AdRequest.Builder().build());
	}
	
	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
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
	
	
	public String _DateTimeFormat (final double _millisecond, final String _format) {
		return new SimpleDateFormat(_format).format(_millisecond);
	}
	
	
	public String _millisToDate (final double _millis) {
		return DateFormat.getDateInstance(DateFormat.SHORT).format(_millis);
		    //You can use DateFormat.LONG instead of SHORT
		
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
				_view = _inflater.inflate(R.layout.participated, null);
			}
			
			final androidx.cardview.widget.CardView cardview1 = (androidx.cardview.widget.CardView) _view.findViewById(R.id.cardview1);
			final LinearLayout linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final LinearLayout linear4 = (LinearLayout) _view.findViewById(R.id.linear4);
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final ImageView imageview4 = (ImageView) _view.findViewById(R.id.imageview4);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			final LinearLayout linear5 = (LinearLayout) _view.findViewById(R.id.linear5);
			final ImageView imageview3 = (ImageView) _view.findViewById(R.id.imageview3);
			final TextView textview4 = (TextView) _view.findViewById(R.id.textview4);
			
			textview1.setText(list.get((int)_position).get("title").toString());
			textview4.setText(_millisToDate(Double.parseDouble(list.get((int)_position).get("date").toString())));
			cardview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setClass(getContext(), ResultViewActivity.class);
					i.putExtra("data", new Gson().toJson(list.get((int)(_position))));
					i.putExtra("key", "offline");
					startActivity(i);
				}
			});
			
			return _view;
		}
	}
	
	
}