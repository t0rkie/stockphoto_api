# Photo API

## 概要
写真の登録・読み込み・編集・削除を行うAPI

## API一覧


| 役割 | HTTPメソッド | URI | アクション
| ---- | ---- | ---- | ----
| 新規登録 | POST | /api/vi/photo/store | store
| 写真一覧 | GET | /api/vi/photo/index | index
| １件表示 | GET | /api/vi/photo/show | show
| 更新 | PUT | /api/vi/photo/update | update
| 削除 | DELETE | /api/vi/photo/delete | delete



## 新規登録
#### Request
```
POST /api/vi/photo/store
```


#### Response
Headers
