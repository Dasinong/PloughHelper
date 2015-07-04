# PloughHelper
## 代码管理（试用）
### 初始化
1. 下载所有文件，创建自己的branch
2. 修改本地包的引用路径（buildpath)。 大部分包已由maven管理，少许需手动。
   注意本地配置会保存在项目 .classpath中，之后上传代码不同步该文件
3. 修改本地路径配置（com.dasinong.ploughHelper.util.Env), 具体内容待改进
4. (可选）修改本branch gitignore, gitignore 不上传或每次不上传bc中内容
  
### 开发时
1. 普通模块开发者:
    每日工作开始时，pull repository 并merge中央库(orign/master)代码。模块开发结束时，commit并push到自己branch。停止工作时通知代码合并者
    （中央库管理员）。
2. 中央库管理员：
      当日工作结束时，先pull repository。在master branch上按序merge各个开发者模块，独立测试模块。所有代码merge成功并测试后，
      commit并push变化到中央库(orign/master)。
  
