package com.xian.blog.constants;

/**
 * 账户类型
 * 
 * @author xian
 * @date 2016年7月27日 下午11:03:13
 *
 */
public final class AccountType {

	/**
	 * 现金账户:现金
	 */
	public static final Integer CASH = 1;
	/**
	 * 金融账户:银行卡(储蓄卡、借记卡)、存折
	 */
	public static final Integer FINANCIAL = 2;
	/**
	 * 信用卡账户:信用卡、蚂蚁花呗、京东白条
	 */
	public static final Integer CREDIT_CART = 3;
	/**
	 * 虚拟账户:支付宝、饭卡、公交卡、财付通(微信钱包)、现金券、储值卡
	 */
	public static final Integer VIRTUAL = 4;
	/**
	 * 投资账户 :股票、余额宝、基金、银行理财
	 */
	public static final Integer INVESTMENT = 5;
	/**
	 * 负债账户:应付款项
	 */
	public static final Integer LIABILITY = 6;
	/**
	 * 债权账户:公司报销、应收款项
	 */
	public static final Integer CREDIT = 7;
}
