# 开发者接口

## 添加依赖

[![releases](https://img.shields.io/github/v/release/SkyNet1748/mirai-economy-core?label=%E6%9C%80%E6%96%B0%E7%89%88&logo=Github)](https://github.com/SkyNet1748/mirai-economy-core/releases)

请自行选择版本填入 `$economyVersion`

```groovy
repositories { 
    maven { url 'https://www.jitpack.io' }
}
dependencies {
    implementation 'com.github.SkyNet1748:mirai-economy-core:$economyVersion'
}
```

在你的插件描述 (插件描述文件 `plugin.yml` 或主类的 `JvmPluginDescription`) 中加入
```yaml
# plugin.yml
dependencies:
  - 'io.github.skynet1748.mirai-economy-core'
```
```kotlin
//kotlin 或 java
dependsOn("io.github.skynet1748.mirai-economy-core")
```

## 使用

```kotlin
// 获取默认经济服务 (但下面这种写法是不推荐的，请自行解决取值为空的问题)
val economy : IEconomyService = EconomyApi.get()!!
// 获取特定经济服务
// EconomyApi["插件ID"]

// 要操作用户的金钱，首先要获取一个上下文
// 不同上下文的用户数据不同

// 获取全局上下文
val context : IEconomyContext = economy.getGlobalContext()
// 获取群聊上下文
// economy.getGroupContext(群号)

// 接下来的东西源码注释有，不细说
```
经济上下文接口 [IEconomyContext](src/main/kotlin/io/github/skynet1748/mirai/economy/IEconomyService.kt#L13-L59)

## 实现经济服务

根据需求，你可能需要实现一个全新的经济服务。详见 [IEconomyService](src/main/kotlin/io/github/skynet1748/mirai/economy/IEconomyService.kt) 及其注释

> kotlin 实现参考 [test/SimpleEconomyService](src/test/kotlin/SimpleEconomyService.kt)  
> Java 实现参考 [test/JavaEconomyService](src/test/java/JavaEconomyService.java)

实现经济服务之后，只需注册到经济 API 即可

```kotlin
// 需要填入插件 ID 和经济服务实例
EconomyApi.register(pluginId, service)
```