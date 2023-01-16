# オンラインポーカー　～セットアップマニュアル～
### セットアップ方法
#### 手段①　はじめに、大学以外のサーバーを利用している場合や大学外からこのセットアップを行っている場合は大学のVPNに接続しておく
#### 手段②　isdev-bash-2.35.2-64.exeを起動し、下記のコマンドを実行する
#### 　　　　`ssh isdev22@150.89.233.208`
#### 手順③　パスワードを要求されるので、`isDev22?208`と入力し実行する
#### 手段④　下記のコマンドを実行し、ファイルをダウンロードする
#### 　　　　`git clone https://github.com/e1b20026/cardgame.git`
#### 手段⑤　下記のコマンドを実行し、cardgameディレクトリに移動する
#### 　　　　`cd cardgame`
#### 手段⑥　cardgameディレクトリ内で`bash ./gradlew`と入力し実行する
#### 手段⑦　`bash ./gradlew bootrun`を実行する
#### 手段⑧　ブラウザでhttp://150.89.233.208 にアクセスする