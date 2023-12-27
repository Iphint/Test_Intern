package com.iphint.testmagang.response

data class ResponseAllData(
	val perPage: Int? = null,
	val total: Int? = null,
	val data: List<DataItem?>? = null,
	val page: Int? = null,
	val totalPages: Int? = null,
	val support: Support? = null
)

data class Support(
	val text: String? = null,
	val url: String? = null
)

data class DataItem(
	val last_name: String? = null,
	val id: Int? = null,
	val avatar: String? = null,
	val first_name: String? = null,
	val email: String? = null
)

