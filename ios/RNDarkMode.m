//
//  RNDarkMode.m
//  RNMonthPicker
//
//  Created by Gustavo Paris on 15/06/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//
#import "RNDarkMode.h"
#import <React/RCTLog.h>

@implementation RNDarkModeManager

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(isDarkMode:(RCTResponseSenderBlock)callback) {
    NSNumber* isDark = [NSNumber numberWithBool:false];
    if (@available(iOS 13.0, *)) {
        if (UITraitCollection.currentTraitCollection.userInterfaceStyle == UIUserInterfaceStyleDark) {
            isDark = [NSNumber numberWithBool:true];
        }
    }
    callback(@[[NSNull null], isDark]);
}

@end
