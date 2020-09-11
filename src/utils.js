import { Platform } from 'react-native';

export const ACTION_DATE_SET = 'dateSetAction';
export const ACTION_DISMISSED = 'dismissedAction';
// prettier-ignore
export const Locale = {ENGLISH:'en',SPANISH:'es',FRENCH:'fr',RUSSIAN:'ru',GERMAN:'de',ITALIAN:'it',JAPANESE:'ja',KOREAN:'ko',CHINESE:'zh'};

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
