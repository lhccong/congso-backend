package com.yupi.springbootinit.wxmp.handler;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.enums.MessageTypeEnum;
import com.yupi.springbootinit.service.UserService;
import com.yupi.springbootinit.utils.CodeUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息处理器
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 **/
@Component
public class MessageHandler implements WxMpMessageHandler {
    @Resource
    public UserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
            WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        String message = wxMpXmlMessage.getContent();
        String openId = wxMpXmlMessage.getFromUser();
        String code = String.valueOf(CodeUtils.generateRandomNumber());
        if(message.equals(MessageTypeEnum.LOGIN_CODE.getText())){
            long count = userService.count(new LambdaQueryWrapper<User>().eq(User::getMpOpenId, openId));
            if(count==0){
                User user = new User();
                user.setMpOpenId(openId);
                userService.save(user);
            }
            stringRedisTemplate.opsForValue().set(code,openId,10, TimeUnit.MINUTES);
            String content = "你的登录验证码是：" + code+"请在十分钟内登录";
            return WxMpXmlOutMessage.TEXT().content(content)
                    .fromUser(wxMpXmlMessage.getToUser())
                    .toUser(openId)
                    .build();

        }

        String content = "我是复读机：" + message+"可以输入验证码来获取登录密钥";
        return WxMpXmlOutMessage.TEXT().content(content)
                .fromUser(wxMpXmlMessage.getToUser())
                .toUser(openId)
                .build();
    }
}
