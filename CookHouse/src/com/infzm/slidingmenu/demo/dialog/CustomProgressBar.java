package com.infzm.slidingmenu.demo.dialog;

import java.io.Closeable;

import com.infzm.slidingmenu.demo.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class CustomProgressBar extends Dialog {

	public CustomProgressBar(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	public CustomProgressBar(Context context, int theme) {
		super(context, theme);
	}

	public CustomProgressBar(Context context) {
		super(context);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog);
	}
	public static CustomProgressBar show(Context ctx){
		CustomProgressBar d = new CustomProgressBar(ctx);  
        d.show();
		return d;
	}
	public static void Closeable(Context ctx){
		CustomProgressBar d = new CustomProgressBar(ctx);  
		d.dismiss();
	}

}
