package com.rishabh.fillrtask.common

/**
 * A sealed class to represent the state of a data request.
 * This class encapsulates the result of a request, which can be in one of three states:
 * - Success: The request was successful, and the data is provided.
 * - Error: The request failed, and an error message is provided.
 * - Loading: The request is in progress, and any current data (if available) can be provided for
 * optimistic UI updates.
 *
 * @param T The type of the data being requested.
 * @param data The data returned by the request. This can be null if the request is loading or has
 * failed.
 * @param message An optional message providing details about the request failure.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}