### FCM-Notification
This is the Firebase Notification Demo. When Application is running in Foreground as well as in background.

There Are two scenarios For that:

##### 1) Application running in foreground:
    
  -  You will get Notification in onMessageReceived(RemoteMessage remoteMessage) method of FirebaseMessagingService.
    
```
      @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        sendNotification(remoteMessage);
    }
      private void sendNotification(RemoteMessage messageBody) {
        Map<String, String> data = messageBody.getData();
        String value = data.get("value");
        String type = data.get("type");
        PendingIntent pendingIntent = null;

        //this intent is for on back click of activity which is open on click of notification
        Intent backIntent = MainActivity.getIntent(this);

        //this is the activity which you want to open on click of notification
        Intent intent = null;
        intent = SecondActivity.getIntent(this, value);


        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pendingIntent = PendingIntent.getActivities(this, 0,
                    new Intent[]{backIntent, intent},
                    PendingIntent.FLAG_ONE_SHOT);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(messageBody.getNotification().getTitle())
                    .setContentText(messageBody.getNotification().getBody())
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0, notificationBuilder.build());
        }
    }
    
   ```
    
 ##### 2) Application running in background:
 
   - When Apllication is running in background, You will get notification data in OnCreate() method of launcher Activity.
 
 ```android
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        Intent i = getIntent();
        if (i.getExtras() != null) {
            String notificationValue = i.getStringExtra("value");
            Intent intent = SecondActivity.getIntent(this, notificationValue);
            startActivity(intent);
        }
     }
    
```
