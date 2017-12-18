package cordova.ryl.cordovalib;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;

import cordova.ryl.cordovalib.CordovaHelper.MyPermissionResult;

/**
 * Created by rongyile on 2017/12/5.
 */

public class RequestPromisionHelper {

    private Activity mContext;

    public String[] mPermissions;
    public int mRequestCode;


    public RequestPromisionHelper(String[] permissions, int requestCode, Activity context) {
        this.mPermissions = permissions;
        this.mRequestCode = requestCode;
        this.mContext = context;
    }

    public MyPermissionResult requestPromissions() {

        if (Build.VERSION.SDK_INT < 23 || mPermissions == null) {
            return MyPermissionResult.SDK_LOWER;
        }


        for (int i = 0; i < mPermissions.length; i++) {

            if (ContextCompat.checkSelfPermission(this.mContext, mPermissions[i]) != PackageManager.PERMISSION_GRANTED) {
                String[] requestPromissions = Arrays.copyOfRange(mPermissions, i, mPermissions.length);
                requestThePermissions(requestPromissions);

                return MyPermissionResult.FAILED;

            }

        }

        return MyPermissionResult.ACCESS;

    }

    private void requestThePermissions(String[] permissions) {
        ActivityCompat.requestPermissions(mContext, permissions, mRequestCode);
    }

    /**
     * 只要有一个失败,则返回失败
     *
     * @param results
     * @return
     */
    public boolean isSuccess(int[] results) {
        for (int r : results) {
            if (r == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }

        return true;
    }

    /**
     * 当有String[]数组传入时,请求失败时,会将请求失败的权限按照 Arraylist的形式返回
     *
     * @param results    请求结果
     * @param promission 对应的权限数组
     * @return
     */
    public PromissionRequestResult isSuccess(int[] results, String[] promission) {

        boolean isSuccess = true;
        ArrayList<String> failList = new ArrayList<String>(results.length > 12 ? 12 : results.length);//Arraylist 默认长度12

        for (int i = 0; i < results.length; i++) {

            if (results[i] == PackageManager.PERMISSION_DENIED) {

                failList.add(promission[i]);
                isSuccess = false;
            }

        }

        if (isSuccess){
            return new PromissionRequestResult(true);
        }else{
            return new PromissionRequestResult(false,failList);
        }
    }


    static class PromissionRequestResult {

        boolean isSuccess;
        ArrayList<String> failLists;


        public PromissionRequestResult(boolean isSuccess, ArrayList<String> lists) {
            this.isSuccess = isSuccess;
            this.failLists = lists;
        }

        public PromissionRequestResult(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }
    }
    //    public ArrayList<PromisionResultLisener> mLisener;

//    interface PromisionResultLisener {
//        public abstract void onPromissionGet();
//
//        public abstract void onPromissionDenied(int position);
//
//    }


//    //静态内部类不会保存外部引用
//    public static class PromissionBuilder {
//
//        private RequestPromisionHelper tager;
//
//        public PromissionBuilder() {
//            this.tager = new RequestPromisionHelper();
//
//        }
//
//        private PromissionBuilder promission(String[] promissions) {
//this.tager.promission=promissions;
//return this;
//        }
//
//        private PromissionBuilder requestCode(int requestCode){
//            this.tager.requestCode=requestCode;
//            return  this;
//        }
//
//
//        private  RequestPromisionHelper build( ){
//return this.tager;
//        }
//    }

}
