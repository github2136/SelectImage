SelectImage  
[![](https://jitpack.io/v/github2136/SelectImage.svg)](https://jitpack.io/#github2136/SelectImage)  
使用该库还需要引用以下库  
Android-utils [![](https://jitpack.io/v/github2136/Android-utils.svg)](https://jitpack.io/#github2136/Android-utils)  
**compile 'com.google.code.gson:gson:2.8.2'**  
**compile 'com.github.bumptech.glide:glide:4.3.1'**  
**annotationProcessor 'com.github.bumptech.glide:compiler:4.3.1'**  
**compile 'com.github.chrisbanes:PhotoView:2.1.3'**  
**添加glide时使用maven { url 'https://maven.google.com' }**

**SelectImageActivity**选择图片  
**ARG_RESULT**返回的结果  
**ARG_SELECT_COUNT**可选择的图片总数  

**PhotoViewActivity**图片浏览  
**ARG_PHOTOS**图片路径  
**ARG_CURRENT_INDEX**查看的图片下标  

**CaptureActivity**拍摄图片  
**ARG_RESULT**返回的结果  
**ARG_FILE_PATH**保存目录可以为空  

**CropActivity**裁剪图片  
**ARG_RESULT**返回的结果  
**ARG_CROP_IMG**被裁剪的图片路径  
**ARG_ASPECT_X**裁剪框比例X  
**ARG_ASPECT_Y**裁剪框比例Y  
**ARG_OUTPUT_X**输出图片X  
**ARG_OUTPUT_Y**输出图片Y  
**ARG_OUTPUT_IMG**保存目录可以为空  
