package com.zhushou.test1.service.support;

import com.yuntao.platform.common.cache.QueueService;
import com.yuntao.platform.common.constant.SystemConstant;
import com.yuntao.platform.common.exception.BizException;
import com.yuntao.platform.common.log.HbLogContextMgr;
import com.yuntao.platform.common.support.SendMsgUtils;
import com.yuntao.platform.common.utils.AppConfigUtils;
import com.yuntao.platform.common.utils.ExceptionUtils;
import com.zhushou.test1.service.impl.AbstService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by shan on 2017/5/19.
 */
@Component
public class SmsServer extends AbstService {

    @Autowired
    private QueueService queueService;

    private int tryMaxCount = 3;

    public String getRegisterSMSCode(final String mobile){
        if (mobile.length() != 11 || !mobile.startsWith("1")) {
            throw new BizException("手机账号不合法");
        }
        final String smsCode = RandomStringUtils.random(4, false, true);
//        if (CustomizedPropertyConfigurer.isProd()) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    String value = "%23code%23%3d"+smsCode;
//                    SmsServer.this.sendMsgJH(AppConstant.SMSTemplate.registerId,mobile,value,0);
//                }
//            }).start();
//        }
        return smsCode;
    }



    private void sendMsgJH(final Long templateId,final String mobile,final  String value,final int tryCount){
        String appName = AppConfigUtils.getAppName();
        long actionTime = System.currentTimeMillis();
        String logTitle = "send sms finished! templateId="+templateId+", mobile=" + mobile + ",value=" + value;
        if(tryCount > 0){
            logTitle = "try send sms,tryCount="+tryCount+"," + logTitle;
        }
        try{
//            if(true){
//                throw new BizException(SystemConstant.ExceptionCode.REMOTE_TIME_OUT,"");
//            }
            String result = SendMsgUtils.sendSMSJH(templateId, mobile, value);
            stackLog.info(result);
            taskLog.info(result);
//            HbLogContextMgr.writeMasterMsg(appName, "sms", "sendMsg", actionTime, logTitle, true, result);
        }catch (Exception e){
            String tryMsg = " ";
            if (e instanceof BizException) {
                BizException bizException = (BizException) e;
                if (bizException.getCode() == SystemConstant.ExceptionCode.REMOTE_TIME_OUT) {
                    if(tryCount < tryMaxCount){  // 最多尝试3次
                        //add to try send queue
//                        String smsErrorList = CacheConstant.smsErrorList;
//                        SmsVo smsVo = new SmsVo();
//                        smsVo.setTemplateId(templateId);
//                        smsVo.setMobile(mobile);
//                        smsVo.setValue(value);
//                        smsVo.setTryCount(tryCount+1);
//                        String smsJson = JsonUtils.object2Json(smsVo);
//                        queueService.add(smsErrorList, smsJson);
                    }else{
                        tryMsg += "，over tryMaxCount,give up! "+tryMaxCount;
                    }
                }
            }
            HbLogContextMgr.writeMasterMsg(appName, "sms", "sendMsg", actionTime, logTitle+tryMsg, false, ExceptionUtils.getPrintStackTrace(e));
        }

    }

    public static void main(String[] args) {
        SmsServer smsServer = new SmsServer();
        smsServer.getRegisterSMSCode("15267164682");
    }

}
