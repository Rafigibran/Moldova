package com.spacevise.moldova;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.*;
import com.unity3d.ads.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.regex.*;
import okhttp3.*;
import org.json.*;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.IUnityAdsListener;

public class MainActivity extends AppCompatActivity {
	
	public final int REQ_CD_LOGIN = 101;
	
	private String prompt = "";
	private  DallE dallE;
	
	private  List<String> imgs = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ScrollView vscroll1;
	private LinearLayout linear4;
	private TextView textview1;
	private LinearLayout linear5;
	private LinearLayout linear3;
	private LinearLayout linear8;
	private LinearLayout linear13;
	private CardView cardview1;
	private CardView cardview2;
	private LinearLayout linear11;
	private ImageView imageview1;
	private LinearLayout linear12;
	private ImageView imageview2;
	private CardView cardview6;
	private CardView cardview7;
	private LinearLayout linear14;
	private ImageView imageview3;
	private LinearLayout linear15;
	private ImageView imageview4;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private Button button1;
	private TextView textview2;
	private EditText edittext1;
	
	private AlertDialog cd;
	private Calendar cal = Calendar.getInstance();
	private RequestNetwork rn;
	private RequestNetwork.RequestListener _rn_request_listener;
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	
	private GoogleSignInClient login;
	private AlertDialog.Builder d;
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
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
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		vscroll1 = findViewById(R.id.vscroll1);
		linear4 = findViewById(R.id.linear4);
		textview1 = findViewById(R.id.textview1);
		linear5 = findViewById(R.id.linear5);
		linear3 = findViewById(R.id.linear3);
		linear8 = findViewById(R.id.linear8);
		linear13 = findViewById(R.id.linear13);
		cardview1 = findViewById(R.id.cardview1);
		cardview2 = findViewById(R.id.cardview2);
		linear11 = findViewById(R.id.linear11);
		imageview1 = findViewById(R.id.imageview1);
		linear12 = findViewById(R.id.linear12);
		imageview2 = findViewById(R.id.imageview2);
		cardview6 = findViewById(R.id.cardview6);
		cardview7 = findViewById(R.id.cardview7);
		linear14 = findViewById(R.id.linear14);
		imageview3 = findViewById(R.id.imageview3);
		linear15 = findViewById(R.id.linear15);
		imageview4 = findViewById(R.id.imageview4);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		button1 = findViewById(R.id.button1);
		textview2 = findViewById(R.id.textview2);
		edittext1 = findViewById(R.id.edittext1);
		rn = new RequestNetwork(this);
		auth = FirebaseAuth.getInstance();
		d = new AlertDialog.Builder(this);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try{
					_zoomAt(0);
				}catch(Exception e){
					 
				}
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try{
					_zoomAt(1);
				}catch(Exception e){
					 
				}
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try{
					_zoomAt(2);
				}catch(Exception e){
					 
				}
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try{
					_zoomAt(3);
				}catch(Exception e){
					 
				}
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1.getText().toString().trim().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Add description first");
					return;
				}
				cd = new AlertDialog.Builder(MainActivity.this).create();
				LayoutInflater cdLI = getLayoutInflater();
				View cdCV = (View) cdLI.inflate(R.layout.loading, null);
				cd.setView(cdCV);
				cd.setCancelable(false);
				cd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				cd.show();
				dallE.generateImage(edittext1.getText().toString().trim());
				
				prompt = edittext1.getText().toString().trim();
				UnityAds.show(MainActivity.this, "Rewarded_Android");
			}
		});
		
		_rn_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
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
			public void onComplete(Task<AuthResult> task) {
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
			public void onComplete(Task<AuthResult> task) {
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
		UnityAds.initialize(this, "5290548", false, false);
		dallE = new DallE(this);
		
		try{
				Picasso.setSingletonInstance(new Picasso.Builder(this).build());
		}catch(Exception e){}
		
		_navigationBarColor("#353541");
		linear7.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)1, 0x33FFFFFF, 0xFF444653));
		button1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)1, 0x33FFFFFF, 0xFFFE2B54));
		cardview1.setCardBackgroundColor(Color.TRANSPARENT);
		cardview2.setCardBackgroundColor(Color.TRANSPARENT);
		cardview6.setCardBackgroundColor(Color.TRANSPARENT);
		cardview7.setCardBackgroundColor(Color.TRANSPARENT);
		
		dallE.setOnImageGeneratedListener(new DallE.OnImageGeneratedListener() {
				@Override
				public void onImageGenerated(List<String> imageUrls) {
						// Handle the list of image URLs here
						imgs = new ArrayList<>(imageUrls);
						// ((android.content.ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", new com.google.gson.Gson().toJson(imgs)));
						SketchwareUtil.showMessage(getApplicationContext(), "4 images generated");
						_displayTheme();
						try{
								cd.dismiss();
						}catch(Exception e){}
				}
				
				@Override
				public void onError() {
						// Handle the error here
						SketchwareUtil.showMessage(getApplicationContext(), "Error generating image");
						try{
								cd.dismiss();
						}catch(Exception e){}
				}
		});
	}
	
	public void _displayTheme() {
		imageview1.setImageResource(Color.TRANSPARENT);
		imageview2.setImageResource(Color.TRANSPARENT);
		imageview3.setImageResource(Color.TRANSPARENT);
		imageview4.setImageResource(Color.TRANSPARENT);
		
		Picasso.get().load(imgs.get(0)).into(imageview1);
		Picasso.get().load(imgs.get(1)).into(imageview2);
		Picasso.get().load(imgs.get(2)).into(imageview3);
		Picasso.get().load(imgs.get(3)).into(imageview4);
	}
	
	
	public void _zoomAt(final double _codee) {
		cd = new AlertDialog.Builder(MainActivity.this).create();
		LayoutInflater cdLI = getLayoutInflater();
		View cdCV = (View) cdLI.inflate(R.layout.mega_ui, null);
		cd.setView(cdCV);
		final CardView containercard = (CardView)
		cdCV.findViewById(R.id.containercard);
		final ImageView displayimg = (ImageView)
		cdCV.findViewById(R.id.displayimg);
		final TextView descriptiontext = (TextView)
		cdCV.findViewById(R.id.descriptiontext);
		final Button savebtn = (Button)
		cdCV.findViewById(R.id.savebtn);
		cd.setCancelable(true);
		cd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		containercard.setCardBackgroundColor(Color.TRANSPARENT);
		savebtn.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)1, 0x33FFFFFF, 0xFFFE2B54));
		descriptiontext.setText("AI menghasilkan gambar untuk deskripsi:\n\"".concat(prompt.concat("\".")));
		Picasso.get().load(imgs.get((int)_codee)).into(displayimg);
		descriptiontext.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View _view){
				 
			}
		});
		displayimg.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View _view){
				 
			}
		});
		savebtn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View _view){
				cal = Calendar.getInstance();
				_SAVE(displayimg, FileUtil.getPublicDir(Environment.DIRECTORY_DOWNLOADS).concat("/Moldova/Generated Image/").concat(new SimpleDateFormat("dd-yyyy-mm-ss-ms").format(cal.getTime()).concat(".png")));
				UnityAds.show(MainActivity.this, "Interstitial_Android");
				SketchwareUtil.showMessage(getApplicationContext(), "Tersimpan di ".concat(FileUtil.getPublicDir(Environment.DIRECTORY_DOWNLOADS).concat("/Moldova/Generated Image/").concat(new SimpleDateFormat("dd-yyyy-mm-ss-ms").format(cal.getTime()).concat(".png"))));
			}
		});
		cd.show();
	}
	
	
	public void _SAVE(final View _view, final String _path) {
		FileUtil.writeFile(_path, "");
		Bitmap returnedBitmap = Bitmap.createBitmap(_view.getWidth(), _view.getHeight(),Bitmap.Config.ARGB_8888);
		
		Canvas canvas = new Canvas(returnedBitmap);
		android.graphics.drawable.Drawable bgDrawable =_view.getBackground();
		if (bgDrawable!=null) {
				bgDrawable.draw(canvas);
		} else {
				canvas.drawColor(Color.BLACK);
		}
		_view.draw(canvas);
		
		java.io.File pictureFile = new java.io.File(_path);
		if (pictureFile == null) {
				showMessage("Error creating media file, check storage permissions: ");
				return; }
		try {
				java.io.FileOutputStream fos = new java.io.FileOutputStream(pictureFile); returnedBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
				fos.close();
				showMessage("Image Saved");
		} catch (java.io.FileNotFoundException e) {
				showMessage("File not found: " + e.getMessage()); } catch (java.io.IOException e) {
				showMessage("Error accessing file: " + e.getMessage());
				
		}
		
	}
	
	
	public void _navigationBarColor(final String _color) {
		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { getWindow().setNavigationBarColor(Color.parseColor(_color)); }
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
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}