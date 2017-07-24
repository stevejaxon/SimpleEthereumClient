package tech.stevejaxon.simpleethereumclient;

import android.Manifest;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.ethereum.geth.*;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "tech.stevejaxon.simpleethereumclient.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        setTitle("Android In-Process Node");
        final TextView textbox = (TextView) findViewById(R.id.consoleOutput);

        Context ctx = new Context();

        textbox.append("HELLO WORLD");
        textbox.append(ctx.toString());
        try {
            Node node = Geth.newNode(getExternalFilesDir(null) + "/.ethereum", new NodeConfig());
            node.start();

            /*NodeInfo info = node.getNodeInfo();
            textbox.append("My name: " + info.getName() + "\n");
            textbox.append("My address: " + info.getListenerAddress() + "\n");
            textbox.append("My protocols: " + info.getProtocols() + "\n\n");*/

            /*EthereumClient ec = node.getEthereumClient();
            textbox.append("Latest block: " + ec.getBlockByNumber(ctx, -1).getNumber() + ", syncing...\n");

            NewHeadHandler handler = new NewHeadHandler() {
                @Override public void onError(String error) { }
                @Override public void onNewHead(final Header header) {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() { textbox.append("#" + header.getNumber() + ": " + header.getHash().getHex().substring(0, 10) + "…\n"); }
                    });
                }
            };
            ec.subscribeNewHead(ctx, handler,  16);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
