## AI Modeling Server

> 前端工程地址：[AI Modeling Frontend](https://github.com/WindStation/ai-modeling)

### 项目说明 Introduction

**AI Modeling**是一款利用 *LLM能力* 和 *PlantUML* 构建的软件建模辅助工具，为开发者提供一个“需求→自动建模→预览→优化→管理”的完整工作流。若您对PlantUML很熟悉，也可以手动编辑代码。

### 后端工程详情 Backend (this repo)

本仓库存储了AI Modeling的后端代码。


#### 环境、依赖 Env/Deps

- MySQL 8+
- Maven 3+
- Java SDK 21

#### 构建 Build

1. 确保数据库已上线，并手动新建一个名为`aimodeling`的空数据库。

> 请在`application.properties`中修改数据库连接详情。

2. Maven构建。

```bash
# 确保work directory为项目根目录

# maven构建
mvn clean package   

# 启动应用
java -jar target/AiModelingServer-0.0.1-SNAPSHOT.jar  # 默认jar文件名
```