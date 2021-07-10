const path = require('path');

module.exports = {
  projectRoot: path.resolve('example'),
  transformer: {
    getTransformOptions: async () => ({
      transform: {
        experimentalImportSupport: false,
        inlineRequires: true,
      },
    }),
  },
  watchFolders: [path.resolve('node_modules'), path.resolve('src')],
};
