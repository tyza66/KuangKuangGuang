# KuangKuangGuangCausedFrameForAndoirdWebviewDevelopWithDAO
### 由框哐咣开发而产生的的具有DAO层的安卓Webview开发框架
- Andorid Studio项目
- 仅有一个主页面去掉了Title
- 主界面放置了一个全屏的WebView
- 暂时默认为横屏模式(可以在AndroidManifest更改)
- SQLite数据库已实现DAO层(SQLiteHelper.java，默认数据库名为user.db)
- Android与WebView页面交互注册已实现
- 会申请联网权限，可以访问或插入网络资源
- 可导入任何视窗js，框架js(v1.0.0资源文件里面有jQuery，vue，ElementUI，Layer)
- 如果你是小白，完全不懂Android原生开发,但有一定前端基础，也可以使用本框架进行开发安卓项目
- 仅仅需要编辑AndroidManifes改变屏幕朝向，并在app/src/main/assets/home.html中进行你的前端开发(最好是响应式的),之后使用Android Studio进行应用调试或打包即可
- Enjoy it!

### 版本更新
#### 【2022.4.30】v1.0.0
- 从框哐咣工程目录中提取出来

#### 【2022.4.30】v1.0.1
- 修复了js执行顺序，当网页加载完毕再进行绑定
- 删除了几个不必要文件

By:tyza66(洮羱芝闇)
