# PiratesKing Auto
Auto tool for piratesking.io
 - Automation play game
 - Notification result to Telegram
# Environment
 - WALLET_ID (Required) :  Your Wallet Address (You can get when login to game or MetaMask, ...)
 - BOT_ID (Default 1): What Bot ? 1 - Easy, 2 - Normal, 3 - Hard, 4 - Challenge
 - ENERGY (Default 5): How many ENERGY of each fight ? Currently is 5
 - CON_TIME (Default 6AM UTC +7 every day): When auto run ?
 - ENABLE_NOTI (Default false): Enable notification to Telegram
 - CHAT_ID: Telegram chat id
 - TELEGRAM_TOKEN: Token of Telegram bot
# Build & run
 - To build app you need maven 3+ and java 11+: mvn clean package
 - To run app you need java 11+: java -jar auto-pirates-king.jar --WALLET_ID=<your-wallet-here> 