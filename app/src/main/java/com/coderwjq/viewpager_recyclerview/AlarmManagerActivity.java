package com.coderwjq.viewpager_recyclerview;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.coderwjq.test.TestJobService;

public class AlarmManagerActivity extends AppCompatActivity {
    private static final String TAG = "AlarmManagerActivity";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);

        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("id", 100);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
        am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000 * 5, pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            JobInfo jobInfo = new JobInfo.Builder(1, new ComponentName(getPackageName(), TestJobService.class.getName()))
                    .setPeriodic(5000)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE)
                    .build();
            jobScheduler.schedule(jobInfo);
        }
    }

    public static class AlarmReceiver extends BroadcastReceiver {
        public AlarmReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive: id = " + intent.getIntExtra("id", -1));

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            String title = "push";
            String subTitle = "sub push";

            // 低版本和主题推送只显示logo
            builder.setSmallIcon(R.drawable.ic_launcher_background);
            builder.setContentTitle(title);
            builder.setContentText(subTitle);
            builder.setColor(Color.parseColor("#24BBFD"));
            builder.setAutoCancel(true);

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(100, builder.build());
        }
    }
}
