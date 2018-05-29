package com.taiyue.tool.common;


import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;


/**
 * Created by ljb on 2018/5/29.
 */
public class MethodObject {


    private String className;
    private String methodName;
    private String paramsArgsName;
    private String paramsArgsValue;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParamsArgsName() {
        return paramsArgsName;
    }

    public void setParamsArgsName(String paramsArgsName) {
        this.paramsArgsName = paramsArgsName;
    }

    public String getParamsArgsValue() {
        return paramsArgsValue;
    }

    public void setParamsArgsValue(String paramsArgsValue) {
        this.paramsArgsValue = paramsArgsValue;
    }

    public MethodObject(ProceedingJoinPoint point) {
        if(point == null){
            return;
        }
        /**
         * Signature 包含了方法名、申明类型以及地址等信息
         */
        String class_name = point.getTarget().getClass().getName();
        String method_name = point.getSignature().getName();
        //重新定义日志
        this.className = class_name;
        this.methodName = method_name;
        /**
         * 获取方法的参数值数组。
         */
        Object[] method_args = point.getArgs();

        try {
            /**
             * 获取方法参数名称
             */
            String[] paramNames = getFieldsName(class_name, method_name);

            /**
             * 打印方法的参数名和参数值
             */
            logParam(paramNames,method_args);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 使用javassist来获取方法参数名称
     * @param class_name    类名
     * @param method_name   方法名
     * @return
     * @throws Exception
     */
    private String[] getFieldsName(String class_name, String method_name) throws Exception {
        Class<?> clazz = Class.forName(class_name);
        String clazz_name = clazz.getName();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(clazz);
        pool.insertClassPath(classPath);

        CtClass ctClass = pool.get(clazz_name);
        CtMethod ctMethod = ctClass.getDeclaredMethod(method_name);
        MethodInfo methodInfo = ctMethod.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if(attr == null){
            return null;
        }
        String[] paramsArgsName = new String[ctMethod.getParameterTypes().length];
        int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
        for (int i=0;i<paramsArgsName.length;i++){
            paramsArgsName[i] = attr.variableName(i + pos);
        }
        return paramsArgsName;
    }


    /**
     * 判断是否为基本类型：包括String
     * @param clazz clazz
     * @return  true：是;     false：不是
     */
    private boolean isPrimite(Class<?> clazz){
        if (clazz.isPrimitive() || clazz == String.class){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 打印方法参数值  基本类型直接打印，非基本类型需要重写toString方法
     * @param paramsArgsName    方法参数名数组
     * @param paramsArgsValue   方法参数值数组
     */
    private void logParam(String[] paramsArgsName,Object[] paramsArgsValue){
        if(ArrayUtils.isEmpty(paramsArgsName) || ArrayUtils.isEmpty(paramsArgsValue)){
//            logger.info("该方法没有参数");
            return;
        }
        StringBuffer paramNames = new StringBuffer();
        StringBuffer method_args = new StringBuffer();
        for (int i=0;i<paramsArgsName.length;i++){
            //参数名
            String name = paramsArgsName[i];
            //参数值
            Object value = paramsArgsValue[i];

            if(i==0) {
                paramNames.append(name);
                if (isPrimite(value.getClass())) {
                    method_args.append(value);
                } else {
                    method_args.append(value.toString());
                }
            }else{
                paramNames.append(name + ",");
                if (isPrimite(value.getClass())) {
                    method_args.append(value + ",");
                } else {
                    method_args.append(value.toString() + ",");
                }
            }
        }
//        logger.info(buffer.toString());
        this.paramsArgsName = "("+paramNames+")";
        this.paramsArgsValue = "("+method_args+")";
    }


}
