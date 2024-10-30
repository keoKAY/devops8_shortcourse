## Note 
Trying  to add the telegram bot

**HELLOTHERE**
```bash

bot_token:  7830516253:AAHfv7jv7AQSFN63UYJSnJtIIJVQNzVFqvQ
user_id:  <chat-id>
https://api.telegram.org/bot<telegram-token>/getUpdates

sh """
curl -s -X POST https://api.telegram.org/bot<telegram-token>/sendMessage \
    -d chat_id=<chat-id> \
    -d text="Good Morning from  jenkins !"

"""

```