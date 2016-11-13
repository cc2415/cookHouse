package com.infzm.slidingmenu.demo.dialog;

import com.infzm.slidingmenu.demo.R;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

public class ShowMyDialog {
	static AlertDialog alertDialog=null;
	
	public static void ShowMyDialog(Context mContext){
		AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
		View view = LayoutInflater.from(mContext).inflate(R.layout.dialog, null);
//		ProgressBar progressBar=new ProgressBar(mContext);
		builder.setView(view);
		builder.setCancelable(true);
		alertDialog= builder.create();
		alertDialog.show();
	}
	public static void closeDialog(){
		alertDialog.dismiss();
	}
}
