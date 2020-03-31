/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Sample Pet Store App
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.apis

import com.tink.service.generated.models.Category
import retrofit2.http.GET
import retrofit2.http.Headers

@JvmSuppressWildcards
interface CategoryApi {
    /**
     * List categories
     * Returns all categories for the given locale. The locale is either taken from the authenticated user or from a query parameter, if no user is authenticated. If no user and no query parameter is given, a default locale is used.
     * The endpoint is owned by defaultname service owner
     * @param locale The locale for which to fetch categories. (optional)
     */
    @Headers(
        "X-Operation-ID: list",
      "Content-Type: application/json"
    )
    @GET("/api/v1/categories")
    suspend fun list(
        @retrofit2.http.Query("locale") locale: String?
    ): List<Category>
}
