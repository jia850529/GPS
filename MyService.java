public class MyService extends Service {
    private static final int NOTIF_ID = 1;
    private static final String NOTIF_CHANNEL_ID = "Channel_Id";
    public static final String ACTION_PLAY = "com.example.ucl.gps.PLAY";
    public static final String ACTION_STOP = "com.example.ucl.gps.STOP";

    @Override
    public void onCreate(){
        super.onCreate();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // do your jobs here
        String action = intent.getAction();
        if(action.equals(ACTION_PLAY)){
            startForeground();
        }
       else if (action.equals(ACTION_STOP)){
            stopupdates();
        }

        return super.onStartCommand(intent, flags, startId);
    }
    private void stopupdates(){
        stopForeground(true);
    }
    private void startForeground() {
        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        startForeground(NOTIF_ID, new NotificationCompat.Builder(this,
                NOTIF_CHANNEL_ID) // don't forget create a notification channel first
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Service is running background")
                .setContentIntent(pendingIntent)
                .build());

    }
}
