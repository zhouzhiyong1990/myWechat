package com.pingan.wechat.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantsCommon {

    /** 机关养老网关地址 */
    public static final  String JIGUANYL_YHKJ = "yhkj";

    //发送短信网关地址   银海短信
    public static final  String DUANXIN_HYDX = "hydx";

    //银海科技短信发送接口
    public static  final String SENDMSG = "sendmessage" ;
    //短信验证码校验
    public static final String SENDPARAMVALID = "ifhasmessage"; //短信验证码校验
    //忘记密码
    public static final String FORGOTPWD_TYPE="1"; //忘记密码
    public static final String MODIFIEDPWD_TYPE="0"; //修改密码
    public static final String FORGOTPARAM="updorforgetpwd"; //忘记密码方法名

    //登陆方法
    public static final String USER_TYPE_PERSON="0"; //移动端登陆账号类型 --自然人
    public static final String LOGINPARAM="loginauthport"; //移动端登陆校验
    //密码加密串
    public static final String PUBLICKEY= "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdbjXagBUfYQcxnUD/kA1v3yYeCm7jh6C5R9qwJkSiMpgPrJQB/GQnEU/P7ZcaYF0okysOxflQ+1k0MBms9c4V78H4IZs0nCnzbSByH3v5AtxIHR1K6R3rr6xAkp7E6f09SzyB//lXXMnrdmOEtHnUyYb5j6zit4vSl8VeQzIS1QIDAQAB";
    //注册调用
    public static final String REGPARAM="regoauthuserport"; //移动端登陆校验

    //发送短信
    public static final String SENDPARAM="sendmessage"; //移动端登陆校验

    //社保卡应用网关调用
    public static final String SBK_PREFIX = "sbk";
    public static final String SBK_SSC105 = "SSC105"; //密码校验
    public static final String SBK_SSC108 = "SSC108"; //卡信息查询


    public static Map<String,String> getSbkApiCode() {
        Map<String, String> codeMap = new HashMap<String, String>();
        codeMap.put("010","交易成功");
        codeMap.put("020","网络连接通讯异常");
        codeMap.put("021","用户鉴权失败");
        codeMap.put("022","报文解密失败");
        codeMap.put("023","获取接口实例失败");
        codeMap.put("030","社会保障号码不存在");
        codeMap.put("031","社会保障号码为空");
        codeMap.put("032","社会保障号码长度不为18位");
        codeMap.put("033","社会保障卡卡号为空");
        codeMap.put("034","社会保障卡卡流水号为空");
        codeMap.put("035","卡片复位信息（ATR）为空");
        codeMap.put("036","受理人为空");
        codeMap.put("037","受理人长度超长");
        codeMap.put("038","受理时间为空");
        codeMap.put("039","受理时间长度超长");
        codeMap.put("040","社会保障号码不唯一");
        codeMap.put("041","业务流水号为空");
        codeMap.put("042","身份证号码或者姓名不能为空");
        codeMap.put("050","社会保障卡状态错误，现状态为未激活");
        codeMap.put("051","社会保障卡状态错误，现状态为正常");
        codeMap.put("052","社会保障卡状态错误，现状态为书面挂失");
        codeMap.put("053","社会保障卡状态错误，现状态为冻结");
        codeMap.put("054","社会保障卡状态错误，现状态为注销");
        codeMap.put("055","社会保障卡状态错误，现状态为口头挂失");
        codeMap.put("056","社会保障卡状态为非正常状态");
        codeMap.put("060","交易执行失败");
        codeMap.put("061","领卡人联系电话为空");
        codeMap.put("062","领卡时间格式不正确");
        codeMap.put("063","社保卡状态不正确或制卡状态不正确");
        codeMap.put("064","已制卡或者制卡信息已录入");
        codeMap.put("070","密码验证失败");
        codeMap.put("071","外国人社会保障号码错误");
        codeMap.put("080","初始化密码不能办理业务");
        codeMap.put("090","PSAM卡号不存在");
        codeMap.put("091","PSAM卡号为空");
        codeMap.put("092","PSAM卡号、读写器编号不能都为空");
        codeMap.put("093","读写器编号不存在");
        codeMap.put("094","数据不唯一");
        codeMap.put("095","数据异常");
        codeMap.put("096","PSAM卡号不存在");
        codeMap.put("100","PSAM卡号不唯一");
        codeMap.put("110","PASM卡状态错误，现状态为未激活");
        codeMap.put("111","PASM卡状态错误，现状态为正常");
        codeMap.put("112","PASM卡状态错误，现状态为挂失");
        codeMap.put("113","PASM卡状态错误，现状态为冻结");
        codeMap.put("114","PASM卡状态错误，现状态为注销");
        codeMap.put("115","PASM卡状态错误，状态为非正常状态");
        codeMap.put("120","该社会保障号码已办理临时卡");
        codeMap.put("121","该社会保障卡补办信息不存在");
        codeMap.put("122","该社会保障卡补办信息不唯一");
        codeMap.put("130","该社会保障号码临时卡已过期");
        codeMap.put("140","该临时卡ATR信息验证失败");
        codeMap.put("150","该临时卡已办理过，请注销后再进行制卡");
        codeMap.put("151","该人员临时卡登记信息不存在，请先进行临时卡制作登记");
        codeMap.put("152","临时卡登记信息不唯一,请检查");
        codeMap.put("153","制卡卡号和临时卡登记卡号不一致，请使用登记的临时卡");
        codeMap.put("154","注销操作失败");
        return codeMap;
    }

}


