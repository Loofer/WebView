package org.loofer.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * ============================================================
 * 版权： x x 版权所有（c）2017
 * <p>
 * 作者：Loofer
 * 版本：1.0
 * 创建日期 ：2017/2/21 18:01.
 * 描述：
 * <p>
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * Modified Date Modify Content:
 * <p>
 * ==========================================================
 */
public class Utils {

    /**
     * 判断否有网络连接
     *
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        int netState = getConnectedType(context);
        if (netState == ConnectivityManager.TYPE_WIFI
                || netState == ConnectivityManager.TYPE_MOBILE) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 判断网络类型 TYPE_DUMMY 8 (0x00000008) TYPE_ETHERNET 9 (0x00000009) TYPE_MOBILE
     * 0 (0x00000000) TYPE_MOBILE_DUN 4 (0x00000004) TYPE_MOBILE_HIPRI 5
     * (0x00000005) TYPE_MOBILE_MMS 2 (0x00000002) TYPE_MOBILE_SUPL 3
     * (0x00000003) TYPE_WIFI 1 (0x00000001) TYPE_WIMAX 6 (0x00000006)
     * DEFAULT_NETWORK_PREFERENCE 1 (0x00000001) TYPE_BLUETOOTH 7 (0x00000007)
     *
     * @param context
     * @return
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }


}
