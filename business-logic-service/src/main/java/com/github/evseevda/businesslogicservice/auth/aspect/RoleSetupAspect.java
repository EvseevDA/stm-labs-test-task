package com.github.evseevda.businesslogicservice.auth.aspect;


import com.github.evseevda.businesslogicservice.auth.aspect.annotation.SetupRole;
import com.github.evseevda.businesslogicservice.user.entity.User;
import com.github.evseevda.businesslogicservice.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Aspect
@Component
@RequiredArgsConstructor
public class RoleSetupAspect {

    private final RoleService roleService;
    private final Environment environment;

    @Before("""
        execution(
            * com.github.evseevda.businesslogicservice.auth.service.AuthService.register*(
                ..,
                @com.github.evseevda.businesslogicservice.auth.aspect.annotation.SetupRole (*),
                ..
            )
        )
    """)
    public void setupRole(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Object[] args = joinPoint.getArgs();
        Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof User user) {
                for (Annotation annotation : parameterAnnotations[i]) {
                    if (annotation instanceof SetupRole setupRole) {
                        String resolvedRole = environment.resolvePlaceholders(setupRole.value());
                        user.setRole(roleService.findRoleByName(resolvedRole));
                    }
                }
            }
        }
    }


}
