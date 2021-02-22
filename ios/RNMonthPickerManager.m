//
//  RNMonthPickerManager.m
//  RNMonthPicker
//
//  Created by Gustavo Paris on 22/04/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RNMonthPickerManager.h"
#import "RNMonthPicker+Toolbar.h"

@implementation RNMonthPickerManager

RCT_EXPORT_MODULE()

- (UIView *)view {
    return [RNMonthPickerToolbar new];
}

RCT_EXPORT_VIEW_PROPERTY(onCancel, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onDone, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onNeutral, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onChange, RCTBubblingEventBlock)

RCT_EXPORT_VIEW_PROPERTY(value, NSDate)
RCT_EXPORT_VIEW_PROPERTY(minimumDate, NSDate)
RCT_EXPORT_VIEW_PROPERTY(maximumDate, NSDate)
RCT_EXPORT_VIEW_PROPERTY(mode, NSString)
RCT_EXPORT_VIEW_PROPERTY(autoTheme, BOOL)
RCT_REMAP_VIEW_PROPERTY(okButton, okButtonLabel, NSString)
RCT_REMAP_VIEW_PROPERTY(neutralButton, neutralButtonLabel, NSString)
RCT_REMAP_VIEW_PROPERTY(cancelButton, cancelButtonLabel, NSString)

RCT_CUSTOM_VIEW_PROPERTY(locale, NSString, RNMonthPickerToolbar) {
    NSString *defaultLocale = [[NSLocale preferredLanguages] objectAtIndex:0];
    if ([json length] != 0) {
        defaultLocale = [RCTConvert NSString:json];
    }
    NSLocale *useLocale = [[NSLocale alloc] initWithLocaleIdentifier:defaultLocale];
    [view setLocale:useLocale];
}

@end
