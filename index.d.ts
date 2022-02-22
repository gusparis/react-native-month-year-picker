import {FunctionComponent} from 'react';

export type EventTypes = 'dateSetAction'|'dismissedAction'|'neutralAction';

export interface MonthPickerProps {
    /**
    * Date change handler.
    * This is called when the user changes the date in the UI. 
    * It receives the event and the date as parameters.
    */
    onChange?: (event:EventTypes, newDate:Date) => void;
    /**
    * Defines the date value used in the component.
    */
    value:Date;
    /**
    * Defines the month list locale.
    * If not sent, it defaults to device's language.
    */
    locale?:string;
    /**
    * Defines the month list display mode.
    * It could be either full, short, number or shortNumber.
    * Default full.
    */
    mode?:'full'|'short'|'number'|'shortNumber';
    /**
    * Enables phone's UI Mode color recognition; for Android 10+ and iOS 13+.
    * Lower OS versions will always be Light Mode.
    * Default true.
    */
    autoTheme?:boolean;
    /**
    * Defines the maximum date that can be selected.
    * Use year and month constructor.
    */
    maximumDate?:Date;
    /**
    * Defines the minimum date that can be selected.
    * Use year and month constructor.
    */
    minimumDate?:Date;
    /**
    * Picker modal confirmation button text.
    * Default Done.
    */
    okButton?:string;
    /**
    * Picker modal cancelation button text.
    * Default Cancel.
    */
    cancelButton?:string;
    /**
    * Picker modal neutral button text.
    * If not sent, button won't appear.
    * Default null.
    */
    neutralButton?:string;
}

declare const MonthPicker: FunctionComponent<MonthPickerProps>;
export default MonthPicker;