# Micronaut + JOOQ (+ React)サンプル

## 利用技術
* Kotlin
* Micronaut
* JOOQ
* Flyway
* Typescript
* React
* Redux

## 動作確認環境
* macOS 1.15.6
* OpenJDK (Amazon Corretto) 11.0.3.7.1
* node 14.4.0
* yarn 1.19.1
* Google Chrome 88.0

## 含むもの
* バックエンド
    * 書籍と著者のCRUD API
    * ControllerとJOOQを用いたリポジトリ実装のテスト
* フロントエンド
    * 上記のUI

## 含まないもの
* バックエンド
    * 入力値のバリデーション
    * 細かいエラーハンドリング
* フロントエンド
    * スタイリング
    
## 構成の概要
* バックエンド
    * controller - APIエンドポイント
    * domain - フレームワークに依存しないドメインオブジェクト
    * implementations - ドメインで定義したinterfaceの実装暮らし
    * usecase - controllerから呼び出されるusecase(アプリケーションサービスを各クラス1パブリックメソッドになるように実装)
* フロントエンド
    * app - Reactコンポーネントと、そこから呼び出されるcustom hooks。コンポーネントからreduxへの直接依存を避けるためにhooksを定義。
    * domain - フレームワークに依存しないドメインオブジェクト
    * state - redux関連のファイルとAPI通信部分の定義

## アプリケーション実行方法

### 1.DBの起動
プロジェクトルートにて以下のコマンドを実行する。

```
$ docker-compose up -d
```
dockerにてMySQLが起動します。初回は時間がかかるので、DBの起動を待ってから次に進んでください。
DBへの接続を確認する場合は以下

* host
    * 127.0.0.1
* port
    * 3316
* user
    * scott
* password
    * tiger
* database
    * main_db (アプリケーション本体用)
    * test_db (ユニットテスト用)
    
### 2.バックエンドの起動

gradleプロジェクトのインポート
プロジェクトルートにて以下のコマンドを実行する。

```
$ ./gradlew
```

バックエンドの起動
プロジェクトルートにて以下のコマンドを実行する。

```
$ ./gradlew clean run
```

バックエンドのユニットテストの実行
プロジェクトルートにて以下のコマンドを実行する。

```
$ ./gradlew clean test
```

### 3.フロントエンドの起動

フロントエンドに移動する

```
$ cd webfrontend
```

依存関係のインポート

```
$ yarn install
```

React Appの起動

```
$ yarn run start
```

### 4.動作確認
上記まで実行したのち、下記URLにブラウザでアクセスしてください。

http://localhost:3000

## LICENSE
MIT
