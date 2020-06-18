import { Platform } from 'react-native';

export const ACTION_DATE_SET = 'dateSetAction';
export const ACTION_DISMISSED = 'dismissedAction';

const theme = {
  ios: {
    dark: {
      container: { backgroundColor: '#636366' },
      cancelButton: { color: '#f2f2f2' },
      okButton: { color: '#0a84ff' },
    },
    light: {
      container: { backgroundColor: '#f2f2f2' },
      cancelButton: { color: '#636366' },
      okButton: { color: '#007aff' },
    },
  },
};

export const useTheme = (isDarkMode) =>
  theme[Platform.OS][isDarkMode ? 'dark' : 'light'];
