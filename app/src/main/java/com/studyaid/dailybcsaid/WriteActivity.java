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
import android.widget.ScrollView;
import android.widget.LinearLayout;
import com.google.android.material.button.*;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.OnProgressListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Continuation;
import android.net.Uri;
import java.io.File;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.content.Intent;
import android.content.ClipData;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.SharedPreferences;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class WriteActivity extends  AppCompatActivity  { 
	
	public final int REQ_CD_FP = 101;
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private HashMap<String, Object> map = new HashMap<>();
	private String thumbnailPath = "";
	private String push_key = "";
	private String thumbnailName = "";
	private double Position = 0;
	
	private ArrayList<HashMap<String, Object>> temp = new ArrayList<>();
	private ArrayList<String> category = new ArrayList<>();
	private ArrayList<String> tes = new ArrayList<>();
	private ArrayList<String> tmp = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout l_title;
	private LinearLayout l_category;
	private LinearLayout l_thumb;
	private LinearLayout l_content;
	private MaterialButton materialbutton2;
	private EditText txt_title;
	private TextView textview4;
	private TextView tx_category;
	private ImageView im_thumbnail;
	private TextView textview1;
	private EditText txt_long;
	
	private DatabaseReference Post = _firebase.getReference("post");
	private ChildEventListener _Post_child_listener;
	private StorageReference image = _firebase_storage.getReference("image");
	private OnCompleteListener<Uri> _image_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _image_download_success_listener;
	private OnSuccessListener _image_delete_success_listener;
	private OnProgressListener _image_upload_progress_listener;
	private OnProgressListener _image_download_progress_listener;
	private OnFailureListener _image_failure_listener;
	private Calendar c = Calendar.getInstance();
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private AlertDialog.Builder dd;
	private DatabaseReference categories = _firebase.getReference("categories");
	private ChildEventListener _categories_child_listener;
	private SharedPreferences Database;
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
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.write);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
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
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		l_title = (LinearLayout) findViewById(R.id.l_title);
		l_category = (LinearLayout) findViewById(R.id.l_category);
		l_thumb = (LinearLayout) findViewById(R.id.l_thumb);
		l_content = (LinearLayout) findViewById(R.id.l_content);
		materialbutton2 = (MaterialButton) findViewById(R.id.materialbutton2);
		txt_title = (EditText) findViewById(R.id.txt_title);
		textview4 = (TextView) findViewById(R.id.textview4);
		tx_category = (TextView) findViewById(R.id.tx_category);
		im_thumbnail = (ImageView) findViewById(R.id.im_thumbnail);
		textview1 = (TextView) findViewById(R.id.textview1);
		txt_long = (EditText) findViewById(R.id.txt_long);
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		dd = new AlertDialog.Builder(this);
		Database = getSharedPreferences("data", Activity.MODE_PRIVATE);
		auth = FirebaseAuth.getInstance();
		
		l_category.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dd.setTitle("Choose category");
				_Add(dd, category);
				dd.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						tx_category.setText(category.get((int)(Position)));
					}
				});
				dd.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dd.create().show();
			}
		});
		
		l_thumb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
		
		materialbutton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_uploadAllStuff();
			}
		});
		
		_Post_child_listener = new ChildEventListener() {
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
		Post.addChildEventListener(_Post_child_listener);
		
		_image_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				_Prog_Dialogue_show(true, "", "Uploading Image...".concat(String.valueOf((long)(_progressValue)).concat("%")));
			}
		};
		
		_image_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_image_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				c = Calendar.getInstance();
				push_key = Post.push().getKey();
				map = new HashMap<>();
				map.put("title", txt_title.getText().toString().trim());
				map.put("img", _downloadUrl);
				map.put("long", txt_long.getText().toString());
				map.put("category", tx_category.getText().toString());
				map.put("key", push_key);
				map.put("time", String.valueOf((long)(c.getTimeInMillis())));
				map.put("status", "pending");
				map.put("author", Database.getString("name", ""));
				if (Database.getString("isGuest", "").equals("true")) {
					map.put("uid", String.valueOf((long)(SketchwareUtil.getRandom((int)(99999), (int)(999999999)))));
				}
				else {
					map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				}
				Post.child(push_key).updateChildren(map);
				map.clear();
				_Prog_Dialogue_show(false, "", "");
				SketchwareUtil.showMessage(getApplicationContext(), "Posted!");
				finish();
			}
		};
		
		_image_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_image_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_image_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_categories_child_listener = new ChildEventListener() {
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
		categories.addChildEventListener(_categories_child_listener);
		
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
		_shapeRadius(l_title, "#FFFFFF", 10);
		_shapeRadius(l_category, "#FFFFFF", 10);
		_shapeRadius(l_thumb, "#FFFFFF", 10);
		_shapeRadius(l_content, "#FFFFFF", 10);
		categories.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				temp = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						temp.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				/*for(HashMap<String,Object> hmap: temp){
for(Object str:hmap.keySet()){

if (hmap.get(str) instanceof HashMap) {
//list.add(hmap);
//SketchwareUtil.showMessage(getApplicationContext(),"true");
SketchwareUtil.getAllKeysFromMap((HashMap<String, Object>)hmap.get(str), tes);
category.addAll(tes);
//category.add(((HashMap<String, Object>) hmap.get(str)).get("name").toString());
for(Object str:hmap.get(str).keySet()){

if (hmap.get(str) instanceof HashMap) {
//list.add(hmap);
//SketchwareUtil.showMessage(getApplicationContext(),"true");
SketchwareUtil.getAllKeysFromMap((HashMap<String, Object>)hmap.get(str), tes);
category.addAll(tes);
//category.add(((HashMap<String, Object>) hmap.get(str)).get("name").toString());
}
}

}
}

}
*/
				for(HashMap<String,Object> hmap: temp){
						for(Object str:hmap.keySet()){
								
								if (hmap.get(str) instanceof HashMap) {
										//list.add(hmap);
										//SketchwareUtil.showMessage(getApplicationContext(),"true");
										//SketchwareUtil.getAllKeysFromMap((HashMap<String, Object>)hmap.get(str), tes);
										//category.addAll(tes);
										//category.add(((HashMap<String, Object>) hmap.get(str)).get("name").toString());
										for(Object str1:((HashMap<String, Object>) hmap.get(str)).keySet()){
												
												if ((HashMap<String, Object>)((HashMap<String, Object>)hmap.get(str)).get(str1) instanceof HashMap) {
														//list.add(hmap);
														//SketchwareUtil.showMessage(getApplicationContext(),"true");
														
														//SketchwareUtil.getAllKeysFromMap((HashMap<String, Object>)hmap.get(str), tes);
														//category.addAll(tes);
														if(((HashMap<String, Object>)((HashMap<String, Object>) hmap.get(str)).get(str1)).containsKey("type")){
																
																if(((HashMap<String, Object>)((HashMap<String, Object>) hmap.get(str)).get(str1)).get("type").toString().equals("article")){
																		category.add(((HashMap<String, Object>)((HashMap<String, Object>) hmap.get(str)).get(str1)).get("name").toString());
																}
																if(((HashMap<String, Object>)((HashMap<String, Object>) hmap.get(str)).get(str1)).containsKey("sub")){
																		getAllValuesFromMap((HashMap<String, Object>)((HashMap<String, Object>)((HashMap<String, Object>) hmap.get(str)).get(str1)).get("sub"), tmp);
																		category.addAll(tmp);
																		tmp.clear();
																	
																	
																	
																}
															
														}
														
												}
												
										}
										
								}
						}
				}
				
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		
		
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				thumbnailPath = _filePath.get((int)(0));
				thumbnailName = Uri.parse(thumbnailPath).getLastPathSegment();
				im_thumbnail.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(thumbnailPath, 1024, 1024));
				textview1.setVisibility(View.GONE);
			}
			else {
				
			}
			break;
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
	
	
	public void _Prog_Dialogue_show (final boolean _ifShow, final String _title, final String _message) {
		if (_ifShow) {
			if (prog == null){
				prog = new ProgressDialog(this);
				prog.setMax(100);
				prog.setIndeterminate(true);
				prog.setCancelable(false);
				prog.setCanceledOnTouchOutside(false);
			}
			prog.setMessage(_message);
			prog.show();
		}
		else {
			if (prog != null){
				prog.dismiss();
			}
		}
	}
	private ProgressDialog prog;
	{
	}
	
	
	public void _Add (final AlertDialog.Builder _Dialog, final ArrayList<String> _ListString) {
		final CharSequence[] _items = _ListString.toArray(new String[_ListString.size()]);
		_Dialog.setSingleChoiceItems(_items, (int)Position, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Position = which;
			}});
	}
	
	
	public void _uploadAllStuff () {
		if (txt_title.getText().toString().replace(" ", "").equals("")) {
			SketchwareUtil.showMessage(getApplicationContext(), "Please type a title");
		}
		else {
			if (thumbnailPath.equals("")) {
				SketchwareUtil.showMessage(getApplicationContext(), "Please choose a thumbnail");
			}
			else {
				image.child(String.valueOf((long)(c.getTimeInMillis())).concat(".jpg")).putFile(Uri.fromFile(new File(thumbnailPath))).addOnFailureListener(_image_failure_listener).addOnProgressListener(_image_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return image.child(String.valueOf((long)(c.getTimeInMillis())).concat(".jpg")).getDownloadUrl();
					}}).addOnCompleteListener(_image_upload_success_listener);
			}
		}
	}
	
	
	public void _getAllValuesFromMap () {
	} public static void getAllValuesFromMap(Map<String, Object> map, ArrayList<String> output) {
						if (output == null) return;
						output.clear();
		
						if (map == null || map.size() <= 0) return;
		
						Iterator itr = map.entrySet().iterator();
						while (itr.hasNext()) {
									Map.Entry<String, String> entry = (Map.Entry) itr.next();
									output.add(entry.getValue());
						}
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