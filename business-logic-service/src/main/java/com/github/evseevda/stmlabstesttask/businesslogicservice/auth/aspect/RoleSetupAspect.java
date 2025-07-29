package com.github.evseevda.stmlabstesttask.businesslogicservice.auth.aspect;


import com.github.evseevda.stmlabstesttask.businesslogicservice.auth.aspect.annotation.SetupRole;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.service.RoleService;
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
                    * com.github.evseevda.stmlabstesttask.businesslogicservice.auth.service.AuthenticationService.register*(
                        com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User,
                        ..
                    )
                )
            """)
    public void setupRole(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        User user = ((User) joinPoint.getArgs()[0]);
        Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();

        for (Annotation annotation : parameterAnnotations[0]) {
            if (annotation instanceof SetupRole setupRole) {
                String resolvedRole = environment.resolvePlaceholders(setupRole.value());
                user.setRole(roleService.findRoleByName(resolvedRole));
            }
        }

    }


}
