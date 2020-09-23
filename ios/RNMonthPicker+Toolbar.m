//
//  RNMonthPicker+Toolbar.m
//  CocoaAsyncSocket
//
//  Created by Gustavo Paris on 22/09/2020.
//
#import "RNMonthPicker+Toolbar.h"
#import "RNMonthPicker.h"

@implementation RNMonthPickerToolbar

UIToolbar *toolbar;
UIBarButtonItem *cancelButton;
UIBarButtonItem *doneButton;
RNMonthPicker *picker;

- (instancetype)initWithFrame:(CGRect)frame {
    CGRect screen = [[UIScreen mainScreen] bounds];
    if ((self = [super initWithFrame:frame])) {
        toolbar = [[UIToolbar alloc] initWithFrame:CGRectMake(0, 0, CGRectGetWidth(screen), 44)];
        cancelButton = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemCancel target:self action:@selector(onCancelButton)];
        doneButton = [[UIBarButtonItem alloc] initWithTitle:@"Done" style:UIBarButtonItemStyleDone target:self action:@selector(onDoneButton)];
        UIBarButtonItem *flexibleSpace = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemFlexibleSpace target:nil action:nil];
        [toolbar setItems:[NSArray arrayWithObjects:cancelButton, flexibleSpace,doneButton,nil]];
        picker = [RNMonthPicker new];
        picker.frame = CGRectMake(0, 44, CGRectGetWidth(screen), 200);
        UIColor* defaultColor;
        if (@available(iOS 13.0, *)) {
            defaultColor = [UIColor tertiarySystemBackgroundColor];
        } else {
            defaultColor = [UIColor whiteColor];
        }
        [self setValue:defaultColor forKey:@"backgroundColor"];
        [self addSubview:toolbar];
        [self addSubview:picker];
    }
    return self;
}

- (void)onCancelButton {
    _onCancel(@{});
}

- (void)onDoneButton {
    _onDone(@{});
}

- (void)setValue:(NSDate *)value {
    [picker setValue:value];
}

- (void)setMaximumDate:(NSDate *)maximumDate {
    [picker setMaximumDate:maximumDate];
}

- (void)setMinimumDate:(NSDate *)minimumDate {
    [picker setMinimumDate:minimumDate];
}

- (void)setOnChange:(RCTBubblingEventBlock)onChange {
    [picker setOnChange:onChange];
}

- (void)initPicker:(NSString *)locale {
    [picker initMonths:locale];
}

- (void)setOkButtonLabel:(NSString *)okButtonLabel {
    NSString *label = okButtonLabel;
    if (!okButtonLabel) {
        label = @"Done";
    }
    [doneButton setTitle:label];
}

- (void)setCancelButtonLabel:(NSString *)cancelButtonLabel {
    NSString *label = cancelButtonLabel;
    if (!cancelButtonLabel) {
        label = @"Cancel";
    }
    [cancelButton setTitle:label];
}

@end
