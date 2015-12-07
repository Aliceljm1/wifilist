package com.example.wifiexample;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Switch s;
	WifiManager wm;
	TextView t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		wm=(WifiManager)getSystemService(Context.WIFI_SERVICE);
		s=(Switch)findViewById(R.id.switch1);
		t=(TextView)findViewById(R.id.textView1);
		s.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(s.isChecked())
				{
					wm.setWifiEnabled(true);
					getwifi();
					Toast.makeText(MainActivity.this, "WIFI is ON", 3000).show();
				}
				else{
					wm.setWifiEnabled(false);
					getwifi();
					Toast.makeText(MainActivity.this, "WIFI is OFF", 3000).show();
				}
			}
		});
		getwifi();
		
	}
	public void getwifi()
	{
		List<ScanResult> results = wm.getScanResults();  
        String otherwifi = "The existing network is: \n";  
        Collections.sort(results, new Comparator<ScanResult>() {
            public int compare(ScanResult arg0, ScanResult arg1) {
                return arg0.SSID.compareTo(arg1.SSID);
            }
        });
        for (ScanResult result : results) {    
            otherwifi += result.SSID  + ":" + result.level + "\n";  
        }  
		
//		if(wm.isWifiEnabled()){
//			s.setChecked(true);
//			Toast.makeText(this, "WIFI is ON", 3000).show();
//			WifiInfo w = wm.getConnectionInfo();
//			t.setText("BSSID : " + w.getBSSID() + "\n");
//			t.append("RSSID : " + w.getRssi() + "\n");
//			t.append("SIGNAL : " + w.getLinkSpeed() + "\n");
//			t.append("SSID : " + w.getSSID() + "\n");
//		}
//		else
//		{
//			t.setText("Not Connected");
//		}
        
       t.setText(otherwifi);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
