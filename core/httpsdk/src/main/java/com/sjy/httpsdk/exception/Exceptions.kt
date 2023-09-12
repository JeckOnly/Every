package com.sjy.httpsdk.exception

/**
 * Created by JeckOnly on 2023/9/12
 * Describe:
 */

/**
 * 与远程 api 通信时出现异常。包含 http [statusCode]。
 */
sealed class ApiException(val statusCode: Int) : Exception() {

    /**
     * status code 4xx
     */
    class ClientException(statusCode: Int): ApiException(statusCode)

    /**
     * status code 5xx
     */
    class ServerException(statusCode: Int): ApiException(statusCode)

    class UnknownApiException(statusCode: Int): ApiException(statusCode)

}

/**
 * 异常表明设备未连接到互联网等情况
 *
 * 背后是IOException
 *
 * 1. 网络连接问题：当设备无法连接到服务器或者网络连接不可用时，例如断网或者无网络信号，Retrofit 可能会抛出 IOException 异常。
 *
 * 2. 超时：当与服务器建立连接后，在指定的时间内未接收到服务器的响应时，Retrofit 可能会抛出 IOException 异常。这可能是由于服务器响应超时或网络延迟导致的。
 *
 * 3. DNS 解析问题：当 Retrofit 尝试解析服务器的域名时发生问题，例如无法解析域名或者 DNS 解析超时，可能会抛出 IOException 异常。
 *
 * 4. SSL/TLS 握手问题：如果在与服务器建立安全连接时发生 SSL/TLS 握手失败或证书验证错误等问题，Retrofit 可能会抛出 IOException 异常。
 */
class NoInternetException(cause: Exception) : Exception(cause)

/**
 * 未处理意外异常
 *
 * cause such as ConnectException, SocketTimeoutException, 以及其他RuntimeException
 */
class UnexpectedException(cause: Throwable) : Exception(cause)

/**
 * 自定义异常
 *
 * 比如说检测到某个status code表示用户不存在，可以创建一个NotFoundUserException: CustomException
 */
open class CustomException(cause: Exception) : Exception()