package cordova.ryl.cordovalib;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by rongyile on 2017/12/5.
 */

public abstract class MyPluginParent extends CordovaPlugin {

    public CallbackContext mCallBack;

    public String[] PROMISSION;
    public int PROMISSION_REQUESTCODE;

    public RequestPromisionHelper permissionHelper;

    protected PromissionResults mPromissionResultsLisener;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        String mAction = getMyAction();

        if (action.equals(mAction)) {

            parseJSData(args);

            initProData(callbackContext);

            getPromission();

            return true;
        }
        return false;
    }

    protected abstract String getMyAction();

    protected abstract int getPromissionRequestCode();

    protected abstract String[] getPromissions();


    /**
     * 解析前端传过来的数据
     *
     * @param args
     */
    protected abstract void parseJSData(JSONArray args) throws JSONException;

    /**
     * 程序绝对入口
     */
    protected abstract void startFunction();


    /**
     * 需要初始化的数据,子类可覆写,会在程序入口前自动调用
     */
    protected void initProData(CallbackContext callbackContext) {

        this.mCallBack = callbackContext;
        PROMISSION_REQUESTCODE = getPromissionRequestCode();
        PROMISSION = getPromissions();

    }

    protected void getPromission() {

        permissionHelper = new RequestPromisionHelper(this.PROMISSION,
                this.PROMISSION_REQUESTCODE, cordova.getActivity());

        if (permissionHelper.requestPromissions() == CordovaHelper.MyPermissionResult.ACCESS ||
                permissionHelper.requestPromissions() == CordovaHelper.MyPermissionResult.SDK_LOWER) {

            startFunction();

        }

    }

    public void onRequestPermissionResult(int requestCode, String[] permissions,
                                          int[] grantResults) throws JSONException {

        if (requestCode == PROMISSION_REQUESTCODE) {

            if (mPromissionResultsLisener == null) {

                if (permissionHelper.isSuccess(grantResults)) {

                    startFunction();

                } else {

                    sendResult(PluginResult.Status.ILLEGAL_ACCESS_EXCEPTION, CordovaHelper.permissionDenied);

                }

            } else {

                RequestPromisionHelper.PromissionRequestResult result =
                        permissionHelper.isSuccess(grantResults, permissions);


                    if (result.isSuccess) {

                        startFunction();
                        mPromissionResultsLisener.onSuccess();

                    } else {
                        mPromissionResultsLisener.failLists(result.failLists);
                    }



            }


        }


    }


    public void sendResult(PluginResult.Status result, String message) {
        this.mCallBack.sendPluginResult(new PluginResult(result, message));
    }

    /**
     * 当传入监听后也只有权限请求失败后的处理交给子类
     * @param promissionResultsLisener
     */
    public void setPromissionResultsLisener(PromissionResults promissionResultsLisener) {
        this.mPromissionResultsLisener = promissionResultsLisener;
    }

    interface PromissionResults {

        void onSuccess();

        void failLists(ArrayList<String> failLists);
    }

}

