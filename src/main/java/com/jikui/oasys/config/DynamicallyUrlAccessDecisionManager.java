package com.jikui.oasys.config;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;

/**
 * @Author: zhaojq
 * @Description:动态URL访问权限是否通过决策
 * @Date:Create：in 2020/6/20 16:41
 * @Modified By：
 **/
public class DynamicallyUrlAccessDecisionManager extends AbstractAccessDecisionManager {
    public DynamicallyUrlAccessDecisionManager(List<AccessDecisionVoter<?>> decisionVoters) {
        super(decisionVoters);
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        int deny = 0;

        for (AccessDecisionVoter voter : getDecisionVoters()) {
            int result = voter.vote(authentication, object, configAttributes);

            if (logger.isDebugEnabled()) {
                logger.debug("Voter: " + voter + ", returned: " + result);
            }

            switch (result) {
                case AccessDecisionVoter.ACCESS_GRANTED:
                    return;

                case AccessDecisionVoter.ACCESS_DENIED:
                    deny++;

                    break;

                default:
                    break;
            }
        }

        if (deny > 0) {
            throw new AccessDeniedException(messages.getMessage(
                    "AbstractAccessDecisionManager.accessDenied", "Access is denied"));
        }

        // To get this far, every AccessDecisionVoter abstained
        checkAllowIfAllAbstainDecisions();
    }
}