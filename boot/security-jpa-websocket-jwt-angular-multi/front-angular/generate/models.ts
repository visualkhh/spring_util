/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.29.814 on 2021-02-12 10:21:39.

export namespace org.springframework.web.filter {

    export class GenericFilterBean implements javax.servlet.Filter, org.springframework.beans.factory.BeanNameAware, org.springframework.context.EnvironmentAware, org.springframework.core.env.EnvironmentCapable, org.springframework.web.context.ServletContextAware, org.springframework.beans.factory.InitializingBean, org.springframework.beans.factory.DisposableBean {
        environment: org.springframework.core.env.Environment;
        filterConfig: javax.servlet.FilterConfig;
    }

}

export namespace org.springframework.web.filter {

    export class OncePerRequestFilter extends org.springframework.web.filter.GenericFilterBean {
    }

}

export namespace com.clone.chat.config.security.jwt {

    export class JwtTokenVerifier extends org.springframework.web.filter.OncePerRequestFilter {
        beanName: string;
        servletContext: javax.servlet.ServletContext;
    }

}

export namespace org.springframework.security.web.authentication {

    export class AbstractAuthenticationProcessingFilter extends org.springframework.web.filter.GenericFilterBean implements org.springframework.context.ApplicationEventPublisherAware, org.springframework.context.MessageSourceAware {
        rememberMeServices: org.springframework.security.web.authentication.RememberMeServices;
    }

}

export namespace org.springframework.security.web.authentication {

    export class UsernamePasswordAuthenticationFilter extends org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter {
        usernameParameter: string;
        passwordParameter: string;
        beanName: string;
        servletContext: javax.servlet.ServletContext;
        authenticationDetailsSource: org.springframework.security.authentication.AuthenticationDetailsSource<javax.servlet.http.HttpServletRequest, any>;
        authenticationManager: org.springframework.security.authentication.AuthenticationManager;
        requiresAuthenticationRequestMatcher: org.springframework.security.web.util.matcher.RequestMatcher;
        continueChainBeforeSuccessfulAuthentication: boolean;
        allowSessionCreation: boolean;
        postOnly: boolean;
        filterProcessesUrl: string;
        applicationEventPublisher: org.springframework.context.ApplicationEventPublisher;
        messageSource: org.springframework.context.MessageSource;
        sessionAuthenticationStrategy: org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
        authenticationSuccessHandler: org.springframework.security.web.authentication.AuthenticationSuccessHandler;
        authenticationFailureHandler: org.springframework.security.web.authentication.AuthenticationFailureHandler;
    }

}

export namespace com.clone.chat.config.security.jwt {

    export class JwtUsernameAndPasswordAuthenticationFilter extends org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter {
        secretKey: javax.crypto.SecretKey;
    }

}

export namespace com.clone.chat.config.security.jwt {

    export class UsernameAndPasswordAuthenticationRequest {
        username: string;
        password: string;
        type: string;
    }

}

export namespace com.clone.chat.model {

    export class ModelBase {
    }

}

export namespace com.clone.chat.controller.api.anon.model {

    export class RequestSignUp extends com.clone.chat.model.ModelBase {
        id: string;
        password: string;
        nickName: string;
        phone: string;
        statusMsg: string;
        file: org.springframework.web.multipart.MultipartFile;
    }

}

export namespace com.clone.chat.controller.api.friends.model {

    export class RequestAddFriend {
        id: string;
    }

}

export namespace com.clone.chat.controller.ws.messages.model {

    export class RequestMessage extends com.clone.chat.model.ModelBase {
        contents: string;
    }

}

export namespace com.clone.chat.controller.ws.rooms.model {

    export class RequestCreateRoom {
        name: string;
        users: string[];
    }

}

export namespace com.clone.chat.controller.ws.rooms.model {

    export class RequestSendRoomMessage extends com.clone.chat.model.ModelBase {
        roomId: number;
        contents: string;
        sendDt: Date;
    }

}

export namespace com.clone.chat.domain {

    export class File extends com.clone.chat.model.ModelBase {
        id: number;
        originalFileName: string;
        fileName: string;
        filePath: string;
        fileSize: number;
    }

}

export namespace com.clone.chat.domain.File {

    export class FileBuilder {
    }

}

export namespace com.clone.chat.domain {

    export class Message extends com.clone.chat.model.ModelBase {
        id: number;
        userId: string;
        contents: string;
        userMessages: com.clone.chat.domain.UserMessage[];
        roomMessages: com.clone.chat.domain.RoomMessage[];
        regDt: Date;
    }

}

