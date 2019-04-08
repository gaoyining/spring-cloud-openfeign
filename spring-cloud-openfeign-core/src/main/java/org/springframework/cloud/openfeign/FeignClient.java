/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.openfeign;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * Annotation for interfaces declaring that a REST client with that interface should be
 * created (e.g. for autowiring into another component). If ribbon is available it will be
 * used to load balance the backend requests, and the load balancer can be configured
 * using a <code>@RibbonClient</code> with the same name (i.e. value) as the feign client.
 *
 * 接口的注释声明应该创建具有该接口的REST客户端（例如，用于自动装配到另一个组件中）。
 * 如果功能区可用，它将用于对后端请求进行负载平衡，并且可以使用与代码客户端具有相同名称（即值）的
 * <code> @RibbonClient </ code>来配置负载均衡器。
 *
 * @author Spencer Gibb
 * @author Venil Noronha
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FeignClient {

	/**
	 * The name of the service with optional protocol prefix. Synonym for {@link #name()
	 * name}. A name must be specified for all clients, whether or not a url is provided.
	 * Can be specified as property key, eg: ${propertyKey}.
	 *
	 * 具有可选协议前缀的服务的名称。 {@link #name（）name}的同义词。
	 * 无论是否提供URL，都必须为所有客户端指定名称。 可以指定为属性键，例如：$ {propertyKey}。
	 */
	@AliasFor("name")
	String value() default "";

	/**
	 * The service id with optional protocol prefix. Synonym for {@link #value() value}.
	 * 带有可选协议前缀的服务标识。 {@link #value（）value}的同义词。
	 *
	 * @deprecated use {@link #name() name} instead
	 */
	@Deprecated
	String serviceId() default "";

	/**
	 * This will be used as the bean name instead of name if present, but will not be used as a service id.
	 * 如果存在，这将用作bean名称而不是名称，但不会用作服务ID。
	 */
	String contextId() default "";

	/**
	 * The service id with optional protocol prefix. Synonym for {@link #value() value}.
	 */
	@AliasFor("value")
	String name() default "";
	
	/**
	 * Sets the <code>@Qualifier</code> value for the feign client.
	 */
	String qualifier() default "";

	/**
	 * An absolute URL or resolvable hostname (the protocol is optional).
	 */
	String url() default "";

	/**
	 * Whether 404s should be decoded instead of throwing FeignExceptions
	 */
	boolean decode404() default false;

	/**
	 * A custom <code>@Configuration</code> for the feign client. Can contain override
	 * <code>@Bean</code> definition for the pieces that make up the client, for instance
	 * {@link feign.codec.Decoder}, {@link feign.codec.Encoder}, {@link feign.Contract}.
	 *
	 * @see FeignClientsConfiguration for the defaults
	 */
	Class<?>[] configuration() default {};

	/**
	 * Fallback class for the specified Feign client interface. The fallback class must
	 * implement the interface annotated by this annotation and be a valid spring bean.
	 */
	Class<?> fallback() default void.class;

	/**
	 * Define a fallback factory for the specified Feign client interface. The fallback
	 * factory must produce instances of fallback classes that implement the interface
	 * annotated by {@link FeignClient}. The fallback factory must be a valid spring
	 * bean.
	 *
	 * 为指定的Feign客户端界面定义回退工厂。
	 * 回退工厂必须生成回退类的实例，这些回复类实现由{@link FeignClient}注释的接口。 后备工厂必须是有效的spring bean。
	 *
	 * @see feign.hystrix.FallbackFactory for details.
	 */
	Class<?> fallbackFactory() default void.class;

	/**
	 * Path prefix to be used by all method-level mappings. Can be used with or without
	 * 所有方法级映射都使用的路径前缀。 可以使用或不使用
	 * <code>@RibbonClient</code>.
	 */
	String path() default "";

	/**
	 * Whether to mark the feign proxy as a primary bean. Defaults to true.
	 * 是否将假装代理标记为主bean。 默认为true。
	 */
	boolean primary() default true;

}
