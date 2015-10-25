# PloughHelper
## Code Management

### Preparation
1. Setup git & arc (https://secure.phabricator.com/book/phabricator/article/arcanist/#installing-arcanist)
2. Configure arc to work with http://phabricator.dasinong666.com (server may be moved in the future)

### Typical work flow
1. git pull --rebase // pull updates from central repository
2. git checkout -b <branch> // create a local branch for your work
3. do your work 
4. git commit xxx
5. arc diff HEAD^ --preview // submit your change for others to review
6. work with reviewers to wait for your diff to be reviewed
7. arc land // this may result in conflicts

If you are interested in the full details, check out https://secure.phabricator.com/book/phabricator/article/arcanist_diff/

### Notes
1. 修改本地包的引用路径（buildpath)。 大部分包已由maven管理，少许需手动。
   注意本地配置会保存在项目 .classpath中，之后上传代码不同步该文件
2. 修改本地路径配置（com.dasinong.ploughHelper.util.Env), 具体内容待改进
3. (可选）修改本branch gitignore, gitignore 不上传或每次不上传bc中内容
