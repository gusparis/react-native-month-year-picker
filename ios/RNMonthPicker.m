//
//  RNMonthPicker.m
//  RNMonthPicker
//
//  Created by Gustavo Paris on 22/04/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RNMonthPicker.h"

#import <React/RCTConvert.h>
#import <React/RCTUtils.h>

#define DEFAULT_SIZE 408

@interface RNMonthPicker() <UIPickerViewDataSource, UIPickerViewDelegate>
@end

@implementation RNMonthPicker

NSCalendar *gregorian;
NSDateComponents *maxComponents;
NSDateComponents *minComponents;

NSMutableArray *months;
NSMutableArray *years;
NSInteger selectedMonthRow;
NSInteger selectedYearRow;

- (instancetype)initWithFrame:(CGRect)frame
{
    if ((self = [super initWithFrame:frame])) {
        self.delegate = self;
        gregorian = [NSCalendar currentCalendar];
        _value = nil;
        _minimumDate = nil;
        _maximumDate = nil;
    }
    return self;
}

RCT_NOT_IMPLEMENTED(- (instancetype)initWithCoder:(NSCoder *)aDecoder)

- (void)initMonths {
    months = [NSMutableArray array];
    NSDateFormatter *df = [[NSDateFormatter alloc] init];
    for(NSInteger i = 0; i < 12; i ++){
        [months addObject:[[df monthSymbols] objectAtIndex:(i)]];
    }
}

- (void)initYears:(NSInteger)selectedYear {
    years = [NSMutableArray array];
    for(NSInteger i = selectedYear - DEFAULT_SIZE; i <= selectedYear + DEFAULT_SIZE; i ++) {
        [years addObject: [NSNumber numberWithLong:i]];
    }
}

- (void)setEnableAutoDarkMode:(BOOL) enableAutoDarkMode {
    if (@available(iOS 13.0, *)) {
        if (!enableAutoDarkMode) {
            self.overrideUserInterfaceStyle = UIUserInterfaceStyleLight;
        }
    }
}

- (void)setValue:(nonnull NSDate *)value {
    if (value != _value) {
        NSDateComponents *selectedDateComponents = [gregorian components:(NSCalendarUnitMonth|NSCalendarUnitYear) fromDate:value];
        if (!_value) {
            [self initMonths];
            [self initYears: [selectedDateComponents year]];
            
            selectedMonthRow = [selectedDateComponents month] - 1;
            selectedYearRow = DEFAULT_SIZE;
            [self setSelectedRows: NO];
        }
        _value = value;
    }
}

-(void)setSelectedRows:(BOOL)animated {
    dispatch_async(dispatch_get_main_queue(), ^{
        [self selectRow:selectedMonthRow inComponent:0 animated:animated];
        [self selectRow:selectedYearRow inComponent:1 animated:animated];
    });
}

- (void)setMaximumDate:(NSDate *)maximumDate {
    _maximumDate = maximumDate;
    maxComponents = _maximumDate ? [gregorian components:NSCalendarUnitMonth | NSCalendarUnitYear fromDate:_maximumDate] : nil;
}

- (void)setMinimumDate:(NSDate *)minimumDate {
    _minimumDate = minimumDate;
    minComponents = _minimumDate ? [gregorian components:NSCalendarUnitMonth | NSCalendarUnitYear fromDate:_minimumDate] : nil;
}

#pragma mark - UIPickerViewDataSource protocol
// number of columns
- (NSInteger)numberOfComponentsInPickerView:(nonnull UIPickerView *)pickerView {
    return 2;
}

// number of rows
- (NSInteger)pickerView:(nonnull UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component {
    switch (component) {
        case 0:
            return [months count];
        case 1:
            return [years count];
            break;
        default:
            return 0;
    }
}

#pragma mark - UIPickerViewDelegate methods
// row titles
- (NSString *)pickerView:(nonnull UIPickerView *) pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component {
    switch (component) {
        case 0:
            return [NSString stringWithFormat:@"%@", months[row]];
        case 1:
            return [NSString stringWithFormat:@"%@", years[row]];
        default:
            return nil;
    }
}

- (void)getSelectedMonthRow:(NSInteger)row {
    NSInteger month = row  + 1;
    NSInteger year = [years[selectedYearRow] longValue];
    if (minComponents && year == [minComponents year] && month < [minComponents month]) {
        selectedMonthRow = [minComponents month] - 1;
    } else if (maxComponents && year == [maxComponents year] && month > [maxComponents month]) {
        selectedMonthRow = [maxComponents month] - 1;
    } else {
        selectedMonthRow = row;
    }
}

- (void)getSelectedYearRow:(NSInteger)row {
    NSInteger year = [years[row] longValue];
    if (minComponents && (year < [minComponents year])) {
        selectedYearRow = [years indexOfObject:[NSNumber numberWithInteger:[minComponents year]]];
    } else if (maxComponents && year > [maxComponents year]) {
        selectedYearRow = [years indexOfObject:[NSNumber numberWithInteger:[maxComponents year]]];
    } else {
        selectedYearRow = row;
    }
}

- (void)pickerView:(__unused UIPickerView *)pickerView
      didSelectRow:(NSInteger)row inComponent:(__unused NSInteger)component
{
    switch (component) {
        case 0:
            [self getSelectedMonthRow:row];
            break;
        case 1:
            [self getSelectedYearRow:row];
            [self getSelectedMonthRow:selectedMonthRow];
            break;
        default:
            return;
    }
    [self setSelectedRows: YES];
    if (_onChange) {
        _onChange(@{
            @"newDate": [NSString stringWithFormat: @"%@-%@", [NSString stringWithFormat: @"%ld", selectedMonthRow + 1], [NSString stringWithFormat: @"%@", years[selectedYearRow]]]
        });
    }
}

@end
