//
//  ImageHelper.m
//  MyApp
//
//  Created by 融易乐 on 2017/6/19.
//
//

#import "ImageHelper.h"

@implementation ImageHelper

/**
 *  压缩图片到指定文件大小
 *
 *  @param image 目标图片
 *  @param size  目标大小（最大值）
 *
 *  @return 返回的图片文件
 */
- (UIImage *)compressOriginalImage:(UIImage *)image toMaxDataSizeKBytes:(CGFloat)size{
    NSData * data = UIImageJPEGRepresentation(image, 1.0);
    CGFloat dataKBytes = data.length/1000.0;
    CGFloat maxQuality = 0.9f;
    CGFloat lastData = dataKBytes;
    while (dataKBytes > size && maxQuality > 0.01f) {
        maxQuality = maxQuality - 0.01f;
        data = UIImageJPEGRepresentation(image, maxQuality);
        dataKBytes = data.length / 1000.0;
        if (lastData == dataKBytes) {
            break;
        }else{
            lastData = dataKBytes;
        }
    }
     UIImage *compressedImage = [UIImage imageWithData:data];
    return compressedImage;
}

-(NSString*)imageToBase64:(UIImage *)img;{
    NSData *imgData = UIImageJPEGRepresentation(img, 1.0f);
    NSString *encodedImgStr = [imgData base64EncodedStringWithOptions:NSDataBase64Encoding64CharacterLineLength];
    
    NSLog(@"encodedImgStr---->%@",encodedImgStr);
    return encodedImgStr;
}

@end
