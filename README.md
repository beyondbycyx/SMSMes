# SMSMes
1 commit
读取指定联系人(type=1:发来的信息)的短信内容。
步骤：1.通过继承BroadcastReceiver类，在类中选择接收到sms的广播。
2.通过按钮，点击后，使用contentprovider读取指定联系人发过来的短信
