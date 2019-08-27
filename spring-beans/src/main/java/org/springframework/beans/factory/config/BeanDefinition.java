/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.config;

import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.AttributeAccessor;
import org.springframework.lang.Nullable;

/**
 * A BeanDefinition describes a bean instance, which has property values,
 * constructor argument values, and further information supplied by
 * concrete implementations.
 *
 * 一个BeanDefinition描述了一个bean的实例，包括属性值，构造方法参数值和继承自它的类的更多信息。
 *
 * <p>This is just a minimal interface: The main intention is to allow a
 * {@link BeanFactoryPostProcessor} such as {@link PropertyPlaceholderConfigurer}
 * to introspect and modify property values and other bean metadata.
 *
 * BeanDefinition仅仅是一个最简单的接口，主要功能是允许BeanFactoryPostProcessor
 * 例如PropertyPlaceHolderConfigure 能够检索并修改属性值和别的bean的元数据。
 *
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @since 19.03.2004
 * @see ConfigurableListableBeanFactory#getBeanDefinition
 * @see org.springframework.beans.factory.support.RootBeanDefinition
 * @see org.springframework.beans.factory.support.ChildBeanDefinition
 */

// BeanDefinition继承了AttributeAccessor，说明它具有处理属性的能力；
// BeanDefinition继承了BeanMetadataElement，说明它可以持有Bean元数据元素，作用是可以持有XML文件的一个bean标签对应的Object。
public interface BeanDefinition extends AttributeAccessor, BeanMetadataElement {

	/**
	 * Scope identifier for the standard singleton scope: "singleton".
	 * <p>Note that extended bean factories might support further scopes.
	 * @see #setScope
	 */
	// 标准单例作用域的作用域标识符：“singleton”。
    // 对于扩展的bean工厂可能支持更多的作用域。
	String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

	/**
	 * Scope identifier for the standard prototype scope: "prototype".
	 * <p>Note that extended bean factories might support further scopes.
	 * @see #setScope
	 */
	// 标准原型作用域的范围标识符：“prototype”。
    // 对于扩展的bean工厂可能支持更多的作用域。
	String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;


	//////////////////设置bean的分类//////////////////
	/**
	 * Role hint indicating that a {@code BeanDefinition} is a major part
	 * of the application. Typically corresponds to a user-defined bean.
	 */
	// 表示BeanDefinition是应用程序主要部分的角色提示。 通常对应于用户定义的bean。
	int ROLE_APPLICATION = 0;

	/**
	 * Role hint indicating that a {@code BeanDefinition} is a supporting
	 * part of some larger configuration, typically an outer
	 * {@link org.springframework.beans.factory.parsing.ComponentDefinition}.
	 * {@code SUPPORT} beans are considered important enough to be aware
	 * of when looking more closely at a particular
	 * {@link org.springframework.beans.factory.parsing.ComponentDefinition},
	 * but not when looking at the overall configuration of an application.
	 */
	// 表示BeanDefinition是某些大型配置的支持部分的角色提示，通常是一个外部ComponentDefinition。
	// 当查看某个特定的ComponentDefinition时，认为bean非常重要，
	// 以便在查看应用程序的整体配置时能够意识到这一点。
	int ROLE_SUPPORT = 1;

	/**
	 * Role hint indicating that a {@code BeanDefinition} is providing an
	 * entirely background role and has no relevance to the end-user. This hint is
	 * used when registering beans that are completely part of the internal workings
	 * of a {@link org.springframework.beans.factory.parsing.ComponentDefinition}.
	 */
	// 角色提示表明一个BeanDefinition是提供一个完全背景的角色，并且与最终用户没有关系。
    // 这个提示用于注册完全是ComponentDefinition内部工作的一部分的bean
	int ROLE_INFRASTRUCTURE = 2;


	// Modifiable attributes

	/**
	 * Set the name of the parent definition of this bean definition, if any.
	 */
	// 如果父类存在，设置这个bean定义的父定义的名称。
	void setParentName(@Nullable String parentName);

	/**
	 * Return the name of the parent definition of this bean definition, if any.
	 */
	// 如果父类存在，则返回当前Bean的父类的名称
	@Nullable
	String getParentName();

	/**
	 * Specify the bean class name of this bean definition.
	 * <p>The class name can be modified during bean factory post-processing,
	 * typically replacing the original class name with a parsed variant of it.
	 * @see #setParentName
	 * @see #setFactoryBeanName
	 * @see #setFactoryMethodName
	 */
	// 指定此bean定义的bean类名称。
    // 类名称可以在bean factory后期处理中修改，通常用它的解析变体替换原来的类名称。
	void setBeanClassName(@Nullable String beanClassName);

