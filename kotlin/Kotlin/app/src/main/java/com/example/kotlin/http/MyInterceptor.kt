package com.example.kotlin.http

import okhttp3.*

class MyInterceptor : Interceptor {

    val TAG = "MyInterceptor"

    override fun intercept(chain: Interceptor.Chain): Response {
//        var request : Request = chain.request()
//        var startTime = System.currentTimeMillis()
//        var response : Response =  chain.proceed(chain.request())
//        var endTime = System.currentTimeMillis()
//        var duration = endTime-startTime
//        var mediaType : MediaType = response.body!!.contentType()!!
//        var content =  response.body.toString()
//        Log.e(TAG,request.toString())
//        Log.e(TAG,request.body.toString())
//
//        request.body?.let { printParams(it) }
//        Log.e(TAG, "请求体返回：| Response:" + response);
//        Log.e(TAG, "请求体返回：| Response:" + content);
//        Log.e(TAG, "----------请求耗时:" + duration + "毫秒----------");
//        return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
//    }
//
//    private fun printParams(body: RequestBody) {
//       var buffer : okio.Buffer = okio.Buffer()
//       try {
//           body.writeTo(buffer)
//           var charset : Charset = Charset.forName("UTF-8")
//           var contentType : MediaType? = body.contentType()
//           if (contentType!=null){
//               charset = contentType.charset(UTF_8!!)!!
//           }
//           var params = buffer.readString(charset)
//           Log.e(TAG, "请求参数： | " + params);
//       }catch ( e : IOException){
//           e.printStackTrace()
//       }
//    }

        val builder = Request.Builder()
            .url("https://www.gjzy352.top/prod-api/system/user/profile")

        return chain.proceed(builder.build())
    }
}

//    private void printParams(RequestBody body) {
//        Buffer buffer = new Buffer();
//        try {
//            body.writeTo(buffer);
//            Charset charset = Charset.forName("UTF-8");
//            MediaType contentType = body.contentType();
//            if (contentType != null) {
//                charset = contentType.charset(UTF_8);
//            }
//            String params = buffer.readString(charset);
//            Log.e(TAG, "请求参数： | " + params);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
