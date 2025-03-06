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
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import com.bumptech.glide.Glide;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;

public class HomeFragmentActivity extends  Fragment  { 
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private String res = "";
	private  int resId;
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
	private RecyclerView recyclerview1;
	
	private Intent i = new Intent();
	private DatabaseReference Categorias = _firebase.getReference("categories");
	private ChildEventListener _Categorias_child_listener;
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.home_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		com.google.firebase.FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		
		recyclerview1 = (RecyclerView) _view.findViewById(R.id.recyclerview1);
		
		_Categorias_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				list.add(_childValue);
				recyclerview1.setAdapter(new Recyclerview1Adapter(list));
				recyclerview1.setLayoutManager(new GridLayoutManager(getContext(), 2));
				//recyclerview1.addItemDecoration(new SpacesItemDecoration(6));
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
		Categorias.addChildEventListener(_Categorias_child_listener);
	}
	
	private void initializeLogic() {
		/*
_add();
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
	
	public void _add () {
		map = new HashMap<>();
		map.put("title", "Loram ipsum dolor sit amet Loram ");
		map.put("icon", "ic_study");
		list.add(map);
		map = new HashMap<>();
		map.put("title", "ipsum dolor sit amet Loram ipsum ");
		map.put("icon", "ic_study");
		list.add(map);
		map = new HashMap<>();
		map.put("title", "dolor sit amet Loram ipsum dolor sit ");
		map.put("icon", "ic_study");
		list.add(map);
		map = new HashMap<>();
		map.put("title", "amet Loram ipsum dolor sit amet");
		map.put("icon", "ic_study");
		list.add(map);
		map = new HashMap<>();
		map.put("title", "ipsum dolor sit amet Loram ipsum ");
		map.put("icon", "ic_study");
		list.add(map);
		map = new HashMap<>();
		map.put("title", "Loram ipsum dolor sit amet Loram ");
		map.put("icon", "ic_study");
		list.add(map);
	}
	
	
	public void _extra () {
	} public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
		
		    private int halfSpace;
		
		    public SpacesItemDecoration(int space) {
			        this.halfSpace = space / 2;
			    }
		
		    @Override
		    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
			
			        if (parent.getPaddingLeft() != halfSpace) {
				            parent.setPadding(halfSpace, halfSpace, halfSpace, halfSpace);
				            parent.setClipToPadding(false);
				        }
			
			        outRect.top = halfSpace;
			        outRect.bottom = halfSpace;
			        outRect.left = halfSpace;
			        outRect.right = halfSpace;
			    }
		
	}
	
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		ArrayList<HashMap<String, Object>> _data;
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _inflater.inflate(R.layout.item, null);
RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final androidx.cardview.widget.CardView cardview1 = (androidx.cardview.widget.CardView) _view.findViewById(R.id.cardview1);
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			
			Glide.with(getContext()).load(Uri.parse(_data.get((int)_position).get("img").toString())).into(imageview1);
			textview1.setText(_data.get((int)_position).get("name").toString());
			cardview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (_data.get((int)_position).get("name").toString().equals("কন্ট্রিবিউট করুন")) {
						i.setClass(getContext(), WriteActivity.class);
						startActivity(i);
					}
					else {
						if (_data.get((int)_position).get("name").toString().equals("Breaking News")) {
							i.setClass(getContext(), BreakingActivity.class);
							startActivity(i);
						}
						else {
							i.setClass(getContext(), NextActivity.class);
							i.putExtra("key", _data.get((int)_position).get("name").toString());
							startActivity(i);
						}
					}
				}
			});
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder{
			public ViewHolder(View v){
				super(v);
			}
		}
		
	}
	
	
}