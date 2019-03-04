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
}
