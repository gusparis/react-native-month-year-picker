//
//  RNMonthPicker+Toolbar.h
//  Pods
//
//  Created by Gustavo Paris on 22/09/2020.
//
#import <UIKit/UIKit.h>
#import <React/UIView+React.h>

@interface RNMonthPickerToolbar : UIView

@property (nonatomic, copy) RCTBubblingEventBlock onCancel;
@property (nonatomic, copy) RCTBubblingEventBlock onDone;
@property (nonatomic, copy) RCTBubblingEventBlock onNeutral;
@property (nonatomic, copy) RCTBubblingEventBlock onChange;

@property (nonatomic, assign) NSDate* value;
@property (nonatomic, assign) NSDate* minimumDate;
@property (nonatomic, assign) NSDate* maximumDate;
@property (nonatomic, assign) NSString* mode;
@property (nonatomic, assign) NSString* okButtonLabel;
@property (nonatomic, assign) NSString* cancelButtonLabel;
@property (nonatomic, assign) NSString* neutralButtonLabel;
@property (nonatomic, assign) BOOL autoTheme;

- (void)setLocale:(NSLocale *)locale;

@end
