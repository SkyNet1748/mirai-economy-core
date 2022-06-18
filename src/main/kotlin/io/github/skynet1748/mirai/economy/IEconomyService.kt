package io.github.skynet1748.mirai.economy

/**
 * 经济服务，用于存取数据和获取经济上下文
 */
public interface IEconomyService {
    public fun getGlobalContext(): IEconomyContextGlobal
    public fun getGroupContext(groupId: Long): IEconomyContextGroup
}

/**
 * 经济上下文，用于区分当前环境分开存取用户数据
 */
public interface IEconomyContext {
    /**
     * 该上下文的显示名称
     */
    public val name: String
    public val service: IEconomyService

    /**
     * 创建账户
     */
    public fun createAccount(userId: Long, money: Double = 0.0)

    /**
     * 查询是否有该账户
     */
    public fun hasAccount(userId: Long): Boolean

    /**
     * 列出账户列表
     * @param count 列出的数量，0为无限制
     */
    public fun listAccounts(count: Int = 0): List<Long>

    /**
     * 查询账户是否有足够的钱 (余额 >= money)
     */
    public fun has(userId: Long, money: Double): Boolean

    /**
     * 获取账户余额
     */
    public fun get(userId: Long): Double

    /**
     * 设置账户余额
     */
    public fun set(userId: Long, money: Double)

    /**
     * 将钱存入账户
     */
    public fun increase(userId: Long, money: Double): Double

    /**
     * 从账户中取钱
     */
    public fun decrease(userId: Long, money: Double): Double
}

/**
 * 全局上下文，适用于好友等非群聊环境
 */
public interface IEconomyContextGlobal : IEconomyContext

/**
 * 群聊上下文，适用于群聊环境
 */
public interface IEconomyContextGroup : IEconomyContext {
    public val groupId: Long
}