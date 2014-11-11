package cychiang.wifi.wifihotspot;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class main extends Activity {
    TextView textView1;
    WiFiAPManager wifiApManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        textView1 = (TextView) findViewById(R.id.textView1);
        wifiApManager = new WiFiAPManager(this);

        scan();
    }

    private void scan() {
        wifiApManager.getClientList(false, new FinishScanListener() {
            @Override
            public void onFinishScan(final ArrayList<ClientScanResult> clients) {

                textView1.setText("WifiApState: " + wifiApManager.getWifiApState() + "\n\n");
                textView1.append("Clients: \n");
                for (ClientScanResult clientScanResult : clients) {
                    textView1.append("####################\n");
                    textView1.append("IpAddr: " + clientScanResult.getIpAddr() + "\n");
                    textView1.append("Device: " + clientScanResult.getDevice() + "\n");
                    textView1.append("HWAddr: " + clientScanResult.getHWAddr() + "\n");
                    textView1.append("isReachable: " + clientScanResult.isReachable() + "\n");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(0, 0, 0, "Get Clients");
        menu.add(0, 1, 0, "Open AP");
        menu.add(0, 2, 0, "Close AP");
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

    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                scan();
                break;
            case 1:
                wifiApManager.setWifiApEnabled(null, true);
                break;
            case 2:
                wifiApManager.setWifiApEnabled(null, false);
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
