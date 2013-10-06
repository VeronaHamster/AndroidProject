package com.project;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageView imgVw = (ImageView) findViewById(R.id.imageView1);
		TextView txtVw = (TextView) findViewById(R.id.txtContent);
		AssetManager assetManager = getAssets();
		InputStream input;

		try {
			input = assetManager.open("project_text.txt");
			int size = input.available();
			byte[] buff = new byte[size];
			input.read(buff);
			input.close();
			String text = new String(buff);
			txtVw.setText(text);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Bitmap bm = getBitmapFromAsset();
			imgVw = (ImageView) findViewById(R.id.imageView1);
			imgVw.setImageBitmap(bm);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private Bitmap getBitmapFromAsset() throws IOException {
		AssetManager assetManager = getAssets();
		String [] fileNames = assetManager.list("");
		for (String fileName : fileNames) {
			if (fileName.startsWith("project_image")) {
				InputStream istr = assetManager.open(fileName);
				Bitmap bitmap = BitmapFactory.decodeStream(istr);
					return bitmap;
			}
		}
		return null;
	}

}