export namespace com.clone.chat.domain.Message {

    export class MessageBuilder {
    }

}

export namespace com.clone.chat.domain {

    export class Room extends com.clone.chat.model.ModelBase {
        id: number;
        name: string;
        lastMsgDt: Date;
        lastMsgContents: string;
        userRooms: com.clone.chat.domain.UserRoom[];
        roomMessages: com.clone.chat.domain.RoomMessage[];
    }

}

export namespace com.clone.chat.domain.Room {

    export class RoomBuilder {
    }

}

export namespace com.clone.chat.domain {

    export class RoomMessage extends com.clone.chat.model.ModelBase {
        id: number;
        roomId: number;
        userId: string;
        confirm: boolean;
        message: com.clone.chat.domain.Message;
    }

}

export namespace com.clone.chat.domain.RoomMessage {

    export class RoomMessageBuilder {
    }

}

export namespace com.clone.chat.domain.base {

    export class UserBase extends com.clone.chat.model.ModelBase {
        id: string;
        password: string;
        nickName: string;
        phone: string;
        role: com.clone.chat.code.UserRole;
        statusMsg: string;
        regDt: Date;
        updDt: Date;
    }

}

export namespace com.clone.chat.domain {

    export class User extends com.clone.chat.domain.base.UserBase {
        token: string;
        friends: com.clone.chat.domain.User[];
        file: com.clone.chat.domain.File;
    }

}

export namespace com.clone.chat.domain {

    export class UserMessage extends com.clone.chat.model.ModelBase {
        id: number;
        userId: string;
        confirm: boolean;
        message: com.clone.chat.domain.Message;
    }

}

export namespace com.clone.chat.domain.UserMessage {

    export class UserMessageBuilder {
    }

}

export namespace com.clone.chat.domain {

    export class UserRoom extends com.clone.chat.model.ModelBase {
        id: number;
        userId: string;
        user: com.clone.chat.domain.User;
        room: com.clone.chat.domain.Room;
    }

}

export namespace com.clone.chat.domain.UserRoom {

    export class UserRoomBuilder {
    }

}

export namespace com.clone.chat.domain.base.UserBase {

    export class UserBaseBuilder {
    }

}

export namespace com.clone.chat.model {

    export class ChatRoomDto extends com.clone.chat.model.ModelBase {
        chatRoomName: string;
        userId: string;
        name: string;
        message: string;
    }

}

export namespace com.clone.chat.model {

    export class Greeting {
        content: string;
    }

}

export namespace com.clone.chat.model {

    export class RoomList {
        userId: string;
        search: string;
    }

}

export namespace com.clone.chat.model {

    export class UserToken extends com.clone.chat.model.ModelBase {
        id: string;
        authorities: org.springframework.security.core.authority.SimpleGrantedAuthority[];
        token: string;
        tokenHeader: string;
    }

}

export namespace com.clone.chat.model.UserToken {

    export class UserTokenBuilder {
    }

}

export namespace com.clone.chat.model.msg {

    export class Msg<T> {
        code: string;
        message: string;
        data: T;
    }

}

export namespace com.clone.chat.model.error {

    export class Error<T> extends com.clone.chat.model.msg.Msg<T> {
        errors: com.clone.chat.model.error.Error<any>[];
    }

}

export namespace com.clone.chat.model.error {

    export class FieldError<T> extends com.clone.chat.model.error.Error<T> {
        field: string;
    }

}

export namespace com.clone.chat.model.view.json {

    export interface JsonViewApi {
    }

}

export namespace com.clone.chat.model.view.json {

    export interface JsonViewFrontEnd {
    }

}

export namespace org.springframework.core.env {

    export interface Environment extends org.springframework.core.env.PropertyResolver {
        activeProfiles: string[];
        defaultProfiles: string[];
    }

}

export namespace javax.servlet {

    export interface FilterConfig {
        servletContext: javax.servlet.ServletContext;
        filterName: string;
        initParameterNames: java.util.Enumeration<string>;
    }

}

export namespace javax.servlet {

