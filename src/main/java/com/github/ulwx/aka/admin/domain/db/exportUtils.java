package com.github.ulwx.aka.admin.domain.db;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;

public class exportUtils {
    public static void main(String[] args)throws Exception {
       // SqlUtils.exportTables("db.datasource-sysdb", "common-base", "c:/ok4/commonbase", "com.github.ulwx.aka.admin.domain.db.sys", "utf-8", true);
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        String AOP_POINTCUT_EXPRESSION =
                "(" +
                        "   execution(* com.github.ulwx.aka.admin..services.service.testdb..*Service.*(..))" +
                        "|| execution(* com.github.ulwx.aka.admin..services.dao.testdb..*Dao.*(..))" +
                        "|| execution(* com.github.ulwx.aka.admin..services.service.sysdb..*Service.*(..))" +
                        "|| execution(* com.github.ulwx.aka.admin..services.dao.sysdb..*Dao.*(..)))" +
                        "&& target(com.github.ulwx.aka.webmvc.DBDaoSupport)" +
                        "&& (!@annotation(org.springframework.transaction.annotation.Transactional))" +
                        "&& (!@within(org.springframework.transaction.annotation.Transactional))";

        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);

    }
}
