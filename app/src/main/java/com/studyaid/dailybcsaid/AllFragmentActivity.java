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
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import com.google.gson.Gson;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class AllFragmentActivity extends  Fragment  { 
	
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
	private ListView listview1;
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.all_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		com.google.firebase.FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		
		listview1 = (ListView) _view.findViewById(R.id.listview1);
	}
	
	private void initializeLogic() {
		Bundle bundle = getArguments();
		list = (ArrayList<HashMap<String,Object>>)bundle.getSerializable("all");
		listview1.setAdapter(new Listview1Adapter(list));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		/*
*/
		
	}
	
	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
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
				_view = _inflater.inflate(R.layout.cust, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final TextView textView1 = (TextView) _view.findViewById(R.id.textView1);
			final RadioGroup group_me = (RadioGroup) _view.findViewById(R.id.group_me);
			final Button button1 = (Button) _view.findViewById(R.id.button1);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			final RadioButton answer0 = (RadioButton) _view.findViewById(R.id.answer0);
			final RadioButton answer1 = (RadioButton) _view.findViewById(R.id.answer1);
			final RadioButton answer2 = (RadioButton) _view.findViewById(R.id.answer2);
			final RadioButton answer3 = (RadioButton) _view.findViewById(R.id.answer3);
			
			textView1.setText(_data.get((int)_position).get("ques").toString());
			answer0.setText(_data.get((int)_position).get("a").toString());
			answer1.setText(_data.get((int)_position).get("b").toString());
			answer2.setText(_data.get((int)_position).get("c").toString());
			answer3.setText(_data.get((int)_position).get("d").toString());
			textview1.setText(_data.get((int)_position).get("def").toString());
			_setBackground(linear1, 5, 5, "#FFFFFF", false);
			((RadioButton)group_me.getChildAt((int)Double.parseDouble(_data.get((int)_position).get("ans").toString()))).setChecked(true);
			button1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					textview1.setVisibility(View.VISIBLE);
				}
			});
			answer0.setEnabled(false);
			answer1.setEnabled(false);
			answer2.setEnabled(false);
			answer3.setEnabled(false);
			
			return _view;
		}
	}
	
	
}