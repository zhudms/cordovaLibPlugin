package cordova.ryl.cordovalib;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.SoftReference;

/**
 * Created by Administrator on 2017/4/13.
 */

 public   class  SoftHandle extends Handler {

    private SoftReference<Context> mSoftContext;

    public SoftHandle(Context context) {
        this.mSoftContext = new SoftReference<Context>(context);
    }


    public Context getSoftContext() {
        if (mSoftContext==null){
            return null;
        }
        return mSoftContext.get();
    }


}