    export interface ServletContext {
        majorVersion: number;
        attributeNames: java.util.Enumeration<string>;
        classLoader: java.lang.ClassLoader;
        minorVersion: number;
        effectiveMajorVersion: number;
        contextPath: string;
        effectiveMinorVersion: number;
        /**
         * @deprecated
         */
        servlets: java.util.Enumeration<javax.servlet.Servlet>;
        /**
         * @deprecated
         */
        servletNames: java.util.Enumeration<string>;
        serverInfo: string;
        initParameterNames: java.util.Enumeration<string>;
        servletContextName: string;
        servletRegistrations: { [index: string]: javax.servlet.ServletRegistration };
        filterRegistrations: { [index: string]: javax.servlet.FilterRegistration };
        sessionCookieConfig: javax.servlet.SessionCookieConfig;
        defaultSessionTrackingModes: javax.servlet.SessionTrackingMode[];
        effectiveSessionTrackingModes: javax.servlet.SessionTrackingMode[];
        jspConfigDescriptor: javax.servlet.descriptor.JspConfigDescriptor;
        virtualServerName: string;
        sessionTimeout: number;
        requestCharacterEncoding: string;
        responseCharacterEncoding: string;
    }

}

export namespace org.springframework.security.web.authentication {

    export interface RememberMeServices {
    }

}

export namespace javax.crypto {

    export interface SecretKey extends java.security.Key, javax.security.auth.Destroyable {
    }

}

export namespace org.springframework.security.authentication {

    export interface AuthenticationDetailsSource<C, T> {
    }

}

export namespace javax.servlet.http {

    export interface HttpServletRequest extends javax.servlet.ServletRequest {
        parts: javax.servlet.http.Part[];
        session: javax.servlet.http.HttpSession;
        method: string;
        authType: string;
        cookies: javax.servlet.http.Cookie[];
        headerNames: java.util.Enumeration<string>;
        httpServletMapping: javax.servlet.http.HttpServletMapping;
        pathInfo: string;
        pathTranslated: string;
        queryString: string;
        remoteUser: string;
        userPrincipal: java.security.Principal;
        requestedSessionId: string;
        requestURI: string;
        requestURL: java.lang.StringBuffer;
        servletPath: string;
        requestedSessionIdValid: boolean;
        requestedSessionIdFromCookie: boolean;
        requestedSessionIdFromURL: boolean;
        /**
         * @deprecated
         */
        requestedSessionIdFromUrl: boolean;
        trailerFields: { [index: string]: string };
        trailerFieldsReady: boolean;
        contextPath: string;
    }

}

export namespace org.springframework.security.authentication {

    export interface AuthenticationManager {
    }

}

export namespace org.springframework.security.web.util.matcher {

    export interface RequestMatcher {
    }

}

export namespace org.springframework.context {

    export interface ApplicationEventPublisher {
    }

}

export namespace org.springframework.context {

    export interface MessageSource {
    }

}

export namespace org.springframework.security.web.authentication.session {

    export interface SessionAuthenticationStrategy {
    }

}

export namespace org.springframework.security.web.authentication {

    export interface AuthenticationSuccessHandler {
    }

}

export namespace org.springframework.security.web.authentication {

    export interface AuthenticationFailureHandler {
    }

}

export namespace org.springframework.web.multipart {

    export interface MultipartFile extends org.springframework.core.io.InputStreamSource {
        contentType: string;
        name: string;
        empty: boolean;
        bytes: number[];
        resource: org.springframework.core.io.Resource;
        size: number;
        originalFilename: string;
    }

}

export namespace org.springframework.security.core.authority {

    export class SimpleGrantedAuthority implements org.springframework.security.core.GrantedAuthority {
        authority: string;
    }

}

export namespace org.springframework.core.env {

    export interface PropertyResolver {
    }

}

export namespace java.util {

    export interface Enumeration<E> {
    }

}

export namespace java.lang {

    export class ClassLoader {
        parent: java.lang.ClassLoader;
    }

}

export namespace javax.servlet {

    export interface Servlet {
        servletConfig: javax.servlet.ServletConfig;
        servletInfo: string;
    }

}

export namespace javax.servlet {

    export interface ServletRegistration extends javax.servlet.Registration {
        runAsRole: string;
        mappings: string[];
    }

}

export namespace javax.servlet {

    export interface FilterRegistration extends javax.servlet.Registration {
        servletNameMappings: string[];
        urlPatternMappings: string[];
    }

}

export namespace javax.servlet {

    export interface SessionCookieConfig {
        path: string;
        comment: string;
        name: string;
        domain: string;
        secure: boolean;
        httpOnly: boolean;
        maxAge: number;
    }

}

export namespace javax.servlet.descriptor {

    export interface JspConfigDescriptor {
        taglibs: javax.servlet.descriptor.TaglibDescriptor[];
        jspPropertyGroups: javax.servlet.descriptor.JspPropertyGroupDescriptor[];
    }

}

export namespace java.security {

    export interface Key extends java.io.Serializable {
        algorithm: string;
        encoded: number[];
        format: string;
    }

}

export namespace javax.security.auth {

