package com.mmc.sampletest.jsTest;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.mmc.sampletest.R;

public class JsTestActivity extends AppCompatActivity {

    private static final String TAG = "JsTestActivity:";
    private Button a2jsButton;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_test);

        a2jsButton = (Button) findViewById(R.id.a2js_button);
        webView = (WebView) findViewById(R.id.test_wv);

        initWebView();
        a2jsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        int version = Build.VERSION.SDK_INT;
                        if (version < Build.VERSION_CODES.KITKAT) {
                            webView.loadUrl("testHtml:callJS()");
                        } else {
                            webView.evaluateJavascript("testHtml:callJS()", new ValueCallback<String>() {
                                @Override
                                public void onReceiveValue(String value) {
                                    Log.d(TAG, value);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void initWebView() {
        WebSettings webSettings = webView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置可调式
        webView.setWebContentsDebuggingEnabled(true);
        webView.addJavascriptInterface(new Js2AndroidFunc(), "testFunc");
        webView.loadUrl("file:///android_asset/testHtml.html");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d(TAG, "start");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d(TAG, "finish");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, "url");
                //2.通过WebViewClient 的shouldOverrideUrlLoading（）拦截url；
                if (url.startsWith("https://bridge_loaded")) {
                    String func = url.split("_")[2];
                    if (func.startsWith("androidalarm")) {
                        androidAlarm("hahaha");
                    }
                }
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                new AlertDialog.Builder(JsTestActivity.this)
                        .setTitle("JsAlert")
                        .setMessage(message)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        })
                        .setCancelable(false)
                        .show();
                return true;
            }
        });
    }

    //1.通过webview的addJavascriptInterface（）方法进行对象映射；
    public class Js2AndroidFunc {
        @JavascriptInterface
        public String androidAlarm(String msg) {
            Log.d(TAG, "Toast");
            Toast.makeText(JsTestActivity.this, "js调用android方法:" + msg, Toast.LENGTH_SHORT).show();
            return "Yes";
        }
    }

    public void androidAlarm(String msg) {
        Log.d(TAG, "Toast");
        Toast.makeText(JsTestActivity.this, "ja调用android方法:" + msg, Toast.LENGTH_SHORT).show();
    }
}
