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
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import android.widget.ImageView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.bumptech.glide.Glide;
import android.graphics.Typeface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import android.widget.RatingBar;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class ViewArticleActivity extends  AppCompatActivity  { 
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private HashMap<String, Object> data = new HashMap<>();
	private String key = "";
	private double ErrorNativeCode = 0;
	private String ADMOB_AD_UNIT_ID = "";
	private  UnifiedNativeAd nativeAd;
	
	private ScrollView vscroll1;
	private LinearLayout linear4;
	private TextView Title_txt;
	private CardView cardview1;
	private LinearLayout linear1;
	private TextView article_txt;
	private LinearLayout native_layout;
	private TextView article_txt2;
	private TextView time_txt;
	private ImageView banner_img;
	private LinearLayout linear3;
	private LinearLayout linear2;
	private TextView textview1;
	private TextView textview2;
	private LinearLayout linear5;
	private TextView textview4;
	private TextView textview3;
	
	private InterstitialAd ad;
	private AdListener _ad_ad_listener;
	private Intent i = new Intent();
	private DatabaseReference User = _firebase.getReference("user");
	private ChildEventListener _User_child_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view_article);
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
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		Title_txt = (TextView) findViewById(R.id.Title_txt);
		cardview1 = (CardView) findViewById(R.id.cardview1);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		article_txt = (TextView) findViewById(R.id.article_txt);
		native_layout = (LinearLayout) findViewById(R.id.native_layout);
		article_txt2 = (TextView) findViewById(R.id.article_txt2);
		time_txt = (TextView) findViewById(R.id.time_txt);
		banner_img = (ImageView) findViewById(R.id.banner_img);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		textview1 = (TextView) findViewById(R.id.textview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		textview4 = (TextView) findViewById(R.id.textview4);
		textview3 = (TextView) findViewById(R.id.textview3);
		
		banner_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), ViewActivity.class);
				i.putExtra("url", data.get("img").toString());
				startActivity(i);
			}
		});
		
		_User_child_listener = new ChildEventListener() {
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
		User.addChildEventListener(_User_child_listener);
		
		_ad_ad_listener = new AdListener() {
			@Override
			public void onAdLoaded() {
				ad.show();
			}
			
			@Override
			public void onAdFailedToLoad(int _param1) {
				final int _errorCode = _param1;
				
			}
			
			@Override
			public void onAdOpened() {
				
			}
			
			@Override
			public void onAdClosed() {
				
			}
		};
	}
	
	private void initializeLogic() {
		com.google.android.material.appbar.AppBarLayout appBarLayout = (com.google.android.material.appbar.AppBarLayout) _toolbar.getParent();
		appBarLayout.setStateListAnimator(null);
		data = new Gson().fromJson(getIntent().getStringExtra("key"), new TypeToken<HashMap<String, Object>>(){}.getType());
		Title_txt.setText(data.get("title").toString());
		article_txt.setText(data.get("long").toString().substring((int)(0), (int)(data.get("long").toString().length() / 3)));
		article_txt2.setText(data.get("long").toString().substring((int)(data.get("long").toString().length() / 3), (int)(data.get("long").toString().length())));
		time_txt.setText(_millisToDate(Double.parseDouble(data.get("time").toString())));
		Glide.with(getApplicationContext()).load(Uri.parse(data.get("img").toString())).into(banner_img);
		cardview1.setCardBackgroundColor(0xFFFFFFFF);
		cardview1.setRadius((float)15);
		cardview1.setCardElevation((float)0);
		Title_txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/manrope_bold.ttf"), 0);
		article_txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		article_txt2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		time_txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 1);
		
		key = data.get("key").toString();
		textview1.setText(data.get("author").toString().substring((int)(0), (int)(1)).toUpperCase());
		textview2.setText(data.get("author").toString());
		textview3.setText(data.get("category").toString());
		textview4.setText(_millisToDate(Double.parseDouble(data.get("time").toString())));
		_shapeRadius(linear3, "#2196F3", 90);
		_createNativeAd();
		ad = new InterstitialAd(getApplicationContext());
		ad.setAdListener(_ad_ad_listener);
		ad.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
		ad.loadAd(new AdRequest.Builder().build());
		if (getIntent().hasExtra("extra") && getIntent().getStringExtra("extra").equals("earn")) {
			//After Timer Rewared Him
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
		finish();
	}
	public void _StatusBarTrans () {
		if (Build.VERSION.SDK_INT >= 23) {
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		}
		else {
			if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
				Window w =ViewArticleActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF9E9E9E);
			}
		}
	}
	
	
	public void _shapeRadius (final View _v, final String _color, final double _radius) {
		android.graphics.drawable.GradientDrawable shape = new android.graphics.drawable.GradientDrawable();
		  shape.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
		
		shape.setCornerRadius((int)_radius);
		
		shape.setColor(Color.parseColor(_color));
		_v.setBackgroundDrawable(shape);
	}
	
	
	public String _millisToDate (final double _millis) {
		return DateFormat.getDateInstance(DateFormat.LONG).format(_millis);
		    //You can use DateFormat.LONG instead of SHORT
		
	}
	
	
	public void _reloadNativeAd () {
		AdLoader.Builder builder = new AdLoader.Builder(this, ADMOB_AD_UNIT_ID);
		builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
			
			@Override
			public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
				
				if (nativeAd != null) {
					nativeAd.destroy();
				}
				nativeAd = unifiedNativeAd;
				UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater()
				.inflate(R.layout.ad_unified, null);
				populateUnifiedNativeAdView(unifiedNativeAd, adView);
				native_layout.removeAllViews();
				native_layout.addView(adView);
			}});
		
		AdLoader adLoader = builder.withAdListener(new AdListener() {
			@Override
			public void onAdFailedToLoad(int errorCode) {
				ErrorNativeCode = errorCode;
				SketchwareUtil.showMessage(getApplicationContext(), String.valueOf((long)(ErrorNativeCode)));
			}}).build();
		adLoader.loadAd(new AdRequest.Builder().build());
	}
	
	
	public void _createNativeAd () {
		MobileAds.initialize(this, new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(InitializationStatus initializationStatus) {
			}});
		ADMOB_AD_UNIT_ID = "ca-app-pub-1909912566027899/1349162571";
		_reloadNativeAd();
	}
	
	
	public void _nativeAdUI () {
	}
	private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
		// Set the media view.
		adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));
		// Set other ad assets.
		ImageView close = adView.findViewById(R.id.close);
		LinearLayout linear8 = adView.findViewById(R.id.linear8);
		LinearLayout linear3 = adView.findViewById(R.id.linear3);
		LinearLayout linear9 = adView.findViewById(R.id.linear9);
		LinearLayout top = adView.findViewById(R.id.linear7);
		LinearLayout top2 = adView.findViewById(R.id.linear3);
		Button ad_call_to_action = adView.findViewById(R.id.ad_call_to_action);
		adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
		adView.setBodyView(adView.findViewById(R.id.ad_body));
		adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
		adView.setIconView(adView.findViewById(R.id.ad_app_icon));
		adView.setPriceView(adView.findViewById(R.id.ad_price));
		adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
		adView.setStoreView(adView.findViewById(R.id.ad_store));
		adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
		
		// The headline and mediaContent are guaranteed to be in every UnifiedNativeAd.
		
		((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
		
		
		// These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
		
		// check before trying to display them.
		
		if (nativeAd.getBody() == null) {
			
			adView.getBodyView().setVisibility(View.INVISIBLE);
		} else {
			adView.getBodyView().setVisibility(View.VISIBLE);
			((TextView) adView.getBodyView()).setText(nativeAd.getBody());
		}
		if (nativeAd.getCallToAction() == null) {
			adView.getCallToActionView().setVisibility(View.INVISIBLE);
		} else {
			adView.getCallToActionView().setVisibility(View.VISIBLE);
			((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
		}
		if (nativeAd.getIcon() == null) {
			adView.getIconView().setVisibility(View.GONE);
		} else {
			((ImageView) adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
			adView.getIconView().setVisibility(View.VISIBLE);
		}
		if (nativeAd.getPrice() == null) {
			adView.getPriceView().setVisibility(View.INVISIBLE);
		} else {
			adView.getPriceView().setVisibility(View.VISIBLE);
			((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
		}
		if (nativeAd.getStore() == null) {
			adView.getStoreView().setVisibility(View.INVISIBLE);
		} else {
			adView.getStoreView().setVisibility(View.VISIBLE);
			((TextView) adView.getStoreView()).setText(nativeAd.getStore());
		}
		if (nativeAd.getAdvertiser() == null) {
			adView.getAdvertiserView().setVisibility(View.INVISIBLE);
		} else {
			((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
			adView.getAdvertiserView().setVisibility(View.VISIBLE);
		}
		// This method tells the Google Mobile Ads SDK that you have finished populating your
		
		// native ad view with this native ad.
		
		adView.setNativeAd(nativeAd);
		
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFFFFFFFF);
			SketchUi.setCornerRadius(d*13);
			top.setElevation(d*8);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
			top.setBackground(SketchUiRD);
			top.setClickable(true);
		}
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFFFFFFFF);
			SketchUi.setCornerRadius(d*13);
			top.setElevation(d*8);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
			top2.setBackground(SketchUiRD);
			top2.setClickable(true);
		}
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFF304FFE);
			SketchUi.setCornerRadius(d*13);
			ad_call_to_action.setElevation(d*8);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
			ad_call_to_action.setBackground(SketchUiRD);
			ad_call_to_action.setClickable(true);
		}
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFFFFFFFF);
			SketchUi.setCornerRadius(d*6);
			linear9.setElevation(d*10);
			linear9.setBackground(SketchUi);
		}
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFFFFFFFF);
			SketchUi.setCornerRadius(d*9);
			linear3.setElevation(d*12);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
			linear3.setBackground(SketchUiRD);
			linear3.setClickable(true);
		}
		linear8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				native_layout.setVisibility(View.GONE);
				SketchwareUtil.showMessage(getApplicationContext(), "ad closed");
			}
		});
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				native_layout.setVisibility(View.GONE);
				SketchwareUtil.showMessage(getApplicationContext(), "ad closed");
			}
		});
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