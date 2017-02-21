package org.loofer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.LinearLayout;

import org.loofer.webview.Html5WebView;

public class WebViewActivity extends AppCompatActivity {
    private Html5WebView mWebView;
    public static final String URL_KEY = "url_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        mWebView = new Html5WebView(getApplicationContext());
        mWebView.setLayoutParams(layoutParams);
        setContentView(mWebView);
        String mUrl = (String) getIntent().getExtras().get(URL_KEY);
        if (!TextUtils.isEmpty(mUrl)) {
            mWebView.loadUrl(mUrl);
        }

        mWebView.setOnPageListerner(mOnPageListerner);
        mWebView.addJavascriptInterface(mInvokeShareJSInterface, "shareObj");

    }


    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        if (mOnPageListerner != null) {
            mOnPageListerner = null;
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
        if (mInvokeShareJSInterface != null) {
            mInvokeShareJSInterface = null;
        }
    }

    Html5WebView.OnPageListerner mOnPageListerner = new Html5WebView.OnPageListerner() {

        @Override
        public void onPageStartLoad() {
            // TODO: 2017/2/21 showLoading
        }

        @Override
        public void onPageComplete() {
            // TODO: 2017/2/21 dismissLoading
        }
    };

    InvokeShareJSInterface mInvokeShareJSInterface = new InvokeShareJSInterface();

    class InvokeShareJSInterface {
        @JavascriptInterface
        public void share(String params) {
            //获取js传递过来的params
        }
    }


}