    export interface Destroyable {
        destroyed: boolean;
    }

}

export namespace javax.servlet.http {

    export interface Part {
        contentType: string;
        name: string;
        size: number;
        inputStream: java.io.InputStream;
        submittedFileName: string;
        headerNames: string[];
    }

}

export namespace javax.servlet.http {

    export interface HttpSession {
        /**
         * @deprecated
         */
        valueNames: string[];
        attributeNames: java.util.Enumeration<string>;
        creationTime: number;
        id: string;
        new: boolean;
        lastAccessedTime: number;
        maxInactiveInterval: number;
        /**
         * @deprecated
         */
        sessionContext: javax.servlet.http.HttpSessionContext;
        servletContext: javax.servlet.ServletContext;
    }

}

export namespace javax.servlet.http {

    export class Cookie implements java.lang.Cloneable, java.io.Serializable {
        name: string;
        value: string;
        version: number;
        comment: string;
        domain: string;
        maxAge: number;
        path: string;
        secure: boolean;
        httpOnly: boolean;
    }

}

export namespace javax.servlet.http {

    export interface HttpServletMapping {
        pattern: string;
        matchValue: string;
        servletName: string;
        mappingMatch: javax.servlet.http.MappingMatch;
    }

}

export namespace java.security {

    export interface Principal {
        name: string;
    }

}

export namespace java.lang {

    export class AbstractStringBuilder implements java.lang.Appendable, java.lang.CharSequence {
    }

}

export namespace java.lang {

    export class StringBuffer extends java.lang.AbstractStringBuilder implements java.io.Serializable, java.lang.CharSequence {
        length: number;
    }

}

export namespace java.io {

    export class Reader implements java.lang.Readable, java.io.Closeable {
    }

}

export namespace java.io {

    export class BufferedReader extends java.io.Reader {
    }

}

export namespace java.io {

    export class InputStream implements java.io.Closeable {
    }

}

export namespace javax.servlet {

    export class ServletInputStream extends java.io.InputStream {
        ready: boolean;
        finished: boolean;
    }

}

export namespace java.util {

    export class Locale implements java.lang.Cloneable, java.io.Serializable {
    }

}

export namespace javax.servlet {

    export interface AsyncContext {
        timeout: number;
        request: javax.servlet.ServletRequest;
        response: javax.servlet.ServletResponse;
    }

}

export namespace javax.servlet {

    export interface ServletRequest {
        reader: java.io.BufferedReader;
        localPort: number;
        attributeNames: java.util.Enumeration<string>;
        localName: string;
        contentLength: number;
        contentLengthLong: number;
        contentType: string;
        asyncStarted: boolean;
        scheme: string;
        inputStream: javax.servlet.ServletInputStream;
        protocol: string;
        locale: java.util.Locale;
        servletContext: javax.servlet.ServletContext;
        parameterNames: java.util.Enumeration<string>;
        characterEncoding: string;
        parameterMap: { [index: string]: string[] };
        serverName: string;
        secure: boolean;
        serverPort: number;
        remoteAddr: string;
        remoteHost: string;
        locales: java.util.Enumeration<java.util.Locale>;
        remotePort: number;
        localAddr: string;
        asyncSupported: boolean;
        asyncContext: javax.servlet.AsyncContext;
        dispatcherType: javax.servlet.DispatcherType;
    }

}

export namespace org.springframework.core.io {

    export interface Resource extends org.springframework.core.io.InputStreamSource {
        filename: string;
        uri: java.net.URI;
        description: string;
        url: java.net.URL;
        file: java.io.File;
        open: boolean;
        readable: boolean;
    }

}

export namespace org.springframework.core.io {

    export interface InputStreamSource {
        inputStream: java.io.InputStream;
    }

}

export namespace org.springframework.security.core {

    export interface GrantedAuthority extends java.io.Serializable {
        authority: string;
    }

}

export namespace javax.servlet {

    export interface ServletConfig {
        servletContext: javax.servlet.ServletContext;
        servletName: string;
        initParameterNames: java.util.Enumeration<string>;
    }

}

export namespace javax.servlet {

    export interface Registration {
        name: string;
        className: string;
        initParameters: { [index: string]: string };
    }

}

export namespace javax.servlet.descriptor {

    export interface TaglibDescriptor {
        taglibURI: string;
        taglibLocation: string;
    }

}

export namespace javax.servlet.descriptor {

    export interface JspPropertyGroupDescriptor {
        buffer: string;
        isXml: string;
        urlPatterns: string[];
        elIgnored: string;
        pageEncoding: string;
        scriptingInvalid: string;
        includePreludes: string[];
        includeCodas: string[];
        deferredSyntaxAllowedAsLiteral: string;
        trimDirectiveWhitespaces: string;
        defaultContentType: string;
        errorOnUndeclaredNamespace: string;
    }

}

