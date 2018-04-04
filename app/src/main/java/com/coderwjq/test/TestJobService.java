package com.coderwjq.test;

import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.coderwjq.viewpager_recyclerview.R;

/**
 * @author: wangjiaqi
 * @data: 2018/3/20
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TestJobService extends JobService {

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            JobParameters param = (JobParameters) msg.obj;
            jobFinished(param, true);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            String title = "TestJobService";
            String subTitle = "TestJobService push";

            // 低版本和主题推送只显示logo
            builder.setSmallIcon(R.drawable.ic_launcher_background);
            builder.setContentTitle(title);
            builder.setContentText(subTitle);
            builder.setColor(Color.parseColor("#24BBFD"));
            builder.setAutoCancel(true);

            NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(101, builder.build());

            return true;
        }
    });

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Message m = Message.obtain();
        m.obj = params;
        handler.sendMessage(m);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        handler.removeCallbacksAndMessages(null);
        return false;
    }
}
