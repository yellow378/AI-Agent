package org.lwx.aiagent.utils.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;


/**
 * 实体类转换工具
 */
public class BeanHelper  extends org.springframework.beans.BeanUtils {


        /**
         * 单个对象复制
         * @param source
         * @param target
         * @param <S>
         * @param <T>
         * @return
         */
        public static <S,T> T copyAndReturn(S source, Supplier<T> target){
            T t = target.get();
            copyProperties(source,t);
            return t;
        }


        public static <S,T> T copyAndReturn(S source, Supplier<T> target, BeanCopyCallBack callBack){
            T t = target.get();
            copyProperties(source,t);
            if(callBack != null){
                callBack.callBack(source,t);
            }
            return t;
        }

        /**
         * list对象复制
         * @param sourceList
         * @param target
         * @param <S>
         * @param <T>
         * @return
         */
        public static <S,T> List<T> copyList(List<S> sourceList, Supplier<T> target){
            return copyList(sourceList,target,null);
        }

        public static <S,T> List<T> copyList(List<S> sourceList, Supplier<T> target, BeanCopyCallBack callBack){
            List<T> list = new ArrayList<T>(sourceList.size());
            for(S source : sourceList){
                T t = target.get();
                copyProperties(source,t);

                if(callBack != null){
                    callBack.callBack(source,t);
                }
                list.add(t);
            }
            return list;
        }

        /**
         *
         * @param source  源对象
         * @param target  目标对象
         * @throws BeansException
         */
        public static void copyProperties(Object source, Object target) throws BeansException {
            copyProperties(source, target, null, (String[]) null);
        }


        private static void copyProperties(Object source, Object target, Class<?> editable, String... ignoreProperties)
                throws BeansException {

            Assert.notNull(source, "Source must not be null");
            Assert.notNull(target, "Target must not be null");

            Class<?> actualEditable = target.getClass();
            if (editable != null) {
                if (!editable.isInstance(target)) {
                    throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
                            "] not assignable to Editable class [" + editable.getName() + "]");
                }
                actualEditable = editable;
            }
            PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
            List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;

            for (PropertyDescriptor targetPd : targetPds) {
                Method writeMethod = targetPd.getWriteMethod();
                if (writeMethod != null && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
                    PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                    if (sourcePd != null) {
                        Method readMethod = sourcePd.getReadMethod();
                        if (readMethod != null &&
                                ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                            try {
                                if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                    readMethod.setAccessible(true);
                                }
                                Object value = readMethod.invoke(source);
                                if (value != null) {
                                    if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                        writeMethod.setAccessible(true);
                                    }
                                    writeMethod.invoke(target, value);
                                }
                            }
                            catch (Exception ex) {
                                throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                            }
                        }
                    }
                }
            }
        }
    }