export namespace javax.servlet {

    export interface Filter {
    }

}

export namespace org.springframework.beans.factory {

    export interface BeanNameAware extends org.springframework.beans.factory.Aware {
    }

}

export namespace org.springframework.context {

    export interface EnvironmentAware extends org.springframework.beans.factory.Aware {
    }

}

export namespace org.springframework.core.env {

    export interface EnvironmentCapable {
        environment: org.springframework.core.env.Environment;
    }

}

export namespace org.springframework.web.context {

    export interface ServletContextAware extends org.springframework.beans.factory.Aware {
    }

}

export namespace org.springframework.beans.factory {

    export interface InitializingBean {
    }

}

export namespace org.springframework.beans.factory {

    export interface DisposableBean {
    }

}

export namespace java.io {

    export interface Serializable {
    }

}

export namespace javax.servlet.http {

    /**
     * @deprecated
     */
    export interface HttpSessionContext {
        /**
         * @deprecated
         */
        ids: java.util.Enumeration<string>;
    }

}

export namespace java.lang {

    export interface Cloneable {
    }

}

export namespace java.lang {

    export interface CharSequence {
    }

}

export namespace javax.servlet {

    export interface ServletResponse {
        contentType: string;
        outputStream: javax.servlet.ServletOutputStream;
        locale: java.util.Locale;
        writer: java.io.PrintWriter;
        committed: boolean;
        bufferSize: number;
        characterEncoding: string;
    }

}

export namespace org.springframework.context {

    export interface ApplicationEventPublisherAware extends org.springframework.beans.factory.Aware {
    }

}

export namespace org.springframework.context {

    export interface MessageSourceAware extends org.springframework.beans.factory.Aware {
    }

}

export namespace java.net {

    export class URI implements java.lang.Comparable<java.net.URI>, java.io.Serializable {
    }

}

export namespace java.net {

    export class URL implements java.io.Serializable {
    }

}

export namespace java.io {

    export class File implements java.io.Serializable, java.lang.Comparable<java.io.File> {
    }

}

export namespace java.io {

    export interface Closeable extends java.lang.AutoCloseable {
    }

}

export namespace org.springframework.beans.factory {

    export interface Aware {
    }

}

export namespace java.lang {

    export interface Appendable {
    }

}

export namespace java.lang {

    export interface Readable {
    }

}

export namespace java.io {

    export class OutputStream implements java.io.Closeable, java.io.Flushable {
    }

}

export namespace javax.servlet {

    export class ServletOutputStream extends java.io.OutputStream {
        ready: boolean;
    }

}

export namespace java.io {

    export class Writer implements java.lang.Appendable, java.io.Closeable, java.io.Flushable {
    }

}

export namespace java.io {

    export class PrintWriter extends java.io.Writer {
    }

}

export namespace java.lang {

    export interface AutoCloseable {
    }

}

export namespace java.lang {

    export interface Comparable<T> {
    }

}

export namespace java.io {

    export interface Flushable {
    }

}

export interface HttpClient {

    request<R>(requestConfig: { method: string; url: string; queryParams?: any; data?: any; copyFn?: (data: R) => R; }): RestResponse<R>;
}

export type RestResponse<R> = Promise<R>;

export namespace com.clone.chat.code {

    export enum UserRole {
        USER = "USER",
        ADMIN = "ADMIN",
    }

}

export namespace javax.servlet {

    export enum SessionTrackingMode {
        COOKIE = "COOKIE",
        URL = "URL",
        SSL = "SSL",
    }

}

export namespace javax.servlet {

    export enum DispatcherType {
        FORWARD = "FORWARD",
        INCLUDE = "INCLUDE",
        REQUEST = "REQUEST",
        ASYNC = "ASYNC",
        ERROR = "ERROR",
    }

}

export namespace javax.servlet.http {

    export enum MappingMatch {
        CONTEXT_ROOT = "CONTEXT_ROOT",
        DEFAULT = "DEFAULT",
        EXACT = "EXACT",
        EXTENSION = "EXTENSION",
        PATH = "PATH",
    }

}

function uriEncoding(template: TemplateStringsArray, ...substitutions: any[]): string {
    let result = "";
    for (let i = 0; i < substitutions.length; i++) {
        result += template[i];
        result += encodeURIComponent(substitutions[i]);
    }
    result += template[template.length - 1];
    return result;
}
