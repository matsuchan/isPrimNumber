## 処理についての説明
サーバを起動し、クライアントを接続することで数値の入力を促されます。
数値を入力するとサーバによってその数値が素数かどうかの判別を行い、判別結果をクライアントに送信します。
また、数値の入力を促されている際に"quit"又は"exit"と入力すことでプログラムを終了することができます。

サーバを常時待機状態にすることはできましたが、クライアントは１回処理を終えるたびにポート番号を再入力して接続しなおさなければなりません。