	/**
	 * Return the current bean class name of this bean definition.
	 * <p>Note that this does not have to be the actual class name used at runtime, in
	 * case of a child definition overriding/inheriting the class name from its parent.
	 * Also, this may just be the class that a factory method is called on, or it may
	 * even be empty in case of a factory bean reference that a method is called on.
	 * Hence, do <i>not</i> consider this to be the definitive bean type at runtime but
	 * rather only use it for parsing purposes at the individual bean definition level.
	 * @see #getParentName()
	 * @see #getFactoryBeanName()
	 * @see #getFactoryMethodName()
	 */
	// 返回此bean定义的当前bean类名称。
	// 需要注意的是，这不一定是在运行时使用的实际类名，以防子类定义覆盖/继承其父类的类名。
	// 此外，这可能只是调用工厂方法的类，甚至在调用工厂方法的时候还是空的。
	// 因此，不要认为这是在运行时定义的bean类型，而只是将其用于在单独的bean定义级别进行解析。
	@Nullable
	String getBeanClassName();

	/**
	 * Override the target scope of this bean, specifying a new scope name.
	 * @see #SCOPE_SINGLETON
	 * @see #SCOPE_PROTOTYPE
	 */
	//覆盖此bean的目标范围，指定一个新的范围名称。
	void setScope(@Nullable String scope);

	/**
	 * Return the name of the current target scope for this bean,
	 * or {@code null} if not known yet.
	 */
	//返回此bean的当前目标作用域的名称，如果没有确定，返回null
	@Nullable
	String getScope();

	/**
	 * Set whether this bean should be lazily initialized.
	 * <p>If {@code false}, the bean will get instantiated on startup by bean
	 * factories that perform eager initialization of singletons.
	 */
	//设置这个bean是否应该被延迟初始化。如果{false}，那么这个bean将在启动时由bean工厂实例化，
    //如果，懒加载为false，这些工厂执行单例的立即初始化。
    //懒加载配置 <bean lazy-init="true/false">
	void setLazyInit(boolean lazyInit);

	/**
	 * Return whether this bean should be lazily initialized, i.e. not
	 * eagerly instantiated on startup. Only applicable to a singleton bean.
	 */
	//返回这个bean是否应该被延迟初始化，即不是在启动时立即实例化。只适用于单例bean。
	boolean isLazyInit();

	/**
	 * Set the names of the beans that this bean depends on being initialized.
	 * The bean factory will guarantee that these beans get initialized first.
	 */
	//设置这个bean依赖被初始化的bean的名字。 bean工厂将保证这些bean首先被初始化。
    //<bean depends-on="">
	void setDependsOn(@Nullable String... dependsOn);

	/**
	 * Return the bean names that this bean depends on.
	 */
	//返回这个bean依赖的bean名称。
	@Nullable
	String[] getDependsOn();

	/**
	 * Set whether this bean is a candidate for getting autowired into some other bean.
	 * <p>Note that this flag is designed to only affect type-based autowiring.
	 * It does not affect explicit references by name, which will get resolved even
	 * if the specified bean is not marked as an autowire candidate. As a consequence,
	 * autowiring by name will nevertheless inject a bean if the name matches.
	 */
	/////////是否是自动装配设置
	//设置这个bean是否是获得自动装配到其他bean的候选人。
	//需要注意是，此标志旨在仅影响基于类型的自动装配。
	//它不会影响按名称的显式引用，即使指定的bean没有标记为autowire候选，也可以解决这个问题。
	//因此，如果名称匹配，通过名称的自动装配将注入一个bean。
	void setAutowireCandidate(boolean autowireCandidate);

	/**
	 * Return whether this bean is a candidate for getting autowired into some other bean.
	 */
	//返回这个bean是否是自动装配到其他bean的候选者。就是是否在其他类中使用autowired来注入当前Bean的
	//spring 没有提供这个配置的相关注解
    //是否为被自动装配 <bean autowire-candidate="true/false">
	boolean isAutowireCandidate();

	/**
	 * Set whether this bean is a primary autowire candidate.
	 * <p>If this value is {@code true} for exactly one bean among multiple
	 * matching candidates, it will serve as a tie-breaker.
	 */
	//在自动装配的时候，有多个候选bean的时候，优先使用这个bean作为注入的对象，和autowireCandidate意思正好相反
	void setPrimary(boolean primary);

	/**
	 * Return whether this bean is a primary autowire candidate.
	 */
	//返回这个bean是否是优先注入的的, 使用注解：@Primary
	//<bean primary="true" />
	boolean isPrimary();

	/**
	 * Specify the factory bean to use, if any.
	 * This the name of the bean to call the specified factory method on.
	 * @see #setFactoryMethodName
	 */
	//指定要使用的工厂bean（如果有的话）。 这是调用指定的工厂方法的bean的名称。
	void setFactoryBeanName(@Nullable String factoryBeanName);

	/**
	 * Return the factory bean name, if any.
	 */
	//返回工厂bean的名字，如果有的话。
	@Nullable
	String getFactoryBeanName();

