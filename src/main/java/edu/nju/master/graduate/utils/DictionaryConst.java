package edu.nju.master.graduate.utils;

public abstract class DictionaryConst {
	
	public interface RESULT_CODE {
		public static final String SUCCESS					= "1000";	// 请求成功
		public static final String FAIL						= "1001";	// 请求失败
    	public static final String VALIDA_ERROR				= "1002";	// 数据校验不通过
		public static final String AUTHENTICATION_ERROR		= "1003";	// 用户未登陆
		public static final String AUTH_TIME_OUT				= "1004";	// 登录失效
		public static final String AUTHORIZATION_ERROR		= "1005";	// 用户无权限
    }

	public interface WX {
		public static final String APP_ID						= "wx29c59885f72680c5";	// 微信小程序APPID
		public static final String APP_SECRET				= "84e962a4f3dc3e6bae3a9bb724447c99";	// 微信小程序秘钥
		public static final String GRANT_TYPE				= "authorization_code";	// 授权类型字段
	}

	public interface MCH {
		public static final String MCH_ID						= "1505329891";	// 商户号
		public static final String MCH_KEY					= "weijiaweijiaweijiaweijiaweijiawe";	// 商户KEY
	}

	public interface ORDER_STATUS{
		public static final Integer NORMAL						= 0;	// 子订单正常，无售后时状态
		public static final Integer WAIT_PAY						= 1;	// 待支付
		public static final Integer WAIT_PAY_CEL					= 2;	// 待支付取消
		public static final Integer ALREADY_PAY					= 3;	// 已支付  代发货
		public static final Integer REFUNDS						= 4;	// 整单申请售后退款中
		public static final Integer WAIT_RECEIPT					= 5;	// 已发货  待收货
		public static final Integer CLOSE						    = 6;	// 订单关闭
		public static final Integer SUCCESS						= 7;	// 确认收货 交易成功
	}

	public interface SERVICE_STATUS{
		public static final Integer REFUND 						= 1;	// 仅退款申请中
		public static final Integer RETURN						= 2;	// 退货退款申请中
		public static final Integer REFUSE						= 3;	// 商家拒绝
		public static final Integer AGREE							= 4;	// 商家同意退货退款申请-等待买家填写物流信息
		public static final Integer EXPRESS  					= 5;	// 商家同意退货退款申请-买家填写完物流信息
		public static final Integer REFUND_SUCCESS				= 6;	// 退款成功-仅退款商家同意退款
		public static final Integer RETUAN_SUCCESS				= 7;	// 退货退款成功-商家收到货后操作
		public static final Integer UNDO							= 8;	// 撤销
	}

	public interface SERVICE_ORDER_TYPE{
		public static final Integer MAIN						= 1;	// 主订单
		public static final Integer CHILD						= 2;	// 子订单
	}

	public interface SERVICE_ORDER_RETURN_TYPE{
		public static final Integer MAIN						= 1;	// 主订单
		public static final Integer CHILD						= 2;	// 子订单
	}

	public interface SERVICE_TYPE{
		public static final Integer REFUND						= 1;	// 退款
		public static final Integer RETURN						= 2;	// 退货退款
	}

	public interface SERVICE_REASON{
		public static final Integer REASON1						= 1;	// 不喜欢/不想要
		public static final Integer REASON2						= 2;	// 商品尺寸与描述不符
		public static final Integer REASON3						= 3;	// 商品材质与描述不符
		public static final Integer REASON4						= 4;	// 商品破损/运输过程受损
	}

	public interface ORDER_DELETE_FLAG{
		public static final Integer NORMAL						= 0;	// 订单正常
		public static final Integer DELETE						= 1;	// 已删除
	}

	public interface SKU_ORDER_STATUS{
		Integer FINANCE											=1;		//提交进货申请，等待财务审核线下打款
		Integer STOREHOUSE										=2;		//财务审核通过，等待仓管发货
		Integer WAITSUCCESS										=3;		//仓管发货，等待到货
		Integer SUCCESS											=4;		//确认收货，进货完成

	}

	public interface SYSTEM_SKU_CODE{
		String SYSTEM_SKU_CODE 									= "system";	// 可供进货的SKUcode
	}

	public interface SKU_CART_STATUS{
		Integer UNUSEFUL											=0;		//不可用-已结算或者删除
		Integer USEFUL												=1;		//可用

	}

	public interface STORE_CODE{
		String SYSTEM_STORE_CODE 									= "system";	// 可供进货的SKUcode
	}

	public interface WITHDRAW_STATUS{
		Integer FINANCE											= 0;	// 提交提现申请
		Integer DONE											= 1;	// 提现通过
	}

	public interface ASSET_INCOME_STATUS{
		Integer IN												= 0;	// 收入
		Integer OUT												= 1;	// 支出
		Integer WAIT											= 2;	// 结算中
	}

	public interface ASSET_RECORD_TYPE{
		String ORDER_IN												="订单收入";	// 订单收入
		String ORDER_BACK												="订单退款";	// 订单退款
		String ORDER_WAIT												="订单结算中";	// 订单结算
		String WITHDRAW												="提现成功";	// 提现
		String WITHDRAW_SERVICE_CHARGE									="提现手续费";	// 提现手续费
	}

	public interface ASSET_INCOME_TYPE{
		String all														="all";	// 全部
		String income													="income";	// 收入
		String expenditure												="expenditure";	// 支出
		String settlement												="settlement";	// 结算中
	}
}
