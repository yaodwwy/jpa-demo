package jpa.auditor;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author yaodw
 */
@Component
public class UserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //TODO: 根据实际情况取真实用户
        return Optional.of("admin");
    }


    /**
     * 从security中获取用户
     */
    /*@Override
    public String getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "nobody";
        }
        return authentication.getPrincipal();
    }*/

    /**
     * 从请求中获取用户
     */
    /*@Override
    public String getCurrentAuditor() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("X-USER-ID");
    }*/

}