	/**
	 * Specify a factory method, if any. This method will be invoked with
	 * constructor arguments, or with no arguments if none are specified.
	 * The method will be invoked on the specified factory bean, if any,
	 * or otherwise as a static method on the local bean class.
	 * @see #setFactoryBeanName
	 * @see #setBeanClassName
	 */
	//如果有的话，指定工厂方法。
	//这个方法先将通过构造函数参数被调用，或者如果参数，将调用该方法的无参数构造。
	//方法将在指定的工厂bean（如果有的话）上被调用，或者作为本地bean类的静态方法被调用。
	void setFactoryMethodName(@Nullable String factoryMethodName);

	/**
	 * Return a factory method, if any.
	 */
	//如果存在，返回工厂方法名
	@Nullable
	String getFactoryMethodName();

	/**
	 * Return the constructor argument values for this bean.
	 * <p>The returned instance can be modified during bean factory post-processing.
	 * @return the ConstructorArgumentValues object (never {@code null})
	 */
	//返回此bean的构造函数参数值。
	ConstructorArgumentValues getConstructorArgumentValues();

	/**
	 * Return if there are constructor argument values defined for this bean.
	 * @since 5.0.2
	 */
	//此bean的构造函数是否有参数
	default boolean hasConstructorArgumentValues() {
		return !getConstructorArgumentValues().isEmpty();
	}

	/**
	 * Return the property values to be applied to a new instance of the bean.
	 * <p>The returned instance can be modified during bean factory post-processing.
	 * @return the MutablePropertyValues object (never {@code null})
	 */
	//获取普通属性集合
	MutablePropertyValues getPropertyValues();

	/**
	 * Return if there are property values values defined for this bean.
	 * @since 5.0.2
	 */
	default boolean hasPropertyValues() {
		return !getPropertyValues().isEmpty();
	}

	/**
	 * Set the name of the initializer method.
	 * @since 5.1
	 */
	//设置此bean的初始化方法
	//在配置类中 @Bean(initMethod = "init",destroyMethod = "destory")注解指定
	//<bean init-method="initMethod"/>
	void setInitMethodName(@Nullable String initMethodName);
	//获取初始化方法名称
	@Nullable
	String getInitMethodName();

	/**
	 * Set the name of the destroy method.
	 * @since 5.1
	 */
	//设置销毁方法名称
	//在配置类中 @Bean(initMethod = "init",destroyMethod = "destory")注解指定
	void setDestroyMethodName(@Nullable String destroyMethodName);
	@Nullable
	String getDestroyMethodName();

	/**
	 * Set the role hint for this {@code BeanDefinition}. The role hint
	 * provides the frameworks as well as tools with an indication of
	 * the role and importance of a particular {@code BeanDefinition}.
	 * @since 5.1
	 * @see #ROLE_APPLICATION
	 * @see #ROLE_SUPPORT
	 * @see #ROLE_INFRASTRUCTURE
	 */
	//设置bean的分类。
	//APPLICATION:用户
	//INFRASTRUCTURE:完全内部使用，与用户无关
	//SUPPORT:某些复杂配置的一部分
	void setRole(int role);
	int getRole();


	//对bean对象的描述
	/**
	 * Set a human-readable description of this bean definition.
	 * @since 5.1
	 */
	void setDescription(@Nullable String description);
	@Nullable
	String getDescription();


	// Read-only attributes

	/**
	 * Return whether this a <b>Singleton</b>, with a single, shared instance
	 * returned on all calls.
	 * @see #SCOPE_SINGLETON
	 */
	//是否是单例的
	boolean isSingleton();

	/**
	 * Return whether this a <b>Prototype</b>, with an independent instance
	 * returned for each call.
	 * @since 3.0
	 * @see #SCOPE_PROTOTYPE
	 */
	//是否是多例的
	boolean isPrototype();

	/**
	 * Return whether this bean is "abstract", that is, not meant to be instantiated.
	 */
	//是否是抽象类
	boolean isAbstract();

	/**
	 * Return a description of the resource that this bean definition
	 * came from (for the purpose of showing context in case of errors).
	 */
	//返回该bean定义来自的资源的描述
	@Nullable
	String getResourceDescription();

	/**
	 * Return the originating BeanDefinition, or {@code null} if none.
	 * Allows for retrieving the decorated bean definition, if any.
	 * <p>Note that this method returns the immediate originator. Iterate through the
	 * originator chain to find the original BeanDefinition as defined by the user.
	 */
	//返回原始的BeanDefinition;如果没有，则返回null。允许检索装饰的bean定义（如果有的话）。
	//注意，这个方法返回直接的发起者。 迭代原始链，找到用户定义的原始BeanDefinition。
	@Nullable
	BeanDefinition getOriginatingBeanDefinition();

}
