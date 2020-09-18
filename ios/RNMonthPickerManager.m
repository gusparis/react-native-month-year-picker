//
//  RNMonthPickerManager.m
//  RNMonthPicker
//
//  Created by Gustavo Paris on 22/04/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RNMonthPickerManager.h"
#import "RNMonthPicker.h"

@implementation RNMonthPickerManager

RCT_EXPORT_MODULE()

- (UIView *)view
{
    return [RNMonthPicker new];
}

RCT_EXPORT_VIEW_PROPERTY(onChange, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(value, NSDate)
RCT_EXPORT_VIEW_PROPERTY(minimumDate, NSDate)
RCT_EXPORT_VIEW_PROPERTY(maximumDate, NSDate)
RCT_EXPORT_VIEW_PROPERTY(locale, NSString)

RCT_CUSTOM_VIEW_PROPERTY(textColor, UIColor, RNMonthPicker) {
    if(json) {
        [view setValue:[RCTConvert UIColor:json] forKey:@"textColor"];
    } else {
        UIColor* defaultColor;
        if (@available(iOS 13.0, *)) {
            defaultColor = [UIColor labelColor];
        } else {
            defaultColor = [UIColor blackColor];
        }
        [view setValue:defaultColor forKey:@"textColor"];
    }
}

RCT_CUSTOM_VIEW_PROPERTY(backgroundColor, UIColor, RNMonthPicker) {
    if(json) {
        [view setValue:[RCTConvert UIColor:json] forKey:@"backgroundColor"];
    } else {
        UIColor* defaultColor;
        if (@available(iOS 13.0, *)) {
            defaultColor = [UIColor tertiarySystemBackgroundColor];
        } else {
            defaultColor = [UIColor whiteColor];
        }
        [view setValue:defaultColor forKey:@"backgroundColor"];
    }
}

@end
