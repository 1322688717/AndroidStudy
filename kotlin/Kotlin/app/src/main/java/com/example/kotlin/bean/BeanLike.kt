package com.example.kotlin.bean

data class BeanLike(
    val code: Int,
    val msg: String,
    val res: Res,
)

data class Res(
    val vertical: List<Vertical>,
)

data class Vertical(
    val atime: Double,
    val cid: List<String>,
    val cr: Boolean,
    val desc: String,
    val favs: Int,
    val id: String,
    val img: String,
    val ncos: Int,
    val preview: String,
    val rank: Int,
    val rule: String,
    val source_type: String,
    val store: String,
    val tag: List<String>,
    val thumb: String,
    val url: List<Any>,
    val views: Int,
    val wp: String,
    val xr: Boolean,
)
